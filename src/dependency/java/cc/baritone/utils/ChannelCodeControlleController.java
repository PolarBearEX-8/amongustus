package cc.baritone.utils;

public class ChannelCodeControlleController implements Cloneable {
   public ChannelCodeController channelCodeController = ChannelCodeController.channelCodeController3;
   public ControllerHandler controllerHandler = new ControllerHandler();

   public ControllerHandler getControllerHandler() {
      return this.controllerHandler;
   }

   public void applyChannelCodeController(ChannelCodeController channelCodeController2) {
      if (channelCodeController2 == null) {
         throw new NullPointerException("out");
      } else {
         this.channelCodeController = channelCodeController2;
      }
   }

   @Override
   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException cloneNotSupportedException) {
         throw new InternalError(this + ": " + cloneNotSupportedException);
      }
   }

   public ChannelCodeController getChannelCodeController() {
      return this.channelCodeController;
   }
}
