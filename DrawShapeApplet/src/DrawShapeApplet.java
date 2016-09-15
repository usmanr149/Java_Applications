import javax.swing.*;

/**
 * Created by usman on 23/07/15.
 */

public class DrawShapeApplet extends JFrame
{
    public DrawShapeApplet()
    {
        ImageIcon image = new ImageIcon ("/home/usman/Java_Application/DrawShapeApplet/src/ball.png");
                                                                        // Creates the image
        JLabel label = new JLabel (image); // add timage to the label
        add(label); //add the label to the frame
        setSize(250,340);
        //setVisible(true);
    }

    public static void main(String [] args)
    {
        DrawShapeApplet it = new DrawShapeApplet();
        it.setVisible(true);
    }
}

/*public class DrawShapeApplet extends JApplet {

    public void paint (Graphics g){

        setBackground(Color.WHITE);

        //draw arc
        g.setColor(Color.CYAN);
        g.drawString("Cyan Arc", 20, 40);
        g.drawRect(50, 50, 180, 120);
        g.drawArc(0, 60, 180, 100, -60, 160);

        //draw oval
        g.setColor(Color.MAGENTA);
        g.drawString("Magenta String", 260, 40);
        g.drawRect(290, 50, 180, 120);
        g.fillOval(305, 65, 150, 90);

        //draw rectangle
        g.setColor(Color.GREEN);
        g.drawString("Green Rectangle", 20, 200);
        g.drawRect(50, 210, 180, 120);
        g.fillRect(70, 220, 140, 100);

        //draw image
        g.setColor(Color.BLACK);
        g.drawString("Gray Rocks", 260, 200);
        g.drawRect(290, 210, 180, 120);


        //load image
        *//*
        MediaTracker tr = new MediaTracker(this);
        Image img = getImage(getCodeBase(), "ball.jpeg");
        tr.addImage(img,0);
        g.drawImage(img, 0, 0, this);
        *//*

        //Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image myImage = getImage(getDocumentBase(), "/home/usman/Java_Application/DrawShapeApplet/src/ball.jpg");

        g.drawImage(myImage, 300, 220, 150, 100, this);

    }

}*/
