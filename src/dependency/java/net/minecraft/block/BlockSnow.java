package net.minecraft.block;

import client.setting.CombatConfigProperty;
import client.util.IBlockState;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.Items;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockSnow extends Block {
   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if (world.computeCount(CombatConfigProperty.BLOCK, blockPos.getX(), blockPos.getY(), blockPos.getZ()) > 11) {
         this.processAction(world, blockPos, world.getBlockState(blockPos), 0);
         world.matchesBlockPos8(blockPos);
      }
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Items.snowball;
   }

   @Override
   public int processAction(Random random) {
      return 4;
   }

   public BlockSnow() {
      super(Material.material11);
      this.processAction(true);
      this.processAction(ItemGroupManager.itemGroupManager10);
   }
}
