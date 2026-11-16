package client.util;

import client.event.PostUpdateBroadcaster;
import client.render.TexturesManager;
import client.setting.LocalOptionType;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import javax.imageio.ImageIO;
import net.minecraft.block.Block;
import net.minecraft.block.BlockMelonStem;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExtendedBlockPosAdapter;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.TertiaryEntityAdapter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpawnEgg;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.ImmutablePair;

public class LoadingManager {
   public static String labelText = "vanilla";
   public static TexturesManager texturesManager3 = null;
   public static TexturesManager texturesManager7 = null;
   public static TexturesManager texturesManager = null;
   public static TexturesManager texturesManager14 = null;
   public static TexturesManager texturesManager15 = null;
   public static TexturesManager[] texturesManagerArray2 = null;
   public static TexturesManager[][] texturesManagerArray = null;
   public static TexturesManager texturesManager12 = null;
   public static Vec3Entry3 vec3Entry5 = new Vec3Entry3();
   public static TexturesManager texturesManager13 = null;
   public static Vec3Entry3 vec3Entry3 = new Vec3Entry3();
   public static TexturesManager texturesManager8 = null;
   public static Vec3Entry3 vec3Entry2 = new Vec3Entry3();
   public static TexturesManager texturesManager5 = null;
   public static Vec3Entry3 vec3Entry4 = new Vec3Entry3();
   public static VectorWatcher[] vectorWatcherArray = null;
   public static int count8 = 0;
   public static TexturesManager texturesManager9 = null;
   public static TexturesManager texturesManager4 = null;
   public static int count6 = -1;
   public static TexturesManager texturesManager16 = null;
   public static TexturesManager texturesManager10 = null;
   public static TexturesManager texturesManager6 = null;
   public static TexturesManager texturesManager11 = null;
   public static TexturesManager texturesManager2 = null;
   public static boolean enabled = true;
   public static int count5 = -1;
   public static int count7 = -1;
   public static int count3 = -1;
   public static int count = -1;
   public static int count4 = -1;
   public static int count2 = -1;
   public static Vec3 vec2 = null;
   public static Vec3 vec4 = null;
   public static Vec3 vec3 = null;
   public static int[] countArray = null;
   public static int[] countArray3 = null;
   public static float[][] factorArray2 = null;
   public static float[][] factorArray = null;
   public static int[] countArray2 = null;
   public static int[] countArray5 = null;
   public static int[] countArray4 = null;
   public static final IBlockState iBlockState = Blocks.block115.getDefaultState();
   public static final IBlockState iBlockState2 = Blocks.blockLava2.getDefaultState();
   public static Random random = new Random();
   public static final PostUpdateBroadcaster postUpdateBroadcaster3 = new ListenerAdapter4();
   public static final PostUpdateBroadcaster postUpdateBroadcaster5 = new AngleSerializer();
   public static final PostUpdateBroadcaster postUpdateBroadcaster4 = new AnglePicker();
   public static final PostUpdateBroadcaster postUpdateBroadcaster = new AuxiliaryListenerHandler();
   public static final PostUpdateBroadcaster postUpdateBroadcaster2 = new SharedClientManager();

   public static Vec3 resolveVecByIBlockAccess(IBlockAccess iBlockAccess, double posX, double posY, double posZ) {
      return resolveVec3(iBlockAccess, posX, posY, posZ, texturesManager8, vec3Entry2);
   }

   private static float[] resolveFloatArray(LocalOptionType localOptionType, float[][] factorMatrix, float[] factorArray3) {
      if (factorMatrix == null) {
         return factorArray3;
      } else if (localOptionType == null) {
         return factorArray3;
      } else {
         float[] factorArray4 = factorMatrix[localOptionType.ordinal()];
         return factorArray4 == null ? factorArray3 : factorArray4;
      }
   }

   private static Vec3 resolveVecByVec3(Vec3 vec, IBlockAccess iBlockAccess, double posX, double posY, double posZ) {
      if (texturesManager13 == null) {
         return vec;
      } else {
         int count9 = texturesManager13.computeCount(iBlockAccess, posX, posY, posZ, 3);
         int count10 = count9 >> 16 & 0xFF;
         int count11 = count9 >> 8 & 0xFF;
         int count12 = count9 & 0xFF;
         float factor = (float)count10 / 255.0F;
         float factor2 = (float)count11 / 255.0F;
         float factor3 = (float)count12 / 255.0F;
         float factor4 = (float)vec.xCoord / 0.753F;
         float factor5 = (float)vec.yCoord / 0.8471F;
         float posZ2 = (float)vec.zCoord;
         float factor6 = factor * factor4;
         float factor7 = factor2 * factor5;
         float factor8 = factor3 * posZ2;
         return vec3Entry3.resolveVec3((double)factor6, (double)factor7, (double)factor8);
      }
   }

   private static Vec3 resolveVecByVec3(Vec3 vec) {
      return vec4 == null ? vec : vec4;
   }

   private static void applyTextByText(String text) {
      try {
         InputStream inputStream = BuildManager.resolveInputStream(new ResourceLocation(text));
         if (inputStream == null) {
            return;
         }

         applyText("Loading " + text);
         PropertiesAdapter propertiesAdapter = new PropertiesAdapter();
         propertiesAdapter.load(inputStream);
         inputStream.close();
         count5 = computeCount(propertiesAdapter, new String[]{"particle.water", "drop.water"});
         count7 = computeCount(propertiesAdapter, "particle.portal");
         count3 = computeCount(propertiesAdapter, "lilypad");
         count = computeCount(propertiesAdapter, "text.xpbar");
         count4 = computeCount(propertiesAdapter, "text.boss");
         count2 = computeCount(propertiesAdapter, "text.sign");
         vec2 = resolveVec3(propertiesAdapter, "fog.nether");
         vec4 = resolveVec3(propertiesAdapter, "fog.end");
         vec3 = resolveVec3(propertiesAdapter, "sky.end");
         texturesManagerArray2 = resolveTexturesManagerArray(propertiesAdapter, text);
         countArray = resolveIndexArray(propertiesAdapter, text, "egg.shell.", "Spawn egg shell");
         countArray3 = resolveIndexArray(propertiesAdapter, text, "egg.spots.", "Spawn egg spot");
         factorArray2 = resolveFloatArray(propertiesAdapter, text, "collar.", "Wolf collar");
         factorArray = resolveFloatArray(propertiesAdapter, text, "sheep.", "Sheep");
         countArray2 = resolveIndexArray3(propertiesAdapter, text, "text.code.", "Text");
         int[] countArray6 = resolveIndexArrayByProperties(propertiesAdapter, text, "map.", "Map");
         if (countArray6 != null) {
            if (countArray5 == null) {
               countArray5 = getIndexArray();
            }

            applyIndexArray(countArray6);
         }

         countArray4 = resolveIndexArray2(propertiesAdapter, text, "potion.", "Potion");
         count6 = BuildManager.computeCount(propertiesAdapter.getProperty("xporb.time"), -1);
      } catch (FileNotFoundException fileNotFoundException) {
         return;
      } catch (IOException ioException) {
         ioException.printStackTrace();
      }
   }

