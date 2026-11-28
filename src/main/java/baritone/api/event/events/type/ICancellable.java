package baritone.api.event.events.type;

public interface ICancellable {
   boolean isCancelled();

   void cancel();
}
