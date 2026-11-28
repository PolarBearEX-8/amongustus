package net.augustus.utils.rotation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class RotationPatternManager {
   private final ArrayList patternDisplayNames;
   private final HashMap patternsByName = new HashMap();

   public RotationPatternManager(short s, int n, int n2) {
      this.patternDisplayNames = new ArrayList();
   }

   public void loadDefaultPatterns() {
      this.loadPattern("heimgesuchtes");
      this.loadPattern("legitevening");
      this.loadPattern("noisetest2.2dr");
   }

   public void loadPattern(String str) {
      Scanner scanner = new Scanner(RotationPatternManager.class.getResourceAsStream("/assets/rotationpatterns/" + str));
      ArrayList list = new ArrayList();
      ArrayList list2 = new ArrayList();

      while (scanner.hasNextLine()) {
         String str2 = scanner.nextLine();
         if (str2.isEmpty()) {
            break;
         }

         list.add(Double.parseDouble(str2));
      }

      while (scanner.hasNextLine()) {
         String str3 = scanner.nextLine();
         if (str3.isEmpty()) {
            break;
         }

         list2.add(Double.parseDouble(str3));
      }

      this.patternsByName.put(str, new RotationPatternData(list, list2, str));
      this.patternDisplayNames.add(str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase());
      scanner.close();
   }

   public RotationPatternData getPattern(String str) {
      return (RotationPatternData)this.patternsByName.get(str.toLowerCase());
   }

   public ArrayList getPatternDisplayNames() {
      return this.patternDisplayNames;
   }
}
