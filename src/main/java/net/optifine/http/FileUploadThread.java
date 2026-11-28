package net.optifine.http;

import java.util.Map;

public class FileUploadThread extends Thread {
   private String urlString;
   private Map headers;
   private byte[] content;
   private IFileUploadListener listener;

   public IFileUploadListener getListener() {
      return this.listener;
   }

   public String getUrlString() {
      return this.urlString;
   }

   public FileUploadThread(String str, Map map, byte[] bytes, IFileUploadListener listener2) {
      this.urlString = str;
      this.headers = map;
      this.content = bytes;
      this.listener = listener2;
   }

   public byte[] getContent() {
      return this.content;
   }

   @Override
   public void run() {
      try {
         HttpUtils.post(this.urlString, this.headers, this.content);
         this.listener.fileUploadFinished(this.urlString, this.content, (Throwable)null);
      } catch (Exception e) {
         this.listener.fileUploadFinished(this.urlString, this.content, e);
      }
   }
}
