package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.settings.ColorSetting;
import net.augustus.font.FontManager;
import net.augustus.ui.notification.ToggleNotification;
import net.augustus.Augustus;
import net.augustus.clickgui.ClickGuiScreen;
import net.augustus.utils.SystemTrayNotifier;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;

public class OldNotification extends Module {
   public final StringValue modes;
   public final StringValue side;
   public final DoubleValue y;
   public final DoubleValue animationTime;
   public final DoubleValue animationDuration;
   public final ColorSetting color2;
   public final BlurShadowSettings blurShadowSettings;
   public java.util.ArrayList activeNotifications;

   public OldNotification() {
       super("OldNotification", "AlteBenachrichtigung", new Color(126, 133, 222), Categorys.RENDER);
      this.modes = new StringValue(this, "Modes", "Windows", new String[]{"Windows", "Basic"});
      this.side = (StringValue)new StringValue(this, "Side", "Right", new String[]{"Left", "Right"}).withVisibility(() -> this.isSideVisible());
      DoubleValue doubleSetting = new DoubleValue(this, "Y", 100.0, 0.0, 100.0, 2);
      SettingVisibilityCondition condition2 = () -> {
         return this.modes.getSelectedValue().equals("Basic");
      };
      this.y = doubleSetting.visibleWhen(condition2);
      this.animationTime = new DoubleValue(this, "AnimationTime", 400.0, 0.0, 1000.0, 0).visibleWhen(() -> this.isAnimationTimeVisible());
      this.animationDuration = new DoubleValue(this, "AnimationDuration", 2000.0, 0.0, 5000.0, 0).visibleWhen(() -> this.isAnimationDurationVisible());
      ColorSetting colorSetting = new ColorSetting(this, "Color", Augustus.getInstance().getClientColor());
      SettingVisibilityCondition condition5 = () -> {
         return this.modes.getSelectedValue().equals("Basic");
      };
      this.color2 = (ColorSetting)colorSetting.withVisibility(condition5);
      this.blurShadowSettings = new BlurShadowSettings(this).withCustomShadowColor(Color.black);
      this.activeNotifications = new java.util.ArrayList();
   }

   public void pushModuleToggleNotification(Module module) {
      switch (this.modes.getSelectedValue()) {
         case "Windows":
            String str = "Module (" + module.getModuleName() + ") Notification";
            StringBuilder sb2 = new StringBuilder().append(module.getModuleName()).append(" toggled ");
            String str2;
            if (module.isToggled()) {
               str2 = "on";
            } else {
               str2 = "off";
            }

            SystemTrayNotifier.displayNotification(str, sb2.append(str2).toString());
            break;
         case "Basic":
            if (!(mc.currentScreen instanceof ClickGuiScreen)) {
               String str4 = module.getModuleName() + " toggled ";
               int n;
               if (this.side.getSelectedValue().equalsIgnoreCase("Right")) {
                  n = mc.getFramebuffer().framebufferWidth - (int)(FontManager.esp32.getStringWidth(str4) + 10.0F);
               } else {
                  n = 0;
               }

               int n2 = n;
               int n3 = (int)((double)(mc.getFramebuffer().framebufferHeight - 60) * (this.y.getValue() / 100.0));
               java.util.ArrayList activeNotifications2 = this.activeNotifications;
               int n4 = (int)(FontManager.esp32.getStringWidth(str4) + 10.0F);
               int value = (int)this.animationTime.getValue();
               int value2 = (int)this.animationDuration.getValue();
               activeNotifications2.add(0, new ToggleNotification(n2, n3, n4, 60, module, value, value2, this.color2.getColorInt((byte)0)));
            }
      }
   }

   public void renderNotifications(float f) {
      boolean flag = false;

      for (ToggleNotification toggleNotification : (Iterable<ToggleNotification>)(this.activeNotifications)) {
         if (flag) {
            this.activeNotifications.remove(toggleNotification);
         }

         if (Math.abs(System.currentTimeMillis() - toggleNotification.getCreatedTime()) > (long)toggleNotification.getDisplayDuration()) {
            this.activeNotifications.remove(toggleNotification);
         } else {
            toggleNotification.render(f, this.blurShadowSettings);
            flag = true;
         }
      }
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
   }

   private boolean isAnimationDurationVisible() {
      return this.modes.getSelectedValue().equals("Basic");
   }

   private boolean isAnimationTimeVisible() {
      return this.modes.getSelectedValue().equals("Basic");
   }

   private boolean isSideVisible() {
      return this.modes.getSelectedValue().equals("Basic");
   }


}
