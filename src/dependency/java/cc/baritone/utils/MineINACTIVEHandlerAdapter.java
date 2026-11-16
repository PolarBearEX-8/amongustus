package cc.baritone.utils;

import cc.baritone.api.ExtendedINACTIVEHandlerExtension;
import cc.baritone.api.INACTIVEHandlerAdapter;
import cc.baritone.api.ItemStackEntry;
import client.util.IBlockState;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.Blocks;
import net.minecraft.block.CustomBlockAdapter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;

public final class MineINACTIVEHandlerAdapter extends INACTIVEHandlerAdapter implements ExtendedINACTIVEHandlerExtension {
   public static final int count2 = 64;
   public HeuristicSystem heuristicSystem;
   public List list;
   public List list2;
   public Map map;
   public BlockPos blockPos;
   public GoalRunAwayFromListener goalRunAwayFromListener;
   public int count;
   public int count3;

   private static PrimaryCustom[] resolvePrimaryCustomArrayByIndex(int count4) {
      return new PrimaryCustom[count4];
   }

   private static boolean matchesBlockPosByBlockPos(BlockPos blockPos2) {
      return blockPos2.getY() <= (Integer)BaritoneManager.getSettings().maxYLevelWhileMining.dataRef;
   }

   @Override
   public String getText() {
      return "Mine " + this.heuristicSystem;
   }

   private SecondaryListener resolveSecondaryListener(BlockPos blockPos2, List list3, ItemStackEntry itemStackEntry) {
      boolean flag = !(this.baritoneManager.heuristicManager.resolveIBlockState(blockPos2.up()).getBlock() instanceof CustomBlockAdapter);
      if (!(Boolean)BaritoneManager.getSettings().forceInternalMining.dataRef) {
         return (SecondaryListener)(flag ? new DynamicCustom(blockPos2) : new HeuristicWatcher(blockPos2));
      } else {
         boolean flag2 = this.matchesBlockPos(blockPos2.up(), itemStackEntry, list3);
         boolean flag3 = this.matchesBlockPos(blockPos2.down(), itemStackEntry, list3);
         boolean flag4 = this.matchesBlockPos(blockPos2.resolveBlockPos4(2), itemStackEntry, list3);
         if (flag2 == flag3) {
            return (SecondaryListener)(flag4 && flag ? new DynamicCustom(blockPos2) : new HeuristicWatcher(blockPos2));
         } else if (flag2) {
            return new DepthFirstResolver(blockPos2);
         } else {
            return (SecondaryListener)(flag4 && flag ? new HeuristicWatcher(blockPos2.down()) : new DepthFirstResolver(blockPos2.down()));
         }
      }
   }

   private static boolean matchesItemStackEntry2(ItemStackEntry itemStackEntry, BlockPos blockPos2) {
      return (Boolean)BaritoneManager.getSettings().allowOnlyExposedOres.dataRef ? matchesItemStackEntry3(itemStackEntry, blockPos2) : true;
   }

   private void applyList(List list3, ItemStackEntry itemStackEntry) {
      HeuristicSystem heuristicSystem2 = this.getHeuristicSystem();
      if (heuristicSystem2 != null) {
         if (!(Boolean)BaritoneManager.getSettings().legitMine.dataRef) {
            List list4 = this.getList();
            List list5 = resolveList(itemStackEntry, heuristicSystem2, 64, list3, this.list2, list4);
            list5.addAll(list4);
            if (list5.isEmpty() && !(Boolean)BaritoneManager.getSettings().exploreForBlocks.dataRef) {
               this.applyText("No locations for " + heuristicSystem2 + " known, cancelling");
               if ((Boolean)BaritoneManager.getSettings().notificationOnMineFail.dataRef) {
                  this.applyText("No locations for " + heuristicSystem2 + " known, cancelling", true);
               }

               this.updateState2();
            } else {
               this.list = list5;
            }
         }
      }
   }

   private void applyMap(Map map2, BlockPos blockPos2) {
      if (this.list.contains(blockPos2)) {
         map2.put(blockPos2, System.currentTimeMillis() + (Long)BaritoneManager.getSettings().mineDropLoiterDurationMSThanksLouca.dataRef);
      }
   }

   private static boolean matchesItemStackEntry(ItemStackEntry itemStackEntry, HeuristicSystem heuristicSystem2, List list3, BlockPos blockPos2) {
      return !itemStackEntry.heuristicManager.checkIndex(blockPos2.getX(), blockPos2.getZ())
         || heuristicSystem2.contains(itemStackEntry.resolveIBlockState(blockPos2.getX(), blockPos2.getY(), blockPos2.getZ()))
         || list3.contains(blockPos2);
   }

