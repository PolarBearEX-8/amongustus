package net.minecraft.block;

import client.setting.AttackEffectsExplosionNormalType;
import client.util.IBlockState;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityFallingsand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDragonEgg extends Block {
   private void applyWorld4(World world, BlockPos blockPos) {
      if (CustomBlockAdapter.matchesWorld3(world, blockPos.down()) && blockPos.getY() >= 0) {
         if (!CustomBlockAdapter.enabled && world.matchesBlockPos(blockPos.add(-32, -32, -32), blockPos.add(32, 32, 32))) {
            world.matchesEntity(
               new EntityFallingsand(
                  world, (double)((float)blockPos.getX() + 0.5F), (double)blockPos.getY(), (double)((float)blockPos.getZ() + 0.5F), this.getDefaultState()
               )
            );
         } else {
            world.matchesBlockPos8(blockPos);
            BlockPos blockPos2 = blockPos;

            while (CustomBlockAdapter.matchesWorld3(world, blockPos2) && blockPos2.getY() > 0) {
               blockPos2 = blockPos2.down();
            }

            if (blockPos2.getY() > 0) {
               world.matchesBlockPos(blockPos2, this.getDefaultState(), 2);
            }
         }
      }
   }

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      this.applyWorld4(world, blockPos);
   }

   @Override
   public void onBlockClicked(World world, BlockPos blockPos, EntityPlayer player) {
      this.applyWorld2(world, blockPos);
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return true;
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return null;
   }

   @Override
   public int processAction(World world) {
      return 5;
   }

   private void applyWorld2(World world, BlockPos blockPos) {
      IBlockState blockState = world.getBlockState(blockPos);
      if (blockState.getBlock() == this) {
         for (int index = 0; index < 1000; index++) {
            BlockPos blockPos2 = blockPos.add(
               world.random.nextInt(16) - world.random.nextInt(16),
               world.random.nextInt(8) - world.random.nextInt(8),
               world.random.nextInt(16) - world.random.nextInt(16)
            );
            if (world.getBlockState(blockPos2).getBlock().coreItemEntry == Material.coreItemEntry) {
               if (world.state) {
                  for (int index2 = 0; index2 < 128; index2++) {
                     double offset = world.random.nextDouble();
                     float factor = (world.random.nextFloat() - 0.5F) * 0.2F;
                     float factor2 = (world.random.nextFloat() - 0.5F) * 0.2F;
                     float factor3 = (world.random.nextFloat() - 0.5F) * 0.2F;
                     double offset2 = (double)blockPos2.getX() + (double)(blockPos.getX() - blockPos2.getX()) * offset + (world.random.nextDouble() - 0.5) * 1.0 + 0.5;
                     double offset3 = (double)blockPos2.getY() + (double)(blockPos.getY() - blockPos2.getY()) * offset + world.random.nextDouble() * 1.0 - 0.5;
                     double offset4 = (double)blockPos2.getZ() + (double)(blockPos.getZ() - blockPos2.getZ()) * offset + (world.random.nextDouble() - 0.5) * 1.0 + 0.5;
                     world.applyAttackEffectsExplosionNorm(
                        AttackEffectsExplosionNormalType.PORTAL, offset2, offset3, offset4, (double)factor, (double)factor2, (double)factor3, new int[0]
                     );
                  }
               } else {
                  world.matchesBlockPos(blockPos2, blockState, 2);
                  world.matchesBlockPos8(blockPos);
               }

               return;
            }
         }
      }
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      world.applyBlockPos(blockPos, this, this.processAction(world));
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      this.applyWorld2(world, blockPos);
      return true;
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3, EnumFacing enumFacing) {
      return true;
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState) {
      world.applyBlockPos(blockPos, this, this.processAction(world));
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   public BlockDragonEgg() {
      super(Material.material3, MapColor.mapColor29);
      this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 1.0F, 0.9375F);
   }
}
