package client.gui;

import client.ClientGlobals;
import client.render.RenderPass;
import client.setting.EasingFunction;
import client.setting.ShaderOptions;
import client.util.KeybindAnimation;
import client.util.MinecraftAccessor;
import client.util.MovementHandler;
import client.util.RandomUtil;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.settings.KeyBinding;

public class KeyStrokeRenderer {
   public final String labelText;
   public final KeyBinding keyBinding;
   public final int count3;
   public final int count2;
   public final int count;
   public final int count4;
   public final FontRenderer fontRenderer = MinecraftAccessor.minecraft.fontRendererObj;
   public boolean enabled = false;
   public final KeybindAnimation keybindAnimation = new KeybindAnimation(0.0F, 0.0F, 0.0F, 0.0F);

   public String getLabelText() {
      return this.labelText;
   }

   public KeyBinding getKeyBinding() {
      return this.keyBinding;
   }

   public int getCount3() {
      return this.count3;
   }

   public int getCount() {
      return this.count;
   }

   public void processAction(int count5, int count6) {
      if (this.enabled != this.keyBinding.pressed) {
         this.keybindAnimation
            .updateEasingFunction(
               (int)ClientGlobals.moduleManager.keyStrokes.settingFadeSpeed.getValue(), EasingFunction.easeInOutSine, ((this.keyBinding.pressed) ? 1 : 0), false
            );
      }

      this.enabled = this.keyBinding.pressed;
      float doubleItem = (float)this.keybindAnimation.getDouble3();
      int count7 = RandomUtil.computeCount(
         ClientGlobals.moduleManager.keyStrokes.settingBackGroundColorPressed.getInt12(0),
         ClientGlobals.moduleManager.keyStrokes.settingBackGroundColor.getInt12(0),
         doubleItem
      );
      int count8 = RandomUtil.computeCount(
         ClientGlobals.moduleManager.keyStrokes.settingKeyColorPressed.getInt12(0), ClientGlobals.moduleManager.keyStrokes.settingKeyColor.getInt12(0), doubleItem
      );
      MovementHandler.processBoolean(
         ClientGlobals.moduleManager.keyStrokes.settingY2.canExecute(), ClientGlobals.moduleManager.keyStrokes.settingY2.canExecute2()
      );
      MovementHandler.applyRunnableTask(false, renderPass -> this.applyRenderPassByRenderPass(count5, count6, count7, renderPass));
      this.fontRenderer
         .computeCountByText(
            this.labelText,
            (float)(count5 + this.count3) + (float)this.count / 2.0F - (float)this.fontRenderer.getIndex(this.labelText) / 2.0F + 0.5F,
            (float)(count6 + this.count2) + (float)this.count4 / 2.0F - 3.5F,
            count8
         );
   }

   public int getCount2() {
      return this.count2;
   }

   public int getCount4() {
      return this.count4;
   }

   private void applyRenderPassByRenderPass(int count5, int count6, int count7, RenderPass renderPass) {
      int count8 = count5 + this.count3;
      int count9 = count6 + this.count2;
      int count10 = count5 + this.count3 + this.count;
      int count11 = count6 + this.count2 + this.count4;
      ShaderOptions settingY = ClientGlobals.moduleManager.keyStrokes.settingY2;
      int index = RandomUtil.getIndex(renderPass, count7);
      TexturesGuiIconsPngManager.processAction(count8, count9, count10, count11, settingY.getIndex(renderPass, index));
   }

   public KeyStrokeRenderer(String text, KeyBinding keyBinding2, int count5, int count6, int count7, int count8) {
      this.labelText = text;
      this.keyBinding = keyBinding2;
      this.count3 = count5;
      this.count2 = count6;
      this.count = count7;
      this.count4 = count8;
   }
}
