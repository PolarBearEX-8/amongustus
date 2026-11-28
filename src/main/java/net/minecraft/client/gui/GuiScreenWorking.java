package net.minecraft.client.gui;

import net.minecraft.util.IProgressUpdate;
import net.optifine.CustomLoadingScreen;
import net.optifine.CustomLoadingScreens;

public class GuiScreenWorking extends GuiScreen implements IProgressUpdate {
   private String title = "";
   private String stage = "";
   private int progress;
   private boolean doneWorking;
   private CustomLoadingScreen customLoadingScreen = CustomLoadingScreens.getCustomLoadingScreen();

   public void setDoneWorking() {
      this.doneWorking = true;
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      if (this.doneWorking) {
         if (!this.mc.isConnectedToRealms()) {
            GuiScreen screen = (GuiScreen)null;
            this.mc.displayGuiScreen(screen);
         }
      } else {
         if (this.customLoadingScreen != null && this.mc.theWorld == null) {
            this.customLoadingScreen.drawBackground(this.width, this.height);
         } else {
            this.drawDefaultBackground();
         }

         if (this.progress > 0) {
            drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 70, 16777215);
            drawCenteredString(this.fontRendererObj, this.stage + " " + this.progress + "%", this.width / 2, 90, 16777215);
         }

         drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 70, 16777215);
         drawCenteredString(this.fontRendererObj, this.stage + " " + this.progress + "%", this.width / 2, 90, 16777215);
         super.drawScreen(mouseX, mouseY, partialTicks);
      }
   }

   public void setLoadingProgress(int n) {
      this.progress = n;
   }

   public void displayLoadingString(String str) {
      this.stage = str;
      this.setLoadingProgress(0);
   }

   public void displaySavingString(String str) {
      this.resetProgressAndMessage(str);
   }

   public void resetProgressAndMessage(String str) {
      this.title = str;
      this.displayLoadingString("Working...");
   }

}
