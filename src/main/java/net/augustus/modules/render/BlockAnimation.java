package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;

public class BlockAnimation extends Module {
   public final StringValue blockAnimations;
   public final DoubleValue height;
   public final BooleanValue fakeBlock;
   public final BooleanValue onlyWhileKillAura;
   public final DoubleValue speed;

   public BlockAnimation() {
       super("BlockAnimation", "BlockAnimation", new Color(136, 29, 163), Categorys.RENDER);
      this.blockAnimations = new StringValue(
         this,
         "BlockAnimations",
         "Minimalistic",
         new String[]{"None", "1.7", "Wiggle", "Minimalistic", "Swong", "Jello", "Exhibition", "Exhibition2", "Forward", "Jigsaw", "Violence", "Sigma", "Spin"}
      );
      this.height = new DoubleValue(this, "Height", 0.1, -0.5, 1.5, 3);
      this.fakeBlock = new BooleanValue(this, "FakeBlock", false);
      BooleanValue booleanValue = new BooleanValue(this, "OnlyWhileKillAura", false);
      BooleanValue fakeBlock2 = this.fakeBlock;
      this.onlyWhileKillAura = (BooleanValue)booleanValue.withVisibility(() -> fakeBlock2.getValue());
      this.speed = new DoubleValue(this, "Speed", 1.0, 0.1, 3.0, 3);
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
