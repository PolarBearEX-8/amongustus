package baritone.command.defaults;

import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.datatypes.BlockById;
import baritone.api.command.exception.CommandException;
import baritone.api.command.helpers.TabCompleteHelper;
import baritone.api.utils.BetterBlockPos;
import baritone.cache.CachedChunk;
import baritone.api.command.argument.IArgConsumer;
import baritone.api.command.IBaritoneChatControl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;

public class FindCommand extends Command {

   public FindCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"find"});
   }

   @Override
   public String getShortDesc() {
      return "Find positions of a certain block";
   }

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      argConsumer.requireMin(1);
      ArrayList list = new ArrayList();

      while (argConsumer.hasAny()) {
         list.add(argConsumer.getDatatypeFor(BlockById.INSTANCE));
      }

      BetterBlockPos betterBlockPos = this.ctx.playerFeet();
      IChatComponent[] components = (IChatComponent[])list.stream()
         .flatMap(
            obj -> this.ctx
                  .worldData()
                  .getCachedWorld()
                  .getLocationsOf(((ResourceLocation)Block.blockRegistry.getNameForObject(obj)).getResourcePath(), Integer.MAX_VALUE, betterBlockPos.x2, betterBlockPos.y2, 4)
                  .stream()
         )
         .map(obj2 -> new BetterBlockPos((BlockPos)obj2))
         .map(
            obj3 -> {
               BetterBlockPos var1b = (BetterBlockPos)obj3;
               String str2 = String.format("%s %s %s", var1b.x2, var1b.y2, var1b.z2);
               String str3 = String.format("%sgoal %s", IBaritoneChatControl.FORCE_COMMAND_PREFIX, str2);
               ChatComponentText chatComponentText = new ChatComponentText(obj3.toString());
               ChatComponentText chatComponentText2 = new ChatComponentText("Click to set goal to this position");
               chatComponentText.getChatStyle()
                  .setColor(EnumChatFormatting.GRAY)
                  .setInsertion(str2)
                  .setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, str3))
                  .setChatHoverEvent(new HoverEvent(net.minecraft.event.HoverEvent.Action.SHOW_TEXT, chatComponentText2));
               return chatComponentText;
            }
         )
         .toArray(IChatComponent[]::new);
      if (components.length > 0) {
         Arrays.asList(components).forEach(component -> this.logDirect(new IChatComponent[]{component}));
      } else {
         this.logDirect2("No positions known, are you sure the blocks are cached?");
      }
   }

   @Override
   public List getLongDesc() {
      return Arrays.asList(
         "The find command searches through Baritone's cache and attempts to find the location of the block.",
         "Tab completion will suggest only cached blocks and uncached blocks can not be found.",
         "",
         "Usage:",
         "> find <block> [...] - Try finding the listed blocks"
      );
   }

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) throws CommandException {
      return new TabCompleteHelper()
         .append(CachedChunk.BLOCKS_TO_KEEP_TRACK_OF.stream().map(Block.blockRegistry::getNameForObject).map(Object::toString))
         .filterPrefixNamespaced(argConsumer.getString())
         .sortAlphabetically()
         .getStream();
   }

}
