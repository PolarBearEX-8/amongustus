package client.util;

import client.setting.MovementModeProperty;
import client.setting.MovementProperty;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockManager;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExtraBlockAdapter;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class AuxiliaryImmutableSetEntry2Entry extends ExtraBlockAdapter {
   public static final ImmutableSetEntry immutableSetEntry3 = ImmutableSetEntry.resolveImmutableSetEntry("variant", MovementProperty.class);

   @Override
   public String resolveText(int count) {
      return super.getTextAsText() + "." + MovementProperty.resolveMovementProperty(count).getLabelText();
   }

   @Override
   public MapColor processAction3(IBlockState blockState) {
      return ((MovementProperty)blockState.getComparable(immutableSetEntry3)).getMapColor();
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return Item.processAction2(Blocks.extraBlockAdapter2);
   }

   @Override
   public IBlockState processAction(int count) {
      IBlockState blockState = this.getDefaultState().resolveIBlockState(immutableSetEntry3, MovementProperty.resolveMovementProperty(count & 7));
      if (!this.isActive5()) {
         blockState = blockState.resolveIBlockState(immutableSetEntry, (count & 8) == 0 ? MovementModeProperty.BOTTOM : MovementModeProperty.TOP);
      }

      return blockState;
   }

   @Override
   public Object resolveTarget(ItemStack stack) {
      return MovementProperty.resolveMovementProperty(stack.getIndexOffset() & 7);
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      if (item != Item.processAction2(Blocks.extraBlockAdapter)) {
         for (MovementProperty movementProperty : MovementProperty.values()) {
            list.add(new ItemStack(item, 1, movementProperty.getCount()));
         }
      }
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Item.processAction2(Blocks.extraBlockAdapter2);
   }

   @Override
   public int getMetaFromState(IBlockState blockState) {
      return ((MovementProperty)blockState.getComparable(immutableSetEntry3)).getCount();
   }

   @Override
   public IProperty getIProperty() {
      return immutableSetEntry3;
   }

   @Override
   public BlockManager processAction11() {
      return this.isActive5()
         ? new BlockManager(this, new IProperty[]{immutableSetEntry3})
         : new BlockManager(this, new IProperty[]{immutableSetEntry, immutableSetEntry3});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      int count = 0 | ((MovementProperty)blockState.getComparable(immutableSetEntry3)).getCount();
      if (!this.isActive5() && blockState.getComparable(immutableSetEntry) == MovementModeProperty.TOP) {
         count |= 8;
      }

      return count;
   }

   public AuxiliaryImmutableSetEntry2Entry() {
      super(Material.material);
      IBlockState blockState = this.blockManager.get();
      if (!this.isActive5()) {
         blockState = blockState.resolveIBlockState(immutableSetEntry, MovementModeProperty.BOTTOM);
      }

      this.setIBlockState(blockState.resolveIBlockState(immutableSetEntry3, MovementProperty.OAK));
      this.processAction(ItemGroupManager.itemGroupManager10);
   }
}
