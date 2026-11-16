package augustus.modules.render;

import client.event.RenderListener;
import client.module.Category;
import client.module.Module;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.ShaderOptions;
import client.util.BooleanSupplier;
import client.util.MovementHandler;
import client.util.PlayerUtil;
import client.util.RandomUtil;
import java.awt.Color;
import java.util.Arrays;
import java.util.Objects;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class ArmorStatus extends Module implements RenderListener {
    public NumberSetting settingPosX;
    public NumberSetting settingPosY;
    public NumberSetting settingScale;
    public ModeSetting settingDurabilityMode;
    public ColorSetting settingDurabilityColor;
    public BooleanSetting settingBackGround;
    public ColorSetting settingBackgroundColor;
    public ShaderOptions settingBackgroundColor2;
    public BooleanSetting settingDynamicArmorRendering;

    public ArmorStatus() {
        super("ArmorStatus", "Rüstungszustand", new Color(125, 255, 203), Category.RENDER);
        this.settingPosX = new NumberSetting(this, "PosX", 0.0, 0.0, 100.0, 2);
        this.settingPosY = new NumberSetting(this, "PosY", 0.0, 0.0, 100.0, 2);
        this.settingScale = new NumberSetting(this, "Scale", 2.0, 0.5, 4.0, 2);
        String[] textArray = new String[3];
        textArray[0] = "None";
        textArray[1] = "Percentage";
        textArray[2] = "Absolute";
        this.settingDurabilityMode = new ModeSetting(this, "DurabilityMode", "Percentage", textArray);
        this.settingDurabilityColor = (ColorSetting)new ColorSetting(this, "DurabilityColor", Color.white).getSetting(() -> this.isDurabilityColorVisible());
        this.settingBackGround = new BooleanSetting(this, "BackGround", false);
        Color color = new Color(0, 0, 0, 120);
        this.settingBackgroundColor = (ColorSetting)new ColorSetting(this, "BackgroundColor", color).getSetting(() -> this.settingBackGround.isEnabled());
        this.settingBackgroundColor2 = new ShaderOptions(this);
        this.settingDynamicArmorRendering = new BooleanSetting(this, "DynamicArmorRendering", true);
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
        GL11.glPushMatrix();
        GL11.glScaled((double)(1.0F / (float)scaledResolution.getScaleFactor()), (double)(1.0F / (float)scaledResolution.getScaleFactor()), 1.0);
        String[] stringArray = this.getStringArray();
        int index = this.getIndex(stringArray);
        int index2 = this.getInt2() * 20;
        double value = this.settingScale.getValue();
        GL11.glTranslated(
            ((double)minecraft.displayWidth - (double)(22 + index) * value) * (this.settingPosX.getValue() / 100.0),
            ((double)minecraft.displayHeight - (double)index2 * value) * (this.settingPosY.getValue() / 100.0),
            0.0
        );
        GL11.glScaled(value, value, 1.0);
        this.applyStringArray3(stringArray, index, index2);
        GL11.glPopMatrix();
        RenderUtils.processAction();
    }

    private void applyRenderPass(int index, int index2, RenderPass renderPass) {
        int index3 = 22 + index;
        int int12 = this.settingBackgroundColor.getInt12(0);
        RenderUtils.processAction(0, 0, index3, index2, RandomUtil.getIndex(renderPass, int12));
    }

    private String[] getStringArray() {
        String[] textArray = new String[minecraft.thePlayer.inventoryPlayer.armorInventory.length];

        for (int i = 0; i < minecraft.thePlayer.inventoryPlayer.armorInventory.length; i++) {
            ItemStack itemStack = minecraft.thePlayer.inventoryPlayer.armorInventory[i];
            if (itemStack != null) {
                textArray[i] = this.getText(itemStack);
            }
        }

        return textArray;
    }

    private void applyString(String text, int index) {
        if (text != null) {
            minecraft.fontRendererObj.getIndex(text, 21, index, this.settingDurabilityColor.getInt12(0));
        }
    }

    private String getText(ItemStack itemStack) {
        String selected = this.settingDurabilityMode.getSelected();
switch (selected) {
            case "Percentage":
                StringBuilder stringBuilder = new StringBuilder();
                double offset = 100.0 - (double)itemStack.getIndexOffset2() / (double)itemStack.processAction() * 100.0;
                return stringBuilder.append(PlayerUtil.getString(offset, 1)).append("%").toString();
            case "Absolute":
                return String.valueOf(itemStack.processAction() - itemStack.getIndexOffset2());
            default:
                return null;
        }
    }

    private void applyStringArray(String[] textArray, int index, int index2, RenderPass renderPass) {
        this.applyStringArray2(textArray, index, index2);
    }

    private int getIndex(String[] textArray) {
        int index = 0;

        for (String text : textArray) {
            if (text != null) {
                int index2 = minecraft.fontRendererObj.getIndex(text) + 3;
                if (index2 > index) {
                    index = index2;
                }
            }
        }

        return index;
    }

    private int getInt2() {
        int index = (int)Arrays.stream(minecraft.thePlayer.inventoryPlayer.armorInventory).filter(itemStack -> Objects.nonNull(itemStack)).count();
        if (this.settingDynamicArmorRendering.isEnabled()) {
            return index;
        } else {
            return index > 0 ? minecraft.thePlayer.inventoryPlayer.armorInventory.length : 0;
        }
    }

    private void applyStringArray2(String[] textArray, int index, int index2) {
        RenderUtils.applyItemStack(minecraft.thePlayer.getItemStack3(index), 3, index2 + 2, false);
        this.applyString(textArray[index], index2 + 6);
    }

    private boolean isDurabilityColorVisible() {
        return !this.settingDurabilityMode.getSelected().equals("None");
    }

    private void applyStringArray3(String[] textArray, int index2, int index3) {
        if (this.settingBackGround.isEnabled()) {
            MovementHandler.processBoolean(
                this.settingBackgroundColor2.canExecute(), this.settingBackgroundColor2.canExecute2()
            );
            MovementHandler.applyRunnableTask(false, renderPass -> this.applyRenderPass(index2, index3, renderPass));
        }

        GlStateManager.disableTexture2D();
        byte state = 0;

        for (int i = textArray.length - 1; i >= 0; i--) {
            this.applyStringArray4(textArray, i, state);
            if (textArray[i] != null || !this.settingDynamicArmorRendering.isEnabled()) {
                state = (byte)(state + 20);
            }
        }
    }

    private void applyStringArray4(String[] textArray, int index, int index2) {
        if (this.checkCondition(index)) {
            if (this.settingBackGround.isEnabled()) {
                this.applyStringArray2(textArray, index, index2);
            } else {
                MovementHandler.processBoolean(
                    this.settingBackgroundColor2.canExecute(), this.settingBackgroundColor2.canExecute2()
                );
                MovementHandler.applyRunnableTask(false, renderPass -> this.applyStringArray(textArray, index, index2, renderPass));
            }
        }
    }

    private boolean checkCondition(int index) {
        return minecraft.thePlayer.getItemStack3(index) != null;
    }
}
