package net.minecraft.block;

import client.setting.BlockSolidType;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroupManager;

public class BlockGlass extends ExtendedBlockAdapter {
   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.CUTOUT;
   }

   @Override
   public boolean isActive() {
      return true;
   }

   @Override
   public int processAction(Random random) {
      return 0;
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   public BlockGlass(Material material, boolean flag) {
      super(material, flag);
      this.processAction(ItemGroupManager.itemGroupManager10);
   }
}
