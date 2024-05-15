using SoftwareDevelopmentTracker.Models;
using System.Collections.Generic;
using System.Linq;

namespace SoftwareDevelopmentTracker.Repository
{
    public interface ISDTRepository
    {
        IQueryable<Members> GetMembers();
        IQueryable<Project> GetProjects();
        IQueryable<ProjectUserMapping> GetProjectUserMappings();
        IQueryable<SdtConfiguration> GetSdtConfigurations();
        IQueryable<Task> GetTasks();
        Members UpdateMember(Members member);
        Members CreateMember(Members member);
        IQueryable<Members> GetUsersForTask(int Id);
        IQueryable<Project> GetProjectsForUser(int UserId);
        Project CreateProject(Project item);
        Project UpdateProject(Project item);
        IQueryable<Task> GetTasksForProject(int Id);
        string GetSdtConfigurationName(int Id);
        string GetMemberName(int UserId);
        Task UpdateTask(Task item);
        Task CreateTask(Task item);
        void DeleteTask(int Id);
        IQueryable<SdtConfiguration> GetSdtConfigurationList(string type);
        void CreateMapping(int UserId, int ProjectId);
        IQueryable<Members> GetUsersForProject(int Id);
    }
}