   private boolean matchesBlockPos(BlockPos blockPos2) {
      return !(HeuristicManager.resolveIBlockState(this.staticListener, blockPos2).getBlock() instanceof BlockAir);
   }

   private SecondaryListener resolveSecondaryListener(List list3, ItemStackEntry itemStackEntry, BlockPos blockPos2) {
      return this.resolveSecondaryListener(blockPos2, list3, itemStackEntry);
   }

   public static List resolveList(ItemStackEntry itemStackEntry, HeuristicSystem heuristicSystem2, int count4, List list3, List list4, List list5) {
      java.util.ArrayList list6 = new java.util.ArrayList();
      java.util.ArrayList list7 = new java.util.ArrayList();
      Iterator iterator = heuristicSystem2.asList().iterator();

      while (iterator.hasNext()) {
         Block block = ((PrimaryCustom)iterator.next()).getBlock();
         if (BreadthFirstWatcher.immutableSet.contains(block)) {
            BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS = itemStackEntry.activeListener.getStaticListener().getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS();
            list6.addAll(
               itemStackEntry.depthFirstEvaluator
                  .getBreadthFirstPipeline()
                  .resolveArrayList(
                     EnhancedCustom.resolveText(block), (Integer)BaritoneManager.getSettings().maxCachedWorldScanCount.dataRef, betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.count9, betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.count10, 2
                  )
            );
         } else {
            list7.add(block);
         }
      }

      List list8 = resolveList(itemStackEntry, list6, heuristicSystem2, count4, list4, list5);
      if (!list7.isEmpty() || (Boolean)BaritoneManager.getSettings().extendCacheOnThreshold.dataRef && list8.size() < count4) {
         list8.addAll(SettingsTxtManager.getExtraListener().getSharedListener().resolveList(itemStackEntry.getActiveListener().getStaticListener(), heuristicSystem2, count4, 10, 32));
      }

      list8.addAll(list3);
      return resolveList(itemStackEntry, list8, heuristicSystem2, count4, list4, list5);
   }

   private HeuristicSystem getHeuristicSystem() {
      if (this.heuristicSystem == null) {
         return null;
      } else if (!(Boolean)BaritoneManager.getSettings().allowBreak.dataRef) {
         HeuristicSystem heuristicSystem2 = new HeuristicSystem(
            (PrimaryCustom[])this.heuristicSystem
               .asList()
               .stream()
               .filter(item -> MineINACTIVEHandlerAdapter.matchesPrimaryCustom((PrimaryCustom)item))
               .toArray(MineINACTIVEHandlerAdapter::resolvePrimaryCustomArrayByIndex)
         );
         if (heuristicSystem2.asList().isEmpty()) {
            this.applyText("Unable to mine when allowBreak is false and target block is not in allowBreakAnyway!");
            return null;
         } else {
            return heuristicSystem2;
         }
      } else {
         return this.heuristicSystem;
      }
   }

   private boolean isActiveAsFlag() {
      List list3 = this.getList();
      this.list.addAll(list3);
      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS = this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS();
      HeuristicManager heuristicManager = new HeuristicManager(this.staticListener);
      HeuristicSystem heuristicSystem2 = this.getHeuristicSystem();
      if (heuristicSystem2 == null) {
         return false;
      } else {
         double offset = 20.0;

         for (int index = betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.getX() - 10; index <= betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.getX() + 10; index++) {
            for (int index2 = betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.getY() - 10; index2 <= betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.getY() + 10; index2++) {
               for (int index3 = betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.getZ() - 10; index3 <= betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.getZ() + 10; index3++) {
                  if (heuristicSystem2.contains(heuristicManager.resolveIBlockState(index, index2, index3))) {
                     BlockPos blockPos2 = new BlockPos(index, index2, index3);
                     if ((Boolean)BaritoneManager.getSettings().legitMineIncludeDiagonals.dataRef
                           && this.list.stream().anyMatch(pos -> MineINACTIVEHandlerAdapter.matchesBlockPos(blockPos2, (BlockPos)pos))
                        || BreadthFirstRecorder.resolveOptional(this.staticListener, blockPos2, offset).isPresent()) {
                        this.list.add(blockPos2);
                     }
                  }
               }
            }
         }

         this.list = resolveList(new ItemStackEntry(this.baritoneManager), this.list, heuristicSystem2, 64, this.list2, list3);
         return true;
      }
   }

