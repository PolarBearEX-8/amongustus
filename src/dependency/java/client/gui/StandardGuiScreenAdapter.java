package client.gui;

import client.setting.FactoryOptionType;
import client.setting.GameSettings;
import client.util.AuxiliaryClientManagerProvider;
import client.util.DynamicFloatEntry;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.StatCollector;

public class StandardGuiScreenAdapter extends GuiScreen {
   public static final FactoryOptionType[] factoryOptionTypeArray = new FactoryOptionType[]{
      FactoryOptionType.INVERT_MOUSE, FactoryOptionType.SENSITIVITY, FactoryOptionType.TOUCHSCREEN
   };
   public String labelText = "Controls";
   public GameSettings gameSettings;
   public KeyBinding keyBinding = null;
   public long timestamp2;
   public AuxiliaryClientManagerProvider auxiliaryClientManagerProvider;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;

   @Override
   public void updateState2() {
      this.auxiliaryClientManagerProvider = new AuxiliaryClientManagerProvider(this, this.mc);
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               200, this.width / 2 - 155, this.height - 29, 150, 20, StatCollector.getText("gui.done", new Object[0])
            )
         );
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               201, this.width / 2 - 155 + 160, this.height - 29, 150, 20, StatCollector.getText("controls.resetAll", new Object[0])
            )
         );
      this.labelText = StatCollector.getText("controls.title", new Object[0]);
      int count = 0;

      for (FactoryOptionType factoryOptionType : factoryOptionTypeArray) {
         if (factoryOptionType.isEnabled()) {
            this.list.add(new DynamicFloatEntry(factoryOptionType.ordinalAsIndex(), this.width / 2 - 155 + count % 2 * 160, 18 + 24 * (count >> 1), factoryOptionType));
         } else {
            this.list
               .add(
                  new ValueSliderControl(
                     factoryOptionType.ordinalAsIndex(),
                     this.width / 2 - 155 + count % 2 * 160,
                     18 + 24 * (count >> 1),
                     factoryOptionType,
                     this.gameSettings.resolveTextByFactoryOptionTyp(factoryOptionType)
                  )
               );
         }

         count++;
      }
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 200) {
         GuiScreen guiScreen = this.guiScreen;
         this.mc.displayGuiScreen(guiScreen);
      } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 201) {
         for (KeyBinding keyBinding2 : this.mc.gameSettings.scorePlayerTeamArray2) {
            keyBinding2.setKeyCode(keyBinding2.getKeyCodeDefault());
         }

         KeyBinding.unPressAllKeys();
      } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 < 100 && auxiliaryTexturesGuiIconsPngManagerAdapter instanceof ValueSliderControl) {
         this.gameSettings.applyFactoryOptionTypeByFactoryOptionTyp(((ValueSliderControl)auxiliaryTexturesGuiIconsPngManagerAdapter).getFactoryOptionType(), 1);
         auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = this.gameSettings.resolveTextByFactoryOptionTyp(FactoryOptionType.resolveFactoryOptionType(auxiliaryTexturesGuiIconsPngManagerAdapter.count2));
      }
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      if (this.keyBinding != null) {
         if (count == 1) {
            this.gameSettings.processAction(this.keyBinding, 0);
         } else if (count != 0) {
            this.gameSettings.processAction(this.keyBinding, count);
         } else if (character > 0) {
            this.gameSettings.processAction(this.keyBinding, character + 256);
         }

         this.keyBinding = null;
         this.timestamp2 = Minecraft.getTimestamp();
         KeyBinding.unPressAllKeys();
      } else {
         super.applyChar(character, count);
      }
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
      if (count3 != 0 || !this.auxiliaryClientManagerProvider.checkIndexByIndex(count, count2, count3)) {
         super.applyIndex(count, count2, count3);
      }
   }

   @Override
   public void updateState3() throws IOException {
      super.updateState3();
      this.auxiliaryClientManagerProvider.updateState();
   }

   @Override
   public void processAction(int count, int count2, int count3) throws IOException {
      if (this.keyBinding != null) {
         this.gameSettings.processAction(this.keyBinding, -100 + count3);
         this.keyBinding = null;
         KeyBinding.unPressAllKeys();
      } else if (count3 != 0 || !this.auxiliaryClientManagerProvider.checkIndex(count, count2, count3)) {
         super.processAction(count, count2, count3);
      }
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.renderElement3();
      this.auxiliaryClientManagerProvider.applyIndex(count, count2, factor);
      applyFontRenderer4(this.fontRenderer, this.labelText, this.width / 2, 8, 16777215);
      boolean flag = true;

      for (KeyBinding keyBinding2 : this.gameSettings.scorePlayerTeamArray2) {
         if (keyBinding2.getKeyCode() != keyBinding2.getKeyCodeDefault()) {
            flag = false;
            break;
         }
      }

      this.auxiliaryTexturesGuiIconsPngManagerAdapter2.enabled3 = !flag;
      super.applyIndex(count, count2, factor);
   }

   public StandardGuiScreenAdapter(GuiScreen screen, GameSettings gameSettings2) {
      super(screen);
      this.gameSettings = gameSettings2;
      GuiScreen.set.add(this);
   }
}
