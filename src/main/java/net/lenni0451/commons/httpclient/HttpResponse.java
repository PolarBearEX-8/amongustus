package net.lenni0451.commons.httpclient;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.lenni0451.commons.httpclient.constants.StatusCodes;
import net.lenni0451.commons.httpclient.model.ContentType;
import net.lenni0451.commons.httpclient.utils.HttpRequestUtils;

public class HttpResponse extends HeaderStore<HttpResponse> {
   private final URL url;
   private final int statusCode;
   private byte[] content;
   private InputStream inputStream;

   public HttpResponse(URL url, int statusCode, byte[] content, Map<String, List<String>> headers) {
      super(headers);
      this.url = url;
      this.statusCode = statusCode;
      this.content = content;
   }

   public HttpResponse(URL url, int statusCode, InputStream inputStream, Map<String, List<String>> headers) {
      super(headers);
      this.url = url;
      this.statusCode = statusCode;
      this.inputStream = inputStream;
   }

   public URL getURL() {
      return this.url;
   }

   public int getStatusCode() {
      return this.statusCode;
   }

   public String getStatusMessage() {
      return StatusCodes.STATUS_CODES.getOrDefault(this.statusCode, "Unknown");
   }

   public InputStream getInputStream() {
      return (InputStream)(this.inputStream == null ? new ByteArrayInputStream(this.content) : this.inputStream);
   }

   public byte[] getContent() {
      try {
         if (this.content == null) {
            this.content = HttpRequestUtils.readFromStream(this.inputStream);
            this.inputStream.close();
            this.inputStream = null;
         }

         return this.content;
      } catch (Throwable e) {
         throw HttpResponse.<RuntimeException>sneakyThrow(e);
      }
   }

   @SuppressWarnings("unchecked")
   private static <E extends Throwable> RuntimeException sneakyThrow(Throwable t) throws E {
      throw (E)t;
   }

   public String getContentAsString() {
      return this.getContentAsString(this.getContentType().flatMap(ContentType::getCharset).orElse(StandardCharsets.UTF_8));
   }

   public String getContentAsString(Charset charset) {
      return new String(this.getContent(), charset);
   }

   public Optional<ContentType> getContentType() {
      return this.getFirstHeader("Content-Type").map(ContentType::parse);
   }
}
