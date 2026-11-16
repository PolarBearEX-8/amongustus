package client.util;

import client.setting.CoreOptionType;
import client.setting.MovementModeProperty;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockManager;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExtraBlockAdapter;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class ImmutableSetHolder extends ExtraBlockAdapter {
   public static final InternalImmutableSetEntry internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("seamless");
   public static final ImmutableSetEntry immutableSetEntry3 = ImmutableSetEntry.resolveImmutableSetEntry("variant", CoreOptionType.class);

   @Override
   public String resolveText(int count) {
      return super.getTextAsText() + "." + CoreOptionType.resolveCoreOptionType(count).getLabelText();
   }

   @Override
   public MapColor processAction3(IBlockState blockState) {
      return ((CoreOptionType)blockState.getComparable(immutableSetEntry3)).getMapColor();
   }

   @Override
   public IBlockState processAction(int count) {
      IBlockState blockState = this.getDefaultState().resolveIBlockState(immutableSetEntry3, CoreOptionType.resolveCoreOptionType(count & 7));
      IBlockState blockState2;
      if (this.isActive5()) {
         blockState2 = blockState.resolveIBlockState(internalImmutableSetEntry, (count & 8) != 0);
      } else {
         blockState2 = blockState.resolveIBlockState(immutableSetEntry, (count & 8) == 0 ? MovementModeProperty.BOTTOM : MovementModeProperty.TOP);
      }

      return blockState2;
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return Item.processAction2(Blocks.extraBlockAdapter4);
   }

   @Override
   public Object resolveTarget(ItemStack stack) {
      return CoreOptionType.resolveCoreOptionType(stack.getIndexOffset() & 7);
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      if (item != Item.processAction2(Blocks.extraBlockAdapter3)) {
         for (CoreOptionType coreOptionType : CoreOptionType.values()) {
            list.add(new ItemStack(item, 1, coreOptionType.getCount()));
         }
      }
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Item.processAction2(Blocks.extraBlockAdapter4);
   }

   @Override
   public BlockManager processAction11() {
      return this.isActive5()
         ? new BlockManager(this, new IProperty[]{internalImmutableSetEntry, immutableSetEntry3})
         : new BlockManager(this, new IProperty[]{immutableSetEntry, immutableSetEntry3});
   }

   @Override
   public int getMetaFromState(IBlockState blockState) {
      return ((CoreOptionType)blockState.getComparable(immutableSetEntry3)).getCount();
   }

   @Override
   public IProperty getIProperty() {
      return immutableSetEntry3;
   }

   @Override
   public int getIndex(IBlockState blockState) {
      int count = 0 | ((CoreOptionType)blockState.getComparable(immutableSetEntry3)).getCount();
      if (this.isActive5()) {
         if ((Boolean)blockState.getComparable(internalImmutableSetEntry)) {
            count |= 8;
         }
      } else if (blockState.getComparable(immutableSetEntry) == MovementModeProperty.TOP) {
         count |= 8;
      }

      return count;
   }

   @Override
   public String getText() {
      return I18n.getString2(this.getTextAsText() + ".red_sandstone.name");
   }

   public ImmutableSetHolder() {
      super(Material.material4);
      IBlockState blockState = this.blockManager.get();
      IBlockState blockState2;
      if (this.isActive5()) {
         blockState2 = blockState.resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE);
      } else {
         blockState2 = blockState.resolveIBlockState(immutableSetEntry, MovementModeProperty.BOTTOM);
      }

      this.setIBlockState(blockState2.resolveIBlockState(immutableSetEntry3, CoreOptionType.RED_SANDSTONE));
      this.processAction(ItemGroupManager.itemGroupManager10);
   }
}
