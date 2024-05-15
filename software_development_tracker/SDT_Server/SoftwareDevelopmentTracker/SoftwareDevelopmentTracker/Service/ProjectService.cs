using SoftwareDevelopmentTracker.Models;
using SoftwareDevelopmentTracker.Repository;
using SoftwareDevelopmentTracker.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;

namespace SoftwareDevelopmentTracker.Service
{
    public class ProjectService : IProjectService
    {
        private readonly ISDTRepository repo;
        public ProjectService(ISDTRepository repo)
        {
            this.repo = repo;
        }
        /// <summary>
        /// This method will fetch the project that a loggedin user is mapped to
        /// </summary>
        /// <param name="UserId">logged in user id</param>
        /// <returns>returns status of the api call</returns>
        public List<ProjectListModel> GetProjectListForUser(int UserId)
        {
            return repo.GetProjectsForUser(UserId)
                .Select(a=> new ProjectListModel
                {
                    Id = a.Id,
                    Name = a.Name,
                    Description = a.Description,
                    CreatedBy = repo.GetMemberName(UserId),
                    CreatedOn = String.Format("{0:MM/dd/yyyy}", a.CreatedOn)
                })
                .ToList();
        }

        /// <summary>
        /// This method will create new project/ if id is passed it will update the project details
        /// </summary>
        /// <param name="Name">name of the project</param>
        /// <param name="Description">Description of the project</param>
        /// <param name="userID">user id of the user editing/creating</param>
        /// <param name="projectId">project if they want to update project details</param>
        /// <returns></returns>
        public (bool,dynamic) SaveProject(string Name, string Description, int userID = 0, int projectId = 0)
        {
            
            if(projectId > 0)
            {
                Project existingProject = repo.GetProjects().FirstOrDefault(a => a.Id == projectId);
                if (existingProject != null)
                {

                    existingProject.Name = Name;
                    existingProject.Description = Description;
                    existingProject.ModifiedBy = userID;

                    repo.UpdateProject(existingProject);
                    return (true, "Project Updated");

                }
                else
                {
                    return(false,"Project Does not exist");
                }
            }

            Project newProject = repo.CreateProject(new Project
            {
                Name = Name,
                Description = Description,
                CreatedBy = userID
            });

            //Create the Map

            repo.CreateMapping(userID, newProject.Id);

            return (true, "Project Created");

        }

        /// <summary>
        /// This method check the accessability of a user to a project
        /// </summary>
        /// <param name="UserId">user id</param>
        /// <param name="ProjectId">project id</param>
        /// <returns></returns>
        public bool CanUserAccessProject(int UserId, int ProjectId)
        {
            return repo.GetProjectsForUser(UserId)
                .Any();
        }

        public List<TaskCardModel> GetTaskCards(int ProjectId)
        {
            return repo.GetTasksForProject(ProjectId)
                .ToList()
                .Select(a=> new TaskCardModel
                {
                    Id = a.Id,
                    Name = a.Name,
                    Type = repo.GetSdtConfigurationName((int)a.IssueTypeId),
                    IssueStatus = repo.GetSdtConfigurationName((int)a.StatusId)
                })
                .ToList();
        }

        /// <summary>
        /// This method will get the task data
        /// </summary>
        /// <param name="TaskID">task id of the task for which data is fetched</param>
        /// <returns></returns>
        public (bool,string,TaskDto) GetTaskData(int TaskID)
        {

            Task task = repo.GetTasks().FirstOrDefault(a => a.Id == TaskID);

            if(task == null)
            {
                return (false, "Task Does not exist", null);
            }

            return (true,"Success",new TaskDto
            {
                Id = task.Id,
                Name = task.Name,
                Description = task.Description,
                CreatedBy = task.CreatedBy > 0 ? repo.GetMemberName((int)task.CreatedBy) : string.Empty,
                CreatedOn = String.Format("{0:MM/dd/yyyy}", task.CreatedOn) ?? string.Empty,
                AssignedTo = task.AssignedTo > 0 ? repo.GetMemberName((int)task.AssignedTo) : string.Empty,
                ReportTo = task.ReportTo > 0 ? repo.GetMemberName((int)task.ReportTo) : string.Empty,
                ModifiedOn = String.Format("{0:MM/dd/yyyy}", task.ModifiedOn) ?? string.Empty,
                IssueStatus = repo.GetSdtConfigurationName((int)task.StatusId),
                IssueType = repo.GetSdtConfigurationName((int)task.IssueTypeId)
            });
        }


