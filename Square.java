public class Square {

    private int side;

    /***
     * creating a square
     */
    public Square(int s){ 
        side = s;
    }
    /***
     * getter for area
     * @param side
     * @return int
     */
    public int getArea(int side){
        return side*side;
    }
    /***
     * getter for side
     * @return
     */
    public int getSide(){ 
        return this.side; 
    }
}