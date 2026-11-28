package net.augustus.modules.render;

import java.awt.Color;
import java.util.WeakHashMap;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.modules.render.helper.ChunkAnimationEntry;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.animation.AnimationUtil;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;

public final class ChunkAnimator extends Module {
   private final String[] animationModes;
   public final StringValue mode;
   public final StringValue animation;
   public final DoubleValue animationTime;
   public final BooleanValue disableAroundPlayer;
   private final WeakHashMap chunkAnimations;

   public ChunkAnimator() {
       super("ChunkAnimator", "LadungsAnimationFürBereiche", new Color(54, 21, 230), Categorys.RENDER);
      this.animationModes = new String[]{"Below", "Above", "Horizon", "Cardinal", "Variation"};
      this.mode = new StringValue(this, "Mode", this.animationModes[0], this.animationModes);
      this.animation = new StringValue(this, "Animation", "easeOutBack", AnimationUtil.getEasingNames(true));
      this.animationTime = new DoubleValue(this, "AnimationTime", 500.0, 0.0, 2000.0, 0).visibleWhen(() -> this.isAnimationTimeVisible());
      this.disableAroundPlayer = new BooleanValue(this, "DisableAroundPlayer", false);
      this.chunkAnimations = new WeakHashMap();
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
   }

   public void applyChunkAnimation(RenderChunk renderChunk) {
      if (this.isToggled()) {
         ChunkAnimationEntry chunkAnimationEntry = (ChunkAnimationEntry)this.chunkAnimations.get(renderChunk);
         if (chunkAnimationEntry != null) {
            long startTimeMs = chunkAnimationEntry.startTimeMs;
            int selectedIndex = this.mode.getSelectedIndex();
            if (startTimeMs == -1L) {
               startTimeMs = System.currentTimeMillis();
               chunkAnimationEntry.startTimeMs = startTimeMs;
               if (selectedIndex == 4) {
                  BlockPos position = mc.thePlayer.getPosition();
                  position = position.add(0, -position.getY(), 0);
                  chunkAnimationEntry.slideDirection = getDominantHorizontalFacing(position.subtract(renderChunk.getPosition().add(8, -renderChunk.getPosition().getY(), 8)));
               }
            }

            long l = System.currentTimeMillis() - startTimeMs;
            int value = (int)this.animationTime.getValue();
            if (l < (long)value) {
               int y = renderChunk.getPosition().getY();
               if (selectedIndex == 2) {
                  if ((double)y < mc.theWorld.getHorizon()) {
                     selectedIndex = 0;
                  } else {
                     selectedIndex = 1;
                  }
               }

               if (selectedIndex == 4) {
                  selectedIndex = 3;
               }

               switch (selectedIndex) {
                  case 0:
                     double d = (double)(-y);
                     float f = (float)l;
                     float f2 = (float)y;
                     float f3 = (float)value;
                     GlStateManager.translate(0.0, d + this.evaluateEasingOffset(f, f2, f3), 0.0);
                     break;
                  case 1:
                     double d2 = (double)(256 - y);
                     float f6 = (float)l;
                     float f7 = (float)(256 - y);
                     float f8 = (float)value;
                     GlStateManager.translate(0.0, d2 - this.evaluateEasingOffset(f6, f7, f8), 0.0);
                  case 2:
                  default:
                     break;
                  case 3:
                     if (chunkAnimationEntry.slideDirection != null) {
                        Vec3i directionVec = chunkAnimationEntry.slideDirection.getDirectionVec();
                        float f11 = (float)l;
                        float f12 = (float)value;
                        float f13 = 200.0F;
                        double d3 = -(200.0 - this.evaluateEasingOffset(f11, f13, f12));
                        GlStateManager.translate((double)directionVec.getX() * d3, 0.0, (double)directionVec.getZ() * d3);
                     }
               }
            } else {
               this.chunkAnimations.remove(renderChunk);
            }
         }
      }
   }

   private static EnumFacing getDominantHorizontalFacing(Vec3i vec) {
      int n = Math.abs(vec.getX());
      int n2 = Math.abs(vec.getZ());
      EnumFacing facing;
      if (n > n2) {
         if (vec.getX() > 0) {
            facing = EnumFacing.EAST;
         } else {
            facing = EnumFacing.WEST;
         }
      } else if (vec.getZ() > 0) {
         facing = EnumFacing.SOUTH;
      } else {
         facing = EnumFacing.NORTH;
      }

      return facing;
   }

   private double evaluateEasingOffset(float f, float f2, float f3) {
      return AnimationUtil.applyEasing(AnimationUtil.parseEasingType(this.animation.getSelectedValue()), (double)(f / f3)) * (double)f2;
   }

   public void onChunkPositionSet(RenderChunk renderChunk, BlockPos pos) {
      if (this.isToggled()) {
         if (mc.thePlayer != null) {
            boolean flag = true;
            BlockPos position = mc.thePlayer.getPosition();
            position = position.add(0, -position.getY(), 0);
            BlockPos pos2 = pos.add(8, -pos.getY(), 8);
            if (this.disableAroundPlayer.getValue()) {
               flag = position.distanceSq(pos2) > 4096.0;
            }

            if (flag) {
               EnumFacing facing = null;
               if (this.mode.getSelectedIndex() == 3) {
                  facing = getDominantHorizontalFacing(position.subtract(pos2));
               }

               ChunkAnimationEntry chunkAnimationEntry = new ChunkAnimationEntry(-1L, facing);
               this.chunkAnimations.put(renderChunk, chunkAnimationEntry);
            } else {
               this.chunkAnimations.remove(renderChunk);
            }
         }
      }
   }

   private boolean isAnimationTimeVisible() {
      return !this.animation.getSelectedValue().equals("None");
   }

}
