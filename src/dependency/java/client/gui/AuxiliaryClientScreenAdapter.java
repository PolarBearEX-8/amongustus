package client.gui;

public abstract class AuxiliaryClientScreenAdapter extends ClientScreen {
   public final String labelText2;

   public String getLabelText2() {
      return this.labelText2;
   }

   public AuxiliaryClientScreenAdapter(String text) {
      this.labelText2 = text;
   }
}
