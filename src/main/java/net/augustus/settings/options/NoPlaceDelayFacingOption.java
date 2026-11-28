package net.augustus.settings.options;

import net.augustus.settings.SelectableOption;
import net.minecraft.util.EnumFacing;

public class NoPlaceDelayFacingOption implements SelectableOption {
   private String name;
   private boolean selected;

   public NoPlaceDelayFacingOption(EnumFacing facing, boolean flag) {
      this.name = facing.getName();
      this.selected = flag;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public boolean isSelected() {
      return this.selected;
   }

   @Override
   public void setSelected(boolean flag) {
      this.selected = flag;
   }
}
