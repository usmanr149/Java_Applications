import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by usman on 26/07/15.
 */
public class BangPopApp2 extends JFrame {

    JButton bangButton = new JButton("Bang");
    JButton popButton = new JButton("Pop");

    public BangPopApp2(){
        this.setTitle("Bangs and Pops!");
        this.setSize(250, 150);
        Container canvas = getContentPane();   //returns a container
        //Use a grid layout
        canvas.setLayout( new GridLayout(2,1) );

        //Now add the buttons for the buttons
        canvas.add(bangButton);
        canvas.add(popButton);

        //Use an anonymous class with the Action Listener
        bangButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Bang!");
            }
        });
        popButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Pop!");
            }
        } );
        this.show();
    }

    public static void main(String args[]){
        BangPopApp2 theApp = new BangPopApp2();
        theApp.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }

}
