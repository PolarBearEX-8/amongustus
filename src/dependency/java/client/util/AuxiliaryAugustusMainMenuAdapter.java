package client.util;

import augustus.modules.misc.ClientSettings;
import client.Client;
import client.ClientGlobals;
import client.event.PostUpdateProcessor;
import client.gui.AugustusMainMenu;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.CommonGuiScreenAdapter;
import client.gui.PrimaryGuiScreenAdapter;
import client.render.RenderPass;
import client.render.RenderUtils;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.renderer.GlStateManager;

public class AuxiliaryAugustusMainMenuAdapter extends AugustusMainMenu {
   private void applyAuxiliaryTexturesGuiIconsP2(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      long keySalt = 0x5b268fa93ca8L ^ 0x2396b042e375L;
      int count = (int)((0x5b268fa93ca8L ^ 0x2396b042e375L) >>> 48);
      int count2 = (int)((0x5b268fa93ca8L ^ 0x2396b042e375L) << 16 >>> 48);
      int count3 = (int)(keySalt << 32 >>> 32);
      GuiMultiplayer guiMultiplayer = new GuiMultiplayer(this);
      char character = (char)count;
      char character2 = (char)count2;
      char character3 = character;
      GuiMultiplayer guiMultiplayer2 = guiMultiplayer;
      this.applyGuiScreenByGuiScreen(guiMultiplayer2, character3, character2, count3);
   }

   @Override
   public void applyIndexByIndex(int count, int count2, float factor) {
      MovementHandler.processAction6();
      MovementHandler.processAction8();
      MovementHandler.applyRunnableTask(false, this::applyRenderPass);
      super.applyIndexByIndex(count, count2, factor);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      ClientSettings.setActive4(false);
      Client clientItem = Client.getClient();
      String string = clientItem.getString4();
      float factor2 = (float)this.getCount3() / 5.0F;
      float factor3 = (((float)this.getCount4() / 2.0F - 24.0F) / 5.0F - 3.0F) / 2.0F + 3.0F;
      float factor4 = 5.0F;
      float factor5 = factor3;
      float factor6 = factor2;
      String text = string;
      this.applyText(text, factor6, factor5, factor4);
   }

   private void applyAuxiliaryTexturesGuiIconsP6(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      long keySalt = 0x68ad7e42085dL ^ 0x101d41a9d780L;
      int count = (int)((0x68ad7e42085dL ^ 0x101d41a9d780L) >>> 48);
      int count2 = (int)((0x68ad7e42085dL ^ 0x101d41a9d780L) << 16 >>> 48);
      int count3 = (int)(keySalt << 32 >>> 32);
      SharedStringEntry sharedStringEntry = new SharedStringEntry(this, 0);
      char character = (char)count;
      char character2 = (char)count2;
      char character3 = character;
      SharedStringEntry sharedStringEntry2 = sharedStringEntry;
      this.applyGuiScreenByGuiScreen(sharedStringEntry2, character3, character2, count3);
   }

   private void applyRenderPass(RenderPass renderPass) {
      if (ClientGlobals.moduleManager.clientSettings.settingRoundedButtons.isEnabled()) {
         RenderUtils.processFloat9(
            (float)(this.getCount3() - 115),
            (float)(this.getCount4() / 2 + 21),
            (float)(this.getCount3() + 115),
            (float)(this.getCount4() / 2 + 154),
            (float)ClientGlobals.moduleManager.clientSettings.settingRoundFactor.getValue(),
            1,
            Integer.MIN_VALUE
         );
      } else {
         RenderUtils.processAction(this.getCount3() - 115, this.getCount4() / 2 + 21, this.getCount3() + 115, this.getCount4() / 2 + 154, Integer.MIN_VALUE);
      }
   }

   private void applyAuxiliaryTexturesGuiIconsP4(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      super.mc.updateState14();
   }

