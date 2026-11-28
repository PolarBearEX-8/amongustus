package net.raphimc.minecraftauth.step.msa;

import com.google.gson.JsonObject;
import net.lenni0451.commons.httpclient.HttpClient;
import net.raphimc.minecraftauth.step.AbstractStep;
import net.raphimc.minecraftauth.util.logging.ILogger;

public abstract class InitialPreparationStep<I extends AbstractStep.InitialInput, O extends AbstractStep.InitialInput> extends AbstractStep<I, O> {
   public InitialPreparationStep(String name, AbstractStep.ApplicationDetails applicationDetails) {
      super(name, applicationDetails);
   }

   public final O refresh(ILogger logger, HttpClient httpClient, O result) {
      throw new UnsupportedOperationException("Cannot refresh initial steps");
   }

   public final O fromJson(JsonObject json) {
      throw new UnsupportedOperationException("Cannot deserialize initial steps");
   }

   public final JsonObject toJson(O result) {
      throw new UnsupportedOperationException("Cannot serialize initial steps");
   }
}
