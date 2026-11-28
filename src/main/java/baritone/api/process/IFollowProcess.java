package baritone.api.process;

import java.util.List;
import java.util.function.Predicate;

public interface IFollowProcess extends IBaritoneProcess {
   List following();

   void follow(Predicate predicate);

   default void cancel() {
      this.onLostControl();
   }

   Predicate currentFilter();
}
