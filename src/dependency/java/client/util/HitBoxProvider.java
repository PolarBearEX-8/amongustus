package client.util;

public class HitBoxProvider extends AbstractFunctionEntryController {
   public final String labelText;
   public final String labelText2;
   public final String labelText3;

   public HitBoxProvider(Object entry, double offset, String text, String text2, String text3, FunctionEntry functionEntry) {
      super(entry, offset, functionEntry);
      this.labelText3 = text;
      this.labelText = text2;
      this.labelText2 = text3;
   }
}
