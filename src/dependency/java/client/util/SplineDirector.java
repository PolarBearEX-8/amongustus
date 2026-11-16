package client.util;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.NativeGuiScreenAdapter;
import client.gui.OptionManagerAuxiliaryTexturesGuiIc;
import client.gui.OptionManagerOptionManagerAuxiliary;
import client.setting.GameSettings;
import client.setting.OptionManager;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;

public class SplineDirector extends NativeGuiScreenAdapter {
   public GuiScreen guiScreen2;
   public String labelText;
   public GameSettings gameSettings;
   public AuxiliaryManager auxiliaryManager = new AuxiliaryManager(this, new GeneralAmbiguousTooltipManager());
   public String labelText2 = null;
   public String labelText3 = null;
   public boolean enabled6 = false;
   public static final String labelText4 = "<profile>";
   public static final String labelText5 = "<empty>";
   public static final String labelText6 = "*";

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.renderElement3();
      if (this.labelText3 != null) {
         applyFontRenderer4(this.fontRenderer, this.labelText3, this.width / 2, 15, 16777215);
      } else {
         applyFontRenderer4(this.fontRenderer, this.labelText, this.width / 2, 15, 16777215);
      }

      super.applyIndex(count, count2, factor);
      this.auxiliaryManager.applyList(count, count2, this.list);
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 < 200 && auxiliaryTexturesGuiIconsPngManagerAdapter instanceof OptionManagerAuxiliaryTexturesGuiIc) {
            OptionManagerAuxiliaryTexturesGuiIc optionManagerAuxiliaryTexturesGuiIc = (OptionManagerAuxiliaryTexturesGuiIc)auxiliaryTexturesGuiIconsPngManagerAdapter;
            OptionManager optionManager = optionManagerAuxiliaryTexturesGuiIc.getOptionManager();
            if (optionManager instanceof GeometryBuffer) {
               String labelText7 = optionManager.getLabelText();
               SplineDirector splineDirector = new SplineDirector(this, this.gameSettings, labelText7);
               this.mc.displayGuiScreen(splineDirector);
               return;
            }

            if (isActiveAsFlag()) {
               optionManager.updateState2();
            } else if (optionManagerAuxiliaryTexturesGuiIc.isActive()) {
               optionManager.updateState();
            }

            this.updateState();
            this.enabled6 = true;
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 201) {
            OptionManager[] optionManagerArray = PreEndHandManager.resolveOptionManagerArrayByOptionManagerArr(PreEndHandManager.getOptionManagerArray());

            for (int index = 0; index < optionManagerArray.length; index++) {
               optionManagerArray[index].updateState2();
               this.enabled6 = true;
            }

            this.updateState();
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 200) {
            if (this.enabled6) {
               PreEndHandManager.updateState4();
               this.enabled6 = false;
               PreEndHandManager.updateState77();
            }

            GuiScreen guiScreen = this.guiScreen2;
            this.mc.displayGuiScreen(guiScreen);
         }
      }
   }

   @Override
   public void updateState2() {
      this.labelText = StatCollector.getText("of.options.shaderOptionsTitle", new Object[0]);
      int count = PreEndHandManager.computeCount(this.labelText2, 2);
      OptionManager[] optionManagerArray = PreEndHandManager.resolveOptionManagerArray(this.labelText2);
      if (optionManagerArray != null) {
         int count2 = MathHelper.truncateDoubleToInt((double)optionManagerArray.length / 9.0);
         if (count < count2) {
            count = count2;
         }

         for (int index = 0; index < optionManagerArray.length; index++) {
            OptionManager optionManager = optionManagerArray[index];
            if (optionManager != null && optionManager.isEnabled2()) {
               int count3 = index % count;
               int count4 = index / count;
               int count5 = Math.min(this.width / count, 200);
               int count6 = (this.width - count5 * count) / 2;
               int count7 = count3 * count5 + 5 + count6;
               int count8 = 30 + count4 * 20;
               int count9 = count5 - 10;
               String text = resolveText(optionManager, count9);
               Object entry;
               if (PreEndHandManager.contains(optionManager.getLabelText())) {
                  entry = new OptionManagerOptionManagerAuxiliary(100 + index, count7, count8, count9, 20, optionManager, text);
               } else {
                  entry = new OptionManagerAuxiliaryTexturesGuiIc(100 + index, count7, count8, count9, 20, optionManager, text);
               }

               ((OptionManagerAuxiliaryTexturesGuiIc)entry).enabled3 = optionManager.isEnabled();
               this.list.add(entry);
            }
         }
      }

      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               201, this.width / 2 - 120 - 20, this.height / 6 + 168 + 11, 120, 20, StatCollector.getText("controls.reset", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               200, this.width / 2 + 20, this.height / 6 + 168 + 11, 120, 20, StatCollector.getText("gui.done", new Object[0])
            )
         );
   }

   public static String resolveText(OptionManager optionManager, int count) {
      String textAsText = optionManager.getTextAsText();
      if (optionManager instanceof GeometryBuffer) {
         return textAsText + "...";
      } else {
         FontRenderer fontRendererObj = BuildManager.getMc().fontRendererObj;
         int count2 = fontRendererObj.getIndex(": " + GeneratorDefaultManager.getText4()) + 5;

         while (fontRendererObj.getIndex(textAsText) + count2 >= count && textAsText.length() > 0) {
            textAsText = textAsText.substring(0, textAsText.length() - 1);
         }

         String text = optionManager.isActiveAsFlag() ? optionManager.resolveText(optionManager.getLabelText2()) : "";
         String text2 = optionManager.resolveTextByText(optionManager.getLabelText2());
         return textAsText + ": " + text + text2;
      }
   }

   public SplineDirector(GuiScreen screen, GameSettings gameSettings2) {
      this.labelText = "Shader Options";
      this.guiScreen2 = screen;
      this.gameSettings = gameSettings2;
   }

   private void updateState() {
      for (AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter : (Iterable<AuxiliaryTexturesGuiIconsPngManagerAdapter>)(Iterable)this.list) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter instanceof OptionManagerAuxiliaryTexturesGuiIc) {
            OptionManagerAuxiliaryTexturesGuiIc optionManagerAuxiliaryTexturesGuiIc = (OptionManagerAuxiliaryTexturesGuiIc)auxiliaryTexturesGuiIconsPngManagerAdapter;
            OptionManager optionManager = optionManagerAuxiliaryTexturesGuiIc.getOptionManager();
            if (optionManager instanceof GeometryPipeline) {
               ((GeometryPipeline)optionManager).updateState4();
            }

            optionManagerAuxiliaryTexturesGuiIc.labelText = resolveText(optionManager, optionManagerAuxiliaryTexturesGuiIc.getCount4());
            optionManagerAuxiliaryTexturesGuiIc.renderElement();
         }
      }
   }

   public SplineDirector(GuiScreen screen, GameSettings gameSettings2, String text) {
      this(screen, gameSettings2);
      this.labelText2 = text;
      if (text != null) {
         this.labelText3 = PreEndHandManager.get("screen." + text, text);
      }
   }

   @Override
   public void enableRepeatEvents() {
      super.enableRepeatEvents();
      if (this.enabled6) {
         PreEndHandManager.updateState4();
         this.enabled6 = false;
         PreEndHandManager.updateState77();
      }
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsPByAuxiliaryTexture(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter instanceof OptionManagerAuxiliaryTexturesGuiIc) {
         OptionManagerAuxiliaryTexturesGuiIc optionManagerAuxiliaryTexturesGuiIc = (OptionManagerAuxiliaryTexturesGuiIc)auxiliaryTexturesGuiIconsPngManagerAdapter;
         OptionManager optionManager = optionManagerAuxiliaryTexturesGuiIc.getOptionManager();
         if (isActiveAsFlag()) {
            optionManager.updateState2();
         } else if (optionManagerAuxiliaryTexturesGuiIc.isActive()) {
            optionManager.updateState3();
         }

         this.updateState();
         this.enabled6 = true;
      }
   }
}
