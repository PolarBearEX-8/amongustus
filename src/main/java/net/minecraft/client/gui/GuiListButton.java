package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class GuiListButton extends GuiButton {
   private boolean value;
   private String localizationStr;
   private final GuiPageButtonList.GuiResponder guiResponder;

   public GuiListButton(GuiPageButtonList.GuiResponder guiResponder2, int n, int n2, int n3, String str, boolean flag) {
      super(n, n2, n3, 150, 20, "");
      this.localizationStr = str;
      this.value = flag;
      this.displayString = this.buildDisplayString();
      this.guiResponder = guiResponder2;
   }

   private String buildDisplayString() {
      return I18n.format(this.localizationStr, new Object[0])
         + ": "
         + (this.value ? I18n.format("gui.yes", new Object[0]) : I18n.format("gui.no", new Object[0]));
   }

   public void setValue(boolean flag) {
      this.value = flag;
      this.displayString = this.buildDisplayString();
      this.guiResponder.setEntryValue2(this.id, flag);
   }

   @Override
   public boolean mousePressed(Minecraft mc, int n, int n2) {
      if (super.mousePressed(mc, n, n2)) {
         this.value = !this.value;
         this.displayString = this.buildDisplayString();
         this.guiResponder.setEntryValue2(this.id, this.value);
         return true;
      } else {
         return false;
      }
   }
}