   private static TexturesManager[] resolveTexturesManagerArray(Properties properties, String text) {
      ArrayList list = new ArrayList();
      String text2 = "palette.block.";
      HashMap map = new HashMap();

      for (Object text3Obj : properties.keySet()) {
         String text3 = (String)text3Obj;
         String property = properties.getProperty(text3);
         if (text3.startsWith(text2)) {
            map.put(text3, property);
         }
      }

      String[] textArray = (String[])map.keySet().toArray(new String[map.size()]);

      for (int index = 0; index < textArray.length; index++) {
         String text4 = textArray[index];
         String property2 = properties.getProperty(text4);
         applyText("Block palette: " + text4 + " = " + property2);
         String text5 = text4.substring(text2.length());
         String text6 = InternalPngManager.resolveText(text);
         String text7 = InternalPngManager.resolveText(text5, text6);
         TexturesManager texturesManager17 = resolveTexturesManager(text7, 256, 256);
         if (texturesManager17 == null) {
            applyText2("Colormap not found: " + text7);
         } else {
            SmoothMatcher[] smoothMatcherArray = new NetherManager("CustomColors").resolveSmoothMatcherArrayByText(property2);
            if (smoothMatcherArray != null && smoothMatcherArray.length > 0) {
               for (int index2 = 0; index2 < smoothMatcherArray.length; index2++) {
                  SmoothMatcher smoothMatcher = smoothMatcherArray[index2];
                  texturesManager17.applySmoothMatcher(smoothMatcher);
               }

               list.add(texturesManager17);
            } else {
               applyText2("Invalid match blocks: " + property2);
            }
         }
      }

      return list.size() <= 0 ? null : (TexturesManager[])list.toArray(new TexturesManager[list.size()]);
   }

   private static TexturesManager[][] resolveTexturesManagerArray(String[] textArray, TexturesManager[] texturesManagerArray3, int count9, int count10) {
      String[] textArray2 = AssetsMinecraftManager.resolveTextArray(textArray, new String[]{".properties"});
      Arrays.sort(textArray2);
      ArrayList list = new ArrayList();

      for (int index = 0; index < textArray2.length; index++) {
         String text = textArray2[index];
         applyText("Block colormap: " + text);

         try {
            InputStream inputStream = BuildManager.resolveInputStream(new ResourceLocation("minecraft", text));
            if (inputStream == null) {
               applyText2("File not found: " + text);
            } else {
               PropertiesAdapter propertiesAdapter = new PropertiesAdapter();
               propertiesAdapter.load(inputStream);
               inputStream.close();
               TexturesManager texturesManager17 = new TexturesManager(propertiesAdapter, text, count9, count10, labelText);
               if (texturesManager17.matchesText(text) && texturesManager17.matchesTextByText(text)) {
                  applyTexturesManager(texturesManager17, list);
               }
            }
         } catch (FileNotFoundException fileNotFoundException) {
            applyText2("File not found: " + text);
         } catch (Exception exception) {
            exception.printStackTrace();
         }
      }

      if (texturesManagerArray3 != null) {
         for (int index2 = 0; index2 < texturesManagerArray3.length; index2++) {
            applyTexturesManager(texturesManagerArray3[index2], list);
         }
      }

      return list.size() <= 0 ? (TexturesManager[][])null : resolveTexturesManagerArray(list);
   }

   private static int computeCount(ItemSpawnEgg itemSpawnEgg, ItemStack stack, int count9, int count10) {
      int indexOffset = stack.getIndexOffset();
      int[] countArray6 = count9 == 0 ? countArray : countArray3;
      if (countArray6 == null) {
         return count10;
      } else if (indexOffset >= 0 && indexOffset < countArray6.length) {
         int count11 = countArray6[indexOffset];
         return count11 < 0 ? count10 : count11;
      } else {
         return count10;
      }
   }

   public static void applyTertiaryEntityAdapter(TertiaryEntityAdapter tertiaryEntityAdapter, IBlockAccess iBlockAccess, double posX, double posY, double posZ) {
      if (texturesManager9 != null) {
         int count9 = computeCount(iBlockAccess.getBlockState(new BlockPos(posX, posY, posZ)), 15);
         int count10 = texturesManager9.computeCount(count9);
         int count11 = count10 >> 16 & 0xFF;
         int count12 = count10 >> 8 & 0xFF;
         int count13 = count10 & 0xFF;
         float factor = (float)count11 / 255.0F;
         float factor2 = (float)count12 / 255.0F;
         float factor3 = (float)count13 / 255.0F;
         tertiaryEntityAdapter.applyFloatByFloat(factor, factor2, factor3);
      }
   }

   public static int computeCountByIndex(int count9) {
      if (texturesManager16 == null) {
         return -1;
      } else {
         int count10 = count9 * texturesManager16.getCount() / 255;
         return texturesManager16.computeCount(count10);
      }
   }

   private static void applyTexturesManager(TexturesManager texturesManager17, List list) {
      int[] indexArray = texturesManager17.getIndexArray();
      if (indexArray != null && indexArray.length > 0) {
         for (int index = 0; index < indexArray.length; index++) {
            int count9 = indexArray[index];
            if (count9 < 0) {
               applyText2("Invalid block ID: " + count9);
            } else {
               applyTexturesManager(texturesManager17, list, count9);
            }
         }
      } else {
         applyText2("No match blocks: " + BuildManager.resolveText(indexArray));
      }
   }

   public static Vec3 resolveVecByVec3(Vec3 vec, World world, Entity entity, float factor) {
      Vec3 vec5 = vec;
      switch (world.secondaryFloatArrayEntry.getCountAsIndex()) {
         case -1:
            vec5 = resolveVec3(vec);
            break;
         case 0:
            Minecraft mc = Minecraft.getMinecraft();
            vec5 = resolveVecByVec3(vec, mc.theWorld, entity.posX, entity.posY + 1.0, entity.posZ);
            break;
         case 1:
            vec5 = resolveVecByVec3(vec);
      }

      return vec5;
   }

   public static int computeCount(int count9, int count10) {
      if (countArray4 == null) {
         return count10;
      } else if (count9 >= 0 && count9 < countArray4.length) {
         int count11 = countArray4[count9];
         return count11 < 0 ? count10 : count11;
      } else {
         return count10;
      }
   }

   public static TexturesManager getTexturesManager15() {
      return texturesManager15;
   }

   public static Vec3 resolveVec3(Vec3 vec, IBlockAccess iBlockAccess, double posX, double posY, double posZ) {
      if (texturesManager12 == null) {
         return vec;
      } else {
         int count9 = texturesManager12.computeCount(iBlockAccess, posX, posY, posZ, 3);
         int count10 = count9 >> 16 & 0xFF;
         int count11 = count9 >> 8 & 0xFF;
         int count12 = count9 & 0xFF;
         float factor = (float)count10 / 255.0F;
         float factor2 = (float)count11 / 255.0F;
         float factor3 = (float)count12 / 255.0F;
         float factor4 = (float)vec.xCoord / 0.5F;
         float factor5 = (float)vec.yCoord / 0.66275F;
         float posZ2 = (float)vec.zCoord;
         float factor6 = factor * factor4;
         float factor7 = factor2 * factor5;
         float factor8 = factor3 * posZ2;
         return vec3Entry5.resolveVec3((double)factor6, (double)factor7, (double)factor8);
      }
   }

