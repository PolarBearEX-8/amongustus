package client.util;

import augustus.modes.movement.MovementHttppipelinereceiverMode;
import augustus.modes.movement.MovementHttppipelinesenderMode;
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
import net.minecraft.item.DiamondRelic;

public class AlreadyConnectedManager {
   public String labelText = null;
   public int count4 = 0;
   public Proxy proxy = Proxy.NO_PROXY;
   public List list = new LinkedList();
   public List list2 = new LinkedList();
   public Socket socket = null;
   public InputStream inputStream = null;
   public OutputStream outputStream = null;
   public MovementHttppipelinesenderMode movementHttppipelinesenderMode = null;
   public MovementHttppipelinereceiverMode movementHttppipelinereceiverMode = null;
   public int count2 = 0;
   public boolean enabled = false;
   public long timestamp = 5000L;
   public int count5 = 1000;
   public long timestamp2 = System.currentTimeMillis();
   public boolean enabled2 = false;
   public static final String labelText2 = "\n";
   public static final int count3 = 5000;
   public static final int count = 5000;
   public static final Pattern pattern = Pattern.compile("^[a-zA-Z]+://.*");

   public synchronized boolean matchesDiamondRelic(DiamondRelic diamondRelic) {
      if (this.isActive()) {
         return false;
      } else {
         this.applyDiamondRelic(diamondRelic, this.list);
         this.applyDiamondRelic(diamondRelic, this.list2);
         this.count2++;
         return true;
      }
   }

   private String[] resolveTextArray(String text, char character) {
      int count6 = text.indexOf(character);
      if (count6 < 0) {
         return new String[]{text};
      } else {
         String text2 = text.substring(0, count6);
         String text3 = text.substring(count6 + 1);
         return new String[]{text2, text3};
      }
   }

   public synchronized DiamondRelic getDiamondRelicAsDiamondRelic() throws InterruptedException, IOException {
      if (this.list2.size() <= 0 && this.outputStream != null) {
         this.outputStream.flush();
      }

      return this.resolveDiamondRelic(this.list2, true);
   }

   public String getLabelText() {
      return this.labelText;
   }

   public synchronized void applySocket(Socket socket2) throws IOException {
      if (!this.enabled2) {
         if (this.socket != null) {
            throw new IllegalArgumentException("Already connected");
         }

         this.socket = socket2;
         this.socket.setTcpNoDelay(true);
         this.inputStream = this.socket.getInputStream();
         this.outputStream = new BufferedOutputStream(this.socket.getOutputStream());
         this.currentTimeMillis();
         this.notifyAll();
      }
   }

   private void updateState() {
      if (this.socket != null) {
         long timestamp3 = this.timestamp;
         if (this.list.size() > 0) {
            timestamp3 = 5000L;
         }

         if (System.currentTimeMillis() > this.timestamp2 + timestamp3) {
            this.applyException(new InterruptedException("Timeout " + timestamp3));
         }
      }
   }

   public synchronized void applyDiamondRelic(DiamondRelic diamondRelic, Exception exception) {
      this.applyException(exception);
   }

   private void applyDiamondRelic(DiamondRelic diamondRelic, List list3) {
      list3.add(diamondRelic);
      this.notifyAll();
   }

   private String resolveText(String text, HEADManager hEADManager) {
      if (pattern.matcher(text).matches()) {
         return text;
      } else if (text.startsWith("//")) {
         return "http:" + text;
      } else {
         String labelText3 = hEADManager.getLabelText9();
         if (hEADManager.getCount2() != 80) {
            labelText3 = labelText3 + ":" + hEADManager.getCount2();
         }

         if (text.startsWith("/")) {
            return "http://" + labelText3 + text;
         } else {
            String labelText4 = hEADManager.getLabelText7();
            int count6 = labelText4.lastIndexOf("/");
            return count6 >= 0 ? "http://" + labelText3 + labelText4.substring(0, count6 + 1) + text : "http://" + labelText3 + "/" + text;
         }
      }
   }

   private void currentTimeMillis() {
      this.timestamp2 = System.currentTimeMillis();
   }

   private DiamondRelic resolveDiamondRelic(List list3, boolean flag) throws InterruptedException {
      while (list3.size() <= 0) {
         this.updateState();
         this.wait(1000L);
      }

      this.currentTimeMillis();
      return flag ? (DiamondRelic)list3.remove(0) : (DiamondRelic)list3.get(0);
   }

   public int getCount2() {
      return this.count2;
   }

   public synchronized DiamondRelic getDiamondRelic() throws InterruptedException {
      return this.resolveDiamondRelic(this.list, false);
   }

   public synchronized OutputStream getOutputStream() throws IOException, InterruptedException {
      while (this.outputStream == null) {
         this.updateState();
         this.wait(1000L);
      }

      return this.outputStream;
   }

   public synchronized boolean isActive() {
      return this.enabled2 ? true : this.count2 >= this.count5;
   }

