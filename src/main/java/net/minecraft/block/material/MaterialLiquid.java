package net.minecraft.block.material;

public class MaterialLiquid extends Material {
   @Override
   public boolean isSolid() {
      return false;
   }

   @Override
   public boolean isLiquid() {
      return true;
   }

   public MaterialLiquid(MapColor mapColor) {
      super(mapColor);
      this.setReplaceable();
      this.setNoPushMobility();
   }

   @Override
   public boolean blocksMovement() {
      return false;
   }
}
