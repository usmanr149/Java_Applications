import javax.swing.*;

/**
 * Created by usman on 17/07/15.
 */
public class TellMeAJoke2 {

    private String question, answer;

    public TellMeAJoke2(){
        question = "Why did the chicken cross the road?";
        answer = "To go where no chicken has gone before.";

        askTheQuestion();
        tellThePunchLine();

    }

    public TellMeAJoke2(String q, String a){
        question = q;
        answer = a;

        askTheQuestion();
        tellThePunchLine();

    }

    public void askTheQuestion(){
        JOptionPane.showMessageDialog(null, question, "Tell Me A Joke", 3);
    }

    public void tellThePunchLine(){
        JOptionPane.showMessageDialog(null, answer, "Tell Me A Joke", 1);
    }

}