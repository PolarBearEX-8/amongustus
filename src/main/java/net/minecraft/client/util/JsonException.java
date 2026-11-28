package net.minecraft.client.util;

import org.apache.commons.lang3.StringUtils;
import com.google.common.collect.Lists;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class JsonException extends IOException {
   private final List entries = Lists.newArrayList();
   private final String message;

   public JsonException(String str, Throwable t) {
      super(t);
      this.entries.add(new JsonException.Entry(null));
      this.message = str;
   }

   public void setFilenameAndFlush(String str) {
      JsonException.Entry.assignFilename((JsonException.Entry)this.entries.get(0), str);
      this.entries.add(0, new JsonException.Entry(null));
   }

   @Override
   public String getMessage() {
      return "Invalid " + ((JsonException.Entry)this.entries.get(this.entries.size() - 1)).toString() + ": " + this.message;
   }

   public JsonException(String str) {
      this.entries.add(new JsonException.Entry(null));
      this.message = str;
   }

   public static JsonException forException(Exception e) {
      if (e instanceof JsonException) {
         return (JsonException)e;
      } else {
         String message2 = e.getMessage();
         if (e instanceof FileNotFoundException) {
            message2 = "File not found";
         }

         return new JsonException(message2, e);
      }
   }

   public void prependJsonKey(String str) {
      JsonException.Entry.invokeAddJsonKey((JsonException.Entry)this.entries.get(0), str);
   }


   public static class InnerEntry {
   }

   public static class Entry {
      private String filename = null;
      private final List jsonKeys = Lists.newArrayList();

      public Entry(JsonException.InnerEntry obj) {
         this();
      }

      public String getJsonKeys() {
         return StringUtils.join(this.jsonKeys, "->");
      }

      @Override
      public String toString() {
         return this.filename != null
            ? (!this.jsonKeys.isEmpty() ? this.filename + " " + this.getJsonKeys() : this.filename)
            : (!this.jsonKeys.isEmpty() ? "(Unknown file) " + this.getJsonKeys() : "(Unknown file)");
      }

      private Entry() {
      }

      private void addJsonKey(String str) {
         this.jsonKeys.add(0, str);
      }

      public static String assignFilename(Entry entry, String str) {
         return entry.filename = str;
      }

      public static void invokeAddJsonKey(Entry entry, String str) {
         entry.addJsonKey(str);
      }
   }
}
