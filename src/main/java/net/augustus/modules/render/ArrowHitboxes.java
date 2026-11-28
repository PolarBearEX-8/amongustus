package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.ColorSetting;
import net.augustus.settings.DoubleValue;

public class ArrowHitboxes extends Module {
   public final ColorSetting boxColor;
   public final DoubleValue lineWidth;

   public ArrowHitboxes() {
       super("ArrowHitboxes", "PfeilTrefferkasten", new Color(153, 0, 153), Categorys.RENDER);
      this.boxColor = new ColorSetting(this, "BoxColor", Color.white);
      this.lineWidth = new DoubleValue(this, "LineWidth", 2.0, 1.0, 10.0, 1);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
   }
}
