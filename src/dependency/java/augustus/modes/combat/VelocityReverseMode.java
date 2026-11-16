package augustus.modes.combat;

import augustus.modules.combat.Velocity;
import client.event.EventMotionUpdate;
import client.event.EventPacket;
import client.event.MotionUpdateListener;
import client.event.PacketListener;
import client.setting.SettingGroup;
import client.util.InternalStringArrayEntry;
import client.util.Packet;
import client.util.PlayerDiggingActionType;
import java.util.function.BiFunction;
import net.minecraft.network.play.client.C06PacketPlayerPosLook;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class VelocityReverseMode extends InternalStringArrayEntry implements PacketListener, MotionUpdateListener {
   public int count3 = 0;
   public int count2 = 1;

   @Override
   public void updateState() {
   }

   public static BiFunction getBiFunction() {
      return ((item, group) -> new VelocityReverseMode((Velocity)item, (SettingGroup)group));
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
      if (this.count2 > 0 && this.isActive()) {
         this.count2++;
         if (this.count2 == 2) {
            minecraft.thePlayer
               .netHandlerPlayClient
               .handlePacket2(
                  new C06PacketPlayerPosLook(
                     minecraft.thePlayer.prevPosX,
                     minecraft.thePlayer.prevPosY,
                     minecraft.thePlayer.prevPosZ,
                     minecraft.thePlayer.prevRotationYaw,
                     minecraft.thePlayer.prevRotationPitch,
                     minecraft.thePlayer.prevPosY % 0.015625 == 0.0
                  )
               );
            minecraft.thePlayer
               .netHandlerPlayClient
               .handlePacket2(new C07PacketPlayerDigging(PlayerDiggingActionType.START_DESTROY_BLOCK, new BlockPos(minecraft.thePlayer), EnumFacing.UP));
         } else {
            this.count2 = 0;
         }
      }
   }

   @Override
   public void clear() {
   }

   @Override
   public void onPacketPostSend(EventPacket packet) {
      if (this.isActive()) {
         Packet packet2 = packet.getPacket();
         if (packet2 instanceof S12PacketEntityVelocity) {
            if (((S12PacketEntityVelocity)packet2).getEntityId() == minecraft.thePlayer.getEntityId() && this.count3 != minecraft.thePlayer.ticksExisted) {
               packet.cancel();
               this.count2 = 1;
            }
         } else if (packet2 instanceof S08PacketPlayerPosLook) {
            this.count3 = minecraft.thePlayer.ticksExisted;
         }
      }
   }

   @Override
   public void updateState2() {
      byte byteValue = 26;
      this.applyIndex(byteValue);
      byte byteValue2 = 18;
      this.applyIndex(byteValue2);
   }

   public VelocityReverseMode(Velocity velocity, SettingGroup settingGroup) {
      super(velocity, settingGroup, "Grim-1.17");
   }
}
