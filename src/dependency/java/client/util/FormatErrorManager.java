package client.util;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import java.util.IllegalFormatException;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

public class FormatErrorManager {
   public static final Pattern pattern = Pattern.compile("%(\\d+\\$)?[\\d\\.]*[df]");
   public static final Splitter splitter = Splitter.on('=').limit(2);
   public static FormatErrorManager formatErrorManager = new FormatErrorManager();
   public final Map map = Maps.newHashMap();
   public long timestamp;

   public synchronized String resolveText(String text, Object[] entryArray) {
      String text2 = this.get(text);

      try {
         return String.format(text2, entryArray);
      } catch (IllegalFormatException illegalFormatException) {
         return "Format error: " + text2;
      }
   }

   public synchronized boolean containsKey(String text) {
      return this.map.containsKey(text);
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   public static synchronized void applyMap(Map map2) {
      formatErrorManager.map.clear();
      formatErrorManager.map.putAll(map2);
      formatErrorManager.timestamp = System.currentTimeMillis();
   }

   private String get(String text) {
      String text2 = (String)this.map.get(text);
      return text2 == null ? text : text2;
   }

   public synchronized String resolveText(String text) {
      return this.get(text);
   }

   public static FormatErrorManager getFormatErrorManager() {
      return formatErrorManager;
   }

   public FormatErrorManager() {
      for (String text : IOUtils.readLines(FormatErrorManager.class.getResourceAsStream("/assets/minecraft/lang/en_US.lang"), Charsets.UTF_8)) {
         if (!text.isEmpty() && text.charAt(0) != '#') {
            String[] textArray = (String[])Iterables.toArray(splitter.split(text), String.class);
            if (textArray != null && textArray.length == 2) {
               String text2 = textArray[0];
               String text3 = pattern.matcher(textArray[1]).replaceAll("%$1s");
               this.map.put(text2, text3);
            }
         }
      }

      this.timestamp = System.currentTimeMillis();
   }
}
