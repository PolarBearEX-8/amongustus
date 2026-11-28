package net.augustus.modules.combat;

import java.awt.Color;
import java.util.ArrayList;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScorePlayerTeam;

public class Teams extends Module implements TickListener {
   private final StringValue modes;
   private final BooleanValue autoTeams;
   private final ArrayList teammates;
   private boolean hasMultipleTeams;
   private String localClanTag;

   public Teams() {
       super("Teams", "Verbündete", Color.red, Categorys.COMBAT);
      this.modes = (StringValue)(new StringValue(this, "Modes", "Color", new String[]{"Name", "Color", "Clan"})).markVisualOnly();
      this.autoTeams = (BooleanValue)(new BooleanValue(this, "AutoTeams", false)).markVisualOnly();
      this.teammates = new ArrayList();
      this.hasMultipleTeams = false;
      this.localClanTag = "";
   }

   public void onEnable() {
      this.hasMultipleTeams = false;
      this.localClanTag = "";
   }

   public void onDisable() {
      this.teammates.clear();
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
   }

   public void onTick() {
      this.teammates.clear();
      this.hasMultipleTeams = false;

      for (Entity entity : (Iterable<Entity>)(mc.theWorld.loadedEntityList)) {
         if (this.matchesTeamCheck(entity)) {
            this.teammates.add((EntityPlayer)entity);
         }
      }
   }

   private boolean matchesTeamCheck(Entity entity) {
      if (entity instanceof EntityLivingBase && entity instanceof EntityPlayer) {
         ScorePlayerTeam playersTeam = mc.theWorld.getScoreboard().getPlayersTeam(entity.getName());
         ScorePlayerTeam playersTeam2 = mc.theWorld.getScoreboard().getPlayersTeam(mc.thePlayer.getName());
         if (playersTeam != null && playersTeam2 != null) {
            if (playersTeam.getColorPrefix().equals(playersTeam2.getColorPrefix()) && this.modes.getSelectedValue().equals("Color")) {
               return true;
            }

            if (playersTeam.getTeamName().equals(playersTeam2.getTeamName()) && this.modes.getSelectedValue().equals("Name")) {
               return true;
            }

            this.hasMultipleTeams = true;
         }

         if (this.modes.getSelectedValue().equals("Clan") && entity.getDisplayName() != null) {
            String formattedText = entity.getDisplayName().getFormattedText();
            if (formattedText != null) {
               String[] parts = formattedText.split("§7 \\[§e");
               if (parts.length > 1) {
                  String str = parts[1];
                  if (this.localClanTag.equals("")) {
                     if (mc.thePlayer.getDisplayName() == null || mc.thePlayer.getDisplayName().getFormattedText() == null) {
                        this.localClanTag = "IAmInNoClanBastard";
                        return false;
                     }

                     String[] strings2 = mc.thePlayer.getDisplayName().getFormattedText().split("§7 \\[§e");
                     if (strings2.length > 1) {
                        this.localClanTag = strings2[1];
                     } else {
                        this.localClanTag = "IAmInNoClanBastard";
                     }
                  }

                  if (str.equals(this.localClanTag)) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   public boolean isTeammate(Entity entity) {
      return (this.hasMultipleTeams || !this.autoTeams.getValue()) && this.isToggled() && entity instanceof EntityPlayer && !this.getTeammates().isEmpty()
         ? this.getTeammates().contains(entity)
         : false;
   }

   public boolean isTeammateName(String str) {
      if ((this.hasMultipleTeams || !this.autoTeams.getValue()) && this.isToggled() && !this.getTeammates().isEmpty()) {
         for (EntityPlayer player : (Iterable<EntityPlayer>)(this.getTeammates())) {
            if (player.getName().equals(str)) {
               return true;
            }
         }
      }

      return false;
   }

   public ArrayList getTeammates() {
      return this.teammates;
   }



}