   private static void applyTexturesManager(TexturesManager texturesManager17, List list, int count9) {
      while (count9 >= list.size()) {
         list.add(null);
      }

      List entry = (List)list.get(count9);
      if (entry == null) {
         entry = new ArrayList();
         list.set(count9, entry);
      }

      entry.add(texturesManager17);
   }

   private static int[] getIndexArray() {
      MapColor[] mapColorArray = MapColor.mapColorArray;
      int[] countArray6 = new int[mapColorArray.length];
      Arrays.fill(countArray6, -1);

      for (int index = 0; index < mapColorArray.length && index < countArray6.length; index++) {
         MapColor mapColor = mapColorArray[index];
         if (mapColor != null) {
            countArray6[index] = mapColor.indexOffset;
         }
      }

      return countArray6;
   }

   public static TexturesManager getTexturesManager14() {
      return texturesManager14;
   }

   private static TexturesManager resolveTexturesManager(IBlockState blockState) {
      if (texturesManagerArray == null) {
         return null;
      } else if (!(blockState instanceof IBlockStateAdapter)) {
         return null;
      } else {
         IBlockStateAdapter iBlockStateAdapter = (IBlockStateAdapter)blockState;
         int count9 = iBlockStateAdapter.getCount2();
         if (count9 >= 0 && count9 < texturesManagerArray.length) {
            TexturesManager[] texturesManagerArray3 = texturesManagerArray[count9];
            if (texturesManagerArray3 == null) {
               return null;
            } else {
               for (int index = 0; index < texturesManagerArray3.length; index++) {
                  TexturesManager texturesManager17 = texturesManagerArray3[index];
                  if (texturesManager17.matchesIBlockStateAdapter(iBlockStateAdapter)) {
                     return texturesManager17;
                  }
               }

               return null;
            }
         } else {
            return null;
         }
      }
   }

   public static BiomeIDIsOutOfBoundsManager resolveBiomeIDIsOutOfBoundsManager(IBlockAccess iBlockAccess, BlockPos blockPos) {
      BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = iBlockAccess.resolveBiomeIDIsOutOfBoundsManager(blockPos);
      if (biomeIDIsOutOfBoundsManager == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager16 && !BuildManager.isActive23()) {
         biomeIDIsOutOfBoundsManager = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager35;
      }

      return biomeIDIsOutOfBoundsManager;
   }

   public static int computeCount(float factor) {
      if (texturesManager4 == null) {
         return -1;
      } else {
         int count9 = (int)Math.round((double)((MathHelper.abs(factor) + 1.0F) * (float)(texturesManager4.getCount() - 1)) / 2.0);
         return texturesManager4.computeCount(count9);
      }
   }

   public static TexturesManager getTexturesManager7() {
      return texturesManager7;
   }

   private static void applyText(String text) {
      BuildManager.applyText4("CustomColors: " + text);
   }

   private static String resolveText(String text, String text2, String[] textArray, String text3) {
      try {
         InputStream inputStream = BuildManager.resolveInputStream(new ResourceLocation(text));
         if (inputStream == null) {
            return text3;
         } else {
            PropertiesAdapter propertiesAdapter = new PropertiesAdapter();
            propertiesAdapter.load(inputStream);
            inputStream.close();
            String property = propertiesAdapter.getProperty(text2);
            if (property == null) {
               return text3;
            } else if (!Arrays.asList(textArray).contains(property)) {
               applyText2("Invalid value: " + text2 + "=" + property);
               applyText2("Expected values: " + BuildManager.resolveText(textArray));
               return text3;
            } else {
               applyText("" + text2 + "=" + property);
               return property;
            }
         }
      } catch (FileNotFoundException fileNotFoundException) {
         return text3;
      } catch (IOException ioException) {
         ioException.printStackTrace();
         return text3;
      }
   }

   private static org.apache.commons.lang3.tuple.Pair getPair() {
      String text = "mcpatcher/lightmap/world";
      String text2 = ".png";
      String[] textArray = AssetsMinecraftManager.resolveTextArray(text, text2);
      HashMap map = new HashMap();

      for (int index = 0; index < textArray.length; index++) {
         String text3 = textArray[index];
         String text4 = SplineCollector.resolveText3(text3, text, text2);
         int count9 = BuildManager.computeCount(text4, Integer.MIN_VALUE);
         if (count9 == Integer.MIN_VALUE) {
            applyText2("Invalid dimension ID: " + text4 + ", path: " + text3);
         } else {
            map.put(count9, text3);
         }
      }

      Set keys = map.keySet();
      Integer[] countArray6 = (Integer[])keys.toArray(new Integer[keys.size()]);
      Arrays.sort(countArray6);
      if (countArray6.length <= 0) {
         return new ImmutablePair(null, 0);
      } else {
         int count10 = countArray6[0];
         TexturesManager[] texturesManagerArray3 = new TexturesManager[countArray6[countArray6.length - 1] - count10 + 1];

         for (int index2 = 0; index2 < countArray6.length; index2++) {
            Integer count11 = countArray6[index2];
            String text5 = (String)map.get(count11);
            TexturesManager texturesManager17 = resolveTexturesManager(text5, -1, -1);
            if (texturesManager17 != null) {
               if (texturesManager17.getCount2() < 16) {
                  applyText2("Invalid lightmap width: " + texturesManager17.getCount2() + ", path: " + text5);
               } else {
                  int count12 = count11 - count10;
                  texturesManagerArray3[count12] = texturesManager17;
               }
            }
         }

         VectorWatcher[] vectorWatcherArray2 = new VectorWatcher[texturesManagerArray3.length];

         for (int index3 = 0; index3 < texturesManagerArray3.length; index3++) {
            TexturesManager texturesManager18 = texturesManagerArray3[index3];
            if (texturesManager18 != null) {
               String labelText2 = texturesManager18.labelText;
               String labelText3 = texturesManager18.labelText7;
               TexturesManager texturesManager19 = resolveTexturesManager(labelText3 + "/" + labelText2 + "_rain.png", -1, -1);
               TexturesManager texturesManager20 = resolveTexturesManager(labelText3 + "/" + labelText2 + "_thunder.png", -1, -1);
               EnhancedCustom enhancedCustom = new EnhancedCustom(texturesManager18);
               EnhancedCustom enhancedCustom2 = texturesManager19 != null ? new EnhancedCustom(texturesManager19) : null;
               EnhancedCustom enhancedCustom3 = texturesManager20 != null ? new EnhancedCustom(texturesManager20) : null;
               VectorWatcher vectorWatcher = new VectorWatcher(enhancedCustom, enhancedCustom2, enhancedCustom3);
               vectorWatcherArray2[index3] = vectorWatcher;
            }
         }

         return new ImmutablePair(vectorWatcherArray2, count10);
      }
   }

