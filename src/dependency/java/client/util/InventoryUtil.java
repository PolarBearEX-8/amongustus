package client.util;

import client.Client;
import client.ClientGlobals;
import client.module.Module;
import client.setting.ChestStealerSortType;
import client.setting.CrafterLeatherType;
import client.setting.GlobalOptionType;
import client.util.ArrayListHelperImpl;
import client.util.EnchantmentManager;
import client.util.GeometryMatcher;
import client.util.LvlManager;
import client.util.MinecraftAccessor;
import client.util.Pair;
import client.util.TimeHelper;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCache;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemGoldenAxe;
import net.minecraft.item.ItemGoldenBoots;
import net.minecraft.item.ItemGoldenHoe;
import net.minecraft.item.ItemGoldenPickaxe;
import net.minecraft.item.ItemGoldenShovel;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

public class InventoryUtil
implements MinecraftAccessor {
    public static final String[] stringArray;
    public static double[] valueArray;
    public static final ArrayList arrayList;
    public static final int[] countArray2;

    private static ArrayList resolveArrayList(ArrayList arrayList, ArrayList arrayList2) {
        TimeHelper.getStringArray();
        if (arrayList.isEmpty()) {
            return arrayList;
        }
        arrayList.sort(Comparator.comparingDouble(pairItem -> (Double)((Pair)pairItem).getSecond()));
        Collections.reverse(arrayList);
        if (arrayList2 == null || arrayList.isEmpty() || arrayList.size() < 2) {
            return arrayList;
        }
        double d = (Double)((Pair)arrayList.get(0)).getSecond();
        for (int i = 0; i < arrayList.size(); ++i) {
            Pair pair = (Pair)arrayList.get(i);
            if ((Double)pair.getSecond() != d || !arrayList2.contains((Integer)pair.getFirst() - 36)) continue;
            arrayList.set(i, new Pair((Double)pair.getSecond() + 1.0, pair.getFirst()));
        }
        arrayList.sort(Comparator.comparingDouble(pairItem -> (Double)((Pair)pairItem).getSecond()));
        Collections.reverse(arrayList);
        return arrayList;
    }

    public static int getIndex(Container container, int n, int n2, Item[] itemArray) {
        int n3 = 0;
        TimeHelper.getStringArray();
        for (int i = n; i < n2; ++i) {
            ItemStack itemStack = container.getSlot(i).getStack();
            if (!InventoryUtil.matchesItemStackByItemStack(itemStack, itemArray)) continue;
            n3 += itemStack.animationsToGo;
        }
        return n3;
    }

    private static double computeValue2(Container container, Pair pair) {
        return container.getSlot((int)((Integer)pair.getFirst()).intValue()).getStack().animationsToGo;
    }

    public static ArrayList getArrayList(Container container, long l, int n) {
        long l2 = 0x526310f55900L;
        long l3 = 0x569AD8EFFC4EL ^ l2 ^ 0x4810B8933D19L;
        return InventoryUtil.getArrayList(container, l3, n, null);
    }

    private static boolean matchesItemStackByItemStack(ItemStack itemStack, Item[] itemArray) {
        TimeHelper.getStringArray();
        if (itemStack == null) {
            return false;
        }
        if (itemArray == null || itemArray.length == 0) {
            return true;
        }
        Item[] itemArray2 = itemArray;
        int n = itemArray.length;
        for (int i = 0; i < n; ++i) {
            if (!itemArray2[i].equals(itemStack.getItem())) continue;
            return true;
        }
        return false;
    }

    public static ArrayList getArrayList(Container container, int n, int n2) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        TimeHelper.getStringArray();
        for (int i = n; i < n2; ++i) {
            ItemStack itemStack = container.getSlot(i).getStack();
            if (itemStack == null || itemStack.animationsToGo <= 0) continue;
            arrayList.add(i);
        }
        return arrayList;
    }

    public static ArrayList getArrayList(Container container, int n, Item[] itemArray) {
        int n2 = container.itemList2.size();
        int n3 = n;
        return InventoryUtil.resolveArrayList(container, n3, n2, itemArray);
    }

    public static int computeIfAbsent(int n, ItemStack itemStack, Int2IntOpenHashMap int2IntOpenHashMap) {
        return int2IntOpenHashMap.computeIfAbsent(n, lambdaArgA -> InventoryUtil.computeCount(itemStack, n, int2IntOpenHashMap, lambdaArgA));
    }

    public static ArrayList getArrayList2(ArrayList arrayList, boolean bl) {
        ArrayList arrayList2 = new ArrayList();
        TimeHelper.getStringArray();
        arrayList2.add(new ArrayList());
        arrayList2.add(new ArrayList());
        arrayList2.add(new ArrayList());
        arrayList2.add(new ArrayList());
        for (ItemStack itemStack : (Iterable<ItemStack>)(Iterable)(arrayList)) {
            Item item;
            if (itemStack == null || !((item = itemStack.getItem()) instanceof ItemGoldenBoots)) continue;
            ItemGoldenBoots itemGoldenBoots = (ItemGoldenBoots)item;
            ((ArrayList)arrayList2.get(itemGoldenBoots.indexOffset3)).add(itemStack);
        }
        for (ArrayList arrayList3 : (Iterable<ArrayList>)(Iterable)(arrayList2)) {
            if (!arrayList3.isEmpty()) continue;
            arrayList3.add(null);
        }
        float f = Float.MAX_VALUE;
        ArrayList arrayList4 = new ArrayList();
        HashMap<ItemStack, Int2IntOpenHashMap> hashMap = new HashMap<ItemStack, Int2IntOpenHashMap>();
        for (ItemStack itemStack : (Iterable<ItemStack>)(Iterable)((ArrayList)arrayList2.get(0))) {
            int n = InventoryUtil.computeCount(hashMap, itemStack);
            for (ItemStack itemStack2 : (Iterable<ItemStack>)(Iterable)((ArrayList)arrayList2.get(1))) {
                int n2 = n + (n > 20 ? 0 : InventoryUtil.computeCount(hashMap, itemStack2));
                for (ItemStack itemStack3 : (Iterable<ItemStack>)(Iterable)((ArrayList)arrayList2.get(2))) {
                    int n3 = n2 + (n2 > 20 ? 0 : InventoryUtil.computeCount(hashMap, itemStack3));
                    for (ItemStack itemStack4 : (Iterable<ItemStack>)(Iterable)((ArrayList)arrayList2.get(3))) {
                        int n4 = n3 + (n3 > 20 ? 0 : InventoryUtil.computeCount(hashMap, itemStack4));
                        float f2 = InventoryUtil.computeFactor(InventoryUtil.computeFactor(100.0f, itemStack, itemStack2, itemStack3, itemStack4), n4);
                        if (f2 < f) {
                            f = f2;
                            arrayList4.clear();
                            ArrayList<ItemStack> arrayList5 = new ArrayList<ItemStack>();
                            arrayList5.add(itemStack);
                            arrayList5.add(itemStack2);
                            arrayList5.add(itemStack3);
                            arrayList5.add(itemStack4);
                            arrayList4.add(arrayList5);
                            continue;
                        }
                        if (f2 != f) continue;
                        ArrayList<ItemStack> arrayList6 = new ArrayList<ItemStack>();
                        arrayList6.add(itemStack);
                        arrayList6.add(itemStack2);
                        arrayList6.add(itemStack3);
                        arrayList6.add(itemStack4);
                        arrayList4.add(arrayList6);
                    }
                }
            }
        }
        if (arrayList4.isEmpty()) {
            return new ArrayList();
        }
        ArrayList<ItemStack> arrayList7 = Lists.newArrayList(MinecraftAccessor.minecraft.thePlayer.container.getSlot(8).getStack(), MinecraftAccessor.minecraft.thePlayer.container.getSlot(7).getStack(), MinecraftAccessor.minecraft.thePlayer.container.getSlot(6).getStack(), MinecraftAccessor.minecraft.thePlayer.container.getSlot(5).getStack());
        ArrayList arrayList8 = (ArrayList)arrayList4.get(0);
        if (arrayList4.size() > 1) {
            float f3 = -2000000.0f;
            for (ArrayList arrayList9 : (Iterable<ArrayList>)(Iterable)(arrayList4)) {
                float f4 = 0.0f;
                for (ItemStack itemStack : (Iterable<ItemStack>)(Iterable)(arrayList9)) {
                    boolean bl2 = false;
                    if (bl && itemStack != null && itemStack.getItem() instanceof ItemGoldenBoots) {
                        ItemGoldenBoots itemGoldenBoots = (ItemGoldenBoots)itemStack.getItem();
                        if (arrayList7.get(itemGoldenBoots.indexOffset3) != null) {
                            bl2 = true;
                        }
                    }
                    f4 += InventoryUtil.computeFactor(hashMap.computeIfAbsent(itemStack, InventoryUtil::resolveInt2IntOpenHashMap7), itemStack, bl2, false);
                }
                if (!(f4 > f3)) continue;
                f3 = f4;
                arrayList8 = arrayList9;
            }
        }
        ArrayList<ItemStack> arrayList10 = new ArrayList<ItemStack>();
        for (ItemStack itemStack : (Iterable<ItemStack>)(Iterable)(arrayList7)) {
            if (itemStack == null || !(itemStack.getItem() instanceof ItemGoldenBoots)) continue;
            Int2IntOpenHashMap int2IntOpenHashMap = hashMap.computeIfAbsent(itemStack, InventoryUtil::resolveInt2IntOpenHashMap2);
            for (ItemStack itemStack5 : (Iterable<ItemStack>)(Iterable)(arrayList8)) {
                if (itemStack5 == null || !(itemStack5.getItem() instanceof ItemGoldenBoots)) continue;
                ItemGoldenBoots itemGoldenBoots = (ItemGoldenBoots)itemStack5.getItem();
                ItemGoldenBoots itemGoldenBoots2 = (ItemGoldenBoots)itemStack.getItem();
                if (itemGoldenBoots.indexOffset3 != itemGoldenBoots2.indexOffset3 || !itemGoldenBoots.getCrafterLeatherType().getLabelText().equals(itemGoldenBoots2.getCrafterLeatherType().getLabelText()) && (itemGoldenBoots.indexOffset3 != 0 || !itemGoldenBoots.getCrafterLeatherType().equals((Object)CrafterLeatherType.GOLD) && !itemGoldenBoots.getCrafterLeatherType().equals((Object)CrafterLeatherType.IRON) || !itemGoldenBoots2.getCrafterLeatherType().equals((Object)CrafterLeatherType.GOLD) && !itemGoldenBoots2.getCrafterLeatherType().equals((Object)CrafterLeatherType.IRON))) continue;
                Int2IntOpenHashMap int2IntOpenHashMap2 = hashMap.computeIfAbsent(itemStack5, InventoryUtil::resolveInt2IntOpenHashMap6);
                if (InventoryUtil.computeFactor(int2IntOpenHashMap, itemStack, bl, true) != InventoryUtil.computeFactor(int2IntOpenHashMap2, itemStack5, bl, true)) continue;
                arrayList10.add(itemStack5);
            }
            arrayList8.removeAll(arrayList10);
            arrayList10.clear();
        }
        return arrayList8;
    }

    public static ArrayList getArrayList(Container container, int n, ArrayList arrayList) {
        ArrayList<Pair> arrayList2 = new ArrayList<Pair>();
        TimeHelper.getStringArray();
        for (int i = n; i < container.itemList2.size(); ++i) {
            ItemStack itemStack = container.getSlot(i).getStack();
            if (itemStack == null) continue;
            Item item = itemStack.getItem();
            double d = InventoryUtil.getCoordinate(itemStack);
            if (!(item instanceof ItemFishingRod)) continue;
            arrayList2.add(new Pair(d, i));
        }
        InventoryUtil.resolveArrayList(arrayList2, arrayList);
        return arrayList2;
    }

    public static ArrayList getArrayList2(Container container, int n) {
        ArrayList<Pair> arrayList = new ArrayList<Pair>();
        TimeHelper.getStringArray();
        for (int i = n; i < container.itemList2.size(); ++i) {
            ItemStack itemStack = container.getSlot(i).getStack();
            if (itemStack == null || !(itemStack.getItem() instanceof ItemEnderPearl)) continue;
            arrayList.add(new Pair(InventoryUtil.getCoordinate(itemStack), i));
        }
        arrayList.sort(Comparator.comparingDouble(lambdaArgA -> InventoryUtil.computeValue2(container, lambdaArgA)));
        Collections.reverse(arrayList);
        return arrayList;
    }

    public static ArrayList resolveArrayList(Container container, int n, int n2) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        TimeHelper.getStringArray();
        for (int i = n; i < n2; ++i) {
            if (container.getSlot(i).getStack() != null) continue;
            arrayList.add(i);
        }
        return arrayList;
    }

    public static ArrayList getSortedContainerList(Container container, int n) {
        ArrayList<Pair> arrayList = new ArrayList<Pair>();
        TimeHelper.getStringArray();
        for (int i = n; i < container.itemList2.size(); ++i) {
            Item item;
            ItemStack itemStack = container.getSlot(i).getStack();
            if (itemStack == null || !((item = itemStack.getItem()) instanceof ItemEgg) && !(item instanceof ItemSnowball)) continue;
            arrayList.add(new Pair(InventoryUtil.getCoordinate(itemStack), i));
        }
        arrayList.sort(Comparator.comparingDouble(lambdaArgA -> InventoryUtil.computeValue3(container, lambdaArgA)));
        Collections.reverse(arrayList);
        return arrayList;
    }

    private static boolean matchesItem(Item item, int n) {
        return item.equals(Item.processAction(n));
    }

    private static Int2IntOpenHashMap resolveInt2IntOpenHashMapByItemStack(ItemStack itemStack) {
        return new Int2IntOpenHashMap();
    }

    private static double computeValue(Container container, Pair pair) {
        return container.getSlot((int)((Integer)pair.getFirst()).intValue()).getStack().animationsToGo;
    }

    public static boolean matchesItemStack(ItemStack itemStack) {
        TimeHelper.getStringArray();
        if (itemStack == null) {
            return false;
        }
        return InventoryUtil.checkItem3(itemStack.getItem());
    }

    public static ArrayList getArrayList(Container container, int n) {
        int n2 = container.itemList2.size();
        int n3 = n;
        return InventoryUtil.resolveArrayListByContainer(container, n3, n2);
    }

    public static ArrayList resolveArrayList(Container container, int n, Item item) {
        Item[] itemArray = new Item[]{item};
        int n2 = container.itemList2.size();
        int n3 = n;
        Container container2 = container;
        return InventoryUtil.resolveArrayList(container2, n3, n2, itemArray);
    }

    private static float computeFactor(ArrayList arrayList) {
        float f = 0.0f;
        TimeHelper.getStringArray();
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            ItemStack itemStack = (ItemStack)iterator.next();
            if (itemStack == null || !(itemStack.getItem() instanceof ItemGoldenBoots)) continue;
            float f2 = ((ItemGoldenBoots)itemStack.getItem()).indexOffset4;
            f += f2;
        }
        return f;
    }

    private static Int2IntOpenHashMap resolveInt2IntOpenHashMap(ItemStack itemStack) {
        return new Int2IntOpenHashMap();
    }

    private static ArrayList resolveArrayList(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        TimeHelper.getStringArray();
        Iterator iterator = ((ArrayList)arrayList.get(0)).iterator();
        while (iterator.hasNext()) {
            ItemStack itemStack = (ItemStack)iterator.next();
            for (ItemStack itemStack2 : (Iterable<ItemStack>)(Iterable)((ArrayList)arrayList.get(1))) {
                for (ItemStack itemStack3 : (Iterable<ItemStack>)(Iterable)((ArrayList)arrayList.get(2))) {
                    for (ItemStack itemStack4 : (Iterable<ItemStack>)(Iterable)((ArrayList)arrayList.get(3))) {
                        ArrayList<ItemStack> arrayList3 = new ArrayList<ItemStack>();
                        arrayList3.add(itemStack);
                        arrayList3.add(itemStack2);
                        arrayList3.add(itemStack3);
                        arrayList3.add(itemStack4);
                        arrayList2.add(arrayList3);
                    }
                }
            }
        }
        return arrayList2;
    }

    public static ArrayList resolveArrayList(Container container, ArrayList arrayList, int n) {
        TimeHelper.getStringArray();
        if (arrayList.isEmpty()) {
            return arrayList;
        }
        ScaledResolution scaledResolution = new ScaledResolution(MinecraftAccessor.minecraft);
        int n2 = (scaledResolution.getScaledWidth() - 176) / 2;
        int n3 = (scaledResolution.getScaledHeight() - n) / 2;
        ArrayList<GeometryMatcher> arrayList2 = new ArrayList<GeometryMatcher>();
        HashMap<GeometryMatcher, Integer> hashMap = new HashMap<GeometryMatcher, Integer>();
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            int n4 = (Integer)iterator.next();
            Slot slot = (Slot)container.itemList2.get(n4);
            GeometryMatcher geometryMatcher = new GeometryMatcher(slot.indexOffset + 2 + n2, slot.indexOffset2 + 2 + n3);
            hashMap.put(geometryMatcher, n4);
            arrayList2.add(geometryMatcher);
        }
        if (arrayList2.size() > 1) {
            GeometryMatcher geometryMatcher = new GeometryMatcher(valueArray[0], valueArray[1]);
            GeometryMatcher geometryMatcher2 = null;
            double d = Double.MAX_VALUE;
            for (GeometryMatcher geometryMatcher3 : (Iterable<GeometryMatcher>)(Iterable)(arrayList2)) {
                double d2 = geometryMatcher3.computeValue(geometryMatcher);
                if (!(d2 < d)) continue;
                geometryMatcher2 = geometryMatcher3;
                d = d2;
            }
            int n5 = arrayList2.indexOf(geometryMatcher2);
            ArrayList arrayList3 = new ArrayList(arrayList2.subList(0, n5));
            ArrayList arrayList4 = new ArrayList(arrayList2.subList(n5, arrayList2.size()));
            arrayList2.clear();
            arrayList2.addAll(arrayList4);
            arrayList2.addAll(arrayList3);
        }
        ArrayList arrayList5 = ArrayListHelperImpl.resolveArrayList(arrayList2);
        ArrayList arrayList6 = new ArrayList();
        for (GeometryMatcher geometryMatcher : (Iterable<GeometryMatcher>)(Iterable)(arrayList5)) {
            arrayList6.add(hashMap.get(geometryMatcher));
        }
        return arrayList6;
    }

    private static Int2IntOpenHashMap resolveInt2IntOpenHashMap3(ItemStack itemStack) {
        return new Int2IntOpenHashMap();
    }

    public static ArrayList shuffle(ArrayList arrayList) {
        Collections.shuffle(arrayList);
        return arrayList;
    }

    public static int getIndex(ItemStack itemStack, boolean bl) {
        TimeHelper.getStringArray();
        if (itemStack == null) {
            return 0;
        }
        Item item = itemStack.getItem();
        if (item instanceof ItemSword || bl && item instanceof ItemTool) {
            return 1;
        }
        if (item instanceof ItemGoldenAxe) {
            return 16;
        }
        if (item instanceof ItemGoldenPickaxe) {
            return 3;
        }
        if (item instanceof ItemGoldenShovel) {
            return 4;
        }
        if (item instanceof ItemBow) {
            return 5;
        }
        if (item instanceof ItemFishingRod) {
            return 6;
        }
        if (item instanceof ItemSnowball || item instanceof ItemEgg) {
            return 7;
        }
        if (item instanceof ItemGoldenBoots) {
            return ((ItemGoldenBoots)item).indexOffset3 + 8;
        }
        if (item instanceof ItemFood) {
            if (item.equals(Items.goldenApple)) {
                return 12;
            }
            return 13;
        }
        if (item instanceof ItemEnderPearl) {
            return 15;
        }
        return 14;
    }

    public static ArrayList getArrayList3(Container container, int n) {
        long l = 0x7720cf908cd2L;
        long l2 = 0x569AD8EFFC4EL ^ l ^ 0x16543FD6B343L;
        int n2 = (int)(l2 >>> 48);
        int n3 = (int)(l2 << 16 >>> 32);
        int n4 = (int)(l2 << 48 >>> 48);
        return InventoryUtil.getArrayList((char)n2, container, n, n3, n4, null);
    }

    private static float computeFactor(float f, int n) {
        float f2 = f;
        int n2 = n;
        TimeHelper.getStringArray();
        if (n2 > 20) {
            n2 = 20;
        }
        if (n2 > 0) {
            int n3 = 25 - n2;
            f2 = f2 * (float)n3 / 25.0f;
        }
        return f2;
    }

    public static ArrayList getArrayList(Container container, int n, boolean bl) {
        int n2 = container.itemList2.size();
        int n3 = n;
        return InventoryUtil.resolveArrayList(container, n3, n2, bl, false);
    }

    public static ArrayList getArrayList(Container container, long l, int n, ArrayList arrayList) {
        ArrayList<Pair> arrayList2 = new ArrayList<Pair>();
        TimeHelper.getStringArray();
        for (int i = n; i < container.itemList2.size(); ++i) {
            ItemStack itemStack = container.getSlot(i).getStack();
            if (itemStack == null) continue;
            Item item = itemStack.getItem();
            double d = InventoryUtil.getCoordinate(itemStack);
            if (!(item instanceof ItemTool)) continue;
            arrayList2.add(new Pair(d, i));
        }
        for (int i = n; i < container.itemList2.size(); ++i) {
            ItemStack itemStack = container.getSlot(i).getStack();
            if (itemStack == null) continue;
            Item item = itemStack.getItem();
            double d = InventoryUtil.getCoordinate(itemStack);
            if (!(item instanceof ItemSword)) continue;
            arrayList2.add(new Pair(d, i));
        }
        InventoryUtil.resolveArrayList(arrayList2, arrayList);
        return arrayList2;
    }

    public static double getCoordinate(ItemStack itemStack, boolean bl) {
        double d;
        TimeHelper.getStringArray();
        if (itemStack == null) {
            return -1.0E7;
        }
        Item item = itemStack.getItem();
        double d2 = 0.0;
        if (item instanceof ItemGoldenHoe) {
            d2 = d2 + (double)((ItemGoldenHoe)item).getGlobalOptionType().getCount() + (double)((float)LvlManager.getIndex(EnchantmentManager.enchantmentManager2.indexOffset, itemStack) * 3.0f) + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager3.indexOffset, itemStack) / 2.0 + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager.indexOffset, itemStack) / 2.0 + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager1.indexOffset, itemStack) / 2.0;
        }
        if (item instanceof ItemTool) {
            if (bl) {
                if (item instanceof ItemGoldenAxe) {
                    d2 += (double)item.processAction(itemStack, new Block(Material.material, MapColor.mapColor18));
                } else if (item instanceof ItemGoldenPickaxe) {
                    d2 += (double)item.processAction(itemStack, new Block(Material.material4, MapColor.mapColor10));
                } else if (item instanceof ItemGoldenShovel) {
                    d2 += (double)item.processAction(itemStack, new Block(Material.material28, MapColor.mapColor20));
                }
                double d3 = d2 + (double)(d2 > 1.0 ? LvlManager.getIndex(EnchantmentManager.enchantmentManager5.indexOffset, itemStack) : 0);
                if (((ItemTool)item).getGlobalOptionType() == GlobalOptionType.GOLD) {
                    d3 -= 7.0;
                }
                d = d3 + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager1.indexOffset, itemStack) / 33.0 + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager4.indexOffset, itemStack) / 33.0 + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager2.indexOffset, itemStack) / 33.0;
            } else {
                ItemTool itemTool = (ItemTool)item;
                d = d2 + (double)(itemTool.getScaleFactor() + (float)LvlManager.getIndex(EnchantmentManager.enchantmentManager2.indexOffset, itemStack) * 1.25f) * (ClientGlobals.moduleManager.killAura.settingNone3.getSelected().equals("None") || ClientGlobals.moduleManager.killAura.settingOnlyRightClickBlock.isEnabled() ? 1.0 : 0.65) + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager3.indexOffset, itemStack) / 33.0 + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager.indexOffset, itemStack) / 33.0 + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager1.indexOffset, itemStack) / 33.0;
            }
        } else if (item instanceof ItemSword) {
            d = d2 + (double)(((ItemSword)item).getScaleFactor() + (float)LvlManager.getIndex(EnchantmentManager.enchantmentManager2.indexOffset, itemStack) * 1.25f) + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager3.indexOffset, itemStack) / 33.0 + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager.indexOffset, itemStack) / 33.0 + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager1.indexOffset, itemStack) / 33.0;
        } else if (item instanceof ItemBow) {
            d = d2 + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager10.indexOffset, itemStack) / 5.0 + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager8.indexOffset, itemStack) / 33.0 + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager24.indexOffset, itemStack) / 5.0 + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager17.indexOffset, itemStack) / 33.0;
        } else if (item instanceof ItemGoldenBoots) {
            d = d2 + (double)(((float)((ItemGoldenBoots)item).indexOffset4 + (float)(6 + LvlManager.getIndex(EnchantmentManager.enchantmentManager6.indexOffset, itemStack) * LvlManager.getIndex(EnchantmentManager.enchantmentManager6.indexOffset, itemStack)) / 3.0f) * 2.0f) + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager18.indexOffset, itemStack) / 50.0 + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager16.indexOffset, itemStack) / 50.0 + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager14.indexOffset, itemStack) / 50.0 + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager1.indexOffset, itemStack) / 69.0 + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager12.indexOffset, itemStack) / 50.0 + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager20.indexOffset, itemStack) / 50.0;
            if (((ItemGoldenBoots)itemStack.getItem()).indexOffset3 == 0 && ((ItemGoldenBoots)itemStack.getItem()).getCrafterLeatherType() == CrafterLeatherType.GOLD) {
                d -= 0.01;
            }
        } else {
            if (item instanceof ItemEgg || item instanceof ItemSnowball || item instanceof ItemBlock && BlockCache.checkItemStack2(itemStack, true) || item instanceof ItemEnderPearl) {
                return d2 + (double)itemStack.animationsToGo;
            }
            if (item instanceof ItemFood) {
                double d4;
                if (item == Item.getItem("golden_apple")) {
                    d4 = d2 + (double)itemStack.animationsToGo;
                } else {
                    ItemFood itemFood = (ItemFood)item;
                    d4 = d2 + (double)((float)itemFood.computeCount(itemStack) + itemFood.computeFactor(itemStack) * 10.0f);
                }
                return d4;
            }
            if (item.equals(Items.mushroomStew)) {
                return 1.0;
            }
            d = d2 - 100000.0;
        }
        return d - (double)itemStack.getIndexOffset2() / 10000.0;
    }

    public static ArrayList getArrayList(long l, Container container, int n, short shortValue, boolean bl) {
        int n2 = container.itemList2.size();
        int n3 = n;
        return InventoryUtil.resolveArrayList(container, n3, n2, bl, true);
    }

    public static ArrayList getArrayList(Container container, int n, int n2, byte by, int n3) {
        int n4 = container.itemList2.size();
        int n5 = n3;
        return InventoryUtil.resolveArrayList(container, n5, n4);
    }

    public static ArrayList resolveArrayList(Container container, int n, int n2, Item[] itemArray) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        TimeHelper.getStringArray();
        for (int i = n; i < n2; ++i) {
            if (!InventoryUtil.matchesItemStackByItemStack(container.getSlot(i).getStack(), itemArray)) continue;
            arrayList.add(i);
        }
        return arrayList;
    }

    public static void updateState() {
    }

    private static Int2IntOpenHashMap resolveInt2IntOpenHashMap6(ItemStack itemStack) {
        return new Int2IntOpenHashMap();
    }

    public static double[] getCoordOffsetArray() {
        return valueArray;
    }

    private static float computeFactor(float f, ArrayList arrayList) {
        float f2 = f;
        float f3 = 25.0f - InventoryUtil.computeFactor(arrayList);
        f2 = f2 * f3 / 25.0f;
        return f2;
    }

    private static Int2IntOpenHashMap resolveInt2IntOpenHashMap7(ItemStack itemStack) {
        return new Int2IntOpenHashMap();
    }

    private static double computeValue3(Container container, Pair pair) {
        return container.getSlot((int)((Integer)pair.getFirst()).intValue()).getStack().animationsToGo;
    }

    public static ArrayList getArrayList(int n, short shortValue, int n2, Container container, int n3) {
        ArrayList<Pair> arrayList = new ArrayList<Pair>();
        TimeHelper.getStringArray();
        for (int i = n3; i < container.itemList2.size(); ++i) {
            ItemStack itemStack = container.getSlot(i).getStack();
            if (itemStack == null || !itemStack.getItem().equals(Items.mushroomStew)) continue;
            arrayList.add(new Pair(InventoryUtil.getCoordinate(itemStack), i));
        }
        arrayList.sort(Comparator.comparingDouble(lambdaArgA -> InventoryUtil.computeValueByContainer(container, lambdaArgA)));
        Collections.reverse(arrayList);
        return arrayList;
    }

    public static boolean matchesItem(Item item) {
        TimeHelper.getStringArray();
        return Client.getClient().getModuleManager() == null || !((Module)ClientGlobals.moduleManager.autoSoup).isToggled() && !((Module)ClientGlobals.moduleManager.refill).isToggled() && !ChestStealerSortType.CRAFT.isSelected() || !item.equals(Item.processAction(281)) && !item.equals(Item.processAction(39)) && !item.equals(Item.processAction(40));
    }

    public static ArrayList resolveArrayListByContainer(Container container, int n, int n2) {
        ArrayList<Pair> arrayList = new ArrayList<Pair>();
        TimeHelper.getStringArray();
        for (int i = n; i < n2; ++i) {
            ItemStack itemStack = container.getSlot(i).getStack();
            if (itemStack == null || !(itemStack.getItem() instanceof ItemBlock) || !BlockCache.checkItemStack2(itemStack, true)) continue;
            arrayList.add(new Pair(InventoryUtil.getCoordinate(itemStack), i));
        }
        arrayList.sort(Comparator.comparingDouble(lambdaArgA -> InventoryUtil.computeValue(container, lambdaArgA)));
        Collections.reverse(arrayList);
        return arrayList;
    }

    public static ArrayList getArrayList(char c, Container container, int n, int n2, int n3, ArrayList arrayList) {
        ArrayList<Pair> arrayList2 = new ArrayList<Pair>();
        TimeHelper.getStringArray();
        for (int i = n; i < container.itemList2.size(); ++i) {
            ItemStack itemStack = container.getSlot(i).getStack();
            if (itemStack == null) continue;
            Item item = itemStack.getItem();
            double d = InventoryUtil.getCoordinate(itemStack);
            if (!(item instanceof ItemBow)) continue;
            arrayList2.add(new Pair(d, i));
        }
        InventoryUtil.resolveArrayList(arrayList2, arrayList);
        return arrayList2;
    }

    private static float computeFactor(float f, ArrayList arrayList, HashMap hashMap) {
        float f2 = f;
        int n = 0;
        LvlManager.storageEspRenderer.damageSource = DamageSource.damageSource14;
        TimeHelper.getStringArray();
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            ItemStack itemStack = (ItemStack)iterator.next();
            if (itemStack == null || !(itemStack.getItem() instanceof ItemGoldenBoots)) continue;
            float f3 = 0.0f;
            Int2IntOpenHashMap int2IntOpenHashMap = (Int2IntOpenHashMap)hashMap.computeIfAbsent(itemStack, stackItem -> InventoryUtil.resolveInt2IntOpenHashMap((ItemStack)stackItem));
            int n2 = InventoryUtil.computeIfAbsent(EnchantmentManager.enchantmentManager6.indexOffset, itemStack, int2IntOpenHashMap);
            if (n2 > 0) {
                int n3 = LvlManager.storageEspRenderer.getIndex(EnchantmentManager.enchantmentManager6, n2);
                f3 = (float)(n3 + 1 >> 1) + (float)((n3 >> 1) + 1) / 2.0f;
            }
            n = (int)((float)n + f3);
        }
        if (n > 20) {
            n = 20;
        }
        if (n > 0) {
            int n4 = 25 - n;
            f2 = f2 * (float)n4 / 25.0f;
        }
        return f2;
    }

    public static ArrayList resolveArrayList(Container container, int n, int n2, Item item) {
        Item[] itemArray = new Item[]{item};
        int n3 = n2;
        int n4 = n;
        Container container2 = container;
        return InventoryUtil.resolveArrayList(container2, n4, n3, itemArray);
    }

    public static int computeCountByContainer(Container container, int n, int n2, Item[] itemArray) {
        TimeHelper.getStringArray();
        for (int i = n; i < n2; ++i) {
            if (!InventoryUtil.matchesItemStackByItemStack(container.getSlot(i).getStack(), itemArray)) continue;
            return i;
        }
        return -1;
    }

    private static float computeFactor(float f, ItemStack itemStack, ItemStack itemStack2, ItemStack itemStack3, ItemStack itemStack4) {
        float f2 = f;
        float f3 = 25.0f - InventoryUtil.computeFactor(itemStack, itemStack2, itemStack3, itemStack4);
        f2 = f2 * f3 / 25.0f;
        return f2;
    }

    public static ArrayList getArrayList(Container container, ArrayList arrayList, String string, int n) {
        String string2 = string;
        TimeHelper.getStringArray();
        int n2 = -1;
        switch (string.hashCode()) {
            case 63955982: {
                if (!string2.equals("Basic")) break;
                n2 = 0;
                break;
            }
            case -804534210: {
                if (!string2.equals("Nearest")) break;
                n2 = 1;
                break;
            }
            case -1854418717: {
                if (!string2.equals("Random")) break;
                n2 = 2;
            }
        }
        switch (n2) {
            case 0: {
                return InventoryUtil.getArrayList6(arrayList);
            }
            case 1: {
                return InventoryUtil.resolveArrayList(container, arrayList, n);
            }
            case 2: {
                return InventoryUtil.shuffle(arrayList);
            }
        }
        return new ArrayList();
    }

    public static ArrayList getArrayList(short shortValue, char c, int n, Container container, int n2) {
        ArrayList<Pair> arrayList = new ArrayList<Pair>();
        TimeHelper.getStringArray();
        for (int i = n2; i < container.itemList2.size(); ++i) {
            Item item;
            ItemStack itemStack = container.getSlot(i).getStack();
            if (itemStack == null || !((item = itemStack.getItem()) instanceof ItemFood) || item == Item.getItem("golden_apple") || item.equals(Items.mushroomStew)) continue;
            double d = InventoryUtil.getCoordinate(itemStack);
            arrayList.add(new Pair(d, i));
        }
        arrayList.sort(Comparator.comparingDouble(pairItem -> (Double)((Pair)pairItem).getSecond()));
        Collections.reverse(arrayList);
        return arrayList;
    }

    private static Int2IntOpenHashMap resolveInt2IntOpenHashMap4(ItemStack itemStack) {
        return new Int2IntOpenHashMap();
    }

    public static ArrayList getArrayList5(int n, int n2) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        TimeHelper.getStringArray();
        for (int i = n; i < n2; ++i) {
            arrayList.add(i);
        }
        return arrayList;
    }

    private static Int2IntOpenHashMap resolveInt2IntOpenHashMap5(ItemStack itemStack) {
        return new Int2IntOpenHashMap();
    }

    public static ArrayList resolveArrayList(Container container, int n, int n2, boolean bl, boolean bl2) {
        ArrayList arrayList = new ArrayList();
        TimeHelper.getStringArray();
        arrayList.add(new ArrayList());
        arrayList.add(new ArrayList());
        arrayList.add(new ArrayList());
        arrayList.add(new ArrayList());
        for (int i = n; i < n2; ++i) {
            Item item;
            ItemStack itemStack = container.getSlot(i).getStack();
            if (itemStack == null || !((item = itemStack.getItem()) instanceof ItemGoldenBoots)) continue;
            ItemGoldenBoots itemGoldenBoots = (ItemGoldenBoots)item;
            ((ArrayList)arrayList.get(itemGoldenBoots.indexOffset3)).add(itemStack);
        }
        if (bl2) {
            for (int i = 5; i < Math.min(n, 9); ++i) {
                Item item;
                ItemStack itemStack = container.getSlot(i).getStack();
                if (itemStack == null || !((item = itemStack.getItem()) instanceof ItemGoldenBoots)) continue;
                ItemGoldenBoots itemGoldenBoots = (ItemGoldenBoots)item;
                ((ArrayList)arrayList.get(itemGoldenBoots.indexOffset3)).add(itemStack);
            }
        }
        for (ArrayList arrayList2 : (Iterable<ArrayList>)(Iterable)(arrayList)) {
            if (!arrayList2.isEmpty()) continue;
            arrayList2.add(null);
        }
        float f = Float.MAX_VALUE;
        ArrayList arrayList3 = new ArrayList();
        HashMap<ItemStack, Int2IntOpenHashMap> hashMap = new HashMap<ItemStack, Int2IntOpenHashMap>();
        for (ItemStack itemStack : (Iterable<ItemStack>)(Iterable)((ArrayList)arrayList.get(0))) {
            int n3 = InventoryUtil.computeCount(hashMap, itemStack);
            for (ItemStack itemStack2 : (Iterable<ItemStack>)(Iterable)((ArrayList)arrayList.get(1))) {
                int n4 = n3 + (n3 > 20 ? 0 : InventoryUtil.computeCount(hashMap, itemStack2));
                for (ItemStack itemStack3 : (Iterable<ItemStack>)(Iterable)((ArrayList)arrayList.get(2))) {
                    int n5 = n4 + (n4 > 20 ? 0 : InventoryUtil.computeCount(hashMap, itemStack3));
                    for (ItemStack itemStack4 : (Iterable<ItemStack>)(Iterable)((ArrayList)arrayList.get(3))) {
                        int n6 = n5 + (n5 > 20 ? 0 : InventoryUtil.computeCount(hashMap, itemStack4));
                        float f2 = InventoryUtil.computeFactor(InventoryUtil.computeFactor(100.0f, itemStack, itemStack2, itemStack3, itemStack4), n6);
                        if (f2 < f) {
                            f = f2;
                            arrayList3.clear();
                            ArrayList<ItemStack> arrayList4 = new ArrayList<ItemStack>();
                            arrayList4.add(itemStack);
                            arrayList4.add(itemStack2);
                            arrayList4.add(itemStack3);
                            arrayList4.add(itemStack4);
                            arrayList3.add(arrayList4);
                            continue;
                        }
                        if (f2 != f) continue;
                        ArrayList<ItemStack> arrayList5 = new ArrayList<ItemStack>();
                        arrayList5.add(itemStack);
                        arrayList5.add(itemStack2);
                        arrayList5.add(itemStack3);
                        arrayList5.add(itemStack4);
                        arrayList3.add(arrayList5);
                    }
                }
            }
        }
        if (arrayList3.isEmpty()) {
            return new ArrayList();
        }
        ArrayList<ItemStack> arrayList6 = Lists.newArrayList(MinecraftAccessor.minecraft.thePlayer.container.getSlot(5).getStack(), MinecraftAccessor.minecraft.thePlayer.container.getSlot(6).getStack(), MinecraftAccessor.minecraft.thePlayer.container.getSlot(7).getStack(), MinecraftAccessor.minecraft.thePlayer.container.getSlot(8).getStack());
        ArrayList arrayList7 = (ArrayList)arrayList3.get(0);
        float f3 = -2000000.0f;
        if (arrayList3.size() > 1) {
            for (ArrayList arrayList8 : (Iterable<ArrayList>)(Iterable)(arrayList3)) {
                float f4 = 0.0f;
                for (ItemStack itemStack : (Iterable<ItemStack>)(Iterable)(arrayList8)) {
                    boolean bl3 = false;
                    if (bl && itemStack != null && itemStack.getItem() instanceof ItemGoldenBoots) {
                        ItemGoldenBoots itemGoldenBoots = (ItemGoldenBoots)itemStack.getItem();
                        if (arrayList6.get(itemGoldenBoots.indexOffset3) != null) {
                            bl3 = true;
                        }
                    }
                    f4 += InventoryUtil.computeFactor(hashMap.computeIfAbsent(itemStack, InventoryUtil::resolveInt2IntOpenHashMap4), itemStack, bl3, false);
                }
                if (!(f4 > f3)) continue;
                f3 = f4;
                arrayList7 = arrayList8;
            }
        }
        ArrayList<ItemStack> arrayList9 = new ArrayList<ItemStack>();
        for (ItemStack itemStack : (Iterable<ItemStack>)(Iterable)(arrayList6)) {
            if (itemStack == null || !(itemStack.getItem() instanceof ItemGoldenBoots)) continue;
            Int2IntOpenHashMap int2IntOpenHashMap = hashMap.computeIfAbsent(itemStack, InventoryUtil::resolveInt2IntOpenHashMap5);
            for (ItemStack itemStack5 : (Iterable<ItemStack>)(Iterable)(arrayList7)) {
                if (itemStack5 == null || !(itemStack5.getItem() instanceof ItemGoldenBoots)) continue;
                ItemGoldenBoots itemGoldenBoots = (ItemGoldenBoots)itemStack5.getItem();
                ItemGoldenBoots itemGoldenBoots2 = (ItemGoldenBoots)itemStack.getItem();
                if (itemGoldenBoots.indexOffset3 != itemGoldenBoots2.indexOffset3 || !itemGoldenBoots.getCrafterLeatherType().getLabelText().equals(itemGoldenBoots2.getCrafterLeatherType().getLabelText()) && (itemGoldenBoots.indexOffset3 != 0 || !itemGoldenBoots.getCrafterLeatherType().equals((Object)CrafterLeatherType.GOLD) && !itemGoldenBoots.getCrafterLeatherType().equals((Object)CrafterLeatherType.IRON) || !itemGoldenBoots2.getCrafterLeatherType().equals((Object)CrafterLeatherType.GOLD) && !itemGoldenBoots2.getCrafterLeatherType().equals((Object)CrafterLeatherType.IRON))) continue;
                Int2IntOpenHashMap int2IntOpenHashMap2 = hashMap.computeIfAbsent(itemStack5, InventoryUtil::resolveInt2IntOpenHashMap3);
                if (InventoryUtil.computeFactor(int2IntOpenHashMap, itemStack, bl, true) != InventoryUtil.computeFactor(int2IntOpenHashMap2, itemStack5, bl, true)) continue;
                arrayList9.add(itemStack5);
            }
            arrayList7.removeAll(arrayList9);
            arrayList9.clear();
        }
        ArrayList<Integer> arrayList10 = new ArrayList<Integer>();
        for (int i = n; i < n2; ++i) {
            ItemStack itemStack = container.getSlot(i).getStack();
            if (itemStack == null || !(itemStack.getItem() instanceof ItemGoldenBoots) || !arrayList7.contains(itemStack)) continue;
            arrayList10.add(i);
        }
        return arrayList10;
    }

    public static ArrayList getArrayList4(Container container, int n) {
        return InventoryUtil.getArrayList(container, n, (ArrayList)null);
    }

    private static int computeCount(HashMap hashMap, ItemStack itemStack) {
        LvlManager.storageEspRenderer.damageSource = DamageSource.damageSource14;
        TimeHelper.getStringArray();
        Int2IntOpenHashMap int2IntOpenHashMap = (Int2IntOpenHashMap)hashMap.computeIfAbsent(itemStack, stackItem -> InventoryUtil.resolveInt2IntOpenHashMapByItemStack((ItemStack)stackItem));
        int n = InventoryUtil.computeIfAbsent(EnchantmentManager.enchantmentManager6.indexOffset, itemStack, int2IntOpenHashMap);
        if (n > 0) {
            int n2 = LvlManager.storageEspRenderer.getIndex(EnchantmentManager.enchantmentManager6, n);
            return (int)((float)(n2 + 1 >> 1) + (float)((n2 >> 1) + 1) / 2.0f);
        }
        return 0;
    }

    public static boolean checkItem3(Item item) {
        TimeHelper.getStringArray();
        if (item instanceof ItemPotion) {
            return true;
        }
        if (!InventoryUtil.matchesItem(item)) {
            return false;
        }
        if (item instanceof ItemGoldenBoots || item instanceof ItemSword || item instanceof ItemTool || item instanceof ItemFishingRod || item instanceof ItemBow) {
            return true;
        }
        return Arrays.stream(countArray2).anyMatch(lambdaArgA -> InventoryUtil.matchesItem(item, lambdaArgA)) || Arrays.stream(stringArray).anyMatch(lambdaArgA -> InventoryUtil.matchesItem(item, lambdaArgA));
    }

    public static double getCoordinate(ItemStack itemStack) {
        return InventoryUtil.getCoordinate(itemStack, false);
    }

    private static boolean matchesItem(Item item, String string) {
        return item.equals(Item.getItem(string));
    }

    private boolean matchesItemStack(ItemStack itemStack, Item[] itemArray) {
        TimeHelper.getStringArray();
        if (itemStack == null) {
            return false;
        }
        if (itemArray == null || itemArray.length == 0) {
            return true;
        }
        Item[] itemArray2 = itemArray;
        int n = itemArray.length;
        for (int i = 0; i < n; ++i) {
            if (!itemArray2[i].equals(itemStack.getItem())) continue;
            return true;
        }
        return false;
    }

    public static int computeCount(Container container, int n, int n2, Item[] itemArray) {
        int n3 = 0;
        TimeHelper.getStringArray();
        for (int i = n; i < n2; ++i) {
            if (!InventoryUtil.matchesItemStackByItemStack(container.getSlot(i).getStack(), itemArray)) continue;
            ++n3;
        }
        return n3;
    }

    public static ArrayList getArrayList6(ArrayList arrayList) {
        Collections.sort(arrayList);
        TimeHelper.getStringArray();
        ArrayList arrayList2 = new ArrayList();
        int n = 0;
        ArrayList<Integer> arrayList3 = new ArrayList<Integer>();
        for (int i = 0; i < arrayList.size(); ++i) {
            int n2 = (Integer)arrayList.get(i);
            int n3 = (int)((double)n2 / 9.0);
            if (i == 0) {
                n = n3;
            }
            if (n3 != n) {
                if (n % 2 == 0) {
                    Collections.reverse(arrayList3);
                }
                arrayList2.addAll(arrayList3);
                arrayList3.clear();
            }
            arrayList3.add(n2);
            n = n3;
            if (i != arrayList.size() - 1) continue;
            if (n % 2 == 0) {
                Collections.reverse(arrayList3);
            }
            arrayList2.addAll(arrayList3);
        }
        return arrayList2;
    }

    private static int computeCount(ItemStack itemStack, int n, Int2IntOpenHashMap int2IntOpenHashMap, int n2) {
        NBTTagList nBTTagList;
        TimeHelper.getStringArray();
        if (itemStack != null && (nBTTagList = itemStack.getExtendedLoggerEntry()) != null) {
            for (int i = 0; i < nBTTagList.getInt(); ++i) {
                if (nBTTagList.getNBTTagCompound(i).processValue("id") != n) continue;
                short shortValue = nBTTagList.getNBTTagCompound(i).processValue("lvl");
                int2IntOpenHashMap.put(n, shortValue);
                return shortValue;
            }
        }
        return 0;
    }

    private static float computeFactor(Int2IntOpenHashMap int2IntOpenHashMap, ItemStack itemStack, boolean bl, boolean bl2) {
        TimeHelper.getStringArray();
        if (itemStack == null) {
            return 0.0f;
        }
        float f = 0.0f;
        if (bl2) {
            int n = InventoryUtil.computeIfAbsent(EnchantmentManager.enchantmentManager6.indexOffset, itemStack, int2IntOpenHashMap);
            f += (float)(6 + n * n) / 3.0f;
        }
        float f2 = f + (float)InventoryUtil.computeIfAbsent(EnchantmentManager.enchantmentManager18.indexOffset, itemStack, int2IntOpenHashMap) / 50.0f + (float)InventoryUtil.computeIfAbsent(EnchantmentManager.enchantmentManager16.indexOffset, itemStack, int2IntOpenHashMap) / 50.0f + (float)InventoryUtil.computeIfAbsent(EnchantmentManager.enchantmentManager14.indexOffset, itemStack, int2IntOpenHashMap) / 50.0f + (float)InventoryUtil.computeIfAbsent(EnchantmentManager.enchantmentManager1.indexOffset, itemStack, int2IntOpenHashMap) / 69.0f + (float)InventoryUtil.computeIfAbsent(EnchantmentManager.enchantmentManager12.indexOffset, itemStack, int2IntOpenHashMap) / 50.0f + (float)InventoryUtil.computeIfAbsent(EnchantmentManager.enchantmentManager20.indexOffset, itemStack, int2IntOpenHashMap) / 50.0f;
        if (!bl) {
            f2 += (float)(itemStack.processAction() - itemStack.getIndexOffset2()) / 10000.0f;
        }
        return f2;
    }

    private static double computeValueByContainer(Container container, Pair pair) {
        return container.getSlot((int)((Integer)pair.getFirst()).intValue()).getStack().animationsToGo;
    }

    public static ArrayList getHoeSlotList(Container container, int n) {
        int n2 = -1;
        TimeHelper.getStringArray();
        int n3 = -1;
        int n4 = -1;
        int n5 = -1;
        ItemStack itemStack = null;
        ItemStack itemStack2 = null;
        ItemStack itemStack3 = null;
        ItemStack itemStack4 = null;
        for (int i = n; i < container.itemList2.size(); ++i) {
            ItemStack itemStack5 = container.getSlot(i).getStack();
            if (itemStack5 == null) continue;
            Item item = itemStack5.getItem();
            if (item instanceof ItemGoldenHoe && (itemStack4 == null || InventoryUtil.getCoordinate(itemStack5, true) > InventoryUtil.getCoordinate(itemStack4, true))) {
                itemStack4 = itemStack5;
                n5 = i;
            }
            if (!(item instanceof ItemTool)) continue;
            if (item instanceof ItemGoldenAxe) {
                if (itemStack != null && !(InventoryUtil.getCoordinate(itemStack5, true) > InventoryUtil.getCoordinate(itemStack, true))) continue;
                itemStack = itemStack5;
                n2 = i;
                continue;
            }
            if (item instanceof ItemGoldenPickaxe) {
                if (itemStack2 != null && !(InventoryUtil.getCoordinate(itemStack5, true) > InventoryUtil.getCoordinate(itemStack2, true))) continue;
                itemStack2 = itemStack5;
                n3 = i;
                continue;
            }
            if (!(item instanceof ItemGoldenShovel) || itemStack3 != null && !(InventoryUtil.getCoordinate(itemStack5, true) > InventoryUtil.getCoordinate(itemStack3, true))) continue;
            itemStack3 = itemStack5;
            n4 = i;
        }
        return new ArrayList<Integer>(Arrays.asList(n2, n3, n4, n5));
    }

    public static ArrayList getArrayList(Container container, ArrayList arrayList, String string) {
        return InventoryUtil.getArrayList(container, arrayList, string, 166);
    }

    private static float computeFactor(ItemStack itemStack, ItemStack itemStack2, ItemStack itemStack3, ItemStack itemStack4) {
        TimeHelper.getStringArray();
        float f = 0.0f;
        if (itemStack != null && itemStack.getItem() instanceof ItemGoldenBoots) {
            float f2 = ((ItemGoldenBoots)itemStack.getItem()).indexOffset4;
            f += f2;
        }
        if (itemStack2 != null && itemStack2.getItem() instanceof ItemGoldenBoots) {
            float f3 = ((ItemGoldenBoots)itemStack2.getItem()).indexOffset4;
            f += f3;
        }
        if (itemStack3 != null && itemStack3.getItem() instanceof ItemGoldenBoots) {
            float f4 = ((ItemGoldenBoots)itemStack3.getItem()).indexOffset4;
            f += f4;
        }
        if (itemStack4 != null && itemStack4.getItem() instanceof ItemGoldenBoots) {
            float f5 = ((ItemGoldenBoots)itemStack4.getItem()).indexOffset4;
            f += f5;
        }
        return f;
    }

    private static Int2IntOpenHashMap resolveInt2IntOpenHashMap2(ItemStack itemStack) {
        return new Int2IntOpenHashMap();
    }

    public static ArrayList getArrayList2(long l, Container container, int n) {
        ArrayList<Pair> arrayList = new ArrayList<Pair>();
        TimeHelper.getStringArray();
        for (int i = n; i < container.itemList2.size(); ++i) {
            Item item;
            ItemStack itemStack = container.getSlot(i).getStack();
            if (itemStack == null || !((item = itemStack.getItem()) instanceof ItemFood) || item != Item.getItem("golden_apple")) continue;
            double d = InventoryUtil.getCoordinate(itemStack);
            arrayList.add(new Pair(d, i));
        }
        arrayList.sort(Comparator.comparingDouble(pairItem -> (Double)((Pair)pairItem).getSecond()));
        Collections.reverse(arrayList);
        return arrayList;
    }

    public static ArrayList getArrayList3(Container container, int n, long l) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        TimeHelper.getStringArray();
        for (int i = n; i < container.itemList2.size(); ++i) {
            PotionEffect potionEffect;
            List list;
            Item item;
            ItemStack itemStack = container.getSlot(i).getStack();
            if (itemStack == null || !((item = itemStack.getItem()) instanceof ItemPotion) || (list = ((ItemPotion)item).getList(itemStack)) == null || list.isEmpty() || Potion.potionArray[(potionEffect = (PotionEffect)list.get(0)).getIndexOffset3()].isState2()) continue;
            arrayList.add(i);
        }
        return arrayList;
    }

    static {
        countArray2 = new int[]{139, 28, 27, 295, 192, 171, 120, 84, 32, 31, 160, 425, 175, 390, 12, 38, 37, 50, 6, 23, 158, 183, 184, 373, 185, 186, 187, 188, 189, 190, 191, 290, 291, 292, 193, 194, 39, 40, 288, 289, 53, 67, 70, 72, 77, 85, 81, 96, 101, 102, 106, 107, 108, 109, 111, 113, 114, 128, 131, 134, 135, 136, 143, 147, 148, 151, 154, 156, 157, 163, 164, 167, 180, 318, 321, 337, 338, 323, 348, 352, 353, 354, 356, 361, 259, 362, 367, 370, 371, 372, 373, 334};
        stringArray = new String[]{"tallgrass", "deadbush", "red_flower", "wooden_slab", "cobblestone_wall", "anvil", "stone_slab", "snow_layer", "stained_glass_pane", "carpet", "double_plant", "stone_slab2", "sapling"};
        arrayList = new ArrayList();
    }
}

