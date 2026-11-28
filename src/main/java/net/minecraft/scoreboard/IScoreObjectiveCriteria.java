package net.minecraft.scoreboard;

import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import net.minecraft.util.EnumChatFormatting;

public interface IScoreObjectiveCriteria {
   Map INSTANCES = Maps.newHashMap();
   IScoreObjectiveCriteria DUMMY = new ScoreDummyCriteria("dummy");
   IScoreObjectiveCriteria TRIGGER = new ScoreDummyCriteria("trigger");
   IScoreObjectiveCriteria deathCount = new ScoreDummyCriteria("deathCount");
   IScoreObjectiveCriteria playerKillCount = new ScoreDummyCriteria("playerKillCount");
   IScoreObjectiveCriteria totalKillCount = new ScoreDummyCriteria("totalKillCount");
   IScoreObjectiveCriteria health = new ScoreHealthCriteria("health");
   IScoreObjectiveCriteria[] TEAM_KILL = new IScoreObjectiveCriteria[]{
      new GoalColor("teamkill.", EnumChatFormatting.BLACK),
      new GoalColor("teamkill.", EnumChatFormatting.DARK_BLUE),
      new GoalColor("teamkill.", EnumChatFormatting.DARK_GREEN),
      new GoalColor("teamkill.", EnumChatFormatting.DARK_AQUA),
      new GoalColor("teamkill.", EnumChatFormatting.DARK_RED),
      new GoalColor("teamkill.", EnumChatFormatting.DARK_PURPLE),
      new GoalColor("teamkill.", EnumChatFormatting.GOLD),
      new GoalColor("teamkill.", EnumChatFormatting.GRAY),
      new GoalColor("teamkill.", EnumChatFormatting.DARK_GRAY),
      new GoalColor("teamkill.", EnumChatFormatting.BLUE),
      new GoalColor("teamkill.", EnumChatFormatting.GREEN),
      new GoalColor("teamkill.", EnumChatFormatting.AQUA),
      new GoalColor("teamkill.", EnumChatFormatting.RED),
      new GoalColor("teamkill.", EnumChatFormatting.LIGHT_PURPLE),
      new GoalColor("teamkill.", EnumChatFormatting.YELLOW),
      new GoalColor("teamkill.", EnumChatFormatting.WHITE)
   };
   IScoreObjectiveCriteria[] KILLED_BY_TEAM = new IScoreObjectiveCriteria[]{
      new GoalColor("killedByTeam.", EnumChatFormatting.BLACK),
      new GoalColor("killedByTeam.", EnumChatFormatting.DARK_BLUE),
      new GoalColor("killedByTeam.", EnumChatFormatting.DARK_GREEN),
      new GoalColor("killedByTeam.", EnumChatFormatting.DARK_AQUA),
      new GoalColor("killedByTeam.", EnumChatFormatting.DARK_RED),
      new GoalColor("killedByTeam.", EnumChatFormatting.DARK_PURPLE),
      new GoalColor("killedByTeam.", EnumChatFormatting.GOLD),
      new GoalColor("killedByTeam.", EnumChatFormatting.GRAY),
      new GoalColor("killedByTeam.", EnumChatFormatting.DARK_GRAY),
      new GoalColor("killedByTeam.", EnumChatFormatting.BLUE),
      new GoalColor("killedByTeam.", EnumChatFormatting.GREEN),
      new GoalColor("killedByTeam.", EnumChatFormatting.AQUA),
      new GoalColor("killedByTeam.", EnumChatFormatting.RED),
      new GoalColor("killedByTeam.", EnumChatFormatting.LIGHT_PURPLE),
      new GoalColor("killedByTeam.", EnumChatFormatting.YELLOW),
      new GoalColor("killedByTeam.", EnumChatFormatting.WHITE)
   };

   IScoreObjectiveCriteria.EnumRenderType getRenderType();

   int computeScore(List list);

   boolean isReadOnly();

   String getName();


   public enum EnumRenderType {
      INTEGER("integer"),
      HEARTS("hearts");

   private static final Map BY_NAME = Maps.newHashMap();
      private final String renderType;
   public String getRenderType() {
         return this.renderType;
      }

      public static EnumRenderType getByName(String str) {
         EnumRenderType enumRenderType = (EnumRenderType)BY_NAME.get(str);
         return enumRenderType == null ? INTEGER : enumRenderType;
      }

      static {
         for (EnumRenderType enumRenderType : values()) {
            BY_NAME.put(enumRenderType.getRenderType(), enumRenderType);
         }
      }

      private EnumRenderType(String str) {
         this.renderType = str;
      }
   }
}
