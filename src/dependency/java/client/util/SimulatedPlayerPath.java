package client.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SimulatedPlayerPath {
   public final ArrayList arrayList;
   public final HashMap hashMap = new HashMap();

   public void applyText(String text) {
      Scanner scanner = new Scanner(SimulatedPlayerPath.class.getResourceAsStream("/assets/rotationpatterns/" + text));
      ArrayList list = new ArrayList();
      ArrayList list2 = new ArrayList();

      while (scanner.hasNextLine()) {
         String text2 = scanner.nextLine();
         if (text2.isEmpty()) {
            break;
         }

         list.add(Double.parseDouble(text2));
      }

      while (scanner.hasNextLine()) {
         String text3 = scanner.nextLine();
         if (text3.isEmpty()) {
            break;
         }

         list2.add(Double.parseDouble(text3));
      }

      this.hashMap.put(text, new SimulationDeltaPosition(list, list2, text));
      this.arrayList.add(text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase());
      scanner.close();
   }

   public void updateState() {
      String text = "heimgesuchtes";
      this.applyText(text);
      String text2 = "legitevening";
      this.applyText(text2);
      String text3 = "noisetest2.2dr";
      this.applyText(text3);
   }

   public ArrayList getArrayList() {
      return this.arrayList;
   }

   public SimulationDeltaPosition resolveSimulationDeltaPosition(String text) {
      return (SimulationDeltaPosition)this.hashMap.get(text.toLowerCase());
   }

   public SimulatedPlayerPath() {
      this.arrayList = new ArrayList();
      this.updateState();
   }
}
