public class Point {
    private double posX, posY;

    public Point(double x, double y){
        posX = x;
        posY = y;
    }

    public double getX(){
        return posX;
    }

    public double getY(){
        return posY;
    }

    public void setX(double x){
        posX = x;
    }

    public void setY(double y){
        posY = y;
    }
}
