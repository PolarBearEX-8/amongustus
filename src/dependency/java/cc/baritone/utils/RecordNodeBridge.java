package cc.baritone.utils;

public class RecordNodeBridge {
   public int count;
   public int count2;
   public final RIFFDDCController rIFFDDCController;

   public RecordNodeBridge(RIFFDDCController rIFFDDCController2) {
      this.rIFFDDCController = rIFFDDCController2;
      this.count = 0;
      this.count2 = 0;
   }
}
