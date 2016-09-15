import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 * Created by usman on 08/08/15.
 */
public class ThreeEmployeeFrame extends JFrame implements ActionListener {

    JButton secButton = new JButton("Show Secretary Info");
    JButton salesButton = new JButton("Show SalesPerson Info");
    JButton bossButton = new JButton("ShowManager Info");

    //Secretary info is name, SSN, dept, hours worked, hourly pay
    Secretary sec = new Secretary("Gloria", "555-11-2222", "Western States", 45.0f, 14.00f);

    //Sales input is name, SSN, region, base wkpay, total sales, comm rate
    SalesPerson sales = new SalesPerson("Janet", "222-33-4444", "Phoenix Area", 250.0f, 6000.0f, 0.20f);

    //Manager input is name, SSN, yearly pay
    Manager boss = new Manager("Paul", "333-44-5555", "Arizona and New Mexico", 65000f);

    public ThreeEmployeeFrame(){
        this.setTitle("Secretary, SalesPerson and Boss");
        Container canvas = getContentPane();
        canvas.setLayout(new GridLayout(3,1));

        //Now add the button to the canvas
        canvas.add(secButton);
        canvas.add(salesButton);
        canvas.add(bossButton);

        //Now need to add the listeners for the button
        secButton.addActionListener(this);
        salesButton.addActionListener(this);
        bossButton.addActionListener(this);

        //Set the size of the window and show it.
        this.setSize(350, 175);
        this.show();
    }

    public static void main(String args[]){
        ThreeEmployeeFrame theApp = new ThreeEmployeeFrame();
        theApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String output = "", title = "";
        DecimalFormat df = new DecimalFormat("0.00");
        float pay;

        if(e.getSource() == secButton){
            sec.calcWeeklySalary();
            title = "Job Title: Secretary";
            output = "Name: " + sec.getName();
            pay = sec.getWeeklySalary();
            output += "\nWeekly Pay: $ " + df.format(pay);
        }
        else if(e.getSource() == salesButton){
            sales.calcWeeklySalary();
            title = "Job Title: Sales Person";
            output = "Name: " + sales.getName();
            pay = sales.getWeeklySalary();
            output += "\nWeekly Pay: $ " + df.format(pay);
        }
        else if(e.getSource() == bossButton){
            boss.calcWeeklySalary();
            title = "Job Title: Manager";
            output = "Name: " + boss.getName();
            pay = boss.getWeeklySalary();
            output += "\nWeekly Pay: $ " + df.format(pay);
        }

        JOptionPane.showMessageDialog(this, output, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
