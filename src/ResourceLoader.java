import java.io.BufferedReader;
import java.io.FileReader;

public class ResourceLoader {

    public static String loadShader(String fileName) {
        StringBuilder source = new StringBuilder();

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader("./res/shaders/" + fileName));
            String line;
            while((line = reader.readLine()) != null) {
                source.append(line).append("\n");
            }
            reader.close();
        }catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return source.toString();
    }
}
