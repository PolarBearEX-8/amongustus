package client.util;

import client.event.PreMotionProcessor;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.IronRing;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class StatCraftItemManager {
   public static Map map = Maps.newHashMap();
   public static List list4 = Lists.newArrayList();
   public static List list2 = Lists.newArrayList();
   public static List list3 = Lists.newArrayList();
   public static List list = Lists.newArrayList();
   public static DuplicateStatIdManager duplicateStatIdManager44 = new TargetProvider(
         "stat.leaveGame", new BaseManagerStyleIChatComponentAdapter("stat.leaveGame", new Object[0])
      )
      .getDuplicateStatIdManager()
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager41 = new TargetProvider(
         "stat.playOneMinute", new BaseManagerStyleIChatComponentAdapter("stat.playOneMinute", new Object[0]), DuplicateStatIdManager.preUpdateListener
      )
      .getDuplicateStatIdManager()
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager40 = new TargetProvider(
         "stat.timeSinceDeath", new BaseManagerStyleIChatComponentAdapter("stat.timeSinceDeath", new Object[0]), DuplicateStatIdManager.preUpdateListener
      )
      .getDuplicateStatIdManager()
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager18 = new TargetProvider(
         "stat.walkOneCm", new BaseManagerStyleIChatComponentAdapter("stat.walkOneCm", new Object[0]), DuplicateStatIdManager.preUpdateListener2
      )
      .getDuplicateStatIdManager()
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager11 = new TargetProvider(
         "stat.crouchOneCm", new BaseManagerStyleIChatComponentAdapter("stat.crouchOneCm", new Object[0]), DuplicateStatIdManager.preUpdateListener2
      )
      .getDuplicateStatIdManager()
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager35 = new TargetProvider(
         "stat.sprintOneCm", new BaseManagerStyleIChatComponentAdapter("stat.sprintOneCm", new Object[0]), DuplicateStatIdManager.preUpdateListener2
      )
      .getDuplicateStatIdManager()
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager7 = new TargetProvider(
         "stat.swimOneCm", new BaseManagerStyleIChatComponentAdapter("stat.swimOneCm", new Object[0]), DuplicateStatIdManager.preUpdateListener2
      )
      .getDuplicateStatIdManager()
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager23 = new TargetProvider(
         "stat.fallOneCm", new BaseManagerStyleIChatComponentAdapter("stat.fallOneCm", new Object[0]), DuplicateStatIdManager.preUpdateListener2
      )
      .getDuplicateStatIdManager()
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager20 = new TargetProvider(
         "stat.climbOneCm", new BaseManagerStyleIChatComponentAdapter("stat.climbOneCm", new Object[0]), DuplicateStatIdManager.preUpdateListener2
      )
      .getDuplicateStatIdManager()
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager8 = new TargetProvider(
         "stat.flyOneCm", new BaseManagerStyleIChatComponentAdapter("stat.flyOneCm", new Object[0]), DuplicateStatIdManager.preUpdateListener2
      )
      .getDuplicateStatIdManager()
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager37 = new TargetProvider(
         "stat.diveOneCm", new BaseManagerStyleIChatComponentAdapter("stat.diveOneCm", new Object[0]), DuplicateStatIdManager.preUpdateListener2
      )
      .getDuplicateStatIdManager()
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager5 = new TargetProvider(
         "stat.minecartOneCm", new BaseManagerStyleIChatComponentAdapter("stat.minecartOneCm", new Object[0]), DuplicateStatIdManager.preUpdateListener2
      )
      .getDuplicateStatIdManager()
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager4 = new TargetProvider(
         "stat.boatOneCm", new BaseManagerStyleIChatComponentAdapter("stat.boatOneCm", new Object[0]), DuplicateStatIdManager.preUpdateListener2
      )
      .getDuplicateStatIdManager()
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager25 = new TargetProvider(
         "stat.pigOneCm", new BaseManagerStyleIChatComponentAdapter("stat.pigOneCm", new Object[0]), DuplicateStatIdManager.preUpdateListener2
      )
      .getDuplicateStatIdManager()
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager21 = new TargetProvider(
         "stat.horseOneCm", new BaseManagerStyleIChatComponentAdapter("stat.horseOneCm", new Object[0]), DuplicateStatIdManager.preUpdateListener2
      )
      .getDuplicateStatIdManager()
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager30 = new TargetProvider(
         "stat.jump", new BaseManagerStyleIChatComponentAdapter("stat.jump", new Object[0])
      )
      .getDuplicateStatIdManager()
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager36 = new TargetProvider(
         "stat.drop", new BaseManagerStyleIChatComponentAdapter("stat.drop", new Object[0])
      )
      .getDuplicateStatIdManager()
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager46 = new TargetProvider(
         "stat.damageDealt", new BaseManagerStyleIChatComponentAdapter("stat.damageDealt", new Object[0]), DuplicateStatIdManager.preUpdateListener3
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager15 = new TargetProvider(
         "stat.damageTaken", new BaseManagerStyleIChatComponentAdapter("stat.damageTaken", new Object[0]), DuplicateStatIdManager.preUpdateListener3
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager42 = new TargetProvider(
         "stat.deaths", new BaseManagerStyleIChatComponentAdapter("stat.deaths", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager9 = new TargetProvider(
         "stat.mobKills", new BaseManagerStyleIChatComponentAdapter("stat.mobKills", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager10 = new TargetProvider(
         "stat.animalsBred", new BaseManagerStyleIChatComponentAdapter("stat.animalsBred", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager14 = new TargetProvider(
         "stat.playerKills", new BaseManagerStyleIChatComponentAdapter("stat.playerKills", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager34 = new TargetProvider(
         "stat.fishCaught", new BaseManagerStyleIChatComponentAdapter("stat.fishCaught", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager45 = new TargetProvider(
         "stat.junkFished", new BaseManagerStyleIChatComponentAdapter("stat.junkFished", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager29 = new TargetProvider(
         "stat.treasureFished", new BaseManagerStyleIChatComponentAdapter("stat.treasureFished", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager26 = new TargetProvider(
         "stat.talkedToVillager", new BaseManagerStyleIChatComponentAdapter("stat.talkedToVillager", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager17 = new TargetProvider(
         "stat.tradedWithVillager", new BaseManagerStyleIChatComponentAdapter("stat.tradedWithVillager", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager19 = new TargetProvider(
         "stat.cakeSlicesEaten", new BaseManagerStyleIChatComponentAdapter("stat.cakeSlicesEaten", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager38 = new TargetProvider(
         "stat.cauldronFilled", new BaseManagerStyleIChatComponentAdapter("stat.cauldronFilled", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager16 = new TargetProvider(
         "stat.cauldronUsed", new BaseManagerStyleIChatComponentAdapter("stat.cauldronUsed", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager48 = new TargetProvider(
         "stat.armorCleaned", new BaseManagerStyleIChatComponentAdapter("stat.armorCleaned", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager47 = new TargetProvider(
         "stat.bannerCleaned", new BaseManagerStyleIChatComponentAdapter("stat.bannerCleaned", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager12 = new TargetProvider(
         "stat.brewingstandInteraction", new BaseManagerStyleIChatComponentAdapter("stat.brewingstandInteraction", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager43 = new TargetProvider(
         "stat.beaconInteraction", new BaseManagerStyleIChatComponentAdapter("stat.beaconInteraction", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager3 = new TargetProvider(
         "stat.dropperInspected", new BaseManagerStyleIChatComponentAdapter("stat.dropperInspected", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager = new TargetProvider(
         "stat.hopperInspected", new BaseManagerStyleIChatComponentAdapter("stat.hopperInspected", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager33 = new TargetProvider(
         "stat.dispenserInspected", new BaseManagerStyleIChatComponentAdapter("stat.dispenserInspected", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager24 = new TargetProvider(
         "stat.noteblockPlayed", new BaseManagerStyleIChatComponentAdapter("stat.noteblockPlayed", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager13 = new TargetProvider(
         "stat.noteblockTuned", new BaseManagerStyleIChatComponentAdapter("stat.noteblockTuned", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager28 = new TargetProvider(
         "stat.flowerPotted", new BaseManagerStyleIChatComponentAdapter("stat.flowerPotted", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager27 = new TargetProvider(
         "stat.trappedChestTriggered", new BaseManagerStyleIChatComponentAdapter("stat.trappedChestTriggered", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager32 = new TargetProvider(
         "stat.enderchestOpened", new BaseManagerStyleIChatComponentAdapter("stat.enderchestOpened", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager22 = new TargetProvider(
         "stat.itemEnchanted", new BaseManagerStyleIChatComponentAdapter("stat.itemEnchanted", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager31 = new TargetProvider(
         "stat.recordPlayed", new BaseManagerStyleIChatComponentAdapter("stat.recordPlayed", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager2 = new TargetProvider(
         "stat.furnaceInteraction", new BaseManagerStyleIChatComponentAdapter("stat.furnaceInteraction", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager39 = new TargetProvider(
         "stat.craftingTableInteraction", new BaseManagerStyleIChatComponentAdapter("stat.workbenchInteraction", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static DuplicateStatIdManager duplicateStatIdManager6 = new TargetProvider(
         "stat.chestOpened", new BaseManagerStyleIChatComponentAdapter("stat.chestOpened", new Object[0])
      )
      .getDuplicateStatIdManagerAsDuplicateStatIdM();
   public static final DuplicateStatIdManager[] duplicateStatIdManagerArray4 = new DuplicateStatIdManager[4096];
   public static final DuplicateStatIdManager[] duplicateStatIdManagerArray = new DuplicateStatIdManager[32000];
   public static final DuplicateStatIdManager[] duplicateStatIdManagerArray2 = new DuplicateStatIdManager[32000];
   public static final DuplicateStatIdManager[] duplicateStatIdManagerArray3 = new DuplicateStatIdManager[32000];

   private static void updateState3() {
      HashSet set = Sets.newHashSet();

      for (PreMotionProcessor preMotionProcessor : (Iterable<PreMotionProcessor>)(Iterable<?>)ExtendedManager.getExtendedManager().getList()) {
         if (preMotionProcessor.getItemStack() != null) {
            set.add(preMotionProcessor.getItemStack().getItem());
         }
      }

      for (ItemStack stack : (Iterable<ItemStack>)(Iterable<?>)IronRing.getIronRing().getMap2().values()) {
         set.add(stack.getItem());
      }

      for (Item item : (Iterable<Item>)(Iterable<?>)set) {
         if (item != null) {
            int index = Item.getIndex(item);
            String text = resolveText(item);
            if (text != null) {
               duplicateStatIdManagerArray[index] = new DuplicateStatIdManagerAdapter(
                     "stat.craftItem.",
                     text,
                     new BaseManagerStyleIChatComponentAdapter("stat.craftItem", new Object[]{new ItemStack(item).getIChatComponent()}),
                     item
                  )
                  .getDuplicateStatIdManagerAsDuplicateStatIdM();
            }
         }
      }

      applyDuplicateStatIdManagerArra(duplicateStatIdManagerArray);
   }

   private static void updateState() {
      for (Item item : (Iterable<Item>)(Iterable<?>)Item.iterableExtensionAdapter) {
         if (item != null) {
            int index = Item.getIndex(item);
            String text = resolveText(item);
            if (text != null && item.isActive()) {
               duplicateStatIdManagerArray3[index] = new DuplicateStatIdManagerAdapter(
                     "stat.breakItem.",
                     text,
                     new BaseManagerStyleIChatComponentAdapter("stat.breakItem", new Object[]{new ItemStack(item).getIChatComponent()}),
                     item
                  )
                  .getDuplicateStatIdManagerAsDuplicateStatIdM();
            }
         }
      }

      applyDuplicateStatIdManagerArra(duplicateStatIdManagerArray3);
   }

   public static DuplicateStatIdManager resolveDuplicateStatIdManagerByDuplicateStatIdM(DuplicateStatIdManagerEntry duplicateStatIdManagerEntry) {
      String text = SkippingEntityWithIdManager.resolveText(duplicateStatIdManagerEntry.count2);
      return text == null
         ? null
         : new DuplicateStatIdManager(
               "stat.killEntity." + text,
               new BaseManagerStyleIChatComponentAdapter(
                  "stat.entityKill", new Object[]{new BaseManagerStyleIChatComponentAdapter("entity." + text + ".name", new Object[0])}
               )
            )
            .getDuplicateStatIdManagerAsDuplicateStatIdM();
   }

   public static DuplicateStatIdManager resolveDuplicateStatIdManager(DuplicateStatIdManagerEntry duplicateStatIdManagerEntry) {
      String text = SkippingEntityWithIdManager.resolveText(duplicateStatIdManagerEntry.count2);
      return text == null
         ? null
         : new DuplicateStatIdManager(
               "stat.entityKilledBy." + text,
               new BaseManagerStyleIChatComponentAdapter(
                  "stat.entityKilledBy", new Object[]{new BaseManagerStyleIChatComponentAdapter("entity." + text + ".name", new Object[0])}
               )
            )
            .getDuplicateStatIdManagerAsDuplicateStatIdM();
   }

   public static DuplicateStatIdManager get(String text) {
      return (DuplicateStatIdManager)map.get(text);
   }

   private static void updateState5() {
      for (Item item : (Iterable<Item>)(Iterable<?>)Item.iterableExtensionAdapter) {
         if (item != null) {
            int index = Item.getIndex(item);
            String text = resolveText(item);
            if (text != null) {
               duplicateStatIdManagerArray2[index] = new DuplicateStatIdManagerAdapter(
                     "stat.useItem.",
                     text,
                     new BaseManagerStyleIChatComponentAdapter("stat.useItem", new Object[]{new ItemStack(item).getIChatComponent()}),
                     item
                  )
                  .getDuplicateStatIdManagerAsDuplicateStatIdM();
               if (!(item instanceof ItemBlock)) {
                  list3.add((DuplicateStatIdManagerAdapter)duplicateStatIdManagerArray2[index]);
               }
            }
         }
      }

      applyDuplicateStatIdManagerArra(duplicateStatIdManagerArray2);
   }

   private static void applyDuplicateStatIdManagerArra(DuplicateStatIdManager[] duplicateStatIdManagerArray5, Block block, Block block2) {
      int idFromBlock = Block.getIdFromBlock(block);
      int idFromBlock2 = Block.getIdFromBlock(block2);
      if (duplicateStatIdManagerArray5[idFromBlock] != null && duplicateStatIdManagerArray5[idFromBlock2] == null) {
         duplicateStatIdManagerArray5[idFromBlock2] = duplicateStatIdManagerArray5[idFromBlock];
      } else {
         list4.remove(duplicateStatIdManagerArray5[idFromBlock]);
         list.remove(duplicateStatIdManagerArray5[idFromBlock]);
         list2.remove(duplicateStatIdManagerArray5[idFromBlock]);
         duplicateStatIdManagerArray5[idFromBlock] = duplicateStatIdManagerArray5[idFromBlock2];
      }
   }

   private static String resolveText(Item item) {
      ResourceLocation object = (ResourceLocation)Item.iterableExtensionAdapter.getObject2(item);
      return object != null ? object.toString().replace(':', '.') : null;
   }

   private static void applyDuplicateStatIdManagerArra(DuplicateStatIdManager[] duplicateStatIdManagerArray5) {
      applyDuplicateStatIdManagerArra(duplicateStatIdManagerArray5, Blocks.blockLava2, Blocks.blockFlowingLava);
      applyDuplicateStatIdManagerArra(duplicateStatIdManagerArray5, Blocks.blockLava, Blocks.blockFlowingLava2);
      applyDuplicateStatIdManagerArra(duplicateStatIdManagerArray5, Blocks.block120, Blocks.block146);
      applyDuplicateStatIdManagerArra(duplicateStatIdManagerArray5, Blocks.block88, Blocks.block39);
      applyDuplicateStatIdManagerArra(duplicateStatIdManagerArray5, Blocks.block, Blocks.block25);
      applyDuplicateStatIdManagerArra(duplicateStatIdManagerArray5, Blocks.blockPoweredRepeater2, Blocks.blockPoweredRepeater);
      applyDuplicateStatIdManagerArra(duplicateStatIdManagerArray5, Blocks.blockPoweredComparator, Blocks.blockPoweredComparator2);
      applyDuplicateStatIdManagerArra(duplicateStatIdManagerArray5, Blocks.block12, Blocks.block10);
      applyDuplicateStatIdManagerArra(duplicateStatIdManagerArray5, Blocks.block49, Blocks.block64);
      applyDuplicateStatIdManagerArra(duplicateStatIdManagerArray5, Blocks.extraBlockAdapter5, Blocks.extraBlockAdapter6);
      applyDuplicateStatIdManagerArra(duplicateStatIdManagerArray5, Blocks.extraBlockAdapter, Blocks.extraBlockAdapter2);
      applyDuplicateStatIdManagerArra(duplicateStatIdManagerArray5, Blocks.extraBlockAdapter3, Blocks.extraBlockAdapter4);
      applyDuplicateStatIdManagerArra(duplicateStatIdManagerArray5, Blocks.blockGrass, Blocks.block115);
      applyDuplicateStatIdManagerArra(duplicateStatIdManagerArray5, Blocks.block7, Blocks.block115);
   }

   private static void updateState4() {
      for (Block block : (Iterable<Block>)(Iterable<?>)Block.iterableExtensionBridge) {
         Item item = Item.processAction2(block);
         if (item != null) {
            int idFromBlock = Block.getIdFromBlock(block);
            String text = resolveText(item);
            if (text != null && block.isState3()) {
               duplicateStatIdManagerArray4[idFromBlock] = new DuplicateStatIdManagerAdapter(
                     "stat.mineBlock.",
                     text,
                     new BaseManagerStyleIChatComponentAdapter("stat.mineBlock", new Object[]{new ItemStack(block).getIChatComponent()}),
                     item
                  )
                  .getDuplicateStatIdManagerAsDuplicateStatIdM();
               list.add((DuplicateStatIdManagerAdapter)duplicateStatIdManagerArray4[idFromBlock]);
            }
         }
      }

      applyDuplicateStatIdManagerArra(duplicateStatIdManagerArray4);
   }

   public static void updateState2() {
      updateState4();
      updateState5();
      updateState();
      updateState3();
      AchievementTheEnd2Manager.updateState();
      SkippingEntityWithIdManager.updateState();
   }
}
