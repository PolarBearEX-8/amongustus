package net.minecraft.client.gui;

import java.io.IOException;
import net.minecraft.client.resources.I18n;

public class GuiConfirmOpenLink extends GuiYesNo {
   private final String openLinkWarning;
   private final String copyLinkButtonText;
   private final String linkText;
   private boolean showSecurityWarning = true;

   public void copyLinkToClipboard() {
      setClipboardString(this.linkText);
   }

   public GuiConfirmOpenLink(GuiYesNoCallback guiYesNoCallback, String str, int n, boolean flag) {
      super(guiYesNoCallback, I18n.format(flag ? "chat.link.confirmTrusted" : "chat.link.confirm", new Object[0]), str, n);
      this.confirmButtonText = I18n.format(flag ? "chat.link.open" : "gui.yes", new Object[0]);
      this.cancelButtonText = I18n.format(flag ? "gui.cancel" : "gui.no", new Object[0]);
      this.copyLinkButtonText = I18n.format("chat.copy", new Object[0]);
      this.openLinkWarning = I18n.format("chat.link.warning", new Object[0]);
      this.linkText = str;
   }

   public void disableSecurityWarning() {
      this.showSecurityWarning = false;
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      super.drawScreen(mouseX, mouseY, partialTicks);
      if (this.showSecurityWarning) {
         drawCenteredString(this.fontRendererObj, this.openLinkWarning, this.width / 2, 110, 16764108);
      }
   }

   @Override
   public void initGui() {
      super.initGui();
      this.buttonList.clear();
      this.buttonList.add(new GuiButton(0, this.width / 2 - 50 - 105, this.height / 6 + 96, 100, 20, this.confirmButtonText));
      this.buttonList.add(new GuiButton(2, this.width / 2 - 50, this.height / 6 + 96, 100, 20, this.copyLinkButtonText));
      this.buttonList.add(new GuiButton(1, this.width / 2 - 50 + 105, this.height / 6 + 96, 100, 20, this.cancelButtonText));
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.id == 2) {
         this.copyLinkToClipboard();
      }

      this.parentScreen.confirmClicked(button.id == 0, this.parentButtonClickedId);
   }
}
