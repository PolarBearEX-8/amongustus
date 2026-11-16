package augustus.anticheat.checks;

import client.Client;
import client.util.ArrayListData;
import client.util.Packet;
import net.minecraft.network.play.client.C0APacketAnimation;

public class HitDelayFixBCheck extends AntiCheatCheck {
   public final ArrayListData arrayListData4;
   public int count2;
   public final ArrayListData arrayListData2;
   public int count3 = 0;
   public final ArrayListData arrayListData;
   public int count = 0;
   public final ArrayListData arrayListData3;

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

   public HitDelayFixBCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.count2 = 0;
      this.arrayListData = new ArrayListData(5);
      this.arrayListData3 = new ArrayListData(20);
      this.arrayListData4 = new ArrayListData(35);
      this.arrayListData2 = new ArrayListData(20);
   }
}
