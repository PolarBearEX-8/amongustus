package net.augustus.utils.player;

import net.augustus.Augustus;
import net.augustus.ClientManagers;
import net.augustus.settings.options.InvManagerActionOption;
import net.augustus.utils.TuplePair;
import net.augustus.utils.inventory.InventorySlotPoint;
import net.augustus.utils.inventory.NearestSlotPathSorter;
import net.augustus.utils.math.Vec2d;
import net.augustus.utils.world.BlockUtil;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import net.augustus.utils.TimeHelper;
import net.augustus.utils.interfaces.MC;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

public class InventoryUtil implements MC {
   private static int lastClickedSlot = 0;
   private static final int[] TRASH_ITEM_IDS = new int[]{
      139,
      28,
      27,
      295,
      192,
      171,
      120,
      84,
      32,
      31,
      160,
      425,
      175,
      390,
      12,
      38,
      37,
      50,
      6,
      23,
      158,
      183,
      184,
      373,
      185,
      186,
      187,
      188,
      189,
      190,
      191,
      290,
      291,
      292,
      193,
      194,
      39,
      40,
      288,
      289,
      53,
      67,
      70,
      72,
      77,
      85,
      81,
      96,
      101,
      102,
      106,
      107,
      108,
      109,
      111,
      113,
      114,
      128,
      131,
      134,
      135,
      136,
      143,
      147,
      148,
      151,
      154,
      156,
      157,
      163,
      164,
      167,
      180,
      318,
      321,
      337,
      338,
      323,
      348,
      352,
      353,
      354,
      356,
      361,
      259,
      362,
      367,
      370,
      371,
      372,
      373,
      334
   };
   private static final ArrayList CACHED_SLOTS = new ArrayList();
   private static final String[] TRASH_ITEM_NAMES = new String[]{
      "tallgrass",
      "deadbush",
      "red_flower",
      "wooden_slab",
      "cobblestone_wall",
      "anvil",
      "stone_slab",
      "snow_layer",
      "stained_glass_pane",
      "carpet",
      "double_plant",
      "stone_slab2",
      "sapling"
   };
   private static Vec2d lastCursorPos = new Vec2d(0.0, 0.0);

   private static float getArmorExtraScore(Int2IntOpenHashMap int2IntOpenHashMap, ItemStack stack, boolean flag, boolean flag2) {
      if (stack == null) {
         return 0.0F;
      } else {
         float f = 0.0F;
         if (flag2) {
            int cachedEnchantmentLevel = getCachedEnchantmentLevel(Enchantment.protection.effectId, stack, int2IntOpenHashMap);
            f += (float)(6 + cachedEnchantmentLevel * cachedEnchantmentLevel) / 3.0F;
         }

         f += (float)getCachedEnchantmentLevel(Enchantment.blastProtection.effectId, stack, int2IntOpenHashMap) / 50.0F;
         f += (float)getCachedEnchantmentLevel(Enchantment.projectileProtection.effectId, stack, int2IntOpenHashMap) / 50.0F;
         f += (float)getCachedEnchantmentLevel(Enchantment.fireProtection.effectId, stack, int2IntOpenHashMap) / 50.0F;
         f += (float)getCachedEnchantmentLevel(Enchantment.unbreaking.effectId, stack, int2IntOpenHashMap) / 69.0F;
         f += (float)getCachedEnchantmentLevel(Enchantment.thorns.effectId, stack, int2IntOpenHashMap) / 50.0F;
         f += (float)getCachedEnchantmentLevel(Enchantment.featherFalling.effectId, stack, int2IntOpenHashMap) / 50.0F;
         if (!flag) {
            f += (float)(stack.getMaxDamage() - stack.getItemDamage()) / 10000.0F;
         }

         return f;
      }
   }

   public static ArrayList getSortedShovels(Container container, int n, ArrayList list) {
      ArrayList list2 = new ArrayList();

      for (int i = n; i < container.inventorySlots.size(); i++) {
         ItemStack stack = container.getSlot(i).getStack();
         if (stack != null) {
            if (stack.getItem() instanceof ItemSpade) {
               list2.add(new TuplePair(getItemScore(stack, true), i));
            }
         }
      }

      sortItemTuplesWithPreferredHotbar(list2, list);
      return list2;
   }

   private static float getTotalArmorDefense(ItemStack stack, ItemStack stack2, ItemStack stack3, ItemStack stack4) {
      float f = 0.0F;
      if (stack != null && stack.getItem() instanceof ItemArmor) {
         float damageReduceAmount = (float)((ItemArmor)stack.getItem()).damageReduceAmount;
         f += damageReduceAmount;
      }

      if (stack2 != null && stack2.getItem() instanceof ItemArmor) {
         float damageReduceAmount2 = (float)((ItemArmor)stack2.getItem()).damageReduceAmount;
         f += damageReduceAmount2;
      }

      if (stack3 != null && stack3.getItem() instanceof ItemArmor) {
         float damageReduceAmount3 = (float)((ItemArmor)stack3.getItem()).damageReduceAmount;
         f += damageReduceAmount3;
      }

      if (stack4 != null && stack4.getItem() instanceof ItemArmor) {
         float damageReduceAmount4 = (float)((ItemArmor)stack4.getItem()).damageReduceAmount;
         f += damageReduceAmount4;
      }

      return f;
   }

   private static float applyArmorDamageReduction(float f, ArrayList list) {
      return (f * (25.0F - getTotalArmorDefense(list))) / 25.0F;
   }

   public static ArrayList getSortedBlocks(Container container, int n, int n2) {
      ArrayList list = new ArrayList();

      for (int i = n; i < n2; i++) {
         ItemStack stack = container.getSlot(i).getStack();
         if (stack != null) {
            if (stack.getItem() instanceof ItemBlock) {
               if (BlockUtil.isValidScaffoldBlock(stack, true)) {
                  list.add(new TuplePair(getItemScore(stack), i));
               }
            }
         }
      }

      list.sort(Comparator.comparingDouble(obj -> (double)container.getSlot((Integer)((TuplePair)obj).getSecond()).getStack().stackSize));
      Collections.reverse(list);
      return list;
   }

