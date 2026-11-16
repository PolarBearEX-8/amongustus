package augustus.anticheat.checks;

import client.Client;
import client.util.ArrayListData;
import client.util.Packet;
import client.util.TimeHelper;
import net.minecraft.entity.EntityLivingBase;

public class AimDistanceCheck extends AntiCheatCheck {
   public int count;
   public int count2 = 0;
   public final ArrayListData arrayListData = new ArrayListData(25);
   public final TimeHelper timer = new TimeHelper();
   public EntityLivingBase entityLivingBase;

   @Override
   public void updateState2() {
      Client.getClient().getClass();
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   public AimDistanceCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.count = 0;
   }
}
