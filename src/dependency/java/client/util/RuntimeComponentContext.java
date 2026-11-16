package client.util;

public class RuntimeComponentContext extends AbstractLISTManagerRecord {
   public final String valueText;

   public RuntimeComponentContext(String labelText, String valueText) {
      this.labelText = labelText;
      this.valueText = valueText;
   }

   @Override
   public LISTManager getLISTManager() throws SecondaryExceptionAdapter {
      return null;
   }
}
