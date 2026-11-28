package baritone.utils.schematic.format.defaults;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

public final class SerializedBlockState {
   private static final Pattern REGEX = Pattern.compile("(?<location>(\\w+:)?\\w+)(\\[(?<properties>(\\w+=\\w+,?)+)])?");
   private final ResourceLocation resourceLocation;
   private final Map properties;
   private IBlockState blockState;

   private static IBlockState setPropertyValue(IBlockState state, IProperty property, String str) {
      return state.withProperty(property, state.getValue(property));
   }

   public static IBlockState getBlockState(SerializedBlockState serializedBlockState) {
      return serializedBlockState.deserialize();
   }

   private IBlockState deserialize() {
      if (this.blockState == null) {
         Block object = (Block)Block.blockRegistry.getObject(this.resourceLocation);
         this.blockState = object.getDefaultState();
         this.properties.keySet().stream().sorted((obj, obj2) -> ((String)obj).compareTo((String)obj2)).forEachOrdered(obj3 -> {
            IProperty property = null;

            for (IProperty property2 : (Iterable<IProperty>)(object.getBlockState().getProperties())) {
               if (property2.getName().equals(obj3)) {
                  property = property2;
                  break;
               }
            }

            if (property != null) {
               this.blockState = setPropertyValue(this.blockState, property, (String)this.properties.get(obj3));
            }
         });
      }

      return this.blockState;
   }

   private static SerializedBlockState getFromString(String str) {
      Matcher matcher = REGEX.matcher(str);
      if (!matcher.matches()) {
         return null;
      } else {
         try {
            String str2 = matcher.group("location");
            String str3 = matcher.group("properties");
            ResourceLocation location = new ResourceLocation(str2);
            HashMap map = new HashMap();
            if (str3 != null) {
               for (String str4 : str3.split(",")) {
                  String[] strings = str4.split("=");
                  map.put(strings[0], strings[1]);
               }
            }

            return new SerializedBlockState(location, map);
         } catch (Exception e) {
            e.printStackTrace();
            return null;
         }
      }
   }

   private SerializedBlockState(ResourceLocation location, Map map) {
      this.resourceLocation = location;
      this.properties = map;
   }

   public static SerializedBlockState fromString(String str) {
      return getFromString(str);
   }

}
