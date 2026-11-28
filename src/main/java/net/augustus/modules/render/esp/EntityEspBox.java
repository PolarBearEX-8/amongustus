package net.augustus.modules.render.esp;

import net.augustus.ClientManagers;
import net.augustus.render.esp.AbstractEspBox;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class EntityEspBox extends AbstractEspBox {
   private final Entity entity;

   public float getYawHead() {
      return this.entity.getRotationYawHead();
   }

   public void updateBounds(float f) {
      RenderManager renderManager = mc.getRenderManager();
      this.x = (float)(this.entity.lastTickPosX + (this.entity.posX - this.entity.lastTickPosX) * (double)f - renderManager.getRenderPosX());
      this.y = (float)(this.entity.lastTickPosY + (this.entity.posY - this.entity.lastTickPosY) * (double)f - renderManager.getRenderPosY());
      this.z = (float)(this.entity.lastTickPosZ + (this.entity.posZ - this.entity.lastTickPosZ) * (double)f - renderManager.getRenderPosZ());
      this.halfWidth = this.entity.width / 2.0F;
      this.height = ClientManagers.moduleManager.child.isToggled() && this.entity instanceof EntityPlayer ? this.entity.height / 1.7F : this.entity.height;
      this.widthExpand = 0.0F;
      this.topExpand = 0.0F;
   }

   private EntityEspBox(Entity entity2) {
      this.entity = entity2;
   }

   public static EntityEspBox fromEntity(Entity entity2) {
      return new EntityEspBox(entity2);
   }



}
