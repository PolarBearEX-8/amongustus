package net.augustus.modules.modes.anticheat.autoclicker;

import java.util.ArrayList;
import java.util.Arrays;
import net.augustus.modules.ModuleMode;
import net.augustus.utils.ChatUtil;
import net.augustus.settings.Setting;
import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class AutoClickerECheck extends ModuleMode {
   private int ticksSinceDigStart;
   private final ArrayList digDurationSamples;

   public AutoClickerECheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.ticksSinceDigStart = 0;
      this.digDurationSamples = new ArrayList();
   }

   @Override
   public void onTick() {
   }

   @Override
   public void onClientPacket(Packet packet) {
      if (packet instanceof C03PacketPlayer) {
         this.ticksSinceDigStart++;
      }

      if (packet instanceof C07PacketPlayerDigging) {
         C07PacketPlayerDigging packet2 = (C07PacketPlayerDigging)packet;
         if (packet2.getStatus() == C07PacketPlayerDigging.Action.START_DESTROY_BLOCK) {
            this.ticksSinceDigStart = 0;
            return;
         }

         if (packet2.getStatus() == C07PacketPlayerDigging.Action.ABORT_DESTROY_BLOCK) {
            BlockPos position = packet2.getPosition();
            EnumFacing facing = packet2.getFacing();
            if (mc.objectMouseOver != null && position.equals(mc.objectMouseOver.getBlockPos()) && !facing.equals(mc.objectMouseOver.sideHit)) {
               this.digDurationSamples.add(this.ticksSinceDigStart);
               if (this.digDurationSamples.size() > 40) {
                  double d = MathStatisticsUtil.stdDevInt(this.digDurationSamples);
                  System.out.println(d);
                  if (d < 0.325) {
                     ChatUtil.sendChat("AutoClickerE: " + String.format("D: %s", d) + "  " + (1.325 - d));
                     ChatUtil.sendChat(Arrays.toString(this.digDurationSamples.toArray()));
                  }

                  this.digDurationSamples.clear();
               }
            }

            this.ticksSinceDigStart = 0;
         }
      }
   }

}
