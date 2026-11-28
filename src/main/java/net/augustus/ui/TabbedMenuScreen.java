package net.augustus.ui;

import net.augustus.render.RenderUtil;
import net.augustus.ui.menu.ClientMenuTab;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.utils.render.ShaderRenderPass;
import java.io.IOException;
import java.util.List;
import net.augustus.modules.misc.ClientSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;

public class TabbedMenuScreen extends AugustusGuiScreen {
   public static final int TAB_BUTTON_HEIGHT = 20;
   private final List tabs;
   public static final int HEADER_HEIGHT = 52;
   public static final int TAB_SPACING = 4;
   public static final int SIDEBAR_WIDTH = 102;
   private int selectedTabIndex;

   private void onTabSelected(ClientMenuTab clientMenuTab, GuiButton button) {
      this.selectedTabIndex = this.getTabs().indexOf(clientMenuTab);
      ClientMenuTab selectedTab = this.getSelectedTab();
      int height2 = this.height;
      int width2 = this.width;
      selectedTab.onResize(this.mc, width2, height2);
   }

   @Override
   public void handleKeyboardInput() throws IOException {
      super.handleKeyboardInput();
      if (this.getSelectedTab() != null) {
         this.getSelectedTab().handleKeyboardInput();
      }
   }

   public List getTabs() {
      return this.tabs;
   }

   public TabbedMenuScreen(List list, GuiScreen screen, int n) {
       super(screen);
      this.selectedTabIndex = n;
      this.setParentScreen(screen);
      this.tabs = list;

      for (ClientMenuTab clientMenuTab : (Iterable<ClientMenuTab>)(this.tabs)) {
         if (this.shouldHideTab(clientMenuTab)) {
            list.remove(clientMenuTab);
         }
      }

      this.disableDefaultBackground();
   }

   public boolean shouldHideTab(ClientMenuTab clientMenuTab) {
      return false;
   }

   @Override
   public void updateScreen() {
      super.updateScreen();
      if (this.getSelectedTab() != null) {
         this.getSelectedTab().updateScreen();
      }
   }

   @Override
   public void onGuiClosed() {
      super.onGuiClosed();
      if (this.getSelectedTab() != null) {
         this.getSelectedTab().onGuiClosed();
      }
   }

   @Override
   public void initContent() {
      super.initContent();
      NO_TOGGLE_SCREENS.add(this);
      int n = 0;

      for (ClientMenuTab clientMenuTab : (Iterable<ClientMenuTab>)(this.getTabs())) {
         this.addButton((byte)5, 52 + n * 24, (byte)90, (byte)20, clientMenuTab.getTabTitle(), button -> this.onTabSelected(clientMenuTab, button));
         n++;
      }

      this.getSelectedTab().setWorldAndResolution(Minecraft.getMinecraft(), this.width, this.height);
   }

   @Override
   public void onResize(Minecraft mc2, int n, int n2) {
      super.onResize(mc2, n, n2);
      if (this.getSelectedTab() != null) {
         this.getSelectedTab().onResize(mc2, n, n2);
      }
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.getSelectedTab().disableDefaultBackground();
      this.getSelectedTab().drawScreen(mouseX, mouseY, partialTicks);
      BlurShadowManager.flushRenderQueues(new ScaledResolution(this.mc), true);
      BlurShadowManager.enableOneShotPass(true, false);
      BlurShadowManager.submitRenderTask(false, shaderRenderPass -> {
         RenderUtil.drawRect(0, 50, 100, this.height, Integer.MIN_VALUE);
         RenderUtil.drawRect(0, 0, this.width, 50, Integer.MIN_VALUE);
      });
      super.drawScreen(mouseX, mouseY, partialTicks);
      GL11.glPushMatrix();
      GL11.glTranslatef(51.0F, 0.0F, 0.0F);
      String str = EnumChatFormatting.RED + this.getSelectedTab().getTabTitle();
      float f = (float)this.width / 4.0F;
      float f2 = 2.0F;
      this.drawCenteredScaledString(str, f, 8.0F, f2);
      GL11.glPopMatrix();
      ClientSettings.renderMainMenuAccountInfo();
      String statusText = ClientMenuTab.getStatusText(this.getSelectedTab());
      if (statusText != null) {
         float f5 = (float)(102 + this.width) / 2.0F;
         int rgb = ClientMenuTab.getStatusColor(this.getSelectedTab()).getRGB();
         this.drawCenteredString(statusText, f5, 38.0F, rgb);
      }
   }

   public ClientMenuTab getSelectedTab() {
      return (ClientMenuTab)this.getTabs().get(this.selectedTabIndex);
   }

   @Override
   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      if (this.getSelectedTab() != null) {
         this.getSelectedTab().handleMouseInput();
      }
   }
}
