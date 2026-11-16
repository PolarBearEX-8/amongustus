package cc.baritone.utils;

import client.util.IBlockState;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.util.ResourceLocation;

public final class LocationPropertiesController {
   public static final Pattern pattern = Pattern.compile("(?<location>(\\w+:)?\\w+)(\\[(?<properties>(\\w+=\\w+,?)+)])?");
   public final ResourceLocation resourceLocation;
   public final Map map;
   public IBlockState iBlockState;

   private LocationPropertiesController(ResourceLocation location, Map map2) {
      this.resourceLocation = location;
      this.map = map2;
   }

   public static IBlockState resolveIBlockState(LocationPropertiesController locationPropertiesController) {
      return locationPropertiesController.getIBlockState();
   }

   private static LocationPropertiesController resolveLocationPropertiesControllerByText(String text) {
      Matcher matcher = pattern.matcher(text);
      if (!matcher.matches()) {
         return null;
      } else {
         try {
            String text2 = matcher.group("location");
            String text3 = matcher.group("properties");
            ResourceLocation location = new ResourceLocation(text2);
            HashMap map2 = new HashMap();
            if (text3 != null) {
               String[] textArray = text3.split(",");
               int length = textArray.length;

               for (int index = 0; index < length; index++) {
                  String[] textArray2 = textArray[index].split("=");
                  map2.put(textArray2[0], textArray2[1]);
               }
            }

            return new LocationPropertiesController(location, map2);
         } catch (Exception exception) {
            exception.printStackTrace();
            return null;
         }
      }
   }

   private void applyBlock(Block block, String text) {
      IProperty iProperty = null;

      for (IProperty iProperty2 : (Iterable<IProperty>)(Iterable<?>)block.getBlockManager().getCollection()) {
         if (iProperty2.getLabelText().equals(text)) {
            iProperty = iProperty2;
            break;
         }
      }

      if (iProperty != null) {
         this.iBlockState = resolveIBlockState(this.iBlockState, iProperty, (String)this.map.get(text));
      }
   }

   private IBlockState getIBlockState() {
      if (this.iBlockState == null) {
         Block object = (Block)Block.iterableExtensionBridge.getObject(this.resourceLocation);
         this.iBlockState = object.getDefaultState();
         ((java.util.Set<String>)this.map.keySet()).stream().sorted(String::compareTo).forEachOrdered(key -> this.applyBlock(object, key));
      }

      return this.iBlockState;
   }

   public static LocationPropertiesController resolveLocationPropertiesController(String text) {
      return resolveLocationPropertiesControllerByText(text);
   }

   private static IBlockState resolveIBlockState(IBlockState blockState, IProperty iProperty, String text) {
      return blockState.resolveIBlockState(iProperty, blockState.getComparable(iProperty));
   }
}
