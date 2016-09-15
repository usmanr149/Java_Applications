/**
 * Created by usman on 08/08/15.
 */
public class Secretary extends Employee {

    protected String dept;
    private float weeklyHours, payRate;

    public Secretary(){
        dept = "";
        weeklyHours = 0.0f;
        payRate = 15.0f;
    }

    public Secretary(String name, String SSN, String dept, float hours, float rate){
        super(name, SSN);
        this.dept = dept;
        weeklyHours = hours;
        payRate = rate;
    }

    @Override
    public void calcWeeklySalary() {
        weeklySalary = weeklyHours * payRate;
        if(weeklyHours > 40)
            weeklySalary += (weeklySalary - 40)*1.5*payRate;
    }
}
