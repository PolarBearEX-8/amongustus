package client.util;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ObjectsManager {
   public static final Logger logger = LogManager.getLogger();
   public final Map map = Maps.newHashMap();

   public Map getMap() {
      return this.map;
   }

   public ObjectsManager(File file, String text) {
      if (text != null) {
         File file2 = new File(file, "objects");
         File file3 = new File(file, "indexes/" + text + ".json");
         BufferedReader reader = null;

         try {
            reader = Files.newReader(file3, Charsets.UTF_8);
            JsonObject jsonObject = MissingManager.resolveJsonObject(new JsonParser().parse(reader).getAsJsonObject(), "objects", (JsonObject)null);
            if (jsonObject != null) {
               for (Entry entry : jsonObject.entrySet()) {
                  JsonObject value = (JsonObject)entry.getValue();
                  String[] textArray = ((String)entry.getKey()).split("/", 2);
                  String text2 = textArray.length == 1 ? textArray[0] : textArray[0] + ":" + textArray[1];
                  String text3 = MissingManager.resolveText(value, "hash");
                  File file4 = new File(file2, text3.substring(0, 2) + "/" + text3);
                  this.map.put(text2, file4);
               }
            }
         } catch (JsonParseException jsonParseException) {
            logger.error("Unable to parse resource index file: " + file3);
         } catch (FileNotFoundException fileNotFoundException) {
            logger.error("Can't find the resource index file: " + file3);
         } finally {
            IOUtils.closeQuietly(reader);
         }
      }
   }
}
