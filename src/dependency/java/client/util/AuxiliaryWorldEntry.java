package client.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class AuxiliaryWorldEntry extends RotationPool {
   public World world;
   public AuxiliaryUUIDEntry auxiliaryUUIDEntry;
   public int count2;
   public double value;
   public boolean enabled;
   public InterpolationConverter interpolationConverter;
   public Class memberRef;
   public int count3;
   public double value2;
   public double value3;
   public double value4;

   public double computeValue(EntityLivingBase livingEntity) {
      return (double)(this.auxiliaryUUIDEntry.width * 2.0F * this.auxiliaryUUIDEntry.width * 2.0F + livingEntity.width);
   }

   public AuxiliaryWorldEntry(AuxiliaryUUIDEntry auxiliaryUUIDEntry2, double offset, boolean flag) {
      this.auxiliaryUUIDEntry = auxiliaryUUIDEntry2;
      this.world = auxiliaryUUIDEntry2.worldObj;
      this.value = offset;
      this.enabled = flag;
      this.setCount(3);
   }

   @Override
   public void updateState() {
      this.auxiliaryUUIDEntry.getPathfindManager().updateState4();
   }

   @Override
   public void updateState2() {
      EntityLivingBase entityLivingBase = this.auxiliaryUUIDEntry.getEntityLivingBase4();
      this.auxiliaryUUIDEntry.getEntityRavagerAdapter().applyEntityByEntity(entityLivingBase, 30.0F, 30.0F);
      double doubleItem = this.auxiliaryUUIDEntry.getDouble3(entityLivingBase.posX, entityLivingBase.getBoundingBox2().minY, entityLivingBase.posZ);
      double offset = this.computeValue(entityLivingBase);
      this.count3--;
      if ((this.enabled || this.auxiliaryUUIDEntry.getCanSeeManager().matchesEntityByEntity(entityLivingBase))
         && this.count3 <= 0
         && (
            this.value2 == 0.0 && this.value3 == 0.0 && this.value4 == 0.0
               || entityLivingBase.getDouble3(this.value2, this.value3, this.value4) >= 1.0
               || this.auxiliaryUUIDEntry.getRandom().nextFloat() < 0.05F
         )) {
         this.value2 = entityLivingBase.posX;
         this.value3 = entityLivingBase.getBoundingBox2().minY;
         this.value4 = entityLivingBase.posZ;
         this.count3 = 4 + this.auxiliaryUUIDEntry.getRandom().nextInt(7);
         if (doubleItem > 1024.0) {
            this.count3 += 10;
         } else if (doubleItem > 256.0) {
            this.count3 += 5;
         }

         if (!this.auxiliaryUUIDEntry.getPathfindManager().matchesEntityByEntity(entityLivingBase, this.value)) {
            this.count3 += 15;
         }
      }

      this.count2 = Math.max(this.count2 - 1, 0);
      if (doubleItem <= offset && this.count2 <= 0) {
         this.count2 = 20;
         if (this.auxiliaryUUIDEntry.getItemStack() != null) {
            this.auxiliaryUUIDEntry.respawnPlayer();
         }

         this.auxiliaryUUIDEntry.matchesEntity4(entityLivingBase);
      }
   }

   @Override
   public boolean isActive() {
      EntityLivingBase entityLivingBase = this.auxiliaryUUIDEntry.getEntityLivingBase4();
      if (entityLivingBase == null) {
         return false;
      } else if (!entityLivingBase.canExecute()) {
         return false;
      } else if (this.memberRef != null && !this.memberRef.isAssignableFrom(entityLivingBase.getClass())) {
         return false;
      } else {
         this.interpolationConverter = this.auxiliaryUUIDEntry.getPathfindManager().resolveInterpolationConverter(entityLivingBase);
         return this.interpolationConverter != null;
      }
   }

   @Override
   public boolean isActiveAsFlag() {
      EntityLivingBase entityLivingBase = this.auxiliaryUUIDEntry.getEntityLivingBase4();
      return entityLivingBase == null
         ? false
         : (
            !entityLivingBase.canExecute()
               ? false
               : (!this.enabled ? !this.auxiliaryUUIDEntry.getPathfindManager().isActive2() : this.auxiliaryUUIDEntry.matchesBlockPos(new BlockPos(entityLivingBase)))
         );
   }

   @Override
   public void updateState3() {
      this.auxiliaryUUIDEntry.getPathfindManager().matchesInterpolationConverter(this.interpolationConverter, this.value);
      this.count3 = 0;
   }

   public AuxiliaryWorldEntry(AuxiliaryUUIDEntry auxiliaryUUIDEntry2, Class clazz, double offset, boolean flag) {
      this(auxiliaryUUIDEntry2, offset, flag);
      this.memberRef = clazz;
   }
}
