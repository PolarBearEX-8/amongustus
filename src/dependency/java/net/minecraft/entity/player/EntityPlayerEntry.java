package net.minecraft.entity.player;

import client.util.ExtendedClassEntry;
import java.util.Collections;
import java.util.List;
import net.minecraft.entity.EntityEnderman;
import net.minecraft.entity.SharedMonsterAttributes;

public class EntityPlayerEntry extends ExtendedClassEntry {
   public EntityPlayer entityPlayer;
   public int count6;
   public int count7;
   public EntityEnderman entityEnderman;

   public EntityPlayerEntry(EntityEnderman entityEnderman2) {
      super(entityEnderman2, EntityPlayer.class, true);
      this.entityEnderman = entityEnderman2;
   }

   @Override
   public boolean isActive() {
      double value = this.getValue();
      List list = this.auxiliaryUUIDEntry
         .worldObj
         .resolveList(EntityPlayer.class, this.auxiliaryUUIDEntry.getBoundingBox2().offset(value, 4.0, value), this.predicate);
      Collections.sort(list, this.auxiliaryComparatorAdapter);
      if (list.isEmpty()) {
         return false;
      } else {
         this.entityPlayer = (EntityPlayer)list.get(0);
         return true;
      }
   }

   @Override
   public void updateState() {
      this.entityPlayer = null;
      this.entityEnderman.setEnabled6(false);
      this.entityEnderman
         .getAuxiliaryListener(SharedMonsterAttributes.extendedListener3)
         .applyInvalidOperationManagerByInvalidOperation(EntityEnderman.getInvalidOperationManager3());
      super.updateState();
   }

   @Override
   public void updateState2() {
      if (this.entityPlayer != null) {
         if (--this.count6 <= 0) {
            this.entityLivingBase = this.entityPlayer;
            this.entityPlayer = null;
            super.updateState3();
            this.entityEnderman.applyText("mob.endermen.stare", 1.0F, 1.0F);
            this.entityEnderman.setEnabled6(true);
            this.entityEnderman
               .getAuxiliaryListener(SharedMonsterAttributes.extendedListener3)
               .applyInvalidOperationManager(EntityEnderman.getInvalidOperationManager3());
         }
      } else {
         if (this.entityLivingBase != null) {
            if (this.entityLivingBase instanceof EntityPlayer
               && EntityEnderman.matchesEntityEndermanByEntityEnderman(this.entityEnderman, (EntityPlayer)this.entityLivingBase)) {
               if (this.entityLivingBase.getCoordinate(this.entityEnderman) < 16.0) {
                  this.entityEnderman.isActive33();
               }

               this.count7 = 0;
            } else if (this.entityLivingBase.getCoordinate(this.entityEnderman) > 256.0
               && this.count7++ >= 30
               && this.entityEnderman.matchesEntity10(this.entityLivingBase)) {
               this.count7 = 0;
            }
         }

         super.updateState2();
      }
   }

   @Override
   public void updateState3() {
      this.count6 = 5;
      this.count7 = 0;
   }

   @Override
   public boolean isActiveAsFlag() {
      if (this.entityPlayer != null) {
         if (!EntityEnderman.matchesEntityEndermanByEntityEnderman(this.entityEnderman, this.entityPlayer)) {
            return false;
         } else {
            EntityEnderman.matchesEntityEnderman(this.entityEnderman, true);
            this.entityEnderman.applyEntityByEntity(this.entityPlayer, 10.0F, 10.0F);
            return true;
         }
      } else {
         return super.isActiveAsFlag();
      }
   }
}
