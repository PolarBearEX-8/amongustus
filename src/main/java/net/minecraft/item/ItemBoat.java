package net.minecraft.item;

import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemBoat extends Item {
   @Override
   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
      float f = 1.0F;
      float f2 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
      float f3 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
      double d = player.prevPosX + (player.posX - player.prevPosX) * (double)f;
      double d2 = player.prevPosY + (player.posY - player.prevPosY) * (double)f + (double)player.getEyeHeight();
      double d3 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)f;
      float f4 = MathHelper.cos(-f3 * (float) (Math.PI / 180.0) - (float) Math.PI);
      float f5 = MathHelper.sin(-f3 * (float) (Math.PI / 180.0) - (float) Math.PI);
      float f6 = -MathHelper.cos(-f2 * (float) (Math.PI / 180.0));
      float f7 = MathHelper.sin(-f2 * (float) (Math.PI / 180.0));
      float f8 = f5 * f6;
      float f9 = f4 * f6;
      double d4 = 5.0;
      MovingObjectPosition hit = world.rayTraceBlocks(d, d2, d3, d + (double)f8 * d4, d2 + (double)f7 * d4, d3 + (double)f9 * d4, true);
      if (hit == null) {
         return stack;
      } else {
         Vec3 look = player.getLook(f);
         boolean flag = false;
         float f10 = 1.0F;
         List list = world.getEntitiesWithinAABBExcludingEntity(
            player,
            player.getEntityBoundingBox()
               .addCoord(look.xCoord * d4, look.yCoord * d4, look.zCoord * d4)
               .expand((double)f10, (double)f10, (double)f10)
         );

         for (int i = 0; i < list.size(); i++) {
            Entity entity = (Entity)list.get(i);
            if (entity.canBeCollidedWith()) {
               float collisionBorderSize = entity.getCollisionBorderSize();
               if (entity.getEntityBoundingBox().expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize).isVecInside(d, d2, d3)) {
                  flag = true;
               }
            }
         }

         if (flag) {
            return stack;
         } else {
            if (hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
               BlockPos blockPos = hit.getBlockPos();
               if (world.getBlockState(blockPos).getBlock() == Blocks.snow_layer) {
                  blockPos = blockPos.down();
               }

               EntityBoat entityBoat = new EntityBoat(
                  world, (double)((float)blockPos.getX() + 0.5F), (double)((float)blockPos.getY() + 1.0F), (double)((float)blockPos.getZ() + 0.5F)
               );
               entityBoat.rotationYaw = (float)(((MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5) & 3) - 1) * 90);
               if (!world.getCollidingBoundingBoxes(entityBoat, entityBoat.getEntityBoundingBox().expand(-0.1, -0.1, -0.1)).isEmpty()) {
                  return stack;
               }

               if (!world.isRemote) {
                  world.spawnEntityInWorld(entityBoat);
               }

               if (!player.capabilities.isCreativeMode) {
                  stack.stackSize--;
               }

               player.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
            }

            return stack;
         }
      }
   }

   public ItemBoat() {
      this.maxStackSize = 1;
      this.setCreativeTab(CreativeTabs.tabTransport);
   }
}
