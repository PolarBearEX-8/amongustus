package client.util;

import client.event.PreMotionInterceptor;
import client.event.PrimaryListener;
import client.setting.BlockSolidType;
import client.setting.LocalOptionType;
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
import net.minecraft.block.Blocks;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class NetherManager {
   public String labelText = null;
   public static final SpecialIntArrayEntry[] specialIntArrayEntryArray = new SpecialIntArrayEntry[0];
   public static final LocalOptionType[] localOptionTypeArray = new LocalOptionType[0];
   public static final PreMotionInterceptor preMotionInterceptor2 = new AngleProfiler();
   public static final PreMotionInterceptor preMotionInterceptor = new ListenerAdapter8();

   private static Object resolveTarget(Comparable comparable) {
      return comparable instanceof PrimaryListener ? ((PrimaryListener)comparable).getText() : comparable.toString();
   }

   public String resolveTextByText(String text) {
      int count = text.lastIndexOf(47);
      return count < 0 ? "" : text.substring(0, count);
   }

   public BiomeIDIsOutOfBoundsManager resolveBiomeIDIsOutOfBoundsManager(String text) {
      text = text.toLowerCase();
      if (text.equals("nether")) {
         return BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager38;
      } else {
         BiomeIDIsOutOfBoundsManager[] biomeIDIsOutOfBoundsManagerArray = BiomeIDIsOutOfBoundsManager.getBiomeIDIsOutOfBoundsManagerArray();

         for (int index = 0; index < biomeIDIsOutOfBoundsManagerArray.length; index++) {
            BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = biomeIDIsOutOfBoundsManagerArray[index];
            if (biomeIDIsOutOfBoundsManager != null && biomeIDIsOutOfBoundsManager.labelText.replace(" ", "").toLowerCase().equals(text)) {
               return biomeIDIsOutOfBoundsManager;
            }
         }

         return null;
      }
   }

   public IBlockState resolveIBlockState(String text, IBlockState blockState) {
      SmoothMatcher[] smoothMatcherArray = this.resolveSmoothMatcherArray(text);
      if (smoothMatcherArray == null) {
         return blockState;
      } else {
         return smoothMatcherArray.length != 1 ? blockState : Block.processAction3(smoothMatcherArray[0].getCount()).getDefaultState();
      }
   }

   public LocalOptionType[] resolveLocalOptionTypeArray(String text, String text2, LocalOptionType[] localOptionTypeArray2) {
      return (LocalOptionType[])this.resolveTargetArray(text, LocalOptionType.values(), preMotionInterceptor, text2, localOptionTypeArray2);
   }

   public String resolveText(String text) {
      String text2 = text;
      int count = text.lastIndexOf(47);
      if (count >= 0) {
         text2 = text.substring(count + 1);
      }

      count = text2.lastIndexOf(46);
      if (count >= 0) {
         text2 = text2.substring(0, count);
      }

      return text2;
   }

   public void applyText(String text) {
      BuildManager.applyText3("" + this.labelText + ": " + text);
   }

   public SmoothMatcher[] resolveSmoothMatcherArray(String text) {
      if (text == null) {
         return null;
      } else {
         text = text.trim();
         if (text.length() <= 0) {
            return null;
         } else {
            String[] textArray = BuildManager.resolveTextArray(text, ":");
            String text2;
            byte byteValue;
            if (textArray.length > 1 && this.matchesTextArray(textArray)) {
               text2 = textArray[0];
               byteValue = 1;
            } else {
               text2 = "minecraft";
               byteValue = 0;
            }

            String text3 = textArray[byteValue];
            textArray = Arrays.copyOfRange(textArray, byteValue + 1, textArray.length);
            Block[] blockArray = this.resolveBlockArray(text2, text3);
            if (blockArray == null) {
               return null;
            } else {
               SmoothMatcher[] smoothMatcherArray = new SmoothMatcher[blockArray.length];

               for (int index = 0; index < blockArray.length; index++) {
                  Block block = blockArray[index];
                  int idFromBlock = Block.getIdFromBlock(block);
                  int[] countArray = null;
                  if (textArray.length > 0) {
                     countArray = this.resolveIndexArray(block, textArray);
                     if (countArray == null) {
                        return null;
                     }
                  }

                  SmoothMatcher smoothMatcher = new SmoothMatcher(idFromBlock, countArray);
                  smoothMatcherArray[index] = smoothMatcher;
               }

               return smoothMatcherArray;
            }
         }
      }
   }

   private static int computeCount(int count, String text) {
      int count2 = BuildManager.computeCount(text, -1);
      if (count2 >= 0) {
         return count2;
      } else {
         if (count == 0) {
            if (text.equals("farmer")) {
               return 1;
            }

            if (text.equals("fisherman")) {
               return 2;
            }

            if (text.equals("shepherd")) {
               return 3;
            }

            if (text.equals("fletcher")) {
               return 4;
            }
         }

         if (count == 1) {
            if (text.equals("librarian")) {
               return 1;
            }

            if (text.equals("cartographer")) {
               return 2;
            }
         }

         if (count == 2 && text.equals("cleric")) {
            return 1;
         } else {
            if (count == 3) {
               if (text.equals("armor")) {
                  return 1;
               }

               if (text.equals("weapon")) {
                  return 2;
               }

               if (text.equals("tool")) {
                  return 3;
               }
            }

            if (count == 4) {
               if (text.equals("butcher")) {
                  return 1;
               }

               if (text.equals("leather")) {
                  return 2;
               }
            }

            return count == 5 && text.equals("nitwit") ? 1 : -1;
         }
      }
   }

   public TargetAggregator resolveTargetAggregator(String text) {
      if (text == null) {
         return null;
      } else {
         TargetAggregator targetAggregator = new TargetAggregator();
         String[] textArray = BuildManager.resolveTextArray(text, " ,");

         for (int index = 0; index < textArray.length; index++) {
            String text2 = textArray[index];
            RotationMonitor rotationMonitor = this.resolveRotationMonitor(text2);
            if (rotationMonitor == null) {
               return null;
            }

            targetAggregator.applyRotationMonitor(rotationMonitor);
         }

         return targetAggregator;
      }
   }

   public boolean matchesIBlockState(IBlockState blockState, Map map) {
      for (Object iPropertyObj : map.keySet()) {
         IProperty iProperty = (IProperty)iPropertyObj;
         List list = (List)map.get(iProperty);
         Comparable comparable = blockState.getComparable(iProperty);
         if (comparable == null) {
            return false;
         }

         if (!list.contains(comparable)) {
            return false;
         }
      }

      return true;
   }

   public BlockSolidType resolveBlockSolidType(String text, BlockSolidType blockSolidType) {
      if (text == null) {
         return blockSolidType;
      } else {
         text = text.toLowerCase().trim();
         BlockSolidType[] values = BlockSolidType.values();

         for (int index = 0; index < values.length; index++) {
            BlockSolidType blockSolidType2 = values[index];
            if (text.equals(blockSolidType2.name().toLowerCase())) {
               return blockSolidType2;
            }
         }

         return blockSolidType;
      }
   }

   public EnumFacing resolveEnumFacing(String text) {
      text = text.toLowerCase();
      if (text.equals("bottom") || text.equals("down")) {
         return EnumFacing.DOWN;
      } else if (text.equals("top") || text.equals("up")) {
         return EnumFacing.UP;
      } else if (text.equals("north")) {
         return EnumFacing.NORTH;
      } else if (text.equals("south")) {
         return EnumFacing.SOUTH;
      } else if (text.equals("east")) {
         return EnumFacing.EAST;
      } else if (text.equals("west")) {
         return EnumFacing.WEST;
      } else {
         BuildManager.applyText3("Unknown face: " + text);
         return null;
      }
   }

   public BiomeIDIsOutOfBoundsManager[] resolveBiomeIDIsOutOfBoundsManagerA(String text) {
      if (text == null) {
         return null;
      } else {
         text = text.trim();
         boolean flag = false;
         if (text.startsWith("!")) {
            flag = true;
            text = text.substring(1);
         }

         String[] textArray = BuildManager.resolveTextArray(text, " ");
         ArrayList list = new ArrayList();

         for (int index = 0; index < textArray.length; index++) {
            String text2 = textArray[index];
            BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = this.resolveBiomeIDIsOutOfBoundsManager(text2);
            if (biomeIDIsOutOfBoundsManager == null) {
               this.applyText("Biome not found: " + text2);
            } else {
               list.add(biomeIDIsOutOfBoundsManager);
            }
         }

         if (flag) {
            ArrayList list2 = new ArrayList<>(Arrays.asList(BiomeIDIsOutOfBoundsManager.getBiomeIDIsOutOfBoundsManagerArray()));
            list2.removeAll(list);
            list = list2;
         }

         return (BiomeIDIsOutOfBoundsManager[])list.toArray(new BiomeIDIsOutOfBoundsManager[list.size()]);
      }
   }

   public Enum[] resolveEnumArray(String text, Enum[] enumArray, String text2, Enum[] enumArray2) {
      return (Enum[])this.resolveTargetArray(text, enumArray, preMotionInterceptor2, text2, enumArray2);
   }

   public static int computeCountByText(String text, int count) {
      if (text == null) {
         return count;
      } else {
         text = text.trim();

         try {
            return (int)(Long.parseLong(text, 16) & -1L);
         } catch (NumberFormatException numberFormatException) {
            return count;
         }
      }
   }

   public int[] resolveIndexArray(String text) {
      text = text.trim();
      TreeSet treeSet = new TreeSet();
      String[] textArray = BuildManager.resolveTextArray(text, " ");

      for (int index = 0; index < textArray.length; index++) {
         String text2 = textArray[index];
         int count = TargetWatcher.computeCount(text2);
         if (count < 0) {
            this.applyText("Entity not found: " + text2);
         } else {
            treeSet.add(new Integer(count));
         }
      }

      return BuildManager.resolveIndexArray((Integer[])treeSet.toArray(new Integer[treeSet.size()]));
   }

   public int computeCount(String text, int count) {
      if (text == null) {
         return count;
      } else {
         text = text.trim();
         int count2 = BuildManager.computeCount(text, -1);
         if (count2 < 0) {
            this.applyText("Invalid number: " + text);
            return count;
         } else {
            return count2;
         }
      }
   }

   private SpecialIntArrayEntry resolveSpecialIntArrayEntry(String text) {
      text = text.toLowerCase();
      String[] textArray = BuildManager.resolveTextArray(text, ":");
      if (textArray.length > 2) {
         return null;
      } else {
         String text2 = textArray[0];
         String text3 = null;
         if (textArray.length > 1) {
            text3 = textArray[1];
         }

         int count = computeCount(text2);
         if (count < 0) {
            return null;
         } else {
            int[] countArray = null;
            if (text3 != null) {
               countArray = resolveIndexArray(count, text3);
               if (countArray == null) {
                  return null;
               }
            }

            return new SpecialIntArrayEntry(count, countArray);
         }
      }
   }

   public SpecialIntArrayEntry[] resolveSpecialIntArrayEntryArray(String text) {
      if (text == null) {
         return null;
      } else {
         ArrayList list = new ArrayList();
         String[] textArray = BuildManager.resolveTextArray(text, " ");

         for (int index = 0; index < textArray.length; index++) {
            String text2 = textArray[index];
            SpecialIntArrayEntry specialIntArrayEntry = this.resolveSpecialIntArrayEntry(text2);
            if (specialIntArrayEntry == null) {
               this.applyText("Invalid profession: " + text2);
               return specialIntArrayEntryArray;
            }

            list.add(specialIntArrayEntry);
         }

         return list.isEmpty() ? null : (SpecialIntArrayEntry[])list.toArray(new SpecialIntArrayEntry[list.size()]);
      }
   }

   public static Comparable resolveComparable(String text, Collection collection) {
      for (Object comparableObj : collection) {
         Comparable comparable = (Comparable)comparableObj;
         if (resolveTarget(comparable).equals(text)) {
            return comparable;
         }
      }

      return null;
   }

   public SmoothWatcher[] resolveSmoothWatcherArray(String text, String text2, SmoothWatcher[] smoothWatcherArray) {
      return (SmoothWatcher[])this.resolveTargetArray(text, SmoothWatcher.values(), preMotionInterceptor2, text2, smoothWatcherArray);
   }

   public boolean matchesTextArray(String[] textArray) {
      if (textArray.length < 2) {
         return false;
      } else {
         String text = textArray[1];
         return text.length() < 1 ? false : (this.matchesText(text) ? false : !text.contains("="));
      }
   }

   public boolean matchesText(String text, boolean flag) {
      if (text == null) {
         return flag;
      } else {
         String text2 = text.toLowerCase().trim();
         if (text2.equals("true")) {
            return true;
         } else if (text2.equals("false")) {
            return false;
         } else {
            this.applyText("Invalid boolean: " + text);
            return flag;
         }
      }
   }

   public static Comparable resolveComparable(IProperty iProperty, String text) {
      Class memberRef = iProperty.getMemberRef();
      Comparable comparable = resolveComparable(text, memberRef);
      if (comparable == null) {
         Collection collection = iProperty.getCollection();
         comparable = resolveComparable(text, collection);
      }

      return comparable;
   }

   public GeometryObserver resolveGeometryObserver(String text, String text2) {
      return text != null && text2 != null ? new GeometryObserver(text, text2) : null;
   }

   public static int computeCount2(String text, int count) {
      if (text == null) {
         return count;
      } else {
         text = text.trim();

         try {
            return Integer.parseInt(text, 16) & 16777215;
         } catch (NumberFormatException numberFormatException) {
            return count;
         }
      }
   }

   public static Comparable resolveComparable(String text, Class clazz) {
      if (clazz == String.class) {
         return text;
      } else if (clazz == Boolean.class) {
         return Boolean.valueOf(text);
      } else if (clazz == Float.class) {
         return Float.valueOf(text);
      } else if (clazz == Double.class) {
         return Double.valueOf(text);
      } else if (clazz == Integer.class) {
         return Integer.valueOf(text);
      } else {
         return clazz == Long.class ? Long.valueOf(text) : null;
      }
   }

   private RotationMonitor resolveRotationMonitor(String text) {
      if (text == null) {
         return null;
      } else if (text.indexOf(45) >= 0) {
         String[] textArray = BuildManager.resolveTextArray(text, "-");
         if (textArray.length != 2) {
            this.applyText("Invalid range: " + text);
            return null;
         } else {
            int count = BuildManager.computeCount(textArray[0], -1);
            int count2 = BuildManager.computeCount(textArray[1], -1);
            if (count >= 0 && count2 >= 0) {
               return new RotationMonitor(count, count2);
            } else {
               this.applyText("Invalid range: " + text);
               return null;
            }
         }
      } else {
         int count3 = BuildManager.computeCount(text, -1);
         if (count3 < 0) {
            this.applyText("Invalid integer: " + text);
            return null;
         } else {
            return new RotationMonitor(count3, count3);
         }
      }
   }

   private static int[] resolveIndexArray(int count, String text) {
      HashSet set = new HashSet();
      String[] textArray = BuildManager.resolveTextArray(text, ",");

      for (int index = 0; index < textArray.length; index++) {
         String text2 = textArray[index];
         int count2 = computeCount(count, text2);
         if (count2 < 0) {
            return null;
         }

         set.add(count2);
      }

      Integer[] countArray = (Integer[])set.toArray(new Integer[set.size()]);
      int[] countArray2 = new int[countArray.length];

      for (int index2 = 0; index2 < countArray2.length; index2++) {
         countArray2[index2] = countArray[index2];
      }

      return countArray2;
   }

   public int[] resolveIndexArray2(String text) {
      text = text.trim();
      TreeSet treeSet = new TreeSet();
      String[] textArray = BuildManager.resolveTextArray(text, " ");

      for (int index = 0; index < textArray.length; index++) {
         String text2 = textArray[index];
         ResourceLocation location = new ResourceLocation(text2);
         Item object = (Item)Item.iterableExtensionAdapter.getObject(location);
         if (object == null) {
            this.applyText("Item not found: " + text2);
         } else {
            int index2 = Item.getIndex(object);
            if (index2 < 0) {
               this.applyText("Item has no ID: " + object + ", name: " + text2);
            } else {
               treeSet.add(new Integer(index2));
            }
         }
      }

      return BuildManager.resolveIndexArray((Integer[])treeSet.toArray(new Integer[treeSet.size()]));
   }

   public boolean[] resolveFlagArray(String text, boolean[] flagArray) {
      if (text == null) {
         return flagArray;
      } else {
         EnumSet enumSet = EnumSet.allOf(EnumFacing.class);
         String[] textArray = BuildManager.resolveTextArray(text, " ,");

         for (int index = 0; index < textArray.length; index++) {
            String text2 = textArray[index];
            if (text2.equals("sides")) {
               enumSet.add(EnumFacing.NORTH);
               enumSet.add(EnumFacing.SOUTH);
               enumSet.add(EnumFacing.WEST);
               enumSet.add(EnumFacing.EAST);
            } else if (text2.equals("all")) {
               enumSet.addAll(Arrays.asList(EnumFacing.VALUES));
            } else {
               EnumFacing enumFacing = this.resolveEnumFacing(text2);
               if (enumFacing != null) {
                  enumSet.add(enumFacing);
               }
            }
         }

         boolean[] flagArray2 = new boolean[EnumFacing.VALUES.length];

         for (int index2 = 0; index2 < flagArray2.length; index2++) {
            flagArray2[index2] = enumSet.contains(EnumFacing.VALUES[index2]);
         }

         return flagArray2;
      }
   }

   public int[] resolveIndexArray(Block block, String[] textArray) {
      if (textArray.length <= 0) {
         return null;
      } else {
         String text = textArray[0];
         if (this.matchesText(text)) {
            return this.resolveIndexArrayByText(text);
         } else {
            IBlockState defaultState = block.getDefaultState();
            Collection collection = defaultState.getCollection();
            HashMap map = new HashMap();

            for (int index = 0; index < textArray.length; index++) {
               String text2 = textArray[index];
               if (text2.length() > 0) {
                  String[] textArray2 = BuildManager.resolveTextArray(text2, "=");
                  if (textArray2.length != 2) {
                     this.applyText("Invalid block property: " + text2);
                     return null;
                  }

                  text2 = textArray2[0];
                  String text3 = textArray2[1];
                  IProperty iProperty = AuxiliaryPngManager.resolveIProperty(text2, collection);
                  if (iProperty == null) {
                     this.applyText("Property not found: " + text2 + ", block: " + block);
                     return null;
                  }

                  List entry = (List)map.get(text2);
                  if (entry == null) {
                     entry = new ArrayList();
                     map.put(iProperty, entry);
                  }

                  textArray2 = BuildManager.resolveTextArray(text3, ",");

                  for (int index2 = 0; index2 < textArray2.length; index2++) {
                     String text4 = textArray2[index2];
                     Comparable comparable = resolveComparable(iProperty, text4);
                     if (comparable == null) {
                        this.applyText("Property value not found: " + text4 + ", property: " + text2 + ", block: " + block);
                        return null;
                     }

                     entry.add(comparable);
                  }
               }
            }

            if (map.isEmpty()) {
               return null;
            } else {
               ArrayList list = new ArrayList();

               for (int index3 = 0; index3 < 16; index3++) {
                  try {
                     IBlockState blockState = this.resolveIBlockState(block, index3);
                     if (this.matchesIBlockState(blockState, map)) {
                        list.add(index3);
                     }
                  } catch (IllegalArgumentException illegalArgumentException) {
                  }
               }

               if (list.size() == 16) {
                  return null;
               } else {
                  int[] countArray = new int[list.size()];

                  for (int index4 = 0; index4 < countArray.length; index4++) {
                     countArray[index4] = (Integer)list.get(index4);
                  }

                  return countArray;
               }
            }
         }
      }
   }

   private static int computeCount(String text) {
      int count = BuildManager.computeCount(text, -1);
      return count >= 0
         ? count
         : (
            text.equals("farmer")
               ? 0
               : (
                  text.equals("librarian")
                     ? 1
                     : (text.equals("priest") ? 2 : (text.equals("blacksmith") ? 3 : (text.equals("butcher") ? 4 : (text.equals("nitwit") ? 5 : -1))))
               )
         );
   }

   public boolean matchesText(String text) {
      if (text == null) {
         return false;
      } else {
         return text.length() < 1 ? false : Character.isDigit(text.charAt(0));
      }
   }

   public void applyTextByText(String text) {
      BuildManager.applyText4("" + this.labelText + ": " + text);
   }

   public Block[] resolveBlockArray(String text, String text2) {
      if (this.matchesText(text2)) {
         int[] countArray = this.resolveIndexArrayByText(text2);
         if (countArray == null) {
            return null;
         } else {
            Block[] blockArray = new Block[countArray.length];

            for (int index = 0; index < countArray.length; index++) {
               int count = countArray[index];
               Block block = Block.processAction3(count);
               if (block == null) {
                  this.applyText("Block not found for id: " + count);
                  return null;
               }

               blockArray[index] = block;
            }

            return blockArray;
         }
      } else {
         String text3 = text + ":" + text2;
         Block blockFromName = Block.getBlockFromName(text3);
         if (blockFromName == null) {
            this.applyText("Block not found for name: " + text3);
            return null;
         } else {
            return new Block[]{blockFromName};
         }
      }
   }

   public Object resolveTarget(String text, Object[] entryArray, PreMotionInterceptor preMotionInterceptor3, String text2) {
      if (text == null) {
         return null;
      } else {
         String text3 = text.toLowerCase().trim();

         for (int index = 0; index < entryArray.length; index++) {
            Object entry = entryArray[index];
            String text4 = preMotionInterceptor3.resolveText(entry);
            if (text4 != null && text4.toLowerCase().equals(text3)) {
               return entry;
            }
         }

         this.applyText("Invalid " + text2 + ": " + text);
         return null;
      }
   }

   public Boolean resolveBoolean(String text) {
      if (text == null) {
         return null;
      } else {
         String text2 = text.toLowerCase().trim();
         if (text2.equals("true")) {
            return Boolean.TRUE;
         } else if (text2.equals("false")) {
            return Boolean.FALSE;
         } else {
            this.applyText("Invalid boolean: " + text);
            return null;
         }
      }
   }

   public int[] resolveIndexArrayByText(String text) {
      if (text == null) {
         return null;
      } else {
         ArrayList list = new ArrayList();
         String[] textArray = BuildManager.resolveTextArray(text, " ,");

         for (int index = 0; index < textArray.length; index++) {
            String text2 = textArray[index];
            if (text2.contains("-")) {
               String[] textArray2 = BuildManager.resolveTextArray(text2, "-");
               if (textArray2.length != 2) {
                  this.applyText("Invalid interval: " + text2 + ", when parsing: " + text);
               } else {
                  int count = BuildManager.computeCount(textArray2[0], -1);
                  int count2 = BuildManager.computeCount(textArray2[1], -1);
                  if (count >= 0 && count2 >= 0 && count <= count2) {
                     while (count <= count2) {
                        list.add(count);
                        count++;
                     }
                  } else {
                     this.applyText("Invalid interval: " + text2 + ", when parsing: " + text);
                  }
               }
            } else {
               int count3 = BuildManager.computeCount(text2, -1);
               if (count3 < 0) {
                  this.applyText("Invalid number: " + text2 + ", when parsing: " + text);
               } else {
                  list.add(count3);
               }
            }
         }

         int[] countArray = new int[list.size()];

         for (int index2 = 0; index2 < countArray.length; index2++) {
            countArray[index2] = (Integer)list.get(index2);
         }

         return countArray;
      }
   }

   private IBlockState resolveIBlockState(Block block, int count) {
      try {
         IBlockState blockState = block.processAction(count);
         if (block == Blocks.blockDoublePlant && count > 7) {
            IBlockState blockState2 = block.processAction(count & 7);
            blockState = blockState.resolveIBlockState(BlockDoublePlant.immutableSetEntry, blockState2.getComparable(BlockDoublePlant.immutableSetEntry));
         }

         return blockState;
      } catch (IllegalArgumentException illegalArgumentException) {
         return block.getDefaultState();
      }
   }

   public SmoothMatcher[] resolveSmoothMatcherArrayByText(String text) {
      if (text == null) {
         return null;
      } else {
         ArrayList list = new ArrayList();
         String[] textArray = BuildManager.resolveTextArray(text, " ");

         for (int index = 0; index < textArray.length; index++) {
            String text2 = textArray[index];
            SmoothMatcher[] smoothMatcherArray = this.resolveSmoothMatcherArray(text2);
            if (smoothMatcherArray != null) {
               list.addAll(Arrays.asList(smoothMatcherArray));
            }
         }

         return (SmoothMatcher[])list.toArray(new SmoothMatcher[list.size()]);
      }
   }

   public Object[] resolveTargetArray(String text, Object[] entryArray, PreMotionInterceptor preMotionInterceptor3, String text2, Object[] entryArray2) {
      if (text == null) {
         return null;
      } else {
         text = text.toLowerCase().trim();
         String[] textArray = BuildManager.resolveTextArray(text, " ");
         Object[] entryArray3 = (Object[])Array.newInstance(entryArray.getClass().getComponentType(), textArray.length);

         for (int index = 0; index < textArray.length; index++) {
            String text3 = textArray[index];
            text3 = (String)this.resolveTarget(text3, entryArray, preMotionInterceptor3, text2);
            if (text3 == null) {
               return entryArray2;
            }

            entryArray3[index] = text3;
         }

         return entryArray3;
      }
   }

   public Enum resolveEnum(String text, Enum[] enumArray, String text2) {
      return (Enum)this.resolveTarget(text, enumArray, preMotionInterceptor2, text2);
   }

   public NetherManager(String text) {
      this.labelText = text;
   }
}
