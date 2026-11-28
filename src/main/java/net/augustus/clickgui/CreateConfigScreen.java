package net.augustus.clickgui;

import net.augustus.Augustus;
import net.augustus.config.ModuleConfigStorage;
import net.augustus.utils.ChatUtil;
import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;

public class CreateConfigScreen extends GuiScreen {
   private GuiScreen parentScreen;
   private boolean unprotected = true;
   private GuiTextField configNameField;

   private void createConfig() {
      if (!this.configNameField.getText().equals("")) {
         ChatUtil.sendChat("Config created: §c" + this.configNameField.getText());
         ModuleConfigStorage moduleConfigStorage = Augustus.getInstance().getConfigManager().moduleConfigStorage;
         String text = this.configNameField.getText();
         boolean unprotected2 = this.unprotected;
         moduleConfigStorage.saveConfig(text, "configs", unprotected2);
         this.configNameField.setText("");
      }
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      super.drawScreen(mouseX, mouseY, partialTicks);
      this.configNameField.drawTextBox();
   }

   @Override
   public void mouseReleased(int mouseX, int mouseY, int state) {
      super.mouseReleased(mouseX, mouseY, state);
   }

   @Override
   public void updateScreen() {
      super.updateScreen();
      this.configNameField.updateCursorCounter();
   }

   public CreateConfigScreen(GuiScreen screen) {
      this.parentScreen = screen;
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      this.configNameField.textboxKeyTyped(typedChar, keyCode);
      if (keyCode == 1) {
         GuiScreen parentScreen22 = this.parentScreen;
         this.mc.displayGuiScreen(parentScreen22);
      }
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      this.configNameField.mouseClicked(mouseX, mouseY, mouseButton);
   }

   @Override
   public void initGui() {
      super.initGui();
      ScaledResolution sr = new ScaledResolution(this.mc);
      this.configNameField = new GuiTextField(1, this.fontRendererObj, sr.getScaledWidth() / 2 - 75, sr.getScaledHeight() / 2 - 25, 150, 20);
      this.configNameField.setMaxStringLength(1377);
      this.buttonList.add(new GuiButton(2, sr.getScaledWidth() / 2 - 50, sr.getScaledHeight() / 2 + 5, 100, 20, "Create"));
      this.buttonList
         .add(
            new GuiButton(3, sr.getScaledWidth() / 2 - 50, sr.getScaledHeight() / 2 + 35, 100, 20, this.unprotected ? "Protected: §cFalse" : "Protected: §aTrue")
         );
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      super.actionPerformed(button);
      if (button.id == 2) {
         this.createConfig();
         GuiScreen parentScreen22 = this.parentScreen;
         this.mc.displayGuiScreen(parentScreen22);
      }

      if (button.id == 3) {
         this.unprotected = !this.unprotected;
         button.displayString = this.unprotected ? "Protected: §cFalse" : "Protected: §aTrue";
      }
   }

}
