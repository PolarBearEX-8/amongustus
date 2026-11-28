package net.minecraft.client.gui;

import com.google.common.base.Predicate;
import net.minecraft.util.MovingObjectPosition;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import javax.imageio.ImageIO;
import net.augustus.modules.render.AntiStrike;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.utils.TextFormatUtil;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.utils.HolidayDetector;
import net.augustus.ClientManagers;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.ColorUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.Augustus;
import net.augustus.events.RenderPassType;
import net.augustus.ui.GuiIngameHook;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.utils.ClientHoliday;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.FoodStats;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.world.border.WorldBorder;
import net.optifine.CustomColors;
import net.optifine.config.Config;
import org.lwjgl.opengl.GL11;

public class GuiIngame extends Gui {
   private static final ResourceLocation vignetteTexPath = new ResourceLocation("textures/misc/vignette.png");
   public static final ResourceLocation widgetsTexPath = new ResourceLocation("textures/gui/widgets.png");
   private static final ResourceLocation pumpkinBlurTexPath = new ResourceLocation("textures/misc/pumpkinblur.png");
   private DynamicTexture halloweenPumpkinTexture = null;
   private ResourceLocation halloweenPumpkinLocation = null;
   private final Random rand = new Random();
   private final Minecraft mc;
   private final RenderItem itemRenderer;
   private final GuiNewChat persistantChatGUI;
   private final GuiStreamIndicator streamIndicator;
   private int updateCounter;
   private String recordPlaying = "";
   private int recordPlayingUpFor;
   private boolean recordIsPlaying;
   public float prevVignetteBrightness = 1.0F;
   public boolean disableVignette = false;
   private int remainingHighlightTicks;
   private ItemStack highlightingItemStack;
   private final GuiOverlayDebug overlayDebug;
   private final GuiSpectator spectatorGui;
   private final GuiPlayerTabOverlay overlayPlayerList;
   private int titlesTimer;
   private String displayedTitle = "";
   private String displayedSubTitle = "";
   private int titleFadeIn;
   private int titleDisplayTime;
   private int titleFadeOut;
   private int playerHealth = 0;
   private int lastPlayerHealth = 0;
   private long lastSystemTime = 0L;
   private long healthUpdateCounter = 0L;

   public void renderDemo(ScaledResolution sr) {
      this.mc.mcProfiler.startSection("demo");
      String str = "";
      if (this.mc.theWorld.getTotalWorldTime() >= 120500L) {
         str = I18n.format("demo.demoExpired", new Object[0]);
      } else {
         str = I18n.format("demo.remainingTime", new Object[]{StringUtils.ticksToElapsedTime((int)(120500L - this.mc.theWorld.getTotalWorldTime()))});
      }

      int stringWidth = this.getFontRenderer().getStringWidth(str);
      this.getFontRenderer().drawStringWithShadow(str, (float)(sr.getScaledWidth() - stringWidth - 10), 5.0F, 16777215);
      this.mc.mcProfiler.endSection();
   }

   public void updateTick() {
      if (this.recordPlayingUpFor > 0) {
         this.recordPlayingUpFor--;
      }

      if (this.titlesTimer > 0) {
         this.titlesTimer--;
         if (this.titlesTimer <= 0) {
            this.displayedTitle = "";
            this.displayedSubTitle = "";
         }
      }

      this.updateCounter++;
      this.streamIndicator.updateStreamAlpha();
      if (this.mc.thePlayer != null) {
         ItemStack currentItem = this.mc.thePlayer.inventory.getCurrentItem();
         if (currentItem == null) {
            this.remainingHighlightTicks = 0;
         } else if (this.highlightingItemStack != null
            && currentItem.getItem() == this.highlightingItemStack.getItem()
            && ItemStack.areItemStackTagsEqual(currentItem, this.highlightingItemStack)
            && (currentItem.isItemStackDamageable() || currentItem.getMetadata() == this.highlightingItemStack.getMetadata())) {
            if (this.remainingHighlightTicks > 0) {
               this.remainingHighlightTicks--;
            }
         } else {
            this.remainingHighlightTicks = 40;
         }

         this.highlightingItemStack = currentItem;
      }
   }

   public void resetPlayersOverlayFooterHeader() {
      this.overlayPlayerList.resetFooterHeader();
   }

   public void renderExpBar(ScaledResolution sr) {
      int n = sr.getScaledWidth() / 2 - 91;
      this.mc.mcProfiler.startSection("expBar");
      this.mc.getTextureManager().bindTexture(Gui.icons);
      if (this.mc.thePlayer.xpBarCap() > 0) {
         short s = 182;
         int n3 = (int)(this.mc.thePlayer.experience * (float)(s + 1));
         int n4 = sr.getScaledHeight() - 32 + 3;
         this.drawTexturedModalRect(n, n4, 0, 64, s, 5);
         if (n3 > 0) {
            this.drawTexturedModalRect(n, n4, 0, 69, n3, 5);
         }
      }

      this.mc.mcProfiler.endSection();
      if (this.mc.thePlayer.experienceLevel > 0) {
         this.mc.mcProfiler.startSection("expLevel");
         int n5 = 8453920;
         if (Config.isCustomColors()) {
            n5 = CustomColors.getExpBarTextColor(n5);
         }

         String str = "" + this.mc.thePlayer.experienceLevel;
         int n6 = (sr.getScaledWidth() - this.getFontRenderer().getStringWidth(str)) / 2;
         int n7 = sr.getScaledHeight() - 31 - 4;
         this.getFontRenderer().drawString(str, n6 + 1, n7, 0);
         this.getFontRenderer().drawString(str, n6 - 1, n7, 0);
         this.getFontRenderer().drawString(str, n6, n7 + 1, 0);
         this.getFontRenderer().drawString(str, n6, n7 - 1, 0);
         this.getFontRenderer().drawString(str, n6, n7, n5);
         this.mc.mcProfiler.endSection();
      }
   }

   public GuiPlayerTabOverlay getTabList() {
      return this.overlayPlayerList;
   }

   private void renderPortal(float f, ScaledResolution sr) {
      if (f < 1.0F) {
         f *= f;
         f *= f;
         f = f * 0.8F + 0.2F;
      }

      GlStateManager.disableAlpha();
      GlStateManager.disableDepth();
      GlStateManager.depthMask(false);
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.color(1.0F, 1.0F, 1.0F, f);
      this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
      TextureAtlasSprite texture = this.mc.getBlockRendererDispatcher().getBlockModelShapes().getTexture(Blocks.portal.getDefaultState());
      float minU = texture.getMinU();
      float minV = texture.getMinV();
      float maxU = texture.getMaxU();
      float maxV = texture.getMaxV();
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
      worldRenderer.pos(0.0, (double)sr.getScaledHeight(), -90.0).tex((double)minU, (double)maxV).endVertex();
      worldRenderer.pos((double)sr.getScaledWidth(), (double)sr.getScaledHeight(), -90.0).tex((double)maxU, (double)maxV).endVertex();
      worldRenderer.pos((double)sr.getScaledWidth(), 0.0, -90.0).tex((double)maxU, (double)minV).endVertex();
      worldRenderer.pos(0.0, 0.0, -90.0).tex((double)minU, (double)minV).endVertex();
      instance.draw();
      GlStateManager.depthMask(true);
      GlStateManager.enableDepth();
      GlStateManager.enableAlpha();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
   }