   public static ArrayList getSortedShears(Container container, int n) {
      return getSortedShears(container, n, (ArrayList)null);
   }

   public static boolean isNotSoupCraftIngredient(Item item) {
      return Augustus.getInstance().getModuleManager() == null
         || !ClientManagers.moduleManager.autoSoup.isToggled() && !ClientManagers.moduleManager.refill.isToggled() && !InvManagerActionOption.CRAFT.isSelected()
         || !item.equals(Item.getItemById(281)) && !item.equals(Item.getItemById(39)) && !item.equals(Item.getItemById(40));
   }

   public static ArrayList getSortedWeapons(Container container, int n) {
      return getSortedWeapons(container, n, (ArrayList)null);
   }

   public static ArrayList getSortedFishingRods(Container container, int n, ArrayList list) {
      ArrayList list2 = new ArrayList();

      for (int i = n; i < container.inventorySlots.size(); i++) {
         ItemStack stack = container.getSlot(i).getStack();
         if (stack != null) {
            Item item = stack.getItem();
            double itemScore = getItemScore(stack);
            if (item instanceof ItemFishingRod) {
               list2.add(new TuplePair(itemScore, i));
            }
         }
      }

      sortItemTuplesWithPreferredHotbar(list2, list);
      return list2;
   }

   public static int getLastClickedSlot() {
      return lastClickedSlot;
   }

   public static List sortSlotsByNearest(Container container, List list, int n) {
      if (list.isEmpty()) {
         return list;
      } else {
         ScaledResolution sr = new ScaledResolution(mc);
         int n2 = (sr.getScaledWidth() - 176) / 2;
         int n3 = (sr.getScaledHeight() - n) / 2;
         ArrayList list2 = new ArrayList();
         HashMap map = new HashMap();

         for (int n4 : (Iterable<Integer>)(list)) {
            Slot slot = (Slot)container.inventorySlots.get(n4);
            InventorySlotPoint inventorySlotPoint = new InventorySlotPoint((double)(slot.xDisplayPosition + 2 + n2), (double)(slot.yDisplayPosition + 2 + n3));
            map.put(inventorySlotPoint, n4);
            list2.add(inventorySlotPoint);
         }

         if (list2.size() > 1) {
            InventorySlotPoint inventorySlotPoint2 = new InventorySlotPoint(lastCursorPos.x, lastCursorPos.y);
            InventorySlotPoint inventorySlotPoint3 = null;
            double maxvalue = Double.MAX_VALUE;

            for (InventorySlotPoint inventorySlotPoint4 : (Iterable<InventorySlotPoint>)(list2)) {
               double distance = inventorySlotPoint4.distanceTo(inventorySlotPoint2);
               if (distance < maxvalue) {
                  inventorySlotPoint3 = inventorySlotPoint4;
                  maxvalue = distance;
               }
            }

            int n5 = list2.indexOf(inventorySlotPoint3);
            ArrayList list3 = new ArrayList(list2.subList(0, n5));
            ArrayList list4 = new ArrayList(list2.subList(n5, list2.size()));
            list2.clear();
            list2.addAll(list4);
            list2.addAll(list3);
         }

         ArrayList list5 = NearestSlotPathSorter.sortByNearestNeighbor(list2);
         ArrayList list6 = new ArrayList();

         for (InventorySlotPoint inventorySlotPoint5 : (Iterable<InventorySlotPoint>)(list5)) {
            list6.add(map.get(inventorySlotPoint5));
         }

         return list6;
      }
   }

   public static int getCachedEnchantmentLevel(int n, ItemStack stack, Int2IntOpenHashMap int2IntOpenHashMap) {
      return int2IntOpenHashMap.computeIfAbsent(n, n2 -> {
         if (stack != null) {
            NBTTagList enchantmentTagList = stack.getEnchantmentTagList();
            if (enchantmentTagList != null) {
               for (int i = 0; i < enchantmentTagList.tagCount(); i++) {
                  if (enchantmentTagList.getCompoundTagAt(i).getShort("id") == n) {
                     short s2 = enchantmentTagList.getCompoundTagAt(i).getShort("lvl");
                     int2IntOpenHashMap.put(n, s2);
                     return s2;
                  }
               }
            }
         }

         return 0;
      });
   }

   private static ArrayList sortItemTuplesWithPreferredHotbar(ArrayList list, ArrayList list2) {
      if (list.isEmpty()) {
         return list;
      } else {
         list.sort(Comparator.comparingDouble(obj -> ((Number)((TuplePair)obj).getFirst()).doubleValue()));
         Collections.reverse(list);
         if (list2 != null && !list.isEmpty() && list.size() >= 2) {
            double first = (Double)((TuplePair)list.get(0)).getFirst();

            for (int i = 0; i < list.size(); i++) {
               TuplePair tuplePair = (TuplePair)list.get(i);
               if ((Double)tuplePair.getFirst() == first && list2.contains((Integer)tuplePair.getSecond() - 36)) {
                  list.set(i, new TuplePair((Double)tuplePair.getFirst() + 1.0, tuplePair.getSecond()));
               }
            }

            list.sort(Comparator.comparingDouble(obj2 -> ((Number)((TuplePair)obj2).getFirst()).doubleValue()));
            Collections.reverse(list);
            return list;
         } else {
            return list;
         }
      }
   }

   private static float applyArmorSetDamageReduction(float f, ItemStack stack, ItemStack stack2, ItemStack stack3, ItemStack stack4) {
      return (f * (25.0F - getTotalArmorDefense(stack, stack2, stack3, stack4))) / 25.0F;
   }

   public static ArrayList getSortedShovels(Container container, int n) {
      return getSortedShovels(container, n, (ArrayList)null);
   }

