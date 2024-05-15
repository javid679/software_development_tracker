using SoftwareDevelopmentTracker.Models;
using SoftwareDevelopmentTracker.ViewModel;
using System.Collections.Generic;

namespace SoftwareDevelopmentTracker.Service
{
    public interface IProjectService
    {
        List<ProjectListModel> GetProjectListForUser(int UserId);
        bool CanUserAccessProject(int UserId, int ProjectId);
        (bool, dynamic) SaveProject(string Name, string Description, int userID = 0, int projectId = 0);
        (bool, string, TaskDto) GetTaskData(int TaskID);
        List<TaskCardModel> GetTaskCards(int ProjectId);
        (bool, dynamic) SaveTask(
            string Name,
            string Description,
            int AssignedTo,
            int ReportTo,
            int Status,
            int IssueType,
            int ProjectId,
            int TaskId = 0);

        (bool, dynamic) DeleteTask(int TaskId);
        List<EntityClass> GetConfigurationList(string Type);
        (bool, dynamic) MapUserWithProject(int ProjectId, int UserId);

        (bool, dynamic) UpdateTaskStatus(int TaskId, int StatusId);
        List<EntityClass> GetMembersListForProject(int ProjectId = 0);
    }
}
