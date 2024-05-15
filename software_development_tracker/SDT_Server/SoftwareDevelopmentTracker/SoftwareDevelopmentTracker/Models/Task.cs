using System;
using System.Collections.Generic;

// Code scaffolded by EF Core assumes nullable reference types (NRTs) are not used or disabled.
// If you have enabled NRTs for your project, then un-comment the following line:
// #nullable disable

namespace SoftwareDevelopmentTracker.Models
{
    public partial class Task : EntityClass
    {
        public string Description { get; set; }
        public int? CreatedBy { get; set; }
        public DateTime? CreatedOn { get; set; }
        public int? ModifiedBy { get; set; }
        public DateTime? ModifiedOn { get; set; }
        public int? AssignedTo { get; set; }
        public int? ReportTo { get; set; }
        public int? StatusId { get; set; }
        public int? IssueTypeId { get; set; }
        public int? ProjectId { get; set; }
    }
}
