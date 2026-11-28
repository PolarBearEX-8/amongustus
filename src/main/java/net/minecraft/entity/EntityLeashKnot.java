package net.minecraft.entity;

import net.minecraft.block.BlockFence;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class EntityLeashKnot extends EntityHanging {
   public static EntityLeashKnot createKnot(World world, BlockPos pos) {
      EntityLeashKnot entityLeashKnot = new EntityLeashKnot(world, pos);
      entityLeashKnot.forceSpawn = true;
      world.spawnEntityInWorld(entityLeashKnot);
      return entityLeashKnot;
   }

   public static EntityLeashKnot getKnotForPosition(World world, BlockPos pos) {
      int x = pos.getX();
      int y = pos.getY();
      int z = pos.getZ();

      for (EntityLeashKnot entityLeashKnot : (Iterable<EntityLeashKnot>)(world.getEntitiesWithinAABB(
         EntityLeashKnot.class,
         new AxisAlignedBB((double)x - 1.0, (double)y - 1.0, (double)z - 1.0, (double)x + 1.0, (double)y + 1.0, (double)z + 1.0)
      ))) {
         if (entityLeashKnot.getHangingPosition().equals(pos)) {
            return entityLeashKnot;
         }
      }

      return null;
   }

   @Override
   public void entityInit() {
      super.entityInit();
   }

   @Override
   public boolean isInRangeToRenderDist(double d) {
      return d < 1024.0;
   }

   @Override
   public boolean onValidSurface() {
      return this.entityWorld.getBlockState(this.hangingPosition).getBlock() instanceof BlockFence;
   }

   @Override
   public int getWidthPixels() {
      return 9;
   }

   public EntityLeashKnot(World world, BlockPos pos) {
      super(world, pos);
      this.setPosition((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5);
      float f = 0.125F;
      float f2 = 0.1875F;
      float f3 = 0.25F;
      this.setEntityBoundingBox(
         new AxisAlignedBB(this.posX - 0.1875, this.posY - 0.25 + 0.125, this.posZ - 0.1875, this.posX + 0.1875, this.posY + 0.25 + 0.125, this.posZ + 0.1875)
      );
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
   }

   @Override
   public void onBroken(Entity entity) {
   }

   @Override
   public int getHeightPixels() {
      return 9;
   }

   @Override
   public boolean interactFirst(EntityPlayer player) {
      ItemStack heldItem = player.getHeldItem();
      boolean flag = false;
      if (heldItem != null && heldItem.getItem() == Items.lead && !this.entityWorld.isRemote) {
         double d = 7.0;

         for (EntityLiving entityLiving : (Iterable<EntityLiving>)(this.entityWorld
            .getEntitiesWithinAABB(
               EntityLiving.class,
               new AxisAlignedBB(this.posX - d, this.posY - d, this.posZ - d, this.posX + d, this.posY + d, this.posZ + d)
            ))) {
            if (entityLiving.getLeashed() && entityLiving.getLeashedToEntity() == player) {
               entityLiving.setLeashedToEntity(this, true);
               flag = true;
            }
         }
      }

      if (!this.entityWorld.isRemote && !flag) {
         this.setDead();
         if (player.capabilities.isCreativeMode) {
            double d2 = 7.0;

            for (EntityLiving entityLiving2 : (Iterable<EntityLiving>)(this.entityWorld
               .getEntitiesWithinAABB(
                  EntityLiving.class,
                  new AxisAlignedBB(this.posX - d2, this.posY - d2, this.posZ - d2, this.posX + d2, this.posY + d2, this.posZ + d2)
               ))) {
               if (entityLiving2.getLeashed() && entityLiving2.getLeashedToEntity() == this) {
                  entityLiving2.clearLeashed(true, false);
               }
            }
         }
      }

      return true;
   }

   @Override
   public float getEyeHeight() {
      return -0.0625F;
   }

   @Override
   public void updateFacingWithBoundingBox(EnumFacing facing) {
   }

   @Override
   public boolean writeToNBTOptional(NBTTagCompound tag) {
      return false;
   }

   public EntityLeashKnot(World world) {
      super(world);
   }
}
