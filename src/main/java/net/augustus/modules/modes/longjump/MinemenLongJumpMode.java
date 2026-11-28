package net.augustus.modules.modes.longjump;

import net.augustus.ClientManagers;
import net.augustus.events.EventClick;
import net.augustus.events.EventMoveInput;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.utils.MoveUtil;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.utils.rotation.RotationController;
import net.augustus.utils.rotation.RotationUtil;
import java.util.function.BiFunction;
import net.augustus.modules.Module;
import net.augustus.modules.movement.LongJump;
import net.augustus.settings.SettingGroup;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;

public class MinemenLongJumpMode extends AbstractLongJumpMode implements ClickListener, UpdateListener, SilentSlotListener, TickListener, MoveInputListener {
   private boolean chargingBow = true;
   private int chargeTicks = 0;
   private int bowSlot = -1;

   public MinemenLongJumpMode(LongJump longJump, SettingGroup doubleValue) {
      super(longJump, doubleValue, "Minemen");
   }

   @Override
   public void updateEventSubscriptions() {
      super.updateEventSubscriptions();
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(43);
      this.toggleEventSubscription(34);
   }

   @Override
   public void onEnable() {
      this.chargingBow = true;
      this.chargeTicks = 0;
   }

   @Override
   public void onDisable() {
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new MinemenLongJumpMode((LongJump)obj, (SettingGroup)obj2);
   }

   @Override
   public void onClick(EventClick event) {
      if (this.bowSlot != -1 && this.chargingBow) {
         this.chargeTicks++;
         if (this.chargeTicks < 4) {
            event.setRightMouseDown(true);
         } else {
            this.chargingBow = false;
         }
      }

      if (!this.chargingBow) {
         this.chargeTicks++;
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      event.setMoveForward(0.0F);
      event.setMoveStrafe(0.0F);
      if (!this.chargingBow && this.chargeTicks > 14) {
         event.setMoveForward(1.0F);
      }
   }

   @Override
   public void onPostSilentSlot() {
      this.bowSlot = -1;
      if (this.chargingBow) {
         this.bowSlot = this.findBowHotbarSlot();
      }

      if (this.bowSlot >= 0) {
         int bowSlot2 = this.bowSlot;
         SilentSlotManager.requestSlot(this.parentModule, (byte)7, bowSlot2);
      }
   }

   @Override
   public void onEarlyTick() {
      if (this.chargingBow) {
         ClientManagers.rotationController.requestPitch(RotationUtil.applyPitchGcd(-90.0F, ClientManagers.rotationController.getPreviousRotation().pitch), (byte)-10, this.parentModule);
      }
   }

   @Override
   public void onPreUpdate() {
      if (mc.thePlayer.hurtTime != 0) {
         if (mc.thePlayer.onGround) {
            mc.thePlayer.motionY = 0.42F;
         }

         if (mc.thePlayer.hurtTime == 8) {
            mc.thePlayer.movementInput.rawMoveForward = mc.thePlayer.movementInput.moveForward;
            MoveUtil.setMoveSpeed(6.0);
         }

         if (mc.thePlayer.hurtTime == 1) {
            this.setEnabled(false);
         }
      }
   }

   private int findBowHotbarSlot() {

      for (int i = 36; i < mc.thePlayer.inventoryContainer.inventorySlots.size(); i++) {
         ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
         if (stack != null && stack.getItem() instanceof ItemBow) {
            return i - 36;
         }
      }

      return -1;
   }

}
