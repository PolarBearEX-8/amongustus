package net.augustus.ui.menu;

import net.augustus.Augustus;
import net.augustus.ClientManagers;
import net.augustus.render.RenderUtil;
import net.augustus.ui.AugustusGuiScreen;
import net.augustus.ui.widgets.ScrollBar;
import net.augustus.utils.shader.BackgroundShader;
import net.augustus.utils.shader.BackgroundShaderManager;
import java.io.IOException;
import java.util.List;
import net.augustus.modules.misc.ClientSettings;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;

public class BackgroundMenuTab extends ClientMenuTab {
   private final ScrollBar scrollBar;

   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.scrollBar.handleMouseWheel();
   }

   public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
      super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      double d = (double)mouseY;
      this.scrollBar.handleMouseDrag(d);
   }

   public void initContent() {
      super.initContent();
      this.scrollBar.setItemCount(this.getBackgroundShaders().size());
      float f = (float)(this.width + 102) / 2.0F;
      int n = (int)(f + 152.0F);
      int n2 = (int)(f + 155.0F);
      int height2 = this.height;
      this.scrollBar.setBounds(n, (byte)52, n2, height2);
   }

   public void drawContent(int n, int n2, float f) {
      super.drawContent(n, n2, f);
      this.scrollBar.updateAndDraw();
      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, 52.0F, 0.0F);
      int n3 = 0;
      float f2 = (float)(this.width + 102) / 2.0F;

      for (BackgroundShader backgroundShader : (Iterable<BackgroundShader>)(this.getBackgroundShaders())) {
         float f3 = (float)((double)((float)((double)n3 * this.scrollBar.itemHeight)) + this.scrollBar.getClampedScrollOffset());
         double d = this.scrollBar.itemHeight + ((double)n3 * this.scrollBar.itemHeight - 2.0);
         if (ClientManagers.moduleManager.clientSettings.roundedButtons.getValue()) {
            RenderUtil.drawRoundedRect(
               f2 - 150.0F,
               f3,
               f2 + 150.0F,
               (float)((double)((float)d) + this.scrollBar.getClampedScrollOffset()),
               (float)ClientManagers.moduleManager.clientSettings.roundFactor.getValue(),
               1,
               -2147483648
            );
         } else {
            RenderUtil.drawRect(f2 - 150.0F, f3, f2 + 150.0F, (float)((double)((float)d) + this.scrollBar.getClampedScrollOffset()), -2147483648);
         }

         BackgroundShader currentShader = Augustus.getInstance().getBackgroundShaderManager().getCurrentShader();
         Object name = backgroundShader.getName();
         String str = (currentShader == backgroundShader ? EnumChatFormatting.GREEN.toString() : EnumChatFormatting.RESET.toString()) + name;
         float f4 = f2 / 1.5F;
         float f5 = f3 / 1.5F + 2.0F;
         float f6 = 1.5F;
         ((AugustusGuiScreen)this).drawCenteredScaledString(str, f4, f5, f6);
         n3++;
      }

      GL11.glPopMatrix();
   }

   private List getBackgroundShaders() {
      return Augustus.getInstance().getBackgroundShaderManager().getSortedShaders();
   }

   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      mouseY -= 52;
      int n = 0;

      for (BackgroundShader backgroundShader : (Iterable<BackgroundShader>)(this.getBackgroundShaders())) {
         float f = (float)(this.width + 102) / 2.0F;
         float f2 = (float)((double)((float)((double)n * this.scrollBar.itemHeight)) + this.scrollBar.getClampedScrollOffset());
         if (RenderUtil.isPointInBounds(
            mouseX,
            mouseY,
            (double)(f - 150.0F),
            (double)f2,
            (double)(f + 150.0F),
            (double)((float)((double)((float)(this.scrollBar.itemHeight + ((double)n * this.scrollBar.itemHeight - 2.0))) + this.scrollBar.getClampedScrollOffset()))
         )) {
            Augustus.getInstance().getBackgroundShaderManager().selectShader(backgroundShader.getName().toLowerCase());
            ClientSettings.playButtonClickSound();
         }

         n++;
      }

      double d = (double)mouseX;
      double d3 = (double)mouseY;
      this.scrollBar.handleMouseClick(d, d3);
   }

   public BackgroundMenuTab() {
       super("Background");
      this.scrollBar = new ScrollBar(21);
   }

}
