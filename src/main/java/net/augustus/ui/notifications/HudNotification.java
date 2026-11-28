package net.augustus.ui.notifications;

import net.augustus.Augustus;
import net.augustus.render.RenderUtil;
import net.augustus.modules.render.Notifications;
import net.augustus.utils.TimeHelper;
import net.augustus.utils.interfaces.MC;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

public class HudNotification implements MC {
   public final TimeHelper elapsedTimer;
   public final int slideOutStartMillis;
   public int stackCount;
   private static final int PADDING = 3;
   public static final int NOTIFICATION_HEIGHT = 25;
   public TimeHelper stackDecayTimer;
   private final NotificationLevel level;
   private final String description;
   private final int width;
   public static final int SLIDE_ANIMATION_MILLIS = 750;
   private final String title;

   public void render(int n) {
      Notifications notifications = Augustus.getInstance().getModuleManager().notifications;
      int selectedIndex = notifications.position.getSelectedIndex();
      double d = this.calculateSlideOffset();
      float f = (float)(3.0 + d - (double)this.width);
      float f2 = (float)(3 + n * 28);
      ScaledResolution sr = new ScaledResolution(mc);
      GL11.glPushMatrix();
      GL11.glTranslatef(
         selectedIndex != 1 && selectedIndex != 3 ? (float)(sr.getScaledWidth() - this.width) - f : f,
         selectedIndex < 2 ? f2 : (float)(sr.getScaledHeight() - 25) - f2,
         0.0F
      );
      RenderUtil.drawRect(0, 0, this.width, 25, -2147483648);
      if (this.stackCount <= 0) {
         int n2 = notifications.resolveNotificationColor(this, notifications.progressColorMode.getSelectedValue());
         RenderUtil.drawRect(
            0.0, 23.0, (double)((float)this.width / 100.0F * (float)this.elapsedTimer.getTime() * (100.0F / (float)this.getTotalDuration())), 25.0, n2
         );
      }

      FontRenderer fontRendererObj = mc.fontRendererObj;
      String title2 = this.title;
      String selectedValue2 = notifications.groupColorMode.getSelectedValue();
      fontRendererObj.drawStringWithShadow(title2, 2.0F, 2.0F, notifications.resolveNotificationColor(this, selectedValue2));
      mc.fontRendererObj.drawStringWithShadow(this.description, 2.0F, (float)(4 + mc.fontRendererObj.FONT_HEIGHT), -1);
      if (this.stackCount > 0) {
         mc.fontRendererObj.drawStringWithShadow("x" + (this.stackCount + 1), (float)(this.width - 2 - mc.fontRendererObj.getStringWidth("x" + (this.stackCount + 1))), 2.0F, -1);
      }

      GL11.glPopMatrix();
   }

   public String getTitle() {
      return this.title;
   }

   public int getTotalDuration() {
      return 750 + this.slideOutStartMillis;
   }

   public boolean isExpired() {
      return this.elapsedTimer.reached((long)this.getTotalDuration());
   }

   public NotificationLevel getLevel() {
      return this.level;
   }

   public HudNotification(String str, String str2, NotificationLevel notificationLevel) {
       super();
      this.title = str;
      this.description = str2;
      this.level = notificationLevel;
      this.width = Math.max(mc.fontRendererObj.getStringWidth(str), mc.fontRendererObj.getStringWidth(str2)) + 3;
      this.slideOutStartMillis = 750 + (int)(Augustus.getInstance().getModuleManager().notifications.showDuration.getValue() * 1000.0);
      this.elapsedTimer = new TimeHelper();
   }

   private double calculateSlideOffset() {
      long time = this.elapsedTimer.getTime();
      double value = Augustus.getInstance().getModuleManager().notifications.speed.getValue();
      double d;
      if (time < 750L) {
         d = Math.tanh((double)time / 750.0 * value) * (double)this.width;
      } else if (time > (long)this.slideOutStartMillis) {
         d = Math.tanh(value - (double)(time - (long)this.slideOutStartMillis) / 750.0 * value) * (double)this.width;
      } else {
         d = (double)this.width;
      }

      return d;
   }

   public String getDescription() {
      return this.description;
   }

   public void incrementStack() {
      this.stackDecayTimer = new TimeHelper();
      this.stackCount++;
   }
}
