package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ItemShears extends Item {
   @Override
   public float processAction(ItemStack stack, Block block) {
      return block != Blocks.web && block.getMaterial() != Material.material9 ? (block == Blocks.wool ? 5.0F : super.processAction(stack, block)) : 15.0F;
   }

   @Override
   public boolean processAction(Block block) {
      return block == Blocks.web || block == Blocks.blockRedstoneWire || block == Blocks.block6;
   }

   @Override
   public boolean processAction(ItemStack stack, World world, Block block, BlockPos blockPos, EntityLivingBase livingEntity) {
      if (block.getMaterial() != Material.material9
         && block != Blocks.web
         && block != Blocks.blockTallgrass
         && block != Blocks.block144
         && block != Blocks.block6
         && block != Blocks.wool) {
         return super.processAction(stack, world, block, blockPos, livingEntity);
      } else {
         stack.applyEntityLivingBaseByEntityLivingBase(1, livingEntity);
         return true;
      }
   }

   public ItemShears() {
      this.processAction4(1);
      this.processAction3(238);
      this.processAction(ItemGroupManager.itemGroupManager2);
   }
}
