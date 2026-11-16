package cc.baritone.utils;

import cc.baritone.pathing.PathFinderCannotBeReusedManager;
import client.util.CustomResourceLocationArrayHandler;
import client.util.IBlockState;
import java.awt.Color;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public final class DepthFirstSampler implements TessellatorCallback {
   private static void applyHeuristicManager(HeuristicManager heuristicManager, Entity entity, BlockPos blockPos) {
      IBlockState blockState = heuristicManager.resolveIBlockState(blockPos);
      AxisAlignedBB boundingBox;
      if (blockState.getBlock().equals(Blocks.air)) {
         boundingBox = Blocks.block115.getDefaultState().getBlock().processAction(entity.worldObj, blockPos);
      } else {
         boundingBox = blockState.getBlock().processAction(entity.worldObj, blockPos);
      }

      TessellatorCallback.applyAxisAlignedBB(boundingBox, 0.002);
   }

   public static void applyList(List list, int count, Color color, boolean flag, int count2, int count3) {
      applyList(list, count, color, flag, count2, count3, 0.5);
   }

   private static void applyCustom(Custom custom) {
      applyList(custom.unmodifiableList(), 0, (Color)settings.colorBestPathSoFar.dataRef, (Boolean)settings.fadePath.dataRef, 10, 20);
   }

   private static void applyStaticListener(StaticListener staticListener, Custom custom) {
      applyList(custom.unmodifiableList(), 0, (Color)settings.colorMostRecentConsidered.dataRef, (Boolean)settings.fadePath.dataRef, 10, 20);
      applyEntity(
         staticListener.getEntityPlayerSP(), Collections.singletonList(custom.getBetterBlockPosXSYSZSBlockPos2()), (Color)settings.colorMostRecentConsidered.dataRef
      );
   }

   private static void applyEntity(Entity entity, SecondaryListener secondaryListener, float factor, Color color, boolean flag) {
      double viewerPosX = renderManager.viewerPosX;
      double viewerPosZ = renderManager.viewerPosZ;
      double viewerPosY = renderManager.viewerPosY;
      double offset;
      if (!(Boolean)settings.renderGoalAnimated.dataRef) {
         offset = 0.999F;
      } else {
         offset = (double)MathHelper.cos((float)((double)((float)(System.nanoTime() / 100000L % 20000L) / 20000.0F) * Math.PI * 2.0));
      }

      if (secondaryListener instanceof VirtualCustom) {
         BlockPos blockPos = ((VirtualCustom)secondaryListener).getBlockPos();
         double offset2 = (double)blockPos.getX() + 0.002 - viewerPosX;
         double offset3 = (double)(blockPos.getX() + 1) - 0.002 - viewerPosX;
         double offset4 = (double)blockPos.getZ() + 0.002 - viewerPosY;
         double offset5 = (double)(blockPos.getZ() + 1) - 0.002 - viewerPosY;
         if (secondaryListener instanceof AStarManager || secondaryListener instanceof HeuristicWatcher) {
            offset /= 2.0;
         }

         double offset6 = 1.0 + offset + (double)blockPos.getY() - viewerPosZ;
         double offset7 = 1.0 - offset + (double)blockPos.getY() - viewerPosZ;
         double offset8 = (double)blockPos.getY() - viewerPosZ;
         double offset9 = offset8 + 2.0;
         if (secondaryListener instanceof AStarManager || secondaryListener instanceof HeuristicWatcher) {
            offset6 -= 0.5;
            offset7 -= 0.5;
            offset9--;
         }

         applyColor(color, offset2, offset3, offset4, offset5, offset8, offset9, offset6, offset7, flag);
      } else if (secondaryListener instanceof DiagonalEvaluator) {
         DiagonalEvaluator diagonalEvaluator = (DiagonalEvaluator)secondaryListener;
         if ((Boolean)settings.renderGoalXZBeacon.dataRef) {
            textureManager.applyResourceLocation3(CustomResourceLocationArrayHandler.resourceLocation2);
            if ((Boolean)settings.renderGoalIgnoreDepth.dataRef) {
               GlStateManager.enableDepth();
            }

            CustomResourceLocationArrayHandler.applyFloatArray(
               (double)diagonalEvaluator.getCount() - viewerPosX,
               -viewerPosZ,
               (double)diagonalEvaluator.getCount2() - viewerPosY,
               (Boolean)settings.renderGoalAnimated.dataRef ? factor : 0.0F,
               (Boolean)settings.renderGoalAnimated.dataRef ? entity.worldObj.getTimestamp2() : 0L,
               256,
               color.getColorComponents(null)
            );
            if ((Boolean)settings.renderGoalIgnoreDepth.dataRef) {
               GlStateManager.disableDepth();
            }

            return;
         }

         double offset10 = (double)diagonalEvaluator.getCount() + 0.002 - viewerPosX;
         double offset11 = (double)(diagonalEvaluator.getCount() + 1) - 0.002 - viewerPosX;
         double offset12 = (double)diagonalEvaluator.getCount2() + 0.002 - viewerPosY;
         double offset13 = (double)(diagonalEvaluator.getCount2() + 1) - 0.002 - viewerPosY;
         double offset14 = 0.0;
         double offset15 = 0.0;
         double offset16 = 0.0 - viewerPosZ;
         double offset17 = 256.0 - viewerPosZ;
         applyColor(color, offset10, offset11, offset12, offset13, offset16, offset17, offset14, offset15, flag);
      } else if (secondaryListener instanceof DepthFirstCoordinator) {
         boolean flag2 = Arrays.stream(((DepthFirstCoordinator)secondaryListener).getSecondaryListenerArray()).allMatch(VirtualCustom.class::isInstance);
         if (flag2) {
            TessellatorCallback.applyColor(color, (Float)settings.goalRenderLineWidthPixels.dataRef, (Boolean)settings.renderGoalIgnoreDepth.dataRef);
         }

         for (SecondaryListener secondaryListener2 : ((DepthFirstCoordinator)secondaryListener).getSecondaryListenerArray()) {
            applyEntity(entity, secondaryListener2, factor, color, !flag2);
         }

         if (flag2) {
            TessellatorCallback.setEnabled((Boolean)settings.renderGoalIgnoreDepth.dataRef);
         }
      } else if (secondaryListener instanceof BreadthFirstEstimator) {
         applyEntity(entity, ((BreadthFirstEstimator)secondaryListener).secondaryListener, factor, (Color)settings.colorInvertedGoalBox.dataRef);
      } else if (secondaryListener instanceof BreadthFirstInspector) {
         BreadthFirstInspector breadthFirstInspector = (BreadthFirstInspector)secondaryListener;
         double offset18 = entity.posX - (Double)settings.yLevelBoxSize.dataRef - viewerPosX;
         double offset19 = entity.posZ - (Double)settings.yLevelBoxSize.dataRef - viewerPosY;
         double offset20 = entity.posX + (Double)settings.yLevelBoxSize.dataRef - viewerPosX;
         double offset21 = entity.posZ + (Double)settings.yLevelBoxSize.dataRef - viewerPosY;
         double offset22 = (double)((BreadthFirstInspector)secondaryListener).count - viewerPosZ;
         double offset23 = offset22 + 2.0;
         double offset24 = 1.0 + offset + (double)breadthFirstInspector.count - viewerPosZ;
         double offset25 = 1.0 - offset + (double)breadthFirstInspector.count - viewerPosZ;
         applyColor(color, offset18, offset20, offset19, offset21, offset22, offset23, offset24, offset25, flag);
      }
   }

   private static void applyDouble(double posX, double posX2, double posX3, double posY, double posZ) {
      if (posZ != 0.0) {
         worldRenderer.getWorldRenderer(posX, posZ, posX3).resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3]).updateState();
         worldRenderer.getWorldRenderer(posX2, posZ, posX3).resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3]).updateState();
         worldRenderer.getWorldRenderer(posX2, posZ, posX3).resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3]).updateState();
         worldRenderer.getWorldRenderer(posX2, posZ, posY).resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3]).updateState();
         worldRenderer.getWorldRenderer(posX2, posZ, posY).resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3]).updateState();
         worldRenderer.getWorldRenderer(posX, posZ, posY).resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3]).updateState();
         worldRenderer.getWorldRenderer(posX, posZ, posY).resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3]).updateState();
         worldRenderer.getWorldRenderer(posX, posZ, posX3).resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3]).updateState();
      }
   }

   private static void applyColor(
      Color color, double posX, double posX2, double posX3, double posX4, double posX5, double posX6, double posY, double posZ, boolean flag
   ) {
      if (flag) {
         TessellatorCallback.applyColor(color, (Float)settings.goalRenderLineWidthPixels.dataRef, (Boolean)settings.renderGoalIgnoreDepth.dataRef);
      }

      applyDouble(posX, posX2, posX3, posX4, posY);
      applyDouble(posX, posX2, posX3, posX4, posZ);
      worldRenderer.getWorldRenderer(posX, posX5, posX3).resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3]).updateState();
      worldRenderer.getWorldRenderer(posX, posX6, posX3).resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3]).updateState();
      worldRenderer.getWorldRenderer(posX2, posX5, posX3).resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3]).updateState();
      worldRenderer.getWorldRenderer(posX2, posX6, posX3).resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3]).updateState();
      worldRenderer.getWorldRenderer(posX2, posX5, posX4).resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3]).updateState();
      worldRenderer.getWorldRenderer(posX2, posX6, posX4).resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3]).updateState();
      worldRenderer.getWorldRenderer(posX, posX5, posX4).resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3]).updateState();
      worldRenderer.getWorldRenderer(posX, posX6, posX4).resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3]).updateState();
      if (flag) {
         TessellatorCallback.setEnabled((Boolean)settings.renderGoalIgnoreDepth.dataRef);
      }
   }

   public static void applyEntity(Entity entity, Collection collection, Color color) {
      TessellatorCallback.applyColor(color, (Float)settings.pathRenderLineWidthPixels.dataRef, (Boolean)settings.renderSelectionBoxesIgnoreDepth.dataRef);
      HeuristicManager heuristicManager = new HeuristicManager(SettingsTxtManager.getExtraListener().get().getStaticListener());
      collection.forEach(pos -> applyHeuristicManager(heuristicManager, entity, (BlockPos)pos));
      TessellatorCallback.setEnabled((Boolean)settings.renderSelectionBoxesIgnoreDepth.dataRef);
   }

   public static void applyEntity(Entity entity, SecondaryListener secondaryListener, float factor, Color color) {
      applyEntity(entity, secondaryListener, factor, color, true);
   }

   private DepthFirstSampler() {
   }

   private static void applyDouble(double posX, double posX2, double posX3, double posX4, double posX5, double posY, double posZ) {
      double offset = posZ + 0.03;
      double viewerPosX = renderManager.viewerPosX;
      double viewerPosZ = renderManager.viewerPosZ;
      double viewerPosY = renderManager.viewerPosY;
      boolean flag = !(Boolean)settings.renderPathAsLine.dataRef;
      worldRenderer.getWorldRenderer(posX + posZ - viewerPosX, posX2 + posZ - viewerPosZ, posX3 + posZ - viewerPosY)
         .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
         .updateState();
      worldRenderer.getWorldRenderer(posX4 + posZ - viewerPosX, posX5 + posZ - viewerPosZ, posY + posZ - viewerPosY)
         .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
         .updateState();
      if (flag) {
         worldRenderer.getWorldRenderer(posX4 + posZ - viewerPosX, posX5 + posZ - viewerPosZ, posY + posZ - viewerPosY)
            .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
            .updateState();
         worldRenderer.getWorldRenderer(posX4 + posZ - viewerPosX, posX5 + offset - viewerPosZ, posY + posZ - viewerPosY)
            .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
            .updateState();
         worldRenderer.getWorldRenderer(posX4 + posZ - viewerPosX, posX5 + offset - viewerPosZ, posY + posZ - viewerPosY)
            .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
            .updateState();
         worldRenderer.getWorldRenderer(posX + posZ - viewerPosX, posX2 + offset - viewerPosZ, posX3 + posZ - viewerPosY)
            .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
            .updateState();
         worldRenderer.getWorldRenderer(posX + posZ - viewerPosX, posX2 + offset - viewerPosZ, posX3 + posZ - viewerPosY)
            .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
            .updateState();
         worldRenderer.getWorldRenderer(posX + posZ - viewerPosX, posX2 + posZ - viewerPosZ, posX3 + posZ - viewerPosY)
            .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
            .updateState();
      }
   }

   private static void applyStaticListener(StaticListener staticListener, PathFinderCannotBeReusedManager pathFinderCannotBeReusedManager) {
      pathFinderCannotBeReusedManager.getOptional().ifPresent((item -> DepthFirstSampler.applyCustom((Custom)item)));
      pathFinderCannotBeReusedManager.getOptionalAsOptional().ifPresent(custom -> applyStaticListener(staticListener, (Custom)custom));
   }

   public static void applyDepthFirstTracker(DepthFirstTracker depthFirstTracker, AlreadyDoingItManager alreadyDoingItManager) {
      StaticListener staticListener = alreadyDoingItManager.staticListener;
      if (staticListener.getWorld() != null) {
         if (staticListener.getMc().currentScreen instanceof HeuristicService) {
            ((HeuristicService)staticListener.getMc().currentScreen).updateState();
         }

         float factor = depthFirstTracker.getFactor();
         SecondaryListener secondaryListener = alreadyDoingItManager.getSecondaryListener();
         int countAsIndex = staticListener.getWorld().secondaryFloatArrayEntry.getCountAsIndex();
         int countAsIndex2 = SettingsTxtManager.getExtraListener().get().getStaticListener().getWorld().secondaryFloatArrayEntry.getCountAsIndex();
         if (countAsIndex == countAsIndex2) {
            if (secondaryListener != null && (Boolean)settings.renderGoal.dataRef) {
               applyEntity(staticListener.getEntityPlayerSP(), secondaryListener, factor, (Color)settings.colorGoalBox.dataRef);
            }

            if ((Boolean)settings.renderPath.dataRef) {
               DepthFirstCalculator depthFirstCalculator = alreadyDoingItManager.getDepthFirstCalculator2();
               DepthFirstCalculator depthFirstCalculator2 = alreadyDoingItManager.getDepthFirstCalculator();
               if (depthFirstCalculator != null && (Boolean)settings.renderSelectionBoxes.dataRef) {
                  applyEntity(staticListener.getEntityPlayerSP(), depthFirstCalculator.unmodifiableSetAsSet(), (Color)settings.colorBlocksToBreak.dataRef);
                  applyEntity(staticListener.getEntityPlayerSP(), depthFirstCalculator.unmodifiableSet(), (Color)settings.colorBlocksToPlace.dataRef);
                  applyEntity(staticListener.getEntityPlayerSP(), depthFirstCalculator.unmodifiableSet2(), (Color)settings.colorBlocksToWalkInto.dataRef);
               }

               if (depthFirstCalculator != null && depthFirstCalculator.getCustom() != null) {
                  int count = Math.max(depthFirstCalculator.getCount() - 3, 0);
                  applyList(depthFirstCalculator.getCustom().unmodifiableList(), count, (Color)settings.colorCurrentPath.dataRef, (Boolean)settings.fadePath.dataRef, 10, 20);
               }

               if (depthFirstCalculator2 != null && depthFirstCalculator2.getCustom() != null) {
                  applyList(depthFirstCalculator2.getCustom().unmodifiableList(), 0, (Color)settings.colorNextPath.dataRef, (Boolean)settings.fadePath.dataRef, 10, 20);
               }

               alreadyDoingItManager.ofNullable().ifPresent(manager -> applyStaticListener(staticListener, (PathFinderCannotBeReusedManager)manager));
            }
         }
      }
   }

   public static void applyList(List list, int count, Color color, boolean flag, int count2, int count3, double offset) {
      TessellatorCallback.applyColor(color, (Float)settings.pathRenderLineWidthPixels.dataRef, (Boolean)settings.renderPathIgnoreDepth.dataRef);
      int count4 = count2 + count;
      int count5 = count3 + count;
      int count6 = count;

      while (count6 < list.size() - 1) {
         BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos = (BetterBlockPosXSYSZSBlockPos)list.get(count6);
         int count7;
         BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2 = (BetterBlockPosXSYSZSBlockPos)list.get(count7 = count6 + 1);
         int count8 = betterBlockPosXSYSZSBlockPos2.count9 - betterBlockPosXSYSZSBlockPos.count9;
         int count9 = betterBlockPosXSYSZSBlockPos2.count12 - betterBlockPosXSYSZSBlockPos.count12;
         int count10 = betterBlockPosXSYSZSBlockPos2.count10 - betterBlockPosXSYSZSBlockPos.count10;

         while (
            count7 + 1 < list.size()
               && (!flag || count7 + 1 < count4)
               && count8 == ((BetterBlockPosXSYSZSBlockPos)list.get(count7 + 1)).count9 - betterBlockPosXSYSZSBlockPos2.count9
               && count9 == ((BetterBlockPosXSYSZSBlockPos)list.get(count7 + 1)).count12 - betterBlockPosXSYSZSBlockPos2.count12
               && count10 == ((BetterBlockPosXSYSZSBlockPos)list.get(count7 + 1)).count10 - betterBlockPosXSYSZSBlockPos2.count10
         ) {
            betterBlockPosXSYSZSBlockPos2 = (BetterBlockPosXSYSZSBlockPos)list.get(++count7);
         }

         if (flag) {
            float factor;
            if (count6 <= count4) {
               factor = 0.4F;
            } else {
               if (count6 > count5) {
                  break;
               }

               factor = 0.4F * (1.0F - (float)(count6 - count4) / (float)(count5 - count4));
            }

            TessellatorCallback.applyColor(color, factor);
         }

         applyDouble(
            (double)betterBlockPosXSYSZSBlockPos.count9, (double)betterBlockPosXSYSZSBlockPos.count12, (double)betterBlockPosXSYSZSBlockPos.count10, (double)betterBlockPosXSYSZSBlockPos2.count9, (double)betterBlockPosXSYSZSBlockPos2.count12, (double)betterBlockPosXSYSZSBlockPos2.count10, offset
         );
         count6 = count7;
      }

      TessellatorCallback.setEnabled((Boolean)settings.renderPathIgnoreDepth.dataRef);
   }
}
