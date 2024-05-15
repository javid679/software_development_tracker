using SoftwareDevelopmentTracker.Models;
using SoftwareDevelopmentTracker.ViewModel;
using System.Collections.Generic;

namespace SoftwareDevelopmentTracker.Service
{
    public interface IAccountService
    {
        (bool, dynamic) RegisterUser(string Name, string Password, string Email);
        (bool, dynamic) SaveUser(int UserId, string Password, string Name = null, string Email = null);
        List<EntityClass> GetMembersList(int TaskId = 0);
    }
}