   public static int computeCount(int count9) {
      return count4 < 0 ? count9 : count4;
   }

   private static int[] resolveIndexArray(Properties properties, String text, String text2, String text3) {
      ArrayList list = new ArrayList();
      Set keys = properties.keySet();
      int count9 = 0;

      for (Object text4Obj : keys) {
         String text4 = (String)text4Obj;
         String property = properties.getProperty(text4);
         if (text4.startsWith(text2)) {
            int count10 = TargetWatcher.computeCount(SplineCollector.resolveText2(text4, text2));
            if (count10 < 0) {
               applyText2("Invalid spawn egg name: " + text4);
            } else {
               int count11 = computeCount(property);
               if (count11 < 0) {
                  applyText2("Invalid spawn egg color: " + text4 + " = " + property);
               } else {
                  while (list.size() <= count10) {
                     list.add(-1);
                  }

                  list.set(count10, count11);
                  count9++;
               }
            }
         }
      }

      if (count9 <= 0) {
         return null;
      } else {
         applyText(text3 + " colors: " + count9);
         int[] countArray6 = new int[list.size()];

         for (int index = 0; index < countArray6.length; index++) {
            countArray6[index] = (Integer)list.get(index);
         }

         return countArray6;
      }
   }

   public static float computeFactor(float factor) {
      if (count6 <= 0) {
         return factor;
      } else {
         float factor2 = 628.0F / (float)count6;
         return factor * factor2;
      }
   }

   public static void updateState() {
      enabled = texturesManager == null
         && texturesManager7 == null
         && texturesManager15 == null
         && texturesManager14 == null
         && BuildManager.isActive23()
         && BuildManager.isActive60();
   }

   public static void applyTertiaryEntityAdapter(TertiaryEntityAdapter tertiaryEntityAdapter) {
      if (texturesManager2 != null) {
         int count9 = texturesManager2.nextInt();
         int count10 = count9 >> 16 & 0xFF;
         int count11 = count9 >> 8 & 0xFF;
         int count12 = count9 & 0xFF;
         float factor = (float)count10 / 255.0F;
         float factor2 = (float)count11 / 255.0F;
         float factor3 = (float)count12 / 255.0F;
         tertiaryEntityAdapter.applyFloatByFloat(factor, factor2, factor3);
      }
   }

   public static int computeCount(IBlockAccess iBlockAccess, IBlockState blockState, BlockPos blockPos, BaseIBlockStateEntry baseIBlockStateEntry) {
      Block block = blockState.getBlock();
      Object entry = resolveTexturesManager(blockState);
      if (entry == null && blockState.getBlock().getMaterial() == Material.coreItemEntry1) {
         entry = postUpdateBroadcaster2;
      }

      return entry == null
         ? block.processAction(iBlockAccess, blockPos, 0)
         : (
            BuildManager.isActive60() && !((PostUpdateBroadcaster)entry).isActive()
               ? computeCount(blockState, iBlockAccess, blockPos, (PostUpdateBroadcaster)entry, baseIBlockStateEntry.getExtendedBlockPosAdapter())
               : ((PostUpdateBroadcaster)entry).computeCount(blockState, iBlockAccess, blockPos)
         );
   }

   private static int computeCount(Block block, IBlockAccess iBlockAccess, BlockPos blockPos, BaseIBlockStateEntry baseIBlockStateEntry) {
      TexturesManager texturesManager17 = texturesManager10;
      if (block == Blocks.block55 && texturesManager11 != null) {
         texturesManager17 = texturesManager11;
      }

      if (block == Blocks.block133 && texturesManager6 != null) {
         texturesManager17 = texturesManager6;
      }

      if (texturesManager17 == null) {
         return -1;
      } else {
         int countAsIndex = baseIBlockStateEntry.getCountAsIndex();
         return texturesManager17.computeCount(countAsIndex);
      }
   }

   private static int computeCount(IBlockState blockState) {
      if (texturesManager9 == null) {
         return -1;
      } else {
         int count9 = computeCount(blockState, 15);
         return texturesManager9.computeCount(count9);
      }
   }

   public static float[] resolveFloatArray(LocalOptionType localOptionType, float[] factorArray3) {
      return resolveFloatArray(localOptionType, factorArray, factorArray3);
   }

   private static int[] resolveIndexArray2(Properties properties, String text, String text2, String text3) {
      int[] countArray6 = new int[Potion.potionArray.length];
      Arrays.fill(countArray6, -1);
      int count9 = 0;

      for (Object text4Obj : properties.keySet()) {
         String text4 = (String)text4Obj;
         String property = properties.getProperty(text4);
         if (text4.startsWith(text2)) {
            int count10 = computeCountByText(text4);
            int count11 = computeCount(property);
            if (count10 >= 0 && count10 < countArray6.length && count11 >= 0) {
               countArray6[count10] = count11;
               count9++;
            } else {
               applyText2("Invalid color: " + text4 + " = " + property);
            }
         }
      }

      if (count9 <= 0) {
         return null;
      } else {
         applyText(text3 + " colors: " + count9);
         return countArray6;
      }
   }

   public static Vec3 resolveVec3(IBlockAccess iBlockAccess, double posX, double posY, double posZ, TexturesManager texturesManager17, Vec3Entry3 vec3Entry) {
      if (texturesManager17 == null) {
         return null;
      } else {
         int count9 = texturesManager17.computeCount(iBlockAccess, posX, posY, posZ, 3);
         int count10 = count9 >> 16 & 0xFF;
         int count11 = count9 >> 8 & 0xFF;
         int count12 = count9 & 0xFF;
         float factor = (float)count10 / 255.0F;
         float factor2 = (float)count11 / 255.0F;
         float factor3 = (float)count12 / 255.0F;
         return vec3Entry.resolveVec3((double)factor, (double)factor2, (double)factor3);
      }
   }

   public static TexturesManager resolveTexturesManager(String text, int count9, int count10) {
      try {
         if (!BuildManager.matchesResourceLocation3(new ResourceLocation(text))) {
            return null;
         } else {
            applyText("Colormap " + text);
            PropertiesAdapter propertiesAdapter = new PropertiesAdapter();
            String text2 = SplineCollector.resolveTextByText(text, ".png", ".properties");
            ResourceLocation location = new ResourceLocation(text2);
            if (BuildManager.matchesResourceLocation3(location)) {
               InputStream inputStream = BuildManager.resolveInputStream(location);
               propertiesAdapter.load(inputStream);
               inputStream.close();
               applyText("Colormap properties: " + text2);
            } else {
               propertiesAdapter.put("format", labelText);
               propertiesAdapter.put("source", text);
               text2 = text;
            }

            TexturesManager texturesManager17 = new TexturesManager(propertiesAdapter, text2, count9, count10, labelText);
            return !texturesManager17.matchesText(text2) ? null : texturesManager17;
         }
      } catch (Exception exception) {
         exception.printStackTrace();
         return null;
      }
   }

