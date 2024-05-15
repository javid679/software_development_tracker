using Microsoft.EntityFrameworkCore;
using SoftwareDevelopmentTracker.Models;

namespace SoftwareDevelopmentTracker.Context
{
    public class SDTContext : DbContext, ISDTContext
    {
        public SDTContext(DbContextOptions<SDTContext> options): base(options) { 
        
        }
        public DbSet<Members> Members { get; set; }
        public DbSet<Project> Project { get; set; }
        public DbSet<SdtConfiguration> SdtConfiguration { get; set; }
        public DbSet<Task> Task { get; set; }
        public DbSet<ProjectUserMapping> ProjectUserMapping { get; set; }




        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {

            modelBuilder.Entity<Members>().ToTable("Members");
            modelBuilder.Entity<Members>().HasKey(a => a.Id);


            modelBuilder.Entity<Project>().ToTable("Project");
            modelBuilder.Entity<Project>().HasKey(a => a.Id);


            modelBuilder.Entity<SdtConfiguration>().ToTable("SdtConfiguration");
            modelBuilder.Entity<SdtConfiguration>().HasKey(a => a.Id);


            modelBuilder.Entity<Task>().ToTable("Task");
            modelBuilder.Entity<Task>().HasKey(a => a.Id);

            modelBuilder.Entity<ProjectUserMapping>().ToTable("ProjectUserMapping");
            modelBuilder.Entity<ProjectUserMapping>().HasKey(a=> a.Id);
        }

    }
}
