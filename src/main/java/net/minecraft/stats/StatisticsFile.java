package net.minecraft.stats;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S37PacketStatistics;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IJsonSerializable;
import net.minecraft.util.TupleIntJsonSerializable;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StatisticsFile extends StatFileWriter {
   private static final Logger LOGGER = LogManager.getLogger();
   private final MinecraftServer mcServer;
   private final File statsFile;
   private final Set dirtyStats = Sets.newHashSet();
   private int lastStatRequest = -300;
   private boolean hasUnsentAchievement = false;

   public boolean hasUnsentAchievement() {
      return this.hasUnsentAchievement;
   }

   public Set getDirtyStats() {
      HashSet set = Sets.newHashSet(this.dirtyStats);
      this.dirtyStats.clear();
      this.hasUnsentAchievement = false;
      return set;
   }

   public void saveStatFile() {
      try {
         FileUtils.writeStringToFile(this.statsFile, dumpJson(this.statsData));
      } catch (IOException e) {
         LOGGER.error("Couldn't save stats", e);
      }
   }

   public void sendStats(EntityPlayerMP entityPlayerMP) {
      int tickCounter = this.mcServer.getTickCounter();
      HashMap map = Maps.newHashMap();
      if (this.hasUnsentAchievement || tickCounter - this.lastStatRequest > 300) {
         this.lastStatRequest = tickCounter;

         for (StatBase statBase : (Iterable<StatBase>)(this.getDirtyStats())) {
            map.put(statBase, this.readStat(statBase));
         }
      }

      entityPlayerMP.playerNetServerHandler.sendPacket(new S37PacketStatistics(map));
   }

   public static String dumpJson(Map map) {
      JsonObject json = new JsonObject();

      for (Entry entry : (Iterable<Entry>)(map.entrySet())) {
         if (((TupleIntJsonSerializable)entry.getValue()).getJsonSerializableValue() != null) {
            JsonObject json2 = new JsonObject();
            json2.addProperty("value", ((TupleIntJsonSerializable)entry.getValue()).getIntegerValue());

            try {
               json2.add("progress", ((TupleIntJsonSerializable)entry.getValue()).getJsonSerializableValue().getSerializableElement());
            } catch (Throwable e) {
               LOGGER.warn("Couldn't save statistic " + ((StatBase)entry.getKey()).getStatName() + ": error serializing progress", e);
            }

            json.add(((StatBase)entry.getKey()).statId, json2);
         } else {
            json.addProperty(((StatBase)entry.getKey()).statId, ((TupleIntJsonSerializable)entry.getValue()).getIntegerValue());
         }
      }

      return json.toString();
   }

   @Override
   public void unlockAchievement(EntityPlayer player, StatBase statBase, int n) {
      int n2 = statBase.isAchievement() ? this.readStat(statBase) : 0;
      super.unlockAchievement(player, statBase, n);
      this.dirtyStats.add(statBase);
      if (statBase.isAchievement() && n2 == 0 && n > 0) {
         this.hasUnsentAchievement = true;
         if (this.mcServer.isAnnouncingPlayerAchievements()) {
            this.mcServer
               .getConfigurationManager()
               .sendChatMsg(new ChatComponentTranslation("chat.type.achievement", new Object[]{player.getDisplayName(), statBase.createChatComponent()}));
         }
      }

      if (statBase.isAchievement() && n2 > 0 && n == 0) {
         this.hasUnsentAchievement = true;
         if (this.mcServer.isAnnouncingPlayerAchievements()) {
            this.mcServer
               .getConfigurationManager()
               .sendChatMsg(new ChatComponentTranslation("chat.type.achievement.taken", new Object[]{player.getDisplayName(), statBase.createChatComponent()}));
         }
      }
   }

   public void readStatFile() {
      if (this.statsFile.isFile()) {
         try {
            this.statsData.clear();
            this.statsData.putAll(this.parseJson(FileUtils.readFileToString(this.statsFile)));
         } catch (IOException e) {
            LOGGER.error("Couldn't read statistics file " + this.statsFile, e);
         } catch (JsonParseException e2) {
            LOGGER.error("Couldn't parse statistics file " + this.statsFile, e2);
         }
      }
   }

   public void markAllDirty() {
      for (StatBase statBase : (Iterable<StatBase>)(this.statsData.keySet())) {
         this.dirtyStats.add(statBase);
      }
   }

   public Map parseJson(String str) {
      JsonElement elem = new JsonParser().parse(str);
      if (!elem.isJsonObject()) {
         return Maps.newHashMap();
      } else {
         JsonObject asJsonObject = elem.getAsJsonObject();
         HashMap map = Maps.newHashMap();

         for (Entry entry : asJsonObject.entrySet()) {
            StatBase oneShotStat = StatList.getOneShotStat((String)entry.getKey());
            if (oneShotStat != null) {
               TupleIntJsonSerializable tupleIntJsonSerializable = new TupleIntJsonSerializable();
               if (((JsonElement)entry.getValue()).isJsonPrimitive() && ((JsonElement)entry.getValue()).getAsJsonPrimitive().isNumber()) {
                  tupleIntJsonSerializable.setIntegerValue(((JsonElement)entry.getValue()).getAsInt());
               } else if (((JsonElement)entry.getValue()).isJsonObject()) {
                  JsonObject asJsonObject2 = ((JsonElement)entry.getValue()).getAsJsonObject();
                  if (asJsonObject2.has("value") && asJsonObject2.get("value").isJsonPrimitive() && asJsonObject2.get("value").getAsJsonPrimitive().isNumber()) {
                     tupleIntJsonSerializable.setIntegerValue(asJsonObject2.getAsJsonPrimitive("value").getAsInt());
                  }

                  if (asJsonObject2.has("progress") && oneShotStat.getSerializableClazz() != null) {
                     try {
                        IJsonSerializable jsonSerializable = (IJsonSerializable)oneShotStat.getSerializableClazz().getConstructor().newInstance();
                        jsonSerializable.fromJson(asJsonObject2.get("progress"));
                        tupleIntJsonSerializable.setJsonSerializableValue(jsonSerializable);
                     } catch (Throwable e) {
                        LOGGER.warn("Invalid statistic progress in " + this.statsFile, e);
                     }
                  }
               }

               map.put(oneShotStat, tupleIntJsonSerializable);
            } else {
               LOGGER.warn("Invalid statistic in " + this.statsFile + ": Don't know what " + (String)entry.getKey() + " is");
            }
         }

         return map;
      }
   }

   public void sendAchievements(EntityPlayerMP entityPlayerMP) {
      HashMap map = Maps.newHashMap();

      for (Achievement achievement : (Iterable<Achievement>)(AchievementList.achievementList)) {
         if (this.hasAchievementUnlocked(achievement)) {
            map.put(achievement, this.readStat(achievement));
            this.dirtyStats.remove(achievement);
         }
      }

      entityPlayerMP.playerNetServerHandler.sendPacket(new S37PacketStatistics(map));
   }

   public StatisticsFile(MinecraftServer minecraftServer, File file) {
      this.mcServer = minecraftServer;
      this.statsFile = file;
   }
}
