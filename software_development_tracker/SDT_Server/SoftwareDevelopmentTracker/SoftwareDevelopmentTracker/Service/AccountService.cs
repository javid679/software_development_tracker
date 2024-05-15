using SoftwareDevelopmentTracker.Models;
using SoftwareDevelopmentTracker.Repository;
using System;
using System.Collections.Generic;
using System.Linq;

namespace SoftwareDevelopmentTracker.Service
{
    public class AccountService : IAccountService
    {
        private readonly ISDTRepository repo;
        public AccountService(ISDTRepository repo)
        {
            this.repo = repo;
        }

        /// <summary>
        /// This method will handle register user
        /// </summary>
        /// <param name="Name"> name entered by user</param>
        /// <param name="Password">password entered by the user</param>
        /// <param name="Email">email entered by the user</param>
        /// <returns>status of the api call</returns>
        public (bool, dynamic) RegisterUser(string Name, string Password, string Email)
        {
            try
            {

                if(repo.GetMembers()
                    .Any(a=> a.Email.ToLower().Trim() == Email.ToLower().Trim()))
                {
                    return (false, "Email already exists");
                }

                Members user = new Members
                {
                    Password = EncryptionService.EncryptString(Password),
                    Name = Name,
                    Email = Email
                };

                repo.CreateMember(user);

                return (true, "User Added Successfully");

            }
            catch(Exception ex)
            {
                throw ex;
            }
        }

        /// <summary>
        /// This method create/update user in DB
        /// </summary>
        /// <param name="UserId">user id should be given if want to edit</param>
        /// <param name="Password">password</param>
        /// <param name="Name">name</param>
        /// <param name="Email">email</param>
        /// <returns></returns>
        public (bool, dynamic) SaveUser(int UserId, string Password, string Name = null, string Email = null)
        {
            try
            {

                Members user = repo
                    .GetMembers()
                    .FirstOrDefault(a => a.Id == UserId);

                user.Password = EncryptionService.EncryptString(Password);
                user.Email = Email ?? user.Email;
                user.Name = Name ?? user.Name;

                repo.UpdateMember(user);

                return (true, "User Updated Successfully");

            }
            catch (Exception Ex)
            {
                return (false, Ex);
            }
        }

        public List<EntityClass> GetMembersList(int TaskId = 0) {
            try
            {
                if (TaskId > 0) {
                    return repo.GetUsersForTask(TaskId)
                        .Select(a => new EntityClass
                        {
                            Id = a.Id,
                            Name = a.Name
                        })
                        .ToList();
                }
                else
                {
                    return repo.GetMembers()
                        .Select(a => new EntityClass
                        {
                            Id = a.Id,
                            Name = a.Name
                        })
                        .ToList();
                }
            }
            catch(Exception Ex)
            {
                throw Ex;
            }
        }


    }
}
