package net.minecraft.entity.passive;

import java.util.Calendar;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBat extends EntityAmbientCreature {
   private BlockPos spawnPosition;

   @Override
   public void collideWithNearbyEntities() {
   }

   @Override
   public boolean canTriggerWalking() {
      return false;
   }

   @Override
   public String getDeathSound() {
      return "mob.bat.death";
   }

   public EntityBat(World world) {
      super(world);
      this.setSize(0.5F, 0.9F);
      this.setIsBatHanging(true);
   }

   @Override
   public float getSoundVolume() {
      return 0.1F;
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (this.getIsBatHanging()) {
         this.motionX = this.motionY = this.motionZ = 0.0;
         this.posY = (double)MathHelper.floor_double(this.posY) + 1.0 - (double)this.height;
      } else {
         this.motionY *= 0.6F;
      }
   }

   @Override
   public boolean doesEntityNotTriggerPressurePlate() {
      return true;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      tag.setByte("BatFlags", this.dataWatcher.getWatchableObjectByte(16));
   }

   @Override
   public String getHurtSound() {
      return "mob.bat.hurt";
   }

   @Override
   public void fall(float f, float f2) {
   }

   @Override
   public boolean getCanSpawnHere() {
      int n = MathHelper.floor_double(this.posX);
      int n2 = MathHelper.floor_double(this.getEntityBoundingBox().minY);
      int n3 = MathHelper.floor_double(this.posZ);
      if (n2 >= this.entityWorld.getSeaLevel()) {
         return false;
      } else {
         int light = this.entityWorld.getLight(n, n2, n3);
         byte b = 4;
         if (this.isDateAroundHalloween(this.entityWorld.getCurrentDate())) {
            b = 7;
         } else if (this.rand.nextBoolean()) {
            return false;
         }

         return light > this.rand.nextInt(b) ? false : super.getCanSpawnHere();
      }
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      if (this.isEntityInvulnerable(damageSource)) {
         return false;
      } else {
         if (!this.entityWorld.isRemote && this.getIsBatHanging()) {
            this.setIsBatHanging(false);
         }

         return super.attackEntityFrom(damageSource, f);
      }
   }

   public void setIsBatHanging(boolean flag) {
      byte watchableObjectByte = this.dataWatcher.getWatchableObjectByte(16);
      if (flag) {
         this.dataWatcher.updateObject(16, (byte)(watchableObjectByte | 1));
      } else {
         this.dataWatcher.updateObject(16, (byte)(watchableObjectByte & -2));
      }
   }

   public boolean getIsBatHanging() {
      return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
   }

   @Override
   public float getSoundPitch() {
      return super.getSoundPitch() * 0.95F;
   }

   @Override
   public String getLivingSound() {
      return this.getIsBatHanging() && this.rand.nextInt(4) != 0 ? null : "mob.bat.idle";
   }

   @Override
   public void updateFallState(double d, boolean flag, Block block, BlockPos pos) {
   }

   @Override
   public float getEyeHeight() {
      return this.height / 2.0F;
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(6.0);
   }

   private boolean isDateAroundHalloween(Calendar calendar) {
      return calendar.get(2) + 1 == 10 && calendar.get(5) >= 20 || calendar.get(2) + 1 == 11 && calendar.get(5) <= 3;
   }

   @Override
   public void entityInit() {
      super.entityInit();
      this.dataWatcher.addObject(16, (byte)0);
   }

   @Override
   public void collideWithEntity(Entity entity) {
   }

   @Override
   public boolean canBePushed() {
      return false;
   }

   @Override
   public void updateAITasks() {
      super.updateAITasks();
      BlockPos pos = new BlockPos(this);
      BlockPos pos2 = pos.up();
      if (this.getIsBatHanging()) {
         if (!this.entityWorld.getBlockState(pos2).getBlock().isNormalCube()) {
            this.setIsBatHanging(false);
            this.entityWorld.playAuxSFXAtEntity((EntityPlayer)null, 1015, pos, 0);
         } else {
            if (this.rand.nextInt(200) == 0) {
               this.rotationYawHead = (float)this.rand.nextInt(360);
            }

            if (this.entityWorld.getClosestPlayerToEntity(this, 4.0) != null) {
               this.setIsBatHanging(false);
               this.entityWorld.playAuxSFXAtEntity((EntityPlayer)null, 1015, pos, 0);
            }
         }
      } else {
         if (this.spawnPosition != null && (!this.entityWorld.isAirBlock(this.spawnPosition) || this.spawnPosition.getY() < 1)) {
            this.spawnPosition = null;
         }

         if (this.spawnPosition == null
            || this.rand.nextInt(30) == 0
            || this.spawnPosition.distanceSq((double)((int)this.posX), (double)((int)this.posY), (double)((int)this.posZ)) < 4.0) {
            this.spawnPosition = new BlockPos(
               (int)this.posX + this.rand.nextInt(7) - this.rand.nextInt(7),
               (int)this.posY + this.rand.nextInt(6) - 2,
               (int)this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7)
            );
         }

         double d = (double)this.spawnPosition.getX() + 0.5 - this.posX;
         double d2 = (double)this.spawnPosition.getY() + 0.1 - this.posY;
         double d3 = (double)this.spawnPosition.getZ() + 0.5 - this.posZ;
         this.motionX = this.motionX + (Math.signum(d) * 0.5 - this.motionX) * 0.1F;
         this.motionY = this.motionY + (Math.signum(d2) * 0.7F - this.motionY) * 0.1F;
         this.motionZ = this.motionZ + (Math.signum(d3) * 0.5 - this.motionZ) * 0.1F;
         float f2 = MathHelper.wrapAngleTo180_float(((float)(MathHelper.atan2(this.motionZ, this.motionX) * 180.0 / Math.PI) - 90.0F) - this.rotationYaw);
         this.moveForward = 0.5F;
         this.rotationYaw += f2;
         if (this.rand.nextInt(100) == 0 && this.entityWorld.getBlockState(pos2).getBlock().isNormalCube()) {
            this.setIsBatHanging(true);
         }
      }
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      this.dataWatcher.updateObject(16, tag.getByte("BatFlags"));
   }
}
