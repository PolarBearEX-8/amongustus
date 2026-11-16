package cc.baritone.utils;

import cc.baritone.api.ItemStackEntry;
import cc.baritone.pathing.PathFinderCannotBeReusedManager;
import client.util.AimObserver;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import net.minecraft.block.BlockLiquid;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;

public class DepthFirstCalculator implements DRNHandler, PrimaryListener {
   public static final double value = 3.0;
   public static final double value2 = 2.0;
   public static final double value3 = 200.0;
   public final Custom custom;
   public int count;
   public int count2;
   public int count3;
   public Double memberRef;
   public Integer integer;
   public boolean enabled;
   public boolean enabled2 = true;
   public HashSet hashSet = new HashSet();
   public HashSet hashSet2 = new HashSet();
   public HashSet hashSet3 = new HashSet();
   public final AlreadyDoingItManager alreadyDoingItManager;
   public final StaticListener staticListener;
   public boolean enabled3;

   @Override
   public int getCount() {
      return this.count;
   }

   private void updateState() {
      this.updateState3();
      this.alreadyDoingItManager.baritoneManager.getMapEntry().getClickListenerAdapter().updateState();
      this.count = this.custom.size() + 3;
      this.enabled = true;
   }

   @Override
   public Custom getCustom() {
      return this.custom;
   }

   public boolean isActiveAsFlag() {
      return this.count >= this.custom.size();
   }

   private boolean isActive2() {
      Optional optional = this.alreadyDoingItManager.ofNullable();
      if (!optional.isPresent()) {
         return false;
      } else if (!this.staticListener.getEntityPlayerSP().onGround) {
         return false;
      } else if (!AStarPredictor.matchesStaticListener(
         this.staticListener, this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().getBetterBlockPosXSYSZSBlockPos4()
      )) {
         return false;
      } else if (!AStarPredictor.matchesStaticListenerByStaticListener(
            this.staticListener, this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS()
         )
         || !AStarPredictor.matchesStaticListenerByStaticListener(
            this.staticListener, this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().getBetterBlockPosXSYSZSBlockPos3()
         )) {
         return false;
      } else if (!((WAITINGCANCELEDEnumService)this.custom.unmodifiableListAsList().get(this.count)).isActive()) {
         return false;
      } else {
         Optional optional2 = ((PathFinderCannotBeReusedManager)optional.get()).getOptional();
         if (!optional2.isPresent()) {
            return false;
         } else {
            List list = ((Custom)optional2.get()).unmodifiableList();
            return list.size() < 3 ? false : list.subList(1, list.size()).contains(this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS());
         }
      }
   }

   private void updateState3() {
      this.alreadyDoingItManager.baritoneManager.getMapEntry().clear();
   }

