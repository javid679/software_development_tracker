using System.Data.Common;
using System.IO;

namespace SoftwareDevelopmentTracker.ViewModel
{
    public class ProjectListModel
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public string CreatedBy { get; set; }
        public string CreatedOn { get; set; }

    }
}
