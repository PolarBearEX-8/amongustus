package net.optifine.http;

import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Proxy;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import net.optifine.config.Config;

public class HttpPipelineConnection {
   private String host = null;
   private int port = 0;
   private Proxy proxy = Proxy.NO_PROXY;
   private List listRequests = new LinkedList();
   private List listRequestsSend = new LinkedList();
   private Socket socket = null;
   private InputStream inputStream = null;
   private OutputStream outputStream = null;
   private HttpPipelineSender httpPipelineSender = null;
   private HttpPipelineReceiver httpPipelineReceiver = null;
   private int countRequests = 0;
   private boolean responseReceived = false;
   private long keepaliveTimeoutMs = 5000L;
   private int keepaliveMaxCount = 1000;
   private long timeLastActivityMs = System.currentTimeMillis();
   private boolean terminated = false;
   private static final String LF = "\n";
   public static final int TIMEOUT_CONNECT_MS = 5000;
   public static final int TIMEOUT_READ_MS = 5000;
   private static final Pattern patternFullUrl = Pattern.compile("^[a-zA-Z]+://.*");

   public int getCountRequests() {
      return this.countRequests;
   }

   public int getPort() {
      return this.port;
   }

   private synchronized void terminate(Exception e) {
      if (!this.terminated) {
         this.terminated = true;
         this.terminateRequests(e);
         if (this.httpPipelineSender != null) {
            this.httpPipelineSender.interrupt();
         }

         if (this.httpPipelineReceiver != null) {
            this.httpPipelineReceiver.interrupt();
         }

         try {
            if (this.socket != null) {
               this.socket.close();
            }
         } catch (IOException e2) {
         }

         this.socket = null;
         this.inputStream = null;
         this.outputStream = null;
      }
   }

   private HttpPipelineRequest getNextRequest(List list, boolean flag) throws InterruptedException {
      while (list.size() <= 0) {
         this.checkTimeout();
         this.wait(1000L);
      }

      this.onActivity();
      return flag ? (HttpPipelineRequest)list.remove(0) : (HttpPipelineRequest)list.get(0);
   }

   public synchronized void setSocket(Socket socket2) throws IOException {
      if (!this.terminated) {
         if (this.socket != null) {
            throw new IllegalArgumentException("Already connected");
         }

         this.socket = socket2;
         this.socket.setTcpNoDelay(true);
         this.inputStream = this.socket.getInputStream();
         this.outputStream = new BufferedOutputStream(this.socket.getOutputStream());
         this.onActivity();
         this.notifyAll();
      }
   }

   private void addRequest(HttpPipelineRequest httpPipelineRequest, List list) {
      list.add(httpPipelineRequest);
      this.notifyAll();
   }

   public synchronized void onRequestSent(HttpPipelineRequest httpPipelineRequest) {
      if (!this.terminated) {
         this.onActivity();
      }
   }

   public Proxy getProxy() {
      return this.proxy;
   }

   public HttpPipelineConnection(String str, int n) {
      this(str, n, Proxy.NO_PROXY);
   }

   public synchronized boolean isClosed() {
      return this.terminated ? true : this.countRequests >= this.keepaliveMaxCount;
   }

   private void onActivity() {
      this.timeLastActivityMs = System.currentTimeMillis();
   }

   private String[] split(String str, char ch) {
      int n = str.indexOf(ch);
      if (n < 0) {
         return new String[]{str};
      } else {
         String str2 = str.substring(0, n);
         String str3 = str.substring(n + 1);
         return new String[]{str2, str3};
      }
   }

   public synchronized boolean hasActiveRequests() {
      return this.listRequests.size() > 0;
   }

   public HttpPipelineConnection(String str, int n, Proxy proxy2) {
      this.host = str;
      this.port = n;
      this.proxy = proxy2;
      this.httpPipelineSender = new HttpPipelineSender(this);
      this.httpPipelineSender.start();
      this.httpPipelineReceiver = new HttpPipelineReceiver(this);
      this.httpPipelineReceiver.start();
   }

   public synchronized void onExceptionReceive(HttpPipelineRequest httpPipelineRequest, Exception e) {
      this.terminate(e);
   }

   private void checkResponseHeader(HttpResponse httpResponse) {
      String header = httpResponse.getHeader("Connection");
      if (header != null && !header.toLowerCase().equals("keep-alive")) {
         this.terminate(new EOFException("Connection not keep-alive"));
      }

      String header2 = httpResponse.getHeader("Keep-Alive");
      if (header2 != null) {
         String[] strings = Config.tokenize(header2, ",;");

         for (int i = 0; i < strings.length; i++) {
            String[] strings2 = this.split(strings[i], '=');
            if (strings2.length >= 2) {
               if (strings2[0].equals("timeout")) {
                  int n = Config.parseInt(strings2[1], -1);
                  if (n > 0) {
                     this.keepaliveTimeoutMs = (long)(n * 1000);
                  }
               }

               if (strings2[0].equals("max")) {
                  int n2 = Config.parseInt(strings2[1], -1);
                  if (n2 > 0) {
                     this.keepaliveMaxCount = n2;
                  }
               }
            }
         }
      }
   }

