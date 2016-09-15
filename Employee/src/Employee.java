/**
 * Created by usman on 08/08/15.
 */
public abstract class Employee {

    protected String name, SSN;
    protected float weeklySalary;

    public Employee(){
        name = "";
        SSN = "";
    }

    public Employee(String name, String SSN){
        this.name = name;
        this.SSN = SSN;
    }

    public String getName(){
        return name;
    }

    public float getWeeklySalary(){
        return weeklySalary;
    }

    //The salary calculation method is abstract because we use a
    //different formula for each type of employee.
    public abstract void calcWeeklySalary();

}
