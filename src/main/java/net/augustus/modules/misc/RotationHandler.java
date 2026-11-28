package net.augustus.modules.misc;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.events.EventMove;
import net.augustus.ClientManagers;
import net.augustus.utils.MoveUtil;
import net.augustus.utils.rotation.MoveFixMode;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.events.listeners.MoveListener;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.events.EventLoadWorld;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.events.EventMoveInput;
import net.augustus.events.EventAttackEntity;
import net.augustus.Augustus;
import net.augustus.events.listeners.AttackEntityListener;
import net.augustus.events.EventManager;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;

public class RotationHandler extends Module implements LoadWorldListener, MoveListener, MoveInputListener, PacketListener, UpdateListener, AttackEntityListener {
   public final StringValue rotationMode;
   public final StringValue mouseSensMode;
   public final StringValue smoothBackRotate;
   public final RandomizedRangeSettings yawSpeed;
   public final RandomizedRangeSettings pitchSpeed;
   public final DoubleValue rotationSpeed;
   public final RandomizedRangeSettings smoothBackRotateTickDelayAfterClosingInventory;
   public final SettingGroup movement;
   public final BooleanValue moveFix;
   public final StringValue silentMoveFix;
   public final BooleanValue silentMoveFixIntave;
   public final BooleanValue randomizeDirections;
   public int framesSinceLastTick;
   public int framesPerTick;

   public RotationHandler() {
       super("RotationHandler", "DrehungsVerwalter", true, new Color(168, 145, 50), Categorys.MISC);
      this.rotationMode = new StringValue(this, "RotationMode", "Vanilla", new String[]{"Vanilla", "Tick"});
      this.mouseSensMode = new StringValue(this, "MouseSensMode", "Advanced", new String[]{"Default", "Advanced"});
      this.smoothBackRotate = new StringValue(this, "SmoothBackRotate", "None", new String[]{"None", "Default", "New"});
      this.yawSpeed = ((RandomizedRangeSettings)new RandomizedRangeSettings(this, "YawSpeed", 18.0, 24.0, 0.0, 180.0, 0).withVisibility(() -> this.isYawSpeedVisible())).withoutNotInARow();
      this.pitchSpeed = ((RandomizedRangeSettings)new RandomizedRangeSettings(this, "PitchSpeed", 18.0, 24.0, 0.0, 180.0, 0).withVisibility(() -> this.isPitchSpeedVisible())).withoutNotInARow();
      this.rotationSpeed = new DoubleValue(this, "RotationSpeed", 75.0, 0.0, 100.0, 0).visibleWhen(() -> this.isRotationSpeedVisible());
      RandomizedRangeSettings randomizedRangeSettings3 = (new RandomizedRangeSettings(this, "SmoothBackRotateTickDelayAfterClosingInventory", 2.0, 4.0, 0.0, 10.0, 0)).withoutNotInARow();
      SettingVisibilityCondition condition4 = () -> {
         return !this.smoothBackRotate.getSelectedValue().equals("None");
      };
      this.smoothBackRotateTickDelayAfterClosingInventory = (RandomizedRangeSettings)randomizedRangeSettings3.withVisibility(condition4);
      this.movement = new SettingGroup(this, "Movement", true).visibleWhen(() -> this.isMovementGroupVisible());
      BooleanValue booleanValue = new BooleanValue(this, "MoveFix", true, this.movement);
      SettingVisibilityCondition condition6 = () -> {
         return !this.smoothBackRotate.getSelectedValue().equals("None");
      };
      this.moveFix = (BooleanValue)booleanValue.withVisibility(condition6);
      this.silentMoveFix = (StringValue)new StringValue(this, "SilentMoveFix", "Default", new String[]{"None", "Default", "Advanced"}, this.movement).withVisibility(() -> this.isSilentMoveFixVisible());
      BooleanValue booleanValue2 = new BooleanValue(this, "SilentMoveFixIntave", true, this.movement);
      SettingVisibilityCondition condition8 = () -> {
         return this.silentMoveFix.isVisible() && !this.silentMoveFix.getSelectedValue().equals("None");
      };
      this.silentMoveFixIntave = (BooleanValue)booleanValue2.withVisibility(condition8);
      this.randomizeDirections = (BooleanValue)new BooleanValue(this, "RandomizeDirections", false, this.movement).withVisibility(() -> this.isRandomizeDirectionsVisible());
      this.framesSinceLastTick = 0;
      this.framesPerTick = 0;
      this.setToggled(true);
      EventManager.getEventBus().subscribe(28, this, -2);
      EventManager.getEventBus().subscribe(21, this, 20);
      EventManager.getEventBus().subscribe(26, this, 20);
      EventManager.getEventBus().subscribe(43, this, 20);
      EventManager.getEventBus().subscribe(1, this, 20);
      EventManager.getEventBus().subscribe(17, this, 20);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
   }