   public static ArrayList findBestArmorSet(Container container, int n, int n2, boolean flag, boolean flag2) {
      return findBestArmorSet((Set)null, container, n, n2, flag, flag2);
   }

   public static ArrayList getSortedFood(Container container, int n) {
      ArrayList list = new ArrayList();

      for (int i = n; i < container.inventorySlots.size(); i++) {
         ItemStack stack = container.getSlot(i).getStack();
         if (stack != null) {
            Item item = stack.getItem();
            if (item instanceof ItemFood && item != Item.getByNameOrId("golden_apple") && !item.equals(Items.mushroom_stew)) {
               double itemScore = getItemScore(stack);
               list.add(new TuplePair(itemScore, i));
            }
         }
      }

      list.sort(Comparator.comparingDouble(obj -> ((Number)((TuplePair)obj).getFirst()).doubleValue()));
      Collections.reverse(list);
      return list;
   }

   public static List sortSlotsByMode(Container container, List list, String str) {
      return sortSlotsByMode(container, list, str, (short)166);
   }

public static ArrayList findBestArmorSet(Set set, Container container, int n, int n2, boolean flag, boolean flag2) {
      ArrayList list = new ArrayList();
      list.add(new ArrayList());
      list.add(new ArrayList());
      list.add(new ArrayList());
      list.add(new ArrayList());

      for (int i = n; i < n2; i++) {
         if (set == null || !set.contains(i)) {
            ItemStack stack = container.getSlot(i).getStack();
            if (stack != null) {
               Item item = stack.getItem();
               if (item instanceof ItemArmor) {
                  ((ArrayList)list.get(((ItemArmor)item).armorType)).add(stack);
               }
            }
         }
      }

      if (flag2) {
         for (int j = 5; j < Math.min(n, 9); j++) {
            if (set == null || !set.contains(j)) {
               ItemStack stack2 = container.getSlot(j).getStack();
               if (stack2 != null) {
                  Item item2 = stack2.getItem();
                  if (item2 instanceof ItemArmor) {
                     ((ArrayList)list.get(((ItemArmor)item2).armorType)).add(stack2);
                  }
               }
            }
         }
      }

      for (ArrayList list2 : (Iterable<ArrayList>)(list)) {
         if (list2.isEmpty()) {
            list2.add(null);
         }
      }

      float maxvalue = Float.MAX_VALUE;
      ArrayList list3 = new ArrayList();
      HashMap map = new HashMap();

      for (ItemStack stack3 : (Iterable<ItemStack>)((ArrayList)list.get(0))) {
         int protectionModifier = getProtectionModifier(map, stack3);

         for (ItemStack stack4 : (Iterable<ItemStack>)((ArrayList)list.get(1))) {
            int n3 = protectionModifier + (protectionModifier > 20 ? 0 : getProtectionModifier(map, stack4));

            for (ItemStack stack5 : (Iterable<ItemStack>)((ArrayList)list.get(2))) {
               int n4 = n3 + (n3 > 20 ? 0 : getProtectionModifier(map, stack5));

               for (ItemStack stack6 : (Iterable<ItemStack>)((ArrayList)list.get(3))) {
                  int n5 = n4 + (n4 > 20 ? 0 : getProtectionModifier(map, stack6));
                  float f = applyArmorSetDamageReduction(100.0F, stack3, stack4, stack5, stack6);
                  f = applyProtectionCapReduction(f, n5);
                  if (f < maxvalue) {
                     maxvalue = f;
                     list3.clear();
                     ArrayList list4 = new ArrayList();
                     list4.add(stack3);
                     list4.add(stack4);
                     list4.add(stack5);
                     list4.add(stack6);
                     list3.add(list4);
                  } else if (f == maxvalue) {
                     ArrayList list5 = new ArrayList();
                     list5.add(stack3);
                     list5.add(stack4);
                     list5.add(stack5);
                     list5.add(stack6);
                     list3.add(list5);
                  }
               }
            }
         }
      }

      if (list3.isEmpty()) {
         return new ArrayList();
      } else {
         ArrayList list6 = Lists.newArrayList(
            new ItemStack[]{
               mc.thePlayer.inventoryContainer.getSlot(5).getStack(),
               mc.thePlayer.inventoryContainer.getSlot(6).getStack(),
               mc.thePlayer.inventoryContainer.getSlot(7).getStack(),
               mc.thePlayer.inventoryContainer.getSlot(8).getStack()
            }
         );
         ArrayList list7 = (ArrayList)list3.get(0);
         float f2 = -2000000.0F;
         if (list3.size() > 1) {
            for (ArrayList list8 : (Iterable<ArrayList>)(list3)) {
               float f3 = 0.0F;

               for (ItemStack stack7 : (Iterable<ItemStack>)(list8)) {
                  boolean flag3 = false;
                  if (flag && stack7 != null && stack7.getItem() instanceof ItemArmor) {
                     if (((ItemStack)list6.get(((ItemArmor)stack7.getItem()).armorType)) != null) {
                        flag3 = true;
                     }
                  }

                  Int2IntOpenHashMap int2IntOpenHashMap = (Int2IntOpenHashMap)(map.computeIfAbsent(stack7, obj -> new Int2IntOpenHashMap()));
                  f3 += getArmorExtraScore(int2IntOpenHashMap, stack7, flag3, false);
               }

               if (f3 > f2) {
                  f2 = f3;
                  list7 = list8;
               }
            }
         }

         ArrayList list9 = new ArrayList();

         for (ItemStack stack9 : (Iterable<ItemStack>)(list6)) {
            if (stack9 != null && stack9.getItem() instanceof ItemArmor) {
               Int2IntOpenHashMap int2IntOpenHashMap2 = (Int2IntOpenHashMap)(map.computeIfAbsent(stack9, obj2 -> new Int2IntOpenHashMap()));

               for (ItemStack stack10 : (Iterable<ItemStack>)(list7)) {
                  if (stack10 != null && stack10.getItem() instanceof ItemArmor) {
                     ItemArmor item4 = (ItemArmor)stack10.getItem();
                     ItemArmor item5 = (ItemArmor)stack9.getItem();
                     if (item4.armorType == item5.armorType
                        && (
                           item4.getArmorMaterial().getName().equals(item5.getArmorMaterial().getName())
                              || item4.armorType == 0
                                 && (item4.getArmorMaterial().equals(ItemArmor.ArmorMaterial.GOLD) || item4.getArmorMaterial().equals(ItemArmor.ArmorMaterial.IRON))
                                 && (item5.getArmorMaterial().equals(ItemArmor.ArmorMaterial.GOLD) || item5.getArmorMaterial().equals(ItemArmor.ArmorMaterial.IRON))
                        )) {
                        Int2IntOpenHashMap int2IntOpenHashMap3 = (Int2IntOpenHashMap)(map.computeIfAbsent(stack10, obj3 -> new Int2IntOpenHashMap()));
                        if (getArmorExtraScore(int2IntOpenHashMap2, stack9, flag, true) == getArmorExtraScore(int2IntOpenHashMap3, stack10, flag, true)) {
                           list9.add(stack10);
                        }
                     }
                  }
               }

               list7.removeAll(list9);
               list9.clear();
            }
         }

         ArrayList list10 = new ArrayList();

         for (int k = n; k < n2; k++) {
            ItemStack stack11 = container.getSlot(k).getStack();
            if (stack11 != null) {
               if (stack11.getItem() instanceof ItemArmor && list7.contains(stack11)) {
                  list10.add(k);
               }
            }
         }

         return list10;
      }
   }

