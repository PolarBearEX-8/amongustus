package cc.baritone.utils;

import cc.baritone.api.INACTIVEHandlerAdapter;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;

public final class FollowingListener extends INACTIVEHandlerAdapter implements AuxiliaryListener {
   public Predicate predicate;
   public List list;

   private SecondaryListener resolveSecondaryListenerByEntity(Entity entity) {
      BlockPos blockPos;
      if ((Double)BaritoneManager.getSettings().followOffsetDistance.dataRef == 0.0) {
         blockPos = new BlockPos(entity);
      } else {
         DiagonalEvaluator diagonalEvaluator = DiagonalEvaluator.resolveDiagonalEvaluator(
            entity.getVec3(),
            (Float)BaritoneManager.getSettings().followOffsetDirection.dataRef,
            (Double)BaritoneManager.getSettings().followOffsetDistance.dataRef
         );
         blockPos = new BlockPos((double)diagonalEvaluator.getCount(), entity.posY, (double)diagonalEvaluator.getCount2());
      }

      return new AdvancedCustom(blockPos, (Integer)BaritoneManager.getSettings().followRadius.dataRef);
   }

   private static SecondaryListener[] resolveSecondaryListenerArray(int count) {
      return new SecondaryListener[count];
   }

   @Override
   public String getText() {
      return "Following " + this.list;
   }

   @Override
   public void setPredicate(Predicate predicate2) {
      this.predicate = predicate2;
   }

   @Override
   public void updateState() {
      this.predicate = null;
      this.list = null;
   }

   @Override
   public boolean isEnabled() {
      if (this.predicate == null) {
         return false;
      } else {
         this.updateState3();
         return !this.list.isEmpty();
      }
   }

   private boolean matchesEntityByEntity(Entity entity) {
      if (entity == null) {
         return false;
      } else if (entity.isDead) {
         return false;
      } else {
         return entity.equals(this.staticListener.getEntityPlayerSP()) ? false : this.staticListener.getWorld().loadedEntityList.contains(entity);
      }
   }

   @Override
   public Listener5Entry resolveListener5Entry(boolean flag, boolean flag2) {
      this.updateState3();
      DepthFirstCoordinator depthFirstCoordinator = new DepthFirstCoordinator(
         (SecondaryListener[])this.list.stream().map((item -> this.resolveSecondaryListenerByEntity((Entity)item))).toArray(FollowingListener::resolveSecondaryListenerArray)
      );
      return new Listener5Entry(depthFirstCoordinator, HeuristicCoordinator.REVALIDATE_GOAL_AND_PATH);
   }

   @Override
   public List getList() {
      return this.list;
   }

   @Override
   public Predicate getPredicate() {
      return this.predicate;
   }

   private void updateState3() {
      this.list = (List)Stream.of(this.staticListener.getWorld().loadedEntityList, this.staticListener.getWorld().itemList7)
         .flatMap(Collection::stream)
         .filter((item -> this.matchesEntityByEntity((Entity)item)))
         .filter(this.predicate)
         .distinct()
         .collect(Collectors.toList());
   }

   public FollowingListener(BaritoneManager baritoneManager) {
      super(baritoneManager);
   }
}
