package net.minecraft.stats;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ResourceLocation;

public class StatList {
   public static Map oneShotStats = Maps.newHashMap();
   public static List allStats = Lists.newArrayList();
   public static List generalStats = Lists.newArrayList();
   public static List itemStats = Lists.newArrayList();
   public static List objectMineStats = Lists.newArrayList();
   public static StatBase leaveGameStat = new StatBasic("stat.leaveGame", new ChatComponentTranslation("stat.leaveGame", new Object[0]))
      .initIndependentStat()
      .registerStat();
   public static StatBase minutesPlayedStat = new StatBasic(
         "stat.playOneMinute", new ChatComponentTranslation("stat.playOneMinute", new Object[0]), StatBase.timeStatType
      )
      .initIndependentStat()
      .registerStat();
   public static StatBase timeSinceDeathStat = new StatBasic(
         "stat.timeSinceDeath", new ChatComponentTranslation("stat.timeSinceDeath", new Object[0]), StatBase.timeStatType
      )
      .initIndependentStat()
      .registerStat();
   public static StatBase distanceWalkedStat = new StatBasic(
         "stat.walkOneCm", new ChatComponentTranslation("stat.walkOneCm", new Object[0]), StatBase.distanceStatType
      )
      .initIndependentStat()
      .registerStat();
   public static StatBase distanceCrouchedStat = new StatBasic(
         "stat.crouchOneCm", new ChatComponentTranslation("stat.crouchOneCm", new Object[0]), StatBase.distanceStatType
      )
      .initIndependentStat()
      .registerStat();
   public static StatBase distanceSprintedStat = new StatBasic(
         "stat.sprintOneCm", new ChatComponentTranslation("stat.sprintOneCm", new Object[0]), StatBase.distanceStatType
      )
      .initIndependentStat()
      .registerStat();
   public static StatBase distanceSwumStat = new StatBasic(
         "stat.swimOneCm", new ChatComponentTranslation("stat.swimOneCm", new Object[0]), StatBase.distanceStatType
      )
      .initIndependentStat()
      .registerStat();
   public static StatBase distanceFallenStat = new StatBasic(
         "stat.fallOneCm", new ChatComponentTranslation("stat.fallOneCm", new Object[0]), StatBase.distanceStatType
      )
      .initIndependentStat()
      .registerStat();
   public static StatBase distanceClimbedStat = new StatBasic(
         "stat.climbOneCm", new ChatComponentTranslation("stat.climbOneCm", new Object[0]), StatBase.distanceStatType
      )
      .initIndependentStat()
      .registerStat();
   public static StatBase distanceFlownStat = new StatBasic(
         "stat.flyOneCm", new ChatComponentTranslation("stat.flyOneCm", new Object[0]), StatBase.distanceStatType
      )
      .initIndependentStat()
      .registerStat();
   public static StatBase distanceDoveStat = new StatBasic(
         "stat.diveOneCm", new ChatComponentTranslation("stat.diveOneCm", new Object[0]), StatBase.distanceStatType
      )
      .initIndependentStat()
      .registerStat();
   public static StatBase distanceByMinecartStat = new StatBasic(
         "stat.minecartOneCm", new ChatComponentTranslation("stat.minecartOneCm", new Object[0]), StatBase.distanceStatType
      )
      .initIndependentStat()
      .registerStat();
   public static StatBase distanceByBoatStat = new StatBasic(
         "stat.boatOneCm", new ChatComponentTranslation("stat.boatOneCm", new Object[0]), StatBase.distanceStatType
      )
      .initIndependentStat()
      .registerStat();
   public static StatBase distanceByPigStat = new StatBasic(
         "stat.pigOneCm", new ChatComponentTranslation("stat.pigOneCm", new Object[0]), StatBase.distanceStatType
      )
      .initIndependentStat()
      .registerStat();
   public static StatBase distanceByHorseStat = new StatBasic(
         "stat.horseOneCm", new ChatComponentTranslation("stat.horseOneCm", new Object[0]), StatBase.distanceStatType
      )
      .initIndependentStat()
      .registerStat();
   public static StatBase jumpStat = new StatBasic("stat.jump", new ChatComponentTranslation("stat.jump", new Object[0])).initIndependentStat().registerStat();
   public static StatBase dropStat = new StatBasic("stat.drop", new ChatComponentTranslation("stat.drop", new Object[0])).initIndependentStat().registerStat();
   public static StatBase damageDealtStat = new StatBasic("stat.damageDealt", new ChatComponentTranslation("stat.damageDealt", new Object[0]), StatBase.divideByTen)
      .registerStat();
   public static StatBase damageTakenStat = new StatBasic("stat.damageTaken", new ChatComponentTranslation("stat.damageTaken", new Object[0]), StatBase.divideByTen)
      .registerStat();
   public static StatBase deathsStat = new StatBasic("stat.deaths", new ChatComponentTranslation("stat.deaths", new Object[0])).registerStat();
   public static StatBase mobKillsStat = new StatBasic("stat.mobKills", new ChatComponentTranslation("stat.mobKills", new Object[0])).registerStat();
   public static StatBase animalsBredStat = new StatBasic("stat.animalsBred", new ChatComponentTranslation("stat.animalsBred", new Object[0])).registerStat();
   public static StatBase playerKillsStat = new StatBasic("stat.playerKills", new ChatComponentTranslation("stat.playerKills", new Object[0])).registerStat();
   public static StatBase fishCaughtStat = new StatBasic("stat.fishCaught", new ChatComponentTranslation("stat.fishCaught", new Object[0])).registerStat();
   public static StatBase junkFishedStat = new StatBasic("stat.junkFished", new ChatComponentTranslation("stat.junkFished", new Object[0])).registerStat();
   public static StatBase treasureFishedStat = new StatBasic("stat.treasureFished", new ChatComponentTranslation("stat.treasureFished", new Object[0]))
      .registerStat();
   public static StatBase timesTalkedToVillagerStat = new StatBasic(
         "stat.talkedToVillager", new ChatComponentTranslation("stat.talkedToVillager", new Object[0])
      )
      .registerStat();
   public static StatBase timesTradedWithVillagerStat = new StatBasic(
         "stat.tradedWithVillager", new ChatComponentTranslation("stat.tradedWithVillager", new Object[0])
      )
      .registerStat();
   public static StatBase cakeSlicesEaten = new StatBasic("stat.cakeSlicesEaten", new ChatComponentTranslation("stat.cakeSlicesEaten", new Object[0])).registerStat();
   public static StatBase cauldronFilled = new StatBasic("stat.cauldronFilled", new ChatComponentTranslation("stat.cauldronFilled", new Object[0])).registerStat();
   public static StatBase cauldronUsed = new StatBasic("stat.cauldronUsed", new ChatComponentTranslation("stat.cauldronUsed", new Object[0])).registerStat();
   public static StatBase armorCleaned = new StatBasic("stat.armorCleaned", new ChatComponentTranslation("stat.armorCleaned", new Object[0])).registerStat();
   public static StatBase bannerCleaned = new StatBasic("stat.bannerCleaned", new ChatComponentTranslation("stat.bannerCleaned", new Object[0])).registerStat();
   public static StatBase brewingstandInteraction = new StatBasic("stat.brewingstandInteraction", new ChatComponentTranslation("stat.brewingstandInteraction", new Object[0]))
      .registerStat();
   public static StatBase beaconInteraction = new StatBasic("stat.beaconInteraction", new ChatComponentTranslation("stat.beaconInteraction", new Object[0])).registerStat();
   public static StatBase dropperInspected = new StatBasic("stat.dropperInspected", new ChatComponentTranslation("stat.dropperInspected", new Object[0])).registerStat();
   public static StatBase hopperInspected = new StatBasic("stat.hopperInspected", new ChatComponentTranslation("stat.hopperInspected", new Object[0])).registerStat();
   public static StatBase dispenserInspected = new StatBasic("stat.dispenserInspected", new ChatComponentTranslation("stat.dispenserInspected", new Object[0]))
      .registerStat();
   public static StatBase noteblockPlayed = new StatBasic("stat.noteblockPlayed", new ChatComponentTranslation("stat.noteblockPlayed", new Object[0])).registerStat();
   public static StatBase noteblockTuned = new StatBasic("stat.noteblockTuned", new ChatComponentTranslation("stat.noteblockTuned", new Object[0])).registerStat();
   public static StatBase flowerPotted = new StatBasic("stat.flowerPotted", new ChatComponentTranslation("stat.flowerPotted", new Object[0])).registerStat();
   public static StatBase trappedChestTriggered = new StatBasic("stat.trappedChestTriggered", new ChatComponentTranslation("stat.trappedChestTriggered", new Object[0]))
      .registerStat();
   public static StatBase enderchestOpened = new StatBasic("stat.enderchestOpened", new ChatComponentTranslation("stat.enderchestOpened", new Object[0])).registerStat();
   public static StatBase itemEnchanted = new StatBasic("stat.itemEnchanted", new ChatComponentTranslation("stat.itemEnchanted", new Object[0])).registerStat();
   public static StatBase recordPlayed = new StatBasic("stat.recordPlayed", new ChatComponentTranslation("stat.recordPlayed", new Object[0])).registerStat();
   public static StatBase furnaceInteraction = new StatBasic("stat.furnaceInteraction", new ChatComponentTranslation("stat.furnaceInteraction", new Object[0]))
      .registerStat();
   public static StatBase craftingTableInteraction = new StatBasic("stat.craftingTableInteraction", new ChatComponentTranslation("stat.workbenchInteraction", new Object[0]))
      .registerStat();
   public static StatBase chestOpened = new StatBasic("stat.chestOpened", new ChatComponentTranslation("stat.chestOpened", new Object[0])).registerStat();
   public static final StatBase[] mineBlockStatArray = new StatBase[4096];
   public static final StatBase[] objectCraftStats = new StatBase[32000];
   public static final StatBase[] objectUseStats = new StatBase[32000];
   public static final StatBase[] objectBreakStats = new StatBase[32000];

