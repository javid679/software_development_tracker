using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using SoftwareDevelopmentTracker.Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SoftwareDevelopmentTracker.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class WeatherForecastController : ControllerBase
    {
        private static readonly string[] Summaries = new[]
        {
            "Freezing", "Bracing", "Chilly", "Cool", "Mild", "Warm", "Balmy", "Hot", "Sweltering", "Scorching"
        };

        private readonly ILogger<WeatherForecastController> _logger;
        private readonly ISDTRepository _repo;
        public WeatherForecastController(ILogger<WeatherForecastController> logger,
            ISDTRepository repo)
        {
            _logger = logger;
            _repo = repo;
        }

        [HttpGet]
        public IActionResult Get()
        {

            return Ok(_repo.GetMembers());

        }
    }
}
