package augustus.modes.movement;

import client.util.AlreadyConnectedManager;
import client.util.HEADManager;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.nio.charset.Charset;
import net.minecraft.item.DiamondRelic;

public class MovementHttppipelinesenderMode extends Thread {
   public AlreadyConnectedManager alreadyConnectedManager = null;
   public static final String labelText = "\r\n";
   public static Charset charset = Charset.forName("ASCII");

   private void updateState() throws IOException {
      String labelText2 = this.alreadyConnectedManager.getLabelText();
      int count = this.alreadyConnectedManager.getCount4();
      Proxy proxy = this.alreadyConnectedManager.getProxy();
      Socket socket = new Socket(proxy);
      socket.connect(new InetSocketAddress(labelText2, count), 5000);
      this.alreadyConnectedManager.applySocket(socket);
   }

   private void applyOutputStream(OutputStream outputStream, String text) throws IOException {
      byte[] bytes = text.getBytes(charset);
      outputStream.write(bytes);
   }

   private void applyHEADManager(HEADManager hEADManager, OutputStream outputStream) throws IOException {
      this.applyOutputStream(outputStream, hEADManager.getLabelText3() + " " + hEADManager.getLabelText7() + " " + hEADManager.getLabelText2() + "\r\n");

      for (String text : (Iterable<String>)(Iterable<?>)hEADManager.getMap().keySet()) {
         String text2 = (String)hEADManager.getMap().get(text);
         this.applyOutputStream(outputStream, text + ": " + text2 + "\r\n");
      }

      this.applyOutputStream(outputStream, "\r\n");
   }

   public MovementHttppipelinesenderMode(AlreadyConnectedManager alreadyConnectedManager2) {
      super("HttpPipelineSender");
      this.alreadyConnectedManager = alreadyConnectedManager2;
   }

   @Override
   public void run() {
      DiamondRelic diamondRelic = null;

      try {
         this.updateState();

         while (!Thread.interrupted()) {
            diamondRelic = this.alreadyConnectedManager.getDiamondRelicAsDiamondRelic();
            HEADManager hEADManager = diamondRelic.getHEADManager();
            OutputStream outputStream = this.alreadyConnectedManager.getOutputStream();
            this.applyHEADManager(hEADManager, outputStream);
            this.alreadyConnectedManager.applyDiamondRelic(diamondRelic);
         }
      } catch (InterruptedException interruptedException) {
         return;
      } catch (Exception exception) {
         this.alreadyConnectedManager.applyDiamondRelic(diamondRelic, exception);
      }
   }
}
