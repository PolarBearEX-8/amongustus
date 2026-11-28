package net.minecraft.client.gui.achievement;

import java.io.IOException;
import java.util.Random;
import net.augustus.utils.input.MouseInputUtil;
import net.augustus.Augustus;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.IProgressMeter;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class GuiAchievements extends GuiScreen implements IProgressMeter {
   private static final int X_MIN = AchievementList.minDisplayColumn * 24 - 112;
   private static final int Y_MIN = AchievementList.minDisplayRow * 24 - 112;
   private static final int X_MAX = AchievementList.maxDisplayColumn * 24 - 77;
   private static final int Y_MAX = AchievementList.maxDisplayRow * 24 - 77;
   private static final ResourceLocation ACHIEVEMENT_BACKGROUND = new ResourceLocation("textures/gui/achievement/achievement_background.png");
   public int imageWidth = 256;
   public int imageHeight = 202;
   public int xLastScroll;
   public int yLastScroll;
   public float zoom = 1.0F;
   public double xScrollO;
   public double yScrollO;
   public double xScrollP;
   public double yScrollP;
   public double xScrollTarget;
   public double yScrollTarget;
   private int scrolling;
   private StatFileWriter statFileWriter;
   private boolean loadingAchievements = true;

   private TextureAtlasSprite getTexture(Block block) {
      return Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getTexture(block.getDefaultState());
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (!this.loadingAchievements && button.id == 1) {
         GuiScreen parentScreen2 = this.parentScreen;
         this.mc.displayGuiScreen(parentScreen2);
      }
   }

   public void drawAchievementScreen(int n, int n2, float f) {
      int n3 = MathHelper.floor_double(this.xScrollO + (this.xScrollP - this.xScrollO) * (double)f);
      int n4 = MathHelper.floor_double(this.yScrollO + (this.yScrollP - this.yScrollO) * (double)f);
      if (n3 < X_MIN) {
         n3 = X_MIN;
      }

      if (n4 < Y_MIN) {
         n4 = Y_MIN;
      }

      if (n3 >= X_MAX) {
         n3 = X_MAX - 1;
      }

      if (n4 >= Y_MAX) {
         n4 = Y_MAX - 1;
      }

      int n5 = (this.width - this.imageWidth) / 2;
      int n6 = (this.height - this.imageHeight) / 2;
      int n7 = n5 + 16;
      int n8 = n6 + 17;
      this.zLevel = 0.0F;
      GlStateManager.depthFunc(518);
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)n7, (float)n8, -200.0F);
      GlStateManager.scale(1.0F / this.zoom, 1.0F / this.zoom, 0.0F);
      GlStateManager.enableTexture2D();
      GlStateManager.disableLighting();
      GlStateManager.enableRescaleNormal();
      GlStateManager.enableColorMaterial();
      int n9 = n3 + 288 >> 4;
      int n10 = n4 + 288 >> 4;
      int n11 = (n3 + 288) % 16;
      int n12 = (n4 + 288) % 16;
      Random rand = new Random();
      float f2 = 16.0F / this.zoom;
      float f3 = 16.0F / this.zoom;

      for (int i = 0; (float)i * f2 - (float)n12 < 155.0F; i++) {
         float f4 = 0.6F - (float)(n10 + i) / 25.0F * 0.3F;
         GlStateManager.color(f4, f4, f4, 1.0F);

         for (int j = 0; (float)j * f3 - (float)n11 < 224.0F; j++) {
            rand.setSeed((long)(this.mc.getSession().getPlayerID().hashCode() + n9 + j + (n10 + i) * 16));
            int n13 = rand.nextInt(1 + n10 + i) + (n10 + i) / 2;
            TextureAtlasSprite texture = this.getTexture(Blocks.sand);
            if (n13 > 37 || n10 + i == 35) {
               texture = this.getTexture(Blocks.bedrock);
            } else if (n13 == 22) {
               if (rand.nextInt(2) == 0) {
                  texture = this.getTexture(Blocks.diamond_ore);
               } else {
                  texture = this.getTexture(Blocks.redstone_ore);
               }
            } else if (n13 == 10) {
               texture = this.getTexture(Blocks.iron_ore);
            } else if (n13 == 8) {
               texture = this.getTexture(Blocks.coal_ore);
            } else if (n13 > 4) {
               texture = this.getTexture(Blocks.stone);
            } else if (n13 > 0) {
               texture = this.getTexture(Blocks.dirt);
            }

            this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
            this.drawTexturedModalRect(j * 16 - n11, i * 16 - n12, texture, 16, 16);
         }
      }

      GlStateManager.enableDepth();
      GlStateManager.depthFunc(515);
      this.mc.getTextureManager().bindTexture(ACHIEVEMENT_BACKGROUND);

      for (int k = 0; k < AchievementList.achievementList.size(); k++) {
         Achievement achievement = (Achievement)AchievementList.achievementList.get(k);
         if (achievement.parentAchievement != null) {
            int n14 = achievement.displayColumn * 24 - n3 + 11;
            int n15 = achievement.displayRow * 24 - n4 + 11;
            int n16 = achievement.parentAchievement.displayColumn * 24 - n3 + 11;
            int n17 = achievement.parentAchievement.displayRow * 24 - n4 + 11;
            boolean flag = this.statFileWriter.hasAchievementUnlocked(achievement);
            boolean flag2 = this.statFileWriter.canUnlockAchievement(achievement);
            if (this.statFileWriter.countRequirementsUntilAvailable(achievement) <= 4) {
               int n19 = -16777216;
               if (flag) {
                  n19 = -6250336;
               } else if (flag2) {
                  n19 = -16711936;
               }

               this.drawHorizontalLine(n14, n16, n15, n19);
               this.drawVerticalLine(n16, n15, n17, n19);
               if (n14 > n16) {
                  this.drawTexturedModalRect(n14 - 11 - 7, n15 - 5, 114, 234, 7, 11);
               } else if (n14 < n16) {
                  this.drawTexturedModalRect(n14 + 11, n15 - 5, 107, 234, 7, 11);
               } else if (n15 > n17) {
                  this.drawTexturedModalRect(n14 - 5, n15 - 11 - 7, 96, 234, 11, 7);
               } else if (n15 < n17) {
                  this.drawTexturedModalRect(n14 - 5, n15 + 11, 96, 241, 11, 7);
               }
            }
         }
      }

      Achievement achievement2 = null;
      float f5 = (float)(n - n7) * this.zoom;
      float f6 = (float)(n2 - n8) * this.zoom;
      RenderHelper.enableGUIStandardItemLighting();
      GlStateManager.disableLighting();
      GlStateManager.enableRescaleNormal();
      GlStateManager.enableColorMaterial();

      for (int m = 0; m < AchievementList.achievementList.size(); m++) {
         Achievement achievement3 = (Achievement)AchievementList.achievementList.get(m);
         int n20 = achievement3.displayColumn * 24 - n3;
         int n21 = achievement3.displayRow * 24 - n4;
         if (n20 >= -24 && n21 >= -24 && (float)n20 <= 224.0F * this.zoom && (float)n21 <= 155.0F * this.zoom) {
            int n22 = this.statFileWriter.countRequirementsUntilAvailable(achievement3);
            if (this.statFileWriter.hasAchievementUnlocked(achievement3)) {
               float f7 = 0.75F;
               GlStateManager.color(f7, f7, f7, 1.0F);
            } else if (this.statFileWriter.canUnlockAchievement(achievement3)) {
               float f8 = 1.0F;
               GlStateManager.color(f8, f8, f8, 1.0F);
            } else if (n22 < 3) {
               float f9 = 0.3F;
               GlStateManager.color(f9, f9, f9, 1.0F);
            } else if (n22 == 3) {
               float f10 = 0.2F;
               GlStateManager.color(f10, f10, f10, 1.0F);
            } else {
               if (n22 != 4) {
                  continue;
               }

               float f11 = 0.1F;
               GlStateManager.color(f11, f11, f11, 1.0F);
            }

            this.mc.getTextureManager().bindTexture(ACHIEVEMENT_BACKGROUND);
            if (achievement3.getSpecial()) {
               this.drawTexturedModalRect(n20 - 2, n21 - 2, 26, 202, 26, 26);
            } else {
               this.drawTexturedModalRect(n20 - 2, n21 - 2, 0, 202, 26, 26);
            }

            if (!this.statFileWriter.canUnlockAchievement(achievement3)) {
               float f12 = 0.1F;
               GlStateManager.color(f12, f12, f12, 1.0F);
               this.itemRender.setNotRenderingEffectsInGUI(false);
            }

            GlStateManager.enableLighting();
            GlStateManager.enableCull();
            this.itemRender.renderItemAndEffectIntoGUI(achievement3.theItemStack, n20 + 3, n21 + 3);
            GlStateManager.blendFunc(770, 771);
            GlStateManager.disableLighting();
            if (!this.statFileWriter.canUnlockAchievement(achievement3)) {
               this.itemRender.setNotRenderingEffectsInGUI(true);
            }

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            if (f5 >= (float)n20 && f5 <= (float)(n20 + 22) && f6 >= (float)n21 && f6 <= (float)(n21 + 22)) {
               achievement2 = achievement3;
            }
         }
      }

      GlStateManager.disableDepth();
      GlStateManager.enableBlend();
      GlStateManager.popMatrix();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(ACHIEVEMENT_BACKGROUND);
      this.drawTexturedModalRect(n5, n6, 0, 0, this.imageWidth, this.imageHeight);
      this.zLevel = 0.0F;
      GlStateManager.depthFunc(515);
      GlStateManager.disableDepth();
      GlStateManager.enableTexture2D();
      super.drawScreen(n, n2, f);
      if (achievement2 != null) {
         String unformattedText = achievement2.getStatName().getUnformattedText();
         String description = achievement2.getDescription();
         int n23 = n + 12;
         int n24 = n2 - 4;
         int n25 = this.statFileWriter.countRequirementsUntilAvailable(achievement2);
         if (this.statFileWriter.canUnlockAchievement(achievement2)) {
            int n26 = Math.max(this.fontRendererObj.getStringWidth(unformattedText), 120);
            int n27 = this.fontRendererObj.splitStringWidth(description, n26);
            if (this.statFileWriter.hasAchievementUnlocked(achievement2)) {
               n27 += 12;
            }

            this.drawGradientRect(n23 - 3, n24 - 3, n23 + n26 + 3, n24 + n27 + 3 + 12, -1073741824, -1073741824);
            this.fontRendererObj.drawSplitString(description, n23, n24 + 12, n26, -6250336);
            if (this.statFileWriter.hasAchievementUnlocked(achievement2)) {
               this.fontRendererObj.drawStringWithShadow(I18n.format("achievement.taken", new Object[0]), (float)n23, (float)(n24 + n27 + 4), -7302913);
            }
         } else if (n25 == 3) {
            unformattedText = I18n.format("achievement.unknown", new Object[0]);
            int n28 = Math.max(this.fontRendererObj.getStringWidth(unformattedText), 120);
            String unformattedText2 = new ChatComponentTranslation("achievement.requires", new Object[]{achievement2.parentAchievement.getStatName()}).getUnformattedText();
            this.drawGradientRect(n23 - 3, n24 - 3, n23 + n28 + 3, n24 + this.fontRendererObj.splitStringWidth(unformattedText2, n28) + 12 + 3, -1073741824, -1073741824);
            this.fontRendererObj.drawSplitString(unformattedText2, n23, n24 + 12, n28, -9416624);
         } else if (n25 < 3) {
            int n30 = Math.max(this.fontRendererObj.getStringWidth(unformattedText), 120);
            String unformattedText3 = new ChatComponentTranslation("achievement.requires", new Object[]{achievement2.parentAchievement.getStatName()}).getUnformattedText();
            this.drawGradientRect(n23 - 3, n24 - 3, n23 + n30 + 3, n24 + this.fontRendererObj.splitStringWidth(unformattedText3, n30) + 12 + 3, -1073741824, -1073741824);
            this.fontRendererObj.drawSplitString(unformattedText3, n23, n24 + 12, n30, -9416624);
         } else {
            unformattedText = null;
         }

         if (unformattedText != null) {
            this.fontRendererObj
               .drawStringWithShadow(
                  unformattedText,
                  (float)n23,
                  (float)n24,
                  this.statFileWriter.canUnlockAchievement(achievement2) ? (achievement2.getSpecial() ? -128 : -1) : (achievement2.getSpecial() ? -8355776 : -8355712)
               );
         }
      }

      GlStateManager.enableDepth();
      GlStateManager.enableLighting();
      RenderHelper.disableStandardItemLighting();
   }

   public void drawTitle() {
      int n = (this.width - this.imageWidth) / 2;
      int n2 = (this.height - this.imageHeight) / 2;
      this.fontRendererObj.drawString(I18n.format("gui.achievements", new Object[0]), n + 15, n2 + 5, 4210752);
   }

   @Override
   public void updateScreen() {
      if (!this.loadingAchievements) {
         this.xScrollO = this.xScrollP;
         this.yScrollO = this.yScrollP;
         double d = this.xScrollTarget - this.xScrollP;
         double d2 = this.yScrollTarget - this.yScrollP;
         if (d * d + d2 * d2 < 4.0) {
            this.xScrollP += d;
            this.yScrollP += d2;
         } else {
            this.xScrollP += d * 0.85;
            this.yScrollP += d2 * 0.85;
         }
      }
   }

   @Override
   public void initGui() {
      this.mc.getNetHandler().addToSendQueue(new C16PacketClientStatus(C16PacketClientStatus.EnumState.REQUEST_STATS));
      this.buttonList.clear();
      this.buttonList.add(new GuiOptionButton(1, this.width / 2 + 24, this.height / 2 + 74, 80, 20, I18n.format("gui.done", new Object[0])));
   }

   public GuiAchievements(GuiScreen screen, StatFileWriter statFileWriter2) {
      super(screen);
      this.statFileWriter = statFileWriter2;
      this.xScrollO = this.xScrollP = this.xScrollTarget = (double)(AchievementList.openInventory.displayColumn * 24 - ((short)141) / 2 - 12);
      this.yScrollO = this.yScrollP = this.yScrollTarget = (double)(AchievementList.openInventory.displayRow * 24 - ((short)141) / 2);
   }

   @Override
   public boolean doesGuiPauseGame() {
      return !this.loadingAchievements;
   }

   public void doneLoading() {
      if (this.loadingAchievements) {
         this.loadingAchievements = false;
      }
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      if (this.loadingAchievements) {
         this.drawDefaultBackground();
         drawCenteredString(this.fontRendererObj, I18n.format("multiplayer.downloadingStats", new Object[0]), this.width / 2, this.height / 2, 16777215);
         drawCenteredString(
            this.fontRendererObj,
            lanSearchStates[(int)(Minecraft.getSystemTime() / 150L % (long)lanSearchStates.length)],
            this.width / 2,
            this.height / 2 + this.fontRendererObj.FONT_HEIGHT * 2,
            16777215
         );
      } else {
         if (MouseInputUtil.isButtonDown(0)) {
            int n = (this.width - this.imageWidth) / 2;
            int n2 = (this.height - this.imageHeight) / 2;
            int n3 = n + 8;
            int n4 = n2 + 17;
            if ((this.scrolling == 0 || this.scrolling == 1) && mouseX >= n3 && mouseX < n3 + 224 && mouseY >= n4 && mouseY < n4 + 155) {
               if (this.scrolling == 0) {
                  this.scrolling = 1;
               } else {
                  this.xScrollP = this.xScrollP - (double)((float)(mouseX - this.xLastScroll) * this.zoom);
                  this.yScrollP = this.yScrollP - (double)((float)(mouseY - this.yLastScroll) * this.zoom);
                  this.xScrollTarget = this.xScrollO = this.xScrollP;
                  this.yScrollTarget = this.yScrollO = this.yScrollP;
               }

               this.xLastScroll = mouseX;
               this.yLastScroll = mouseY;
            }
         } else {
            this.scrolling = 0;
         }

         int lastDelta = Augustus.getInstance().getMouseWheelTracker().lastDelta;
         float zoom2 = this.zoom;
         if (lastDelta < 0) {
            this.zoom += 0.25F;
         } else if (lastDelta > 0) {
            this.zoom -= 0.25F;
         }

         this.zoom = MathHelper.clamp_float(this.zoom, 1.0F, 2.0F);
         if (this.zoom != zoom2) {
            float f = zoom2 - this.zoom;
            float f2 = zoom2 * (float)this.imageWidth;
            float f3 = zoom2 * (float)this.imageHeight;
            float f4 = this.zoom * (float)this.imageWidth;
            float f5 = this.zoom * (float)this.imageHeight;
            this.xScrollP -= (double)((f4 - f2) * 0.5F);
            this.yScrollP -= (double)((f5 - f3) * 0.5F);
            this.xScrollTarget = this.xScrollO = this.xScrollP;
            this.yScrollTarget = this.yScrollO = this.yScrollP;
         }

         if (this.xScrollTarget < (double)X_MIN) {
            this.xScrollTarget = (double)X_MIN;
         }

         if (this.yScrollTarget < (double)Y_MIN) {
            this.yScrollTarget = (double)Y_MIN;
         }

         if (this.xScrollTarget >= (double)X_MAX) {
            this.xScrollTarget = (double)(X_MAX - 1);
         }

         if (this.yScrollTarget >= (double)Y_MAX) {
            this.yScrollTarget = (double)(Y_MAX - 1);
         }

         this.drawDefaultBackground();
         this.drawAchievementScreen(mouseX, mouseY, partialTicks);
         GlStateManager.disableLighting();
         GlStateManager.disableDepth();
         this.drawTitle();
         GlStateManager.enableLighting();
         GlStateManager.enableDepth();
      }
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      if (keyCode == this.mc.gameSettings.keyBindInventory.getKeyCode()) {
         GuiScreen screen = (GuiScreen)null;
         this.mc.displayGuiScreen(screen);
         this.mc.setIngameFocus();
      } else {
         super.keyTyped(typedChar, keyCode);
      }
   }

}
