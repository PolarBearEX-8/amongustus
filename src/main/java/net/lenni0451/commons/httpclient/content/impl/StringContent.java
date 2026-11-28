package net.lenni0451.commons.httpclient.content.impl;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import net.lenni0451.commons.httpclient.model.ContentType;

public class StringContent extends ByteArrayContent {
   public StringContent(String content) {
      this(content, StandardCharsets.UTF_8);
   }

   public StringContent(String content, Charset charset) {
      super(content.getBytes(charset));
   }

   public StringContent(ContentType contentType, String content) {
      this(contentType, content, StandardCharsets.UTF_8);
   }

   public StringContent(ContentType contentType, String content, Charset charset) {
      super(contentType, content.getBytes(charset));
   }
}
