package client.util;

public class HitBoxEstimator extends AbstractFunctionEntryController {
   public final String labelText;

   public HitBoxEstimator(Object entry, double offset, String text, FunctionEntry functionEntry) {
      super(entry, offset, functionEntry);
      this.labelText = text;
   }
}
