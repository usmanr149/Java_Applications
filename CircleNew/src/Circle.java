import java.text.DecimalFormat;

/**
 * Created by usman on 16/07/15.
 */
public class Circle {

    double radius, area, circumference;

    //Constructor, performs initialization tasks.
    //Sets the radius to 1 and calls the method to
    //calculate the area and circumference of the circle.
    public Circle(){
        radius = 1.0;
        calcAreaAndCircumference();
    }

    //Constructor, sets the radius to the input value
    //and calls method to calc the area and circumference
   /* public Circle(double r){
        radius = r;
        calcAreaAndCircumference();
    }
    */

    //setRadius is passed a radius value.
    public void setRadius(double r){
        radius = r;
        calcAreaAndCircumference();
    }

    private void calcAreaAndCircumference(){
        area = Math.PI*Math.pow(radius, 2);
        circumference = 2.0*Math.PI*radius;
    }

    //This method returns the specified variable
    public double getRadius(){
        return radius;
    }
    public double getArea(){
        return area;
    }
    public double getCircumference(){
        return circumference;
    }

    public String toString(){
        DecimalFormat df = new DecimalFormat("0.000");

        String desc;
        desc = "I am a Circle.\n Radius = " + df.format(radius) +
                "\n Area = " + df.format(area) + " \n Circumference = " + df.format(circumference);

        return desc;
    }

}
