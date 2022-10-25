// 10.25.22   

import java.util.Random;
import java.util.Arrays;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.io.IOException;

public class Simulate {
    public static void main(String[] args){
        // creating a square with sides = 2
        int side = 2;
        Square square = new Square(side);
        int sqArea = square.getArea(side);
        System.out.println("The area of the square is: "+sqArea);
        System.out.println("The distance from the center of the square to the edges is: "+side/2);
        /***
         * calculating the furthest fistance from the side of the square to the center of the circle
         * visually: I am creating a triangle whose longest side is from the corner of the square to the center of the square. The other two sides of the triangle are equal to side/2
         */
        int sidesT = (side/2);
        double sqsidesT = Math.pow(sidesT, 2);
        double hypotenuse = Math.sqrt(sqsidesT+sqsidesT);
        System.out.println("The length of the hypotenuse is: "+hypotenuse);

        // creating a circle with radius side/2 (to fit inside the square)
        Circle circle = new Circle(side/2);
        int radius = circle.getRadius();
        System.out.println("The radius of the circle is: "+ radius);

        /***
         * simulate a number from 0-1  
         * ** if the number is less than 1 it is inscribed in the circle and the square if the number is
         * ** if the number is greater than 1 it is not inscribed in the circle
         * ** if the number is equal to 1, it counts as in the circle
         * */
        // when n = 100
        int inCircle100 = 0;
        for(int i=0; i<100; i++){
            double randomNumber = generateRandomNumber(hypotenuse);
            if (randomNumber <= 1){
                inCircle100++;
            }
        }
        /***
         * Calculating the proportion of inCircle values to outCircle values
         * area of circle / area of square = # of values <= 1 / # of total values generated
         * looking for the area of circle
         */
        double denominator100 = inCircle100/100.00;
        double cArea100 = (double) denominator100*sqArea;

        // when n = 1000
        int inCircle1000 = 0;
        for(int i=0; i<1000; i++){
            double randomNumber = generateRandomNumber(hypotenuse);
            if (randomNumber <= 1){
                inCircle1000++;
            }
        }
        double denominator1000 = inCircle1000/1000.00;
        double cArea1000 = (double) denominator1000*sqArea;
        
        // when n = 10000
        int inCircle10000 = 0;
        for(int i=0; i<10000; i++){
            double randomNumber = generateRandomNumber(hypotenuse);
            if (randomNumber <= 1){
                inCircle10000++;
            }
        }
        double denominator10000 = inCircle10000/10000.00;
        double cArea10000 = (double) denominator10000*sqArea;

        // when n = 100000
        int inCircle100000 = 0;
        for(int i=0; i<100000; i++){
            double randomNumber = generateRandomNumber(hypotenuse);
            if (randomNumber <= 1){
                inCircle100000++;
            }
        }
        double denominator100000 = inCircle100000/100000.00;
        double cArea100000 = (double) denominator100000*sqArea;
        
        // when n = 1000000
        int inCircle1000000 = 0;
        for(int i=0; i<1000000; i++){
            double randomNumber = generateRandomNumber(hypotenuse);
            if (randomNumber <= 1){
                inCircle1000000++;
            }
        }
        double denominator1000000 = inCircle1000000/1000000.00;
        double cArea1000000 = (double) denominator1000000*sqArea;

        // when n = 10000000
        int inCircle10000000 = 0;
        for(int i=0; i<10000000; i++){
            double randomNumber = generateRandomNumber(hypotenuse);
            if (randomNumber <= 1){
                inCircle10000000++;
            }
        }
        double denominator10000000 = inCircle10000000/10000000.00;
        double cArea10000000 = (double) denominator10000000*sqArea;

        /***
         * Array to store the cArea values in for each values of n
         */
        double [] areaVals = new double[6];
        areaVals[0] = cArea100;
        areaVals[1] = cArea1000;
        areaVals[2] = cArea10000;
        areaVals[3] = cArea100000;
        areaVals[4] = cArea1000000;
        areaVals[5] = cArea10000000;
        System.out.println(Arrays.toString(areaVals));
        int [] nVals = {100, 1000, 10000, 100000, 1000000, 10000000};
        System.out.println(Arrays.toString(nVals));

        /***
         * start by creating a csv file using nVals and areaVals as the axi
         */
        //File f = createFile();
        writeToFile(areaVals, nVals,"darts.txt");
    }
    public static double generateRandomNumber(double max){
        Random rand = new Random();
        double randomNumber = rand.nextDouble(max); // generates a random number from 0 to 2 (unsure what the upper bound is supposed to be)
        return randomNumber;
    }
    public static File createFile() {
        // creating file
        File file = new File("darts.txt");
        try{
            if(file.createNewFile()){
                System.out.println("File created: "+ file.getName());
            }
            else {
                System.out.println("File already exists.");
            }
        } 
        catch (IOException e){
            System.out.println("An error occurred.");
        }
        return file;
    }
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
        catch (IOException e){

        }
    }
}