   public String getHost() {
      return this.host;
   }

   private void checkTimeout() {
      if (this.socket != null) {
         long keepaliveTimeoutMs2 = this.keepaliveTimeoutMs;
         if (this.listRequests.size() > 0) {
            keepaliveTimeoutMs2 = 5000L;
         }

         if (System.currentTimeMillis() > this.timeLastActivityMs + keepaliveTimeoutMs2) {
            this.terminate(new InterruptedException("Timeout " + keepaliveTimeoutMs2));
         }
      }
   }

   public synchronized boolean addRequest(HttpPipelineRequest httpPipelineRequest) {
      if (this.isClosed()) {
         return false;
      } else {
         this.addRequest(httpPipelineRequest, this.listRequests);
         this.addRequest(httpPipelineRequest, this.listRequestsSend);
         this.countRequests++;
         return true;
      }
   }

   public synchronized InputStream getInputStream() throws IOException, InterruptedException {
      while (this.inputStream == null) {
         this.checkTimeout();
         this.wait(1000L);
      }

      return this.inputStream;
   }

   public synchronized OutputStream getOutputStream() throws IOException, InterruptedException {
      while (this.outputStream == null) {
         this.checkTimeout();
         this.wait(1000L);
      }

      return this.outputStream;
   }

   private String normalizeUrl(String str, HttpRequest httpRequest) {
      if (patternFullUrl.matcher(str).matches()) {
         return str;
      } else if (str.startsWith("//")) {
         return "http:" + str;
      } else {
         String host2 = httpRequest.getHost();
         if (httpRequest.getPort() != 80) {
            host2 = host2 + ":" + httpRequest.getPort();
         }

         if (str.startsWith("/")) {
            return "http://" + host2 + str;
         } else {
            String file = httpRequest.getFile();
            int n = file.lastIndexOf("/");
            return n >= 0 ? "http://" + host2 + file.substring(0, n + 1) + str : "http://" + host2 + "/" + str;
         }
      }
   }

   public synchronized void onExceptionSend(HttpPipelineRequest httpPipelineRequest, Exception e) {
      this.terminate(e);
   }

   public synchronized void onResponseReceived(HttpPipelineRequest httpPipelineRequest, HttpResponse httpResponse) {
      if (!this.terminated) {
         this.responseReceived = true;
         this.onActivity();
         if (this.listRequests.size() <= 0 || this.listRequests.get(0) != httpPipelineRequest) {
            throw new IllegalArgumentException("Response out of order: " + httpPipelineRequest);
         }

         this.listRequests.remove(0);
         httpPipelineRequest.setClosed(true);
         String header = httpResponse.getHeader("Location");
         if (httpResponse.getStatus() / 100 == 3 && header != null && httpPipelineRequest.getHttpRequest().getRedirects() < 5) {
            try {
               header = this.normalizeUrl(header, httpPipelineRequest.getHttpRequest());
               HttpRequest httpRequest = HttpPipeline.makeRequest(header, httpPipelineRequest.getHttpRequest().getProxy());
               httpRequest.setRedirects(httpPipelineRequest.getHttpRequest().getRedirects() + 1);
               HttpPipeline.addRequest(new HttpPipelineRequest(httpRequest, httpPipelineRequest.getHttpListener()));
            } catch (IOException e) {
               httpPipelineRequest.getHttpListener().failed(httpPipelineRequest.getHttpRequest(), e);
            }
         } else {
            httpPipelineRequest.getHttpListener().finished(httpPipelineRequest.getHttpRequest(), httpResponse);
         }

         this.checkResponseHeader(httpResponse);
      }
   }

   private void terminateRequests(Exception e) {
      if (this.listRequests.size() > 0) {
         if (!this.responseReceived) {
            HttpPipelineRequest httpPipelineRequest = (HttpPipelineRequest)this.listRequests.remove(0);
            httpPipelineRequest.getHttpListener().failed(httpPipelineRequest.getHttpRequest(), e);
            httpPipelineRequest.setClosed(true);
         }

         while (this.listRequests.size() > 0) {
            HttpPipeline.addRequest((HttpPipelineRequest)this.listRequests.remove(0));
         }
      }
   }

   public synchronized HttpPipelineRequest getNextRequestSend() throws InterruptedException, IOException {
      if (this.listRequestsSend.size() <= 0 && this.outputStream != null) {
         this.outputStream.flush();
      }

      return this.getNextRequest(this.listRequestsSend, true);
   }

   public synchronized HttpPipelineRequest getNextRequestReceive() throws InterruptedException {
      return this.getNextRequest(this.listRequests, false);
   }
}
