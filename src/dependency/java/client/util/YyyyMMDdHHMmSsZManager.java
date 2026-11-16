package client.util;

import com.google.common.base.Charsets;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import org.apache.commons.io.IOUtils;

public class YyyyMMDdHHMmSsZManager {
   public static final SimpleDateFormat simpleDateFormat;
   public final Map map2 = Maps.newHashMap();
   public final Map map = Maps.newHashMap();
   public final LinkedList linkedList = Lists.newLinkedList();
   public final VanillaRunnable vanillaRunnable;
   public final Gson gson;
   public final File file;
   public static final ParameterizedType parameterizedType = new AuxiliaryParameterizedTypeAdapter();

   public void updateState() {
      String text = this.gson.toJson(this.resolveList(1000));
      BufferedWriter writer = null;

      try {
         writer = Files.newWriter(this.file, Charsets.UTF_8);
         writer.write(text);
         return;
      } catch (FileNotFoundException fileNotFoundException) {
         return;
      } catch (IOException ioException) {
      } finally {
         IOUtils.closeQuietly(writer);
      }
   }

   private static GameProfile resolveGameProfile(VanillaRunnable vanillaRunnable2, String text) {
      GameProfile[] gameProfileArray = new GameProfile[1];
      InterpolationBuilder interpolationBuilder = new InterpolationBuilder(gameProfileArray);
      vanillaRunnable2.getGameProfileRepository().findProfilesByNames(new String[]{text}, Agent.MINECRAFT, interpolationBuilder);
      if (!vanillaRunnable2.isEnabled2() && gameProfileArray[0] == null) {
         UUID uuid = EntityPlayer.resolveUUID(new GameProfile((UUID)null, text));
         GameProfile gameProfile = new GameProfile(uuid, text);
         interpolationBuilder.onProfileLookupSucceeded(gameProfile);
      }

      return gameProfileArray[0];
   }

   public GameProfile resolveGameProfile(String text) {
      String text2 = text.toLowerCase(Locale.ROOT);
      GameProfileEntry gameProfileEntry = (GameProfileEntry)this.map2.get(text2);
      if (gameProfileEntry != null && new Date().getTime() >= GameProfileEntry.resolveDate(gameProfileEntry).getTime()) {
         this.map.remove(gameProfileEntry.getGameProfile().getId());
         this.map2.remove(gameProfileEntry.getGameProfile().getName().toLowerCase(Locale.ROOT));
         this.linkedList.remove(gameProfileEntry.getGameProfile());
         gameProfileEntry = null;
      }

      if (gameProfileEntry != null) {
         GameProfile gameProfile = gameProfileEntry.getGameProfile();
         this.linkedList.remove(gameProfile);
         this.linkedList.addFirst(gameProfile);
      } else {
         GameProfile gameProfile2 = resolveGameProfile(this.vanillaRunnable, text2);
         if (gameProfile2 != null) {
            this.applyGameProfile(gameProfile2);
            gameProfileEntry = (GameProfileEntry)this.map2.get(text2);
         }
      }

      this.updateState();
      return gameProfileEntry == null ? null : gameProfileEntry.getGameProfile();
   }

   private void applyGameProfile(GameProfile gameProfile, Date date) {
      Date date2 = date;
      UUID id = gameProfile.getId();
      if (date == null) {
         Calendar instance = Calendar.getInstance();
         instance.setTime(new Date());
         instance.add(2, 1);
         date2 = instance.getTime();
      }

      gameProfile.getName().toLowerCase(Locale.ROOT);
      GameProfileEntry gameProfileEntry = new GameProfileEntry(this, gameProfile, date2, null);
      if (this.map.containsKey(id)) {
         GameProfileEntry gameProfileEntry2 = (GameProfileEntry)this.map.get(id);
         this.map2.remove(gameProfileEntry2.getGameProfile().getName().toLowerCase(Locale.ROOT));
         this.linkedList.remove(gameProfile);
      }

      this.map2.put(gameProfile.getName().toLowerCase(Locale.ROOT), gameProfileEntry);
      this.map.put(id, gameProfileEntry);
      this.linkedList.addFirst(gameProfile);
      this.updateState();
   }

   private GameProfileEntry resolveGameProfileEntry(UUID uuid) {
      GameProfileEntry gameProfileEntry = (GameProfileEntry)this.map.get(uuid);
      if (gameProfileEntry != null) {
         GameProfile gameProfile = gameProfileEntry.getGameProfile();
         this.linkedList.remove(gameProfile);
         this.linkedList.addFirst(gameProfile);
      }

      return gameProfileEntry;
   }

   public void applyGameProfile(GameProfile gameProfile) {
      this.applyGameProfile(gameProfile, (Date)null);
   }

   private List resolveList(int count) {
      ArrayList list = Lists.newArrayList();

      for (Object gameProfileObj : Lists.newArrayList(Iterators.limit(this.linkedList.iterator(), count))) { GameProfile gameProfile = (GameProfile)gameProfileObj;
         GameProfileEntry gameProfileEntry = this.resolveGameProfileEntry(gameProfile.getId());
         if (gameProfileEntry != null) {
            list.add(gameProfileEntry);
         }
      }

      return list;
   }

   public void updateState2() {
      BufferedReader reader = null;

      try {
         reader = Files.newReader(this.file, Charsets.UTF_8);
         List list = (List)this.gson.fromJson(reader, parameterizedType);
         this.map2.clear();
         this.map.clear();
         this.linkedList.clear();
         if (list != null) {
            for (Object gameProfileEntryObj : Lists.reverse(list)) { GameProfileEntry gameProfileEntry = (GameProfileEntry)gameProfileEntryObj;
               if (gameProfileEntry != null) {
                  this.applyGameProfile(gameProfileEntry.getGameProfile(), gameProfileEntry.getDate());
               }
            }

            return;
         }
      } catch (FileNotFoundException fileNotFoundException) {
         return;
      } catch (JsonParseException jsonParseException) {
         return;
      } finally {
         IOUtils.closeQuietly(reader);
      }
   }

   public String[] getTextArray() {
      ArrayList list = Lists.newArrayList(this.map2.keySet());
      return (String[])list.toArray(new String[list.size()]);
   }

   public GameProfile resolveGameProfile(UUID uuid) {
      GameProfileEntry gameProfileEntry = (GameProfileEntry)this.map.get(uuid);
      return gameProfileEntry == null ? null : gameProfileEntry.getGameProfile();
   }

   public YyyyMMDdHHMmSsZManager(VanillaRunnable vanillaRunnable2, File file2) {
      this.vanillaRunnable = vanillaRunnable2;
      this.file = file2;
      GsonBuilder gsonBuilder = new GsonBuilder();
      gsonBuilder.registerTypeHierarchyAdapter(GameProfileEntry.class, new NameJsonDeserializer(this, null));
      this.gson = gsonBuilder.create();
      this.updateState2();
   }

   static {
      String text = "yyyy-MM-dd HH:mm:ss Z";
      simpleDateFormat = new SimpleDateFormat(text);
   }
}
