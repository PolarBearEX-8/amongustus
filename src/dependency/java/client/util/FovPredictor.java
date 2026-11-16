package client.util;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class FovPredictor extends Material {
   @Override
   public boolean isActive2() {
      return true;
   }

   @Override
   public boolean isActiveAsFlag() {
      return false;
   }

   @Override
   public boolean canExecute2() {
      return false;
   }

   public FovPredictor(MapColor mapColor) {
      super(mapColor);
      this.getMaterial();
      this.getMaterial3();
   }
}
