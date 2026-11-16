package net.minecraft.block;

import client.setting.CombatAlphaProperty;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;

public class BlockSand extends CustomBlockAdapter {
   public static final ImmutableSetEntry immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry("variant", CombatAlphaProperty.class);

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(immutableSetEntry, CombatAlphaProperty.resolveCombatAlphaProperty(count));
   }

   @Override
   public MapColor processAction3(IBlockState blockState) {
      return ((CombatAlphaProperty)blockState.getComparable(immutableSetEntry)).getMapColor();
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      for (CombatAlphaProperty combatAlphaProperty : CombatAlphaProperty.values()) {
         list.add(new ItemStack(item, 1, combatAlphaProperty.getCount()));
      }
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{immutableSetEntry});
   }

   @Override
   public int getMetaFromState(IBlockState blockState) {
      return ((CombatAlphaProperty)blockState.getComparable(immutableSetEntry)).getCount();
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return ((CombatAlphaProperty)blockState.getComparable(immutableSetEntry)).getCount();
   }

   public BlockSand() {
      this.setIBlockState(this.blockManager.get().resolveIBlockState(immutableSetEntry, CombatAlphaProperty.SAND));
   }
}
