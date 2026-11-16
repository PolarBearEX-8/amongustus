package augustus.modes.movement;

import augustus.modules.combat.Velocity;
import client.event.EventPacket;
import client.event.PacketListener;
import client.event.UpdateStateListener;
import client.network.LatencyPacket;
import client.setting.BooleanSetting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.InternalStringArrayEntry;
import client.util.Packet;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiFunction;
import net.minecraft.block.BlockAir;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;
import net.minecraft.network.play.server.S40PacketDisconnect;
import net.minecraft.util.MathHelper;

public class JesusIntavePolarflagMode extends InternalStringArrayEntry implements PacketListener, UpdateStateListener {
   public final BooleanSetting settingPolar;
   public final Queue queue;
   public boolean enabled2;
   public double value;
   public int count2;

   @Override
   public void updateState() {
      if (minecraft.thePlayer != null) {
         this.queue.forEach((item -> this.applyPacket((Packet)item)));
      }

      this.queue.clear();
   }

   @Override
   public void onPacketSend(EventPacket packet) {
      if (packet.getPacket() instanceof C03PacketPlayer && ((C03PacketPlayer)packet.getPacket()).isOnGround2()) {
         minecraft.thePlayer.processDouble2(minecraft.thePlayer.posX, minecraft.thePlayer.posY, minecraft.thePlayer.posZ);
      }
   }

   private void applyPacket(Packet packet) {
      try {
         if (!(packet instanceof LatencyPacket)) {
            packet.processAction(minecraft.getNetHandlerPlayClient().getNetworkManager().getINetHandler());
         }
      } catch (Exception exception) {
      }
   }

   private void updateState3() {
      this.queue.forEach((item -> this.applyPacket((Packet)item)));
      this.queue.clear();
   }

   public static BiFunction getBiFunction() {
      return ((item, group) -> new JesusIntavePolarflagMode((Velocity)item, (SettingGroup)group));
   }

   @Override
   public void clear() {
      this.enabled2 = false;
      this.queue.clear();
   }

   @Override
   public void onPacketPostSend(EventPacket packet) {
      if (minecraft.thePlayer != null && minecraft.thePlayer.ticksExisted >= 100) {
         if (packet.getPacket() instanceof S32PacketConfirmTransaction && !this.enabled2) {
            this.count2++;
            if (this.settingPolar.isEnabled() ? this.count2 > 2 : this.count2 > 0) {
               this.count2 = 0;
               this.updateState3();
            }
         }

         if (packet.getPacket() instanceof S19PacketEntityStatus) {
            S19PacketEntityStatus packet2 = (S19PacketEntityStatus)packet.getPacket();
            if (packet2.getEntity(minecraft.theWorld) == minecraft.thePlayer && packet2.processValue() == 2 && minecraft.thePlayer.onGround) {
               if (!this.enabled2) {
                  this.value = minecraft.thePlayer.posY;
               }

               this.enabled2 = true;
            }
         }

         if (!(packet.getPacket() instanceof S02PacketChat)
            && !(packet.getPacket() instanceof S19PacketEntityStatus)
            && !(packet.getPacket() instanceof S40PacketDisconnect)
            && !(packet.getPacket() instanceof S0BPacketAnimation)) {
            this.queue.add(packet.getPacket());
            packet.setCancelled(true);
         }

         if (packet.getPacket() instanceof S08PacketPlayerPosLook) {
            S08PacketPlayerPosLook packet3 = (S08PacketPlayerPosLook)packet.getPacket();
            this.enabled2 = false;
         }
      } else {
         this.queue.clear();
      }
   }

   @Override
   public void updateState2() {
      byte byteValue = 26;
      this.applyIndex(byteValue);
      byte byteValue2 = 43;
      this.applyIndex(byteValue2);
   }

   @Override
   public void onPreUpdateState() {
      if (this.enabled2) {
         minecraft.thePlayer.posY -= 1.0E-7;
      }

      if (this.value - minecraft.thePlayer.posY > 0.03 || !(minecraft.theWorld.getBlockState(minecraft.thePlayer.getBlockPos()).getBlock() instanceof BlockAir)) {
         this.enabled2 = false;
      }
   }

   @Override
   public void onPostUpdateState() {
      if (!this.enabled2) {
         int count = MathHelper.ceiling_double_int(minecraft.thePlayer.getBoundingBox2().minY);
         int count3 = MathHelper.truncateDoubleToInt(minecraft.thePlayer.getBoundingBox2().minY);
         if (Math.abs((double)count - minecraft.thePlayer.getBoundingBox2().minY) < 1.0E-4) {
            minecraft.thePlayer.processDouble2(minecraft.thePlayer.posX, (double)count, minecraft.thePlayer.posZ);
         } else if (Math.abs((double)count3 - minecraft.thePlayer.getBoundingBox2().minY) < 1.0E-4) {
            minecraft.thePlayer.processDouble2(minecraft.thePlayer.posX, (double)count3, minecraft.thePlayer.posZ);
         }
      }
   }

   public JesusIntavePolarflagMode(Velocity velocity, SettingGroup settingGroup) {
        super(velocity, settingGroup, "Intave/PolarFlag");
      
      BooleanSetting setting = new BooleanSetting(this.module, "Polar", false);
      BooleanSupplier booleanSupplier = this::booleanValue;
      this.settingPolar = (BooleanSetting)setting.getSetting(booleanSupplier);
      this.queue = new ConcurrentLinkedQueue();
   }
}
