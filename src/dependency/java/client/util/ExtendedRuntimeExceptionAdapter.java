package client.util;

public class ExtendedRuntimeExceptionAdapter extends RuntimeException {
   public final InternalComparableAdapter internalComparableAdapter;

   public ExtendedRuntimeExceptionAdapter(InternalComparableAdapter internalComparableAdapter2, String text) {
      super(text);
      this.internalComparableAdapter = internalComparableAdapter2;
   }
}
