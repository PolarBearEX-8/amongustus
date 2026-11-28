package net.augustus.modules.modes.highjump;

import net.augustus.events.EventMoveInput;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.modules.modes.AbstractSubMode;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.modules.movement.HighJump;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;

public abstract class HighJumpMode extends AbstractSubMode implements UpdateListener, MoveInputListener {
   public BooleanValue autoDisable;
   public BooleanValue activateWhileJumpKeyPressed;
   private boolean jumpActive;
   public int groundTicks;
   private boolean triggeredByJumpKey;

   public HighJumpMode(HighJump highJump, SettingGroup doubleValue, String str) {
       super(highJump, doubleValue, str);
      this.jumpActive = false;
      this.groundTicks = 0;
      BooleanValue booleanValue = new BooleanValue(highJump, "AutoDisable" + str, true, doubleValue);
      booleanValue = (BooleanValue)booleanValue.withDisplayName("AutoDisable");
      this.autoDisable = (BooleanValue)booleanValue.withVisibility(() -> this.isSelected());
      booleanValue = new BooleanValue(highJump, "ActivateWhileJumpKeyPressed" + str, false, doubleValue);
      booleanValue = (BooleanValue)booleanValue.withDisplayName("ActivateWhileJumpKeyPressed");
      this.activateWhileJumpKeyPressed = (BooleanValue)booleanValue.withVisibility(() -> this.isSelected());
   }

   @Override
   public void onEnable() {
      this.groundTicks = 0;
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(43);
      this.toggleEventSubscription(21);
   }

   @Override
   public void onPreUpdate() {
      this.jumpActive = true;
      if (mc.thePlayer.onGround) {
         if (this.groundTicks > 0 && this.autoDisable.getValue()) {
            ((HighJump)this.parentModule).setToggled(false);
            this.jumpActive = false;
            return;
         }

         if (this.groundTicks > 0 && this.activateWhileJumpKeyPressed.getValue()) {
            this.triggeredByJumpKey = false;
            this.onDisable();
         }

         this.groundTicks++;
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (event.isJump() && this.activateWhileJumpKeyPressed.getValue() && !this.triggeredByJumpKey) {
         event.setJump(false);
         this.onEnable();
         this.triggeredByJumpKey = true;
      }
   }

   public boolean isActivated() {
      return this.triggeredByJumpKey || !this.activateWhileJumpKeyPressed.getValue();
   }

   public boolean isJumpActive() {
      return this.jumpActive;
   }




}
