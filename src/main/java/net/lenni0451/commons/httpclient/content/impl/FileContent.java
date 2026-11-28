package net.lenni0451.commons.httpclient.content.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.annotation.Nonnull;
import net.lenni0451.commons.httpclient.constants.ContentTypes;
import net.lenni0451.commons.httpclient.content.HttpContent;
import net.lenni0451.commons.httpclient.model.ContentType;
import net.lenni0451.commons.httpclient.utils.HttpRequestUtils;

public class FileContent extends HttpContent {
   private final File file;

   public FileContent(File file) {
      this(ContentTypes.APPLICATION_OCTET_STREAM, file);
   }

   public FileContent(ContentType contentType, File file) {
      super(contentType);
      this.file = file;
   }

   @Override
   public int getContentLength() {
      return (int)this.file.length();
   }

   @Nonnull
   @Override
   protected byte[] compute() throws IOException {
      FileInputStream fis = new FileInputStream(this.file);

      byte[] bytes;
      try {
         bytes = HttpRequestUtils.readFromStream(fis);
      } catch (Throwable e) {
         try {
            fis.close();
         } catch (Throwable e2) {
            e.addSuppressed(e2);
         }

         throw e;
      }

      fis.close();
      return bytes;
   }
}
