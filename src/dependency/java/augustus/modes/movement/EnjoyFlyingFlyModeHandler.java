package augustus.modes.movement;

import augustus.modules.movement.Fly;
import client.event.EventClick;
import client.event.EventMotionUpdate;
import client.event.EventMoveEntity;
import client.event.EventPacket;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;
import client.util.Packet;
import client.util.RayTraceUtil;
import net.minecraft.entity.EntityBoat;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S1BPacketEntityAttach;

public class EnjoyFlyingFlyModeHandler extends FlyModeHandler {
   public boolean enabled;
   public int count2 = 0;

   public EnjoyFlyingFlyModeHandler(String text, Fly fly) {
      super(text, fly);
   }

   @Override
   public void updateState2() {
      this.enabled = false;
      this.count2 = 0;
      if (super.fly.settingHelpMessage.isEnabled()) {
         RayTraceUtil.applyString("1. Toggle Fly");
         RayTraceUtil.applyString("2. Look at boat (in hit reach)");
         RayTraceUtil.applyString("3. Enjoy flying");
      }
   }

   @Override
   public void updateState3() {
   }

   @Override
   public void onClick(EventClick event) {
      if (MinecraftAccessor.minecraft.objectMouseOver != null && MinecraftAccessor.minecraft.objectMouseOver.entityHit instanceof EntityBoat && this.count2 > 2
         )
       {
         event.updateInt2(1);
         event.updateInt(2);
      }
   }

   @Override
   public void processChar() {
      double offset = Math.toRadians((double)MoveUtil.computeFactor(false));
      double offset2 = -(Math.sin(offset) * 4.0);
      double offset3 = Math.cos(offset) * 4.0;
      if (MinecraftAccessor.minecraft.theWorld != null && MinecraftAccessor.minecraft.thePlayer != null) {
         MinecraftAccessor.minecraft
            .thePlayer
            .processDouble2(
               MinecraftAccessor.minecraft.thePlayer.posX + offset2,
               MinecraftAccessor.minecraft.thePlayer.posY,
               MinecraftAccessor.minecraft.thePlayer.posZ + offset3
            );
      }

      this.count2++;
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void onPacket(EventPacket packet) {
      Packet packet2 = packet.getPacket();
      if (packet2 instanceof S1BPacketEntityAttach && ((S1BPacketEntityAttach)packet2).getCount2() == MinecraftAccessor.minecraft.thePlayer.getEntityId()) {
         packet.cancel();
         this.enabled = true;
      }

      if (packet2 instanceof S08PacketPlayerPosLook && this.enabled && this.count2 < 1) {
         this.count2 = 2;
      }
   }
}
