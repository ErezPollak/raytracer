package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Point;

import java.util.List;
import java.util.Random;

/**
 * testing the ImageWriter class.
 */
class ImageWriterTest {
    /**
     * Test method for
     * {@link ImageWriter#writeToImage()}.
     */
    @Test
    void writeToImage() {
        ImageWriter iw = new ImageWriter("FirstPic", 800, 500);
        for (int i = 0; i < 800; i++) {
            for (int j = 0; j < 500; j++) {
                if (i % 50 == 0 || j % 50 == 0) {
                    iw.writePixel(i, j, new Color(20, 20, 20));
                } else {
                    iw.writePixel(i, j, new Color(10, 100, 100));
                }
            }
        }
        iw.writeToImage();
    }


    /**
     * Testing the sierpinski triangle.
     */
    @Test
    void writeToImage2() {
        Random rand = new Random();

        int SIZE = 1000;
        int ACCURACY = 300000;

        ImageWriter iw = new ImageWriter("SierpinskiTriangle", SIZE + 1, SIZE + 1);
        for (int i = 0; i < SIZE + 1; i++) {
            for (int j = 0; j < SIZE + 1; j++) {
                iw.writePixel(i, j, new Color(200, 200, 200));
            }
        }

        List<Point> threePoints = List.of(new Point(SIZE / 2, 0, 0), new Point(0, SIZE, 0), new Point(SIZE, SIZE, 0));

        for (Point p : threePoints) {
            iw.writePixel((int) p.getX(), (int) p.getY(), new Color(20, 20, 20));
        }

        Point p = new Point(SIZE / 2, SIZE, 0);

        for (int i = 0; i < ACCURACY; i++) {

            iw.writePixel((int) p.getX(), (int) p.getY(), new Color(0, 0, 200));

            int nextPoint = rand.nextInt(3);

            int x = (int) ((p.getX() + threePoints.get(nextPoint).getX()) / 2);
            int y = (int) ((p.getY() + threePoints.get(nextPoint).getY()) / 2);

            p = new Point(x, y, 0);

        }

        iw.writeToImage();
    }


}