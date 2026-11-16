package client.util;

import client.event.CompositeListener;
import net.minecraft.client.gui.GuiScreen;

public class GeometrySystem extends GuiScreen implements CompositeListener {
   public String labelText = "";
   public String labelText2 = "";
   public int count2;
   public boolean enabled6;
   public InterpolationChecker interpolationChecker = PngManager.getInterpolationChecker();

   @Override
   public void setCount2(int count) {
      this.count2 = count;
   }

   @Override
   public void applyText2(String text) {
      this.applyTextByText(text);
   }

   @Override
   public void applyIndex(int count, int count3, float factor) {
      if (this.enabled6) {
         if (!this.mc.isEnabled5()) {
            GuiScreen screen = null;
            this.mc.displayGuiScreen(screen);
         }
      } else {
         if (this.interpolationChecker != null && this.mc.theWorld == null) {
            this.interpolationChecker.applyIndex(this.width, this.height);
         } else {
            this.renderElement3();
         }

         if (this.count2 > 0) {
            applyFontRenderer4(this.fontRenderer, this.labelText, this.width / 2, 70, 16777215);
            applyFontRenderer4(this.fontRenderer, this.labelText2 + " " + this.count2 + "%", this.width / 2, 90, 16777215);
         }

         applyFontRenderer4(this.fontRenderer, this.labelText, this.width / 2, 70, 16777215);
         applyFontRenderer4(this.fontRenderer, this.labelText2 + " " + this.count2 + "%", this.width / 2, 90, 16777215);
         super.applyIndex(count, count3, factor);
      }
   }

   @Override
   public void applyText(String text) {
      this.labelText2 = text;
      this.setCount2(0);
   }

   @Override
   public void updateState() {
      this.enabled6 = true;
   }

   @Override
   public void applyTextByText(String text) {
      this.labelText = text;
      this.applyText("Working...");
   }
}
