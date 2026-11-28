package wdl.gui;

import wdl.WDL;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;

public class GuiWDLMultiworldSelect extends GuiTurningCameraBase {
   private final WorldSelectionCallback callback;
   private final String title;
   private GuiButton cancelBtn;
   private GuiButton acceptBtn;
   private GuiTextField newNameField;
   private GuiTextField searchField;
   private GuiButton newWorldButton;
   private boolean showNewWorldTextBox;
   private List linkedWorlds;
   private List linkedWorldsFiltered;
   private MultiworldInfo selectedMultiWorld;
   private int index;
   private GuiButton nextButton;
   private GuiButton prevButton;
   private int numWorldButtons;
   private String searchText;

   @Override
   public void initGui() {
      super.initGui();
      this.numWorldButtons = (this.width - 50) / 155;
      if (this.numWorldButtons < 1) {
         this.numWorldButtons = 1;
      }

      int n = (this.numWorldButtons * 155 + 45) / 2;
      int n2 = this.height - 49;
      this.cancelBtn = new GuiButton(-1, this.width / 2 - 155, this.height - 25, 150, 20, I18n.format("gui.cancel", new Object[0]));
      this.buttonList.add(this.cancelBtn);
      this.acceptBtn = new GuiButton(-2, this.width / 2 + 5, this.height - 25, 150, 20, I18n.format("wdl.gui.multiworldSelect.done", new Object[0]));
      this.acceptBtn.enabled = this.selectedMultiWorld != null;
      this.buttonList.add(this.acceptBtn);
      this.prevButton = new GuiButton(-4, this.width / 2 - n, n2, 20, 20, "<");
      this.buttonList.add(this.prevButton);

      for (int i = 0; i < this.numWorldButtons; i++) {
         this.buttonList.add(new MultiworldSelectButton(this, i, this.width / 2 - n + i * 155 + 25, n2, 150, 20));
      }

      this.nextButton = new GuiButton(-5, this.width / 2 - n + 25 + this.numWorldButtons * 155, n2, 20, 20, ">");
      this.buttonList.add(this.nextButton);
      this.newWorldButton = new GuiButton(-3, this.width / 2 - 155, 29, 150, 20, I18n.format("wdl.gui.multiworldSelect.newName", new Object[0]));
      this.buttonList.add(this.newWorldButton);
      this.newNameField = new GuiTextField(40, this.fontRendererObj, this.width / 2 - 155, 29, 150, 20);
      this.searchField = new GuiTextField(41, this.fontRendererObj, this.width / 2 + 5, 29, 150, 20);
      this.searchField.setText(this.searchText);
   }

   private void rebuildFilteredWorlds() {
      String str = this.searchText.toLowerCase();
      this.linkedWorldsFiltered.clear();

      for (MultiworldInfo multiworldInfo : (Iterable<MultiworldInfo>)(this.linkedWorlds)) {
         if (multiworldInfo.displayName.toLowerCase().contains(str)) {
            this.linkedWorldsFiltered.add(multiworldInfo);
         }
      }
   }

   public GuiWDLMultiworldSelect(String str, WorldSelectionCallback worldSelectionCallback) {
       super();
      this.index = 0;
      this.searchText = "";
      this.title = str;
      this.callback = worldSelectionCallback;
      String[] strings = WDL.baseProps.getProperty("LinkedWorlds").split("\\|");
      this.linkedWorlds = new ArrayList();

      for (String str2 : strings) {
         if (str2 != null && !str2.isEmpty()) {
            Properties properties = WDL.loadWorldProps(str2);
            if (properties.containsKey("WorldName")) {
               String property = properties.getProperty("WorldName", str2);
               this.linkedWorlds.add(new MultiworldInfo(str2, property));
            }
         }
      }

      this.linkedWorldsFiltered = new ArrayList();
      this.linkedWorldsFiltered.addAll(this.linkedWorlds);
   }

   public static List getLinkedWorldsFiltered(GuiWDLMultiworldSelect guiWDLMultiworldSelect) {
      return guiWDLMultiworldSelect.linkedWorldsFiltered;
   }

   @Override
   public void updateScreen() {
      this.newNameField.updateCursorCounter();
      this.searchField.updateCursorCounter();
      super.updateScreen();
   }

   public static MultiworldInfo getSelectedMultiWorld(GuiWDLMultiworldSelect guiWDLMultiworldSelect) {
      return guiWDLMultiworldSelect.selectedMultiWorld;
   }

