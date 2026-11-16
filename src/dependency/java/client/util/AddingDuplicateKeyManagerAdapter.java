package client.util;

public class AddingDuplicateKeyManagerAdapter extends AddingDuplicateKeyManager {
   public final Object dataRef;

   @Override
   public Object getObject(Object entry) {
      Object object = super.getObject(entry);
      return object == null ? this.dataRef : object;
   }

   public AddingDuplicateKeyManagerAdapter(Object entry) {
      this.dataRef = entry;
   }
}
