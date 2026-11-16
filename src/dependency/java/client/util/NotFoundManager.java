package client.util;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;

public class NotFoundManager {
   public static MapArrayHolder[] mapArrayHolderArray = new MapArrayHolder[0];

   public static MapArrayHolder resolveMapArrayHolder(ServiceStringEntry serviceStringEntry) {
      if (!(serviceStringEntry instanceof ServiceStringEntry)) {
         return null;
      } else {
         int count = serviceStringEntry.getCount8();
         return count >= 0 && count < mapArrayHolderArray.length ? mapArrayHolderArray[count] : null;
      }
   }

   public static void updateState() {
      mapArrayHolderArray = new MapArrayHolder[0];
      if (BuildManager.isActive48()) {
         String text = "optifine/natural.properties";

         try {
            ResourceLocation location = new ResourceLocation(text);
            if (!BuildManager.matchesResourceLocation3(location)) {
               BuildManager.applyText4("NaturalTextures: configuration \"" + text + "\" not found");
               return;
            }

            boolean flag = BuildManager.matchesResourceLocationByResourceLocation(location);
            InputStream inputStream = BuildManager.resolveInputStream(location);
            ArrayList list = new ArrayList(256);
            String text2 = BuildManager.resolveText(inputStream);
            inputStream.close();
            String[] textArray = BuildManager.resolveTextArray(text2, "\n\r");
            if (flag) {
               BuildManager.applyText4("Natural Textures: Parsing default configuration \"" + text + "\"");
               BuildManager.applyText4("Natural Textures: Valid only for textures from default resource pack");
            } else {
               BuildManager.applyText4("Natural Textures: Parsing configuration \"" + text + "\"");
            }

            ListenerAdapter27 listenerAdapter = InternalPngManager.getListenerAdapter27();

            for (int index = 0; index < textArray.length; index++) {
               String text3 = textArray[index].trim();
               if (!text3.startsWith("#")) {
                  String[] textArray2 = BuildManager.resolveTextArray(text3, "=");
                  if (textArray2.length != 2) {
                     BuildManager.applyText3("Natural Textures: Invalid \"" + text + "\" line: " + text3);
                  } else {
                     String text4 = textArray2[0].trim();
                     String text5 = textArray2[1].trim();
                     ServiceStringEntry serviceStringEntry = listenerAdapter.resolveServiceStringEntry("minecraft:blocks/" + text4);
                     if (serviceStringEntry == null) {
                        BuildManager.applyText3("Natural Textures: Texture not found: \"" + text + "\" line: " + text3);
                     } else {
                        int count = serviceStringEntry.getCount8();
                        if (count < 0) {
                           BuildManager.applyText3("Natural Textures: Invalid \"" + text + "\" line: " + text3);
                        } else {
                           if (flag && !BuildManager.matchesResourceLocationByResourceLocation(new ResourceLocation("textures/blocks/" + text4 + ".png"))) {
                              return;
                           }

                           MapArrayHolder mapArrayHolder = new MapArrayHolder(text5);
                           if (mapArrayHolder.isActive()) {
                              while (list.size() <= count) {
                                 list.add(null);
                              }

                              list.set(count, mapArrayHolder);
                              BuildManager.applyText4("NaturalTextures: " + text4 + " = " + text5);
                           }
                        }
                     }
                  }
               }
            }

            mapArrayHolderArray = (MapArrayHolder[])list.toArray(new MapArrayHolder[list.size()]);
         } catch (FileNotFoundException fileNotFoundException) {
            BuildManager.applyText3("NaturalTextures: configuration \"" + text + "\" not found");
            return;
         } catch (Exception exception) {
            exception.printStackTrace();
         }
      }
   }

   public static LocalIntArrayEntry resolveLocalIntArrayEntry(BlockPos blockPos, LocalIntArrayEntry localIntArrayEntry) {
      ServiceStringEntry serviceStringEntry = localIntArrayEntry.getServiceStringEntry();
      if (serviceStringEntry == null) {
         return localIntArrayEntry;
      } else {
         MapArrayHolder mapArrayHolder = resolveMapArrayHolder(serviceStringEntry);
         if (mapArrayHolder == null) {
            return localIntArrayEntry;
         } else {
            int count = McpatcherCtmDefaultManager.computeCount(localIntArrayEntry.getEnumFacing());
            int count2 = BuildManager.computeCount(blockPos, count);
            int count3 = 0;
            boolean flag = false;
            if (mapArrayHolder.count > 1) {
               count3 = count2 & 3;
            }

            if (mapArrayHolder.count == 2) {
               count3 = count3 / 2 * 2;
            }

            if (mapArrayHolder.enabled) {
               flag = (count2 & 4) != 0;
            }

            return mapArrayHolder.resolveLocalIntArrayEntryByLocalIntArrayEnt(localIntArrayEntry, count3, flag);
         }
      }
   }
}
