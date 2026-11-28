package net.augustus.modules.render;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.utils.TextFormatUtil;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.settings.options.AntiStrikeComponentOption;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.BooleanValue;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S33PacketUpdateSign;
import net.minecraft.network.play.server.S45PacketTitle;

public class AntiStrike extends Module implements PacketListener {
   public final MultiOptionSetting componentsToHide;
   public final BooleanValue replaceServerName;
   public final BooleanValue removeColorCodes;
   public final BooleanValue useAdvancedFilter;
   private String scoreboardTitleServerIp;
   private String scoreboardLineServerIp;
   private String scoreboardTitleServerName;
   private String scoreboardLineServerName;
   public static final Pattern DOMAIN_PATTERN = Pattern.compile("(?:\\b[a-zA-Z0-9\\p{L}_-]+(?:\\.[a-zA-Z0-9\\p{L}_-]+)*\\.[a-zA-Z\\p{L}]{2,}\\b)", 64);
   public static final Pattern COLOR_CODE_PATTERN = Pattern.compile("(?:§(?:[0-9]|a|b|c|d|e|f|k|l|m|n|o|r|)|)*", 66);

   public AntiStrike() {
       super("AntiStrike", "GegenStrike", new Color(82, 119, 222), Categorys.RENDER);
      this.componentsToHide = new MultiOptionSetting(this, "Components to hide", AntiStrikeComponentOption.values());
      this.replaceServerName = new BooleanValue(this, "Replace server name", true);
      this.removeColorCodes = new BooleanValue(this, "Remove color codes", false);
      this.useAdvancedFilter = new BooleanValue(this, "Use advanced filter", false);
      this.scoreboardTitleServerIp = "";
      this.scoreboardLineServerIp = "";
      this.scoreboardTitleServerName = "";
      this.scoreboardLineServerName = "";
   }

   public void onEnable() {
      this.sendChatMessage("Please rejoin the server for full Protection");
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(26);
   }

   public void onPacketReceive(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof S45PacketTitle && AntiStrikeComponentOption.TITLE.isSelected()) {
         event.cancel();
      }

      if (packet instanceof S02PacketChat && this.shouldHideChat()) {
         S02PacketChat packet2 = (S02PacketChat)packet;
         event.cancel();
         if (packet2.getType() == 2) {
            event.cancel();
         }
      }

      if (packet instanceof S33PacketUpdateSign && AntiStrikeComponentOption.SIGNS.isSelected()) {
         event.cancel();
      }
   }

   public void updateScoreboardTitleServer(String str) {
      if (str != null) {
         str = COLOR_CODE_PATTERN.matcher(str).replaceAll("");
         Matcher matcher = DOMAIN_PATTERN.matcher(str);
         if (matcher.find()) {
            String[] parts = matcher.group().split("\\.");
            if (parts.length > 1) {
               this.scoreboardTitleServerName = TextFormatUtil.findLongestString(parts);
            } else {
               this.scoreboardTitleServerName = "";
            }
         }

         String[] strings2 = str.split(" ");
         if (strings2.length > 1) {
            str = strings2[0];
         }

         str = TextFormatUtil.stripFormattingCodes(str, (char)167);
         this.scoreboardTitleServerIp = str;
      }
   }

   public String getScoreboardTitleServerIp() {
      return this.scoreboardTitleServerIp;
   }

   public String getScoreboardLineServerIp() {
      return this.scoreboardLineServerIp;
   }

   public String getScoreboardTitleServerName() {
      return this.scoreboardTitleServerName;
   }

   public String getScoreboardLineServerName() {
      return this.scoreboardLineServerName;
   }

   public void updateScoreboardLineServer(String str) {
      if (str != null) {
         str = COLOR_CODE_PATTERN.matcher(str).replaceAll("");
         Matcher matcher = DOMAIN_PATTERN.matcher(str);
         if (matcher.find()) {
            String[] parts = matcher.group().split("\\.");
            if (parts.length > 1) {
               this.scoreboardLineServerName = TextFormatUtil.findLongestString(parts);
            } else {
               this.scoreboardLineServerName = "";
            }
         }

         String[] strings2 = str.split(" ");
         if (strings2.length > 1) {
            str = strings2[0];
         }

         str = TextFormatUtil.stripFormattingCodes(str, (char)167);
         this.scoreboardLineServerIp = str;
      }
   }

   public boolean shouldHideChat() {
      return AntiStrikeComponentOption.isOptionSelected(AntiStrikeComponentOption.CHAT) && this.isToggled();
   }

   public boolean shouldHideTabList() {
      return AntiStrikeComponentOption.isOptionSelected(AntiStrikeComponentOption.TAB_LIST) && this.isToggled();
   }

   public boolean shouldHideScoreboard() {
      return AntiStrikeComponentOption.isOptionSelected(AntiStrikeComponentOption.SCOREBOARD) && this.isToggled();
   }

   public boolean shouldHideItemNames() {
      return AntiStrikeComponentOption.isOptionSelected(AntiStrikeComponentOption.ITEM_NAMES) && this.isToggled();
   }

   public boolean shouldHidePlayerNameTags() {
      return AntiStrikeComponentOption.isOptionSelected(AntiStrikeComponentOption.PLAYER_NAME_TAGS) && this.isToggled();
   }

   public boolean shouldHideOtherNameTags() {
      return AntiStrikeComponentOption.isOptionSelected(AntiStrikeComponentOption.OTHER_NAME_TAGS) && this.isToggled();
   }

   public boolean shouldHideTooltips() {
      return AntiStrikeComponentOption.isOptionSelected(AntiStrikeComponentOption.TOOL_TIPS) && this.isToggled();
   }

   public boolean shouldHideBooks() {
      return AntiStrikeComponentOption.BOOKS.isSelected() && this.isToggled();
   }

   public boolean shouldHideMaps() {
      return AntiStrikeComponentOption.MAPS.isSelected() && this.isToggled();
   }

   public boolean shouldHideChestNames() {
      return AntiStrikeComponentOption.CHEST_NAMES.isSelected() && this.isToggled();
   }


}
