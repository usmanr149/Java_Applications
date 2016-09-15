import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by usman on 23/11/14.
 */
public class RPS extends JFrame implements ActionListener {

    //Declare the buttons first
    JButton RockButton = new JButton("Rock");
    JButton ScissorButton = new JButton("Scissor");
    JButton PaperButton = new JButton("Paper");
    JLabel WhoWon = new JLabel("", JLabel.CENTER);

    int HumanPick, ComputerPick;
    boolean go;

    Image picture []= new Image[3];
    Thread counterThread;
    String filenames[] = {"rock.jpg", "scissor.jpg", "paper.jpg"};

    public RPS() {

        getContentPane().setLayout(new GridLayout(4, 1, 10, 10));

        this.setTitle("RPS");

        initComponents();
        //Set the size of the window and show it.
        this.setSize(275, 500);
        this.show();
    }


    private void initComponents()
    {
        //Put listeners on the buttons
        RockButton.addActionListener(this);
        getContentPane().add(RockButton);
        ScissorButton.addActionListener(this);
        getContentPane().add(ScissorButton);
        PaperButton.addActionListener(this);
        getContentPane().add(PaperButton);
        //String picTitles [] = {"Rock", "Scissor", "Paper"};
        WhoWon.setBorder(new BevelBorder(BevelBorder.LOWERED));
        getContentPane().add(WhoWon);
    }

    public static void main(String[] args){
        RPS theApp = new RPS();
        theApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == RockButton) {
            System.out.println("You picked Rock");
            HumanPick = 1;
        }

        if (e.getSource() == ScissorButton) {
            System.out.println("You picked Scissor");
            HumanPick = 2;
        }
        if (e.getSource() == PaperButton) {
            System.out.println("You picked Paper");
            HumanPick = 3;
        }

        ComputerPick = (int) (Math.random() * 3 + 1);

        switch (ComputerPick) {
            case 1:
                System.out.println("Computer picked Rock");
                break;
            case 2:
                System.out.println("Computer picked Scissor");
                break;
            case 3:
                System.out.println("Computer picked Paper");
                break;
        }

        execute(e);

    }

    private void execute(ActionEvent e) {
        counterThread = new Thread()
        {
            public void run()
            {
                if (HumanPick == ComputerPick)

                {
                    System.out.println("Draw");
                    WhoWon.setText("Draw");
                } else if ((HumanPick == 1 && ComputerPick == 2) || (HumanPick == 2 && ComputerPick == 3) || (HumanPick == 3 && ComputerPick == 1))

                {
                    System.out.println("You Win");
                    WhoWon.setText("You Win");
                } else

                {
                    System.out.println("You Lose");
                    WhoWon.setText("You Lose");
                }

            }
        };
        counterThread.start();
    }
}