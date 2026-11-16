package augustus.modules.render;

import client.event.EventGuiOpen;
import client.event.EventGuiOpenCallback;
import client.gui.TexturesGuiIconsPngManager;
import client.module.Category;
import client.module.Module;
import client.render.RenderPass;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.CustomGuiBGColorScreenType;
import client.setting.MultiSelectSetting;
import client.setting.SettingGroup;
import client.util.MovementHandler;
import java.awt.Color;
import java.lang.reflect.Array;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

public class CustomGuiBGColor extends Module implements EventGuiOpenCallback {
    public CustomGuiBGColor() {
        super("CustomGuiBGColor", "EinstellbareFenserHintergrundfarbe", new Color(235, 190, 127), Category.RENDER);
        CustomGuiBGColorScreenType[] customGuiBGColorScreenTypeArray = CustomGuiBGColorScreenType.values();
        new MultiSelectSetting(this, "Guis", customGuiBGColorScreenTypeArray);
        CustomGuiBGColorScreenType[] customGuiBGColorScreenTypeArray2 = CustomGuiBGColorScreenType.values();
        int length = Array.getLength(customGuiBGColorScreenTypeArray2);

        for (int i = 0; i < length; i++) {
            Object object = customGuiBGColorScreenTypeArray2[i];
            String text = ((CustomGuiBGColorScreenType)object).getText();
            SettingGroup settingGroup = new SettingGroup(this, text, true);
            StringBuilder stringBuilder = new StringBuilder();
            String text2 = ((CustomGuiBGColorScreenType)object).getText();
            String text3 = stringBuilder.append(text2).append("Color").toString();
            Color color = new Color(0, 0, 0, 50);
            ColorSetting colorSetting = new ColorSetting(this, text3, color, settingGroup);
            ColorSetting colorSetting2 = (ColorSetting)colorSetting.getSetting("Color");
            CustomGuiBGColorScreenType.getColorSetting((CustomGuiBGColorScreenType)object, colorSetting2);
            StringBuilder stringBuilder2 = new StringBuilder();
            String text4 = ((CustomGuiBGColorScreenType)object).getText();
            String text5 = stringBuilder2.append(text4).append("Blur").toString();
            BooleanSetting booleanSetting = new BooleanSetting(this, text5, false, settingGroup);
            BooleanSetting setting = (BooleanSetting)booleanSetting.getSetting("Blur");
            CustomGuiBGColorScreenType.getBooleanSetting((CustomGuiBGColorScreenType)object, setting);
        }
    }

    @Override
    public void onEnable() {
        this.registerEvent(54);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onGuiOpen(EventGuiOpen event) {
        GuiScreen guiScreen = event.getGuiScreen();

        for (CustomGuiBGColorScreenType customGuiBGColorScreenTypeKind : CustomGuiBGColorScreenType.values()) {
            if (customGuiBGColorScreenTypeKind.isSelected() && (Boolean)CustomGuiBGColorScreenType.getFunction(customGuiBGColorScreenTypeKind).apply(guiScreen)) {
                boolean enabled = MovementHandler.canExecute();
                MovementHandler.processAction12();
                int stateInt11 = GlStateManager.getStateInt11();
                float stateFloat = GlStateManager.getStateFloat();
                GlStateManager.alphaFunc(stateInt11, 0.0F);
                MovementHandler.processBoolean(CustomGuiBGColorScreenType.getBooleanSetting(customGuiBGColorScreenTypeKind).isEnabled(), false);
                MovementHandler.applyRunnableTask(false, renderPass -> applyGuiScreen(guiScreen, customGuiBGColorScreenTypeKind, renderPass));
                GlStateManager.alphaFunc(stateInt11, stateFloat);
                if (enabled) {
                    MovementHandler.processAction5();
                }

                event.cancel();
                return;
            }
        }
    }

    private static void applyGuiScreen(GuiScreen guiScreen, CustomGuiBGColorScreenType customGuiBGColorScreenTypeKind, RenderPass renderPass) {
        if (renderPass == RenderPass.BLUR) {
            TexturesGuiIconsPngManager.processAction(0, 0, guiScreen.width, guiScreen.height, -1);
        } else {
            int width = guiScreen.width;
            int height = guiScreen.height;
            ColorSetting colorSetting = CustomGuiBGColorScreenType.getColorSetting(customGuiBGColorScreenTypeKind);
            TexturesGuiIconsPngManager.processAction(0, 0, width, height, colorSetting.getInt12(0));
        }
    }
}
