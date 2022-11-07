// updated: 10/25
/// removed repetitive code and condensed into methods
/// updated and added descriptive comments
/// improved the read out of data to identify any errors
/// next: create the graph using the txt file
/// question: why is my estimate pi value not closer to 3.14

import java.util.Random;
import java.util.Arrays;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Simulate {
    public static void main(String[] args){
        /***
         * creating a square with sides = 2
         ***/
        int side = 2;
        Square square = new Square(side);
        int sqArea = square.getArea(side);
        /***
         * calculating the furthest distance from the side of the square to the center of the circle
         * visually: I am creating a triangle whose longest side is from the corner of the square to the center of the square. The other two sides of the triangle are equal to side/2
         ***/
        int sidesT = (side/2);
        double sqsidesT = Math.pow(sidesT, 2);
        double hypotenuse = Math.sqrt(sqsidesT+sqsidesT);
        /***
         * Creating two arrays: nVals and areaVals
         * nVals to hold the number of samples which increases by 10x with each increasing value
         * areaVals to hold the calculation of the area calculated
         ***/
        int [] nVals = {100, 1000, 10000, 100000, 1000000, 10000000, 100000000};
        double [] areaVals = new double[nVals.length];
        for(int i=0; i<areaVals.length; i++){
            areaVals[i] = calculateArea(nVals[i], hypotenuse, sqArea);
        }
        System.out.println("Array of estimate pi's with increasing n values: "+Arrays.toString(areaVals));

        /***
         * Ensuring the file is created and creating it if it is not
         * Writing the results to a csv file
         ****/
        createFile("darts.txt");
        writeToFile(areaVals, nVals,"darts.txt");
    }
    /***
     * Method to generate the random number from 0 to the hypotenuse (max) value
     * @param max
     * @return double
     */
    public static double generateRandomNumber(double max){
        Random rand = new Random();
        double randomNumber = rand.nextDouble(max); // generates a random number from 0 to 2 (unsure what the upper bound is supposed to be)
        return randomNumber;
    }
    /***
     * Method to create the file
     * @param filename
     * @return File
     ***/
    public static File createFile(String filename) {
        // creating file
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
     * Method to calculate the simulate calculating the area
     * Calculation: it is the proportion of data points inside the circle vs the data points inside the circle + outside the circle
     * @param n
     * @param hypotenuse
     * @param sqArea
     * @return double which is the estimation of the area of a circle
     ***/
    public static double calculateArea(int n, double hypotenuse, int sqArea){
        System.out.println("When n = "+ n);
        int inCircle = 0;
        for(int i=0; i<n; i++){
            double randomNumber = generateRandomNumber(hypotenuse);
            if (randomNumber <= 1){
                inCircle++;
            }
        }
        System.out.println("\tThe amount of numbers generated that are less than or equal to 1: "+inCircle);
        /***
         * Calculating the proportion of inCircle values to outCircle values
         * area of circle / area of square = # of values <= 1 / # of total values generated
         * looking for the area of circle
         ***/
        double ndouble = (double) n;
        double denominator = inCircle/ndouble;
        System.out.println("\tProportion: "+ inCircle+"/"+ndouble);
        double cArea = (double) denominator*sqArea;
        System.out.println("\tEstimated pi: "+ cArea);
        return cArea;
    }
}
