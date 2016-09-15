/**
 * Created by usman on 17/07/15.
 */
public class Jokes2 {

    TellMeAJoke2 monty;

    public Jokes2(){
        String quest = "Why doesn't an elephant have a glove compartment?";
        String answ = "Because it has a trunk";

        //Call the TellMeAJoke2 constructor and pass in our own joke.
        monty = new TellMeAJoke2(quest, answ);

        monty.askTheQuestion();
        monty.tellThePunchLine();

    }

    public static void main( String args[] ){
        new Jokes2();
        System.exit(0);
    }


}