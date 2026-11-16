package net.minecraft.block;

import client.setting.CombatTimeoutProperty;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;

public class BlockStonebrick extends Block {
   public static final ImmutableSetEntry immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry("variant", CombatTimeoutProperty.class);
   public static final int count = CombatTimeoutProperty.DEFAULT.getCount();
   public static final int count3 = CombatTimeoutProperty.MOSSY.getCount();
   public static final int count2 = CombatTimeoutProperty.CRACKED.getCount();
   public static final int count4 = CombatTimeoutProperty.CHISELED.getCount();

   @Override
   public IBlockState processAction(int count5) {
      return this.getDefaultState().resolveIBlockState(immutableSetEntry, CombatTimeoutProperty.resolveCombatTimeoutProperty(count5));
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      for (CombatTimeoutProperty combatTimeoutProperty : CombatTimeoutProperty.values()) {
         list.add(new ItemStack(item, 1, combatTimeoutProperty.getCount()));
      }
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{immutableSetEntry});
   }

   @Override
   public int getMetaFromState(IBlockState blockState) {
      return ((CombatTimeoutProperty)blockState.getComparable(immutableSetEntry)).getCount();
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return ((CombatTimeoutProperty)blockState.getComparable(immutableSetEntry)).getCount();
   }

   public BlockStonebrick() {
      super(Material.material4);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(immutableSetEntry, CombatTimeoutProperty.DEFAULT));
      this.processAction(ItemGroupManager.itemGroupManager10);
   }
}
