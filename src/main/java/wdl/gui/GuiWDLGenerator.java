package wdl.gui;

import wdl.WDL;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCustomizeWorldScreen;
import net.minecraft.client.gui.GuiFlatPresets;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;

public class GuiWDLGenerator extends GuiScreen {
   private String title;
   private GuiScreen parent;
   private GuiTextField seedField;
   private GuiButton generatorBtn;
   private GuiButton generateStructuresBtn;
   private GuiButton settingsPageBtn;
   private String seedText;

   @Override
   public void actionPerformed(GuiButton button) {
      if (button.enabled) {
         if (button.id == 1) {
            this.cycleGenerator();
         } else if (button.id == 2) {
            this.cycleGenerateStructures();
         } else if (button.id == 3) {
            if (WDL.worldProps.getProperty("MapGenerator", "").equals("flat")) {
               this.mc.displayGuiScreen(new GuiFlatPresets(new GuiWDLGeneratorFlatProxy(this)));
            } else if (WDL.worldProps.getProperty("MapGenerator", "").equals("custom")) {
               this.mc.displayGuiScreen(new GuiCustomizeWorldScreen(new GuiWDLGeneratorCustomizeProxy(this), WDL.worldProps.getProperty("GeneratorOptions", "")));
            }
         } else if (button.id == 100) {
            GuiScreen parent2 = this.parent;
            this.mc.displayGuiScreen(parent2);
         }
      }
   }

   private void cycleGenerator() {
      String property = WDL.worldProps.getProperty("MapGenerator");
      if (property.equals("void")) {
         WDL.worldProps.setProperty("MapGenerator", "default");
         WDL.worldProps.setProperty("GeneratorName", "default");
         WDL.worldProps.setProperty("GeneratorVersion", "1");
         WDL.worldProps.setProperty("GeneratorOptions", "");
      } else if (property.equals("default")) {
         WDL.worldProps.setProperty("MapGenerator", "flat");
         WDL.worldProps.setProperty("GeneratorName", "flat");
         WDL.worldProps.setProperty("GeneratorVersion", "0");
         WDL.worldProps.setProperty("GeneratorOptions", "");
      } else if (property.equals("flat")) {
         WDL.worldProps.setProperty("MapGenerator", "largeBiomes");
         WDL.worldProps.setProperty("GeneratorName", "largeBiomes");
         WDL.worldProps.setProperty("GeneratorVersion", "0");
         WDL.worldProps.setProperty("GeneratorOptions", "");
      } else if (property.equals("largeBiomes")) {
         WDL.worldProps.setProperty("MapGenerator", "amplified");
         WDL.worldProps.setProperty("GeneratorName", "amplified");
         WDL.worldProps.setProperty("GeneratorVersion", "0");
         WDL.worldProps.setProperty("GeneratorOptions", "");
      } else if (property.equals("amplified")) {
         WDL.worldProps.setProperty("MapGenerator", "custom");
         WDL.worldProps.setProperty("GeneratorName", "custom");
         WDL.worldProps.setProperty("GeneratorVersion", "0");
         WDL.worldProps.setProperty("GeneratorOptions", "");
      } else if (property.equals("custom")) {
         WDL.worldProps.setProperty("MapGenerator", "legacy");
         WDL.worldProps.setProperty("GeneratorName", "default_1_1");
         WDL.worldProps.setProperty("GeneratorVersion", "0");
         WDL.worldProps.setProperty("GeneratorOptions", "");
      } else {
         WDL.worldProps.setProperty("MapGenerator", "void");
         WDL.worldProps.setProperty("GeneratorName", "flat");
         WDL.worldProps.setProperty("GeneratorVersion", "0");
         WDL.worldProps.setProperty("GeneratorOptions", ";0");
      }

      this.generatorBtn.displayString = this.getGeneratorText();
      this.updateSettingsButtonVisibility();
   }

   public GuiWDLGenerator(GuiScreen screen) {
      this.parent = screen;
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      this.seedField.mouseClicked(mouseX, mouseY, mouseButton);
   }

