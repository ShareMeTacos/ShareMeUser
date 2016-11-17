package sharemeuser.Interfaces;

public interface ShareMeUserInterface
{
    void SetSettingsFileWithPath(String s);
    void SetUserIdFromSettings();
    boolean GetIsCorrect();
    void SetUserId();
    void SaveSettingsFile();
    void SendUserIdToServer();
}
