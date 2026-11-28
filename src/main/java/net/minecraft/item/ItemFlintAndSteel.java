package net.minecraft.item;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemFlintAndSteel extends Item {
   @Override
   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3) {
      pos = pos.offset(facing);
      if (!player.canPlayerEdit(pos, facing, stack)) {
         return false;
      } else {
         if (world.getBlockState(pos).getBlock().getMaterial() == Material.air) {
            world.playSoundEffect(
               (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F
            );
            world.setBlockState(pos, Blocks.fire.getDefaultState());
         }

         stack.damageItem(1, player);
         return true;
      }
   }

   public ItemFlintAndSteel() {
      this.maxStackSize = 1;
      this.setMaxDamage(64);
      this.setCreativeTab(CreativeTabs.tabTools);
   }
}