   public static StatBase getStatKillEntity(EntityList.EntityEggInfo entityEggInfo) {
      String stringFromID = EntityList.getStringFromID(entityEggInfo.spawnedID);
      return stringFromID == null
         ? null
         : new StatBase(
               "stat.killEntity." + stringFromID,
               new ChatComponentTranslation("stat.entityKill", new Object[]{new ChatComponentTranslation("entity." + stringFromID + ".name", new Object[0])})
            )
            .registerStat();
   }

   private static void initStats() {
      for (Item item : (Iterable<Item>)(Item.itemRegistry)) {
         if (item != null) {
            int idFromItem = Item.getIdFromItem(item);
            String itemName = getItemName(item);
            if (itemName != null) {
               objectUseStats[idFromItem] = new StatCrafting(
                     "stat.useItem.", itemName, new ChatComponentTranslation("stat.useItem", new Object[]{new ItemStack(item).getChatComponent()}), item
                  )
                  .registerStat();
               if (!(item instanceof ItemBlock)) {
                  itemStats.add((StatCrafting)objectUseStats[idFromItem]);
               }
            }
         }
      }

      replaceAllSimilarBlocks(objectUseStats);
   }

   private static void replaceAllSimilarBlocks(StatBase[] statBases) {
      mergeStatBases(statBases, Blocks.water, Blocks.flowing_water);
      mergeStatBases(statBases, Blocks.lava, Blocks.flowing_lava);
      mergeStatBases(statBases, Blocks.lit_pumpkin, Blocks.pumpkin);
      mergeStatBases(statBases, Blocks.lit_furnace, Blocks.furnace);
      mergeStatBases(statBases, Blocks.lit_redstone_ore, Blocks.redstone_ore);
      mergeStatBases(statBases, Blocks.powered_repeater, Blocks.unpowered_repeater);
      mergeStatBases(statBases, Blocks.powered_comparator, Blocks.unpowered_comparator);
      mergeStatBases(statBases, Blocks.redstone_torch, Blocks.unlit_redstone_torch);
      mergeStatBases(statBases, Blocks.lit_redstone_lamp, Blocks.redstone_lamp);
      mergeStatBases(statBases, Blocks.double_stone_slab, Blocks.stone_slab);
      mergeStatBases(statBases, Blocks.double_wooden_slab, Blocks.wooden_slab);
      mergeStatBases(statBases, Blocks.double_stone_slab2, Blocks.stone_slab2);
      mergeStatBases(statBases, Blocks.grass, Blocks.dirt);
      mergeStatBases(statBases, Blocks.farmland, Blocks.dirt);
   }

