package client.gui;

import client.util.InterpolationValidator;
import net.minecraft.client.Minecraft;
import net.minecraft.util.StatCollector;

public class GuiYesAuxiliaryTexturesGuiIc extends AuxiliaryTexturesGuiIconsPngManagerAdapter {
   public boolean enabled5;
   public String labelText2;
   public final InterpolationValidator interpolationValidator;

   public void setEnabled(boolean enabled) {
      this.enabled5 = enabled;
      this.labelText = this.getText();
      this.interpolationValidator.applyIndex(this.count2, enabled);
   }

   @Override
   public boolean matchesMinecraft(Minecraft mc, int count, int count2) {
      if (super.matchesMinecraft(mc, count, count2)) {
         this.enabled5 = !this.enabled5;
         this.labelText = this.getText();
         this.interpolationValidator.applyIndex(this.count2, this.enabled5);
         return true;
      } else {
         return false;
      }
   }

   public GuiYesAuxiliaryTexturesGuiIc(InterpolationValidator interpolationValidator2, int count, int count2, int count3, String text, boolean flag) {
      super(count, count2, count3, 150, 20, "");
      this.labelText2 = text;
      this.enabled5 = flag;
      this.labelText = this.getText();
      this.interpolationValidator = interpolationValidator2;
   }

   private String getText() {
      return StatCollector.getText(this.labelText2, new Object[0])
         + ": "
         + (this.enabled5 ? StatCollector.getText("gui.yes", new Object[0]) : StatCollector.getText("gui.no", new Object[0]));
   }
}
