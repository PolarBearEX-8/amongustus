package net.minecraft.entity;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityXporb extends Entity {
   public int count12;
   public int count11;
   public int count13;
   public int count16 = 5;
   public int count14;
   public EntityPlayer entityPlayer;
   public int count15;

   public static int computeCount(int count) {
      return count >= 2477
         ? 2477
         : (
            count >= 1237
               ? 1237
               : (
                  count >= 617
                     ? 617
                     : (
                        count >= 307
                           ? 307
                           : (count >= 149 ? 149 : (count >= 73 ? 73 : (count >= 37 ? 37 : (count >= 17 ? 17 : (count >= 7 ? 7 : (count >= 3 ? 3 : 1))))))
                     )
               )
         );
   }

   @Override
   public boolean isActiveAsFlag() {
      return false;
   }

   @Override
   public int computeCount(float factor) {
      float factor2 = MathHelper.clamp_float(0.5F, 0.0F, 1.0F);
      int count = super.computeCount(factor);
      int count2 = count & 0xFF;
      int count3 = count >> 16 & 0xFF;
      int count4 = count2 + (int)(factor2 * 15.0F * 16.0F);
      if (count4 > 240) {
         count4 = 240;
      }

      return count4 | count3 << 16;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      this.count16 = tagCompound.processValue("Health") & 255;
      this.count11 = tagCompound.processValue("Age");
      this.count14 = tagCompound.processValue("Value");
   }

   public int getCount14() {
      return this.count14;
   }

   @Override
   public void applyIndex(int count) {
      this.matchesDamageSource(DamageSource.damageSource8, (float)count);
   }

   @Override
   public void applyEntityPlayer2(EntityPlayer player) {
      if (!this.worldObj.state && this.count13 == 0 && player.count23 == 0) {
         player.count23 = 2;
         this.worldObj.applyEntityByEntity(player, "random.orb", 0.1F, 0.5F * ((this.random.nextFloat() - this.random.nextFloat()) * 0.7F + 1.8F));
         player.applyEntity2(this, 1);
         player.applyIndex12(this.count14);
         this.updateState9();
      }
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      if (this.matchesDamageSource(damageSource)) {
         return false;
      } else {
         this.updateState8();
         this.count16 = (int)((float)this.count16 - factor);
         if (this.count16 <= 0) {
            this.updateState9();
         }

         return false;
      }
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      tagCompound.put("Health", (short)((byte)this.count16));
      tagCompound.put("Age", (short)this.count11);
      tagCompound.put("Value", (short)this.count14);
   }

   public int getCount4() {
      return this.count14 >= 2477
         ? 10
         : (
            this.count14 >= 1237
               ? 9
               : (
                  this.count14 >= 617
                     ? 8
                     : (
                        this.count14 >= 307
                           ? 7
                           : (
                              this.count14 >= 149
                                 ? 6
                                 : (
                                    this.count14 >= 73
                                       ? 5
                                       : (this.count14 >= 37 ? 4 : (this.count14 >= 17 ? 3 : (this.count14 >= 7 ? 2 : (this.count14 >= 3 ? 1 : 0))))
                                 )
                           )
                     )
               )
         );
   }

   @Override
   public boolean isActive3() {
      return false;
   }

   @Override
   public boolean isActive2() {
      return this.worldObj.matchesAxisAlignedBBByAxisAlignedBB(this.getBoundingBox2(), Material.coreItemEntry1, this);
   }

   @Override
   public void updateState() {
      super.updateState();
      if (this.count13 > 0) {
         this.count13--;
      }

      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.motionY -= 0.03F;
      if (this.worldObj.getBlockState(new BlockPos(this)).getBlock().getMaterial() == Material.material29) {
         this.motionY = 0.2F;
         this.motionX = (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
         this.motionZ = (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
         this.applyText("random.fizz", 0.4F, 2.0F + this.random.nextFloat() * 0.4F);
      }

      this.checkDouble(this.posX, (this.getBoundingBox2().minY + this.getBoundingBox2().maxY) / 2.0, this.posZ);
      double offset = 8.0;
      if (this.count15 < this.count12 - 20 + this.getEntityId() % 100) {
         if (this.entityPlayer == null || this.entityPlayer.getCoordinate(this) > offset * offset) {
            this.entityPlayer = this.worldObj.resolveEntityPlayerByEntity(this, offset);
         }

         this.count15 = this.count12;
      }

      if (this.entityPlayer != null && this.entityPlayer.isActive23()) {
         this.entityPlayer = null;
      }

      if (this.entityPlayer != null) {
         double offset2 = (this.entityPlayer.posX - this.posX) / offset;
         double offset3 = (this.entityPlayer.posY + (double)this.entityPlayer.getHeight2() - this.posY) / offset;
         double offset4 = (this.entityPlayer.posZ - this.posZ) / offset;
         double offset5 = Math.sqrt(offset2 * offset2 + offset3 * offset3 + offset4 * offset4);
         double offset6 = 1.0 - offset5;
         if (offset6 > 0.0) {
            double offset7 = offset6 * offset6;
            this.motionX += offset2 / offset5 * offset7 * 0.1;
            this.motionY += offset3 / offset5 * offset7 * 0.1;
            this.motionZ += offset4 / offset5 * offset7 * 0.1;
         }
      }

      this.applyDouble(this.motionX, this.motionY, this.motionZ);
      float factor = 0.98F;
      if (this.onGround) {
         factor = this.worldObj
               .getBlockState(
                  new BlockPos(
                     MathHelper.ceiling_double_int(this.posX),
                     MathHelper.ceiling_double_int(this.getBoundingBox2().minY) - 1,
                     MathHelper.ceiling_double_int(this.posZ)
                  )
               )
               .getBlock()
               .scaleFactor
            * 0.98F;
      }

      this.motionX *= (double)factor;
      this.motionY *= 0.98F;
      this.motionZ *= (double)factor;
      if (this.onGround) {
         this.motionY *= -0.9F;
      }

      this.count12++;
      this.count11++;
      if (this.count11 >= 6000) {
         this.updateState9();
      }
   }

   @Override
   public void updateState7() {
   }

   public EntityXporb(World world, double posX, double posY, double posZ, int count) {
      super(world);
      this.applyFloat(0.5F, 0.5F);
      this.processDouble2(posX, posY, posZ);
      this.rotationYaw = (float)(Math.random() * 360.0);
      this.motionX = (double)((float)(Math.random() * 0.2F - 0.1F) * 2.0F);
      this.motionY = (double)((float)(Math.random() * 0.2) * 2.0F);
      this.motionZ = (double)((float)(Math.random() * 0.2F - 0.1F) * 2.0F);
      this.count14 = count;
   }

   public EntityXporb(World world) {
      super(world);
      this.applyFloat(0.25F, 0.25F);
   }
}
