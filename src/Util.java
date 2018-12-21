import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

public class Util {
    public static FloatBuffer createFloatBuffer(int size) {
        return BufferUtils.createFloatBuffer(size);
    }

    public static FloatBuffer createFlippedBuffer(Vertex[] data) {
        FloatBuffer buffer = createFloatBuffer(data.length * Vertex.SIZE);
        for(int i = 0; i < data.length; i++)
        {
            buffer.put(data[i].getPos().getX());
            buffer.put(data[i].getPos().getY());
            buffer.put(data[i].getPos().getZ());
        }
        buffer.flip();
        return buffer;
    }

    public static FloatBuffer createFlippedBuffer(Matrix4f data) {
        FloatBuffer buffer = createFloatBuffer(4 * 4);
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
                buffer.put(data.get(i, j));
        }
        buffer.flip();
        return buffer;
    }
}
