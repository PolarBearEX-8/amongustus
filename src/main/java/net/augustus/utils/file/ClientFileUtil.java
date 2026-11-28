package net.augustus.utils.file;

import net.augustus.config.GsonConstants;
import net.augustus.utils.ChatUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import net.augustus.utils.TimeHelper;
import net.minecraft.block.state.pattern.BlockStateHelper;
import net.minecraft.client.Minecraft;
import org.apache.commons.io.FileUtils;

public class ClientFileUtil {
   public static final String WORKING_DIR = System.getProperty("user.dir").endsWith(File.separator)
      ? System.getProperty("user.dir")
      : System.getProperty("user.dir") + File.separator;
   public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

   public static void writeJsonArray(String str, String str2, JsonArray array) {
      writeJsonArray(str, str2, array, GsonConstants.PRETTY_GSON);
   }

   public static File getExistingFile(String str) throws FileNotFoundException {
      File file = new File(WORKING_DIR + str);
      if (!file.exists()) {
         throw new FileNotFoundException();
      } else {
         return file;
      }
   }

   public static synchronized void ensureDirectory(String str) {
      Path path = Paths.get(str);
      if (!Files.exists(path)) {
         try {
            Files.createDirectories(path);
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }

   public static List readAllLines(String str) {
      try {
         return Files.readAllLines(Paths.get(str), StandardCharsets.UTF_8);
      } catch (IOException e) {
         return new ArrayList();
      }
   }

   public static void writeJsonArray(String str, String str2, JsonArray array, Gson gson) {
      ensureDirectory(str);

      try (BufferedWriter writer = Files.newBufferedWriter(new File(str, str2).toPath(), StandardCharsets.UTF_8)) {
         String str3 = gson.toJson(array);
         writer.write(str3);
         writer.flush();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public static void writeJsonObject(String str, String str2, JsonObject json) {
      writeJsonObject(str, str2, json, GsonConstants.PRETTY_GSON);
   }

   public static JsonArray readJsonArray(String str, String str2, Runnable runnable) {
      File file = new File(str, str2);
      ensureDirectory(file.getParentFile().getAbsolutePath());
      JsonArray array = null;

      for (int i = 0; i < 5; i++) {
         try (BufferedReader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {
            array = (JsonArray)GSON.fromJson(reader, JsonArray.class);
         } catch (Exception e) {
            try {
               if (runnable != null) {
                  runnable.run();
               }
            } catch (Exception e2) {
            }

            if (runnable != null) {
               System.err.println("Corrupted file: " + file.getAbsolutePath());
            }
         }

         if (array != null || runnable == null) {
            break;
         }
      }

      return array;
   }

   public static void writeJsonObject(String str, String str2, JsonObject json, Gson gson) {
      ensureDirectory(str);

      try (BufferedWriter writer = Files.newBufferedWriter(new File(str, str2).toPath(), StandardCharsets.UTF_8)) {
         String str3 = gson.toJson(json);
         writer.write(str3);
         writer.flush();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public static void extractResource(String str, String str2, boolean flag) {
      if (str != null && !str.equals("")) {
         str = str.replace('\\', '/');
         InputStream resourceAsStream = BlockStateHelper.class.getClassLoader().getResourceAsStream(str);
         if (resourceAsStream == null) {
            throw new IllegalArgumentException("The embedded resource '" + str + "' cannot be found in client");
         } else {
            File file = new File(str);
            File file2 = new File(WORKING_DIR + str2 + "/" + file.getName());
            if (!file2.getParentFile().exists()) {
               ensureDirectory(file2.getParentFile().getAbsolutePath());
            }

            try {
               if (!file2.exists() || flag) {
                  FileUtils.copyInputStreamToFile(resourceAsStream, file2);
                  resourceAsStream.close();
               }
            } catch (IOException e) {
               System.err.println(Level.SEVERE + "Could not save " + file2.getName() + " to " + file2.getAbsolutePath());
            }
         }
      } else {
         throw new IllegalArgumentException("ResourcePath cannot be null or empty");
      }
   }


   public static JsonObject readJsonObject(String str, String str2, Runnable runnable) {
      return readJsonObject(new File(str, str2), runnable);
   }

   public static void moveToTrashOrDelete(File file) {
      com.sun.jna.platform.FileUtils instance = com.sun.jna.platform.FileUtils.getInstance();
      if (!instance.hasTrash()) {
         System.out.println("No trash bin available");
         file.delete();
      } else {
         try {
            instance.moveToTrash(new File[]{file});
         } catch (Throwable e) {
            System.out.println("Failed to move file to trash bin.");
            file.delete();
         }
      }
   }

   public static void openFolder(String str) {
      File file = new File(str);

      try {
         Desktop.getDesktop().open(new File(file.getAbsolutePath()));
      } catch (IOException e) {
         e.printStackTrace();
         ChatUtil.sendChat("§cFailed to open folder: " + file.getName());
      }
   }

   public static void waitForFileStable(Path path, Duration duration, Duration duration2, Duration duration3) {

      try {
         long l = -1L;
         FileTime fileTime = null;
         long currentTimeNs = System.nanoTime();
         long l3 = System.nanoTime() + duration3.toNanos();

         while (System.nanoTime() < l3) {
            if (!Files.exists(path)) {
               return;
            }

            long size = Files.size(path);
            FileTime lastModifiedTime = Files.getLastModifiedTime(path);
            if (size == l && lastModifiedTime.equals(fileTime)) {
               if (System.nanoTime() - currentTimeNs >= duration.toNanos()) {
                  return;
               }
            } else {
               l = size;
               fileTime = lastModifiedTime;
               currentTimeNs = System.nanoTime();
            }

            TimeUnit.NANOSECONDS.sleep(duration2.toNanos());
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public static String toFileUri(String str) {
      return ("file:/" + str).replace('\\', '/');
   }

   public static JsonObject readJsonObject(File file, Runnable runnable) {
      JsonObject json = null;

      for (int i = 0; i < 5; i++) {
         try (BufferedReader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {
            json = (JsonObject)GSON.fromJson(reader, JsonObject.class);
         } catch (Exception e) {
            try {
               if (runnable != null) {
                  runnable.run();
               }
            } catch (Exception e2) {
            }

            if (runnable != null) {
               System.err.println("Corrupted file: " + file.getAbsolutePath());
            }
         }

         if (json != null || runnable == null) {
            break;
         }
      }

      return json;
   }
}
