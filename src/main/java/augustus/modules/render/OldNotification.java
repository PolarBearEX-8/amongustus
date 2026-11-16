package augustus.modules.render;

import augustus.custom.RuntimeBridge;
import client.Client;
import client.font.FontRegistry;
import client.gui.ClickGuiScreen;
import client.gui.NotificationEntry;
import client.module.Category;
import client.module.Module;
import client.setting.ColorSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.ShaderOptions;
import client.util.BooleanSupplier;
import client.util.SystemTrayNotifier;
import java.awt.Color;
import java.util.ArrayList;

public class OldNotification extends Module {
    public ModeSetting settingModes;
    public ModeSetting settingSide;
    public NumberSetting settingY;
    public NumberSetting settingAnimationTime;
    public NumberSetting settingAnimationDuration;
    public ColorSetting settingColor;
    public ShaderOptions settingColor2;
    public ArrayList itemList3;
    public static boolean activeState;

    public OldNotification() {
        super("OldNotification", "AlteBenachrichtigung", new Color(126, 133, 222), Category.RENDER);
        String[] textArray = new String[2];
        textArray[0] = "Windows";
        textArray[1] = "Basic";
        this.settingModes = new ModeSetting(this, "Modes", "Windows", textArray);
        String[] textArray2 = new String[2];
        textArray2[0] = "Left";
        textArray2[1] = "Right";
        this.settingSide = new ModeSetting(this, "Side", "Right", textArray2).getModeSetting(() -> this.isSideVisible());
        this.settingY = new NumberSetting(this, "Y", 100.0, 0.0, 100.0, 2).getNumberSetting(() -> this.canExecute6());
        this.settingAnimationTime = new NumberSetting(this, "AnimationTime", 400.0, 0.0, 1000.0, 0).getNumberSetting(() -> this.isAnimationTimeVisible());
        this.settingAnimationDuration = new NumberSetting(this, "AnimationDuration", 2000.0, 0.0, 5000.0, 0).getNumberSetting(() -> this.isAnimationDurationVisible());
        Color color = Client.getClient().getColor();
        this.settingColor = (ColorSetting)new ColorSetting(this, "Color", color).getSetting(() -> this.isColorVisible());
        ShaderOptions shaderOptions = new ShaderOptions(this);
        Color black = Color.black;
        this.settingColor2 = shaderOptions.getShaderOptions(black);
        this.itemList3 = new ArrayList();
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    public void processAction(float value) {
        boolean enabled = false;

        for (NotificationEntry notificationEntry : (Iterable<NotificationEntry>) this.itemList3) {
            if (enabled) {
                this.itemList3.remove(notificationEntry);
            }

            if (Math.abs(System.currentTimeMillis() - RuntimeBridge.getNotificationStartTime(notificationEntry)) > (long)RuntimeBridge.getNotificationDuration(notificationEntry)) {
                this.itemList3.remove(notificationEntry);
            } else {
                RuntimeBridge.renderNotification(notificationEntry, value, this.settingColor2);
                enabled = true;
            }
        }
    }

    private boolean isAnimationTimeVisible() {
        return this.settingModes.getSelected().equals("Basic");
    }

    public static void setActive4(boolean enabled) {
        activeState = enabled;
    }

    public static boolean canExecute5() {
        return !canExecute7();
    }

    public void applyModule2(Module module) {
        String selected = this.settingModes.getSelected();
switch (selected) {
            case "Windows":
                String text = "Module (" + module.getDescription() + ") Notification";
                StringBuilder sb2 = new StringBuilder().append(module.getDescription());
                SystemTrayNotifier.applyString(text, sb2.append(" toggled ").append(module.isToggled() ? "on" : "off").toString());
                return;
            case "Basic":
                if (!(minecraft.currentScreen instanceof ClickGuiScreen)) {
                                String text2 = module.getDescription() + " toggled ";
                                int index = this.settingSide.getSelected().equalsIgnoreCase("Right")
                                ? minecraft.getFramebuffer().indexOffset2 - (int)(FontRegistry.fontFace10.getScaleFactor(text2) + 10.0F)
                                : 0;
                                int index2 = (int)((double)(minecraft.getFramebuffer().indexOffset1 - 60) * (this.settingY.getValue() / 100.0));
                                this.itemList3
                                .add(
                                0,
                                new NotificationEntry(
                                index,
                                index2,
                                (int)(FontRegistry.fontFace10.getScaleFactor(text2) + 10.0F),
                                60,
                                module,
                                (int)this.settingAnimationTime.getValue(),
                                (int)this.settingAnimationDuration.getValue(),
                                this.settingColor.getInt12(0)
                                )
                                );
                            }
                return;
            default:
                return;
        }
    }

    private boolean isAnimationDurationVisible() {
        return this.settingModes.getSelected().equals("Basic");
    }

    private boolean isSideVisible() {
        return this.settingModes.getSelected().equals("Basic");
    }

    private boolean isColorVisible() {
        return this.settingModes.getSelected().equals("Basic");
    }

    private boolean canExecute6() {
        return this.settingModes.getSelected().equals("Basic");
    }

    public static boolean canExecute7() {
        return activeState;
    }
}
