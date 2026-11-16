package cc.baritone.utils;

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

public enum DepthFirstWatcher implements ComplexListener {
    DOUBLE(Double.class, s -> Double.parseDouble((String) s)),
    BOOLEAN(Boolean.class, s -> Boolean.parseBoolean((String) s)),
    INTEGER(Integer.class, s -> Integer.parseInt((String) s)),
    FLOAT(Float.class, s -> Float.parseFloat((String) s)),
    LONG(Long.class, s -> Long.parseLong((String) s)),
    STRING(String.class, s -> new String((String) s)),
    ENUMFACING(EnumFacing.class, s -> EnumFacing.resolveEnumFacing((String) s)),
    COLOR(Color.class, s -> resolveColor((String) s), c -> resolveText((Color) c)),
    VEC3I(Vec3i.class, s -> resolveVec3i((String) s), v -> resolveText((Vec3i) v)),
    BLOCK(Block.class, s -> resolveBlock((String) s), b -> EnhancedCustom.resolveText((Block) b)),
    ITEM(Item.class, s -> resolveItem((String) s), i -> resolveText((Item) i));

   public final Class memberRef;
   public final Function function;
   public final Function function2;
   public static final DepthFirstWatcher[] ENUM_VALUES = new DepthFirstWatcher[]{
      DOUBLE, BOOLEAN, INTEGER, FLOAT, LONG, STRING, ENUMFACING, COLOR, VEC3I, BLOCK, ITEM
   };

   private static String resolveText(Item item) {
      return ((ResourceLocation)Item.iterableExtensionAdapter.getObject2(item)).toString();
   }

   private DepthFirstWatcher(Class clazz, Function mapper, Function mapper2) {
      this.memberRef = clazz;
      this.function = mapper::apply;
      this.function2 = entry -> apply(mapper2, entry);
   }

   private DepthFirstWatcher(Class clazz, Function mapper) {
      this(clazz, mapper, Object::toString);
   }

   private static Color resolveColor(String text) {
      return new Color(Integer.parseInt(text.split(",")[0]), Integer.parseInt(text.split(",")[1]), Integer.parseInt(text.split(",")[2]));
   }

   @Override
   public Object resolveTarget(AStarPolicy aStarPolicy, String text) {
      Object entry = this.function.apply(text);
      Objects.requireNonNull(entry);
      return entry;
   }

   private static String apply(Function mapper, Object entry) {
      return (String)mapper.apply(entry);
   }

   private static Vec3i resolveVec3i(String text) {
      return new Vec3i(Integer.parseInt(text.split(",")[0]), Integer.parseInt(text.split(",")[1]), Integer.parseInt(text.split(",")[2]));
   }

   @Override
   public boolean isAssignableFrom(Type type) {
      return type instanceof Class && this.memberRef.isAssignableFrom((Class<?>)type);
   }

   private static String resolveText(Vec3i vec) {
      return vec.getX() + "," + vec.getY() + "," + vec.getZ();
   }

   private DepthFirstWatcher() {
      this.memberRef = null;
      this.function = null;
      this.function2 = null;
   }

   @Override
   public String apply(AStarPolicy aStarPolicy, Object entry) {
      return (String)this.function2.apply(entry);
   }

   private static Block resolveBlock(String text) {
      return EnhancedCustom.resolveBlockByText(text.trim());
   }

   private static String resolveText(Color color) {
      return color.getRed() + "," + color.getGreen() + "," + color.getBlue();
   }

   private static Item resolveItem(String text) {
      return Item.getItem(text.trim());
   }



   public static DepthFirstWatcher resolveDepthFirstWatcher(Type type) {
      return Stream.of(values()).filter(d -> matchesType(type, d)).findFirst().orElse(null);
   }

   private static boolean matchesType(Type type, DepthFirstWatcher depthFirstWatcher) {
      return depthFirstWatcher.isAssignableFrom(type);
   }
}
