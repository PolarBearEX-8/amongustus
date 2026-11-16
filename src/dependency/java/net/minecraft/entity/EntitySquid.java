package net.minecraft.entity;

import client.setting.LocalOptionType;
import client.util.AimPipeline;
import client.util.ExtendedClientManagerBridge;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntitySquid extends AimPipeline {
   public float factor52;
   public float factor53;
   public float factor41;
   public float factor38;
   public float factor45;
   public float factor40;
   public float factor49;
   public float factor48;
   public float factor42;
   public float factor46;
   public float factor43;
   public float factor54;
   public float factor51;
   public float factor47;

   @Override
   public float getFactor() {
      return 0.4F;
   }

   public static boolean matchesEntitySquid(EntitySquid entitySquid) {
      return entitySquid.inWater;
   }

   @Override
   public String getText9() {
      return null;
   }

   @Override
   public boolean isActiveAsFlag() {
      return false;
   }

   @Override
   public float getHeight2() {
      return this.height * 0.5F;
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(10.0);
   }

   @Override
   public void applyFlag(boolean flag, int count) {
      int count2 = this.random.nextInt(3 + count) + 1;

      for (int index = 0; index < count2; index++) {
         this.resolveEntityItem(new ItemStack(Items.dye, 1, LocalOptionType.BLACK.getCount2()), 0.0F);
      }
   }

   public void applyFloatByFloat(float posX, float posY, float posZ) {
      this.factor54 = posX;
      this.factor51 = posY;
      this.factor47 = posZ;
   }

   @Override
   public void processByte(byte byteValue) {
      if (byteValue == 19) {
         this.factor45 = 0.0F;
      } else {
         super.processByte(byteValue);
      }
   }

   @Override
   public Item getGunpowder() {
      return null;
   }

   @Override
   public String getText3() {
      return null;
   }

   @Override
   public void applyFloat2(float factor, float factor2) {
      this.applyDouble(this.motionX, this.motionY, this.motionZ);
   }

   @Override
   public String getText10() {
      return null;
   }

   public boolean isActive29() {
      return this.factor54 != 0.0F || this.factor51 != 0.0F || this.factor47 != 0.0F;
   }

   @Override
   public boolean isInWater() {
      return this.worldObj.matchesAxisAlignedBBByAxisAlignedBB(this.getBoundingBox2().offset(0.0, -0.6F, 0.0), Material.coreItemEntry1, this);
   }

   @Override
   public void updateState33() {
      super.updateState33();
      this.factor53 = this.factor52;
      this.factor38 = this.factor41;
      this.factor40 = this.factor45;
      this.factor48 = this.factor49;
      this.factor45 = this.factor45 + this.factor46;
      if ((double)this.factor45 > Math.PI * 2) {
         if (this.worldObj.state) {
            this.factor45 = (float) (Math.PI * 2);
         } else {
            this.factor45 = (float)((double)this.factor45 - (Math.PI * 2));
            if (this.random.nextInt(10) == 0) {
               this.factor46 = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
            }

            this.worldObj.applyEntity(this, (byte)19);
         }
      }

      if (this.inWater) {
         if (this.factor45 < (float) Math.PI) {
            float factor = this.factor45 / (float) Math.PI;
            this.factor49 = MathHelper.abs(factor * factor * (float) Math.PI) * (float) Math.PI * 0.25F;
            if ((double)factor > 0.75) {
               this.factor42 = 1.0F;
               this.factor43 = 1.0F;
            } else {
               this.factor43 *= 0.8F;
            }
         } else {
            this.factor49 = 0.0F;
            this.factor42 *= 0.9F;
            this.factor43 *= 0.99F;
         }

         if (!this.worldObj.state) {
            this.motionX = (double)(this.factor54 * this.factor42);
            this.motionY = (double)(this.factor51 * this.factor42);
            this.motionZ = (double)(this.factor47 * this.factor42);
         }

         float factor2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
         this.scaleFactor8 = this.scaleFactor8 + (-((float)MathHelper.getDouble6(this.motionX, this.motionZ)) * 180.0F / (float) Math.PI - this.scaleFactor8) * 0.1F;
         this.rotationYaw = this.scaleFactor8;
         this.factor41 = (float)((double)this.factor41 + Math.PI * (double)this.factor43 * 1.5);
         this.factor52 = this.factor52 + (-((float)MathHelper.getDouble6((double)factor2, this.motionY)) * 180.0F / (float) Math.PI - this.factor52) * 0.1F;
      } else {
         this.factor49 = MathHelper.computeFactor(MathHelper.abs(this.factor45)) * (float) Math.PI * 0.25F;
         if (!this.worldObj.state) {
            this.motionX = 0.0;
            this.motionY -= 0.08;
            this.motionY *= 0.98F;
            this.motionZ = 0.0;
         }

         this.factor52 = (float)((double)this.factor52 + (double)(-90.0F - this.factor52) * 0.02);
      }
   }

   @Override
   public boolean nextInt() {
      return this.posY > 45.0 && this.posY < (double)this.worldObj.getIndexOffset1() && super.nextInt();
   }

   public EntitySquid(World world) {
      super(world);
      this.applyFloat(0.95F, 0.95F);
      this.random.setSeed((long)(1 + this.getEntityId()));
      this.factor46 = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
      this.goalSetupManager.add(0, new ExtendedClientManagerBridge(this));
   }
}
