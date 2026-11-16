package augustus.anticheat.checks;

import client.Client;
import client.setting.DirectOptionType;
import client.util.CompositeDoubleEntry;
import client.util.Packet;

public class Intave14P141144Check extends AntiCheatCheck {
   public boolean enabled2;
   public boolean enabled;
   public int count4;
   public final CompositeDoubleEntry compositeDoubleEntry2;
   public final CompositeDoubleEntry compositeDoubleEntry3;
   public int count5;
   public int count3;
   public final CompositeDoubleEntry compositeDoubleEntry = new CompositeDoubleEntry(141, "", DirectOptionType.KILLAURA);
   public int count;
   public int count2;
   public final CompositeDoubleEntry compositeDoubleEntry4;

   @Override
   public void updateState2() {
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   public Intave14P141144Check(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.compositeDoubleEntry3 = new CompositeDoubleEntry(142, "", DirectOptionType.KILLAURA);
      this.compositeDoubleEntry2 = new CompositeDoubleEntry(143, "", DirectOptionType.KILLAURA);
      this.compositeDoubleEntry4 = new CompositeDoubleEntry(144, "", DirectOptionType.KILLAURA);
   }
}
