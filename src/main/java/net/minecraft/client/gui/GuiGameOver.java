package net.minecraft.client.gui;

import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Mouse;

public class GuiGameOver extends GuiScreen implements GuiYesNoCallback {
   private int enableButtonsTimer;
   private boolean enableButtons = false;

   @Override
   public void confirmClicked(boolean flag, int n) {
      if (flag) {
         this.mc.theWorld.sendQuittingDisconnectingPacket();
         WorldClient world = (WorldClient)null;
         this.mc.loadWorld(world);
         this.mc.displayGuiScreen(new GuiMainMenu());
      } else {
         this.mc.thePlayer.respawnPlayer();
         GuiScreen screen = (GuiScreen)null;
         this.mc.displayGuiScreen(screen);
      }
   }

   @Override
   public void updateScreen() {
      super.updateScreen();
      this.enableButtonsTimer++;
      if (this.enableButtonsTimer >= 20) {
         for (GuiButton button : (Iterable<GuiButton>)(this.buttonList)) {
            button.enabled = true;
         }
      }
   }

   @Override
   public boolean doesGuiPauseGame() {
      return false;
   }

   @Override
   public void initGui() {
      this.buttonList.clear();
      if (this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled()) {
         if (this.mc.isIntegratedServerRunning()) {
            this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96, I18n.format("deathScreen.deleteWorld", new Object[0])));
         } else {
            this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96, I18n.format("deathScreen.leaveServer", new Object[0])));
         }
      } else {
         this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 72, I18n.format("deathScreen.respawn", new Object[0])));
         this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96, I18n.format("deathScreen.titleScreen", new Object[0])));
         if (this.mc.getSession() == null) {
            ((GuiButton)this.buttonList.get(1)).enabled = false;
         }
      }

      for (GuiButton button : (Iterable<GuiButton>)(this.buttonList)) {
         button.enabled = false;
      }

      Mouse.setGrabbed(false);
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      switch (button.id) {
         case 0:
            this.mc.thePlayer.respawnPlayer();
            GuiScreen screen = (GuiScreen)null;
            this.mc.displayGuiScreen(screen);
            break;
         case 1:
            if (this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled()) {
               this.mc.displayGuiScreen(new GuiMainMenu());
            } else {
               GuiYesNo guiYesNo = new GuiYesNo(
                  this,
                  I18n.format("deathScreen.quit.confirm", new Object[0]),
                  "",
                  I18n.format("deathScreen.titleScreen", new Object[0]),
                  I18n.format("deathScreen.respawn", new Object[0]),
                  0
               );
               this.mc.displayGuiScreen(guiYesNo);
               guiYesNo.setButtonDelay(20);
            }
      }
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawGradientRect(0, 0, this.width, this.height, 1615855616, -1602211792);
      GlStateManager.pushMatrix();
      GlStateManager.scale(2.0F, 2.0F, 2.0F);
      boolean isHardcoreModeEnabled = this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled();
      String str = isHardcoreModeEnabled ? I18n.format("deathScreen.title.hardcore", new Object[0]) : I18n.format("deathScreen.title", new Object[0]);
      drawCenteredString(this.fontRendererObj, str, this.width / 2 / 2, 30, 16777215);
      GlStateManager.popMatrix();
      if (isHardcoreModeEnabled) {
         drawCenteredString(this.fontRendererObj, I18n.format("deathScreen.hardcoreInfo", new Object[0]), this.width / 2, 144, 16777215);
      }

      drawCenteredString(
         this.fontRendererObj,
         I18n.format("deathScreen.score", new Object[0]) + ": " + EnumChatFormatting.YELLOW + this.mc.thePlayer.getScore(),
         this.width / 2,
         100,
         16777215
      );
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

}
