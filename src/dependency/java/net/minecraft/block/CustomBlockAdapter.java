package net.minecraft.block;

import client.util.IBlockState;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityFallingsand;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CustomBlockAdapter extends Block {
   public static boolean enabled;

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if (!world.state) {
         this.applyWorld2(world, blockPos);
      }
   }

   public static boolean matchesIBlockState(IBlockState blockState) {
      Block block = blockState.getBlock();
      Material material = block.getMaterial();
      return block == Blocks.blockFire || material == Material.coreItemEntry || material == Material.coreItemEntry1 || material == Material.material29;
   }

   @Override
   public int processAction(World world) {
      return 2;
   }

   public void applyEntityFallingsand(EntityFallingsand entityFallingsand) {
   }

   private void applyWorld2(World world, BlockPos blockPos) {
      if (matchesWorld3(world, blockPos.down()) && blockPos.getY() >= 0) {
         if (enabled || !world.matchesBlockPos(blockPos.add(-32, -32, -32), blockPos.add(32, 32, 32))) {
            world.matchesBlockPos8(blockPos);
            BlockPos blockPos2 = blockPos.down();

            while (matchesWorld3(world, blockPos2) && blockPos2.getY() > 0) {
               blockPos2 = blockPos2.down();
            }

            if (blockPos2.getY() > 0) {
               world.matchesBlockPos(blockPos2.up(), this.getDefaultState());
            }
         } else if (!world.state) {
            EntityFallingsand entityFallingsand = new EntityFallingsand(
               world, (double)blockPos.getX() + 0.5, (double)blockPos.getY(), (double)blockPos.getZ() + 0.5, world.getBlockState(blockPos)
            );
            this.applyEntityFallingsand(entityFallingsand);
            world.matchesEntity(entityFallingsand);
         }
      }
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      world.applyBlockPos(blockPos, this, this.processAction(world));
   }

   public static boolean matchesWorld3(World world, BlockPos blockPos) {
      return matchesIBlockState(world.getBlockState(blockPos));
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState) {
      world.applyBlockPos(blockPos, this, this.processAction(world));
   }

   public void applyWorld4(World world, BlockPos blockPos) {
   }

   public CustomBlockAdapter() {
      super(Material.material28);
      this.processAction(ItemGroupManager.itemGroupManager10);
   }

   public CustomBlockAdapter(Material material) {
      super(material);
   }
}
