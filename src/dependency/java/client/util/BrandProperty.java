package client.util;

public class BrandProperty implements Comparable {
   public final String labelText;
   public final String labelText2;
   public final String labelText3;
   public final boolean enabled;

   public BrandProperty(String text, String text2, String text3, boolean flag) {
      this.labelText = text;
      this.labelText2 = text2;
      this.labelText3 = text3;
      this.enabled = flag;
   }

   public String getString() {
      return this.labelText;
   }

   @Override
   public int hashCode() {
      return this.labelText.hashCode();
   }

   @Override
   public boolean equals(Object other) {
      return this == other ? true : (!(other instanceof BrandProperty) ? false : this.labelText.equals(((BrandProperty)other).labelText));
   }

   @Override
   public int compareTo(Object entry) {
      return this.compareTo((BrandProperty)entry);
   }

   public int compareTo(BrandProperty brandProperty) {
      return this.labelText.compareTo(brandProperty.labelText);
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   @Override
   public String toString() {
      return String.format("%s (%s)", this.labelText3, this.labelText2);
   }
}
