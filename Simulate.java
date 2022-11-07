// updated: 11/7
// approximating pi

import java.util.Random;
import java.util.Arrays;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Simulate {
    public static void main(String[] args){
        /***
         * creating a "square" with sides = 2 and assigning its radius to variable radius
         ***/
        Circle c = new Circle(10);
        double side = c.getSide();
        double radius = c.getRadius();

        /***
         * Creating two arrays: nVals and areaVals
         * nVals to hold the number of samples which increases by 10x with each increasing value
         * areaVals to hold the calculation of the area calculated
         ***/
        int [] nVals = {100, 1000, 10000, 100000, 1000000, 10000000, 100000000};
        double [] areaVals = new double[nVals.length];
        for(int i=0; i<areaVals.length; i++){
            areaVals[i] = calculateArea(nVals[i], radius);
        }
        System.out.println("Array of estimate pi's with increasing n values: "+Arrays.toString(areaVals));

        /***
         * Ensuring the file is created and creating it if it is not
         * Writing the results to a csv file
         ****/
        createFile("darts.txt");
        writeToFile(areaVals, nVals,"darts.txt"); // rather than plotting n vs pi, plot log(n) vs pi (y-axis pi, x-axis n)
    }
    /***
     * Method to generate a random number from the minimum to the maxiumum
     * @param max
     * @return random number of type double
     */
    public static double generateRandomNumber(double min, double max){
        Random rand = new Random();
        double randomNumber = min + rand.nextDouble(max-min);
        return randomNumber;
    }
    /***
     * Method to create the file
     * @param filename
     * @return File
     ***/
    public static File createFile(String filename) {
        File file = new File(filename);
        try{
            if(file.createNewFile()){
                System.out.println("File created: "+ file.getName());
            }
        } 
        catch (IOException e){
            System.out.println("An error occurred.");
        }
        return file;
    }
    /*** 
     * Method to write to the file
     * @param array called areaVals type double
     * @param array called n type int
     * @param String with the filename
     * @return void
    ***/
    public static void writeToFile(double [] areaVals, int [] n, String file){
        try{
            FileWriter writer = new FileWriter(file);
            PrintWriter printer = new PrintWriter(writer);
            printer.printf("%s\t\t%s\n", "Area", "n");
            for (int i = 0; i< areaVals.length; i++){
                printer.printf("%,.3f\t\t%d\n", areaVals[i], n[i]);
            }
            printer.close();
        }
        catch (IOException e){}
    }
    /***
     * Method to simulate calculating the area
     * Calculation: it is the proportion of data points inside the circle vs the data points inside the circle + outside the circle
     * @param n
     * @param sqArea
     * @return double which is the estimation of the area of a circle
     ***/
    public static double calculateArea(int n, double radius){
        System.out.println("When n = "+ n);
        int inCircle = 0;
        for(int i=0; i<n; i++){
            double x = generateRandomNumber(-radius, radius);
            double y = generateRandomNumber(-radius, radius);
            double sides = Math.pow(x, 2) + Math.pow(y, 2);
            double hypotenuse = Math.pow(sides, 0.5);
            if (hypotenuse <= radius){ 
                inCircle++;
            }
        }
        System.out.println("\tThe amount of numbers generated that are less than or equal to 1: "+inCircle);
        /***
         * Calculating the proportion of inCircle values to outCircle values
         * area of circle / area of square = nCircle / n
         *  pi ~ 4 * nCircle/n --> (pi*radius^2)/(4*radius^2) ~ nCircle/n --> pi/4 ~ nCircle/n --> pi ~4*(nCircle/n)
         ***/
        double ndouble = (double) n;
        double denominator = inCircle/ndouble;
        System.out.println("\tProportion: "+ inCircle+"/"+ndouble);
        double cArea = (double) denominator*(4); // because pi = (inCircle / n)*4 because the radius' cancel out
        System.out.println("\tEstimated pi: "+ cArea);
        return cArea;
    }
}
