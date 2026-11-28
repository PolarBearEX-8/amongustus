package net.augustus.modules.modes.noweb;

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
import net.augustus.modules.misc.Dummy;
import net.augustus.modules.movement.NoWeb;
import net.augustus.settings.SettingGroup;

public class MotionModifierNoWebMode extends AbstractNoWebMode implements MotionModifierProvider, UpdateListener, MoveEntityListener, MoveInputListener, PacketListener, PacketDelayListener {
   private final Dummy dummy;
   private final MotionModifierEngine motionModifierEngine;

   public MotionModifierNoWebMode(NoWeb noWeb, SettingGroup doubleValue, String str) {
       super(noWeb, doubleValue, str);
      this.dummy = new Dummy();
      this.motionModifierEngine = new MotionModifierEngine(this.dummy);
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
      this.motionModifierEngine.onEnable();
   }

   @Override
   public void onDisable() {
      this.motionModifierEngine.onDisable();
   }

   @Override
   public void onPreUpdate() {
      this.motionModifierEngine.onPreUpdate();
   }

   @Override
   public void onPostUpdate() {
      this.motionModifierEngine.onPostUpdate();
   }

   @Override
   public void onPreMoveEntity(EventMoveEntity event) {
      this.motionModifierEngine.onMoveEntity(event);
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      this.motionModifierEngine.onMoveInput(event);
   }

   @Override
   public void onPacketSend(EventPacket event) {
      this.motionModifierEngine.onSendPacket(event);
   }

   @Override
   public void onOutgoingPacketDelay(EventPacketDelay event) {
      this.motionModifierEngine.onOutgoingPacketDelay(event);
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
