package net.augustus.modules.modes.anticheat.movement;

import net.augustus.modules.ModuleMode;
import net.augustus.events.EventPacket;
import net.augustus.utils.math.DoubleSampleWindow;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;

public class NoJumpDelayACheck extends ModuleMode {
   private int airTicks;
   private DoubleSampleWindow jumpDelaySamples;
   private int groundTicks;
   private int minJumpTicks;
   private int consecutiveZeroDelayJumps = 0;
   private boolean awaitingTeleportConfirm = false;
   private int ticksSinceJump = 0;

   public NoJumpDelayACheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.airTicks = 0;
      this.groundTicks = 0;
      this.minJumpTicks = 1;
      this.jumpDelaySamples = new DoubleSampleWindow(80);
   }

   @Override
   public void onTick() {
   }

   @Override
   public void onClientPacket(Packet packet) {
      if (packet instanceof C03PacketPlayer && !this.awaitingTeleportConfirm) {
      }

      if (packet instanceof C03PacketPlayer.C06PacketPlayerPosLook && !((C03PacketPlayer.C06PacketPlayerPosLook)packet).isOnGround()) {
         this.awaitingTeleportConfirm = false;
      }
   }

   @Override
   public void onServerPacket(EventPacket event) {
      if (null instanceof S08PacketPlayerPosLook) {
         this.awaitingTeleportConfirm = true;
      }
   }
}
