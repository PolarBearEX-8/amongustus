package net.optifine.http;

import net.minecraft.client.Minecraft;

public class FileDownloadThread extends Thread {
   private String urlString = null;
   private IFileDownloadListener listener = null;

   @Override
   public void run() {
      try {
         byte[] bytes = HttpPipeline.get(this.urlString, Minecraft.getMinecraft().getProxy());
         this.listener.fileDownloadFinished(this.urlString, bytes, (Throwable)null);
      } catch (Exception e) {
         this.listener.fileDownloadFinished(this.urlString, (byte[])null, e);
      }
   }

   public String getUrlString() {
      return this.urlString;
   }

   public FileDownloadThread(String str, IFileDownloadListener listener2) {
      this.urlString = str;
      this.listener = listener2;
   }

   public IFileDownloadListener getListener() {
      return this.listener;
   }
}
