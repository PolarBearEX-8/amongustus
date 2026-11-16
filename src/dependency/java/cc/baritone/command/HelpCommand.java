package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.BreadthFirstHandler;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.DynamicListener;
import cc.baritone.utils.ExceptionAdapter;
import cc.baritone.utils.HeuristicEstimator;
import cc.baritone.utils.HeuristicEvaluator;
import client.setting.ClientSettingsBlackType;
import client.setting.ClientSettingsOpenUrlType;
import client.setting.ClientSettingsShowTextType;
import client.util.ClickEventActionManager;
import client.util.HoverEventActionManager;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class HelpCommand extends ExtendedItemEntry {
   private static boolean matchesDynamicListener(DynamicListener dynamicListener) {
      return !dynamicListener.isActive();
   }

   private static IChatComponent resolveIChatComponent(String text, DynamicListener dynamicListener) {
      String text2 = String.join("/", dynamicListener.getListAsList());
      String text3 = (String)dynamicListener.getListAsList().get(0);
      ChatComponentText chatComponentText = new ChatComponentText(" - " + dynamicListener.getLabelText2());
      chatComponentText.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.DARK_GRAY);
      ChatComponentText chatComponentText2 = new ChatComponentText(text2);
      chatComponentText2.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.WHITE);
      ChatComponentText chatComponentText3 = new ChatComponentText("");
      chatComponentText3.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.GRAY);
      chatComponentText3.appendChatComponent(chatComponentText2);
      chatComponentText3.resolveIChatComponent("\n" + dynamicListener.getLabelText2());
      chatComponentText3.resolveIChatComponent("\n\nClick to view full help");
      String text4 = BreadthFirstHandler.labelText + String.format("%s %s", text, dynamicListener.getListAsList().get(0));
      ChatComponentText chatComponentText4 = new ChatComponentText(text3);
      chatComponentText4.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.GRAY);
      chatComponentText4.appendChatComponent(chatComponentText);
      chatComponentText4.getStyleHasParentManager()
         .getStyleHasParentManager(new HoverEventActionManager(ClientSettingsShowTextType.SHOW_TEXT, chatComponentText3))
         .getStyleHasParentManager(new ClickEventActionManager(ClientSettingsOpenUrlType.RUN_COMMAND, text4));
      return chatComponentText4;
   }

   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      return depthFirstManager.isActiveAsFlag()
         ? new HeuristicEstimator()
            .resolveHeuristicEstimator(this.activeListener.getGenericListener())
            .resolveHeuristicEstimatorByText(depthFirstManager.getText())
            .getStream()
         : Stream.empty();
   }

   @Override
   public List getList() {
      return Arrays.asList(
         "Using this command, you can view detailed help information on how to use certain commands of Baritone.",
         "",
         "Usage:",
         "> help - Lists all commands and their short descriptions.",
         "> help <command> - Displays help information on a specific command."
      );
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      depthFirstManager.applyIndexByIndex(1);
      if (depthFirstManager.isActive() && !depthFirstManager.matchesClass(Integer.class)) {
         String text2 = depthFirstManager.getText().toLowerCase();
         DynamicListener dynamicListener = this.activeListener.getGenericListener().resolveDynamicListener(text2);
         if (dynamicListener == null) {
            throw new CommandNotFoundSManager(text2);
         }

         this.applyText(String.format("%s - %s", String.join(" / ", dynamicListener.getListAsList()), dynamicListener.getLabelText2()));
         this.applyText("");
         dynamicListener.getList().forEach((item -> this.applyText((String)item)));
         this.applyText("");
         ChatComponentText chatComponentText = new ChatComponentText("Click to return to the help menu");
         chatComponentText.getStyleHasParentManager()
            .getStyleHasParentManager(new ClickEventActionManager(ClientSettingsOpenUrlType.RUN_COMMAND, BreadthFirstHandler.labelText + text));
         this.applyIChatComponentArray(new IChatComponent[]{chatComponentText});
      } else {
         HeuristicEvaluator.applyDepthFirstManager(
            depthFirstManager,
            new HeuristicEvaluator(
               (List)this.activeListener.getGenericListener().getDequeEntry().getStream().filter(item -> HelpCommand.matchesDynamicListener((DynamicListener)item)).collect(Collectors.toList())
            ),
            this::updateState,
            item -> HelpCommand.resolveIChatComponent(text, (DynamicListener)item),
            BreadthFirstHandler.labelText + text
         );
      }
   }

   @Override
   public String getLabelText2() {
      return "View all commands or help on specific ones";
   }

   private void updateState() {
      this.applyText("All Baritone commands (clickable):");
   }

   public HelpCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"help", "?"});
   }
}
