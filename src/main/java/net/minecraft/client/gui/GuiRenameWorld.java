package net.minecraft.client.gui;

import java.io.IOException;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldInfo;
import org.lwjgl.input.Keyboard;

public class GuiRenameWorld extends GuiScreen {
   private GuiTextField nameEdit;
   private final String saveName;

   @Override
   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
   }

   @Override
   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      this.buttonList.clear();
      this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, I18n.format("selectWorld.renameButton", new Object[0])));
      this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12, I18n.format("gui.cancel", new Object[0])));
      String worldName = this.mc.getSaveLoader().getWorldInfo(this.saveName).getWorldName();
      this.nameEdit = new GuiTextField(2, this.fontRendererObj, this.width / 2 - 100, 60, 200, 20);
      this.nameEdit.setFocused(true);
      this.nameEdit.setText(worldName);
   }

   @Override
   public void updateScreen() {
      this.nameEdit.updateCursorCounter();
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      this.nameEdit.mouseClicked(mouseX, mouseY, mouseButton);
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.enabled) {
         if (button.id == 1) {
            GuiScreen parentScreen2 = this.parentScreen;
            this.mc.displayGuiScreen(parentScreen2);
         } else if (button.id == 0) {
            this.mc.getSaveLoader().renameWorld(this.saveName, this.nameEdit.getText().trim());
            GuiScreen parentScreen3 = this.parentScreen;
            this.mc.displayGuiScreen(parentScreen3);
         }
      }
   }

   public GuiRenameWorld(GuiScreen screen, String str) {
      super(screen);
      this.saveName = str;
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      drawCenteredString(this.fontRendererObj, I18n.format("selectWorld.renameTitle", new Object[0]), this.width / 2, 20, 16777215);
      this.drawString(this.fontRendererObj, I18n.format("selectWorld.enterName", new Object[0]), this.width / 2 - 100, 47, 10526880);
      this.nameEdit.drawTextBox();
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      this.nameEdit.textboxKeyTyped(typedChar, keyCode);
      ((GuiButton)this.buttonList.get(0)).enabled = this.nameEdit.getText().trim().length() > 0;
      if (keyCode == 28 || keyCode == 156) {
         this.actionPerformed((GuiButton)this.buttonList.get(0));
      }
   }

}
