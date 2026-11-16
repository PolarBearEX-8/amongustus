package client.util;

import net.minecraft.world.storage.WorldInfo;

public class ListEntryAdapter extends WorldInfo {
   public final NetherFloatArrayEntry netherFloatArrayEntry;

   @Override
   public double getValue6() {
      return super.getValue6() / 8.0;
   }

   @Override
   public double getValue5() {
      return super.getValue5() / 8.0;
   }

   public ListEntryAdapter(NetherFloatArrayEntry netherFloatArrayEntry2) {
      this.netherFloatArrayEntry = netherFloatArrayEntry2;
   }
}
