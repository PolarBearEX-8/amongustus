package net.minecraft.item;

import net.minecraft.entity.EntityMob;

public class PrimaryItemEntry {
   public EntityMob entityMob;
   public boolean enabled;

   public void updateState2() {
      this.entityMob.setState31(this.enabled);
      this.enabled = false;
   }

   public void updateState() {
      this.enabled = true;
   }

   public PrimaryItemEntry(EntityMob entityMob2) {
      this.entityMob = entityMob2;
   }
}
