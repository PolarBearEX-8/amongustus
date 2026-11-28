package net.minecraft.client.gui;

import net.minecraft.world.gen.ChunkProviderSettings;
import com.google.common.base.Predicate;
import com.google.common.primitives.Floats;
import java.io.IOException;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;

public class GuiCustomizeWorldScreen extends GuiScreen implements GuiSlider.FormatHelper, GuiPageButtonList.GuiResponder {
   private GuiCreateWorld parent;
   public String title = "Customize World Settings";
   public String subtitle = "Page 1 of 3";
   public String pageTitle = "Basic Settings";
   public String[] pageNames = new String[4];
   private GuiPageButtonList list;
   private GuiButton done;
   private GuiButton randomize;
   private GuiButton defaults;
   private GuiButton previousPage;
   private GuiButton nextPage;
   private GuiButton confirm;
   private GuiButton cancel;
   private GuiButton presets;
   private boolean settingsModified = false;
   private int confirmMode = 0;
   private boolean confirmDismissed = false;
   private Predicate numberFilter = new GuiCustomizeWorldScreen.FilterPredicate(this);
   private ChunkProviderSettings.Factory defaultSettings = new ChunkProviderSettings.Factory();
   private ChunkProviderSettings.Factory settings;
   private Random random = new Random();

   private void modifyTextFieldValue(float f) {
      Gui focusedControl = this.list.getFocusedControl();
      if (focusedControl instanceof GuiTextField) {
         float f2 = f;
         if (GuiScreen.isShiftKeyDown()) {
            f2 = f * 0.1F;
            if (GuiScreen.isCtrlKeyDown()) {
               f2 *= 0.1F;
            }
         } else if (GuiScreen.isCtrlKeyDown()) {
            f2 = f * 10.0F;
            if (GuiScreen.isAltKeyDown()) {
               f2 *= 10.0F;
            }
         }

         GuiTextField guiTextField = (GuiTextField)focusedControl;
         Float f3 = Floats.tryParse(guiTextField.getText());
         if (f3 != null) {
            f3 = f3 + f2;
            int id = guiTextField.getId();
            String formattedValue = this.getFormattedValue(guiTextField.getId(), f3);
            guiTextField.setText(formattedValue);
            this.setEntryValue(id, formattedValue);
         }
      }
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      super.keyTyped(typedChar, keyCode);
      if (this.confirmMode == 0) {
         switch (keyCode) {
            case 200:
               this.modifyTextFieldValue(1.0F);
               break;
            case 208:
               this.modifyTextFieldValue(-1.0F);
               break;
            default:
               this.list.onKeyPressed(typedChar, keyCode);
         }
      }
   }

   public void setEntryValue2(int n, boolean flag) {
      switch (n) {
         case 148:
            this.settings.useCaves = flag;
            break;
         case 149:
            this.settings.useDungeons = flag;
            break;
         case 150:
            this.settings.useStrongholds = flag;
            break;
         case 151:
            this.settings.useVillages = flag;
            break;
         case 152:
            this.settings.useMineShafts = flag;
            break;
         case 153:
            this.settings.useTemples = flag;
            break;
         case 154:
            this.settings.useRavines = flag;
            break;
         case 155:
            this.settings.useWaterLakes = flag;
            break;
         case 156:
            this.settings.useLavaLakes = flag;
            break;
         case 161:
            this.settings.useLavaOceans = flag;
            break;
         case 210:
            this.settings.useMonuments = flag;
      }

      if (!this.settings.equals(this.defaultSettings)) {
         this.setSettingsModified(true);
      }
   }

   private void setSettingsToDefaults() {
      this.settings.setDefaults();
      this.createPagedList();
      this.setSettingsModified(false);
   }

   public void loadValues(String str) {
      if (str != null && str.length() != 0) {
         this.settings = ChunkProviderSettings.Factory.jsonToFactory(str);
      } else {
         this.settings = new ChunkProviderSettings.Factory();
      }
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.list.drawScreen(mouseX, mouseY, partialTicks);
      drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 2, 16777215);
      drawCenteredString(this.fontRendererObj, this.subtitle, this.width / 2, 12, 16777215);
      drawCenteredString(this.fontRendererObj, this.pageTitle, this.width / 2, 22, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
      if (this.confirmMode != 0) {
         drawRect(0, 0, this.width, this.height, -2147483648);
         this.drawHorizontalLine(this.width / 2 - 91, this.width / 2 + 90, 99, -2039584);
         this.drawHorizontalLine(this.width / 2 - 91, this.width / 2 + 90, 185, -6250336);
         this.drawVerticalLine(this.width / 2 - 91, 99, 185, -2039584);
         this.drawVerticalLine(this.width / 2 + 90, 99, 185, -6250336);
         float f = 85.0F;
         float f2 = 180.0F;
         GlStateManager.disableLighting();
         GlStateManager.disableFog();
         Tessellator instance = Tessellator.getInstance();
         WorldRenderer worldRenderer = instance.getWorldRenderer();
         this.mc.getTextureManager().bindTexture(optionsBackground);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         float f3 = 32.0F;
         worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
         worldRenderer.pos((double)(this.width / 2 - 90), 185.0, 0.0).tex(0.0, 2.65625).color(64, 64, 64, 64).endVertex();
         worldRenderer.pos((double)(this.width / 2 + 90), 185.0, 0.0).tex(5.625, 2.65625).color(64, 64, 64, 64).endVertex();
         worldRenderer.pos((double)(this.width / 2 + 90), 100.0, 0.0).tex(5.625, 0.0).color(64, 64, 64, 64).endVertex();
         worldRenderer.pos((double)(this.width / 2 - 90), 100.0, 0.0).tex(0.0, 0.0).color(64, 64, 64, 64).endVertex();
         instance.draw();
         drawCenteredString(this.fontRendererObj, I18n.format("createWorld.customize.custom.confirmTitle", new Object[0]), this.width / 2, 105, 16777215);
         drawCenteredString(this.fontRendererObj, I18n.format("createWorld.customize.custom.confirm1", new Object[0]), this.width / 2, 125, 16777215);
         drawCenteredString(this.fontRendererObj, I18n.format("createWorld.customize.custom.confirm2", new Object[0]), this.width / 2, 135, 16777215);
         this.confirm.drawButton(this.mc, mouseX, mouseY);
         this.cancel.drawButton(this.mc, mouseX, mouseY);
      }
   }

