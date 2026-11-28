package net.lenni0451.commons.httpclient.content;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import javax.annotation.Nonnull;
import net.lenni0451.commons.httpclient.content.impl.ByteArrayContent;
import net.lenni0451.commons.httpclient.content.impl.FileContent;
import net.lenni0451.commons.httpclient.content.impl.MultiPartFormContent;
import net.lenni0451.commons.httpclient.content.impl.StringContent;
import net.lenni0451.commons.httpclient.content.impl.URLEncodedFormContent;
import net.lenni0451.commons.httpclient.model.ContentType;

public abstract class HttpContent {
   private final ContentType contentType;
   public byte[] content;

   public static HttpContent bytes(byte[] content) {
      return new ByteArrayContent(content);
   }

   public static HttpContent bytes(byte[] content, int offset, int length) {
      return new ByteArrayContent(content, offset, length);
   }

   public static HttpContent string(String content) {
      return new StringContent(content);
   }

   public static HttpContent string(String content, Charset charset) {
      return new StringContent(content, charset);
   }

   public static HttpContent file(File file) {
      return new FileContent(file);
   }

   public static HttpContent form(String key, String value) {
      return new URLEncodedFormContent().put(key, value);
   }

   public static HttpContent form(Map<String, String> form) {
      return new URLEncodedFormContent(form);
   }

   public static MultiPartFormContent multiPartForm() {
      return new MultiPartFormContent();
   }

   public static StreamedHttpContent streamed(ContentType contentType, InputStream inputStream, int contentLength) {
      return new StreamedHttpContent(contentType, inputStream, contentLength);
   }

   public HttpContent(ContentType contentType) {
      this.contentType = contentType;
   }

   public ContentType getContentType() {
      return this.contentType;
   }

   @Nonnull
   public byte[] getAsBytes() throws IOException {
      if (this.content == null) {
         this.content = this.compute();
      }

      return this.content;
   }

   @Nonnull
   public String getAsString() throws IOException {
      return this.getAsString(StandardCharsets.UTF_8);
   }

   @Nonnull
   public String getAsString(Charset charset) throws IOException {
      return new String(this.getAsBytes(), charset);
   }

   public abstract int getContentLength();

   @Nonnull
   protected abstract byte[] compute() throws IOException;
}
