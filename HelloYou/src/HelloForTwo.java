/**
 * Created by usman on 19/07/15.
 */
public class HelloForTwo {

    //We create two HelloYou objects.

    HelloYou h1 = new HelloYou("Marc");
    HelloYou h2 = new HelloYou("Gina");

    public static void main(String args[]){
        new HelloForTwo();
        System.exit(0);
    }

    public HelloForTwo(){
        h1.sayHello();
        h2.sayHello();
    }

}
