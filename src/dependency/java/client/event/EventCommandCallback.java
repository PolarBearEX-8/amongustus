package client.event;

public interface EventCommandCallback {
   EventCommand eventCommand = new EventCommand();

   void onCommand(EventCommand event);

   static EventCommand resolveEventCommand(String text) {
      EventCommand.onCommand(eventCommand, text);
      EventBusManager.getDietrichEvents2().post(5, eventCommand);
      return eventCommand;
   }
}
