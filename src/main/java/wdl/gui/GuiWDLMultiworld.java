package wdl.gui;

import java.io.IOException;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiWDLMultiworld extends GuiScreen {
   private final MultiworldCallback callback;
   private GuiButton multiworldEnabledBtn;
   private boolean enableMultiworld = false;
   private int infoBoxWidth;
   private int infoBoxHeight;
   private int infoBoxX;
   private int infoBoxY;
   private List infoBoxLines;

   @Override
   public void updateScreen() {
      super.updateScreen();
   }

   private String getMultiworldEnabledText() {
      return I18n.format("wdl.gui.multiworld." + this.enableMultiworld, new Object[0]);
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      Utils.drawBorder(32, 32, 0, 0, this.height, this.width);
      drawCenteredString(this.fontRendererObj, I18n.format("wdl.gui.multiworld.title", new Object[0]), this.width / 2, 8, 16777215);
      drawRect(this.infoBoxX, this.infoBoxY, this.infoBoxX + this.infoBoxWidth, this.infoBoxY + this.infoBoxHeight, -1342177280);
      int n = this.infoBoxX + 10;
      int n2 = this.infoBoxY + 10;

      for (String str : (Iterable<String>)(this.infoBoxLines)) {
         this.drawString(this.fontRendererObj, str, n, n2, 16777215);
         n2 += this.fontRendererObj.FONT_HEIGHT;
      }

      drawRect(this.multiworldEnabledBtn.xPosition - 2, this.multiworldEnabledBtn.yPosition - 2, this.multiworldEnabledBtn.xPosition + this.multiworldEnabledBtn.getButtonWidth() + 2, this.multiworldEnabledBtn.yPosition + 20 + 2, -65536);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   @Override
   public void actionPerformed(GuiButton button) {
      if (button.id == 1) {
         this.toggleMultiworldEnabled();
      } else if (button.id == 100) {
         this.callback.onCancel();
      } else if (button.id == 101) {
         this.callback.onSelect(this.enableMultiworld);
      }
   }

   @Override
   public void initGui() {
      this.buttonList.clear();
      String str = I18n.format("wdl.gui.multiworld.descirption.requiredWhen", new Object[0])
         + "\n\n"
         + I18n.format("wdl.gui.multiworld.descirption.whatIs", new Object[0]);
      this.infoBoxWidth = 320;
      this.infoBoxLines = Utils.wordWrap(str, this.infoBoxWidth - 20);
      this.infoBoxHeight = this.fontRendererObj.FONT_HEIGHT * (this.infoBoxLines.size() + 1) + 40;
      this.infoBoxX = this.width / 2 - this.infoBoxWidth / 2;
      this.infoBoxY = this.height / 2 - this.infoBoxHeight / 2;
      this.multiworldEnabledBtn = new GuiButton(1, this.width / 2 - 100, this.infoBoxY + this.infoBoxHeight - 30, this.getMultiworldEnabledText());
      this.buttonList.add(this.multiworldEnabledBtn);
      this.buttonList.add(new GuiButton(100, this.width / 2 - 155, this.height - 29, 150, 20, I18n.format("gui.cancel", new Object[0])));
      this.buttonList.add(new GuiButton(101, this.width / 2 + 5, this.height - 29, 150, 20, I18n.format("gui.done", new Object[0])));
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      super.keyTyped(typedChar, keyCode);
   }

   private void toggleMultiworldEnabled() {
      if (this.enableMultiworld) {
         this.enableMultiworld = false;
      } else {
         this.enableMultiworld = true;
      }

      this.multiworldEnabledBtn.displayString = this.getMultiworldEnabledText();
   }

   public GuiWDLMultiworld(MultiworldCallback multiworldCallback) {
      this.callback = multiworldCallback;
   }
}
