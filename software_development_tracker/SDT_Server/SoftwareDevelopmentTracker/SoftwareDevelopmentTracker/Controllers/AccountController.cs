using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.DataProtection.AuthenticatedEncryption;
using Microsoft.AspNetCore.Mvc;
using SoftwareDevelopmentTracker.Service;
using SoftwareDevelopmentTracker.ViewModel;

namespace SoftwareDevelopmentTracker.Controllers
{
    [ApiController]
    [Route("Account")]

    /**
     * Account Controller handle all the API calls related users
     */
    public class AccountController : Controller
    {
        private readonly IAuthenticationService authService;
        private readonly IAccountService accountService;

        public AccountController(IAuthenticationService authService,
            IAccountService accountService)
        {
            this.authService = authService;
            this.accountService = accountService;
        }

        /*
         * "/Account/Login" - Endpoint for authenticating the user
         */
        [HttpPost("Login")]
        public IActionResult Login([FromBody]LoginParamClass item)
        {
            //Verify User
            var result = authService.VerifyUserPassword(item.Email, item.Password);

            if (result.Item1)
                return Ok(result.Item2);
            else
                return BadRequest(result.Item2);
        }

        /**
         * "/Account/Register" - Endpoint for handling user registration
         */
        [HttpPost("Register")]
        [AllowAnonymous]
        public IActionResult Register([FromBody] RegisterParamClass item)
        {

            (bool, dynamic) result = accountService
                .RegisterUser(item.Name, item.Password, item.Email);

            if(result.Item1)
                return Ok(result.Item2);
            else
                return BadRequest(result.Item2);

        }


        /**
         * "/Account/Save" - Endpoint for handling updating user details
         */
        [HttpPost("SaveUser")]
        public IActionResult SaveUser([FromBody] SaveUserParamClass item)
        {

            (bool, dynamic) result = accountService
                .SaveUser(item.UserId, item.Password,item.Name,item.Email);

            if (result.Item1)
                return Ok(result.Item2);
            else
                return BadRequest(result.Item2);

        }

        /**
         * "/Account/GetUserList" - Endpoint for getting all the users
         */
        [HttpGet("GetUserList")]
        public IActionResult GetUserList(int TaskId = 0)
        {
            return Ok(accountService.GetMembersList(TaskId));
        }

    }
}
