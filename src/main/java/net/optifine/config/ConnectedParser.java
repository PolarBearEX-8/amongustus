package net.optifine.config;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.ConnectedProperties;
import net.optifine.util.EntityUtils;

public class ConnectedParser {
   private String context = null;
   public static final VillagerProfession[] PROFESSIONS_INVALID = new VillagerProfession[0];
   public static final EnumDyeColor[] DYE_COLORS_INVALID = new EnumDyeColor[0];
   private static final INameGetter NAME_GETTER_ENUM = new ConnectedParser.DefaultNameGetter();
   private static final INameGetter NAME_GETTER_DYE_COLOR = new ConnectedParser.DefaultNameGetter2();

   public MatchBlock[] parseMatchBlocks(String str) {
      if (str == null) {
         return null;
      } else {
         ArrayList list = new ArrayList();
         String[] strings = Config.tokenize(str, " ");

         for (int i = 0; i < strings.length; i++) {
            MatchBlock[] matchBlocks = this.parseMatchBlock(strings[i]);
            if (matchBlocks != null) {
               list.addAll(Arrays.asList(matchBlocks));
            }
         }

         return (MatchBlock[])list.toArray(new MatchBlock[list.size()]);
      }
   }

   public static int parseColor4(String str, int n) {
      if (str == null) {
         return n;
      } else {
         str = str.trim();

         try {
            return (int)(Long.parseLong(str, 16) & -1L);
         } catch (NumberFormatException e) {
            return n;
         }
      }
   }

   public Boolean parseBooleanObject(String str) {
      if (str == null) {
         return null;
      } else {
         String str2 = str.toLowerCase().trim();
         if (str2.equals("true")) {
            return Boolean.TRUE;
         } else if (str2.equals("false")) {
            return Boolean.FALSE;
         } else {
            this.warn("Invalid boolean: " + str);
            return null;
         }
      }
   }

   public Object[] parseObjects(String str, Object[] array, INameGetter nameGetter, String str2, Object[] array2) {
      if (str == null) {
         return null;
      } else {
         str = str.toLowerCase().trim();
         String[] strings = Config.tokenize(str, " ");
         Object[] array3 = (Object[])Array.newInstance(array.getClass().getComponentType(), strings.length);

         for (int i = 0; i < strings.length; i++) {
            Object obj = this.parseObject(strings[i], array, nameGetter, str2);
            if (obj == null) {
               return array2;
            }

            array3[i] = obj;
         }

         return array3;
      }
   }

   private VillagerProfession parseProfession(String str) {
      str = str.toLowerCase();
      String[] strings = Config.tokenize(str, ":");
      if (strings.length > 2) {
         return null;
      } else {
         String str2 = strings[0];
         String str3 = null;
         if (strings.length > 1) {
            str3 = strings[1];
         }

         int n = parseProfessionId(str2);
         if (n < 0) {
            return null;
         } else {
            int[] ints = null;
            if (str3 != null) {
               ints = parseCareerIds(n, str3);
               if (ints == null) {
                  return null;
               }
            }

            return new VillagerProfession(n, ints);
         }
      }
   }

   private static int parseProfessionId(String str) {
      int n = Config.parseInt(str, -1);
      return n >= 0
         ? n
         : (
            str.equals("farmer")
               ? 0
               : (
                  str.equals("librarian")
                     ? 1
                     : (str.equals("priest") ? 2 : (str.equals("blacksmith") ? 3 : (str.equals("butcher") ? 4 : (str.equals("nitwit") ? 5 : -1))))
               )
         );
   }

   public EnumDyeColor[] parseDyeColors(String str, String str2, EnumDyeColor[] enumDyeColors) {
      return (EnumDyeColor[])this.parseObjects(str, EnumDyeColor.values(), NAME_GETTER_DYE_COLOR, str2, enumDyeColors);
   }

   public void warn(String str) {
      Config.warn("" + this.context + ": " + str);
   }

   public Enum[] parseEnums(String str, Enum[] array, String str2, Enum[] array2) {
      return (Enum[])this.parseObjects(str, array, NAME_GETTER_ENUM, str2, array2);
   }

