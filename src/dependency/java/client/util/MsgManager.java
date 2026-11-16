package client.util;

import client.Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MsgManager {
   public static PrintWriter printWriter;
   public static BufferedReader bufferedReader;
   public static boolean enabled3 = true;
   public static Socket socket;
   public static boolean enabled2 = false;

   private static void applyText3(String text) {
      while (enabled3) {
         if (isEnabled2()) {
            try {
               socket = new Socket("162.212.154.28", 18777);
               bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
               printWriter = new PrintWriter(socket.getOutputStream(), true);
               printWriter.println(text);
               printWriter.println(Client.getClient().getString7());

               String text2;
               while (enabled3 && (text2 = bufferedReader.readLine()) != null) {
                  if (text2 != null && !text2.equals("msg")) {
                     String message = text2;
                     ClientManager.applyRunnable2(() -> MsgManager.applyText2(message));
                  }
               }
            } catch (IOException ioException) {
            } finally {
               updateState2();
            }
         }

         try {
            Thread.sleep(1000L);
         } catch (InterruptedException interruptedException) {
         }
      }
   }

   private static void applyText(String text) {
      String[] textArray = text.split(":", 3);
      if (textArray.length > 2) {
         String text2 = textArray[2].trim();
         if (text2.contains("Userinfo:") || text2.contains("Aqua") && text2.contains("ESound")) {
            return;
         }

         String text3 = "AquaMessenger";
         RayTraceUtil.resolveText(text3, text2);
      }
   }

   public static void start(String text) {
      new Thread(null, () -> MsgManager.applyText3(text), "IRC", 0L).start();
   }

   private static synchronized void setEnabled2(boolean enabled) {
      enabled2 = enabled;
   }

   private static void updateState2() {
      try {
         if (socket != null) {
            socket.close();
            socket = null;
         }

         if (bufferedReader != null) {
            bufferedReader.close();
            bufferedReader = null;
         }

         if (printWriter != null) {
            printWriter.close();
            printWriter = null;
         }
      } catch (IOException ioException) {
         ioException.printStackTrace();
      }
   }

   public static void updateState3() {
      enabled3 = false;
   }

   private static void applyText2(String text) {
      applyText(text);
   }

   public static void setActive(boolean active) {
      setEnabled2(active);
      updateState2();
   }

   private static synchronized boolean isEnabled2() {
      return enabled2;
   }

   private static void updateState() {
      updateState3();
   }

   public static void applyTextByText(String text) {
      if (printWriter != null) {
         String text2 = Client.getClient().getString4() + " [" + Client.getClient().getString7() + "] : " + text;
         printWriter.println(text2);
         RayTraceUtil.resolveText("AquaMessenger", text2);
      } else {
         String text3 = "You are not connected to the AquaIRC (enable AquaMessenger)";
         RayTraceUtil.resolveText("AquaMessenger", text3);
      }
   }

   static {
      Runtime.getRuntime().addShutdownHook(new Thread(MsgManager::updateState));
   }
}
