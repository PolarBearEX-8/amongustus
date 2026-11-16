package client.util;

import com.viaversion.viaversion.libs.gson.ClientRedirectController;

public final class TimerTask extends java.util.TimerTask {
   private static void updateState() {
      try {
         ClientRedirectController.updateState();
         ClientRedirectController.labelText = ClientRedirectController.resolveText(ClientRedirectController.getLabelText4());
         System.out.println(ClientRedirectController.labelText);
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   @Override
   public void run() {
      ClientRedirectController.getExecutorService().submit(TimerTask::updateState);
   }
}
