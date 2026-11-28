package net.lenni0451.commons.httpclient.content.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.lenni0451.commons.httpclient.HeaderStore;
import net.lenni0451.commons.httpclient.content.HttpContent;
import net.lenni0451.commons.httpclient.model.ContentType;

public class MultiPartFormContent extends HttpContent {
   private final String boundary;
   private final List<MultiPartFormContent.FormPart> parts = new ArrayList<>();

   public MultiPartFormContent() {
      this("---" + UUID.randomUUID() + "---");
   }

   public MultiPartFormContent(String boundary) {
      super(new ContentType("multipart/form-data; boundary=" + boundary));
      this.boundary = boundary;
   }

   public MultiPartFormContent addPart(String name, HttpContent content) {
      return this.addPart(new MultiPartFormContent.FormPart(name, content));
   }

   public MultiPartFormContent addPart(String name, HttpContent content, @Nullable String fileName) {
      return this.addPart(new MultiPartFormContent.FormPart(name, content, fileName));
   }

   public MultiPartFormContent addPart(MultiPartFormContent.FormPart part) {
      this.parts.add(part);
      return this;
   }

   @Override
   public int getContentLength() {
      try {
         return this.getAsBytes().length;
      } catch (Throwable e) {
         throw MultiPartFormContent.<RuntimeException>sneakyThrow(e);
      }
   }

   @Nonnull
   @Override
   protected byte[] compute() throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();

      for (MultiPartFormContent.FormPart part : this.parts) {
         baos.write(("--" + this.boundary + "\r\n").getBytes());

         for (Entry<String, List<String>> entry : part.getHeaders().entrySet()) {
            for (String value : entry.getValue()) {
               baos.write((entry.getKey() + ": " + value + "\r\n").getBytes());
            }
         }

         baos.write("\r\n".getBytes());
         baos.write(part.getContent().getAsBytes());
         baos.write("\r\n".getBytes());
      }

      baos.write(("--" + this.boundary + "--").getBytes());
      return baos.toByteArray();
   }

   public static class FormPart extends HeaderStore<MultiPartFormContent.FormPart> {
      private final HttpContent content;

      public FormPart(String name, HttpContent content) {
         this(name, content, null);
      }

      public FormPart(String name, HttpContent content, @Nullable String fileName) {
         this.content = content;
         this.setHeader("Content-Disposition", "form-data; name=\"" + name + "\"" + (fileName == null ? "" : "; filename=\"" + fileName + "\""));
         this.setHeader("Content-Type", content.getContentType().toString());
      }

      public HttpContent getContent() {
         return this.content;
      }
   }

   @SuppressWarnings("unchecked")
   private static <E extends Throwable> RuntimeException sneakyThrow(Throwable t) throws E {
      throw (E)t;
   }
}
