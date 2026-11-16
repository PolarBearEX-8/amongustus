package augustus.anticheat.checks;

import client.Client;
import client.util.Packet;

public class Intave14InventoryWalkingCheck extends AntiCheatCheck {
   public int count = 0;

   @Override
   public void updateState2() {
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   public Intave14InventoryWalkingCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
   }
}
