import javax.swing.*;
import java.awt.*;

/**
 * Created by usman on 01/08/15.
 */
public class Main extends JFrame {

    public Main(){
        add(new Board());

        setResizable(false);
        pack();
        setTitle("Snake");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame ex = new Main();
                ex.setVisible(true);
            }
        });
    }

}