   private static int[] parseCareerIds(int n, String str) {
      HashSet set = new HashSet();
      String[] strings = Config.tokenize(str, ",");

      for (int i = 0; i < strings.length; i++) {
         int n2 = parseCareerId(n, strings[i]);
         if (n2 < 0) {
            return null;
         }

         set.add(n2);
      }

      Integer[] nums = (Integer[])set.toArray(new Integer[set.size()]);
      int[] ints = new int[nums.length];

      for (int j = 0; j < ints.length; j++) {
         ints[j] = nums[j];
      }

      return ints;
   }

   public int parseInt(String str, int n) {
      if (str == null) {
         return n;
      } else {
         str = str.trim();
         int n2 = Config.parseInt(str, -1);
         if (n2 < 0) {
            this.warn("Invalid number: " + str);
            return n;
         } else {
            return n2;
         }
      }
   }

   public static Comparable getPropertyValue(String str, Collection items) {
      for (Object obj : items) {
         Comparable comparable = (Comparable)obj;
         if (getValueName(comparable).equals(str)) {
            return comparable;
         }
      }

      return null;
   }

   public void dbg(String str) {
      Config.dbg("" + this.context + ": " + str);
   }

   public boolean matchState(IBlockState state, Map map) {
      for (IProperty property : (Iterable<IProperty>)(map.keySet())) {
         List list = (List)map.get(property);
         Comparable value = state.getValue(property);
         if (value == null) {
            return false;
         }

         if (!list.contains(value)) {
            return false;
         }
      }

      return true;
   }

   public RangeListInt parseRangeListInt(String str) {
      if (str == null) {
         return null;
      } else {
         RangeListInt rangeListInt = new RangeListInt();
         String[] strings = Config.tokenize(str, " ,");

         for (int i = 0; i < strings.length; i++) {
            RangeInt rangeInt = this.parseRangeInt(strings[i]);
            if (rangeInt == null) {
               return null;
            }

            rangeListInt.addRange(rangeInt);
         }

         return rangeListInt;
      }
   }

   public EnumFacing parseFace(String str) {
      str = str.toLowerCase();
      if (str.equals("bottom") || str.equals("down")) {
         return EnumFacing.DOWN;
      } else if (str.equals("top") || str.equals("up")) {
         return EnumFacing.UP;
      } else if (str.equals("north")) {
         return EnumFacing.NORTH;
      } else if (str.equals("south")) {
         return EnumFacing.SOUTH;
      } else if (str.equals("east")) {
         return EnumFacing.EAST;
      } else if (str.equals("west")) {
         return EnumFacing.WEST;
      } else {
         Config.warn("Unknown face: " + str);
         return null;
      }
   }

   public MatchBlock[] parseMatchBlock(String str) {
      if (str == null) {
         return null;
      } else {
         str = str.trim();
         if (str.length() <= 0) {
            return null;
         } else {
            String[] strings = Config.tokenize(str, ":");
            String str2 = "minecraft";
            byte b = 0;
            if (strings.length > 1 && this.isFullBlockName(strings)) {
               str2 = strings[0];
               b = 1;
            } else {
               str2 = "minecraft";
               b = 0;
            }

            String str3 = strings[b];
            String[] strings2 = Arrays.copyOfRange(strings, b + 1, strings.length);
            Block[] blocks = this.parseBlockPart(str2, str3);
            if (blocks == null) {
               return null;
            } else {
               MatchBlock[] matchBlocks = new MatchBlock[blocks.length];

               for (int i = 0; i < blocks.length; i++) {
                  Block block = blocks[i];
                  int idFromBlock = Block.getIdFromBlock(block);
                  int[] ints = null;
                  if (strings2.length > 0) {
                     ints = this.parseBlockMetadatas(block, strings2);
                     if (ints == null) {
                        return null;
                     }
                  }

                  matchBlocks[i] = new MatchBlock(idFromBlock, ints);
               }

               return matchBlocks;
            }
         }
      }
   }

