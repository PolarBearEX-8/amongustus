package augustus.anticheat.checks;

import client.Client;
import client.util.ArrayListData;
import client.util.Packet;
import net.minecraft.network.play.client.C0APacketAnimation;

public class HitDelayFixACheck extends AntiCheatCheck {
   public final ArrayListData arrayListData;
   public int count = 0;
   public double value;
   public int count2;
   public int count3 = 0;

   @Override
   public void updateState2() {
      Client.getClient().getClass();
   }

   @Override
   public void handlePacket(Packet packet) {
      if (packet instanceof C0APacketAnimation) {
         this.count++;
      }
   }

   public HitDelayFixACheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.count2 = 0;
      this.value = 0.0;
      this.arrayListData = new ArrayListData(15);
   }
}
