package client.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Supplier;

public class AngleStack {
   public void applyFile(File file) {
      Path path = file.getParentFile().toPath();
      if (!Files.exists(path)) {
         try {
            Files.createDirectories(path);
         } catch (IOException ioException) {
            ioException.printStackTrace();
         }
      }
   }

   public JsonElement resolveJsonElement(File file, Gson gson, Class clazz, Supplier supplier) {
      this.applyFile(file);
      JsonElement jsonElement = null;
      FileReader reader = null;
      boolean flag = false ;

      guardBlock: {
         searchBlock: {
            try {
               flag = true;
               reader = new FileReader(file);
               jsonElement = (JsonElement)gson.fromJson(reader, clazz);
               flag = false;
               break searchBlock;
            } catch (Exception exception) {
               if (supplier != null) {
                  jsonElement = (JsonElement)supplier.get();
                  flag = false;
               } else {
                  flag = false;
               }
            } finally {
               if (flag) {
                  if (reader != null) {
                     try {
                        reader.close();
                     } catch (IOException ioException) {
                     }
                  }
               }
            }

            if (reader != null) {
               try {
                  reader.close();
               } catch (IOException ioException2) {
               }
            }
            break guardBlock;
         }

         try {
            reader.close();
         } catch (IOException ioException3) {
         }
      }

      if (supplier != null && this.matchesJsonElement(jsonElement)) {
         jsonElement = (JsonElement)supplier.get();
      }

      return jsonElement;
   }

   public void applyFile(File file, JsonElement jsonElement, Gson gson) {
      this.applyFile(file);

      try {
         String text = gson.toJson(jsonElement);
         BufferedWriter writer = new BufferedWriter(new FileWriter(file));
         writer.write(text);
         writer.flush();
         writer.close();
      } catch (IOException ioException) {
         ioException.printStackTrace();
      }
   }

   public JsonElement resolveJsonElement(File file, Gson gson, Class clazz) {
      return this.resolveJsonElement(file, gson, clazz, (Supplier)null);
   }

   public boolean matchesJsonElement(JsonElement jsonElement) {
      if (jsonElement == null) {
         return true;
      } else {
         if (jsonElement instanceof JsonObject) {
            if (((JsonObject)jsonElement).entrySet().isEmpty()) {
               return true;
            }
         } else if (jsonElement instanceof JsonArray && ((JsonArray)jsonElement).isEmpty()) {
            return true;
         }

         return false;
      }
   }
}