   private RangeInt parseRangeInt(String str) {
      if (str == null) {
         return null;
      } else if (str.indexOf(45) >= 0) {
         String[] strings = Config.tokenize(str, "-");
         if (strings.length != 2) {
            this.warn("Invalid range: " + str);
            return null;
         } else {
            int n = Config.parseInt(strings[0], -1);
            int n2 = Config.parseInt(strings[1], -1);
            if (n >= 0 && n2 >= 0) {
               return new RangeInt(n, n2);
            } else {
               this.warn("Invalid range: " + str);
               return null;
            }
         }
      } else {
         int n3 = Config.parseInt(str, -1);
         if (n3 < 0) {
            this.warn("Invalid integer: " + str);
            return null;
         } else {
            return new RangeInt(n3, n3);
         }
      }
   }

   public Block[] parseBlockPart(String str, String str2) {
      if (this.startsWithDigit(str2)) {
         int[] ints = this.parseIntList(str2);
         if (ints == null) {
            return null;
         } else {
            Block[] blocks = new Block[ints.length];

            for (int i = 0; i < ints.length; i++) {
               int n = ints[i];
               Block blockById = Block.getBlockById(n);
               if (blockById == null) {
                  this.warn("Block not found for id: " + n);
                  return null;
               }

               blocks[i] = blockById;
            }

            return blocks;
         }
      } else {
         String str3 = str + ":" + str2;
         Block blockFromName = Block.getBlockFromName(str3);
         if (blockFromName == null) {
            this.warn("Block not found for name: " + str3);
            return null;
         } else {
            return new Block[]{blockFromName};
         }
      }
   }

   public IBlockState parseBlockState(String str, IBlockState state) {
      MatchBlock[] matchBlocks = this.parseMatchBlock(str);
      if (matchBlocks == null) {
         return state;
      } else if (matchBlocks.length != 1) {
         return state;
      } else {
         return Block.getBlockById(matchBlocks[0].getBlockId()).getDefaultState();
      }
   }

   public static Comparable parseValue(String str, Class clazz) {
      if (clazz == String.class) {
         return str;
      } else if (clazz == Boolean.class) {
         return Boolean.valueOf(str);
      } else if (clazz == Float.class) {
         return Float.valueOf(str);
      } else if (clazz == Double.class) {
         return Double.valueOf(str);
      } else if (clazz == Integer.class) {
         return Integer.valueOf(str);
      } else {
         return clazz == Long.class ? Long.valueOf(str) : null;
      }
   }

   public int[] parseEntities(String str) {
      str = str.trim();
      TreeSet treeSet = new TreeSet();
      String[] strings = Config.tokenize(str, " ");

      for (int i = 0; i < strings.length; i++) {
         String str2 = strings[i];
         int entityIdByName = EntityUtils.getEntityIdByName(str2);
         if (entityIdByName < 0) {
            this.warn("Entity not found: " + str2);
         } else {
            treeSet.add(new Integer(entityIdByName));
         }
      }

      return Config.toPrimitive((Integer[])treeSet.toArray(new Integer[treeSet.size()]));
   }

   private static int parseCareerId(int n, String str) {
      int n2 = Config.parseInt(str, -1);
      if (n2 >= 0) {
         return n2;
      } else {
         if (n == 0) {
            if (str.equals("farmer")) {
               return 1;
            }

            if (str.equals("fisherman")) {
               return 2;
            }

            if (str.equals("shepherd")) {
               return 3;
            }

            if (str.equals("fletcher")) {
               return 4;
            }
         }

         if (n == 1) {
            if (str.equals("librarian")) {
               return 1;
            }

            if (str.equals("cartographer")) {
               return 2;
            }
         }

         if (n == 2 && str.equals("cleric")) {
            return 1;
         } else {
            if (n == 3) {
               if (str.equals("armor")) {
                  return 1;
               }

               if (str.equals("weapon")) {
                  return 2;
               }

               if (str.equals("tool")) {
                  return 3;
               }
            }

            if (n == 4) {
               if (str.equals("butcher")) {
                  return 1;
               }

               if (str.equals("leather")) {
                  return 2;
               }
            }

            return n == 5 && str.equals("nitwit") ? 1 : -1;
         }
      }
   }

