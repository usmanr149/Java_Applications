import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by usman on 22/11/14.
 */
public class GoCounter extends JFrame implements ActionListener, Runnable{

    //Declare the buttons first

    JButton goButton = new JButton("GO");
    JButton stopButton = new JButton("STOP");

    JLabel goStopLabel = new JLabel("Start and stop the counter.", JLabel.CENTER);
    JLabel counterLabel = new JLabel("Look in the system window", JLabel.CENTER);

    //Thread reference
    Thread counterThread;

    int counter;
    boolean go;

    public GoCounter(){

        this.setTitle("Good go and and Stop Counter!");
        Container canvas = getContentPane();
        canvas.setLayout( new GridLayout(4,1) );

        //Add controls to the container
        canvas.add(goStopLabel);
        canvas.add(goButton);
        canvas.add(stopButton);
        canvas.add(counterLabel);

        //Put listeners on the buttons
        goButton.addActionListener(this);
        goButton.setBackground(Color.BLUE);
        stopButton.addActionListener(this);
        stopButton.setBackground(Color.CYAN);

        //Set the size of the window and show it.
        this.setSize(275,150);
        this.show();


    }

    public static void main (String args[])
    {
        GoCounter theApp = new GoCounter();
        theApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == goButton)
        {
            System.out.println("Go button push!");

            //If there is no thread start one now
            if(counterThread == null)
            {
                go = true;
                counterThread = new Thread(GoCounter.this);
                counterThread.start();
            }
        }
        else if(e.getSource() == stopButton)
        {
            System.out.println("Stop button pushed");
            go = false;

            counterThread = null;
        }
    }

    public void run(){
        while(go)
        {
            counter++;
            System.out.println("Counter = " + Integer.toString(counter));

                    try
                    {
                        counterThread.sleep(100);
                    }
                    catch(InterruptedException ie){}
        }
    }

}
