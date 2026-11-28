package net.augustus.modules.render;

import net.minecraft.util.MovingObjectPosition;
import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.events.EventBlockOverlay;
import net.augustus.settings.ColorSetting;

import net.augustus.events.listeners.BlockOverlayListener;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.ColorUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.optifine.config.Config;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.GL11;

public class BlockOverlay extends Module implements BlockOverlayListener {
   public final ColorSetting color2;
   public final BooleanValue blackLine;
   public final BooleanValue blockFill;
   public final DoubleValue lineWidth;
   public final BooleanValue inAdventureMode;
   public final BooleanValue onlyBlockFace;
   public final DoubleValue blockExpand;
   public final BlurShadowSettings blurShadowSettings;

   public BlockOverlay() {
       super("BlockOverlay", "Blocküberlag", new Color(26, 124, 173), Categorys.RENDER);
      this.color2 = new ColorSetting(this, "Color", new Color(21, 121, 230, 65));
      this.blackLine = new BooleanValue(this, "BlackLine", false);
      this.blockFill = new BooleanValue(this, "BlockFill", true);
      this.lineWidth = new DoubleValue(this, "LineWidth", 5.0, 0.0, 10.0, 2);
      this.inAdventureMode = new BooleanValue(this, "InAdventureMode", true);
      this.onlyBlockFace = new BooleanValue(this, "OnlyBlockFace", false);
      this.blockExpand = new DoubleValue(this, "BlockExpand", 2.0, 0.8, 20.0, 2);
      this.blurShadowSettings = (new BlurShadowSettings(this)).disableBothByDefault();
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(4);
   }

   @Override
   public void onBlockOverlay(EventBlockOverlay event) {
      if (mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.color(0.0F, 0.0F, 0.0F, 0.4F);
         GL11.glLineWidth(2.0F);
         GlStateManager.disableTexture2D();
         if (Config.isShaderPackLoaded()) {
            Shaders.disableTexture2D();
         }

         GlStateManager.depthMask(false);
         float f = 0.002F;
         BlockPos blockPos = mc.objectMouseOver.getBlockPos();
         Block block = mc.theWorld.getBlockState(blockPos).getBlock();
         if (block.getMaterial() != Material.air && mc.theWorld.getWorldBorder().contains(blockPos)) {
            block.setBlockBoundsBasedOnState(mc.theWorld, blockPos.getX(), blockPos.getY(), blockPos.getZ());
            int colorInt = this.color2.getColorInt((byte)0);
            block.setBlockBoundsBasedOnState(mc.theWorld, blockPos.getX(), blockPos.getY(), blockPos.getZ());
            double d = mc.thePlayer.lastTickPosX + (mc.thePlayer.posX - mc.thePlayer.lastTickPosX) * (double)mc.getTimer().renderPartialTicks;
            double d2 = mc.thePlayer.lastTickPosY + (mc.thePlayer.posY - mc.thePlayer.lastTickPosY) * (double)mc.getTimer().renderPartialTicks;
            double d3 = mc.thePlayer.lastTickPosZ + (mc.thePlayer.posZ - mc.thePlayer.lastTickPosZ) * (double)mc.getTimer().renderPartialTicks;
            double d4 = this.blockExpand.getValue() / 1000.0;
            AxisAlignedBB box = block.getSelectedBoundingBox(mc.theWorld, blockPos).expand(d4, d4, d4).offset(-d, -d2, -d3);
            if (this.onlyBlockFace.getValue()) {
               box = getFaceBoundingBox(box, mc.objectMouseOver.sideHit);
            }

            BlurShadowManager.enableOneShotPass(this.blurShadowSettings.isBlurActive(), this.blurShadowSettings.isShadowActive());
            AxisAlignedBB box2 = box;
            BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderOverlayPass(box2, colorInt, shaderRenderPass));
            event.cancel();
         }

         GlStateManager.depthMask(true);
         GlStateManager.enableTexture2D();
         if (Config.isShaderPackLoaded()) {
            Shaders.enableTexture2D();
         }

         GlStateManager.disableBlend();
      }
   }

   public static AxisAlignedBB getFaceBoundingBox(AxisAlignedBB box, EnumFacing facing) {
      switch (facing) {
         case SOUTH:
            return new AxisAlignedBB(box.minX, box.minY, box.maxZ, box.maxX, box.maxY, box.maxZ);
         case NORTH:
            return new AxisAlignedBB(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.minZ);
         case EAST:
            return new AxisAlignedBB(box.maxX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
         case WEST:
            return new AxisAlignedBB(box.minX, box.minY, box.minZ, box.minX, box.maxY, box.maxZ);
         case UP:
            return new AxisAlignedBB(box.minX, box.maxY, box.minZ, box.maxX, box.maxY, box.maxZ);
         case DOWN:
            return new AxisAlignedBB(box.minX, box.minY, box.minZ, box.maxX, box.minY, box.maxZ);
         default:
            return null;
      }
   }

   public void renderBoundingBoxOverlay(AxisAlignedBB box, int n) {
      if (this.blockFill.getValue()) {
         RenderUtil.setGlColor(n);
         RenderUtil.drawFilledBox(box);
      }

      if (this.lineWidth.getValue() > 0.0) {
         if (this.blackLine.getValue()) {
            GlStateManager.color(0.0F, 0.0F, 0.0F, 1.0F);
         } else {
            RenderUtil.setGlColorWithAlpha(n, 1.0F);
         }

         GL11.glLineWidth((float)this.lineWidth.getValue() / 2.0F);
         GL11.glEnable(2848);
         RenderGlobal.drawSelectionBoundingBox(box);
         GL11.glDisable(2848);
         GL11.glLineWidth(1.0F);
      }

      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
   }

   private void renderOverlayPass(AxisAlignedBB box, int n, ShaderRenderPass shaderRenderPass) {
      this.renderBoundingBoxOverlay(box, this.blurShadowSettings.resolvePassColor(shaderRenderPass, ColorUtil.resolvePassColor(shaderRenderPass, n)));
   }

}