   private void renderBossHealth() {
      if (BossStatus.bossName != null && BossStatus.statusBarTime > 0) {
         BossStatus.statusBarTime--;
         ScaledResolution sr = ClientManagers.moduleManager.hotbar.isToggled()
            ? new ScaledResolution(
               (double)this.mc.displayWidth * (1.0 / ClientManagers.moduleManager.hotbar.hotbarScale.getValue()),
               (double)this.mc.displayHeight * (1.0 / ClientManagers.moduleManager.hotbar.hotbarScale.getValue()),
               (int)ClientManagers.moduleManager.hotbar.hotbarScale.getValue()
            )
            : new ScaledResolution(this.mc);
         int scaledWidth = sr.getScaledWidth();
         short s = 182;
         int n = scaledWidth / 2 - s / 2;
         int n2 = (int)(BossStatus.healthScale * (float)(s + 1));
         byte b = 12;
         this.drawTexturedModalRect(n, b, 0, 74, s, 5);
         this.drawTexturedModalRect(n, b, 0, 74, s, 5);
         if (n2 > 0) {
            this.drawTexturedModalRect(n, b, 0, 79, n2, 5);
         }

         String bossName = BossStatus.bossName;
         this.getFontRenderer()
            .drawStringWithShadow(bossName, (float)(scaledWidth / 2 - this.getFontRenderer().getStringWidth(bossName) / 2), (float)(b - 10), 16777215);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.mc.getTextureManager().bindTexture(icons);
      }
   }

