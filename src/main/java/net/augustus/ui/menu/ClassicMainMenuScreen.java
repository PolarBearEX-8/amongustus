package net.augustus.ui.menu;

import net.augustus.Augustus;
import net.augustus.ClientManagers;
import net.augustus.render.RenderUtil;
import net.augustus.ui.GuiButtonClickHandler;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.modules.misc.ClientSettings;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.renderer.GlStateManager;

public class ClassicMainMenuScreen extends AbstractMainMenuScreen {

   private void onClientMenuClick(GuiButton button) {
      this.openScreen(new ClientTabContainerScreen(this, 0));
   }

   private void renderMenuBackdrop(ShaderRenderPass shaderRenderPass) {
      if (ClientManagers.moduleManager.clientSettings.roundedButtons.getValue()) {
         RenderUtil.drawRoundedRect(
            (float)(this.getCenterX() - 115),
            (float)(this.getCenterY() / 2 + 21),
            (float)(this.getCenterX() + 115),
            (float)(this.getCenterY() / 2 + 154),
            (float)ClientManagers.moduleManager.clientSettings.roundFactor.getValue(),
            1,
            Integer.MIN_VALUE
         );
      } else {
         RenderUtil.drawRect(this.getCenterX() - 115, this.getCenterY() / 2 + 21, this.getCenterX() + 115, this.getCenterY() / 2 + 154, Integer.MIN_VALUE);
      }
   }

   public ClassicMainMenuScreen() {
       super();
   }

   @Override
   public void initContent() {
      super.initContent();
      int n = this.getCenterY() / 2 + 60;
      int n2 = this.getCenterX() - 100;
      int n3 = n + 60;
      String optionsLabel2 = this.optionsLabel;
      this.addButton(n2, n3, (byte)98, (byte)20, optionsLabel2, button -> this.onOptionsClick(button));
      int n6 = this.getCenterX() + 2;
      n3 = n + 60;
      optionsLabel2 = this.quitLabel;
      this.addButton(n6, n3, (byte)98, (byte)20, optionsLabel2, button2 -> this.mc.shutdown());
      n3 = this.getCenterX() - 100;
      int n9 = n - 24;
      String singleplayerLabel2 = this.singleplayerLabel;
      GuiButtonClickHandler guiButtonClickHandler3 = button3 -> {
         this.openScreen(new GuiSelectWorld(this));
      };
      this.addButton(n3, n9, (short)200, (byte)20, singleplayerLabel2, guiButtonClickHandler3);
      int n12 = this.getCenterX() - 100;
      optionsLabel2 = this.multiplayerLabel;
      this.addButton(n12, n, (short)200, (byte)20, optionsLabel2, button4 -> this.onMultiplayerClick(button4));
      int n14 = this.getCenterX() - 100;
      n3 = n + 24;
      optionsLabel2 = Augustus.getInstance().getName();
      this.addButton(n14, n3, (short)200, (byte)20, optionsLabel2, button5 -> this.onClientMenuClick(button5));
   }

   private void onMultiplayerClick(GuiButton button) {
      this.openScreen(new GuiMultiplayer(this));
   }

   @Override
   public void drawContent(int n, int n2, float f) {
      BlurShadowManager.enableBlurOnce();
      BlurShadowManager.enableNormalPassOnce();
      BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderMenuBackdrop(shaderRenderPass));
      super.drawContent(n, n2, f);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.drawCenteredScaledString(Augustus.getInstance().getName(), (float)this.getCenterX() / 5.0F, (((float)this.getCenterY() / 2.0F - 24.0F) / 5.0F - 3.0F) / 2.0F + 3.0F, 5.0F);
   }

   private void onOptionsClick(GuiButton button) {
      this.openScreen(new GuiOptions(this, this.mc.gameSettings));
   }
}