   @Override
   public void onPostUpdate() {
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (this.moveFix.getValue() && ClientManagers.rotationController.isSmoothlyReturning()) {
         event.setMoveFixMode(this.silentMoveFix.getSelectedValue().equals("None")
            ? MoveFixMode.NONE
            : (this.silentMoveFix.getSelectedValue().equals("Default") ? MoveFixMode.DEFAULT : MoveFixMode.ADVANCED));
         if (!this.silentMoveFix.getSelectedValue().equals("None")) {
            event.setPreventInstantReverse(this.silentMoveFixIntave.getValue());
            if (this.randomizeDirections.getValue()) {
               float f = 30.0F;
               float f2 = 20.0F;
               event.setYaw(Augustus.getInstance().getYawPitchHelper().realYaw
                  + DefaultRandomGenerator.instance.nextFloat(f2, f) * (float)(ThreadLocalRandom.current().nextBoolean() ? 1 : -1));
            }
         }
      }

      if (ClientManagers.rotationController.isHoldingRotation()) {
         event.setMoveFixMode(MoveFixMode.DEFAULT);
         event.setPreventInstantReverse(true);
      }
   }

   @Override
   public void onMoveFlying(EventMove event) {
      if (!this.moveFix.getValue() && ClientManagers.rotationController.isSmoothlyReturning()) {
         event.setYaw(MoveUtil.getMovementYaw());
      }
   }

   @Override
   public void onJumpMove(EventMove event) {
      if (!this.moveFix.getValue() && ClientManagers.rotationController.isSmoothlyReturning()) {
         event.setYaw(MoveUtil.getMovementYaw());
      }
   }

   @Override
   public void onPacketSend(EventPacket event) {
      ClientManagers.moduleManager.killAura.clickSpeedMonitor.onPacketSend(event);
      ClientManagers.moduleManager.autoClicker.clickSpeedMonitor.onPacketSend(event);
      ClientManagers.moduleManager.killAura.onOutgoingCombatPacket(event);
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      ClientManagers.moduleManager.killAura.clickSpeedMonitor.onPacketReceive(event);
      ClientManagers.moduleManager.autoClicker.clickSpeedMonitor.onPacketReceive(event);
   }

   @Override
   public void onAttackEntity(EventAttackEntity event) {
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
   }

   private boolean isRandomizeDirectionsVisible() {
      return this.silentMoveFix.isVisible() && !this.silentMoveFix.getSelectedValue().equals("None");
   }

   private boolean isSilentMoveFixVisible() {
      return this.moveFix.getValue() && this.moveFix.isVisible();
   }

   private boolean isMovementGroupVisible() {
      return !this.smoothBackRotate.getSelectedValue().equals("None");
   }

   private boolean isRotationSpeedVisible() {
      return this.smoothBackRotate.getSelectedValue().equals("New");
   }

   private boolean isPitchSpeedVisible() {
      return this.smoothBackRotate.getSelectedValue().equals("Default");
   }

   private boolean isYawSpeedVisible() {
      return this.smoothBackRotate.getSelectedValue().equals("Default");
   }

}
