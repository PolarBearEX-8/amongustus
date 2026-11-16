package client.util;

import client.setting.WorldSettingsGameType;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.ISaveHandler;

public class BezierWriter extends WorldServer {
   public static final long timestamp2 = -0x1479bd7aL;
   public static final AdvancedLongEntry advancedLongEntry = new AdvancedLongEntry(
         timestamp2, WorldSettingsGameType.SURVIVAL, true, false, InfoManager.infoManager7
      )
      .getAdvancedLongEntryAsAdvancedLongEntr();

   public BezierWriter(VanillaRunnable vanillaRunnable, ISaveHandler iSaveHandler, RandomSeedManager randomSeedManager, int count, UNKNOWNManager uNKNOWNManager) {
      super(vanillaRunnable, iSaveHandler, randomSeedManager, count, uNKNOWNManager);
      this.randomSeedManager.applyAdvancedLongEntry(advancedLongEntry);
   }
}
