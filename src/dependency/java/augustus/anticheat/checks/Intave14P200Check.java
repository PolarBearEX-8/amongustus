package augustus.anticheat.checks;

import client.Client;
import client.setting.DirectOptionType;
import client.util.CompositeDoubleEntry;
import client.util.Packet;

public class Intave14P200Check extends AntiCheatCheck {
   public final CompositeDoubleEntry compositeDoubleEntry;
   public int count = 0;
   public boolean enabled = false;
   public int count2 = 0;

   @Override
   public void updateState2() {
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   public Intave14P200Check(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.compositeDoubleEntry = new CompositeDoubleEntry(200, "sprint-toggles aligned with attacks", DirectOptionType.KILLAURA);
   }
}
