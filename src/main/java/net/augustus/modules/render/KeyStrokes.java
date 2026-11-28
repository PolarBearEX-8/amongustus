package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.settings.ColorSetting;
import net.augustus.render.RenderUtil;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;

public class KeyStrokes extends Module implements RenderListener {
   public final KeyStrokeTile keyW;
   public final KeyStrokeTile keyA;
   public final KeyStrokeTile keyS;
   public final KeyStrokeTile keyD;
   public final KeyStrokeTile keyLmb;
   public final KeyStrokeTile keyRmb;
   public final ColorSetting backgroundColor;
   public final ColorSetting backgroundColorPressed;
   public final ColorSetting keyColor;
   public final ColorSetting keyColorPressed;
   public final DoubleValue scale;
   public final DoubleValue fadeSpeed;
   public final DoubleValue x;
   public final DoubleValue y;
   public final BlurShadowSettings blurShadowSettings;
   private final java.util.ArrayList tiles;

   public KeyStrokes() {
       super("KeyStrokes", "TastenStreicheln", new Color(95, 218, 222), Categorys.RENDER);
      this.keyW = new KeyStrokeTile("W", mc.gameSettings.keyBindForward, 20, 0, 18, 18);
      this.keyA = new KeyStrokeTile("A", mc.gameSettings.keyBindLeft, 0, 20, 18, 18);
      this.keyS = new KeyStrokeTile("S", mc.gameSettings.keyBindBack, 20, 20, 18, 18);
      this.keyD = new KeyStrokeTile("D", mc.gameSettings.keyBindRight, 40, 20, 18, 18);
      this.keyLmb = new KeyStrokeTile("LMB", mc.gameSettings.keyBindAttack, 0, 40, 28, 18);
      this.keyRmb = new KeyStrokeTile("RMB", mc.gameSettings.keyBindUseItem, 30, 40, 28, 18);
      this.backgroundColor = new ColorSetting(this, "BackGroundColor", new Color(0, 0, 0, 100));
      this.backgroundColorPressed = new ColorSetting(this, "BackGroundColorPressed", new Color(100, 100, 100, 100));
      this.keyColor = new ColorSetting(this, "KeyColor", Color.LIGHT_GRAY);
      this.keyColorPressed = new ColorSetting(this, "KeyColorPressed", Color.white);
      this.scale = new DoubleValue(this, "Scale", 2.0, 1.0, 4.0, 1);
      this.fadeSpeed = new DoubleValue(this, "FadeSpeed", 300.0, 0.0, 1000.0, 0);
      this.x = new DoubleValue(this, "X", 80.0, 0.0, 100.0, 2);
      this.y = new DoubleValue(this, "Y", 80.0, 0.0, 100.0, 2);
      this.blurShadowSettings = new BlurShadowSettings(this).withCustomShadowColor(Color.black);
      this.tiles = new java.util.ArrayList();
      this.tiles.add(this.keyW);
      this.tiles.add(this.keyA);
      this.tiles.add(this.keyS);
      this.tiles.add(this.keyD);
      this.tiles.add(this.keyLmb);
      this.tiles.add(this.keyRmb);
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32);
   }

   public void onRender2DPost(float partialTicks, ScaledResolution sr) {
      GlStateManager.pushMatrix();
      GlStateManager.scale(1.0 / (double)sr.getScaleFactor(), 1.0 / (double)sr.getScaleFactor(), 1.0);
      GlStateManager.scale(this.scale.getValue(), this.scale.getValue(), 1.0);
      double d = (double)mc.displayWidth / this.scale.getValue();
      double d2 = (double)mc.displayHeight / this.scale.getValue();
      int n = MathHelper.ceiling_double_int(d);
      int n2 = MathHelper.ceiling_double_int(d2);
      int n3 = (int)((double)(n - 58) * (this.x.getValue() / 100.0));
      int n4 = (int)((double)(n2 - 58) * (this.y.getValue() / 100.0));

      for (KeyStrokeTile keyStrokeTile : (Iterable<KeyStrokeTile>)(this.tiles)) {
         keyStrokeTile.render(n3, n4);
      }

      GlStateManager.popMatrix();
      RenderUtil.resetGlColorState();
   }

}
