package client.util;

public final class BytesWhereMaxAllowedManagerAdapter extends BytesWhereMaxAllowedManager {
   @Override
   public void applyTime(long value) {
   }

   public BytesWhereMaxAllowedManagerAdapter(long value) {
      super(value);
   }
}
