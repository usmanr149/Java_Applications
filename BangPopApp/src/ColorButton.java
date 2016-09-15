import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by usman on 26/07/15.
 */
public class ColorButton extends JFrame implements ActionListener {

    JButton leftButton = new JButton("Left");
    JButton centerButton = new JButton("Center");
    JButton rightButton = new JButton("Right");

    public ColorButton(){
        this.setTitle("Color My Buttons!");

        Container canvas = getContentPane();    //returns a container

        //Use a flow layout manager to place thebuttons
        canvas.setLayout( new FlowLayout() );

        //Now ass the button
        canvas.add(leftButton);
        canvas.add(centerButton);
        canvas.add(rightButton);

        //add action listener
        leftButton.addActionListener(this);
        centerButton.addActionListener(this);
        rightButton.addActionListener(this);

        this.setSize(250, 100);
        this.show();

    }

    public static void main(String args[]){
        ColorButton theApp = new ColorButton();
        theApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonText;
        Color current, newColor;
        JButton whichButton = new JButton();

        //Determine which button
        if(e.getSource() == leftButton) whichButton = leftButton;
        else if(e.getSource() == centerButton)   whichButton = centerButton;
        else if(e.getSource() == rightButton)   whichButton = rightButton;

        //get the button's text
        buttonText = whichButton.getText();

        //Get the button's current color
        current = whichButton.getBackground();

        //Show the colorChooser with the button's label and current color
        newColor = JColorChooser.showDialog(null, "Pick a color for " + buttonText, current);
        if(newColor != null)    whichButton.setBackground(newColor);

    }
}
