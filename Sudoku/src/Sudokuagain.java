import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sudokuagain extends JApplet implements ActionListener {
    SudokuGame board = new SudokuGame();
    JTextField[][] cell = new JTextField[9][9];
    JLabel MessageText = new JLabel(" ");
    JButton NewPuzzle;
    JButton NextMove;

    // PlayerFont is for the moves added by the player.
    // StartFont is for the initial numbers that are filled in.
    Font PlayerFont = new Font(null, Font.PLAIN, 14);
    Font StartFont = new Font(null, Font.BOLD, 14);

    public void init() {
        // Make GUI.
        // Make a main panel that holds everything. This only has the
        // usefulness of allowing a border (I think).
        JPanel MainPanel = new JPanel();
        MainPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        getContentPane().add(MainPanel);

        // The BorderLayout layout manager lets me put the board in the middle
        // and expand as much as necessary while keeping the message at the
        // bottom always the same.
        MainPanel.setLayout(new BorderLayout());

        //Make JPanel to hold board (text fields)
        JPanel BoardPanel = new JPanel();
        BoardPanel.setBorder(BorderFactory.createEtchedBorder());
        MainPanel.add(BoardPanel, BorderLayout.CENTER);
        //Add buttons in 3x3 array
        int rctr, cctr;
        for (rctr=0; rctr<9; rctr++)
            for (cctr=0; cctr<9; cctr++){
                cell[rctr][cctr] = new JTextField("", 1);
                cell[rctr][cctr].setHorizontalAlignment(JTextField.CENTER);
                cell[rctr][cctr].setFont(StartFont);
                cell[rctr][cctr].addActionListener(this);
                cell[rctr][cctr].setActionCommand("text:"+rctr+":"+cctr);
                BoardPanel.add(cell[rctr][cctr]);
            }
        BoardPanel.setLayout(new GridLayout(9,9));
        //Make JPanel to hold new game button and message
        JPanel BottomPanel = new JPanel();
        BottomPanel.setLayout(new BoxLayout(BottomPanel, BoxLayout.PAGE_AXIS));
        MainPanel.add(BottomPanel, BorderLayout.PAGE_END);
        //Add buttons for new games
        NewPuzzle = new JButton("Enter new puzzle");
        NewPuzzle.addActionListener(this);
        NewPuzzle.setActionCommand("newpuzzle");
        NewPuzzle.setAlignmentX(Component.CENTER_ALIGNMENT);
        BottomPanel.add(Box.createRigidArea(new Dimension(0,5)));
        BottomPanel.add(NewPuzzle);

        NextMove = new JButton("Solve next move");
        NextMove.addActionListener(this);
        NextMove.setActionCommand("nextmove");
        NextMove.setAlignmentX(Component.CENTER_ALIGNMENT);
        BottomPanel.add(Box.createRigidArea(new Dimension(0,5)));
        BottomPanel.add(NextMove);
        BottomPanel.add(Box.createRigidArea(new Dimension(0,5)));

        //Add text box
        MessageText.setHorizontalAlignment(JLabel.CENTER);
        MessageText.setAlignmentX(Component.CENTER_ALIGNMENT);
        //MessageText.setBorder(BorderFactory.createEtchedBorder());
        BottomPanel.add(MessageText);

        // Some puzzles for debugging
        /*
        cell[2][0].setText("2");
        cell[2][2].setText("7");
        cell[2][3].setText("5");
        cell[1][4].setText("9");
        cell[2][6].setText("1");
        cell[0][8].setText("3");
        cell[1][8].setText("8");

        cell[4][1].setText("6");
        cell[5][1].setText("8");
        cell[5][2].setText("3");
        cell[3][3].setText("6");
        cell[5][3].setText("9");
        cell[4][4].setText("5");
        cell[3][5].setText("8");
        cell[5][5].setText("7");
        cell[3][6].setText("3");
        cell[3][7].setText("5");
        cell[4][7].setText("1");

        cell[7][0].setText("4");
        cell[8][0].setText("8");
        cell[6][2].setText("9");
        cell[7][4].setText("2");
        cell[6][5].setText("1");
        cell[6][6].setText("2");
        cell[6][8].setText("6");
        */
        // websudoku.com, evil puzzle # 1,768,849,787
        /*
        cell[2][0].setText("1");
        cell[2][2].setText("7");
        cell[0][3].setText("8");
        cell[1][3].setText("1");
        cell[2][4].setText("5");
        cell[0][5].setText("4");
        cell[0][7].setText("7");
        cell[1][8].setText("5");

        cell[3][1].setText("1");
        cell[4][1].setText("8");
        cell[3][2].setText("9");
        cell[4][2].setText("6");
        cell[4][4].setText("1");
        cell[4][6].setText("3");
        cell[5][6].setText("7");
        cell[4][7].setText("5");
        cell[5][7].setText("2");

        cell[7][0].setText("5");
        cell[8][1].setText("3");
        cell[8][3].setText("7");
        cell[6][4].setText("3");
        cell[7][5].setText("6");
        cell[8][5].setText("1");
        cell[6][6].setText("8");
        cell[6][8].setText("4");
        */
        // websudoku.com evil puzzle # 3,333,808,030
        cell[1][0].setText("3");
        cell[0][1].setText("9");
        cell[1][1].setText("4");
        cell[2][2].setText("6");
        cell[2][4].setText("9");
        cell[0][6].setText("6");
        cell[0][7].setText("8");
        cell[1][8].setText("2");
        cell[2][8].setText("4");

        cell[5][1].setText("5");
        cell[3][2].setText("8");
        cell[4][3].setText("2");
        cell[3][4].setText("7");
        cell[5][4].setText("1");
        cell[4][5].setText("8");
        cell[5][6].setText("8");
        cell[3][7].setText("9");

        cell[6][0].setText("7");
        cell[7][0].setText("1");
        cell[8][1].setText("8");
        cell[8][2].setText("4");
        cell[6][4].setText("4");
        cell[6][6].setText("1");
        cell[7][7].setText("7");
        cell[8][7].setText("6");
        cell[7][8].setText("3");
    }

    public void actionPerformed(ActionEvent evt) {
        int rctr, cctr;
        String[] cmdparsed = evt.getActionCommand().split(":");
        if(cmdparsed.length<1)
            return;
        if(cmdparsed[0].equals("newpuzzle")) {
            // Clear space, set all fonts back to bold
            for (rctr=0; rctr<9; rctr++)
                for (cctr=0; cctr<9; cctr++) {
                    cell[rctr][cctr].setText("");
                    cell[rctr][cctr].setFont(StartFont);
                    cell[rctr][cctr].setEditable(true);
                    board.setValue(rctr, cctr, null);
                    MessageText.setText("");
                }
        } else if(cmdparsed[0].equals("nextmove")) {

            // Validate current entries & disable editing these
            // Really only needs to be done first time after new puzzle started.
            for (rctr=0; rctr<9; rctr++)
                for (cctr=0; cctr<9; cctr++) {
                    if(cell[rctr][cctr].getText().equals("")) {
                        //board.setValue(rctr, cctr, null);
                        cell[rctr][cctr].setFont(PlayerFont);
                    }
                    else {
                        try {
                            board.setValue(rctr, cctr,
                                    Integer.decode(cell[rctr][cctr].getText()));
                            cell[rctr][cctr].setEditable(false);
                        }
                        catch(NumberFormatException e) {
                            //board.setValue(rctr, cctr, null);
                            cell[rctr][cctr].setText("");
                            cell[rctr][cctr].setFont(PlayerFont);
                        }
                    }
                }
            // Get next move
            Point NextPlay = board.findNextPlay();

            // Put new move on the board
            if(NextPlay!=null) {
                Integer NumToFill = board.getValue(NextPlay);
                cell[NextPlay.x][NextPlay.y].setText(NumToFill.toString());
                cell[NextPlay.x][NextPlay.y].setEditable(false);
            }
            else
                MessageText.setText("No play found");
        }
    }
}