package net.minecraft.item;

import java.util.List;
import java.util.Random;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Rotations;
import net.minecraft.world.World;

public class ItemArmorStand extends Item {

   public ItemArmorStand() {
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   @Override
   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3) {
      if (facing == EnumFacing.DOWN) {
         return false;
      } else {
         BlockPos pos2 = world.getBlockState(pos).getBlock().isReplaceable(world, pos) ? pos : pos.offset(facing);
         if (!player.canPlayerEdit(pos2, facing, stack)) {
            return false;
         } else {
            BlockPos pos3 = pos2.up();
            boolean flag = !world.isAirBlock(pos2) && !world.getBlockState(pos2).getBlock().isReplaceable(world, pos2);
            flag |= !world.isAirBlock(pos3) && !world.getBlockState(pos3).getBlock().isReplaceable(world, pos3);
            if (flag) {
               return false;
            } else {
               double x = (double)pos2.getX();
               double y = (double)pos2.getY();
               double z = (double)pos2.getZ();
               if (world.getEntitiesWithinAABBExcludingEntity(
                  (Entity)null, AxisAlignedBB.fromBounds(x, y, z, x + 1.0, y + 2.0, z + 1.0)
               ).size() > 0) {
                  return false;
               } else {
                  if (!world.isRemote) {
                     world.setBlockToAir(pos2);
                     world.setBlockToAir(pos3);
                     EntityArmorStand entityArmorStand = new EntityArmorStand(world, x + 0.5, y, z + 0.5);
                     float f4 = (float)MathHelper.floor_float((MathHelper.wrapAngleTo180_float(player.rotationYaw - 180.0F) + 22.5F) / 45.0F) * 45.0F;
                     entityArmorStand.setLocationAndAngles(x + 0.5, y, z + 0.5, f4, 0.0F);
                     this.applyRandomRotations(entityArmorStand, world.rand);
                     NBTTagCompound tagCompound = stack.getTagCompound();
                     if (tagCompound != null && tagCompound.hasKey("EntityTag", 10)) {
                        NBTTagCompound tag = new NBTTagCompound();
                        entityArmorStand.writeToNBTOptional(tag);
                        tag.merge(tagCompound.getCompoundTag("EntityTag"));
                        entityArmorStand.readFromNBT(tag);
                     }

                     world.spawnEntityInWorld(entityArmorStand);
                  }

                  stack.stackSize--;
                  return true;
               }
            }
         }
      }
   }

   private void applyRandomRotations(EntityArmorStand entityArmorStand, Random rand) {
      Rotations headRotation = entityArmorStand.getHeadRotation();
      float f = rand.nextFloat() * 5.0F;
      float f2 = rand.nextFloat() * 20.0F - 10.0F;
      Rotations rotations = new Rotations(headRotation.getX() + f, headRotation.getY() + f2, headRotation.getZ());
      entityArmorStand.setHeadRotation(rotations);
      headRotation = entityArmorStand.getBodyRotation();
      f = rand.nextFloat() * 10.0F - 5.0F;
      rotations = new Rotations(headRotation.getX(), headRotation.getY() + f, headRotation.getZ());
      entityArmorStand.setBodyRotation(rotations);
   }
}
