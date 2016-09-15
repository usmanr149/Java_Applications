import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by usman on 26/07/15.
 */
public class BangPopApp extends JFrame implements ActionListener {

    JButton bangButton = new JButton("Bang");
    JButton popButton = new JButton("Pop");

    public BangPopApp(){
        this.setTitle("Bangs and Pops!");

        Container canvas = getContentPane();   //returns a container
        //Use a grid layout
        canvas.setLayout( new GridLayout(2,1) );

        //Now add the buttons for the buttons
        canvas.add(bangButton);
        canvas.add(popButton);

        bangButton.addActionListener(this);
        popButton.addActionListener(this);

        this.setSize(250, 150);
        this.show();
    }

    public static void main(String args[]){
        BangPopApp theApp = new BangPopApp();
        theApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Where did the event come from?
        if(e.getSource() == bangButton){
            JOptionPane.showMessageDialog(this, "Bang!");
        }
        else if(e.getSource() == popButton){
            JOptionPane.showMessageDialog(this, "pop");
        }
    }
}