   private static TexturesManager[][] resolveTexturesManagerArray(List list) {
      TexturesManager[][] texturesManagerMatrix = new TexturesManager[list.size()][];

      for (int index = 0; index < list.size(); index++) {
         List list2 = (List)list.get(index);
         if (list2 != null) {
            TexturesManager[] texturesManagerArray3 = (TexturesManager[])list2.toArray(new TexturesManager[list2.size()]);
            texturesManagerMatrix[index] = texturesManagerArray3;
         }
      }

      return texturesManagerMatrix;
   }

   public static int computeCount(LocalIntArrayEntry localIntArrayEntry, IBlockState blockState, IBlockAccess iBlockAccess, BlockPos blockPos, BaseIBlockStateEntry baseIBlockStateEntry) {
      BlockPos blockPos2 = blockPos;
      Block block = blockState.getBlock();
      IBlockState iBlockState3 = baseIBlockStateEntry.getIBlockState();
      if (texturesManagerArray != null) {
         if (!localIntArrayEntry.isActive2()) {
            if (block == Blocks.blockGrass) {
               iBlockState3 = iBlockState;
            }

            if (block == Blocks.blockRedstoneWire) {
               return -1;
            }
         }

         if (block == Blocks.blockDoublePlant && baseIBlockStateEntry.getCountAsIndex() >= 8) {
            blockPos2 = blockPos.down();
            iBlockState3 = iBlockAccess.getBlockState(blockPos2);
         }

         TexturesManager texturesManager17 = resolveTexturesManager(iBlockState3);
         if (texturesManager17 != null) {
            if (BuildManager.isActive60() && !texturesManager17.isActive()) {
               return computeCount(blockState, iBlockAccess, blockPos2, texturesManager17, baseIBlockStateEntry.getExtendedBlockPosAdapter());
            }

            return texturesManager17.computeCount(iBlockAccess, blockPos2);
         }
      }

      if (!localIntArrayEntry.isActive2()) {
         return -1;
      } else if (block == Blocks.block33) {
         return computeCount(iBlockAccess, blockPos2);
      } else if (block == Blocks.blockRedstoneWire) {
         return computeCount(baseIBlockStateEntry.getIBlockState());
      } else if (block instanceof BlockMelonStem) {
         return computeCount(block, iBlockAccess, blockPos2, baseIBlockStateEntry);
      } else if (enabled) {
         return -1;
      } else {
         int countAsIndex = baseIBlockStateEntry.getCountAsIndex();
         PostUpdateBroadcaster postUpdateBroadcaster6;
         if (block == Blocks.blockGrass || block == Blocks.blockTallgrass || block == Blocks.blockDoublePlant) {
            postUpdateBroadcaster6 = postUpdateBroadcaster3;
         } else if (block == Blocks.blockDoublePlant) {
            postUpdateBroadcaster6 = postUpdateBroadcaster3;
            if (countAsIndex >= 8) {
               blockPos2 = blockPos2.down();
            }
         } else if (block == Blocks.decayableManager2) {
            switch (countAsIndex & 3) {
               case 0:
                  postUpdateBroadcaster6 = postUpdateBroadcaster5;
                  break;
               case 1:
                  postUpdateBroadcaster6 = postUpdateBroadcaster4;
                  break;
               case 2:
                  postUpdateBroadcaster6 = postUpdateBroadcaster;
                  break;
               default:
                  postUpdateBroadcaster6 = postUpdateBroadcaster5;
            }
         } else if (block == Blocks.decayableManager) {
            postUpdateBroadcaster6 = postUpdateBroadcaster5;
         } else {
            if (block != Blocks.block144) {
               return -1;
            }

            postUpdateBroadcaster6 = postUpdateBroadcaster5;
         }

         return BuildManager.isActive60() && !postUpdateBroadcaster6.isActive()
            ? computeCount(blockState, iBlockAccess, blockPos2, postUpdateBroadcaster6, baseIBlockStateEntry.getExtendedBlockPosAdapter())
            : postUpdateBroadcaster6.computeCount(iBlockState3, iBlockAccess, blockPos2);
      }
   }

   private static void applyIndexArray(int[] countArray6) {
      if (countArray6 != null) {
         MapColor[] mapColorArray = MapColor.mapColorArray;
         boolean flag = false;

         for (int index = 0; index < mapColorArray.length && index < countArray6.length; index++) {
            MapColor mapColor = mapColorArray[index];
            if (mapColor != null) {
               int count9 = countArray6[index];
               if (count9 >= 0 && mapColor.indexOffset != count9) {
                  mapColor.indexOffset = count9;
                  flag = true;
               }
            }
         }

         if (flag) {
            Minecraft.getMinecraft().getTextureManager().updateState();
         }
      }
   }

   private static Vec3 resolveVec2(Vec3 vec) {
      return vec3 == null ? vec : vec3;
   }

   private static Vec3 resolveVec3(Properties properties, String text) {
      int count9 = computeCount(properties, text);
      if (count9 < 0) {
         return null;
      } else {
         int count10 = count9 >> 16 & 0xFF;
         int count11 = count9 >> 8 & 0xFF;
         int count12 = count9 & 0xFF;
         float factor = (float)count10 / 255.0F;
         float factor2 = (float)count11 / 255.0F;
         float factor3 = (float)count12 / 255.0F;
         return new Vec3((double)factor, (double)factor2, (double)factor3);
      }
   }

   private static int[] resolveIndexArray3(Properties properties, String text, String text2, String text3) {
      int[] countArray6 = new int[32];
      Arrays.fill(countArray6, -1);
      int count9 = 0;

      for (Object text4Obj : properties.keySet()) {
         String text4 = (String)text4Obj;
         String property = properties.getProperty(text4);
         if (text4.startsWith(text2)) {
            int count10 = BuildManager.computeCount(SplineCollector.resolveText2(text4, text2), -1);
            int count11 = computeCount(property);
            if (count10 >= 0 && count10 < countArray6.length && count11 >= 0) {
               countArray6[count10] = count11;
               count9++;
            } else {
               applyText2("Invalid color: " + text4 + " = " + property);
            }
         }
      }

      if (count9 <= 0) {
         return null;
      } else {
         applyText(text3 + " colors: " + count9);
         return countArray6;
      }
   }

   private static int computeCount(IBlockState blockState, IBlockAccess iBlockAccess, BlockPos blockPos, PostUpdateBroadcaster postUpdateBroadcaster6, ExtendedBlockPosAdapter extendedBlockPosAdapter) {
      int count9 = 0;
      int count10 = 0;
      int count11 = 0;
      int coordX = blockPos.getX();
      int coordY = blockPos.getY();
      int coordZ = blockPos.getZ();
      ExtendedBlockPosAdapter extendedBlockPosAdapter2 = extendedBlockPosAdapter;

      for (int index = coordX - 1; index <= coordX + 1; index++) {
         for (int index2 = coordZ - 1; index2 <= coordZ + 1; index2++) {
            extendedBlockPosAdapter2.applyIndex(index, coordY, index2);
            int count12 = postUpdateBroadcaster6.computeCount(blockState, iBlockAccess, extendedBlockPosAdapter2);
            count9 += count12 >> 16 & 0xFF;
            count10 += count12 >> 8 & 0xFF;
            count11 += count12 & 0xFF;
         }
      }

      int count13 = count9 / 9;
      int count14 = count10 / 9;
      int count15 = count11 / 9;
      return count13 << 16 | count14 << 8 | count15;
   }

