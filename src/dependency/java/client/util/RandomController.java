package client.util;

import java.util.Random;

public class RandomController {
   public static final RandomController randomController = new RandomController();
   public Random random = new Random();
   public String[] stringArray = "the elder scrolls klaatu berata niktu xyzzy bless curse light darkness fire air earth water hot dry cold wet ignite snuff embiggen twist shorten stretch fiddle destroy imbue galvanize enchant free limited range of towards inside sphere cube self other ball mental physical grow shrink demon elemental spirit animal creature beast humanoid undead fresh stale "
      .split(" ");

   public String getText() {
      int count = this.random.nextInt(2) + 3;
      String text = "";

      for (int index = 0; index < count; index++) {
         if (index > 0) {
            text = text + " ";
         }

         text = text + this.stringArray[this.random.nextInt(this.stringArray.length)];
      }

      return text;
   }

   public void setSeed(long seed) {
      this.random.setSeed(seed);
   }

   public static RandomController getRandomController() {
      return randomController;
   }
}
