using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.HttpsPolicy;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using SoftwareDevelopmentTracker.Context;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using SoftwareDevelopmentTracker.Repository;
using Microsoft.AspNetCore.DataProtection.AuthenticatedEncryption;
using SoftwareDevelopmentTracker.Service;

namespace SoftwareDevelopmentTracker
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }


        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddControllers();

            services.AddCors(options => options.AddDefaultPolicy(
                builder => builder
                .AllowAnyOrigin()
                .AllowAnyHeader()
                .AllowAnyMethod()
                )); // Make sure you call this previous to AddMvc

            var asd = Configuration.GetConnectionString("EmployeeDBConncetion");

            services.AddDbContextPool<SDTContext>(options =>
            options.UseSqlServer(Configuration.GetConnectionString("EmployeeDBConncetion")));

            //Dependency Registration
            services.AddTransient<ISDTRepository, SDTRepository>();
            services.AddTransient<ISDTContext, SDTContext>();
            services.AddTransient<IAuthenticationService, AuthenticationService>();
            services.AddTransient<IAccountService, AccountService>();
            services.AddTransient<IProjectService, ProjectService>();




        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {


            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseHttpsRedirection();

            app.UseRouting();

            app.UseCors();

            app.UseAuthorization();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });


        }
    }
}
