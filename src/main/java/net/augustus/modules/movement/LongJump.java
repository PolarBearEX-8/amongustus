package net.augustus.modules.movement;

import java.awt.Color;
import java.util.function.BiFunction;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.MotionListener;
import net.augustus.settings.Setting;
import net.augustus.settings.ModeSetting;
import net.augustus.modules.modes.longjump.OldCubeCraftLongJumpMode;
import net.augustus.modules.modes.longjump.IntaveBoatLongJumpMode;
import net.augustus.modules.modes.longjump.MotionModifierLongJumpMode;
import net.augustus.modules.modes.longjump.OldZoneCraftLongJumpMode;
import net.augustus.modules.modes.longjump.MinemenLongJumpMode;
import net.augustus.modules.modes.longjump.NcpLongJumpMode;
import net.augustus.settings.CustomSubModeFactory;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.events.EventMotion;
import net.augustus.settings.BooleanValue;

public class LongJump extends Module implements MotionListener {
   private final ModeSetting modes;
   private final BooleanValue autoDisable;
   private int groundTicks;
   public boolean pendingJump;

   public LongJump() {
       super("LongJump", "Weitsprung", new Color(243, 45, 134), Categorys.MOVEMENT);
      BiFunction[] biFunctions = new BiFunction[]{
         NcpLongJumpMode.factory(),
         OldCubeCraftLongJumpMode.factory(),
         OldZoneCraftLongJumpMode.factory(),
         MinemenLongJumpMode.factory(),
         IntaveBoatLongJumpMode.factory()
      };
      this.modes = new ModeSetting(this, "Modes", "NCP", biFunctions).enableCustomModes(MotionModifierLongJumpMode.factory());
      this.autoDisable = (BooleanValue)new BooleanValue(this, "AutoDisable", true).withVisibility(() -> this.isAutoDisableVisible());
      this.groundTicks = 0;
   }

   @Override
   public void onEnable() {
      this.groundTicks = 0;
      this.pendingJump = true;
   }

   @Override
   public void onDisable() {
      mc.getTimer().timerSpeed = 1.0F;
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(18);
   }

   @Override
   public void onPreMotion(EventMotion event) {
      this.setSuffix(this.modes.getSelectedValue());
      if (!this.modes.getSelectedValue().equals("IntaveBoat") && mc.thePlayer.onGround) {
         if (this.groundTicks > 0) {
            if (this.autoDisable.getValue()) {
               this.setToggled(false);
               return;
            }
         }

         this.groundTicks++;
         if (this.groundTicks % 2 == 0) {
            this.pendingJump = true;
         }
      }
   }

   private boolean isAutoDisableVisible() {
      return !this.modes.getSelectedValue().equals("IntaveBoat");
   }



}
