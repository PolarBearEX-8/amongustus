package baritone.api.command.datatypes;

import baritone.api.command.exception.CommandException;
import baritone.api.command.helpers.TabCompleteHelper;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;

public enum BlockById implements IDatatypeFor {
   INSTANCE;

private static Pattern PATTERN = Pattern.compile("(?:[a-z0-9_.-]+:)?[a-z0-9/_.-]*");
@Override
   public Object get(IDatatypeContext datatypeContext) throws CommandException {
      return this.get2(datatypeContext);
   }

   @Override
   public Stream tabComplete(IDatatypeContext datatypeContext) throws CommandException {
      String string = datatypeContext.getConsumer().getString();
      return !PATTERN.matcher(string).matches()
         ? Stream.empty()
         : new TabCompleteHelper().append(Block.blockRegistry.getKeys().stream().map(Object::toString)).filterPrefixNamespaced(string).sortAlphabetically().getStream();
   }

   public Block get2(IDatatypeContext datatypeContext) throws CommandException {
      ResourceLocation location = new ResourceLocation(datatypeContext.getConsumer().getString());
      Block block;
      if ((block = (Block)Block.blockRegistry.getObject(location)) == Blocks.air) {
         throw new IllegalArgumentException("no block found by that id");
      } else {
         return block;
      }
   }
}
