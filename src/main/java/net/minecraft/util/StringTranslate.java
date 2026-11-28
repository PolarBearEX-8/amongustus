package net.minecraft.util;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import java.io.InputStream;
import java.util.IllegalFormatException;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

public class StringTranslate {
   private static final Pattern numericVariablePattern = Pattern.compile("%(\\d+\\$)?[\\d\\.]*[df]");
   private static final Splitter equalSignSplitter = Splitter.on('=').limit(2);
   private static StringTranslate instance = new StringTranslate();
   private final Map languageList = Maps.newHashMap();
   private long lastUpdateTimeInMilliseconds;

   private String tryTranslateKey(String str) {
      String str2 = (String)this.languageList.get(str);
      return str2 == null ? str : str2;
   }

   public StringTranslate() {

      for (String str : IOUtils.readLines(StringTranslate.class.getResourceAsStream("/assets/minecraft/lang/en_US.lang"), Charsets.UTF_8)) {
         if (!str.isEmpty() && str.charAt(0) != '#') {
            String[] strings = (String[])Iterables.toArray(equalSignSplitter.split(str), String.class);
            if (strings != null && strings.length == 2) {
               String str2 = strings[0];
               String str3 = numericVariablePattern.matcher(strings[1]).replaceAll("%$1s");
               this.languageList.put(str2, str3);
            }
         }
      }

      this.lastUpdateTimeInMilliseconds = System.currentTimeMillis();
   }

   public static synchronized void replaceWith(Map map) {
      instance.languageList.clear();
      instance.languageList.putAll(map);
      instance.lastUpdateTimeInMilliseconds = System.currentTimeMillis();
   }

   public long getLastUpdateTimeInMilliseconds() {
      return this.lastUpdateTimeInMilliseconds;
   }

   public static StringTranslate getInstance() {
      return instance;
   }

   public synchronized String translateKeyFormat(String str, Object[] array) {
      String str2 = this.tryTranslateKey(str);

      try {
         return String.format(str2, array);
      } catch (IllegalFormatException e) {
         return "Format error: " + str2;
      }
   }

   public synchronized boolean isKeyTranslated(String str) {
      return this.languageList.containsKey(str);
   }

   public synchronized String translateKey(String str) {
      return this.tryTranslateKey(str);
   }
}
