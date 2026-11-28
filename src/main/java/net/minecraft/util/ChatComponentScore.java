package net.minecraft.util;

import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.server.MinecraftServer;

public class ChatComponentScore extends ChatComponentStyle {
   private final String name;
   private final String objective;
   private String value = "";

   public void setValue(String str) {
      this.value = str;
   }

   public ChatComponentScore(String str, String str2) {
      this.name = str;
      this.objective = str2;
   }

   public String getName() {
      return this.name;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof ChatComponentScore)) {
         return false;
      } else {
         ChatComponentScore chatComponentScore = (ChatComponentScore)obj;
         return this.name.equals(chatComponentScore.name) && this.objective.equals(chatComponentScore.objective) && super.equals(obj);
      }
   }

   @Override
   public String getUnformattedTextForChat() {
      MinecraftServer server = MinecraftServer.getServer();
      if (server != null && server.isAnvilFileSet() && StringUtils.isNullOrEmpty(this.value)) {
         Scoreboard scoreboard = server.worldServerForDimension(0).getScoreboard();
         ScoreObjective objective2 = scoreboard.getObjective(this.objective);
         if (scoreboard.entityHasObjective(this.name, objective2)) {
            Score valueFromObjective = scoreboard.getValueFromObjective(this.name, objective2);
            this.setValue(String.format("%d", valueFromObjective.getScorePoints()));
         } else {
            this.value = "";
         }
      }

      return this.value;
   }

   @Override
   public IChatComponent copy() {
      return this.createCopy();
   }

   public ChatComponentScore createCopy() {
      ChatComponentScore chatComponentScore = new ChatComponentScore(this.name, this.objective);
      chatComponentScore.setValue(this.value);
      chatComponentScore.setChatStyle(this.getChatStyle().createShallowCopy());

      for (IChatComponent component : (Iterable<IChatComponent>)(this.getSiblings())) {
         chatComponentScore.appendSibling(component.copy());
      }

      return chatComponentScore;
   }

   @Override
   public String toString() {
      return "ScoreComponent{name='"
         + this.name
         + '\''
         + "objective='"
         + this.objective
         + '\''
         + ", siblings="
         + this.siblings
         + ", style="
         + this.getChatStyle()
         + '}';
   }

   public String getObjective() {
      return this.objective;
   }
}
