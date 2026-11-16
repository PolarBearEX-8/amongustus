package net.minecraft.tileentity;

public class TileEntityDropper extends TileEntityTrap {
   @Override
   public String getText() {
      return this.processAction() ? this.labelText : "container.dropper";
   }

   @Override
   public String getLabelText2() {
      return "minecraft:dropper";
   }
}
