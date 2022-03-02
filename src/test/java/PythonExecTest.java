import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PythonExecTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        String PYTHON3_PATH = "/Users/pankaiming/miniforge3/envs/tf_mac/bin/python";
        String PYTHON_SCRIPT_PATH = "/Users/pankaiming/PycharmProjects/MovieRecommendSystem/main.py";
        Process process;
        process = Runtime.getRuntime().exec(String.join(" ", PYTHON3_PATH, PYTHON_SCRIPT_PATH));
        System.out.println(process.waitFor());
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
    }
}
