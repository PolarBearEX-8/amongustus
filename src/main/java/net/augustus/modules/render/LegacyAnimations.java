package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;

public class LegacyAnimations extends Module {
   private final StringValue heartAnimationMode;
   private final BooleanValue damageAnimation;
   private final BooleanValue itemHeld;
   private final BooleanValue swing;
   private final BooleanValue debugHitBox;
   private final BooleanValue rod;

   public LegacyAnimations() {
       super("LegacyAnimations", "AlteAnimationen", new Color(50, 168, 104), Categorys.RENDER);
      this.heartAnimationMode = new StringValue(this, "HeartAnimationMode", "Default", new String[]{"Default", "Legacy", "Minimal"});
      this.damageAnimation = new BooleanValue(this, "DamageAnimation", true);
      this.itemHeld = new BooleanValue(this, "ItemHeld", false);
      this.swing = new BooleanValue(this, "Swing", true);
      this.debugHitBox = new BooleanValue(this, "DebugHitBox", true);
      this.rod = new BooleanValue(this, "Rod", true);
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
   }

   public boolean isDamageAnimationEnabled() {
      return this.isToggled() && this.damageAnimation.getValue();
   }

   public boolean isMinimalHeartAnimation() {
      return this.isToggled() && this.heartAnimationMode.getSelectedValue().equals("Minimal");
   }

   public boolean isLegacyHeartAnimation() {
      return this.isToggled() && this.heartAnimationMode.getSelectedValue().equals("Legacy");
   }

   public boolean isLegacyItemHeldEnabled() {
      return this.isToggled() && this.itemHeld.getValue();
   }

   public boolean isLegacyDebugHitBoxEnabled() {
      return this.isToggled() && this.debugHitBox.getValue();
   }

   public boolean isLegacyRodEnabled() {
      return this.isToggled() && this.rod.getValue();
   }

   public boolean shouldPlayLegacySwing() {
      return this.isToggled()
         && this.swing.getValue()
         && mc.thePlayer.getCurrentEquippedItem() != null
         && mc.thePlayer.isSwingInProgress
         && !mc.thePlayer.isEating()
         && !mc.thePlayer.isBlocking();
   }


}
