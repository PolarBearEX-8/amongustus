package client.util;

import client.event.PreUpdateProcessor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGoldenBoots;
import net.minecraft.item.ItemManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class InvalidItemIDManager {
   public static ItemManager[][] itemManagerArray = null;
   public static ItemManager[][] itemManagerArray2 = null;
   public static Map map = null;
   public static PassiveListEntry passiveListEntry = new PassiveListEntry();
   public static boolean enabled = true;
   public static boolean enabled2 = false;
   public static final int count = 16384;
   public static final int count3 = 63;
   public static final int count2 = 64;
   public static final String labelText5 = "texture.potion_overlay";
   public static final String labelText6 = "texture.potion_bottle_splash";
   public static final String labelText = "texture.potion_bottle_drinkable";
   public static final String labelText9 = "items/potion_overlay";
   public static final String labelText2 = "items/potion_bottle_splash";
   public static final String labelText7 = "items/potion_bottle_drinkable";
   public static final int[][] countArray = new int[0][];
   public static final String labelText3 = "normal";
   public static final String labelText4 = "splash";
   public static final String labelText8 = "linger";

   private static void applyItemManager(ItemManager itemManager, List list) {
      if (itemManager.countArray != null) {
         for (int index = 0; index < itemManager.countArray.length; index++) {
            int count4 = itemManager.countArray[index];
            if (count4 <= 0) {
               BuildManager.applyText3("Invalid item ID: " + count4);
            } else {
               applyItemManager(itemManager, list, count4);
            }
         }
      }
   }

   public static void updateState() {
      for (ItemManager itemManager : (Iterable<ItemManager>)(Iterable<?>)getList()) {
         if (itemManager.count10 == 1) {
            ListenerAdapter27 listenerAdapter = Minecraft.getMinecraft().getListenerAdapter27();
            itemManager.applyListenerAdapter27(listenerAdapter, passiveListEntry);
            itemManager.updateState();
         }
      }
   }

   private static int computeCount(String text) {
      String text2 = "potion." + text;
      Potion[] potionArray = Potion.potionArray;

      for (int index = 0; index < potionArray.length; index++) {
         Potion potion = potionArray[index];
         if (potion != null) {
            String text3 = potion.getText();
            if (text2.equals(text3)) {
               return potion.getIndexOffset3();
            }
         }
      }

      return -1;
   }

   private static Map resolveMap(PreUpdateProcessor preUpdateProcessor) {
      HashMap map2 = new HashMap();
      map2.putAll(resolveMap(preUpdateProcessor, "normal", Item.getIndex(Items.potion)));
      map2.putAll(resolveMap(preUpdateProcessor, "splash", Item.getIndex(Items.potion)));
      map2.putAll(resolveMap(preUpdateProcessor, "linger", Item.getIndex(Items.potion)));
      return map2;
   }

   public static boolean matchesEntityLivingBaseByEntityLivingBase(
      EntityLivingBase livingEntity, ItemStack stack, PassiveFloatEntry passiveFloatEntry, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ
   ) {
      if (itemManagerArray2 == null) {
         return false;
      } else if (BuildManager.canExecute() && PreEndHandManager.enabled12) {
         return false;
      } else if (stack == null) {
         return false;
      } else {
         int[][] countMatrix = resolveIndexArray(stack);
         if (countMatrix.length <= 0) {
            return false;
         } else {
            HashSet set = null;
            boolean flag = false;
            TextureManager textureManagerAsTextureManager = BuildManager.getTextureManagerAsTextureManager();

            for (int index = 0; index < countMatrix.length; index++) {
               int count4 = countMatrix[index][0];
               if (count4 >= 0 && count4 < itemManagerArray2.length) {
                  ItemManager[] itemManagerArray3 = itemManagerArray2[count4];
                  if (itemManagerArray3 != null) {
                     for (int index2 = 0; index2 < itemManagerArray3.length; index2++) {
                        ItemManager itemManager = itemManagerArray3[index2];
                        if (set == null) {
                           set = new HashSet();
                        }

                        if (set.add(count4) && matchesItemManager(itemManager, stack, countMatrix) && itemManager.resourceLocation != null) {
                           textureManagerAsTextureManager.applyResourceLocation3(itemManager.resourceLocation);
                           float factor = itemManager.computeFactorByTextureManager(textureManagerAsTextureManager);
                           if (!flag) {
                              flag = true;
                              if (BuildManager.canExecute()) {
                                 RotationBridge.updateState9();
                              }

                              GlStateManager.disableBlend();
                              GlStateManager.glDepthFunc(514);
                              GlStateManager.depthMask(false);
                           }

                           FovSorter.applyIndex(itemManager.count13, 1.0F);
                           GlStateManager.applyState3();
                           GlStateManager.matrixMode(5890);
                           GlStateManager.loadIdentity();
                           GlStateManager.rotate(itemManager.factor3, 0.0F, 0.0F, 1.0F);
                           float factor2 = factor / 8.0F;
                           GlStateManager.translate3f3(factor2, factor2 / 2.0F, factor2);
                           float factor3 = itemManager.factor2 * (float)(Minecraft.getTimestamp() % 3000L) / 3000.0F / 8.0F;
                           GlStateManager.translate(0.0F, factor3, 0.0F);
                           GlStateManager.matrixMode(5888);
                           passiveFloatEntry.applyEntity(livingEntity, posX, posX2, posX4, posX5, posY, posZ, true);
                        }
                     }
                  }
               }
            }

            if (flag) {
               GlStateManager.applyState14();
               GlStateManager.disableBlend();
               GlStateManager.glStateAction3(770, 771);
               GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
               GlStateManager.matrixMode(5890);
               GlStateManager.loadIdentity();
               GlStateManager.matrixMode(5888);
               GlStateManager.applyState13();
               GlStateManager.depthMask(true);
               GlStateManager.glDepthFunc(515);
               GlStateManager.enableBlend();
               if (BuildManager.canExecute()) {
                  RotationBridge.updateState();
               }
            }

            return flag;
         }
      }
   }

   private static void applyPreUpdateProcessor(PreUpdateProcessor preUpdateProcessor) {
      String[] textArray = AssetsMinecraftManager.resolveTextArray(preUpdateProcessor, "mcpatcher/cit/", ".properties", (String[])null);
      Map map2 = resolveMap(preUpdateProcessor);
      if (map2.size() > 0) {
         Set keys = map2.keySet();
         String[] textArray2 = (String[])keys.toArray(new String[keys.size()]);
         textArray = (String[])BuildManager.resolveTargetArray(textArray, textArray2);
      }

      Arrays.sort(textArray);
      List list = resolveList(itemManagerArray);
      List list2 = resolveList(itemManagerArray2);

      for (int index = 0; index < textArray.length; index++) {
         String text = textArray[index];
         BuildManager.applyText4("CustomItems: " + text);

         try {
            ItemManager itemManager = null;
            if (map2.containsKey(text)) {
               itemManager = (ItemManager)map2.get(text);
            }

            if (itemManager == null) {
               ResourceLocation location = new ResourceLocation(text);
               InputStream inputStream = preUpdateProcessor.resolveInputStream(location);
               if (inputStream == null) {
                  BuildManager.applyText3("CustomItems file not found: " + text);
                  continue;
               }

               PropertiesAdapter propertiesAdapter = new PropertiesAdapter();
               propertiesAdapter.load(inputStream);
               inputStream.close();
               itemManager = new ItemManager(propertiesAdapter, text);
            }

            if (itemManager.matchesText(text)) {
               applyItemManager(itemManager, list);
               applyItemManagerByItemManager(itemManager, list2);
            }
         } catch (FileNotFoundException fileNotFoundException) {
            BuildManager.applyText3("CustomItems file not found: " + text);
         } catch (Exception exception) {
            exception.printStackTrace();
         }
      }

      itemManagerArray = resolveItemManagerArray(list);
      itemManagerArray2 = resolveItemManagerArray(list2);
      Comparator comparator = getComparator();

      for (int index2 = 0; index2 < itemManagerArray.length; index2++) {
         ItemManager[] itemManagerArray3 = itemManagerArray[index2];
         if (itemManagerArray3 != null) {
            Arrays.sort(itemManagerArray3, comparator);
         }
      }

      for (int index3 = 0; index3 < itemManagerArray2.length; index3++) {
         ItemManager[] itemManagerArray4 = itemManagerArray2[index3];
         if (itemManagerArray4 != null) {
            Arrays.sort(itemManagerArray4, comparator);
         }
      }
   }

   private static int[][] resolveIndexArray(ItemStack stack) {
      NBTTagList nBTTagList = stack.getItem() == Items.enchantedBook ? Items.enchantedBook.resolveNBTTagList(stack) : stack.getExtendedLoggerEntry();
      if (nBTTagList != null && nBTTagList.getInt() > 0) {
         int[][] countMatrix = new int[nBTTagList.getInt()][2];

         for (int index = 0; index < nBTTagList.getInt(); index++) {
            NBTTagCompound nBTTagCompound = nBTTagList.getNBTTagCompound(index);
            short shortValue = nBTTagCompound.processValue("id");
            short shortValue2 = nBTTagCompound.processValue("lvl");
            countMatrix[index][0] = shortValue;
            countMatrix[index][1] = shortValue2;
         }

         return countMatrix;
      } else {
         return countArray;
      }
   }

   private static ItemManager resolveItemManager(ItemStack stack, int count4) {
      if (itemManagerArray == null) {
         return null;
      } else if (stack == null) {
         return null;
      } else {
         int index = Item.getIndex(stack.getItem());
         if (index >= 0 && index < itemManagerArray.length) {
            ItemManager[] itemManagerArray3 = itemManagerArray[index];
            if (itemManagerArray3 != null) {
               for (int index2 = 0; index2 < itemManagerArray3.length; index2++) {
                  ItemManager itemManager = itemManagerArray3[index2];
                  if (itemManager.count10 == count4 && matchesItemManager(itemManager, stack, (int[][])null)) {
                     return itemManager;
                  }
               }
            }
         }

         return null;
      }
   }

   private static void applyItemManager(ItemManager itemManager, List list, int count4) {
      while (count4 >= list.size()) {
         list.add(null);
      }

      List entry = (List)list.get(count4);
      if (entry == null) {
         entry = new ArrayList();
         list.set(count4, entry);
      }

      entry.add(itemManager);
   }

   public static boolean isEnabled() {
      return enabled;
   }

   private static ResourceLocation resolveResourceLocationByItemStack(ItemStack stack, int count4, String text) {
      ItemManager itemManager = resolveItemManager(stack, 3);
      if (itemManager == null) {
         return null;
      } else if (itemManager.map3 == null) {
         return itemManager.resourceLocation;
      } else {
         Item item = stack.getItem();
         if (!(item instanceof ItemGoldenBoots)) {
            return null;
         } else {
            String labelText10 = ((ItemGoldenBoots)item).getCrafterLeatherType().getLabelText();
            StringBuffer builder = new StringBuffer();
            builder.append("texture.");
            builder.append(labelText10);
            builder.append("_layer_");
            builder.append(count4);
            if (text != null) {
               builder.append("_");
               builder.append(text);
            }

            String text2 = builder.toString();
            ResourceLocation location = (ResourceLocation)itemManager.map3.get(text2);
            return location == null ? itemManager.resourceLocation : location;
         }
      }
   }

   public static boolean matchesRenderItem(RenderItem renderItem, ItemStack stack, IBakedModel iBakedModel) {
      if (itemManagerArray2 == null) {
         return false;
      } else if (stack == null) {
         return false;
      } else {
         int[][] countMatrix = resolveIndexArray(stack);
         if (countMatrix.length <= 0) {
            return false;
         } else {
            HashSet set = null;
            boolean flag = false;
            TextureManager textureManagerAsTextureManager = BuildManager.getTextureManagerAsTextureManager();

            for (int index = 0; index < countMatrix.length; index++) {
               int count4 = countMatrix[index][0];
               if (count4 >= 0 && count4 < itemManagerArray2.length) {
                  ItemManager[] itemManagerArray3 = itemManagerArray2[count4];
                  if (itemManagerArray3 != null) {
                     for (int index2 = 0; index2 < itemManagerArray3.length; index2++) {
                        ItemManager itemManager = itemManagerArray3[index2];
                        if (set == null) {
                           set = new HashSet();
                        }

                        if (set.add(count4) && matchesItemManager(itemManager, stack, countMatrix) && itemManager.resourceLocation != null) {
                           textureManagerAsTextureManager.applyResourceLocation3(itemManager.resourceLocation);
                           float factor = itemManager.computeFactorByTextureManager(textureManagerAsTextureManager);
                           if (!flag) {
                              flag = true;
                              GlStateManager.depthMask(false);
                              GlStateManager.glDepthFunc(514);
                              GlStateManager.applyState3();
                              GlStateManager.matrixMode(5890);
                           }

                           FovSorter.applyIndex(itemManager.count13, 1.0F);
                           GlStateManager.pushMatrix();
                           GlStateManager.translate3f3(factor / 2.0F, factor / 2.0F, factor / 2.0F);
                           GlStateManager.translate(itemManager.factor2 * (float)(Minecraft.getTimestamp() % 3000L) / 3000.0F / 8.0F, 0.0F, 0.0F);
                           GlStateManager.rotate(itemManager.factor3, 0.0F, 0.0F, 1.0F);
                           renderItem.applyIBakedModel(iBakedModel, -1);
                           GlStateManager.popMatrix();
                        }
                     }
                  }
               }
            }

            if (flag) {
               GlStateManager.applyState14();
               GlStateManager.disableBlend();
               GlStateManager.glStateAction3(770, 771);
               GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
               GlStateManager.matrixMode(5888);
               GlStateManager.applyState13();
               GlStateManager.glDepthFunc(515);
               GlStateManager.depthMask(true);
               textureManagerAsTextureManager.applyResourceLocation3(ListenerAdapter27.resourceLocation);
            }

            return flag;
         }
      }
   }

   private static Map getMap() {
      if (map == null) {
         map = new LinkedHashMap();
         map.put("water", resolveIndexArray(0, 0));
         map.put("awkward", resolveIndexArray(0, 1));
         map.put("thick", resolveIndexArray(0, 2));
         map.put("potent", resolveIndexArray(0, 3));
         map.put("regeneration", resolveIndexArray(1));
         map.put("movespeed", resolveIndexArray(2));
         map.put("fireresistance", resolveIndexArray(3));
         map.put("poison", resolveIndexArray(4));
         map.put("heal", resolveIndexArray(5));
         map.put("nightvision", resolveIndexArray(6));
         map.put("clear", resolveIndexArray(7, 0));
         map.put("bungling", resolveIndexArray(7, 1));
         map.put("charming", resolveIndexArray(7, 2));
         map.put("rank", resolveIndexArray(7, 3));
         map.put("weakness", resolveIndexArray(8));
         map.put("damageboost", resolveIndexArray(9));
         map.put("moveslowdown", resolveIndexArray(10));
         map.put("leaping", resolveIndexArray(11));
         map.put("harm", resolveIndexArray(12));
         map.put("waterbreathing", resolveIndexArray(13));
         map.put("invisibility", resolveIndexArray(14));
         map.put("thin", resolveIndexArray(15, 0));
         map.put("debonair", resolveIndexArray(15, 1));
         map.put("sparkling", resolveIndexArray(15, 2));
         map.put("stinky", resolveIndexArray(15, 3));
         map.put("mundane", resolveIndexArray(0, 4));
         map.put("speed", map.get("movespeed"));
         map.put("fire_resistance", map.get("fireresistance"));
         map.put("instant_health", map.get("heal"));
         map.put("night_vision", map.get("nightvision"));
         map.put("strength", map.get("damageboost"));
         map.put("slowness", map.get("moveslowdown"));
         map.put("instant_damage", map.get("harm"));
         map.put("water_breathing", map.get("waterbreathing"));
      }

      return map;
   }

   private static void applyText(String text) {
      try {
         InputStream inputStream = BuildManager.resolveInputStream(new ResourceLocation(text));
         if (inputStream == null) {
            return;
         }

         BuildManager.applyText4("CustomItems: Loading " + text);
         PropertiesAdapter propertiesAdapter = new PropertiesAdapter();
         propertiesAdapter.load(inputStream);
         inputStream.close();
         enabled = BuildManager.matchesText(propertiesAdapter.getProperty("useGlint"), true);
      } catch (FileNotFoundException fileNotFoundException) {
         return;
      } catch (IOException ioException) {
         ioException.printStackTrace();
      }
   }

   private static int[] resolveIndexArray(int count4, int count5) {
      return new int[]{count4 + count5 * 16};
   }

   private static Map resolveMap(PreUpdateProcessor preUpdateProcessor, String text, int count4) {
      HashMap map2 = new HashMap();
      String text2 = text + "/";
      String[] textArray = new String[]{"mcpatcher/cit/potion/" + text2, "mcpatcher/cit/Potion/" + text2};
      String[] textArray2 = new String[]{".png"};
      String[] textArray3 = AssetsMinecraftManager.resolveTextArray(preUpdateProcessor, textArray, textArray2);

      for (int index = 0; index < textArray3.length; index++) {
         String text3 = textArray3[index];
         Properties properties = resolveProperties(SplineCollector.resolveText(text3, textArray, textArray2), text, count4, text3);
         if (properties != null) {
            String text4 = SplineCollector.resolveTextByText(text3, textArray2) + ".properties";
            ItemManager itemManager = new ItemManager(properties, text4);
            map2.put(text4, itemManager);
         }
      }

      return map2;
   }

   private static boolean matchesItemManager(ItemManager itemManager, ItemStack stack, int[][] countMatrix) {
      Item item = stack.getItem();
      if (itemManager.targetAggregator4 != null) {
         int indexOffset = stack.getIndexOffset2();
         if (itemManager.count9 != 0) {
            indexOffset &= itemManager.count9;
         }

         if (itemManager.enabled) {
            int indexOffset2 = item.getIndexOffset2();
            indexOffset = (int)((double)(indexOffset * 100) / (double)indexOffset2);
         }

         if (!itemManager.targetAggregator4.checkIndex(indexOffset)) {
            return false;
         }
      }

      if (itemManager.targetAggregator2 != null && !itemManager.targetAggregator2.checkIndex(stack.animationsToGo)) {
         return false;
      } else {
         int[][] countMatrix2 = countMatrix;
         if (itemManager.targetAggregator != null) {
            if (countMatrix == null) {
               countMatrix2 = resolveIndexArray(stack);
            }

            boolean flag = false;

            for (int index = 0; index < countMatrix2.length; index++) {
               int count4 = countMatrix2[index][0];
               if (itemManager.targetAggregator.checkIndex(count4)) {
                  flag = true;
                  break;
               }
            }

            if (!flag) {
               return false;
            }
         }

         if (itemManager.targetAggregator3 != null) {
            if (countMatrix2 == null) {
               countMatrix2 = resolveIndexArray(stack);
            }

            boolean flag2 = false;

            for (int index2 = 0; index2 < countMatrix2.length; index2++) {
               int count5 = countMatrix2[index2][1];
               if (itemManager.targetAggregator3.checkIndex(count5)) {
                  flag2 = true;
                  break;
               }
            }

            if (!flag2) {
               return false;
            }
         }

         if (itemManager.geometryObserverArray != null) {
            NBTTagCompound stackTagCompound = stack.getStackTagCompound();

            for (int index3 = 0; index3 < itemManager.geometryObserverArray.length; index3++) {
               if (!itemManager.geometryObserverArray[index3].matchesNBTTagCompound(stackTagCompound)) {
                  return false;
               }
            }
         }

         if (itemManager.count15 != 0) {
            if (itemManager.count15 == 1 && enabled2) {
               return false;
            }

            if (itemManager.count15 == 2 && !enabled2) {
               return false;
            }
         }

         return true;
      }
   }

   private static List resolveList(ItemManager[][] itemManagerMatrix) {
      ArrayList list = new ArrayList();
      if (itemManagerMatrix != null) {
         for (int index = 0; index < itemManagerMatrix.length; index++) {
            ItemManager[] itemManagerArray3 = itemManagerMatrix[index];
            ArrayList list2 = null;
            if (itemManagerArray3 != null) {
               list2 = new ArrayList<>(Arrays.asList(itemManagerArray3));
            }

            list.add(list2);
         }
      }

      return list;
   }

   private static void applyItemManagerArray(ItemManager[][] itemManagerMatrix, List list) {
      if (itemManagerMatrix != null) {
         for (int index = 0; index < itemManagerMatrix.length; index++) {
            ItemManager[] itemManagerArray3 = itemManagerMatrix[index];
            if (itemManagerArray3 != null) {
               for (int index2 = 0; index2 < itemManagerArray3.length; index2++) {
                  ItemManager itemManager = itemManagerArray3[index2];
                  if (itemManager != null) {
                     list.add(itemManager);
                  }
               }
            }
         }
      }
   }

   public static void updateState2() {
      itemManagerArray = (ItemManager[][])null;
      itemManagerArray2 = (ItemManager[][])null;
      enabled = true;
      if (BuildManager.isActive35()) {
         applyText("mcpatcher/cit.properties");
         PreUpdateProcessor[] preUpdateProcessorArray = BuildManager.getPreUpdateProcessorArray();

         for (int index = preUpdateProcessorArray.length - 1; index >= 0; index--) {
            applyPreUpdateProcessor(preUpdateProcessorArray[index]);
         }

         applyPreUpdateProcessor(BuildManager.getIReloadableResourceManager());
         if (itemManagerArray.length <= 0) {
            itemManagerArray = (ItemManager[][])null;
         }

         if (itemManagerArray2.length <= 0) {
            itemManagerArray2 = (ItemManager[][])null;
         }
      }
   }

   private static int[] resolveIndexArray(int count4) {
      return new int[]{count4, count4 + 16, count4 + 32, count4 + 48};
   }

   private static Comparator getComparator() {
      return new AdvancedComparatorAdapter();
   }

   private static void applyItemManagerByItemManager(ItemManager itemManager, List list) {
      if (itemManager.count10 == 2 && itemManager.targetAggregator != null) {
         for (int index = 0; index < 256; index++) {
            if (itemManager.targetAggregator.checkIndex(index)) {
               applyItemManager(itemManager, list, index);
            }
         }
      }
   }

   private static Properties resolveProperties(String text, String text2, int count4, String text3) {
      if (SplineCollector.matchesText(text, new String[]{"_n", "_s"})) {
         return null;
      } else if (text.equals("empty") && text2.equals("normal")) {
         int index = Item.getIndex(Items.glassBottle);
         PropertiesAdapter propertiesAdapter = new PropertiesAdapter();
         propertiesAdapter.put("type", "item");
         propertiesAdapter.put("items", "" + index);
         return propertiesAdapter;
      } else {
         int[] countArray2 = (int[])getMap().get(text);
         if (countArray2 == null) {
            BuildManager.applyText3("Potion not found for image: " + text3);
            return null;
         } else {
            StringBuffer builder = new StringBuffer();

            for (int index2 = 0; index2 < countArray2.length; index2++) {
               int count5 = countArray2[index2];
               if (text2.equals("splash")) {
                  count5 |= 16384;
               }

               if (index2 > 0) {
                  builder.append(" ");
               }

               builder.append(count5);
            }

            int count6 = 16447;
            if (text.equals("water") || text.equals("mundane")) {
               count6 |= 64;
            }

            PropertiesAdapter propertiesAdapter2 = new PropertiesAdapter();
            propertiesAdapter2.put("type", "item");
            propertiesAdapter2.put("items", "" + count4);
            propertiesAdapter2.put("damage", "" + builder.toString());
            propertiesAdapter2.put("damageMask", "" + count6);
            if (text2.equals("splash")) {
               propertiesAdapter2.put("texture.potion_bottle_splash", text);
            } else {
               propertiesAdapter2.put("texture.potion_bottle_drinkable", text);
            }

            return propertiesAdapter2;
         }
      }
   }

   private static List getList() {
      ArrayList list = new ArrayList();
      applyItemManagerArray(itemManagerArray, list);
      applyItemManagerArray(itemManagerArray2, list);
      return list;
   }

   public static void applyJsonManager(JsonManager jsonManager) {
      Iterator iterator = getList().iterator();

      while (iterator.hasNext()) {
         ((ItemManager)iterator.next()).applyJsonManager(jsonManager);
      }
   }

   private static ItemManager[][] resolveItemManagerArray(List list) {
      ItemManager[][] itemManagerMatrix = new ItemManager[list.size()][];

      for (int index = 0; index < list.size(); index++) {
         List list2 = (List)list.get(index);
         if (list2 != null) {
            ItemManager[] itemManagerArray3 = (ItemManager[])list2.toArray(new ItemManager[list2.size()]);
            Arrays.sort(itemManagerArray3, new AngleChecker());
            itemManagerMatrix[index] = itemManagerArray3;
         }
      }

      return itemManagerMatrix;
   }

   public static IBakedModel resolveIBakedModel(ItemStack stack, IBakedModel iBakedModel, ResourceLocation location, boolean flag) {
      if (!flag && iBakedModel.canExecute()) {
         return iBakedModel;
      } else if (itemManagerArray == null) {
         return iBakedModel;
      } else {
         ItemManager itemManager = resolveItemManager(stack, 1);
         if (itemManager == null) {
            return iBakedModel;
         } else {
            IBakedModel iBakedModel2 = itemManager.resolveIBakedModelByResourceLocation(location, flag);
            return iBakedModel2 != null ? iBakedModel2 : iBakedModel;
         }
      }
   }

   public static boolean matchesItemStack(ItemStack stack, int count4, String text) {
      if (itemManagerArray == null) {
         return false;
      } else {
         ResourceLocation location = resolveResourceLocationByItemStack(stack, count4, text);
         if (location == null) {
            return false;
         } else {
            BuildManager.getTextureManagerAsTextureManager().applyResourceLocation3(location);
            return true;
         }
      }
   }

   public static void applyListenerAdapter27(ListenerAdapter27 listenerAdapter) {
      Iterator iterator = getList().iterator();

      while (iterator.hasNext()) {
         ((ItemManager)iterator.next()).applyListenerAdapter27(listenerAdapter);
      }
   }
}
