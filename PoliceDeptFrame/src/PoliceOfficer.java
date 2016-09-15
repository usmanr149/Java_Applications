/**
 * Created by usman on 26/07/15.
 */
public class PoliceOfficer {

    private String name, rank;
    private int badgeNumber;
    private boolean bOnDuty, bAvailable;

    //These are class-wide variables
    private static int totalOfficers, totalOnDuty, totalAvailable;

    public PoliceOfficer(){
        name = "TBA";
        rank = "Patrol Officer";
        badgeNumber = 10000;
        totalOfficers++;    //Increment the totalOfficers eac time
        bOnDuty = false;    //an officer is created

    }

    public PoliceOfficer(String n, String r, int b){
        name = n;
        rank = r;
        badgeNumber = b;
        totalOfficers++;
        bOnDuty = false;
        bAvailable = false;
    }
    public void setOfficer(String n, String r, int b){
        name = n;
        rank = r;
        badgeNumber = b;
        bOnDuty = false;
        bAvailable = false;
    }
    public void setDutyStatus(boolean bOnJob){
        //When an officer on duty,
        //we assume he/she is available for a call
        bOnDuty = bOnJob;

        if(bOnDuty){
            totalOnDuty++;  //increment the class-wide counters
            totalAvailable++;
            bAvailable = true;
        }
        else{
            totalOnDuty--;
            totalAvailable--;
            bAvailable = false;
        }
    }

    public void setCallStatus(boolean bAvail){
        bAvailable = bAvail;
        if(bAvailable) totalAvailable++;    //adjust the count as
        else totalAvailable--;  //call status changes
    }

    public static int getTotalOfficers(){ return totalOfficers; }

    public static int getTotalOnDuty(){ return totalOnDuty; }

    public static int getTotalAvailable(){ return totalAvailable; }

    public String toString(){
        String desc;
        desc = rank + " " + name + " Badge Number: " + badgeNumber;
        if(bOnDuty){
            desc += "\n I am on duty";
            if(bAvailable)  desc += "and available for a call";
            else desc += "and not available for a call";
        }
        else{
            desc += "\n I am not on duty";
        }
        return desc;
    }

    protected void finalize(){
        totalOfficers--;
    }


}
