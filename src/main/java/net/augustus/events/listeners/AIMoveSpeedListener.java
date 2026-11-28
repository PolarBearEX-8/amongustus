package net.augustus.events.listeners;


import net.augustus.events.EventAIMoveSpeed;
import net.augustus.events.EventManager;
public interface AIMoveSpeedListener {
   EventAIMoveSpeed AI_MOVE_SPEED_EVENT = new EventAIMoveSpeed();

   static EventAIMoveSpeed postAIMoveSpeed(float f) {
      EventAIMoveSpeed.reset(AI_MOVE_SPEED_EVENT, f);
      EventManager.getEventBus().post(0, AI_MOVE_SPEED_EVENT);
      return AI_MOVE_SPEED_EVENT;
   }

   void onAIMoveSpeed(EventAIMoveSpeed event);
}