   public List getList() {
      if (!(Boolean)BaritoneManager.getSettings().mineScanDroppedItems.dataRef) {
         return Collections.emptyList();
      } else {
         java.util.ArrayList list3 = new java.util.ArrayList();

         for (Entity entity : (Iterable<Entity>)(Iterable<?>)this.staticListener.getWorld().loadedEntityList) {
            if (entity instanceof EntityItem) {
               EntityItem entityItem = (EntityItem)entity;
               if (this.heuristicSystem.matchesItemStack(entityItem.getItemStack())) {
                  list3.add(new BlockPos(entity));
               }
            }
         }

         list3.addAll(this.map.keySet());
         return list3;
      }
   }

   private static boolean matchesItemStackEntry(ItemStackEntry itemStackEntry, BlockPos blockPos2) {
      return matchesItemStackEntryByItemStackEntry(itemStackEntry, blockPos2);
   }

   @Override
   public void updateState() {
      this.applyHeuristicSystem(0, (HeuristicSystem)null);
   }

   private static boolean matchesBlockPos(BlockPos blockPos2, BlockPos blockPos3) {
      return blockPos3.getCoordinate(blockPos2) <= 2.0;
   }

   private static boolean matchesList(List list3, HeuristicSystem heuristicSystem2, ItemStackEntry itemStackEntry, BlockPos blockPos2) {
      for (BlockPos blockPos3 : (Iterable<BlockPos>)(Iterable<?>)list3) {
         if (blockPos3.getCoordinate(blockPos2) <= 9.0
            && heuristicSystem2.contains(itemStackEntry.resolveIBlockState(blockPos3.getX(), blockPos3.getY(), blockPos3.getZ()))
            && matchesItemStackEntryByItemStackEntry(itemStackEntry, blockPos3)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public boolean isEnabled() {
      return this.heuristicSystem != null;
   }

   private boolean matchesBlockPos3(BlockPos blockPos2) {
      return blockPos2.getX() == this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().getX()
         && blockPos2.getZ() == this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().getZ();
   }

   @Override
   public void applyTextArray(int count4, String[] textArray) {
      this.applyHeuristicSystem(count4, new HeuristicSystem(textArray));
   }

   private static int computeCount(ItemStack stack) {
      return stack.animationsToGo;
   }

   public static boolean matchesItemStackEntry3(ItemStackEntry itemStackEntry, BlockPos blockPos2) {
      int dataRef = (Integer)BaritoneManager.getSettings().allowOnlyExposedOresDistance.dataRef;

      for (int index = -dataRef; index <= dataRef; index++) {
         for (int index2 = -dataRef; index2 <= dataRef; index2++) {
            for (int index3 = -dataRef; index3 <= dataRef; index3++) {
               if (Math.abs(index) + Math.abs(index2) + Math.abs(index3) <= dataRef
                  && AStarPredictor.matchesBlock3(itemStackEntry.resolveBlock(blockPos2.getX() + index, blockPos2.getY() + index2, blockPos2.getZ() + index3))) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   private Listener5Entry getListener5Entry() {
      HeuristicSystem heuristicSystem2 = this.getHeuristicSystem();
      if (heuristicSystem2 == null) {
         return null;
      } else {
         boolean dataRef = (Boolean)BaritoneManager.getSettings().legitMine.dataRef;
         List list3 = this.list;
         if (!list3.isEmpty()) {
            ItemStackEntry itemStackEntry = new ItemStackEntry(this.baritoneManager);
            List list4 = resolveList(itemStackEntry, new java.util.ArrayList(list3), heuristicSystem2, 64, this.list2, this.getList());
            DepthFirstCoordinator depthFirstCoordinator = new DepthFirstCoordinator(
               (SecondaryListener[])list4.stream()
                  .map(pos -> this.resolveSecondaryListener(list4, itemStackEntry, (BlockPos)pos))
                  .toArray(MineINACTIVEHandlerAdapter::resolveSecondaryListenerArray)
            );
            this.list = list4;
            return new Listener5Entry(depthFirstCoordinator, dataRef ? HeuristicCoordinator.FORCE_REVALIDATE_GOAL_AND_PATH : HeuristicCoordinator.REVALIDATE_GOAL_AND_PATH);
         } else if (!dataRef && !(Boolean)BaritoneManager.getSettings().exploreForBlocks.dataRef) {
            return null;
         } else {
            int dataRef2 = (Integer)BaritoneManager.getSettings().legitMineYLevel.dataRef;
            if (this.blockPos == null) {
               this.blockPos = this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS();
            }

            if (this.goalRunAwayFromListener == null) {
               this.goalRunAwayFromListener = new MineINACTIVEHandlerEntryAdapter(this, 1.0, dataRef2, new BlockPos[]{this.blockPos});
            }

            return new Listener5Entry(this.goalRunAwayFromListener, HeuristicCoordinator.REVALIDATE_GOAL_AND_PATH);
         }
      }
   }

   private void applyListByList(List list3, ItemStackEntry itemStackEntry) {
      this.applyList(list3, itemStackEntry);
   }

   private static boolean matchesBlockPos4(BlockPos blockPos2) {
      return blockPos2.getY() >= (Integer)BaritoneManager.getSettings().minYLevelWhileMining.dataRef;
   }

   @Override
   public Listener5Entry resolveListener5Entry(boolean flag, boolean flag2) {
      if (this.count > 0) {
         int count4 = Arrays.stream(this.staticListener.getEntityPlayerSP().inventoryPlayer.mainInventory)
            .filter(this::matchesItemStack)
            .mapToInt(MineINACTIVEHandlerAdapter::computeCount)
            .sum();
         System.out.println("Currently have " + count4 + " valid items");
         if (count4 >= this.count) {
            this.applyText("Have " + count4 + " valid items");
            this.updateState2();
            return null;
         }
      }

      if (flag) {
         if (this.list.isEmpty() || !(Boolean)BaritoneManager.getSettings().blacklistClosestOnFailure.dataRef) {
            this.applyText("Unable to find any path to " + this.heuristicSystem + ", canceling mine");
            if ((Boolean)BaritoneManager.getSettings().notificationOnMineFail.dataRef) {
               this.applyText("Unable to find any path to " + this.heuristicSystem + ", canceling mine", true);
            }

            this.updateState2();
            return null;
         }

         this.applyText("Unable to find any path to " + this.heuristicSystem + ", blacklisting presumably unreachable closest instance...");
         if ((Boolean)BaritoneManager.getSettings().notificationOnMineFail.dataRef) {
            this.applyText("Unable to find any path to " + this.heuristicSystem + ", blacklisting presumably unreachable closest instance...", true);
         }

         ((java.util.stream.Stream<BlockPos>)this.list.stream()).min(Comparator.comparingDouble(this.staticListener.getEntityPlayerSP()::computeValue)).ifPresent(this.list2::add);
         this.list.removeIf(this.list2::contains);
      }

      this.updateState3();
      int dataRef = (Integer)BaritoneManager.getSettings().mineGoalUpdateInterval.dataRef;
      java.util.ArrayList list3 = new java.util.ArrayList(this.list);
      if (dataRef != 0 && this.count3++ % dataRef == 0) {
         ItemStackEntry itemStackEntry = new ItemStackEntry(this.baritoneManager, true);
         BaritoneManager.getExecutor().execute(() -> this.applyListByList(list3, itemStackEntry));
      }

      if ((Boolean)BaritoneManager.getSettings().legitMine.dataRef && !this.isActiveAsFlag()) {
         this.updateState2();
         return null;
      } else {
         Optional optional = ((java.util.stream.Stream<BlockPos>)list3.stream())
            .filter(this::matchesBlockPos3)
            .filter(this::matchesBlockPos2)
            .filter(this::matchesBlockPos)
            .min(Comparator.comparingDouble(this.staticListener.getEntityPlayerSP()::computeValue));
         this.baritoneManager.getMapEntry().clear();
         if (optional.isPresent() && this.staticListener.getEntityPlayerSP().onGround) {
            BlockPos blockPos2 = (BlockPos)optional.get();
            IBlockState blockState = this.baritoneManager.heuristicManager.resolveIBlockState(blockPos2);
            if (!AStarPredictor.matchesHeuristicManagerByHeuristicManager(this.baritoneManager.heuristicManager, blockPos2.getX(), blockPos2.getY(), blockPos2.getZ(), blockState)
               )
             {
               Optional optional2 = BreadthFirstRecorder.resolveOptional(this.staticListener, blockPos2);
               if (optional2.isPresent() && flag2) {
                  this.baritoneManager.getTickListenerAdapter().applyDefaultCustom((DefaultCustom)optional2.get(), true);
                  AStarPredictor.applyStaticListener(this.staticListener, this.staticListener.getWorld().getBlockState(blockPos2));
                  if (this.staticListener.matchesBlockPos(blockPos2)
                     || this.staticListener.getDefaultCustom().matchesDefaultCustomByDefaultCustom((DefaultCustom)optional2.get())) {
                     this.baritoneManager.getMapEntry().applyBaseCustom(BaseCustom.CLICK_LEFT, true);
                  }

                  return new Listener5Entry(null, HeuristicCoordinator.REQUEST_PAUSE);
               }
            }
         }

         Listener5Entry listener5Entry = this.getListener5Entry();
         if (listener5Entry == null) {
            this.updateState2();
            return null;
         } else {
            return listener5Entry;
         }
      }
   }

   private static boolean matchesPrimaryCustom(PrimaryCustom primaryCustom) {
      return ((List)BaritoneManager.getSettings().allowBreakAnyway.dataRef).contains(primaryCustom.getBlock());
   }

   private static List resolveList(ItemStackEntry itemStackEntry, List list3, HeuristicSystem heuristicSystem2, int count4, List list4, List list5) {
      list5.removeIf(pos -> MineINACTIVEHandlerAdapter.matchesList(list3, heuristicSystem2, itemStackEntry, (BlockPos)pos));
      List list6 = ((java.util.stream.Stream<BlockPos>)list3.stream())
         .distinct()
         .filter(pos -> MineINACTIVEHandlerAdapter.matchesItemStackEntry(itemStackEntry, heuristicSystem2, list5, pos))
         .filter(pos -> MineINACTIVEHandlerAdapter.matchesItemStackEntry(itemStackEntry, pos))
         .filter(pos -> MineINACTIVEHandlerAdapter.matchesItemStackEntry2(itemStackEntry, pos))
         .filter(MineINACTIVEHandlerAdapter::matchesBlockPos4)
         .filter(MineINACTIVEHandlerAdapter::matchesBlockPosByBlockPos)
         .filter(pos -> MineINACTIVEHandlerAdapter.contains(list4, pos))
         .sorted(Comparator.comparingDouble(itemStackEntry.getActiveListener().getStaticListener().getEntityPlayerSP()::computeValue))
         .collect(Collectors.toList());
      return list6.size() > count4 ? list6.subList(0, count4) : list6;
   }

   private static SecondaryListener[] resolveSecondaryListenerArray(int count4) {
      return new SecondaryListener[count4];
   }

   private boolean matchesBlockPos(BlockPos blockPos2, ItemStackEntry itemStackEntry, List list3) {
      if (list3.contains(blockPos2)) {
         return true;
      } else {
         IBlockState blockState = itemStackEntry.heuristicManager.resolveIBlockState(blockPos2);
         return (Boolean)BaritoneManager.getSettings().internalMiningAirException.dataRef && blockState.getBlock() instanceof BlockAir
            ? true
            : this.heuristicSystem.contains(blockState) && matchesItemStackEntryByItemStackEntry(itemStackEntry, blockPos2);
      }
   }

   private static boolean contains(List list3, BlockPos blockPos2) {
      return !list3.contains(blockPos2);
   }

   private boolean matchesBlockPos2(BlockPos blockPos2) {
      return blockPos2.getY() >= this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().getY();
   }

   private boolean matchesItemStack(ItemStack stack) {
      return this.heuristicSystem.matchesItemStack(stack);
   }

   public static boolean matchesItemStackEntryByItemStackEntry(ItemStackEntry itemStackEntry, BlockPos blockPos2) {
      return AStarPredictor.computeValue(itemStackEntry, blockPos2.getX(), blockPos2.getY(), blockPos2.getZ(), itemStackEntry.heuristicManager.resolveIBlockState(blockPos2), true) >= 1000000.0
         ? false
         : itemStackEntry.heuristicManager.resolveIBlockState(blockPos2.up()).getBlock() != Blocks.block95
            || itemStackEntry.heuristicManager.resolveIBlockState(blockPos2.down()).getBlock() != Blocks.block95;
   }

   @Override
   public void applyHeuristicSystem(int count4, HeuristicSystem heuristicSystem2) {
      this.heuristicSystem = heuristicSystem2;
      if (this.getHeuristicSystem() == null) {
         this.heuristicSystem = null;
      }

      this.count = count4;
      this.list = new java.util.ArrayList();
      this.list2 = new java.util.ArrayList();
      this.blockPos = null;
      this.goalRunAwayFromListener = null;
      this.map = new HashMap();
      if (heuristicSystem2 != null) {
         this.applyList(new java.util.ArrayList(), new ItemStackEntry(this.baritoneManager));
      }
   }

   private void updateState3() {
      HashMap map2 = new HashMap(this.map);
      this.staticListener.getOptional().ifPresent(pos -> this.applyMap(map2, (BlockPos)pos));

      for (BlockPos blockPos2 : (Iterable<BlockPos>)(Iterable<?>)this.map.keySet()) {
         if ((Long)map2.get(blockPos2) < System.currentTimeMillis()) {
            map2.remove(blockPos2);
         }
      }

      this.map = map2;
   }

   public MineINACTIVEHandlerAdapter(BaritoneManager baritoneManager) {
      super(baritoneManager);
   }
}
