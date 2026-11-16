package client.util;

public class VectorProfiler extends VectorCollector {
   public final String labelText3;
   public final String labelText2;
   public final String labelText;

   public VectorProfiler(Double offset, String text, String text2, String text3) {
      super(offset, text + "\n" + text2 + "\n" + text3);
      this.labelText2 = text3;
      this.labelText = text2;
      this.labelText3 = text;
   }

   public String getText() {
      return (String)super.getDataRef();
   }

   @Override
   public Object getDataRef() {
      return this.getText();
   }
}
