package net.minecraft.block;

import client.setting.MovementProperty;
import client.setting.MovementScaleProperty;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import client.util.PrimaryIntArrayEntry;
import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;

public class AuxiliaryBlockLog extends GeometryParser {
   public static final ImmutableSetEntry immutableSetEntry4 = ImmutableSetEntry.resolveImmutableSetEntry(
      "variant", MovementProperty.class, property -> matchesMovementProperty((MovementProperty)property)
   );

   @Override
   public MapColor processAction3(IBlockState blockState) {
      MovementProperty comparable = (MovementProperty)blockState.getComparable(immutableSetEntry4);
      switch (PrimaryIntArrayEntry.countArray2[((MovementScaleProperty)blockState.getComparable(immutableSetEntry2)).ordinal()]) {
         case 1:
         case 2:
         case 3:
         default:
            switch (PrimaryIntArrayEntry.countArray[comparable.ordinal()]) {
               case 1:
               default:
                  return MapColor.mapColor10;
               case 2:
                  return MovementProperty.DARK_OAK.getMapColor();
            }
         case 4:
            return comparable.getMapColor();
      }
   }

   @Override
   public IBlockState processAction(int count) {
      IBlockState blockState = this.getDefaultState().resolveIBlockState(immutableSetEntry4, MovementProperty.resolveMovementProperty((count & 3) + 4));
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
      list.add(new ItemStack(item, 1, MovementProperty.ACACIA.getCount() - 4));
      list.add(new ItemStack(item, 1, MovementProperty.DARK_OAK.getCount() - 4));
   }

   private static boolean matchesMovementProperty(MovementProperty movementProperty) {
      return movementProperty.getCount() >= 4;
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{immutableSetEntry4, immutableSetEntry2});
   }

   @Override
   public int getMetaFromState(IBlockState blockState) {
      return ((MovementProperty)blockState.getComparable(immutableSetEntry4)).getCount() - 4;
   }

   @Override
   public int getIndex(IBlockState blockState) {
      int count = 0 | ((MovementProperty)blockState.getComparable(immutableSetEntry4)).getCount() - 4;
      switch (PrimaryIntArrayEntry.countArray2[((MovementScaleProperty)blockState.getComparable(immutableSetEntry2)).ordinal()]) {
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
      return new ItemStack(Item.processAction2(this), 1, ((MovementProperty)blockState.getComparable(immutableSetEntry4)).getCount() - 4);
   }

   public AuxiliaryBlockLog() {
      this.setIBlockState(
         this.blockManager
            .get()
            .resolveIBlockState(immutableSetEntry4, MovementProperty.ACACIA)
            .resolveIBlockState(immutableSetEntry2, MovementScaleProperty.Y)
      );
   }
}
