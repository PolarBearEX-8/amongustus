package net.minecraft.block;

import client.setting.CombatAlphaProperty;
import client.setting.CombatToggleProperty;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;

public class BlockRedSandstone extends Block {
   public static final ImmutableSetEntry immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry("type", CombatToggleProperty.class);

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(immutableSetEntry, CombatToggleProperty.resolveCombatToggleProperty(count));
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      for (CombatToggleProperty combatToggleProperty : CombatToggleProperty.values()) {
         list.add(new ItemStack(item, 1, combatToggleProperty.getCount()));
      }
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{immutableSetEntry});
   }

   @Override
   public int getMetaFromState(IBlockState blockState) {
      return ((CombatToggleProperty)blockState.getComparable(immutableSetEntry)).getCount();
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return ((CombatToggleProperty)blockState.getComparable(immutableSetEntry)).getCount();
   }

   public BlockRedSandstone() {
      super(Material.material4, CombatAlphaProperty.RED_SAND.getMapColor());
      this.setIBlockState(this.blockManager.get().resolveIBlockState(immutableSetEntry, CombatToggleProperty.DEFAULT));
      this.processAction(ItemGroupManager.itemGroupManager10);
   }
}
