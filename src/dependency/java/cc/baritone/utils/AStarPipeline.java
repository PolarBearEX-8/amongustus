package cc.baritone.utils;

import cc.baritone.api.ItemStackEntry;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.EntityFallingsand;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public abstract class AStarPipeline implements AStarPredictor, WAITINGCANCELEDEnumService {
   public static final EnumFacing[] enumFacingArray = new EnumFacing[]{EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.EAST, EnumFacing.WEST, EnumFacing.DOWN};
   public final ActiveListener activeListener;
   public final StaticListener staticListener;
   public HeuristicPlanner heuristicPlanner = new HeuristicPlanner().withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum.PREPPING);
   public final BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos;
   public final BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2;
   public final BetterBlockPosXSYSZSBlockPos[] betterBlockPosXSYSZSBlockPosArray;
   public final BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos3;
   public Double memberRef;
   public List list = null;
   public List list2 = null;
   public List list3 = null;
   public Set set = null;
   public Boolean memberRef2;

   private void applyBaseCustom(BaseCustom baseCustom, Boolean flag) {
      this.activeListener.getPassiveListener().applyBaseCustom(baseCustom, flag);
   }

   @Override
   public boolean booleanValue() {
      return this.memberRef2;
   }

   @Override
   public BetterBlockPosXSYSZSBlockPos getBetterBlockPosXSYSZSBlockPos2() {
      return this.betterBlockPosXSYSZSBlockPos2;
   }

   public HeuristicPlanner resolveHeuristicPlanner(HeuristicPlanner heuristicPlanner2) {
      if (!this.matchesHeuristicPlannerByHeuristicPlanner(heuristicPlanner2)) {
         return heuristicPlanner2.withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum.PREPPING);
      } else {
         if (heuristicPlanner2.getWAITINGCANCELEDEnum() == WAITINGCANCELEDEnum.PREPPING) {
            heuristicPlanner2.withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum.WAITING);
         }

         if (heuristicPlanner2.getWAITINGCANCELEDEnum() == WAITINGCANCELEDEnum.WAITING) {
            heuristicPlanner2.withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum.RUNNING);
         }

         return heuristicPlanner2;
      }
   }

   public boolean matchesHeuristicPlannerByHeuristicPlanner(HeuristicPlanner heuristicPlanner2) {
      if (heuristicPlanner2.getWAITINGCANCELEDEnum() == WAITINGCANCELEDEnum.WAITING) {
         return true;
      } else {
         for (BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos4 : this.betterBlockPosXSYSZSBlockPosArray) {
            if (!this.staticListener
                  .getWorld()
                  .resolveList(
                     EntityFallingsand.class,
                     new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.1, 1.0).expand((double)betterBlockPosXSYSZSBlockPos4.count9, (double)betterBlockPosXSYSZSBlockPos4.count12, (double)betterBlockPosXSYSZSBlockPos4.count10)
                  )
                  .isEmpty()
               && (Boolean)BaritoneManager.getSettings().pauseMiningForFallingBlocks.dataRef) {
               return false;
            }

            if (!AStarPredictor.matchesStaticListenerByStaticListener(this.staticListener, betterBlockPosXSYSZSBlockPos4)
               && !(HeuristicManager.resolveBlock(this.staticListener, betterBlockPosXSYSZSBlockPos4) instanceof BlockLiquid)) {
               AStarPredictor.applyStaticListener(this.staticListener, HeuristicManager.resolveIBlockState(this.staticListener, betterBlockPosXSYSZSBlockPos4));
               Optional optional = BreadthFirstRecorder.resolveOptional(this.staticListener, betterBlockPosXSYSZSBlockPos4, this.staticListener.getDefaultListener().getValue());
               if (!optional.isPresent()) {
                  heuristicPlanner2.withAStarHandler(
                     new AStarHandler(
                        BreadthFirstRecorder.resolveDefaultCustom(
                           this.staticListener.getVecAsVec3(), Vec3Helper.resolveVec3(betterBlockPosXSYSZSBlockPos4), this.staticListener.getDefaultCustom()
                        ),
                        true
                     )
                  );
                  heuristicPlanner2.resolveHeuristicPlanner(BaseCustom.CLICK_LEFT, true);
                  return false;
               }

               DefaultCustom defaultCustom = (DefaultCustom)optional.get();
               heuristicPlanner2.withAStarHandler(new AStarHandler(defaultCustom, true));
               if (this.staticListener.matchesBlockPos(betterBlockPosXSYSZSBlockPos4) || this.staticListener.getDefaultCustom().matchesDefaultCustomByDefaultCustom(defaultCustom)) {
                  heuristicPlanner2.resolveHeuristicPlanner(BaseCustom.CLICK_LEFT, true);
               }

               return false;
            }
         }

         return true;
      }
   }

   @Override
   public double doubleValue() throws NullPointerException {
      return this.memberRef;
   }

   public double computeValue2(ItemStackEntry itemStackEntry) {
      if (this.memberRef == null) {
         this.memberRef = this.computeValue(itemStackEntry);
      }

      return this.memberRef;
   }

   private void applyBlockPos(BlockPos blockPos) {
      AStarPredictor.applyStaticListener(this.staticListener, HeuristicManager.resolveIBlockState(this.staticListener, blockPos));
   }

   @Override
   public BetterBlockPosXSYSZSBlockPos getBetterBlockPosXSYSZSBlockPos() {
      return this.betterBlockPosXSYSZSBlockPos;
   }

   public BlockPos[] getBlockPosArray() {
      return this.betterBlockPosXSYSZSBlockPosArray;
   }

   public boolean matchesHeuristicPlanner(HeuristicPlanner heuristicPlanner2) {
      return true;
   }

   @Override
   public BlockPos getBlockPos() {
      return this.getBetterBlockPosXSYSZSBlockPos2().getBlockPos(this.getBetterBlockPosXSYSZSBlockPos());
   }

   public void applyDouble(double offset) {
      this.memberRef = offset;
   }

   @Override
   public void updateState() {
      this.heuristicPlanner = new HeuristicPlanner().withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum.PREPPING);
   }

   public AStarPipeline(
      ActiveListener activeListener2,
      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos4,
      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos5,
      BetterBlockPosXSYSZSBlockPos[] betterBlockPosXSYSZSBlockPosArray2,
      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos6
   ) {
      this.activeListener = activeListener2;
      this.staticListener = activeListener2.getStaticListener();
      this.betterBlockPosXSYSZSBlockPos = betterBlockPosXSYSZSBlockPos4;
      this.betterBlockPosXSYSZSBlockPos2 = betterBlockPosXSYSZSBlockPos5;
      this.betterBlockPosXSYSZSBlockPosArray = betterBlockPosXSYSZSBlockPosArray2;
      this.betterBlockPosXSYSZSBlockPos3 = betterBlockPosXSYSZSBlockPos6;
   }

   private void applyDefaultCustom(DefaultCustom defaultCustom) {
      this.activeListener.getGlobalListener().applyDefaultCustom(defaultCustom, this.heuristicPlanner.getAStarHandler().isEnabled());
   }

   public Set getSet() {
      if (this.set == null) {
         this.set = this.of();
         Objects.requireNonNull(this.set);
      }

      return this.set;
   }

   @Override
   public boolean isActive() {
      return this.matchesHeuristicPlanner(this.heuristicPlanner);
   }

   public List resolveList2(HeuristicManager heuristicManager) {
      if (this.list2 != null) {
         return this.list2;
      } else {
         java.util.ArrayList list4 = new java.util.ArrayList();
         if (this.betterBlockPosXSYSZSBlockPos3 != null
            && !AStarPredictor.matchesHeuristicManager(
               heuristicManager, this.betterBlockPosXSYSZSBlockPos3.count9, this.betterBlockPosXSYSZSBlockPos3.count12, this.betterBlockPosXSYSZSBlockPos3.count10
            )) {
            list4.add(this.betterBlockPosXSYSZSBlockPos3);
         }

         this.list2 = list4;
         return list4;
      }
   }

   public double computeValueByItemStackEntry(ItemStackEntry itemStackEntry) {
      this.memberRef = null;
      return this.computeValue2(itemStackEntry);
   }

   public List resolveListByHeuristicManager(HeuristicManager heuristicManager) {
      if (this.list != null) {
         return this.list;
      } else {
         java.util.ArrayList list4 = new java.util.ArrayList();

         for (BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos4 : this.betterBlockPosXSYSZSBlockPosArray) {
            if (!AStarPredictor.matchesHeuristicManagerByHeuristicManager(heuristicManager, betterBlockPosXSYSZSBlockPos4.count9, betterBlockPosXSYSZSBlockPos4.count12, betterBlockPosXSYSZSBlockPos4.count10)) {
               list4.add(betterBlockPosXSYSZSBlockPos4);
            }
         }

         this.list = list4;
         return list4;
      }
   }

   public abstract double computeValue(ItemStackEntry itemStackEntry);

   public List resolveList(HeuristicManager heuristicManager) {
      if (this.list3 == null) {
         this.list3 = new java.util.ArrayList();
      }

      return this.list3;
   }

   public void applyItemStackEntry(ItemStackEntry itemStackEntry) {
      this.memberRef2 = itemStackEntry.heuristicManager.checkIndex(this.betterBlockPosXSYSZSBlockPos2.count9, this.betterBlockPosXSYSZSBlockPos2.count10);
   }

   public boolean isActiveAsFlag() {
      return this.getSet().contains(this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS())
         || this.getSet().contains(((AlreadyDoingItManager)this.activeListener.getCoreListener()).getBetterBlockPosXSYSZSBlockPos());
   }

   public abstract Set of();

   public AStarPipeline(ActiveListener activeListener2, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos4, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos5, BetterBlockPosXSYSZSBlockPos[] betterBlockPosXSYSZSBlockPosArray2) {
      this(activeListener2, betterBlockPosXSYSZSBlockPos4, betterBlockPosXSYSZSBlockPos5, betterBlockPosXSYSZSBlockPosArray2, null);
   }

   @Override
   public WAITINGCANCELEDEnum getWAITINGCANCELEDEnum() {
      this.staticListener.getEntityPlayerSP().invulnerableManager.state = false;
      this.heuristicPlanner = this.resolveHeuristicPlanner(this.heuristicPlanner);
      if (AStarPredictor.matchesStaticListener2(this.staticListener, this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS())) {
         this.heuristicPlanner.resolveHeuristicPlanner(BaseCustom.JUMP, true);
      }

      if (this.staticListener.getEntityPlayerSP().isActive15()) {
         this.staticListener.getOptional().ifPresent((item -> this.applyBlockPos((BlockPos)item)));
         this.heuristicPlanner.resolveHeuristicPlanner(BaseCustom.CLICK_LEFT, true);
      }

      this.heuristicPlanner.getAStarHandler().ofNullable().ifPresent((item -> this.applyDefaultCustom((DefaultCustom)item)));
      this.activeListener.getPassiveListener().clear();
      this.heuristicPlanner.getMap().forEach((key, value) -> this.applyBaseCustom((BaseCustom)key, (Boolean)value));
      this.heuristicPlanner.getMap().clear();
      if (this.heuristicPlanner.getWAITINGCANCELEDEnum().isEnabled()) {
         this.activeListener.getPassiveListener().clear();
      }

      return this.heuristicPlanner.getWAITINGCANCELEDEnum();
   }

   @Override
   public void updateState2() {
      this.list = null;
      this.list2 = null;
      this.list3 = null;
   }
}
