import java.text.DecimalFormat;

/**
 * Created by usman on 23/11/14.
 */
public class Circle {

    double radius, area, circumference;

    public Circle() {
        radius = 1.0;
        calcAreaAndCircumference();
    }

    public Circle(double r) {
        radius = r;
        calcAreaAndCircumference();
    }

    public void setRadius(double r) {
        radius = r;
        calcAreaAndCircumference();
    }


    private void calcAreaAndCircumference() {
        area = Math.PI * Math.pow(radius, 2);
        circumference = 2.0 * Math.PI * radius;

    }

    //This gets the specified variables
    public double getRadius() {
        return radius;
    }

    public double getArea() {
        return area;
    }

    public double getCircumference() {
        return circumference;

    }

    public String toString()
    {
        DecimalFormat df = new DecimalFormat("0.000");

        String desc;
        desc = "I am a Circle. \n Radius = " + df.format(radius) + "\n Area = " + df.format(area) + "\n Circumf = " +
                df.format(circumference);

        return desc;
    }

}