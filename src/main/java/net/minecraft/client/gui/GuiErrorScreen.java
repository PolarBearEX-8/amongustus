package net.minecraft.client.gui;

import java.io.IOException;
import net.minecraft.client.resources.I18n;

public class GuiErrorScreen extends GuiScreen {
   private String title;
   private String message;

   @Override
   public void initGui() {
      super.initGui();
      this.buttonList.add(new GuiButton(0, this.width / 2 - 100, 140, I18n.format("gui.cancel", new Object[0])));
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
   }

   public GuiErrorScreen(String str, String str2) {
      this.title = str;
      this.message = str2;
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawGradientRect(0, 0, this.width, this.height, -12574688, -11530224);
      drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 90, 16777215);
      drawCenteredString(this.fontRendererObj, this.message, this.width / 2, 110, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      GuiScreen screen = (GuiScreen)null;
      this.mc.displayGuiScreen(screen);
   }
}
