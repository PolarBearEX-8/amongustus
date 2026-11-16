package cc.baritone.utils;

public class RecordNode {
   public int[] countArray;
   public int[] countArray2;
   public final DepthFirstPipelineCallbackHandler depthFirstPipelineCallbackHandler;

   public RecordNode(DepthFirstPipelineCallbackHandler depthFirstPipelineCallbackHandler2) {
      this.depthFirstPipelineCallbackHandler = depthFirstPipelineCallbackHandler2;
      this.countArray = new int[5];
      this.countArray2 = new int[3];
   }

   public RecordNode(DepthFirstPipelineCallbackHandler depthFirstPipelineCallbackHandler2, int[] countArray3, int[] countArray4) {
      this.depthFirstPipelineCallbackHandler = depthFirstPipelineCallbackHandler2;
      this.countArray = countArray3;
      this.countArray2 = countArray4;
   }
}
