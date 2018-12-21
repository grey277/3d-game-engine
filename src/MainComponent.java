import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class MainComponent {

    public static final String windowTitle = "Game engine 3d";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private boolean isRunning = false;
    private static final float FRAME_CAP = 5000.0f;
    private Game game;


    MainComponent() {
        RenderUtil.initWindow(WIDTH, HEIGHT, windowTitle);
        RenderUtil.initGraphics();
        game = new Game();
        System.out.println(RenderUtil.getOpenGLVersion());
    }

    public void start() {
        if(isRunning)
            return;

        run();
    }

    public void stop() {
        if(!isRunning)
            return;

        isRunning = false;

    }

    private void run() {
        isRunning = true;

        RenderUtil.clearScreen();
        render();
    }

    private void render() {
        long lastTime = Time.getTime();
        double unprocessedTime = 0;
        int frames = 0;
        long frameCounter = 0;

        final double frameTime = 1.0 / FRAME_CAP;

        while ( isRunning) {
            boolean render = false;

            long startTime = Time.getTime();
            long passedTime = startTime - lastTime;
            lastTime = startTime;

            unprocessedTime += passedTime / (double)Time.SECOND;
            frameCounter += passedTime;

            while(unprocessedTime > frameTime) {
                unprocessedTime -= frameTime;
                render = true;
                if(Window.shouldWindowClose())
                    stop();

                Time.setDelta(frameTime);

                game.input();
                game.update();

                if(frameCounter >= Time.SECOND) {
                    System.out.println(frames);
                    frames = 0;
                    frameCounter = 0;
                }
            }
            if(render) {
                RenderUtil.clearScreen();
                game.render();
                Window.render();
                frames++;
            }
            else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        cleanUp();
    }

    private void cleanUp() {
        Window.cleanUp();
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }


    public static void main(String[] args) {

        MainComponent game = new MainComponent();
        game.start();
    }

}