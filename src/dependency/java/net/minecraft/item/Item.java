package net.minecraft.item;

import augustus.modes.movement.MovementVerydamagedMode;
import client.setting.CrafterLeatherType;
import client.setting.GlobalOptionType;
import client.setting.ItemNoneType;
import client.setting.ItemStackCommonType;
import client.setting.MovementNumberProperty;
import client.util.AdvancedFunctionAdapter;
import client.util.AuxiliaryFunctionHandler;
import client.util.CommonFunctionAdapter;
import client.util.CoreFunctionAdapter;
import client.util.CustomFunctionAdapter;
import client.util.DefaultFunctionHandler;
import client.util.EnhancedFunctionAdapter;
import client.util.ExtendedFunctionAdapter;
import client.util.ExtraFunctionAdapter;
import client.util.FunctionHandler;
import client.util.InternalFunctionAdapter;
import client.util.NativeFunctionAdapter;
import client.util.PrimaryFunctionHandler;
import client.util.SpecialFunctionAdapter;
import client.util.SupplementalFunctionAdapter;
import client.util.SystemClientManager;
import client.util.TertiaryFunctionAdapter;
import client.util.WetFunction;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntry;
import net.minecraft.block.BlockEntryAdapter;
import net.minecraft.block.Blocks;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityItemframe;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityPainting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.RegistryNamespaced;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class Item {
   public static final RegistryNamespaced iterableExtensionAdapter = new RegistryNamespaced();
   public static final Map entryMap = Maps.newHashMap();
   public static final UUID uuid = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
   public ItemGroupManager itemGroupManager;
   public int indexOffset = 3;
   public static Random random = new Random();
   public int indexOffset1 = 64;
   public int indexOffset2;
   public boolean state4;
   public boolean state;
   public Item item;
   public String text;
   public String text3;

   public ItemStackCommonType getItemStackCommonType(ItemStack stack) {
      return stack.canExecute3() ? ItemStackCommonType.RARE : ItemStackCommonType.COMMON;
   }

   private static void processAction(int count, ResourceLocation location, Item item2) {
      iterableExtensionAdapter.applyObject2(count, location, item2);
   }

   public boolean processAction(ItemStack stack, EntityPlayer player, EntityLivingBase livingEntity) {
      return false;
   }

   public int processAction(ItemStack stack) {
      return 0;
   }

   public boolean isState4() {
      return this.state4;
   }

   public float processAction(ItemStack stack, Block block) {
      return 1.0F;
   }

   public String resolveTextByItemStack(ItemStack stack) {
      String text2 = this.getText(stack);
      return text2 == null ? "" : I18n.getString2(text2);
   }

   public static Item processAction(int count) {
      return (Item)iterableExtensionAdapter.getObject3(count);
   }

   public void processAction(ItemStack stack, World world, Entity entity, int count, boolean flag) {
   }

   public boolean processAction(NBTTagCompound tagCompound) {
      return false;
   }

   public Item processAction(String text2) {
      this.text3 = text2;
      return this;
   }

   public boolean isState() {
      return this.state;
   }

   public String getString() {
      return "item." + this.text3;
   }

   public void processAction(Item item2, ItemGroupManager itemGroupManager2, List list) {
      list.add(new ItemStack(item2, 1, 0));
   }

   public boolean processAction() {
      return true;
   }

   public void processAction(ItemStack stack, World world, EntityPlayer player) {
   }

   public static int getIndex(Item item2) {
      return item2 == null ? 0 : iterableExtensionAdapter.getIndex(item2);
   }

   public int processAction2(int count) {
      return 0;
   }

   public boolean processAction2(ItemStack stack) {
      return stack.canExecute3();
   }

   public boolean isActive() {
      return this.indexOffset2 > 0 && !this.state;
   }

   public Item processAction3(int count) {
      this.indexOffset2 = count;
      return this;
   }

   public String getText(ItemStack stack) {
      return "item." + this.text3;
   }

   public ItemGroupManager getItemGroupManager() {
      return this.itemGroupManager;
   }

   public boolean isActiveAsFlag() {
      return this.item != null;
   }

   private static void processAction(int count, String text2, Item item2) {
      processAction(count, new ResourceLocation(text2), item2);
   }

   public String processAction3(ItemStack stack) {
      return this.text;
   }

   public static void updateState() {
      processAction(Blocks.stone, new BlockEntry(Blocks.stone, Blocks.stone, new CoreFunctionAdapter()).resolveItemBlock("stone"));
      processAction(Blocks.blockGrass, new DiamondShovel(Blocks.blockGrass, false));
      processAction(Blocks.block115, new BlockEntry(Blocks.block115, Blocks.block115, new CustomFunctionAdapter()).resolveItemBlock("dirt"));
      processAction3(Blocks.block65);
      processAction(Blocks.block142, new BlockEntry(Blocks.block142, Blocks.block142, new AdvancedFunctionAdapter()).resolveItemBlock("wood"));
      processAction(Blocks.block83, new BlockEntry(Blocks.block83, Blocks.block83, new SpecialFunctionAdapter()).resolveItemBlock("sapling"));
      processAction3(Blocks.block95);
      processAction(Blocks.blockSand, new BlockEntry(Blocks.blockSand, Blocks.blockSand, new PrimaryFunctionHandler()).resolveItemBlock("sand"));
      processAction3(Blocks.block45);
      processAction3(Blocks.block121);
      processAction3(Blocks.block103);
      processAction3(Blocks.block89);
      processAction(Blocks.block140, new BlockEntry(Blocks.block140, Blocks.block140, new CommonFunctionAdapter()).resolveItemBlock("log"));
      processAction(Blocks.block104, new BlockEntry(Blocks.block104, Blocks.block104, new DefaultFunctionHandler()).resolveItemBlock("log"));
      processAction(Blocks.decayableManager2, new DiamondHoe(Blocks.decayableManager2).resolveItemBlock("leaves"));
      processAction(Blocks.decayableManager, new DiamondHoe(Blocks.decayableManager).resolveItemBlock("leaves"));
      processAction(Blocks.block11, new BlockEntry(Blocks.block11, Blocks.block11, new WetFunction()).resolveItemBlock("sponge"));
      processAction3(Blocks.block68);
      processAction3(Blocks.block47);
      processAction3(Blocks.block76);
      processAction3(Blocks.block129);
      processAction(Blocks.block136, new BlockEntry(Blocks.block136, Blocks.block136, new ExtraFunctionAdapter()).resolveItemBlock("sandStone"));
      processAction3(Blocks.block143);
      processAction3(Blocks.block50);
      processAction3(Blocks.block40);
      processAction(Blocks.blockPiston, new DiamondPickaxe(Blocks.blockPiston));
      processAction3(Blocks.web);
      processAction(Blocks.blockTallgrass, new DiamondShovel(Blocks.blockTallgrass, true).withStringArray(new String[]{"shrub", "grass", "fern"}));
      processAction3(Blocks.blockDeadbush);
      processAction(Blocks.blockPiston2, new DiamondPickaxe(Blocks.blockPiston2));
      processAction(Blocks.wool, new DiamondHelmet(Blocks.wool).resolveItemBlock("cloth"));
      processAction(
         Blocks.immutableSetEntry2Entry,
         new BlockEntry(Blocks.immutableSetEntry2Entry, Blocks.immutableSetEntry2Entry, new ExtendedFunctionAdapter()).resolveItemBlock("flower")
      );
      processAction(
         Blocks.immutableSetEntry2Entry2,
         new BlockEntry(Blocks.immutableSetEntry2Entry2, Blocks.immutableSetEntry2Entry2, new NativeFunctionAdapter()).resolveItemBlock("rose")
      );
      processAction3(Blocks.secondaryBlockAdapter2);
      processAction3(Blocks.secondaryBlockAdapter);
      processAction3(Blocks.block35);
      processAction3(Blocks.block16);
      processAction(
         Blocks.extraBlockAdapter6,
         new DiamondAxe(Blocks.extraBlockAdapter6, Blocks.extraBlockAdapter6, Blocks.extraBlockAdapter5).resolveItemBlock("stoneSlab")
      );
      processAction3(Blocks.block100);
      processAction3(Blocks.block135);
      processAction3(Blocks.block8);
      processAction3(Blocks.block131);
      processAction3(Blocks.block113);
      processAction3(Blocks.block21);
      processAction3(Blocks.block29);
      processAction3(Blocks.block3);
      processAction3(Blocks.blockTrappedChest);
      processAction3(Blocks.block44);
      processAction3(Blocks.block91);
      processAction3(Blocks.block46);
      processAction3(Blocks.block7);
      processAction3(Blocks.block39);
      processAction3(Blocks.block88);
      processAction3(Blocks.block81);
      processAction3(Blocks.block13);
      processAction3(Blocks.block125);
      processAction3(Blocks.block48);
      processAction3(Blocks.block23);
      processAction3(Blocks.block94);
      processAction3(Blocks.block25);
      processAction3(Blocks.block12);
      processAction3(Blocks.block75);
      processAction(Blocks.block74, new DiamondSword(Blocks.block74));
      processAction3(Blocks.block53);
      processAction3(Blocks.block61);
      processAction3(Blocks.blockCactus);
      processAction3(Blocks.block123);
      processAction3(Blocks.block114);
      processAction3(Blocks.block111);
      processAction3(Blocks.block38);
      processAction3(Blocks.block27);
      processAction3(Blocks.block87);
      processAction3(Blocks.block147);
      processAction3(Blocks.block97);
      processAction3(Blocks.block146);
      processAction3(Blocks.block96);
      processAction3(Blocks.block126);
      processAction3(Blocks.block77);
      processAction3(Blocks.block120);
      processAction3(Blocks.block30);
      processAction(Blocks.block22, new BlockEntry(Blocks.block22, Blocks.block22, new TertiaryFunctionAdapter()).resolveItemBlock("monsterStoneEgg"));
      processAction(Blocks.block42, new BlockEntry(Blocks.block42, Blocks.block42, new SupplementalFunctionAdapter()).resolveItemBlock("stonebricksmooth"));
      processAction3(Blocks.block54);
      processAction3(Blocks.block41);
      processAction3(Blocks.block127);
      processAction3(Blocks.block19);
      processAction3(Blocks.block67);
      processAction(Blocks.block144, new DiamondShovel(Blocks.block144, false));
      processAction3(Blocks.block117);
      processAction3(Blocks.block43);
      processAction3(Blocks.block73);
      processAction3(Blocks.block31);
      processAction3(Blocks.block101);
      processAction3(Blocks.block116);
      processAction3(Blocks.block109);
      processAction3(Blocks.block130);
      processAction3(Blocks.blockMycelium);
      processAction(Blocks.block33, new SystemClientManager(Blocks.block33));
      processAction3(Blocks.block86);
      processAction3(Blocks.block134);
      processAction3(Blocks.block24);
      processAction3(Blocks.block102);
      processAction3(Blocks.block52);
      processAction3(Blocks.block28);
      processAction3(Blocks.block82);
      processAction3(Blocks.block64);
      processAction(
         Blocks.extraBlockAdapter2, new DiamondAxe(Blocks.extraBlockAdapter2, Blocks.extraBlockAdapter2, Blocks.extraBlockAdapter).resolveItemBlock("woodSlab")
      );
      processAction3(Blocks.block122);
      processAction3(Blocks.block70);
      processAction3(Blocks.block60);
      processAction3(Blocks.blockTripwireHook);
      processAction3(Blocks.block93);
      processAction3(Blocks.block85);
      processAction3(Blocks.block118);
      processAction3(Blocks.block18);
      processAction3(Blocks.block139);
      processAction3(Blocks.blockBeacon);
      processAction(Blocks.block63, new BlockEntry(Blocks.block63, Blocks.block63, new InternalFunctionAdapter()).resolveItemBlock("cobbleWall"));
      processAction3(Blocks.block59);
      processAction(Blocks.block51, new MovementVerydamagedMode(Blocks.block51).resolveItemBlock("anvil"));
      processAction3(Blocks.block128);
      processAction3(Blocks.block26);
      processAction3(Blocks.block132);
      processAction3(Blocks.blockDaylightDetectorInverted);
      processAction3(Blocks.block57);
      processAction3(Blocks.block4);
      processAction3(Blocks.blockHopper);
      processAction(
         Blocks.block17, new BlockEntry(Blocks.block17, Blocks.block17, new String[]{"default", "chiseled", "lines"}).resolveItemBlock("quartzBlock")
      );
      processAction3(Blocks.block15);
      processAction3(Blocks.block106);
      processAction3(Blocks.block69);
      processAction(Blocks.block107, new DiamondHelmet(Blocks.block107).resolveItemBlock("clayHardenedStained"));
      processAction3(Blocks.block145);
      processAction3(Blocks.block14);
      processAction3(Blocks.block137);
      processAction(Blocks.block141, new DiamondHelmet(Blocks.block141).resolveItemBlock("woolCarpet"));
      processAction3(Blocks.block98);
      processAction3(Blocks.block9);
      processAction3(Blocks.block105);
      processAction3(Blocks.block34);
      processAction3(Blocks.block5);
      processAction3(Blocks.block71);
      processAction(
         Blocks.blockDoublePlant,
         new BlockEntryAdapter(Blocks.blockDoublePlant, Blocks.blockDoublePlant, new EnhancedFunctionAdapter()).resolveItemBlock("doublePlant")
      );
      processAction(Blocks.blockStainedGlass, new DiamondHelmet(Blocks.blockStainedGlass).resolveItemBlock("stainedGlass"));
      processAction(Blocks.blockStainedGlassPane, new DiamondHelmet(Blocks.blockStainedGlassPane).resolveItemBlock("stainedGlassPane"));
      processAction(Blocks.block58, new BlockEntry(Blocks.block58, Blocks.block58, new FunctionHandler()).resolveItemBlock("prismarine"));
      processAction3(Blocks.block62);
      processAction(Blocks.block119, new BlockEntry(Blocks.block119, Blocks.block119, new AuxiliaryFunctionHandler()).resolveItemBlock("redSandStone"));
      processAction3(Blocks.block72);
      processAction(
         Blocks.extraBlockAdapter4,
         new DiamondAxe(Blocks.extraBlockAdapter4, Blocks.extraBlockAdapter4, Blocks.extraBlockAdapter3).resolveItemBlock("stoneSlab2")
      );
      processAction(256, "iron_shovel", new ItemGoldenShovel(GlobalOptionType.IRON).processAction("shovelIron"));
      processAction(257, "iron_pickaxe", new ItemGoldenPickaxe(GlobalOptionType.IRON).processAction("pickaxeIron"));
      processAction(258, "iron_axe", new ItemGoldenAxe(GlobalOptionType.IRON).processAction("hatchetIron"));
      processAction(259, "flint_and_steel", new ItemFlintAndSteel().processAction("flintAndSteel"));
      processAction(260, "apple", new ItemFood(4, 0.3F, false).processAction("apple"));
      processAction(261, "bow", new ItemBow().processAction("bow"));
      processAction(262, "arrow", new Item().processAction("arrow").processAction(ItemGroupManager.itemGroupManager4));
      processAction(263, "coal", new ItemCoal().processAction("coal"));
      processAction(264, "diamond", new Item().processAction("diamond").processAction(ItemGroupManager.itemGroupManager11));
      processAction(265, "iron_ingot", new Item().processAction("ingotIron").processAction(ItemGroupManager.itemGroupManager11));
      processAction(266, "gold_ingot", new Item().processAction("ingotGold").processAction(ItemGroupManager.itemGroupManager11));
      processAction(267, "iron_sword", new ItemSword(GlobalOptionType.IRON).processAction("swordIron"));
      processAction(268, "wooden_sword", new ItemSword(GlobalOptionType.WOOD).processAction("swordWood"));
      processAction(269, "wooden_shovel", new ItemGoldenShovel(GlobalOptionType.WOOD).processAction("shovelWood"));
      processAction(270, "wooden_pickaxe", new ItemGoldenPickaxe(GlobalOptionType.WOOD).processAction("pickaxeWood"));
      processAction(271, "wooden_axe", new ItemGoldenAxe(GlobalOptionType.WOOD).processAction("hatchetWood"));
      processAction(272, "stone_sword", new ItemSword(GlobalOptionType.STONE).processAction("swordStone"));
      processAction(273, "stone_shovel", new ItemGoldenShovel(GlobalOptionType.STONE).processAction("shovelStone"));
      processAction(274, "stone_pickaxe", new ItemGoldenPickaxe(GlobalOptionType.STONE).processAction("pickaxeStone"));
      processAction(275, "stone_axe", new ItemGoldenAxe(GlobalOptionType.STONE).processAction("hatchetStone"));
      processAction(276, "diamond_sword", new ItemSword(GlobalOptionType.DIAMOND).processAction("swordDiamond"));
      processAction(277, "diamond_shovel", new ItemGoldenShovel(GlobalOptionType.DIAMOND).processAction("shovelDiamond"));
      processAction(278, "diamond_pickaxe", new ItemGoldenPickaxe(GlobalOptionType.DIAMOND).processAction("pickaxeDiamond"));
      processAction(279, "diamond_axe", new ItemGoldenAxe(GlobalOptionType.DIAMOND).processAction("hatchetDiamond"));
      processAction(280, "stick", new Item().processAction4().processAction("stick").processAction(ItemGroupManager.itemGroupManager11));
      processAction(281, "bowl", new Item().processAction("bowl").processAction(ItemGroupManager.itemGroupManager11));
      processAction(282, "mushroom_stew", new ItemRabbitStew(6).processAction("mushroomStew"));
      processAction(283, "golden_sword", new ItemSword(GlobalOptionType.GOLD).processAction("swordGold"));
      processAction(284, "golden_shovel", new ItemGoldenShovel(GlobalOptionType.GOLD).processAction("shovelGold"));
      processAction(285, "golden_pickaxe", new ItemGoldenPickaxe(GlobalOptionType.GOLD).processAction("pickaxeGold"));
      processAction(286, "golden_axe", new ItemGoldenAxe(GlobalOptionType.GOLD).processAction("hatchetGold"));
      processAction(287, "string", new ItemComparator(Blocks.block6).processAction("string").processAction(ItemGroupManager.itemGroupManager11));
      processAction(288, "feather", new Item().processAction("feather").processAction(ItemGroupManager.itemGroupManager11));
      processAction(289, "gunpowder", new Item().processAction("sulphur").processAction2("+14&13-13").processAction(ItemGroupManager.itemGroupManager11));
      processAction(290, "wooden_hoe", new ItemGoldenHoe(GlobalOptionType.WOOD).processAction("hoeWood"));
      processAction(291, "stone_hoe", new ItemGoldenHoe(GlobalOptionType.STONE).processAction("hoeStone"));
      processAction(292, "iron_hoe", new ItemGoldenHoe(GlobalOptionType.IRON).processAction("hoeIron"));
      processAction(293, "diamond_hoe", new ItemGoldenHoe(GlobalOptionType.DIAMOND).processAction("hoeDiamond"));
      processAction(294, "golden_hoe", new ItemGoldenHoe(GlobalOptionType.GOLD).processAction("hoeGold"));
      processAction(295, "wheat_seeds", new ItemNetherWart(Blocks.block92, Blocks.block7).processAction("seeds"));
      processAction(296, "wheat", new Item().processAction("wheat").processAction(ItemGroupManager.itemGroupManager11));
      processAction(297, "bread", new ItemFood(5, 0.6F, false).processAction("bread"));
      processAction(298, "leather_helmet", new ItemGoldenBoots(CrafterLeatherType.LEATHER, 0, 0).processAction("helmetCloth"));
      processAction(299, "leather_chestplate", new ItemGoldenBoots(CrafterLeatherType.LEATHER, 0, 1).processAction("chestplateCloth"));
      processAction(300, "leather_leggings", new ItemGoldenBoots(CrafterLeatherType.LEATHER, 0, 2).processAction("leggingsCloth"));
      processAction(301, "leather_boots", new ItemGoldenBoots(CrafterLeatherType.LEATHER, 0, 3).processAction("bootsCloth"));
      processAction(302, "chainmail_helmet", new ItemGoldenBoots(CrafterLeatherType.CHAIN, 1, 0).processAction("helmetChain"));
      processAction(303, "chainmail_chestplate", new ItemGoldenBoots(CrafterLeatherType.CHAIN, 1, 1).processAction("chestplateChain"));
      processAction(304, "chainmail_leggings", new ItemGoldenBoots(CrafterLeatherType.CHAIN, 1, 2).processAction("leggingsChain"));
      processAction(305, "chainmail_boots", new ItemGoldenBoots(CrafterLeatherType.CHAIN, 1, 3).processAction("bootsChain"));
      processAction(306, "iron_helmet", new ItemGoldenBoots(CrafterLeatherType.IRON, 2, 0).processAction("helmetIron"));
      processAction(307, "iron_chestplate", new ItemGoldenBoots(CrafterLeatherType.IRON, 2, 1).processAction("chestplateIron"));
      processAction(308, "iron_leggings", new ItemGoldenBoots(CrafterLeatherType.IRON, 2, 2).processAction("leggingsIron"));
      processAction(309, "iron_boots", new ItemGoldenBoots(CrafterLeatherType.IRON, 2, 3).processAction("bootsIron"));
      processAction(310, "diamond_helmet", new ItemGoldenBoots(CrafterLeatherType.DIAMOND, 3, 0).processAction("helmetDiamond"));
      processAction(311, "diamond_chestplate", new ItemGoldenBoots(CrafterLeatherType.DIAMOND, 3, 1).processAction("chestplateDiamond"));
      processAction(312, "diamond_leggings", new ItemGoldenBoots(CrafterLeatherType.DIAMOND, 3, 2).processAction("leggingsDiamond"));
      processAction(313, "diamond_boots", new ItemGoldenBoots(CrafterLeatherType.DIAMOND, 3, 3).processAction("bootsDiamond"));
      processAction(314, "golden_helmet", new ItemGoldenBoots(CrafterLeatherType.GOLD, 4, 0).processAction("helmetGold"));
      processAction(315, "golden_chestplate", new ItemGoldenBoots(CrafterLeatherType.GOLD, 4, 1).processAction("chestplateGold"));
      processAction(316, "golden_leggings", new ItemGoldenBoots(CrafterLeatherType.GOLD, 4, 2).processAction("leggingsGold"));
      processAction(317, "golden_boots", new ItemGoldenBoots(CrafterLeatherType.GOLD, 4, 3).processAction("bootsGold"));
      processAction(318, "flint", new Item().processAction("flint").processAction(ItemGroupManager.itemGroupManager11));
      processAction(319, "porkchop", new ItemFood(3, 0.3F, true).processAction("porkchopRaw"));
      processAction(320, "cooked_porkchop", new ItemFood(8, 0.8F, true).processAction("porkchopCooked"));
      processAction(321, "painting", new ItemItemFrame(EntityPainting.class).processAction("painting"));
      processAction(
         322,
         "golden_apple",
         new ItemGoldenApple(4, 1.2F, false).getItemFood().resolveItemFood(Potion.potion11.indexOffset3, 5, 1, 1.0F).processAction("appleGold")
      );
      processAction(323, "sign", new ItemSign().processAction("sign"));
      processAction(324, "wooden_door", new ItemDarkOakDoor(Blocks.block110).processAction("doorOak"));
      Item item2 = new ItemLavaBucket(Blocks.air).processAction("bucket").processAction4(16);
      processAction(325, "bucket", item2);
      processAction(326, "water_bucket", new ItemLavaBucket(Blocks.blockFlowingLava).processAction("bucketWater").processAction(item2));
      processAction(327, "lava_bucket", new ItemLavaBucket(Blocks.blockFlowingLava2).processAction("bucketLava").processAction(item2));
      processAction(328, "minecart", new ItemCommandBlockMinecart(MovementNumberProperty.RIDEABLE).processAction("minecart"));
      processAction(329, "saddle", new ItemSaddle().processAction("saddle"));
      processAction(330, "iron_door", new ItemDarkOakDoor(Blocks.ironDoor).processAction("doorIron"));
      processAction(331, "redstone", new ItemRedstone().processAction("redstone").processAction2("-5+6-7"));
      processAction(332, "snowball", new ItemSnowball().processAction("snowball"));
      processAction(333, "boat", new ItemBoat().processAction("boat"));
      processAction(334, "leather", new Item().processAction("leather").processAction(ItemGroupManager.itemGroupManager11));
      processAction(335, "milk_bucket", new ItemMilkBucket().processAction("milk").processAction(item2));
      processAction(336, "brick", new Item().processAction("brick").processAction(ItemGroupManager.itemGroupManager11));
      processAction(337, "clay_ball", new Item().processAction("clay").processAction(ItemGroupManager.itemGroupManager11));
      processAction(338, "reeds", new ItemComparator(Blocks.blockReeds).processAction("reeds").processAction(ItemGroupManager.itemGroupManager11));
      processAction(339, "paper", new Item().processAction("paper").processAction(ItemGroupManager.itemGroupManager5));
      processAction(340, "book", new ItemBook().processAction("book").processAction(ItemGroupManager.itemGroupManager5));
      processAction(341, "slime_ball", new Item().processAction("slimeball").processAction(ItemGroupManager.itemGroupManager5));
      processAction(342, "chest_minecart", new ItemCommandBlockMinecart(MovementNumberProperty.CHEST).processAction("minecartChest"));
      processAction(343, "furnace_minecart", new ItemCommandBlockMinecart(MovementNumberProperty.FURNACE).processAction("minecartFurnace"));
      processAction(344, "egg", new ItemEgg().processAction("egg"));
      processAction(345, "compass", new Item().processAction("compass").processAction(ItemGroupManager.itemGroupManager2));
      processAction(346, "fishing_rod", new ItemFishingRod().processAction("fishingRod"));
      processAction(347, "clock", new Item().processAction("clock").processAction(ItemGroupManager.itemGroupManager2));
      processAction(348, "glowstone_dust", new Item().processAction("yellowDust").processAction2("+5-6-7").processAction(ItemGroupManager.itemGroupManager11));
      processAction(349, "fish", new ItemCookedFish(false).processAction("fish").processAction(true));
      processAction(350, "cooked_fish", new ItemCookedFish(true).processAction("fish").processAction(true));
      processAction(351, "dye", new ItemDye().processAction("dyePowder"));
      processAction(352, "bone", new Item().processAction("bone").processAction4().processAction(ItemGroupManager.itemGroupManager5));
      processAction(353, "sugar", new Item().processAction("sugar").processAction2("-0+1-2-3&4-4+13").processAction(ItemGroupManager.itemGroupManager11));
      processAction(354, "cake", new ItemComparator(Blocks.block32).processAction4(1).processAction("cake").processAction(ItemGroupManager.itemGroupManager3));
      processAction(355, "bed", new ItemBed().processAction4(1).processAction("bed"));
      processAction(356, "repeater", new ItemComparator(Blocks.blockPoweredRepeater).processAction("diode").processAction(ItemGroupManager.itemGroupManager9));
      processAction(357, "cookie", new ItemFood(2, 0.1F, false).processAction("cookie"));
      processAction(358, "filled_map", new ItemFilledMap().processAction("map"));
      processAction(359, "shears", new ItemShears().processAction("shears"));
      processAction(360, "melon", new ItemFood(2, 0.3F, false).processAction("melon"));
      processAction(361, "pumpkin_seeds", new ItemNetherWart(Blocks.block55, Blocks.block7).processAction("seeds_pumpkin"));
      processAction(362, "melon_seeds", new ItemNetherWart(Blocks.block133, Blocks.block7).processAction("seeds_melon"));
      processAction(363, "beef", new ItemFood(3, 0.3F, true).processAction("beefRaw"));
      processAction(364, "cooked_beef", new ItemFood(8, 0.8F, true).processAction("beefCooked"));
      processAction(365, "chicken", new ItemFood(2, 0.3F, true).resolveItemFood(Potion.potion29.indexOffset3, 30, 0, 0.3F).processAction("chickenRaw"));
      processAction(366, "cooked_chicken", new ItemFood(6, 0.6F, true).processAction("chickenCooked"));
      processAction(367, "rotten_flesh", new ItemFood(4, 0.1F, true).resolveItemFood(Potion.potion29.indexOffset3, 30, 0, 0.8F).processAction("rottenFlesh"));
      processAction(368, "ender_pearl", new ItemEnderPearl().processAction("enderPearl"));
      processAction(369, "blaze_rod", new Item().processAction("blazeRod").processAction(ItemGroupManager.itemGroupManager11).processAction4());
      processAction(
         370, "ghast_tear", new Item().processAction("ghastTear").processAction2("+0-1-2-3&4-4+13").processAction(ItemGroupManager.itemGroupManager7)
      );
      processAction(371, "gold_nugget", new Item().processAction("goldNugget").processAction(ItemGroupManager.itemGroupManager11));
      processAction(372, "nether_wart", new ItemNetherWart(Blocks.block78, Blocks.block126).processAction("netherStalkSeeds").processAction2("+4"));
      processAction(373, "potion", new ItemPotion().processAction("potion"));
      processAction(374, "glass_bottle", new ItemGlassBottle().processAction("glassBottle"));
      processAction(
         375,
         "spider_eye",
         new ItemFood(2, 0.8F, false).resolveItemFood(Potion.potion24.indexOffset3, 5, 0, 1.0F).processAction("spiderEye").processAction2("-0-1+2-3&4-4+13")
      );
      processAction(
         376,
         "fermented_spider_eye",
         new Item().processAction("fermentedSpiderEye").processAction2("-0+3-4+13").processAction(ItemGroupManager.itemGroupManager7)
      );
      processAction(
         377, "blaze_powder", new Item().processAction("blazePowder").processAction2("+0-1-2+3&4-4+13").processAction(ItemGroupManager.itemGroupManager7)
      );
      processAction(
         378, "magma_cream", new Item().processAction("magmaCream").processAction2("+0+1-2-3&4-4+13").processAction(ItemGroupManager.itemGroupManager7)
      );
      processAction(379, "brewing_stand", new ItemComparator(Blocks.block80).processAction("brewingStand").processAction(ItemGroupManager.itemGroupManager7));
      processAction(380, "cauldron", new ItemComparator(Blocks.blockCauldron).processAction("cauldron").processAction(ItemGroupManager.itemGroupManager7));
      processAction(381, "ender_eye", new ItemEnderEye().processAction("eyeOfEnder"));
      processAction(
         382, "speckled_melon", new Item().processAction("speckledMelon").processAction2("+0-1+2-3&4-4+13").processAction(ItemGroupManager.itemGroupManager7)
      );
      processAction(383, "spawn_egg", new ItemSpawnEgg().processAction("monsterPlacer"));
      processAction(384, "experience_bottle", new ItemExperienceBottle().processAction("expBottle"));
      processAction(385, "fire_charge", new ItemFireCharge().processAction("fireball"));
      processAction(386, "writable_book", new ItemWritableBook().processAction("writingBook").processAction(ItemGroupManager.itemGroupManager5));
      processAction(387, "written_book", new ItemWrittenBook().processAction("writtenBook").processAction4(16));
      processAction(388, "emerald", new Item().processAction("emerald").processAction(ItemGroupManager.itemGroupManager11));
      processAction(389, "item_frame", new ItemItemFrame(EntityItemframe.class).processAction("frame"));
      processAction(390, "flower_pot", new ItemComparator(Blocks.block56).processAction("flowerPot").processAction(ItemGroupManager.itemGroupManager8));
      processAction(391, "carrot", new ItemPotato(3, 0.6F, Blocks.block66, Blocks.block7).processAction("carrots"));
      processAction(392, "potato", new ItemPotato(1, 0.3F, Blocks.block20, Blocks.block7).processAction("potato"));
      processAction(393, "baked_potato", new ItemFood(5, 0.6F, false).processAction("potatoBaked"));
      processAction(
         394, "poisonous_potato", new ItemFood(2, 0.3F, false).resolveItemFood(Potion.potion24.indexOffset3, 5, 0, 0.6F).processAction("potatoPoisonous")
      );
      processAction(395, "map", new ItemMap().processAction("emptyMap"));
      processAction(
         396,
         "golden_carrot",
         new ItemFood(6, 1.2F, false).processAction("carrotGolden").processAction2("-0+1+2-3+13&4-4").processAction(ItemGroupManager.itemGroupManager7)
      );
      processAction(397, "skull", new ItemSkull().processAction("skull"));
      processAction(398, "carrot_on_a_stick", new ItemCarrotOnAStick().processAction("carrotOnAStick"));
      processAction(399, "nether_star", new ItemNetherStar().processAction("netherStar").processAction(ItemGroupManager.itemGroupManager11));
      processAction(400, "pumpkin_pie", new ItemFood(8, 0.3F, false).processAction("pumpkinPie").processAction(ItemGroupManager.itemGroupManager3));
      processAction(401, "fireworks", new ItemFireworks().processAction("fireworks"));
      processAction(402, "firework_charge", new ItemFireworkCharge().processAction("fireworksCharge").processAction(ItemGroupManager.itemGroupManager5));
      processAction(403, "enchanted_book", new ItemEnchantedBook().processAction4(1).processAction("enchantedBook"));
      processAction(
         404, "comparator", new ItemComparator(Blocks.blockPoweredComparator2).processAction("comparator").processAction(ItemGroupManager.itemGroupManager9)
      );
      processAction(405, "netherbrick", new Item().processAction("netherbrick").processAction(ItemGroupManager.itemGroupManager11));
      processAction(406, "quartz", new Item().processAction("netherquartz").processAction(ItemGroupManager.itemGroupManager11));
      processAction(407, "tnt_minecart", new ItemCommandBlockMinecart(MovementNumberProperty.TNT).processAction("minecartTnt"));
      processAction(408, "hopper_minecart", new ItemCommandBlockMinecart(MovementNumberProperty.HOPPER).processAction("minecartHopper"));
      processAction(409, "prismarine_shard", new Item().processAction("prismarineShard").processAction(ItemGroupManager.itemGroupManager11));
      processAction(410, "prismarine_crystals", new Item().processAction("prismarineCrystals").processAction(ItemGroupManager.itemGroupManager11));
      processAction(411, "rabbit", new ItemFood(3, 0.3F, true).processAction("rabbitRaw"));
      processAction(412, "cooked_rabbit", new ItemFood(5, 0.6F, true).processAction("rabbitCooked"));
      processAction(413, "rabbit_stew", new ItemRabbitStew(10).processAction("rabbitStew"));
      processAction(
         414, "rabbit_foot", new Item().processAction("rabbitFoot").processAction2("+0+1-2+3&4-4+13").processAction(ItemGroupManager.itemGroupManager7)
      );
      processAction(415, "rabbit_hide", new Item().processAction("rabbitHide").processAction(ItemGroupManager.itemGroupManager11));
      processAction(416, "armor_stand", new ItemArmorStand().processAction("armorStand").processAction4(16));
      processAction(417, "iron_horse_armor", new Item().processAction("horsearmormetal").processAction4(1).processAction(ItemGroupManager.itemGroupManager5));
      processAction(418, "golden_horse_armor", new Item().processAction("horsearmorgold").processAction4(1).processAction(ItemGroupManager.itemGroupManager5));
      processAction(
         419, "diamond_horse_armor", new Item().processAction("horsearmordiamond").processAction4(1).processAction(ItemGroupManager.itemGroupManager5)
      );
      processAction(420, "lead", new ItemLead().processAction("leash"));
      processAction(421, "name_tag", new ItemNameTag().processAction("nameTag"));
      processAction(
         422,
         "command_block_minecart",
         new ItemCommandBlockMinecart(MovementNumberProperty.COMMAND_BLOCK).processAction("minecartCommandBlock").processAction((ItemGroupManager)null)
      );
      processAction(423, "mutton", new ItemFood(2, 0.3F, true).processAction("muttonRaw"));
      processAction(424, "cooked_mutton", new ItemFood(6, 0.8F, true).processAction("muttonCooked"));
      processAction(425, "banner", new ItemBanner().resolveItemBlock("banner"));
      processAction(427, "spruce_door", new ItemDarkOakDoor(Blocks.block36).processAction("doorSpruce"));
      processAction(428, "birch_door", new ItemDarkOakDoor(Blocks.block124).processAction("doorBirch"));
      processAction(429, "jungle_door", new ItemDarkOakDoor(Blocks.block112).processAction("doorJungle"));
      processAction(430, "acacia_door", new ItemDarkOakDoor(Blocks.block84).processAction("doorAcacia"));
      processAction(431, "dark_oak_door", new ItemDarkOakDoor(Blocks.block99).processAction("doorDarkOak"));
      processAction(2256, "record_13", new ItemRecordWait("13").processAction("record"));
      processAction(2257, "record_cat", new ItemRecordWait("cat").processAction("record"));
      processAction(2258, "record_blocks", new ItemRecordWait("blocks").processAction("record"));
      processAction(2259, "record_chirp", new ItemRecordWait("chirp").processAction("record"));
      processAction(2260, "record_far", new ItemRecordWait("far").processAction("record"));
      processAction(2261, "record_mall", new ItemRecordWait("mall").processAction("record"));
      processAction(2262, "record_mellohi", new ItemRecordWait("mellohi").processAction("record"));
      processAction(2263, "record_stal", new ItemRecordWait("stal").processAction("record"));
      processAction(2264, "record_strad", new ItemRecordWait("strad").processAction("record"));
      processAction(2265, "record_ward", new ItemRecordWait("ward").processAction("record"));
      processAction(2266, "record_11", new ItemRecordWait("11").processAction("record"));
      processAction(2267, "record_wait", new ItemRecordWait("wait").processAction("record"));
   }

   public Item processAction2(String text2) {
      this.text = text2;
      return this;
   }

   public boolean processAction(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return false;
   }

   public Item processAction(boolean flag) {
      this.state = flag;
      return this;
   }

   public int processAction2() {
      return 0;
   }

   public boolean processAction(Block block) {
      return false;
   }

   public boolean matchesItemStack(ItemStack stack) {
      return this.getIndexOffset1() == 1 && this.isActive();
   }

   public boolean processAction3() {
      return false;
   }

   public boolean processAction(ItemStack stack, EntityLivingBase livingEntity, EntityLivingBase livingEntity2) {
      return false;
   }

   public Item processAction4() {
      this.state4 = true;
      return this;
   }

   public Multimap processAction5() {
      return HashMultimap.create();
   }

   public static Item getItem(String text2) {
      Item object = (Item)iterableExtensionAdapter.getObject(new ResourceLocation(text2));
      if (object == null) {
         try {
            return processAction(Integer.parseInt(text2));
         } catch (NumberFormatException numberFormatException) {
         }
      }

      return object;
   }

   public ItemStack processAction2(ItemStack stack, World world, EntityPlayer player) {
      return stack;
   }

   public MovingObjectPosition resolveMovingObjectPositionByWorld(World world, EntityPlayer player, boolean flag) {
      float pitch = player.rotationPitch;
      float yaw = player.rotationYaw;
      double posX = player.posX;
      double offset = player.posY + (double)player.getHeight2();
      double posZ = player.posZ;
      float factor = MathHelper.cos(-yaw * (float) (Math.PI / 180.0) - (float) Math.PI);
      float factor2 = MathHelper.abs(-yaw * (float) (Math.PI / 180.0) - (float) Math.PI);
      float factor3 = -MathHelper.cos(-pitch * (float) (Math.PI / 180.0));
      float factor4 = MathHelper.abs(-pitch * (float) (Math.PI / 180.0));
      float factor5 = factor2 * factor3;
      float factor6 = factor * factor3;
      double offset2 = 5.0;
      return world.getMovingObjectPosition4(
         posX, offset, posZ, posX + (double)factor5 * offset2, offset + (double)factor4 * offset2, posZ + (double)factor6 * offset2, flag, !flag, false
      );
   }

   public boolean processAction(ItemStack stack, ItemStack stack2) {
      return false;
   }

   public static void processAction(Block block, Item item2) {
      processAction(Block.getIdFromBlock(block), (ResourceLocation)Block.iterableExtensionBridge.getObject2(block), item2);
      entryMap.put(block, item2);
   }

   public static Item processAction2(Block block) {
      return (Item)entryMap.get(block);
   }

   public boolean processAction(ItemStack stack, World world, Block block, BlockPos blockPos, EntityLivingBase livingEntity) {
      return false;
   }

   public int processAction(ItemStack stack, int count) {
      return 16777215;
   }

   public boolean processAction6() {
      return false;
   }

   public Item getItem() {
      return this.item;
   }

   public ItemNoneType processAction4(ItemStack stack) {
      return ItemNoneType.NONE;
   }

   public ItemStack processAction3(ItemStack stack, World world, EntityPlayer player) {
      return stack;
   }

   public Item processAction(ItemGroupManager itemGroupManager2) {
      this.itemGroupManager = itemGroupManager2;
      return this;
   }

   public int getIndexOffset1() {
      return this.indexOffset1;
   }

   public boolean matchesItemStackByItemStack(ItemStack stack) {
      return this.processAction3(stack) != null;
   }

   public void processAction(ItemStack stack, EntityPlayer player, List list, boolean flag) {
   }

   public boolean processAction7() {
      return false;
   }

   public Item processAction4(int count) {
      this.indexOffset1 = count;
      return this;
   }

   public String resolveText(ItemStack stack) {
      return I18n.getString2(this.resolveTextByItemStack(stack) + ".name").trim();
   }

   public void processAction(ItemStack stack, World world, EntityPlayer player, int count) {
   }

   private static void processAction3(Block block) {
      processAction(block, new ItemBlock(block));
   }

   public int getIndexOffset2() {
      return this.indexOffset2;
   }

   public Item processAction(Item item2) {
      this.item = item2;
      return this;
   }
}
