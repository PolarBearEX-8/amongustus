package client.util;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.ValueSliderControl;
import client.setting.FactoryOptionType;
import client.setting.GameSettings;
import java.io.IOException;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.util.StatCollector;

public class GeometryService extends GuiScreen {
   public GuiDoneExtendedMinecraftEntry guiDoneExtendedMinecraftEntry;
   public final GameSettings gameSettings;
   public final LanguageManager languageManager;
   public ValueSliderControl valueSliderControl;
   public ValueSliderControl valueSliderControl2;

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.guiDoneExtendedMinecraftEntry.applyIndex(count, count2, factor);
      applyFontRenderer4(this.fontRenderer, StatCollector.getText("options.language", new Object[0]), this.width / 2, 16, 16777215);
      applyFontRenderer4(
         this.fontRenderer, "(" + StatCollector.getText("options.languageWarning", new Object[0]) + ")", this.width / 2, this.height - 56, 8421504
      );
      super.applyIndex(count, count2, factor);
   }

   public GeometryService(GuiScreen screen, GameSettings gameSettings2, LanguageManager languageManager2) {
      super(screen);
      this.gameSettings = gameSettings2;
      this.languageManager = languageManager2;
      GuiScreen.set.add(this);
   }

   public static GameSettings resolveGameSettings(GeometryService geometryService) {
      return geometryService.gameSettings;
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         switch (auxiliaryTexturesGuiIconsPngManagerAdapter.count2) {
            case 5:
               break;
            case 6:
               GuiScreen guiScreen = this.guiScreen;
               this.mc.displayGuiScreen(guiScreen);
               break;
            case 100:
               if (auxiliaryTexturesGuiIconsPngManagerAdapter instanceof ValueSliderControl) {
                  this.gameSettings.applyFactoryOptionTypeByFactoryOptionTyp(((ValueSliderControl)auxiliaryTexturesGuiIconsPngManagerAdapter).getFactoryOptionType(), 1);
                  auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = this.gameSettings.resolveTextByFactoryOptionTyp(FactoryOptionType.FORCE_UNICODE_FONT);
                  ScaledResolution scaledResolution = new ScaledResolution(this.mc);
                  int scaledWidth = scaledResolution.getScaledWidth();
                  int scaledHeight = scaledResolution.getScaledHeight();
                  this.applyMinecraft2(this.mc, scaledWidth, scaledHeight);
               }
               break;
            default:
               this.guiDoneExtendedMinecraftEntry.applyAuxiliaryTexturesGuiIconsP(auxiliaryTexturesGuiIconsPngManagerAdapter);
         }
      }
   }

   public static ValueSliderControl resolveValueSliderControlByGeometryService(GeometryService geometryService) {
      return geometryService.valueSliderControl2;
   }

   @Override
   public void enableRepeatEvents() {
      super.enableRepeatEvents();
      this.mc.ingameGUI.getInternalLoggerEntry().renderElement2();
   }

   public static ValueSliderControl resolveValueSliderControl(GeometryService geometryService) {
      return geometryService.valueSliderControl;
   }

   @Override
   public void updateState2() {
      this.list
         .add(
            this.valueSliderControl = new ValueSliderControl(
               100,
               this.width / 2 - 155,
               this.height - 38,
               FactoryOptionType.FORCE_UNICODE_FONT,
               this.gameSettings.resolveTextByFactoryOptionTyp(FactoryOptionType.FORCE_UNICODE_FONT)
            )
         );
      this.list
         .add(
            this.valueSliderControl2 = new ValueSliderControl(6, this.width / 2 - 155 + 160, this.height - 38, StatCollector.getText("gui.done", new Object[0]))
         );
      this.guiDoneExtendedMinecraftEntry = new GuiDoneExtendedMinecraftEntry(this, this.mc);
      this.guiDoneExtendedMinecraftEntry.applyIndex2(7, 8);
   }

   @Override
   public void updateState3() throws IOException {
      super.updateState3();
      this.guiDoneExtendedMinecraftEntry.updateState();
   }

   public static LanguageManager resolveLanguageManager(GeometryService geometryService) {
      return geometryService.languageManager;
   }
}
