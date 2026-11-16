package cc.baritone.utils;

public class RecordNodeImpl {
   public int[] countArray;
   public int[] countArray2;

   public RecordNodeImpl(int[] countArray3, int[] countArray4) {
      this.countArray = countArray3;
      this.countArray2 = countArray4;
   }

   public RecordNodeImpl() {
      this.countArray = new int[23];
      this.countArray2 = new int[14];
   }
}
