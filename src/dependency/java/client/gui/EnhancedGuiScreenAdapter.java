package client.gui;

import client.setting.WorldSettingsGameType;
import client.util.AdvancedLongEntry;
import client.util.CharArrayHolder;
import client.util.InfoManager;
import client.util.RandomSeedManager;
import java.io.IOException;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.CreateWorldCustomizeGuiScreen;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.OptionsCustomizeTitleGuiScreen;
import net.minecraft.util.StatCollector;
import net.minecraft.world.storage.ISaveFormat;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Keyboard;

public class EnhancedGuiScreenAdapter extends GuiScreen {
   public InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter;
   public InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter2;
   public String labelText2;
   public String labelText3 = "survival";
   public String labelText4;
   public boolean enabled6 = true;
   public boolean enabled7;
   public boolean enabled8;
   public boolean enabled9;
   public boolean enabled10;
   public boolean enabled11;
   public boolean enabled12;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter3;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter4;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter5;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter6;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter7;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter8;
   public String labelText5;
   public String labelText6;
   public String labelText7;
   public String labelText8;
   public int count2;
   public String labelText = "";
   public static final String[] stringArray = new String[]{
      "CON",
      "COM",
      "PRN",
      "AUX",
      "CLOCK$",
      "NUL",
      "COM1",
      "COM2",
      "COM3",
      "COM4",
      "COM5",
      "COM6",
      "COM7",
      "COM8",
      "COM9",
      "LPT1",
      "LPT2",
      "LPT3",
      "LPT4",
      "LPT5",
      "LPT6",
      "LPT7",
      "LPT8",
      "LPT9"
   };

   public EnhancedGuiScreenAdapter(GuiScreen screen) {
      super(screen);
      this.labelText7 = "";
      this.labelText8 = StatCollector.getText("selectWorld.newWorld", new Object[0]);
   }

   private void renderElement5() {
      this.labelText2 = this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().trim();

      for (char character : CharArrayHolder.charCodeArray) {
         this.labelText2 = this.labelText2.replace(character, '_');
      }

      if (StringUtils.isEmpty(this.labelText2)) {
         this.labelText2 = "World";
      }

      this.labelText2 = resolveText(this.mc.getISaveFormat(), this.labelText2);
   }

   public static String resolveText(ISaveFormat iSaveFormat, String text) {
      StringBuilder builder = new StringBuilder(text.replaceAll("[\\./\"]", "_"));

      for (String text2 : stringArray) {
         if (builder.toString().equalsIgnoreCase(text2)) {
            builder = new StringBuilder("_" + builder + "_");
         }
      }

      String text3 = builder.toString();

      while (iSaveFormat.resolveRandomSeedManager(text3) != null) {
         text3 = text3 + "-";
      }

      return text3;
   }

