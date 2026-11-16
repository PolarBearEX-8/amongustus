package client.util;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.ValueSliderControl;
import client.setting.FactoryOptionType;
import client.setting.GameSettings;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class SplineEstimator extends GuiScreen {
   public GuiScreen guiScreen2;
   public String labelText;
   public GameSettings gameSettings;
   public static FactoryOptionType[] factoryOptionTypeArray = new FactoryOptionType[]{
      FactoryOptionType.CLOUDS,
      FactoryOptionType.CLOUD_HEIGHT,
      FactoryOptionType.TREES,
      FactoryOptionType.RAIN,
      FactoryOptionType.SKY,
      FactoryOptionType.STARS,
      FactoryOptionType.SUN_MOON,
      FactoryOptionType.SHOW_CAPES,
      FactoryOptionType.FOG_FANCY,
      FactoryOptionType.FOG_START,
      FactoryOptionType.TRANSLUCENT_BLOCKS,
      FactoryOptionType.HELD_ITEM_TOOLTIPS,
      FactoryOptionType.DROPPED_ITEMS,
      FactoryOptionType.ENTITY_SHADOWS,
      FactoryOptionType.VIGNETTE,
      FactoryOptionType.ALTERNATE_BLOCKS,
      FactoryOptionType.SWAMP_COLORS,
      FactoryOptionType.SMOOTH_BIOMES
   };
   public AuxiliaryManager auxiliaryManager = new AuxiliaryManager(this, new TooltipManager());

   public SplineEstimator(GuiScreen screen, GameSettings gameSettings2) {
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
      }
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.renderElement3();
      applyFontRenderer4(this.fontRenderer, this.labelText, this.width / 2, 15, 16777215);
      super.applyIndex(count, count2, factor);
      this.auxiliaryManager.applyList(count, count2, this.list);
   }

   @Override
   public void updateState2() {
      this.labelText = StatCollector.getText("of.options.detailsTitle", new Object[0]);
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
}