   public boolean isActive() {
      if (this.count == this.custom.size() - 1) {
         this.count++;
      }

      if (this.count >= this.custom.size()) {
         return true;
      } else {
         AStarPipeline aStarPipeline = (AStarPipeline)this.custom.unmodifiableListAsList().get(this.count);
         BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS = this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS();
         if (!aStarPipeline.getSet().contains(betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS)) {
            for (int index = 0; index < this.count && index < this.custom.size(); index++) {
               if (((AStarPipeline)this.custom.unmodifiableListAsList().get(index)).getSet().contains(betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS)) {
                  int count4 = this.count;
                  this.count = index;

                  for (int index2 = this.count; index2 <= count4; index2++) {
                     ((WAITINGCANCELEDEnumService)this.custom.unmodifiableListAsList().get(index2)).updateState();
                  }

                  this.updateState2();
                  this.isActive();
                  return false;
               }
            }

            for (int index3 = this.count + 3; index3 < this.custom.size() - 1; index3++) {
               if (((AStarPipeline)this.custom.unmodifiableListAsList().get(index3)).getSet().contains(betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS)) {
                  if (index3 - this.count > 2) {
                     this.applyTextByText("Skipping forward " + (index3 - this.count) + " steps, to " + index3);
                  }

                  this.count = index3 - 1;
                  this.updateState2();
                  this.isActive();
                  return false;
               }
            }
         }

         AimObserver aimObserver = this.resolveAimObserver(this.custom);
         if (this.matchesAimObserver(aimObserver, 2.0)) {
            this.count2++;
            System.out.println("FAR AWAY FROM PATH FOR " + this.count2 + " TICKS. Current distance: " + aimObserver.getDataRef2() + ". Threshold: " + 2.0);
            if ((double)this.count2 > 200.0) {
               this.applyTextByText("Too far away from path for too long, cancelling path");
               this.updateState();
               return false;
            }
         } else {
            this.count2 = 0;
         }

         if (this.matchesAimObserver(aimObserver, 3.0)) {
            this.applyTextByText("too far from path");
            this.updateState();
            return false;
         } else {
            HeuristicManager heuristicManager = new HeuristicManager(this.staticListener);

            for (int index4 = this.count - 10; index4 < this.count + 10; index4++) {
               if (index4 >= 0 && index4 < this.custom.unmodifiableListAsList().size()) {
                  AStarPipeline aStarPipeline2 = (AStarPipeline)this.custom.unmodifiableListAsList().get(index4);
                  List list = aStarPipeline2.resolveListByHeuristicManager(heuristicManager);
                  List list2 = aStarPipeline2.resolveList2(heuristicManager);
                  List list3 = aStarPipeline2.resolveList(heuristicManager);
                  aStarPipeline2.updateState2();
                  if (!list.equals(aStarPipeline2.resolveListByHeuristicManager(heuristicManager))) {
                     this.enabled2 = true;
                  }

                  if (!list2.equals(aStarPipeline2.resolveList2(heuristicManager))) {
                     this.enabled2 = true;
                  }

                  if (!list3.equals(aStarPipeline2.resolveList(heuristicManager))) {
                     this.enabled2 = true;
                  }
               }
            }

            if (this.enabled2) {
               HashSet set = new HashSet();
               HashSet set2 = new HashSet();
               HashSet set3 = new HashSet();

               for (int index5 = this.count; index5 < this.custom.unmodifiableListAsList().size(); index5++) {
                  AStarPipeline aStarPipeline3 = (AStarPipeline)this.custom.unmodifiableListAsList().get(index5);
                  set.addAll(aStarPipeline3.resolveListByHeuristicManager(heuristicManager));
                  set2.addAll(aStarPipeline3.resolveList2(heuristicManager));
                  set3.addAll(aStarPipeline3.resolveList(heuristicManager));
               }

               this.hashSet = set;
               this.hashSet2 = set2;
               this.hashSet3 = set3;
               this.enabled2 = false;
            }

            if (this.count < this.custom.unmodifiableListAsList().size() - 1) {
               WAITINGCANCELEDEnumService wAITINGCANCELEDEnumService = (WAITINGCANCELEDEnumService)this.custom.unmodifiableListAsList().get(this.count + 1);
               if (!this.alreadyDoingItManager
                  .baritoneManager
                  .heuristicManager
                  .checkIndex(wAITINGCANCELEDEnumService.getBetterBlockPosXSYSZSBlockPos2().count9, wAITINGCANCELEDEnumService.getBetterBlockPosXSYSZSBlockPos2().count10)) {
                  this.applyTextByText("Pausing since destination is at edge of loaded chunks");
                  this.updateState3();
                  return true;
               }
            }

            boolean active = aStarPipeline.isActive();
            if (this.integer == null || this.integer != this.count) {
               this.integer = this.count;
               this.memberRef = aStarPipeline.doubleValue();

               for (int index6 = 1;
                  index6 < (Integer)BaritoneManager.getSettings().costVerificationLookahead.dataRef && this.count + index6 < this.custom.size() - 1;
                  index6++
               ) {
                  if (((AStarPipeline)this.custom.unmodifiableListAsList().get(this.count + index6))
                           .computeValue(this.alreadyDoingItManager.getItemStackEntry())
                        >= 1000000.0
                     && active) {
                     this.applyTextByText("Something has changed in the world and a future movement has become impossible. Cancelling.");
                     this.updateState();
                     return true;
                  }
               }
            }

            double offset = aStarPipeline.computeValueByItemStackEntry(this.alreadyDoingItManager.getItemStackEntry());
            if (offset >= 1000000.0 && active) {
               this.applyTextByText("Something has changed in the world and this movement has become impossible. Cancelling.");
               this.updateState();
               return true;
            } else if (!aStarPipeline.booleanValue() && offset - this.memberRef > (Double)BaritoneManager.getSettings().maxCostIncrease.dataRef && active) {
               this.applyTextByText("Original cost " + this.memberRef + " current cost " + offset + ". Cancelling.");
               this.updateState();
               return true;
            } else if (this.isActive2()) {
               this.applyTextByText("Pausing since current best path is a backtrack");
               this.updateState3();
               return true;
            } else {
               WAITINGCANCELEDEnum wAITINGCANCELEDEnum = aStarPipeline.getWAITINGCANCELEDEnum();
               if (wAITINGCANCELEDEnum == WAITINGCANCELEDEnum.UNREACHABLE || wAITINGCANCELEDEnum == WAITINGCANCELEDEnum.FAILED) {
                  this.applyTextByText("Movement returns status " + wAITINGCANCELEDEnum);
                  this.updateState();
                  return true;
               } else if (wAITINGCANCELEDEnum == WAITINGCANCELEDEnum.SUCCESS) {
                  this.count++;
                  this.updateState2();
                  this.isActive();
                  return true;
               } else {
                  this.enabled3 = this.isActive4();
                  if (!this.enabled3) {
                     this.staticListener.getEntityPlayerSP().setState3(false);
                  }

                  this.count3++;
                  if ((double)this.count3 > this.memberRef + (double)((Integer)BaritoneManager.getSettings().movementTimeoutTicks.dataRef).intValue()) {
                     this.applyTextByText("This movement has taken too long (" + this.count3 + " ticks, expected " + this.memberRef + "). Cancelling.");
                     this.updateState();
                     return true;
                  } else {
                     return active;
                  }
               }
            }
         }
      }
   }