   private static void applyText2(String text) {
      BuildManager.applyText3("CustomColors: " + text);
   }

   private static int computeCount2(String text) {
      return text == null
         ? -1
         : (
            text.equals("air")
               ? MapColor.mapColor19.count
               : (
                  text.equals("grass")
                     ? MapColor.mapColor17.count
                     : (
                        text.equals("sand")
                           ? MapColor.mapColor20.count
                           : (
                              text.equals("cloth")
                                 ? MapColor.mapColor22.count
                                 : (
                                    text.equals("tnt")
                                       ? MapColor.mapColor8.count
                                       : (
                                          text.equals("ice")
                                             ? MapColor.mapColor32.count
                                             : (
                                                text.equals("iron")
                                                   ? MapColor.mapColor34.count
                                                   : (
                                                      text.equals("foliage")
                                                         ? MapColor.mapColor26.count
                                                         : (
                                                            text.equals("clay")
                                                               ? MapColor.mapColor28.count
                                                               : (
                                                                  text.equals("dirt")
                                                                     ? MapColor.mapColor4.count
                                                                     : (
                                                                        text.equals("stone")
                                                                           ? MapColor.mapColor10.count
                                                                           : (
                                                                              text.equals("water")
                                                                                 ? MapColor.mapColor9.count
                                                                                 : (
                                                                                    text.equals("wood")
                                                                                       ? MapColor.mapColor18.count
                                                                                       : (
                                                                                          text.equals("quartz")
                                                                                             ? MapColor.mapColor30.count
                                                                                             : (
                                                                                                text.equals("gold")
                                                                                                   ? MapColor.mapColor23.count
                                                                                                   : (
                                                                                                      text.equals("diamond")
                                                                                                         ? MapColor.mapColor.count
                                                                                                         : (
                                                                                                            text.equals("lapis")
                                                                                                               ? MapColor.mapColor15.count
                                                                                                               : (
                                                                                                                  text.equals("emerald")
                                                                                                                     ? MapColor.mapColor27.count
                                                                                                                     : (
                                                                                                                        text.equals("podzol")
                                                                                                                           ? MapColor.mapColor7.count
                                                                                                                           : (
                                                                                                                              text.equals("netherrack")
                                                                                                                                 ? MapColor.mapColor33.count
                                                                                                                                 : (
                                                                                                                                    text.equals("snow")
                                                                                                                                          || text.equals(
                                                                                                                                             "white"
                                                                                                                                          )
                                                                                                                                       ? MapColor.mapColor6.count
                                                                                                                                       : (
                                                                                                                                          text.equals("adobe")
                                                                                                                                                || text.equals(
                                                                                                                                                   "orange"
                                                                                                                                                )
                                                                                                                                             ? MapColor.mapColor36
                                                                                                                                                .count
                                                                                                                                             : (
                                                                                                                                                text.equals(
                                                                                                                                                      "magenta"
                                                                                                                                                   )
                                                                                                                                                   ? MapColor.mapColor12
                                                                                                                                                      .count
                                                                                                                                                   : (
                                                                                                                                                      !text.equals(
                                                                                                                                                               "light_blue"
                                                                                                                                                            )
                                                                                                                                                            && !text.equals(
                                                                                                                                                               "lightBlue"
                                                                                                                                                            )
                                                                                                                                                         ? (
                                                                                                                                                            text.equals(
                                                                                                                                                                  "yellow"
                                                                                                                                                               )
                                                                                                                                                               ? MapColor.mapColor3
                                                                                                                                                                  .count
                                                                                                                                                               : (
                                                                                                                                                                  text.equals(
                                                                                                                                                                        "lime"
                                                                                                                                                                     )
                                                                                                                                                                     ? MapColor.mapColor14
                                                                                                                                                                        .count
                                                                                                                                                                     : (
                                                                                                                                                                        text.equals(
                                                                                                                                                                              "pink"
                                                                                                                                                                           )
                                                                                                                                                                           ? MapColor.mapColor2
                                                                                                                                                                              .count
                                                                                                                                                                           : (
                                                                                                                                                                              text.equals(
                                                                                                                                                                                    "gray"
                                                                                                                                                                                 )
                                                                                                                                                                                 ? MapColor.mapColor16
                                                                                                                                                                                    .count
                                                                                                                                                                                 : (
                                                                                                                                                                                    text.equals(
                                                                                                                                                                                          "silver"
                                                                                                                                                                                       )
                                                                                                                                                                                       ? MapColor.mapColor11
                                                                                                                                                                                          .count
                                                                                                                                                                                       : (
                                                                                                                                                                                          text.equals(
                                                                                                                                                                                                "cyan"
                                                                                                                                                                                             )
                                                                                                                                                                                             ? MapColor.mapColor24
                                                                                                                                                                                                .count
                                                                                                                                                                                             : (
                                                                                                                                                                                                text.equals(
                                                                                                                                                                                                      "purple"
                                                                                                                                                                                                   )
                                                                                                                                                                                                   ? MapColor.mapColor35
                                                                                                                                                                                                      .count
                                                                                                                                                                                                   : (
                                                                                                                                                                                                      text.equals(
                                                                                                                                                                                                            "blue"
                                                                                                                                                                                                         )
                                                                                                                                                                                                         ? MapColor.mapColor21
                                                                                                                                                                                                            .count
                                                                                                                                                                                                         : (
                                                                                                                                                                                                            text.equals(
                                                                                                                                                                                                                  "brown"
                                                                                                                                                                                                               )
                                                                                                                                                                                                               ? MapColor.mapColor13
                                                                                                                                                                                                                  .count
                                                                                                                                                                                                               : (
                                                                                                                                                                                                                  text.equals(
                                                                                                                                                                                                                        "green"
                                                                                                                                                                                                                     )
                                                                                                                                                                                                                     ? MapColor.mapColor25
                                                                                                                                                                                                                        .count
                                                                                                                                                                                                                     : (
                                                                                                                                                                                                                        text.equals(
                                                                                                                                                                                                                              "red"
                                                                                                                                                                                                                           )
                                                                                                                                                                                                                           ? MapColor.mapColor5
                                                                                                                                                                                                                              .count
                                                                                                                                                                                                                           : (
                                                                                                                                                                                                                              text.equals(
                                                                                                                                                                                                                                    "black"
                                                                                                                                                                                                                                 )
                                                                                                                                                                                                                                 ? MapColor.mapColor29
                                                                                                                                                                                                                                    .count
                                                                                                                                                                                                                                 : -1
                                                                                                                                                                                                                           )
                                                                                                                                                                                                                     )
                                                                                                                                                                                                               )
                                                                                                                                                                                                         )
                                                                                                                                                                                                   )
                                                                                                                                                                                             )
                                                                                                                                                                                       )
                                                                                                                                                                                 )
                                                                                                                                                                           )
                                                                                                                                                                     )
                                                                                                                                                               )
                                                                                                                                                         )
                                                                                                                                                         : MapColor.mapColor31
                                                                                                                                                            .count
                                                                                                                                                   )
                                                                                                                                             )
                                                                                                                                       )
                                                                                                                                 )
                                                                                                                           )
                                                                                                                     )
                                                                                                               )
                                                                                                         )
                                                                                                   )
                                                                                             )
                                                                                       )
                                                                                 )
                                                                           )
                                                                     )
                                                               )
                                                         )
                                                   )
                                             )
                                       )
                                 )
                           )
                     )
               )
         );
   }

