package net.lenni0451.commons.httpclient.content;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nonnull;
import net.lenni0451.commons.httpclient.model.ContentType;
import net.lenni0451.commons.httpclient.utils.HttpRequestUtils;

public class StreamedHttpContent extends HttpContent {
   private final InputStream inputStream;
   private final int contentLength;
   private int bufferSize = 1024;

   public static StreamedHttpContent wrap(HttpContent other) throws IOException {
      byte[] content = other.getAsBytes();
      return new StreamedHttpContent(other.getContentType(), new ByteArrayInputStream(content), content.length);
   }

   public StreamedHttpContent(ContentType contentType, InputStream inputStream, int contentLength) {
      super(contentType);
      this.inputStream = inputStream;
      this.contentLength = contentLength;
   }

   public InputStream getInputStream() {
      return this.inputStream;
   }

   public int getBufferSize() {
      return this.bufferSize;
   }

   public StreamedHttpContent setBufferSize(int bufferSize) {
      this.bufferSize = bufferSize;
      return this;
   }

   @Override
   public int getContentLength() {
      return this.contentLength;
   }

   @Nonnull
   @Override
   protected byte[] compute() throws IOException {
      return HttpRequestUtils.readFromStream(this.inputStream);
   }
}
