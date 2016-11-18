package sharemeuser.Classes;

import sharemeuser.Interfaces.ShareMeUserInterface;
import sharemeuser.Factories.ShareMeUserOsFactory;

public class ShareMeUser 
{
    private static final String OS = System.getProperty("os.name").toLowerCase();
            
    public static void main(String[] args) 
    {   
       ShareMeUserInterface smua = ShareMeUserOsFactory.GetShareMeUser();
       
       while(!smua.GetIsCorrect())
       {
           smua.SetUserId();
       }
       
       smua.SaveSettingsFile();
       
       smua.SendUserIdToServer();
    }
    
}
