package cc.baritone.utils;

public class DepthFirstPipelineCall extends DepthFirstPipelineCallbackHandlerImpl implements CallbackHandler {
   @Override
   public void updateState3() {
      for (int index = 0; index < this.count; index++) {
         ((DepthFirstPipelineDepthFirstPipe)this.abstractDepthFirstPipelineControllerArray[index])
            .applyDepthFirstPipeline(this.depthFirstPipeline, this.controllerProvider);
      }
   }

   @Override
   public void updateState2() {
      if (this.count2 == 3) {
         for (int index = 0; index < this.count; index++) {
            this.abstractDepthFirstPipelineControllerArray[index] = new DepthFirstPipelineDepthFirstPipe(index);
         }
      } else if (this.count2 == 1) {
         int count;
         for (count = 0; count < this.aStarService.getCount20(); count++) {
            this.abstractDepthFirstPipelineControllerArray[count] = new DepthFirstPipelineDepthFirstPipelineDept(count);
         }

         while (count < this.count) {
            this.abstractDepthFirstPipelineControllerArray[count] = new DepthFirstPipelineDepthFirstPipelineDeptImpl(count);
            count++;
         }
      } else {
         for (int index2 = 0; index2 < this.count; index2++) {
            this.abstractDepthFirstPipelineControllerArray[index2] = new DepthFirstPipelineDepthFirstPipelineDept(index2);
         }
      }
   }
}
