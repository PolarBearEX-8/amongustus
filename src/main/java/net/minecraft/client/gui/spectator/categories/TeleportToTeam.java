package net.minecraft.client.gui.spectator.categories;

import java.util.Random;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiSpectator;
import net.minecraft.client.gui.spectator.ISpectatorMenuObject;
import net.minecraft.client.gui.spectator.ISpectatorMenuView;
import net.minecraft.client.gui.spectator.SpectatorMenu;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class TeleportToTeam implements ISpectatorMenuObject, ISpectatorMenuView {
   private final List items = Lists.newArrayList();

   @Override
   public void renderIcon(float f, int n) {
      Minecraft.getMinecraft().getTextureManager().bindTexture(GuiSpectator.SPECTATOR_WIDGETS_TEXTURE);
      Gui.drawModalRectWithCustomSizedTexture(0, 0, 16.0F, 0.0F, 16, 16, 256.0F, 256.0F);
   }

   public TeleportToTeam() {

      for (ScorePlayerTeam scorePlayerTeam : (Iterable<ScorePlayerTeam>)(Minecraft.getMinecraft().theWorld.getScoreboard().getTeams())) {
         this.items.add(new TeleportToTeam.TeamSelectionObject(this, scorePlayerTeam));
      }
   }

   @Override
   public List getItems() {
      return this.items;
   }

   @Override
   public IChatComponent getSpectatorName() {
      return new ChatComponentText("Teleport to team member");
   }

   @Override
   public IChatComponent getPrompt() {
      return new ChatComponentText("Select a team to teleport to");
   }

   @Override
   public void selectItem(SpectatorMenu spectatorMenu) {
      spectatorMenu.selectCategory(this);
   }

   @Override
   public boolean isEnabled() {
      for (ISpectatorMenuObject spectatorMenuObject : (Iterable<ISpectatorMenuObject>)(this.items)) {
         if (spectatorMenuObject.isEnabled()) {
            return true;
         }
      }

      return false;
   }


   public static class TeamSelectionObject implements ISpectatorMenuObject {
      private final ScorePlayerTeam team;
      private final ResourceLocation location;
      private final List players;
      public final TeleportToTeam teleportToTeam;

      public TeamSelectionObject(TeleportToTeam teleportToTeam2, ScorePlayerTeam scorePlayerTeam) {
         this.teleportToTeam = teleportToTeam2;
         this.team = scorePlayerTeam;
         this.players = Lists.newArrayList();

         for (String str : (Iterable<String>)(scorePlayerTeam.getMembershipCollection())) {
            NetworkPlayerInfo playerInfo = Minecraft.getMinecraft().getNetHandler().getPlayerInfo(str);
            if (playerInfo != null) {
               this.players.add(playerInfo);
            }
         }

         if (!this.players.isEmpty()) {
            String name = ((NetworkPlayerInfo)this.players.get(new Random().nextInt(this.players.size()))).getGameProfile().getName();
            this.location = AbstractClientPlayer.getLocationSkin(name);
            AbstractClientPlayer.getDownloadImageSkin(this.location, name);
         } else {
            this.location = DefaultPlayerSkin.getDefaultSkinLegacy();
         }
      }

      @Override
      public IChatComponent getSpectatorName() {
         return new ChatComponentText(this.team.getTeamName());
      }

      @Override
      public boolean isEnabled() {
         return !this.players.isEmpty();
      }

      @Override
      public void renderIcon(float f, int n) {
         int n2 = -1;
         String formatFromString = FontRenderer.getFormatFromString(this.team.getColorPrefix());
         if (formatFromString.length() >= 2) {
            n2 = Minecraft.getMinecraft().fontRendererObj.getColorCode(formatFromString.charAt(1));
         }

         if (n2 >= 0) {
            float f2 = (float)(n2 >> 16 & 0xFF) / 255.0F;
            float f3 = (float)(n2 >> 8 & 0xFF) / 255.0F;
            float f4 = (float)(n2 & 0xFF) / 255.0F;
            Gui.drawRect(1, 1, 15, 15, MathHelper.rgb2(f2 * f, f3 * f, f4 * f) | n << 24);
         }

         Minecraft.getMinecraft().getTextureManager().bindTexture(this.location);
         GlStateManager.color(f, f, f, (float)n / 255.0F);
         Gui.drawScaledCustomSizeModalRect(2, 2, 8.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
         Gui.drawScaledCustomSizeModalRect(2, 2, 40.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
      }

      @Override
      public void selectItem(SpectatorMenu spectatorMenu) {
         spectatorMenu.selectCategory(new TeleportToPlayer(this.players));
      }
   }
}
