package net.augustus.modules.modes.velocity;

import net.augustus.events.listeners.UpdateListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.MoveUtil;
import java.util.HashMap;
import java.util.function.BiFunction;
import net.augustus.modules.combat.Velocity;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;

public class PushVelocityMode extends VelocityMode implements UpdateListener {
   private final StringValue pushMode;
   public final DoubleValue push;
   public final DoubleValue pushStart;
   public final DoubleValue pushEnd;
   public final BooleanValue onGround;
   private final HashMap hurtTimeEntries;

   public PushVelocityMode(Velocity velocity, SettingGroup doubleValue) {
       super(velocity, doubleValue, "Push");
      this.pushMode = (StringValue)new StringValue(this.parentModule, "PushMode", "Simple", new String[]{"Simple", "Expert"}, this.modeSetting).withVisibility(() -> this.isSelected());
      DoubleValue pushSetting = new DoubleValue(this.parentModule, "Push", 1.1, 0.01, 20.0, 2, this.modeSetting);
      pushSetting = pushSetting.named("PushMotion");
      this.push = pushSetting.visibleWhen(() -> this.isPushMotionVisible());
      pushSetting = new DoubleValue(this.parentModule, "PushStart", 9.0, 1.0, 10.0, 0, this.modeSetting);
      this.pushStart = pushSetting.visibleWhen(() -> this.isPushStartVisible());
      pushSetting = new DoubleValue(this.parentModule, "PushEnd", 2.0, 1.0, 10.0, 0, this.modeSetting);
      SettingVisibilityCondition condition4 = () -> {
         return this.isSelected() && this.isSimpleMode();
      };
      this.pushEnd = pushSetting.visibleWhen(condition4);
      this.onGround = (BooleanValue)new BooleanValue(this.parentModule, "OnGround", false, this.modeSetting).withVisibility(() -> this.isOnGroundVisible());
      this.hurtTimeEntries = new HashMap();

      for (int i = 10; i > 0; i--) {
         SettingGroup settingGroup = new SettingGroup(velocity, "Hurttime " + i, doubleValue).visibleWhen(() -> this.isExpertGroupVisible());
         DoubleValue doubleSetting = new DoubleValue(velocity, "PushMotion" + i, 0.0, 0.0, 20.0, 2, settingGroup).named("PushMotion");
         SettingVisibilityCondition condition7 = () -> {
            return this.isSelected() && !this.isSimpleMode();
         };
         DoubleValue doubleSetting2 = doubleSetting.visibleWhen(condition7);
         BooleanValue booleanValue3 = (BooleanValue)new BooleanValue(velocity, "PushOnGround" + i, false, settingGroup).withDisplayName("PushOnGround");
         SettingVisibilityCondition condition8 = () -> {
            return this.isSelected() && !this.isSimpleMode();
         };
         BooleanValue booleanValue4 = (BooleanValue)booleanValue3.withVisibility(condition8);
         this.hurtTimeEntries.put(i, new PushVelocityHurtTimeEntry(this, doubleSetting2, booleanValue4));
      }
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(43);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new PushVelocityMode((Velocity)obj, (SettingGroup)obj2);
   }

   @Override
   public void onPreUpdate() {
      if (this.canApplyVelocity()) {
         if (this.isSimpleMode()) {
            if ((double)mc.thePlayer.hurtTime <= Math.max(this.pushStart.getValue(), this.pushEnd.getValue())
               && (double)mc.thePlayer.hurtTime >= Math.min(this.pushStart.getValue(), this.pushEnd.getValue())) {
               MoveUtil.addMotion(this.push.getValue() / 100.0, true);
               if (this.onGround.getValue()) {
                  mc.thePlayer.onGround = true;
               }
            }
         } else {
            PushVelocityHurtTimeEntry entry = (PushVelocityHurtTimeEntry)this.hurtTimeEntries.get(mc.thePlayer.hurtTime);
            if (entry != null) {
               MoveUtil.addMotion(entry.getPushMotionFactor(), true);
               if (entry.shouldSpoofOnGround()) {
                  mc.thePlayer.onGround = true;
               }
            }
         }
      }
   }

   private boolean isSimpleMode() {
      return this.pushMode.getSelectedValue().equals("Simple");
   }

   private boolean isExpertGroupVisible() {
      return this.isSelected() && !this.isSimpleMode();
   }

   private boolean isOnGroundVisible() {
      return this.isSelected() && this.isSimpleMode();
   }

   private boolean isPushStartVisible() {
      return this.isSelected() && this.isSimpleMode();
   }

   private boolean isPushMotionVisible() {
      return this.isSelected() && this.isSimpleMode();
   }

}
