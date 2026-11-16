package client.util;

import client.Client;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class FileManager {
   public static ArrayList getArrayList(String text) {
      ArrayList list = new ArrayList();
      Client.getClient().getClass();

      try {
         String text2 = ConfigManager.labelText + "resources.jar!/assets/augustus/" + text;
         String text3 = "file:/" + text2.replace('\\', '/');
         String[] textArray = text3.split("!/");
         if (text3.contains(".jar") && textArray.length == 2 && textArray[0].startsWith("file:")) {
            FileSystem fileSystem = FileSystems.newFileSystem(new URI("jar", textArray[0], null), new HashMap<>());
            Files.list(fileSystem.getPath(textArray[1])).forEach(path -> FileManager.applyArrayList(list, path));
            fileSystem.close();
            return list;
         } else {
            throw new Exception("bad URI for jar resource");
         }
      } catch (Exception exception) {
         exception.printStackTrace();
         return list;
      }
   }

   private static void applyArrayList(ArrayList list, Path path) {
      String[] textArray = path.toString().split("/");
      list.add(textArray[textArray.length - 1]);
   }
}
