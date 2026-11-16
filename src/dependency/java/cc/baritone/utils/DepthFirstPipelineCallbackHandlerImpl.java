package cc.baritone.utils;

public class DepthFirstPipelineCallbackHandlerImpl implements CallbackHandler {
   public DepthFirstPipeline depthFirstPipeline;
   public AStarService aStarService;
   public SfdSerController sfdSerController;
   public SfdSerController sfdSerController2;
   public ExtraCustom extraCustom;
   public int count3;
   public int count2;
   public int count;
   public AbstractDepthFirstPipelineController[] abstractDepthFirstPipelineControllerArray;
   public ControllerProvider controllerProvider = null;

   @Override
   public void updateState() throws RuntimeComponent {
      this.count = this.aStarService.getCount19();
      this.abstractDepthFirstPipelineControllerArray = new AbstractDepthFirstPipelineController[32];
      this.count2 = this.aStarService.getCount17();
      this.updateState2();
      this.updateState5();
      this.updateState3();
      if (this.controllerProvider != null || this.aStarService.isActive()) {
         this.updateState6();
         this.updateState4();
      }
   }

   public void updateState6() {
      for (int index = 0; index < this.count; index++) {
         this.abstractDepthFirstPipelineControllerArray[index].applyDepthFirstPipeline(this.depthFirstPipeline, this.aStarService);
      }
   }

   public DepthFirstPipelineCallbackHandlerImpl() {
      this.controllerProvider = new ControllerProvider();
   }

   public void applyDepthFirstPipeline(DepthFirstPipeline depthFirstPipeline2, AStarService aStarService2, SfdSerController sfdSerController3, SfdSerController sfdSerController4, ExtraCustom extraCustom2, int count4) {
      this.depthFirstPipeline = depthFirstPipeline2;
      this.aStarService = aStarService2;
      this.sfdSerController = sfdSerController3;
      this.sfdSerController2 = sfdSerController4;
      this.extraCustom = extraCustom2;
      this.count3 = count4;
   }

   public void updateState4() {
      boolean flag = false;
      boolean flag2 = false;
      int count4 = this.aStarService.getCount17();

      do {
         for (int index = 0; index < this.count; index++) {
            flag = this.abstractDepthFirstPipelineControllerArray[index].matchesDepthFirstPipeline(this.depthFirstPipeline);
         }

         do {
            for (int index2 = 0; index2 < this.count; index2++) {
               flag2 = this.abstractDepthFirstPipelineControllerArray[index2].matchesSfdSerController(this.count3, this.sfdSerController, this.sfdSerController2);
            }

            this.sfdSerController.applyExtraCustom11(this.extraCustom);
            if (this.count3 == 0 && count4 != 3) {
               this.sfdSerController2.applyExtraCustom11(this.extraCustom);
            }
         } while (!flag2);
      } while (!flag);
   }

   public void updateState2() {
      if (this.count2 == 3) {
         for (int index = 0; index < this.count; index++) {
            this.abstractDepthFirstPipelineControllerArray[index] = new SfdSerControllerDepthFirstPipe(index);
         }
      } else if (this.count2 == 1) {
         int count4;
         for (count4 = 0; count4 < this.aStarService.getCount20(); count4++) {
            this.abstractDepthFirstPipelineControllerArray[count4] = new RuntimeComponentSupport(count4);
         }

         while (count4 < this.count) {
            this.abstractDepthFirstPipelineControllerArray[count4] = new DepthFirstPipelineSfdSerControllerDepthF(count4);
            count4++;
         }
      } else {
         for (int index2 = 0; index2 < this.count; index2++) {
            this.abstractDepthFirstPipelineControllerArray[index2] = new RuntimeComponentSupport(index2);
         }
      }
   }

   public void updateState5() throws RuntimeComponent {
      for (int index = 0; index < this.count; index++) {
         this.abstractDepthFirstPipelineControllerArray[index].applyDepthFirstPipeline(this.depthFirstPipeline, this.aStarService, this.controllerProvider);
      }
   }

   public void updateState3() {
   }
}
