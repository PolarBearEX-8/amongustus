package net.minecraft.server.management;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.List;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserList {
   public static final Logger LOGGER = LogManager.getLogger();
   public final Gson gson;
   private final File saveFile;
   private final Map values = Maps.newHashMap();
   private boolean lanServer = true;
   private static final ParameterizedType saveFileFormat = new UserList.DefaultParameterizedType();

   public boolean hasEntry(Object obj) {
      return this.values.containsKey(this.getObjectKey(obj));
   }

   public String[] getKeys() {
      return (String[])this.values.keySet().toArray(new String[this.values.size()]);
   }

   public void addEntry(UserListEntry userListEntry) {
      this.values.put(this.getObjectKey(userListEntry.getValue()), userListEntry);

      try {
         this.writeChanges();
      } catch (IOException e) {
         LOGGER.warn("Could not save the list after adding a user.", e);
      }
   }

   public UserListEntry createEntry(JsonObject json) {
      return new UserListEntry(null, json);
   }

   public void setLanServer(boolean flag) {
      this.lanServer = flag;
   }

   public UserListEntry getEntry(Object obj) {
      this.removeExpired();
      return (UserListEntry)this.values.get(this.getObjectKey(obj));
   }

   public boolean isLanServer() {
      return this.lanServer;
   }

   public Map getValues() {
      return this.values;
   }

   public void writeChanges() throws IOException {
      Collection values2 = this.values.values();
      String str = this.gson.toJson(values2);
      BufferedWriter writer = null;

      try {
         writer = Files.newWriter(this.saveFile, Charsets.UTF_8);
         writer.write(str);
      } finally {
         IOUtils.closeQuietly(writer);
      }
   }

   public String getObjectKey(Object obj) {
      return obj.toString();
   }

   private void removeExpired() {
      ArrayList list = Lists.newArrayList();

      for (UserListEntry userListEntry : (Iterable<UserListEntry>)(this.values.values())) {
         if (userListEntry.hasBanExpired()) {
            list.add(userListEntry.getValue());
         }
      }

      for (Object obj : list) {
         this.values.remove(obj);
      }
   }

   public UserList(File file) {
      this.saveFile = file;
      GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
      gsonBuilder.registerTypeHierarchyAdapter(UserListEntry.class, new UserList.Serializer(this, null));
      this.gson = gsonBuilder.create();
   }

   public void removeEntry(Object obj) {
      this.values.remove(this.getObjectKey(obj));

      try {
         this.writeChanges();
      } catch (IOException e) {
         LOGGER.warn("Could not save the list after removing a user.", e);
      }
   }


   public static final class DefaultParameterizedType implements ParameterizedType {
      @Override
      public Type getOwnerType() {
         return null;
      }

      @Override
      public Type getRawType() {
         return List.class;
      }

      @Override
      public Type[] getActualTypeArguments() {
         return new Type[]{UserListEntry.class};
      }
   }

   public static class Serializer implements JsonDeserializer, JsonSerializer {
      public final UserList userList;

      public Serializer(UserList userList2, UserList.DefaultParameterizedType obj) {
         this(userList2);
      }

      public JsonElement serialize(Object obj, Type type, JsonSerializationContext jsonSerializationContext) {
         return this.serialize((UserListEntry)obj, type, jsonSerializationContext);
      }

      public UserListEntry deserializeEntry(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         if (elem.isJsonObject()) {
            JsonObject asJsonObject = elem.getAsJsonObject();
            return this.userList.createEntry(asJsonObject);
         } else {
            return null;
         }
      }

      public Object deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         return this.deserializeEntry(elem, type, context);
      }

      public JsonElement serialize(UserListEntry userListEntry, Type type, JsonSerializationContext jsonSerializationContext) {
         JsonObject json = new JsonObject();
         userListEntry.onSerialization(json);
         return json;
      }

      private Serializer(UserList userList2) {
         this.userList = userList2;
      }
   }
}
