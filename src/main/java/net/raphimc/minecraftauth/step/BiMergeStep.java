package net.raphimc.minecraftauth.step;

import net.lenni0451.commons.httpclient.HttpClient;
import net.raphimc.minecraftauth.util.logging.ILogger;

public abstract class BiMergeStep<I1 extends AbstractStep.StepResult<?>, I2 extends AbstractStep.StepResult<?>, O extends BiMergeStep.StepResult<I1, I2>>
   extends AbstractStep<I1, O> {
   public final AbstractStep<?, I2> prevStep2;

   public BiMergeStep(String name, AbstractStep<?, I1> prevStep1, AbstractStep<?, I2> prevStep2) {
      super(name, prevStep1);
      this.prevStep2 = prevStep2;
   }

   protected final O execute(ILogger logger, HttpClient httpClient, I1 prevResult) throws Exception {
      throw new UnsupportedOperationException();
   }

   protected abstract O execute(ILogger logger, HttpClient httpClient, I1 stepResult, I2 stepResult2) throws Exception;

   public O refresh(ILogger logger, HttpClient httpClient, O result) throws Exception {
      if (!result.isExpiredOrOutdated()) {
         return result;
      } else {
         return this.execute(logger, httpClient, this.prevStep.refresh(logger, httpClient, result.prevResult()), this.prevStep2 != null ? this.prevStep2.refresh(logger, httpClient, result.prevResult2()) : null);
      }
   }

   public O getFromInput(ILogger logger, HttpClient httpClient, AbstractStep.InitialInput input) throws Exception {
      return this.execute(logger, httpClient, this.prevStep.getFromInput(logger, httpClient, input), this.prevStep2 != null ? this.prevStep2.getFromInput(logger, httpClient, input) : null);
   }

   public abstract static class StepResult<P1 extends AbstractStep.StepResult<?>, P2 extends AbstractStep.StepResult<?>> extends AbstractStep.StepResult<P1> {
      protected abstract P2 prevResult2();

      @Override
      public boolean isExpired() {
         return this.prevResult() != null && this.prevResult().isExpired() || this.prevResult2() != null && this.prevResult2().isExpired();
      }

      @Override
      public boolean isExpiredOrOutdated() {
         return this.prevResult() != null && this.prevResult().isExpiredOrOutdated() || this.prevResult2() != null && this.prevResult2().isExpiredOrOutdated();
      }
   }
}
