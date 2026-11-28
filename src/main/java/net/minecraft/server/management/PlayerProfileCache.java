package net.minecraft.server.management;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mojang.authlib.ProfileLookupCallback;
import java.lang.reflect.Type;
import java.text.ParseException;
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
import net.minecraft.server.MinecraftServer;
import org.apache.commons.io.IOUtils;

public class PlayerProfileCache {
   public static final SimpleDateFormat dateFormat;
   private final Map usernameToProfileEntryMap = Maps.newHashMap();
   private final Map uuidToProfileEntryMap = Maps.newHashMap();
   private final LinkedList gameProfiles = Lists.newLinkedList();
   private final MinecraftServer mcServer;
   public final Gson gson;
   private final File usercacheFile;
   private static final ParameterizedType TYPE = new PlayerProfileCache.DefaultParameterizedType();

   private void addEntry(GameProfile profile, Date date) {
      UUID id = profile.getId();
      if (date == null) {
         Calendar instance = Calendar.getInstance();
         instance.setTime(new Date());
         instance.add(2, 1);
         date = instance.getTime();
      }

      String str = profile.getName().toLowerCase(Locale.ROOT);
      PlayerProfileCache.ProfileEntry profileEntry = new PlayerProfileCache.ProfileEntry(this, profile, date, null);
      if (this.uuidToProfileEntryMap.containsKey(id)) {
         PlayerProfileCache.ProfileEntry profileEntry2 = (PlayerProfileCache.ProfileEntry)this.uuidToProfileEntryMap.get(id);
         this.usernameToProfileEntryMap.remove(profileEntry2.getGameProfile().getName().toLowerCase(Locale.ROOT));
         this.gameProfiles.remove(profile);
      }

      this.usernameToProfileEntryMap.put(profile.getName().toLowerCase(Locale.ROOT), profileEntry);
      this.uuidToProfileEntryMap.put(id, profileEntry);
      this.gameProfiles.addFirst(profile);
      this.save();
   }

   public GameProfile getGameProfileForUsername(String str) {
      String str2 = str.toLowerCase(Locale.ROOT);
      PlayerProfileCache.ProfileEntry profileEntry = (PlayerProfileCache.ProfileEntry)this.usernameToProfileEntryMap.get(str2);
      if (profileEntry != null && new Date().getTime() >= PlayerProfileCache.ProfileEntry.accessExpirationDate(profileEntry).getTime()) {
         this.uuidToProfileEntryMap.remove(profileEntry.getGameProfile().getId());
         this.usernameToProfileEntryMap.remove(profileEntry.getGameProfile().getName().toLowerCase(Locale.ROOT));
         this.gameProfiles.remove(profileEntry.getGameProfile());
         profileEntry = null;
      }

      if (profileEntry != null) {
         GameProfile gameProfile = profileEntry.getGameProfile();
         this.gameProfiles.remove(gameProfile);
         this.gameProfiles.addFirst(gameProfile);
      } else {
         GameProfile gameProfile2 = getGameProfile(this.mcServer, str2);
         if (gameProfile2 != null) {
            this.addEntry(gameProfile2);
            profileEntry = (PlayerProfileCache.ProfileEntry)this.usernameToProfileEntryMap.get(str2);
         }
      }

      this.save();
      return profileEntry == null ? null : profileEntry.getGameProfile();
   }

   public void addEntry(GameProfile profile) {
      this.addEntry(profile, (Date)null);
   }

   private PlayerProfileCache.ProfileEntry getByUUID(UUID uuid) {
      PlayerProfileCache.ProfileEntry profileEntry = (PlayerProfileCache.ProfileEntry)this.uuidToProfileEntryMap.get(uuid);
      if (profileEntry != null) {
         GameProfile gameProfile = profileEntry.getGameProfile();
         this.gameProfiles.remove(gameProfile);
         this.gameProfiles.addFirst(gameProfile);
      }

      return profileEntry;
   }

   private List getEntriesWithLimit(int n) {
      ArrayList list = Lists.newArrayList();

      for (GameProfile profile : (Iterable<GameProfile>)(Lists.newArrayList(Iterators.limit(this.gameProfiles.iterator(), n)))) {
         PlayerProfileCache.ProfileEntry byUUID = this.getByUUID(profile.getId());
         if (byUUID != null) {
            list.add(byUUID);
         }
      }

      return list;
   }

   private static GameProfile getGameProfile(MinecraftServer minecraftServer, String str) {
      GameProfile[] profiles = new GameProfile[1];
      PlayerProfileCache.DefaultProfileLookupCallback obj = new PlayerProfileCache.DefaultProfileLookupCallback(profiles);
      minecraftServer.getGameProfileRepository().findProfilesByNames(new String[]{str}, Agent.MINECRAFT, obj);
      if (!minecraftServer.isServerInOnlineMode() && profiles[0] == null) {
         UUID uuid = EntityPlayer.getUUID(new GameProfile((UUID)null, str));
         obj.onProfileLookupSucceeded(new GameProfile(uuid, str));
      }

      return profiles[0];
   }

   static {
      String str = "yyyy-MM-dd HH:mm:ss Z";
      dateFormat = new SimpleDateFormat(str);
   }

   public PlayerProfileCache(MinecraftServer minecraftServer, File file) {
      this.mcServer = minecraftServer;
      this.usercacheFile = file;
      GsonBuilder gsonBuilder = new GsonBuilder();
      gsonBuilder.registerTypeHierarchyAdapter(PlayerProfileCache.ProfileEntry.class, new PlayerProfileCache.Serializer(this, null));
      this.gson = gsonBuilder.create();
      this.load();
   }

