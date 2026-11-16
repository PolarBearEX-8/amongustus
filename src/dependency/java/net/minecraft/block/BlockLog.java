package net.minecraft.block;

import client.setting.MovementProperty;
import client.setting.MovementScaleProperty;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import client.util.SharedIntArrayEntry;
import client.util.SharedPredicateAdapter;
import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;

public class BlockLog extends GeometryParser {
   public static final ImmutableSetEntry immutableSetEntry3 = ImmutableSetEntry.resolveImmutableSetEntry(
      "variant", MovementProperty.class, new SharedPredicateAdapter()
   );

   @Override
   public MapColor processAction3(IBlockState blockState) {
      MovementProperty comparable = (MovementProperty)blockState.getComparable(immutableSetEntry3);
      switch (SharedIntArrayEntry.countArray2[((MovementScaleProperty)blockState.getComparable(immutableSetEntry2)).ordinal()]) {
         case 1:
         case 2:
         case 3:
         default:
            switch (SharedIntArrayEntry.countArray[comparable.ordinal()]) {
               case 1:
               default:
                  return MovementProperty.SPRUCE.getMapColor();
               case 2:
                  return MovementProperty.DARK_OAK.getMapColor();
               case 3:
                  return MapColor.mapColor30;
               case 4:
                  return MovementProperty.SPRUCE.getMapColor();
            }
         case 4:
            return comparable.getMapColor();
      }
   }

   @Override
   public IBlockState processAction(int count) {
      IBlockState blockState = this.getDefaultState().resolveIBlockState(immutableSetEntry3, MovementProperty.resolveMovementProperty((count & 3) % 4));
      IBlockState blockState2;
      switch (count & 12) {
         case 0:
            blockState2 = blockState.resolveIBlockState(immutableSetEntry2, MovementScaleProperty.Y);
            break;
         case 4:
            blockState2 = blockState.resolveIBlockState(immutableSetEntry2, MovementScaleProperty.X);
            break;
         case 8:
            blockState2 = blockState.resolveIBlockState(immutableSetEntry2, MovementScaleProperty.Z);
            break;
         default:
            blockState2 = blockState.resolveIBlockState(immutableSetEntry2, MovementScaleProperty.NONE);
      }

      return blockState2;
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      list.add(new ItemStack(item, 1, MovementProperty.OAK.getCount()));
      list.add(new ItemStack(item, 1, MovementProperty.SPRUCE.getCount()));
      list.add(new ItemStack(item, 1, MovementProperty.BIRCH.getCount()));
      list.add(new ItemStack(item, 1, MovementProperty.JUNGLE.getCount()));
   }

   @Override
   public int getMetaFromState(IBlockState blockState) {
      return ((MovementProperty)blockState.getComparable(immutableSetEntry3)).getCount();
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{immutableSetEntry3, immutableSetEntry2});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      int count = 0 | ((MovementProperty)blockState.getComparable(immutableSetEntry3)).getCount();
      switch (SharedIntArrayEntry.countArray2[((MovementScaleProperty)blockState.getComparable(immutableSetEntry2)).ordinal()]) {
         case 1:
            count |= 4;
            break;
         case 2:
            count |= 8;
            break;
         case 3:
            count |= 12;
      }

      return count;
   }

   @Override
   public ItemStack resolveItemStack(IBlockState blockState) {
      return new ItemStack(Item.processAction2(this), 1, ((MovementProperty)blockState.getComparable(immutableSetEntry3)).getCount());
   }

   public BlockLog() {
      this.setIBlockState(
         this.blockManager.get().resolveIBlockState(immutableSetEntry3, MovementProperty.OAK).resolveIBlockState(immutableSetEntry2, MovementScaleProperty.Y)
      );
   }
}
