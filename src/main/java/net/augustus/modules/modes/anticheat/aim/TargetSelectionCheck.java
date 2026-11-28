package net.augustus.modules.modes.anticheat.aim;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.ToDoubleFunction;
import net.augustus.modules.ModuleMode;
import net.augustus.ClientManagers;
import net.augustus.utils.ChatUtil;
import net.augustus.settings.Setting;
import net.augustus.utils.math.DoubleSampleWindow;
import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.utils.combat.EntityTargetSelector;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.augustus.utils.combat.TargetSortMode;
import net.augustus.utils.interfaces.MC;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;

public class TargetSelectionCheck extends ModuleMode implements MC {
   private ArrayList matchRateWindows;
   private ArrayList sortModes;

   @Override
   public void onTick() {
   }

   @Override
   public void onClientPacket(Packet packet) {
      if (packet instanceof C02PacketUseEntity) {
         if (this.sortModes.isEmpty()) {
            for (TargetSortMode targetSortMode : TargetSortMode.values()) {
               this.matchRateWindows.add(new DoubleSampleWindow(100));
               this.sortModes.add(targetSortMode);
            }
         }

         int entityId = ((C02PacketUseEntity)packet).getEntityId();
         List sortedTargets = ClientManagers.moduleManager.killAura.getSortedTargets();
         if (sortedTargets.size() == 1 && ((Entity)sortedTargets.get(0)).getEntityId() == entityId) {
            return;
         }


         for (int i = 0; i < this.sortModes.size(); i++) {
            TargetSortMode targetSortMode2 = (TargetSortMode)this.sortModes.get(i);
            ToDoubleFunction toDoubleFunction = ClientManagers.moduleManager.killAura.targetSelection
               .createTargetComparatorKey(targetSortMode2, ClientManagers.moduleManager.killAura.getEffectiveRotation(), ClientManagers.moduleManager.killAura.currentRange);
            Optional optional = EntityTargetSelector.of(sortedTargets).sortBy(toDoubleFunction).findBest();
            if (optional.isPresent()) {
               if (((Entity)optional.get()).getEntityId() == entityId) {
                  ((DoubleSampleWindow)this.matchRateWindows.get(i)).add(1.0);
               } else {
                  ((DoubleSampleWindow)this.matchRateWindows.get(i)).add(0.0);
               }
            } else {
               ((DoubleSampleWindow)this.matchRateWindows.get(i)).add(0.0);
            }
         }

         StringBuilder sb = new StringBuilder();

         for (int j = 0; j < this.sortModes.size(); j++) {
            double d = ((DoubleSampleWindow)this.matchRateWindows.get(j)).mean();
            sb.append(((TargetSortMode)this.sortModes.get(j)).getDisplayName())
               .append(" - ")
               .append(MathStatisticsUtil.roundToDecimalPlaces(d * 100.0, 1))
               .append(" ");
         }

         ChatUtil.sendChat(sb.toString());
      }
   }

   public TargetSelectionCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.matchRateWindows = new ArrayList();
      this.sortModes = new ArrayList();
   }
}
