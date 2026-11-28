package net.minecraft.util;

import java.io.FileOutputStream;
import java.io.InputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.ServerSocket;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpUtil {
   public static final ListeningExecutorService DOWNLOADER_EXECUTOR = MoreExecutors.listeningDecorator(
      Executors.newCachedThreadPool(new ThreadFactoryBuilder().setDaemon(true).setNameFormat("Downloader %d").build())
   );
   private static final AtomicInteger downloadThreadsStarted = new AtomicInteger(0);
   private static final Logger logger = LogManager.getLogger();

   public static String postMap(URL url, Map map, boolean flag) {
      return post(url, buildPostString(map), flag);
   }

   public static Logger accessLogger() {
      return logger;
   }

   private static String post(URL url, String str, boolean flag) {
      try {
         Proxy proxy = MinecraftServer.getServer() == null ? null : MinecraftServer.getServer().getServerProxy();
         if (proxy == null) {
            proxy = Proxy.NO_PROXY;
         }

         HttpURLConnection conn = (HttpURLConnection)url.openConnection(proxy);
         conn.setRequestMethod("POST");
         conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
         conn.setRequestProperty("Content-Length", "" + str.getBytes().length);
         conn.setRequestProperty("Content-Language", "en-US");
         conn.setUseCaches(false);
         conn.setDoInput(true);
         conn.setDoOutput(true);
         DataOutputStream out = new DataOutputStream(conn.getOutputStream());
         out.writeBytes(str);
         out.flush();
         out.close();
         BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
         StringBuffer sb = new StringBuffer();

         String str2;
         while ((str2 = reader.readLine()) != null) {
            sb.append(str2);
            sb.append('\r');
         }

         reader.close();
         return sb.toString();
      } catch (Exception e) {
         if (!flag) {
            logger.error("Could not post to " + url, e);
         }

         return "";
      }
   }

   public static int getSuitableLanPort() throws IOException {
      ServerSocket serverSocket = null;
      int n = -1;

      try {
         serverSocket = new ServerSocket(0);
         n = serverSocket.getLocalPort();
      } finally {
         try {
            if (serverSocket != null) {
               serverSocket.close();
            }
         } catch (IOException e) {
         }
      }

      return n;
   }

   public static ListenableFuture downloadResourcePack(File file, String str, Map map, int n, IProgressUpdate progressUpdate, Proxy proxy) {
      return DOWNLOADER_EXECUTOR.submit(new HttpUtil.WorkerTask(progressUpdate, str, proxy, map, file, n));
   }

   public static String buildPostString(Map map) {
      StringBuilder sb = new StringBuilder();

      for (Entry entry : (Iterable<Entry>)(map.entrySet())) {
         if (sb.length() > 0) {
            sb.append('&');
         }

         try {
            sb.append(URLEncoder.encode((String)entry.getKey(), "UTF-8"));
         } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
         }

         if (entry.getValue() != null) {
            sb.append('=');

            try {
               sb.append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
            } catch (UnsupportedEncodingException e2) {
               e2.printStackTrace();
            }
         }
      }

      return sb.toString();
   }

   public static String get(URL url) throws IOException {
      HttpURLConnection conn = (HttpURLConnection)url.openConnection();
      conn.setRequestMethod("GET");
      BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      StringBuilder sb = new StringBuilder();

      String str;
      while ((str = reader.readLine()) != null) {
         sb.append(str);
         sb.append('\r');
      }

      reader.close();
      return sb.toString();
   }


   public static final class WorkerTask implements Runnable {
      public final IProgressUpdate progressUpdate;
      public final String packUrl;
      public final Proxy proxy;
      public final Map requestHeaders;
      public final File saveFile;
      public final int maxSize;

      @Override
      public void run() {
         HttpURLConnection conn = null;
         InputStream in = null;
         DataOutputStream out = null;
         if (this.progressUpdate != null) {
            this.progressUpdate.resetProgressAndMessage("Downloading Resource Pack");
            this.progressUpdate.displayLoadingString("Making Request...");
         }

         try {
            byte[] bytes = new byte[4096];
            conn = (HttpURLConnection)new URL(this.packUrl).openConnection(this.proxy);
            float f = 0.0F;
            float size = (float)this.requestHeaders.entrySet().size();

            for (Entry entry : (Iterable<Entry>)(this.requestHeaders.entrySet())) {
               conn.setRequestProperty((String)entry.getKey(), (String)entry.getValue());
               if (this.progressUpdate != null) {
                  this.progressUpdate.setLoadingProgress((int)(++f / size * 100.0F));
               }
            }

            in = conn.getInputStream();
            size = (float)conn.getContentLength();
            int contentLength = conn.getContentLength();
            if (this.progressUpdate != null) {
               this.progressUpdate.displayLoadingString(String.format("Downloading file (%.2f MB)...", size / 1000.0F / 1000.0F));
            }

            if (this.saveFile.exists()) {
               long length = this.saveFile.length();
               if (length == (long)contentLength) {
                  if (this.progressUpdate != null) {
                     this.progressUpdate.setDoneWorking();
                  }

                  return;
               }

               HttpUtil.accessLogger().warn("Deleting " + this.saveFile + " as it does not match what we currently have (" + contentLength + " vs our " + length + ").");
               FileUtils.deleteQuietly(this.saveFile);
            } else if (this.saveFile.getParentFile() != null) {
               this.saveFile.getParentFile().mkdirs();
            }

            out = new DataOutputStream(new FileOutputStream(this.saveFile));
            if (this.maxSize > 0 && size > (float)this.maxSize) {
               if (this.progressUpdate != null) {
                  this.progressUpdate.setDoneWorking();
               }

               throw new IOException("Filesize is bigger than maximum allowed (file is " + f + ", limit is " + this.maxSize + ")");
            }

            int n = 0;

            while (true) {
               if ((n = in.read(bytes)) < 0) {
                  if (this.progressUpdate == null) {
                     return;
                  }

                  this.progressUpdate.setDoneWorking();
                  return;
               }

               f += (float)n;
               if (this.progressUpdate != null) {
                  this.progressUpdate.setLoadingProgress((int)(f / size * 100.0F));
               }

               if (this.maxSize > 0 && f > (float)this.maxSize) {
                  if (this.progressUpdate != null) {
                     this.progressUpdate.setDoneWorking();
                  }

                  throw new IOException("Filesize was bigger than maximum allowed (got >= " + f + ", limit was " + this.maxSize + ")");
               }

               if (Thread.interrupted()) {
                  HttpUtil.accessLogger().error("INTERRUPTED");
                  if (this.progressUpdate != null) {
                     this.progressUpdate.setDoneWorking();
                  }
                  break;
               }

               out.write(bytes, 0, n);
            }
         } catch (Throwable e) {
            e.printStackTrace();
            if (conn != null) {
               InputStream errorStream = conn.getErrorStream();

               try {
                  HttpUtil.accessLogger().error(IOUtils.toString(errorStream));
               } catch (IOException e2) {
                  e2.printStackTrace();
               }
            }

            if (this.progressUpdate != null) {
               this.progressUpdate.setDoneWorking();
               return;
            }

            return;
         } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
         }
      }

      public WorkerTask(IProgressUpdate progressUpdate2, String str, Proxy proxy2, Map map, File file, int n) {
         this.progressUpdate = progressUpdate2;
         this.packUrl = str;
         this.proxy = proxy2;
         this.requestHeaders = map;
         this.saveFile = file;
         this.maxSize = n;
      }
   }
}