   public BiomeGenBase[] parseBiomes(String str) {
      if (str == null) {
         return null;
      } else {
         str = str.trim();
         boolean flag = false;
         if (str.startsWith("!")) {
            flag = true;
            str = str.substring(1);
         }

         String[] strings = Config.tokenize(str, " ");
         ArrayList list = new ArrayList();

         for (int i = 0; i < strings.length; i++) {
            String str2 = strings[i];
            BiomeGenBase biomeGenBase = this.findBiome(str2);
            if (biomeGenBase == null) {
               this.warn("Biome not found: " + str2);
            } else {
               list.add(biomeGenBase);
            }
         }

         if (flag) {
            ArrayList list2 = new ArrayList<>(Arrays.asList(BiomeGenBase.getBiomeGenArray()));
            list2.removeAll(list);
            list = list2;
         }

         return (BiomeGenBase[])list.toArray(new BiomeGenBase[list.size()]);
      }
   }

   public ConnectedParser(String str) {
      this.context = str;
   }

   public BiomeGenBase findBiome(String str) {
      str = str.toLowerCase();
      if (str.equals("nether")) {
         return BiomeGenBase.hell;
      } else {
         BiomeGenBase[] biomeGenArray = BiomeGenBase.getBiomeGenArray();

         for (int i = 0; i < biomeGenArray.length; i++) {
            BiomeGenBase biomeGenBase = biomeGenArray[i];
            if (biomeGenBase != null) {
               if (biomeGenBase.biomeName.replace(" ", "").toLowerCase().equals(str)) {
                  return biomeGenBase;
               }
            }
         }

         return null;
      }
   }

   public boolean isFullBlockName(String[] strings) {
      if (strings.length < 2) {
         return false;
      } else {
         String str = strings[1];
         return str.length() < 1 ? false : (this.startsWithDigit(str) ? false : !str.contains("="));
      }
   }

   public static int parseColor(String str, int n) {
      if (str == null) {
         return n;
      } else {
         str = str.trim();

         try {
            return Integer.parseInt(str, 16) & 16777215;
         } catch (NumberFormatException e) {
            return n;
         }
      }
   }

   public String parseName(String str) {
      String str2 = str;
      int n = str.lastIndexOf(47);
      if (n >= 0) {
         str2 = str.substring(n + 1);
      }

      int n2 = str2.lastIndexOf(46);
      if (n2 >= 0) {
         str2 = str2.substring(0, n2);
      }

      return str2;
   }

   public Weather[] parseWeather(String str, String str2, Weather[] weathers) {
      return (Weather[])this.parseObjects(str, Weather.values(), NAME_GETTER_ENUM, str2, weathers);
   }

   public EnumWorldBlockLayer parseBlockRenderLayer(String str, EnumWorldBlockLayer enumWorldBlockLayer) {
      if (str == null) {
         return enumWorldBlockLayer;
      } else {
         str = str.toLowerCase().trim();
         EnumWorldBlockLayer[] values = EnumWorldBlockLayer.values();

         for (int i = 0; i < values.length; i++) {
            EnumWorldBlockLayer enumWorldBlockLayer2 = values[i];
            if (str.equals(enumWorldBlockLayer2.name().toLowerCase())) {
               return enumWorldBlockLayer2;
            }
         }

         return enumWorldBlockLayer;
      }
   }

   public NbtTagValue parseNbtTagValue(String str, String str2) {
      return str != null && str2 != null ? new NbtTagValue(str, str2) : null;
   }

   public Enum parseEnum(String str, Enum[] array, String str2) {
      return (Enum)this.parseObject(str, array, NAME_GETTER_ENUM, str2);
   }

   public int[] parseItems(String str) {
      str = str.trim();
      TreeSet treeSet = new TreeSet();
      String[] strings = Config.tokenize(str, " ");

      for (int i = 0; i < strings.length; i++) {
         String str2 = strings[i];
         ResourceLocation location = new ResourceLocation(str2);
         Item object = (Item)Item.itemRegistry.getObject(location);
         if (object == null) {
            this.warn("Item not found: " + str2);
         } else {
            int idFromItem = Item.getIdFromItem(object);
            if (idFromItem < 0) {
               this.warn("Item has no ID: " + object + ", name: " + str2);
            } else {
               treeSet.add(new Integer(idFromItem));
            }
         }
      }

      return Config.toPrimitive((Integer[])treeSet.toArray(new Integer[treeSet.size()]));
   }

