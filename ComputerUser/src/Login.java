import javax.swing.*;

/**
 * Created by usman on 19/07/15.
 */
public class Login {

    private String loginName, loginPassword;
    private String loginDetails;

    public Login(){

        loginName = "";
        loginPassword = "";
        loginDetails = "No user and password has been obtained.";

    }

    public void askForLoginInfo(){
        loginName = JOptionPane.showInputDialog("Enter User Name");
        loginPassword = JOptionPane.showInputDialog("Enter Password");
    }

    public boolean validateUser(ComputerJava userArray[]){
        //Return true if valid false if not valid
        //Login details are written into the details String

        for(int i = 0; i < userArray.length; i++){
            if(loginName.equals( userArray[i].getUserName() ) ){
                if( loginPassword.equals(userArray[i].getUserName() ) ){
                    loginDetails = "Valid user and password.";
                    return true;
                }
                else{
                    loginDetails = "Valid user but invalid passwrod";
                }
            }
        }
        loginDetails = "User name not found";
        return false;

    }

    public String loginReport(){
        return loginDetails;
    }

    public String toString(){
        String current = "User: " + loginName + " (password: " + loginPassword + ")";
        return current;
    }

}
