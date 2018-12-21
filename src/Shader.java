import java.util.HashMap;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;

public class Shader {

    private int program;

    private HashMap<String, Integer> uniformsLocations;

    public Shader() {
        program = glCreateProgram();

        if(program == 0) {
            System.err.println("Shader create program failed");
            System.exit(1);
        }
        uniformsLocations = new HashMap<>();
    }

    public void bind() {
        glUseProgram(program);
    }

    public void addVertexShader(String text) {
        addProgram(text, GL_VERTEX_SHADER);
    }

    public void addGeometryShader(String text) {
        addProgram(text, GL_GEOMETRY_SHADER);
    }

    public void addFragmentShader(String text) {
        addProgram(text, GL_FRAGMENT_SHADER);
    }

    public void addUniform(String uniformName) {
        int uniformLocation = glGetUniformLocation(program, uniformName);
        if(uniformLocation == -1) {
            System.err.println("Could not find uniform " + uniformName);
            System.exit(1);
        }

        uniformsLocations.put(uniformName, uniformLocation);
    }

    public void compileShader() {
        glLinkProgram(program);
        if(glGetProgrami(program, GL_LINK_STATUS) == 0) {
            System.err.println(glGetShaderInfoLog(program, 1024));
            System.exit(1);
        }

        glValidateProgram(program);

        if(glGetProgrami(program, GL_VALIDATE_STATUS) == 0) {
            System.err.println(glGetShaderInfoLog(program, 1024));
            System.exit(1);
        }
    }

    private void addProgram(String text, int type) {
        int shader = glCreateShader(type);
        if (shader == 0) {
            System.err.println("Failed to create shader");
            System.exit(1);
        }

        glShaderSource(shader, text);

        glCompileShader(shader);

        if(glGetShaderi(shader, GL_COMPILE_STATUS) == 0) {
            System.err.println(glGetShaderInfoLog(shader, 1024));
            System.exit(1);
        }
        glAttachShader(program, shader);
    }

    private int getUniformLocation(String uniformName) {
        Integer uniformLocation = uniformsLocations.get(uniformName);
        if(uniformLocation == null) {
            System.err.println("Could not find uniform name: " + uniformName);
            System.exit(1);
        }
        return uniformLocation;
    }

    public void setUniformi(String uniformName, int value) {
        glUniform1i(getUniformLocation(uniformName), value);
    }

    public void setUniformf(String uniformName, float value) {
        glUniform1f(getUniformLocation(uniformName), value);
    }

    public void setUniform(String uniformName, Vector3f value) {
        glUniform3f(getUniformLocation(uniformName), value.getX(), value.getY(), value.getZ());
    }

    public void setUniform(String uniformName, Matrix4f value) {
        glUniformMatrix4fv(getUniformLocation(uniformName), true, Util.createFlippedBuffer(value));
    }
}
