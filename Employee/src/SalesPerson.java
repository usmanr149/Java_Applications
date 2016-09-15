/**
 * Created by usman on 08/08/15.
 */
public class SalesPerson extends Employee {

    protected String region;
    private float baseWkPay, totalWkSales, commission;

    public SalesPerson(){
        totalWkSales = 0.0f;
        commission = 0.10f;   //base rate is 10%
        baseWkPay = 250.0f;   //base pay is $250/week
    }

    public SalesPerson(String name, String SSN, String reg, float basePay, float salesTotal, float comm){
        super(name, SSN);
        region = reg;
        totalWkSales = salesTotal;
        commission = comm;
        baseWkPay = basePay;
    }

    @Override
    public void calcWeeklySalary() {
        weeklySalary = baseWkPay + totalWkSales*commission;
    }
}
