import javax.swing.*;

/**
 * Created by usman on 05/08/15.
 */
public class Main extends JFrame {

    public Main(){
        add(new Board());
        setTitle("Breakout");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Commons.WIDTH, Commons.HEIGHT);
        setLocationRelativeTo(null);
        setIgnoreRepaint(true);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args){
        new Main();
    }

}
