package net.raphimc.minecraftauth.step;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.lenni0451.commons.httpclient.HttpClient;
import net.raphimc.minecraftauth.util.logging.ILogger;

public interface SameInputStep<I1 extends AbstractStep.StepResult<?>, O extends AbstractStep.StepResult<?>> {
   O fromUnoptimizedJson(JsonObject json);

   JsonObject toUnoptimizedJson(O stepResult);

   default <I2 extends AbstractStep.StepResult<?>> I2 executeSecondaryStepChain(
      ILogger logger, HttpClient httpClient, I1 prevResult1, List<AbstractStep<?, ?>> steps1UntilSameInput, List<AbstractStep<?, ?>> steps2UntilSameInput
   ) throws Exception {
      if (steps2UntilSameInput.isEmpty()) {
         return null;
      } else {
         AbstractStep.StepResult<?> prevResult = prevResult1;

         for (int i = 0; i < steps1UntilSameInput.size() - 1; i++) {
            prevResult = prevResult.prevResult();
         }

         for (int i = 1; i < steps2UntilSameInput.size(); i++) {
            AbstractStep step = steps2UntilSameInput.get(i);
            prevResult = step.execute(logger, httpClient, prevResult);
         }

         return (I2)prevResult;
      }
   }

   default <I2 extends AbstractStep.StepResult<?>> I2 refreshSecondaryStepChain(
      ILogger logger,
      HttpClient httpClient,
      I1 prevResult1,
      AbstractStep.StepResult<?> prevResult2,
      List<AbstractStep<?, ?>> steps1UntilSameInput,
      List<AbstractStep<?, ?>> steps2UntilSameInput
   ) throws Exception {
      int count;
      for (count = 1; count < steps2UntilSameInput.size() && prevResult2.isExpiredOrOutdated(); count++) {
         prevResult2 = prevResult2.prevResult();
      }

      if (count == steps2UntilSameInput.size()) {
         AbstractStep.StepResult<?> prevResult = prevResult1;

         for (int i = 0; i < steps1UntilSameInput.size() - 1; i++) {
            prevResult = prevResult.prevResult();
         }

         prevResult2 = prevResult;
      }

      for (int i = steps2UntilSameInput.size() - count + 1; i < steps2UntilSameInput.size(); i++) {
         AbstractStep step = steps2UntilSameInput.get(i);
         prevResult2 = step.execute(logger, httpClient, prevResult2);
      }

      return (I2)prevResult2;
   }

   default void removeDuplicateStepResultsFromJson(JsonObject json, List<AbstractStep<?, ?>> steps2UntilSameInput) {
      if (!steps2UntilSameInput.isEmpty()) {
         JsonObject resultJson = json;

         for (int i = steps2UntilSameInput.size() - 1; i >= 0; i--) {
            AbstractStep<?, ?> step = steps2UntilSameInput.get(i);
            if (i == 0) {
               resultJson.remove(step.name);
               break;
            }

            resultJson = resultJson.getAsJsonObject(step.name);
         }
      }
   }

   default void insertDuplicateStepResultsIntoJson(
      JsonObject json, List<AbstractStep<?, ?>> steps1UntilSameInput, List<AbstractStep<?, ?>> steps2UntilSameInput
   ) {
      if (!steps2UntilSameInput.isEmpty()) {
         String targetName = null;
         JsonObject step2Json = json;

         for (int i = steps2UntilSameInput.size() - 1; i >= 0; i--) {
            AbstractStep<?, ?> step = steps2UntilSameInput.get(i);
            if (i == 0) {
               targetName = step.name;
               break;
            }

            step2Json = step2Json.getAsJsonObject(step.name);
         }

         JsonObject step1Json = json;

         for (int i = steps1UntilSameInput.size() - 1; i >= 0; i--) {
            AbstractStep<?, ?> step = steps1UntilSameInput.get(i);
            step1Json = step1Json.getAsJsonObject(step.name);
         }

         step2Json.add(targetName, step1Json);
      }
   }

   default List<AbstractStep<?, ?>> findCommonStep(AbstractStep<?, ?> step1, AbstractStep<?, ?> step2) {
      List<AbstractStep<?, ?>> stepsUntilSameInput = new ArrayList<>();
      stepsUntilSameInput.add(step1);
      AbstractStep<?, ?> step1Temp = step1;

      while ((step1Temp = step1Temp.prevStep) != null) {
         stepsUntilSameInput.add(step1Temp);
         AbstractStep<?, ?> step2Temp = step2;

         while ((step2Temp = step2Temp.prevStep) != null) {
            if (step1Temp == step2Temp) {
               Collections.reverse(stepsUntilSameInput);
               return stepsUntilSameInput;
            }
         }
      }

      throw new IllegalStateException("Cannot find a common step");
   }
}
