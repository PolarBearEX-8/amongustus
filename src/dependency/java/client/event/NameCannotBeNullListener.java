package client.event;

public abstract class NameCannotBeNullListener implements ExtendedListener {
   public final ExtendedListener extendedListener;
   public final String labelText;
   public final double value;
   public boolean enabled;

   public NameCannotBeNullListener withEnabled(boolean flag) {
      this.enabled = flag;
      return this;
   }

   @Override
   public boolean isEnabled() {
      return this.enabled;
   }

   @Override
   public ExtendedListener getExtendedListener() {
      return this.extendedListener;
   }

   @Override
   public String getLabelText() {
      return this.labelText;
   }

   @Override
   public double getValue() {
      return this.value;
   }

   public NameCannotBeNullListener(ExtendedListener extendedListener2, String text, double offset) {
      this.extendedListener = extendedListener2;
      this.labelText = text;
      this.value = offset;
      if (text == null) {
         throw new IllegalArgumentException("Name cannot be null!");
      }
   }

   @Override
   public boolean equals(Object other) {
      return other instanceof ExtendedListener && this.labelText.equals(((ExtendedListener)other).getLabelText());
   }

   @Override
   public int hashCode() {
      return this.labelText.hashCode();
   }
}
