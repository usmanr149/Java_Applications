/* ThredDemo */

public class ThreadDemo {
    public static void main(String args[]) {

        // This is the first block of code
        Thread thread = new Thread() {
            public void run() {
                for (int i = 0; i < 10; i ++) {
                    System.out.println("hello this is thread one");
                }
            }

	    };

        // This is the second block of code
        Thread threadTwo = new Thread() {
            public void run() {
                for (int i = 0; i < 10; i ++) {
                    System.out.println("hello this is thread two");
                }
            }

	    };

        // These two statements are in the main method and begin the two
        // threads.
        // This is the third block of code
        thread.start();

        // This is the fourth block of code
        threadTwo.start();
    }

}
