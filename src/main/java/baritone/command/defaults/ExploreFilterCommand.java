package baritone.command.defaults;

import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.datatypes.RelativeFile;
import baritone.api.command.exception.CommandException;
import baritone.api.command.exception.CommandInvalidStateException;
import baritone.api.command.exception.CommandInvalidTypeException;
import baritone.api.command.argument.IArgConsumer;
import com.google.gson.JsonSyntaxException;
import java.io.File;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ExploreFilterCommand extends Command {

   @Override
   public List getLongDesc() {
      return Arrays.asList(
         "Apply an explore filter before using explore, which tells the explore process which chunks have been explored/not explored.",
         "",
         "The JSON file will follow this format: [{\"x\":0,\"z\":0},...]",
         "",
         "If 'invert' is specified, the chunks listed will be considered NOT explored, rather than explored.",
         "",
         "Usage:",
         "> explorefilter <path> [invert] - Load the JSON file referenced by the specified path. If invert is specified, it must be the literal word 'invert'."
      );
   }

   @Override
   public String getShortDesc() {
      return "Explore chunks from a json";
   }

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) throws CommandException {
      return argConsumer.hasExactlyOne() ? RelativeFile.tabComplete(argConsumer, RelativeFile.gameDir(this.ctx.getMc())) : Stream.empty();
   }

   public ExploreFilterCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"explorefilter"});
   }

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      argConsumer.requireMax(2);
      File datatypePost = (File)argConsumer.getDatatypePost(RelativeFile.INSTANCE, this.ctx.getMc().mcDataDir.getAbsoluteFile().getParentFile());
      boolean flag = false;
      if (argConsumer.hasAny()) {
         if (!argConsumer.getString().equalsIgnoreCase("invert")) {
            throw new CommandInvalidTypeException(argConsumer.consumed(), "either \"invert\" or nothing");
         }

         flag = true;
      }

      try {
         this.baritone.getExploreProcess().applyJsonFilter(datatypePost.toPath().toAbsolutePath(), flag);
      } catch (NoSuchFileException e) {
         throw new CommandInvalidStateException("File not found");
      } catch (JsonSyntaxException e2) {
         throw new CommandInvalidStateException("Invalid JSON syntax");
      } catch (Exception e3) {
         throw new IllegalStateException(e3);
      }

      this.logDirect2(String.format("Explore filter applied. Inverted: %s", Boolean.toString(flag)));
   }
}
