package cubesAPI;

public class Cube {

    private String name = "";
    private String scheme = "";
    private int pointX = -1;
    private int pointY = -1;

    public Cube(String name, String scheme, int pointX, int pointY) {
        this.name = name;
        this.scheme = scheme;
        this.pointX = pointX;
        this.pointY = pointY;
    }

    public Cube(String message){
         String[] cubeProps = message.split(",");
         for(String str : cubeProps){
             switch (str.charAt(0)){
                 case 'n':
                     this.name = str.substring(1);
                     break;
                 case 's':
                     this.scheme = str.substring(1);
                     break;
                 case 'x':
                     this.pointX = Integer.parseInt(str.substring(1));
                     break;
                 case 'y':
                     this.pointY = Integer.parseInt(str.substring(1));
                     break;
                 default: throw new IllegalArgumentException("not an acceptable scheme!");
             }
         }
    }

    public boolean isLegalCube(){
        return this.name != "" && this.scheme != "" && this.pointX >= 0 && this.pointX < 8 && this.pointY >= 0 && this.pointY < 8;
    }

    public String getScheme() {
        return scheme;
    }

    public int getPointX() {
        return pointX;
    }

    public int getPointY() {
        return pointY;
    }

    public String getName() {
        return name;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public void setPointX(int pointX) {
        this.pointX = pointX;
    }

    public void setPointY(int pointY) {
        this.pointY = pointY;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
