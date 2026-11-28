package net.optifine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemModelGenerator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;
import net.optifine.config.NbtTagValue;
import net.optifine.render.Blender;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.ShadersRender;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.ResUtils;
import net.optifine.util.StrUtils;

public class CustomItems {
   private static CustomItemProperties[][] itemProperties = (CustomItemProperties[][])null;
   private static CustomItemProperties[][] enchantmentProperties = (CustomItemProperties[][])null;
   private static Map mapPotionIds = null;
   private static ItemModelGenerator itemModelGenerator = new ItemModelGenerator();
   private static boolean useGlint = true;
   private static boolean renderOffHand = false;
   public static final int MASK_POTION_SPLASH = 16384;
   public static final int MASK_POTION_NAME = 63;
   public static final int MASK_POTION_EXTENDED = 64;
   public static final String KEY_TEXTURE_OVERLAY = null;
   public static final String KEY_TEXTURE_SPLASH = null;
   public static final String KEY_TEXTURE_DRINKABLE = null;
   public static final String DEFAULT_TEXTURE_OVERLAY = null;
   public static final String DEFAULT_TEXTURE_SPLASH = null;
   public static final String DEFAULT_TEXTURE_DRINKABLE = null;
   private static final int[][] EMPTY_INT2_ARRAY = new int[0][];
   private static final String TYPE_POTION_NORMAL = null;
   private static final String TYPE_POTION_SPLASH = null;
   private static final String TYPE_POTION_LINGER = null;

   private static void addToEnchantmentList(CustomItemProperties customItemProperties, List list) {
      if (customItemProperties.type == 2 && customItemProperties.enchantmentIds != null) {
         for (int i = 0; i < 256; i++) {
            if (customItemProperties.enchantmentIds.isInRange(i)) {
               addToList(customItemProperties, list, i);
            }
         }
      }
   }

