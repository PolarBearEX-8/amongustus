package client.util;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.ValueSliderControl;
import client.setting.FactoryOptionType;
import client.setting.GameSettings;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.StatCollector;

public class CurveCalculator extends GuiScreen {
   public GuiScreen guiScreen2;
   public String labelText;
   public GameSettings gameSettings;
   public static FactoryOptionType[] factoryOptionTypeArray = new FactoryOptionType[]{
      FactoryOptionType.ANIMATED_WATER,
      FactoryOptionType.ANIMATED_LAVA,
      FactoryOptionType.ANIMATED_FIRE,
      FactoryOptionType.ANIMATED_PORTAL,
      FactoryOptionType.ANIMATED_REDSTONE,
      FactoryOptionType.ANIMATED_EXPLOSION,
      FactoryOptionType.ANIMATED_FLAME,
      FactoryOptionType.ANIMATED_SMOKE,
      FactoryOptionType.VOID_PARTICLES,
      FactoryOptionType.WATER_PARTICLES,
      FactoryOptionType.RAIN_SPLASH,
      FactoryOptionType.PORTAL_PARTICLES,
      FactoryOptionType.POTION_PARTICLES,
      FactoryOptionType.DRIPPING_WATER_LAVA,
      FactoryOptionType.ANIMATED_TERRAIN,
      FactoryOptionType.ANIMATED_TEXTURES,
      FactoryOptionType.FIREWORK_PARTICLES,
      FactoryOptionType.PARTICLES
   };

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
            this.mc.gameSettings.setEnabled(true);
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 211) {
            this.mc.gameSettings.setEnabled(false);
         }

         ScaledResolution scaledResolution = new ScaledResolution(this.mc);
         this.applyMinecraft2(this.mc, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight());
      }
   }

   @Override
   public void updateState2() {
      this.labelText = StatCollector.getText("of.options.animationsTitle", new Object[0]);
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
               210, this.width / 2 - 155, this.height / 6 + 168 + 11, 70, 20, GeneratorDefaultManager.resolveText("of.options.animation.allOn")
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               211, this.width / 2 - 155 + 80, this.height / 6 + 168 + 11, 70, 20, GeneratorDefaultManager.resolveText("of.options.animation.allOff")
            )
         );
      this.list.add(new ValueSliderControl(200, this.width / 2 + 5, this.height / 6 + 168 + 11, StatCollector.getText("gui.done", new Object[0])));
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.renderElement3();
      applyFontRenderer4(this.fontRenderer, this.labelText, this.width / 2, 15, 16777215);
      super.applyIndex(count, count2, factor);
   }

   public CurveCalculator(GuiScreen screen, GameSettings gameSettings2) {
      this.guiScreen2 = screen;
      this.gameSettings = gameSettings2;
   }
}
