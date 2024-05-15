using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Data.SqlClient.DataClassification;
using SoftwareDevelopmentTracker.Enums;
using SoftwareDevelopmentTracker.Models;
using SoftwareDevelopmentTracker.Service;
using SoftwareDevelopmentTracker.ViewModel;
using System.Reflection;
using System.Security.Principal;
using System.Xml.Linq;

namespace SoftwareDevelopmentTracker.Controllers
{
    [ApiController]
    [Route("Project")]

    /*
     * Controller for handling all the Project and Task APIs
     */
    public class ProjectController : ControllerBase
    {
        private readonly IProjectService projectService;
        public ProjectController(IProjectService projectService) { 
            this.projectService = projectService;
        }

        /*
         *  "Project/GetProjects" - End point for getting projects
         */
        [HttpGet("GetProjects")]
        public IActionResult GetProjects(int UserID = 0)
        {
            //TODO : If UserID is 0 fetch it from the JWT Token.
            //if(UserID == 0)
            //{
                
            //}

            return Ok(projectService.GetProjectListForUser(UserID));
        }

        /*
         *  "Project/SaveProjects" - End point for creating project
         */
        [HttpPost("SaveProject")]
        public IActionResult SaveProject([FromBody]ProjectSaveParamClass item)
        {

            //TODO : If UserID is 0 fetch it from the JWT Token.
            //if(UserID == 0)
            //{

            //}

            var result = projectService.SaveProject(item.Name, item.Description, item.userID, item.projectId);

            if (result.Item1)
                return Ok(result.Item2);
            else
                return BadRequest(result.Item2);


        }

        /*
         *  "Project/GetTaskCards" - End point for getting task cards
         */
        [HttpGet("GetTaskCards")]
        public IActionResult GetTaskCards(int ProjectId, int UserId = 0)
        {
            //Verify if the User has access to the Project. 
            if (projectService.CanUserAccessProject(UserId, ProjectId))
                return Ok(projectService.GetTaskCards(ProjectId));

            return Unauthorized("Unauthorised User");
        }

        /*
         *  "Project/GetTaskData" - End point for getting task data
         */
        [HttpGet("GetTaskData")]
        public IActionResult GetTaskData(int TaskId)
        {
            var result = projectService.GetTaskData(TaskId);

            if (result.Item1)
                return Ok(result.Item3);
            else
                return BadRequest(result.Item2);
        }

        /*
         *  "Project/SaveTask" - End point for creating or updating a task
         */

        [HttpPost("SaveTask")]
        public IActionResult SaveTask([FromBody] TaskSaveParamClass item)
        {

            var result = projectService.SaveTask(item.Name, item.Description, item.AssignedTo, item.ReportTo, item.StatusId, item.IssueTypeId,item.ProjectId,item.TaskId);

            if (result.Item1)
                return Ok(result.Item2);
            else
                return BadRequest(result.Item2);

        }

        /*
         *  "Project/DeleteTask" - End point for deleting a task
         */
        [HttpGet("DeleteTask")]
        public IActionResult DeleteTask(int TaskId)
        {

            var result = projectService.DeleteTask(TaskId);

            if (result.Item1)
                return Ok(result.Item2);
            else
                return BadRequest(result.Item2);
        }

        /*
         *  "Project/GetStatusList" - End point for getting status list
         */
        [HttpGet("GetStatusList")]
        public IActionResult GetStatusList()
        {
            return Ok(projectService.GetConfigurationList(ConfigurationTypes.IssueStatus));
        }

        /*
         *  "Project/GetIssueTypeList" - End point for getting issue type
         */
        [HttpGet("GetIssueTypeList")]
        public IActionResult GetIssueTypeList()
        {
            return Ok(projectService.GetConfigurationList(ConfigurationTypes.IssueType));
        }

        /*
         *  "Project/MapUserWithProject" - End point for mapping user with project
         */
        [HttpGet("MapUserWithProject")]
        public IActionResult MapUserWithProject(int ProjectId, int UserId)
        {

            var result = projectService.MapUserWithProject(ProjectId, UserId);

            if (result.Item1)
                return Ok(result.Item2);
            else
                return BadRequest(result.Item2);
        }

        /*
         *  "Project/UpdateTaskStatus" - End point for updating task status
         */
        [HttpPut("UpdateTaskStatus")]
        public IActionResult UpdateTaskStatus([FromBody] TaskStatusUpdate item)
        {

            var result = projectService.UpdateTaskStatus(item.TaskId, item.StatusId);

            if (result.Item1)
                return Ok(result.Item2);
            else
                return BadRequest(result.Item2);
        }

        /*
         *  "Project/GetTaskCards" - End point for getting project members
         */
        [HttpGet("GetMembersListForProject")]
        public IActionResult GetMembersListForProject(int ProjectId)
        {
            return Ok(projectService.GetMembersListForProject(ProjectId));
        }
    }
}