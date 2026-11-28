package net.minecraft.client.gui;

import com.google.common.collect.ComparisonChain;
import java.util.Comparator;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.world.WorldSettings;
import com.google.common.collect.Ordering;
import com.mojang.authlib.GameProfile;
import java.awt.Color;
import java.util.List;
import net.augustus.modules.misc.MidClick;
import net.augustus.modules.misc.MurdererFinder;
import net.augustus.ClientManagers;
import net.augustus.render.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;

public class GuiPlayerTabOverlay extends Gui {
   public static final Ordering ENTRY_ORDERING = Ordering.from(new GuiPlayerTabOverlay.PlayerComparator(null));
   private final Minecraft mc;
   private final GuiIngame guiIngame;
   private IChatComponent footer;
   private IChatComponent header;
   private long lastTimeOpened;
   private boolean isBeingRendered;

   public void drawScoreboardValues(ScoreObjective scoreObjective, int n, String str, int n2, int n3, NetworkPlayerInfo networkPlayerInfo) {
      int scorePoints = scoreObjective.getScoreboard().getValueFromObjective(str, scoreObjective).getScorePoints();
      if (scoreObjective.getRenderType() == IScoreObjectiveCriteria.EnumRenderType.HEARTS) {
         this.mc.getTextureManager().bindTexture(icons);
         if (this.lastTimeOpened == networkPlayerInfo.getRenderVisibilityId()) {
            if (scorePoints < networkPlayerInfo.getLastHealth()) {
               networkPlayerInfo.setLastHealthTime(Minecraft.getSystemTime());
               networkPlayerInfo.setHealthBlinkTime((long)(this.guiIngame.getUpdateCounter() + 20));
            } else if (scorePoints > networkPlayerInfo.getLastHealth()) {
               networkPlayerInfo.setLastHealthTime(Minecraft.getSystemTime());
               networkPlayerInfo.setHealthBlinkTime((long)(this.guiIngame.getUpdateCounter() + 10));
            }
         }

         if (Minecraft.getSystemTime() - networkPlayerInfo.getLastHealthTime() > 1000L || this.lastTimeOpened != networkPlayerInfo.getRenderVisibilityId()) {
            networkPlayerInfo.setLastHealth(scorePoints);
            networkPlayerInfo.setDisplayHealth(scorePoints);
            networkPlayerInfo.setLastHealthTime(Minecraft.getSystemTime());
         }

         networkPlayerInfo.setRenderVisibilityId(this.lastTimeOpened);
         networkPlayerInfo.setLastHealth(scorePoints);
         int n4 = MathHelper.ceiling_float_int((float)Math.max(scorePoints, networkPlayerInfo.getDisplayHealth()) / 2.0F);
         int n5 = Math.max(MathHelper.ceiling_float_int((float)(scorePoints / 2)), Math.max(MathHelper.ceiling_float_int((float)(networkPlayerInfo.getDisplayHealth() / 2)), 10));
         boolean flag = networkPlayerInfo.getHealthBlinkTime() > (long)this.guiIngame.getUpdateCounter()
            && (networkPlayerInfo.getHealthBlinkTime() - (long)this.guiIngame.getUpdateCounter()) / 3L % 2L == 1L;
         if (n4 > 0) {
            float f = Math.min((float)(n3 - n2 - 4) / (float)n5, 9.0F);
            if (f > 3.0F) {
               for (int i = n4; i < n5; i++) {
                  this.drawTexturedModalRect((float)n2 + (float)i * f, (float)n, flag ? 25 : 16, 0, 9, 9);
               }

               for (int j = 0; j < n4; j++) {
                  this.drawTexturedModalRect((float)n2 + (float)j * f, (float)n, flag ? 25 : 16, 0, 9, 9);
                  if (flag) {
                     if (j * 2 + 1 < networkPlayerInfo.getDisplayHealth()) {
                        this.drawTexturedModalRect((float)n2 + (float)j * f, (float)n, 70, 0, 9, 9);
                     }

                     if (j * 2 + 1 == networkPlayerInfo.getDisplayHealth()) {
                        this.drawTexturedModalRect((float)n2 + (float)j * f, (float)n, 79, 0, 9, 9);
                     }
                  }

                  if (j * 2 + 1 < scorePoints) {
                     this.drawTexturedModalRect((float)n2 + (float)j * f, (float)n, j >= 10 ? 160 : 52, 0, 9, 9);
                  }

                  if (j * 2 + 1 == scorePoints) {
                     this.drawTexturedModalRect((float)n2 + (float)j * f, (float)n, j >= 10 ? 169 : 61, 0, 9, 9);
                  }
               }
            } else {
               float f2 = MathHelper.clamp_float((float)scorePoints / 20.0F, 0.0F, 1.0F);
               int n6 = (int)((1.0F - f2) * 255.0F) << 16 | (int)(f2 * 255.0F) << 8;
               String str2 = "" + (float)scorePoints / 2.0F;
               if (n3 - this.mc.fontRendererObj.getStringWidth(str2 + "hp") >= n2) {
                  str2 = str2 + "hp";
               }

               this.mc.fontRendererObj.drawStringWithShadow(str2, (float)((n3 + n2) / 2 - this.mc.fontRendererObj.getStringWidth(str2) / 2), (float)n, n6);
            }
         }
      } else {
         String str3 = EnumChatFormatting.YELLOW + "" + scorePoints;
         this.mc.fontRendererObj.drawStringWithShadow(str3, (float)(n3 - this.mc.fontRendererObj.getStringWidth(str3)), (float)n, 16777215);
      }
   }