   @Override
   public void renderElement() {
      super.renderElement();
      int count = this.getCount4() / 2 + 60;
      int count2 = this.getCount3() - 100;
      int count3 = count + 60;
      String labelText = super.labelText2;
      PostUpdateProcessor postUpdateProcessor = this::applyAuxiliaryTexturesGuiIconsP3;
      String text = labelText;
      byte byteValue = 20;
      byte byteValue2 = 98;
      int count4 = count3;
      int count5 = count2;
      this.resolveAuxiliaryTexturesGuiIconsPng(count5, count4, byteValue2, byteValue, text, postUpdateProcessor);
      count2 = this.getCount3() + 2;
      count3 = count + 60;
      labelText = super.labelText4;
      PostUpdateProcessor postUpdateProcessor2 = this::applyAuxiliaryTexturesGuiIconsP4;
      String text2 = labelText;
      byte byteValue3 = 20;
      byte byteValue4 = 98;
      int count6 = count3;
      int count7 = count2;
      this.resolveAuxiliaryTexturesGuiIconsPng(count7, count6, byteValue4, byteValue3, text2, postUpdateProcessor2);
      count2 = this.getCount3() - 100;
      count3 = count - 24;
      labelText = super.labelText3;
      PostUpdateProcessor postUpdateProcessor3 = this::applyAuxiliaryTexturesGuiIconsP5;
      String text3 = labelText;
      byte byteValue5 = 20;
      short shortValue = 200;
      int count8 = count3;
      int count9 = count2;
      this.resolveAuxiliaryTexturesGuiIconsPng(count9, count8, shortValue, byteValue5, text3, postUpdateProcessor3);
      count2 = this.getCount3() - 100;
      labelText = super.labelText;
      PostUpdateProcessor postUpdateProcessor4 = this::applyAuxiliaryTexturesGuiIconsP2;
      String text4 = labelText;
      byte byteValue6 = 20;
      short shortValue2 = 200;
      int count10 = count2;
      this.resolveAuxiliaryTexturesGuiIconsPng(count10, count, shortValue2, byteValue6, text4, postUpdateProcessor4);
      count2 = this.getCount3() - 100;
      count3 = count + 24;
      labelText = Client.getClient().getString4();
      PostUpdateProcessor postUpdateProcessor5 = this::applyAuxiliaryTexturesGuiIconsP6;
      String text5 = labelText;
      byte byteValue7 = 20;
      short shortValue3 = 200;
      int count11 = count3;
      int count12 = count2;
      this.resolveAuxiliaryTexturesGuiIconsPng(count12, count11, shortValue3, byteValue7, text5, postUpdateProcessor5);
   }

   private void applyAuxiliaryTexturesGuiIconsP5(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      long keySalt = 0x2ae0f8ae36e9L ^ 0x5250c745e934L;
      int count = (int)((0x2ae0f8ae36e9L ^ 0x5250c745e934L) >>> 48);
      int count2 = (int)((0x2ae0f8ae36e9L ^ 0x5250c745e934L) << 16 >>> 48);
      int count3 = (int)(keySalt << 32 >>> 32);
      PrimaryGuiScreenAdapter primaryGuiScreenAdapter = new PrimaryGuiScreenAdapter(this);
      char character = (char)count;
      char character2 = (char)count2;
      char character3 = character;
      PrimaryGuiScreenAdapter primaryGuiScreenAdapter2 = primaryGuiScreenAdapter;
      this.applyGuiScreenByGuiScreen(primaryGuiScreenAdapter2, character3, character2, count3);
   }

   private void applyAuxiliaryTexturesGuiIconsP3(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      long keySalt = 0x6e9faf49d48L ^ 0x7e59c51f4295L;
      int count = (int)((0x6e9faf49d48L ^ 0x7e59c51f4295L) >>> 48);
      int count2 = (int)((0x6e9faf49d48L ^ 0x7e59c51f4295L) << 16 >>> 48);
      int count3 = (int)(keySalt << 32 >>> 32);
      CommonGuiScreenAdapter commonGuiScreenAdapter = new CommonGuiScreenAdapter(this, super.mc.gameSettings);
      char character = (char)count;
      char character2 = (char)count2;
      char character3 = character;
      CommonGuiScreenAdapter commonGuiScreenAdapter2 = commonGuiScreenAdapter;
      this.applyGuiScreenByGuiScreen(commonGuiScreenAdapter2, character3, character2, count3);
   }
}
