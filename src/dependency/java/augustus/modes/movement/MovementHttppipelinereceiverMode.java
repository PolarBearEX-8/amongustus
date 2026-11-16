package augustus.modes.movement;

import client.util.AlreadyConnectedManager;
import client.util.BuildManager;
import client.util.ComplexStringEntry;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import net.minecraft.item.DiamondRelic;

public class MovementHttppipelinereceiverMode extends Thread {
   public AlreadyConnectedManager alreadyConnectedManager = null;
   public static final Charset charset = Charset.forName("ASCII");
   public static final String labelText = "Content-Length";
   public static final char charCode2 = '\r';
   public static final char charCode = '\n';

   private ComplexStringEntry resolveComplexStringEntry(InputStream inputStream) throws IOException {
      String text = this.resolveText(inputStream);
      String[] textArray = BuildManager.resolveTextArray(text, " ");
      if (textArray.length < 3) {
         throw new IOException("Invalid status line: " + text);
      } else {
         String text2 = textArray[0];
         int count = BuildManager.computeCount(textArray[1], 0);
         text2 = textArray[2];
         LinkedHashMap map = new LinkedHashMap();

         while (true) {
            String text3 = this.resolveText(inputStream);
            if (text3.length() <= 0) {
               byte[] byteValueArray = null;
               String text4 = (String)map.get("Content-Length");
               if (text4 != null) {
                  int count2 = BuildManager.computeCount(text4, -1);
                  if (count2 > 0) {
                     byteValueArray = new byte[count2];
                     this.applyByteArray(byteValueArray, inputStream);
                  }
               } else if (BuildManager.matchesTarget((String)map.get("Transfer-Encoding"), "chunked")) {
                  byteValueArray = this.resolveByteArray(inputStream);
               }

               return new ComplexStringEntry(count, text, map, byteValueArray);
            }

            int count3 = text3.indexOf(":");
            if (count3 > 0) {
               String text5 = text3.substring(0, count3).trim();
               String text6 = text3.substring(count3 + 1).trim();
               map.put(text5, text6);
            }
         }
      }
   }

   private byte[] resolveByteArray(InputStream inputStream) throws IOException {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

      int count;
      do {
         count = Integer.parseInt(BuildManager.resolveTextArray(this.resolveText(inputStream), "; ")[0], 16);
         byte[] byteValueArray = new byte[count];
         this.applyByteArray(byteValueArray, inputStream);
         byteArrayOutputStream.write(byteValueArray);
         this.resolveText(inputStream);
      } while (count != 0);

      return byteArrayOutputStream.toByteArray();
   }

   private String resolveText(InputStream inputStream) throws IOException {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      int count = -1;
      boolean flag = false;

      while (true) {
         int count2 = inputStream.read();
         if (count2 < 0) {
            break;
         }

         byteArrayOutputStream.write(count2);
         if (count == 13 && count2 == 10) {
            flag = true;
            break;
         }

         count = count2;
      }

      byte[] byteValueArray = byteArrayOutputStream.toByteArray();
      String text = new String(byteValueArray, charset);
      if (flag) {
         text = text.substring(0, text.length() - 2);
      }

      return text;
   }

   private void applyByteArray(byte[] byteValueArray, InputStream inputStream) throws IOException {
      int count = 0;

      while (count < byteValueArray.length) {
         int count2 = inputStream.read(byteValueArray, count, byteValueArray.length - count);
         if (count2 < 0) {
            throw new EOFException();
         }

         count += count2;
      }
   }

   public MovementHttppipelinereceiverMode(AlreadyConnectedManager alreadyConnectedManager2) {
      super("HttpPipelineReceiver");
      this.alreadyConnectedManager = alreadyConnectedManager2;
   }

   @Override
   public void run() {
      while (!Thread.interrupted()) {
         DiamondRelic diamondRelic = null;

         try {
            diamondRelic = this.alreadyConnectedManager.getDiamondRelic();
            InputStream inputStream = this.alreadyConnectedManager.getInputStream();
            ComplexStringEntry complexStringEntry = this.resolveComplexStringEntry(inputStream);
            this.alreadyConnectedManager.applyDiamondRelic(diamondRelic, complexStringEntry);
         } catch (InterruptedException interruptedException) {
            return;
         } catch (Exception exception) {
            this.alreadyConnectedManager.applyDiamondRelicByDiamondRelic(diamondRelic, exception);
         }
      }
   }
}
