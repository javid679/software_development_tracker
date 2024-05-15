namespace SoftwareDevelopmentTracker.ViewModel
{
    public class TaskSaveParamClass
    {
        public string Name { get; set; }
        public string Description { get; set; }
        public int AssignedTo { get; set; }
        public int ReportTo { get; set; }
        public int StatusId { get; set; }
        public int IssueTypeId { get; set; }
        public int ProjectId { get; set; }
        public int TaskId { get; set; } = 0;
    }

    public class TaskStatusUpdate
    {
        public int TaskId { get; set; }
        public int StatusId { get; set; }
    }
}