   private void addMultiworld(String str) {
      String str2 = str;

      for (char ch : "\\/:*?\"<>|.".toCharArray()) {
         str2 = str2.replace(ch, '_');
      }

      Properties properties = new Properties(WDL.baseProps);
      properties.setProperty("WorldName", str);
      String property = WDL.baseProps.getProperty("LinkedWorlds");
      property = property + "|" + str2;
      WDL.baseProps.setProperty("LinkedWorlds", property);
      WDL.saveProps(str2, properties);
      this.linkedWorlds.add(new MultiworldInfo(str2, str));
      this.rebuildFilteredWorlds();
   }

   public static int getIndex(GuiWDLMultiworldSelect guiWDLMultiworldSelect) {
      return guiWDLMultiworldSelect.index;
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      if (keyCode == 1) {
         this.callback.onCancel();
      }

      super.keyTyped(typedChar, keyCode);
      if (this.showNewWorldTextBox) {
         this.newNameField.textboxKeyTyped(typedChar, keyCode);
         if (keyCode == 28) {
            String text = this.newNameField.getText();
            if (text != null && !text.isEmpty()) {
               this.addMultiworld(text);
               this.newNameField.setText("");
               this.showNewWorldTextBox = false;
            }
         }
      }

      if (this.searchField.textboxKeyTyped(typedChar, keyCode)) {
         this.searchText = this.searchField.getText();
         this.rebuildFilteredWorlds();
      }
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      if (this.showNewWorldTextBox) {
         this.newNameField.mouseClicked(mouseX, mouseY, mouseButton);
      }

      this.searchField.mouseClicked(mouseX, mouseY, mouseButton);
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      if (this.index >= this.linkedWorlds.size() - this.numWorldButtons) {
         this.index = this.linkedWorlds.size() - this.numWorldButtons;
         this.nextButton.enabled = false;
      } else {
         this.nextButton.enabled = true;
      }

      if (this.index <= 0) {
         this.index = 0;
         this.prevButton.enabled = false;
      } else {
         this.prevButton.enabled = true;
      }

      Utils.drawBorder(53, 53, 0, 0, this.height, this.width);
      drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 8, 16777215);
      drawCenteredString(this.fontRendererObj, I18n.format("wdl.gui.multiworldSelect.subtitle", new Object[0]), this.width / 2, 18, 16711680);
      if (this.showNewWorldTextBox) {
         this.newNameField.drawTextBox();
      }

      this.searchField.drawTextBox();
      if (this.searchField.getText().isEmpty() && !this.searchField.isFocused()) {
         this.drawString(this.fontRendererObj, I18n.format("wdl.gui.multiworldSelect.search", new Object[0]), this.searchField.xPosition + 4, this.searchField.yPosition + 6, 9474192);
      }

      this.newWorldButton.visible = !this.showNewWorldTextBox;
      super.drawScreen(mouseX, mouseY, partialTicks);
      this.drawMultiworldDescription();
   }

   private void drawMultiworldDescription() {
      if (this.selectedMultiWorld != null) {
         String str = "Info about " + this.selectedMultiWorld.displayName;
         List description = this.selectedMultiWorld.getDescription();
         int stringWidth = this.fontRendererObj.getStringWidth(str);

         for (String str2 : (Iterable<String>)(description)) {
            int stringWidth2 = this.fontRendererObj.getStringWidth(str2);
            if (stringWidth2 > stringWidth) {
               stringWidth = stringWidth2;
            }
         }

         drawRect(2, 61, 5 + stringWidth + 3, this.height - 61, Integer.MIN_VALUE);
         this.drawString(this.fontRendererObj, str, 5, 64, 16777215);
         int n = 64 + this.fontRendererObj.FONT_HEIGHT;

         for (String str3 : (Iterable<String>)(description)) {
            this.drawString(this.fontRendererObj, str3, 5, n, 16777215);
            n += this.fontRendererObj.FONT_HEIGHT;
         }
      }
   }

   @Override
   public void actionPerformed(GuiButton button) {
      if (button.enabled) {
         if (button instanceof MultiworldSelectButton) {
            this.selectedMultiWorld = ((MultiworldSelectButton)button).getWorldInfo();
            if (this.selectedMultiWorld != null) {
               this.acceptBtn.enabled = true;
            } else {
               this.acceptBtn.enabled = false;
            }
         } else if (button.id == -1) {
            this.callback.onCancel();
         } else if (button.id == -2) {
            this.callback.onWorldSelected(this.selectedMultiWorld.folderName);
         } else if (button.id == -3) {
            this.showNewWorldTextBox = true;
         } else if (button.id == -4) {
            this.index--;
         } else if (button.id == -5) {
            this.index++;
         }
      }
   }
}
