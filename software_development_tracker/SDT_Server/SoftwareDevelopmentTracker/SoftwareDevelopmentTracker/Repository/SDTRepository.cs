using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.ValueGeneration.Internal;
using SoftwareDevelopmentTracker.Context;
using SoftwareDevelopmentTracker.Enums;
using SoftwareDevelopmentTracker.Models;
using System;
using System.Collections.Generic;
using System.Linq;

namespace SoftwareDevelopmentTracker.Repository
{
    public class SDTRepository : ISDTRepository
    {
        private readonly SDTContext context;
        public SDTRepository(SDTContext context)
        {
            this.context = context;
        }

        public IQueryable<Members> GetMembers()
        { 
            return context.Members;
        }
        public IQueryable<Project> GetProjects()
        {
            return context.Project;
        }
        public IQueryable<ProjectUserMapping> GetProjectUserMappings()
        {
            return context.ProjectUserMapping;
        }
        public IQueryable<SdtConfiguration> GetSdtConfigurations()
        {
            return context.SdtConfiguration;
        }
        public IQueryable<Task> GetTasks()
        {
            return context.Task;
        }

        public IQueryable<Project>GetProjectsForUser(int UserId)
        {
            try
            {
                return GetProjects()
                    .Where(a => GetProjectUserMappings()
                                        .Where(q => q.UserId == UserId)
                                        .Select(r=> r.ProjectId)
                                .Contains(a.Id));
            }
            catch(Exception Ex)
            {
                throw Ex;
            }
        }

        public IQueryable<Task>GetTasksForProject(int Id)
        {
            try
            {
                return GetTasks()
                    .Where(a => a.ProjectId == Id);
            }
            catch(Exception Ex)
            {
                throw Ex;
            }
        }

        public IQueryable<Members> GetUsersForProject(int Id)
        {
            try
            {
                return GetMembers()
                    .Where(a => GetProjectUserMappings()
                                        .Where(q => q.ProjectId == Id)
                                        .Select(r => r.UserId)
                                .Contains(a.Id));
            }
            catch (Exception Ex)
            {
                throw Ex;
            }
        }

        public IQueryable<Members> GetUsersForTask(int Id)
        {
            try
            {
                return GetMembers()
                    .Where(a => GetProjectUserMappings()
                                                    .Where(q => q.ProjectId == GetTasks()
                                                    .FirstOrDefault(e => e.Id == Id)
                                                    .ProjectId)
                                                    .Select(r => r.UserId)
                                       .Contains(a.Id));
                    
            }
            catch(Exception Ex)
            {
                throw Ex;
            }
        }


        public string GetSdtConfigurationName(int Id)
        {
            try
            {
                return GetSdtConfigurations()
                    .FirstOrDefault(a => a.Id == Id)
                    .Name;
            }
            catch(Exception Ex)
            {
                throw Ex;
            }
        }



        public IQueryable<SdtConfiguration> GetSdtConfigurationList(string type)
        {
            try
            {
                return GetSdtConfigurations()
                    .Where(a => a.ConfigurationType == type);
            }
            catch (Exception Ex)
            {
                throw Ex;
            }
        }


        public string GetMemberName(int UserId)
        {
            return GetMembers()
                .FirstOrDefault(q => q.Id == UserId)
                .Name;
        }

        #region Update

        public Members UpdateMember(Members member)
        {
            member.ModifiedOn = DateTime.UtcNow;

            context.Members.Update(member);
            context.SaveChanges();
            return member;
        }

        public Members CreateMember(Members member)
        {

            member.CreatedOn = DateTime.UtcNow;

            context.Members.Add(member);
            context.SaveChanges();
            return member;
        }
        public Project UpdateProject(Project item)
        {
            item.ModifiedOn = DateTime.UtcNow;

            context.Project.Update(item);
            context.SaveChanges();
            return item;
        }

        public Project CreateProject(Project item)
        {

            item.CreatedOn = DateTime.UtcNow;

            context.Project.Add(item);
            context.SaveChanges();
            return item;
        }
        public Task UpdateTask(Task item)
        {
            item.ModifiedOn = DateTime.UtcNow;

            context.Task.Update(item);
            context.SaveChanges();
            return item;
        }

        public Task CreateTask(Task item)
        {

            item.CreatedOn = DateTime.UtcNow;

            context.Task.Add(item);
            context.SaveChanges();
            return item;
        }



        public void DeleteTask(int Id)
        {
            context.Task.Remove(GetTasks().FirstOrDefault(a=> a.Id == Id));
            context.SaveChanges();
        }


        public void CreateMapping(int UserId, int ProjectId)
        {
            context.ProjectUserMapping.Add(new ProjectUserMapping
            {
                ProjectId= ProjectId,
                UserId= UserId
            });

            context.SaveChanges();
        }

        #endregion

    }
}
