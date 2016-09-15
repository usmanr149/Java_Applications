package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created by usman on 13/07/15.
 */
public class Sanke extends JPanel implements Commons {

    String message = "Game Over";

    public Sanke(){
        setBackground(Color.MAGENTA);
    }

    public void paint(Graphics g) {
        super.paint(g);

        Font font = new Font("Verdana", Font.BOLD, 18);
        FontMetrics metr = this.getFontMetrics(font);

        g.setColor(Color.BLACK);
        g.drawString(message, (Commons.WIDTH - metr.stringWidth(message)) / 2, Commons.WIDTH / 2);

    }


}