   public IChatComponent getFooter() {
      return this.footer;
   }

   public GuiPlayerTabOverlay(Minecraft mc2, GuiIngame guiIngame2) {
      this.mc = mc2;
      this.guiIngame = guiIngame2;
   }

   public void setHeader(IChatComponent component) {
      this.header = component;
   }

   public void renderPlayerlist(int n, Scoreboard scoreboard, ScoreObjective scoreObjective) {
      NetHandlerPlayClient sendQueue = this.mc.thePlayer.sendQueue;
      List list = ENTRY_ORDERING.sortedCopy(sendQueue.getPlayerInfoMap());
      int n2 = 0;
      int n3 = 0;

      for (NetworkPlayerInfo networkPlayerInfo : (Iterable<NetworkPlayerInfo>)(list)) {
         int stringWidth = this.mc.fontRendererObj.getStringWidth(getPlayerName(networkPlayerInfo) + " " + networkPlayerInfo.getResponseTime());
         n2 = Math.max(n2, stringWidth);
         if (scoreObjective != null && scoreObjective.getRenderType() != IScoreObjectiveCriteria.EnumRenderType.HEARTS) {
            stringWidth = this.mc.fontRendererObj.getStringWidth(" " + scoreboard.getValueFromObjective(networkPlayerInfo.getGameProfile().getName(), scoreObjective).getScorePoints());
            n3 = Math.max(n3, stringWidth);
         }
      }

      list = list.subList(0, Math.min(list.size(), 80));
      int size = list.size();
      int n4 = size;

      int n5;
      for (n5 = 1; n4 > 20; n4 = (size + n5 - 1) / n5) {
         n5++;
      }

      boolean flag = this.mc.isIntegratedServerRunning() || this.mc.getNetHandler().getNetworkManager().getIsencrypted();
      int n6;
      if (scoreObjective != null) {
         if (scoreObjective.getRenderType() == IScoreObjectiveCriteria.EnumRenderType.HEARTS) {
            n6 = 90;
         } else {
            n6 = n3;
         }
      } else {
         n6 = 0;
      }

      int n7 = Math.min(n5 * ((flag ? 9 : 0) + n2 + n6 + 13), n - 50) / n5;
      int n8 = n / 2 - (n7 * n5 + (n5 - 1) * 5) / 2;
      int n9 = 10;
      int n10 = n7 * n5 + (n5 - 1) * 5;
      List list2 = null;
      List list3 = null;
      if (this.header != null) {
         list2 = this.mc.fontRendererObj.listFormattedStringToWidth(this.header.getFormattedText(), n - 50);

         for (String str : (Iterable<String>)(list2)) {
            n10 = Math.max(n10, this.mc.fontRendererObj.getStringWidth(str));
         }
      }

      if (this.footer != null) {
         list3 = this.mc.fontRendererObj.listFormattedStringToWidth(this.footer.getFormattedText(), n - 50);

         for (String str2 : (Iterable<String>)(list3)) {
            n10 = Math.max(n10, this.mc.fontRendererObj.getStringWidth(str2));
         }
      }

      if (list2 != null) {
         RenderUtil.drawRect(
            n / 2 - n10 / 2 - 1, n9 - 1, n / 2 + n10 / 2 + 1, n9 + list2.size() * this.mc.fontRendererObj.FONT_HEIGHT, -2147483648
         );

         for (String str3 : (Iterable<String>)(list2)) {
            int stringWidth2 = this.mc.fontRendererObj.getStringWidth(str3);
            this.mc.fontRendererObj.drawStringWithShadow(str3, (float)(n / 2 - stringWidth2 / 2), (float)n9, -1);
            n9 += this.mc.fontRendererObj.FONT_HEIGHT;
         }

         n9++;
      }

      RenderUtil.drawRect(n / 2 - n10 / 2 - 1, n9 - 1, n / 2 + n10 / 2 + 1, n9 + n4 * 9, -2147483648);

      for (int i = 0; i < size; i++) {
         int n11 = i / n4;
         int n12 = i % n4;
         int n13 = n8 + n11 * n7 + n11 * 5;
         int n14 = n9 + n12 * 9;
         RenderUtil.drawRect(n13, n14, n13 + n7, n14 + 8, 553648127);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.enableAlpha();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         if (i < list.size()) {
            NetworkPlayerInfo networkPlayerInfo2 = (NetworkPlayerInfo)list.get(i);
            Object playerName = getPlayerName(networkPlayerInfo2);
            GameProfile gameProfile = networkPlayerInfo2.getGameProfile();
            if (flag) {
               EntityPlayer playerEntityByUUID = this.mc.theWorld.getPlayerEntityByUUID(gameProfile.getId());
               boolean flag2 = playerEntityByUUID != null
                  && playerEntityByUUID.isWearing(EnumPlayerModelParts.CAPE)
                  && (gameProfile.getName().equals("Dinnerbone") || gameProfile.getName().equals("Grumm"));
               this.mc.getTextureManager().bindTexture(networkPlayerInfo2.getLocationSkin());
               int n15 = 8 + (flag2 ? 8 : 0);
               int n16 = 8 * (flag2 ? -1 : 1);
               Gui.drawScaledCustomSizeModalRect(n13, n14, 8.0F, (float)n15, 8, n16, 8, 8, 64.0F, 64.0F);
               if (playerEntityByUUID != null && playerEntityByUUID.isWearing(EnumPlayerModelParts.HAT)) {
                  int n17 = 8 + (flag2 ? 8 : 0);
                  int n18 = 8 * (flag2 ? -1 : 1);
                  Gui.drawScaledCustomSizeModalRect(n13, n14, 40.0F, (float)n17, 8, n18, 8, 8, 64.0F, 64.0F);
               }

               n13 += 9;
            }

            if (networkPlayerInfo2.getGameType() == WorldSettings.GameType.SPECTATOR) {
               playerName = EnumChatFormatting.ITALIC.toString() + playerName;
               this.mc.fontRendererObj.drawStringWithShadow((String)playerName, (float)n13, (float)n14, -1862270977);
            } else {
               this.mc.fontRendererObj.drawStringWithShadow((String)playerName, (float)n13, (float)n14, -1);
            }

            if (scoreObjective != null && networkPlayerInfo2.getGameType() != WorldSettings.GameType.SPECTATOR) {
               int n19 = n13 + n2 + 1;
               int n20 = n19 + n6;
               if (n20 - n19 > 5) {
                  this.drawScoreboardValues(scoreObjective, n14, gameProfile.getName(), n19, n20, networkPlayerInfo2);
               }
            }

            this.drawPing(n7, n13 - (flag ? 9 : 0), n14, networkPlayerInfo2);
         }
      }

      if (list3 != null) {
         n9 = n9 + n4 * 9 + 1;
         RenderUtil.drawRect(
            n / 2 - n10 / 2 - 1, n9 - 1, n / 2 + n10 / 2 + 1, n9 + list3.size() * this.mc.fontRendererObj.FONT_HEIGHT, -2147483648
         );

         for (String str4 : (Iterable<String>)(list3)) {
            int stringWidth3 = this.mc.fontRendererObj.getStringWidth(str4);
            this.mc.fontRendererObj.drawStringWithShadow(str4, (float)(n / 2 - stringWidth3 / 2), (float)n9, -1);
            n9 += this.mc.fontRendererObj.FONT_HEIGHT;
         }
      }
   }