   public static ArrayList getSortedMushroomStews(Container container, int n) {
      ArrayList list = new ArrayList();

      for (int i = n; i < container.inventorySlots.size(); i++) {
         ItemStack stack = container.getSlot(i).getStack();
         if (stack != null) {
            if (stack.getItem().equals(Items.mushroom_stew)) {
               list.add(new TuplePair(getItemScore(stack), i));
            }
         }
      }

      list.sort(Comparator.comparingDouble(obj -> (double)container.getSlot((Integer)((TuplePair)obj).getSecond()).getStack().stackSize));
      Collections.reverse(list);
      return list;
   }

   private static List getSortedBuckets(Container container, int n, Item item) {
      ArrayList list = new ArrayList();
      List list2 = ContainerSlotQuery.of(container).fromSlot(n).filterItemExact(item).toSlotIndexList();
      if (list2.isEmpty()) {
         list2 = ContainerSlotQuery.of(container).fromSlot(n).filterItemExact(Items.bucket).toSlotIndexList();
      }

      for (int n2 : (Iterable<Integer>)(list2)) {
         ItemStack stack = container.getSlot(n2).getStack();
         list.add(new TuplePair(getItemScore(stack), n2));
      }

      list.sort(Comparator.comparingDouble(obj -> (double)container.getSlot((Integer)((TuplePair)obj).getSecond()).getStack().stackSize));
      Collections.reverse(list);
      return list;
   }

   public static ArrayList getSortedGoldenApples(Container container, int n) {
      ArrayList list = new ArrayList();

      for (int i = n; i < container.inventorySlots.size(); i++) {
         ItemStack stack = container.getSlot(i).getStack();
         if (stack != null) {
            Item item = stack.getItem();
            if (item instanceof ItemFood && item == Item.getByNameOrId("golden_apple")) {
               double itemScore = getItemScore(stack);
               list.add(new TuplePair(itemScore, i));
            }
         }
      }

      list.sort(Comparator.comparingDouble(obj -> ((Number)((TuplePair)obj).getFirst()).doubleValue()));
      Collections.reverse(list);
      return list;
   }

   public static Vec2d getLastCursorPos() {
      return lastCursorPos;
   }

   public static ArrayList getSortedAxes(Container container, int n) {
      return getSortedAxes(container, n, (ArrayList)null);
   }