   public void renderScoreboard(ScoreObjective scoreObjective, ScaledResolution sr, boolean flag, boolean flag2) {
      if ((!ClientManagers.moduleManager.scoreboard.remove.getValue() || !ClientManagers.moduleManager.scoreboard.isToggled()) && !ClientManagers.moduleManager.antiStrike.shouldHideScoreboard()) {
         Scoreboard scoreboard = scoreObjective.getScoreboard();
         Collection sortedScores = scoreboard.getSortedScores(scoreObjective);
         ArrayList list = Lists.newArrayList(Iterables.filter(sortedScores, new GuiIngame.FilterPredicate(this)));
         ArrayList list2;
         if (list.size() > 15) {
            list2 = Lists.newArrayList(Iterables.skip(list, sortedScores.size() - 15));
         } else {
            list2 = list;
         }

         int stringWidth = this.getFontRenderer().getStringWidth(scoreObjective.getDisplayName());

         for (Object obj : list2) {
            String str = ScorePlayerTeam.formatPlayerName(scoreboard.getPlayersTeam(((Score)obj).getPlayerName()), ((Score)obj).getPlayerName())
               + (ClientManagers.moduleManager.scoreboard.isToggled() ? "" : ": " + EnumChatFormatting.RED + ((Score)obj).getScorePoints());
            stringWidth = Math.max(stringWidth, this.getFontRenderer().getStringWidth(str));
         }

         int n = list2.size() * this.getFontRenderer().FONT_HEIGHT;
         int n2;
         if (ClientManagers.moduleManager.scoreboard.isToggled()) {
            n += this.getFontRenderer().FONT_HEIGHT + 1;
            if (ClientManagers.moduleManager.scoreboard.stick.getValue()) {
               double d = ClientManagers.moduleManager.arrayList.font.getSelectedValue().equals("Minecraft") ? ClientManagers.moduleManager.arrayList.scale.getValue() : 1.0;
               n2 = (int)((double)GuiIngameHook.arrayListBottomY * (d / ClientManagers.moduleManager.scoreboard.scale.getValue()) + (double)n);
            } else {
               n2 = (int)((double)(sr.getScaledHeight() - n) * (ClientManagers.moduleManager.scoreboard.positionY.getValue() / 100.0)) + n;
            }
         } else {
            n2 = sr.getScaledHeight() / 2 + n / 3;
         }

         int n3;
         int n4;
         int n5;
         boolean flag3;
         boolean flag4;
         byte b = 3;
            n5 = ClientManagers.moduleManager.scoreboard.isToggled()
               ? (int)((double)(sr.getScaledWidth() - stringWidth - b) * (ClientManagers.moduleManager.scoreboard.positionX.getValue() / 100.0))
               : sr.getScaledWidth() - stringWidth - b;
            n3 = list2.size();
            n4 = ClientManagers.moduleManager.scoreboard.isToggled()
               ? sr.getScaledWidth()
                  - (
                     sr.getScaledWidth()
                        - stringWidth
                        - b
                        - (int)((double)(sr.getScaledWidth() - stringWidth - b) * (ClientManagers.moduleManager.scoreboard.positionX.getValue() / 100.0))
                  )
               : sr.getScaledWidth() - b + 2;
            flag3 = ClientManagers.moduleManager.scoreboard.isToggled() && ClientManagers.moduleManager.scoreboard.blurShadowSettings.isBlurActive();
if (ClientManagers.moduleManager.scoreboard.isToggled()) {
if (ClientManagers.moduleManager.scoreboard.blurShadowSettings.isShadowActive()) {
flag4 = true;
} else {
flag4 = false;
}
} else {
flag4 = false;
}

         BlurShadowManager.enableOneShotPass(flag3, flag4);
         int n6 = stringWidth;
         String str2 = "";
         ClientManagers.moduleManager.antiStrike.updateScoreboardLineServer(str2);
         ArrayList list3 = list2;
         int n7 = n2;
         BlurShadowManager.submitRenderTask(
            false,
            shaderRenderPass -> {
               int n8 = 0;
               if (!flag || shaderRenderPass != ShaderRenderPass.NONE) {
                  if (ClientManagers.moduleManager.scoreboard.isToggled() && ClientManagers.moduleManager.scoreboard.rounded.getValue()) {
                     RenderUtil.drawRoundedRect(
                        (float)(n5 - 2), (float)(n7 - n3 * this.getFontRenderer().FONT_HEIGHT), (float)n4, (float)n7, (float)ClientManagers.moduleManager.scoreboard.roundFactor.getValue(), 1, false, false, true, true, ClientManagers.moduleManager.scoreboard.blurShadowSettings.resolvePassColor(shaderRenderPass, ColorUtil.resolvePassColor(shaderRenderPass, 1342177280))
                     );
                  } else {
                     drawRect(n5 - 2, n7, n4, n7 - n3 * this.getFontRenderer().FONT_HEIGHT, ClientManagers.moduleManager.scoreboard.blurShadowSettings.resolvePassColor(shaderRenderPass, ColorUtil.resolvePassColor(shaderRenderPass, 1342177280)));
                  }
               }

               for (Object obj2 : list3) {
                  n8++;
                  int n15 = n7 - n8 * this.getFontRenderer().FONT_HEIGHT;
                  if (shaderRenderPass == ShaderRenderPass.NONE) {
                     Score score = (Score)obj2;
                     String str3 = ScorePlayerTeam.formatPlayerName(scoreboard.getPlayersTeam(score.getPlayerName()), score.getPlayerName());
                     String str4 = EnumChatFormatting.RED + "" + score.getScorePoints();
                     if (AntiStrike.DOMAIN_PATTERN.matcher(str3).find()) {
                        ClientManagers.moduleManager.antiStrike.updateScoreboardLineServer(str3);
                        if (ClientManagers.moduleManager.antiStrike.isToggled() && ClientManagers.moduleManager.antiStrike.replaceServerName.getValue()) {
                           String[] strings = str3.split("\\.");
                           str3 = new StringBuilder().append(TextFormatUtil.extractFormattingCodes(strings[0], (char)167)).append("Augustus.top").toString();
                        }
                     }

                     this.getFontRenderer().drawStringInt(str3, n5, n15, -1, flag2);
                     if (!ClientManagers.moduleManager.scoreboard.isToggled()) {
                        this.getFontRenderer().drawString(str4, n4 - this.getFontRenderer().getStringWidth(str4), n15, -1);
                     }
                  }

                  if (n8 == list3.size()) {
                     if (!flag || shaderRenderPass != ShaderRenderPass.NONE) {
                        if (ClientManagers.moduleManager.scoreboard.isToggled() && ClientManagers.moduleManager.scoreboard.rounded.getValue()) {
                           RenderUtil.drawRoundedRect(
                              (float)(n5 - 2), (float)(n15 - this.getFontRenderer().FONT_HEIGHT - 1), (float)n4, (float)(n15 - 1), (float)ClientManagers.moduleManager.scoreboard.roundFactor.getValue(), 1, true, true, false, false, ClientManagers.moduleManager.scoreboard.blurShadowSettings.resolvePassColor(shaderRenderPass, ColorUtil.resolvePassColor(shaderRenderPass, 1610612736))
                           );
                           drawRect(n5 - 2, n15 - 1, n4, n15, ClientManagers.moduleManager.scoreboard.blurShadowSettings.resolvePassColor(shaderRenderPass, ColorUtil.resolvePassColor(shaderRenderPass, 1342177280)));
                        } else {
                           int n22 = n5 - 2;
                           int n23 = n15 - this.getFontRenderer().FONT_HEIGHT - 1;
                           int n24 = n15 - 1;
                           BlurShadowSettings blurShadowSettings5 = ClientManagers.moduleManager.scoreboard.blurShadowSettings;
                           drawRect(n22, n23, n4, n24, blurShadowSettings5.resolvePassColor(shaderRenderPass, ColorUtil.resolvePassColor(shaderRenderPass, 1610612736)));
                           n22 = n5 - 2;
                           n23 = n15 - 1;
                           blurShadowSettings5 = ClientManagers.moduleManager.scoreboard.blurShadowSettings;
                           drawRect(n22, n23, n4, n15, blurShadowSettings5.resolvePassColor(shaderRenderPass, ColorUtil.resolvePassColor(shaderRenderPass, 1342177280)));
                        }
                     }

                     String displayName = scoreObjective.getDisplayName();
                     if (AntiStrike.DOMAIN_PATTERN.matcher(displayName).find()) {
                        ClientManagers.moduleManager.antiStrike.updateScoreboardTitleServer(displayName);
                        if (ClientManagers.moduleManager.antiStrike.isToggled() && ClientManagers.moduleManager.antiStrike.replaceServerName.getValue()) {
                           String[] strings2 = displayName.split("\\.");
                           displayName = new StringBuilder().append(TextFormatUtil.extractFormattingCodes(strings2[0], (char)167)).append("Augustus.top").toString();
                        }
                     } else {
                        String str7 = "";
                        ClientManagers.moduleManager.antiStrike.updateScoreboardTitleServer(str7);
                     }

                     this.getFontRenderer().drawStringInt(displayName, n5 + n6 / 2 - this.getFontRenderer().getStringWidth(displayName) / 2, n15 - this.getFontRenderer().FONT_HEIGHT, ClientManagers.moduleManager.scoreboard.blurShadowSettings.resolvePassColor(shaderRenderPass, ColorUtil.resolvePassColor(shaderRenderPass, (byte)-1)), flag2);
                  }
               }
            }
         );
      }
   }

