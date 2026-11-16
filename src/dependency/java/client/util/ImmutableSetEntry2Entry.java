package client.util;

import client.setting.BlockNoneType;
import client.setting.CombatFactorProperty;
import client.setting.CombatProfileProperty;
import java.util.List;
import net.minecraft.block.BlockManager;
import net.minecraft.block.SecondaryBlockAdapter;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;

public abstract class ImmutableSetEntry2Entry extends SecondaryBlockAdapter {
   public ImmutableSetEntry immutableSetEntry;

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(this.getIProperty(), CombatFactorProperty.resolveCombatFactorProperty(this.getYELLOW(), count));
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      for (CombatFactorProperty combatFactorProperty : CombatFactorProperty.ordinal(this.getYELLOW())) {
         list.add(new ItemStack(item, 1, combatFactorProperty.getCount()));
      }
   }

   @Override
   public BlockNoneType processAction9() {
      return BlockNoneType.XZ;
   }

   @Override
   public int getMetaFromState(IBlockState blockState) {
      return ((CombatFactorProperty)blockState.getComparable(this.getIProperty())).getCount();
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{this.getIProperty()});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return ((CombatFactorProperty)blockState.getComparable(this.getIProperty())).getCount();
   }

   public IProperty getIProperty() {
      if (this.immutableSetEntry == null) {
         this.immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry("type", CombatFactorProperty.class, new ManagedPredicateAdapter(this));
      }

      return this.immutableSetEntry;
   }

   public abstract CombatProfileProperty getYELLOW();

   public ImmutableSetEntry2Entry() {
      this.setIBlockState(
         this.blockManager
            .get()
            .resolveIBlockState(
               this.getIProperty(), this.getYELLOW() == CombatProfileProperty.RED ? CombatFactorProperty.POPPY : CombatFactorProperty.DANDELION
            )
      );
   }
}
