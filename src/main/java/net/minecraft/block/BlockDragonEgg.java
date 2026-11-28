package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDragonEgg extends Block {
   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      this.teleport(world, pos);
      return true;
   }

   @Override
   public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {
      this.teleport(world, pos);
   }

   private void teleport(World world, BlockPos pos) {
      IBlockState blockState2 = world.getBlockState(pos);
      if (blockState2.getBlock() == this) {
         for (int i = 0; i < 1000; i++) {
            BlockPos pos2 = pos.add(
               world.rand.nextInt(16) - world.rand.nextInt(16), world.rand.nextInt(8) - world.rand.nextInt(8), world.rand.nextInt(16) - world.rand.nextInt(16)
            );
            if (world.getBlockState(pos2).getBlock().blockMaterial == Material.air) {
               if (world.isRemote) {
                  for (int j = 0; j < 128; j++) {
                     double d = world.rand.nextDouble();
                     float f = (world.rand.nextFloat() - 0.5F) * 0.2F;
                     float f2 = (world.rand.nextFloat() - 0.5F) * 0.2F;
                     float f3 = (world.rand.nextFloat() - 0.5F) * 0.2F;
                     double d2 = (double)pos2.getX() + (double)(pos.getX() - pos2.getX()) * d + (world.rand.nextDouble() - 0.5) * 1.0 + 0.5;
                     double d3 = (double)pos2.getY() + (double)(pos.getY() - pos2.getY()) * d + world.rand.nextDouble() * 1.0 - 0.5;
                     double d4 = (double)pos2.getZ() + (double)(pos.getZ() - pos2.getZ()) * d + (world.rand.nextDouble() - 0.5) * 1.0 + 0.5;
                     world.spawnParticle(EnumParticleTypes.PORTAL, d2, d3, d4, (double)f, (double)f2, (double)f3, new int[0]);
                  }
               } else {
                  world.setBlockState(pos2, blockState2, 2);
                  world.setBlockToAir(pos);
               }

               return;
            }
         }
      }
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      world.scheduleUpdate(pos, this, this.tickRate(world));
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      this.checkFall(world, pos);
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return null;
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public boolean shouldSideBeRendered(IBlockAccess world, int n, int n2, int n3, EnumFacing facing) {
      return true;
   }

   public BlockDragonEgg() {
      super(Material.dragonEgg, MapColor.blackColor);
      this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 1.0F, 0.9375F);
   }

   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
      world.scheduleUpdate(pos, this, this.tickRate(world));
   }

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return true;
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public int tickRate(World world) {
      return 5;
   }

   private void checkFall(World world, BlockPos pos) {
      if (BlockFalling.canFallInto(world, pos.down()) && pos.getY() >= 0) {
         byte b = 32;
         if (!BlockFalling.fallInstantly && world.isAreaLoaded(pos.add(-b, -b, -b), pos.add(b, b, b))) {
            world.spawnEntityInWorld(
               new EntityFallingBlock(
                  world, (double)((float)pos.getX() + 0.5F), (double)pos.getY(), (double)((float)pos.getZ() + 0.5F), this.getDefaultState()
               )
            );
         } else {
            world.setBlockToAir(pos);
            BlockPos pos2 = pos;

            while (BlockFalling.canFallInto(world, pos2) && pos2.getY() > 0) {
               pos2 = pos2.down();
            }

            if (pos2.getY() > 0) {
               world.setBlockState(pos2, this.getDefaultState(), 2);
            }
         }
      }
   }
}
