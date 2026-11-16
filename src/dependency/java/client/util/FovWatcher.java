package client.util;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class FovWatcher extends Material {
   @Override
   public boolean isActive() {
      return false;
   }

   @Override
   public boolean isActiveAsFlag() {
      return false;
   }

   @Override
   public boolean canExecute2() {
      return false;
   }

   public FovWatcher(MapColor mapColor) {
      super(mapColor);
      this.getMaterialAsMaterial();
   }
}
