import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by usman on 24/11/14.
 */
public class UpDownCounters extends JFrame implements ActionListener {

    JButton upButton = new JButton("Count Up");
    JButton downButton = new JButton("Count Down");
    JLabel upLabel = new JLabel("0", JLabel.CENTER);
    JLabel downLabel = new JLabel("100", JLabel.CENTER);

    private Thread upThread;
    private Thread downThread;

    public UpDownCounters()
    {
        //GridLayout inputs (rows, cols, horiGap, vertGap)
        getContentPane().setLayout(new GridLayout(2, 2, 10, 10));
        this.setTitle("Thread Counter");

        initComponents();
        this.setSize(275,150);
        this.show();
    }

    private void initComponents()
    {
        upButton.addActionListener(this);
        getContentPane().add(upButton);
        downButton.addActionListener(this);
        getContentPane().add(downButton);

        upLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        getContentPane().add(upLabel);

        downLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        getContentPane().add(downLabel);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == upButton)
        {
            forwardButtonActionPerformed(ae);
        }
        else if (ae.getSource() == downButton)
        {
            backwardButtonActionPerformed(ae);
        }
    }

    private void backwardButtonActionPerformed(ActionEvent evt)
    {
        //Turn off the button so we cannot press it again.
        downButton.setEnabled(false);

        downThread = new Thread()
        {
            public void run()
            {
                for(int i = 100; i > -1; i--)
                {
                    downLabel.setText(String.valueOf(i));
                    try
                    {
                        Thread.currentThread().sleep(50);
                    }catch (InterruptedException e) {}
                }
                downButton.setEnabled(true);
            }
        }; //close of inner class

        //Now we call downThread's start so that ir calls run().
    downThread.start();
    }

    private void forwardButtonActionPerformed(ActionEvent evt)
    {
        //Turn off the button so we cannot press it again.
        upButton.setEnabled(false);

        upThread = new Thread()
        {
            public void run()
            {
                for(int i = 0; i < 101; i++)
                {
                    upLabel.setText(String.valueOf(i));
                    try
                    {
                        Thread.currentThread().sleep(50);
                    }catch (InterruptedException e) {}
                }
                upButton.setEnabled(true);
            }
        }; //close of inner class

        //Now we call downThread's start so that ir calls run().
        upThread.start();
    }

    public static void main(String[] args)
    {
        UpDownCounters theApp = new UpDownCounters();
        theApp.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
