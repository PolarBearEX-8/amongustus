package client.setting;

import net.minecraft.util.EnumFacing;

public class NamedModeOption implements NamedOption {
   public String labelText;
   public boolean enabled;

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   public NamedModeOption(EnumFacing enumFacing, boolean flag) {
      this.labelText = enumFacing.getText();
      this.enabled = flag;
   }
}