   private String getGenerateStructuresText() {
      return I18n.format("wdl.gui.generator.generateStructures." + WDL.worldProps.getProperty("MapFeatures"), new Object[0]);
   }

   private void updateSettingsButtonVisibility() {
      if (WDL.worldProps.getProperty("MapGenerator", "").equals("flat")) {
         this.settingsPageBtn.visible = true;
         this.settingsPageBtn.displayString = I18n.format("wdl.gui.generator.flatSettings", new Object[0]);
      } else if (WDL.worldProps.getProperty("MapGenerator", "").equals("custom")) {
         this.settingsPageBtn.visible = true;
         this.settingsPageBtn.displayString = I18n.format("wdl.gui.generator.customSettings", new Object[0]);
      } else {
         this.settingsPageBtn.visible = false;
      }
   }

   @Override
   public void onGuiClosed() {
      WDL.worldProps.setProperty("RandomSeed", this.seedField.getText());
      WDL.saveProps();
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      Utils.drawListBackground(23, 32, 0, 0, this.height, this.width);
      drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 8, 16777215);
      this.drawString(this.fontRendererObj, this.seedText, this.width / 2 - 100, this.height / 4 - 10, 16777215);
      this.seedField.drawTextBox();
      super.drawScreen(mouseX, mouseY, partialTicks);
      String str = null;
      if (Utils.isMouseOverTextBox(mouseX, mouseY, this.seedField)) {
         str = I18n.format("wdl.gui.generator.seed.description", new Object[0]);
      } else if (this.generatorBtn.isMouseOver()) {
         str = I18n.format("wdl.gui.generator.generator.description", new Object[0]);
      } else if (this.generateStructuresBtn.isMouseOver()) {
         str = I18n.format("wdl.gui.generator.generateStructures.description", new Object[0]);
      }

      Utils.drawGuiInfoBox(str, this.width, this.height, 48);
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      super.keyTyped(typedChar, keyCode);
      this.seedField.textboxKeyTyped(typedChar, keyCode);
   }

   private String getGeneratorText() {
      return I18n.format("wdl.gui.generator.generator." + WDL.worldProps.getProperty("MapGenerator"), new Object[0]);
   }

   private void cycleGenerateStructures() {
      if (WDL.worldProps.getProperty("MapFeatures").equals("true")) {
         WDL.worldProps.setProperty("MapFeatures", "false");
      } else {
         WDL.worldProps.setProperty("MapFeatures", "true");
      }

      this.generateStructuresBtn.displayString = this.getGenerateStructuresText();
   }

   @Override
   public void updateScreen() {
      this.seedField.updateCursorCounter();
      super.updateScreen();
   }

   @Override
   public void initGui() {
      this.seedText = I18n.format("wdl.gui.generator.seed", new Object[0]);
      int stringWidth = this.fontRendererObj.getStringWidth(this.seedText + " ");
      this.buttonList.clear();
      this.title = I18n.format("wdl.gui.generator.title", new Object[]{WDL.baseFolderName.replace('@', ':')});
      int n = this.height / 4 - 15;
      this.seedField = new GuiTextField(40, this.fontRendererObj, this.width / 2 - (100 - stringWidth), n, 200 - stringWidth, 18);
      this.seedField.setText(WDL.worldProps.getProperty("RandomSeed"));
      n += 22;
      this.generatorBtn = new GuiButton(1, this.width / 2 - 100, n, this.getGeneratorText());
      this.buttonList.add(this.generatorBtn);
      n += 22;
      this.generateStructuresBtn = new GuiButton(2, this.width / 2 - 100, n, this.getGenerateStructuresText());
      this.buttonList.add(this.generateStructuresBtn);
      n += 22;
      this.settingsPageBtn = new GuiButton(3, this.width / 2 - 100, n, "");
      this.updateSettingsButtonVisibility();
      this.buttonList.add(this.settingsPageBtn);
      this.buttonList.add(new GuiButton(100, this.width / 2 - 100, this.height - 29, I18n.format("gui.done", new Object[0])));
   }

}
