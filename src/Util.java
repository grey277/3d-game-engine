import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

public class Util {
    public static FloatBuffer createDoubleBuffer(int size) {
        return BufferUtils.createFloatBuffer(size);
    }

    public static FloatBuffer createFlippedBuffer(Vertex[] data) {
        FloatBuffer buffer = createDoubleBuffer(data.length * Vertex.SIZE);
        for(int i = 0; i < data.length; i++)
        {
            buffer.put(data[i].getPos().getX());
            buffer.put(data[i].getPos().getY());
            buffer.put(data[i].getPos().getZ());
        }
        buffer.flip();
        return buffer;
    }
}
