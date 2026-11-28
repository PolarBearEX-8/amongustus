package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.ClientManagers;
import net.augustus.render.RenderUtil;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;

public class Scoreboard extends Module implements RenderListener {
   public final BooleanValue remove;
   public final BooleanValue stick;
   public final BooleanValue transparent;
   public final BooleanValue fontShadow;
   public final BooleanValue rounded;
   public final DoubleValue roundFactor;
   public final DoubleValue positionX;
   public final DoubleValue positionY;
   public final DoubleValue scale;
   public final BlurShadowSettings blurShadowSettings;

   public Scoreboard() {
       super("Scoreboard", "Spielstandanzeige", new Color(34, 162, 162), Categorys.RENDER);
      this.remove = new BooleanValue(this, "Remove", false);
      this.stick = (BooleanValue)new BooleanValue(this, "Stick", false).withVisibility(() -> this.isStickVisible());
      this.transparent = (BooleanValue)new BooleanValue(this, "Transparent", false).withVisibility(() -> this.isTransparentVisible());
      this.fontShadow = (BooleanValue)new BooleanValue(this, "FontShadow", false).withVisibility(() -> this.isFontShadowVisible());
      this.rounded = (BooleanValue)new BooleanValue(this, "Rounded", false).withVisibility(() -> this.isRoundedVisible());
      this.roundFactor = new DoubleValue(this, "RoundFactor", 3.0, 0.0, 8.0, 1).visibleWhen(() -> this.isRoundFactorVisible());
      this.positionX = new DoubleValue(this, "PositionX", 100.0, 0.0, 100.0, 1).visibleWhen(() -> this.isPositionXVisible());
      DoubleValue positionYSetting = new DoubleValue(this, "PositionY", 60.0, 0.0, 100.0, 1);
      SettingVisibilityCondition condition7 = () -> {
         return !this.remove.getValue() && !this.stick.getValue();
      };
      this.positionY = positionYSetting.visibleWhen(condition7);
      this.scale = new DoubleValue(this, "Scale", 2.0, 1.0, 4.0, 2).visibleWhen(() -> this.isScaleVisible());
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
      if (!this.remove.getValue() && !ClientManagers.moduleManager.antiStrike.shouldHideScoreboard()) {
         GlStateManager.pushMatrix();
         ScaledResolution sr2 = new ScaledResolution(mc);
         GlStateManager.scale(1.0F / (float)sr2.getScaleFactor(), 1.0F / (float)sr2.getScaleFactor(), 1.0F);
         GlStateManager.scale(this.scale.getValue(), this.scale.getValue(), 1.0);
         GlStateManager.enableAlpha();
         GlStateManager.alphaFunc(516, 0.0F);
         ScaledResolution sr3 = new ScaledResolution(
            (double)mc.displayWidth * (1.0 / this.scale.getValue()), (double)mc.displayHeight * (1.0 / this.scale.getValue()), (int)this.scale.getValue()
         );
         net.minecraft.scoreboard.Scoreboard scoreboard = mc.theWorld.getScoreboard();
         if (scoreboard != null) {
            ScoreObjective scoreObjective = null;
            ScorePlayerTeam playersTeam = scoreboard.getPlayersTeam(mc.thePlayer.getName());
            if (playersTeam != null) {
               int colorIndex = playersTeam.getChatFormat().getColorIndex();
               if (colorIndex >= 0) {
                  scoreObjective = scoreboard.getObjectiveInDisplaySlot(3 + colorIndex);
               }
            }

            ScoreObjective scoreObjective2 = scoreObjective != null ? scoreObjective : scoreboard.getObjectiveInDisplaySlot(1);
            if (scoreObjective2 != null) {
               mc.ingameGUI.renderScoreboard(scoreObjective2, sr3, this.transparent.getValue(), this.fontShadow.getValue());
            }
         }

         GlStateManager.popMatrix();
         RenderUtil.resetGlColorState();
      }
   }

   private boolean isBlurShadowVisible() {
      return !this.remove.getValue();
   }

   private boolean isScaleVisible() {
      return !this.remove.getValue();
   }

   private boolean isPositionXVisible() {
      return !this.remove.getValue();
   }

   private boolean isRoundFactorVisible() {
      return !this.remove.getValue() && this.rounded.getValue();
   }

   private boolean isRoundedVisible() {
      return !this.remove.getValue();
   }

   private boolean isFontShadowVisible() {
      return !this.remove.getValue();
   }

   private boolean isTransparentVisible() {
      return !this.remove.getValue();
   }

   private boolean isStickVisible() {
      return !this.remove.getValue();
   }

}
