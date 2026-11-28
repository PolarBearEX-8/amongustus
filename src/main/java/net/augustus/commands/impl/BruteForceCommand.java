package net.augustus.commands.impl;

import net.augustus.commands.Command;
import net.augustus.utils.rotation.RotationParamBruteforcer;
import java.io.IOException;

public class BruteForceCommand extends Command {
   public BruteForceCommand(long l) {
      super("bruteforce");
   }

   @Override
   public void helpMessage() {
   }

   @Override
   public void commandAction(String[] args, String rawMessage) {

      try {
         new RotationParamBruteforcer();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
