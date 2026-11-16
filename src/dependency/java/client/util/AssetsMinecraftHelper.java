package client.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;

public class AssetsMinecraftHelper {
   public static Path path;

   private static void applyPath(Path path2) throws IOException {
      URL resource = AssetsMinecraftHelper.class.getResource("/assets/minecraft/aqua/miniblox");
      if (resource == null) {
         throw new FileNotFoundException("Miniblox directory not found in resources");
      } else {
         URI uri;
         try {
            uri = resource.toURI();
         } catch (URISyntaxException uRISyntaxException) {
            throw new IOException("Invalid resource URI", uRISyntaxException);
         }

         Path path3;
         if (uri.getScheme().equals("jar")) {
            path3 = FileSystems.newFileSystem(uri, Collections.emptyMap()).getPath("/assets/minecraft/aqua/miniblox");
         } else {
            path3 = Paths.get(uri);
         }

         Files.walkFileTree(path3, new PathSimpleFileVisitor(path2, path3));
      }
   }

   private static void updateState2() throws IOException, InterruptedException {
      ProcessBuilder processBuilder = new ProcessBuilder();
      Map map = processBuilder.environment();
      String text = getText();
      map.put("PATH", System.getenv("PATH") + File.pathSeparator + text);
      String text2 = Paths.get(text, "npm.cmd").toString();
      processBuilder.command(text2, "install");
      processBuilder.directory(path.toFile());
      processBuilder.redirectErrorStream(true);
      Process process = processBuilder.start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

      String text3;
      while ((text3 = reader.readLine()) != null) {
         System.out.println("npm install: " + text3);
      }

      int count = process.waitFor();
      if (count != 0) {
         throw new RuntimeException("npm install failed with exit code " + count);
      }
   }

   private static String getText() {
      String[] textArray = new String[]{
         System.getenv("ProgramFiles") + "\\nodejs",
         System.getenv("ProgramFiles(x86)") + "\\nodejs",
         System.getProperty("user.home") + "\\AppData\\Roaming\\npm"
      };

      for (String text : textArray) {
         if (Files.exists(Paths.get(text))) {
            return text;
         }
      }

      throw new RuntimeException("Node.js installation not found");
   }

   private static void updateState3() {

      try {
         path = Files.createTempDirectory("miniblox");
         applyPath(path);
         if (!isActive()) {
            updateState2();
         }

         updateState4();
      } catch (InterruptedException | IOException interruptedException) {
         interruptedException.printStackTrace();
      }
   }

   private static void updateState() {
      try {
         updateState3();
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   private static boolean isActive() {
      return Files.exists(path.resolve("node_modules"));
   }

   public static void start() {
      new Thread(AssetsMinecraftHelper::updateState).start();
   }

   private static void updateState4() throws IOException {
      ProcessBuilder processBuilder = new ProcessBuilder();
      String text = Paths.get(getText(), "node.exe").toString();
      processBuilder.command(text, "index.js");
      processBuilder.directory(path.toFile());
      processBuilder.redirectErrorStream(true);
      Process process = processBuilder.start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

      String text2;
      while ((text2 = reader.readLine()) != null) {
         System.out.println("Node.js Server: " + text2);
      }
   }
}
