import javax.swing.*;
import java.awt.*;

/**
 * Created by usman on 26/07/15.
 */
public class PoliceDeptFrame extends JFrame {

    //Create an array reference for policeOfficers
    PoliceOfficer officers[] = new PoliceOfficer[4];

    public static void main (String[] args){
        PoliceDeptFrame jpd = new PoliceDeptFrame();
        jpd.setDefaultCloseOperation( EXIT_ON_CLOSE );
    }

    //class construct initilizes values
    public PoliceDeptFrame(){
        this.setSize( 550, 200 );
        this.setTitle( "Java Police Dept" );

        //Before we can use the officers array, we must fill the array with
        //PoliceOfficer objects!!!
        for(int i = 0; i < officers.length; i++){
            officers[i] = new PoliceOfficer();
        }

        //Now set 4 officers
        officers[0].setOfficer("Joe Friday", "Detective", 997);
        officers[1].setOfficer("Bill Monday", "Patrolman", 2903);
        officers[2].setOfficer("Juanita Tuesday", "Seargent", 1225);
        officers[3].setOfficer("Sue Sunday", "Captain", 235);

        //officers are created in the off duty status
        //assign on duty and availability status
        officers[1].setDutyStatus(true);
        officers[2].setDutyStatus(true);
        officers[3].setDutyStatus(true);

        officers[1].setCallStatus(false);
        officers[3].setCallStatus(false);

        this.show();

    }

    public void paint(Graphics g){
        String output = "Total number of officers on the force: " + PoliceOfficer.getTotalOfficers();
        g.drawString(output, 25, 50);

        output = "Total number of officers on duty: " + PoliceOfficer.getTotalOnDuty();
        g.drawString(output, 25, 70);

        output = "Total number of officers on available for a call: " + PoliceOfficer.getTotalAvailable();
        g.drawString(output, 25, 90);

        for (int i = 0; i < officers.length; i++){
            g.drawString( officers[i].toString(), 25, 125 + i*15 );
        }

    }

}
