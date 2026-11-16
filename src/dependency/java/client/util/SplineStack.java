package client.util;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.ValueSliderControl;
import client.setting.FactoryOptionType;
import client.setting.GameSettings;
import java.io.IOException;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class SplineStack extends GuiScreen {
   public static final FactoryOptionType[] factoryOptionTypeArray = new FactoryOptionType[]{
      FactoryOptionType.CHAT_VISIBILITY,
      FactoryOptionType.CHAT_COLOR,
      FactoryOptionType.CHAT_LINKS,
      FactoryOptionType.CHAT_OPACITY,
      FactoryOptionType.CHAT_LINKS_PROMPT,
      FactoryOptionType.CHAT_SCALE,
      FactoryOptionType.CHAT_HEIGHT_FOCUSED,
      FactoryOptionType.CHAT_HEIGHT_UNFOCUSED,
      FactoryOptionType.CHAT_WIDTH,
      FactoryOptionType.REDUCED_DEBUG_INFO
   };
   public final GameSettings gameSettings;
   public String labelText;

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 < 100 && auxiliaryTexturesGuiIconsPngManagerAdapter instanceof ValueSliderControl) {
            this.gameSettings.applyFactoryOptionTypeByFactoryOptionTyp(((ValueSliderControl)auxiliaryTexturesGuiIconsPngManagerAdapter).getFactoryOptionType(), 1);
            auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = this.gameSettings.resolveTextByFactoryOptionTyp(FactoryOptionType.resolveFactoryOptionType(auxiliaryTexturesGuiIconsPngManagerAdapter.count2));
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 200) {
            this.mc.gameSettings.updateState();
            GuiScreen guiScreen = this.guiScreen;
            this.mc.displayGuiScreen(guiScreen);
         }
      }
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.renderElement3();
      applyFontRenderer4(this.fontRenderer, this.labelText, this.width / 2, 20, 16777215);
      super.applyIndex(count, count2, factor);
   }

   @Override
   public void updateState2() {
      int count = 0;
      this.labelText = StatCollector.getText("options.chat.title", new Object[0]);

      for (FactoryOptionType factoryOptionType : factoryOptionTypeArray) {
         if (factoryOptionType.isEnabled()) {
            this.list.add(new DynamicFloatEntry(factoryOptionType.ordinalAsIndex(), this.width / 2 - 155 + count % 2 * 160, this.height / 6 + 24 * (count >> 1), factoryOptionType));
         } else {
            this.list
               .add(
                  new ValueSliderControl(
                     factoryOptionType.ordinalAsIndex(),
                     this.width / 2 - 155 + count % 2 * 160,
                     this.height / 6 + 24 * (count >> 1),
                     factoryOptionType,
                     this.gameSettings.resolveTextByFactoryOptionTyp(factoryOptionType)
                  )
               );
         }

         count++;
      }

      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(200, this.width / 2 - 100, this.height / 6 + 120, StatCollector.getText("gui.done", new Object[0]))
         );
   }

   public SplineStack(GuiScreen screen, GameSettings gameSettings2) {
      super(screen);
      this.gameSettings = gameSettings2;
      GuiScreen.set.add(this);
   }
}
