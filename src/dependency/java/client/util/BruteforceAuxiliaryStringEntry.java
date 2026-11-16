package client.util;

import client.event.RandomizeReadStepListener;
import java.io.IOException;

public class BruteforceAuxiliaryStringEntry extends AuxiliaryStringEntry {
   @Override
   public void updateState() {
   }

   public BruteforceAuxiliaryStringEntry() {
      super("bruteforce");
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {
      try {
         new RandomizeReadStepListener();
      } catch (IOException ioException) {
         ioException.printStackTrace();
      }
   }
}
