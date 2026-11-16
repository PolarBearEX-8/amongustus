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
import org.lwjgl.opengl.GL11;

public final class AugustusMainMenuAdapter extends AugustusMainMenu {
   public static final int count9 = 3;
   public static final int count10 = 150;
   public static final int count8 = 20;

   private void applyAuxiliaryTexturesGuiIconsP4(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      long keySalt = 0x719dc3c22567L ^ 0x92dfc29fabaL;
      int count = (int)((0x719dc3c22567L ^ 0x92dfc29fabaL) >>> 48);
      int count2 = (int)((0x719dc3c22567L ^ 0x92dfc29fabaL) << 16 >>> 48);
      int count3 = (int)(keySalt << 32 >>> 32);
      SharedStringEntry sharedStringEntry = new SharedStringEntry(this, 0);
      char character = (char)count;
      char character2 = (char)count2;
      char character3 = character;
      SharedStringEntry sharedStringEntry2 = sharedStringEntry;
      this.applyGuiScreenByGuiScreen(sharedStringEntry2, character3, character2, count3);
   }

   private int computeCount(int count, String text, PostUpdateProcessor postUpdateProcessor) {
      super.resolveAuxiliaryTexturesGuiIconsPng(3, count, 150, 20, text, postUpdateProcessor);
      return count + 20 + 3;
   }

   private void applyAuxiliaryTexturesGuiIconsP3(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      super.mc.updateState14();
   }

   private void applyAuxiliaryTexturesGuiIconsP2(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      long keySalt = 0x1b1a2342ad05L ^ 0x63aa1ca972d8L;
      int count = (int)((0x1b1a2342ad05L ^ 0x63aa1ca972d8L) >>> 48);
      int count2 = (int)((0x1b1a2342ad05L ^ 0x63aa1ca972d8L) << 16 >>> 48);
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
      Client clientItem = Client.getClient();
      String string = clientItem.getString4();
      float factor2 = 26.0F + (float)this.getCount3() / 3.0F;
      float factor3 = (float)this.getCount4() / 3.0F - (float)super.mc.fontRendererObj.indexOffset / 2.0F;
      float factor4 = 3.0F;
      float factor5 = factor3;
      float factor6 = factor2;
      String text = string;
      this.applyText(text, factor6, factor5, factor4);
      GL11.glPushMatrix();
      GL11.glTranslated(78.0, (double)(this.getCount4() + super.mc.fontRendererObj.indexOffset * 2), 0.0);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      ClientSettings.setActive4(false);
      GL11.glPopMatrix();
   }

   private void applyAuxiliaryTexturesGuiIconsP6(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      long keySalt = 0x2760da7bb5d8L ^ 0x5fd0e5906a05L;
      int count = (int)((0x2760da7bb5d8L ^ 0x5fd0e5906a05L) >>> 48);
      int count2 = (int)((0x2760da7bb5d8L ^ 0x5fd0e5906a05L) << 16 >>> 48);
      int count3 = (int)(keySalt << 32 >>> 32);
      CommonGuiScreenAdapter commonGuiScreenAdapter = new CommonGuiScreenAdapter(this, super.mc.gameSettings);
      char character = (char)count;
      char character2 = (char)count2;
      char character3 = character;
      CommonGuiScreenAdapter commonGuiScreenAdapter2 = commonGuiScreenAdapter;
      this.applyGuiScreenByGuiScreen(commonGuiScreenAdapter2, character3, character2, count3);
   }

   @Override
   public void renderElement() {
      super.renderElement();
      int count = this.getCount4() - 51;
      String labelText = super.labelText3;
      PostUpdateProcessor postUpdateProcessor = this::applyAuxiliaryTexturesGuiIconsP5;
      String text = labelText;
      int count2 = this.computeCount(count, text, postUpdateProcessor);
      labelText = super.labelText;
      PostUpdateProcessor postUpdateProcessor2 = this::applyAuxiliaryTexturesGuiIconsP2;
      String text2 = labelText;
      int count3 = this.computeCount(count2, text2, postUpdateProcessor2);
      labelText = Client.getClient().getString4();
      PostUpdateProcessor postUpdateProcessor3 = this::applyAuxiliaryTexturesGuiIconsP4;
      String text3 = labelText;
      int count4 = this.computeCount(count3, text3, postUpdateProcessor3);
      labelText = super.labelText2;
      PostUpdateProcessor postUpdateProcessor4 = this::applyAuxiliaryTexturesGuiIconsP6;
      String text4 = labelText;
      int count5 = this.computeCount(count4, text4, postUpdateProcessor4);
      labelText = super.labelText4;
      PostUpdateProcessor postUpdateProcessor5 = this::applyAuxiliaryTexturesGuiIconsP3;
      String text5 = labelText;
      this.computeCount(count5, text5, postUpdateProcessor5);
   }

   private void applyAuxiliaryTexturesGuiIconsP5(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      long keySalt = 0x16ff073b7a6fL ^ 0x6e4f38d0a5b2L;
      int count = (int)((0x16ff073b7a6fL ^ 0x6e4f38d0a5b2L) >>> 48);
      int count2 = (int)((0x16ff073b7a6fL ^ 0x6e4f38d0a5b2L) << 16 >>> 48);
      int count3 = (int)(keySalt << 32 >>> 32);
      PrimaryGuiScreenAdapter primaryGuiScreenAdapter = new PrimaryGuiScreenAdapter(this);
      char character = (char)count;
      char character2 = (char)count2;
      char character3 = character;
      PrimaryGuiScreenAdapter primaryGuiScreenAdapter2 = primaryGuiScreenAdapter;
      this.applyGuiScreenByGuiScreen(primaryGuiScreenAdapter2, character3, character2, count3);
   }

   private void applyRenderPass(RenderPass renderPass) {
      if (ClientGlobals.moduleManager.clientSettings.settingRoundedButtons.isEnabled()) {
         RenderUtils.processFloat9(
            0.0F, 0.0F, 156.0F, (float)super.height, (float)ClientGlobals.moduleManager.clientSettings.settingRoundFactor.getValue(), 1, Integer.MIN_VALUE
         );
      } else {
         RenderUtils.processAction(0, 0, 156, super.height, Integer.MIN_VALUE);
      }
   }
}
