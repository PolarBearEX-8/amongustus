package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventEntityHurt;
import net.augustus.events.listeners.EntityHurtListener;
import net.augustus.settings.ColorSetting;
import net.augustus.events.EventEntityColor;
import net.augustus.events.listeners.EntityColorListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.animation.AnimationUtil;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.minecraft.util.MathHelper;

public class EntityColor extends Module implements EntityColorListener, EntityHurtListener {
   private final ColorSetting hitColor;
   private final StringValue hitColorAnimation;
   private final BooleanValue reverseAnimation;
   private final ColorSetting color2;

   public EntityColor() {
       super("EntityColor", "EntitätFarbe", new Color(12, 171, 152), Categorys.RENDER);
      this.hitColor = new ColorSetting(this, "HitColor", new Color(0, 255, 0, 36));
      this.hitColorAnimation = new StringValue(this, "HitColorAnimation", "easeInSine", AnimationUtil.getEasingNames(true));
      this.reverseAnimation = (BooleanValue)new BooleanValue(this, "ReverseAnimation", false).withVisibility(() -> this.isReverseAnimationVisible());
      this.color2 = new ColorSetting(this, "Color", new Color(0, 0, 0, 0));
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(10);
      this.toggleEventSubscription(50);
   }

   @Override
   public void onEntityColor(EventEntityColor event) {
      if (event.getEntity().hurtTime > 0) {
         if (!this.hitColorAnimation.getSelectedValue().equals("None")) {
            this.hitColor.getColorInt((byte)0);
            event.setRed(this.hitColor.getRedFloat());
            event.setGreen(this.hitColor.getGreenFloat());
            event.setBlue(this.hitColor.getBlueFloat());
            event.setAlpha((float)MathHelper.clamp_int((int)((double)this.hitColor.getAlpha() * event.getEntity().colorAnimation.getEasedProgress()), 0, 255) / 255.0F);
            return;
         }

         event.setColor(this.hitColor.getColorInt((byte)0));
      } else if (this.color2.getPrimaryAlpha() > 10) {
         event.setColor(this.color2.getColorInt((byte)0));
         event.setOverrideColor(true);
      }
   }

   @Override
   public void onEntityHurt(EventEntityHurt event) {
      if (!this.hitColorAnimation.getSelectedValue().equals("None")) {
         event.getEntity().colorAnimation.start(500, AnimationUtil.parseEasingType(this.hitColorAnimation.getSelectedValue()), this.reverseAnimation.getValue() ? 1 : 0, true);
      }
   }

   private boolean isReverseAnimationVisible() {
      return !this.hitColorAnimation.getSelectedValue().equals("None");
   }

}