   public static ArrayList findBestArmorSetFromList(List list, boolean flag) {
      ArrayList list2 = new ArrayList();
      list2.add(new ArrayList());
      list2.add(new ArrayList());
      list2.add(new ArrayList());
      list2.add(new ArrayList());

      for (ItemStack stack : (Iterable<ItemStack>)(list)) {
         if (stack != null) {
            Item item = stack.getItem();
            if (item instanceof ItemArmor) {
               ((ArrayList)list2.get(((ItemArmor)item).armorType)).add(stack);
            }
         }
      }

      for (ArrayList list3 : (Iterable<ArrayList>)(list2)) {
         if (list3.isEmpty()) {
            list3.add(null);
         }
      }

      float maxvalue = Float.MAX_VALUE;
      ArrayList list4 = new ArrayList();
      HashMap map = new HashMap();

      for (ItemStack stack2 : (Iterable<ItemStack>)((ArrayList)list2.get(0))) {
         int protectionModifier = getProtectionModifier(map, stack2);

         for (ItemStack stack3 : (Iterable<ItemStack>)((ArrayList)list2.get(1))) {
            int n = protectionModifier + (protectionModifier > 20 ? 0 : getProtectionModifier(map, stack3));

            for (ItemStack stack4 : (Iterable<ItemStack>)((ArrayList)list2.get(2))) {
               int n2 = n + (n > 20 ? 0 : getProtectionModifier(map, stack4));

               for (ItemStack stack5 : (Iterable<ItemStack>)((ArrayList)list2.get(3))) {
                  int n3 = n2 + (n2 > 20 ? 0 : getProtectionModifier(map, stack5));
                  float f = applyArmorSetDamageReduction(100.0F, stack2, stack3, stack4, stack5);
                  f = applyProtectionCapReduction(f, n3);
                  if (f < maxvalue) {
                     maxvalue = f;
                     list4.clear();
                     ArrayList list5 = new ArrayList();
                     list5.add(stack2);
                     list5.add(stack3);
                     list5.add(stack4);
                     list5.add(stack5);
                     list4.add(list5);
                  } else if (f == maxvalue) {
                     ArrayList list6 = new ArrayList();
                     list6.add(stack2);
                     list6.add(stack3);
                     list6.add(stack4);
                     list6.add(stack5);
                     list4.add(list6);
                  }
               }
            }
         }
      }

      if (list4.isEmpty()) {
         return new ArrayList();
      } else {
         ArrayList list7 = Lists.newArrayList(
            new ItemStack[]{
               mc.thePlayer.inventoryContainer.getSlot(8).getStack(),
               mc.thePlayer.inventoryContainer.getSlot(7).getStack(),
               mc.thePlayer.inventoryContainer.getSlot(6).getStack(),
               mc.thePlayer.inventoryContainer.getSlot(5).getStack()
            }
         );
         ArrayList list8 = (ArrayList)list4.get(0);
         if (list4.size() > 1) {
            float f2 = -2000000.0F;

            for (ArrayList list9 : (Iterable<ArrayList>)(list4)) {
               float f3 = 0.0F;

               for (ItemStack stack6 : (Iterable<ItemStack>)(list9)) {
                  boolean flag2 = false;
                  if (flag && stack6 != null && stack6.getItem() instanceof ItemArmor) {
                     if (((ItemStack)list7.get(((ItemArmor)stack6.getItem()).armorType)) != null) {
                        flag2 = true;
                     }
                  }

                  Int2IntOpenHashMap int2IntOpenHashMap = (Int2IntOpenHashMap)(map.computeIfAbsent(stack6, obj -> new Int2IntOpenHashMap()));
                  f3 += getArmorExtraScore(int2IntOpenHashMap, stack6, flag2, false);
               }

               if (f3 > f2) {
                  f2 = f3;
                  list8 = list9;
               }
            }
         }

         ArrayList list10 = new ArrayList();

         for (ItemStack stack8 : (Iterable<ItemStack>)(list7)) {
            if (stack8 != null && stack8.getItem() instanceof ItemArmor) {
               Int2IntOpenHashMap int2IntOpenHashMap2 = (Int2IntOpenHashMap)(map.computeIfAbsent(stack8, obj2 -> new Int2IntOpenHashMap()));

               for (ItemStack stack9 : (Iterable<ItemStack>)(list8)) {
                  if (stack9 != null && stack9.getItem() instanceof ItemArmor) {
                     ItemArmor item3 = (ItemArmor)stack9.getItem();
                     ItemArmor item4 = (ItemArmor)stack8.getItem();
                     if (item3.armorType == item4.armorType
                        && (
                           item3.getArmorMaterial().getName().equals(item4.getArmorMaterial().getName())
                              || item3.armorType == 0
                                 && (item3.getArmorMaterial().equals(ItemArmor.ArmorMaterial.GOLD) || item3.getArmorMaterial().equals(ItemArmor.ArmorMaterial.IRON))
                                 && (item4.getArmorMaterial().equals(ItemArmor.ArmorMaterial.GOLD) || item4.getArmorMaterial().equals(ItemArmor.ArmorMaterial.IRON))
                        )) {
                        Int2IntOpenHashMap int2IntOpenHashMap3 = (Int2IntOpenHashMap)(map.computeIfAbsent(stack9, obj3 -> new Int2IntOpenHashMap()));
                        if (getArmorExtraScore(int2IntOpenHashMap2, stack8, flag, true) == getArmorExtraScore(int2IntOpenHashMap3, stack9, flag, true)) {
                           list10.add(stack9);
                        }
                     }
                  }
               }

               list8.removeAll(list10);
               list10.clear();
            }
         }

         return list8;
      }
   }

   public static ArrayList getSortedPickaxes(Container container, int n, ArrayList list) {
      ArrayList list2 = new ArrayList();

      for (int i = n; i < container.inventorySlots.size(); i++) {
         ItemStack stack = container.getSlot(i).getStack();
         if (stack != null) {
            if (stack.getItem() instanceof ItemPickaxe) {
               list2.add(new TuplePair(getItemScore(stack, true), i));
            }
         }
      }

      sortItemTuplesWithPreferredHotbar(list2, list);
      return list2;
   }

   public static List getSortedLavaBuckets(Container container, int n) {
      return getSortedBuckets(container, n, Items.lava_bucket);
   }

   public static List getSortedWaterBuckets(Container container, int n) {
      return getSortedBuckets(container, n, Items.water_bucket);
   }

   public static ArrayList getBestToolSlots(Container container, int n) {
      int n2 = -1;
      int n3 = -1;
      int n4 = -1;
      int n5 = -1;
      ItemStack stack = null;
      ItemStack stack2 = null;
      ItemStack stack3 = null;
      ItemStack stack4 = null;

      for (int i = n; i < container.inventorySlots.size(); i++) {
         ItemStack stack5 = container.getSlot(i).getStack();
         if (stack5 != null) {
            Item item;
            item = stack5.getItem();
            if (item instanceof ItemHoe) {
if (stack4 != null) {
if (getItemScore(stack5, true) > getItemScore(stack4, true)) {
stack4 = stack5;
               n5 = i;
}
} else {
stack4 = stack5;
               n5 = i;
}
}

            if (item instanceof ItemTool) {
               if (item instanceof ItemAxe) {
                  if (stack != null) {
                     if (!(getItemScore(stack5, true) > getItemScore(stack, true))) {
                        continue;
                     }
                  }

                  stack = stack5;
                  n2 = i;
               } else if (item instanceof ItemPickaxe) {
                  if (stack2 != null) {
                     if (!(getItemScore(stack5, true) > getItemScore(stack2, true))) {
                        continue;
                     }
                  }

                  stack2 = stack5;
                  n3 = i;
               } else if (item instanceof ItemSpade) {
                  if (stack3 != null) {
                     if (!(getItemScore(stack5, true) > getItemScore(stack3, true))) {
                        continue;
                     }
                  }

                  stack3 = stack5;
                  n4 = i;
               }
            }
         }
      }

      return new ArrayList<>(Arrays.asList(n2, n3, n4, n5));
   }

