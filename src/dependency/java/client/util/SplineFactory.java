package client.util;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.setting.WorldSettingsGameType;
import java.io.IOException;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;

public class SplineFactory extends GuiScreen {
   public final GuiScreen guiScreen2;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter3;
   public String labelText = "survival";
   public boolean enabled6;

   private void updateState() {
      this.auxiliaryTexturesGuiIconsPngManagerAdapter3.labelText = StatCollector.getText("selectWorld.gameMode", new Object[0])
         + " "
         + StatCollector.getText("selectWorld.gameMode." + this.labelText, new Object[0]);
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2.labelText = StatCollector.getText("selectWorld.allowCommands", new Object[0]) + " ";
      if (this.enabled6) {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter2.labelText = this.auxiliaryTexturesGuiIconsPngManagerAdapter2.labelText
            + StatCollector.getText("options.on", new Object[0]);
      } else {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter2.labelText = this.auxiliaryTexturesGuiIconsPngManagerAdapter2.labelText
            + StatCollector.getText("options.off", new Object[0]);
      }
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 102) {
         GuiScreen guiScreen = this.guiScreen2;
         this.mc.displayGuiScreen(guiScreen);
      } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 104) {
         if (this.labelText.equals("spectator")) {
            this.labelText = "creative";
         } else if (this.labelText.equals("creative")) {
            this.labelText = "adventure";
         } else if (this.labelText.equals("adventure")) {
            this.labelText = "survival";
         } else {
            this.labelText = "spectator";
         }

         this.updateState();
      } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 103) {
         this.enabled6 = !this.enabled6;
         this.updateState();
      } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 101) {
         GuiScreen screen = null;
         this.mc.displayGuiScreen(screen);
         String text = this.mc.getIntegratedServer().resolveText(WorldSettingsGameType.resolveWorldSettingsGameType(this.labelText), this.enabled6);
         Object entry;
         if (text != null) {
            entry = new BaseManagerStyleIChatComponentAdapter("commands.publish.started", new Object[]{text});
         } else {
            entry = new ChatComponentText("commands.publish.failed");
         }

         this.mc.ingameGUI.getInternalLoggerEntry().applyIChatComponent((IChatComponent)entry);
      }
   }

   public SplineFactory(GuiScreen screen) {
      this.guiScreen2 = screen;
   }

   @Override
   public void updateState2() {
      this.list.clear();
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               101, this.width / 2 - 155, this.height - 28, 150, 20, StatCollector.getText("lanServer.start", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               102, this.width / 2 + 5, this.height - 28, 150, 20, StatCollector.getText("gui.cancel", new Object[0])
            )
         );
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter3 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               104, this.width / 2 - 155, 100, 150, 20, StatCollector.getText("selectWorld.gameMode", new Object[0])
            )
         );
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               103, this.width / 2 + 5, 100, 150, 20, StatCollector.getText("selectWorld.allowCommands", new Object[0])
            )
         );
      this.updateState();
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.renderElement3();
      applyFontRenderer4(this.fontRenderer, StatCollector.getText("lanServer.title", new Object[0]), this.width / 2, 50, 16777215);
      applyFontRenderer4(this.fontRenderer, StatCollector.getText("lanServer.otherPlayers", new Object[0]), this.width / 2, 82, 16777215);
      super.applyIndex(count, count2, factor);
   }
}
