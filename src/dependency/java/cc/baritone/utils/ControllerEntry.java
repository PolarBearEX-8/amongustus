package cc.baritone.utils;

public class ControllerEntry {
   public int count;
   public String labelText;
   public int count2;
   public String labelText2;
   public boolean enabled;
   public boolean enabled2;

   public int getCount2() {
      return this.count2;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   @Override
   public String toString() {
      return String.format("AccountDetails[%s:%s:%s:%s:%s:%s]", this.count, this.labelText, this.count2, this.labelText2, this.enabled, this.enabled2);
   }

   public int getCount() {
      return this.count;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public String getLabelText2() {
      return this.labelText2;
   }
}
