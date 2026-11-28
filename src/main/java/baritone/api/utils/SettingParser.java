package baritone.api.utils;

import java.awt.Color;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3i;

public enum SettingParser implements ISettingParser {
   DOUBLE(Double.class, Double::parseDouble),
   BOOLEAN(Boolean.class, Boolean::parseBoolean),
   INTEGER(Integer.class, Integer::parseInt),
   FLOAT(Float.class, Float::parseFloat),
   LONG(Long.class, Long::parseLong),
   STRING(String.class, String::new),
   ENUMFACING(EnumFacing.class, EnumFacing::byName),
   COLOR(
      Color.class,
      str -> new Color(Integer.parseInt(str.split(",")[0]), Integer.parseInt(str.split(",")[1]), Integer.parseInt(str.split(",")[2])),
      color -> color.getRed() + "," + color.getGreen() + "," + color.getBlue()
   ),
   VEC3I(
      Vec3i.class,
      str -> new Vec3i(Integer.parseInt(str.split(",")[0]), Integer.parseInt(str.split(",")[1]), Integer.parseInt(str.split(",")[2])),
      vec -> vec.getX() + "," + vec.getY() + "," + vec.getZ()
   ),
   BLOCK(Block.class, str -> BlockUtils.stringToBlockRequired(str.trim()), BlockUtils::blockToString),
   ITEM(
      Item.class, str -> Item.getByNameOrId(str.trim()), item -> ((ResourceLocation)Item.itemRegistry.getNameForObject(item)).toString()
   );
   private final Class type;
   private final Function parser;
   private final Function toString;

   private <T> SettingParser(Class<T> clazz, Function<String, T> function) {
      this(clazz, function, Object::toString);
   }

   private <T> SettingParser(Class<T> clazz, Function<String, T> function, Function<T, String> function2) {
      this.type = clazz;
      this.parser = obj -> function.apply((String)obj);
      this.toString = obj2 -> (String)function2.apply((T)obj2);
   }

   @Override
   public Object parse(SettingParserContext settingParserContext, String str) {
      Object obj = this.parser.apply(str);
      Objects.requireNonNull(obj);
      return obj;
   }

   private SettingParser() {
      this.type = null;
      this.parser = null;
      this.toString = null;
   }

   @Override
   public boolean accepts(Type type2) {
      return type2 instanceof Class && this.type.isAssignableFrom((Class<?>)type2);
   }

   @Override
   public String toString(SettingParserContext settingParserContext, Object obj) {
      return (String)this.toString.apply(obj);
   }

   public static SettingParser getParser(Type type2) {
      return Stream.of(values()).filter(settingParser -> settingParser.accepts(type2)).findFirst().orElse(null);
   }

}
