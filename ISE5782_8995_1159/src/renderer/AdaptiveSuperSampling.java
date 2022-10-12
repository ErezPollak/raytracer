package renderer;

import primitives.Color;
import primitives.Ray;
import primitives.Vector;

public class AdaptiveSuperSampling {




    /**
     *
     */
    private static class MatrixCoordinate {
        int i = 0;
        int j = 0;
        Color color;

        /**
         *
         * @param x
         * @param y
         * @return
         */
        public MatrixCoordinate setParams(int x, int y) {
            this.i = x;
            this.j = y;
            return this;
        }

        /**
         *
         * @param ray
         * @return
         */
        public MatrixCoordinate setColor(Ray ray) {
            this.color = calcRelativeColor(ray, this.i, this.j);
            return this;
        }

    }

    /**
     *
     * @param ray
     * @param i
     * @param j
     * @param width
     * @param height
     * @return
     */
    public static Color calcAdaptiveSuperSamplingColor(Ray ray, int i, int j, int width, int height) {
        if (width / 2 == 0 && height / 2 == 0)
            return calcRelativeColor(ray, i, j);
        MatrixCoordinate point0 = new MatrixCoordinate().setParams(i + width / 2, j + height / 2).setColor(ray);
        MatrixCoordinate[] points = {new MatrixCoordinate().setParams(i, j).setColor(ray),
                new MatrixCoordinate().setParams(i + width, j).setColor(ray),
                new MatrixCoordinate().setParams(i, j + height).setColor(ray),
                new MatrixCoordinate().setParams(i + width, j + height).setColor(ray)};

        int newWidth = width / 2;
        int newHeight = height / 2;

        return Color.BLACK.add(
                point0.color.equals(points[0].color) ? point0.color : calcAdaptiveSuperSamplingColor(ray, i, j, newWidth, newHeight),
                point0.color.equals(points[1].color) ? point0.color : calcAdaptiveSuperSamplingColor(ray, i + newWidth, j, newWidth, newHeight),
                point0.color.equals(points[2].color) ? point0.color : calcAdaptiveSuperSamplingColor(ray, i, j + newHeight, newWidth, newHeight),
                point0.color.equals(points[3].color) ? point0.color : calcAdaptiveSuperSamplingColor(ray, i + newWidth, j + newHeight, newWidth, newHeight)
        ).scale(0.25);
    }

    /**
     *
     * @param ray
     * @param i
     * @param j
     * @return
     */
    private static Color calcRelativeColor(Ray ray, int i, int j) {
        return Color.BLACK;
//        Vector v = this.relativeVectors[i][j];
//        Ray movingRay = new Ray(ray.getPoint(), ray.getVector().add(v));
//        return rayTracer.traceRay(movingRay);
    }



}
