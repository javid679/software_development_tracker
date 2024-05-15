using SoftwareDevelopmentTracker.Models;
using System.IO;

namespace SoftwareDevelopmentTracker.ViewModel
{
    public class TaskDto : EntityClass
    {
        public string Description { get; set; }
        public string CreatedBy { get; set; }
        public string AssignedTo { get; set;}
        public string ReportTo { get; set; }
        public string CreatedOn { get; set; }
        public string ModifiedOn { get; set;}
        public string IssueStatus { get; set; }
        public string IssueType { get; set; }

    }
}
