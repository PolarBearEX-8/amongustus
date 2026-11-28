package net.augustus.modules.modes.anticheat.autoclicker;

import net.augustus.modules.ModuleMode;
import net.augustus.utils.ChatUtil;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0APacketAnimation;

public class AutoClickerP5Check extends ModuleMode {
   private int attacksThisTick;
   private int violationLevel;
   private int swingsThisTick = 0;

   public void resetState() {
      this.violationLevel = 0;
   }

   public void onClientPacket(Packet packet) {
      if (packet instanceof C02PacketUseEntity && ((C02PacketUseEntity)packet).getAction() == C02PacketUseEntity.Action.ATTACK) {
         this.attacksThisTick++;
      }

      if (packet instanceof C0APacketAnimation) {
         this.swingsThisTick++;
      }

      if (packet instanceof C03PacketPlayer) {
         if (mc.playerController.gameIsSurvivalOrAdventure()
            && (this.attacksThisTick > 0 && this.swingsThisTick > this.attacksThisTick || this.attacksThisTick > this.swingsThisTick || this.attacksThisTick == 0 && this.swingsThisTick > 1 && !mc.playerController.isHittingBlock)) {
            this.violationLevel++;
            ChatUtil.sendChat("AutoClickerP5 lv: " + this.violationLevel);
         }

         this.attacksThisTick = 0;
         this.swingsThisTick = 0;
      }
   }

   public AutoClickerP5Check(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.attacksThisTick = 0;
      this.violationLevel = 0;
      this.visibleInGui = true;
   }

   public void onTick() {
   }

}
