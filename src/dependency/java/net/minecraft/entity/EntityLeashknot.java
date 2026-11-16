package net.minecraft.entity;

import net.minecraft.block.BlockAcaciaFence;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class EntityLeashknot extends CustomEntityAdapter {
   @Override
   public boolean checkDouble(double offset) {
      return offset < 1024.0;
   }

   @Override
   public void applyEntity9(Entity entity) {
   }

   @Override
   public boolean matchesEntityPlayerByEntityPlayer(EntityPlayer player) {
      ItemStack stack = player.getItemStack();
      boolean flag = false;
      if (stack != null && stack.getItem() == Items.lead && !this.worldObj.state) {
         double offset = 7.0;

         for (EntityMob entityMob : (Iterable<EntityMob>)this.worldObj
            .resolveList(
               EntityMob.class,
               new AxisAlignedBB(this.posX - offset, this.posY - offset, this.posZ - offset, this.posX + offset, this.posY + offset, this.posZ + offset)
            )) {
            if (entityMob.isEnabled23() && entityMob.getEntity6() == player) {
               entityMob.applyEntity(this, true);
               flag = true;
            }
         }
      }

      if (!this.worldObj.state && !flag) {
         this.updateState9();
         if (player.invulnerableManager.enabled2) {
            double offset2 = 7.0;

            for (EntityMob entityMob2 : (Iterable<EntityMob>)this.worldObj
               .resolveList(
                  EntityMob.class,
                  new AxisAlignedBB(
                     this.posX - offset2, this.posY - offset2, this.posZ - offset2, this.posX + offset2, this.posY + offset2, this.posZ + offset2
                  )
               )) {
               if (entityMob2.isEnabled23() && entityMob2.getEntity6() == this) {
                  entityMob2.applyFlag(true, false);
               }
            }
         }
      }

      return true;
   }

   public static EntityLeashknot resolveEntityLeashknotByWorld(World world, BlockPos blockPos) {
      EntityLeashknot entityLeashknot = new EntityLeashknot(world, blockPos);
      entityLeashknot.enabled10 = true;
      world.matchesEntity(entityLeashknot);
      return entityLeashknot;
   }

   @Override
   public float getHeight2() {
      return -0.0625F;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
   }

   public static EntityLeashknot resolveEntityLeashknot3(World world, BlockPos blockPos) {
      int coordX = blockPos.getX();
      int coordY = blockPos.getY();
      int coordZ = blockPos.getZ();

      for (EntityLeashknot entityLeashknot : (Iterable<EntityLeashknot>)world.resolveList(
         EntityLeashknot.class,
         new AxisAlignedBB((double)coordX - 1.0, (double)coordY - 1.0, (double)coordZ - 1.0, (double)coordX + 1.0, (double)coordY + 1.0, (double)coordZ + 1.0)
      )) {
         if (entityLeashknot.getBlockPos2().equals(blockPos)) {
            return entityLeashknot;
         }
      }

      return null;
   }

   @Override
   public boolean matchesNBTTagCompound(NBTTagCompound tagCompound) {
      return false;
   }

   @Override
   public int getCount5() {
      return 9;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
   }

   @Override
   public void applyEnumFacing(EnumFacing enumFacing) {
   }

   @Override
   public boolean isActive16() {
      return this.worldObj.getBlockState(this.blockPos2).getBlock() instanceof BlockAcaciaFence;
   }

   @Override
   public int getCount4() {
      return 9;
   }

   @Override
   public void updateState7() {
      super.updateState7();
   }

   public EntityLeashknot(World world, BlockPos blockPos) {
      super(world, blockPos);
      this.processDouble2((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5);
      this.setBoundingBox2(
         new AxisAlignedBB(
            this.posX - 0.1875,
            this.posY - 0.25 + 0.125,
            this.posZ - 0.1875,
            this.posX + 0.1875,
            this.posY + 0.25 + 0.125,
            this.posZ + 0.1875
         )
      );
   }

   public EntityLeashknot(World world) {
      super(world);
   }
}
