package client.util;

public class HitBoxAggregator extends AbstractFunctionEntryController {
   public final String labelText;

   public HitBoxAggregator(Object entry, double offset, String text, FunctionEntry functionEntry) {
      super(entry, offset, functionEntry);
      this.labelText = text;
   }
}
