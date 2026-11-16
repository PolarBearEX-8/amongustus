package augustus.modes.movement;

import augustus.modules.movement.Fly;
import client.ClientGlobals;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.event.EventPacket;
import client.event.EventSendPacket;
import client.module.Module;
import client.util.MinecraftAccessor;
import client.util.MouseUtils;
import client.util.Packet;
import client.util.RayTraceUtil;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S1BPacketEntityAttach;

public class FlyIntaveBoatMode extends FlyModeHandler {
   public boolean enabled = false;
   public boolean enabled2 = false;
   public boolean enabled3 = false;
   public int count3 = 0;
   public int count2 = 0;
   public double value2;
   public double value;
   public double value3;

   @Override
   public void onPacket(EventPacket packet) {
      Packet packet2 = packet.getPacket();
      if (packet2 instanceof S1BPacketEntityAttach && ((S1BPacketEntityAttach)packet2).getCount2() == MinecraftAccessor.minecraft.thePlayer.getEntityId()) {
         this.enabled2 = true;
         packet.cancel();
         this.enabled3 = true;
      }

      if (packet2 instanceof S08PacketPlayerPosLook) {
         S08PacketPlayerPosLook packet3 = (S08PacketPlayerPosLook)packet2;
         if (this.count3 > 3) {
            this.enabled = true;
         }

         if (this.value2 == packet3.getCoordinate() && this.value != packet3.getCoordinate1() && this.value3 == packet3.getCoordinate4() && this.enabled2) {
            this.count3++;
         } else {
            this.count3 = 0;
         }

         this.value2 = packet3.getCoordinate();
         this.value = packet3.getCoordinate1();
         this.value3 = packet3.getCoordinate4();
      }
   }

   @Override
   public void onSendPacket(EventSendPacket packet) {
      if (this.enabled) {
         packet.setCancelled(true);
      }
   }

   @Override
   public void processChar() {
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
      if (this.enabled2 && !this.enabled) {
         MinecraftAccessor.minecraft.thePlayer.motionY = this.enabled3 ? 0.2 : 0.0;
         MinecraftAccessor.minecraft.thePlayer.motionX = 0.0;
         MinecraftAccessor.minecraft.thePlayer.motionZ = 0.0;
         this.count2 = 0;
      }

      this.enabled3 = false;
      if (MouseUtils.checkCondition(0)) {
         this.enabled = true;
      }

      if (this.enabled) {
         this.count2++;
      }
   }

   @Override
   public void updateState2() {
      this.enabled = false;
      this.enabled2 = false;
      this.enabled3 = false;
      this.value2 = 0.0;
      this.value = 0.0;
      this.value3 = 0.0;
      this.count3 = 0;
      this.count2 = 0;
      if (super.fly.settingHelpMessage.isEnabled()) {
         RayTraceUtil.applyString("1. Toggle Fly");
         RayTraceUtil.applyString("2. Right click boat");
         RayTraceUtil.applyString("3. Enjoy flying (if you fail, press Sneak to tp back)");
      }
   }

   @Override
   public void onMove(EventMove event) {
      if (this.enabled && this.count2 > 20) {
         ((Module)ClientGlobals.moduleManager.fly).setActive(false);
      }
   }

   @Override
   public void updateState3() {
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   public FlyIntaveBoatMode(String text, Fly fly) {
      super(text, fly);
   }
}