        /// <summary>
        /// This method will craete or update task
        /// </summary>
        /// <param name="Name"></param>
        /// <param name="Description"></param>
        /// <param name="AssignedTo"></param>
        /// <param name="ReportTo"></param>
        /// <param name="Status"></param>
        /// <param name="IssueType"></param>
        /// <param name="ProjectId"></param>
        /// <param name="TaskId"></param>
        /// <returns></returns>
        public (bool, dynamic) SaveTask(
            string Name, 
            string Description, 
            int AssignedTo, 
            int ReportTo,
            int Status, 
            int IssueType,
            int ProjectId,
            int TaskId = 0)
        {

            if(TaskId > 0)
            {
                Task existingTask = repo.GetTasks().FirstOrDefault(a => a.Id == TaskId);

                if(existingTask == null) {
                    return (false, "Task Does not exist.");
                }

                existingTask.Name = Name;
                existingTask.Description = Description;
                existingTask.AssignedTo = AssignedTo;
                existingTask.ReportTo = ReportTo;
                existingTask.StatusId = Status;
                existingTask.IssueTypeId = IssueType;
                existingTask.ProjectId = ProjectId;
                existingTask.ModifiedBy = ReportTo;

                repo.UpdateTask(existingTask);

                return (true, "Task Updated");

            }

            repo.CreateTask(new Task
            {
                Name = Name,
                Description = Description,
                AssignedTo = AssignedTo,
                ReportTo = ReportTo,
                StatusId = Status,
                IssueTypeId = IssueType,
                CreatedBy = ReportTo,
                ProjectId= ProjectId,
            });

            return (true, "Task Created");
        }

        /// <summary>
        /// This method will delete the task
        /// </summary>
        /// <param name="TaskId"></param>
        /// <returns></returns>
        public (bool,dynamic) DeleteTask(int TaskId)
        {
            repo.DeleteTask(TaskId);

            return (true, "Task Deleted");
        }


        /// <summary>
        /// This method will get the issue types
        /// </summary>
        /// <param name="Type"></param>
        /// <returns></returns>
        public List<EntityClass> GetConfigurationList(string Type)
        {
            return repo.GetSdtConfigurationList(Type)
                .Select(a => new EntityClass
                {
                    Id = a.Id,
                    Name = a.Name
                })
                .ToList();
        }


        /// <summary>
        /// This method will map user to particular project
        /// </summary>
        /// <param name="ProjectId">project id to which user to be mapped</param>
        /// <param name="UserId">user id who is need to mapped to a project</param>
        /// <returns></returns>
        public (bool,dynamic) MapUserWithProject(int ProjectId, int UserId)
        {
            if (!repo.GetProjects().Any(a => a.Id == ProjectId))
                return (false, "Project Does not exist");

            if (!repo.GetMembers().Any(a => a.Id == UserId))
                return (false, "User Does not exist");

            if (repo.GetProjectUserMappings().Any(a => a.UserId == UserId && a.ProjectId == ProjectId))
                return (false, "User already mapped");

            repo.CreateMapping(UserId, ProjectId);

            return (true, "Mapping Complete");


        }

        /// <summary>
        /// This method will update the task status
        /// </summary>
        /// <param name="TaskId">task id of task that need to be updated</param>
        /// <param name="StatusId">status id</param>
        /// <returns></returns>
        public (bool,dynamic) UpdateTaskStatus(int TaskId, int StatusId)
        {
            Task task = repo
                .GetTasks()
                .FirstOrDefault(a => a.Id == TaskId);

            if(!repo.GetSdtConfigurations().Any(a=> a.Id == StatusId)){
                return (false, "Invalid Status");
            }

            if(task.StatusId == StatusId)
            {
                return (false, "The Task status is already "+StatusId);
            }

            task.StatusId= StatusId;

            repo.UpdateTask(task);

            return (true, "Task Status updated.");
        }

        /// <summary>
        /// This method will get the members of the particular project
        /// </summary>
        /// <param name="ProjectId">project id of the project for which members list to be fetched</param>
        /// <returns></returns>
        public List<EntityClass> GetMembersListForProject(int ProjectId = 0)
        {
            try
            {
                return repo.GetUsersForProject(ProjectId)
                    .Select(a => new EntityClass
                    {
                        Id = a.Id,
                        Name = a.Name
                    })
                    .ToList();
            }
            catch (Exception Ex)
            {
                throw Ex;
            }
        }

    }
}
