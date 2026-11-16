package client.gui;

import client.setting.FactoryOptionType;
import client.setting.GameSettings;
import client.util.AuxiliaryManager;
import client.util.FloatEntryHandler;
import client.util.ListenerAdapter14;
import client.util.TooltipManager;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class SharedGuiScreenAdapter extends GuiScreen {
   public GuiScreen guiScreen2;
   public String labelText;
   public GameSettings gameSettings;
   public static FactoryOptionType[] factoryOptionTypeArray = new FactoryOptionType[]{
      FactoryOptionType.SMOOTH_FPS,
      FactoryOptionType.SMOOTH_WORLD,
      FactoryOptionType.FAST_RENDER,
      FactoryOptionType.FAST_MATH,
      FactoryOptionType.CHUNK_UPDATES,
      FactoryOptionType.CHUNK_UPDATES_DYNAMIC,
      FactoryOptionType.RENDER_REGIONS,
      FactoryOptionType.LAZY_CHUNK_LOADING,
      FactoryOptionType.SMART_ANIMATIONS
   };
   public AuxiliaryManager auxiliaryManager = new AuxiliaryManager(this, new TooltipManager());

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.renderElement3();
      applyFontRenderer4(this.fontRenderer, this.labelText, this.width / 2, 15, 16777215);
      super.applyIndex(count, count2, factor);
      this.auxiliaryManager.applyList(count, count2, this.list);
   }

   public SharedGuiScreenAdapter(GuiScreen screen, GameSettings gameSettings2) {
      this.guiScreen2 = screen;
      this.gameSettings = gameSettings2;
   }

   @Override
   public void updateState2() {
      this.labelText = StatCollector.getText("of.options.performanceTitle", new Object[0]);
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
               200, this.width / 2 - 100, this.height / 6 + 168 + 11, StatCollector.getText("gui.done", new Object[0])
            )
         );
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
      }
   }
}
