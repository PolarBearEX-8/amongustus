package client.util;

import client.event.PreUpdateProcessor;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

public class GeneratorDefaultManager {
   public static final Splitter splitter = Splitter.on('=').limit(2);
   public static final Pattern pattern = Pattern.compile("%(\\d+\\$)?[\\d\\.]*[df]");

   public static String getText3() {
      return StatCollector.getText("generator.default", new Object[0]);
   }

   public static void updateState() {
      Map map = StatCollector.getMap();
      ArrayList list = new ArrayList();
      String text = "optifine/lang/";
      String text2 = "en_US";
      String text3 = ".lang";
      list.add(text + text2 + text3);
      if (!BuildManager.getGameSettings().text93.equals(text2)) {
         list.add(text + BuildManager.getGameSettings().text93 + text3);
      }

      String[] textArray = ((String[])list.toArray(new String[list.size()]));
      applyPreUpdateProcessor(BuildManager.getIReloadableResourceManager(), textArray, map);
      PreUpdateProcessor[] preUpdateProcessorArray = BuildManager.getPreUpdateProcessorArray();

      for (int index = 0; index < preUpdateProcessorArray.length; index++) {
         applyPreUpdateProcessor(preUpdateProcessorArray[index], textArray, map);
      }
   }

   public static String resolveText(String text) {
      return StatCollector.getText(text, new Object[0]);
   }

   public static String resolveText(String text, String text2) {
      String text3 = StatCollector.getText(text, new Object[0]);
      return text3 != null && !text3.equals(text) ? text3 : text2;
   }

   public static String getText() {
      return StatCollector.getText("options.graphics.fast", new Object[0]);
   }

   private static void applyPreUpdateProcessor(PreUpdateProcessor preUpdateProcessor, String[] textArray, Map map) {
      try {
         for (int index = 0; index < textArray.length; index++) {
            String text = textArray[index];
            ResourceLocation location = new ResourceLocation(text);
            if (preUpdateProcessor.matchesResourceLocation(location)) {
               InputStream inputStream = preUpdateProcessor.resolveInputStream(location);
               if (inputStream != null) {
                  applyInputStream(inputStream, map);
               }
            }
         }
      } catch (IOException ioException) {
         ioException.printStackTrace();
      }
   }

   public static String getText2() {
      return StatCollector.getText("options.graphics.fancy", new Object[0]);
   }

   public static void applyInputStream(InputStream inputStream, Map map) throws IOException {
      Iterator iterator = IOUtils.readLines(inputStream, Charsets.UTF_8).iterator();
      inputStream.close();

      while (iterator.hasNext()) {
         String text = (String)iterator.next();
         if (!text.isEmpty() && text.charAt(0) != '#') {
            String[] textArray = (String[])Iterables.toArray(splitter.split(text), String.class);
            if (textArray != null && textArray.length == 2) {
               String text2 = textArray[0];
               String text3 = pattern.matcher(textArray[1]).replaceAll("%$1s");
               map.put(text2, text3);
            }
         }
      }
   }

   public static String getText4() {
      return StatCollector.getText("options.off", new Object[0]);
   }

   public static String getTextAsText() {
      return StatCollector.getText("options.on", new Object[0]);
   }
}
