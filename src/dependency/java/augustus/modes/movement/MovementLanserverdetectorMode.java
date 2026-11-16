package augustus.modes.movement;

import client.util.AtomicIntegerEntry;
import client.util.ListContainer;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;

public class MovementLanserverdetectorMode extends Thread {
   public final ListContainer listContainer;
   public final InetAddress inetAddress;
   public final MulticastSocket multicastSocket;

   public MovementLanserverdetectorMode(ListContainer listContainer2) throws IOException {
      super("LanServerDetector #" + AtomicIntegerEntry.getAtomicInteger().incrementAndGet());
      this.listContainer = listContainer2;
      this.setDaemon(true);
      this.multicastSocket = new MulticastSocket(4445);
      this.inetAddress = InetAddress.getByName("224.0.2.60");
      this.multicastSocket.setSoTimeout(5000);
      this.multicastSocket.joinGroup(this.inetAddress);
   }

   @Override
   public void run() {
      byte[] byteValueArray = new byte[1024];

      while (!this.isInterrupted()) {
         DatagramPacket packet = new DatagramPacket(byteValueArray, byteValueArray.length);

         try {
            this.multicastSocket.receive(packet);
         } catch (SocketTimeoutException socketTimeoutException) {
            continue;
         } catch (IOException ioException) {
            AtomicIntegerEntry.getLogger().error("Couldn't ping server", ioException);
            break;
         }

         String text = new String(packet.getData(), packet.getOffset(), packet.getLength());
         AtomicIntegerEntry.getLogger().debug(packet.getAddress() + ": " + text);
         this.listContainer.applyText(text, packet.getAddress());
      }

      try {
         this.multicastSocket.leaveGroup(this.inetAddress);
      } catch (IOException ioException2) {
      }

      this.multicastSocket.close();
   }
}
