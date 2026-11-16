package client.util;

import com.google.common.collect.Maps;
import java.util.HashMap;
import net.minecraft.profiler.PlayerUsageSnooper;

public class SnooperCountTimerTask extends java.util.TimerTask {
   public final PlayerUsageSnooper playerUsageSnooper;

   public SnooperCountTimerTask(PlayerUsageSnooper playerUsageSnooper2) {
      this.playerUsageSnooper = playerUsageSnooper2;
   }

   @Override
   public void run() {
      if (PlayerUsageSnooper.resolvePostMotionNotifier(this.playerUsageSnooper).isActiveAsFlag()) {
         HashMap map;
         synchronized (PlayerUsageSnooper.resolveTarget(this.playerUsageSnooper)) {
            map = Maps.newHashMap(PlayerUsageSnooper.resolveMap(this.playerUsageSnooper));
            if (PlayerUsageSnooper.computeCount(this.playerUsageSnooper) == 0) {
               map.putAll(PlayerUsageSnooper.resolveMapByPlayerUsageSnoop(this.playerUsageSnooper));
            }

            map.put("snooper_count", PlayerUsageSnooper.computeCountByPlayerUsageSnoop(this.playerUsageSnooper));
            map.put("snooper_token", PlayerUsageSnooper.resolveText(this.playerUsageSnooper));
         }

         GETManager.resolveText(PlayerUsageSnooper.resolveURL(this.playerUsageSnooper), map, true);
      }
   }
}