   public static int computeCount3(int count9) {
      return count2 < 0 ? count9 : count2;
   }

   public static int computeCount2(int count9) {
      return count < 0 ? count9 : count;
   }

   public static Vec3 resolveVec4(Vec3 vec, World world, Entity entity, float factor) {
      Vec3 vec5 = vec;
      switch (world.secondaryFloatArrayEntry.getCountAsIndex()) {
         case 0:
            Minecraft mc = Minecraft.getMinecraft();
            vec5 = resolveVec3(vec, mc.theWorld, entity.posX, entity.posY + 1.0, entity.posZ);
            break;
         case 1:
            vec5 = resolveVec2(vec);
      }

      return vec5;
   }

   public static Vec3 resolveVec3(IBlockAccess iBlockAccess, double posX, double posY, double posZ) {
      return resolveVec3(iBlockAccess, posX, posY, posZ, texturesManager5, vec3Entry4);
   }

   private static Vec3 resolveVec3(Vec3 vec) {
      return vec2 == null ? vec : vec2;
   }

   public static void applyTertiaryEntityAdapter(
      TertiaryEntityAdapter tertiaryEntityAdapter, IBlockAccess iBlockAccess, double posX, double posY, double posZ, BaseIBlockStateEntry baseIBlockStateEntry
   ) {
      if (texturesManager3 != null || texturesManagerArray != null || count5 >= 0) {
         BlockPos blockPos = new BlockPos(posX, posY, posZ);
         baseIBlockStateEntry.applyIBlockState(iBlockState2, blockPos);
         int count9 = computeCount(iBlockAccess, iBlockState2, blockPos, baseIBlockStateEntry);
         int count10 = count9 >> 16 & 0xFF;
         int count11 = count9 >> 8 & 0xFF;
         int count12 = count9 & 0xFF;
         float factor = (float)count10 / 255.0F;
         float factor2 = (float)count11 / 255.0F;
         float factor3 = (float)count12 / 255.0F;
         if (count5 >= 0) {
            int count13 = count5 >> 16 & 0xFF;
            int count14 = count5 >> 8 & 0xFF;
            int count15 = count5 & 0xFF;
            factor *= (float)count13 / 255.0F;
            factor2 *= (float)count14 / 255.0F;
            factor3 *= (float)count15 / 255.0F;
         }

         tertiaryEntityAdapter.applyFloatByFloat(factor, factor2, factor3);
      }
   }

   private static int computeCount(IBlockState blockState, int count9) {
      if (!(blockState.getBlock() instanceof BlockRedstoneWire)) {
         return count9;
      } else {
         Comparable comparable = blockState.getComparable(BlockRedstoneWire.auxiliaryImmutableSetEntry);
         return !(comparable instanceof Integer) ? count9 : (Integer)comparable;
      }
   }

   public static int computeCountByIndex(int count9, int count10) {
      if (countArray2 == null) {
         return count10;
      } else if (count9 >= 0 && count9 < countArray2.length) {
         int count11 = countArray2[count9];
         return count11 < 0 ? count10 : count11;
      } else {
         return count10;
      }
   }

   private static int computeCountByText(String text) {
      if (text.equals("potion.water")) {
         return 0;
      } else {
         Potion[] potionArray = Potion.potionArray;

         for (int index = 0; index < potionArray.length; index++) {
            Potion potion = potionArray[index];
            if (potion != null && potion.getText().equals(text)) {
               return potion.getIndexOffset3();
            }
         }

         return -1;
      }
   }

   private static int computeCount(Properties properties, String[] textArray) {
      for (int index = 0; index < textArray.length; index++) {
         String text = textArray[index];
         int count9 = computeCount(properties, text);
         if (count9 >= 0) {
            return count9;
         }
      }

      return -1;
   }

   private static int computeCount(IBlockAccess iBlockAccess, BlockPos blockPos) {
      return count3 < 0 ? Blocks.block33.processAction2(iBlockAccess, blockPos) : count3;
   }

   public static TexturesManager getTexturesManager3() {
      return texturesManager3;
   }

   private static int computeCount(Properties properties, String text) {
      String property = properties.getProperty(text);
      if (property == null) {
         return -1;
      } else {
         String text2 = property.trim();
         int count9 = computeCount(text2);
         if (count9 < 0) {
            applyText2("Invalid color: " + text + " = " + text2);
            return count9;
         } else {
            applyText(text + " = " + text2);
            return count9;
         }
      }
   }

   public static void applyTertiaryEntityAdapterByTertiaryEntityAd(TertiaryEntityAdapter tertiaryEntityAdapter) {
      if (count7 >= 0) {
         int count9 = count7;
         int count10 = count9 >> 16 & 0xFF;
         int count11 = count9 >> 8 & 0xFF;
         int count12 = count9 & 0xFF;
         float factor = (float)count10 / 255.0F;
         float factor2 = (float)count11 / 255.0F;
         float factor3 = (float)count12 / 255.0F;
         tertiaryEntityAdapter.applyFloatByFloat(factor, factor2, factor3);
      }
   }

   public static int computeCount(ItemStack stack, int count9, int count10) {
      if (stack == null) {
         return count10;
      } else {
         Item item = stack.getItem();
         return item == null ? count10 : (item instanceof ItemSpawnEgg ? computeCount((ItemSpawnEgg)item, stack, count9, count10) : count10);
      }
   }

   private static int computeCount(String text, int count9) {
      try {
         InputStream inputStream = BuildManager.resolveInputStream(new ResourceLocation(text));
         if (inputStream == null) {
            return count9;
         } else {
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            inputStream.close();
            return bufferedImage == null ? count9 : bufferedImage.getHeight();
         }
      } catch (IOException ioException) {
         return count9;
      }
   }

   public static boolean matchesWorldByWorld(World world, float factor, int[] countArray6, boolean flag, float factor2) {
      if (world == null) {
         return false;
      } else if (vectorWatcherArray == null) {
         return false;
      } else {
         int count9 = world.secondaryFloatArrayEntry.getCountAsIndex() - count8;
         if (count9 >= 0 && count9 < vectorWatcherArray.length) {
            VectorWatcher vectorWatcher = vectorWatcherArray[count9];
            return vectorWatcher == null ? false : vectorWatcher.matchesWorldByWorld(world, factor, countArray6, flag, factor2);
         } else {
            return false;
         }
      }
   }

   public static float[] resolveFloatArrayByLocalOptionType(LocalOptionType localOptionType, float[] factorArray3) {
      return resolveFloatArray(localOptionType, factorArray2, factorArray3);
   }

