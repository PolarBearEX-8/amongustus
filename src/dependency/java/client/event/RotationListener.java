package client.event;

import client.setting.BugUpMoveFlyingType;

public interface RotationListener {
   EventRotation eventRotation = new EventRotation();

   static EventRotation getEventRotation(float factor, BugUpMoveFlyingType bugUpMoveFlyingType) {
      EventRotation.onRotation(eventRotation, factor, bugUpMoveFlyingType);
      EventBusManager.getDietrichEvents2().post(28, eventRotation);
      return eventRotation;
   }

   void onRotation(EventRotation event);

   void onPostRotation(EventRotation event);

   static EventRotation resolveEventRotation(float posX, float posX2, float posY, float posZ, BugUpMoveFlyingType bugUpMoveFlyingType) {
      EventRotation.onRotation(eventRotation, posX, posX2, posY, posZ, bugUpMoveFlyingType);
      EventBusManager.getDietrichEvents2().post(28, eventRotation);
      return eventRotation;
   }
}
