package client.util;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Reader;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class GsonEntry {
   public static final Gson gson = new GsonBuilder()
      .registerTypeAdapter(GsonEntry.class, new TertiaryJsonDeserializerAdapter())
      .registerTypeAdapter(DynamicResourceLocationEntry.class, new StandardJsonDeserializerAdapter())
      .create();
   public final Map map = Maps.newHashMap();

   public static GsonEntry fromJson(Reader reader) {
      return (GsonEntry)gson.fromJson(reader, GsonEntry.class);
   }

   public EnhancedStringEntry get(String text) {
      EnhancedStringEntry enhancedStringEntry = (EnhancedStringEntry)this.map.get(text);
      if (enhancedStringEntry == null) {
         throw new InternalRuntimeExceptionAdapter(this);
      } else {
         return enhancedStringEntry;
      }
   }

   public GsonEntry(Collection collection) {
      for (EnhancedStringEntry enhancedStringEntry : (Iterable<EnhancedStringEntry>)(Iterable<?>)collection) {
         this.map.put(EnhancedStringEntry.resolveText(enhancedStringEntry), enhancedStringEntry);
      }
   }

   public GsonEntry(List list) {
      for (GsonEntry gsonEntry : (Iterable<GsonEntry>)(Iterable<?>)list) {
         this.map.putAll(gsonEntry.map);
      }
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other instanceof GsonEntry) {
         GsonEntry gsonEntry = (GsonEntry)other;
         return this.map.equals(gsonEntry.map);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return this.map.hashCode();
   }
}