   public void setFooter(IChatComponent component) {
      this.footer = component;
   }

   public void drawPing(int n, int n2, int n3, NetworkPlayerInfo networkPlayerInfo) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      if (ClientManagers.moduleManager.tabList.isToggled() && ClientManagers.moduleManager.tabList.pingNumber.getValue()) {
         int rgb = Color.green.getRGB();
         if (networkPlayerInfo.getResponseTime() < 70) {
            rgb = Color.green.getRGB();
         } else if (networkPlayerInfo.getResponseTime() < 150) {
            rgb = Color.yellow.getRGB();
         } else if (networkPlayerInfo.getResponseTime() < 250) {
            rgb = Color.orange.getRGB();
         } else if (networkPlayerInfo.getResponseTime() >= 250) {
            rgb = Color.red.getRGB();
         }

         this.mc.fontRendererObj.drawString(networkPlayerInfo.getResponseTime() + "", n2 + n - this.mc.fontRendererObj.getStringWidth(networkPlayerInfo.getResponseTime() + ""), n3, rgb);
      } else {
         this.mc.getTextureManager().bindTexture(icons);
         byte b2 = 0;
         if (networkPlayerInfo.getResponseTime() < 0) {
            b2 = 5;
         } else if (networkPlayerInfo.getResponseTime() < 150) {
            b2 = 0;
         } else if (networkPlayerInfo.getResponseTime() < 300) {
            b2 = 1;
         } else if (networkPlayerInfo.getResponseTime() < 600) {
            b2 = 2;
         } else if (networkPlayerInfo.getResponseTime() < 1000) {
            b2 = 3;
         } else {
            b2 = 4;
         }

         this.zLevel += 100.0F;
         this.drawTexturedModalRect(n2 + n - 11, n3, 0 + ((byte)0) * 10, 176 + b2 * 8, 10, 8);
         this.zLevel -= 100.0F;
      }
   }

   public void updatePlayerList(boolean flag) {
      if (flag && !this.isBeingRendered) {
         this.lastTimeOpened = Minecraft.getSystemTime();
      }

      this.isBeingRendered = flag;
   }

   public static String getPlayerName(NetworkPlayerInfo networkPlayerInfo) {
      String str = networkPlayerInfo.getDisplayName() != null
         ? networkPlayerInfo.getDisplayName().getFormattedText()
         : ScorePlayerTeam.formatPlayerName(networkPlayerInfo.getPlayerTeam(), networkPlayerInfo.getGameProfile().getName());
      if (ClientManagers.moduleManager.midClick.isFriend(networkPlayerInfo.getGameProfile().getName(), true) && ClientManagers.moduleManager.midClick.isToggled()) {
         String name2 = networkPlayerInfo.getGameProfile().getName();
         String str3 = str.replace(name2, (ClientManagers.moduleManager.midClick.simple.getValue() ? "§l" : "§a§l") + name2);
         if (!ClientManagers.moduleManager.midClick.simple.getValue()) {
            str = "§7[§2§lFriend§7]§9 " + str3;
         } else {
            str = str3;
         }
      }

      return str + (ClientManagers.moduleManager.murdererFinder.isMurderer(networkPlayerInfo.getGameProfile().getName()) ? " §7[§4Murderer§7]§9" : "");
   }

   public void resetFooterHeader() {
      this.header = null;
      this.footer = null;
   }

   public IChatComponent getHeader() {
      return this.header;
   }



   public static class InnerEntry {
   }

   public static class PlayerComparator implements Comparator {
      @Override
      public int compare(Object a, Object b) {
         return this.compare((NetworkPlayerInfo)a, (NetworkPlayerInfo)b);
      }

      public PlayerComparator(GuiPlayerTabOverlay.InnerEntry obj) {
         this();
      }

      private PlayerComparator() {
      }

      public int compare(NetworkPlayerInfo a, NetworkPlayerInfo b) {
         ScorePlayerTeam playerTeam = a.getPlayerTeam();
         ScorePlayerTeam playerTeam2 = b.getPlayerTeam();
         return ComparisonChain.start()
            .compareTrueFirst(a.getGameType() != WorldSettings.GameType.SPECTATOR, b.getGameType() != WorldSettings.GameType.SPECTATOR)
            .compare(playerTeam != null ? playerTeam.getRegisteredName() : "", playerTeam2 != null ? playerTeam2.getRegisteredName() : "")
            .compare(a.getGameProfile().getName(), b.getGameProfile().getName())
            .result();
      }
   }
}
