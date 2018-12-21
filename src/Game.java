import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_1;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_2;

public class Game {
    private Mesh mesh;
    private Shader shader;
    private Transform transform;
    Game() {
        mesh = new Mesh();
        shader = new Shader();
        transform = new Transform();
        Vertex[] data = new Vertex[] {
                new Vertex(new Vector3f(-1, -1, 0)),
                new Vertex(new Vector3f(0, 1, 0)),
                new Vertex(new Vector3f(1, -1, 0)),
                new Vertex(new Vector3f(0, -1, 1))};

        int[] indices = new int[] { 0, 1, 3,
                                    3, 1, 2,
                                    2, 1, 0,
                                    0, 2, 3};

        mesh.addVertices(data, indices);

        shader.addVertexShader(ResourceLoader.loadShader("basicVertex.vs"));
        shader.addFragmentShader(ResourceLoader.loadShader("basicFragment.fs"));
        shader.compileShader();

        shader.addUniform("transform");
    }

    public void input() {
        if(Input.getKeyDown(GLFW_KEY_A))
            System.out.println("A pressed");
        if(Input.getKeyUp(GLFW_KEY_A))
            System.out.println("A down");
        if(Input.isMouseButtonDown(GLFW_MOUSE_BUTTON_1))
            System.out.println("Mouse Down at " + Input.getMousePosition().toString());
        if(Input.isMouseButtonUp(GLFW_MOUSE_BUTTON_1))
            System.out.println("Mouse Up");

        if(Input.isMouseButtonDown(GLFW_MOUSE_BUTTON_2))
            System.out.println("Mouse Down 2");
        if(Input.isMouseButtonUp(GLFW_MOUSE_BUTTON_2))
            System.out.println("Mouse Up 2");
    }

    float tmp = 0.0f;
    float tmpAmount = 0.0f;

    public void update() {
        tmp += Time.getDelta();
        tmpAmount = (float)Math.sin(tmp);
        transform.setTranslation(tmpAmount, 0, 0);
        transform.setRotation(0, tmpAmount * 180, tmpAmount * 180);
        transform.setScale(tmpAmount, tmpAmount, tmpAmount);
    }

    public void render() {
        shader.bind();
        shader.setUniform("transform", transform.getTransformation());
        mesh.draw();
    }
}
