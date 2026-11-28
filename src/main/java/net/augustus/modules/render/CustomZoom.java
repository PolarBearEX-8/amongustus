package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.MouseMoveListener;
import net.augustus.events.EventZoom;
import net.augustus.events.listeners.ZoomListener;
import net.augustus.events.EventMouseMove;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.animation.AnimationUtil;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;

public class CustomZoom extends Module implements ZoomListener, MouseMoveListener {
   public final DoubleValue zoomFactor;
   public final StringValue animationModes;
   public final DoubleValue animationTime;
   public final BooleanValue fastZoom;
   private final AnimationUtil zoomAnimation;
   private boolean wasZoomKeyDown;

   public CustomZoom() {
       super("CustomZoom", "EinstellbarerZoom", new Color(252, 186, 3), Categorys.RENDER);
      this.zoomFactor = new DoubleValue(this, "ZoomFactor", 4.0, 1.0, 10.0, 2);
      this.animationModes = new StringValue(this, "AnimationModes", "easeOutQuint", AnimationUtil.getEasingNames(true));
      DoubleValue animationTimeSetting = new DoubleValue(this, "AnimationTime", 200.0, 0.0, 1000.0, 0);
      SettingVisibilityCondition condition = () -> {
         return !this.animationModes.getSelectedValue().equalsIgnoreCase("None");
      };
      this.animationTime = animationTimeSetting.visibleWhen(condition);
      this.fastZoom = new BooleanValue(this, "FastZoom", false);
      this.zoomAnimation = new AnimationUtil(0.0F, 0.0F, 0.0F, 0.0F);
      this.wasZoomKeyDown = false;
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(46);
      this.toggleEventSubscription(19);
   }

   @Override
   public void onZoom(EventZoom event) {
      float f = (float)this.zoomFactor.getValue() - 1.0F;
      if (!this.animationModes.getSelectedValue().equals("None")) {
         if (this.zoomAnimation.getLinearProgress() < 1.0) {
            f = 1.0F + (float)((double)f * this.zoomAnimation.getEasedProgress());
         } else {
            f++;
         }
      }

      event.setFovMultiplier(f);
   }

   @Override
   public void onMouseMove(EventMouseMove event) {
      if (mc.gameSettings.ofKeyBindZoom.isKeyDown() && !this.wasZoomKeyDown) {
      }

      this.wasZoomKeyDown = mc.gameSettings.ofKeyBindZoom.isKeyDown();
   }

   public AnimationUtil getZoomAnimation() {
      return this.zoomAnimation;
   }

}
