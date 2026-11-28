package net.lenni0451.commons.httpclient.model;

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Nullable;

public class ContentType {
   private final String mimeType;
   private final Charset charset;
   private final String boundary;

   public static ContentType parse(String contentType) {
      if (!contentType.contains(";")) {
         return new ContentType(contentType.toLowerCase(Locale.ROOT), null, null);
      } else {
         String[] parts = contentType.split(";");
         String type = parts[0].toLowerCase(Locale.ROOT);
         Charset charset = null;
         String boundary = null;

         for (int i = 1; i < parts.length; i++) {
            String part = parts[i].trim();
            if (part.startsWith("charset=")) {
               try {
                  charset = Charset.forName(part.substring(8));
               } catch (UnsupportedCharsetException e) {
               }
            } else if (part.startsWith("boundary=")) {
               boundary = part.substring(9);
            }
         }

         return new ContentType(type, charset, boundary);
      }
   }

   public ContentType(String mimeType) {
      this(mimeType, null, null);
   }

   public ContentType(String mimeType, @Nullable Charset charset) {
      this(mimeType, charset, null);
   }

   public ContentType(String mimeType, @Nullable String boundary) {
      this(mimeType, null, boundary);
   }

   public ContentType(String mimeType, @Nullable Charset charset, @Nullable String boundary) {
      this.mimeType = mimeType;
      this.charset = charset;
      this.boundary = boundary;
   }

   public String getMimeType() {
      return this.mimeType;
   }

   public Optional<Charset> getCharset() {
      return Optional.ofNullable(this.charset);
   }

   public Optional<String> getBoundary() {
      return Optional.ofNullable(this.boundary);
   }

   @Override
   public String toString() {
      return this.mimeType + (this.charset != null ? "; charset=" + this.charset.name() : "") + (this.boundary != null ? "; boundary=" + this.boundary : "");
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         ContentType that = (ContentType)o;
         return Objects.equals(this.mimeType, that.mimeType) && Objects.equals(this.charset, that.charset) && Objects.equals(this.boundary, that.boundary);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.mimeType, this.charset, this.boundary);
   }
}
