package net.optifine.gui;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.optifine.config.Config;

public class GuiMessage extends GuiScreen {
   private GuiScreen parentScreen2;
   private String messageLine1;
   private String messageLine2;
   private final List listLines2 = Lists.newArrayList();
   public String confirmButtonText;
   private int ticksUntilEnable;

   @Override
   public void updateScreen() {
      super.updateScreen();
      if (--this.ticksUntilEnable == 0) {
         for (GuiButton button : (Iterable<GuiButton>)(this.buttonList)) {
            button.enabled = true;
         }
      }
   }

   public GuiMessage(GuiScreen screen, String str, String str2) {
      this.parentScreen2 = screen;
      this.messageLine1 = str;
      this.messageLine2 = str2;
      this.confirmButtonText = I18n.format("gui.done", new Object[0]);
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      Config.getMc().displayGuiScreen(this.parentScreen2);
   }

   @Override
   public void initGui() {
      this.buttonList.add(new GuiOptionButton(0, this.width / 2 - 74, this.height / 6 + 96, this.confirmButtonText));
      this.listLines2.clear();
      this.listLines2.addAll(this.fontRendererObj.listFormattedStringToWidth(this.messageLine2, this.width - 50));
   }

   public void setButtonDelay(int n) {
      this.ticksUntilEnable = n;

      for (GuiButton button : (Iterable<GuiButton>)(this.buttonList)) {
         button.enabled = false;
      }
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      drawCenteredString(this.fontRendererObj, this.messageLine1, this.width / 2, 70, 16777215);
      int n = 90;

      for (Object obj : this.listLines2) {
         drawCenteredString(this.fontRendererObj, (String)obj, this.width / 2, n, 16777215);
         n += this.fontRendererObj.FONT_HEIGHT;
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
   }
}