   private void applyExceptionByException(Exception exception) {
      if (this.list.size() > 0) {
         if (!this.enabled) {
            DiamondRelic diamondRelic = (DiamondRelic)this.list.remove(0);
            diamondRelic.getPreRenderConsumer().applyHEADManager(diamondRelic.getHEADManager(), exception);
            diamondRelic.setEnabled(true);
         }

         while (this.list.size() > 0) {
            AuxiliaryResponseManager.applyDiamondRelic((DiamondRelic)this.list.remove(0));
         }
      }
   }

   public synchronized InputStream getInputStream() throws IOException, InterruptedException {
      while (this.inputStream == null) {
         this.updateState();
         this.wait(1000L);
      }

      return this.inputStream;
   }

   public synchronized void applyDiamondRelic(DiamondRelic diamondRelic, ComplexStringEntry complexStringEntry) {
      if (!this.enabled2) {
         this.enabled = true;
         this.currentTimeMillis();
         if (this.list.size() <= 0 || this.list.get(0) != diamondRelic) {
            throw new IllegalArgumentException("Response out of order: " + diamondRelic);
         }

         this.list.remove(0);
         diamondRelic.setEnabled(true);
         String text = complexStringEntry.get("Location");
         if (complexStringEntry.getCount() / 100 == 3 && text != null && diamondRelic.getHEADManager().getCount() < 5) {
            try {
               HEADManager hEADManager = AuxiliaryResponseManager.resolveHEADManager(this.resolveText(text, diamondRelic.getHEADManager()), diamondRelic.getHEADManager().getProxy());
               hEADManager.setCount(diamondRelic.getHEADManager().getCount() + 1);
               AuxiliaryResponseManager.applyDiamondRelic(new DiamondRelic(hEADManager, diamondRelic.getPreRenderConsumer()));
            } catch (IOException ioException) {
               diamondRelic.getPreRenderConsumer().applyHEADManager(diamondRelic.getHEADManager(), ioException);
            }
         } else {
            diamondRelic.getPreRenderConsumer().applyHEADManager(diamondRelic.getHEADManager(), complexStringEntry);
         }

         this.applyComplexStringEntry(complexStringEntry);
      }
   }

   public int getCount4() {
      return this.count4;
   }

   private void applyComplexStringEntry(ComplexStringEntry complexStringEntry) {
      String text = complexStringEntry.get("Connection");
      if (text != null && !text.toLowerCase().equals("keep-alive")) {
         this.applyException(new EOFException("Connection not keep-alive"));
      }

      String text2 = complexStringEntry.get("Keep-Alive");
      if (text2 != null) {
         String[] textArray = BuildManager.resolveTextArray(text2, ",;");

         for (int index = 0; index < textArray.length; index++) {
            String text3 = textArray[index];
            String[] textArray2 = this.resolveTextArray(text3, '=');
            if (textArray2.length >= 2) {
               if (textArray2[0].equals("timeout")) {
                  int count6 = BuildManager.computeCount(textArray2[1], -1);
                  if (count6 > 0) {
                     this.timestamp = (long)(count6 * 1000);
                  }
               }

               if (textArray2[0].equals("max")) {
                  int count7 = BuildManager.computeCount(textArray2[1], -1);
                  if (count7 > 0) {
                     this.count5 = count7;
                  }
               }
            }
         }
      }
   }

   public synchronized void applyDiamondRelic(DiamondRelic diamondRelic) {
      if (!this.enabled2) {
         this.currentTimeMillis();
      }
   }

   public synchronized void applyDiamondRelicByDiamondRelic(DiamondRelic diamondRelic, Exception exception) {
      this.applyException(exception);
   }

   public synchronized boolean size() {
      return this.list.size() > 0;
   }

   public Proxy getProxy() {
      return this.proxy;
   }

   private synchronized void applyException(Exception exception) {
      if (!this.enabled2) {
         this.enabled2 = true;
         this.applyExceptionByException(exception);
         if (this.movementHttppipelinesenderMode != null) {
            this.movementHttppipelinesenderMode.interrupt();
         }

         if (this.movementHttppipelinereceiverMode != null) {
            this.movementHttppipelinereceiverMode.interrupt();
         }

         try {
            if (this.socket != null) {
               this.socket.close();
            }
         } catch (IOException ioException) {
         }

         this.socket = null;
         this.inputStream = null;
         this.outputStream = null;
      }
   }

   public AlreadyConnectedManager(String text, int count6) {
      this(text, count6, Proxy.NO_PROXY);
   }

   public AlreadyConnectedManager(String text, int count6, Proxy proxy2) {
      this.labelText = text;
      this.count4 = count6;
      this.proxy = proxy2;
      this.movementHttppipelinesenderMode = new MovementHttppipelinesenderMode(this);
      this.movementHttppipelinesenderMode.start();
      this.movementHttppipelinereceiverMode = new MovementHttppipelinereceiverMode(this);
      this.movementHttppipelinereceiverMode.start();
   }
}
