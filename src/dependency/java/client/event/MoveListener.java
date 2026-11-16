package client.event;

import client.setting.ArrowDodgerPhase;

public interface MoveListener {
   void onMove(EventMove event);

   static EventMove resolveEventMove(float factor, float factor2, boolean flag, boolean flag2, ArrowDodgerPhase arrowDodgerPhase) {
      EventMove event = new EventMove(factor, factor2, flag, flag2, arrowDodgerPhase);
      EventBusManager.getDietrichEvents2().post(21, event);
      return event;
   }

   default void updateState9() {
   }
}
