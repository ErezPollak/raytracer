package primitives;

public class Vector extends Point{
    /**
     * Vector Constructor
     * @param x
     * @param y
     * @param z
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if(x == 0 &&  y == 0 &&  z == 0){
            throw new IllegalArgumentException();
        }
    }

    /**
     * Calculates Add between two vectors
     * @param v
     * @return
     */
    public Vector add(Vector v){
        Double3 temp = this.xyz.add(v.xyz);
        return new Vector(temp.d1 , temp.d2 , temp.d3);
    }

    /**
     * Calculates Substruct between two vectors
     * @param v
     * @return
     */
    public Vector subtruct(Vector v){
        Double3 temp = this.xyz.subtract(v.xyz);
        return new Vector(temp.d1 , temp.d2 , temp.d3);
    }

    /**
     * Calculates double between vector and scalar
     * @param scale
     * @return
     */
    public Vector scale(double scale){
        Double3 temp = this.xyz.scale(scale);
        return new Vector(temp.d1 , temp.d2 , temp.d3);
    }

    /**
     * Calculates Scalar double between two vectors
     * @param v
     * @return
     */
    public double dotProduct(Vector v){
        Double3 temp =  this.xyz.product(v.xyz);
        return temp.d1 + temp.d2 + temp.d3;
    }

    /**
     * Calculates Vector double between two vectors
     * @param v
     * @return
     */
    public Vector crossProduct(Vector v){
        if(v.normalize().equals(this.normalize())){
            throw new IllegalArgumentException();
        }
        return new Vector(
                this.xyz.d2 * v.xyz.d3 - this.xyz.d3 * v.xyz.d2,
                this.xyz.d3 * v.xyz.d1 - this.xyz.d1 * v.xyz.d3,
                this.xyz.d1 * v.xyz.d2 - this.xyz.d2 * v.xyz.d1
        );
    }

    @Override
    public Double3 getXyz() {
        return super.getXyz();
    }

    /**
     * Calculates Vector length squared
     * @return
     */
    public double lengthSquared(){
        return super.distanceSquared(new Point(0,0,0));
    }

    /**
     * Calculates length of vector
     * @return
     */
    public double length(){
        return Math.sqrt(lengthSquared());
    }

    /**
     * Normalize vector
     * @return
     */
    public Vector normalize(){
        Double3 temp = this.xyz.reduce(length());
        return new Vector(temp.d1 , temp.d2 , temp.d3);
    }


    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public String toString() {
        return super.toString();
    }



}

