package net.minecraft.block.material;

public class MaterialPortal extends Material {
   @Override
   public boolean blocksMovement() {
      return false;
   }

   public MaterialPortal(MapColor mapColor) {
      super(mapColor);
   }

   @Override
   public boolean isSolid() {
      return false;
   }

   @Override
   public boolean blocksLight() {
      return false;
   }
}
