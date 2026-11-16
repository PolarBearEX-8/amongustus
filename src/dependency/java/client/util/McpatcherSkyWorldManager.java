package client.util;

import client.event.BridgeListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class McpatcherSkyWorldManager {
   public static InvalidAxisManager[][] invalidAxisManagerArray = null;

   private static InvalidAxisManager[][] getInvalidAxisManagerArray() {
      InvalidAxisManager[][] invalidAxisManagerMatrix = new InvalidAxisManager[10][0];
      String text = "mcpatcher/sky/world";
      int count = -1;

      for (int index = 0; index < invalidAxisManagerMatrix.length; index++) {
         String text2 = text + index + "/sky";
         ArrayList list = new ArrayList();

         for (int index2 = 1; index2 < 1000; index2++) {
            String text3 = text2 + index2 + ".properties";

            try {
               InputStream inputStream = BuildManager.resolveInputStream(new ResourceLocation(text3));
               if (inputStream == null) {
                  break;
               }

               PropertiesAdapter propertiesAdapter = new PropertiesAdapter();
               propertiesAdapter.load(inputStream);
               inputStream.close();
               BuildManager.applyText4("CustomSky properties: " + text3);
               String text4 = text2 + index2 + ".png";
               InvalidAxisManager invalidAxisManager = new InvalidAxisManager(propertiesAdapter, text4);
               if (invalidAxisManager.matchesText(text3)) {
                  ResourceLocation location = new ResourceLocation(invalidAxisManager.labelText4);
                  BridgeListener bridgeListener = InternalPngManager.resolveBridgeListenerByResourceLocation(location);
                  if (bridgeListener == null) {
                     BuildManager.applyText("CustomSky: Texture not found: " + location);
                  } else {
                     invalidAxisManager.count6 = bridgeListener.getCount();
                     list.add(invalidAxisManager);
                     inputStream.close();
                  }
               }
            } catch (FileNotFoundException fileNotFoundException) {
               break;
            } catch (IOException ioException) {
               ioException.printStackTrace();
            }
         }

         if (list.size() > 0) {
            InvalidAxisManager[] invalidAxisManagerArray2 = ((InvalidAxisManager[])list.toArray(new InvalidAxisManager[list.size()]));
            invalidAxisManagerMatrix[index] = invalidAxisManagerArray2;
            count = index;
         }
      }

      if (count < 0) {
         return (InvalidAxisManager[][])null;
      } else {
         InvalidAxisManager[][] invalidAxisManagerMatrix2 = new InvalidAxisManager[count + 1][0];

         for (int index3 = 0; index3 < invalidAxisManagerMatrix2.length; index3++) {
            invalidAxisManagerMatrix2[index3] = invalidAxisManagerMatrix[index3];
         }

         return invalidAxisManagerMatrix2;
      }
   }

   public static void updateState() {
      updateState2();
      if (BuildManager.isActive70()) {
         invalidAxisManagerArray = getInvalidAxisManagerArray();
      }
   }

   public static boolean matchesWorldByWorld(World world) {
      if (invalidAxisManagerArray == null) {
         return false;
      } else {
         int countAsIndex = world.secondaryFloatArrayEntry.getCountAsIndex();
         if (countAsIndex >= 0 && countAsIndex < invalidAxisManagerArray.length) {
            InvalidAxisManager[] invalidAxisManagerArray2 = invalidAxisManagerArray[countAsIndex];
            return invalidAxisManagerArray2 == null ? false : invalidAxisManagerArray2.length > 0;
         } else {
            return false;
         }
      }
   }

   public static void updateState2() {
      invalidAxisManagerArray = (InvalidAxisManager[][])null;
   }

   public static void applyWorld(World world, TextureManager textureManager, float factor) {
      if (invalidAxisManagerArray != null) {
         int countAsIndex = world.secondaryFloatArrayEntry.getCountAsIndex();
         if (countAsIndex >= 0 && countAsIndex < invalidAxisManagerArray.length) {
            InvalidAxisManager[] invalidAxisManagerArray2 = invalidAxisManagerArray[countAsIndex];
            if (invalidAxisManagerArray2 != null) {
               int count = (int)(world.getTimestampAsTime() % 24000L);
               float factor2 = world.computeFactor(factor);
               float floatItem = world.getFloat6(factor);
               float floatItem2 = world.getFloat7(factor);
               if (floatItem > 0.0F) {
                  floatItem2 /= floatItem;
               }

               for (int index = 0; index < invalidAxisManagerArray2.length; index++) {
                  InvalidAxisManager invalidAxisManager = invalidAxisManagerArray2[index];
                  if (invalidAxisManager.matchesWorldByWorld(world, count)) {
                     invalidAxisManager.applyWorld(world, count, factor2, floatItem, floatItem2);
                  }
               }

               FovSorter.applyFloat(1.0F - floatItem);
            }
         }
      }
   }
}
