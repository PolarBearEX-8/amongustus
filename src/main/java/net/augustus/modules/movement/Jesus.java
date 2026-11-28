package net.augustus.modules.movement;

import java.awt.Color;
import java.util.function.BiFunction;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventLiquidJump;
import net.augustus.ClientManagers;
import net.augustus.settings.Setting;
import net.augustus.settings.ModeSetting;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.modes.jesus.IntaveNewJesusMode;
import net.augustus.modules.modes.jesus.DolphinJesusMode;
import net.augustus.modules.modes.jesus.SolidJesusMode;
import net.augustus.modules.modes.jesus.IntaveJesusMode;
import net.augustus.modules.modes.jesus.MotionModifierJesusMode;
import net.augustus.modules.modes.jesus.IgnoreJesusMode;
import net.augustus.modules.modes.jesus.Swim113JesusMode;
import net.augustus.modules.modes.jesus.SpeedJesusMode;
import net.augustus.modules.modes.jesus.JumpJesusMode;
import net.augustus.events.listeners.LiquidJumpListener;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.CustomSubModeFactory;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.block.BlockLiquid;

public class Jesus extends Module implements TickListener, LiquidJumpListener {
   public final ModeSetting mode;
   public final BooleanValue disableatsneakJump;
   private final StringValue ignoreFlowingWaterForce;
   public final BooleanValue customWaterUpMotion;
   public final DoubleValue customWaterUpMotionValue;
   public final BooleanValue customLavaUpMotion;
   public final DoubleValue customLavaUpMotionValue;

   public Jesus() {
       super("Jesus", "JesusChristus", new Color(39, 103, 146), Categorys.MOVEMENT);
      BiFunction[] biFunctions = new BiFunction[]{
         (obj, obj2) -> new SolidJesusMode((Jesus)obj, (SettingGroup)obj2),
         (obj3, obj4) -> new SpeedJesusMode((Jesus)obj3, (SettingGroup)obj4),
         null,
         null,
         null,
         null,
         null,
         null
      };
      biFunctions[2] = (obj5, obj6) -> new JumpJesusMode((Jesus)obj5, (SettingGroup)obj6);
      biFunctions[3] = (obj7, obj8) -> new Swim113JesusMode((Jesus)obj7, (SettingGroup)obj8);
      biFunctions[4] = (obj9, obj10) -> new DolphinJesusMode((Jesus)obj9, (SettingGroup)obj10);
      biFunctions[5] = (obj11, obj12) -> new IntaveJesusMode((Jesus)obj11, (SettingGroup)obj12);
      biFunctions[6] = (obj13, obj14) -> new IntaveNewJesusMode((Jesus)obj13, (SettingGroup)obj14);
      biFunctions[7] = (obj15, obj16) -> new IgnoreJesusMode((Jesus)obj15, (SettingGroup)obj16);
      this.mode = new ModeSetting(this, "Mode", "Solid", biFunctions).enableCustomModes((obj17, obj18, obj19) -> new MotionModifierJesusMode((Jesus)obj17, (SettingGroup)obj18, (String)obj19));
      this.disableatsneakJump = new BooleanValue(this, "DisableAtSneak/Jump", false);
      this.ignoreFlowingWaterForce = new StringValue(
         this, "IgnoreFlowingWaterForce", "Never", new String[]{"Never", "OnlyAtMovement", "OnlyWhileStanding", "Everytime"}
      );
      BooleanValue booleanValue = new BooleanValue(this, "CustomWaterUpMotion", false);
      SettingVisibilityCondition condition = () -> {
         return !this.mode.getSelectedValue().equals("1.13");
      };
      this.customWaterUpMotion = (BooleanValue)booleanValue.withVisibility(condition);
      this.customWaterUpMotionValue = new DoubleValue(this, "CustomWaterUpMotionValue", 0.042, 0.03, 0.15, 5).visibleWhen(() -> this.isCustomWaterMotionValueVisible());
      this.customLavaUpMotion = new BooleanValue(this, "CustomLavaUpMotion", false);
      DoubleValue customLavaUpMotionValue2 = new DoubleValue(this, "CustomLavaUpMotionValue", 0.042, 0.03, 0.15, 5);
      BooleanValue customLavaUpMotion2 = this.customLavaUpMotion;
      this.customLavaUpMotionValue = customLavaUpMotionValue2.visibleWhen(() -> customLavaUpMotion2.getValue());
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(67);
   }

   @Override
   public void onTick() {
      this.setSuffix(this.mode.getSelectedValue());
   }

   public boolean shouldIgnoreWaterFlow() {
      return this.isToggled()
         && (
            this.ignoreFlowingWaterForce.getSelectedValue().equalsIgnoreCase("OnlyAtMovement") && MoveUtil.isMoving()
               || this.ignoreFlowingWaterForce.getSelectedValue().equalsIgnoreCase("OnlyWhileStanding") && !MoveUtil.isMoving()
               || this.ignoreFlowingWaterForce.getSelectedValue().equalsIgnoreCase("Everytime")
         );
   }

   public boolean isLiquidSolidEnabled() {
      return (
            !ClientManagers.moduleManager.jesus.disableatsneakJump.getValue()
               || !mc.gameSettings.keyBindSneak.isKeyDown()
                  && (
                     !mc.gameSettings.keyBindJump.isKeyDown()
                        || !(mc.theWorld.getBlockState(mc.thePlayer.posX, mc.thePlayer.posY + 1.0, mc.thePlayer.posZ).getBlock() instanceof BlockLiquid)
                  )
         );
   }

   @Override
   public void onLiquidJump(EventLiquidJump event) {
      if (this.customWaterUpMotion.getValue()) {
         event.setWaterMotionY(this.customWaterUpMotionValue.getValue());
      }

      if (this.customLavaUpMotion.getValue()) {
         event.setLavaMotionY(this.customLavaUpMotionValue.getValue());
      }
   }

   private boolean isCustomWaterMotionValueVisible() {
      return this.customWaterUpMotion.getValue() && this.customWaterUpMotion.isVisible();
   }


}
