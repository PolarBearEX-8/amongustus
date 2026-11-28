package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemLead extends Item {
   @Override
   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3) {
      if (world.getBlockState(pos).getBlock() instanceof BlockFence) {
         if (world.isRemote) {
            return true;
         } else {
            attachToFence(player, world, pos);
            return true;
         }
      } else {
         return false;
      }
   }

   public static boolean attachToFence(EntityPlayer player, World world, BlockPos pos) {
      EntityLeashKnot knotForPosition = EntityLeashKnot.getKnotForPosition(world, pos);
      boolean flag = false;
      double d = 7.0;
      int x = pos.getX();
      int y = pos.getY();
      int z = pos.getZ();

      for (EntityLiving entityLiving : (Iterable<EntityLiving>)(world.getEntitiesWithinAABB(
         EntityLiving.class,
         new AxisAlignedBB((double)x - d, (double)y - d, (double)z - d, (double)x + d, (double)y + d, (double)z + d)
      ))) {
         if (entityLiving.getLeashed() && entityLiving.getLeashedToEntity() == player) {
            if (knotForPosition == null) {
               knotForPosition = EntityLeashKnot.createKnot(world, pos);
            }

            entityLiving.setLeashedToEntity(knotForPosition, true);
            flag = true;
         }
      }

      return flag;
   }

   public ItemLead() {
      this.setCreativeTab(CreativeTabs.tabTools);
   }

   public static boolean hasLeashedEntity(EntityPlayer player, World world, BlockPos pos) {
      double d = 7.0;
      int x = pos.getX();
      int y = pos.getY();
      int z = pos.getZ();

      for (EntityLiving entityLiving : (Iterable<EntityLiving>)(world.getEntitiesWithinAABB(
         EntityLiving.class,
         new AxisAlignedBB((double)x - d, (double)y - d, (double)z - d, (double)x + d, (double)y + d, (double)z + d)
      ))) {
         if (entityLiving.getLeashed() && entityLiving.getLeashedToEntity() == player) {
            return true;
         }
      }

      return false;
   }
}
