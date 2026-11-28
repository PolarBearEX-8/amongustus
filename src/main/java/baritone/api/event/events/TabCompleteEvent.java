package baritone.api.event.events;

import baritone.api.event.events.type.Cancellable;

public class TabCompleteEvent extends Cancellable {
   public final String prefix;
   public String[] completions;

   public TabCompleteEvent(String str) {
      this.prefix = str;
      this.completions = null;
   }
}
