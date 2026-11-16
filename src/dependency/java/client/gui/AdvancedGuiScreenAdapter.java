package client.gui;

import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class AdvancedGuiScreenAdapter extends GuiScreen {
   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.renderElement3();
      applyFontRenderer4(this.fontRenderer, "Out of memory!", this.width / 2, this.height / 4 - 60 + 20, 16777215);
      this.applyFontRenderer(this.fontRenderer, "Minecraft has run out of memory.", this.width / 2 - 140, this.height / 4 - 60 + 60 + 0, 10526880);
      this.applyFontRenderer(
         this.fontRenderer, "This could be caused by a bug in the game or by the", this.width / 2 - 140, this.height / 4 - 60 + 60 + 18, 10526880
      );
      this.applyFontRenderer(
         this.fontRenderer, "Java Virtual Machine not being allocated enough", this.width / 2 - 140, this.height / 4 - 60 + 60 + 27, 10526880
      );
      this.applyFontRenderer(this.fontRenderer, "memory.", this.width / 2 - 140, this.height / 4 - 60 + 60 + 36, 10526880);
      this.applyFontRenderer(
         this.fontRenderer, "To prevent level corruption, the current game has quit.", this.width / 2 - 140, this.height / 4 - 60 + 60 + 54, 10526880
      );
      this.applyFontRenderer(
         this.fontRenderer, "We've tried to free up enough memory to let you go back to", this.width / 2 - 140, this.height / 4 - 60 + 60 + 63, 10526880
      );
      this.applyFontRenderer(
         this.fontRenderer, "the main menu and back to playing, but this may not have worked.", this.width / 2 - 140, this.height / 4 - 60 + 60 + 72, 10526880
      );
      this.applyFontRenderer(
         this.fontRenderer, "Please restart the game if you see this message again.", this.width / 2 - 140, this.height / 4 - 60 + 60 + 81, 10526880
      );
      super.applyIndex(count, count2, factor);
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
   }

   @Override
   public void updateState2() {
      this.list.clear();
      this.list.add(new ValueSliderControl(0, this.width / 2 - 155, this.height / 4 + 120 + 12, StatCollector.getText("gui.toTitle", new Object[0])));
      this.list.add(new ValueSliderControl(1, this.width / 2 - 155 + 160, this.height / 4 + 120 + 12, StatCollector.getText("menu.quit", new Object[0])));
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 0) {
         Minecraft mc = this.mc;
         GuiMainMenu guiMainMenu = new GuiMainMenu();
         mc.displayGuiScreen(guiMainMenu);
      } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
         this.mc.updateState14();
      }
   }
}