   public static int getContainerGuiHeight(Container container) {
      return 114 + (container.inventorySlots.size() - 36) / 9 * 18;
   }

   public static void setLastClickedSlot(int n) {
      lastClickedSlot = n;
   }

   private static float applyProtectionDamageReduction(float f, ArrayList list, HashMap map) {
      int n = 0;
      EnchantmentHelper.enchantmentModifierDamage.source = DamageSource.anvil;

      for (ItemStack stack : (Iterable<ItemStack>)(list)) {
         if (stack != null && stack.getItem() instanceof ItemArmor) {
            float f2 = 0.0F;
            int cachedEnchantmentLevel = getCachedEnchantmentLevel(Enchantment.protection.effectId, stack, (Int2IntOpenHashMap)(map.computeIfAbsent(stack, obj -> new Int2IntOpenHashMap())));
            if (cachedEnchantmentLevel > 0) {
               int damageModifier = EnchantmentHelper.enchantmentModifierDamage.getDamageModifier(Enchantment.protection, cachedEnchantmentLevel);
               f2 = (float)(damageModifier + 1 >> 1) + (float)((damageModifier >> 1) + 1) / 2.0F;
            }

            n = (int)((float)n + f2);
         }
      }

      if (n > 20) {
         n = 20;
      }

      if (n > 0) {
         f = (f * (float)(25 - n)) / 25.0F;
      }

      return f;
   }

   public static ArrayList getSortedWeapons(Container container, int n, ArrayList list) {
      ArrayList list2 = new ArrayList();

      for (int i = n; i < container.inventorySlots.size(); i++) {
         ItemStack stack = container.getSlot(i).getStack();
         if (stack != null) {
            Item item = stack.getItem();
            double itemScore = getItemScore(stack);
            if (item instanceof ItemTool) {
               list2.add(new TuplePair(itemScore, i));
            }
         }
      }

      for (int j = n; j < container.inventorySlots.size(); j++) {
         ItemStack stack2 = container.getSlot(j).getStack();
         if (stack2 != null) {
            Item item2 = stack2.getItem();
            double itemScore2 = getItemScore(stack2);
            if (item2 instanceof ItemSword) {
               list2.add(new TuplePair(itemScore2, j));
            }
         }
      }

      sortItemTuplesWithPreferredHotbar(list2, list);
      return list2;
   }

   public static void setLastCursorPos(double d, double d2) {
      lastCursorPos = new Vec2d(d, d2);
   }

   private static ArrayList generateArmorCombinations(ArrayList list) {
      ArrayList list2 = new ArrayList();

      for (ItemStack stack : (Iterable<ItemStack>)((ArrayList)list.get(0))) {
         for (ItemStack stack2 : (Iterable<ItemStack>)((ArrayList)list.get(1))) {
            for (ItemStack stack3 : (Iterable<ItemStack>)((ArrayList)list.get(2))) {
               for (ItemStack stack4 : (Iterable<ItemStack>)((ArrayList)list.get(3))) {
                  ArrayList list3 = new ArrayList();
                  list3.add(stack);
                  list3.add(stack2);
                  list3.add(stack3);
                  list3.add(stack4);
                  list2.add(list3);
               }
            }
         }
      }

      return list2;
   }

   public static List sortSlotsByMode(Container container, List list, String str, int n) {
      switch (str) {
         case "Basic":
            return sortSlotsSerpentine(list);
         case "Nearest":
            return sortSlotsByNearest(container, list, n);
         case "Random":
            return shuffleSlots(list);
         default:
            return new ArrayList();
      }
   }

   public static ArrayList getSortedAxes(Container container, int n, ArrayList list) {
      ArrayList list2 = new ArrayList();

      for (int i = n; i < container.inventorySlots.size(); i++) {
         ItemStack stack = container.getSlot(i).getStack();
         if (stack != null) {
            if (stack.getItem() instanceof ItemAxe) {
               list2.add(new TuplePair(getItemScore(stack, true), i));
            }
         }
      }

      sortItemTuplesWithPreferredHotbar(list2, list);
      return list2;
   }

   public static double getItemScore(ItemStack stack) {
      return getItemScore(stack, false);
   }

   private static int getProtectionModifier(HashMap map, ItemStack stack) {
      EnchantmentHelper.enchantmentModifierDamage.source = DamageSource.anvil;
      int cachedEnchantmentLevel = getCachedEnchantmentLevel(Enchantment.protection.effectId, stack, (Int2IntOpenHashMap)(map.computeIfAbsent(stack, obj -> new Int2IntOpenHashMap())));
      if (cachedEnchantmentLevel > 0) {
         int damageModifier = EnchantmentHelper.enchantmentModifierDamage.getDamageModifier(Enchantment.protection, cachedEnchantmentLevel);
         return (int)((float)(damageModifier + 1 >> 1) + (float)((damageModifier >> 1) + 1) / 2.0F);
      } else {
         return 0;
      }
   }

   public static ArrayList getSortedBows(Container container, int n, ArrayList list) {
      ArrayList list2 = new ArrayList();

      for (int i = n; i < container.inventorySlots.size(); i++) {
         ItemStack stack = container.getSlot(i).getStack();
         if (stack != null) {
            Item item = stack.getItem();
            double itemScore = getItemScore(stack);
            if (item instanceof ItemBow) {
               list2.add(new TuplePair(itemScore, i));
            }
         }
      }

      sortItemTuplesWithPreferredHotbar(list2, list);
      return list2;
   }

   public static ArrayList findBestArmorSetExcludingEquipped(Container container, int n, boolean flag) {
      return findBestArmorSet(container, n, container.inventorySlots.size(), flag, false);
   }

