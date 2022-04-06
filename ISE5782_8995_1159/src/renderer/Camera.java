package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import primitives.Color;

import java.util.MissingResourceException;

import static primitives.Util.*;

public class Camera {


    private Point location;
    private Vector up;
    private Vector to;
    private Vector right;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;

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
    public Camera(Point location, Vector to, Vector up) {
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
        // Calculate the ratio of the pixel by the height and by the width of the view plane.
        Point pC = location.add(to.scale(distance));

        // The ratio Ry = h/Ny, the height of the pixel.
        double rY = alignZero(height/nY);

        // The ratio Rx = w/Nx, the width of the pixel
        double rX = alignZero(width/nX);

        //calculating the ratio in witch the vectors of the
        // view plain needs to be scaled in order to get the correct point.
        double xJ = alignZero((j-((nX-1)/2.0))*rX);
        double yI = alignZero(-(i-((nY-1)/2.0))*rY);

        //Point pIJ = pC;

        // if xJ and yI are not zero then add them to the calculation of pIJ
        if(xJ!=0){
            pC = pC.add(right.scale(xJ));
        }

        if(yI!=0){
            pC = pC.add(up.scale(yI));
        }

        // Center point of the pixel.
        Point pIJ = pC;

        // The direction of the ray through the pixel.
        Vector vIJ = pIJ.subtract(location);

        return new Ray(location,vIJ);
    }

    /**
     * Set for image writer, and return the object itself.
     * @param imageWriter
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * Set for ray trace, and return the object itself.
     * @param rayTraceBase
     */
    public Camera setRayTraceBase(RayTracerBase _rayTracer) {
        this.rayTracer = _rayTrace;
        return this;
    }

    /**
     * Create an image according to the colors and rays.
     * in addition, checks if all the fields are not empty.
     */
    public void renderImage(){
        try {
            // if image writer is empty throw exception.
            if (imageWriter == null) {
                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
            }

            // if ray tracer is empty throw exception.
            if (rayTracer == null) {
                throw new MissingResourceException("missing resource", RayTracerBase.class.getName(), "");
            }

            // if image location is empty throw exception.
            if (location == null) {
                throw new MissingResourceException("missing resource", Point.class.getName(), "");
            }

            // if vector 'up' is empty throw exception.
            if (up == null) {
                throw new MissingResourceException("missing resource", Vector.class.getName(), "");
            }

            // if vector 'to' is empty throw exception.
            if (to == null) {
                throw new MissingResourceException("missing resource", Vector.class.getName(), "");
            }

            // if vector 'right' is empty throw exception.
            if (right == null) {
                throw new MissingResourceException("missing resource", Vector.class.getName(), "");
            }
            //           cant compare double to null.
            //           if(height == null){
            //               throw new MissingResourceException("missing resource", double.class.getName(),"");
            //           }
            //           if(width == null){
            //               throw new MissingResourceException("missing resource", double.class.getName(),"");
            //           }
            //           if(distance == null){
            //               throw new MissingResourceException("missing resource", double.class.getName(),"");
            //           }
            int Nx = imageWriter.getNx(), Ny = imageWriter.getNy();

            //Go all over the matrix, and create ray and get color for each pixel.
            for (int i = 0; i < Nx; i++) {
                for (int j = 0; j < Ny; j++) {
                    Color pixelColor = castRay(Nx, Ny, j, i);
                    imageWriter.writePixel(i,j,pixelColor);
                }
            }
        }
        catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Missing resources in order to create the image"
                    + e.getClassName());
        }
    }

    /**
     * The function calculates the color of the given pixel, according to the ray.
     * @param nX  represents the amount of columns, the  width of a row.
     * @param nY represents the amount for rows, the height of a column.
     * the index of the pixel on the view plane.
     * @param col represents the column.
     * @param row represents the row.
     * @return
     */
    private Color castRay(int nX, int nY, int col, int row){
        Color pixelColor;
        Ray ray = this.constructRay(nX,nY,col,row);
        pixelColor = rayTracer.traceRay(ray);
        return pixelColor;
    }

    /**
     * The function paint the matrix's grid with the given color.
     * @param interval
     * @param color
     */
    public void printGrid(int interval, Color color){

        // if the Image writer is empty, throw exception.
        if(imageWriter == null){
            throw new MissingResourceException("missing resource",ImageWriter.class.getName(),"");
        }

        int Nx = imageWriter.getNx(), Ny = imageWriter.getNy();

        //Go all over the matrix, and paint the grid with the given color.
        for(int i=0; i<Nx;i++){
            for(int j=0; j<Ny;j++){
                if(i%interval == 0 || j% interval ==0){
                    imageWriter.writePixel(i,j,color);
                }
            }
        }
    }

    /**
     * Create png file of the image according
     * to pixel color matrix in the directory of the project.
     * If the image writer is missing - throw exception.
     */
    public void writeToImage()
    {
        if(imageWriter == null){
            throw new MissingResourceException("missing resource",ImageWriter.class.getName(),"");
        }
        imageWriter.writeToImage();
    }
}
