package client.util;

import client.event.PreRenderInterceptor;
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
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.item.StaticItemEntry;
import net.minecraft.network.play.server.S37PacketStatistics;
import net.minecraft.stats.StatFileWriter;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StatFileWriterHandler extends StatFileWriter {
   public static final Logger logger = LogManager.getLogger();
   public final VanillaRunnable vanillaRunnable;
   public final File file;
   public final Set set = Sets.newHashSet();
   public int count = -300;
   public boolean enabled = false;

   public Map resolveMap(String text) {
      JsonElement jsonElement = new JsonParser().parse(text);
      if (!jsonElement.isJsonObject()) {
         return Maps.newHashMap();
      } else {
         JsonObject asJsonObject = jsonElement.getAsJsonObject();
         HashMap map = Maps.newHashMap();

         for (Entry entry : asJsonObject.entrySet()) {
            DuplicateStatIdManager duplicateStatIdManager = StatCraftItemManager.get((String)entry.getKey());
            if (duplicateStatIdManager != null) {
               StaticItemEntry staticItemEntry = new StaticItemEntry();
               if (((JsonElement)entry.getValue()).isJsonPrimitive() && ((JsonElement)entry.getValue()).getAsJsonPrimitive().isNumber()) {
                  staticItemEntry.setCount(((JsonElement)entry.getValue()).getAsInt());
               } else if (((JsonElement)entry.getValue()).isJsonObject()) {
                  JsonObject asJsonObject2 = ((JsonElement)entry.getValue()).getAsJsonObject();
                  if (asJsonObject2.has("value") && asJsonObject2.get("value").isJsonPrimitive() && asJsonObject2.get("value").getAsJsonPrimitive().isNumber()) {
                     staticItemEntry.setCount(asJsonObject2.getAsJsonPrimitive("value").getAsInt());
                  }

                  if (asJsonObject2.has("progress") && duplicateStatIdManager.getMemberRef() != null) {
                     try {
                        Constructor constructor = duplicateStatIdManager.getMemberRef().getConstructor();
                        PreRenderInterceptor preRenderInterceptor = (PreRenderInterceptor)constructor.newInstance();
                        preRenderInterceptor.applyJsonElement(asJsonObject2.get("progress"));
                        staticItemEntry.setPreRenderInterceptor(preRenderInterceptor);
                     } catch (Throwable throwable) {
                        logger.warn("Invalid statistic progress in " + this.file, throwable);
                     }
                  }
               }

               map.put(duplicateStatIdManager, staticItemEntry);
            } else {
               logger.warn("Invalid statistic in " + this.file + ": Don't know what " + (String)entry.getKey() + " is");
            }
         }

         return map;
      }
   }

   public StatFileWriterHandler(VanillaRunnable vanillaRunnable2, File file2) {
      this.vanillaRunnable = vanillaRunnable2;
      this.file = file2;
   }

   public static String resolveText(Map map) {
      JsonObject jsonObject = new JsonObject();

      for (Entry entry : (Iterable<Entry>) (Iterable) (map.entrySet())) {
         if (((StaticItemEntry)entry.getValue()).getPreRenderInterceptor() != null) {
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty("value", ((StaticItemEntry)entry.getValue()).getCount());

            try {
               jsonObject2.add("progress", ((StaticItemEntry)entry.getValue()).getPreRenderInterceptor().getJsonElement());
            } catch (Throwable throwable) {
               logger.warn("Couldn't save statistic " + ((DuplicateStatIdManager)entry.getKey()).getIChatComponent() + ": error serializing progress", throwable);
            }

            jsonObject.add(((DuplicateStatIdManager)entry.getKey()).labelText, jsonObject2);
         } else {
            jsonObject.addProperty(((DuplicateStatIdManager)entry.getKey()).labelText, ((StaticItemEntry)entry.getValue()).getCount());
         }
      }

      return jsonObject.toString();
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public Set getSet() {
      HashSet set2 = Sets.newHashSet(this.set);
      this.set.clear();
      this.enabled = false;
      return set2;
   }

   public void applyEntityPlayerAdapter(EntityPlayerAdapter entityPlayerAdapter) {
      HashMap map = Maps.newHashMap();

      for (AuxiliaryDuplicateStatIdManagerAdapter auxiliaryDuplicateStatIdManagerAdapter : (Iterable<AuxiliaryDuplicateStatIdManagerAdapter>) (Iterable) (AchievementTheEnd2Manager.list)) {
         if (this.matchesAuxiliaryDuplicateStatIdMaByAuxiliaryDuplica(auxiliaryDuplicateStatIdManagerAdapter)) {
            map.put(auxiliaryDuplicateStatIdManagerAdapter, this.computeCount((DuplicateStatIdManager)auxiliaryDuplicateStatIdManagerAdapter));
            this.set.remove(auxiliaryDuplicateStatIdManagerAdapter);
         }
      }

      entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S37PacketStatistics(map));
   }

   public void updateState() {
      for (DuplicateStatIdManager duplicateStatIdManager : (Iterable<DuplicateStatIdManager>) (Iterable) (this.map.keySet())) {
         this.set.add(duplicateStatIdManager);
      }
   }

   @Override
   public void applyEntityPlayer(EntityPlayer player, DuplicateStatIdManager duplicateStatIdManager, int count2) {
      int count3 = duplicateStatIdManager.isActive() ? this.computeCount(duplicateStatIdManager) : 0;
      super.applyEntityPlayer(player, duplicateStatIdManager, count2);
      this.set.add(duplicateStatIdManager);
      if (duplicateStatIdManager.isActive() && count3 == 0 && count2 > 0) {
         this.enabled = true;
         if (this.vanillaRunnable.isActive13()) {
            this.vanillaRunnable
               .getMovingManager()
               .applyIChatComponent(
                  new BaseManagerStyleIChatComponentAdapter(
                     "chat.type.achievement", new Object[]{player.getChatComponent(), duplicateStatIdManager.getIChatComponentAsIChatComponent()}
                  )
               );
         }
      }

      if (duplicateStatIdManager.isActive() && count3 > 0 && count2 == 0) {
         this.enabled = true;
         if (this.vanillaRunnable.isActive13()) {
            this.vanillaRunnable
               .getMovingManager()
               .applyIChatComponent(
                  new BaseManagerStyleIChatComponentAdapter(
                     "chat.type.achievement.taken", new Object[]{player.getChatComponent(), duplicateStatIdManager.getIChatComponentAsIChatComponent()}
                  )
               );
         }
      }
   }

   public void applyEntityPlayerAdapterByEntityPlayerAdap(EntityPlayerAdapter entityPlayerAdapter) {
      int countAsIndex = this.vanillaRunnable.getCountAsIndex();
      HashMap map = Maps.newHashMap();
      if (this.enabled || countAsIndex - this.count > 300) {
         this.count = countAsIndex;

         for (DuplicateStatIdManager duplicateStatIdManager : (Iterable<DuplicateStatIdManager>) (Iterable) (this.getSet())) {
            map.put(duplicateStatIdManager, this.computeCount(duplicateStatIdManager));
         }
      }

      entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S37PacketStatistics(map));
   }

   public void updateState3() {
      if (this.file.isFile()) {
         try {
            this.map.clear();
            this.map.putAll(this.resolveMap(FileUtils.readFileToString(this.file)));
         } catch (IOException ioException) {
            logger.error("Couldn't read statistics file " + this.file, ioException);
         } catch (JsonParseException jsonParseException) {
            logger.error("Couldn't parse statistics file " + this.file, jsonParseException);
         }
      }
   }

   public void updateState2() {
      try {
         FileUtils.writeStringToFile(this.file, resolveText(this.map));
      } catch (IOException ioException) {
         logger.error("Couldn't save stats", ioException);
      }
   }
}