   @Override
   public void updateState2() {
      Keyboard.enableRepeatEvents(true);
      this.list.clear();
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               0, this.width / 2 - 155, this.height - 28, 150, 20, StatCollector.getText("selectWorld.create", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(1, this.width / 2 + 5, this.height - 28, 150, 20, StatCollector.getText("gui.cancel", new Object[0]))
         );
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               2, this.width / 2 - 75, 115, 150, 20, StatCollector.getText("selectWorld.gameMode", new Object[0])
            )
         );
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter3 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               3, this.width / 2 - 75, 187, 150, 20, StatCollector.getText("selectWorld.moreWorldOptions", new Object[0])
            )
         );
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter4 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               4, this.width / 2 - 155, 100, 150, 20, StatCollector.getText("selectWorld.mapFeatures", new Object[0])
            )
         );
      this.auxiliaryTexturesGuiIconsPngManagerAdapter4.enabled2 = false;
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter5 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               7, this.width / 2 + 5, 151, 150, 20, StatCollector.getText("selectWorld.bonusItems", new Object[0])
            )
         );
      this.auxiliaryTexturesGuiIconsPngManagerAdapter5.enabled2 = false;
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter6 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               5, this.width / 2 + 5, 100, 150, 20, StatCollector.getText("selectWorld.mapType", new Object[0])
            )
         );
      this.auxiliaryTexturesGuiIconsPngManagerAdapter6.enabled2 = false;
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter7 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               6, this.width / 2 - 155, 151, 150, 20, StatCollector.getText("selectWorld.allowCommands", new Object[0])
            )
         );
      this.auxiliaryTexturesGuiIconsPngManagerAdapter7.enabled2 = false;
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter8 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               8, this.width / 2 + 5, 120, 150, 20, StatCollector.getText("selectWorld.customizeType", new Object[0])
            )
         );
      this.auxiliaryTexturesGuiIconsPngManagerAdapter8.enabled2 = false;
      this.internalTexturesGuiIconsPngManagerAdapter = new InternalTexturesGuiIconsPngManagerAdapter(9, this.fontRenderer, this.width / 2 - 100, 60, 200, 20);
      this.internalTexturesGuiIconsPngManagerAdapter.setEnabledByFlag(true);
      this.internalTexturesGuiIconsPngManagerAdapter.applyText(this.labelText8);
      this.internalTexturesGuiIconsPngManagerAdapter2 = new InternalTexturesGuiIconsPngManagerAdapter(10, this.fontRenderer, this.width / 2 - 100, 60, 200, 20);
      this.internalTexturesGuiIconsPngManagerAdapter2.applyText(this.labelText7);
      this.setEnabled(this.enabled12);
      this.renderElement5();
      this.renderElement();
   }

   @Override
   public void enableRepeatEvents() {
      Keyboard.enableRepeatEvents(false);
   }

   private void renderElement() {
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2.labelText = StatCollector.getText("selectWorld.gameMode", new Object[0])
         + ": "
         + StatCollector.getText("selectWorld.gameMode." + this.labelText3, new Object[0]);
      this.labelText5 = StatCollector.getText("selectWorld.gameMode." + this.labelText3 + ".line1", new Object[0]);
      this.labelText6 = StatCollector.getText("selectWorld.gameMode." + this.labelText3 + ".line2", new Object[0]);
      this.auxiliaryTexturesGuiIconsPngManagerAdapter4.labelText = StatCollector.getText("selectWorld.mapFeatures", new Object[0]) + " ";
      if (this.enabled6) {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter4.labelText = this.auxiliaryTexturesGuiIconsPngManagerAdapter4.labelText
            + StatCollector.getText("options.on", new Object[0]);
      } else {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter4.labelText = this.auxiliaryTexturesGuiIconsPngManagerAdapter4.labelText
            + StatCollector.getText("options.off", new Object[0]);
      }

      this.auxiliaryTexturesGuiIconsPngManagerAdapter5.labelText = StatCollector.getText("selectWorld.bonusItems", new Object[0]) + " ";
      if (this.enabled9 && !this.enabled10) {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter5.labelText = this.auxiliaryTexturesGuiIconsPngManagerAdapter5.labelText
            + StatCollector.getText("options.on", new Object[0]);
      } else {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter5.labelText = this.auxiliaryTexturesGuiIconsPngManagerAdapter5.labelText
            + StatCollector.getText("options.off", new Object[0]);
      }

      this.auxiliaryTexturesGuiIconsPngManagerAdapter6.labelText = StatCollector.getText("selectWorld.mapType", new Object[0])
         + " "
         + StatCollector.getText(InfoManager.infoManagerArray[this.count2].getText2(), new Object[0]);
      this.auxiliaryTexturesGuiIconsPngManagerAdapter7.labelText = StatCollector.getText("selectWorld.allowCommands", new Object[0]) + " ";
      if (this.enabled7 && !this.enabled10) {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter7.labelText = this.auxiliaryTexturesGuiIconsPngManagerAdapter7.labelText
            + StatCollector.getText("options.on", new Object[0]);
      } else {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter7.labelText = this.auxiliaryTexturesGuiIconsPngManagerAdapter7.labelText
            + StatCollector.getText("options.off", new Object[0]);
      }
   }

   @Override
   public void applyIndex(int count, int count3, float factor) {
      this.renderElement3();
      applyFontRenderer4(this.fontRenderer, StatCollector.getText("selectWorld.create", new Object[0]), this.width / 2, 20, -1);
      if (this.enabled12) {
         this.applyFontRenderer(this.fontRenderer, StatCollector.getText("selectWorld.enterSeed", new Object[0]), this.width / 2 - 100, 47, -6250336);
         this.applyFontRenderer(this.fontRenderer, StatCollector.getText("selectWorld.seedInfo", new Object[0]), this.width / 2 - 100, 85, -6250336);
         if (this.auxiliaryTexturesGuiIconsPngManagerAdapter4.enabled2) {
            this.applyFontRenderer(this.fontRenderer, StatCollector.getText("selectWorld.mapFeatures.info", new Object[0]), this.width / 2 - 150, 122, -6250336);
         }

         if (this.auxiliaryTexturesGuiIconsPngManagerAdapter7.enabled2) {
            this.applyFontRenderer(
               this.fontRenderer, StatCollector.getText("selectWorld.allowCommands.info", new Object[0]), this.width / 2 - 150, 172, -6250336
            );
         }

         this.internalTexturesGuiIconsPngManagerAdapter2.updateState();
         if (InfoManager.infoManagerArray[this.count2].isEnabled3()) {
            this.fontRenderer
               .applyText(
                  StatCollector.getText(InfoManager.infoManagerArray[this.count2].getTextAsText(), new Object[0]),
                  this.auxiliaryTexturesGuiIconsPngManagerAdapter6.count + 2,
                  this.auxiliaryTexturesGuiIconsPngManagerAdapter6.count3 + 22,
                  this.auxiliaryTexturesGuiIconsPngManagerAdapter6.getCount4(),
                  10526880
               );
         }
      } else {
         this.applyFontRenderer(this.fontRenderer, StatCollector.getText("selectWorld.enterName", new Object[0]), this.width / 2 - 100, 47, -6250336);
         this.applyFontRenderer(
            this.fontRenderer, StatCollector.getText("selectWorld.resultFolder", new Object[0]) + " " + this.labelText2, this.width / 2 - 100, 85, -6250336
         );
         this.internalTexturesGuiIconsPngManagerAdapter.updateState();
         this.applyFontRenderer(this.fontRenderer, this.labelText5, this.width / 2 - 100, 137, -6250336);
         this.applyFontRenderer(this.fontRenderer, this.labelText6, this.width / 2 - 100, 149, -6250336);
      }

      super.applyIndex(count, count3, factor);
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      if (this.internalTexturesGuiIconsPngManagerAdapter.isEnabled2() && !this.enabled12) {
         this.internalTexturesGuiIconsPngManagerAdapter.checkChar(character, count);
         this.labelText8 = this.internalTexturesGuiIconsPngManagerAdapter.getLabelText();
      } else if (this.internalTexturesGuiIconsPngManagerAdapter2.isEnabled2() && this.enabled12) {
         this.internalTexturesGuiIconsPngManagerAdapter2.checkChar(character, count);
         this.labelText7 = this.internalTexturesGuiIconsPngManagerAdapter2.getLabelText();
      }

      if (count == 28 || count == 156) {
         this.applyAuxiliaryTexturesGuiIconsP((AuxiliaryTexturesGuiIconsPngManagerAdapter)this.list.get(0));
      }

      ((AuxiliaryTexturesGuiIconsPngManagerAdapter)this.list.get(0)).enabled3 = this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().length() > 0;
      this.renderElement5();
      super.applyChar(character, count);
   }

   private void renderElement6() {
      this.setEnabled(!this.enabled12);
   }

   private void setEnabled(boolean enabled) {
      this.enabled12 = enabled;
      if (InfoManager.infoManagerArray[this.count2] == InfoManager.infoManager4) {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter2.enabled2 = !this.enabled12;
         this.auxiliaryTexturesGuiIconsPngManagerAdapter2.enabled3 = false;
         if (this.labelText4 == null) {
            this.labelText4 = this.labelText3;
         }

         this.labelText3 = "spectator";
         this.auxiliaryTexturesGuiIconsPngManagerAdapter4.enabled2 = false;
         this.auxiliaryTexturesGuiIconsPngManagerAdapter5.enabled2 = false;
         this.auxiliaryTexturesGuiIconsPngManagerAdapter6.enabled2 = this.enabled12;
         this.auxiliaryTexturesGuiIconsPngManagerAdapter7.enabled2 = false;
         this.auxiliaryTexturesGuiIconsPngManagerAdapter8.enabled2 = false;
      } else {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter2.enabled2 = !this.enabled12;
         this.auxiliaryTexturesGuiIconsPngManagerAdapter2.enabled3 = true;
         if (this.labelText4 != null) {
            this.labelText3 = this.labelText4;
            this.labelText4 = null;
         }

         this.auxiliaryTexturesGuiIconsPngManagerAdapter4.enabled2 = this.enabled12 && InfoManager.infoManagerArray[this.count2] != InfoManager.infoManager3;
         this.auxiliaryTexturesGuiIconsPngManagerAdapter5.enabled2 = this.enabled12;
         this.auxiliaryTexturesGuiIconsPngManagerAdapter6.enabled2 = this.enabled12;
         this.auxiliaryTexturesGuiIconsPngManagerAdapter7.enabled2 = this.enabled12;
         this.auxiliaryTexturesGuiIconsPngManagerAdapter8.enabled2 = this.enabled12
            && (InfoManager.infoManagerArray[this.count2] == InfoManager.infoManager2 || InfoManager.infoManagerArray[this.count2] == InfoManager.infoManager3);
      }

      this.renderElement();
      if (this.enabled12) {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter3.labelText = StatCollector.getText("gui.done", new Object[0]);
      } else {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter3.labelText = StatCollector.getText("selectWorld.moreWorldOptions", new Object[0]);
      }
   }

   @Override
   public void processAction(int count, int count3, int count4) throws IOException {
      super.processAction(count, count3, count4);
      if (this.enabled12) {
         this.internalTexturesGuiIconsPngManagerAdapter2.applyIndex(count, count3, count4);
      } else {
         this.internalTexturesGuiIconsPngManagerAdapter.applyIndex(count, count3, count4);
      }
   }

   private boolean isActive4() {
      InfoManager infoManager = InfoManager.infoManagerArray[this.count2];
      return infoManager != null && infoManager.isEnabled() ? (infoManager == InfoManager.infoManager4 ? isActiveAsFlag() : true) : false;
   }

   public void applyRandomSeedManager(RandomSeedManager randomSeedManager) {
      this.labelText8 = StatCollector.getText("selectWorld.newWorld.copyOf", new Object[]{randomSeedManager.getLabelText()});
      this.labelText7 = randomSeedManager.getTimestamp5() + "";
      this.count2 = randomSeedManager.getInfoManager().getCount2();
      this.labelText = randomSeedManager.getLabelText2();
      this.enabled6 = randomSeedManager.isEnabled7();
      this.enabled7 = randomSeedManager.isEnabled3();
      if (randomSeedManager.isEnabled2()) {
         this.labelText3 = "hardcore";
      } else if (randomSeedManager.getWorldSettingsGameType().canExecute2()) {
         this.labelText3 = "survival";
      } else if (randomSeedManager.getWorldSettingsGameType().canExecute()) {
         this.labelText3 = "creative";
      }
   }

   @Override
   public void updateState4() {
      this.internalTexturesGuiIconsPngManagerAdapter.renderElement();
      this.internalTexturesGuiIconsPngManagerAdapter2.renderElement();
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
            GuiScreen guiScreen = this.guiScreen;
            this.mc.displayGuiScreen(guiScreen);
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 0) {
            GuiScreen screen = null;
            this.mc.displayGuiScreen(screen);
            if (this.enabled11) {
               return;
            }

            this.enabled11 = true;
            long value = new Random().nextLong();
            String labelText9 = this.internalTexturesGuiIconsPngManagerAdapter2.getLabelText();
            if (!StringUtils.isEmpty(labelText9)) {
               try {
                  value = Long.parseLong(labelText9);
               } catch (NumberFormatException numberFormatException) {
                  value = (long)labelText9.hashCode();
               }
            }

            WorldSettingsGameType worldSettingsGameType = WorldSettingsGameType.resolveWorldSettingsGameType(this.labelText3);
            AdvancedLongEntry advancedLongEntry = new AdvancedLongEntry(value, worldSettingsGameType, this.enabled6, this.enabled10, InfoManager.infoManagerArray[this.count2]);
            advancedLongEntry.withLabelText(this.labelText);
            if (this.enabled9 && !this.enabled10) {
               advancedLongEntry.getAdvancedLongEntryAsAdvancedLongEntr();
            }

            if (this.enabled7 && !this.enabled10) {
               advancedLongEntry.getAdvancedLongEntry();
            }

            Minecraft mc = this.mc;
            String labelText10 = this.labelText2;
            String text = this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().trim();
            String text2 = labelText10;
            mc.applyText(text2, text, advancedLongEntry);
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 3) {
            this.renderElement6();
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 2) {
            if (this.labelText3.equals("survival")) {
               if (!this.enabled8) {
                  this.enabled7 = false;
               }

               this.enabled10 = false;
               this.labelText3 = "hardcore";
               this.enabled10 = true;
               this.auxiliaryTexturesGuiIconsPngManagerAdapter7.enabled3 = false;
               this.auxiliaryTexturesGuiIconsPngManagerAdapter5.enabled3 = false;
               this.renderElement();
            } else if (this.labelText3.equals("hardcore")) {
               if (!this.enabled8) {
                  this.enabled7 = true;
               }

               this.enabled10 = false;
               this.labelText3 = "creative";
               this.renderElement();
               this.enabled10 = false;
               this.auxiliaryTexturesGuiIconsPngManagerAdapter7.enabled3 = true;
               this.auxiliaryTexturesGuiIconsPngManagerAdapter5.enabled3 = true;
            } else {
               if (!this.enabled8) {
                  this.enabled7 = false;
               }

               this.labelText3 = "survival";
               this.renderElement();
               this.auxiliaryTexturesGuiIconsPngManagerAdapter7.enabled3 = true;
               this.auxiliaryTexturesGuiIconsPngManagerAdapter5.enabled3 = true;
               this.enabled10 = false;
            }

            this.renderElement();
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 4) {
            this.enabled6 = !this.enabled6;
            this.renderElement();
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 7) {
            this.enabled9 = !this.enabled9;
            this.renderElement();
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 5) {
            this.count2++;
            if (this.count2 >= InfoManager.infoManagerArray.length) {
               this.count2 = 0;
            }

            while (!this.isActive4()) {
               this.count2++;
               if (this.count2 >= InfoManager.infoManagerArray.length) {
                  this.count2 = 0;
               }
            }

            this.labelText = "";
            this.renderElement();
            this.setEnabled(this.enabled12);
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 6) {
            this.enabled8 = true;
            this.enabled7 = !this.enabled7;
            this.renderElement();
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 8) {
            if (InfoManager.infoManagerArray[this.count2] == InfoManager.infoManager2) {
               Minecraft mc2 = this.mc;
               CreateWorldCustomizeGuiScreen createWorldCustomizeGuiScreen = new CreateWorldCustomizeGuiScreen(this, this.labelText);
               mc2.displayGuiScreen(createWorldCustomizeGuiScreen);
            } else {
               Minecraft mc3 = this.mc;
               OptionsCustomizeTitleGuiScreen optionsCustomizeTitleGuiScreen = new OptionsCustomizeTitleGuiScreen(this, this.labelText);
               mc3.displayGuiScreen(optionsCustomizeTitleGuiScreen);
            }
         }
      }
   }
}
