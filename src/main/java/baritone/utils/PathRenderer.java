package baritone.utils;

import baritone.api.BaritoneAPI;
import baritone.api.event.events.RenderEvent;
import baritone.api.pathing.goals.Goal;
import baritone.api.pathing.goals.GoalComposite;
import baritone.api.pathing.goals.GoalGetToBlock;
import baritone.api.pathing.goals.GoalInverted;
import baritone.api.pathing.goals.GoalTwoBlocks;
import baritone.api.pathing.goals.GoalXZ;
import baritone.api.pathing.goals.GoalYLevel;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.IPlayerContext;
import baritone.behavior.PathingBehavior;
import baritone.api.pathing.calc.IPath;
import baritone.pathing.calc.AbstractNodeCostSearch;
import baritone.pathing.path.PathExecutor;
import baritone.api.utils.interfaces.IGoalRenderPos;
import java.awt.Color;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityBeaconRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public final class PathRenderer implements IRenderer {
   public static void drawManySelectionBoxes(Entity entity, Collection items, Color color2) {
      IRenderer.startLines(color2, (Float)settings.pathRenderLineWidthPixels.value, (Boolean)settings.renderSelectionBoxesIgnoreDepth.value);
      BlockStateInterface blockStateInterface = new BlockStateInterface(BaritoneAPI.getProvider().getPrimaryBaritone().getPlayerContext());
      items.forEach(obj -> {
         IBlockState state = blockStateInterface.get0((BlockPos)obj);
         AxisAlignedBB box;
         if (state.getBlock().equals(Blocks.air)) {
            box = Blocks.dirt.getDefaultState().getBlock().getSelectedBoundingBox(entity.entityWorld, (BlockPos)obj);
         } else {
            box = state.getBlock().getSelectedBoundingBox(entity.entityWorld, (BlockPos)obj);
         }

         IRenderer.emitAABB(box, 0.002);
      });
      IRenderer.endLines((Boolean)settings.renderSelectionBoxesIgnoreDepth.value);
   }

   private static void drawHorizontalQuad(double d, double d2, double d3, double d4, double d5) {
      if (d5 != 0.0) {
         buffer.pos(d, d5, d3).color(color[0], color[1], color[2], color[3]).endVertex();
         buffer.pos(d2, d5, d3).color(color[0], color[1], color[2], color[3]).endVertex();
         buffer.pos(d2, d5, d3).color(color[0], color[1], color[2], color[3]).endVertex();
         buffer.pos(d2, d5, d4).color(color[0], color[1], color[2], color[3]).endVertex();
         buffer.pos(d2, d5, d4).color(color[0], color[1], color[2], color[3]).endVertex();
         buffer.pos(d, d5, d4).color(color[0], color[1], color[2], color[3]).endVertex();
         buffer.pos(d, d5, d4).color(color[0], color[1], color[2], color[3]).endVertex();
         buffer.pos(d, d5, d3).color(color[0], color[1], color[2], color[3]).endVertex();
      }
   }

   private static void emitLine(double d, double d2, double d3, double d4, double d5, double d6, double d7) {
      double d8 = d7 + 0.03;
      double viewerPosX = renderManager.viewerPosX;
      double viewerPosY = renderManager.viewerPosY;
      double viewerPosZ = renderManager.viewerPosZ;
      boolean flag = !(Boolean)settings.renderPathAsLine.value;
      buffer.pos(d + d7 - viewerPosX, d2 + d7 - viewerPosY, d3 + d7 - viewerPosZ).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(d4 + d7 - viewerPosX, d5 + d7 - viewerPosY, d6 + d7 - viewerPosZ).color(color[0], color[1], color[2], color[3]).endVertex();
      if (flag) {
         buffer.pos(d4 + d7 - viewerPosX, d5 + d7 - viewerPosY, d6 + d7 - viewerPosZ).color(color[0], color[1], color[2], color[3]).endVertex();
         buffer.pos(d4 + d7 - viewerPosX, d5 + d8 - viewerPosY, d6 + d7 - viewerPosZ).color(color[0], color[1], color[2], color[3]).endVertex();
         buffer.pos(d4 + d7 - viewerPosX, d5 + d8 - viewerPosY, d6 + d7 - viewerPosZ).color(color[0], color[1], color[2], color[3]).endVertex();
         buffer.pos(d + d7 - viewerPosX, d2 + d8 - viewerPosY, d3 + d7 - viewerPosZ).color(color[0], color[1], color[2], color[3]).endVertex();
         buffer.pos(d + d7 - viewerPosX, d2 + d8 - viewerPosY, d3 + d7 - viewerPosZ).color(color[0], color[1], color[2], color[3]).endVertex();
         buffer.pos(d + d7 - viewerPosX, d2 + d7 - viewerPosY, d3 + d7 - viewerPosZ).color(color[0], color[1], color[2], color[3]).endVertex();
      }
   }

   private static void drawGoal(Entity entity, Goal goal, float f, Color color2, boolean flag) {
      double viewerPosX = renderManager.viewerPosX;
      double viewerPosY = renderManager.viewerPosY;
      double viewerPosZ = renderManager.viewerPosZ;
      double d;
      if (!(Boolean)settings.renderGoalAnimated.value) {
         d = 0.999F;
      } else {
         d = (double)MathHelper.cos((float)((double)((float)(System.nanoTime() / 100000L % 20000L) / 20000.0F) * Math.PI * 2.0));
      }

      if (goal instanceof IGoalRenderPos) {
         BlockPos goalPos = ((IGoalRenderPos)goal).getGoalPos();
         double d2 = (double)goalPos.getX() + 0.002 - viewerPosX;
         double d3 = (double)(goalPos.getX() + 1) - 0.002 - viewerPosX;
         double d4 = (double)goalPos.getZ() + 0.002 - viewerPosZ;
         double d5 = (double)(goalPos.getZ() + 1) - 0.002 - viewerPosZ;
         if (goal instanceof GoalGetToBlock || goal instanceof GoalTwoBlocks) {
            d /= 2.0;
         }

         double d6 = 1.0 + d + (double)goalPos.getY() - viewerPosY;
         double d7 = 1.0 - d + (double)goalPos.getY() - viewerPosY;
         double d8 = (double)goalPos.getY() - viewerPosY;
         double d9 = d8 + 2.0;
         if (goal instanceof GoalGetToBlock || goal instanceof GoalTwoBlocks) {
            d6 -= 0.5;
            d7 -= 0.5;
            d9--;
         }

         drawDankLitGoalBox(color2, d2, d3, d4, d5, d8, d9, d6, d7, flag);
      } else if (goal instanceof GoalXZ) {
         GoalXZ goalXZ = (GoalXZ)goal;
         if ((Boolean)settings.renderGoalXZBeacon.value) {
            textureManager.bindTexture(TileEntityBeaconRenderer.beaconBeam);
            if ((Boolean)settings.renderGoalIgnoreDepth.value) {
               GlStateManager.disableDepth();
            }

            TileEntityBeaconRenderer.renderBeaconBeam(
               (double)goalXZ.getX() - viewerPosX,
               -viewerPosY,
               (double)goalXZ.getZ() - viewerPosZ,
               (Boolean)settings.renderGoalAnimated.value ? f : 0.0F,
               (Boolean)settings.renderGoalAnimated.value ? entity.entityWorld.getTotalWorldTime() : 0L,
               256,
               color2.getColorComponents(null)
            );
            if ((Boolean)settings.renderGoalIgnoreDepth.value) {
               GlStateManager.enableDepth();
            }

            return;
         }

         double d10 = (double)goalXZ.getX() + 0.002 - viewerPosX;
         double d11 = (double)(goalXZ.getX() + 1) - 0.002 - viewerPosX;
         double d12 = (double)goalXZ.getZ() + 0.002 - viewerPosZ;
         double d13 = (double)(goalXZ.getZ() + 1) - 0.002 - viewerPosZ;
         double d14 = 0.0;
         double d15 = 0.0;
         drawDankLitGoalBox(color2, d10, d11, d12, d13, 0.0 - viewerPosY, 256.0 - viewerPosY, d14, d15, flag);
      } else if (goal instanceof GoalComposite) {
         boolean flag2 = Arrays.stream(((GoalComposite)goal).getGoals()).allMatch(IGoalRenderPos.class::isInstance);
         if (flag2) {
            IRenderer.startLines(color2, (Float)settings.goalRenderLineWidthPixels.value, (Boolean)settings.renderGoalIgnoreDepth.value);
         }

         for (Goal goal2 : ((GoalComposite)goal).getGoals()) {
            drawGoal(entity, goal2, f, color2, !flag2);
         }

         if (flag2) {
            IRenderer.endLines((Boolean)settings.renderGoalIgnoreDepth.value);
         }
      } else if (goal instanceof GoalInverted) {
         drawGoal(entity, ((GoalInverted)goal).origin, f, (Color)settings.colorInvertedGoalBox.value);
      } else if (goal instanceof GoalYLevel) {
         GoalYLevel goalYLevel = (GoalYLevel)goal;
         double d18 = entity.posX - (Double)settings.yLevelBoxSize.value - viewerPosX;
         double d19 = entity.posZ - (Double)settings.yLevelBoxSize.value - viewerPosZ;
         double d20 = entity.posX + (Double)settings.yLevelBoxSize.value - viewerPosX;
         double d21 = entity.posZ + (Double)settings.yLevelBoxSize.value - viewerPosZ;
         double d22 = (double)((GoalYLevel)goal).level - viewerPosY;
         drawDankLitGoalBox(color2, d18, d20, d19, d21, d22, d22 + 2.0, 1.0 + d + (double)goalYLevel.level - viewerPosY, 1.0 - d + (double)goalYLevel.level - viewerPosY, flag);
      }
   }

   private PathRenderer() {
   }

   public static void render(RenderEvent renderEvent, PathingBehavior pathingBehavior) {
      IPlayerContext ctx = pathingBehavior.ctx;
      if (ctx.world() != null) {
         if (ctx.getMc().currentScreen instanceof GuiClick) {
            ((GuiClick)ctx.getMc().currentScreen).onRender();
         }

         float partialTicks = renderEvent.getPartialTicks();
         Goal goal = pathingBehavior.getGoal();
         if (ctx.world().provider.getDimensionId() == BaritoneAPI.getProvider().getPrimaryBaritone().getPlayerContext().world().provider.getDimensionId()) {
            if (goal != null && (Boolean)settings.renderGoal.value) {
               drawGoal(ctx.player(), goal, partialTicks, (Color)settings.colorGoalBox.value);
            }

            if ((Boolean)settings.renderPath.value) {
               PathExecutor current2 = pathingBehavior.getCurrent2();
               PathExecutor next2 = pathingBehavior.getNext2();
               if (current2 != null && (Boolean)settings.renderSelectionBoxes.value) {
                  drawManySelectionBoxes(ctx.player(), current2.toBreak(), (Color)settings.colorBlocksToBreak.value);
                  drawManySelectionBoxes(ctx.player(), current2.toPlace(), (Color)settings.colorBlocksToPlace.value);
                  drawManySelectionBoxes(ctx.player(), current2.toWalkInto(), (Color)settings.colorBlocksToWalkInto.value);
               }

               if (current2 != null && current2.getPath() != null) {
                  int n = Math.max(current2.getPosition() - 3, 0);
                  drawPath(current2.getPath().positions(), n, (Color)settings.colorCurrentPath.value, (Boolean)settings.fadePath.value, 10, 20);
               }

               if (next2 != null && next2.getPath() != null) {
                  drawPath(next2.getPath().positions(), 0, (Color)settings.colorNextPath.value, (Boolean)settings.fadePath.value, 10, 20);
               }

               pathingBehavior.getInProgress().ifPresent(obj -> {
                  ((AbstractNodeCostSearch)obj).bestPathSoFar().ifPresent(obj2 -> drawPath(((IPath)obj2).positions(), 0, (Color)settings.colorBestPathSoFar.value, (Boolean)settings.fadePath.value, 10, 20));
                  ((AbstractNodeCostSearch)obj).pathToMostRecentNodeConsidered().ifPresent(obj3 -> {
                     drawPath(((IPath)obj3).positions(), 0, (Color)settings.colorMostRecentConsidered.value, (Boolean)settings.fadePath.value, 10, 20);
                     drawManySelectionBoxes(ctx.player(), Collections.singletonList(((IPath)obj3).getDest()), (Color)settings.colorMostRecentConsidered.value);
                  });
               });
            }
         }
      }
   }

   public static void drawGoal(Entity entity, Goal goal, float f, Color color2) {
      drawGoal(entity, goal, f, color2, true);
   }

   public static void drawPath(List list, int n, Color color2, boolean flag, int n2, int n3, double d) {
      IRenderer.startLines(color2, (Float)settings.pathRenderLineWidthPixels.value, (Boolean)settings.renderPathIgnoreDepth.value);
      int n4 = n2 + n;
      int n5 = n3 + n;
      int n6 = n;

      while (n6 < list.size() - 1) {
         BetterBlockPos betterBlockPos = (BetterBlockPos)list.get(n6);
         int n7;
         BetterBlockPos betterBlockPos2 = (BetterBlockPos)list.get(n7 = n6 + 1);
         int n8 = betterBlockPos2.x2 - betterBlockPos.x2;
         int n9 = betterBlockPos2.y2 - betterBlockPos.y2;
         int n10 = betterBlockPos2.z2 - betterBlockPos.z2;

         while (
            n7 + 1 < list.size()
               && (!flag || n7 + 1 < n4)
               && n8 == ((BetterBlockPos)list.get(n7 + 1)).x2 - betterBlockPos2.x2
               && n9 == ((BetterBlockPos)list.get(n7 + 1)).y2 - betterBlockPos2.y2
               && n10 == ((BetterBlockPos)list.get(n7 + 1)).z2 - betterBlockPos2.z2
         ) {
            betterBlockPos2 = (BetterBlockPos)list.get(++n7);
         }

         if (flag) {
            float f;
            if (n6 <= n4) {
               f = 0.4F;
            } else {
               if (n6 > n5) {
                  break;
               }

               f = 0.4F * (1.0F - (float)(n6 - n4) / (float)(n5 - n4));
            }

            IRenderer.glColor(color2, f);
         }

         emitLine((double)betterBlockPos.x2, (double)betterBlockPos.y2, (double)betterBlockPos.z2, (double)betterBlockPos2.x2, (double)betterBlockPos2.y2, (double)betterBlockPos2.z2, d);
         n6 = n7;
      }

      IRenderer.endLines((Boolean)settings.renderPathIgnoreDepth.value);
   }

   public static void drawPath(List list, int n, Color color2, boolean flag, int n2, int n3) {
      drawPath(list, n, color2, flag, n2, n3, 0.5);
   }

   private static void drawDankLitGoalBox(
      Color color2, double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, boolean flag
   ) {
      if (flag) {
         IRenderer.startLines(color2, (Float)settings.goalRenderLineWidthPixels.value, (Boolean)settings.renderGoalIgnoreDepth.value);
      }

      drawHorizontalQuad(d, d2, d3, d4, d7);
      drawHorizontalQuad(d, d2, d3, d4, d8);
      buffer.pos(d, d5, d3).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(d, d6, d3).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(d2, d5, d3).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(d2, d6, d3).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(d2, d5, d4).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(d2, d6, d4).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(d, d5, d4).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(d, d6, d4).color(color[0], color[1], color[2], color[3]).endVertex();
      if (flag) {
         IRenderer.endLines((Boolean)settings.renderGoalIgnoreDepth.value);
      }
   }
}
