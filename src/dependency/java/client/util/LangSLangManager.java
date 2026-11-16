package client.util;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.io.InputStream;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

public class LangSLangManager {
   public static final Splitter splitter = Splitter.on('=').limit(2);
   public static final Pattern pattern = Pattern.compile("%(\\d+\\$)?[\\d\\.]*[df]");
   public Map map = Maps.newHashMap();
   public boolean enabled;

   public synchronized void applyIResourceManager(IResourceManager iResourceManager, List list) {
      this.map.clear();

      for (String text : (Iterable<String>) list) {
         String text2 = String.format("lang/%s.lang", text);

         for (String text3 : (Iterable<String>) iResourceManager.getSet()) {
            try {
               this.applyList(iResourceManager.resolveListByResourceLocation(new ResourceLocation(text3, text2)));
            } catch (IOException ioException) {
            }
         }
      }

      this.updateState();
   }

   private String get(String text) {
      String text2 = (String)this.map.get(text);
      return text2 == null ? text : text2;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   private void updateState() {
      this.enabled = false;
      int count = 0;
      int count2 = 0;

      for (String text : (Iterable<String>) this.map.values()) {
         int length = text.length();
         count2 += length;

         for (int index = 0; index < length; index++) {
            if (text.charAt(index) >= 256) {
               count++;
            }
         }
      }

      float factor = (float)count / (float)count2;
      this.enabled = (double)factor > 0.1;
   }

   private void applyInputStream(InputStream inputStream) throws IOException {
      for (String text : IOUtils.readLines(inputStream, Charsets.UTF_8)) {
         if (!text.isEmpty() && text.charAt(0) != '#') {
            String[] textArray = (String[])Iterables.toArray(splitter.split(text), String.class);
            if (textArray != null && textArray.length == 2) {
               String text2 = textArray[0];
               String text3 = pattern.matcher(textArray[1]).replaceAll("%$1s");
               this.map.put(text2, text3);
            }
         }
      }
   }

   private void applyList(List list) throws IOException {
      Iterator iterator = list.iterator();

      while (iterator.hasNext()) {
         InputStream inputStream = ((IResource)iterator.next()).getInputStream();

         try {
            this.applyInputStream(inputStream);
         } finally {
            IOUtils.closeQuietly(inputStream);
         }
      }
   }

   public String resolveText(String text, Object[] entryArray) {
      String text2 = this.get(text);

      try {
         return String.format(text2, entryArray);
      } catch (IllegalFormatException illegalFormatException) {
         return "Format error: " + text2;
      }
   }
}
