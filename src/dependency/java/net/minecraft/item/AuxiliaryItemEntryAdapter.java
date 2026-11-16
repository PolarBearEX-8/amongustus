package net.minecraft.item;

import net.minecraft.entity.EntityMob;

public class AuxiliaryItemEntryAdapter extends SystemItemEntry {
   public boolean enabled2;
   public int count2;

   @Override
   public void updateState() {
      if (this.enabled2) {
         this.blockDarkOakDoor.applyWorldByWorld(this.entityMob.worldObj, this.blockPos, false);
      }
   }

   @Override
   public void updateState3() {
      this.count2 = 20;
      this.blockDarkOakDoor.applyWorldByWorld(this.entityMob.worldObj, this.blockPos, true);
   }

   @Override
   public void updateState2() {
      this.count2--;
      super.updateState2();
   }

   @Override
   public boolean isActiveAsFlag() {
      return this.enabled2 && this.count2 > 0 && super.isActiveAsFlag();
   }

   public AuxiliaryItemEntryAdapter(EntityMob entityMob, boolean flag) {
      super(entityMob);
      this.entityMob = entityMob;
      this.enabled2 = flag;
   }
}
