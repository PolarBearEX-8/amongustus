package net.augustus.modules.modes.highjump;

import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.events.EventPacket;
import net.augustus.events.EventPacketDelay;
import net.augustus.events.listeners.MoveEntityListener;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.listeners.PacketDelayListener;
import net.augustus.events.listeners.PacketListener;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.modules.modes.motion.MotionModifierEngine;
import net.augustus.modules.movement.helper.MotionModifierProvider;
import net.augustus.settings.CustomSubModeFactory;
import net.augustus.modules.misc.Dummy;
import net.augustus.modules.movement.HighJump;
import net.augustus.settings.SettingGroup;

public class MotionModifierHighJumpMode extends HighJumpMode implements MotionModifierProvider, UpdateListener, MoveEntityListener, MoveInputListener, PacketListener, PacketDelayListener {
   private final Dummy dummy;
   private final MotionModifierEngine motionModifierEngine;

   public MotionModifierHighJumpMode(HighJump highJump, SettingGroup doubleValue, String str) {
       super(highJump, doubleValue, str);
      this.dummy = new Dummy();
      this.motionModifierEngine = new MotionModifierEngine(this.dummy);
   }

   public static CustomSubModeFactory factory() {
      return (obj, obj2, obj3) -> new MotionModifierHighJumpMode((HighJump)obj, (SettingGroup)obj2, (String)obj3);
   }

   @Override
   public void updateEventSubscriptions() {
      super.updateEventSubscriptions();
      this.toggleEventSubscription(20);
      this.toggleEventSubscription(21);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(25);
   }

   @Override
   public void onEnable() {
      super.onEnable();
      if (this.isActivated()) {
         this.motionModifierEngine.onEnable();
      }
   }

   @Override
   public void onDisable() {
      if (this.isActivated()) {
         this.motionModifierEngine.onDisable();
      }
   }

   @Override
   public void onPreUpdate() {
      super.onPreUpdate();
      if (this.isActivated()) {
         this.motionModifierEngine.onPreUpdate();
      }
   }

   @Override
   public void onPostUpdate() {
      if (this.isActivated()) {
         this.motionModifierEngine.onPostUpdate();
      }
   }

   @Override
   public void onPreMoveEntity(EventMoveEntity event) {
      if (this.isActivated()) {
         this.motionModifierEngine.onMoveEntity(event);
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      super.onPreMoveInput(event);
      if (this.isActivated()) {
         this.motionModifierEngine.onMoveInput(event);
      }
   }

   @Override
   public void onPacketSend(EventPacket event) {
      if (this.isActivated()) {
         this.motionModifierEngine.onSendPacket(event);
      }
   }

   @Override
   public void onOutgoingPacketDelay(EventPacketDelay event) {
      if (this.isActivated()) {
         this.motionModifierEngine.onOutgoingPacketDelay(event);
      }
   }

   @Override
   public MotionModifierEngine getMotionModifierEngine() {
      return this.motionModifierEngine;
   }

   @Override
   public Dummy getDummy() {
      return this.dummy;
   }
}
