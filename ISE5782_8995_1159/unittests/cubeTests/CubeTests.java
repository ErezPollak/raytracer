package cubeTests;


import cubesAPI.Cube;
import cubesAPI.RenderCube;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;

public class CubeTests {

    @Test
    public void drawCube() {

        RenderCube renderCube = RenderCube.getInstance();

        Cube Acube = new Cube("a" , "yyyyrrry.gggwwrrr.rrwbbbbb.bbboooyy.yooggggg.wwooowww" , 0 ,3);
        Cube Bcube = new Cube("b" , "yyyyrrry.gggwwrrr.rrwbbbbb.bbboooyy.yooggggg.wwooowww" , 0 ,3);
        Cube Ccube = new Cube("c" , "yyyyrrry.gggwwrrr.rrwbbbbb.bbboooyy.yooggggg.wwooowww" , 0 ,3);

        renderCube.renderCube(Acube);
        System.out.println("first");
        renderCube.renderCube(Bcube);
        System.out.println("second");
        renderCube.renderCube(Ccube);

    }

}
