package net.minecraft.stats;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IJsonSerializable;
import net.minecraft.util.TupleIntJsonSerializable;

public class StatFileWriter {
   public final Map statsData = Maps.newConcurrentMap();

   public boolean hasAchievementUnlocked(Achievement achievement) {
      return this.readStat(achievement) > 0;
   }

   public IJsonSerializable setProgress(StatBase statBase, IJsonSerializable jsonSerializable) {
      TupleIntJsonSerializable tupleIntJsonSerializable = (TupleIntJsonSerializable)this.statsData.get(statBase);
      if (tupleIntJsonSerializable == null) {
         tupleIntJsonSerializable = new TupleIntJsonSerializable();
         this.statsData.put(statBase, tupleIntJsonSerializable);
      }

      tupleIntJsonSerializable.setJsonSerializableValue(jsonSerializable);
      return jsonSerializable;
   }

   public void unlockAchievement(EntityPlayer player, StatBase statBase, int n) {
      TupleIntJsonSerializable tupleIntJsonSerializable = (TupleIntJsonSerializable)this.statsData.get(statBase);
      if (tupleIntJsonSerializable == null) {
         tupleIntJsonSerializable = new TupleIntJsonSerializable();
         this.statsData.put(statBase, tupleIntJsonSerializable);
      }

      tupleIntJsonSerializable.setIntegerValue(n);
   }

   public boolean canUnlockAchievement(Achievement achievement) {
      return achievement.parentAchievement == null || this.hasAchievementUnlocked(achievement.parentAchievement);
   }

   public int countRequirementsUntilAvailable(Achievement achievement) {
      if (this.hasAchievementUnlocked(achievement)) {
         return 0;
      } else {
         int n = 0;

         for (Achievement i = achievement.parentAchievement; i != null && !this.hasAchievementUnlocked(i); n++) {
            i = i.parentAchievement;
         }

         return n;
      }
   }

   public void increaseStat(EntityPlayer player, StatBase statBase, int n) {
      if (!statBase.isAchievement() || this.canUnlockAchievement((Achievement)statBase)) {
         this.unlockAchievement(player, statBase, this.readStat(statBase) + n);
      }
   }

   public IJsonSerializable getProgress(StatBase statBase) {
      TupleIntJsonSerializable tupleIntJsonSerializable = (TupleIntJsonSerializable)this.statsData.get(statBase);
      return tupleIntJsonSerializable != null ? tupleIntJsonSerializable.getJsonSerializableValue() : null;
   }

   public int readStat(StatBase statBase) {
      TupleIntJsonSerializable tupleIntJsonSerializable = (TupleIntJsonSerializable)this.statsData.get(statBase);
      return tupleIntJsonSerializable == null ? 0 : tupleIntJsonSerializable.getIntegerValue();
   }
}
