package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.BetterBlockPosXSYSZSBlockPos;
import cc.baritone.utils.BreadthFirstHandler;
import cc.baritone.utils.BreadthFirstWatcher;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.ExceptionAdapter;
import cc.baritone.utils.HeuristicEstimator;
import cc.baritone.utils.ManagedCustom;
import client.setting.ClientSettingsBlackType;
import client.setting.ClientSettingsOpenUrlType;
import client.setting.ClientSettingsShowTextType;
import client.util.ClickEventActionManager;
import client.util.HoverEventActionManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;

public class FindCommand extends ExtendedItemEntry {
   private Stream resolveStream(BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos, Block block) {
      return this.staticListener
         .getNativeListener()
         .getBreadthFirstPipeline()
         .resolveArrayList(((ResourceLocation)Block.iterableExtensionBridge.getObject2(block)).getText1(), Integer.MAX_VALUE, betterBlockPosXSYSZSBlockPos.count9, betterBlockPosXSYSZSBlockPos.count12, 4)
         .stream();
   }

   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      return new HeuristicEstimator()
         .concat(BreadthFirstWatcher.immutableSet.stream().map(Block.iterableExtensionBridge::getObject2).map(Object::toString))
         .resolveHeuristicEstimator(depthFirstManager.getText())
         .getHeuristicEstimator2()
         .getStream();
   }

   private IChatComponent resolveIChatComponent(BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
      String text = String.format("%s %s %s", betterBlockPosXSYSZSBlockPos.count9, betterBlockPosXSYSZSBlockPos.count12, betterBlockPosXSYSZSBlockPos.count10);
      String text2 = String.format("%sgoal %s", BreadthFirstHandler.labelText, text);
      ChatComponentText chatComponentText = new ChatComponentText(betterBlockPosXSYSZSBlockPos.toString());
      ChatComponentText chatComponentText2 = new ChatComponentText("Click to set goal to this position");
      chatComponentText.getStyleHasParentManager()
         .withClientSettingsBlackType(ClientSettingsBlackType.GRAY)
         .withLabelText(text)
         .getStyleHasParentManager(new ClickEventActionManager(ClientSettingsOpenUrlType.RUN_COMMAND, text2))
         .getStyleHasParentManager(new HoverEventActionManager(ClientSettingsShowTextType.SHOW_TEXT, chatComponentText2));
      return chatComponentText;
   }

   @Override
   public List getList() {
      return Arrays.asList(
         "The find command searches through Baritone's cache and attempts to find the location of the block.",
         "Tab completion will suggest only cached blocks and uncached blocks can not be found.",
         "",
         "Usage:",
         "> find <block> [...] - Try finding the listed blocks"
      );
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      depthFirstManager.applyIndex2(1);
      ArrayList list = new ArrayList();

      while (depthFirstManager.isActive()) {
         list.add(depthFirstManager.resolveTarget2(ManagedCustom.INSTANCE));
      }

      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS = this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS();
      IChatComponent[] iChatComponentArray = (IChatComponent[])list.stream()
         .flatMap(block -> this.resolveStream(betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS, (Block)block))
         .map(pos -> new BetterBlockPosXSYSZSBlockPos((BlockPos)pos))
         .map(pos -> this.resolveIChatComponent((BetterBlockPosXSYSZSBlockPos)pos))
         .toArray(FindCommand::resolveIChatComponentArray);
      if (iChatComponentArray.length > 0) {
         Arrays.asList(iChatComponentArray).forEach(component -> FindCommand.applyFindCommand(this, component));
      } else {
         this.applyText("No positions known, are you sure the blocks are cached?");
      }
   }

   @Override
   public String getLabelText2() {
      return "Find positions of a certain block";
   }

   private static void applyFindCommand(FindCommand findCommand, IChatComponent iChatComponent) {
      findCommand.applyIChatComponentArray(new IChatComponent[]{iChatComponent});
   }

   private static IChatComponent[] resolveIChatComponentArray(int count) {
      return new IChatComponent[count];
   }

   public FindCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"find"});
   }
}