   public static void init() {
      initMiningStats();
      initStats();
      initItemDepleteStats();
      initCraftableStats();
      AchievementList.init();
      EntityList.init();
   }

   private static void mergeStatBases(StatBase[] statBases, Block block, Block block2) {
      int idFromBlock = Block.getIdFromBlock(block);
      int idFromBlock2 = Block.getIdFromBlock(block2);
      if (statBases[idFromBlock] != null && statBases[idFromBlock2] == null) {
         statBases[idFromBlock2] = statBases[idFromBlock];
      } else {
         allStats.remove(statBases[idFromBlock]);
         objectMineStats.remove(statBases[idFromBlock]);
         generalStats.remove(statBases[idFromBlock]);
         statBases[idFromBlock] = statBases[idFromBlock2];
      }
   }

   private static void initMiningStats() {
      for (Block block : (Iterable<Block>)(Block.blockRegistry)) {
         Item itemFromBlock = Item.getItemFromBlock(block);
         if (itemFromBlock != null) {
            int idFromBlock = Block.getIdFromBlock(block);
            String itemName = getItemName(itemFromBlock);
            if (itemName != null && block.getEnableStats()) {
               mineBlockStatArray[idFromBlock] = new StatCrafting(
                     "stat.mineBlock.", itemName, new ChatComponentTranslation("stat.mineBlock", new Object[]{new ItemStack(block).getChatComponent()}), itemFromBlock
                  )
                  .registerStat();
               objectMineStats.add((StatCrafting)mineBlockStatArray[idFromBlock]);
            }
         }
      }

      replaceAllSimilarBlocks(mineBlockStatArray);
   }

