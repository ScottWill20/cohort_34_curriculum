package learn.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileIOHelper {

    public void processLine(String line) throws IOException {
        if (line.startsWith("CREATE")) {
            create(line);
        } else if (line.startsWith("APPEND")) {
            append(line);
        } else if (line.startsWith("DELETE")) {
            delete(line);
        } else if (line.startsWith("COPY")) {
            copy(line);
        }
    }

    public static void create(String line) throws IOException {
        int firstSpace = line.indexOf(' '); // grabbing the index of the first space in the line
        File file = new File(line.substring(firstSpace + 1)); // we want to name the file everything after the space
        file.createNewFile(); // automatically creates a new empty file - only if file with that name doesn't exist
    }

    public static void append(String line) throws IOException {
        int firstSpace = line.indexOf(' '); // grabbing index of the first space
        int secondSpace = line.indexOf(' ', firstSpace + 1); // grab what comes after the second space
        String path = line.substring(firstSpace + 1, secondSpace); // grab what comes between the first and second space
        try (PrintWriter write = new PrintWriter(new FileWriter(path, true))) {
            write.println(line.substring(secondSpace + 1)); // we are writing what comes after the second space to a file
        }
    }

    public static void delete(String line) {
        int firstSpace = line.indexOf(' ');
        File file = new File(line.substring(firstSpace + 1)); // we are referencing the existing file, whose name comes after the space
        file.delete(); // here we are using the variable name to delete the file

    }

    public static void copy(String line) throws IOException {
        String [] tokens = line.split(" "); // splitting the spaces out of the string
        Path source = Paths.get(tokens[1]); // grabbing the second item at index 1 inside of our tokens array
        Path destination = Paths.get(tokens[2]); // grabbing the third item at index 2 inside of our tokens array
        if (tokens.length == 3) { // if the length of the tokens array is 3
            Files.createDirectories(destination.getParent()); //
            String content = Files.readString(source);
            Files.writeString(destination, content, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }

    }
    // this method is void
    // accepts a String - line we are reading
    // might throw an IO exception


}
