package net.augustus.ui.proxy;

import net.augustus.Augustus;
import net.augustus.render.RenderUtil;
import net.augustus.utils.animation.AnimationUtil;
import net.augustus.utils.animation.EasingType;
import net.augustus.utils.input.KeyboardInputUtil;
import net.augustus.utils.network.ProxyEntry;
import java.awt.Color;
import net.augustus.utils.TimeHelper;
import net.augustus.utils.interfaces.MC;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.opengl.GL11;

public class ProxyListEntry implements MC {
   private boolean hovered;
   private final TimeHelper timeHelper = new TimeHelper();
   private boolean selected;
   private int renderY;
   private int renderHeight;
   private int renderWidth;
   private final ProxyEntry proxyEntry;
   private final AnimationUtil hoverAnimation = new AnimationUtil(0.0F, 0.0F, 0.0F, 0.0F);
   private int renderX;

   public boolean isHovered() {
      return this.hovered;
   }

   public void drawEntry(int n, int n2, int n3, int n4, int n5, int n6, boolean flag) {
      this.renderX = n3;
      this.renderY = n4;
      this.renderWidth = n5;
      this.renderHeight = n6;
      this.selected = flag;
      FontRenderer fontRendererObj = mc.fontRendererObj;
      float f = (float)n5 / 2.0F;
      float f2 = (float)n6 / 2.0F;
      float f3 = (float)n3 + f;
      float f4 = (float)n4 + f2;
      GL11.glPushMatrix();
      boolean isHovered = RenderUtil.isHovered((float)n3, (float)n4, (float)n5, (float)n6, n, n2);
      if (this.hovered != isHovered) {
         this.hoverAnimation.start(100, EasingType.easeInSine, isHovered ? 1 : 0, false);
      }

      String str;
      StringBuilder sb;
      float f5 = (float)(1.0 - this.hoverAnimation.getEasedProgress() * 0.019999999552965164);
         GL11.glScaled((double)f5, (double)f5, 0.0);
         f3 /= f5;
         f4 /= f5;
         this.hovered = isHovered;
         RenderUtil.drawRect((double)(f3 - f), (double)(f4 - f2), (double)(f3 + f), (double)(f4 + f2), new Color(0, 0, 0, flag ? 200 : 100).getRGB());
         GL11.glScaled((double)(1.0F / f5), (double)(1.0F / f5), 0.0);
         ProxyEntry proxy = Augustus.getInstance().getProxy();
         StringBuilder sb2 = new StringBuilder();
         short s = 923;
         sb = sb2.append("§7IP: ");
if (proxy != null) {
if (proxy.matches(this.proxyEntry)) {
str = "§a";
} else {
str = this.isFavorite() ? "§6" : "§f";
}
} else {
str = this.isFavorite() ? "§6" : "§f";
}

      fontRendererObj.drawString(sb.append(str).append(this.proxyEntry.getHost()).toString(), (float)n3 + 5.5F, (float)n4 + 3.5F, Color.white.getRGB());
      StringBuilder sb3 = new StringBuilder();
      short s2 = 29509;
      StringBuilder sb4 = sb3.append("§7Port: ");
      if (this.isFavorite()) {
         str = "§6";
      } else {
         short s3 = 23372;
         str = "§f";
      }

      fontRendererObj.drawString(
         sb4.append(str).append(this.proxyEntry.getPort()).toString(), (float)n3 + 5.5F, (float)n4 + 7.0F + (float)fontRendererObj.FONT_HEIGHT, Color.white.getRGB()
      );
      StringBuilder sb5 = new StringBuilder();
      short s4 = 1778;
      sb5 = sb5.append("§7Ping: ");
      String str2;
      if (this.isFavorite()) {
         str2 = "§6";
      } else {
         short s5 = 23372;
         str2 = "§f";
      }

      String str3 = sb5.append(str2).append(this.proxyEntry.getPing()).toString();
      fontRendererObj.drawString(str3, (float)n3 + ((float)(n5 - fontRendererObj.getStringWidth(str3)) - 5.5F), (float)n4 + 3.5F, Color.white.getRGB());
      GL11.glPopMatrix();
   }

   public void setFavorite(boolean favorite) {
      this.proxyEntry.setMarked(favorite);
   }

   public TimeHelper getTimeHelper() {
      return this.timeHelper;
   }

   public ProxyListEntry(ProxyEntry proxyEntry2) {
      this.proxyEntry = proxyEntry2;
   }

   public int getRenderHeight() {
      return this.renderHeight;
   }

   public int getRenderY() {
      return this.renderY;
   }

   public boolean isFavorite() {
      return this.proxyEntry.isMarked();
   }

   public void mouseClicked(int n, int n2, int n3, ProxyManagerScreen proxyManagerScreen) {
      if (n3 == 0 && RenderUtil.isHovered((float)this.renderX, (float)this.renderY, (float)this.renderWidth, (float)this.renderHeight, n, n2)) {
         if (KeyboardInputUtil.isKeyDown(157) || KeyboardInputUtil.isKeyDown(29)) {
            this.setFavorite(!this.isFavorite());
            return;
         }

         if (!this.timeHelper.reached(250L)) {
            String str = this.proxyEntry.getHost()
               + ":"
               + this.proxyEntry.getPort()
               + (
                  !this.proxyEntry.getUsername().equals("")
                     ? ":" + this.proxyEntry.getUsername() + (!this.proxyEntry.getPassword().equals("") ? ":" + this.proxyEntry.getPassword() : "")
                     : ""
               );
            proxyManagerScreen.connectToProxy(str, true);
         }

         this.timeHelper.reset();
      }
   }

   public ProxyEntry getProxyEntry() {
      return this.proxyEntry;
   }

   public boolean isSelected() {
      return this.selected;
   }

   public int getRenderWidth() {
      return this.renderWidth;
   }

   public int getRenderX() {
      return this.renderX;
   }

   public AnimationUtil getHoverAnimation() {
      return this.hoverAnimation;
   }

}