   public static ArrayList findBestArmorSetIncludingEquipped(Container container, int n, boolean flag) {
      return findBestArmorSet(container, n, container.inventorySlots.size(), flag, true);
   }

   public static double getItemScore(ItemStack stack, boolean flag) {
      if (stack == null) {
         return -1.0E7;
      } else {
         Item item = stack.getItem();
         double d = 0.0;
         if (item instanceof ItemHoe) {
            d += (double)((ItemHoe)item).getTheToolMaterial().getHarvestLevel();
            d += (double)((float)EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, stack) * 3.0F);
            d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, stack) / 2.0;
            d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.knockback.effectId, stack) / 2.0;
            d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack) / 2.0;
         }

         if (item instanceof ItemTool) {
            if (flag) {
               if (item instanceof ItemAxe) {
                  d += (double)item.getStrVsBlock(stack, new Block(Material.wood, MapColor.woodColor));
               } else if (item instanceof ItemPickaxe) {
                  d += (double)item.getStrVsBlock(stack, new Block(Material.rock, MapColor.stoneColor));
               } else if (item instanceof ItemSpade) {
                  d += (double)item.getStrVsBlock(stack, new Block(Material.sand, MapColor.sandColor));
               }

               d += (double)(d > 1.0 ? EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, stack) : 0);
               if (((ItemTool)item).getToolMaterial() == Item.ToolMaterial.GOLD) {
                  d -= 7.0;
               }

               d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack) / 33.0;
               d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.silkTouch.effectId, stack) / 33.0;
               d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, stack) / 33.0;
            } else {
               ItemTool itemTool2 = (ItemTool)item;
               d += (double)(itemTool2.getDamageVsEntity() + (float)EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, stack) * 1.25F)
                  * (!ClientManagers.moduleManager.killAura.blockMode.getSelectedValue().equals("None") && !ClientManagers.moduleManager.killAura.onlyRightClickBlock.getValue() ? 0.65 : 1.0);
               d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, stack) / 33.0;
               d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.knockback.effectId, stack) / 33.0;
               d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack) / 33.0;
            }
         } else if (item instanceof ItemSword) {
            d += (double)(((ItemSword)item).getAttackDamage() + (float)EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, stack) * 1.25F);
            d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, stack) / 33.0;
            d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.knockback.effectId, stack) / 33.0;
            d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack) / 33.0;
         } else if (item instanceof ItemBow) {
            d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack) / 5.0;
            d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack) / 33.0;
            d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) / 5.0;
            d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack) / 33.0;
         } else if (item instanceof ItemArmor) {
            d += (double)(
               (
                     (float)((ItemArmor)item).damageReduceAmount
                        + (float)(
                              6
                                 + EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.effectId, stack)
                                    * EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.effectId, stack)
                           )
                           / 3.0F
                  )
                  * 2.0F
            );
            d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.blastProtection.effectId, stack) / 50.0;
            d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.projectileProtection.effectId, stack) / 50.0;
            d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.fireProtection.effectId, stack) / 50.0;
            d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack) / 69.0;
            d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.thorns.effectId, stack) / 50.0;
            d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.featherFalling.effectId, stack) / 50.0;
            if (((ItemArmor)stack.getItem()).armorType == 0 && ((ItemArmor)stack.getItem()).getArmorMaterial() == ItemArmor.ArmorMaterial.GOLD) {
               d -= 0.01;
            }
         } else {
            if (item instanceof ItemEgg || item instanceof ItemSnowball) {
               return d + (double)stack.stackSize;
            }

            if (item instanceof ItemBlock) {
               if (BlockUtil.isValidScaffoldBlock(stack, true)) {
                  return d + (double)stack.stackSize;
               }
            }

            if (item instanceof ItemEnderPearl) {
               return d + (double)stack.stackSize;
            }

            if (item instanceof ItemFood) {
               if (item == Item.getByNameOrId("golden_apple")) {
                  d += (double)stack.stackSize;
               } else {
                  ItemFood itemFood = (ItemFood)item;
                  d += (double)((float)itemFood.getHealAmount(stack) + itemFood.getSaturationModifier(stack) * 10.0F);
               }

               return d;
            }

            if (item.equals(Items.mushroom_stew)) {
               return 1.0;
            }

            d -= 100000.0;
         }

         return d - (double)stack.getItemDamage() / 10000.0;
      }
   }

   public static ArrayList createSlotIndexRange(int n, int n2) {
      ArrayList list = new ArrayList();

      for (int i = n; i < n2; i++) {
         list.add(i);
      }

      return list;
   }

   public static ArrayList getSortedPickaxes(Container container, int n) {
      return getSortedPickaxes(container, n, (ArrayList)null);
   }

   public static ArrayList getSortedBlocks(Container container, int n) {
      return getSortedBlocks(container, n, container.inventorySlots.size());
   }

   public static String getItemCategoryName(ItemStack stack) {
      if (stack == null) {
         return "";
      } else {
         Item item = stack.getItem();
         if (item instanceof ItemSword) {
            return "Sword";
         } else if (item instanceof ItemBow) {
            return "Bow";
         } else if (item instanceof ItemFishingRod) {
            return "Rod";
         } else if (item instanceof ItemSnowball || item instanceof ItemEgg) {
            return "Throwables";
         } else if (item instanceof ItemAppleGold) {
            return "Gapple";
         } else if (item instanceof ItemEnderPearl) {
            return "EnderPearls";
         } else if (item instanceof ItemFood) {
            return "Food";
         } else {
            if (item instanceof ItemBlock) {
               if (BlockUtil.isValidScaffoldBlock(stack, true)) {
                  return "Blocks";
               }
            }

            return "";
         }
      }
   }

   public static ArrayList getSortedEnderPearls(Container container, int n) {
      ArrayList list = new ArrayList();

      for (int i = n; i < container.inventorySlots.size(); i++) {
         ItemStack stack = container.getSlot(i).getStack();
         if (stack != null) {
            if (stack.getItem() instanceof ItemEnderPearl) {
               list.add(new TuplePair(getItemScore(stack), i));
            }
         }
      }

      list.sort(Comparator.comparingDouble(obj -> (double)container.getSlot((Integer)((TuplePair)obj).getSecond()).getStack().stackSize));
      Collections.reverse(list);
      return list;
   }

   public static ArrayList getGoodPotionSlots(Container container, int n) {
      ArrayList list = new ArrayList();

      for (int i = n; i < container.inventorySlots.size(); i++) {
         ItemStack stack = container.getSlot(i).getStack();
         if (stack != null) {
            Item item = stack.getItem();
            if (item instanceof ItemPotion) {
               List effects = ((ItemPotion)item).getEffects(stack);
               if (effects != null && !effects.isEmpty()) {
                  PotionEffect potionEffect = (PotionEffect)effects.get(0);
                  if (!Potion.potionTypes[potionEffect.getPotionID()].isBadEffect()) {
                     list.add(i);
                  }
               }
            }
         }
      }

      return list;
   }

   public static boolean isCleanableItem(Item item) {
      if (item instanceof ItemPotion) {
         return true;
      } else if (!isNotSoupCraftIngredient(item)) {
         return false;
      } else {
         return !(item instanceof ItemArmor)
               && !(item instanceof ItemSword)
               && !(item instanceof ItemTool)
               && !(item instanceof ItemFishingRod)
               && !(item instanceof ItemBow)
            ? Arrays.stream(TRASH_ITEM_IDS).anyMatch(n -> item.equals(Item.getItemById(n)))
               || Arrays.stream(TRASH_ITEM_NAMES).anyMatch(str -> item.equals(Item.getByNameOrId(str)))
            : true;
      }
   }

   public static ArrayList getSortedBows(Container container, int n) {
      return getSortedBows(container, n, (ArrayList)null);
   }

   private static float getTotalArmorDefense(ArrayList list) {
      float f = 0.0F;

      for (ItemStack stack : (Iterable<ItemStack>)(list)) {
         if (stack != null && stack.getItem() instanceof ItemArmor) {
            float damageReduceAmount = (float)((ItemArmor)stack.getItem()).damageReduceAmount;
            f += damageReduceAmount;
         }
      }

      return f;
   }

   public static List sortSlotsSerpentine(List list) {
      Collections.sort(list);
      ArrayList list2 = new ArrayList();
      int n = 0;
      ArrayList list3 = new ArrayList();

      for (int i = 0; i < list.size(); i++) {
         int n2 = (Integer)list.get(i);
         int n3 = (int)((double)n2 / 9.0);
         if (i == 0) {
            n = n3;
         }

         if (n3 != n) {
            if (n % 2 == 0) {
               Collections.reverse(list3);
            }

            list2.addAll(list3);
            list3.clear();
         }

         list3.add(n2);
         n = n3;
         if (i == list.size() - 1) {
            if (n3 % 2 == 0) {
               Collections.reverse(list3);
            }

            list2.addAll(list3);
         }
      }

      return list2;
   }

   public static int getItemCategoryPriority(ItemStack stack, boolean flag) {
      if (stack == null) {
         return 0;
      } else {
         Item item = stack.getItem();
         if (!(item instanceof ItemSword) && (!flag || !(item instanceof ItemTool))) {
            if (item instanceof ItemAxe) {
               return 2;
            } else if (item instanceof ItemPickaxe) {
               return 3;
            } else if (item instanceof ItemSpade) {
               return 4;
            } else if (item instanceof ItemBow) {
               return 5;
            } else if (item instanceof ItemFishingRod) {
               return 6;
            } else if (item instanceof ItemSnowball || item instanceof ItemEgg) {
               return 7;
            } else if (item instanceof ItemArmor) {
               return ((ItemArmor)item).armorType + 8;
            } else if (item instanceof ItemFood) {
               return item.equals(Items.golden_apple) ? 12 : 13;
            } else if (item instanceof ItemEnderPearl) {
               return 14;
            } else {
               return item instanceof ItemShears ? 15 : 16;
            }
         } else {
            return 1;
         }
      }
   }

   public static ArrayList getSortedFishingRods(Container container, int n) {
      return getSortedFishingRods(container, n, (ArrayList)null);
   }

   public static List shuffleSlots(List list) {
      Collections.shuffle(list);
      return list;
   }

   public static ArrayList getSortedShears(Container container, int n, ArrayList list) {
      ArrayList list2 = new ArrayList();

      for (int i = n; i < container.inventorySlots.size(); i++) {
         ItemStack stack = container.getSlot(i).getStack();
         if (stack != null) {
            if (stack.getItem() instanceof ItemShears) {
               list2.add(new TuplePair(getItemScore(stack, true), i));
            }
         }
      }

      sortItemTuplesWithPreferredHotbar(list2, list);
      return list2;
   }

   public static ArrayList getSortedThrowables(Container container, int n) {
      ArrayList list = new ArrayList();

      for (int i = n; i < container.inventorySlots.size(); i++) {
         ItemStack stack = container.getSlot(i).getStack();
         if (stack != null) {
            Item item = stack.getItem();
            if (item instanceof ItemEgg || item instanceof ItemSnowball) {
               list.add(new TuplePair(getItemScore(stack), i));
            }
         }
      }

      list.sort(Comparator.comparingDouble(obj -> (double)container.getSlot((Integer)((TuplePair)obj).getSecond()).getStack().stackSize));
      Collections.reverse(list);
      return list;
   }

   public static boolean isCleanableItemStack(ItemStack stack) {
      return stack == null ? false : isCleanableItem(stack.getItem());
   }

   private static float applyProtectionCapReduction(float f, int n) {
      if (n > 20) {
         n = 20;
      }

      if (n > 0) {
         f = (f * (float)(25 - n)) / 25.0F;
      }

      return f;
   }

}
