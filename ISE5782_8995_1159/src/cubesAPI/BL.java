package cubesAPI;

public class BL {

    private RenderCube renderCube;


    //region Singleton
    private static BL instance = null;

    public static BL getInstance() {
        if (instance == null)
            instance = new BL();
        return instance;
    }

    private BL() {
        this.renderCube = RenderCube.getInstance();
    }
    //endregion

    public String paintCube(String message) {

        try {
            Cube cube = new Cube(message);
            this.renderCube.renderCube(cube);
            return System.getProperty("user.dir") + "/images/" + cube.getName() + ".png";
        } catch (Exception e) {
            return e.getMessage();
        }
    }


}
