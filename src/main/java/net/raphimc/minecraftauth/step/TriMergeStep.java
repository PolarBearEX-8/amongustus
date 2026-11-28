package net.raphimc.minecraftauth.step;

import net.lenni0451.commons.httpclient.HttpClient;
import net.raphimc.minecraftauth.util.logging.ILogger;

public abstract class TriMergeStep<I1 extends AbstractStep.StepResult<?>, I2 extends AbstractStep.StepResult<?>, I3 extends AbstractStep.StepResult<?>, O extends TriMergeStep.StepResult<I1, I2, I3>>
   extends BiMergeStep<I1, I2, O> {
   public final AbstractStep<?, I3> prevStep3;

   public TriMergeStep(String name, AbstractStep<?, I1> prevStep1, AbstractStep<?, I2> prevStep2, AbstractStep<?, I3> prevStep3) {
      super(name, prevStep1, prevStep2);
      this.prevStep3 = prevStep3;
   }

   protected final O execute(ILogger logger, HttpClient httpClient, I1 prevResult1, I2 prevResult2) throws Exception {
      throw new UnsupportedOperationException();
   }

   protected abstract O execute(ILogger logger, HttpClient httpClient, I1 stepResult, I2 stepResult2, I3 stepResult3) throws Exception;

   public O refresh(ILogger logger, HttpClient httpClient, O result) throws Exception {
      if (!result.isExpiredOrOutdated()) {
         return result;
      } else {
         return this.execute(logger, httpClient, this.prevStep.refresh(logger, httpClient, result.prevResult()), this.prevStep2 != null ? this.prevStep2.refresh(logger, httpClient, result.prevResult2()) : null, this.prevStep3 != null ? this.prevStep3.refresh(logger, httpClient, result.prevResult3()) : null);
      }
   }

   public O getFromInput(ILogger logger, HttpClient httpClient, AbstractStep.InitialInput input) throws Exception {
      return this.execute(logger, httpClient, this.prevStep.getFromInput(logger, httpClient, input), this.prevStep2 != null ? this.prevStep2.getFromInput(logger, httpClient, input) : null, this.prevStep3 != null ? this.prevStep3.getFromInput(logger, httpClient, input) : null);
   }

   public abstract static class StepResult<P1 extends AbstractStep.StepResult<?>, P2 extends AbstractStep.StepResult<?>, P3 extends AbstractStep.StepResult<?>>
      extends BiMergeStep.StepResult<P1, P2> {
      protected abstract P3 prevResult3();

      @Override
      public boolean isExpired() {
         return super.isExpired() || this.prevResult3() != null && this.prevResult3().isExpired();
      }

      @Override
      public boolean isExpiredOrOutdated() {
         return super.isExpiredOrOutdated() || this.prevResult3() != null && this.prevResult3().isExpiredOrOutdated();
      }
   }
}
