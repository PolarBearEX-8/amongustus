package net.minecraft.block.material;

public class MaterialLiquid extends Material {
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

   public MaterialLiquid(MapColor mapColor) {
      super(mapColor);
   }
}
