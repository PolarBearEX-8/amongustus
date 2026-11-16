package client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class InternalMinecraftEntry {
   public Minecraft mc;
   public boolean enabled3 = false;
   public boolean enabled = false;
   public int count;
   public Vec3 vec2;
   public float factor;
   public boolean enabled2 = false;
   public int count3 = 0;
   public Vec3 vec3 = new Vec3(-1.0, -1.0, -1.0);
   public double value3 = 0.0;
   public double value2 = 0.0;
   public double value = 0.0;
   public int count2 = -1;

   public void applyVec3(boolean flag, int count4, float factor2, Vec3 vec) {
      this.enabled = flag;
      this.count = count4;
      this.factor = factor2;
      this.vec2 = vec;
   }

   public void updateState() {
      Entity renderViewEntity = this.mc.getRenderViewEntity();
      double offset = renderViewEntity.prevPosX + (renderViewEntity.posX - renderViewEntity.prevPosX) * (double)this.factor;
      double offset2 = renderViewEntity.prevPosY + (renderViewEntity.posY - renderViewEntity.prevPosY) * (double)this.factor;
      double offset3 = renderViewEntity.prevPosZ + (renderViewEntity.posZ - renderViewEntity.prevPosZ) * (double)this.factor;
      double offset4 = (double)((float)(this.count - this.count3) + this.factor);
      float factor2 = (float)(offset - this.value3 + offset4 * 0.03);
      float factor3 = (float)(offset2 - this.value2);
      float factor4 = (float)(offset3 - this.value);
      GlStateManager.pushMatrix();
      if (this.enabled) {
         GlStateManager.translate(-factor2 / 12.0F, -factor3, -factor4 / 12.0F);
      } else {
         GlStateManager.translate(-factor2, -factor3, -factor4);
      }

      GlStateManager.applyIndexByIndex(this.count2);
      GlStateManager.popMatrix();
      GlStateManager.applyState();
   }

   public void glNewList() {
      GL11.glNewList(this.count2, 4864);
   }

   public void updateState3() {
      GL11.glEndList();
      this.enabled2 = this.enabled;
      this.count3 = this.count;
      this.vec3 = this.vec2;
      this.value3 = this.mc.getRenderViewEntity().prevPosX;
      this.value2 = this.mc.getRenderViewEntity().prevPosY;
      this.value = this.mc.getRenderViewEntity().prevPosZ;
      this.enabled3 = true;
      GlStateManager.applyState();
   }

   public boolean isActive() {
      if (!this.enabled3) {
         return true;
      } else if (this.enabled != this.enabled2) {
         return true;
      } else if (this.count >= this.count3 + 20) {
         return true;
      } else if (Math.abs(this.vec2.xCoord - this.vec3.xCoord) > 0.003) {
         return true;
      } else if (Math.abs(this.vec2.yCoord - this.vec3.yCoord) > 0.003) {
         return true;
      } else if (Math.abs(this.vec2.zCoord - this.vec3.zCoord) > 0.003) {
         return true;
      } else {
         Entity renderViewEntity = this.mc.getRenderViewEntity();
         boolean flag = this.value2 + (double)renderViewEntity.getHeight2() < 128.0 + (double)(this.mc.gameSettings.scaleFactor60 * 128.0F);
         return renderViewEntity.prevPosY + (double)renderViewEntity.getHeight2() < 128.0 + (double)(this.mc.gameSettings.scaleFactor60 * 128.0F) != flag;
      }
   }

   public void updateState2() {
      this.enabled3 = false;
   }

   public InternalMinecraftEntry(Minecraft mc2) {
      this.mc = mc2;
      this.count2 = VectorMatcher.computeCount(1);
   }
}
