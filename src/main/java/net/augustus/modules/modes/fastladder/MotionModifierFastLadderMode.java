package net.augustus.modules.modes.fastladder;

import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.MoveEntityListener;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.listeners.PacketListener;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.modules.modes.motion.MotionModifierEngine;
import net.augustus.modules.movement.helper.FastLadderMotionModifier;
import net.augustus.modules.movement.helper.MotionModifierProvider;
import net.augustus.modules.misc.Dummy;
import net.augustus.modules.movement.FastLadder;
import net.augustus.settings.SettingGroup;

public class MotionModifierFastLadderMode extends FastLadderMode implements MotionModifierProvider, UpdateListener, MoveEntityListener, MoveInputListener, PacketListener {
   private final Dummy dummy;
   private final MotionModifierEngine motionModifierEngine;

   public MotionModifierFastLadderMode(FastLadder fastLadder, SettingGroup doubleValue, String str) {
       super(fastLadder, doubleValue, str);
      this.dummy = new Dummy();
      this.motionModifierEngine = new FastLadderMotionModifier(this, this.dummy, fastLadder);
      this.motionModifierEngine.initRuleSettings();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(20);
      this.toggleEventSubscription(43);
      this.toggleEventSubscription(21);
      this.toggleEventSubscription(26);
   }

   @Override
   public void onEnable() {
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
   public MotionModifierEngine getMotionModifierEngine() {
      return this.motionModifierEngine;
   }

   @Override
   public Dummy getDummy() {
      return this.dummy;
   }
}
