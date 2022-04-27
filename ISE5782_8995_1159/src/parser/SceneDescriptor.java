package parser;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SceneDescriptor {
    Map<String,String> sceneAttributes;
    Map<String,String> ambientLightAttributes;
    List<Map<String,String>> spheres;
    List<Map<String,String>> triangles;

    public void initializeFromJsonObject(FileReader jsonFile){
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonScene = (JSONObject) parser.parse(jsonFile);



        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }



    }
}
