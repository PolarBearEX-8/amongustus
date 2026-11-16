package net.minecraft.block;

import client.setting.AttackEffectsExplosionNormalType;
import client.util.DampenerFormatter;
import client.util.IBlockState;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRedstoneTorch extends BlockTorch {
   public static Map map = Maps.newHashMap();
   public final boolean enabled;

   private boolean matchesWorldByWorld(World world, BlockPos blockPos, boolean flag) {
      if (!map.containsKey(world)) {
         map.put(world, Lists.newArrayList());
      }

      List list = (List)map.get(world);
      if (flag) {
         list.add(new DampenerFormatter(blockPos, world.getTimestamp2()));
      }

      int count = 0;

      for (int index = 0; index < list.size(); index++) {
         if (((DampenerFormatter)list.get(index)).blockPos.equals(blockPos)) {
            if (++count >= 8) {
               return true;
            }
         }
      }

      return false;
   }

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      boolean flag = this.matchesWorld5(world, blockPos, blockState);
      List list = (List)map.get(world);

      while (list != null && !list.isEmpty() && world.getTimestamp2() - ((DampenerFormatter)list.get(0)).timestamp > 60L) {
         list.remove(0);
      }

      if (this.enabled) {
         if (flag) {
            world.matchesBlockPos(blockPos, Blocks.block10.getDefaultState().resolveIBlockState(autoWalkPath, blockState.getComparable(autoWalkPath)), 3);
            if (this.matchesWorldByWorld(world, blockPos, true)) {
               world.applyText(
                  (double)((float)blockPos.getX() + 0.5F),
                  (double)((float)blockPos.getY() + 0.5F),
                  (double)((float)blockPos.getZ() + 0.5F),
                  "random.fizz",
                  0.5F,
                  2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F
               );

               for (int index = 0; index < 5; index++) {
                  double offset = (double)blockPos.getX() + random.nextDouble() * 0.6 + 0.2;
                  double offset2 = (double)blockPos.getY() + random.nextDouble() * 0.6 + 0.2;
                  double offset3 = (double)blockPos.getZ() + random.nextDouble() * 0.6 + 0.2;
                  world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.SMOKE_NORMAL, offset, offset2, offset3, 0.0, 0.0, 0.0, new int[0]);
               }

               world.applyBlockPos(blockPos, world.getBlockState(blockPos).getBlock(), 160);
            }
         }
      } else if (!flag && !this.matchesWorldByWorld(world, blockPos, false)) {
         world.matchesBlockPos(blockPos, Blocks.block12.getDefaultState().resolveIBlockState(autoWalkPath, blockState.getComparable(autoWalkPath)), 3);
      }
   }

   @Override
   public void processAction2(World world, BlockPos blockPos, IBlockState blockState, Random random) {
   }

   @Override
   public int processAction(World world) {
      return 2;
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return Item.processAction2(Blocks.block12);
   }

   @Override
   public boolean processAction7() {
      return true;
   }

   @Override
   public void onBlockAdded(World world, BlockPos blockPos, IBlockState blockState) {
      if (this.enabled) {
         for (EnumFacing enumFacing : EnumFacing.getEntries()) {
            world.applyBlockPosByBlockPos(blockPos.offset(enumFacing), this);
         }
      }
   }

   @Override
   public int processAction(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState blockState, EnumFacing enumFacing) {
      return this.enabled && blockState.getComparable(autoWalkPath) != enumFacing ? 15 : 0;
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Item.processAction2(Blocks.block12);
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      if (!this.matchesWorld(world, blockPos, blockState) && this.enabled == this.matchesWorld5(world, blockPos, blockState)) {
         world.applyBlockPos(blockPos, this, this.processAction(world));
      }
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState) {
      if (this.enabled) {
         for (EnumFacing enumFacing : EnumFacing.getEntries()) {
            world.applyBlockPosByBlockPos(blockPos.offset(enumFacing), this);
         }
      }
   }

   @Override
   public int processAction2(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState blockState, EnumFacing enumFacing) {
      return enumFacing == EnumFacing.DOWN ? this.processAction(iBlockAccess, blockPos, blockState, enumFacing) : 0;
   }

   private boolean matchesWorld5(World world, BlockPos blockPos, IBlockState blockState) {
      EnumFacing enumFacing = ((EnumFacing)blockState.getComparable(autoWalkPath)).getEnumFacing5();
      return world.matchesBlockPos(blockPos.offset(enumFacing), enumFacing);
   }

   @Override
   public boolean matchesBlock(Block block) {
      return block == Blocks.block10 || block == Blocks.block12;
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if (this.enabled) {
         double offset = (double)blockPos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.2;
         double offset2 = (double)blockPos.getY() + 0.7 + (random.nextDouble() - 0.5) * 0.2;
         double offset3 = (double)blockPos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.2;
         EnumFacing comparable = (EnumFacing)blockState.getComparable(autoWalkPath);
         if (comparable.getEnumFacingXType().isActiveAsFlag()) {
            EnumFacing enumFacing = comparable.getEnumFacing5();
            offset += 0.27 * (double)enumFacing.getInt2();
            offset2 += 0.22;
            offset3 += 0.27 * (double)enumFacing.getInt();
         }

         world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.REDSTONE, offset, offset2, offset3, 0.0, 0.0, 0.0, new int[0]);
      }
   }

   public BlockRedstoneTorch(boolean flag) {
      this.enabled = flag;
      this.processAction(true);
      this.processAction((ItemGroupManager)null);
   }
}
