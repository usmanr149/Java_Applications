import javax.swing.*;

/**
 * Created by usman on 17/07/15.
 */
public class CoffeeLotto {

    private int userCreamBalls[] = new int[4];
    private int userCoffeeBall;
    private int gameCreamBalls[] = new int[4];
    private int gameCoffeeBall;

    private boolean bBallChoices[] = new boolean[11];

    private Sort2 sorter = new Sort2();

    private int count, newball;
    private String sTitle, sCream, sCoffee;

    public CoffeeLotto(){
        reset();  //call reset to initialize bBallChoices array
    }

    private void reset(){
        //Because the applet can be restarted, we need to
        //set our boolean array to false here.
        for(int i = 0; i < bBallChoices.length; i++)
            bBallChoices[i] = false;
    }

    public void askUserForChoices(){   //asks the user to enter his/her choices
        //Call the reset() to make sure bBallChoices contains false values
        reset();

        count = 0;

        sTitle = "User\'s Coffee Ball";
        sCoffee = JOptionPane.showInputDialog(null, "Enter a number between 1-20", sTitle,
                JOptionPane.QUESTION_MESSAGE);

        userCoffeeBall = Integer.parseInt(sCoffee);

        sTitle = "Cream Ball Guess # " + Integer.toString(count + 1);

        sCream = JOptionPane.showInputDialog(null, "Enter a number between 1-10", sTitle,
                JOptionPane.QUESTION_MESSAGE);

        while (count < 4){
            if( count == 0)  //guess is a keeper
            {
                newball = Integer.parseInt(sCream);
                userCreamBalls[count] = newball;
                bBallChoices[newball] = true;
                count++;
            }
            else{
                sTitle = "Cream Ball Guess # " + Integer.toString(count + 1);

                System.out.println("newball = " + newball);

                sCream = JOptionPane.showInputDialog(null, "Enter a number between 1-10",
                        sTitle, JOptionPane.QUESTION_MESSAGE);

                newball = Integer.parseInt(sCream);
                if(bBallChoices[newball] == false){
                    userCreamBalls[count] = newball;  //keep it
                    bBallChoices[newball] = true;
                    count++;
                }
                else {
                    JOptionPane.showMessageDialog(null, "Sorry, you have already guessed " +
                    sCream, "Duplicate Guess!", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        sorter.bubblesort(userCreamBalls);
    }

    public void generateGameBalls(){

        boolean gotADup;
        count = 0;
        while ( count < 4 ){
            if(count == 0){
                gameCreamBalls[count] = (int)(Math.random()*10 + 1);
                count++;
            }
            else {
                newball = (int)(Math.random()*10 + 1);

                //check it against previous balls to see if we have a duplicate
                gotADup = false;
                for (int i = 0; i < count; i++){
                    if(newball == gameCreamBalls[i]){
                        gotADup = true;
                        break;
                    }
                }
                if(gotADup == false){      //no dup, keep it, increment count
                    gameCreamBalls[count] = newball;
                    count++;
                }
            }
        }

        gameCoffeeBall = (int)(Math.random()*20 + 1);

        sorter.bubblesort(gameCreamBalls);

    }

    public boolean doWeHaveAWinner(){

        if(gameCoffeeBall != userCoffeeBall)
            return  false;

        for(int i = 0; i < gameCreamBalls.length; i++){
            if( gameCreamBalls[i] != userCreamBalls[i])
                return false;
        }

        return true;

    }

    public int getGameCoffeeBall(){
        return gameCoffeeBall;
    }

    public int[] getGameCreamBalls(){
        return gameCreamBalls;
    }

    public int getUserCoffeeBall(){
        return userCoffeeBall;
    }

    public int[] getUserCreamBalls(){
        return userCreamBalls;
    }

}
