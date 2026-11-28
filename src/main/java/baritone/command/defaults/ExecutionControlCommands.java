package baritone.command.defaults;

import baritone.api.command.argument.IArgConsumer;
import baritone.api.command.exception.CommandException;
import baritone.api.command.exception.CommandInvalidStateException;
import baritone.api.process.IBaritoneProcess;
import baritone.api.process.PathingCommand;
import baritone.api.process.PathingCommandType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import baritone.api.IBaritone;
import baritone.api.command.Command;

public class ExecutionControlCommands {
   public Command pauseCommand;
   public Command resumeCommand;
   public Command pausedCommand;
   public Command cancelCommand;

   public ExecutionControlCommands(IBaritone baritone) {
      boolean[] flags = new boolean[]{false};
      baritone.getPathingControlManager().registerProcess(new ExecutionControlCommands.DefaultBaritoneProcess(this, flags, baritone));
      this.pauseCommand = new ExecutionControlCommands.DefaultCommand(this, baritone, new String[]{"pause", "p", "paws"}, flags);
      this.resumeCommand = new ExecutionControlCommands.DefaultCommand2(this, baritone, new String[]{"resume", "r", "unpause", "unpaws"}, flags);
      this.pausedCommand = new ExecutionControlCommands.DefaultCommand3(this, baritone, new String[]{"paused"}, flags);
      this.cancelCommand = new ExecutionControlCommands.DefaultCommand4(this, baritone, new String[]{"cancel", "c", "stop"}, flags);
   }


   public static class DefaultBaritoneProcess implements IBaritoneProcess {
      public final boolean[] paused;
      public final IBaritone baritone;
      public final ExecutionControlCommands parent;

      public DefaultBaritoneProcess(ExecutionControlCommands executionControlCommands, boolean[] flags, IBaritone baritone2) {
         this.parent = executionControlCommands;
         this.paused = flags;
         this.baritone = baritone2;
      }

      @Override
      public double priority() {
         return 0.0;
      }

      @Override
      public PathingCommand onTick(boolean flag, boolean flag2) {
         this.baritone.getInputOverrideHandler().clearAllKeys();
         return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
      }

      @Override
      public String displayName0() {
         return "Pause/Resume Commands";
      }

      @Override
      public boolean isTemporary() {
         return true;
      }

      @Override
      public boolean isActive() {
         return this.paused[0];
      }

      @Override
      public void onLostControl() {
      }
   }

   public static class DefaultCommand extends Command {
      public final boolean[] paused;
      public final ExecutionControlCommands parent;

      @Override
      public List getLongDesc() {
         return Arrays.asList(
            "The pause command tells Baritone to temporarily stop whatever it's doing.",
            "",
            "This can be used to pause pathing, building, following, whatever. A single use of the resume command will start it right back up again!",
            "",
            "Usage:",
            "> pause"
         );
      }

      @Override
      public String getShortDesc() {
         return "Pauses Baritone until you use resume";
      }

      @Override
      public void execute(String str, IArgConsumer argConsumer) throws CommandException {
         argConsumer.requireMax(0);
         if (this.paused[0]) {
            throw new CommandInvalidStateException("Already paused");
         } else {
            this.paused[0] = true;
            this.logDirect2("Paused");
         }
      }

      @Override
      public Stream tabComplete(String str, IArgConsumer argConsumer) {
         return Stream.empty();
      }

      public DefaultCommand(ExecutionControlCommands executionControlCommands, IBaritone baritone2, String[] strings, boolean[] flags) {
         super(baritone2, strings);
         this.parent = executionControlCommands;
         this.paused = flags;
      }
   }

   public static class DefaultCommand2 extends Command {
      public final boolean[] paused;
      public final ExecutionControlCommands parent;

      @Override
      public String getShortDesc() {
         return "Resumes Baritone after a pause";
      }

      @Override
      public Stream tabComplete(String str, IArgConsumer argConsumer) {
         return Stream.empty();
      }

      @Override
      public List getLongDesc() {
         return Arrays.asList("The resume command tells Baritone to resume whatever it was doing when you last used pause.", "", "Usage:", "> resume");
      }

      public DefaultCommand2(ExecutionControlCommands executionControlCommands, IBaritone baritone2, String[] strings, boolean[] flags) {
         super(baritone2, strings);
         this.parent = executionControlCommands;
         this.paused = flags;
      }

      @Override
      public void execute(String str, IArgConsumer argConsumer) throws CommandException {
         argConsumer.requireMax(0);
         this.baritone.getBuilderProcess().resume();
         if (!this.paused[0]) {
            throw new CommandInvalidStateException("Not paused");
         } else {
            this.paused[0] = false;
            this.logDirect2("Resumed");
         }
      }
   }

   public static class DefaultCommand3 extends Command {
      public final boolean[] paused;
      public final ExecutionControlCommands parent;

      @Override
      public String getShortDesc() {
         return "Tells you if Baritone is paused";
      }

      public DefaultCommand3(ExecutionControlCommands executionControlCommands, IBaritone baritone2, String[] strings, boolean[] flags) {
         super(baritone2, strings);
         this.parent = executionControlCommands;
         this.paused = flags;
      }

      @Override
      public void execute(String str, IArgConsumer argConsumer) throws CommandException {
         argConsumer.requireMax(0);
         this.logDirect2(String.format("Baritone is %spaused", this.paused[0] ? "" : "not "));
      }

      @Override
      public List getLongDesc() {
         return Arrays.asList("The paused command tells you if Baritone is currently paused by use of the pause command.", "", "Usage:", "> paused");
      }

      @Override
      public Stream tabComplete(String str, IArgConsumer argConsumer) {
         return Stream.empty();
      }
   }

   public static class DefaultCommand4 extends Command {
      public final boolean[] paused;
      public final ExecutionControlCommands parent;

      @Override
      public String getShortDesc() {
         return "Cancel what Baritone is currently doing";
      }

      public DefaultCommand4(ExecutionControlCommands executionControlCommands, IBaritone baritone2, String[] strings, boolean[] flags) {
         super(baritone2, strings);
         this.parent = executionControlCommands;
         this.paused = flags;
      }

      @Override
      public Stream tabComplete(String str, IArgConsumer argConsumer) {
         return Stream.empty();
      }

      @Override
      public void execute(String str, IArgConsumer argConsumer) throws CommandException {
         argConsumer.requireMax(0);
         if (this.paused[0]) {
            this.paused[0] = false;
         }

         this.baritone.getPathingBehavior().cancelEverything();
         this.logDirect2("ok canceled");
      }

      @Override
      public List getLongDesc() {
         return Arrays.asList("The cancel command tells Baritone to stop whatever it's currently doing.", "", "Usage:", "> cancel");
      }
   }
}