   private void confirmAction() throws IOException {
      switch (this.confirmMode) {
         case 300:
            this.actionPerformed((GuiListButton)this.list.getComponent(300));
            break;
         case 304:
            this.setSettingsToDefaults();
      }

      this.confirmMode = 0;
      this.confirmDismissed = true;
      this.setConfirmationControls(false);
   }

   private String getFormattedValue(int n, float f) {
      switch (n) {
         case 100:
         case 101:
         case 102:
         case 103:
         case 104:
         case 107:
         case 108:
         case 110:
         case 111:
         case 132:
         case 133:
         case 134:
         case 135:
         case 136:
         case 139:
         case 140:
         case 142:
         case 143:
            return String.format("%5.3f", f);
         case 105:
         case 106:
         case 109:
         case 112:
         case 113:
         case 114:
         case 115:
         case 137:
         case 138:
         case 141:
         case 144:
         case 145:
         case 146:
         case 147:
            return String.format("%2.3f", f);
         case 116:
         case 117:
         case 118:
         case 119:
         case 120:
         case 121:
         case 122:
         case 123:
         case 124:
         case 125:
         case 126:
         case 127:
         case 128:
         case 129:
         case 130:
         case 131:
         case 148:
         case 149:
         case 150:
         case 151:
         case 152:
         case 153:
         case 154:
         case 155:
         case 156:
         case 157:
         case 158:
         case 159:
         case 160:
         case 161:
         default:
            return String.format("%d", (int)f);
         case 162:
            if (f < 0.0F) {
               return I18n.format("gui.all", new Object[0]);
            } else if ((int)f >= BiomeGenBase.hell.biomeID) {
               BiomeGenBase biomeGenBase = BiomeGenBase.getBiomeGenArray()[(int)f + 2];
               return biomeGenBase != null ? biomeGenBase.biomeName : "?";
            } else {
               BiomeGenBase biomeGenBase2 = BiomeGenBase.getBiomeGenArray()[(int)f];
               return biomeGenBase2 != null ? biomeGenBase2.biomeName : "?";
            }
      }
   }

