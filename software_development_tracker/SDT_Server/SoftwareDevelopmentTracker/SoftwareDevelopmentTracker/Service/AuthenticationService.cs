using SoftwareDevelopmentTracker.Context;
using SoftwareDevelopmentTracker.Models;
using SoftwareDevelopmentTracker.Repository;
using System;
using System.Diagnostics.Eventing.Reader;
using System.Linq;

namespace SoftwareDevelopmentTracker.Service
{
    public class AuthenticationService : IAuthenticationService
    {
        private readonly ISDTRepository repo;
        public AuthenticationService(ISDTRepository repo)
        {
            this.repo = repo;
        }
        /// <summary>
        /// This method will verify whether the user is existing or not
        /// </summary>
        /// <param name="email">user email id</param>
        /// <param name="password">user password</param>
        /// <returns></returns>
        public (bool,int) VerifyUserPassword(string email, string password)
        {
            try
            {
                //Check if User Exists
                Members user = repo.GetMembers()?
                    .Where(a=> a.Email == email)?
                    .FirstOrDefault();

                if (user == null)
                    return (false,0);

                //If User Exists Check if password is correct. 
                if(EncryptionService.DecryptString(user.Password) == password)
                    return (true,user.Id);

                return (false,0);
            }
            catch(Exception Ex)
            {
                throw Ex;
            }
        }
    }
}