   public void renderGameOverlay(float f) {
      ScaledResolution sr = new ScaledResolution(this.mc);
      int scaledWidth = sr.getScaledWidth();
      int scaledHeight = sr.getScaledHeight();
      this.mc.entityRenderer.setupOverlayRendering(sr);
      RenderListener.postRender(f, sr, RenderPassType.RENDER2D_PRE);
      GlStateManager.enableBlend();
      if (!this.disableVignette && Config.isVignetteEnabled()) {
         this.renderVignette(this.mc.thePlayer.getBrightness(f), sr);
      } else {
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      }

      ItemStack stack = this.mc.thePlayer.inventory.armorItemInSlot(3);
      if (HolidayDetector.getCurrentHoliday() == ClientHoliday.HALLOWEEN && this.mc.gameSettings.thirdPersonView == 0) {
         this.renderHalloweenPumpkinOverlay(sr);
      } else if (this.mc.gameSettings.thirdPersonView == 0 && stack != null && stack.getItem() == Item.getItemFromBlock(Blocks.pumpkin)) {
         this.renderPumpkinOverlay(sr);
      }

      if (!this.mc.thePlayer.isPotionActive(Potion.confusion)) {
         float f2 = this.mc.thePlayer.prevTimeInPortal + (this.mc.thePlayer.timeInPortal - this.mc.thePlayer.prevTimeInPortal) * f;
         if (f2 > 0.0F) {
            this.renderPortal(f2, sr);
         }
      }

      if (this.mc.playerController.isSpectator()) {
         this.spectatorGui.renderTooltip(sr, f);
      } else if (!ClientManagers.moduleManager.hotbar.isToggled() || !ClientManagers.moduleManager.hotbar.isCustomHotbarActive()) {
         GL11.glPushMatrix();
         if (ClientManagers.moduleManager.hotbar.isToggled()) {
            GL11.glScaled((double)(1.0F / (float)sr.getScaleFactor()), (double)(1.0F / (float)sr.getScaleFactor()), 1.0);
            GL11.glScaled(ClientManagers.moduleManager.hotbar.hotbarScale.getValue(), ClientManagers.moduleManager.hotbar.hotbarScale.getValue(), 1.0);
         }

         this.renderTooltip(
            ClientManagers.moduleManager.hotbar.isToggled()
               ? new ScaledResolution(
                  (double)this.mc.displayWidth * (1.0 / ClientManagers.moduleManager.hotbar.hotbarScale.getValue()),
                  (double)this.mc.displayHeight * (1.0 / ClientManagers.moduleManager.hotbar.hotbarScale.getValue()),
                  (int)ClientManagers.moduleManager.hotbar.hotbarScale.getValue()
               )
               : sr,
            f
         );
         GL11.glPopMatrix();
      }

      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(icons);
      GlStateManager.enableBlend();
      GlStateManager.enableAlpha();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      this.mc.mcProfiler.startSection("bossHealth");
      GL11.glPushMatrix();
      if (ClientManagers.moduleManager.hotbar.isToggled()) {
         GL11.glScaled((double)(1.0F / (float)sr.getScaleFactor()), (double)(1.0F / (float)sr.getScaleFactor()), 1.0);
         GL11.glScaled(ClientManagers.moduleManager.hotbar.hotbarScale.getValue(), ClientManagers.moduleManager.hotbar.hotbarScale.getValue(), 1.0);
         GL11.glTranslated(0.0, (double)ClientManagers.moduleManager.hotbar.getInventoryHudYOffset(), 0.0);
      }

      this.renderBossHealth();
      this.mc.mcProfiler.endSection();
      if (this.mc.playerController.shouldDrawHUD()) {
         this.renderPlayerStats(
            ClientManagers.moduleManager.hotbar.isToggled()
               ? new ScaledResolution(
                  (double)this.mc.displayWidth * (1.0 / ClientManagers.moduleManager.hotbar.hotbarScale.getValue()),
                  (double)this.mc.displayHeight * (1.0 / ClientManagers.moduleManager.hotbar.hotbarScale.getValue()),
                  (int)ClientManagers.moduleManager.hotbar.hotbarScale.getValue()
               )
               : sr
         );
      }

      GL11.glPopMatrix();
      GlStateManager.disableBlend();
      if (this.mc.thePlayer.getSleepTimer() > 0) {
         this.mc.mcProfiler.startSection("sleep");
         GlStateManager.disableDepth();
         GlStateManager.disableAlpha();
         int sleepTimer = this.mc.thePlayer.getSleepTimer();
         float f3 = (float)sleepTimer / 100.0F;
         if (f3 > 1.0F) {
            f3 = 1.0F - (float)(sleepTimer - 100) / 10.0F;
         }

         drawRect(0, 0, scaledWidth, scaledHeight, (int)(220.0F * f3) << 24 | 1052704);
         GlStateManager.enableAlpha();
         GlStateManager.enableDepth();
         this.mc.mcProfiler.endSection();
      }

      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      int n2 = scaledWidth / 2 - 91;
      if (this.mc.thePlayer.isRidingHorse()) {
         this.renderHorseJumpBar(sr, n2);
      } else if (this.mc.playerController.gameIsSurvivalOrAdventure()) {
         GL11.glPushMatrix();
         if (ClientManagers.moduleManager.hotbar.isToggled()) {
            GL11.glScaled((double)(1.0F / (float)sr.getScaleFactor()), (double)(1.0F / (float)sr.getScaleFactor()), 1.0);
            GL11.glScaled(ClientManagers.moduleManager.hotbar.hotbarScale.getValue(), ClientManagers.moduleManager.hotbar.hotbarScale.getValue(), 1.0);
            GL11.glTranslated(0.0, (double)ClientManagers.moduleManager.hotbar.getInventoryHudYOffset(), 0.0);
         }

         this.renderExpBar(
            ClientManagers.moduleManager.hotbar.isToggled()
               ? new ScaledResolution(
                  (double)this.mc.displayWidth * (1.0 / ClientManagers.moduleManager.hotbar.hotbarScale.getValue()),
                  (double)this.mc.displayHeight * (1.0 / ClientManagers.moduleManager.hotbar.hotbarScale.getValue()),
                  (int)ClientManagers.moduleManager.hotbar.hotbarScale.getValue()
               )
               : sr
         );
         GL11.glPopMatrix();
      }

      if (this.mc.gameSettings.heldItemTooltips && !this.mc.playerController.isSpectator() && !ClientManagers.moduleManager.antiStrike.shouldHideTooltips()) {
         GL11.glPushMatrix();
         if (ClientManagers.moduleManager.hotbar.isToggled()) {
            GL11.glScaled((double)(1.0F / (float)sr.getScaleFactor()), (double)(1.0F / (float)sr.getScaleFactor()), 1.0);
            GL11.glScaled(ClientManagers.moduleManager.hotbar.hotbarScale.getValue(), ClientManagers.moduleManager.hotbar.hotbarScale.getValue(), 1.0);
            GL11.glTranslated(0.0, (double)ClientManagers.moduleManager.hotbar.getInventoryHudYOffset(), 0.0);
         }

         this.renderSelectedItem(
            ClientManagers.moduleManager.hotbar.isToggled()
               ? new ScaledResolution(
                  (double)this.mc.displayWidth * (1.0 / ClientManagers.moduleManager.hotbar.hotbarScale.getValue()),
                  (double)this.mc.displayHeight * (1.0 / ClientManagers.moduleManager.hotbar.hotbarScale.getValue()),
                  (int)ClientManagers.moduleManager.hotbar.hotbarScale.getValue()
               )
               : sr
         );
         GL11.glPopMatrix();
      } else if (this.mc.thePlayer.isSpectator()) {
         this.spectatorGui.renderSelectedItem(sr);
      }

      if (this.mc.isDemo()) {
         this.renderDemo(sr);
      }

      if (this.recordPlayingUpFor > 0) {
         this.mc.mcProfiler.startSection("overlayMessage");
         float f4 = (float)this.recordPlayingUpFor - f;
         int n3 = (int)(f4 * 255.0F / 20.0F);
         if (n3 > 255) {
            n3 = 255;
         }

         if (n3 > 8) {
            GlStateManager.pushMatrix();
            ScaledResolution sr2 = sr;
            if (ClientManagers.moduleManager.hotbar.isToggled()) {
               GlStateManager.scale(1.0F / (float)sr.getScaleFactor(), 1.0F / (float)sr.getScaleFactor(), 1.0F);
               GlStateManager.scale(ClientManagers.moduleManager.hotbar.hotbarScale.getValue(), ClientManagers.moduleManager.hotbar.hotbarScale.getValue(), 1.0);
               GL11.glTranslated(0.0, (double)ClientManagers.moduleManager.hotbar.getInventoryHudYOffset(), 0.0);
               sr2 = new ScaledResolution(
                  (double)this.mc.displayWidth * (1.0 / ClientManagers.moduleManager.hotbar.hotbarScale.getValue()),
                  (double)this.mc.displayHeight * (1.0 / ClientManagers.moduleManager.hotbar.hotbarScale.getValue()),
                  (int)ClientManagers.moduleManager.hotbar.hotbarScale.getValue()
               );
            }

            GlStateManager.translate((float)(sr2.getScaledWidth() / 2), (float)(sr2.getScaledHeight() - 68), 0.0F);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            int n4 = 16777215;
            if (this.recordIsPlaying) {
               n4 = MathHelper.hsvToRGB(f4 / 50.0F, 0.7F, 0.6F) & 16777215;
            }

            this.getFontRenderer()
               .drawString(this.recordPlaying, -this.getFontRenderer().getStringWidth(this.recordPlaying) / 2, -4, n4 + (n3 << 24 & 0xFF000000));
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
         }

         this.mc.mcProfiler.endSection();
      }

      if (this.titlesTimer > 0) {
         this.mc.mcProfiler.startSection("titleAndSubtitle");
         float f5 = (float)this.titlesTimer - f;
         int n5 = 255;
         if (this.titlesTimer > this.titleFadeOut + this.titleDisplayTime) {
            n5 = (int)(((float)(this.titleFadeIn + this.titleDisplayTime + this.titleFadeOut) - f5) * 255.0F / (float)this.titleFadeIn);
         }

         if (this.titlesTimer <= this.titleFadeOut) {
            n5 = (int)(f5 * 255.0F / (float)this.titleFadeOut);
         }

         n5 = MathHelper.clamp_int(n5, 0, 255);
         if (n5 > 8) {
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)(scaledWidth / 2), (float)(scaledHeight / 2), 0.0F);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.pushMatrix();
            GlStateManager.scale(4.0F, 4.0F, 4.0F);
            int n6 = n5 << 24 & 0xFF000000;
            this.getFontRenderer().drawString(this.displayedTitle, (float)(-this.getFontRenderer().getStringWidth(this.displayedTitle) / 2), -10.0F, 16777215 | n6, true);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(2.0F, 2.0F, 2.0F);
            this.getFontRenderer().drawString(this.displayedSubTitle, (float)(-this.getFontRenderer().getStringWidth(this.displayedSubTitle) / 2), 5.0F, 16777215 | n6, true);
            GlStateManager.popMatrix();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
         }