   public boolean startsWithDigit(String str) {
      if (str == null) {
         return false;
      } else if (str.length() < 1) {
         return false;
      } else {
         return Character.isDigit(str.charAt(0));
      }
   }

   public Object parseObject(String str, Object[] array, INameGetter nameGetter, String str2) {
      if (str == null) {
         return null;
      } else {
         String str3 = str.toLowerCase().trim();

         for (int i = 0; i < array.length; i++) {
            Object obj = array[i];
            String name = nameGetter.getName(obj);
            if (name != null && name.toLowerCase().equals(str3)) {
               return obj;
            }
         }

         this.warn("Invalid " + str2 + ": " + str);
         return null;
      }
   }

   public int[] parseBlockMetadatas(Block block, String[] strings) {
      if (strings.length <= 0) {
         return null;
      } else {
         String str = strings[0];
         if (this.startsWithDigit(str)) {
            return this.parseIntList(str);
         } else {
            Collection propertyNames = block.getDefaultState().getPropertyNames();
            HashMap map = new HashMap();

            for (int i = 0; i < strings.length; i++) {
               String str2 = strings[i];
               if (str2.length() > 0) {
                  String[] strings2 = Config.tokenize(str2, "=");
                  if (strings2.length != 2) {
                     this.warn("Invalid block property: " + str2);
                     return null;
                  }

                  String str3 = strings2[0];
                  String str4 = strings2[1];
                  IProperty property = ConnectedProperties.getProperty(str3, propertyNames);
                  if (property == null) {
                     this.warn("Property not found: " + str3 + ", block: " + block);
                     return null;
                  }

                  Object obj = (List)map.get(str3);
                  if (obj == null) {
                     obj = new ArrayList();
                     map.put(property, obj);
                  }

                  String[] strings3 = Config.tokenize(str4, ",");

                  for (int j = 0; j < strings3.length; j++) {
                     String str5 = strings3[j];
                     Comparable comparable = parsePropertyValue(property, str5);
                     if (comparable == null) {
                        this.warn("Property value not found: " + str5 + ", property: " + str3 + ", block: " + block);
                        return null;
                     }

                     ((List)obj).add(comparable);
                  }
               }
            }

            if (map.isEmpty()) {
               return null;
            } else {
               ArrayList list = new ArrayList();

               for (int k = 0; k < 16; k++) {
                  int n = k;

                  try {
                     if (this.matchState(this.getStateFromMeta(block, n), map)) {
                        list.add(n);
                     }
                  } catch (IllegalArgumentException e) {
                  }
               }

               if (list.size() == 16) {
                  return null;
               } else {
                  int[] ints = new int[list.size()];

                  for (int m = 0; m < ints.length; m++) {
                     ints[m] = (Integer)list.get(m);
                  }

                  return ints;
               }
            }
         }
      }
   }

   private static Object getValueName(Comparable comparable) {
      if (comparable instanceof IStringSerializable) {
         return ((IStringSerializable)comparable).getName();
      } else {
         return comparable.toString();
      }
   }

   public String parseBasePath(String str) {
      int n = str.lastIndexOf(47);
      return n < 0 ? "" : str.substring(0, n);
   }

   public boolean[] parseFaces(String str, boolean[] flags) {
      if (str == null) {
         return flags;
      } else {
         EnumSet enumSet = EnumSet.allOf(EnumFacing.class);
         String[] strings = Config.tokenize(str, " ,");

         for (int i = 0; i < strings.length; i++) {
            String str2 = strings[i];
            if (str2.equals("sides")) {
               enumSet.add(EnumFacing.NORTH);
               enumSet.add(EnumFacing.SOUTH);
               enumSet.add(EnumFacing.WEST);
               enumSet.add(EnumFacing.EAST);
            } else if (str2.equals("all")) {
               enumSet.addAll(Arrays.asList(EnumFacing.VALUES));
            } else {
               EnumFacing facing = this.parseFace(str2);
               if (facing != null) {
                  enumSet.add(facing);
               }
            }
         }

         boolean[] flags2 = new boolean[EnumFacing.VALUES.length];

         for (int j = 0; j < flags2.length; j++) {
            flags2[j] = enumSet.contains(EnumFacing.VALUES[j]);
         }

         return flags2;
      }
   }

