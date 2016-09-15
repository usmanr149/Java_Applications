/**
 * Created by usman on 08/08/15.
 */
public class Manager extends Employee {

    protected String dept;
    private float yearlySalary;

    public Manager(){
        yearlySalary = 60000;
    }

    public Manager(String name, String SSN, String dept, float yearPay){
        super(name, SSN);
        this.dept = dept;
        yearlySalary = yearPay;
    }

    @Override
    public void calcWeeklySalary() {
        weeklySalary = (float)(yearlySalary/52.0);
    }
}
