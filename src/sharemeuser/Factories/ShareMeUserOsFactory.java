package sharemeuser.Factories;

import javax.swing.JOptionPane;
import sharemeuser.Classes.*;
import sharemeuser.Interfaces.ShareMeUserInterface;

public class ShareMeUserOsFactory 
{
    private final static String OS = System.getProperty("os.name").toLowerCase();

    public static ShareMeUserInterface GetShareMeUser()
    {
        if (isWindows()) 
        {
            return new ShareMeUserWindows();
        } 
        else if (isMac()) 
        {
            return new ShareMeUserMac();
	} 
        else if (isUnix()) 
        {
            return new ShareMeUserLinux();
	} 
        else if (isSolaris()) 
        {
            JOptionPane.showMessageDialog(null, "This operating system is not supported.", "OH NO!", 0);
            System.exit(0);
        } 
        else 
        {
            JOptionPane.showMessageDialog(null, "We could not detect your operating system.", "OH NO!", 0);
            System.exit(0);
        }
        
        return null;
    }
    
    private static boolean isWindows() 
    {
        return (OS.contains("win"));
    }

    private static boolean isMac() 
    {
        return (OS.contains("mac"));    
    }
    
    private static boolean isUnix() 
    {
        return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"));
    }

    private static boolean isSolaris() 
    {
        return (OS.contains("sunos"));
    }
}