   public int[] parseIntList(String str) {
      if (str == null) {
         return null;
      } else {
         ArrayList list = new ArrayList();
         String[] strings = Config.tokenize(str, " ,");

         for (int i = 0; i < strings.length; i++) {
            String str2 = strings[i];
            if (str2.contains("-")) {
               String[] strings2 = Config.tokenize(str2, "-");
               if (strings2.length != 2) {
                  this.warn("Invalid interval: " + str2 + ", when parsing: " + str);
               } else {
                  int n = Config.parseInt(strings2[0], -1);
                  int n2 = Config.parseInt(strings2[1], -1);
                  if (n >= 0 && n2 >= 0 && n <= n2) {
                     for (int j = n; j <= n2; j++) {
                        list.add(j);
                     }
                  } else {
                     this.warn("Invalid interval: " + str2 + ", when parsing: " + str);
                  }
               }
            } else {
               int n3 = Config.parseInt(str2, -1);
               if (n3 < 0) {
                  this.warn("Invalid number: " + str2 + ", when parsing: " + str);
               } else {
                  list.add(n3);
               }
            }
         }

         int[] ints = new int[list.size()];

         for (int k = 0; k < ints.length; k++) {
            ints[k] = (Integer)list.get(k);
         }

         return ints;
      }
   }

   public static Comparable parsePropertyValue(IProperty property, String str) {
      Comparable comparable = parseValue(str, property.getValueClass());
      if (comparable == null) {
         comparable = getPropertyValue(str, property.getAllowedValues());
      }

      return comparable;
   }

   private IBlockState getStateFromMeta(Block block, int n) {
      try {
         IBlockState stateFromMeta = block.getStateFromMeta(n);
         if (block == Blocks.double_plant && n > 7) {
            IBlockState stateFromMeta2 = block.getStateFromMeta(n & 7);
            stateFromMeta = stateFromMeta.withProperty(BlockDoublePlant.VARIANT, stateFromMeta2.getValue(BlockDoublePlant.VARIANT));
         }

         return stateFromMeta;
      } catch (IllegalArgumentException e) {
         return block.getDefaultState();
      }
   }

   public VillagerProfession[] parseProfessions(String str) {
      if (str == null) {
         return null;
      } else {
         ArrayList list = new ArrayList();
         String[] strings = Config.tokenize(str, " ");

         for (int i = 0; i < strings.length; i++) {
            String str2 = strings[i];
            VillagerProfession villagerProfession = this.parseProfession(str2);
            if (villagerProfession == null) {
               this.warn("Invalid profession: " + str2);
               return PROFESSIONS_INVALID;
            }

            list.add(villagerProfession);
         }

         return list.isEmpty() ? null : (VillagerProfession[])list.toArray(new VillagerProfession[list.size()]);
      }
   }

   public boolean parseBoolean(String str, boolean flag) {
      if (str == null) {
         return flag;
      } else {
         String str2 = str.toLowerCase().trim();
         if (str2.equals("true")) {
            return true;
         } else if (str2.equals("false")) {
            return false;
         } else {
            this.warn("Invalid boolean: " + str);
            return flag;
         }
      }
   }


   public static final class DefaultNameGetter implements INameGetter {
      @Override
      public String getName(Object obj) {
         return this.getName2((Enum)obj);
      }

      public String getName2(Enum obj) {
         return obj.name();
      }
   }

   public static final class DefaultNameGetter2 implements INameGetter {
      public String getName2(EnumDyeColor enumDyeColor) {
         return enumDyeColor.getName();
      }

      @Override
      public String getName(Object obj) {
         return this.getName2((EnumDyeColor)obj);
      }
   }
}
