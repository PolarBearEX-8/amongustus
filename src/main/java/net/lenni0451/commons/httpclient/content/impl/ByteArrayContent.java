package net.lenni0451.commons.httpclient.content.impl;

import java.util.Arrays;
import javax.annotation.Nonnull;
import net.lenni0451.commons.httpclient.constants.ContentTypes;
import net.lenni0451.commons.httpclient.content.HttpContent;
import net.lenni0451.commons.httpclient.model.ContentType;

public class ByteArrayContent extends HttpContent {
   private final byte[] content;
   private final int start;
   private final int length;

   public ByteArrayContent(byte[] content) {
      this(content, 0, content.length);
   }

   public ByteArrayContent(byte[] content, int start, int length) {
      super(ContentTypes.APPLICATION_OCTET_STREAM);
      this.content = content;
      this.start = start;
      this.length = length;
   }

   public ByteArrayContent(ContentType contentType, byte[] content) {
      this(contentType, content, 0, content.length);
   }

   public ByteArrayContent(ContentType contentType, byte[] content, int start, int length) {
      super(contentType);
      this.content = content;
      this.start = start;
      this.length = length;
   }

   @Override
   public int getContentLength() {
      return this.content.length;
   }

   @Nonnull
   @Override
   protected byte[] compute() {
      return Arrays.copyOfRange(this.content, this.start, this.start + this.length);
   }
}
