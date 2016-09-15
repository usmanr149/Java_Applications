package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String sPetName;

        sPetName = JOptionPane.showInputDialog(null,
                "Please enter you pets name.", "Love out animals",
                JOptionPane.QUESTION_MESSAGE);

        JOptionPane.showMessageDialog(null,
                sPetName + " is your pet's name.", "Love out animals",
                JOptionPane.INFORMATION_MESSAGE);

        System.exit(0);
    }
}
