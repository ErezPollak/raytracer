package scene;
import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;
/**
 * Class for scene
 */
public class Scene {
    public String name;
    public Color background;
    public AmbientLight ambientLight;
    public Geometries geometries;

    /**
     * Scene constructor that gets the scene name and restart the scene fields to be default.
     * @param sceneName
     */
    public Scene(String sceneName){
        name = sceneName;
        background = Color.BLACK;
        ambientLight = new AmbientLight();
        geometries = new Geometries();
    }

    /**
     * set for ambient light.
     * @param ambientLight
     */
    public void setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
    }

    /**
     * set for background.
     * @param background
     */
    public void setBackground(Color background) {
        this.background = background;
    }

    /**
     * set for geometries
     * @param geometries
     */
    public void setGeometries(Geometries geometries) {
        this.geometries = geometries;
    }

    /**
     * set name.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
