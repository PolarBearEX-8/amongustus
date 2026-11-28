package net.raphimc.minecraftauth.step;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import net.lenni0451.commons.httpclient.HttpClient;
import net.raphimc.minecraftauth.util.logging.ILogger;

public abstract class SameInputTriMergeStep<I1 extends AbstractStep.StepResult<?>, I2 extends AbstractStep.StepResult<?>, I3 extends AbstractStep.StepResult<?>, O extends TriMergeStep.StepResult<I1, I2, I3>>
   extends TriMergeStep<I1, I2, I3, O>
   implements SameInputStep<I1, O> {
   protected final List<AbstractStep<?, ?>> steps1UntilSameInput = new ArrayList<>();
   protected final List<AbstractStep<?, ?>> steps2UntilSameInput = new ArrayList<>();
   protected final List<AbstractStep<?, ?>> steps3UntilSameInput = new ArrayList<>();

   public SameInputTriMergeStep(String name, AbstractStep<?, I1> prevStep1, AbstractStep<?, I2> prevStep2, AbstractStep<?, I3> prevStep3) {
      super(name, prevStep1, prevStep2, prevStep3);
      AbstractStep<?, ?> secondaryStep = null;
      if (this.prevStep2 != null) {
         secondaryStep = this.prevStep2;
         this.steps2UntilSameInput.addAll(this.findCommonStep(this.prevStep2, this.prevStep));
      }

      if (this.prevStep3 != null) {
         secondaryStep = this.prevStep3;
         this.steps3UntilSameInput.addAll(this.findCommonStep(this.prevStep3, this.prevStep));
      }

      if (secondaryStep != null) {
         this.steps1UntilSameInput.addAll(this.findCommonStep(this.prevStep, secondaryStep));
      }
   }

   @Override
   public O refresh(ILogger logger, HttpClient httpClient, O result) throws Exception {
      if (!result.isExpiredOrOutdated()) {
         return result;
      } else {
         I1 prevResult1 = this.prevStep.refresh(logger, httpClient, result.prevResult());
         I2 prevResult2 = this.refreshSecondaryStepChain(
            logger, httpClient, prevResult1, result.prevResult2(), this.steps1UntilSameInput, this.steps2UntilSameInput
         );
         I3 prevResult3 = this.refreshSecondaryStepChain(
            logger, httpClient, prevResult1, result.prevResult3(), this.steps1UntilSameInput, this.steps3UntilSameInput
         );
         return this.execute(logger, httpClient, prevResult1, prevResult2, prevResult3);
      }
   }

   @Override
   public O getFromInput(ILogger logger, HttpClient httpClient, AbstractStep.InitialInput input) throws Exception {
      I1 prevResult1 = this.prevStep.getFromInput(logger, httpClient, input);
      I2 prevResult2 = this.executeSecondaryStepChain(logger, httpClient, prevResult1, this.steps1UntilSameInput, this.steps2UntilSameInput);
      I3 prevResult3 = this.executeSecondaryStepChain(logger, httpClient, prevResult1, this.steps1UntilSameInput, this.steps3UntilSameInput);
      return this.execute(logger, httpClient, prevResult1, prevResult2, prevResult3);
   }

   public final O fromJson(JsonObject json) {
      this.insertDuplicateStepResultsIntoJson(json, this.steps1UntilSameInput, this.steps2UntilSameInput);
      this.insertDuplicateStepResultsIntoJson(json, this.steps1UntilSameInput, this.steps3UntilSameInput);
      return this.fromUnoptimizedJson(json);
   }

   public final JsonObject toJson(O result) {
      JsonObject json = this.toUnoptimizedJson(result);
      this.removeDuplicateStepResultsFromJson(json, this.steps2UntilSameInput);
      this.removeDuplicateStepResultsFromJson(json, this.steps3UntilSameInput);
      return json;
   }
}
