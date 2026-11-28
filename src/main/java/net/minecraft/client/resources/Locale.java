package net.minecraft.client.resources;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.io.InputStream;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

public class Locale {
   private static final Splitter splitter = Splitter.on('=').limit(2);
   private static final Pattern pattern = Pattern.compile("%(\\d+\\$)?[\\d\\.]*[df]");
   public Map properties = Maps.newHashMap();
   private boolean unicode;

   public String formatMessage(String str, Object[] array) {
      String str2 = this.translateKeyPrivate(str);

      try {
         return String.format(str2, array);
      } catch (IllegalFormatException e) {
         return "Format error: " + str2;
      }
   }

   private String translateKeyPrivate(String str) {
      String str2 = (String)this.properties.get(str);
      return str2 == null ? str : str2;
   }

   private void loadLocaleData(InputStream in) throws IOException {
      for (String str : IOUtils.readLines(in, Charsets.UTF_8)) {
         if (!str.isEmpty() && str.charAt(0) != '#') {
            String[] strings = (String[])Iterables.toArray(splitter.split(str), String.class);
            if (strings != null && strings.length == 2) {
               String str2 = strings[0];
               String str3 = pattern.matcher(strings[1]).replaceAll("%$1s");
               this.properties.put(str2, str3);
            }
         }
      }
   }

   public synchronized void loadLocaleDataFiles(IResourceManager resourceManager, List list) {
      this.properties.clear();

      for (String str : (Iterable<String>)(list)) {
         String str2 = String.format("lang/%s.lang", str);

         for (String str3 : (Iterable<String>)(resourceManager.getResourceDomains())) {
            try {
               this.loadLocaleData(resourceManager.getAllResources(new ResourceLocation(str3, str2)));
            } catch (IOException e) {
            }
         }
      }

      this.checkUnicode();
   }

   private void checkUnicode() {
      this.unicode = false;
      int n = 0;
      int n2 = 0;

      for (String str : (Iterable<String>)(this.properties.values())) {
         int length = str.length();
         n2 += length;

         for (int i = 0; i < length; i++) {
            if (str.charAt(i) >= 256) {
               n++;
            }
         }
      }

      this.unicode = (double)((float)n / (float)n2) > 0.1;
   }

   private void loadLocaleData(List list) throws IOException {
      for (IResource resource : (Iterable<IResource>)(list)) {
         InputStream inputStream = resource.getInputStream();

         try {
            this.loadLocaleData(inputStream);
         } finally {
            IOUtils.closeQuietly(inputStream);
         }
      }
   }

   public boolean isUnicode() {
      return this.unicode;
   }
}
