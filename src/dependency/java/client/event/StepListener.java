package client.event;

public interface StepListener {
   EventStep eventStep = new EventStep();

   void onStep(EventStep event);

   static EventStep resolveEventStep(float factor) {
      EventStep.onStep(eventStep, factor);
      EventBusManager.getDietrichEvents2().post(0, eventStep);
      return eventStep;
   }
}
