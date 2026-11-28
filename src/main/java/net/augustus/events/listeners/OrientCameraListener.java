package net.augustus.events.listeners;


import net.augustus.events.EventManager;
import net.augustus.events.EventOrientCamera;
public interface OrientCameraListener {
   EventOrientCamera ORIENT_CAMERA_EVENT = new EventOrientCamera();

   void onOrientCamera(EventOrientCamera event);

   static EventOrientCamera onOrientCamera(float f, float f2, float f3) {
      EventOrientCamera.reset(ORIENT_CAMERA_EVENT, f, f2, f3);
      EventManager.getEventBus().post(24, ORIENT_CAMERA_EVENT);
      return ORIENT_CAMERA_EVENT;
   }
}
