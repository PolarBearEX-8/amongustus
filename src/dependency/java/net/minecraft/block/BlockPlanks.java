package net.minecraft.block;

import client.setting.MovementProperty;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;

public class BlockPlanks extends Block {
   public static final ImmutableSetEntry immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry("variant", MovementProperty.class);

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(immutableSetEntry, MovementProperty.resolveMovementProperty(count));
   }

   @Override
   public MapColor processAction3(IBlockState blockState) {
      return ((MovementProperty)blockState.getComparable(immutableSetEntry)).getMapColor();
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      for (MovementProperty movementProperty : MovementProperty.values()) {
         list.add(new ItemStack(item, 1, movementProperty.getCount()));
      }
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{immutableSetEntry});
   }

   @Override
   public int getMetaFromState(IBlockState blockState) {
      return ((MovementProperty)blockState.getComparable(immutableSetEntry)).getCount();
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return ((MovementProperty)blockState.getComparable(immutableSetEntry)).getCount();
   }

   public BlockPlanks() {
      super(Material.material);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(immutableSetEntry, MovementProperty.OAK));
      this.processAction(ItemGroupManager.itemGroupManager10);
   }
}
