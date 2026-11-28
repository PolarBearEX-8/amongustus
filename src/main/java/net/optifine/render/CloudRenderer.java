package net.optifine.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class CloudRenderer {
   private Minecraft mc;
   private boolean updated = false;
   private boolean renderFancy = false;
   public int cloudTickCounter;
   private Vec3 cloudColor;
   public float partialTicks;
   private boolean updateRenderFancy = false;
   private int updateCloudTickCounter = 0;
   private Vec3 updateCloudColor = new Vec3(-1.0, -1.0, -1.0);
   private double updatePlayerX = 0.0;
   private double updatePlayerY = 0.0;
   private double updatePlayerZ = 0.0;
   private int glListClouds = -1;

   public CloudRenderer(Minecraft mc2) {
      this.mc = mc2;
      this.glListClouds = GLAllocation.generateDisplayLists(1);
   }

   public boolean shouldUpdateGlList() {
      if (!this.updated) {
         return true;
      } else if (this.renderFancy != this.updateRenderFancy) {
         return true;
      } else if (this.cloudTickCounter >= this.updateCloudTickCounter + 20) {
         return true;
      } else if (Math.abs(this.cloudColor.xCoord - this.updateCloudColor.xCoord) > 0.003) {
         return true;
      } else if (Math.abs(this.cloudColor.yCoord - this.updateCloudColor.yCoord) > 0.003) {
         return true;
      } else if (Math.abs(this.cloudColor.zCoord - this.updateCloudColor.zCoord) > 0.003) {
         return true;
      } else {
         Entity renderViewEntity = this.mc.getRenderViewEntity();
         boolean flag = this.updatePlayerY + (double)renderViewEntity.getEyeHeight() < 128.0 + (double)(this.mc.gameSettings.ofCloudsHeight * 128.0F);
         return (renderViewEntity.prevPosY + (double)renderViewEntity.getEyeHeight() < 128.0 + (double)(this.mc.gameSettings.ofCloudsHeight * 128.0F)) != flag;
      }
   }

   public void startUpdateGlList() {
      GL11.glNewList(this.glListClouds, 4864);
   }

   public void endUpdateGlList() {
      GL11.glEndList();
      this.updateRenderFancy = this.renderFancy;
      this.updateCloudTickCounter = this.cloudTickCounter;
      this.updateCloudColor = this.cloudColor;
      this.updatePlayerX = this.mc.getRenderViewEntity().prevPosX;
      this.updatePlayerY = this.mc.getRenderViewEntity().prevPosY;
      this.updatePlayerZ = this.mc.getRenderViewEntity().prevPosZ;
      this.updated = true;
      GlStateManager.resetColor();
   }

   public void reset() {
      this.updated = false;
   }

   public void renderGlList() {
      Entity renderViewEntity = this.mc.getRenderViewEntity();
      double d = renderViewEntity.prevPosX + (renderViewEntity.posX - renderViewEntity.prevPosX) * (double)this.partialTicks;
      double d2 = renderViewEntity.prevPosY + (renderViewEntity.posY - renderViewEntity.prevPosY) * (double)this.partialTicks;
      double d3 = renderViewEntity.prevPosZ + (renderViewEntity.posZ - renderViewEntity.prevPosZ) * (double)this.partialTicks;
      double d4 = (double)((float)(this.cloudTickCounter - this.updateCloudTickCounter) + this.partialTicks);
      float f = (float)(d - this.updatePlayerX + d4 * 0.03);
      float f2 = (float)(d2 - this.updatePlayerY);
      float f3 = (float)(d3 - this.updatePlayerZ);
      GlStateManager.pushMatrix();
      if (this.renderFancy) {
         GlStateManager.translate(-f / 12.0F, -f2, -f3 / 12.0F);
      } else {
         GlStateManager.translate(-f, -f2, -f3);
      }

      GlStateManager.callList(this.glListClouds);
      GlStateManager.popMatrix();
      GlStateManager.resetColor();
   }

   public void prepareToRender(boolean flag, int n, float f, Vec3 vec) {
      this.renderFancy = flag;
      this.cloudTickCounter = n;
      this.partialTicks = f;
      this.cloudColor = vec;
   }
}
