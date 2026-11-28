package net.raphimc.minecraftauth.step;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import net.lenni0451.commons.httpclient.HttpClient;
import net.raphimc.minecraftauth.util.logging.ILogger;

public abstract class SameInputBiMergeStep<I1 extends AbstractStep.StepResult<?>, I2 extends AbstractStep.StepResult<?>, O extends BiMergeStep.StepResult<I1, I2>>
   extends BiMergeStep<I1, I2, O>
   implements SameInputStep<I1, O> {
   protected final List<AbstractStep<?, ?>> steps1UntilSameInput = new ArrayList<>();
   protected final List<AbstractStep<?, ?>> steps2UntilSameInput = new ArrayList<>();

   public SameInputBiMergeStep(String name, AbstractStep<?, I1> prevStep1, AbstractStep<?, I2> prevStep2) {
      super(name, prevStep1, prevStep2);
      if (this.prevStep2 != null) {
         this.steps1UntilSameInput.addAll(this.findCommonStep(this.prevStep, this.prevStep2));
         this.steps2UntilSameInput.addAll(this.findCommonStep(this.prevStep2, this.prevStep));
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
         return this.execute(logger, httpClient, prevResult1, prevResult2);
      }
   }

   @Override
   public O getFromInput(ILogger logger, HttpClient httpClient, AbstractStep.InitialInput input) throws Exception {
      I1 prevResult1 = this.prevStep.getFromInput(logger, httpClient, input);
      I2 prevResult2 = this.executeSecondaryStepChain(logger, httpClient, prevResult1, this.steps1UntilSameInput, this.steps2UntilSameInput);
      return this.execute(logger, httpClient, prevResult1, prevResult2);
   }

   public final O fromJson(JsonObject json) {
      this.insertDuplicateStepResultsIntoJson(json, this.steps1UntilSameInput, this.steps2UntilSameInput);
      return this.fromUnoptimizedJson(json);
   }

   public final JsonObject toJson(O result) {
      JsonObject json = this.toUnoptimizedJson(result);
      this.removeDuplicateStepResultsFromJson(json, this.steps2UntilSameInput);
      return json;
   }
}
