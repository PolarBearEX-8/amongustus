package client.util;

public class AuxiliaryComparableAdapter implements Comparable {
   public final int count;
   public static final AuxiliaryComparableAdapter auxiliaryComparableAdapter = new AuxiliaryComparableAdapter(0, 0, 0);
   public final int count2;
   public final int count3;

   public int getCount() {
      return this.count;
   }

   public int computeCount(AuxiliaryComparableAdapter auxiliaryComparableAdapter2) {
      return this.getCount2() == auxiliaryComparableAdapter2.getCount2()
         ? (this.getCount3() == auxiliaryComparableAdapter2.getCount3() ? this.getCount() - auxiliaryComparableAdapter2.getCount() : this.getCount3() - auxiliaryComparableAdapter2.getCount3())
         : this.getCount2() - auxiliaryComparableAdapter2.getCount2();
   }

   public AuxiliaryComparableAdapter resolveAuxiliaryComparableAdapter(AuxiliaryComparableAdapter auxiliaryComparableAdapter2) {
      return new AuxiliaryComparableAdapter(
         this.getCount2() * auxiliaryComparableAdapter2.getCount3() - this.getCount3() * auxiliaryComparableAdapter2.getCount2(),
         this.getCount3() * auxiliaryComparableAdapter2.getCount() - this.getCount() * auxiliaryComparableAdapter2.getCount3(),
         this.getCount() * auxiliaryComparableAdapter2.getCount2() - this.getCount2() * auxiliaryComparableAdapter2.getCount()
      );
   }

   public int getCount3() {
      return this.count3;
   }

   public int getCount2() {
      return this.count2;
   }

   public double computeValueByDouble(double posX, double posY, double posZ) {
      double offset = (double)this.getCount() - posX;
      double offset2 = (double)this.getCount2() - posY;
      double offset3 = (double)this.getCount3() - posZ;
      return offset * offset + offset2 * offset2 + offset3 * offset3;
   }

   public double computeValue(double posX, double posY, double posZ) {
      double offset = (double)this.getCount() + 0.5 - posX;
      double offset2 = (double)this.getCount2() + 0.5 - posY;
      double offset3 = (double)this.getCount3() + 0.5 - posZ;
      return offset * offset + offset2 * offset2 + offset3 * offset3;
   }

   public double computeValue(AuxiliaryComparableAdapter auxiliaryComparableAdapter2) {
      double count4 = (double)auxiliaryComparableAdapter2.getCount();
      double count5 = (double)auxiliaryComparableAdapter2.getCount3();
      double count6 = (double)auxiliaryComparableAdapter2.getCount2();
      double offset = count4;
      return this.computeValueByDouble(offset, count6, count5);
   }

   public AuxiliaryComparableAdapter(int count4, int count5, int count6) {
      this.count = count4;
      this.count2 = count5;
      this.count3 = count6;
   }

   public AuxiliaryComparableAdapter(double posX, double posY, double posZ) {
      this(InternalFloatArrayEntry.computeCount(posX), InternalFloatArrayEntry.computeCount(posY), InternalFloatArrayEntry.computeCount(posZ));
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof AuxiliaryComparableAdapter)) {
         return false;
      } else {
         AuxiliaryComparableAdapter auxiliaryComparableAdapter2 = (AuxiliaryComparableAdapter)other;
         return this.getCount() != auxiliaryComparableAdapter2.getCount() ? false : (this.getCount2() != auxiliaryComparableAdapter2.getCount2() ? false : this.getCount3() == auxiliaryComparableAdapter2.getCount3());
      }
   }

   @Override
   public String toString() {
      return this.getCount() + "," + this.getCount2() + "," + this.getCount3();
   }

   @Override
   public int hashCode() {
      return (this.getCount2() + this.getCount3() * 31) * 31 + this.getCount();
   }

   @Override
   public int compareTo(Object entry) {
      AuxiliaryComparableAdapter auxiliaryComparableAdapter2 = (AuxiliaryComparableAdapter)entry;
      return this.computeCount(auxiliaryComparableAdapter2);
   }
}
