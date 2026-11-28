package net.augustus.modules.fun;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.ClickListener;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.events.EventMove;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.MotionListener;
import net.augustus.events.EventClick;
import net.augustus.utils.input.MouseInputUtil;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.utils.rotation.MoveFixMode;
import net.augustus.events.listeners.MoveListener;
import net.augustus.utils.world.BlockUtil;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.settings.options.SpinBotStopConditionOption;
import net.augustus.events.EventMoveInput;
import net.augustus.Augustus;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.events.EventMotion;
import net.augustus.utils.rotation.Rotation;
import net.augustus.settings.BooleanValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.util.MovingObjectPosition;

public class SpinBot extends Module implements MoveListener, ClickListener, TickListener, MoveInputListener, MotionListener {
   private final RandomizedRangeSettings yawSpeed;
   private final RandomizedRangeSettings pitchValue;
   private final BooleanValue serverside;
   public final BooleanValue moveFix;
   private final MultiOptionSetting stopSpinningOn;
   private final TimeHelper unusedTimer;
   private final TimeHelper resumeSpinTimer;
   private Rotation spinRotation;
   private Rotation lastRotation;
   private boolean spinning;

   public SpinBot() {
       super("SpinBot", "Drehen", new Color(50, 168, 82), Categorys.FUN);
      this.yawSpeed = (new RandomizedRangeSettings(this, "YawSpeed", 90.0, 90.0, 0.0, 180.0, 1)).withoutNotInARow();
      this.pitchValue = (new RandomizedRangeSettings(this, "PitchValue", 90.0, 90.0, -90.0, 90.0, 1)).withoutNotInARow();
      this.serverside = new BooleanValue(this, "Serverside", true);
      BooleanValue booleanValue = new BooleanValue(this, "MoveFix", false);
      BooleanValue serverside2 = this.serverside;
      BooleanValue booleanValue2 = serverside2;
      this.moveFix = (BooleanValue)booleanValue.withVisibility(() -> booleanValue2.getValue());
      MultiOptionSetting multiOptionSetting = new MultiOptionSetting(this, "StopSpinningOn", SpinBotStopConditionOption.values());
      serverside2 = this.serverside;
      BooleanValue booleanValue3 = serverside2;
      this.stopSpinningOn = multiOptionSetting.visibleWhen(() -> booleanValue3.getValue());
      this.unusedTimer = new TimeHelper();
      this.resumeSpinTimer = new TimeHelper();
      this.spinRotation = new Rotation();
      this.lastRotation = new Rotation();
   }

   @Override
   public void onEnable() {
      if (mc.thePlayer != null) {
         this.spinRotation = new Rotation(mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
         this.lastRotation = new Rotation(mc.thePlayer.prevRotationYaw, mc.thePlayer.prevRotationPitch);
      }
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(28);
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(21);
      this.toggleEventSubscription(18);
   }

   @Override
   public void onEarlyTick() {
      this.updateSpinState();
      if (this.spinning) {
         this.stepSpinAngles();
         this.applySpinRotation();
      }
   }

   private boolean canSpinNow() {
      if (!this.serverside.getValue()) {
         return true;
      } else {
         MovingObjectPosition hit = mc.thePlayer.rayTraceCustom(4.5, 1.0F, Augustus.getInstance().getYawPitchHelper().realYaw, Augustus.getInstance().getYawPitchHelper().realPitch);
         if (!SpinBotStopConditionOption.CLICK.isSelected()
            || !mc.gameSettings.keyBindUseItem.isKeyDown()
               && !mc.gameSettings.keyBindAttack.isKeyDown()
               && !MouseInputUtil.isButtonDown(0)
               && !MouseInputUtil.isButtonDown(1)) {
            if (hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
               if (SpinBotStopConditionOption.CHEST.isSelected() && mc.theWorld.getBlockState(hit.getBlockPos()).getBlock() instanceof BlockChest) {
                  return false;
               }

               if (SpinBotStopConditionOption.CRAFTER.isSelected() && mc.theWorld.getBlockState(hit.getBlockPos()).getBlock().equals(Block.getBlockById(58))) {
                  return false;
               }

               if (SpinBotStopConditionOption.FURNACE.isSelected() && mc.theWorld.getBlockState(hit.getBlockPos()).getBlock().equals(Block.getBlockById(61))) {
                  return false;
               }
            }

            return (!ClientManagers.moduleManager.killAura.isToggled() || ClientManagers.moduleManager.killAura.target == null || ClientManagers.moduleManager.killAura.noRotation.getValue())
               && !BlockUtil.isScaffoldActiveAndValid()
               && !ClientManagers.moduleManager.teleport.isToggled();
         } else {
            return false;
         }
      }
   }

   @Override
   public void onClick(EventClick event) {
   }

   @Override
   public void onPreMotion(EventMotion event) {
      if (!this.moveFix.getValue() || !this.serverside.getValue()) {
         if (this.spinning) {
            this.applyMotionRotations(event);
         }
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (this.spinning) {
         if (this.isMoveFixEnabled() && this.serverside.getValue()) {
            event.setMoveFixMode(MoveFixMode.DEFAULT);
         }
      }
   }

   @Override
   public void onMoveFlying(EventMove event) {
      if (this.spinning) {
         if (!this.isMoveFixEnabled() && this.serverside.getValue()) {
            event.setYaw(Augustus.getInstance().getYawPitchHelper().realYaw);
         }
      }
   }

   @Override
   public void onJumpMove(EventMove event) {
      if (this.spinning) {
         if (!this.isMoveFixEnabled() && this.serverside.getValue()) {
            event.setYaw(Augustus.getInstance().getYawPitchHelper().realYaw);
         }
      }
   }

   private void updateSpinState() {
      this.spinning = false;
      if (this.canSpinNow()) {
         this.spinning = true;
      } else {
         this.resumeSpinTimer.reset();
         if (this.spinning && this.serverside.getValue()) {
            this.spinRotation = new Rotation(ClientManagers.rotationController.getPreviousRotation());
         }

         this.spinning = false;
      }
   }

   private void applySpinRotation() {
      if (this.resumeSpinTimer.reached(1000L)) {
         Rotation lastRotation2 = this.lastRotation;
         this.spinRotation = RotationUtil.applyGcdFix(this.spinRotation, lastRotation2);
         if (this.isMoveFixEnabled() && this.serverside.getValue()) {
            Rotation spinRotation2 = this.spinRotation;
            ClientManagers.rotationController.requestRotation(spinRotation2, (byte)8, this);
         }
      }
   }

   private void stepSpinAngles() {
      if (this.resumeSpinTimer.reached(1000L)) {
         this.spinRotation.yaw = this.spinRotation.yaw + (float)this.yawSpeed.nextValue();
         this.spinRotation.pitch = (float)this.pitchValue.nextValue();
      }
   }

   private void applyMotionRotations(EventMotion event) {
      if (this.serverside.getValue()) {
         event.setYaw(this.spinRotation.yaw);
         event.setPitch(this.spinRotation.pitch);
      }

      mc.thePlayer.rotationYawHead = this.spinRotation.yaw;
      mc.thePlayer.rotationPitchHead = this.spinRotation.pitch;
      mc.thePlayer.renderYawOffset = this.spinRotation.yaw;
   }

   private boolean isMoveFixEnabled() {
      return this.moveFix.getValue();
   }

}
