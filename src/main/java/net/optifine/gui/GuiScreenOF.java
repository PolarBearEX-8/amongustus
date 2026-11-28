package net.optifine.gui;

import java.io.IOException;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiVideoSettings;

public class GuiScreenOF extends GuiScreen {
   public void actionPerformedRightClick(GuiButton button) throws IOException {
   }

   public static GuiButton getSelectedButton(int n, int n2, List list) {
      for (int i = 0; i < list.size(); i++) {
         GuiButton button = (GuiButton)list.get(i);
         if (button.visible) {
            int buttonWidth = GuiVideoSettings.getButtonWidth(button);
            int buttonHeight = GuiVideoSettings.getButtonHeight(button);
            if (n >= button.xPosition && n2 >= button.yPosition && n < button.xPosition + buttonWidth && n2 < button.yPosition + buttonHeight) {
               return button;
            }
         }
      }

      return null;
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      if (mouseButton == 1) {
         GuiButton selectedButton2 = getSelectedButton(mouseX, mouseY, this.buttonList);
         if (selectedButton2 != null && selectedButton2.enabled) {
            selectedButton2.playPressSound(this.mc.getSoundHandler());
            this.actionPerformedRightClick(selectedButton2);
         }
      }
   }
}