   @Override
   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.list.handleMouseInput();
   }

   @Override
   public void mouseReleased(int mouseX, int mouseY, int state) {
      super.mouseReleased(mouseX, mouseY, state);
      if (this.confirmDismissed) {
         this.confirmDismissed = false;
      } else if (this.confirmMode == 0) {
         this.list.mouseReleased(mouseX, mouseY, state);
      }
   }

   public void setEntryValue(int n, String str) {
      float f = 0.0F;

      try {
         f = Float.parseFloat(str);
      } catch (NumberFormatException e) {
      }

      float f2 = 0.0F;
      switch (n) {
         case 132:
            f2 = this.settings.mainNoiseScaleX = MathHelper.clamp_float(f, 1.0F, 5000.0F);
            break;
         case 133:
            f2 = this.settings.mainNoiseScaleY = MathHelper.clamp_float(f, 1.0F, 5000.0F);
            break;
         case 134:
            f2 = this.settings.mainNoiseScaleZ = MathHelper.clamp_float(f, 1.0F, 5000.0F);
            break;
         case 135:
            f2 = this.settings.depthNoiseScaleX = MathHelper.clamp_float(f, 1.0F, 2000.0F);
            break;
         case 136:
            f2 = this.settings.depthNoiseScaleZ = MathHelper.clamp_float(f, 1.0F, 2000.0F);
            break;
         case 137:
            f2 = this.settings.depthNoiseScaleExponent = MathHelper.clamp_float(f, 0.01F, 20.0F);
            break;
         case 138:
            f2 = this.settings.baseSize = MathHelper.clamp_float(f, 1.0F, 25.0F);
            break;
         case 139:
            f2 = this.settings.coordinateScale = MathHelper.clamp_float(f, 1.0F, 6000.0F);
            break;
         case 140:
            f2 = this.settings.heightScale = MathHelper.clamp_float(f, 1.0F, 6000.0F);
            break;
         case 141:
            f2 = this.settings.stretchY = MathHelper.clamp_float(f, 0.01F, 50.0F);
            break;
         case 142:
            f2 = this.settings.upperLimitScale = MathHelper.clamp_float(f, 1.0F, 5000.0F);
            break;
         case 143:
            f2 = this.settings.lowerLimitScale = MathHelper.clamp_float(f, 1.0F, 5000.0F);
            break;
         case 144:
            f2 = this.settings.biomeDepthWeight = MathHelper.clamp_float(f, 1.0F, 20.0F);
            break;
         case 145:
            f2 = this.settings.biomeDepthOffset = MathHelper.clamp_float(f, 0.0F, 20.0F);
            break;
         case 146:
            f2 = this.settings.biomeScaleWeight = MathHelper.clamp_float(f, 1.0F, 20.0F);
            break;
         case 147:
            f2 = this.settings.biomeScaleOffset = MathHelper.clamp_float(f, 0.0F, 20.0F);
      }

      if (f2 != f && f != 0.0F) {
         ((GuiTextField)this.list.getComponent(n)).setText(this.getFormattedValue(n, f2));
      }

      ((GuiSlider)this.list.getComponent(n - 132 + 100)).setSliderValue(f2, false);
      if (!this.settings.equals(this.defaultSettings)) {
         this.setSettingsModified(true);
      }
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.enabled) {
         switch (button.id) {
            case 300:
               this.parent.chunkProviderSettingsJson = this.settings.toString();
               GuiCreateWorld parent2 = this.parent;
               this.mc.displayGuiScreen(parent2);
               break;
            case 301:
               for (int i = 0; i < this.list.getSize(); i++) {
                  GuiPageButtonList.GuiEntry listEntry = this.list.getListEntry(i);
                  Gui component1 = listEntry.getComponent1();
                  if (component1 instanceof GuiButton) {
                     GuiButton button2 = (GuiButton)component1;
                     if (button2 instanceof GuiSlider) {
                        float f = ((GuiSlider)button2).getSliderPosition() * (0.75F + this.random.nextFloat() * 0.5F) + (this.random.nextFloat() * 0.1F - 0.05F);
                        ((GuiSlider)button2).setSliderPosition(MathHelper.clamp_float(f, 0.0F, 1.0F));
                     } else if (button2 instanceof GuiListButton) {
                        ((GuiListButton)button2).setValue(this.random.nextBoolean());
                     }
                  }

                  Gui component2 = listEntry.getComponent2();
                  if (component2 instanceof GuiButton) {
                     GuiButton button3 = (GuiButton)component2;
                     if (button3 instanceof GuiSlider) {
                        float f2 = ((GuiSlider)button3).getSliderPosition() * (0.75F + this.random.nextFloat() * 0.5F) + (this.random.nextFloat() * 0.1F - 0.05F);
                        ((GuiSlider)button3).setSliderPosition(MathHelper.clamp_float(f2, 0.0F, 1.0F));
                     } else if (button3 instanceof GuiListButton) {
                        ((GuiListButton)button3).setValue(this.random.nextBoolean());
                     }
                  }
               }

               return;
            case 302:
               this.list.previousPage();
               this.updatePageControls();
               break;
            case 303:
               this.list.nextPage();
               this.updatePageControls();
               break;
            case 304:
               if (this.settingsModified) {
                  this.enterConfirmation(304);
               }
               break;
            case 305:
               this.mc.displayGuiScreen(new GuiScreenCustomizePresets(this));
               break;
            case 306:
               this.confirmAction();
               break;
            case 307:
               this.confirmMode = 0;
               this.confirmAction();
         }
      }
   }

   private void createPagedList() {
      GuiPageButtonList.GuiListEntry[] guiListEntrys = new GuiPageButtonList.GuiListEntry[]{
         new GuiPageButtonList.GuiSlideEntry(160, I18n.format("createWorld.customize.custom.seaLevel", new Object[0]), true, this, 1.0F, 255.0F, (float)this.settings.seaLevel),
         new GuiPageButtonList.GuiButtonEntry(148, I18n.format("createWorld.customize.custom.useCaves", new Object[0]), true, this.settings.useCaves),
         new GuiPageButtonList.GuiButtonEntry(150, I18n.format("createWorld.customize.custom.useStrongholds", new Object[0]), true, this.settings.useStrongholds),
         new GuiPageButtonList.GuiButtonEntry(151, I18n.format("createWorld.customize.custom.useVillages", new Object[0]), true, this.settings.useVillages),
         new GuiPageButtonList.GuiButtonEntry(152, I18n.format("createWorld.customize.custom.useMineShafts", new Object[0]), true, this.settings.useMineShafts),
         new GuiPageButtonList.GuiButtonEntry(153, I18n.format("createWorld.customize.custom.useTemples", new Object[0]), true, this.settings.useTemples),
         new GuiPageButtonList.GuiButtonEntry(210, I18n.format("createWorld.customize.custom.useMonuments", new Object[0]), true, this.settings.useMonuments),
         new GuiPageButtonList.GuiButtonEntry(154, I18n.format("createWorld.customize.custom.useRavines", new Object[0]), true, this.settings.useRavines),
         new GuiPageButtonList.GuiButtonEntry(149, I18n.format("createWorld.customize.custom.useDungeons", new Object[0]), true, this.settings.useDungeons),
         new GuiPageButtonList.GuiSlideEntry(
            157, I18n.format("createWorld.customize.custom.dungeonChance", new Object[0]), true, this, 1.0F, 100.0F, (float)this.settings.dungeonChance
         ),
         new GuiPageButtonList.GuiButtonEntry(155, I18n.format("createWorld.customize.custom.useWaterLakes", new Object[0]), true, this.settings.useWaterLakes),
         new GuiPageButtonList.GuiSlideEntry(
            158, I18n.format("createWorld.customize.custom.waterLakeChance", new Object[0]), true, this, 1.0F, 100.0F, (float)this.settings.waterLakeChance
         ),
         new GuiPageButtonList.GuiButtonEntry(156, I18n.format("createWorld.customize.custom.useLavaLakes", new Object[0]), true, this.settings.useLavaLakes),
         new GuiPageButtonList.GuiSlideEntry(
            159, I18n.format("createWorld.customize.custom.lavaLakeChance", new Object[0]), true, this, 10.0F, 100.0F, (float)this.settings.lavaLakeChance
         ),
         new GuiPageButtonList.GuiButtonEntry(161, I18n.format("createWorld.customize.custom.useLavaOceans", new Object[0]), true, this.settings.useLavaOceans),
         new GuiPageButtonList.GuiSlideEntry(162, I18n.format("createWorld.customize.custom.fixedBiome", new Object[0]), true, this, -1.0F, 37.0F, (float)this.settings.fixedBiome),
         new GuiPageButtonList.GuiSlideEntry(163, I18n.format("createWorld.customize.custom.biomeSize", new Object[0]), true, this, 1.0F, 8.0F, (float)this.settings.biomeSize),
         new GuiPageButtonList.GuiSlideEntry(164, I18n.format("createWorld.customize.custom.riverSize", new Object[0]), true, this, 1.0F, 5.0F, (float)this.settings.riverSize)
      };
      GuiPageButtonList.GuiListEntry[] guiListEntrys2 = new GuiPageButtonList.GuiListEntry[]{
         new GuiPageButtonList.GuiLabelEntry(416, I18n.format("tile.dirt.name", new Object[0]), false),
         null,
         new GuiPageButtonList.GuiSlideEntry(165, I18n.format("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, (float)this.settings.dirtSize),
         new GuiPageButtonList.GuiSlideEntry(166, I18n.format("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, (float)this.settings.dirtCount),
         new GuiPageButtonList.GuiSlideEntry(167, I18n.format("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, (float)this.settings.dirtMinHeight),
         new GuiPageButtonList.GuiSlideEntry(168, I18n.format("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, (float)this.settings.dirtMaxHeight),
         new GuiPageButtonList.GuiLabelEntry(417, I18n.format("tile.gravel.name", new Object[0]), false),
         null,
         new GuiPageButtonList.GuiSlideEntry(169, I18n.format("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, (float)this.settings.gravelSize),
         new GuiPageButtonList.GuiSlideEntry(170, I18n.format("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, (float)this.settings.gravelCount),
         new GuiPageButtonList.GuiSlideEntry(
            171, I18n.format("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, (float)this.settings.gravelMinHeight
         ),
         new GuiPageButtonList.GuiSlideEntry(
            172, I18n.format("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, (float)this.settings.gravelMaxHeight
         ),
         new GuiPageButtonList.GuiLabelEntry(418, I18n.format("tile.stone.granite.name", new Object[0]), false),
         null,
         new GuiPageButtonList.GuiSlideEntry(173, I18n.format("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, (float)this.settings.graniteSize),
         new GuiPageButtonList.GuiSlideEntry(174, I18n.format("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, (float)this.settings.graniteCount),
         new GuiPageButtonList.GuiSlideEntry(
            175, I18n.format("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, (float)this.settings.graniteMinHeight
         ),
         new GuiPageButtonList.GuiSlideEntry(
            176, I18n.format("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, (float)this.settings.graniteMaxHeight
         ),
         new GuiPageButtonList.GuiLabelEntry(419, I18n.format("tile.stone.diorite.name", new Object[0]), false),
         null,
         new GuiPageButtonList.GuiSlideEntry(177, I18n.format("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, (float)this.settings.dioriteSize),
         new GuiPageButtonList.GuiSlideEntry(178, I18n.format("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, (float)this.settings.dioriteCount),
         new GuiPageButtonList.GuiSlideEntry(
            179, I18n.format("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, (float)this.settings.dioriteMinHeight
         ),
         new GuiPageButtonList.GuiSlideEntry(
            180, I18n.format("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, (float)this.settings.dioriteMaxHeight
         ),
         new GuiPageButtonList.GuiLabelEntry(420, I18n.format("tile.stone.andesite.name", new Object[0]), false),
         null,
         new GuiPageButtonList.GuiSlideEntry(181, I18n.format("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, (float)this.settings.andesiteSize),
         new GuiPageButtonList.GuiSlideEntry(182, I18n.format("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, (float)this.settings.andesiteCount),
         new GuiPageButtonList.GuiSlideEntry(
            183, I18n.format("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, (float)this.settings.andesiteMinHeight
         ),
         new GuiPageButtonList.GuiSlideEntry(
            184, I18n.format("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, (float)this.settings.andesiteMaxHeight
         ),
         new GuiPageButtonList.GuiLabelEntry(421, I18n.format("tile.oreCoal.name", new Object[0]), false),
         null,
         new GuiPageButtonList.GuiSlideEntry(185, I18n.format("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, (float)this.settings.coalSize),
         new GuiPageButtonList.GuiSlideEntry(186, I18n.format("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, (float)this.settings.coalCount),
         new GuiPageButtonList.GuiSlideEntry(187, I18n.format("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, (float)this.settings.coalMinHeight),
         new GuiPageButtonList.GuiSlideEntry(189, I18n.format("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, (float)this.settings.coalMaxHeight),
         new GuiPageButtonList.GuiLabelEntry(422, I18n.format("tile.oreIron.name", new Object[0]), false),
         null,
         new GuiPageButtonList.GuiSlideEntry(190, I18n.format("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, (float)this.settings.ironSize),
         new GuiPageButtonList.GuiSlideEntry(191, I18n.format("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, (float)this.settings.ironCount),
         new GuiPageButtonList.GuiSlideEntry(192, I18n.format("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, (float)this.settings.ironMinHeight),
         new GuiPageButtonList.GuiSlideEntry(193, I18n.format("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, (float)this.settings.ironMaxHeight),
         new GuiPageButtonList.GuiLabelEntry(423, I18n.format("tile.oreGold.name", new Object[0]), false),
         null,
         new GuiPageButtonList.GuiSlideEntry(194, I18n.format("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, (float)this.settings.goldSize),
         new GuiPageButtonList.GuiSlideEntry(195, I18n.format("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, (float)this.settings.goldCount),
         new GuiPageButtonList.GuiSlideEntry(196, I18n.format("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, (float)this.settings.goldMinHeight),
         new GuiPageButtonList.GuiSlideEntry(197, I18n.format("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, (float)this.settings.goldMaxHeight),
         new GuiPageButtonList.GuiLabelEntry(424, I18n.format("tile.oreRedstone.name", new Object[0]), false),
         null,
         new GuiPageButtonList.GuiSlideEntry(198, I18n.format("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, (float)this.settings.redstoneSize),
         new GuiPageButtonList.GuiSlideEntry(199, I18n.format("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, (float)this.settings.redstoneCount),
         new GuiPageButtonList.GuiSlideEntry(
            200, I18n.format("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, (float)this.settings.redstoneMinHeight
         ),
         new GuiPageButtonList.GuiSlideEntry(
            201, I18n.format("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, (float)this.settings.redstoneMaxHeight
         ),
         new GuiPageButtonList.GuiLabelEntry(425, I18n.format("tile.oreDiamond.name", new Object[0]), false),
         null,
         new GuiPageButtonList.GuiSlideEntry(202, I18n.format("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, (float)this.settings.diamondSize),
         new GuiPageButtonList.GuiSlideEntry(203, I18n.format("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, (float)this.settings.diamondCount),
         new GuiPageButtonList.GuiSlideEntry(
            204, I18n.format("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, (float)this.settings.diamondMinHeight
         ),
         new GuiPageButtonList.GuiSlideEntry(
            205, I18n.format("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, (float)this.settings.diamondMaxHeight
         ),
         new GuiPageButtonList.GuiLabelEntry(426, I18n.format("tile.oreLapis.name", new Object[0]), false),
         null,
         new GuiPageButtonList.GuiSlideEntry(206, I18n.format("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, (float)this.settings.lapisSize),
         new GuiPageButtonList.GuiSlideEntry(207, I18n.format("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, (float)this.settings.lapisCount),
         new GuiPageButtonList.GuiSlideEntry(
            208, I18n.format("createWorld.customize.custom.center", new Object[0]), false, this, 0.0F, 255.0F, (float)this.settings.lapisCenterHeight
         ),
         new GuiPageButtonList.GuiSlideEntry(209, I18n.format("createWorld.customize.custom.spread", new Object[0]), false, this, 0.0F, 255.0F, (float)this.settings.lapisSpread)
      };
      GuiPageButtonList.GuiListEntry[] guiListEntrys3 = new GuiPageButtonList.GuiListEntry[]{
         new GuiPageButtonList.GuiSlideEntry(
            100, I18n.format("createWorld.customize.custom.mainNoiseScaleX", new Object[0]), false, this, 1.0F, 5000.0F, this.settings.mainNoiseScaleX
         ),
         new GuiPageButtonList.GuiSlideEntry(
            101, I18n.format("createWorld.customize.custom.mainNoiseScaleY", new Object[0]), false, this, 1.0F, 5000.0F, this.settings.mainNoiseScaleY
         ),
         new GuiPageButtonList.GuiSlideEntry(
            102, I18n.format("createWorld.customize.custom.mainNoiseScaleZ", new Object[0]), false, this, 1.0F, 5000.0F, this.settings.mainNoiseScaleZ
         ),
         new GuiPageButtonList.GuiSlideEntry(
            103, I18n.format("createWorld.customize.custom.depthNoiseScaleX", new Object[0]), false, this, 1.0F, 2000.0F, this.settings.depthNoiseScaleX
         ),
         new GuiPageButtonList.GuiSlideEntry(
            104, I18n.format("createWorld.customize.custom.depthNoiseScaleZ", new Object[0]), false, this, 1.0F, 2000.0F, this.settings.depthNoiseScaleZ
         ),
         new GuiPageButtonList.GuiSlideEntry(
            105,
            I18n.format("createWorld.customize.custom.depthNoiseScaleExponent", new Object[0]),
            false,
            this,
            0.01F,
            20.0F,
            this.settings.depthNoiseScaleExponent
         ),
         new GuiPageButtonList.GuiSlideEntry(106, I18n.format("createWorld.customize.custom.baseSize", new Object[0]), false, this, 1.0F, 25.0F, this.settings.baseSize),
         new GuiPageButtonList.GuiSlideEntry(
            107, I18n.format("createWorld.customize.custom.coordinateScale", new Object[0]), false, this, 1.0F, 6000.0F, this.settings.coordinateScale
         ),
         new GuiPageButtonList.GuiSlideEntry(108, I18n.format("createWorld.customize.custom.heightScale", new Object[0]), false, this, 1.0F, 6000.0F, this.settings.heightScale),
         new GuiPageButtonList.GuiSlideEntry(109, I18n.format("createWorld.customize.custom.stretchY", new Object[0]), false, this, 0.01F, 50.0F, this.settings.stretchY),
         new GuiPageButtonList.GuiSlideEntry(
            110, I18n.format("createWorld.customize.custom.upperLimitScale", new Object[0]), false, this, 1.0F, 5000.0F, this.settings.upperLimitScale
         ),
         new GuiPageButtonList.GuiSlideEntry(
            111, I18n.format("createWorld.customize.custom.lowerLimitScale", new Object[0]), false, this, 1.0F, 5000.0F, this.settings.lowerLimitScale
         ),
         new GuiPageButtonList.GuiSlideEntry(
            112, I18n.format("createWorld.customize.custom.biomeDepthWeight", new Object[0]), false, this, 1.0F, 20.0F, this.settings.biomeDepthWeight
         ),
         new GuiPageButtonList.GuiSlideEntry(
            113, I18n.format("createWorld.customize.custom.biomeDepthOffset", new Object[0]), false, this, 0.0F, 20.0F, this.settings.biomeDepthOffset
         ),
         new GuiPageButtonList.GuiSlideEntry(
            114, I18n.format("createWorld.customize.custom.biomeScaleWeight", new Object[0]), false, this, 1.0F, 20.0F, this.settings.biomeScaleWeight
         ),
         new GuiPageButtonList.GuiSlideEntry(
            115, I18n.format("createWorld.customize.custom.biomeScaleOffset", new Object[0]), false, this, 0.0F, 20.0F, this.settings.biomeScaleOffset
         )
      };
      GuiPageButtonList.GuiListEntry[] guiListEntrys4 = new GuiPageButtonList.GuiListEntry[]{
         new GuiPageButtonList.GuiLabelEntry(400, I18n.format("createWorld.customize.custom.mainNoiseScaleX", new Object[0]) + ":", false),
         new GuiPageButtonList.EditBoxEntry(132, String.format("%5.3f", this.settings.mainNoiseScaleX), false, this.numberFilter),
         new GuiPageButtonList.GuiLabelEntry(401, I18n.format("createWorld.customize.custom.mainNoiseScaleY", new Object[0]) + ":", false),
         new GuiPageButtonList.EditBoxEntry(133, String.format("%5.3f", this.settings.mainNoiseScaleY), false, this.numberFilter),
         new GuiPageButtonList.GuiLabelEntry(402, I18n.format("createWorld.customize.custom.mainNoiseScaleZ", new Object[0]) + ":", false),
         new GuiPageButtonList.EditBoxEntry(134, String.format("%5.3f", this.settings.mainNoiseScaleZ), false, this.numberFilter),
         new GuiPageButtonList.GuiLabelEntry(403, I18n.format("createWorld.customize.custom.depthNoiseScaleX", new Object[0]) + ":", false),
         new GuiPageButtonList.EditBoxEntry(135, String.format("%5.3f", this.settings.depthNoiseScaleX), false, this.numberFilter),
         new GuiPageButtonList.GuiLabelEntry(404, I18n.format("createWorld.customize.custom.depthNoiseScaleZ", new Object[0]) + ":", false),
         new GuiPageButtonList.EditBoxEntry(136, String.format("%5.3f", this.settings.depthNoiseScaleZ), false, this.numberFilter),
         new GuiPageButtonList.GuiLabelEntry(405, I18n.format("createWorld.customize.custom.depthNoiseScaleExponent", new Object[0]) + ":", false),
         new GuiPageButtonList.EditBoxEntry(137, String.format("%2.3f", this.settings.depthNoiseScaleExponent), false, this.numberFilter),
         new GuiPageButtonList.GuiLabelEntry(406, I18n.format("createWorld.customize.custom.baseSize", new Object[0]) + ":", false),
         new GuiPageButtonList.EditBoxEntry(138, String.format("%2.3f", this.settings.baseSize), false, this.numberFilter),
         new GuiPageButtonList.GuiLabelEntry(407, I18n.format("createWorld.customize.custom.coordinateScale", new Object[0]) + ":", false),
         new GuiPageButtonList.EditBoxEntry(139, String.format("%5.3f", this.settings.coordinateScale), false, this.numberFilter),
         new GuiPageButtonList.GuiLabelEntry(408, I18n.format("createWorld.customize.custom.heightScale", new Object[0]) + ":", false),
         new GuiPageButtonList.EditBoxEntry(140, String.format("%5.3f", this.settings.heightScale), false, this.numberFilter),
         new GuiPageButtonList.GuiLabelEntry(409, I18n.format("createWorld.customize.custom.stretchY", new Object[0]) + ":", false),
         new GuiPageButtonList.EditBoxEntry(141, String.format("%2.3f", this.settings.stretchY), false, this.numberFilter),
         new GuiPageButtonList.GuiLabelEntry(410, I18n.format("createWorld.customize.custom.upperLimitScale", new Object[0]) + ":", false),
         new GuiPageButtonList.EditBoxEntry(142, String.format("%5.3f", this.settings.upperLimitScale), false, this.numberFilter),
         new GuiPageButtonList.GuiLabelEntry(411, I18n.format("createWorld.customize.custom.lowerLimitScale", new Object[0]) + ":", false),
         new GuiPageButtonList.EditBoxEntry(143, String.format("%5.3f", this.settings.lowerLimitScale), false, this.numberFilter),
         new GuiPageButtonList.GuiLabelEntry(412, I18n.format("createWorld.customize.custom.biomeDepthWeight", new Object[0]) + ":", false),
         new GuiPageButtonList.EditBoxEntry(144, String.format("%2.3f", this.settings.biomeDepthWeight), false, this.numberFilter),
         new GuiPageButtonList.GuiLabelEntry(413, I18n.format("createWorld.customize.custom.biomeDepthOffset", new Object[0]) + ":", false),
         new GuiPageButtonList.EditBoxEntry(145, String.format("%2.3f", this.settings.biomeDepthOffset), false, this.numberFilter),
         new GuiPageButtonList.GuiLabelEntry(414, I18n.format("createWorld.customize.custom.biomeScaleWeight", new Object[0]) + ":", false),
         new GuiPageButtonList.EditBoxEntry(146, String.format("%2.3f", this.settings.biomeScaleWeight), false, this.numberFilter),
         new GuiPageButtonList.GuiLabelEntry(415, I18n.format("createWorld.customize.custom.biomeScaleOffset", new Object[0]) + ":", false),
         new GuiPageButtonList.EditBoxEntry(147, String.format("%2.3f", this.settings.biomeScaleOffset), false, this.numberFilter)
      };
      this.list = new GuiPageButtonList(this.mc, this.width, this.height, 32, this.height - 32, 25, this, new GuiPageButtonList.GuiListEntry[][]{guiListEntrys, guiListEntrys2, guiListEntrys3, guiListEntrys4});

      for (int i = 0; i < 4; i++) {
         this.pageNames[i] = I18n.format("createWorld.customize.custom.page" + i, new Object[0]);
      }

      this.updatePageControls();
   }

   private void setConfirmationControls(boolean flag) {
      this.confirm.visible = flag;
      this.cancel.visible = flag;
      this.randomize.enabled = !flag;
      this.done.enabled = !flag;
      this.previousPage.enabled = !flag;
      this.nextPage.enabled = !flag;
      this.defaults.enabled = this.settingsModified && !flag;
      this.presets.enabled = !flag;
      this.list.setActive(!flag);
   }

   private void enterConfirmation(int n) {
      this.confirmMode = n;
      this.setConfirmationControls(true);
   }

   public void onTick(int n, float f) {
      switch (n) {
         case 100:
            this.settings.mainNoiseScaleX = f;
            break;
         case 101:
            this.settings.mainNoiseScaleY = f;
            break;
         case 102:
            this.settings.mainNoiseScaleZ = f;
            break;
         case 103:
            this.settings.depthNoiseScaleX = f;
            break;
         case 104:
            this.settings.depthNoiseScaleZ = f;
            break;
         case 105:
            this.settings.depthNoiseScaleExponent = f;
            break;
         case 106:
            this.settings.baseSize = f;
            break;
         case 107:
            this.settings.coordinateScale = f;
            break;
         case 108:
            this.settings.heightScale = f;
            break;
         case 109:
            this.settings.stretchY = f;
            break;
         case 110:
            this.settings.upperLimitScale = f;
            break;
         case 111:
            this.settings.lowerLimitScale = f;
            break;
         case 112:
            this.settings.biomeDepthWeight = f;
            break;
         case 113:
            this.settings.biomeDepthOffset = f;
            break;
         case 114:
            this.settings.biomeScaleWeight = f;
            break;
         case 115:
            this.settings.biomeScaleOffset = f;
         case 116:
         case 117:
         case 118:
         case 119:
         case 120:
         case 121:
         case 122:
         case 123:
         case 124:
         case 125:
         case 126:
         case 127:
         case 128:
         case 129:
         case 130:
         case 131:
         case 132:
         case 133:
         case 134:
         case 135:
         case 136:
         case 137:
         case 138:
         case 139:
         case 140:
         case 141:
         case 142:
         case 143:
         case 144:
         case 145:
         case 146:
         case 147:
         case 148:
         case 149:
         case 150:
         case 151:
         case 152:
         case 153:
         case 154:
         case 155:
         case 156:
         case 161:
         case 188:
         default:
            break;
         case 157:
            this.settings.dungeonChance = (int)f;
            break;
         case 158:
            this.settings.waterLakeChance = (int)f;
            break;
         case 159:
            this.settings.lavaLakeChance = (int)f;
            break;
         case 160:
            this.settings.seaLevel = (int)f;
            break;
         case 162:
            this.settings.fixedBiome = (int)f;
            break;
         case 163:
            this.settings.biomeSize = (int)f;
            break;
         case 164:
            this.settings.riverSize = (int)f;
            break;
         case 165:
            this.settings.dirtSize = (int)f;
            break;
         case 166:
            this.settings.dirtCount = (int)f;
            break;
         case 167:
            this.settings.dirtMinHeight = (int)f;
            break;
         case 168:
            this.settings.dirtMaxHeight = (int)f;
            break;
         case 169:
            this.settings.gravelSize = (int)f;
            break;
         case 170:
            this.settings.gravelCount = (int)f;
            break;
         case 171:
            this.settings.gravelMinHeight = (int)f;
            break;
         case 172:
            this.settings.gravelMaxHeight = (int)f;
            break;
         case 173:
            this.settings.graniteSize = (int)f;
            break;
         case 174:
            this.settings.graniteCount = (int)f;
            break;
         case 175:
            this.settings.graniteMinHeight = (int)f;
            break;
         case 176:
            this.settings.graniteMaxHeight = (int)f;
            break;
         case 177:
            this.settings.dioriteSize = (int)f;
            break;
         case 178:
            this.settings.dioriteCount = (int)f;
            break;
         case 179:
            this.settings.dioriteMinHeight = (int)f;
            break;
         case 180:
            this.settings.dioriteMaxHeight = (int)f;
            break;
         case 181:
            this.settings.andesiteSize = (int)f;
            break;
         case 182:
            this.settings.andesiteCount = (int)f;
            break;
         case 183:
            this.settings.andesiteMinHeight = (int)f;
            break;
         case 184:
            this.settings.andesiteMaxHeight = (int)f;
            break;
         case 185:
            this.settings.coalSize = (int)f;
            break;
         case 186:
            this.settings.coalCount = (int)f;
            break;
         case 187:
            this.settings.coalMinHeight = (int)f;
            break;
         case 189:
            this.settings.coalMaxHeight = (int)f;
            break;
         case 190:
            this.settings.ironSize = (int)f;
            break;
         case 191:
            this.settings.ironCount = (int)f;
            break;
         case 192:
            this.settings.ironMinHeight = (int)f;
            break;
         case 193:
            this.settings.ironMaxHeight = (int)f;
            break;
         case 194:
            this.settings.goldSize = (int)f;
            break;
         case 195:
            this.settings.goldCount = (int)f;
            break;
         case 196:
            this.settings.goldMinHeight = (int)f;
            break;
         case 197:
            this.settings.goldMaxHeight = (int)f;
            break;
         case 198:
            this.settings.redstoneSize = (int)f;
            break;
         case 199:
            this.settings.redstoneCount = (int)f;
            break;
         case 200:
            this.settings.redstoneMinHeight = (int)f;
            break;
         case 201:
            this.settings.redstoneMaxHeight = (int)f;
            break;
         case 202:
            this.settings.diamondSize = (int)f;
            break;
         case 203:
            this.settings.diamondCount = (int)f;
            break;
         case 204:
            this.settings.diamondMinHeight = (int)f;
            break;
         case 205:
            this.settings.diamondMaxHeight = (int)f;
            break;
         case 206:
            this.settings.lapisSize = (int)f;
            break;
         case 207:
            this.settings.lapisCount = (int)f;
            break;
         case 208:
            this.settings.lapisCenterHeight = (int)f;
            break;
         case 209:
            this.settings.lapisSpread = (int)f;
      }

      if (n >= 100 && n < 116) {
         Gui component = this.list.getComponent(n - 100 + 132);
         if (component != null) {
            ((GuiTextField)component).setText(this.getFormattedValue(n, f));
         }
      }

      if (!this.settings.equals(this.defaultSettings)) {
         this.setSettingsModified(true);
      }
   }

   @Override
   public void initGui() {
      int n = 0;
      int n2 = 0;
      if (this.list != null) {
         n = this.list.getPage();
         n2 = this.list.getAmountScrolled();
      }

      this.title = I18n.format("options.customizeTitle", new Object[0]);
      this.buttonList.clear();
      this.buttonList.add(this.previousPage = new GuiButton(302, 20, 5, 80, 20, I18n.format("createWorld.customize.custom.prev", new Object[0])));
      this.buttonList.add(this.nextPage = new GuiButton(303, this.width - 100, 5, 80, 20, I18n.format("createWorld.customize.custom.next", new Object[0])));
      this.buttonList
         .add(
            this.defaults = new GuiButton(304, this.width / 2 - 187, this.height - 27, 90, 20, I18n.format("createWorld.customize.custom.defaults", new Object[0]))
         );
      this.buttonList
         .add(
            this.randomize = new GuiButton(301, this.width / 2 - 92, this.height - 27, 90, 20, I18n.format("createWorld.customize.custom.randomize", new Object[0]))
         );
      this.buttonList
         .add(this.presets = new GuiButton(305, this.width / 2 + 3, this.height - 27, 90, 20, I18n.format("createWorld.customize.custom.presets", new Object[0])));
      this.buttonList.add(this.done = new GuiButton(300, this.width / 2 + 98, this.height - 27, 90, 20, I18n.format("gui.done", new Object[0])));
      this.defaults.enabled = this.settingsModified;
      this.confirm = new GuiButton(306, this.width / 2 - 55, 160, 50, 20, I18n.format("gui.yes", new Object[0]));
      this.confirm.visible = false;
      this.buttonList.add(this.confirm);
      this.cancel = new GuiButton(307, this.width / 2 + 5, 160, 50, 20, I18n.format("gui.no", new Object[0]));
      this.cancel.visible = false;
      this.buttonList.add(this.cancel);
      if (this.confirmMode != 0) {
         this.confirm.visible = true;
         this.cancel.visible = true;
      }

      this.createPagedList();
      if (n != 0) {
         this.list.setPage(n);
         this.list.scrollBy(n2);
         this.updatePageControls();
      }
   }

   private void updatePageControls() {
      this.previousPage.enabled = this.list.getPage() != 0;
      this.nextPage.enabled = this.list.getPage() != this.list.getPages() - 1;
      this.subtitle = I18n.format("book.pageIndicator", new Object[]{this.list.getPage() + 1, this.list.getPages()});
      this.pageTitle = this.pageNames[this.list.getPage()];
      this.randomize.enabled = this.list.getPage() != this.list.getPages() - 1;
   }

   @Override
   public String getText(int n, String str, float f) {
      return str + ": " + this.getFormattedValue(n, f);
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      if (this.confirmMode == 0 && !this.confirmDismissed) {
         this.list.mouseClicked(mouseX, mouseY, mouseButton);
      }
   }

   public GuiCustomizeWorldScreen(GuiScreen screen, String str) {
      this.parent = (GuiCreateWorld)screen;
      this.loadValues(str);
   }

   public String saveValues() {
      return this.settings.toString().replace("\n", "");
   }

   private void setSettingsModified(boolean flag) {
      this.settingsModified = flag;
      this.defaults.enabled = flag;
   }



   public static class FilterPredicate implements Predicate {
      public final GuiCustomizeWorldScreen customizeScreen;

      public boolean apply(Object obj) {
         return this.apply((String)obj);
      }

      public boolean apply(String str) {
         Float f = Floats.tryParse(str);
         return str.length() == 0 || f != null && Floats.isFinite(f) && f >= 0.0F;
      }

      public FilterPredicate(GuiCustomizeWorldScreen guiCustomizeWorldScreen) {
         this.customizeScreen = guiCustomizeWorldScreen;
      }
   }
}
