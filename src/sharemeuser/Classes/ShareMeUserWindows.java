package sharemeuser.Classes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import javax.swing.JOptionPane;
import sharemeuser.Interfaces.ShareMeUserInterface;

public class ShareMeUserWindows implements ShareMeUserInterface 
{

    private final String UserIdFilePath = "C:\\Users\\Public\\Documents\\shareMesettings.txt";
    private boolean isCorrect;
    private String userId;
    private File shareMeSettings;

    public ShareMeUserWindows()
    {
        this.SetSettingsFileWithPath(UserIdFilePath);
        this.SetUserIdFromSettings();
    }
    
    @Override
    public void SetSettingsFileWithPath(String s) 
    {
        shareMeSettings = new File(s);
    }

    @Override
    public void SetUserIdFromSettings() 
    {
        if(this.shareMeSettings.exists())
        {
            Scanner input;
            
            try 
            {
                input = new Scanner(shareMeSettings);
                
                if(input.hasNext())
                {
                    userId = input.nextLine();
                }
                
                input.close();
            } 
            catch (FileNotFoundException ex) 
            {
                JOptionPane.showMessageDialog(null, "There was a problem importing your id from the settings file.");
            } 
        }
        else
        {
            userId = "";
            
            try 
            {
                shareMeSettings.createNewFile();
            } 
            catch (IOException ex) 
            {
                JOptionPane.showMessageDialog(null, "There was a problem creating Share Me settings file.");
            }
        }
    }

    @Override
    public boolean GetIsCorrect() 
    {
        return this.isCorrect;
    }

    @Override
    public void SetUserId() 
    { 
        if(!"".equals(userId))
            {
                Object[] options = 
                {
                    "Yes",
                    "No",
                };
                
                int n = JOptionPane.showOptionDialog(null,
                    "Is "+userId+" the correct id?",
                    "Correct user id?",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
                            
                if(n == 0)
                {
                    isCorrect = true;
                }
                else
                {
                    userId = JOptionPane.showInputDialog("What is your user id?");
                }
            }
            else
            {
                userId = JOptionPane.showInputDialog("What is your user id?");
            }
    }

    @Override
    public void SaveSettingsFile() 
    {
        FileWriter output;
        
        try
        {
            output = new FileWriter(this.shareMeSettings.getAbsoluteFile());
            try (BufferedWriter bw = new BufferedWriter(output)) 
            {
                bw.write(userId);
            }
            output.close();
        } 
        catch (IOException ex) 
        {
            JOptionPane.showMessageDialog(null, "There was a problem saving your id.");
        } 
    }

    @Override
    public void SendUserIdToServer() 
    {
        File target = new File("\\\\maanas\\maanas\\IMC\\ACS\\ShareMe\\UserId.txt");
        try 
        {
            Files.copy(this.shareMeSettings.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } 
        catch (IOException ex) 
        {
            JOptionPane.showMessageDialog(null, "There was a problem sending your id to the server.");
        }
    }
}
