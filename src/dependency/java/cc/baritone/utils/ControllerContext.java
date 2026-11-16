package cc.baritone.utils;

public class ControllerContext {
   public String labelText;
   public boolean enabled;
   public String labelText2;
   public String labelText3;

   @Override
   public String toString() {
      return String.format("License[%s:%s:%s:%s]", this.labelText, this.enabled, this.labelText2, this.labelText3);
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public String getLabelText3() {
      return this.labelText3;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   @Override
   public boolean equals(Object other) {
      if (!(other instanceof ControllerContext)) {
         return false;
      } else {
         ControllerContext controllerContext = (ControllerContext)other;
         return controllerContext.getLabelText3().equals(this.getLabelText3())
            && controllerContext.getLabelText2().equals(this.getLabelText2())
            && controllerContext.isEnabled() == this.isEnabled()
            && controllerContext.getLabelText().equals(this.getLabelText());
      }
   }

   public String getLabelText() {
      return this.labelText;
   }
}
