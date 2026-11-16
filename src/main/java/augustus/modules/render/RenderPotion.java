package augustus.modules.render;

import client.event.RenderListener;
import client.module.Category;
import client.module.Module;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.ColorSetting;
import client.setting.NumberSetting;
import client.setting.ShaderOptions;
import client.util.MovementHandler;
import client.util.RandomUtil;
import java.awt.Color;
import java.util.Collection;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class RenderPotion extends Module implements RenderListener {
    public NumberSetting settingXCoord = new NumberSetting(this, "X-Coord", 0.0, 0.0, 100.0, 2);
    public NumberSetting settingYCoord = new NumberSetting(this, "Y-Coord", 0.0, 0.0, 100.0, 2);
    public NumberSetting settingScale = new NumberSetting(this, "Scale", 2.0, 0.5, 4.0, 2);
    public NumberSetting settingMargin = new NumberSetting(this, "Margin", 1.0, 0.0, 10.0, 0);
    public ColorSetting settingBackgroundColor = new ColorSetting(this, "BackgroundColor", new Color(0, 0, 0, 150));
    public ShaderOptions settingBackgroundColor2;

    public RenderPotion() {
        super("RenderPotion", "ZeichneTränke", new Color(123, 252, 3), Category.RENDER);
        ShaderOptions shaderOptions = new ShaderOptions(this);
        Color black = Color.black;
        this.settingBackgroundColor2 = shaderOptions.getShaderOptions(black);
    }

    @Override
    public void onEnable() {
        this.registerEvent(32);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        Collection collection = minecraft.thePlayer.getCollection();
        GL11.glPushMatrix();
        GL11.glScaled((double)(1.0F / (float)scaledResolution.getScaleFactor()), (double)(1.0F / (float)scaledResolution.getScaleFactor()), 1.0);
        GL11.glScaled(this.settingScale.getValue(), this.settingScale.getValue(), 1.0);
        ScaledResolution scaledResolution2 = new ScaledResolution(
            (double)minecraft.displayWidth * (1.0 / this.settingScale.getValue()),
            (double)minecraft.displayHeight * (1.0 / this.settingScale.getValue()),
            (int)this.settingScale.getValue()
        );
        if (!collection.isEmpty()) {
            int index = (int)((double)32 + this.settingMargin.getValue());
            int index2 = (int)((double)(scaledResolution2.getScaledWidth() - 142) * (this.settingXCoord.getValue() / 100.0));
            int index3 = (int)((double)(scaledResolution2.getScaledHeight() - (index * (collection.size() - 1) + 32)) * (this.settingYCoord.getValue() / 100.0));
            GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.applyState3();
            GlStateManager.applyState14();
            int int12 = this.settingBackgroundColor.getInt12(0);

            for (PotionEffect potionEffect : ((Iterable<PotionEffect>) minecraft.thePlayer.getCollection())) {
                Potion potion = Potion.potionArray[potionEffect.getIndexOffset3()];
                if (potion != null) {
                    MovementHandler.processBoolean(
                        this.settingBackgroundColor2.canExecute(), this.settingBackgroundColor2.canExecute2()
                    );
                    int index4 = index3;
                    int index5 = index3;
                    MovementHandler.applyRunnableTask(false, renderPass -> this.applyRenderPass(index2, index5, index4, 32, int12, renderPass));
                    GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
                    if (potion.canExecute()) {
                        minecraft.getTextureManager().applyResourceLocation3(GuiInventory.resourceLocation4);
                        int indexOffset = potion.getIndexOffset();
                        minecraft.ingameGUI.processAction(index2 + 6, index3 + 7, 0 + indexOffset % 8 * 18, 198 + indexOffset / 8 * 18, 18, 18);
                    }

                    String text = StatCollector.getText(potion.getText(), new Object[0]);
                    if (potionEffect.getIndexOffset2() == 1) {
                        text = text + " " + StatCollector.getText("enchantment.level.2", new Object[0]);
                    } else if (potionEffect.getIndexOffset2() == 2) {
                        text = text + " " + StatCollector.getText("enchantment.level.3", new Object[0]);
                    } else if (potionEffect.getIndexOffset2() == 3) {
                        text = text + " " + StatCollector.getText("enchantment.level.4", new Object[0]);
                    }

                    minecraft.fontRendererObj.getIndex2(text, (float)(index2 + 10 + 18), (float)(index3 + 6), 16777215);
                    String text2 = Potion.getText(potionEffect);
                    minecraft.fontRendererObj.getIndex2(text2, (float)(index2 + 10 + 18), (float)(index3 + 6 + 10), 8355711);
                    index3 += index;
                }
            }
        }

        GL11.glPopMatrix();
        RenderUtils.processAction();
    }

    private void applyRenderPass(int x, int x2, int x3, int y, int z, RenderPass renderPass) {
        int index2 = x + 140;
        int index3 = x3 + y;
        int index = RandomUtil.getIndex(renderPass, z);
        RenderUtils.processAction(x, x2, index2, index3, this.settingBackgroundColor2.getIndex(renderPass, index));
    }
}
