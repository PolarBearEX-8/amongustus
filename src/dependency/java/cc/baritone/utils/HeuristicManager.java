package cc.baritone.utils;

import client.event.MultiplayerChunkCacheListener;
import client.util.AlertNotificationSound;
import client.util.IBlockState;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.block.AuxiliaryBlockPosAdapter;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class HeuristicManager {
   public final Long2ObjectMap long2ObjectMap;
   public final DepthFirstEvaluator depthFirstEvaluator;
   public final AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter;
   public final IBlockAccess iBlockAccess;
   public final DirectCustom directCustom;
   public AlertNotificationSound alertNotificationSound = null;
   public RemovingChunkBlockStateService removingChunkBlockStateService = null;
   public final boolean enabled;
   public static final IBlockState iBlockState = Blocks.air.getDefaultState();

   public IBlockState resolveIBlockState(BlockPos blockPos) {
      return this.resolveIBlockState(blockPos.getX(), blockPos.getY(), blockPos.getZ());
   }

   public static IBlockState resolveIBlockState(StaticListener staticListener, BlockPos blockPos) {
      return new HeuristicManager(staticListener).resolveIBlockState(blockPos.getX(), blockPos.getY(), blockPos.getZ());
   }

   public static Block resolveBlock(StaticListener staticListener, BlockPos blockPos) {
      return resolveIBlockState(staticListener, blockPos).getBlock();
   }

   public IBlockState resolveIBlockState(int count, int count2, int count3) {
      if (count2 >= 0 && count2 < 256) {
         if (this.enabled) {
            AlertNotificationSound alertNotificationSound2 = this.alertNotificationSound;
            if (alertNotificationSound2 != null && alertNotificationSound2.indexOffset1 == count >> 4 && alertNotificationSound2.indexOffset == count3 >> 4) {
               return alertNotificationSound2.resolveIBlockState(count, count2, count3);
            }

            AlertNotificationSound alertNotificationSound3 = (AlertNotificationSound)this.long2ObjectMap.get(AStarVerifier.computeTimestamp(count >> 4, count3 >> 4));
            if (alertNotificationSound3 != null && alertNotificationSound3.canExecute2()) {
               this.alertNotificationSound = alertNotificationSound3;
               return alertNotificationSound3.resolveIBlockState(count, count2, count3);
            }
         }

         RemovingChunkBlockStateService removingChunkBlockStateService2 = this.removingChunkBlockStateService;
         if (removingChunkBlockStateService2 == null || removingChunkBlockStateService2.getCount2() != count >> 9 || removingChunkBlockStateService2.getCount3() != count3 >> 9) {
            if (this.depthFirstEvaluator == null) {
               return iBlockState;
            }

            removingChunkBlockStateService2 = this.depthFirstEvaluator.cachedWorldBreadthFirstPipeline.resolveRemovingChunkBlockStateServiByIndex(count >> 9, count3 >> 9);
            if (removingChunkBlockStateService2 == null) {
               return iBlockState;
            }

            this.removingChunkBlockStateService = removingChunkBlockStateService2;
         }

         IBlockState blockState = removingChunkBlockStateService2.resolveIBlockState(count & 511, count2, count3 & 511);
         return blockState == null ? iBlockState : blockState;
      } else {
         return iBlockState;
      }
   }

   public boolean checkIndexByIndex(int count, int count2) {
      AlertNotificationSound alertNotificationSound2 = this.alertNotificationSound;
      if (alertNotificationSound2 != null && alertNotificationSound2.indexOffset1 == count >> 4 && alertNotificationSound2.indexOffset == count2 >> 4) {
         return true;
      } else {
         AlertNotificationSound alertNotificationSound3 = (AlertNotificationSound)this.long2ObjectMap.get(AStarVerifier.computeTimestamp(count >> 4, count2 >> 4));
         if (alertNotificationSound3 != null && alertNotificationSound3.canExecute2()) {
            this.alertNotificationSound = alertNotificationSound3;
            return true;
         } else {
            RemovingChunkBlockStateService removingChunkBlockStateService2 = this.removingChunkBlockStateService;
            if (removingChunkBlockStateService2 != null && removingChunkBlockStateService2.getCount2() == count >> 9 && removingChunkBlockStateService2.getCount3() == count2 >> 9) {
               return removingChunkBlockStateService2.checkIndex(count & 511, count2 & 511);
            } else if (this.depthFirstEvaluator == null) {
               return false;
            } else {
               RemovingChunkBlockStateService removingChunkBlockStateService3 = this.depthFirstEvaluator
                  .cachedWorldBreadthFirstPipeline
                  .resolveRemovingChunkBlockStateServiByIndex(count >> 9, count2 >> 9);
               if (removingChunkBlockStateService3 == null) {
                  return false;
               } else {
                  this.removingChunkBlockStateService = removingChunkBlockStateService3;
                  return removingChunkBlockStateService3.checkIndex(count & 511, count2 & 511);
               }
            }
         }
      }
   }

   public HeuristicManager(StaticListener staticListener) {
      this(staticListener, false);
   }

   public boolean checkIndex(int count, int count2) {
      return this.long2ObjectMap.containsKey(AStarVerifier.computeTimestamp(count >> 4, count2 >> 4));
   }

   public HeuristicManager(StaticListener staticListener, boolean flag) {
      World world = staticListener.getWorld();
      this.directCustom = new DirectCustom(world.getInternalListEntry());
      this.depthFirstEvaluator = (DepthFirstEvaluator)staticListener.getNativeListener();
      Long2ObjectOpenHashMap long2ObjectOpenHashMap = new Long2ObjectOpenHashMap();

      for (long value : ((MultiplayerChunkCacheListener)world.getCommonListener()).internalFloatEntry.getTimeArray()) {
         AlertNotificationSound alertNotificationSound2 = (AlertNotificationSound)((MultiplayerChunkCacheListener)world.getCommonListener())
            .internalFloatEntry
            .resolveTarget(value);
         long2ObjectOpenHashMap.put(value, alertNotificationSound2);
      }

      if (flag) {
         this.long2ObjectMap = new Long2ObjectOpenHashMap(long2ObjectOpenHashMap);
      } else {
         this.long2ObjectMap = long2ObjectOpenHashMap;
      }

      this.enabled = !(Boolean)BaritoneManager.getSettings().pathThroughCachedOnly.dataRef;
      if (!staticListener.getMc().isSingleplayer()) {
         throw new IllegalStateException();
      } else {
         this.auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter();
         this.iBlockAccess = new HeuristicManagerBlockAccess(this, world);
      }
   }
}