   public GameProfile getProfileByUUID(UUID uuid) {
      PlayerProfileCache.ProfileEntry profileEntry = (PlayerProfileCache.ProfileEntry)this.uuidToProfileEntryMap.get(uuid);
      return profileEntry == null ? null : profileEntry.getGameProfile();
   }

   public void load() {
      BufferedReader reader = null;

      try {
         reader = Files.newReader(this.usercacheFile, Charsets.UTF_8);
         List list = (List)this.gson.fromJson(reader, TYPE);
         this.usernameToProfileEntryMap.clear();
         this.uuidToProfileEntryMap.clear();
         this.gameProfiles.clear();
         if (list != null) {
            for (PlayerProfileCache.ProfileEntry profileEntry : (Iterable<PlayerProfileCache.ProfileEntry>)(Lists.reverse(list))) {
               if (profileEntry != null) {
                  this.addEntry(profileEntry.getGameProfile(), profileEntry.getExpirationDate());
               }
            }

            return;
         }
      } catch (FileNotFoundException e) {
         return;
      } catch (JsonParseException e2) {
         return;
      } finally {
         IOUtils.closeQuietly(reader);
      }
   }

   public void save() {
      String str = this.gson.toJson(this.getEntriesWithLimit(1000));
      BufferedWriter writer = null;

      try {
         writer = Files.newWriter(this.usercacheFile, Charsets.UTF_8);
         writer.write(str);
         return;
      } catch (FileNotFoundException e) {
         return;
      } catch (IOException e2) {
      } finally {
         IOUtils.closeQuietly(writer);
      }
   }

   public String[] getUsernames() {
      ArrayList list = Lists.newArrayList(this.usernameToProfileEntryMap.keySet());
      return (String[])list.toArray(new String[list.size()]);
   }


   public static final class DefaultParameterizedType implements ParameterizedType {
      @Override
      public Type[] getActualTypeArguments() {
         return new Type[]{PlayerProfileCache.ProfileEntry.class};
      }

      @Override
      public Type getOwnerType() {
         return null;
      }

      @Override
      public Type getRawType() {
         return List.class;
      }
   }

   public static final class DefaultProfileLookupCallback implements ProfileLookupCallback {
      public final GameProfile[] profileResult;

      public DefaultProfileLookupCallback(GameProfile[] profiles) {
         this.profileResult = profiles;
      }

      public void onProfileLookupSucceeded(GameProfile profile) {
         this.profileResult[0] = profile;
      }

      public void onProfileLookupFailed(GameProfile profile, Exception e) {
         this.profileResult[0] = null;
      }
   }

   public static class Serializer implements JsonSerializer, JsonDeserializer {
      public final PlayerProfileCache profileCache;

      public Serializer(PlayerProfileCache playerProfileCache, PlayerProfileCache.DefaultParameterizedType obj) {
         this(playerProfileCache);
      }

      public PlayerProfileCache.ProfileEntry deserializeEntry(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         if (elem.isJsonObject()) {
            JsonObject asJsonObject = elem.getAsJsonObject();
            JsonElement elem2 = asJsonObject.get("name");
            JsonElement elem3 = asJsonObject.get("uuid");
            JsonElement elem4 = asJsonObject.get("expiresOn");
            if (elem2 != null && elem3 != null) {
               String asString = elem3.getAsString();
               String asString2 = elem2.getAsString();
               Date date = null;
               if (elem4 != null) {
                  try {
                     date = PlayerProfileCache.dateFormat.parse(elem4.getAsString());
                  } catch (ParseException e) {
                     date = null;
                  }
               }

               if (asString2 != null && asString != null) {
                  UUID uuid;
                  try {
                     uuid = UUID.fromString(asString);
                  } catch (Throwable e2) {
                     return null;
                  }

                  PlayerProfileCache profileCache2 = this.profileCache;
                  return new PlayerProfileCache.ProfileEntry(profileCache2, new GameProfile(uuid, asString2), date, null);
               } else {
                  return null;
               }
            } else {
               return null;
            }
         } else {
            return null;
         }
      }

      public JsonElement serialize(Object obj, Type type, JsonSerializationContext jsonSerializationContext) {
         return this.serialize((PlayerProfileCache.ProfileEntry)obj, type, jsonSerializationContext);
      }

      private Serializer(PlayerProfileCache playerProfileCache) {
         this.profileCache = playerProfileCache;
      }

      public JsonElement serialize(PlayerProfileCache.ProfileEntry profileEntry, Type type, JsonSerializationContext jsonSerializationContext) {
         JsonObject json = new JsonObject();
         json.addProperty("name", profileEntry.getGameProfile().getName());
         UUID id = profileEntry.getGameProfile().getId();
         json.addProperty("uuid", id == null ? "" : id.toString());
         json.addProperty("expiresOn", PlayerProfileCache.dateFormat.format(profileEntry.getExpirationDate()));
         return json;
      }

      public Object deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         return this.deserializeEntry(elem, type, context);
      }
   }

   public static class ProfileEntry {
      private final GameProfile gameProfile;
      private final Date expirationDate;
      public final PlayerProfileCache profileCache;

      public ProfileEntry(PlayerProfileCache playerProfileCache, GameProfile profile, Date date, PlayerProfileCache.DefaultParameterizedType obj) {
         this(playerProfileCache, profile, date);
      }

      public GameProfile getGameProfile() {
         return this.gameProfile;
      }

      public Date getExpirationDate() {
         return this.expirationDate;
      }

      public static Date accessExpirationDate(ProfileEntry profileEntry) {
         return profileEntry.expirationDate;
      }

      private ProfileEntry(PlayerProfileCache playerProfileCache, GameProfile profile, Date date) {
         this.profileCache = playerProfileCache;
         this.gameProfile = profile;
         this.expirationDate = date;
      }
   }
}
