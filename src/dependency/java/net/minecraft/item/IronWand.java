package net.minecraft.item;

public class IronWand {
   public final int count2;
   public Object dataRef;
   public IronWand ironWand;
   public final int count;

   public final Object getDataRef() {
      return this.dataRef;
   }

   public final int getCount2() {
      return this.count2;
   }

   public IronWand(int count3, int count4, Object entry, IronWand ironWand2) {
      this.dataRef = entry;
      this.ironWand = ironWand2;
      this.count2 = count4;
      this.count = count3;
   }

   @Override
   public final boolean equals(Object other) {
      if (!(other instanceof IronWand)) {
         return false;
      } else {
         IronWand ironWand2 = (IronWand)other;
         Integer count3 = this.getCount2();
         Integer count4 = ironWand2.getCount2();
         if (count3 == count4 || count3 != null && count3.equals(count4)) {
            Object dataRef2 = this.getDataRef();
            Object dataRef3 = ironWand2.getDataRef();
            if (dataRef2 == dataRef3 || dataRef2 != null && dataRef2.equals(dataRef3)) {
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public final String toString() {
      return this.getCount2() + "=" + this.getDataRef();
   }

   @Override
   public final int hashCode() {
      return AltItemArrayEntry.computeCount(this.count2);
   }
}
