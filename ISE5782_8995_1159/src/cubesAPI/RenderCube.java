package cubesAPI;

import geometries.Cylinder;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Tube;
import lighting.PointLight;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import java.util.List;

import static java.awt.Color.*;
import static java.lang.Math.*;

public class RenderCube {

    final static int VIEW_MATRIX_POINTS_SIZE = 8;

    final static int NUMBER_OF_THREADS = 5;

    final static int CUBE_SIZE = 50;
    final static int CUBE_DISTANCE_CAMERA = 500;

    final static int[][] cubeSideCoordinates = {{4, 5, 7}, {7, 6, 3}, {4, 7, 0}, {5, 4, 1}, {6, 5, 2}, {3, 2, 0}};
    final static int[] sidePointsOrder = {0, 1, 2, 6, 10, 9, 8, 4, 5};
    final static int[] square = {0, 1, 5, 4};
    final static Point[] vertex = new Point[]{
            new Point(-CUBE_SIZE, -CUBE_SIZE, -CUBE_SIZE), new Point(-CUBE_SIZE, CUBE_SIZE, -CUBE_SIZE),
            new Point(CUBE_SIZE, CUBE_SIZE, -CUBE_SIZE), new Point(CUBE_SIZE, -CUBE_SIZE, -CUBE_SIZE),
            new Point(-CUBE_SIZE, -CUBE_SIZE, CUBE_SIZE), new Point(-CUBE_SIZE, CUBE_SIZE, CUBE_SIZE),
            new Point(CUBE_SIZE, CUBE_SIZE, CUBE_SIZE), new Point(CUBE_SIZE, -CUBE_SIZE, CUBE_SIZE)};
    Color[] emitting = {new Color(YELLOW), new Color(RED), new Color(BLUE), new Color(600, 165, 0), new Color(GREEN), new Color(WHITE)};
    Material material = new Material().setKd(1);

    private Point[][] viewPointsMatrix;
    private Polygon[][] polygonMatrix;
    //private
    private Camera camera = new Camera(new Point(0, 0, 100), new Vector(0, 0, -1), new Vector(0, 1, 0));


    //region Description
    private static RenderCube instance = null;

    public static RenderCube getInstance() {
        if (instance == null)
            instance = new RenderCube();
        return instance;
    }

    private RenderCube() {
        initializeViewMatrix();
        initializedPolygonMatrix();
    }
    //endregion

    private void initializedPolygonMatrix() {
        this.polygonMatrix = new Polygon[6][9];
        for (int h = 0; h < 6; h++) {
            Vector v1 = vertex[cubeSideCoordinates[h][2]].subtract(vertex[cubeSideCoordinates[h][0]]).reduce(3);
            Vector v2 = vertex[cubeSideCoordinates[h][1]].subtract(vertex[cubeSideCoordinates[h][0]]).reduce(3);
            Point[] sidePoints = new Point[16];
            for (int i = 0; i <= 3; i++) {
                for (int j = 0; j <= 3; j++) {
                    int index = i * 4 + j;
                    sidePoints[index] = vertex[cubeSideCoordinates[h][0]];
                    if (i != 0) sidePoints[index] = sidePoints[index].add(v1.scale(i));
                    if (j != 0) sidePoints[index] = sidePoints[index].add(v2.scale(j));
                }
            }
            for (int i = 0; i < RenderCube.sidePointsOrder.length; i++) {
                Point[] corners = new Point[4];
                for (int j = 0; j < square.length; j++) {
                    corners[j] = sidePoints[sidePointsOrder[i] + square[j]];
                }
                this.polygonMatrix[h][i] = (Polygon) new Polygon(corners).setMaterial(material);
            }
        }
    }

    private void initializeViewMatrix() {
        Vector[] directionVectors = new Vector[VIEW_MATRIX_POINTS_SIZE];
        List<Integer> coordinates = List.of(1, 1, 1, 0, -1, -1, -1, 0);
        for (int i = 0; i < 8; i++)
            directionVectors[i] = new Vector(coordinates.get(i), coordinates.get((i + 2) % 8), 0).normalize().scale(CUBE_DISTANCE_CAMERA / 5);//divide to 5 in order to avoid the duplication of points in the view array.
        this.viewPointsMatrix = new Point[VIEW_MATRIX_POINTS_SIZE][VIEW_MATRIX_POINTS_SIZE];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                Vector vector = directionVectors[i].scale(j + 1);
                this.viewPointsMatrix[i][j] = Point.ZERO.add(vector).add(calculateSpherePoint(vector.getX(), vector.getY(), true));
                this.viewPointsMatrix[i][7 - j] = Point.ZERO.add(vector).add(calculateSpherePoint(vector.getX(), vector.getY(), false));
            }
        }
    }

    private Vector calculateSpherePoint(double x, double y, boolean b) {
        double z = sqrt(pow(CUBE_DISTANCE_CAMERA, 2) - pow(x, 2) - pow(y, 2));
        return new Vector(0, 0, 1).scale(b ? z : -z);
    }

    public int renderCube(Cube cube) {

        if(!cube.isLegalCube()) throw new IllegalArgumentException("not a legal cube");

        paintPolygons(cube.getScheme());

        Scene scene = new Scene("Cube Scene");

        for (Polygon[] side : this.polygonMatrix) {
            scene.geometries.add(side);
        }
        //scene.geometries.add(new Sphere(new Point(0,0,0) , 20).setMaterial(material).setEmission(new Color(RED)));

        camera.cameraMove(fromPoint(cube), new Point(0, 0, 0), new Vector(0, 0, 1))
                .setVPSize(200, 200).setVPDistance(500) //
                .setRayTracer(new RayTracerBasic(scene));
        camera.setImageWriter(new ImageWriter(cube.getName(), 500, 500)) //
                .setThreadsCount(NUMBER_OF_THREADS)
                .renderImage()
                .writeToImage();
        return 0;
    }

    private void paintPolygons(String scheme) {
        char centers[] = new char[]{'y', 'r', 'b', 'o', 'g', 'w'};
        String cubeScheme[] = scheme.split("\\.");
        for (int i = 0; i < this.polygonMatrix.length; i++) {
            cubeScheme[i] = cubeScheme[i] + centers[i];
            for (int j = 0; j < this.polygonMatrix[i].length; j++) {
                this.polygonMatrix[i][j].setEmission(clacColorFromScheme(cubeScheme[i].charAt(j)).reduce(j % 2 == 0 ? 1 : 1.1));
            }
        }
    }

    private Color clacColorFromScheme(char charAt) {
        switch (charAt) {
            case 'y':
                return emitting[0];
            case 'r':
                return emitting[1];
            case 'b':
                return emitting[2];
            case 'o':
                return emitting[3];
            case 'g':
                return emitting[4];
            case 'w':
                return emitting[5];
            default:
                return Color.BLACK;
        }
    }

    private Point fromPoint(Cube cube) {
        return this.viewPointsMatrix[cube.getPointX()][cube.getPointY()];
    }

}
