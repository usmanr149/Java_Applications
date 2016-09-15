/**
 * Created by usman on 19/07/15.
 */
public class ComputerJava {

    private String userName, password;

    private int accessCode;
    //0 = standard user
    //1 = write access to group drives
    //2 = read/write access to entire system

    public ComputerJava(){
        userName = "";
        password = "";
        accessCode = 0;
    }

    public ComputerJava(String name, String passwd, int code){
        userName = name;
        password = passwd;
        accessCode = code;
    }

    public void setUser(String name, String passwd, int code){
        userName = name;
        password = passwd;
        accessCode = code;
    }

    public String getUserName() { return userName; }

    public String getPassword() { return password; }

    public int getAccessCode() { return accessCode; }

}
