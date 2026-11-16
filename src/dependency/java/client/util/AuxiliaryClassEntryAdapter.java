package client.util;

public final class AuxiliaryClassEntryAdapter extends InternalClassEntry {
   public AuxiliaryClassEntryAdapter(Class clazz, int count, int count2) {
      super(clazz, count, count2);
   }

   @Override
   public boolean checkIndex(int count) {
      return super.checkIndex(count) && count > 4;
   }
}
