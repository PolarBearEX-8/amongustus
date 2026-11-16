package client.util;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonLoggerEntry {
   public static final Logger logger = LogManager.getLogger();
   public final Gson gson;
   public final File file;
   public final Map map = Maps.newHashMap();
   public boolean enabled = true;
   public static final ParameterizedType parameterizedType = new ExtendedParameterizedTypeAdapter();

   public TargetStack resolveTargetStack(JsonObject jsonObject) {
      return new TargetStack(null, jsonObject);
   }

   public Map getMap() {
      return this.map;
   }

   public String resolveText(Object entry) {
      return entry.toString();
   }

   public CommonLoggerEntry(File file2) {
      this.file = file2;
      GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
      gsonBuilder.registerTypeHierarchyAdapter(TargetStack.class, new CustomJsonDeserializerAdapter(this, null));
      this.gson = gsonBuilder.create();
   }

   public TargetStack resolveTargetStack(Object entry) {
      this.updateState();
      return (TargetStack)this.map.get(this.resolveText(entry));
   }

   public String[] getTextArray() {
      return ((String[])this.map.keySet().toArray(new String[this.map.size()]));
   }

   public void applyTarget(Object entry) {
      this.map.remove(this.resolveText(entry));

      try {
         this.updateState2();
      } catch (IOException ioException) {
         logger.warn("Could not save the list after removing a user.", ioException);
      }
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void updateState2() throws IOException {
      Collection values = this.map.values();
      String text = this.gson.toJson(values);
      BufferedWriter writer = null;

      try {
         writer = Files.newWriter(this.file, Charsets.UTF_8);
         writer.write(text);
      } finally {
         IOUtils.closeQuietly(writer);
      }
   }

   public void applyTargetStack(TargetStack targetStack) {
      this.map.put(this.resolveText(targetStack.getDataRef()), targetStack);

      try {
         this.updateState2();
      } catch (IOException ioException) {
         logger.warn("Could not save the list after adding a user.", ioException);
      }
   }

   public boolean matchesTarget(Object entry) {
      return this.map.containsKey(this.resolveText(entry));
   }

   public void setEnabled(boolean enabled2) {
      this.enabled = enabled2;
   }

   private void updateState() {
      ArrayList list = Lists.newArrayList();

      for (TargetStack targetStack : (Iterable<TargetStack>)(Iterable<?>)this.map.values()) {
         if (targetStack.before()) {
            list.add(targetStack.getDataRef());
         }
      }

      for (Object element : list) {
         this.map.remove(element);
      }
   }
}
