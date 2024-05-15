using SoftwareDevelopmentTracker.Models;

namespace SoftwareDevelopmentTracker.ViewModel
{
    public class TaskCardModel : EntityClass
    {
        public string Type { get; set; }
        public string IssueStatus { get; set; }
    }
}
