package client.event;

import net.minecraft.profiler.PlayerUsageSnooper;

public interface PostMotionNotifier {
   void applyPlayerUsageSnooperByPlayerUsageSnoop(PlayerUsageSnooper playerUsageSnooper);

   void applyPlayerUsageSnooper(PlayerUsageSnooper playerUsageSnooper);

   boolean isActiveAsFlag();
}
