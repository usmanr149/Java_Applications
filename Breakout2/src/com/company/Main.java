package com.company;

import javax.swing.*;

public class Main extends JFrame {


    public static void main(String[] args) {
	// write your code here
        new Main();
    }

    public Main()
    {
        add(new Sanke());
        setTitle("Breakout");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Commons.WIDTH, Commons.HEIGHT);
        setLocationRelativeTo(null);
        setIgnoreRepaint(true);
        setResizable(false);
        setVisible(true);
    }
}
