/**
 * Created by usman on 16/07/15.
 */
public class Die {

    private int faceValue;

    public Die() {
        faceValue = 1;
    }

    public void tossDie(){
        faceValue = (int)(1 + Math.random()*6);
    }

    public int getFaceValue(){
        return faceValue;
    }

    public String toString(){
        String definition = "I am a die. My face value is " + faceValue;
        return definition;
    }

}
