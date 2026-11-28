package net.raphimc.minecraftauth.util.logging;

import net.raphimc.minecraftauth.step.AbstractStep;

public interface ILogger {
   default void info(AbstractStep<?, ?> step, String message) {
      this.info(message);
   }

   void info(String str);

   default void warn(AbstractStep<?, ?> step, String message) {
      this.warn(message);
   }

   void warn(String str);

   default void error(AbstractStep<?, ?> step, String message) {
      this.error(message);
   }

   void error(String str);
}
