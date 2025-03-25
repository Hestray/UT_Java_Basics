package AirwaySystem_MyCode;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SerializableJSON {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static boolean createFolder(String folderName) {
        // folderName will be a route's name
        String folderPath = ".\\flights\\" + folderName;
        File d = new File(folderPath);
        return d.mkdirs();
    }

    /**
     * Parses a directory and returns a list of files. Similar to the command ls in PowerShell
     * @param folderName to look through
     * @return a list of files
     */
    public static List<String> ls(String folderName) {
        File file = new File(".\\flights\\" + folderName);
        return Arrays.asList(file.list());
    }

    /**
     * Reads all the lines in a given file.
     * @param fileName
     * @return
     * @throws IOException
     */
    public static List<String> readAllFileLines(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        List<String> lines = new ArrayList<>();
        try(BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            String line;

            while((line = br.readLine()) != null){
                //process the line
                lines.add(line);
            }
        }
        return lines;
    }

    /**
     * Saves a waypoint file to a given route folder.
     * @param folderName is the route's name
     * @param waypoint is the waypoint to write to file
     */
    public static void toJSONFile(String folderName, Waypoint waypoint) {
        File path = new File(".\\flights\\" + folderName);

        ObjectMapper objectMapper = new ObjectMapper();
        File waypointFile = new File(path, waypoint.getName() + ".json");
        try {
            objectMapper.writeValue(waypointFile, waypoint);
            System.out.println("Route saved: " + folderName + " to " + path);
        } catch (Exception e) {
            System.out.println("Error saving waypoint: " + e.getMessage());
        }
    }

    public static List<Waypoint> readJSONFile(String folderName) throws IOException {
        String folderPath = ".\\flights\\" + folderName + "\\";
        List<Waypoint> waypoints = new ArrayList<>();

        // this may throw an IOException due to its definition throwing one
        for (String fileName : ls(folderName)) {
            String jsonContent = readAllFileLines(
                folderPath + "\\" + fileName)
                .stream()
                .collect(Collectors.joining("\n"));
            waypoints.add(objectMapper.readValue(jsonContent, Waypoint.class));
        }

        return waypoints;
    }

    /**
     * This method ensures that a folder and its subfolders and files are deleted
     * @param file is the folder to delete
     */
    public static boolean deleteFolder(File file) {
        for (File subfile : file.listFiles()) {

            //  recursively call function to empty subfolders
            if (subfile.isDirectory()) {
                deleteFolder(subfile);
            }

            // delete files and the now empty subfolders
            return subfile.delete();
        }
        return file.delete();
    }

    public static void loadRoutesAndWaypoints() throws IOException {
        AirwaySystem airwaySystem = new AirwaySystem();
        for (String routeName : ls("")) {
            airwaySystem.addRoute(routeName);
            for (Waypoint waypoint : readJSONFile(routeName))
                AirwaySystem.getRoutes().get(AirwaySystem.getRoutes().size()-1).addWaypoint(waypoint);
        }
    }
}
