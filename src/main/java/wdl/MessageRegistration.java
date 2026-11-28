package wdl;



import wdl.api.IWDLMessageType;
public class MessageRegistration {
   public final String name;
   public final IWDLMessageType type;
   public final MessageTypeCategory category;

   public MessageRegistration(String str, IWDLMessageType wdlMessageType, MessageTypeCategory messageTypeCategory) {
      this.name = str;
      this.type = wdlMessageType;
      this.category = messageTypeCategory;
   }

   @Override
   public int hashCode() {
      int n = 1;
      n = 31 * n + (this.name == null ? 0 : this.name.hashCode());
      n = 31 * n + (this.category == null ? 0 : this.category.hashCode());
      return 31 * n + (this.type == null ? 0 : this.type.hashCode());
   }

   @Override
   public String toString() {
      return "MessageRegistration [name=" + this.name + ", type=" + this.type + ", category=" + this.category + "]";
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (!(obj instanceof MessageRegistration)) {
         return false;
      } else {
         MessageRegistration messageRegistration = (MessageRegistration)obj;
         if (this.name == null) {
            if (messageRegistration.name != null) {
               return false;
            }
         } else if (!this.name.equals(messageRegistration.name)) {
            return false;
         }

         if (this.category == null) {
            if (messageRegistration.category != null) {
               return false;
            }
         } else if (!this.category.equals(messageRegistration.category)) {
            return false;
         }

         if (this.type == null) {
            if (messageRegistration.type != null) {
               return false;
            }
         } else if (!this.type.equals(messageRegistration.type)) {
            return false;
         }

         return true;
      }
   }
}
