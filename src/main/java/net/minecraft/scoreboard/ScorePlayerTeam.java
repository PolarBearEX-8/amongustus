package net.minecraft.scoreboard;

import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Set;
import net.minecraft.util.EnumChatFormatting;

public class ScorePlayerTeam extends Team {
   private final Scoreboard theScoreboard;
   private final String registeredName;
   private final Set membershipSet = Sets.newHashSet();
   private String teamNameSPT;
   private String namePrefixSPT = "";
   private String colorSuffix = "";
   private boolean allowFriendlyFire = true;
   private boolean canSeeFriendlyInvisibles = true;
   private Team.EnumVisible nameTagVisibility = Team.EnumVisible.ALWAYS;
   private Team.EnumVisible deathMessageVisibility = Team.EnumVisible.ALWAYS;
   private EnumChatFormatting chatFormat = EnumChatFormatting.RESET;

   public void setSeeFriendlyInvisiblesEnabled(boolean flag) {
      this.canSeeFriendlyInvisibles = flag;
      this.theScoreboard.sendTeamUpdate(this);
   }

   @Override
   public boolean getAllowFriendlyFire() {
      return this.allowFriendlyFire;
   }

   public void setAllowFriendlyFire(boolean flag) {
      this.allowFriendlyFire = flag;
      this.theScoreboard.sendTeamUpdate(this);
   }

   public ScorePlayerTeam(Scoreboard scoreboard, String str) {
      this.theScoreboard = scoreboard;
      this.registeredName = str;
      this.teamNameSPT = str;
   }

   public EnumChatFormatting getChatFormat() {
      return this.chatFormat;
   }

   @Override
   public String getRegisteredName() {
      return this.registeredName;
   }

   public void setTeamName(String str) {
      if (str == null) {
         throw new IllegalArgumentException("Name cannot be null");
      } else {
         this.teamNameSPT = str;
         this.theScoreboard.sendTeamUpdate(this);
      }
   }

   @Override
   public Team.EnumVisible getNameTagVisibility() {
      return this.nameTagVisibility;
   }

   @Override
   public String formatString(String str) {
      return this.getColorPrefix() + str + this.getColorSuffix();
   }

   public void setDeathMessageVisibility(Team.EnumVisible enumVisible) {
      this.deathMessageVisibility = enumVisible;
      this.theScoreboard.sendTeamUpdate(this);
   }

   public String getTeamName() {
      return this.teamNameSPT;
   }

   public void setChatFormat(EnumChatFormatting enumChatFormatting) {
      this.chatFormat = enumChatFormatting;
   }

   @Override
   public Collection getMembershipCollection() {
      return this.membershipSet;
   }

   @Override
   public Team.EnumVisible getDeathMessageVisibility() {
      return this.deathMessageVisibility;
   }

   @Override
   public boolean getSeeFriendlyInvisiblesEnabled() {
      return this.canSeeFriendlyInvisibles;
   }

   public void setNameTagVisibility(Team.EnumVisible enumVisible) {
      this.nameTagVisibility = enumVisible;
      this.theScoreboard.sendTeamUpdate(this);
   }

   public String getColorSuffix() {
      return this.colorSuffix;
   }

   public void setNameSuffix(String str) {
      this.colorSuffix = str;
      this.theScoreboard.sendTeamUpdate(this);
   }

   public void setNamePrefix(String str) {
      if (str == null) {
         throw new IllegalArgumentException("Prefix cannot be null");
      } else {
         this.namePrefixSPT = str;
         this.theScoreboard.sendTeamUpdate(this);
      }
   }

   public String getColorPrefix() {
      return this.namePrefixSPT;
   }

   public void setFriendlyFlags(int n) {
      this.setAllowFriendlyFire((n & 1) > 0);
      this.setSeeFriendlyInvisiblesEnabled((n & 2) > 0);
   }

   public int getFriendlyFlags() {
      byte b = 0;
      if (this.getAllowFriendlyFire()) {
         b |= 1;
      }

      if (this.getSeeFriendlyInvisiblesEnabled()) {
         b |= 2;
      }

      return b;
   }

   public static String formatPlayerName(Team team, String str) {
      return team == null ? str : team.formatString(str);
   }
}
