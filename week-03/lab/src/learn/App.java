package learn;

import learn.utils.FileIOHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        FileIOHelper fileIOHelper = new FileIOHelper();
        List<String> lines = Files.readAllLines(Paths.get("README.txt"));

        for (String line : lines) {
            fileIOHelper.processLine(line);

        }
    }
}
