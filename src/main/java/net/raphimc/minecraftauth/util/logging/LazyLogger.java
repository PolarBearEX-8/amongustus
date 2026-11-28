package net.raphimc.minecraftauth.util.logging;

import java.util.function.Supplier;
import net.raphimc.minecraftauth.step.AbstractStep;

public class LazyLogger implements ILogger {
   private final Supplier<ILogger> loggerSupplier;
   private ILogger delegate;

   public LazyLogger(Supplier<ILogger> loggerSupplier) {
      this.loggerSupplier = loggerSupplier;
   }

   @Override
   public void info(AbstractStep<?, ?> step, String message) {
      this.getDelegate().info(step, message);
   }

   @Override
   public void info(String message) {
      this.getDelegate().info(message);
   }

   @Override
   public void warn(AbstractStep<?, ?> step, String message) {
      this.getDelegate().warn(step, message);
   }

   @Override
   public void warn(String message) {
      this.getDelegate().warn(message);
   }

   @Override
   public void error(AbstractStep<?, ?> step, String message) {
      this.getDelegate().error(step, message);
   }

   @Override
   public void error(String message) {
      this.getDelegate().error(message);
   }

   private ILogger getDelegate() {
      if (this.delegate == null) {
         this.delegate = this.loggerSupplier.get();
      }

      return this.delegate;
   }
}
