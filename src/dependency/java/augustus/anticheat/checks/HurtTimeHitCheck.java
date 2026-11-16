package augustus.anticheat.checks;

import client.Client;
import client.util.ClickHelper;
import client.util.Packet;
import java.util.LinkedList;

public class HurtTimeHitCheck extends AntiCheatCheck {
   public final ClickHelper[] clickHelperArray = new ClickHelper[10];
   public boolean enabled;
   public static final double value = 0.8;
   public final LinkedList linkedList = new LinkedList();
   public static final double value3 = 1.0;
   public double value2 = 0.0;

   private void updateState5() {
      Client.getClient().getClass();
   }

   @Override
   public void updateState2() {
   }

   private boolean isActiveAsFlag() {
      ClickHelper[] clickHelperArray2 = this.clickHelperArray;
      int length = clickHelperArray2.length;

      for (int index = 0; index < length; index++) {
         if (!clickHelperArray2[index].canExecute()) {
            return false;
         }
      }

      return true;
   }

   private void updateState6() {
      ClickHelper[] clickHelperArray2 = this.clickHelperArray;
      int length = clickHelperArray2.length;

      for (int index = 0; index < length; index++) {
         clickHelperArray2[index].clear();
      }
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   public HurtTimeHitCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      super.state = true;

      for (int index = 0; index < this.clickHelperArray.length; index++) {
         this.clickHelperArray[index] = new ClickHelper(50);
      }
   }
}
