package net.augustus.modules.movement;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S27PacketExplosion;

public class ExplosionModifier extends Module implements PacketListener {
   private final DoubleValue xzMultiplierExplosion;
   private final DoubleValue yMultiplierExplosion;
   private final BooleanValue modifyVelocityAfterExplosion;
   private final DoubleValue xzMultiplierVelocity;
   private final DoubleValue yMultiplierVelocity;
   private final TimeHelper timeHelper;

   public ExplosionModifier() {
       super("ExplosionModifier", "ExplosionModifizierer", new Color(235, 91, 52), Categorys.MOVEMENT);
      this.xzMultiplierExplosion = new DoubleValue(this, "XZMultiplierExplosion", 1.0, 0.0, 10.0, 4);
      this.yMultiplierExplosion = new DoubleValue(this, "YMultiplierExplosion", 1.0, 0.0, 10.0, 4);
      this.modifyVelocityAfterExplosion = new BooleanValue(this, "ModifyVelocityAfterExplosion", true);
      this.xzMultiplierVelocity = new DoubleValue(this, "XZMultiplierVelocity", 1.0, 0.0, 10.0, 4);
      this.yMultiplierVelocity = new DoubleValue(this, "YMultiplierVelocity", 1.0, 0.0, 10.0, 4);
      this.timeHelper = new TimeHelper();
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(26);
   }

   public void onPacketReceive(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof S27PacketExplosion) {
         S27PacketExplosion packet2 = (S27PacketExplosion)packet;
         if (mc.thePlayer.getDistance(packet2.getX(), packet2.getY(), packet2.getZ()) < 5.0) {
            this.timeHelper.reset();
         }

         packet2.setMotionX((float)((double)packet2.getMotionX() * this.xzMultiplierExplosion.getValue()));
         packet2.setMotionY((float)((double)packet2.getMotionY() * this.yMultiplierExplosion.getValue()));
         packet2.setMotionZ((float)((double)packet2.getMotionZ() * this.xzMultiplierExplosion.getValue()));
      }

      if (packet instanceof S12PacketEntityVelocity) {
         if (!this.timeHelper.reached(200L) && this.modifyVelocityAfterExplosion.getValue()) {
            S12PacketEntityVelocity packet3 = (S12PacketEntityVelocity)packet;
            packet3.setMotionX((int)((double)packet3.getMotionX() * this.xzMultiplierVelocity.getValue()));
            packet3.setMotionY((int)((double)packet3.getMotionY() * this.yMultiplierVelocity.getValue()));
            packet3.setMotionZ((int)((double)packet3.getMotionZ() * this.xzMultiplierVelocity.getValue()));
         }
      }
   }

}
