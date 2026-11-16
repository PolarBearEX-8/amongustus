package client.util;

import client.event.CompositeListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public final class GeometryWatcher implements Runnable {
   public final CompositeListener compositeListener;
   public final String labelText;
   public final Proxy proxy;
   public final Map map;
   public final File file;
   public final int count;

   @Override
   public void run() {
      HttpURLConnection httpURLConnection = null;
      InputStream inputStream = null;
      DataOutputStream dataOutputStream = null;
      if (this.compositeListener != null) {
         this.compositeListener.applyTextByText("Downloading Resource Pack");
         this.compositeListener.applyText("Making Request...");
      }

      try {
         byte[] byteValueArray = new byte[4096];
         httpURLConnection = (HttpURLConnection)new URL(this.labelText).openConnection(this.proxy);
         float factor = 0.0F;
         float size = (float)this.map.entrySet().size();

         for (Entry entry : (Iterable<java.util.Map.Entry>)(Iterable)this.map.entrySet()) {
            httpURLConnection.setRequestProperty((String)entry.getKey(), (String)entry.getValue());
            if (this.compositeListener != null) {
               this.compositeListener.setCount2((int)(++factor / size * 100.0F));
            }
         }

         inputStream = httpURLConnection.getInputStream();
         float contentLength = (float)httpURLConnection.getContentLength();
         int contentLength2 = httpURLConnection.getContentLength();
         if (this.compositeListener != null) {
            this.compositeListener.applyText(String.format("Downloading file (%.2f MB)...", contentLength / 1000.0F / 1000.0F));
         }

         if (this.file.exists()) {
            long length = this.file.length();
            if (length == (long)contentLength2) {
               if (this.compositeListener != null) {
                  this.compositeListener.updateState();
               }

               return;
            }

            GETManager.getLogger().warn("Deleting " + this.file + " as it does not match what we currently have (" + contentLength2 + " vs our " + length + ").");
            FileUtils.deleteQuietly(this.file);
         } else if (this.file.getParentFile() != null) {
            this.file.getParentFile().mkdirs();
         }

         dataOutputStream = new DataOutputStream(new FileOutputStream(this.file));
         if (this.count > 0 && contentLength > (float)this.count) {
            if (this.compositeListener != null) {
               this.compositeListener.updateState();
            }

            throw new IOException("Filesize is bigger than maximum allowed (file is " + factor + ", limit is " + this.count + ")");
         }

         while (true) {
            int count2;
            if ((count2 = inputStream.read(byteValueArray)) < 0) {
               if (this.compositeListener == null) {
                  return;
               }

               this.compositeListener.updateState();
               return;
            }

            factor += (float)count2;
            if (this.compositeListener != null) {
               this.compositeListener.setCount2((int)(factor / contentLength * 100.0F));
            }

            if (this.count > 0 && factor > (float)this.count) {
               if (this.compositeListener != null) {
                  this.compositeListener.updateState();
               }

               throw new IOException("Filesize was bigger than maximum allowed (got >= " + factor + ", limit was " + this.count + ")");
            }

            if (Thread.interrupted()) {
               GETManager.getLogger().error("INTERRUPTED");
               if (this.compositeListener != null) {
                  this.compositeListener.updateState();
               }
               break;
            }

            dataOutputStream.write(byteValueArray, 0, count2);
         }
      } catch (Throwable throwable) {
         throwable.printStackTrace();
         if (httpURLConnection != null) {
            InputStream errorStream = httpURLConnection.getErrorStream();

            try {
               GETManager.getLogger().error(IOUtils.toString(errorStream));
            } catch (IOException ioException) {
               ioException.printStackTrace();
            }
         }

         if (this.compositeListener != null) {
            this.compositeListener.updateState();
            return;
         }

         return;
      } finally {
         IOUtils.closeQuietly(inputStream);
         IOUtils.closeQuietly(dataOutputStream);
      }
   }

   public GeometryWatcher(CompositeListener compositeListener2, String text, Proxy proxy2, Map map2, File file2, int count2) {
      this.compositeListener = compositeListener2;
      this.labelText = text;
      this.proxy = proxy2;
      this.map = map2;
      this.file = file2;
      this.count = count2;
   }
}
