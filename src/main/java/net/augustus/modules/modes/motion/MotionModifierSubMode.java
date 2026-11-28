package net.augustus.modules.modes.motion;

import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.events.EventPacket;
import net.augustus.events.EventPacketDelay;
import net.augustus.events.listeners.MoveEntityListener;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.listeners.PacketDelayListener;
import net.augustus.events.listeners.PacketListener;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.modules.modes.AbstractSubMode;
import net.augustus.modules.movement.helper.MotionModifierProvider;
import net.augustus.settings.CustomSubModeFactory;
import net.augustus.modules.Module;
import net.augustus.modules.misc.Dummy;
import net.augustus.settings.SettingGroup;

public class MotionModifierSubMode extends AbstractSubMode implements PacketDelayListener, PacketListener, MoveInputListener, MotionModifierProvider, UpdateListener, MoveEntityListener {
   private final MotionModifierEngine motionModifierEngine;
   private final Dummy dummy;

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      this.motionModifierEngine.onMoveInput(event);
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(20);
      this.toggleEventSubscription(43);
      this.toggleEventSubscription(21);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(25);
   }

   @Override
   public void onPreMoveEntity(EventMoveEntity event) {
      this.motionModifierEngine.onMoveEntity(event);
   }

   public MotionModifierSubMode(Module module, SettingGroup doubleValue, String str) {
       super(module, doubleValue, str);
      this.dummy = new Dummy();
      this.motionModifierEngine = new MotionModifierEngine(this.dummy);
      this.motionModifierEngine.initRuleSettings();
   }

   @Override
   public void onPacketSend(EventPacket event) {
      this.motionModifierEngine.onSendPacket(event);
   }

   public static CustomSubModeFactory factory() {
      return (obj, obj2, obj3) -> new MotionModifierSubMode((Module)obj, (SettingGroup)obj2, (String)obj3);
   }

   @Override
   public void onPreUpdate() {
      this.motionModifierEngine.onPreUpdate();
   }

   @Override
   public void onOutgoingPacketDelay(EventPacketDelay event) {
      this.motionModifierEngine.onOutgoingPacketDelay(event);
   }

   @Override
   public void onDisable() {
      this.motionModifierEngine.onDisable();
   }

   @Override
   public void onPostUpdate() {
      this.motionModifierEngine.onPostUpdate();
   }

   @Override
   public Dummy getDummy() {
      return this.dummy;
   }

   @Override
   public MotionModifierEngine getMotionModifierEngine() {
      return this.motionModifierEngine;
   }

   @Override
   public void onEnable() {
      this.motionModifierEngine.onEnable();
   }
}
