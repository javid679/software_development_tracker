namespace SoftwareDevelopmentTracker.Service
{
    public interface IAuthenticationService
    {
        (bool, int) VerifyUserPassword(string email, string password);
    }
}
