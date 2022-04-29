package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import primitives.Color;

import java.util.MissingResourceException;

import static primitives.Util.*;

enum CAMERA_ROTATION {ROLL, TRANSFORM};

public class Camera {

    //camera location and vectors.
    private Point location;
    private Vector up;
    private Vector to;
    private Vector right;

    //rendering properties.
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;

    ////view plain properties.
    private double height;
    private double width;
    private double distance;

    /**
     * the camera constructor, there is only constructor that contains parameters.
     *
     * @param location the point of the location of the caemera.
     * @param up       the up vector, points up from the camera.
     * @param to       points where the camera points to.
     */
    public Camera(Point location, Vector to, Vector up) {
        this.location = location;

        //if the given vectors are not orthogonal we need to throw an exception.
        if (up.dotProduct(to) != 0) {
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
     *
     * @param width  the width of the plane.
     * @param height the height of the plane.
     * @return the camera with the new properties.
     */
    public Camera setVPSize(double width, double height) {
        this.height = height;
        this.width = width;
        return this;
    }

    /**
     * updates the height and width properties of the view plane, needed for the Builder architecture.
     *
     * @param distance
     * @return
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * creates the ray from the camera to the pixel that is given by the i,j indexes.
     * the resolution of the view plane.
     *
     * @param nX represents the amount of columns, the  width of a row.
     * @param nY represents the amount for rows, the height of a column.
     *           the index of the pixel on the view plane.
     * @param j  represents the column.
     * @param i  represents the row.
     * @return the ray from the camera to the center of that pixel.
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        // Calculate the ratio of the pixel by the height and by the width of the view plane.
        Point pC = location.add(to.scale(distance));

        // The ratio Ry = h/Ny, the height of the pixel.
        double rY = alignZero(height / nY);

        // The ratio Rx = w/Nx, the width of the pixel
        double rX = alignZero(width / nX);

        //calculating the ratio in witch the vectors of the
        // view plain needs to be scaled in order to get the correct point.
        double xJ = alignZero((j - ((nX - 1) / 2.0)) * rX);
        double yI = alignZero(-(i - ((nY - 1) / 2.0)) * rY);

        //Point pIJ = pC;

        // if xJ and yI are not zero then add them to the calculation of pIJ
        if (xJ != 0) {
            pC = pC.add(right.scale(xJ));
        }

        if (yI != 0) {
            pC = pC.add(up.scale(yI));
        }

        // Center point of the pixel.
        Point pIJ = pC;

        // The direction of the ray through the pixel.
        Vector vIJ = pIJ.subtract(location);

        return new Ray(location, vIJ);
    }

    /**
     * Set for image writer, and return the object itself.
     *
     * @param imageWriter
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * Set for ray trace, and return the object itself.
     *
     * @param rayTracer
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    /**
     * Create an image according to the colors and rays.
     * in addition, checks if all the fields are not empty.
     */
    public Camera renderImage() {
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

            if (height == 0) {
                throw new MissingResourceException("missing resource", double.class.getName(), "");
            }
            if (width == 0) {
                throw new MissingResourceException("missing resource", double.class.getName(), "");
            }
            if (distance == 0) {
                throw new MissingResourceException("missing resource", double.class.getName(), "");
            }
            int Nx = imageWriter.getNx(), Ny = imageWriter.getNy();

            //Go all over the matrix, and create ray and get color for each pixel.
            for (int i = 0; i < Nx; i++) {
                for (int j = 0; j < Ny; j++) {
                    Color pixelColor = castRay(Nx, Ny, j, i);
                    imageWriter.writePixel(j, i, pixelColor);
                }
            }
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Missing resources in order to create the image"
                    + e.getClassName());
        }

        return this;
    }

    /**
     * The function calculates the color of the given pixel, according to the ray.
     *
     * @param nX  represents the amount of columns, the  width of a row.
     * @param nY  represents the amount for rows, the height of a column.
     *            the index of the pixel on the view plane.
     * @param col represents the column.
     * @param row represents the row.
     * @return
     */
    private Color castRay(int nX, int nY, int col, int row) {
        Color pixelColor;
        Ray ray = this.constructRay(nX, nY, col, row);
        pixelColor = rayTracer.traceRay(ray);
        return pixelColor;
    }

