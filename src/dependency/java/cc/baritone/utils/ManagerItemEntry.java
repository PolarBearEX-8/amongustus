package cc.baritone.utils;

import cc.Settings;
import cc.SettingsUtil;
import cc.baritone.api.AuxiliaryItemEntry;
import cc.baritone.api.ItemEntry;
import cc.baritone.command.CommandNotFoundSManager;
import client.setting.ClientSettingsBlackType;
import client.setting.ClientSettingsOpenUrlType;
import client.setting.ClientSettingsShowTextType;
import client.util.AimObserver;
import client.util.ClickEventActionManager;
import client.util.HoverEventActionManager;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class ManagerItemEntry extends AuxiliaryItemEntry implements DRNHandler {
   public static final Settings settings = SettingsTxtManager.getSettings();
   public final GenericListener genericListener;

   @Override
   public void forEach(HeuristicInspector heuristicInspector) {
      String labelText = heuristicInspector.getLabelText();
      String dataRef = (String)settings.prefix.dataRef;
      boolean flag = labelText.startsWith(BreadthFirstHandler.labelText);
      if ((!(Boolean)settings.prefixControl.dataRef || !labelText.startsWith(dataRef)) && !flag) {
         if (((Boolean)settings.chatControl.dataRef || (Boolean)settings.chatControlAnyway.dataRef) && this.matchesText(labelText)) {
            heuristicInspector.updateState();
         }
      } else {
         heuristicInspector.updateState();
         labelText = labelText.substring(flag ? BreadthFirstHandler.labelText.length() : dataRef.length());
         if (!this.matchesText(labelText) && !labelText.trim().isEmpty()) {
            new CommandNotFoundSManager((String)ItemEntry.resolveAimObserver(labelText).getDataRef2()).applyDynamicListener(null, null);
         }
      }
   }

   private static String[] resolveTextArray(int count) {
      return new String[count];
   }

   private void applyTextByText(String text, String text2) {
      if ((Boolean)settings.echoCommands.dataRef) {
         String text3 = text + text2;
         String text4 = (Boolean)settings.censorRanCommands.dataRef ? text + " ..." : text3;
         ChatComponentText chatComponentText = new ChatComponentText(String.format("> %s", text4));
         chatComponentText.getStyleHasParentManager()
            .withClientSettingsBlackType(ClientSettingsBlackType.WHITE)
            .getStyleHasParentManager(new HoverEventActionManager(ClientSettingsShowTextType.SHOW_TEXT, new ChatComponentText("Click to rerun command")))
            .getStyleHasParentManager(new ClickEventActionManager(ClientSettingsOpenUrlType.RUN_COMMAND, BreadthFirstHandler.labelText + text3));
         this.applyIChatComponentArray(new IChatComponent[]{chatComponentText});
      }
   }

   private static String resolveText(String text, String text2) {
      return text + text2;
   }

   @Override
   public void forEach(ActiveCustom activeCustom) {
      if ((Boolean)settings.prefixControl.dataRef) {
         String labelText = activeCustom.labelText;
         String dataRef = (String)settings.prefix.dataRef;
         if (labelText.startsWith(dataRef)) {
            labelText = labelText.substring(dataRef.length());
            List list = BreadthFirstSampler.resolveList(labelText, true);
            Stream stream = this.resolveStream(labelText);
            if (list.size() == 1) {
               stream = stream.map(item -> ManagerItemEntry.resolveText(dataRef, (String)item));
            }

            activeCustom.stringArray = ((String[])stream.toArray(ManagerItemEntry::resolveTextArray));
         }
      }
   }

   public boolean matchesText(String text) {
      if (text.trim().equalsIgnoreCase("damn")) {
         this.applyText("daniel");
         return false;
      } else if (text.trim().equalsIgnoreCase("orderpizza")) {
         try {
            this.staticListener.getMc().currentScreen.applyURI(new URI("https://www.dominos.com/en/pages/order/"));
         } catch (URISyntaxException | NullPointerException uRISyntaxException) {
         }

         return false;
      } else if (text.isEmpty()) {
         return this.matchesText("help");
      } else {
         AimObserver aimObserver = ItemEntry.resolveAimObserver(text);
         String dataRef = (String)aimObserver.getDataRef2();
         String text2 = text.substring(((String)aimObserver.getDataRef2()).length());
         BreadthFirstValidator breadthFirstValidator = new BreadthFirstValidator(this.genericListener, (List)aimObserver.getDataRef());
         if (!breadthFirstValidator.isActive()) {
            AuxiliaryStringEntry auxiliaryStringEntry = (AuxiliaryStringEntry)settings.byLowerName.get(dataRef.toLowerCase(Locale.US));
            if (auxiliaryStringEntry != null) {
               this.applyTextByText(dataRef, text2);
               if (auxiliaryStringEntry.getClassAsClass() == Boolean.class) {
                  this.genericListener.matchesText(String.format("set toggle %s", auxiliaryStringEntry.getLabelText()));
               } else {
                  this.genericListener.matchesText(String.format("set %s", auxiliaryStringEntry.getLabelText()));
               }

               return true;
            }
         } else if (breadthFirstValidator.isActiveAsFlag()) {
            for (AuxiliaryStringEntry auxiliaryStringEntry2 : (Iterable<AuxiliaryStringEntry>) (Iterable) (settings.allSettings)) {
               if (!auxiliaryStringEntry2.isEnabled() && auxiliaryStringEntry2.getLabelText().equalsIgnoreCase((String)aimObserver.getDataRef2())) {
                  this.applyTextByText(dataRef, text2);

                  try {
                     this.genericListener.matchesText(String.format("set %s %s", auxiliaryStringEntry2.getLabelText(), breadthFirstValidator.getText()));
                  } catch (AuxiliaryClientManager exception) {
                  }

                  return true;
               }
            }
         }

         if (this.genericListener.resolveDynamicListener((String)aimObserver.getDataRef2()) != null) {
            this.applyTextByText(dataRef, text2);
         }

         return this.genericListener.matchesAimObserver(aimObserver);
      }
   }

   public Stream resolveStream(String text) {
      try {
         List list = BreadthFirstSampler.resolveList(text, true);
         BreadthFirstValidator breadthFirstValidator = new BreadthFirstValidator(this.genericListener, list);
         if (breadthFirstValidator.sizeByIndex(2)) {
            if (breadthFirstValidator.size2(1)) {
               return new HeuristicEstimator()
                  .resolveHeuristicEstimator(this.genericListener)
                  .getHeuristicEstimatorAsHeuristicEstimat()
                  .resolveHeuristicEstimatorByText(breadthFirstValidator.getText())
                  .getStream();
            }

            AuxiliaryStringEntry auxiliaryStringEntry = (AuxiliaryStringEntry)settings.byLowerName.get(breadthFirstValidator.getText().toLowerCase(Locale.US));
            if (auxiliaryStringEntry != null && !auxiliaryStringEntry.isEnabled()) {
               if (auxiliaryStringEntry.getClassAsClass() == Boolean.class) {
                  HeuristicEstimator heuristicEstimator = new HeuristicEstimator();
                  if ((Boolean)auxiliaryStringEntry.dataRef) {
                     heuristicEstimator.resolveHeuristicEstimatorByTextArray(new String[]{"true", "false"});
                  } else {
                     heuristicEstimator.resolveHeuristicEstimatorByTextArray(new String[]{"false", "true"});
                  }

                  return heuristicEstimator.resolveHeuristicEstimatorByText(breadthFirstValidator.getText()).getStream();
               }

               return Stream.of(SettingsUtil.settingValueToString(auxiliaryStringEntry));
            }
         }

         return this.genericListener.resolveStream(text);
      } catch (AuxiliaryClientManager exception) {
         return Stream.empty();
      }
   }

   public ManagerItemEntry(BaritoneManager baritoneManager) {
      super(baritoneManager);
      this.genericListener = baritoneManager.getItemEntry();
   }
}
