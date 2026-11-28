package wdl;

public abstract class MessageTypeCategory {
   public final String internalName;
   public static final MessageTypeCategory CORE_RECOMMENDED = new I18nableMessageTypeCategory("CORE_RECOMMENDED", "wdl.messages.category.core_recommended");
   public static final MessageTypeCategory CORE_DEBUG = new I18nableMessageTypeCategory("CORE_DEBUG", "wdl.messages.category.core_debug");

   public abstract String getDisplayName();

   public MessageTypeCategory(String str) {
      this.internalName = str;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         MessageTypeCategory messageTypeCategory = (MessageTypeCategory)obj;
         if (this.internalName == null) {
            if (messageTypeCategory.internalName != null) {
               return false;
            }
         } else if (!this.internalName.equals(messageTypeCategory.internalName)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return "MessageTypeCategory [internalName=" + this.internalName + ", displayName=" + this.getDisplayName() + "]";
   }

   @Override
   public int hashCode() {
      return 31 * ((byte)1) + (this.internalName == null ? 0 : this.internalName.hashCode());
   }
}
