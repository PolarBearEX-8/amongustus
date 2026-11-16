package augustus.anticheat.checks;

import client.Client;
import client.setting.DirectOptionType;
import client.util.CompositeDoubleEntry;
import client.util.Packet;

public class Intave14InventorySlotDupeCheck extends AntiCheatCheck {
   public final CompositeDoubleEntry compositeDoubleEntry;
   public int count;
   public int count2 = 0;

   @Override
   public void updateState2() {
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   public Intave14InventorySlotDupeCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.count = 0;
      this.compositeDoubleEntry = new CompositeDoubleEntry(9669, "sent slot twice", DirectOptionType.KILLAURA);
   }
}
