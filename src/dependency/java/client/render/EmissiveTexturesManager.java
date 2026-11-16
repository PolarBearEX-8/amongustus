package client.render;

import client.event.BridgeListener;
import client.util.BuildManager;
import client.util.DefaultLoggerEntry;
import client.util.PropertiesAdapter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

public class EmissiveTexturesManager {
   public static String labelText3 = null;
   public static String labelText2 = null;
   public static boolean enabled2 = false;
   public static boolean enabled3 = false;
   public static boolean enabled4 = false;
   public static boolean enabled = false;
   public static float factor2;
   public static float factor;
   public static final String labelText = ".png";
   public static final ResourceLocation resourceLocation = new ResourceLocation("mcpatcher/ctm/default/empty.png");

   public static boolean matchesResourceLocationByResourceLocation(ResourceLocation location) {
      return labelText2 == null ? false : location.getText1().endsWith(labelText2);
   }

   public static boolean isEnabled4() {
      return enabled4;
   }

   public static void renderElement2() {
      enabled3 = false;
      enabled4 = false;
   }

   public static void renderElement() {
      factor2 = OpenGlHelper.factor;
      factor = OpenGlHelper.factor2;
      OpenGlHelper.applyIndex(OpenGlHelper.defaultTexUnit, 240.0F, factor);
      enabled = true;
   }

   private static void applyText(String text) {
      BuildManager.applyText3("EmissiveTextures: " + text);
   }

   public static BridgeListener resolveBridgeListener(BridgeListener bridgeListener, Map map) {
      if (!enabled3) {
         return bridgeListener;
      } else if (!(bridgeListener instanceof DefaultLoggerEntry)) {
         return bridgeListener;
      } else {
         ResourceLocation resourceLocation2 = ((DefaultLoggerEntry)bridgeListener).resourceLocation;
         if (!enabled) {
            if (resourceLocation2 != null) {
               enabled4 = true;
            }

            return bridgeListener;
         } else {
            if (resourceLocation2 == null) {
               resourceLocation2 = resourceLocation;
            }

            Object entry = (BridgeListener)map.get(resourceLocation2);
            if (entry == null) {
               entry = new DefaultLoggerEntry(resourceLocation2, true);
               BuildManager.getTextureManagerAsTextureManager().matchesResourceLocation(resourceLocation2, (BridgeListener)entry);
            }

            return (BridgeListener)entry;
         }
      }
   }

   public static void renderElement4() {
      enabled3 = true;
      enabled4 = false;
   }

   public static void renderElement5() {
      enabled2 = false;
      labelText3 = null;
      labelText2 = null;
      if (BuildManager.isActive39()) {
         try {
            String text = "optifine/emissive.properties";
            InputStream inputStream = BuildManager.resolveInputStream(new ResourceLocation(text));
            if (inputStream == null) {
               return;
            }

            applyTextByText("Loading " + text);
            PropertiesAdapter propertiesAdapter = new PropertiesAdapter();
            propertiesAdapter.load(inputStream);
            inputStream.close();
            labelText3 = propertiesAdapter.getProperty("suffix.emissive");
            if (labelText3 != null) {
               labelText2 = labelText3 + ".png";
            }

            enabled2 = labelText3 != null;
         } catch (FileNotFoundException fileNotFoundException) {
            return;
         } catch (IOException ioException) {
            ioException.printStackTrace();
         }
      }
   }

   public static void renderElement3() {
      enabled = false;
      OpenGlHelper.applyIndex(OpenGlHelper.defaultTexUnit, factor2, factor);
   }

   public static void applyResourceLocationByResourceLocation(ResourceLocation location, DefaultLoggerEntry defaultLoggerEntry) {
      if (location != null && defaultLoggerEntry != null) {
         defaultLoggerEntry.enabled5 = false;
         defaultLoggerEntry.resourceLocation = null;
         if (labelText2 != null) {
            String text = location.getText1();
            if (text.endsWith(".png")) {
               if (text.endsWith(labelText2)) {
                  defaultLoggerEntry.enabled5 = true;
               } else {
                  String text2 = text.substring(0, text.length() - ".png".length()) + labelText2;
                  ResourceLocation location2 = new ResourceLocation(location.getText(), text2);
                  if (BuildManager.matchesResourceLocation3(location2)) {
                     defaultLoggerEntry.resourceLocation = location2;
                  }
               }
            }
         }
      }
   }

   public static boolean isEnabled2() {
      return enabled2;
   }

   private static void applyTextByText(String text) {
      BuildManager.applyText4("EmissiveTextures: " + text);
   }

   public static String getLabelText3() {
      return labelText3;
   }
}
