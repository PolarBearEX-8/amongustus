package cc.baritone.utils;

import java.util.Optional;

public interface CoreListener extends HeuristicRecorder {
   PrimaryListener getPrimaryListener();

   default Optional getOptionalAsOptional() {
      return this.resolveOptional(true);
   }

   void updateState();

   Optional getOptional();

   boolean isActive2();

   default Optional getOptional2() {
      return Optional.ofNullable(this.getPrimaryListenerAsPrimaryListener()).map(PrimaryListener::getCustom);
   }

   default Optional resolveOptional(boolean flag) {
      PrimaryListener primaryListenerAsPrimaryListener = this.getPrimaryListenerAsPrimaryListener();
      if (primaryListenerAsPrimaryListener == null) {
         return Optional.empty();
      } else {
         int count = flag ? primaryListenerAsPrimaryListener.getCount() : primaryListenerAsPrimaryListener.getCount() + 1;
         return Optional.of(primaryListenerAsPrimaryListener.getCustom().computeValue(count));
      }
   }

   boolean isActiveAsFlag();

   PrimaryListener getPrimaryListenerAsPrimaryListener();

   default boolean isActive() {
      return this.getPrimaryListenerAsPrimaryListener() != null;
   }

   Optional ofNullable();

   SecondaryListener getSecondaryListener();
}
