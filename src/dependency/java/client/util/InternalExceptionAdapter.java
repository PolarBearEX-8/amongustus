package client.util;

public class InternalExceptionAdapter extends Exception {
   public final Object[] objectArray;

   public Object[] getObjectArray() {
      return this.objectArray;
   }

   public InternalExceptionAdapter(String text, Object[] entryArray) {
      super(text);
      this.objectArray = entryArray;
   }
}