         this.mc.mcProfiler.endSection();
      }

      Scoreboard scoreboard = this.mc.theWorld.getScoreboard();
      ScoreObjective scoreObjective = null;
      ScorePlayerTeam playersTeam = scoreboard.getPlayersTeam(this.mc.thePlayer.getName());
      if (playersTeam != null) {
         int colorIndex = playersTeam.getChatFormat().getColorIndex();
         if (colorIndex >= 0) {
            scoreObjective = scoreboard.getObjectiveInDisplaySlot(3 + colorIndex);
         }
      }

      ScoreObjective scoreObjective2 = scoreObjective != null ? scoreObjective : scoreboard.getObjectiveInDisplaySlot(1);
      if (scoreObjective2 != null && !ClientManagers.moduleManager.scoreboard.isToggled()) {
         this.renderScoreboard(scoreObjective2, sr, false, false);
      }

      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.disableAlpha();
      GlStateManager.pushMatrix();
      GlStateManager.translate(0.0F, (float)(scaledHeight - 48), 0.0F);
      this.mc.mcProfiler.startSection("chat");
      if (!ClientManagers.moduleManager.chat.isToggled()) {
         this.persistantChatGUI.drawChatCustom(this.updateCounter, true, true, Integer.MIN_VALUE);
      }

      this.mc.mcProfiler.endSection();
      GlStateManager.popMatrix();
      scoreObjective2 = scoreboard.getObjectiveInDisplaySlot(0);
      if (this.mc.gameSettings.keyBindPlayerList.isKeyDown()
         && (!this.mc.isIntegratedServerRunning() || this.mc.thePlayer.sendQueue.getPlayerInfoMap().size() > 1 || scoreObjective2 != null)) {
         this.overlayPlayerList.updatePlayerList(true);
         if (!ClientManagers.moduleManager.tabList.isToggled() && !ClientManagers.moduleManager.antiStrike.shouldHideTabList()) {
            this.overlayPlayerList.renderPlayerlist(scaledWidth, scoreboard, scoreObjective2);
         }
      } else {
         this.overlayPlayerList.updatePlayerList(false);
      }

      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.disableLighting();
      GlStateManager.disableTexture2D();
      GlStateManager.enableTexture2D();
      RenderListener.postRender(f, sr, RenderPassType.RENDER2D_POST);
      GlStateManager.enableAlpha();
      if (this.mc.gameSettings.showDebugInfo) {
         this.overlayDebug.renderDebugInfo(sr);
      }
   }

   public boolean showCrosshair() {
      if (this.mc.gameSettings.showDebugInfo && !this.mc.thePlayer.hasReducedDebug() && !this.mc.gameSettings.reducedDebugInfo) {
         return false;
      } else if (this.mc.playerController.isSpectator()) {
         if (this.mc.pointedEntity != null) {
            return true;
         } else {
            if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
               BlockPos blockPos = this.mc.objectMouseOver.getBlockPos();
               if (this.mc.theWorld.getTileEntity(blockPos) instanceof IInventory) {
                  return true;
               }
            }

            return false;
         }
      } else {
         return true;
      }
   }

   public void renderSelectedItem(ScaledResolution sr) {
      this.mc.mcProfiler.startSection("selectedItemName");
      if (this.remainingHighlightTicks > 0 && this.highlightingItemStack != null) {
         Object displayName = this.highlightingItemStack.getDisplayName();
         if (this.highlightingItemStack.hasDisplayName()) {
            displayName = EnumChatFormatting.ITALIC.toString() + displayName;
         }

         int n = (sr.getScaledWidth() - this.getFontRenderer().getStringWidth((String)displayName)) / 2;
         int n2 = sr.getScaledHeight() - 59;
         if (!this.mc.playerController.shouldDrawHUD()) {
            n2 += 14;
         }

         int n3 = (int)((float)this.remainingHighlightTicks * 256.0F / 10.0F);
         if (n3 > 255) {
            n3 = 255;
         }

         if (n3 > 0) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            this.getFontRenderer().drawStringWithShadow((String)displayName, (float)n, (float)n2, 16777215 + (n3 << 24));
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
         }
      }

      this.mc.mcProfiler.endSection();
   }

   public void setRecordPlayingMessage(String str) {
      this.setRecordPlaying(I18n.format("record.nowPlaying", new Object[]{str}), true);
   }

   public void setDefaultTitlesTimes() {
      this.titleFadeIn = 10;
      this.titleDisplayTime = 70;
      this.titleFadeOut = 20;
   }

   public void setRecordPlaying(String str, boolean flag) {
      this.recordPlaying = str;
      this.recordPlayingUpFor = 60;
      this.recordIsPlaying = flag;
   }

   public void renderHotbarItemStack(ItemStack stack, int n, int n2, float f) {
      if (stack != null) {
         float f2 = (float)stack.animationsToGo - f;
         if (f2 > 0.0F) {
            GlStateManager.pushMatrix();
            float f3 = 1.0F + f2 / 5.0F;
            GlStateManager.translate((float)(n + 8), (float)(n2 + 12), 0.0F);
            GlStateManager.scale(1.0F / f3, (f3 + 1.0F) / 2.0F, 1.0F);
            GlStateManager.translate((float)(-(n + 8)), (float)(-(n2 + 12)), 0.0F);
         }

         this.itemRenderer.renderItemAndEffectIntoGUI(stack, n, n2);
         if (f2 > 0.0F) {
            GlStateManager.popMatrix();
         }

         this.itemRenderer.renderItemOverlays(this.mc.fontRendererObj, stack, n, n2);
      }
   }

   public int getUpdateCounter() {
      return this.updateCounter;
   }

   public void displayTitle(String str, String str2, int n, int n2, int n3) {
      if (str == null && str2 == null && n < 0 && n2 < 0 && n3 < 0) {
         this.displayedTitle = "";
         this.displayedSubTitle = "";
         this.titlesTimer = 0;
      } else if (str != null) {
         this.titleDisplayTime = 70;
         this.displayedTitle = str;
         this.titlesTimer = this.titleFadeIn + this.titleDisplayTime + this.titleFadeOut;
      } else if (str2 != null) {
         this.displayedSubTitle = str2;
      } else {
         if (n >= 0) {
            this.titleFadeIn = n;
         }

         if (n2 >= 0) {
            this.titleDisplayTime = n2;
         }

         if (n3 >= 0) {
            this.titleFadeOut = n3;
         }

         if (this.titlesTimer > 0) {
            this.titlesTimer = this.titleFadeIn + this.titleDisplayTime + this.titleFadeOut;
         }
      }
   }

   private void loadHalloweenPumpkinTexture() {
      try {
         InputStream resourceAsStream = this.getClass().getResourceAsStream("/assets/augustus/pumpkinblur.png");
         if (resourceAsStream == null) {
            return;
         }

         BufferedImage image = ImageIO.read(resourceAsStream);
         resourceAsStream.close();
         if (image == null) {
            return;
         }

         this.halloweenPumpkinTexture = new DynamicTexture(image);
         this.halloweenPumpkinLocation = this.mc.getTextureManager().getDynamicTextureLocation("halloween_pumpkin", this.halloweenPumpkinTexture);
      } catch (Exception e) {
      }
   }

   public GuiSpectator getSpectatorGui() {
      return this.spectatorGui;
   }

   public void setRecordPlaying(IChatComponent component, boolean flag) {
      this.setRecordPlaying(component.getUnformattedText(), flag);
   }

   public void renderHotbarItem(int n, int n2, int n3, float f, EntityPlayer player) {
      this.renderHotbarItemStack(player.inventory.mainInventory[n], n2, n3, f);
   }

   public void renderHorseJumpBar(ScaledResolution sr, int n) {
      this.mc.mcProfiler.startSection("jumpBar");
      this.mc.getTextureManager().bindTexture(Gui.icons);
      float horseJumpPower = this.mc.thePlayer.getHorseJumpPower();
      short s = 182;
      int n2 = (int)(horseJumpPower * (float)(s + 1));
      int n3 = sr.getScaledHeight() - 32 + 3;
      this.drawTexturedModalRect(n, n3, 0, 84, s, 5);
      if (n2 > 0) {
         this.drawTexturedModalRect(n, n3, 0, 89, n2, 5);
      }

      this.mc.mcProfiler.endSection();
   }

   private void renderPlayerStats(ScaledResolution sr) {
      if (this.mc.getRenderViewEntity() instanceof EntityPlayer) {
         EntityPlayer renderViewEntity = (EntityPlayer)this.mc.getRenderViewEntity();
         int n = MathHelper.ceiling_float_int(renderViewEntity.getCurrentHealth());
         boolean flag = this.healthUpdateCounter > (long)this.updateCounter && (this.healthUpdateCounter - (long)this.updateCounter) / 3L % 2L == 1L;
         if (n < this.playerHealth && renderViewEntity.hurtResistantTime > 0) {
            this.lastSystemTime = Minecraft.getSystemTime();
            this.healthUpdateCounter = (long)(this.updateCounter + 20);
         } else if (n > this.playerHealth && renderViewEntity.hurtResistantTime > 0) {
            this.lastSystemTime = Minecraft.getSystemTime();
            this.healthUpdateCounter = (long)(this.updateCounter + 10);
         }

         if (Minecraft.getSystemTime() - this.lastSystemTime > 1000L) {
            this.playerHealth = n;
            this.lastPlayerHealth = n;
            this.lastSystemTime = Minecraft.getSystemTime();
         }

         this.playerHealth = n;
         int lastPlayerHealth2 = this.lastPlayerHealth;
         this.rand.setSeed((long)(this.updateCounter * 312871));
         boolean flag2 = false;
         FoodStats foodStats = renderViewEntity.getFoodStats();
         int foodLevel = foodStats.getFoodLevel();
         int prevFoodLevel = foodStats.getPrevFoodLevel();
         IAttributeInstance entityAttribute = renderViewEntity.getEntityAttribute(SharedMonsterAttributes.maxHealth);
         int n2 = sr.getScaledWidth() / 2 - 91;
         int n3 = sr.getScaledWidth() / 2 + 91;
         int n4 = sr.getScaledHeight() - 39;
         float attributeValue = (float)entityAttribute.getAttributeValue();
         float absorptionAmount = renderViewEntity.getAbsorptionAmount();
         int n5 = MathHelper.ceiling_float_int((attributeValue + absorptionAmount) / 2.0F / 10.0F);
         int n6 = Math.max(10 - (n5 - 2), 3);
         int n7 = n4 - (n5 - 1) * n6 - 10;
         float f = absorptionAmount;
         int totalArmorValue = renderViewEntity.getTotalArmorValue();
         int n8 = -1;
         if (renderViewEntity.isPotionActive(Potion.regeneration)) {
            n8 = this.updateCounter % MathHelper.ceiling_float_int(attributeValue + 5.0F);
         }

         this.mc.mcProfiler.startSection("armor");

         for (int i = 0; i < 10; i++) {
            if (totalArmorValue > 0) {
               int n9 = n2 + i * 8;
               if (i * 2 + 1 < totalArmorValue) {
                  this.drawTexturedModalRect(n9, n7, 34, 9, 9, 9);
               }

               if (i * 2 + 1 == totalArmorValue) {
                  this.drawTexturedModalRect(n9, n7, 25, 9, 9, 9);
               }

               if (i * 2 + 1 > totalArmorValue) {
                  this.drawTexturedModalRect(n9, n7, 16, 9, 9, 9);
               }
            }
         }

         this.mc.mcProfiler.endStartSection("health");

         for (int j = MathHelper.ceiling_float_int((attributeValue + absorptionAmount) / 2.0F) - 1; j >= 0; j--) {
            int n10 = 16;
            if (renderViewEntity.isPotionActive(Potion.poison)) {
               n10 += 36;
            } else if (renderViewEntity.isPotionActive(Potion.wither)) {
               n10 += 72;
            }

            byte b = 0;
            if (flag) {
               b = 1;
            }

            int n11 = MathHelper.ceiling_float_int((float)(j + 1) / 10.0F) - 1;
            int n12 = n2 + j % 10 * 8;
            int n13 = n4 - n11 * n6;
            if (n <= 4) {
               n13 += this.rand.nextInt(2);
            }

            if (j == n8) {
               n13 -= 2;
            }

            byte b2 = 0;
            if (renderViewEntity.entityWorld.getWorldInfo().isHardcoreModeEnabled()) {
               b2 = 5;
            }

            this.drawTexturedModalRect(n12, n13, 16 + b * 9, 9 * b2, 9, 9);
            if ((ClientManagers.moduleManager.legacyAnimations.isLegacyHeartAnimation() || flag) && !ClientManagers.moduleManager.legacyAnimations.isMinimalHeartAnimation()) {
               if (j * 2 + 1 < lastPlayerHealth2) {
                  this.drawTexturedModalRect(n12, n13, n10 + 54, 9 * b2, 9, 9);
               }

               if (j * 2 + 1 == lastPlayerHealth2) {
                  this.drawTexturedModalRect(n12, n13, n10 + 63, 9 * b2, 9, 9);
               }
            }

            if (f <= 0.0F) {
               if (j * 2 + 1 < n) {
                  this.drawTexturedModalRect(n12, n13, n10 + 36, 9 * b2, 9, 9);
               }

               if (j * 2 + 1 == n) {
                  this.drawTexturedModalRect(n12, n13, n10 + 45, 9 * b2, 9, 9);
               }
            } else {
               if (f == absorptionAmount && absorptionAmount % 2.0F == 1.0F) {
                  this.drawTexturedModalRect(n12, n13, n10 + 153, 9 * b2, 9, 9);
               } else {
                  this.drawTexturedModalRect(n12, n13, n10 + 144, 9 * b2, 9, 9);
               }

               f -= 2.0F;
            }
         }

         Entity ridingEntity = renderViewEntity.ridingEntity;
         if (ridingEntity == null) {
            this.mc.mcProfiler.endStartSection("food");

            for (int k = 0; k < 10; k++) {
               int n14 = n4;
               int n15 = 16;
               byte b3 = 0;
               if (renderViewEntity.isPotionActive(Potion.hunger)) {
                  n15 += 36;
                  b3 = 13;
               }

               if (renderViewEntity.getFoodStats().getSaturationLevel() <= 0.0F && this.updateCounter % (foodLevel * 3 + 1) == 0) {
                  n14 = n4 + (this.rand.nextInt(3) - 1);
               }

               if (flag2) {
                  b3 = 1;
               }

               int n16 = n3 - k * 8 - 9;
               this.drawTexturedModalRect(n16, n14, 16 + b3 * 9, 27, 9, 9);
               if (flag2) {
                  if (k * 2 + 1 < prevFoodLevel) {
                     this.drawTexturedModalRect(n16, n14, n15 + 54, 27, 9, 9);
                  }

                  if (k * 2 + 1 == prevFoodLevel) {
                     this.drawTexturedModalRect(n16, n14, n15 + 63, 27, 9, 9);
                  }
               }

               if (k * 2 + 1 < foodLevel) {
                  this.drawTexturedModalRect(n16, n14, n15 + 36, 27, 9, 9);
               }

               if (k * 2 + 1 == foodLevel) {
                  this.drawTexturedModalRect(n16, n14, n15 + 45, 27, 9, 9);
               }
            }
         } else if (ridingEntity instanceof EntityLivingBase) {
            this.mc.mcProfiler.endStartSection("mountHealth");
            EntityLivingBase entity = (EntityLivingBase)ridingEntity;
            int n17 = (int)Math.ceil((double)entity.getCurrentHealth());
            int n18 = (int)(entity.getMaxHealth() + 0.5F) / 2;
            if (n18 > 30) {
               n18 = 30;
            }

            int n19 = n4;

            for (int m = 0; n18 > 0; m += 20) {
               int n20 = Math.min(n18, 10);
               n18 -= n20;

               for (int idx = 0; idx < n20; idx++) {
                  byte b4 = 52;
                  byte b5 = 0;
                  if (flag2) {
                     b5 = 1;
                  }

                  int n21 = n3 - idx * 8 - 9;
                  this.drawTexturedModalRect(n21, n19, b4 + b5 * 9, 9, 9, 9);
                  if (idx * 2 + 1 + m < n17) {
                     this.drawTexturedModalRect(n21, n19, b4 + 36, 9, 9, 9);
                  }

                  if (idx * 2 + 1 + m == n17) {
                     this.drawTexturedModalRect(n21, n19, b4 + 45, 9, 9, 9);
                  }
               }

               n19 -= 10;
            }
         }

         this.mc.mcProfiler.endStartSection("air");
         if (renderViewEntity.isInsideOfMaterial(Material.water)) {
            int air = this.mc.thePlayer.getAir();
            int n22 = MathHelper.ceiling_double_int((double)(air - 2) * 10.0 / 300.0);
            int n23 = MathHelper.ceiling_double_int((double)air * 10.0 / 300.0) - n22;

            for (int index = 0; index < n22 + n23; index++) {
               if (index < n22) {
                  this.drawTexturedModalRect(n3 - index * 8 - 9, n7, 16, 18, 9, 9);
               } else {
                  this.drawTexturedModalRect(n3 - index * 8 - 9, n7, 25, 18, 9, 9);
               }
            }
         }

         this.mc.mcProfiler.endSection();
      }
   }

   public GuiNewChat getChatGUI() {
      return this.persistantChatGUI;
   }

   public GuiIngame(Minecraft mc2) {
      this.mc = mc2;
      this.itemRenderer = mc2.getRenderItem();
      this.overlayDebug = new GuiOverlayDebug(mc2);
      this.spectatorGui = new GuiSpectator(mc2);
      this.persistantChatGUI = new GuiNewChat(mc2);
      this.streamIndicator = new GuiStreamIndicator(mc2);
      this.overlayPlayerList = new GuiPlayerTabOverlay(mc2, this);
      this.setDefaultTitlesTimes();
   }

   public FontRenderer getFontRenderer() {
      return this.mc.fontRendererObj;
   }

   private void renderPumpkinOverlay(ScaledResolution sr) {
      GlStateManager.disableDepth();
      GlStateManager.depthMask(false);
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.disableAlpha();
      this.mc.getTextureManager().bindTexture(pumpkinBlurTexPath);
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
      worldRenderer.pos(0.0, (double)sr.getScaledHeight(), -90.0).tex(0.0, 1.0).endVertex();
      worldRenderer.pos((double)sr.getScaledWidth(), (double)sr.getScaledHeight(), -90.0).tex(1.0, 1.0).endVertex();
      worldRenderer.pos((double)sr.getScaledWidth(), 0.0, -90.0).tex(1.0, 0.0).endVertex();
      worldRenderer.pos(0.0, 0.0, -90.0).tex(0.0, 0.0).endVertex();
      instance.draw();
      GlStateManager.depthMask(true);
      GlStateManager.enableDepth();
      GlStateManager.enableAlpha();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
   }

   private void renderHalloweenPumpkinOverlay(ScaledResolution sr) {
      if (this.halloweenPumpkinTexture == null) {
         this.loadHalloweenPumpkinTexture();
      }

      if (this.halloweenPumpkinLocation == null) {
         this.renderPumpkinOverlay(sr);
      } else {
         GlStateManager.disableDepth();
         GlStateManager.depthMask(false);
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.disableAlpha();
         this.mc.getTextureManager().bindTexture(this.halloweenPumpkinLocation);
         Tessellator instance = Tessellator.getInstance();
         WorldRenderer worldRenderer = instance.getWorldRenderer();
         worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
         worldRenderer.pos(0.0, (double)sr.getScaledHeight(), -90.0).tex(0.0, 1.0).endVertex();
         worldRenderer.pos((double)sr.getScaledWidth(), (double)sr.getScaledHeight(), -90.0).tex(1.0, 1.0).endVertex();
         worldRenderer.pos((double)sr.getScaledWidth(), 0.0, -90.0).tex(1.0, 0.0).endVertex();
         worldRenderer.pos(0.0, 0.0, -90.0).tex(0.0, 0.0).endVertex();
         instance.draw();
         GlStateManager.depthMask(true);
         GlStateManager.enableDepth();
         GlStateManager.enableAlpha();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      }
   }

   public void renderVignette(float f, ScaledResolution sr) {
      if (!Config.isVignetteEnabled()) {
         GlStateManager.enableDepth();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      } else {
         f = 1.0F - f;
         f = MathHelper.clamp_float(f, 0.0F, 1.0F);
         WorldBorder worldBorder = this.mc.theWorld.getWorldBorder();
         float closestDistance = (float)worldBorder.getClosestDistance(this.mc.thePlayer);
         double d = Math.min(worldBorder.getResizeSpeed() * (double)worldBorder.getWarningTime() * 1000.0, Math.abs(worldBorder.getTargetSize() - worldBorder.getDiameter()));
         double d2 = Math.max((double)worldBorder.getWarningDistance(), d);
         if ((double)closestDistance < d2) {
            closestDistance = 1.0F - (float)((double)closestDistance / d2);
         } else {
            closestDistance = 0.0F;
         }

         this.prevVignetteBrightness = (float)((double)this.prevVignetteBrightness + (double)(f - this.prevVignetteBrightness) * 0.01);
         GlStateManager.disableDepth();
         GlStateManager.depthMask(false);
         GlStateManager.tryBlendFuncSeparate(0, 769, 1, 0);
         if (closestDistance > 0.0F) {
            GlStateManager.color(0.0F, closestDistance, closestDistance, 1.0F);
         } else {
            GlStateManager.color(this.prevVignetteBrightness, this.prevVignetteBrightness, this.prevVignetteBrightness, 1.0F);
         }

         this.mc.getTextureManager().bindTexture(vignetteTexPath);
         Tessellator instance = Tessellator.getInstance();
         WorldRenderer worldRenderer = instance.getWorldRenderer();
         worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
         worldRenderer.pos(0.0, (double)sr.getScaledHeight(), -90.0).tex(0.0, 1.0).endVertex();
         worldRenderer.pos((double)sr.getScaledWidth(), (double)sr.getScaledHeight(), -90.0).tex(1.0, 1.0).endVertex();
         worldRenderer.pos((double)sr.getScaledWidth(), 0.0, -90.0).tex(1.0, 0.0).endVertex();
         worldRenderer.pos(0.0, 0.0, -90.0).tex(0.0, 0.0).endVertex();
         instance.draw();
         GlStateManager.depthMask(true);
         GlStateManager.enableDepth();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      }
   }

   public void renderTooltip(ScaledResolution sr, float f) {
      if (this.mc.getRenderViewEntity() instanceof EntityPlayer) {
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.mc.getTextureManager().bindTexture(widgetsTexPath);
         EntityPlayer renderViewEntity = (EntityPlayer)this.mc.getRenderViewEntity();
         int n = sr.getScaledWidth() / 2;
         float zLevel2 = this.zLevel;
         this.zLevel = -90.0F;
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 771);
         this.drawTexturedModalRect(n - 91, sr.getScaledHeight() - 22, 0, 0, 182, 22);
         this.drawTexturedModalRect(n - 91 - 1 + SilentSlotManager.realHotbarSlot * 20, sr.getScaledHeight() - 22 - 1, 0, 22, 24, 22);
         this.zLevel = zLevel2;
         GlStateManager.enableRescaleNormal();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         RenderHelper.enableGUIStandardItemLighting();

         for (int i = 0; i < 9; i++) {
            this.renderHotbarItem(i, sr.getScaledWidth() / 2 - 90 + i * 20 + 2, sr.getScaledHeight() - 16 - 3, f, renderViewEntity);
         }

         RenderHelper.disableStandardItemLighting();
         GlStateManager.disableRescaleNormal();
         GlStateManager.disableBlend();
      }
   }

public static class FilterPredicate implements Predicate {
      public final GuiIngame guiIngame;

      public boolean apply(Object obj) {
         return this.apply((Score)obj);
      }

      public boolean apply(Score score) {
         return score.getPlayerName() != null && !score.getPlayerName().startsWith("#");
      }

      public FilterPredicate(GuiIngame guiIngame2) {
         this.guiIngame = guiIngame2;
      }
   }
}
