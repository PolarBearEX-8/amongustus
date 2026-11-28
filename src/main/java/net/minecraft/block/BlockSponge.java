package net.minecraft.block;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Tuple;
import net.minecraft.world.World;

public class BlockSponge extends Block {
   public static final PropertyBool WET = PropertyBool.create("wet");

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      this.tryAbsorb(world, pos, state);
      super.onNeighborBlockChange(world, pos, state, block);
   }

   @Override
   public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
      list.add(new ItemStack(item, 1, 0));
      list.add(new ItemStack(item, 1, 1));
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(WET, (n & 1) == 1);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{WET});
   }

   @Override
   public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if ((Boolean)state.getValue(WET)) {
         EnumFacing facing = EnumFacing.random(rand);
         if (facing != EnumFacing.UP
            && !World.doesBlockHaveSolidTopSurface(world, pos.getX() + facing.getFrontOffsetX(), pos.getY() + facing.getFrontOffsetY(), pos.getZ() + facing.getFrontOffsetZ())) {
            double x = (double)pos.getX();
            double y = (double)pos.getY();
            double z = (double)pos.getZ();
            if (facing == EnumFacing.DOWN) {
               y -= 0.05;
               x += rand.nextDouble();
               z += rand.nextDouble();
            } else {
               y += rand.nextDouble() * 0.8;
               if (facing.getAxis() == EnumFacing.Axis.X) {
                  z += rand.nextDouble();
                  if (facing == EnumFacing.EAST) {
                     x++;
                  } else {
                     x += 0.05;
                  }
               } else {
                  x += rand.nextDouble();
                  if (facing == EnumFacing.SOUTH) {
                     z++;
                  } else {
                     z += 0.05;
                  }
               }
            }

            world.spawnParticle(EnumParticleTypes.DRIP_WATER, x, y, z, 0.0, 0.0, 0.0, new int[0]);
         }
      }
   }

   public BlockSponge() {
      super(Material.sponge);
      this.setDefaultState(this.blockState.getBaseState().withProperty(WET, Boolean.FALSE));
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public int damageDropped(IBlockState state) {
      return (Boolean)state.getValue(WET) ? 1 : 0;
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return (Boolean)state.getValue(WET) ? 1 : 0;
   }

   @Override
   public String getLocalizedName() {
      return StatCollector.translateToLocal(this.getUnlocalizedName() + ".dry.name");
   }

   public void tryAbsorb(World world, BlockPos pos, IBlockState state) {
      if (!(Boolean)state.getValue(WET) && this.absorb(world, pos)) {
         world.setBlockState(pos, state.withProperty(WET, Boolean.TRUE), 2);
         world.playAuxSFX(2001, pos, Block.getIdFromBlock(Blocks.water));
      }
   }

   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
      this.tryAbsorb(world, pos, state);
   }

   private boolean absorb(World world, BlockPos pos) {
      LinkedList list = Lists.newLinkedList();
      ArrayList list2 = Lists.newArrayList();
      list.add(new Tuple(pos, 0));
      int n = 0;

      while (!list.isEmpty()) {
         Tuple tuple = (Tuple)list.poll();
         BlockPos first = (BlockPos)tuple.getFirst();
         int second = (Integer)tuple.getSecond();

         for (EnumFacing facing : EnumFacing.getValues()) {
            BlockPos pos2 = first.offset(facing);
            if (world.getBlockState(pos2).getBlock().getMaterial() == Material.water) {
               world.setBlockState(pos2, Blocks.air.getDefaultState(), 2);
               list2.add(pos2);
               n++;
               if (second < 6) {
                  list.add(new Tuple(pos2, second + 1));
               }
            }
         }

         if (n > 64) {
            break;
         }
      }

      for (BlockPos pos3 : (Iterable<BlockPos>)(list2)) {
         world.notifyNeighborsOfStateChange(pos3, Blocks.air);
      }

      return n > 0;
   }
}
