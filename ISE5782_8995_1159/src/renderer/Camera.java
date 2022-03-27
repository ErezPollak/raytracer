package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

public class Camera {


    private Point location;
    private Vector up;
    private Vector to;
    private Vector right;

    ////view plain properties.////
    private double height;
    private double width;
    private double distance;

    /**
     * the camera constructor, there is only constructor that contains parameters.
     * @param location the point of the location of the caemera.
     * @param up the up vector, points up from the camera.
     * @param to points where the camera points to.
     */
    public Camera(Point location, Vector up, Vector to) {
        this.location = location;

        //if the given vectors are not orthogonal we need to throw an exception.
        if(up.dotProduct(to) != 0) {
            throw new IllegalArgumentException("the vectors are not orthogonal!");
        }

        //making sure all the vectors are normalized.
        this.to = to.normalize();
        this.up = up.normalize();

        //calculation of the right vector according to the cross product of the given vectors.
        this.right = this.to.crossProduct(this.up).normalize();
    }

    /**
     * updates the height and width properties of the view plane, needed for the Builder architecture.
     * @param width the width of the plane.
     * @param height the height of the plane.
     * @return the camera with the new properties.
     */
    public Camera setVPSize(double width, double height){
        this.height = height;
        this.width = width;
        return this;
    }

    /**
     * updates the height and width properties of the view plane, needed for the Builder architecture.
     * @param distance
     * @return
     */
    public Camera setVPDistance(double distance){
        this.distance = distance;
        return this;
    }

    /**
     * creates the ray from the camera to the pixel that is given by the i,j indexes.
     * the resolution of the view plane.
     * @param nX  represents the amount of columns, the  width of a row.
     * @param nY represents the amount for rows, the height of a column.
     * the index of the pixel on the view plane.
     * @param j represents the column.
     * @param i represents the row.
     * @return the ray from the camera to the center of that pixel.
     */
    public Ray constructRay(int nX, int nY, int j, int i){

        // Center point of the pixel.
        Point pIJ = getCenterOfPixel(nX,nY,j,i);

        // The direction of the ray through the pixel.
        Vector vIJ = pIJ.subtract(location);
        Ray ray = new Ray(location,vIJ);

        return ray;
    }

    /**
     * Get the center point of the pixel in the view plane.
     * @param nX  represents the amount of columns, the  width of a row.
     * @param nY represents the amount for rows, the height of a column.
     * the index of the pixel on the view plane.
     * @param j represents the column.
     * @param i represents the row.
     * @return
     */
    private Point getCenterOfPixel(int nX,int nY, int j, int i){
        // Calculate the ratio of the pixel by the height and by the width of the view plane
        // The ratio Ry = h/Ny, the height of the pixel.
        Point pC = location.add(to.scale(distance));
        double rY = alignZero(height/nY);

        // The ratio Rx = w/Nx, the width of the pixel
        double rX = alignZero(width/nX);
        double xJ = alignZero((j-((nX-1)/2))*rX);
        double yI = alignZero(-(i-((nY-1)/2))*rY);
        Point pIJ = pC;

        // if xJ and yI are not zero then add them to the calculation of pIJ
        if(xJ!=0){
            pIJ = pIJ.add(right.scale(xJ));
        }
        if(yI!=0){
            pIJ = pIJ.add(up.scale(yI));
        }
        return pIJ;
    }



}
