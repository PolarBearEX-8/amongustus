package net.minecraft.block;

import client.setting.AttackEffectsExplosionNormalType;
import client.setting.EnumFacingXType;
import client.util.AimObserver;
import client.util.IBlockState;
import client.util.InternalImmutableSetEntry;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockSponge extends Block {
   public static final InternalImmutableSetEntry internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("wet");

   private boolean matchesWorld3(World world, BlockPos blockPos) {
      LinkedList list = Lists.newLinkedList();
      ArrayList list2 = Lists.newArrayList();
      list.add(new AimObserver(blockPos, 0));
      int count = 0;

      while (!list.isEmpty()) {
         AimObserver aimObserver = (AimObserver)list.poll();
         BlockPos dataRef = (BlockPos)aimObserver.getDataRef2();
         int dataRef2 = (Integer)aimObserver.getDataRef();

         for (EnumFacing enumFacing : EnumFacing.getEntries()) {
            BlockPos blockPos2 = dataRef.offset(enumFacing);
            if (world.getBlockState(blockPos2).getBlock().getMaterial() == Material.coreItemEntry1) {
               world.matchesBlockPos(blockPos2, Blocks.air.getDefaultState(), 2);
               list2.add(blockPos2);
               count++;
               if (dataRef2 < 6) {
                  list.add(new AimObserver(blockPos2, dataRef2 + 1));
               }
            }
         }

         if (count > 64) {
            break;
         }
      }

      for (Object blockPos3Obj : list2) { BlockPos blockPos3 = (BlockPos)blockPos3Obj;
         world.applyBlockPosByBlockPos(blockPos3, Blocks.air);
      }

      return count > 0;
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(internalImmutableSetEntry, (count & 1) == 1);
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      list.add(new ItemStack(item, 1, 0));
      list.add(new ItemStack(item, 1, 1));
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      this.applyWorld3(world, blockPos, blockState);
      super.processAction(world, blockPos, blockState, block);
   }

   public void applyWorld3(World world, BlockPos blockPos, IBlockState blockState) {
      if (!(Boolean)blockState.getComparable(internalImmutableSetEntry) && this.matchesWorld3(world, blockPos)) {
         world.matchesBlockPos(blockPos, blockState.resolveIBlockState(internalImmutableSetEntry, Boolean.TRUE), 2);
         world.applyBlockPosByBlockPos(2001, blockPos, Block.getIdFromBlock(Blocks.blockLava2));
      }
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState) {
      this.applyWorld3(world, blockPos, blockState);
   }

   @Override
   public int getMetaFromState(IBlockState blockState) {
      return (((Boolean)blockState.getComparable(internalImmutableSetEntry)) ? 1 : 0);
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{internalImmutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return (((Boolean)blockState.getComparable(internalImmutableSetEntry)) ? 1 : 0);
   }

   @Override
   public String getText() {
      return I18n.getString2(this.getTextAsText() + ".dry.name");
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if ((Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         EnumFacing enumFacing = EnumFacing.resolveEnumFacing(random);
         if (enumFacing != EnumFacing.UP && !World.matchesIBlockAccess(world, blockPos.getX() + enumFacing.getInt2(), blockPos.getY() + enumFacing.getInt3(), blockPos.getZ() + enumFacing.getInt())
            )
          {
            double coordX = (double)blockPos.getX();
            double coordY = (double)blockPos.getY();
            double coordZ = (double)blockPos.getZ();
            double offset;
            double offset2;
            double offset3;
            if (enumFacing == EnumFacing.DOWN) {
               offset2 = coordY - 0.05;
               offset = coordX + random.nextDouble();
               offset3 = coordZ + random.nextDouble();
            } else {
               offset2 = coordY + random.nextDouble() * 0.8;
               if (enumFacing.getEnumFacingXType() == EnumFacingXType.X) {
                  offset3 = coordZ + random.nextDouble();
                  if (enumFacing == EnumFacing.EAST) {
                     offset = coordX + 1.0;
                  } else {
                     offset = coordX + 0.05;
                  }
               } else {
                  offset = coordX + random.nextDouble();
                  if (enumFacing == EnumFacing.SOUTH) {
                     offset3 = coordZ + 1.0;
                  } else {
                     offset3 = coordZ + 0.05;
                  }
               }
            }

            world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.DRIP_WATER, offset, offset2, offset3, 0.0, 0.0, 0.0, new int[0]);
         }
      }
   }

   public BlockSponge() {
      super(Material.material2);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE));
      this.processAction(ItemGroupManager.itemGroupManager10);
   }
}