   private static Properties makePotionProperties(String str, String str2, int n, String str3) {
      if (StrUtils.endsWith(str, new String[]{"_n", "_s"})) {
         return null;
      } else if (str.equals("empty") && str2.equals("normal")) {
         n = Item.getIdFromItem(Items.glass_bottle);
         PropertiesOrdered propertiesOrdered = new PropertiesOrdered();
         propertiesOrdered.put("type", "item");
         propertiesOrdered.put("items", "" + n);
         return propertiesOrdered;
      } else {
         int[] ints = (int[])getMapPotionIds().get(str);
         if (ints == null) {
            Config.warn("Potion not found for image: " + str3);
            return null;
         } else {
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < ints.length; i++) {
               int n2 = ints[i];
               if (str2.equals("splash")) {
                  n2 |= 16384;
               }

               if (i > 0) {
                  sb.append(" ");
               }

               sb.append(n2);
            }

            int n3 = 16447;
            if (str.equals("water") || str.equals("mundane")) {
               n3 |= 64;
            }

            PropertiesOrdered propertiesOrdered2 = new PropertiesOrdered();
            propertiesOrdered2.put("type", "item");
            propertiesOrdered2.put("items", "" + n);
            propertiesOrdered2.put("damage", "" + sb.toString());
            propertiesOrdered2.put("damageMask", "" + n3);
            if (str2.equals("splash")) {
               propertiesOrdered2.put("texture.potion_bottle_splash", str);
            } else {
               propertiesOrdered2.put("texture.potion_bottle_drinkable", str);
            }

            return propertiesOrdered2;
         }
      }
   }

   public static IBakedModel getCustomItemModel(ItemStack stack, IBakedModel bakedModel, ResourceLocation location, boolean flag) {
      if (!flag && bakedModel.isGui3d()) {
         return bakedModel;
      } else if (itemProperties == null) {
         return bakedModel;
      } else {
         CustomItemProperties customItemProperties = getCustomItemProperties(stack, 1);
         if (customItemProperties == null) {
            return bakedModel;
         } else {
            IBakedModel bakedModel2 = customItemProperties.getBakedModel(location, flag);
            return bakedModel2 != null ? bakedModel2 : bakedModel;
         }
      }
   }

   public static boolean bindCustomArmorTexture(ItemStack stack, int n, String str) {
      if (itemProperties == null) {
         return false;
      } else {
         ResourceLocation customArmorLocation = getCustomArmorLocation(stack, n, str);
         if (customArmorLocation == null) {
            return false;
         } else {
            Config.getTextureManager().bindTexture(customArmorLocation);
            return true;
         }
      }
   }

   private static Map makeAutoImageProperties(IResourcePack resourcePack) {
      HashMap map = new HashMap();
      map.putAll(makePotionImageProperties(resourcePack, "normal", Item.getIdFromItem(Items.potionitem)));
      map.putAll(makePotionImageProperties(resourcePack, "splash", Item.getIdFromItem(Items.potionitem)));
      map.putAll(makePotionImageProperties(resourcePack, "linger", Item.getIdFromItem(Items.potionitem)));
      return map;
   }

   private static Map getMapPotionIds() {
      if (mapPotionIds == null) {
         mapPotionIds = new LinkedHashMap();
         mapPotionIds.put("water", getPotionIds(0, 0));
         mapPotionIds.put("awkward", getPotionIds(0, 1));
         mapPotionIds.put("thick", getPotionIds(0, 2));
         mapPotionIds.put("potent", getPotionIds(0, 3));
         mapPotionIds.put("regeneration", getPotionIds(1));
         mapPotionIds.put("movespeed", getPotionIds(2));
         mapPotionIds.put("fireresistance", getPotionIds(3));
         mapPotionIds.put("poison", getPotionIds(4));
         mapPotionIds.put("heal", getPotionIds(5));
         mapPotionIds.put("nightvision", getPotionIds(6));
         mapPotionIds.put("clear", getPotionIds(7, 0));
         mapPotionIds.put("bungling", getPotionIds(7, 1));
         mapPotionIds.put("charming", getPotionIds(7, 2));
         mapPotionIds.put("rank", getPotionIds(7, 3));
         mapPotionIds.put("weakness", getPotionIds(8));
         mapPotionIds.put("damageboost", getPotionIds(9));
         mapPotionIds.put("moveslowdown", getPotionIds(10));
         mapPotionIds.put("leaping", getPotionIds(11));
         mapPotionIds.put("harm", getPotionIds(12));
         mapPotionIds.put("waterbreathing", getPotionIds(13));
         mapPotionIds.put("invisibility", getPotionIds(14));
         mapPotionIds.put("thin", getPotionIds(15, 0));
         mapPotionIds.put("debonair", getPotionIds(15, 1));
         mapPotionIds.put("sparkling", getPotionIds(15, 2));
         mapPotionIds.put("stinky", getPotionIds(15, 3));
         mapPotionIds.put("mundane", getPotionIds(0, 4));
         mapPotionIds.put("speed", mapPotionIds.get("movespeed"));
         mapPotionIds.put("fire_resistance", mapPotionIds.get("fireresistance"));
         mapPotionIds.put("instant_health", mapPotionIds.get("heal"));
         mapPotionIds.put("night_vision", mapPotionIds.get("nightvision"));
         mapPotionIds.put("strength", mapPotionIds.get("damageboost"));
         mapPotionIds.put("slowness", mapPotionIds.get("moveslowdown"));
         mapPotionIds.put("instant_damage", mapPotionIds.get("harm"));
         mapPotionIds.put("water_breathing", mapPotionIds.get("waterbreathing"));
      }

      return mapPotionIds;
   }

   private static int[] getPotionIds(int n) {
      return new int[]{n, n + 16, n + 32, n + 48};
   }

   private static List getAllProperties() {
      ArrayList list = new ArrayList();
      addAll(itemProperties, list);
      addAll(enchantmentProperties, list);
      return list;
   }

   public static boolean renderCustomEffect(RenderItem renderItem, ItemStack stack, IBakedModel bakedModel) {
      if (enchantmentProperties == null) {
         return false;
      } else if (stack == null) {
         return false;
      } else {
         int[][] enchantmentIdLevels = getEnchantmentIdLevels(stack);
         if (enchantmentIdLevels.length <= 0) {
            return false;
         } else {
            HashSet set = null;
            boolean flag = false;
            TextureManager textureManager = Config.getTextureManager();

            for (int i = 0; i < enchantmentIdLevels.length; i++) {
               int n = enchantmentIdLevels[i][0];
               if (n >= 0 && n < enchantmentProperties.length) {
                  CustomItemProperties[] customItemPropertiesArr = enchantmentProperties[n];
                  if (customItemPropertiesArr != null) {
                     for (int j = 0; j < customItemPropertiesArr.length; j++) {
                        CustomItemProperties customItemProperties = customItemPropertiesArr[j];
                        if (set == null) {
                           set = new HashSet();
                        }

                        if (set.add(n) && matchesProperties(customItemProperties, stack, enchantmentIdLevels) && customItemProperties.textureLocation != null) {
                           textureManager.bindTexture(customItemProperties.textureLocation);
                           float textureWidth = customItemProperties.getTextureWidth(textureManager);
                           if (!flag) {
                              flag = true;
                              GlStateManager.depthMask(false);
                              GlStateManager.depthFunc(514);
                              GlStateManager.disableLighting();
                              GlStateManager.matrixMode(5890);
                           }

                           Blender.setupBlend(customItemProperties.blend, 1.0F);
                           GlStateManager.pushMatrix();
                           GlStateManager.scale(textureWidth / 2.0F, textureWidth / 2.0F, textureWidth / 2.0F);
                           GlStateManager.translate(customItemProperties.speed * (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F / 8.0F, 0.0F, 0.0F);
                           GlStateManager.rotate(customItemProperties.rotation, 0.0F, 0.0F, 1.0F);
                           renderItem.renderModel(bakedModel, -1);
                           GlStateManager.popMatrix();
                        }
                     }
                  }
               }
            }

            if (flag) {
               GlStateManager.enableAlpha();
               GlStateManager.enableBlend();
               GlStateManager.blendFunc(770, 771);
               GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
               GlStateManager.matrixMode(5888);
               GlStateManager.enableLighting();
               GlStateManager.depthFunc(515);
               GlStateManager.depthMask(true);
               textureManager.bindTexture(TextureMap.locationBlocksTexture);
            }

            return flag;
         }
      }
   }

   private static CustomItemProperties getCustomItemProperties(ItemStack stack, int n) {
      if (itemProperties == null) {
         return null;
      } else if (stack == null) {
         return null;
      } else {
         int idFromItem = Item.getIdFromItem(stack.getItem());
         if (idFromItem >= 0 && idFromItem < itemProperties.length) {
            CustomItemProperties[] customItemPropertiesArr = itemProperties[idFromItem];
            if (customItemPropertiesArr != null) {
               for (int i = 0; i < customItemPropertiesArr.length; i++) {
                  CustomItemProperties customItemProperties = customItemPropertiesArr[i];
                  if (customItemProperties.type == n && matchesProperties(customItemProperties, stack, (int[][])null)) {
                     return customItemProperties;
                  }
               }
            }
         }

         return null;
      }
   }

   public static void loadModels(ModelBakery modelBakery) {
      for (CustomItemProperties customItemProperties : (Iterable<CustomItemProperties>)(getAllProperties())) {
         customItemProperties.loadModels(modelBakery);
      }
   }

   public static void update() {
      itemProperties = (CustomItemProperties[][])null;
      enchantmentProperties = (CustomItemProperties[][])null;
      useGlint = true;
      if (Config.isCustomItems()) {
         readCitProperties("mcpatcher/cit.properties");
         IResourcePack[] resourcePacks = Config.getResourcePacks();

         for (int i = resourcePacks.length - 1; i >= 0; i--) {
            update(resourcePacks[i]);
         }

         update(Config.getDefaultResourcePack());
         if (itemProperties.length <= 0) {
            itemProperties = (CustomItemProperties[][])null;
         }

         if (enchantmentProperties.length <= 0) {
            enchantmentProperties = (CustomItemProperties[][])null;
         }
      }
   }

   public static boolean isUseGlint() {
      return useGlint;
   }

   private static List makePropertyList(CustomItemProperties[][] customItemPropertiesArrs) {
      ArrayList list = new ArrayList();
      if (customItemPropertiesArrs != null) {
         for (int i = 0; i < customItemPropertiesArrs.length; i++) {
            CustomItemProperties[] customItemPropertiesArr = customItemPropertiesArrs[i];
            ArrayList list2 = null;
            if (customItemPropertiesArr != null) {
               list2 = new ArrayList<>(Arrays.asList(customItemPropertiesArr));
            }

            list.add(list2);
         }
      }

      return list;
   }

   private static void addAll(CustomItemProperties[][] customItemPropertiesArrs, List list) {
      if (customItemPropertiesArrs != null) {
         for (int i = 0; i < customItemPropertiesArrs.length; i++) {
            CustomItemProperties[] customItemPropertiesArr = customItemPropertiesArrs[i];
            if (customItemPropertiesArr != null) {
               for (int j = 0; j < customItemPropertiesArr.length; j++) {
                  CustomItemProperties customItemProperties = customItemPropertiesArr[j];
                  if (customItemProperties != null) {
                     list.add(customItemProperties);
                  }
               }
            }
         }
      }
   }

   private static int getPotionIdByName(String str) {
      String str2 = "potion." + str;
      Potion[] potionTypes = Potion.potionTypes;

      for (int i = 0; i < potionTypes.length; i++) {
         Potion potion = potionTypes[i];
         if (potion != null) {
            if (str2.equals(potion.getName())) {
               return potion.getId();
            }
         }
      }

      return -1;
   }

   public static void updateModels() {
      for (CustomItemProperties customItemProperties : (Iterable<CustomItemProperties>)(getAllProperties())) {
         if (customItemProperties.type == 1) {
            customItemProperties.updateModelTexture(Minecraft.getMinecraft().getTextureMapBlocks(), itemModelGenerator);
            customItemProperties.updateModelsFull();
         }
      }
   }

   private static Map makePotionImageProperties(IResourcePack resourcePack, String str, int n) {
      HashMap map = new HashMap();
      String str2 = str + "/";
      String[] strings = new String[]{"mcpatcher/cit/potion/" + str2, "mcpatcher/cit/Potion/" + str2};
      String[] strings2 = new String[]{".png"};
      String[] strings3 = ResUtils.collectFiles(resourcePack, strings, strings2);

      for (int i = 0; i < strings3.length; i++) {
         String str3 = strings3[i];
         Properties properties = makePotionProperties(StrUtils.removePrefixSuffix2(str3, strings, strings2), str, n, str3);
         if (properties != null) {
            String str5 = StrUtils.removeSuffix(str3, strings2) + ".properties";
            map.put(str5, new CustomItemProperties(properties, str5));
         }
      }

      return map;
   }

   private static boolean matchesProperties(CustomItemProperties customItemProperties, ItemStack stack, int[][] intsArr) {
      Item item = stack.getItem();
      if (customItemProperties.damage != null) {
         int itemDamage = stack.getItemDamage();
         if (customItemProperties.damageMask != 0) {
            itemDamage &= customItemProperties.damageMask;
         }

         if (customItemProperties.damagePercent) {
            itemDamage = (int)((double)(itemDamage * 100) / (double)item.getMaxDamage());
         }

         if (!customItemProperties.damage.isInRange(itemDamage)) {
            return false;
         }
      }

      if (customItemProperties.stackSize != null && !customItemProperties.stackSize.isInRange(stack.stackSize)) {
         return false;
      } else {
         int[][] intsArr2 = intsArr;
         if (customItemProperties.enchantmentIds != null) {
            if (intsArr == null) {
               intsArr2 = getEnchantmentIdLevels(stack);
            }

            boolean flag = false;

            for (int i = 0; i < intsArr2.length; i++) {
               int n = intsArr2[i][0];
               if (customItemProperties.enchantmentIds.isInRange(n)) {
                  flag = true;
                  break;
               }
            }

            if (!flag) {
               return false;
            }
         }

         if (customItemProperties.enchantmentLevels != null) {
            if (intsArr2 == null) {
               intsArr2 = getEnchantmentIdLevels(stack);
            }

            boolean flag2 = false;

            for (int j = 0; j < intsArr2.length; j++) {
               int n2 = intsArr2[j][1];
               if (customItemProperties.enchantmentLevels.isInRange(n2)) {
                  flag2 = true;
                  break;
               }
            }

            if (!flag2) {
               return false;
            }
         }

         if (customItemProperties.nbtTagValues != null) {
            NBTTagCompound tagCompound = stack.getTagCompound();

            for (int k = 0; k < customItemProperties.nbtTagValues.length; k++) {
               if (!customItemProperties.nbtTagValues[k].matches(tagCompound)) {
                  return false;
               }
            }
         }

         if (customItemProperties.hand != 0) {
            if (customItemProperties.hand == 1 && renderOffHand) {
               return false;
            }

            if (customItemProperties.hand == 2 && !renderOffHand) {
               return false;
            }
         }

         return true;
      }
   }

   private static Comparator getPropertiesComparator() {
      return new CustomItems.EntryComparator();
   }

   private static void addToItemList(CustomItemProperties customItemProperties, List list) {
      if (customItemProperties.items != null) {
         for (int i = 0; i < customItemProperties.items.length; i++) {
            int n = customItemProperties.items[i];
            if (n <= 0) {
               Config.warn("Invalid item ID: " + n);
            } else {
               addToList(customItemProperties, list, n);
            }
         }
      }
   }

   private static ResourceLocation getCustomArmorLocation(ItemStack stack, int n, String str) {
      CustomItemProperties customItemProperties = getCustomItemProperties(stack, 3);
      if (customItemProperties == null) {
         return null;
      } else if (customItemProperties.mapTextureLocations == null) {
         return customItemProperties.textureLocation;
      } else {
         Item item = stack.getItem();
         if (!(item instanceof ItemArmor)) {
            return null;
         } else {
            String name = ((ItemArmor)item).getArmorMaterial().getName();
            StringBuffer sb = new StringBuffer();
            sb.append("texture.");
            sb.append(name);
            sb.append("_layer_");
            sb.append(n);
            if (str != null) {
               sb.append("_");
               sb.append(str);
            }

            String str2 = sb.toString();
            ResourceLocation location = (ResourceLocation)customItemProperties.mapTextureLocations.get(str2);
            return location == null ? customItemProperties.textureLocation : location;
         }
      }
   }

   public static void updateIcons(TextureMap textureMap) {
      for (CustomItemProperties customItemProperties : (Iterable<CustomItemProperties>)(getAllProperties())) {
         customItemProperties.updateIcons(textureMap);
      }
   }

   private static int[][] getEnchantmentIdLevels(ItemStack stack) {
      NBTTagList tagList = stack.getItem() == Items.enchanted_book ? Items.enchanted_book.getEnchantments(stack) : stack.getEnchantmentTagList();
      if (tagList != null && tagList.tagCount() > 0) {
         int[][] intsArr = new int[tagList.tagCount()][2];

         for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound compoundTagAt = tagList.getCompoundTagAt(i);
            short s = compoundTagAt.getShort("id");
            short s2 = compoundTagAt.getShort("lvl");
            intsArr[i][0] = s;
            intsArr[i][1] = s2;
         }

         return intsArr;
      } else {
         return EMPTY_INT2_ARRAY;
      }
   }

   private static CustomItemProperties[][] propertyListToArray(List list) {
      CustomItemProperties[][] customItemPropertiesArrs = new CustomItemProperties[list.size()][];

      for (int i = 0; i < list.size(); i++) {
         List list2 = (List)list.get(i);
         if (list2 != null) {
            CustomItemProperties[] customItemPropertiesArr = (CustomItemProperties[])list2.toArray(new CustomItemProperties[list2.size()]);
            Arrays.sort(customItemPropertiesArr, new CustomItemsComparator());
            customItemPropertiesArrs[i] = customItemPropertiesArr;
         }
      }

      return customItemPropertiesArrs;
   }

   private static void update(IResourcePack resourcePack) {
      String[] strings = ResUtils.collectFiles(resourcePack, "mcpatcher/cit/", ".properties", (String[])null);
      Map map = makeAutoImageProperties(resourcePack);
      if (map.size() > 0) {
         Set keys = map.keySet();
         strings = (String[])Config.addObjectsToArray(strings, (String[])keys.toArray(new String[keys.size()]));
      }

      Arrays.sort(strings);
      List list = makePropertyList(itemProperties);
      List list2 = makePropertyList(enchantmentProperties);

      for (int i = 0; i < strings.length; i++) {
         String str = strings[i];
         Config.dbg("CustomItems: " + str);

         try {
            CustomItemProperties customItemProperties = null;
            if (map.containsKey(str)) {
               customItemProperties = (CustomItemProperties)map.get(str);
            }

            if (customItemProperties == null) {
               InputStream inputStream = resourcePack.getInputStream(new ResourceLocation(str));
               if (inputStream == null) {
                  Config.warn("CustomItems file not found: " + str);
                  continue;
               }

               PropertiesOrdered propertiesOrdered = new PropertiesOrdered();
               propertiesOrdered.load(inputStream);
               inputStream.close();
               customItemProperties = new CustomItemProperties(propertiesOrdered, str);
            }

            if (customItemProperties.isValid(str)) {
               addToItemList(customItemProperties, list);
               addToEnchantmentList(customItemProperties, list2);
            }
         } catch (FileNotFoundException e) {
            Config.warn("CustomItems file not found: " + str);
         } catch (Exception e2) {
            e2.printStackTrace();
         }
      }

      itemProperties = propertyListToArray(list);
      enchantmentProperties = propertyListToArray(list2);
      Comparator propertiesComparator = getPropertiesComparator();

      for (int j = 0; j < itemProperties.length; j++) {
         CustomItemProperties[] customItemPropertiesArr = itemProperties[j];
         if (customItemPropertiesArr != null) {
            Arrays.sort(customItemPropertiesArr, propertiesComparator);
         }
      }

      for (int k = 0; k < enchantmentProperties.length; k++) {
         CustomItemProperties[] customItemPropertiesArr2 = enchantmentProperties[k];
         if (customItemPropertiesArr2 != null) {
            Arrays.sort(customItemPropertiesArr2, propertiesComparator);
         }
      }
   }

   private static void readCitProperties(String str) {
      try {
         InputStream resourceStream = Config.getResourceStream(new ResourceLocation(str));
         if (resourceStream == null) {
            return;
         }

         Config.dbg("CustomItems: Loading " + str);
         PropertiesOrdered propertiesOrdered = new PropertiesOrdered();
         propertiesOrdered.load(resourceStream);
         resourceStream.close();
         useGlint = Config.parseBoolean2(propertiesOrdered.getProperty("useGlint"), true);
      } catch (FileNotFoundException e) {
         return;
      } catch (IOException e2) {
         e2.printStackTrace();
      }
   }

   private static int[] getPotionIds(int n, int n2) {
      return new int[]{n + n2 * 16};
   }

   public static boolean renderCustomArmorEffect(
      EntityLivingBase entity, ItemStack stack, ModelBase modelBase, float f, float f2, float f3, float f4, float f5, float f6, float f7
   ) {
      if (enchantmentProperties == null) {
         return false;
      } else if (Config.isShaderPackLoaded() && Shaders.isShadowPass) {
         return false;
      } else if (stack == null) {
         return false;
      } else {
         int[][] enchantmentIdLevels = getEnchantmentIdLevels(stack);
         if (enchantmentIdLevels.length <= 0) {
            return false;
         } else {
            HashSet set = null;
            boolean flag = false;
            TextureManager textureManager = Config.getTextureManager();

            for (int i = 0; i < enchantmentIdLevels.length; i++) {
               int n = enchantmentIdLevels[i][0];
               if (n >= 0 && n < enchantmentProperties.length) {
                  CustomItemProperties[] customItemPropertiesArr = enchantmentProperties[n];
                  if (customItemPropertiesArr != null) {
                     for (int j = 0; j < customItemPropertiesArr.length; j++) {
                        CustomItemProperties customItemProperties = customItemPropertiesArr[j];
                        if (set == null) {
                           set = new HashSet();
                        }

                        if (set.add(n) && matchesProperties(customItemProperties, stack, enchantmentIdLevels) && customItemProperties.textureLocation != null) {
                           textureManager.bindTexture(customItemProperties.textureLocation);
                           float textureWidth = customItemProperties.getTextureWidth(textureManager);
                           if (!flag) {
                              flag = true;
                              if (Config.isShaderPackLoaded()) {
                                 ShadersRender.renderEnchantedGlintBegin();
                              }

                              GlStateManager.enableBlend();
                              GlStateManager.depthFunc(514);
                              GlStateManager.depthMask(false);
                           }

                           Blender.setupBlend(customItemProperties.blend, 1.0F);
                           GlStateManager.disableLighting();
                           GlStateManager.matrixMode(5890);
                           GlStateManager.loadIdentity();
                           GlStateManager.rotate(customItemProperties.rotation, 0.0F, 0.0F, 1.0F);
                           float f8 = textureWidth / 8.0F;
                           GlStateManager.scale(f8, f8 / 2.0F, f8);
                           float f9 = customItemProperties.speed * (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F / 8.0F;
                           GlStateManager.translate(0.0F, f9, 0.0F);
                           GlStateManager.matrixMode(5888);
                           modelBase.render(entity, f, f2, f4, f5, f6, f7, true);
                        }
                     }
                  }
               }
            }

            if (flag) {
               GlStateManager.enableAlpha();
               GlStateManager.enableBlend();
               GlStateManager.blendFunc(770, 771);
               GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
               GlStateManager.matrixMode(5890);
               GlStateManager.loadIdentity();
               GlStateManager.matrixMode(5888);
               GlStateManager.enableLighting();
               GlStateManager.depthMask(true);
               GlStateManager.depthFunc(515);
               GlStateManager.disableBlend();
               if (Config.isShaderPackLoaded()) {
                  ShadersRender.renderEnchantedGlintEnd();
               }
            }

            return flag;
         }
      }
   }

   private static void addToList(CustomItemProperties customItemProperties, List list, int n) {
      while (n >= list.size()) {
         list.add(null);
      }

      Object obj = (List)list.get(n);
      if (obj == null) {
         obj = new ArrayList();
         list.set(n, obj);
      }

      ((List)obj).add(customItemProperties);
   }


   public static final class EntryComparator implements Comparator {
      @Override
      public int compare(Object a, Object b) {
         CustomItemProperties customItemProperties = (CustomItemProperties)a;
         CustomItemProperties customItemProperties2 = (CustomItemProperties)b;
         return customItemProperties.layer != customItemProperties2.layer
            ? customItemProperties.layer - customItemProperties2.layer
            : (
               customItemProperties.weight != customItemProperties2.weight
                  ? customItemProperties2.weight - customItemProperties.weight
                  : (!customItemProperties.basePath.equals(customItemProperties2.basePath) ? customItemProperties.basePath.compareTo(customItemProperties2.basePath) : customItemProperties.name.compareTo(customItemProperties2.name))
            );
      }
   }
}
