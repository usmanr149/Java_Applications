import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by usman on 26/07/15.
 */
public class ColorButton2 extends JFrame {

    JButton leftButton = new JButton("Left");
    JButton centerButton = new JButton("Center");
    JButton rightButton = new JButton("Right");

    public ColorButton2(){
        this.setTitle("Color My Buttons!");

        Container canvas = getContentPane();    //returns a container

        //Use a flow layout manager to place thebuttons
        canvas.setLayout( new FlowLayout() );

        //Now ass the button
        canvas.add(leftButton);
        canvas.add(centerButton);
        canvas.add(rightButton);

        this.setSize(250, 100);
        this.show();

        //Register the button events with the program.
        //anonymous inner classes
        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String buttonText = leftButton.getText();
                Color current = leftButton.getBackground();
                Color newColor = JColorChooser.showDialog(null,
                        "Pick a color for " + buttonText, current);
                if(newColor != null)    leftButton.setBackground(newColor);
            }
        });

        centerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String buttonText = centerButton.getText();
                Color current = centerButton.getBackground();
                Color newColor = JColorChooser.showDialog(null,
                        "Pick a color for " + buttonText, current);
                if(newColor != null)    centerButton.setBackground(newColor);
            }
        });

        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String buttonText = rightButton.getText();
                Color current = rightButton.getBackground();
                Color newColor = JColorChooser.showDialog(null,
                        "Pick a color for " + buttonText, current);
                if(newColor != null)    rightButton.setBackground(newColor);
            }
        });

        //Set the size of the window and show it
        this.setSize(250, 100);
        this.show();

    }

    public static void main(String args[]){
        ColorButton2 theApp = new ColorButton2();
        theApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
