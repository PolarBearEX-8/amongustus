package client.util;

import client.event.IndirectListener;
import client.event.PreUpdateObserver;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HelperDelegate {
   public static void main(String[] textArray) throws Exception {
      TargetSorter targetSorter = new TargetSorter((IndirectListener)null);

      while (true) {
         try {
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            String line = new BufferedReader(inputStreamReader).readLine();
            if (line.length() <= 0) {
               return;
            }

            PreUpdateObserver preUpdateObserver = targetSorter.resolvePreUpdateObserver(line);
            if (preUpdateObserver instanceof AimValidator) {
               float factor = ((AimValidator)preUpdateObserver).getFactor();
               System.out.println("" + factor);
            }

            if (preUpdateObserver instanceof TargetObserver) {
               boolean active = ((TargetObserver)preUpdateObserver).isActive();
               System.out.println("" + active);
            }
         } catch (Exception exception) {
            exception.printStackTrace();
         }
      }
   }
}
