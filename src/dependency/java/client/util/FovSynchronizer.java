package client.util;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public final class FovSynchronizer extends Material {
   @Override
   public boolean isActiveAsFlag() {
      return false;
   }

   public FovSynchronizer(MapColor mapColor) {
      super(mapColor);
   }
}
