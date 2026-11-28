package net.optifine.http;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import net.optifine.config.Config;

public class HttpPipelineReceiver extends Thread {
   private HttpPipelineConnection httpPipelineConnection = null;
   private static final Charset ASCII = Charset.forName("ASCII");
   private static final String HEADER_CONTENT_LENGTH = null;
   private static final char CR = '\r';
   private static final char LF = '\n';

   private void readFull(byte[] bytes, InputStream in) throws IOException {
      int n = 0;

      while (n < bytes.length) {
         int n2 = in.read(bytes, n, bytes.length - n);
         if (n2 < 0) {
            throw new EOFException();
         }

         n += n2;
      }
   }

   private byte[] readContentChunked(InputStream in) throws IOException {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

      int n;
      do {
         n = Integer.parseInt(Config.tokenize(this.readLine(in), "; ")[0], 16);
         byte[] bytes = new byte[n];
         this.readFull(bytes, in);
         byteArrayOutputStream.write(bytes);
         this.readLine(in);
      } while (n != 0);

      return byteArrayOutputStream.toByteArray();
   }

   private String readLine(InputStream in) throws IOException {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      int n = -1;
      boolean flag = false;

      while (true) {
         int n2 = in.read();
         if (n2 < 0) {
            break;
         }

         byteArrayOutputStream.write(n2);
         if (n == 13 && n2 == 10) {
            flag = true;
            break;
         }

         n = n2;
      }

      byte[] bytes = byteArrayOutputStream.toByteArray();
      String str = new String(bytes, ASCII);
      if (flag) {
         str = str.substring(0, str.length() - 2);
      }

      return str;
   }

   public HttpPipelineReceiver(HttpPipelineConnection httpPipelineConnection2) {
      super("HttpPipelineReceiver");
      this.httpPipelineConnection = httpPipelineConnection2;
   }

   @Override
   public void run() {
      while (!Thread.interrupted()) {
         HttpPipelineRequest httpPipelineRequest = null;

         try {
            httpPipelineRequest = this.httpPipelineConnection.getNextRequestReceive();
            HttpResponse httpResponse = this.readResponse(this.httpPipelineConnection.getInputStream());
            this.httpPipelineConnection.onResponseReceived(httpPipelineRequest, httpResponse);
         } catch (InterruptedException e) {
            return;
         } catch (Exception e2) {
            this.httpPipelineConnection.onExceptionReceive(httpPipelineRequest, e2);
         }
      }
   }

   private HttpResponse readResponse(InputStream in) throws IOException {
      String str = this.readLine(in);
      String[] strings = Config.tokenize(str, " ");
      if (strings.length < 3) {
         throw new IOException("Invalid status line: " + str);
      } else {
         String str2 = strings[0];
         int n = Config.parseInt(strings[1], 0);
         String str3 = strings[2];
         LinkedHashMap map = new LinkedHashMap();

         while (true) {
            String str4 = this.readLine(in);
            if (str4.length() <= 0) {
               byte[] bytes = null;
               String str5 = (String)map.get("Content-Length");
               if (str5 != null) {
                  int n2 = Config.parseInt(str5, -1);
                  if (n2 > 0) {
                     bytes = new byte[n2];
                     this.readFull(bytes, in);
                  }
               } else {
                  if (Config.equals((String)map.get("Transfer-Encoding"), "chunked")) {
                     bytes = this.readContentChunked(in);
                  }
               }

               return new HttpResponse(n, str, map, bytes);
            }

            int n3 = str4.indexOf(":");
            if (n3 > 0) {
               map.put(str4.substring(0, n3).trim(), str4.substring(n3 + 1).trim());
            }
         }
      }
   }
}
