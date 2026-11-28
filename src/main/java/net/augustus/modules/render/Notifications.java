package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.RenderListener;
import net.augustus.ClientManagers;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.ui.notifications.NotificationManager;
import net.augustus.ui.notifications.HudNotification;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.ColorUtil;
import net.augustus.Augustus;
import net.augustus.utils.render.BlurShaderRenderer;
import net.augustus.settings.options.NotificationCategoryOption;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.MathHelper;
import net.optifine.config.Config;

public class Notifications extends Module implements RenderListener {
   private static final BlurShaderRenderer BLUR_RENDERER;
   private static final String[] COLOR_MODES = new String[]{"Client color", "Notification type", "Fade", "Blank"};
   public final BooleanValue blur;
   public final BooleanValue avoidDuplications;
   public final StringValue position;
   public final DoubleValue duplicateTime;
   public final DoubleValue showDuration;
   public final DoubleValue speed;
   public final StringValue groupColorMode;
   public final StringValue progressColorMode;
   public final MultiOptionSetting notificationTypes;

   public Notifications() {
       super("Notifications", "Benachrichtigungen", Color.WHITE, Categorys.RENDER);
      this.blur = new BooleanValue(this, "Blur", true);
      this.avoidDuplications = new BooleanValue(this, "AvoidDuplications", true);
      this.position = new StringValue(this, "Position", "Bottom right", new String[]{"Top right", "Top left", "Bottom right", "Bottom left"});
      this.duplicateTime = new DoubleValue(this, "DuplicateTime", 0.20000000298023224, 0.0, 1.0, 1);
      this.showDuration = new DoubleValue(this, "ShowDuration", 2.5, 0.0, 10.0, 1);
      this.speed = new DoubleValue(this, "Speed", 5.0, 1.0, 10.0, 0);
      this.groupColorMode = new StringValue(this, "GroupColorMode", "Blank", COLOR_MODES);
      this.progressColorMode = new StringValue(this, "ProgressColorMode", "Notification type", COLOR_MODES);
      this.notificationTypes = new MultiOptionSetting(this, "NotificationTypes", NotificationCategoryOption.values());
      this.setToggled(true, false);
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32, 2147483647);
   }

   public void onRender2DPost(float partialTicks, ScaledResolution sr) {
      this.renderNotifications(false);
      RenderUtil.resetGlColorState();
   }

   public void renderNotifications(boolean flag) {
      if (!NotificationManager.isEmpty()) {
         NotificationManager.updateNotifications();
         if (this.blur.getValue() && !Config.isFastRender() && !Config.isAntialiasing() && !Config.isAnisotropicFiltering()) {
            BLUR_RENDERER.beginMaskCapture(true, flag);
            NotificationManager.renderNotifications();
            BLUR_RENDERER.endMaskCapture();
            BLUR_RENDERER.renderBlurPass(flag, (int)ClientManagers.moduleManager.blur.sigma.getValue());
         }

         NotificationManager.renderNotifications();
      }
   }

   public int resolveNotificationColor(HudNotification hudNotification, String str) {
      switch (str) {
         case "Client color":
            return Augustus.getInstance().getClientColor();
         case "Notification type":
            return hudNotification.getLevel().getColor().getRGB();
         case "Fade":
            long time = hudNotification.elapsedTimer.getTime();
            return ColorUtil.blendColors(Color.RED, Color.GREEN, (double)(1.0F - MathHelper.clamp_float((float)time / (float)hudNotification.getTotalDuration(), 0.0F, 1.0F)));
         default:
            return Color.GRAY.getRGB();
      }
   }

   static {
      BLUR_RENDERER = new BlurShaderRenderer();
   }

}
