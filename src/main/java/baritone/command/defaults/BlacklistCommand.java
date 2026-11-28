package baritone.command.defaults;

import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.exception.CommandException;
import baritone.api.command.exception.CommandInvalidStateException;
import baritone.api.process.IGetToBlockProcess;
import baritone.api.command.argument.IArgConsumer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class BlacklistCommand extends Command {

   @Override
   public List getLongDesc() {
      return Arrays.asList(
         "While going to a block this command blacklists the closest block so that Baritone won't attempt to get to it.", "", "Usage:", "> blacklist"
      );
   }

   public BlacklistCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"blacklist"});
   }

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) {
      return Stream.empty();
   }

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      argConsumer.requireMax(0);
      IGetToBlockProcess getToBlockProcess = this.baritone.getGetToBlockProcess();
      if (!getToBlockProcess.isActive()) {
         throw new CommandInvalidStateException("GetToBlockProcess is not currently active");
      } else if (getToBlockProcess.blacklistClosest()) {
         this.logDirect2("Blacklisted closest instances");
      } else {
         throw new CommandInvalidStateException("No known locations, unable to blacklist");
      }
   }

   @Override
   public String getShortDesc() {
      return "Blacklist closest block";
   }
}
