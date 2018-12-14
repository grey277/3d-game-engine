import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_1;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class Input {

    private static ArrayList<KeyStatus<Integer, Integer>> currentKeys = new ArrayList<>();
    private static ArrayList<Integer> downKeys = new ArrayList<>();
    private static ArrayList<Integer> upKeys = new ArrayList<>();

    private static ArrayList<KeyStatus<Integer, Integer>> currentMouse = new ArrayList<>();
    private static ArrayList<Integer> downMouse = new ArrayList<>();
    private static ArrayList<Integer> upMouse = new ArrayList<>();

    private static Vector2d mousePosition = new Vector2d(0, 0);

    public static void updateKeys(int keyCode, int action) {
        KeyStatus<Integer, Integer> status = new KeyStatus<>(keyCode, action);
        if(currentKeys.contains(status)) {
            currentKeys.set(currentKeys.indexOf(status), status);
            if(downKeys.contains(keyCode) && action == GLFW_RELEASE) {
                downKeys.remove((Integer) keyCode);
            }
            if(upKeys.contains(keyCode) && action == GLFW_PRESS) {
                upKeys.remove((Integer) keyCode);
            }
        } else {
            currentKeys.add(status);
        }
    }

    public static boolean getKeyDown(int keyCode) {
        if(!currentKeys.contains(new KeyStatus<>(keyCode, 0)))
            return false;
        KeyStatus status = currentKeys.get(currentKeys.indexOf(new KeyStatus<>(keyCode, 0)));
        if (status.getValue() == (Integer)GLFW_PRESS && !downKeys.contains(keyCode)) {
            downKeys.add(keyCode);
            return true;
        }
        return false;
    }

    public static boolean getKeyUp(int keyCode) {
        if(!currentKeys.contains(new KeyStatus<>(keyCode, 0)))
            return false;
        KeyStatus status = currentKeys.get(currentKeys.indexOf(new KeyStatus<>(keyCode, 0)));
        if (status.getValue() == (Integer)GLFW_RELEASE && !upKeys.contains(keyCode)) {
            upKeys.add(keyCode);
            return true;
        }
        return false;
    }

    public static void mouseButtonCallback(int button, int action) {
        KeyStatus<Integer, Integer> status = new KeyStatus<>(button, action);
        if(currentMouse.contains(status)) {
            currentMouse.set(currentMouse.indexOf(status), status);
            if(downMouse.contains(button) && action == GLFW_RELEASE) {
                downMouse.remove((Integer) button);
            }
            if(upMouse.contains(button) && action == GLFW_PRESS) {
                upMouse.remove((Integer) button);
            }
        }
        else {
            currentMouse.add(status);
        }
    }

    public static boolean isMouseButtonDown(int button) {
        if(!currentMouse.contains(new KeyStatus<>(button, 0)))
            return false;
        KeyStatus status = currentMouse.get(currentMouse.indexOf(new KeyStatus<>(button, 0)));
        if (status.getValue() == (Integer)GLFW_PRESS && !downMouse.contains(button)) {
            downMouse.add(button);
            return true;
        }
        return false;
    }

    public static boolean isMouseButtonUp(int button) {
        if(!currentMouse.contains(new KeyStatus<>(button, 0)))
            return false;
        KeyStatus status = currentMouse.get(currentMouse.indexOf(new KeyStatus<>(button, 0)));
        if (status.getValue() == (Integer)GLFW_RELEASE && !upMouse.contains(button)) {
            upMouse.add(button);
            return true;
        }
        return false;
    }

    public static void setMousePositionCallback(double x, double y) {
        mousePosition.setXY(x, y);
    }

    public static Vector2d getMousePosition() {
        return mousePosition;
    }

    private static class KeyStatus<K, V> {
        K key;
        V value;
        KeyStatus(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof KeyStatus))
                return false;
            return this.key.equals(((KeyStatus) obj).getKey());
        }
    }
}
