import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;

/**
 * Created by usman on 29/07/15.
 */
public class SetIconImage extends JFrame{



    public SetIconImage(){
        createAndShowGUI();
    }

    private void createAndShowGUI(){
        setTitle("Icon image");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //use toolkit
        setIconImage(Toolkit.getDefaultToolkit().getImage("/home/usman/Java_Application/SetIconImage/src/dice.png"));
        setIconImage(new ImageIcon("/home/usman/Java_Application/SetIconImage/src/dice.png").getImage());
        try{
            setIconImage(ImageIO.read(new FileInputStream("/home/usman/Java_Application/SetIconImage/src/dice.png")));
        }catch (Exception e){ }

        setSize(400, 400);
        setVisible(true);
    }

    public static void main (String args[]){
        new SetIconImage();
    }

}