   public Set unmodifiableSetAsSet() {
      return Collections.unmodifiableSet(this.hashSet);
   }

   public Set unmodifiableSet() {
      return Collections.unmodifiableSet(this.hashSet2);
   }

   private boolean matchesAimObserver(AimObserver aimObserver, double offset) {
      if ((Double)aimObserver.getDataRef2() > offset) {
         if (this.custom.unmodifiableListAsList().get(this.count) instanceof ItemStackAStarPipeline) {
            BlockPos blockPos = (BlockPos)this.custom.unmodifiableList().get(this.count + 1);
            return Vec3Helper.computeValue(this.staticListener.getEntityPlayerSP(), blockPos) >= offset;
         } else {
            return true;
         }
      } else {
         return false;
      }
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   private boolean isActive4() {
      boolean flag = this.alreadyDoingItManager.baritoneManager.getMapEntry().matchesBaseCustom(BaseCustom.SPRINT);
      this.alreadyDoingItManager.baritoneManager.getMapEntry().applyBaseCustom(BaseCustom.SPRINT, false);
      if (!(new ItemStackEntry(this.alreadyDoingItManager.baritoneManager, false)).enabled11) {
         return false;
      } else {
         WAITINGCANCELEDEnumService wAITINGCANCELEDEnumService = (WAITINGCANCELEDEnumService)this.custom.unmodifiableListAsList().get(this.count);
         if (wAITINGCANCELEDEnumService instanceof WrongCoordinateAStarPipeline && this.count < this.custom.size() - 3) {
            WAITINGCANCELEDEnumService wAITINGCANCELEDEnumService2 = (WAITINGCANCELEDEnumService)this.custom.unmodifiableListAsList().get(this.count + 1);
            if (wAITINGCANCELEDEnumService2 instanceof HeuristicPlannerAStarPipeline
               && matchesStaticListener(
                  this.staticListener,
                  (WrongCoordinateAStarPipeline)wAITINGCANCELEDEnumService,
                  (HeuristicPlannerAStarPipeline)wAITINGCANCELEDEnumService2,
                  (WAITINGCANCELEDEnumService)this.custom.unmodifiableListAsList().get(this.count + 2)
               )) {
               if (matchesStaticListener(this.staticListener, wAITINGCANCELEDEnumService)) {
                  this.applyTextByText("Skipping traverse to straight ascend");
                  this.count++;
                  this.updateState2();
                  this.isActive();
                  this.alreadyDoingItManager.baritoneManager.getMapEntry().applyBaseCustom(BaseCustom.JUMP, true);
                  return true;
               }

               this.applyTextByText("Too far to the side to safely sprint ascend");
            }
         }

         if (flag) {
            return true;
         } else {
            if (wAITINGCANCELEDEnumService instanceof ItemStackEntryAStarPipelineImpl) {
               if (((ItemStackEntryAStarPipelineImpl)wAITINGCANCELEDEnumService).isActive2() && !((ItemStackEntryAStarPipelineImpl)wAITINGCANCELEDEnumService).isActive3()) {
                  this.applyTextByText("Sprinting would be unsafe");
                  return false;
               }

               if (this.count < this.custom.size() - 2) {
                  WAITINGCANCELEDEnumService wAITINGCANCELEDEnumService3 = (WAITINGCANCELEDEnumService)this.custom.unmodifiableListAsList().get(this.count + 1);
                  if (wAITINGCANCELEDEnumService3 instanceof HeuristicPlannerAStarPipeline && wAITINGCANCELEDEnumService.getBlockPos().up().equals(wAITINGCANCELEDEnumService3.getBlockPos().down())) {
                     this.count++;
                     this.updateState2();
                     this.isActive();
                     this.applyTextByText("Skipping descend to straight ascend");
                     return true;
                  }

                  if (matchesStaticListener(this.staticListener, wAITINGCANCELEDEnumService, wAITINGCANCELEDEnumService3)) {
                     if (wAITINGCANCELEDEnumService3 instanceof ItemStackEntryAStarPipelineImpl && this.count < this.custom.size() - 3) {
                        WAITINGCANCELEDEnumService wAITINGCANCELEDEnumService4 = (WAITINGCANCELEDEnumService)this.custom.unmodifiableListAsList().get(this.count + 2);
                        if (wAITINGCANCELEDEnumService4 instanceof ItemStackEntryAStarPipelineImpl && !matchesStaticListener(this.staticListener, wAITINGCANCELEDEnumService3, wAITINGCANCELEDEnumService4)) {
                           return false;
                        }
                     }

                     if (this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().equals(wAITINGCANCELEDEnumService.getBetterBlockPosXSYSZSBlockPos2())) {
                        this.count++;
                        this.updateState2();
                        this.isActive();
                     }

                     return true;
                  }
               }
            }

            if (wAITINGCANCELEDEnumService instanceof HeuristicPlannerAStarPipeline && this.count != 0) {
               WAITINGCANCELEDEnumService wAITINGCANCELEDEnumService5 = (WAITINGCANCELEDEnumService)this.custom.unmodifiableListAsList().get(this.count - 1);
               if (wAITINGCANCELEDEnumService5 instanceof ItemStackEntryAStarPipelineImpl && wAITINGCANCELEDEnumService5.getBlockPos().up().equals(wAITINGCANCELEDEnumService.getBlockPos().down())) {
                  BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos = wAITINGCANCELEDEnumService.getBetterBlockPosXSYSZSBlockPos().getBetterBlockPosXSYSZSBlockPos3();
                  if (this.staticListener.getEntityPlayerSP().posY >= (double)betterBlockPosXSYSZSBlockPos.getY() - 0.07) {
                     this.alreadyDoingItManager.baritoneManager.getMapEntry().applyBaseCustom(BaseCustom.JUMP, false);
                     return true;
                  }
               }

               if (this.count < this.custom.size() - 2
                  && wAITINGCANCELEDEnumService5 instanceof WrongCoordinateAStarPipeline
                  && matchesStaticListener(
                     this.staticListener,
                     (WrongCoordinateAStarPipeline)wAITINGCANCELEDEnumService5,
                     (HeuristicPlannerAStarPipeline)wAITINGCANCELEDEnumService,
                     (WAITINGCANCELEDEnumService)this.custom.unmodifiableListAsList().get(this.count + 1)
                  )) {
                  return true;
               }
            }

            if (wAITINGCANCELEDEnumService instanceof ItemStackAStarPipeline) {
               AimObserver aimObserver = this.resolveAimObserver((ItemStackAStarPipeline)wAITINGCANCELEDEnumService);
               if (aimObserver != null) {
                  BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2 = new BetterBlockPosXSYSZSBlockPos((BlockPos)aimObserver.getDataRef());
                  if (!this.custom.unmodifiableList().contains(betterBlockPosXSYSZSBlockPos2)) {
                     throw new IllegalStateException();
                  }

                  if (this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().equals(betterBlockPosXSYSZSBlockPos2)) {
                     this.count = this.custom.unmodifiableList().indexOf(betterBlockPosXSYSZSBlockPos2);
                     this.updateState2();
                     this.isActive();
                     return true;
                  }

                  this.updateState3();
                  this.alreadyDoingItManager
                     .baritoneManager
                     .getTickListenerAdapter()
                     .applyDefaultCustom(
                        BreadthFirstRecorder.resolveDefaultCustom(
                           this.staticListener.getVecAsVec3(), (Vec3)aimObserver.getDataRef2(), this.staticListener.getDefaultCustom()
                        ),
                        false
                     );
                  this.alreadyDoingItManager.baritoneManager.getMapEntry().applyBaseCustom(BaseCustom.MOVE_FORWARD, true);
                  return true;
               }
            }

            return false;
         }
      }
   }

   public boolean isActive3() {
      if (!this.staticListener.getEntityPlayerSP().onGround
         && !(
            this.staticListener.getWorld().getBlockState(this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS()).getBlock() instanceof BlockLiquid
         )) {
         return false;
      } else if (this.staticListener.getEntityPlayerSP().motionY < -0.1) {
         return false;
      } else {
         int count4 = this.custom.unmodifiableList().indexOf(this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS());
         if (count4 == -1) {
            return false;
         } else {
            this.count = count4;
            this.updateState3();
            return true;
         }
      }
   }

   private static boolean matchesStaticListener(
      StaticListener staticListener2, WrongCoordinateAStarPipeline wrongCoordinateAStarPipeline, HeuristicPlannerAStarPipeline heuristicPlannerAStarPipeline, WAITINGCANCELEDEnumService wAITINGCANCELEDEnumService
   ) {
      if (!(Boolean)BaritoneManager.getSettings().sprintAscends.dataRef) {
         return false;
      } else if (!wrongCoordinateAStarPipeline.getBlockPos().equals(heuristicPlannerAStarPipeline.getBlockPos().down())) {
         return false;
      } else if (wAITINGCANCELEDEnumService.getBlockPos().getX() == heuristicPlannerAStarPipeline.getBlockPos().getX() && wAITINGCANCELEDEnumService.getBlockPos().getZ() == heuristicPlannerAStarPipeline.getBlockPos().getZ()) {
         if (!AStarPredictor.matchesStaticListener(staticListener2, wrongCoordinateAStarPipeline.getBetterBlockPosXSYSZSBlockPos2().getBetterBlockPosXSYSZSBlockPos4())) {
            return false;
         } else if (!AStarPredictor.matchesStaticListener(staticListener2, heuristicPlannerAStarPipeline.getBetterBlockPosXSYSZSBlockPos2().getBetterBlockPosXSYSZSBlockPos4())) {
            return false;
         } else if (!heuristicPlannerAStarPipeline.list.isEmpty()) {
            return false;
         } else {
            for (int index = 0; index < 2; index++) {
               for (int index2 = 0; index2 < 3; index2++) {
                  Object entry = wrongCoordinateAStarPipeline.getBetterBlockPosXSYSZSBlockPos().resolveBetterBlockPosXSYSZSBlockPos2(index2);
                  if (index == 1) {
                     entry = ((BlockPos)entry).getBlockPos2(wrongCoordinateAStarPipeline.getBlockPos());
                  }

                  if (!AStarPredictor.matchesStaticListener3(staticListener2, (BlockPos)entry)) {
                     return false;
                  }
               }
            }

            return AStarPredictor.matchesBlockByBlock(
                  staticListener2.getWorld().getBlockState(wrongCoordinateAStarPipeline.getBetterBlockPosXSYSZSBlockPos().resolveBetterBlockPosXSYSZSBlockPos2(3)).getBlock()
               )
               ? false
               : !AStarPredictor.matchesBlockByBlock(
                  staticListener2.getWorld().getBlockState(heuristicPlannerAStarPipeline.getBetterBlockPosXSYSZSBlockPos2().resolveBetterBlockPosXSYSZSBlockPos2(2)).getBlock()
               );
         }
      } else {
         return false;
      }
   }

   private void updateState2() {
      this.updateState3();
      this.count3 = 0;
   }

   private AimObserver resolveAimObserver(ItemStackAStarPipeline itemStackAStarPipeline) {
      BlockPos blockPos = itemStackAStarPipeline.getBlockPos();
      if (blockPos.getY() < -3) {
         return null;
      } else if (!itemStackAStarPipeline.list.isEmpty()) {
         return null;
      } else {
         Vec3i vec = new Vec3i(blockPos.getX(), 0, blockPos.getZ());

         int count4;
         outerLoop:
         for (count4 = this.count + 1; count4 < this.custom.size() - 1 && count4 < this.count + 3; count4++) {
            WAITINGCANCELEDEnumService wAITINGCANCELEDEnumService = (WAITINGCANCELEDEnumService)this.custom.unmodifiableListAsList().get(count4);
            if (!(wAITINGCANCELEDEnumService instanceof WrongCoordinateAStarPipeline) || !vec.equals(wAITINGCANCELEDEnumService.getBlockPos())) {
               break;
            }

            for (int index = wAITINGCANCELEDEnumService.getBetterBlockPosXSYSZSBlockPos2().count12; index <= itemStackAStarPipeline.getBetterBlockPosXSYSZSBlockPos().count12 + 1; index++) {
               BlockPos blockPos2 = new BlockPos(wAITINGCANCELEDEnumService.getBetterBlockPosXSYSZSBlockPos2().count9, index, wAITINGCANCELEDEnumService.getBetterBlockPosXSYSZSBlockPos2().count10);
               if (!AStarPredictor.matchesStaticListener3(this.staticListener, blockPos2)) {
                  break outerLoop;
               }
            }

            if (!AStarPredictor.matchesStaticListener(this.staticListener, wAITINGCANCELEDEnumService.getBetterBlockPosXSYSZSBlockPos2().getBetterBlockPosXSYSZSBlockPos4())) {
               break;
            }
         }

         if (--count4 == this.count) {
            return null;
         } else {
            double offset = (double)(count4 - this.count) - 0.4;
            return new AimObserver(
               new Vec3(
                  (double)vec.getX() * offset + (double)itemStackAStarPipeline.getBetterBlockPosXSYSZSBlockPos2().count9 + 0.5,
                  (double)itemStackAStarPipeline.getBetterBlockPosXSYSZSBlockPos2().count12,
                  (double)vec.getZ() * offset + (double)itemStackAStarPipeline.getBetterBlockPosXSYSZSBlockPos2().count10 + 0.5
               ),
               itemStackAStarPipeline.getBetterBlockPosXSYSZSBlockPos2().add(vec.getX() * (count4 - this.count), 0, vec.getZ() * (count4 - this.count))
            );
         }
      }
   }

   public boolean isEnabled3() {
      return this.enabled3;
   }

   public DepthFirstCalculator(AlreadyDoingItManager alreadyDoingItManager2, Custom custom2) {
      this.alreadyDoingItManager = alreadyDoingItManager2;
      this.staticListener = alreadyDoingItManager2.staticListener;
      this.custom = custom2;
      this.count = 0;
   }

   private DepthFirstCalculator getDepthFirstCalculator() {
      if (this.count > (Integer)BaritoneManager.getSettings().maxPathHistoryLength.dataRef) {
         int dataRef = (Integer)BaritoneManager.getSettings().pathHistoryCutoffAmount.dataRef;
         SecondaryListenerSecondaryListe secondaryListenerSecondaryListe = new SecondaryListenerSecondaryListe(this.custom, dataRef, this.custom.size() - 1);
         if (!secondaryListenerSecondaryListe.getBetterBlockPosXSYSZSBlockPos2().equals(this.custom.getBetterBlockPosXSYSZSBlockPos2())) {
            throw new IllegalStateException();
         } else {
            this.applyTextByText("Discarding earliest segment movements, length cut from " + this.custom.size() + " to " + secondaryListenerSecondaryListe.size());
            DepthFirstCalculator depthFirstCalculator = new DepthFirstCalculator(this.alreadyDoingItManager, secondaryListenerSecondaryListe);
            depthFirstCalculator.count = this.count - dataRef;
            depthFirstCalculator.memberRef = this.memberRef;
            if (this.integer != null) {
               depthFirstCalculator.integer = this.integer - dataRef;
            }

            depthFirstCalculator.count3 = this.count3;
            return depthFirstCalculator;
         }
      } else {
         return this;
      }
   }

   private AimObserver resolveAimObserver(Custom custom2) {
      double offset = -1.0;
      BlockPos blockPos = null;
      Iterator iterator = custom2.unmodifiableListAsList().iterator();

      while (iterator.hasNext()) {
         for (BlockPos blockPos2 : (Iterable<BlockPos>)(Iterable)((AStarPipeline)((WAITINGCANCELEDEnumService)iterator.next())).getSet()) {
            double offset2 = Vec3Helper.computeValueByEntity(this.staticListener.getEntityPlayerSP(), blockPos2);
            if (offset2 < offset || offset == -1.0) {
               offset = offset2;
               blockPos = blockPos2;
            }
         }
      }

      return new AimObserver(offset, blockPos);
   }

   public DepthFirstCalculator resolveDepthFirstCalculator(DepthFirstCalculator depthFirstCalculator) {
      return depthFirstCalculator == null
         ? this.getDepthFirstCalculator()
         : (DepthFirstCalculator)SecondaryListenerSecondaryListeImpl.resolveOptional(this.custom, depthFirstCalculator.custom, false)
            .map(item -> this.resolveDepthFirstCalculator(depthFirstCalculator, (SecondaryListenerSecondaryListeImpl)item))
            .orElseGet(this::getDepthFirstCalculator);
   }

   private static boolean matchesStaticListener(StaticListener staticListener2, WAITINGCANCELEDEnumService wAITINGCANCELEDEnumService) {
      if (Math.abs((double)wAITINGCANCELEDEnumService.getBlockPos().getX() * ((double)wAITINGCANCELEDEnumService.getBetterBlockPosXSYSZSBlockPos().count10 + 0.5 - staticListener2.getEntityPlayerSP().posZ))
            + Math.abs((double)wAITINGCANCELEDEnumService.getBlockPos().getZ() * ((double)wAITINGCANCELEDEnumService.getBetterBlockPosXSYSZSBlockPos().count9 + 0.5 - staticListener2.getEntityPlayerSP().posX))
         > 0.1) {
         return false;
      } else {
         BlockPos blockPos = wAITINGCANCELEDEnumService.getBetterBlockPosXSYSZSBlockPos().getBlockPos(wAITINGCANCELEDEnumService.getBlockPos()).resolveBlockPos2(2);
         return AStarPredictor.matchesStaticListener3(staticListener2, blockPos)
            ? true
            : Math.abs((double)wAITINGCANCELEDEnumService.getBlockPos().getX() * ((double)blockPos.getX() + 0.5 - staticListener2.getEntityPlayerSP().posX))
                  + Math.abs((double)wAITINGCANCELEDEnumService.getBlockPos().getZ() * ((double)blockPos.getZ() + 0.5 - staticListener2.getEntityPlayerSP().posZ))
               > 0.8;
      }
   }

   private DepthFirstCalculator resolveDepthFirstCalculator(DepthFirstCalculator depthFirstCalculator, SecondaryListenerSecondaryListeImpl secondaryListenerSecondaryListeImpl) {
      if (!secondaryListenerSecondaryListeImpl.getBetterBlockPosXSYSZSBlockPos2().equals(depthFirstCalculator.getCustom().getBetterBlockPosXSYSZSBlockPos2())) {
         throw new IllegalStateException();
      } else {
         DepthFirstCalculator depthFirstCalculator2 = new DepthFirstCalculator(this.alreadyDoingItManager, secondaryListenerSecondaryListeImpl);
         depthFirstCalculator2.count = this.count;
         depthFirstCalculator2.memberRef = this.memberRef;
         depthFirstCalculator2.integer = this.integer;
         depthFirstCalculator2.count3 = this.count3;
         return depthFirstCalculator2;
      }
   }

   private static boolean matchesStaticListener(StaticListener staticListener2, WAITINGCANCELEDEnumService wAITINGCANCELEDEnumService, WAITINGCANCELEDEnumService wAITINGCANCELEDEnumService2) {
      if (wAITINGCANCELEDEnumService2 instanceof ItemStackEntryAStarPipelineImpl && wAITINGCANCELEDEnumService2.getBlockPos().equals(wAITINGCANCELEDEnumService.getBlockPos())) {
         return true;
      } else if (!AStarPredictor.matchesStaticListener(staticListener2, wAITINGCANCELEDEnumService.getBetterBlockPosXSYSZSBlockPos2().getBlockPos2(wAITINGCANCELEDEnumService.getBlockPos()))) {
         return false;
      } else {
         return wAITINGCANCELEDEnumService2 instanceof WrongCoordinateAStarPipeline && wAITINGCANCELEDEnumService2.getBlockPos().down().equals(wAITINGCANCELEDEnumService.getBlockPos())
            ? true
            : wAITINGCANCELEDEnumService2 instanceof ItemStackEntryAStarPipeline && (Boolean)BaritoneManager.getSettings().allowOvershootDiagonalDescend.dataRef;
      }
   }

   public Set unmodifiableSet2() {
      return Collections.unmodifiableSet(this.hashSet3);
   }
}
