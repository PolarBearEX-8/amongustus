package net.minecraft.entity.boss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;

public class EntityDragonPart extends Entity {
   public final IEntityMultiPart entityDragonObj;
   public final String partName;

   @Override
   public boolean isEntityEqual(Entity entity) {
      return this == entity || this.entityDragonObj == entity;
   }

   @Override
   public boolean canBeCollidedWith() {
      return true;
   }

   public EntityDragonPart(IEntityMultiPart entityMultiPart, String str, float f, float f2) {
      super(entityMultiPart.getWorld());
      this.setSize(f, f2);
      this.entityDragonObj = entityMultiPart;
      this.partName = str;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
   }

   @Override
   public void entityInit() {
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      return this.isEntityInvulnerable(damageSource) ? false : this.entityDragonObj.attackEntityFromPart(this, damageSource, f);
   }
}
