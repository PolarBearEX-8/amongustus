package net.augustus.modules.movement;

import java.awt.Color;
import java.util.function.BiFunction;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.MotionListener;
import net.augustus.settings.ModeSetting;
import net.augustus.modules.modes.fastladder.PositionFastLadderMode;
import net.augustus.modules.modes.fastladder.MotionFastLadderMode;
import net.augustus.modules.modes.fastladder.VulcanFastLadderMode;
import net.augustus.modules.modes.fastladder.VerusFastLadderMode;
import net.augustus.modules.modes.fastladder.MotionModifierFastLadderMode;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.settings.CustomSubModeFactory;
import net.augustus.events.EventMotion;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;

public class FastLadder extends Module implements UpdateListener, MotionListener {
   public final ModeSetting modes;
   public final DoubleValue timer;
   public final BooleanValue stopOnEnd;
   public final BooleanValue vines;
   private boolean wasOnClimbable;
   private boolean timerModified;

   public FastLadder() {
       super("FastLadder", "Schnellleiter", new Color(166, 168, 50), Categorys.MOVEMENT);
      BiFunction[] biFunctions = new BiFunction[4];
      biFunctions[0] = (obj, obj2) -> new MotionFastLadderMode((FastLadder)obj, (SettingGroup)obj2);
      biFunctions[1] = (obj3, obj4) -> new PositionFastLadderMode((FastLadder)obj3, (SettingGroup)obj4);
      biFunctions[2] = (obj5, obj6) -> new VerusFastLadderMode((FastLadder)obj5, (SettingGroup)obj6);
      biFunctions[3] = (obj7, obj8) -> new VulcanFastLadderMode((FastLadder)obj7, (SettingGroup)obj8);
      this.modes = new ModeSetting(this, "Modes", "Motion", biFunctions).enableCustomModes((obj9, obj10, obj11) -> new MotionModifierFastLadderMode((FastLadder)obj9, (SettingGroup)obj10, (String)obj11));
      this.timer = new DoubleValue(this, "Timer", 1.0, 0.1, 2.0, 2);
      this.stopOnEnd = new BooleanValue(this, "StopOnEnd", true);
      this.vines = new BooleanValue(this, "Vines", true);
   }

   @Override
   public void onEnable() {
      this.wasOnClimbable = false;
      this.timerModified = false;
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(43);
      this.toggleEventSubscription(18);
   }

   @Override
   public void onPreUpdate() {
      this.setSuffix(this.modes.getSelectedValue());
      if (this.stopOnEnd.getValue() && !this.isOnClimbable() && this.wasOnClimbable) {
         mc.thePlayer.motionY = 0.2F;
      }

      this.wasOnClimbable = this.isOnClimbable();
   }

   @Override
   public void onPreMotion(EventMotion event) {
      if (this.isClimbing()) {
         this.timerModified = true;
         mc.getTimer().timerSpeed = (float)Math.max(0.1, this.timer.getValue());
      } else if (this.timerModified) {
         mc.getTimer().timerSpeed = 1.0F;
         this.timerModified = false;
      }
   }

   public boolean isClimbing() {
      return this.isOnClimbable() && mc.thePlayer.isCollidedHorizontally;
   }

   public boolean isOnClimbable() {
      int n = MathHelper.floor_double(mc.thePlayer.posX);
      int n2 = MathHelper.floor_double(mc.thePlayer.getEntityBoundingBox().minY);
      int n3 = MathHelper.floor_double(mc.thePlayer.posZ);
      Block block = mc.theWorld.getBlockState2(n, n2, n3).getBlock();
      return (block == Blocks.ladder || block == Blocks.vine && this.vines.getValue()) && (mc.thePlayer == null || !mc.thePlayer.isSpectator());
   }


}
