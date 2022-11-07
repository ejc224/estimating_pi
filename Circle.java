// created: 11/7
// class Circle

public class Circle {
    public int radius;
    public int side;
    public Circle(int s){
        side = s;
        radius = s/2;
    }
    public int getRadius(){
        return this.radius;
    }
    public int getSide(){
        return this.side;
    }
}
