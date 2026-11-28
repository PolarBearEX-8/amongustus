package net.minecraft.block;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRedstoneTorch extends BlockTorch {
   private static Map toggles = Maps.newHashMap();
   private final boolean isOn;

   @Override
   public void randomTick(World world, BlockPos pos, IBlockState state, Random rand) {
   }

   @Override
   public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (this.isOn) {
         double d = (double)pos.getX() + 0.5 + (rand.nextDouble() - 0.5) * 0.2;
         double d2 = (double)pos.getY() + 0.7 + (rand.nextDouble() - 0.5) * 0.2;
         double d3 = (double)pos.getZ() + 0.5 + (rand.nextDouble() - 0.5) * 0.2;
         EnumFacing value = (EnumFacing)state.getValue(FACING);
         if (value.getAxis().isHorizontal()) {
            EnumFacing opposite = value.getOpposite();
            double d4 = 0.27;
            d += 0.27 * (double)opposite.getFrontOffsetX();
            d2 += 0.22;
            d3 += 0.27 * (double)opposite.getFrontOffsetZ();
         }

         world.spawnParticle(EnumParticleTypes.REDSTONE, d, d2, d3, 0.0, 0.0, 0.0, new int[0]);
      }
   }

   public BlockRedstoneTorch(boolean flag) {
      this.isOn = flag;
      this.setTickRandomly(true);
      this.setCreativeTab((CreativeTabs)null);
   }

   @Override
   public int getWeakPower(IBlockAccess world, BlockPos pos, IBlockState state, EnumFacing facing) {
      return this.isOn && state.getValue(FACING) != facing ? 15 : 0;
   }

   private boolean shouldBeOff(World world, BlockPos pos, IBlockState state) {
      EnumFacing opposite = ((EnumFacing)state.getValue(FACING)).getOpposite();
      return world.isSidePowered(pos.offset(opposite), opposite);
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      if (!this.onNeighborChangeInternal(world, pos, state) && this.isOn == this.shouldBeOff(world, pos, state)) {
         world.scheduleUpdate(pos, this, this.tickRate(world));
      }
   }

   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
      if (this.isOn) {
         for (EnumFacing facing : EnumFacing.getValues()) {
            world.notifyNeighborsOfStateChange(pos.offset(facing), this);
         }
      }
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return Item.getItemFromBlock(Blocks.redstone_torch);
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      boolean flag = this.shouldBeOff(world, pos, state);
      List list = (List)toggles.get(world);

      while (list != null && !list.isEmpty() && world.getTotalWorldTime() - ((BlockRedstoneTorch.Toggle)list.get(0)).time > 60L) {
         list.remove(0);
      }

      if (this.isOn) {
         if (flag) {
            world.setBlockState(pos, Blocks.unlit_redstone_torch.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
            if (this.isBurnedOut(world, pos, true)) {
               world.playSoundEffect(
                  (double)((float)pos.getX() + 0.5F),
                  (double)((float)pos.getY() + 0.5F),
                  (double)((float)pos.getZ() + 0.5F),
                  "random.fizz",
                  0.5F,
                  2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F
               );

               for (int i = 0; i < 5; i++) {
                  double d = (double)pos.getX() + rand.nextDouble() * 0.6 + 0.2;
                  double d2 = (double)pos.getY() + rand.nextDouble() * 0.6 + 0.2;
                  double d3 = (double)pos.getZ() + rand.nextDouble() * 0.6 + 0.2;
                  world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d, d2, d3, 0.0, 0.0, 0.0, new int[0]);
               }

               world.scheduleUpdate(pos, world.getBlockState(pos).getBlock(), 160);
            }
         }
      } else if (!flag && !this.isBurnedOut(world, pos, false)) {
         world.setBlockState(pos, Blocks.redstone_torch.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
      }
   }

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      if (this.isOn) {
         for (EnumFacing facing : EnumFacing.getValues()) {
            world.notifyNeighborsOfStateChange(pos.offset(facing), this);
         }
      }
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Item.getItemFromBlock(Blocks.redstone_torch);
   }

   @Override
   public boolean isAssociatedBlock(Block block) {
      return block == Blocks.unlit_redstone_torch || block == Blocks.redstone_torch;
   }

   @Override
   public boolean canProvidePower() {
      return true;
   }

   @Override
   public int tickRate(World world) {
      return 2;
   }

   @Override
   public int getStrongPower(IBlockAccess world, BlockPos pos, IBlockState state, EnumFacing facing) {
      return facing == EnumFacing.DOWN ? this.getWeakPower(world, pos, state, facing) : 0;
   }

   private boolean isBurnedOut(World world, BlockPos pos, boolean flag) {
      if (!toggles.containsKey(world)) {
         toggles.put(world, Lists.newArrayList());
      }

      List list = (List)toggles.get(world);
      if (flag) {
         list.add(new BlockRedstoneTorch.Toggle(pos, world.getTotalWorldTime()));
      }

      int n = 0;

      for (int i = 0; i < list.size(); i++) {
         if (((BlockRedstoneTorch.Toggle)list.get(i)).pos.equals(pos)) {
            if (++n >= 8) {
               return true;
            }
         }
      }

      return false;
   }


   public static class Toggle {
      public BlockPos pos;
      public long time;

      public Toggle(BlockPos pos2, long l) {
         this.pos = pos2;
         this.time = l;
      }
   }
}
