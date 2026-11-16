package net.minecraft.entity;

import client.util.VectorTransformer;
import java.util.Calendar;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBat extends VectorTransformer {
   public BlockPos blockPos2;

   @Override
   public void applyEntity9(Entity entity) {
   }

   @Override
   public float getFactor() {
      return 0.1F;
   }

   @Override
   public boolean isActiveAsFlag() {
      return false;
   }

   @Override
   public String getText9() {
      return "mob.bat.death";
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.dataWatcher.applyTargetByTarget(16, tagCompound.computeByteCode("BatFlags"));
   }

   @Override
   public float getHeight2() {
      return this.height / 2.0F;
   }

   private boolean matchesCalendar(Calendar calendar) {
      return calendar.get(2) + 1 == 10 && calendar.get(5) >= 20 || calendar.get(2) + 1 == 11 && calendar.get(5) <= 3;
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(6.0);
   }

   @Override
   public void processFloat2(float factor, float factor2) {
   }

   @Override
   public boolean isActive9() {
      return false;
   }

   @Override
   public float getFactorAsFloat() {
      return super.getFactorAsFloat() * 0.95F;
   }

   @Override
   public String getText3() {
      return this.isActive29() && this.random.nextInt(4) != 0 ? null : "mob.bat.idle";
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      if (this.matchesDamageSource(damageSource)) {
         return false;
      } else {
         if (!this.worldObj.state && this.isActive29()) {
            this.setEnabled6(false);
         }

         return super.matchesDamageSource(damageSource, factor);
      }
   }

   @Override
   public String getText10() {
      return "mob.bat.hurt";
   }

   @Override
   public void applyBlock(double offset, boolean flag, Block block, BlockPos blockPos) {
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.put("BatFlags", this.dataWatcher.computeByteCode(16));
   }

   public boolean isActive29() {
      return (this.dataWatcher.computeByteCode(16) & 1) != 0;
   }

   @Override
   public void updateState3() {
      super.updateState3();
      BlockPos blockPos = new BlockPos(this);
      BlockPos blockPos3 = blockPos.up();
      if (this.isActive29()) {
         if (!this.worldObj.getBlockState(blockPos3).getBlock().isActive2()) {
            this.setEnabled6(false);
            this.worldObj.applyEntityPlayerByEntityPlayer((EntityPlayer)null, 1015, blockPos, 0);
         } else {
            if (this.random.nextInt(200) == 0) {
               this.scaleFactor12 = (float)this.random.nextInt(360);
            }

            if (this.worldObj.resolveEntityPlayerByEntity(this, 4.0) != null) {
               this.setEnabled6(false);
               this.worldObj.applyEntityPlayerByEntityPlayer((EntityPlayer)null, 1015, blockPos, 0);
            }
         }
      } else {
         if (this.blockPos2 != null && (!this.worldObj.isAirBlock(this.blockPos2) || this.blockPos2.getY() < 1)) {
            this.blockPos2 = null;
         }

         if (this.blockPos2 == null
            || this.random.nextInt(30) == 0
            || this.blockPos2.computeValue((double)((int)this.posX), (double)((int)this.posY), (double)((int)this.posZ)) < 4.0) {
            this.blockPos2 = new BlockPos(
               (int)this.posX + this.random.nextInt(7) - this.random.nextInt(7),
               (int)this.posY + this.random.nextInt(6) - 2,
               (int)this.posZ + this.random.nextInt(7) - this.random.nextInt(7)
            );
         }

         double offset = (double)this.blockPos2.getX() + 0.5 - this.posX;
         double offset2 = (double)this.blockPos2.getY() + 0.1 - this.posY;
         double offset3 = (double)this.blockPos2.getZ() + 0.5 - this.posZ;
         this.motionX = this.motionX + (Math.signum(offset) * 0.5 - this.motionX) * 0.1F;
         this.motionY = this.motionY + (Math.signum(offset2) * 0.7F - this.motionY) * 0.1F;
         this.motionZ = this.motionZ + (Math.signum(offset3) * 0.5 - this.motionZ) * 0.1F;
         float factor = MathHelper.sqrt_float((float)(MathHelper.getDouble6(this.motionZ, this.motionX) * 180.0 / Math.PI) - 90.0F - this.rotationYaw);
         this.scaleFactor3 = 0.5F;
         this.rotationYaw += factor;
         if (this.random.nextInt(100) == 0 && this.worldObj.getBlockState(blockPos3).getBlock().isActive2()) {
            this.setEnabled6(true);
         }
      }
   }

   public void setEnabled6(boolean enabled) {
      byte byteValue = this.dataWatcher.computeByteCode(16);
      if (enabled) {
         this.dataWatcher.applyTargetByTarget(16, (byte)(byteValue | 1));
      } else {
         this.dataWatcher.applyTargetByTarget(16, (byte)(byteValue & -2));
      }
   }

   @Override
   public void updateState() {
      super.updateState();
      if (this.isActive29()) {
         this.motionX = this.motionY = this.motionZ = 0.0;
         this.posY = (double)MathHelper.ceiling_double_int(this.posY) + 1.0 - (double)this.height;
      } else {
         this.motionY *= 0.6F;
      }
   }

   @Override
   public void updateState7() {
      super.updateState7();
      this.dataWatcher.applyTarget(16, (byte)0);
   }

   @Override
   public boolean isActive10() {
      return true;
   }

   @Override
   public boolean nextInt() {
      int count = MathHelper.ceiling_double_int(this.posX);
      int count2 = MathHelper.ceiling_double_int(this.getBoundingBox2().minY);
      int count3 = MathHelper.ceiling_double_int(this.posZ);
      if (count2 >= this.worldObj.getIndexOffset1()) {
         return false;
      } else {
         int count4 = this.worldObj.computeCountByIndex(count, count2, count3);
         byte byteValue = 4;
         if (this.matchesCalendar(this.worldObj.getCalendar())) {
            byteValue = 7;
         } else if (this.random.nextBoolean()) {
            return false;
         }

         return count4 > this.random.nextInt(byteValue) ? false : super.nextInt();
      }
   }

   @Override
   public void updateState35() {
   }

   public EntityBat(World world) {
      super(world);
      this.applyFloat(0.5F, 0.9F);
      this.setEnabled6(true);
   }
}