    /**
     * The function paint the matrix's grid with the given color.
     *
     * @param interval
     * @param color
     */
    public void printGrid(int interval, Color color) {

        // if the Image writer is empty, throw exception.
        if (imageWriter == null) {
            throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
        }

        int Nx = imageWriter.getNx(), Ny = imageWriter.getNy();

        //Go all over the matrix, and paint the grid with the given color.
        for (int i = 0; i < Nx; i++) {
            for (int j = 0; j < Ny; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(i, j, color);
                }
            }
        }
    }

    /**
     * Create png file of the image according
     * to pixel color matrix in the directory of the project.
     * If the image writer is missing - throw exception.
     */
    public void writeToImage() {
        if (imageWriter == null) {
            throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
        }
        imageWriter.writeToImage();
    }

    /**
     * set the to vector by the given degrees.
     *
     * @param degrees the amount of degrees to move the  to vector of the camera.
     * @return the camera after the move.
     */
    public Camera cameraRoll(double degrees) {
        cameraRotate(degrees, CAMERA_ROTATION.ROLL);
        return this;
    }

    /**
     * set the up vector by the given degrees.
     *
     * @param degrees the amount of degrees to move the  to vector of the camera.
     * @return the camera after the move.
     */
    public Camera cameraTransform(double degrees) {
        cameraRotate(degrees, CAMERA_ROTATION.TRANSFORM);
        return this;
    }

    /**
     * a general function for rotating a camra in some direction.
     *
     * @param degrees the amount of degrees to rotate the camera with.
     * @param action  the way the move the camera in.
     */
    private void cameraRotate(double degrees, CAMERA_ROTATION action) {

        //needed fo the scaling of the right vector:
        int isRightPositive = action == CAMERA_ROTATION.TRANSFORM ? 1 : -1;

        //putting the value of the changed vector
        Vector changedVector = action == CAMERA_ROTATION.ROLL ? this.to : this.up;

        //putting the degrees to be between 1 and 360.
        degrees %= 360;

        //taking care over all the cases that the tangents will be zero or inf.
        if (degrees == 0) return;
        else if (degrees == 90) {
            changedVector = this.right.scale(isRightPositive);
        } else if (degrees == 270) {
            changedVector = this.right.scale(-isRightPositive);
        } else if (degrees == 180) {
            //if the action is roll we appose the to vector, and if the action is to transform we oppose the up.
            changedVector = changedVector.scale(-1);
        } else {
            //determines of needed to oppose the vectors according to the quarter.
            int apposeSigns = degrees > 90 && degrees < 270 ? -1 : 1;

            //calculating the scale required for the right vector in order to get the correct vector.
            double scale = alignHoleNumber(Math.tan(Math.toRadians(degrees)));

            //scaling the right vector.
            Vector scaledRight = this.right.scale(isRightPositive * apposeSigns * scale);

            //adding it to the new to vector.
            changedVector = changedVector.scale(apposeSigns).add(scaledRight).normalize();
        }

        //putting the value in the right vector;
        if (action == CAMERA_ROTATION.ROLL) {
            this.to = changedVector;
        } else {
            this.up = changedVector;
        }

        //recalculating the right vector.
        this.right = this.to.crossProduct(this.up).normalize();

    }


    /**
     * relocating the camera to be in the new location.
     * the new up vector is on the same plane with the y.
     *
     * @param from the new location of the camera.
     * @param to   the point that the camera sees,
     * @return the camera after the changes.
     */
    public Camera cameraMove(Point from, Point to) {

        if(from.equals(to))
            throw new IllegalArgumentException();

        //initialize the location of the camera to be the "from" point.
        this.location = from;

        //the const of the y vector.
        Vector y = new Vector(0, 1, 0);

        //calculate the new to vector to be between the two given points.
        this.to = to.subtract(from).normalize();

        //if the to vector is on the y vector, we need to set the up vector with no parameters.
        if (this.to.equals(y) || this.to.equals(y.scale(-1))) {

            //set the up vector to be the x vector.
            this.up = new Vector(1, 0, 0);

            //calculation of the right vector according to the cross product of the given vectors.
            this.right = new Vector(0, 0, -this.to.getY());

            return this;
        }

        //calculate the right vector at first because we can know that the y vector and the to vector are its product..
        this.right = this.to.crossProduct(y).normalize(); // finding the common vector is the dot product between their normals.

        //the up vector is the cross product between the to vector and the common vector.
        this.up = this.right.crossProduct(this.to).normalize();

        return this;
    }

    /**
     * returns rather or not all the vectors and location of the camera are the same with other given camera.
     *
     * @param camera the camera the compare to.
     * @return rather or not all the vectors and the point are the same.
     */
    public boolean sameCameraVectorsAndLocation(Camera camera) {
        return this.location.equals(camera.location) &&
                this.up.equals(camera.up) &&
                this.to.equals(camera.to) &&
                this.right.equals(camera.right);

    }

}
