package net.minecraft.client.gui;

import net.minecraft.world.WorldSettings;
import java.io.IOException;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class GuiShareToLan extends GuiScreen {
   private final GuiScreen lastScreen;
   private GuiButton allowCheatsButton;
   private GuiButton gameModeButton;
   private String gameMode = "survival";
   private boolean allowCheats;

   @Override
   public void initGui() {
      this.buttonList.clear();
      this.buttonList.add(new GuiButton(101, this.width / 2 - 155, this.height - 28, 150, 20, I18n.format("lanServer.start", new Object[0])));
      this.buttonList.add(new GuiButton(102, this.width / 2 + 5, this.height - 28, 150, 20, I18n.format("gui.cancel", new Object[0])));
      this.buttonList.add(this.gameModeButton = new GuiButton(104, this.width / 2 - 155, 100, 150, 20, I18n.format("selectWorld.gameMode", new Object[0])));
      this.buttonList.add(this.allowCheatsButton = new GuiButton(103, this.width / 2 + 5, 100, 150, 20, I18n.format("selectWorld.allowCommands", new Object[0])));
      this.updateDisplayNames();
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      drawCenteredString(this.fontRendererObj, I18n.format("lanServer.title", new Object[0]), this.width / 2, 50, 16777215);
      drawCenteredString(this.fontRendererObj, I18n.format("lanServer.otherPlayers", new Object[0]), this.width / 2, 82, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.id == 102) {
         GuiScreen lastScreen2 = this.lastScreen;
         this.mc.displayGuiScreen(lastScreen2);
      } else if (button.id == 104) {
         if (this.gameMode.equals("spectator")) {
            this.gameMode = "creative";
         } else if (this.gameMode.equals("creative")) {
            this.gameMode = "adventure";
         } else if (this.gameMode.equals("adventure")) {
            this.gameMode = "survival";
         } else {
            this.gameMode = "spectator";
         }

         this.updateDisplayNames();
      } else if (button.id == 103) {
         this.allowCheats = !this.allowCheats;
         this.updateDisplayNames();
      } else if (button.id == 101) {
         GuiScreen screen = (GuiScreen)null;
         this.mc.displayGuiScreen(screen);
         String str = this.mc.getIntegratedServer().shareToLAN(WorldSettings.GameType.getByName(this.gameMode), this.allowCheats);
         Object obj;
         if (str != null) {
            obj = new ChatComponentTranslation("commands.publish.started", new Object[]{str});
         } else {
            obj = new ChatComponentText("commands.publish.failed");
         }

         this.mc.ingameGUI.getChatGUI().printChatMessage((IChatComponent)obj);
      }
   }

   public GuiShareToLan(GuiScreen screen) {
      this.lastScreen = screen;
   }

   private void updateDisplayNames() {
      this.gameModeButton.displayString = I18n.format("selectWorld.gameMode", new Object[0]) + " " + I18n.format("selectWorld.gameMode." + this.gameMode, new Object[0]);
      this.allowCheatsButton.displayString = I18n.format("selectWorld.allowCommands", new Object[0]) + " ";
      if (this.allowCheats) {
         this.allowCheatsButton.displayString = this.allowCheatsButton.displayString + I18n.format("options.on", new Object[0]);
      } else {
         this.allowCheatsButton.displayString = this.allowCheatsButton.displayString + I18n.format("options.off", new Object[0]);
      }
   }

}
