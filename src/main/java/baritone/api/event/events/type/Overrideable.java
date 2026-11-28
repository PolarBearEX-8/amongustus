package baritone.api.event.events.type;

public class Overrideable {
   private Object value;
   private boolean modified;

   public Object getValue() {
      return this.value;
   }

   public Overrideable(Object obj) {
      this.value = obj;
   }

   @Override
   public String toString() {
      return String.format("Overrideable{modified=%b,value=%s}", this.modified, this.value.toString());
   }

   public void set(Object obj) {
      this.value = obj;
      this.modified = true;
   }

   public boolean isModified() {
      return this.modified;
   }
}
