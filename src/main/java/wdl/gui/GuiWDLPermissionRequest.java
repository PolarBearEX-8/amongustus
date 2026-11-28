package wdl.gui;

import wdl.WDLPluginChannels;
import java.io.IOException;
import java.util.Map.Entry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;

public class GuiWDLPermissionRequest extends GuiScreen {
   private static final int TOP_MARGIN = 61;
   private static final int BOTTOM_MARGIN = 32;
   private TextList list;
   private final GuiScreen parent;
   private GuiTextField requestField;
   private GuiButton submitButton;

   @Override
   public void updateScreen() {
      this.requestField.updateCursorCounter();
      super.updateScreen();
   }

   @Override
   public void mouseReleased(int mouseX, int mouseY, int state) {
      if (!this.list.mouseReleased(mouseX, mouseY, state)) {
         super.mouseReleased(mouseX, mouseY, state);
      }
   }

   public GuiWDLPermissionRequest(GuiScreen screen) {
      this.parent = screen;
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.id == 1) {
         WDLPluginChannels.sendRequests();
         button.displayString = "Submitted!";
      }

      if (button.id == 100) {
         GuiScreen parent2 = this.parent;
         this.mc.displayGuiScreen(parent2);
      }

      if (button.id == 200) {
         this.mc.displayGuiScreen(new GuiWDLPermissions(this.parent));
      }

      if (button.id == 201) {
      }

      if (button.id == 202) {
         this.mc.displayGuiScreen(new GuiWDLChunkOverrides(this.parent));
      }
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      super.keyTyped(typedChar, keyCode);
      this.requestField.textboxKeyTyped(typedChar, keyCode);
      if (this.requestField.isFocused()) {
         String text = this.requestField.getText();
         boolean flag = false;
         if (text.contains("=")) {
            String[] strings = text.split("=", 2);
            if (strings.length == 2) {
               String str = strings[0];
               String str2 = strings[1];
               flag = WDLPluginChannels.isValidRequest(str, str2);
               if (flag && keyCode == 28) {
                  this.requestField.setText("");
                  flag = false;
                  WDLPluginChannels.addRequest(str, str2);
                  this.list.addLine("Requesting '" + str + "' to be '" + str2 + "'.");
                  this.submitButton.enabled = true;
               }
            }
         }

         this.requestField.setTextColor(flag ? 4251712 : 14696512);
      }
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      this.requestField.mouseClicked(mouseX, mouseY, mouseButton);
      this.list.mouseClicked(mouseX, mouseY, mouseButton);
      super.mouseClicked(mouseX, mouseY, mouseButton);
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      if (this.list != null) {
         this.list.drawScreen(mouseX, mouseY, partialTicks);
         this.requestField.drawTextBox();
         drawCenteredString(this.fontRendererObj, "Permission request", this.width / 2, 8, 16777215);
         super.drawScreen(mouseX, mouseY, partialTicks);
      }
   }

   @Override
   public void initGui() {
      this.list = new TextList(this.mc, this.width, this.height, 61, 32);
      this.list.addLine("§c§lThis is a work in progress.");
      this.list.addLine("You can request permissions in this GUI, although it currently requires manually specifying the names.");
      this.list.addBlankLine();
      this.list.addLine("Boolean fields: " + WDLPluginChannels.BOOLEAN_REQUEST_FIELDS);
      this.list.addLine("Integer fields: " + WDLPluginChannels.INTEGER_REQUEST_FIELDS);
      this.list.addBlankLine();

      for (Entry entry : (Iterable<Entry>)(WDLPluginChannels.getRequests().entrySet())) {
         this.list.addLine("Requesting '" + (String)entry.getKey() + "' to be '" + (String)entry.getValue() + "'.");
      }

      this.requestField = new GuiTextField(0, this.fontRendererObj, this.width / 2 - 155, 18, 150, 20);
      this.submitButton = new GuiButton(1, this.width / 2 + 5, 18, 150, 20, "Submit request");
      this.submitButton.enabled = !WDLPluginChannels.getRequests().isEmpty();
      this.buttonList.add(this.submitButton);
      this.buttonList.add(new GuiButton(100, this.width / 2 - 100, this.height - 29, I18n.format("gui.done", new Object[0])));
      this.buttonList.add(new GuiButton(200, this.width / 2 - 155, 39, 100, 20, I18n.format("wdl.gui.permissions.current", new Object[0])));
      this.buttonList.add(new GuiButton(201, this.width / 2 - 50, 39, 100, 20, I18n.format("wdl.gui.permissions.request", new Object[0])));
      this.buttonList.add(new GuiButton(202, this.width / 2 + 55, 39, 100, 20, I18n.format("wdl.gui.permissions.overrides", new Object[0])));
   }

   @Override
   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.list.handleMouseInput();
   }

}
