package cc.baritone.utils;

import client.setting.ClientSettingsBlackType;
import client.setting.ClientSettingsOpenUrlType;
import client.util.BaseManagerStyleIChatComponentAdapter;
import client.util.ClickEventActionManager;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WorldDownloaderUpdateThread extends Thread {
   public static volatile boolean enabled = false;
   public static volatile boolean enabled2 = false;
   public static volatile boolean enabled3 = false;
   public static volatile String labelText = null;
   public static volatile List list;
   public static volatile ReleaseURLController releaseURLController;
   public static final String labelText2 = "http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/2520465#Usage";
   public static final String labelText3 = "https://github.com/pokechu22/WorldDownloader/wiki";
   public static final String labelText4 = "https://github.com/pokechu22/WorldDownloader";
   public static final String labelText5 = "http://pokechu22.github.io/WorldDownloader/redistribution";
   public static final String labelText6 = "http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/mods-discussion/2314237";

   public static boolean isEnabled3() {
      return enabled3;
   }

   public static boolean isEnabled2() {
      return enabled2;
   }

   public static void applyIndex(int count, char character, char character2) {
      long keySalt = ((long)count << 32 | (long)character << 48 >>> 32 | (long)character2 << 48 >>> 48) ^ 0x1616dc009e85L ^ 0x3a6cf73ade7aL;
      if (!enabled) {
         enabled = true;
         new WorldDownloaderUpdateThread(keySalt).start();
      }
   }

   private WorldDownloaderUpdateThread(long value) {
      super("WorldDownloader update check thread");
   }

   public static ReleaseURLController getReleaseURLController() {
      if (list == null) {
         return null;
      } else if (releaseURLController == null) {
         return null;
      } else {
         String textAsText = Pokechu22WorldDownloaderManager.getTextAsText();
         boolean flag = Pokechu22WorldDownloaderManager.properties3.getProperty("UpdateAllowBetas").equals("true");
         boolean flag2 = Pokechu22WorldDownloaderManager.properties3.getProperty("UpdateMinecraftVersion").equals("client");
         boolean flag3 = Pokechu22WorldDownloaderManager.properties3.getProperty("UpdateMinecraftVersion").equals("server");

         outerLoop:
         for (ReleaseURLController releaseURLController2 : (Iterable<ReleaseURLController>)(Iterable)list) {
            if (releaseURLController2.minecraftMinecraftCompaController != null && (!releaseURLController2.enabled || flag)) {
               if (flag2) {
                  if (!releaseURLController2.minecraftMinecraftCompaController.labelText.equals(textAsText)) {
                     continue;
                  }
               } else if (flag3) {
                  boolean flag4 = false;
                  String[] stringArray = releaseURLController2.minecraftMinecraftCompaController.stringArray;
                  int length = stringArray.length;
                  int count = 0;

                  while (true) {
                     if (count < length) {
                        if (!stringArray[count].equals(textAsText)) {
                           count++;
                           continue;
                        }

                        flag4 = true;
                     }

                     if (flag4) {
                        break;
                     }
                     continue outerLoop;
                  }
               }

               if (list.indexOf(releaseURLController2) <= list.indexOf(releaseURLController)) {
                  return releaseURLController2;
               }
            }
         }

         return null;
      }
   }

   public static List getList() {
      return list;
   }

   public static boolean isActive() {
      if (releaseURLController == null) {
         return false;
      } else {
         ReleaseURLController releaseURLController2 = getReleaseURLController();
         return releaseURLController2 == null ? false : releaseURLController != releaseURLController2;
      }
   }

   @Override
   public void run() {
      try {
         if (!Pokechu22WorldDownloaderManager.properties3.getProperty("TutorialShown").equals("true")) {
            sleep(5000L);
            BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter = new BaseManagerStyleIChatComponentAdapter("wdl.intro.success", new Object[0]);
            BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter2 = new BaseManagerStyleIChatComponentAdapter("wdl.intro.forumsLink", new Object[0]);
            baseManagerStyleIChatComponentAdapter2.getStyleHasParentManager()
               .withClientSettingsBlackType(ClientSettingsBlackType.BLUE)
               .withMemberRef3(true)
               .getStyleHasParentManager(
                  new ClickEventActionManager(
                     ClientSettingsOpenUrlType.OPEN_URL, "http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/2520465#Usage"
                  )
               );
            BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter3 = new BaseManagerStyleIChatComponentAdapter("wdl.intro.wikiLink", new Object[0]);
            baseManagerStyleIChatComponentAdapter3.getStyleHasParentManager()
               .withClientSettingsBlackType(ClientSettingsBlackType.BLUE)
               .withMemberRef3(true)
               .getStyleHasParentManager(new ClickEventActionManager(ClientSettingsOpenUrlType.OPEN_URL, "https://github.com/pokechu22/WorldDownloader/wiki"));
            BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter4 = new BaseManagerStyleIChatComponentAdapter("wdl.intro.usage", new Object[]{baseManagerStyleIChatComponentAdapter2, baseManagerStyleIChatComponentAdapter3});
            BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter5 = new BaseManagerStyleIChatComponentAdapter("wdl.intro.githubRepo", new Object[0]);
            baseManagerStyleIChatComponentAdapter5.getStyleHasParentManager()
               .withClientSettingsBlackType(ClientSettingsBlackType.BLUE)
               .withMemberRef3(true)
               .getStyleHasParentManager(new ClickEventActionManager(ClientSettingsOpenUrlType.OPEN_URL, "https://github.com/pokechu22/WorldDownloader"));
            BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter6 = new BaseManagerStyleIChatComponentAdapter("wdl.intro.contribute", new Object[]{baseManagerStyleIChatComponentAdapter5});
            BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter7 = new BaseManagerStyleIChatComponentAdapter("wdl.intro.redistributionList", new Object[0]);
            baseManagerStyleIChatComponentAdapter7.getStyleHasParentManager()
               .withClientSettingsBlackType(ClientSettingsBlackType.BLUE)
               .withMemberRef3(true)
               .getStyleHasParentManager(
                  new ClickEventActionManager(ClientSettingsOpenUrlType.OPEN_URL, "http://pokechu22.github.io/WorldDownloader/redistribution")
               );
            BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter8 = new BaseManagerStyleIChatComponentAdapter("wdl.intro.warning", new Object[0]);
            baseManagerStyleIChatComponentAdapter8.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.DARK_RED).withMemberRef2(true);
            BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter9 = new BaseManagerStyleIChatComponentAdapter("wdl.intro.illegally", new Object[0]);
            baseManagerStyleIChatComponentAdapter9.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.DARK_RED).withMemberRef2(true);
            BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter10 = new BaseManagerStyleIChatComponentAdapter("wdl.intro.stolen", new Object[]{baseManagerStyleIChatComponentAdapter8, baseManagerStyleIChatComponentAdapter7, baseManagerStyleIChatComponentAdapter9});
            BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter11 = new BaseManagerStyleIChatComponentAdapter("wdl.intro.stopModReposts", new Object[0]);
            baseManagerStyleIChatComponentAdapter11.getStyleHasParentManager()
               .withClientSettingsBlackType(ClientSettingsBlackType.BLUE)
               .withMemberRef3(true)
               .getStyleHasParentManager(
                  new ClickEventActionManager(
                     ClientSettingsOpenUrlType.OPEN_URL, "http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/mods-discussion/2314237"
                  )
               );
            BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter12 = new BaseManagerStyleIChatComponentAdapter("wdl.intro.stolenBeware", new Object[]{baseManagerStyleIChatComponentAdapter11});
            LoggerEntry.applyAltListener(OptionType.UPDATES, baseManagerStyleIChatComponentAdapter);
            LoggerEntry.applyAltListener(OptionType.UPDATES, baseManagerStyleIChatComponentAdapter4);
            LoggerEntry.applyAltListener(OptionType.UPDATES, baseManagerStyleIChatComponentAdapter6);
            LoggerEntry.applyAltListener(OptionType.UPDATES, baseManagerStyleIChatComponentAdapter10);
            LoggerEntry.applyAltListener(OptionType.UPDATES, baseManagerStyleIChatComponentAdapter12);
            Pokechu22WorldDownloaderManager.properties3.setProperty("TutorialShown", "true");
            Pokechu22WorldDownloaderManager.updateState8();
         }

         sleep(5000L);
         list = UserAgentHelper.getList();
         LoggerEntry.applyAltListener(OptionType.UPDATE_DEBUG, "wdl.messages.updates.releaseCount", new Object[]{list.size()});
         if (list.isEmpty()) {
            enabled3 = true;
            labelText = "No releases found.";
            return;
         }

         for (int index = 0; index < list.size(); index++) {
            ReleaseURLController releaseURLController2 = (ReleaseURLController)list.get(index);
            if (releaseURLController2.labelText.equalsIgnoreCase("1.8.9a-beta2")) {
               releaseURLController = releaseURLController2;
            }
         }

         if (releaseURLController == null) {
            LoggerEntry.applyAltListener(OptionType.UPDATE_DEBUG, "wdl.messages.updates.failedToFindMatchingRelease", new Object[]{"1.8.9a-beta2"});
            return;
         }

         if (isActive()) {
            ReleaseURLController releaseURLController3 = getReleaseURLController();
            BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter13 = new BaseManagerStyleIChatComponentAdapter("wdl.messages.updates.newRelease.updateLink", new Object[0]);
            baseManagerStyleIChatComponentAdapter13.getStyleHasParentManager()
               .withClientSettingsBlackType(ClientSettingsBlackType.BLUE)
               .withMemberRef3(true)
               .getStyleHasParentManager(new ClickEventActionManager(ClientSettingsOpenUrlType.OPEN_URL, releaseURLController3.labelText2));
            LoggerEntry.applyAltListener(
               OptionType.UPDATES, "wdl.messages.updates.newRelease", new Object[]{releaseURLController.labelText, releaseURLController3.labelText, baseManagerStyleIChatComponentAdapter13}
            );
         }

         if (releaseURLController.minecraftMinecraftCompaController != null) {
            HashMap map = new HashMap();

            outerLoop:
            for (RelativeToFileController relativeToFileController : releaseURLController.minecraftMinecraftCompaController.relativeToFileControllerArray) {
               try {
                  String text = ABCDEFMD5Helper.resolveText(relativeToFileController.labelText, relativeToFileController.labelText2);
                  String[] stringArray = relativeToFileController.stringArray;
                  int length = stringArray.length;

                  for (int index2 = 0; index2 < length; index2++) {
                     if (stringArray[index2].equalsIgnoreCase(text)) {
                        continue outerLoop;
                     }
                  }

                  LoggerEntry.applyAltListener(
                     OptionType.UPDATE_DEBUG,
                     "wdl.messages.updates.incorrectHash",
                     new Object[]{relativeToFileController.labelText2, relativeToFileController.labelText, Arrays.toString((Object[])relativeToFileController.stringArray), text}
                  );
                  map.put(relativeToFileController, text);
               } catch (Exception exception) {
                  LoggerEntry.applyAltListener(
                     OptionType.UPDATE_DEBUG,
                     "wdl.messages.updates.hashException",
                     new Object[]{relativeToFileController.labelText2, relativeToFileController.labelText, Arrays.toString((Object[])relativeToFileController.stringArray), exception}
                  );
                  map.put(relativeToFileController, exception);
               }
            }

            if (map.size() > 0) {
               BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter14 = new BaseManagerStyleIChatComponentAdapter("wdl.intro.forumsLink", new Object[0]);
               baseManagerStyleIChatComponentAdapter14.getStyleHasParentManager()
                  .withClientSettingsBlackType(ClientSettingsBlackType.BLUE)
                  .withMemberRef3(true)
                  .getStyleHasParentManager(
                     new ClickEventActionManager(
                        ClientSettingsOpenUrlType.OPEN_URL, "http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/2520465#Usage"
                     )
                  );
               LoggerEntry.applyAltListener(OptionType.UPDATES, "wdl.messages.updates.badHashesFound", new Object[]{baseManagerStyleIChatComponentAdapter14});
            }

            return;
         }

         LoggerEntry.applyAltListener(OptionType.UPDATE_DEBUG, "wdl.messages.updates.failedToFindMetadata", new Object[]{"1.8.9a-beta2"});
      } catch (Exception exception2) {
         LoggerEntry.applyAltListener(OptionType.UPDATE_DEBUG, "wdl.messages.updates.updateCheckError", new Object[]{exception2});
         enabled3 = true;
         labelText = exception2.toString();
         return;
      } finally {
         enabled2 = true;
      }
   }

   public static String getLabelText() {
      return labelText;
   }

   public static ReleaseURLController getReleaseURLControllerAsReleaseURLContro() {
      return releaseURLController;
   }
}
