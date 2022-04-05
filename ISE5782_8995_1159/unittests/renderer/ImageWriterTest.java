package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {
/**
 * Test method for
 * {@link renderer.Camera#constructRay(int, int, int, int)}.
 */



    /**
     * Test method for
     * {@link ImageWriter#writeToImage()}.
     */
    @Test
    void writeToImage() {
        ImageWriter iw = new ImageWriter("FirstPic" , 800 , 500);
        for (int i = 0; i < 800 ; i++){
            for (int j = 0; j < 500; j++) {
                if(i % 50 == 0 || j % 50 == 0){
                    iw.writePixel(i , j , new Color(200,20,200));
                }else{
                    iw.writePixel(i , j , new Color(10,100,100));
                }
            }
        }
        iw.writeToImage();
    }
}