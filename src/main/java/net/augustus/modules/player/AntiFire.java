package net.augustus.modules.player;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.network.play.client.C03PacketPlayer;

public class AntiFire extends Module implements UpdateListener {
   public final BooleanValue onlyOnGround;
   public final DoubleValue maxPacketsPerTick;

   public AntiFire() {
       super("AntiFire", "GegenFeuer", new Color(252, 186, 3), Categorys.PLAYER);
      this.onlyOnGround = new BooleanValue(this, "OnlyOnGround", false);
      this.maxPacketsPerTick = new DoubleValue(this, "MaxPacketsPerTick", 20.0, 0.0, 100.0, 0);
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(43);
   }

   public void onPreUpdate() {
      if (mc.thePlayer.isBurning() && (mc.thePlayer.onGround || !this.onlyOnGround.getValue())) {
         for (int i = 0; (double)i < this.maxPacketsPerTick.getValue(); i++) {
            mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C03PacketPlayer(mc.thePlayer.onGround));
         }
      }
   }

}
