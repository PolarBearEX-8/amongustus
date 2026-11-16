package client.util;

public class InternalRuntimeExceptionAdapter extends RuntimeException {
   public final GsonEntry gsonEntry;

   public InternalRuntimeExceptionAdapter(GsonEntry gsonEntry2) {
      this.gsonEntry = gsonEntry2;
   }
}
