import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class Mesh {

    private int vbo;
    private int size;

    public Mesh() {
        vbo = glGenBuffers();
        size = 0;
    }

    public void addVertices(Vertex[] data) {
        size = data.length;

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(data), GL_STATIC_DRAW);

        this.exitOnGLError("Error in addVertices");
    }

    public void draw() {
        glEnableVertexAttribArray(0);

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);

        glDrawArrays(GL_TRIANGLES, 0, size);

        glDisableVertexAttribArray(0);

        this.exitOnGLError("Error in draw");
    }

    public void exitOnGLError(String errorMessage) {
        int errorValue = glGetError();

        if (errorValue != GL_NO_ERROR) {
            System.err.println("ERROR - " + errorMessage);
            System.exit(-1);
        }
    }


}
