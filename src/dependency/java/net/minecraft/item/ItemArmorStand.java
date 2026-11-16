package net.minecraft.item;

import client.util.ManagedFloatEntry;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityArmorstand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemArmorStand extends Item {
   private void applyEntityArmorstand(EntityArmorstand entityArmorstand, Random random) {
      ManagedFloatEntry managedFloatEntry = entityArmorstand.getManagedFloatEntry();
      float factor = random.nextFloat() * 5.0F;
      float factor2 = random.nextFloat() * 20.0F - 10.0F;
      ManagedFloatEntry managedFloatEntry2 = new ManagedFloatEntry(managedFloatEntry.getFactor3() + factor, managedFloatEntry.getFactor() + factor2, managedFloatEntry.getFactor2());
      entityArmorstand.applyManagedFloatEntry2(managedFloatEntry2);
      ManagedFloatEntry managedFloatEntry3 = entityArmorstand.getManagedFloatEntry8();
      float factor3 = random.nextFloat() * 10.0F - 5.0F;
      ManagedFloatEntry managedFloatEntry4 = new ManagedFloatEntry(managedFloatEntry3.getFactor3(), managedFloatEntry3.getFactor() + factor3, managedFloatEntry3.getFactor2());
      entityArmorstand.applyManagedFloatEntryByManagedFloatEntr(managedFloatEntry4);
   }

   @Override
   public boolean processAction(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (enumFacing == EnumFacing.DOWN) {
         return false;
      } else {
         BlockPos blockPos2 = world.getBlockState(blockPos).getBlock().processAction6(world, blockPos) ? blockPos : blockPos.offset(enumFacing);
         if (!player.matchesBlockPos(blockPos2, enumFacing, stack)) {
            return false;
         } else {
            BlockPos blockPos3 = blockPos2.up();
            if ((!world.isAirBlock(blockPos2) && !world.getBlockState(blockPos2).getBlock().processAction6(world, blockPos2))
               | (!world.isAirBlock(blockPos3) && !world.getBlockState(blockPos3).getBlock().processAction6(world, blockPos3))) {
               return false;
            } else {
               double coordX = (double)blockPos2.getX();
               double coordY = (double)blockPos2.getY();
               double coordZ = (double)blockPos2.getZ();
               if (world.resolveListByEntity((Entity)null, AxisAlignedBB.resolveAxisAlignedBB(coordX, coordY, coordZ, coordX + 1.0, coordY + 2.0, coordZ + 1.0))
                     .size()
                  > 0) {
                  return false;
               } else {
                  if (!world.state) {
                     world.matchesBlockPos8(blockPos2);
                     world.matchesBlockPos8(blockPos3);
                     EntityArmorstand entityArmorstand = new EntityArmorstand(world, coordX + 0.5, coordY, coordZ + 0.5);
                     float factor = (float)MathHelper.floor_float((MathHelper.sqrt_float(player.rotationYaw - 180.0F) + 22.5F) / 45.0F) * 45.0F;
                     entityArmorstand.applyDoubleByDouble(coordX + 0.5, coordY, coordZ + 0.5, factor, 0.0F);
                     this.applyEntityArmorstand(entityArmorstand, world.random);
                     NBTTagCompound stackTagCompound = stack.getStackTagCompound();
                     if (stackTagCompound != null && stackTagCompound.matchesText("EntityTag", 10)) {
                        NBTTagCompound tagCompound = new NBTTagCompound();
                        entityArmorstand.matchesNBTTagCompound(tagCompound);
                        tagCompound.applyNBTTagCompound(stackTagCompound.getNBTTagCompound("EntityTag"));
                        entityArmorstand.applyNBTTagCompound2(tagCompound);
                     }

                     world.matchesEntity(entityArmorstand);
                  }

                  stack.animationsToGo--;
                  return true;
               }
            }
         }
      }
   }

   public ItemArmorStand() {
      this.processAction(ItemGroupManager.itemGroupManager8);
   }
}
