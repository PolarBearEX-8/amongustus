package net.minecraft.item;

import client.setting.GameSettingsPeacefulType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDarkOakDoor;
import net.minecraft.entity.EntityMob;

public class ItemEntryBridge extends SystemItemEntry {
   public int count2;
   public int count3 = -1;

   @Override
   public boolean isActiveAsFlag() {
      double offset = this.entityMob.computeValue(this.blockPos);
      return this.count2 <= 240 && !BlockDarkOakDoor.matchesIBlockAccessByIBlockAccess(this.entityMob.worldObj, this.blockPos) && offset < 4.0;
   }

   @Override
   public boolean isActive() {
      if (!super.isActive()) {
         return false;
      } else {
         return !this.entityMob.worldObj.getDoFireTickManager().matchesText("mobGriefing")
            ? false
            : !BlockDarkOakDoor.matchesIBlockAccessByIBlockAccess(this.entityMob.worldObj, this.blockPos);
      }
   }

   public ItemEntryBridge(EntityMob entityMob) {
      super(entityMob);
   }

   @Override
   public void updateState2() {
      super.updateState2();
      if (this.entityMob.getRandom().nextInt(20) == 0) {
         this.entityMob.worldObj.applyBlockPosByBlockPos(1010, this.blockPos, 0);
      }

      this.count2++;
      int count = (int)((float)this.count2 / 240.0F * 10.0F);
      if (count != this.count3) {
         this.entityMob.worldObj.applyBlockPos(this.entityMob.getEntityId(), this.blockPos, count);
         this.count3 = count;
      }

      if (this.count2 == 240 && this.entityMob.worldObj.getGameSettingsPeacefulType() == GameSettingsPeacefulType.HARD) {
         this.entityMob.worldObj.matchesBlockPos8(this.blockPos);
         this.entityMob.worldObj.applyBlockPosByBlockPos(1012, this.blockPos, 0);
         this.entityMob.worldObj.applyBlockPosByBlockPos(2001, this.blockPos, Block.getIdFromBlock(this.blockDarkOakDoor));
      }
   }

   @Override
   public void updateState3() {
      super.updateState3();
      this.count2 = 0;
   }

   @Override
   public void updateState() {
      super.updateState();
      this.entityMob.worldObj.applyBlockPos(this.entityMob.getEntityId(), this.blockPos, -1);
   }
}
