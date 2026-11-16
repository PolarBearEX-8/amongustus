package augustus.anticheat.checks;

import client.Client;
import client.setting.DirectOptionType;
import client.util.CompositeDoubleEntry;
import client.util.Packet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.BlockPos;

public class Intave14RotationBlockCheck extends AntiCheatCheck {
   public final List list2;
   public long timestamp;
   public final CompositeDoubleEntry compositeDoubleEntry;
   public long timestamp2 = 0L;
   public final List list;

   @Override
   public void updateState2() {
   }

   private boolean matchesBlockPos(BlockPos blockPos) {
      Iterator iterator = this.list.iterator();

      while (iterator.hasNext()) {
         if (Math.sqrt(((BlockPos)iterator.next()).computeValue((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ())) == 0.0) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   public Intave14RotationBlockCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.timestamp = 0L;
      this.list2 = new ArrayList();
      this.list = new ArrayList();
      this.compositeDoubleEntry = new CompositeDoubleEntry(1337, "high rotation activity while placing blocks", DirectOptionType.SCAFFOLD);
   }
}
