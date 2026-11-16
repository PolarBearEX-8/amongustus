package client.util;

public class InternalComparableAdapter implements Comparable {
   public final ServiceStringEntry serviceStringEntry;
   public final int count2;
   public final int count;
   public final int count3;
   public boolean enabled;
   public float factor = 1.0F;

   public ServiceStringEntry getServiceStringEntry() {
      return this.serviceStringEntry;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public int getCountAsIndex() {
      return this.enabled
         ? ExtendedSetEntry.computeCount((int)((float)this.count * this.factor), this.count3)
         : ExtendedSetEntry.computeCount((int)((float)this.count2 * this.factor), this.count3);
   }

   public int getCount() {
      return this.enabled
         ? ExtendedSetEntry.computeCount((int)((float)this.count2 * this.factor), this.count3)
         : ExtendedSetEntry.computeCount((int)((float)this.count * this.factor), this.count3);
   }

   public void applyIndex(int count4) {
      if (this.count2 > count4 && this.count > count4) {
         this.factor = (float)count4 / (float)Math.min(this.count2, this.count);
      }
   }

   public int computeCount(InternalComparableAdapter internalComparableAdapter) {
      int count4;
      if (this.getCount() == internalComparableAdapter.getCount()) {
         if (this.getCountAsIndex() == internalComparableAdapter.getCountAsIndex()) {
            if (this.serviceStringEntry.getLabelText3() == null) {
               return internalComparableAdapter.serviceStringEntry.getLabelText3() == null ? 0 : -1;
            }

            return this.serviceStringEntry.getLabelText3().compareTo(internalComparableAdapter.serviceStringEntry.getLabelText3());
         }

         count4 = this.getCountAsIndex() < internalComparableAdapter.getCountAsIndex() ? 1 : -1;
      } else {
         count4 = this.getCount() < internalComparableAdapter.getCount() ? 1 : -1;
      }

      return count4;
   }

   public void updateState() {
      this.enabled = !this.enabled;
   }

   public InternalComparableAdapter(ServiceStringEntry serviceStringEntry2, int count4) {
      this.serviceStringEntry = serviceStringEntry2;
      this.count2 = serviceStringEntry2.getCount();
      this.count = serviceStringEntry2.getCount2();
      this.count3 = count4;
      this.enabled = ExtendedSetEntry.computeCount(this.count, count4) > ExtendedSetEntry.computeCount(this.count2, count4);
   }

   @Override
   public String toString() {
      return "Holder{width=" + this.count2 + ", height=" + this.count + '}';
   }

   @Override
   public int compareTo(Object entry) {
      return this.computeCount((InternalComparableAdapter)entry);
   }
}