   public static StatBase getStatEntityKilledBy(EntityList.EntityEggInfo entityEggInfo) {
      String stringFromID = EntityList.getStringFromID(entityEggInfo.spawnedID);
      return stringFromID == null
         ? null
         : new StatBase(
               "stat.entityKilledBy." + stringFromID,
               new ChatComponentTranslation("stat.entityKilledBy", new Object[]{new ChatComponentTranslation("entity." + stringFromID + ".name", new Object[0])})
            )
            .registerStat();
   }

   private static void initItemDepleteStats() {
      for (Item item : (Iterable<Item>)(Item.itemRegistry)) {
         if (item != null) {
            int idFromItem = Item.getIdFromItem(item);
            String itemName = getItemName(item);
            if (itemName != null && item.isDamageable()) {
               objectBreakStats[idFromItem] = new StatCrafting(
                     "stat.breakItem.", itemName, new ChatComponentTranslation("stat.breakItem", new Object[]{new ItemStack(item).getChatComponent()}), item
                  )
                  .registerStat();
            }
         }
      }

      replaceAllSimilarBlocks(objectBreakStats);
   }

   private static String getItemName(Item item) {
      ResourceLocation nameForObject = (ResourceLocation)Item.itemRegistry.getNameForObject(item);
      return nameForObject != null ? nameForObject.toString().replace(':', '.') : null;
   }

   private static void initCraftableStats() {
      HashSet set = Sets.newHashSet();

      for (IRecipe recipe : (Iterable<IRecipe>)(CraftingManager.getInstance().getRecipeList())) {
         if (recipe.getRecipeOutput() != null) {
            set.add(recipe.getRecipeOutput().getItem());
         }
      }

      for (ItemStack stack : (Iterable<ItemStack>)(FurnaceRecipes.instance().getSmeltingList().values())) {
         set.add(stack.getItem());
      }

      for (Item item : (Iterable<Item>)(set)) {
         if (item != null) {
            int idFromItem = Item.getIdFromItem(item);
            String itemName = getItemName(item);
            if (itemName != null) {
               objectCraftStats[idFromItem] = new StatCrafting(
                     "stat.craftItem.", itemName, new ChatComponentTranslation("stat.craftItem", new Object[]{new ItemStack(item).getChatComponent()}), item
                  )
                  .registerStat();
            }
         }
      }

      replaceAllSimilarBlocks(objectCraftStats);
   }

   public static StatBase getOneShotStat(String str) {
      return (StatBase)oneShotStats.get(str);
   }
}
