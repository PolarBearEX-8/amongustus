package net.augustus.ui.menu;

import net.augustus.ClientManagers;
import net.augustus.render.RenderUtil;
import net.augustus.ui.AugustusGuiScreen;
import net.augustus.ui.widgets.ScrollBar;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumChatFormatting;

public class CreditsMenuTab extends ClientMenuTab {
   public final ScrollBar scrollBar;
   private static final List CREDIT_SECTIONS;

   static {
      CREDIT_SECTIONS = Arrays.asList(new DeveloperCreditsSection(), new ContributorCreditsSection());
   }

   public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
      super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      double d = (double)mouseY;
      this.scrollBar.handleMouseDrag(d);
   }

   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.scrollBar.handleMouseWheel();
   }

   public CreditsMenuTab() {
       super("Credits");
      this.scrollBar = new ScrollBar(Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 1);
   }

   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      double d = (double)mouseX;
      double d3 = (double)mouseY;
      this.scrollBar.handleMouseClick(d, d3);
   }

   public void initContent() {
      super.initContent();
      int n = 0;

      for (CreditsSection creditsSection : (Iterable<CreditsSection>)(CREDIT_SECTIONS)) {
         n = (int)((double)n + (double)creditsSection.calculateHeight() / this.scrollBar.itemHeight);
      }

      this.scrollBar.setItemCount(n);
      float f = (float)(this.width + 102) / 2.0F;
      int n2 = (int)(f + 152.0F);
      int n3 = (int)(f + 155.0F);
      int height2 = this.height;
      this.scrollBar.setBounds(n2, (byte)52, n3, height2);
   }

   public void drawContent(int n, int n2, float f) {
      super.drawContent(n, n2, f);
      this.scrollBar.updateAndDraw();
      int n3 = 52;

      for (CreditsSection creditsSection : (Iterable<CreditsSection>)(CREDIT_SECTIONS)) {
         int n4 = (int)((double)n3 + this.scrollBar.getClampedScrollOffset());
         GlStateManager.pushMatrix();
         GlStateManager.translate((float)(this.width + 102) / 2.0F - 150.0F, (float)n4, 0.0F);
         if (ClientManagers.moduleManager.clientSettings.roundedButtons.getValue()) {
            RenderUtil.drawRoundedRect(
               0.0F,
               0.0F,
               300.0F,
               (float)(creditsSection.calculateHeight() - 2),
               (float)ClientManagers.moduleManager.clientSettings.roundFactor.getValue(),
               1,
               -2147483648
            );
         } else {
            RenderUtil.drawRect(0, 0, 300, creditsSection.calculateHeight() - 2, -2147483648);
         }

         String sectionTitle = creditsSection.getSectionTitle();
         float f2 = 2.0F;
         float f3 = 2.0F;
         float f4 = 75.0F;
         ((AugustusGuiScreen)this).drawCenteredScaledString(sectionTitle, f4, f3, f2);
         int n5 = 0;

         for (String str2 : (Iterable<String>)(creditsSection.getMemberLines())) {
            float f5 = (float)(29 + n5);
            float f6 = 3.0F;
            ((AugustusGuiScreen)this).drawString(str2, f6, f5);
            n5 += this.fontRendererObj.FONT_HEIGHT + 1;
         }

         int n6 = 39 + n5;
         String str3 = EnumChatFormatting.GOLD + "Social Media ";
         float f7 = (float)n6;
         float f8 = 3.0F;
         ((AugustusGuiScreen)this).drawString(str3, f8, f7);
         int n7 = 0;

         for (String str5 : (Iterable<String>)(creditsSection.getSocialLinks())) {
            if (str5.startsWith("Discord")) {
               str5 = EnumChatFormatting.BLUE.toString() + str5;
            }

            if (str5.startsWith("GitHub")) {
               str5 = EnumChatFormatting.GRAY.toString() + str5;
            }

            if (str5.startsWith("YouTube")) {
               str5 = EnumChatFormatting.RED.toString() + str5;
            }

            float f9 = (float)(10 + n6 + n7);
            float f10 = 3.0F;
            ((AugustusGuiScreen)this).drawString((String)str5, f10, f9);
            n7 += this.fontRendererObj.FONT_HEIGHT + 1;
         }

         GlStateManager.popMatrix();
         n3 += creditsSection.calculateHeight();
      }
   }

}
