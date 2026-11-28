package net.augustus.commands.impl;



import net.augustus.commands.Command;
import net.augustus.utils.input.ClickPatternRecorder;
public class ClickerCommand extends Command {
   public static final ClickPatternRecorder CLICK_PATTERN_RECORDER = new ClickPatternRecorder();

   public void helpMessage() {
      this.sendChat("§c" + this.getCommand() + "§7 (Start or stop the click recording)");
   }

   public void commandAction(String[] args, String rawMessage) {
      if (args.length > 1) {
         if (args[1].equalsIgnoreCase("start")) {
            if (CLICK_PATTERN_RECORDER.startRecording()) {
               this.sendChat("Started recording... Please hold sneak key while recording");
            } else {
               this.sendChat("You are already recording");
            }

            return;
         }

         if (args[1].equalsIgnoreCase("stop")) {
            if (args.length == 3) {
               String str4 = args[2];
               if (CLICK_PATTERN_RECORDER.stopAndSave(str4)) {
                  this.sendChat("Saved recording successfully to " + args[2] + ".json!");
               } else {
                  this.sendChat("Something went wrong while recording/saving. Please try again!");
               }
            } else {
               this.sendChat("§7" + this.getCommand() + " [§cstop§7] [§2name§7]");
            }

            return;
         }

         if (args[1].equalsIgnoreCase("abort")) {
            CLICK_PATTERN_RECORDER.cancel();
            this.sendChat("Recording successfully canceled.");
            return;
         }
      }

      this.sendChat("§7" + this.getCommand() + " [§cstart/stop/abort§7]");
   }

   public ClickerCommand() {
      super("clicker");
   }

}
