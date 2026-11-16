package cc.baritone.utils;

public abstract class CORERECOMMENDEDManager {
   public final String labelText;
   public static final CORERECOMMENDEDManager cORERECOMMENDEDManager2 = new InternalStringEntry("CORE_RECOMMENDED", "wdl.messages.category.core_recommended");
   public static final CORERECOMMENDEDManager cORERECOMMENDEDManager = new InternalStringEntry("CORE_DEBUG", "wdl.messages.category.core_debug");

   public abstract String getText();

   public CORERECOMMENDEDManager(String text) {
      this.labelText = text;
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other == null) {
         return false;
      } else if (this.getClass() != other.getClass()) {
         return false;
      } else {
         CORERECOMMENDEDManager cORERECOMMENDEDManager3 = (CORERECOMMENDEDManager)other;
         if (this.labelText == null) {
            if (cORERECOMMENDEDManager3.labelText != null) {
               return false;
            }
         } else if (!this.labelText.equals(cORERECOMMENDEDManager3.labelText)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return "MessageTypeCategory [internalName=" + this.labelText + ", displayName=" + this.getText() + "]";
   }

   @Override
   public int hashCode() {
      return 31 + (this.labelText == null ? 0 : this.labelText.hashCode());
   }
}
