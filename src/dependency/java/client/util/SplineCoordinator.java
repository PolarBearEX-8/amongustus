package client.util;

import client.event.PreMotionObserver;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.DefaultGuiScreenAdapter;
import client.gui.ValueSliderControl;
import client.setting.FactoryOptionType;
import client.setting.GameSettings;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class SplineCoordinator extends GuiScreen implements PreMotionObserver {
   public GuiScreen guiScreen2;
   public String labelText;
   public GameSettings gameSettings;
   public static FactoryOptionType[] factoryOptionTypeArray = new FactoryOptionType[]{
      FactoryOptionType.LAGOMETER,
      FactoryOptionType.PROFILER,
      FactoryOptionType.SHOW_FPS,
      FactoryOptionType.ADVANCED_TOOLTIPS,
      FactoryOptionType.WEATHER,
      FactoryOptionType.TIME,
      FactoryOptionType.USE_FULLSCREEN,
      FactoryOptionType.FULLSCREEN_MODE,
      FactoryOptionType.ANAGLYPH,
      FactoryOptionType.AUTOSAVE_TICKS,
      FactoryOptionType.SCREENSHOT_SIZE,
      FactoryOptionType.SHOW_GL_ERRORS
   };
   public AuxiliaryManager auxiliaryManager = new AuxiliaryManager(this, new TooltipManager());

   @Override
   public void applyFlag(boolean flag, int count) {
      if (flag) {
         this.mc.gameSettings.updateState7();
      }

      this.mc.displayGuiScreen(this);
   }

   @Override
   public void updateState2() {
      this.labelText = StatCollector.getText("of.options.otherTitle", new Object[0]);
      this.list.clear();

      for (int index = 0; index < factoryOptionTypeArray.length; index++) {
         FactoryOptionType factoryOptionType = factoryOptionTypeArray[index];
         int count = this.width / 2 - 155 + index % 2 * 160;
         int count2 = this.height / 6 + 21 * (index / 2) - 12;
         if (!factoryOptionType.isEnabled()) {
            this.list.add(new ListenerAdapter14(factoryOptionType.ordinalAsIndex(), count, count2, factoryOptionType, this.gameSettings.resolveTextByFactoryOptionTyp(factoryOptionType)));
         } else {
            this.list.add(new FloatEntryHandler(factoryOptionType.ordinalAsIndex(), count, count2, factoryOptionType));
         }
      }

      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               210, this.width / 2 - 100, this.height / 6 + 168 + 11 - 44, StatCollector.getText("of.options.other.reset", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               200, this.width / 2 - 100, this.height / 6 + 168 + 11, StatCollector.getText("gui.done", new Object[0])
            )
         );
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.renderElement3();
      applyFontRenderer4(this.fontRenderer, this.labelText, this.width / 2, 15, 16777215);
      super.applyIndex(count, count2, factor);
      this.auxiliaryManager.applyList(count, count2, this.list);
   }

   public SplineCoordinator(GuiScreen screen, GameSettings gameSettings2) {
      this.guiScreen2 = screen;
      this.gameSettings = gameSettings2;
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 < 200 && auxiliaryTexturesGuiIconsPngManagerAdapter instanceof ValueSliderControl) {
            this.gameSettings.applyFactoryOptionTypeByFactoryOptionTyp(((ValueSliderControl)auxiliaryTexturesGuiIconsPngManagerAdapter).getFactoryOptionType(), 1);
            auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = this.gameSettings.resolveTextByFactoryOptionTyp(FactoryOptionType.resolveFactoryOptionType(auxiliaryTexturesGuiIconsPngManagerAdapter.count2));
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 200) {
            this.mc.gameSettings.updateState();
            GuiScreen guiScreen = this.guiScreen2;
            this.mc.displayGuiScreen(guiScreen);
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 210) {
            this.mc.gameSettings.updateState();
            DefaultGuiScreenAdapter defaultGuiScreenAdapter = new DefaultGuiScreenAdapter(this, StatCollector.getText("of.message.other.reset", new Object[0]), "", 9999);
            this.mc.displayGuiScreen(defaultGuiScreenAdapter);
         }
      }
   }
}
