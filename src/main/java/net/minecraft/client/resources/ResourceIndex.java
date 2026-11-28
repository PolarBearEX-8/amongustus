package net.minecraft.client.resources;

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
import net.minecraft.util.JsonUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResourceIndex {
   private static final Logger LOGGER = LogManager.getLogger();
   private final Map resourceMap = Maps.newHashMap();

   public ResourceIndex(File file, String str) {
      if (str != null) {
         File file2 = new File(file, "objects");
         File file3 = new File(file, "indexes/" + str + ".json");
         BufferedReader reader = null;

         try {
            reader = Files.newReader(file3, Charsets.UTF_8);
            JsonObject jsonObject = JsonUtils.getJsonObject(new JsonParser().parse(reader).getAsJsonObject(), "objects", (JsonObject)null);
            if (jsonObject != null) {
               for (Entry entry : jsonObject.entrySet()) {
                  JsonObject value = (JsonObject)entry.getValue();
                  String[] strings = ((String)entry.getKey()).split("/", 2);
                  String str2 = strings.length == 1 ? strings[0] : strings[0] + ":" + strings[1];
                  String string = JsonUtils.getString(value, "hash");
                  File file4 = new File(file2, string.substring(0, 2) + "/" + string);
                  this.resourceMap.put(str2, file4);
               }
            }
         } catch (JsonParseException e) {
            LOGGER.error("Unable to parse resource index file: " + file3);
         } catch (FileNotFoundException e2) {
            LOGGER.error("Can't find the resource index file: " + file3);
         } finally {
            IOUtils.closeQuietly(reader);
         }
      }
   }

   public Map getResourceMap() {
      return this.resourceMap;
   }
}