   private static int computeCount(String text) {
      if (text == null) {
         return -1;
      } else {
         String text2 = text.trim();

         try {
            return Integer.parseInt(text2, 16) & 16777215;
         } catch (NumberFormatException numberFormatException) {
            return -1;
         }
      }
   }

   private static TexturesManager resolveTexturesManager(String text, String[] textArray, int count9, int count10) {
      for (int index = 0; index < textArray.length; index++) {
         String text2 = textArray[index];
         TexturesManager texturesManager17 = resolveTexturesManager(text + text2, count9, count10);
         if (texturesManager17 != null) {
            return texturesManager17;
         }
      }

      return null;
   }

   public static void updateState2() {
      labelText = "vanilla";
      texturesManager3 = null;
      texturesManager = null;
      texturesManager7 = null;
      texturesManager15 = null;
      texturesManager14 = null;
      texturesManager12 = null;
      texturesManager13 = null;
      texturesManager8 = null;
      texturesManager5 = null;
      texturesManager9 = null;
      texturesManager4 = null;
      count6 = -1;
      texturesManager16 = null;
      texturesManager10 = null;
      texturesManager2 = null;
      vectorWatcherArray = null;
      count5 = -1;
      count7 = -1;
      count3 = -1;
      count = -1;
      count4 = -1;
      count2 = -1;
      vec2 = null;
      vec4 = null;
      vec3 = null;
      texturesManagerArray2 = null;
      texturesManagerArray = (TexturesManager[][])null;
      enabled = true;
      countArray = null;
      countArray3 = null;
      factorArray2 = (float[][])null;
      factorArray = (float[][])null;
      countArray2 = null;
      applyIndexArray(countArray5);
      countArray4 = null;
      labelText = resolveText("mcpatcher/color.properties", "palette.format", TexturesManager.stringArray, "vanilla");
      String text = "mcpatcher/colormap/";
      String[] textArray = new String[]{"water.png", "watercolorX.png"};
      texturesManager3 = resolveTexturesManager(text, textArray, 256, 256);
      updateState();
      if (BuildManager.isActive33()) {
         String[] textArray2 = new String[]{"pine.png", "pinecolor.png"};
         texturesManager7 = resolveTexturesManager(text, textArray2, 256, 256);
         String[] textArray3 = new String[]{"birch.png", "birchcolor.png"};
         texturesManager = resolveTexturesManager(text, textArray3, 256, 256);
         String[] textArray4 = new String[]{"swampgrass.png", "swampgrasscolor.png"};
         texturesManager15 = resolveTexturesManager(text, textArray4, 256, 256);
         String[] textArray5 = new String[]{"swampfoliage.png", "swampfoliagecolor.png"};
         texturesManager14 = resolveTexturesManager(text, textArray5, 256, 256);
         String[] textArray6 = new String[]{"sky0.png", "skycolor0.png"};
         texturesManager12 = resolveTexturesManager(text, textArray6, 256, 256);
         String[] textArray7 = new String[]{"fog0.png", "fogcolor0.png"};
         texturesManager13 = resolveTexturesManager(text, textArray7, 256, 256);
         String[] textArray8 = new String[]{"underwater.png", "underwatercolor.png"};
         texturesManager8 = resolveTexturesManager(text, textArray8, 256, 256);
         String[] textArray9 = new String[]{"underlava.png", "underlavacolor.png"};
         texturesManager5 = resolveTexturesManager(text, textArray9, 256, 256);
         String[] textArray10 = new String[]{"redstone.png", "redstonecolor.png"};
         texturesManager9 = resolveTexturesManager(text, textArray10, 16, 1);
         texturesManager4 = resolveTexturesManager(text + "xporb.png", -1, -1);
         texturesManager16 = resolveTexturesManager(text + "durability.png", -1, -1);
         String[] textArray11 = new String[]{"stem.png", "stemcolor.png"};
         texturesManager10 = resolveTexturesManager(text, textArray11, 8, 1);
         texturesManager11 = resolveTexturesManager(text + "pumpkinstem.png", 8, 1);
         texturesManager6 = resolveTexturesManager(text + "melonstem.png", 8, 1);
         String[] textArray12 = new String[]{"myceliumparticle.png", "myceliumparticlecolor.png"};
         texturesManager2 = resolveTexturesManager(text, textArray12, -1, -1);
         org.apache.commons.lang3.tuple.Pair pair = getPair();
         vectorWatcherArray = (VectorWatcher[])pair.getLeft();
         count8 = (Integer)pair.getRight();
         applyTextByText("mcpatcher/color.properties");
         texturesManagerArray = resolveTexturesManagerArray(new String[]{text + "custom/", text + "blocks/"}, texturesManagerArray2, 256, 256);
         updateState();
      }
   }

   private static int[] resolveIndexArrayByProperties(Properties properties, String text, String text2, String text3) {
      int[] countArray6 = new int[MapColor.mapColorArray.length];
      Arrays.fill(countArray6, -1);
      int count9 = 0;

      for (Object text4Obj : properties.keySet()) {
         String text4 = (String)text4Obj;
         String property = properties.getProperty(text4);
         if (text4.startsWith(text2)) {
            int count10 = computeCount2(SplineCollector.resolveText2(text4, text2));
            int count11 = computeCount(property);
            if (count10 >= 0 && count10 < countArray6.length && count11 >= 0) {
               countArray6[count10] = count11;
               count9++;
            } else {
               applyText2("Invalid color: " + text4 + " = " + property);
            }
         }
      }

      if (count9 <= 0) {
         return null;
      } else {
         applyText(text3 + " colors: " + count9);
         return countArray6;
      }
   }

   public static TexturesManager getTexturesManager() {
      return texturesManager;
   }

   private static float[][] resolveFloatArray(Properties properties, String text, String text2, String text3) {
      LocalOptionType[] values = LocalOptionType.values();
      HashMap map = new HashMap();

      for (int index = 0; index < values.length; index++) {
         LocalOptionType localOptionType = values[index];
         map.put(localOptionType.getText(), localOptionType);
      }

      float[][] factorMatrix = new float[values.length][];
      int count9 = 0;

      for (Object text4Obj : properties.keySet()) {
         String text4 = (String)text4Obj;
         String property = properties.getProperty(text4);
         if (text4.startsWith(text2)) {
            String text5 = SplineCollector.resolveText2(text4, text2);
            if (text5.equals("lightBlue")) {
               text5 = "light_blue";
            }

            LocalOptionType localOptionType2 = (LocalOptionType)map.get(text5);
            int count10 = computeCount(property);
            if (localOptionType2 != null && count10 >= 0) {
               float[] factorArray3 = new float[]{(float)(count10 >> 16 & 0xFF) / 255.0F, (float)(count10 >> 8 & 0xFF) / 255.0F, (float)(count10 & 0xFF) / 255.0F};
               factorMatrix[localOptionType2.ordinal()] = factorArray3;
               count9++;
            } else {
               applyText2("Invalid color: " + text4 + " = " + property);
            }
         }
      }

      if (count9 <= 0) {
         return (float[][])null;
      } else {
         applyText(text3 + " colors: " + count9);
         return factorMatrix;
      }
   }
}
