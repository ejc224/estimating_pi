import java.util.Random;
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
        int inCircle = 0;
        for(int i=0; i<1000; i++){
            double randomNumber = generateRandomNumber(hypotenuse);
            if (randomNumber <= 1){
                inCircle++;
            }
        }
        System.out.println("Values less than 1: "+ inCircle);
        /***
         * Calculating the proportion of inCircle values to outCircle values
         * area of circle / area of square = # of values <= 1 / # of total values generated
         * looking for the area of circle
         */
        // calculations are not looking right
        double denominator = inCircle/1000;
        System.out.println("The proportion of values less than 1: "+denominator);
        double cArea = denominator*sqArea;
        System.out.println("The area of the circle is: "+ cArea);
    }
    public static double generateRandomNumber(double max){
        Random rand = new Random();
        double randomNumber = rand.nextDouble(max); // generates a random number from 0 to 2 (unsure what the upper bound is supposed to be)
        return randomNumber;
    }
}
