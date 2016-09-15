import javax.swing.*;

/**
 * Created by usman on 19/07/15.
 */
public class HelloYou {

    String name;

    public HelloYou(String n){
        name = n;
    }

    public void sayHello(){
        String output = "Hello " + name + "!";
        JOptionPane.showMessageDialog(null, output, "HelloYou Program",
                JOptionPane.INFORMATION_MESSAGE);
    }
}