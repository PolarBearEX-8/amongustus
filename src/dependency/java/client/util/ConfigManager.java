package client.util;

import client.setting.CannotSupportPropertyPredicate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import net.minecraft.client.Minecraft;
import org.apache.commons.io.FileUtils;

public class ConfigManager {
   public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
   public static final String labelText = System.getProperty("user.dir") + (Minecraft.state3 ? File.separator : "");

   public static void applyText(String text, String text2, JsonArray jsonArray, Gson gson2) {
      applyString2(text);

      try {
         String text3 = gson2.toJson((JsonElement)jsonArray);
         BufferedWriter writer = new BufferedWriter(new FileWriter(new File(text, text2)));
         writer.write(text3);
         writer.flush();
         writer.close();
      } catch (IOException ioException) {
         ioException.printStackTrace();
      }
   }

   public static JsonArray getJsonArray(String text, String text2, Runnable task) {
      File file = new File(text, text2);
      applyString2(file.getParentFile().getAbsolutePath());
      JsonArray jsonArray = null;

      for (int index = 0; index < 5; index++) {
         FileReader reader = null;

         try {
            reader = new FileReader(file);
            jsonArray = gson.fromJson(reader, JsonArray.class);
         } catch (Exception exception) {
            try {
               if (task != null) {
                  task.run();
               }
            } catch (Exception exception2) {
            }

            if (task != null) {
               System.err.println("Corrupted file: " + file.getAbsolutePath());
            }
         }

         if (reader != null) {
            try {
               reader.close();
            } catch (IOException ioException) {
            }
         }

         if (jsonArray != null || task == null) {
            break;
         }
      }

      return jsonArray;
   }

   public static void applyText(String text, String text2, JsonObject jsonObject, Gson gson2) {
      applyString2(text);

      try {
         String text3 = gson2.toJson((JsonElement)jsonObject);
         BufferedWriter writer = new BufferedWriter(new FileWriter(new File(text, text2)));
         writer.write(text3);
         writer.flush();
         writer.close();
      } catch (IOException ioException) {
         ioException.printStackTrace();
      }
   }

   public static void applyString(String text, String text2, boolean flag) {
      if (text != null && !text.equals("")) {
         String text3 = text.replace('\\', '/');
         InputStream resourceAsStream = CannotSupportPropertyPredicate.class.getClassLoader().getResourceAsStream(text3);
         if (resourceAsStream == null) {
            throw new IllegalArgumentException("The embedded resource '" + text3 + "' cannot be found in client");
         } else {
            File file = new File(text3);
            File file2 = new File(labelText + text2 + "/" + file.getName());
            if (!file2.getParentFile().exists()) {
               try {
                  Files.createDirectories(file2.getParentFile().toPath());
               } catch (IOException ioException) {
                  ioException.printStackTrace();
               }
            }

            try {
               if (!file2.exists() || flag) {
                  FileUtils.copyInputStreamToFile(resourceAsStream, file2);
                  resourceAsStream.close();
               }
            } catch (IOException ioException2) {
               System.err.println(Level.SEVERE + "Could not save " + file2.getName() + " to " + file2.getAbsolutePath());
            }
         }
      } else {
         throw new IllegalArgumentException("ResourcePath cannot be null or empty");
      }
   }

   public static void applyFile(File file) {
      com.sun.jna.platform.FileUtils instance = com.sun.jna.platform.FileUtils.getInstance();
      if (!instance.hasTrash()) {
         System.out.println("No trash bin available");
         file.delete();
      } else {
         try {
            instance.moveToTrash(file);
         } catch (Throwable throwable) {
            System.out.println("Failed to move file to trash bin.");
            file.delete();
         }
      }
   }

   public static JsonObject getJsonObject(String text, String text2, Runnable task) {
      File file = new File(text, text2);
      JsonObject jsonObject = null;

      for (int index = 0; index < 5; index++) {
         FileReader reader = null;

         try {
            reader = new FileReader(file);
            jsonObject = gson.fromJson(reader, JsonObject.class);
         } catch (Exception exception) {
            try {
               if (task != null) {
                  task.run();
               }
            } catch (Exception exception2) {
            }

            if (task != null) {
               System.err.println("Corrupted file: " + file.getAbsolutePath());
            }
         }

         if (reader != null) {
            try {
               reader.close();
            } catch (IOException ioException) {
            }
         }

         if (jsonObject != null || task == null) {
            break;
         }
      }

      return jsonObject;
   }

   public static synchronized void applyString2(String text) {
      Path path = Paths.get(text);
      if (!Files.exists(path)) {
         try {
            Files.createDirectories(path);
         } catch (IOException ioException) {
            ioException.printStackTrace();
         }
      }
   }

   public static void applyText(String text, String text2, JsonArray jsonArray) {
      Gson gson2 = GsonCallback.gson2;
      applyText(text, text2, jsonArray, gson2);
   }

   public static File getFile(String text) throws FileNotFoundException {
      File file = new File(labelText + text);
      if (!file.exists()) {
         throw new FileNotFoundException();
      } else {
         return file;
      }
   }

   public static void applyText(String text, String text2, JsonObject jsonObject) {
      Gson gson2 = GsonCallback.gson2;
      applyText(text, text2, jsonObject, gson2);
   }

   public static List getList(String text) {
      try {
         return Files.readAllLines(Paths.get(text), StandardCharsets.UTF_8);
      } catch (IOException ioException) {
         return new ArrayList();
      }
   }

   public static String resolveText(String text) {
      return ("file:/" + text).replace('\\', '/');
   }
}
