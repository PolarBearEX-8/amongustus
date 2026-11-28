package net.minecraft.entity.player;

import net.minecraft.nbt.NBTTagCompound;

public class PlayerCapabilities {
   public boolean disableDamage;
   public boolean isFlying;
   public boolean allowFlying;
   public boolean isCreativeMode;
   public boolean allowEdit = true;
   private float flySpeed = 0.05F;
   private float walkSpeed = 0.1F;

   public float getWalkSpeed() {
      return this.walkSpeed;
   }

   public void setPlayerWalkSpeed(float f) {
      this.walkSpeed = f;
   }

   public void writeCapabilitiesToNBT(NBTTagCompound tag) {
      NBTTagCompound tag2 = new NBTTagCompound();
      tag2.setBoolean("invulnerable", this.disableDamage);
      tag2.setBoolean("flying", this.isFlying);
      tag2.setBoolean("mayfly", this.allowFlying);
      tag2.setBoolean("instabuild", this.isCreativeMode);
      tag2.setBoolean("mayBuild", this.allowEdit);
      tag2.setFloat("flySpeed", this.flySpeed);
      tag2.setFloat("walkSpeed", this.walkSpeed);
      tag.setTag("abilities", tag2);
   }

   public void readCapabilitiesFromNBT(NBTTagCompound tag) {
      if (tag.hasKey("abilities", 10)) {
         NBTTagCompound compoundTag = tag.getCompoundTag("abilities");
         this.disableDamage = compoundTag.getBoolean("invulnerable");
         this.isFlying = compoundTag.getBoolean("flying");
         this.allowFlying = compoundTag.getBoolean("mayfly");
         this.isCreativeMode = compoundTag.getBoolean("instabuild");
         if (compoundTag.hasKey("flySpeed", 99)) {
            this.flySpeed = compoundTag.getFloat("flySpeed");
            this.walkSpeed = compoundTag.getFloat("walkSpeed");
         }

         if (compoundTag.hasKey("mayBuild", 1)) {
            this.allowEdit = compoundTag.getBoolean("mayBuild");
         }
      }
   }

   public void setFlySpeed(float f) {
      this.flySpeed = f;
   }

   public float getFlySpeed() {
      return this.flySpeed;
   }
}
