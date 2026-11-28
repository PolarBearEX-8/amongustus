package net.augustus.modules.modes.anticheat.intave14.movement;

import net.augustus.modules.ModuleMode;
import net.augustus.events.EventPacket;
import net.augustus.modules.modes.anticheat.AntiCheatCheckTracker;
import net.augustus.modules.modes.anticheat.AntiCheatCategory;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;

public class Intave14TimerCheck extends ModuleMode {
   private double timerBalance;
   private final int maxBalanceThreshold;
   private final AntiCheatCheckTracker antiCheatCheckTracker;
   private long lastRespawnTime;
   private long packetBalanceMs;
   private long lastPacketTime;
   private long lastTeleportTime;
   private long lastFlagTime;
   private final boolean debugOutput;

   @Override
   public void onServerPacket(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof S07PacketRespawn) {
         this.lastRespawnTime = System.currentTimeMillis();
         this.timerBalance -= 20.0;
      }
      if (packet instanceof S08PacketPlayerPosLook) {
         this.packetBalanceMs = 0L;
         this.timerBalance -= 2.0;
         this.lastTeleportTime = System.currentTimeMillis();
      }
   }

   @Override
   public void onPacketEvent(EventPacket event) {
   }

   @Override
   public void onClientPacket(Packet packet) {
   }

   @Override
   public void onTick() {
   }

   public Intave14TimerCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.packetBalanceMs = 0L;
      this.lastTeleportTime = 0L;
      this.lastRespawnTime = 0L;
      this.lastPacketTime = 0L;
      this.lastFlagTime = 0L;
      this.timerBalance = 0.0;
      this.debugOutput = false;
      this.maxBalanceThreshold = 1000;
      this.antiCheatCheckTracker = new AntiCheatCheckTracker(13, "", AntiCheatCategory.KILLAURA);
   }
}
