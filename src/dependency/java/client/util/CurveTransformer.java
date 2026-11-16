package client.util;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.ValueSliderControl;
import client.setting.FactoryOptionType;
import client.setting.GameSettings;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.StatCollector;

public class CurveTransformer extends GuiScreen {
   public GuiScreen guiScreen2;
   public String labelText;
   public GameSettings gameSettings;
   public static FactoryOptionType[] factoryOptionTypeArray = new FactoryOptionType[]{
      FactoryOptionType.MIPMAP_LEVELS,
      FactoryOptionType.MIPMAP_TYPE,
      FactoryOptionType.AF_LEVEL,
      FactoryOptionType.AA_LEVEL,
      FactoryOptionType.CLEAR_WATER,
      FactoryOptionType.RANDOM_ENTITIES,
      FactoryOptionType.BETTER_GRASS,
      FactoryOptionType.BETTER_SNOW,
      FactoryOptionType.CUSTOM_FONTS,
      FactoryOptionType.CUSTOM_COLORS,
      FactoryOptionType.CONNECTED_TEXTURES,
      FactoryOptionType.NATURAL_TEXTURES,
      FactoryOptionType.CUSTOM_SKY,
      FactoryOptionType.CUSTOM_ITEMS,
      FactoryOptionType.CUSTOM_ENTITY_MODELS,
      FactoryOptionType.CUSTOM_GUIS,
      FactoryOptionType.EMISSIVE_TEXTURES
   };
   public AuxiliaryManager auxiliaryManager = new AuxiliaryManager(this, new TooltipManager());

   public CurveTransformer(GuiScreen screen, GameSettings gameSettings2) {
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

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 != FactoryOptionType.AA_LEVEL.ordinal()) {
            ScaledResolution scaledResolution = new ScaledResolution(this.mc);
            this.applyMinecraft2(this.mc, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight());
         }
      }
   }

   @Override
   public void updateState2() {
      this.labelText = StatCollector.getText("of.options.qualityTitle", new Object[0]);
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
   public void applyIndex(int count, int count2, float factor) {
      this.renderElement3();
      applyFontRenderer4(this.fontRenderer, this.labelText, this.width / 2, 15, 16777215);
      super.applyIndex(count, count2, factor);
      this.auxiliaryManager.applyList(count, count2, this.list);
   }
}
