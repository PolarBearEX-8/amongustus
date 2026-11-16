package augustus.anticheat.checks;

import client.Client;
import client.util.ArrayListData;
import client.util.Packet;
import net.minecraft.network.play.client.C0APacketAnimation;

public class HitDelayFixCCheck extends AntiCheatCheck {
   public int count;
   public final ArrayListData arrayListData;
   public final ArrayListData arrayListData4;
   public final ArrayListData arrayListData2;
   public final ArrayListData arrayListData3;
   public int count2;
   public int count3 = 0;

   @Override
   public void updateState2() {
      Client.getClient().getClass();
   }

   @Override
   public void handlePacket(Packet packet) {
      if (packet instanceof C0APacketAnimation) {
         this.count3++;
      }
   }

   public HitDelayFixCCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.count2 = 0;
      this.count = 0;
      this.arrayListData4 = new ArrayListData(2);
      this.arrayListData3 = new ArrayListData(5);
      this.arrayListData2 = new ArrayListData(30);
      this.arrayListData = new ArrayListData(30);
   }
}
