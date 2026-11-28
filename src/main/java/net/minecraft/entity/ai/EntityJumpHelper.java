package net.minecraft.entity.ai;

import net.minecraft.entity.EntityLiving;

public class EntityJumpHelper {
   private EntityLiving entity;
   public boolean isJumping;

   public void doJump() {
      this.entity.setJumping(this.isJumping);
      this.isJumping = false;
   }

   public EntityJumpHelper(EntityLiving entityLiving) {
      this.entity = entityLiving;
   }

   public void setJumping() {
      this.isJumping = true;
   }
}
