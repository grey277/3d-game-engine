import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private static long window;

    public static void createWindow(int width, int height, String title) {
        window = glfwCreateWindow(width, height, title, NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            glfwGetWindowSize(window, pWidth, pHeight);

            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        }

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);

        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            Input.updateKeys(key, action);
        });

        glfwSetMouseButtonCallback(window, (window, button, action, mods) -> {
            Input.mouseButtonCallback(button, action);
        });

        glfwSetCursorPosCallback(window, (window, xpos, ypos) -> {
            Input.setMousePositionCallback(xpos, ypos);
        });

        glfwShowWindow(window);
    }

    public static void render() {
        glfwSwapBuffers(window);
        glfwPollEvents();

    }

    public static void cleanUp() {
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
    }

    public static boolean shouldWindowClose() {
        return glfwWindowShouldClose(window);
    }
}
