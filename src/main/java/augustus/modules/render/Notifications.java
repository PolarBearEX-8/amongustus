package augustus.modules.render;

import client.Client;
import client.ClientGlobals;
import client.event.RenderListener;
import client.gui.ClientNotification;
import client.module.Category;
import client.module.Module;
import client.setting.BeverageNotifyModuleTogglesType;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.util.BeverageItemNotification;
import client.util.BuildManager;
import client.util.ClientTickEventSync;
import client.util.RandomUtil;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.MathHelper;

public class Notifications extends Module implements RenderListener {
    public static final ClientTickEventSync minecraftEntry = new ClientTickEventSync();
    public static final String[] stringArray = new String[]{"Client color", "Notification type", "Fade", "Blank"};
    public BooleanSetting settingBlur = new BooleanSetting(this, "Blur", true);
    public BooleanSetting settingAvoidDuplications = new BooleanSetting(this, "AvoidDuplications", true);

    public ModeSetting settingPosition = new ModeSetting(
        this, "Position", "Bottom right", new String[]{"Top right", "Top left", "Bottom right", "Bottom left"}
    );
    public NumberSetting settingDuplicateTime = new NumberSetting(this, "DuplicateTime", 0.2F, 0.0, 1.0, 1);
    public NumberSetting settingShowDuration = new NumberSetting(this, "ShowDuration", 2.5, 0.0, 10.0, 1);
    public NumberSetting settingSpeed = new NumberSetting(this, "Speed", 5.0, 1.0, 10.0, 0);
    public ModeSetting settingGroupColorMode = new ModeSetting(this, "GroupColorMode", "Blank", stringArray);
    public ModeSetting settingProgressColorMode = new ModeSetting(this, "ProgressColorMode", "Notification type", stringArray);

    public MultiSelectSetting settingNotificationTypes = new MultiSelectSetting(
        this, "NotificationTypes", BeverageNotifyModuleTogglesType.values()
    );
    public static boolean activeState;

    public Notifications() {
        super("Notifications", "Benachrichtigungen", Color.WHITE, Category.RENDER);
        this.processBoolean(true, false);
    }

    @Override
    public void onEnable() {
        this.registerEvent(32, Integer.MAX_VALUE);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        this.processBoolean2(false);
    }

    public static void setActive4(boolean enabled) {
        activeState = enabled;
    }

    public static boolean canExecute5() {
        return activeState;
    }

    public int getIndex(ClientNotification extendedItemEntry, String text) {
switch (text) {
            case "Client color":
                return Client.getClient().getColor().getRGB();
            case "Notification type":
                return extendedItemEntry.getBeverageNotifyInfoType().getColor().getRGB();
            case "Fade":
                long time2 = extendedItemEntry.timer2.getTime();
                return RandomUtil.getIndex(
                    Color.RED, Color.GREEN, (double)(1.0F - MathHelper.clamp_float((float)time2 / (float)extendedItemEntry.getIndex(), 0.0F, 1.0F))
                );
            default:
                return Color.GRAY.getRGB();
        }
    }

    public static boolean canExecute6() {
        return !canExecute5();
    }

    public void processBoolean2(boolean enabled) {
        if (!BeverageItemNotification.canExecute()) {
            BeverageItemNotification.processChar();
            if (this.settingBlur.isEnabled() && !Minecraft.state3 && !BuildManager.canExecute3() && !BuildManager.canExecute4() && !BuildManager.canExecute2()) {
                minecraftEntry.setActive(true, enabled);
                BeverageItemNotification.updateState();
                minecraftEntry.updateState();
                minecraftEntry.processBoolean(enabled, (int)ClientGlobals.moduleManager.blur.settingSigma.getValue());
            }

            BeverageItemNotification.updateState();
        }
    }
}
