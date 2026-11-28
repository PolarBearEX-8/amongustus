package net.minecraft.item;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemFireball extends Item {
   public ItemFireball() {
      this.setCreativeTab(CreativeTabs.tabMisc);
   }

   @Override
   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3) {
      if (world.isRemote) {
         return true;
      } else {
         pos = pos.offset(facing);
         if (!player.canPlayerEdit(pos, facing, stack)) {
            return false;
         } else {
            if (world.getBlockState(pos).getBlock().getMaterial() == Material.air) {
               world.playSoundEffect(
                  (double)pos.getX() + 0.5,
                  (double)pos.getY() + 0.5,
                  (double)pos.getZ() + 0.5,
                  "item.fireCharge.use",
                  1.0F,
                  (itemRand.nextFloat() - itemRand.nextFloat()) * 0.2F + 1.0F
               );
               world.setBlockState(pos, Blocks.fire.getDefaultState());
            }

            if (!player.capabilities.isCreativeMode) {
               stack.stackSize--;
            }

            return true;
         }
      }
   }
}
