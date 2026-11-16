package cc.baritone.utils;

public class BreadthFirstPlanner implements ServiceAdapterHandler {
   public static final ChannelCodeControlleController channelCodeControlleController = new ChannelCodeControlleController();
   public ExtraCustom extraCustom;
   public SfdSerController sfdSerController;
   public SfdSerController sfdSerController2;
   public DepthFirstPipelineCallbackHandler depthFirstPipelineCallbackHandler;
   public DepthFirstPipelineCall depthFirstPipelineCall;
   public DepthFirstPipelineCallbackHandlerImpl depthFirstPipelineCallbackHandlerImpl;
   public int count6;
   public int count7;
   public ControllerHandler controllerHandler = new ControllerHandler();
   public ChannelCodeControlleController channelCodeControlleController2;
   public boolean enabled;

   private void applyAStarService(AStarService aStarService) throws RuntimeComponent {
      float factor = 32700.0F;
      int count = aStarService.getCount17();
      aStarService.getCount11();
      int count2 = count == 3 ? 1 : 2;
      if (this.extraCustom == null) {
         this.extraCustom = new BreadthFirstCalibrator(aStarService.getCount3(), count2);
      }

      float[] floatArray = this.controllerHandler.getFloatArray();
      this.sfdSerController = new SfdSerController(0, factor, floatArray);
      if (count2 == 2) {
         this.sfdSerController2 = new SfdSerController(1, factor, floatArray);
      }

      this.count7 = count2;
      this.count6 = aStarService.getCount3();
      this.enabled = true;
   }

   public CallbackHandler resolveCallbackHandler(AStarService aStarService, DepthFirstPipeline depthFirstPipeline, int count) throws RuntimeComponent {
      Object entry = null;
      switch (count) {
         case 1:
            if (this.depthFirstPipelineCallbackHandlerImpl == null) {
               this.depthFirstPipelineCallbackHandlerImpl = new DepthFirstPipelineCallbackHandlerImpl();
               this.depthFirstPipelineCallbackHandlerImpl
                  .applyDepthFirstPipeline(depthFirstPipeline, aStarService, this.sfdSerController, this.sfdSerController2, this.extraCustom, 0);
            }

            entry = this.depthFirstPipelineCallbackHandlerImpl;
            break;
         case 2:
            if (this.depthFirstPipelineCall == null) {
               this.depthFirstPipelineCall = new DepthFirstPipelineCall();
               this.depthFirstPipelineCall.applyDepthFirstPipeline(depthFirstPipeline, aStarService, this.sfdSerController, this.sfdSerController2, this.extraCustom, 0);
            }

            entry = this.depthFirstPipelineCall;
            break;
         case 3:
            if (this.depthFirstPipelineCallbackHandler == null) {
               this.depthFirstPipelineCallbackHandler = new DepthFirstPipelineCallbackHandler(
                  depthFirstPipeline, aStarService, this.sfdSerController, this.sfdSerController2, this.extraCustom, 0
               );
            }

            entry = this.depthFirstPipelineCallbackHandler;
      }

      if (entry == null) {
         throw this.resolveRuntimeComponent(513, null);
      } else {
         return (CallbackHandler)entry;
      }
   }

   public RuntimeComponent resolveRuntimeComponent(int count, Throwable throwable) {
      return new RuntimeComponent(count, throwable);
   }

   public int getCount6() {
      return this.count6;
   }

   public ExtraCustom resolveExtraCustom(AStarService aStarService, DepthFirstPipeline depthFirstPipeline) throws RuntimeComponent {
      if (!this.enabled) {
         this.applyAStarService(aStarService);
      }

      int count = aStarService.getCount11();
      this.extraCustom.updateState2();
      this.resolveCallbackHandler(aStarService, depthFirstPipeline, count).updateState();
      this.extraCustom.applyIndex(1);
      return this.extraCustom;
   }

   public static ChannelCodeControlleController getChannelCodeControlleControll() {
      return (ChannelCodeControlleController)channelCodeControlleController.clone();
   }

   public void setExtraCustom(ExtraCustom extraCustom2) {
      this.extraCustom = extraCustom2;
   }

   public int getCount() {
      return 2304;
   }

   public void applyControllerHandler(ControllerHandler controllerHandler2) {
      ControllerHandler controllerHandler3 = controllerHandler2;
      if (controllerHandler2 == null) {
         controllerHandler3 = ControllerHandler.controllerHandler;
      }

      this.controllerHandler.applyControllerHandler(controllerHandler3);
      float[] floatArray = this.controllerHandler.getFloatArray();
      if (this.sfdSerController != null) {
         this.sfdSerController.applyFloatArrayByFloatArray(floatArray);
      }

      if (this.sfdSerController2 != null) {
         this.sfdSerController2.applyFloatArrayByFloatArray(floatArray);
      }
   }

   public int getCount7() {
      return this.count7;
   }

   public RuntimeComponent resolveRuntimeComponent(int count) {
      return new RuntimeComponent(count, null);
   }

   public BreadthFirstPlanner(ChannelCodeControlleController channelCodeControlleController3) {
      if (channelCodeControlleController3 == null) {
         channelCodeControlleController3 = channelCodeControlleController;
      }

      this.channelCodeControlleController2 = channelCodeControlleController3;
      ControllerHandler controllerHandler2 = this.channelCodeControlleController2.getControllerHandler();
      if (controllerHandler2 != null) {
         this.controllerHandler.applyControllerHandler(controllerHandler2);
      }
   }

   public BreadthFirstPlanner() {
      this(null);
   }
}
