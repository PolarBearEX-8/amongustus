package net.minecraft.entity;

import client.Client;
import client.module.Module;
import client.setting.EnumFacingXType;
import client.util.VectorRanker;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import org.apache.commons.lang3.Validate;

public abstract class CustomEntityAdapter extends Entity {
   public int count11;
   public BlockPos blockPos2;
   public EnumFacing enumFacing2;

   @Override
   public void processDouble2(double posX, double posY, double posZ) {
      this.posX = posX;
      this.posY = posY;
      this.posZ = posZ;
      BlockPos blockPos = this.blockPos2;
      this.blockPos2 = new BlockPos(posX, posY, posZ);
      if (!this.blockPos2.equals(blockPos)) {
         this.updateState2();
         this.enabled4 = true;
      }
   }

   public abstract void applyEntity9(Entity entity);

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      this.blockPos2 = new BlockPos(tagCompound.computeCount("TileX"), tagCompound.computeCount("TileY"), tagCompound.computeCount("TileZ"));
      EnumFacing enumFacing;
      if (tagCompound.matchesText("Direction", 99)) {
         enumFacing = EnumFacing.resolveEnumFacing(tagCompound.computeByteCode("Direction"));
         this.blockPos2 = this.blockPos2.offset(enumFacing);
      } else if (tagCompound.matchesText("Facing", 99)) {
         enumFacing = EnumFacing.resolveEnumFacing(tagCompound.computeByteCode("Facing"));
      } else {
         enumFacing = EnumFacing.resolveEnumFacing(tagCompound.computeByteCode("Dir"));
      }

      this.applyEnumFacing(enumFacing);
   }

   public BlockPos getBlockPos2() {
      return this.blockPos2;
   }

   @Override
   public EnumFacing getEnumFacing2() {
      return this.enumFacing2;
   }

   private double computeValue(int count) {
      return count % 32 == 0 ? 0.5 : 0.0;
   }

   @Override
   public void applyDouble(double posX, double posY, double posZ) {
      if (!this.worldObj.state && !this.isDead && posX * posX + posY * posY + posZ * posZ > 0.0) {
         this.updateState9();
         this.applyEntity9((Entity)null);
      }
   }

   @Override
   public boolean matchesEntityByEntity(Entity entity) {
      return entity instanceof EntityPlayer ? this.matchesDamageSource(DamageSource.resolveDamageSource((EntityPlayer)entity), 0.0F) : false;
   }

   @Override
   public void applyDouble3(double posX, double posY, double posZ) {
      if (!this.worldObj.state && !this.isDead && posX * posX + posY * posY + posZ * posZ > 0.0) {
         this.updateState9();
         this.applyEntity9((Entity)null);
      }
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      tagCompound.put("Facing", (byte)this.enumFacing2.getIndexOffset());
      tagCompound.put("TileX", this.getBlockPos2().getX());
      tagCompound.put("TileY", this.getBlockPos2().getY());
      tagCompound.put("TileZ", this.getBlockPos2().getZ());
   }

   public abstract int getCount5();

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      if (this.matchesDamageSource(damageSource)) {
         return false;
      } else {
         if (!this.isDead && !this.worldObj.state) {
            this.updateState9();
            this.updateState8();
            this.applyEntity9(damageSource.getEntity());
         }

         return true;
      }
   }

   public void applyEnumFacing(EnumFacing enumFacing) {
      Validate.notNull(enumFacing);
      Validate.isTrue(enumFacing.getEnumFacingXType().isActiveAsFlag());
      this.enumFacing2 = enumFacing;
      this.prevRotationYaw = this.rotationYaw = (float)(this.enumFacing2.getIndexOffset() * 90);
      this.updateState2();
   }

   public boolean isActive16() {
      if (!this.worldObj.getList2(this, this.getBoundingBox2()).isEmpty()) {
         return false;
      } else {
         int count = Math.max(1, this.getCount5() / 16);
         int count2 = Math.max(1, this.getCount4() / 16);
         BlockPos blockPos = this.blockPos2.offset(this.enumFacing2.getEnumFacing5());
         EnumFacing enumFacing = this.enumFacing2.getEnumFacing2();

         for (int index = 0; index < count; index++) {
            for (int index2 = 0; index2 < count2; index2++) {
               BlockPos blockPos3 = blockPos.getBlockPos(enumFacing, index).resolveBlockPos2(index2);
               Block block = this.worldObj.getBlockState(blockPos3).getBlock();
               if (!block.getMaterial().canExecute2() && !VectorRanker.matchesBlock3(block)) {
                  return false;
               }
            }
         }

         Iterator iterator = this.worldObj.resolveListByEntity(this, this.getBoundingBox2()).iterator();

         while (iterator.hasNext()) {
            if ((Entity)iterator.next() instanceof CustomEntityAdapter) {
               return false;
            }
         }

         return true;
      }
   }

   private void updateState2() {
      if (this.enumFacing2 != null) {
         double offset = (double)this.blockPos2.getX() + 0.5;
         double offset2 = (double)this.blockPos2.getY() + 0.5;
         double offset3 = (double)this.blockPos2.getZ() + 0.5;
         double offset4 = this.computeValue(this.getCount5());
         double offset5 = this.computeValue(this.getCount4());
         double offset6 = offset - (double)this.enumFacing2.getInt2() * 0.46875;
         double offset7 = offset3 - (double)this.enumFacing2.getInt() * 0.46875;
         double offset8 = offset2 + offset5;
         EnumFacing enumFacing = this.enumFacing2.getEnumFacing2();
         double offset9 = offset6 + offset4 * (double)enumFacing.getInt2();
         double offset10 = offset7 + offset4 * (double)enumFacing.getInt();
         this.posX = offset9;
         this.posY = offset8;
         this.posZ = offset10;
         double count = (double)this.getCount5();
         double count2 = (double)this.getCount4();
         double count3 = (double)this.getCount5();
         if (this.enumFacing2.getEnumFacingXType() == EnumFacingXType.Z) {
            count3 = 1.0;
         } else {
            count = 1.0;
         }

         double offset11 = count / 32.0;
         double offset12 = count2 / 32.0;
         double offset13 = count3 / 32.0;
         this.setBoundingBox2(new AxisAlignedBB(offset9 - offset11, offset8 - offset12, offset10 - offset13, offset9 + offset11, offset8 + offset12, offset10 + offset13));
      }
   }

   public abstract int getCount4();

   @Override
   public boolean isActive5() {
      return true;
   }

   @Override
   public boolean isActive14() {
      return false;
   }

   @Override
   public void updateState() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      if (this.count11++ == 100 && !this.worldObj.state) {
         this.count11 = 0;
         if (!this.isDead && !this.isActive16()) {
            this.updateState9();
            this.applyEntity9((Entity)null);
         }
      }
   }

   @Override
   public void updateState7() {
   }

   public CustomEntityAdapter(World world) {
      super(world);
      this.applyFloat(0.5F, 0.5F);
      if (((Module)Client.getClient().getModuleManager().exploitFixer).isToggled()) {
         this.enumFacing2 = EnumFacing.NORTH;
      }
   }

   public CustomEntityAdapter(World world, BlockPos blockPos) {
      this(world);
      this.blockPos2 = blockPos;
   }
}
