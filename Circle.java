/***
 * Class to model the entity Circle
 * @author Emma Closter
 * @version 0.1
 * Date of creation: September 7, 2022
 * Last Date Modified: September 15, 2022
 */

public class Circle{
    
    // data members
    private int radius;

    /***
    * Constructor with no parameters
    * @param none
    */
    public Circle(int r){ 
        radius = r; 
    }

    /***
    * Method to get the radius of the circle
    * @param none
    * @return radius
    */
    public int getRadius(){ 
        return radius; 
    }
}