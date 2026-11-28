package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ItemShears extends Item {
   @Override
   public float getStrVsBlock(ItemStack stack, Block block) {
      return block != Blocks.web && block.getMaterial() != Material.leaves ? (block == Blocks.wool ? 5.0F : super.getStrVsBlock(stack, block)) : 15.0F;
   }

   public ItemShears() {
      this.setMaxStackSize(1);
      this.setMaxDamage(238);
      this.setCreativeTab(CreativeTabs.tabTools);
   }

   @Override
   public boolean onBlockDestroyed(ItemStack stack, World world, Block block, BlockPos pos, EntityLivingBase entity) {
      if (block.getMaterial() != Material.leaves
         && block != Blocks.web
         && block != Blocks.tallgrass
         && block != Blocks.vine
         && block != Blocks.tripwire
         && block != Blocks.wool) {
         return super.onBlockDestroyed(stack, world, block, pos, entity);
      } else {
         stack.damageItem(1, entity);
         return true;
      }
   }

   @Override
   public boolean canHarvestBlock(Block block) {
      return block == Blocks.web || block == Blocks.redstone_wire || block == Blocks.tripwire;
   }
}
