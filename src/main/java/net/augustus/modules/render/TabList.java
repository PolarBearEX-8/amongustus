package net.augustus.modules.render;

import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.world.WorldSettings;
import com.mojang.authlib.GameProfile;
import java.awt.Color;
import java.util.List;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.ClientManagers;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import org.lwjgl.opengl.GL11;

public class TabList extends Module implements RenderListener {
   private final BooleanValue remove;
   private final DoubleValue scale;
   public final BooleanValue pingNumber;
   public final BooleanValue forceRenderHeads;
   private final BooleanValue alwaysOpen;
   public final BooleanValue fontShadow;
   private final DoubleValue rowSize;
   private final DoubleValue columnSize;
   private final BlurShadowSettings blurShadowSettings;

   public TabList() {
       super("TabList", "TabulaturListe", new Color(162, 250, 115), Categorys.RENDER);
      this.remove = new BooleanValue(this, "Remove", false);
      this.scale = new DoubleValue(this, "Scale", 2.0, 1.0, 4.0, 2).visibleWhen(() -> this.isScaleVisible());
      this.pingNumber = (BooleanValue)new BooleanValue(this, "PingNumber", false).withVisibility(() -> this.isPingNumberVisible());
      BooleanValue booleanValue2 = new BooleanValue(this, "ForceRenderHeads", false);
      SettingVisibilityCondition condition3 = () -> {
         return !this.remove.getValue();
      };
      this.forceRenderHeads = (BooleanValue)booleanValue2.withVisibility(condition3);
      BooleanValue booleanValue3 = new BooleanValue(this, "AlwaysOpen", false);
      SettingVisibilityCondition condition4 = () -> {
         return !this.remove.getValue();
      };
      this.alwaysOpen = (BooleanValue)booleanValue3.withVisibility(condition4);
      this.fontShadow = (BooleanValue)new BooleanValue(this, "FontShadow", true).withVisibility(() -> this.isFontShadowVisible());
      this.rowSize = new DoubleValue(this, "RowSize", 80.0, 10.0, 200.0, 1).named("MaxPlayersToRender").visibleWhen(() -> this.isRowSizeVisible());
      this.columnSize = new DoubleValue(this, "ColumnSize", 20.0, 1.0, 200.0, 1).visibleWhen(() -> this.isColumnSizeVisible());
      this.blurShadowSettings = (BlurShadowSettings)new BlurShadowSettings(this).withCustomShadowColor(Color.black).withVisibility(() -> this.isBlurShadowVisible());
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32);
   }

   @Override
   public void onRender2DPost(float partialTicks, ScaledResolution sr) {
      if ((mc.gameSettings.keyBindPlayerList.isKeyDown() || this.alwaysOpen.getValue())
         && !mc.isIntegratedServerRunning()
         && !mc.thePlayer.sendQueue.getPlayerInfoMap().isEmpty()
         && !ClientManagers.moduleManager.antiStrike.shouldHideTabList()
         && (!ClientManagers.moduleManager.tabList.isToggled() || !ClientManagers.moduleManager.tabList.remove.getValue())) {
         GL11.glPushMatrix();
         int scaledWidth = this.applyCustomScale(sr).getScaledWidth();
         net.minecraft.scoreboard.Scoreboard scoreboard = mc.theWorld.getScoreboard();
         ScoreObjective objectiveInDisplaySlot = scoreboard.getObjectiveInDisplaySlot(0);
         NetHandlerPlayClient sendQueue = mc.thePlayer.sendQueue;
         List list = GuiPlayerTabOverlay.ENTRY_ORDERING.sortedCopy(sendQueue.getPlayerInfoMap());
         int n = 0;
         int n2 = 0;

         for (NetworkPlayerInfo networkPlayerInfo : (Iterable<NetworkPlayerInfo>)(list)) {
            int stringWidth = mc.fontRendererObj.getStringWidth(GuiPlayerTabOverlay.getPlayerName(networkPlayerInfo) + " " + networkPlayerInfo.getResponseTime());
            n = Math.max(n, stringWidth);
            if (objectiveInDisplaySlot != null && objectiveInDisplaySlot.getRenderType() != IScoreObjectiveCriteria.EnumRenderType.HEARTS) {
               stringWidth = mc.fontRendererObj.getStringWidth(" " + scoreboard.getValueFromObjective(networkPlayerInfo.getGameProfile().getName(), objectiveInDisplaySlot).getScorePoints());
               n2 = Math.max(n2, stringWidth);
            }
         }

         list = list.subList(0, Math.min(list.size(), (int)this.rowSize.getValue()));
         int size = list.size();
         int n3 = size;
         int value = (int)this.columnSize.getValue();

         int n4;
         for (n4 = 1; n3 > value; n3 = (size + n4 - 1) / n4) {
            n4++;
         }

         boolean flag = mc.isIntegratedServerRunning() || mc.getNetHandler().getNetworkManager().getIsencrypted() || this.forceRenderHeads.getValue();
         int n5;
         if (objectiveInDisplaySlot != null) {
            if (objectiveInDisplaySlot.getRenderType() == IScoreObjectiveCriteria.EnumRenderType.HEARTS) {
               n5 = 90;
            } else {
               n5 = n2;
            }
         } else {
            n5 = 0;
         }

         int n6 = Math.min(n4 * ((flag ? 9 : 0) + n + n5 + 13), scaledWidth - 50) / n4;
         int n7 = scaledWidth / 2 - (n6 * n4 + (n4 - 1) * 5) / 2;
         int n8 = 10;
         int n9 = n6 * n4 + (n4 - 1) * 5;
         List list2 = null;
         List list3 = null;
         IChatComponent header = mc.ingameGUI.getTabList().getHeader();
         IChatComponent footer = mc.ingameGUI.getTabList().getFooter();
         if (header != null) {
            list2 = mc.fontRendererObj.listFormattedStringToWidth(header.getFormattedText(), scaledWidth - 50);

            for (String str : (Iterable<String>)(list2)) {
               n9 = Math.max(n9, mc.fontRendererObj.getStringWidth(str));
            }
         }

         if (footer != null) {
            list3 = mc.fontRendererObj.listFormattedStringToWidth(footer.getFormattedText(), scaledWidth - 50);

            for (String str2 : (Iterable<String>)(list3)) {
               n9 = Math.max(n9, mc.fontRendererObj.getStringWidth(str2));
            }
         }

         int n10 = (this.blurShadowSettings.isBlurActive()) ? 1 : 0;
         BlurShadowManager.enableOneShotPass((n10 != 0), this.blurShadowSettings.isShadowActive());
         BlurShadowManager.disableNormalPass();
         int n11 = n9;
         int n12 = n8;
         int n13 = n3;
         List list4 = list3;
         List list5 = list2;
         List list6 = list2;
         BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderShaderPass(scaledWidth, n11, n12, n13, list4, list5, n7, n6, size, list6, shaderRenderPass));
         if (list2 != null) {
            RenderUtil.drawRect(
               scaledWidth / 2 - n9 / 2 - 1,
               n8 - 1,
               scaledWidth / 2 + n9 / 2 + 1,
               n8 + list2.size() * mc.fontRendererObj.FONT_HEIGHT,
               Integer.MIN_VALUE
            );

            for (String str3 : (Iterable<String>)(list2)) {
               int stringWidth2 = mc.fontRendererObj.getStringWidth(str3);
               mc.fontRendererObj.drawString(str3, (float)(scaledWidth / 2 - stringWidth2 / 2), (float)n8, -1, this.fontShadow.getValue());
               n8 += mc.fontRendererObj.FONT_HEIGHT;
            }

            n8++;
         }

         RenderUtil.drawRect(scaledWidth / 2 - n9 / 2 - 1, n8 - 1, scaledWidth / 2 + n9 / 2 + 1, n8 + n3 * 9, Integer.MIN_VALUE);

         for (int i = 0; i < size; i++) {
            int n14 = i / n3;
            int n15 = i % n3;
            int n16 = n7 + n14 * n6 + n14 * 5;
            int n17 = n8 + n15 * 9;
            RenderUtil.drawRect(n16, n17, n16 + n6, n17 + 8, 553648127);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            if (i < list.size()) {
               NetworkPlayerInfo networkPlayerInfo2 = (NetworkPlayerInfo)list.get(i);
               Object playerName = GuiPlayerTabOverlay.getPlayerName(networkPlayerInfo2);
               GameProfile gameProfile = networkPlayerInfo2.getGameProfile();
               if (flag) {
                  EntityPlayer playerEntityByUUID = mc.theWorld.getPlayerEntityByUUID(gameProfile.getId());
                  boolean flag2 = playerEntityByUUID != null
                     && playerEntityByUUID.isWearing(EnumPlayerModelParts.CAPE)
                     && (gameProfile.getName().equals("Dinnerbone") || gameProfile.getName().equals("Grumm"));
                  mc.getTextureManager().bindTexture(networkPlayerInfo2.getLocationSkin());
                  int n18 = 8 + (flag2 ? 8 : 0);
                  int n19 = 8 * (flag2 ? -1 : 1);
                  Gui.drawScaledCustomSizeModalRect(n16, n17, 8.0F, (float)n18, 8, n19, 8, 8, 64.0F, 64.0F);
                  if (playerEntityByUUID != null && playerEntityByUUID.isWearing(EnumPlayerModelParts.HAT)) {
                     int n20 = 8 + (flag2 ? 8 : 0);
                     int n21 = 8 * (flag2 ? -1 : 1);
                     Gui.drawScaledCustomSizeModalRect(n16, n17, 40.0F, (float)n20, 8, n21, 8, 8, 64.0F, 64.0F);
                  }

                  n16 += 9;
               }

               if (networkPlayerInfo2.getGameType() == WorldSettings.GameType.SPECTATOR) {
                  playerName = EnumChatFormatting.ITALIC.toString() + playerName;
                  mc.fontRendererObj.drawString((String)playerName, (float)n16, (float)n17, -1862270977, this.fontShadow.getValue());
               } else {
                  mc.fontRendererObj.drawString((String)playerName, (float)n16, (float)n17, -1, this.fontShadow.getValue());
               }

               if (objectiveInDisplaySlot != null && networkPlayerInfo2.getGameType() != WorldSettings.GameType.SPECTATOR) {
                  int n22 = n16 + n + 1;
                  int n23 = n22 + n5;
                  if (n23 - n22 > 5) {
                     mc.ingameGUI.getTabList().drawScoreboardValues(objectiveInDisplaySlot, n17, gameProfile.getName(), n22, n23, networkPlayerInfo2);
                  }
               }

               mc.ingameGUI.getTabList().drawPing(n6, n16 - (flag ? 9 : 0), n17, networkPlayerInfo2);
            }
         }

         GlStateManager.disableBlend();
         if (list3 != null) {
            n8 = n8 + n3 * 9 + 1;
            n10 = scaledWidth / 2 - n9 / 2 - 1;
            RenderUtil.drawRect(n10, n8 - 1, scaledWidth / 2 + n9 / 2 + 1, n8 + list3.size() * mc.fontRendererObj.FONT_HEIGHT, Integer.MIN_VALUE);

            for (String str4 : (Iterable<String>)(list3)) {
               int stringWidth3 = mc.fontRendererObj.getStringWidth(str4);
               mc.fontRendererObj.drawString(str4, (float)(scaledWidth / 2 - stringWidth3 / 2), (float)n8, -1, this.fontShadow.getValue());
               n8 += mc.fontRendererObj.FONT_HEIGHT;
            }
         }

         GL11.glPopMatrix();
         RenderUtil.resetGlColorState();
      }
   }

   private ScaledResolution applyCustomScale(ScaledResolution sr) {
      GL11.glScaled((double)(1.0F / (float)sr.getScaleFactor()), (double)(1.0F / (float)sr.getScaleFactor()), 1.0);
      GL11.glScaled(this.scale.getValue(), this.scale.getValue(), 1.0);
      return new ScaledResolution(
         (double)mc.displayWidth * (1.0 / this.scale.getValue()), (double)mc.displayHeight * (1.0 / this.scale.getValue()), (int)this.scale.getValue()
      );
   }

   private void renderBlurMask(int n, int n2, int n3, int n4, int n5, int n6, int n7, List list, List list2) {
      if (list2 != null) {
         int n8 = n3;
         RenderUtil.drawRect(
            n / 2 - n2 / 2 - 1, n8 - 1, n / 2 + n2 / 2 + 1, n8 + list2.size() * mc.fontRendererObj.FONT_HEIGHT, Integer.MIN_VALUE
         );
         n3 = n8 + mc.fontRendererObj.FONT_HEIGHT * list2.size();
         n3++;
      }

      RenderUtil.drawRect(n / 2 - n2 / 2 - 1, n3 - 1, n / 2 + n2 / 2 + 1, n3 + n4 * 9, Integer.MIN_VALUE);

      for (int i = 0; i < n7; i++) {
         int n9 = i / n4;
         int n10 = i % n4;
         int n11 = n5 + n9 * n6 + n9 * 5;
         int n12 = n3 + n10 * 9;
         RenderUtil.drawRect(n11, n12, n11 + n6, n12 + 8, 553648127);
      }

      if (list != null) {
         n3 = n3 + n4 * 9 + 1;
         RenderUtil.drawRect(
            n / 2 - n2 / 2 - 1, n3 - 1, n / 2 + n2 / 2 + 1, n3 + list.size() * mc.fontRendererObj.FONT_HEIGHT, Integer.MIN_VALUE
         );
      }
   }

   private void renderShadowMask(int n, int n2, int n3, int n4, List list, List list2) {
      if (list2 != null) {
         int n5 = n / 2 - n2 / 2 - 1;
         int n6 = n3;
         RenderUtil.drawRect(n5, n6 - 1, n / 2 + n2 / 2 + 1, n6 + list2.size() * mc.fontRendererObj.FONT_HEIGHT, this.blurShadowSettings.resolveShadowColor(Color.black.getRGB()));
         n3 = n6 + mc.fontRendererObj.FONT_HEIGHT * list2.size();
         n3++;
      }

      int n10 = n / 2 - n2 / 2 - 1;
      int n11 = n3 - 1;
      int n12 = n / 2 + n2 / 2 + 1;
      int n13 = n3 + n4 * 9;
      int rgb2 = Color.black.getRGB();
      RenderUtil.drawRect(n10, n11, n12, n13, rgb2);
      if (list != null) {
         n3 = n3 + n4 * 9 + 1;
         n10 = n / 2 - n2 / 2 - 1;
         n11 = n3 - 1;
         n12 = n / 2 + n2 / 2 + 1;
         n13 = n3 + list.size() * mc.fontRendererObj.FONT_HEIGHT;
         rgb2 = this.blurShadowSettings.resolveShadowColor(Color.black.getRGB());
         RenderUtil.drawRect(n10, n11, n12, n13, rgb2);
      }
   }

   private void renderShaderPass(int n, int n2, int n3, int n4, List list, List list2, int n5, int n6, int n7, List list3, ShaderRenderPass shaderRenderPass) {
      if (shaderRenderPass == ShaderRenderPass.SHADOW) {
         this.renderShadowMask(n, n2, n3, n4, list, list2);
      } else {
         this.renderBlurMask(n, n2, n3, n4, n5, n6, n7, list, list3);
      }
   }

   private boolean isBlurShadowVisible() {
      return !this.remove.getValue();
   }

   private boolean isColumnSizeVisible() {
      return !this.remove.getValue();
   }

   private boolean isRowSizeVisible() {
      return !this.remove.getValue();
   }

   private boolean isFontShadowVisible() {
      return !this.remove.getValue();
   }

   private boolean isPingNumberVisible() {
      return !this.remove.getValue();
   }

   private boolean isScaleVisible() {
      return !this.remove.getValue();
   }

}
