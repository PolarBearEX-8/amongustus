package cc.baritone.utils;

public class RecordNodeAdapter {
   public int[] countArray = new int[4];
   public RecordNodeSupport[] recordNodeSupportArray = new RecordNodeSupport[2];

   public RecordNodeAdapter() {
      this.recordNodeSupportArray[0] = new RecordNodeSupport();
      this.recordNodeSupportArray[1] = new RecordNodeSupport();
   }
}
