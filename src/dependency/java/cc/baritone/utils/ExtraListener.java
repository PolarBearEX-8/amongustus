package cc.baritone.utils;

import java.util.List;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.NetHandlerPlayClient;

public interface ExtraListener {
   ActiveListener resolveActiveListenerByMinecraft(Minecraft mc);

   AdvancedListener getAdvancedListener();

   boolean matchesActiveListener(ActiveListener activeListener);

   default ActiveListener resolveActiveListener3(Minecraft mc) {
      for (ActiveListener activeListener : (java.lang.Iterable<ActiveListener>) (this.getList())) {
         if (Objects.equals(mc, activeListener.getStaticListener().getMc())) {
            return activeListener;
         }
      }

      return null;
   }

   ActiveListener get();

   default ActiveListener resolveActiveListener(NetHandlerPlayClient netHandlerPlayClient) {
      for (ActiveListener activeListener : (java.lang.Iterable<ActiveListener>) (this.getList())) {
         EntityPlayerSP entityPlayerSP = activeListener.getStaticListener().getEntityPlayerSP();
         if (entityPlayerSP != null && entityPlayerSP.netHandlerPlayClient == netHandlerPlayClient) {
            return activeListener;
         }
      }

      return null;
   }

   default ActiveListener resolveActiveListenerByEntityPlayerSP(EntityPlayerSP player) {
      for (ActiveListener activeListener : (java.lang.Iterable<ActiveListener>) (this.getList())) {
         if (Objects.equals(player, activeListener.getStaticListener().getEntityPlayerSP())) {
            return activeListener;
         }
      }

      return null;
   }

   List getList();

   SharedListener getSharedListener();

   CommonListener getCommonListener();
}
