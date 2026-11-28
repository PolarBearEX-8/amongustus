package net.raphimc.minecraftauth.step.xbl.adapter;

import com.google.gson.JsonObject;
import net.lenni0451.commons.httpclient.HttpClient;
import net.raphimc.minecraftauth.step.AbstractStep;
import net.raphimc.minecraftauth.step.xbl.StepXblXstsToken;
import net.raphimc.minecraftauth.step.xbl.session.StepFullXblSession;
import net.raphimc.minecraftauth.util.logging.ILogger;

public class StepXblXstsToFullXblSession extends AbstractStep<StepXblXstsToken.XblXsts<?>, StepFullXblSession.FullXblSession> {
   public StepXblXstsToFullXblSession(AbstractStep<?, ? extends StepXblXstsToken.XblXsts<?>> prevStep) {
      super("xblXstsToFullXblSession", (AbstractStep<?, StepXblXstsToken.XblXsts<?>>)prevStep);
   }

   protected StepFullXblSession.FullXblSession execute(ILogger logger, HttpClient httpClient, StepXblXstsToken.XblXsts<?> xblXsts) throws Exception {
      return new StepXblXstsToFullXblSession.FullXblSessionWrapper(xblXsts);
   }

   public StepFullXblSession.FullXblSession refresh(ILogger logger, HttpClient httpClient, StepFullXblSession.FullXblSession fullXblSession) throws Exception {
      StepXblXstsToFullXblSession.FullXblSessionWrapper fullXblSessionWrapper = (StepXblXstsToFullXblSession.FullXblSessionWrapper)fullXblSession;
      return new StepXblXstsToFullXblSession.FullXblSessionWrapper(this.prevStep.refresh(logger, httpClient, fullXblSessionWrapper.xblXsts));
   }

   public StepFullXblSession.FullXblSession fromJson(JsonObject json) {
      return new StepXblXstsToFullXblSession.FullXblSessionWrapper(this.prevStep.fromJson(json.getAsJsonObject(this.prevStep.name)));
   }

   public JsonObject toJson(StepFullXblSession.FullXblSession fullXblSession) {
      StepXblXstsToFullXblSession.FullXblSessionWrapper fullXblSessionWrapper = (StepXblXstsToFullXblSession.FullXblSessionWrapper)fullXblSession;
      JsonObject json = new JsonObject();
      json.add(this.prevStep.name, this.prevStep.toJson(fullXblSessionWrapper.xblXsts));
      return json;
   }

   private static class FullXblSessionWrapper extends StepFullXblSession.FullXblSession {
      private final StepXblXstsToken.XblXsts<?> xblXsts;

      public FullXblSessionWrapper(StepXblXstsToken.XblXsts<?> xblXsts) {
         super(xblXsts.getFullXblSession());
         this.xblXsts = xblXsts;
      }
   }
}
