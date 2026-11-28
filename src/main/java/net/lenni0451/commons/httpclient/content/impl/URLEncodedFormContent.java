package net.lenni0451.commons.httpclient.content.impl;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import net.lenni0451.commons.httpclient.constants.ContentTypes;
import net.lenni0451.commons.httpclient.content.HttpContent;
import net.lenni0451.commons.httpclient.utils.URLCoder;

public class URLEncodedFormContent extends HttpContent {
   private final List<URLEncodedFormContent.FormEntry> entries;
   private final Charset charset;

   public URLEncodedFormContent() {
      this(StandardCharsets.UTF_8);
   }

   public URLEncodedFormContent(Map<String, String> entries) {
      this(entries, StandardCharsets.UTF_8);
   }

   public URLEncodedFormContent(Charset charset) {
      super(ContentTypes.APPLICATION_FORM_URLENCODED);
      this.entries = new ArrayList<>();
      this.charset = charset;
   }

   public URLEncodedFormContent(Map<String, String> entries, Charset charset) {
      super(ContentTypes.APPLICATION_FORM_URLENCODED);
      this.entries = entries.entrySet().stream().map(e -> new URLEncodedFormContent.FormEntry(e.getKey(), e.getValue())).collect(Collectors.toList());
      this.charset = charset;
   }

   public URLEncodedFormContent put(String key, String value) {
      this.entries.add(new URLEncodedFormContent.FormEntry(key, value));
      this.content = null;
      return this;
   }

   @Override
   public int getContentLength() {
      try {
         return this.getAsBytes().length;
      } catch (Throwable e) {
         throw URLEncodedFormContent.<RuntimeException>sneakyThrow(e);
      }
   }

   @Nonnull
   @Override
   protected byte[] compute() {
      StringBuilder builder = new StringBuilder();

      for (URLEncodedFormContent.FormEntry entry : this.entries) {
         if (builder.length() != 0) {
            builder.append("&");
         }

         builder.append(entry.encodeKey(this.charset)).append("=").append(entry.encodeValue(this.charset));
      }

      return builder.toString().getBytes(this.charset);
   }

   private static class FormEntry {
      private final String key;
      private final String value;

      private FormEntry(String key, String value) {
         this.key = key;
         this.value = value;
      }

      private String getKey() {
         return this.key;
      }

      private String encodeKey(Charset charset) {
         return URLCoder.encode(this.key, charset);
      }

      private String getValue() {
         return this.value;
      }

      private String encodeValue(Charset charset) {
         return URLCoder.encode(this.value, charset);
      }
   }

   @SuppressWarnings("unchecked")
   private static <E extends Throwable> RuntimeException sneakyThrow(Throwable t) throws E {
      throw (E)t;
   }
}
