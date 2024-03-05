
abstract public class Coordinates {
    protected int x, y;
    protected Directions direction;
    protected int speed = 1;
    protected enum Directions {UP,LEFT,DOWN,RIGHT,UPPERLEFT,UPPERRIGHT,LOWERLEFT,LOWERRIGHT}

    public Coordinates(int valueX, int valueY) {
        x = valueX;
        y = valueY;
    }

    public Coordinates(int value) {
        this(value, value);
    }
    public Coordinates() {
        this(0);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
    
    public void setX(int value){
        this.x = value;
    }

    public void setY(int value){
        this.y = value;
    }
    public void setCoordinates(int valueX, int valueY){
        setX(valueX);
        setY(valueY);
    }

    protected void getCoordinates(){
        System.out.println(String.format("\nx: %d; y: %d", x, y));
    }
    
    public void move(String a){
        this.direction=Directions.valueOf(a);

        switch(a){
            case "UP":
                this.x-=speed;
                System.out.print("Player moved up");
                break;
            case "LEFT":
                this.y-=speed;
                System.out.print("Player turned left");
                break;
            case "DOWN":
                this.x+=speed;
                System.out.print("Player moved down");
                break;
            case "RIGHT":
                this.y+=speed;
                System.out.print("Player turned right");
                break;
            case "UPPERLEFT":
                this.y-=speed;
                this.x-=speed;
                System.out.print("Player moved up diagonally and turned left");
                break;
            case "UPPERRIGHT":
                this.y+=speed;
                this.x-=speed;
                System.out.print("Player moved up diagonally and turned right");
                break;
            case "LOWERLEFT":
                this.y-=speed;
                this.x+=speed;
                System.out.print("Player moved down diagonally and turned left");
                break;
            case "LOWERRIGHT":
                this.y+=speed;
                this.x+=speed;
                System.out.print("Player moved down diagonally and turned right");
                break;
            default:
                System.out.print("Player did not move");
        }
    }

    public void teleport(int x,int y){
        this.x = x;
        this.y = y;
    }

    public static double getDistance(Coordinates pFrom, Coordinates pTarget) {
        return Math.sqrt(Math.pow(pTarget.x - pFrom.x, 2) + Math.pow(pTarget.y - pFrom.y, 2));
    }
}