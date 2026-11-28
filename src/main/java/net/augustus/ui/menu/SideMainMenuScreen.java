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
import org.lwjgl.opengl.GL11;

public final class SideMainMenuScreen extends AbstractMainMenuScreen {
   private static final int BUTTON_HEIGHT = 20;
   private static final int BUTTON_WIDTH = 150;
   private static final int BUTTON_SPACING = 3;

   @Override
   public void initContent() {
      super.initContent();
      int n = this.getCenterY() - 51;
      String singleplayerLabel2 = this.singleplayerLabel;
      GuiButtonClickHandler guiButtonClickHandler = button -> {
         this.openScreen(new GuiSelectWorld(this));
      };
      n = this.addSidebarButton(n, singleplayerLabel2, guiButtonClickHandler);
      singleplayerLabel2 = this.multiplayerLabel;
      n = this.addSidebarButton(n, singleplayerLabel2, button2 -> this.onMultiplayerClick(button2));
      singleplayerLabel2 = Augustus.getInstance().getName();
      n = this.addSidebarButton(n, singleplayerLabel2, button3 -> this.onClientMenuClick(button3));
      singleplayerLabel2 = this.optionsLabel;
      GuiButtonClickHandler guiButtonClickHandler4 = button4 -> {
         this.openScreen(new GuiOptions(this, this.mc.gameSettings));
      };
      n = this.addSidebarButton(n, singleplayerLabel2, guiButtonClickHandler4);
      singleplayerLabel2 = this.quitLabel;
      n = this.addSidebarButton(n, singleplayerLabel2, button5 -> this.mc.shutdown());
   }

   private void renderSidebarBackdrop(ShaderRenderPass shaderRenderPass) {
      if (ClientManagers.moduleManager.clientSettings.roundedButtons.getValue()) {
         RenderUtil.drawRoundedRect(
            0.0F, 0.0F, 156.0F, (float)this.height, (float)ClientManagers.moduleManager.clientSettings.roundFactor.getValue(), 1, Integer.MIN_VALUE
         );
      } else {
         RenderUtil.drawRect(0, 0, 156, this.height, Integer.MIN_VALUE);
      }
   }

   private void onMultiplayerClick(GuiButton button) {
      this.openScreen(new GuiMultiplayer(this));
   }

   private void onClientMenuClick(GuiButton button) {
      this.openScreen(new ClientTabContainerScreen(this, 0));
   }

   private int addSidebarButton(int n, String str, GuiButtonClickHandler guiButtonClickHandler) {
      super.addButton(3, n, 150, 20, str, guiButtonClickHandler);
      return n + 20 + 3;
   }

   public SideMainMenuScreen() {
       super();
   }

   @Override
   public void drawContent(int n, int n2, float f) {
      BlurShadowManager.enableBlurOnce();
      BlurShadowManager.enableNormalPassOnce();
      BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderSidebarBackdrop(shaderRenderPass));
      super.drawContent(n, n2, f);
      this.drawCenteredScaledString(Augustus.getInstance().getName(), 26.0F + (float)this.getCenterX() / 3.0F, (float)this.getCenterY() / 3.0F - (float)this.mc.fontRendererObj.FONT_HEIGHT / 2.0F, 3.0F);
      GL11.glPushMatrix();
      GL11.glTranslated(78.0, (double)(this.getCenterY() + this.mc.fontRendererObj.FONT_HEIGHT * 2), 0.0);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glPopMatrix();
   }
}
