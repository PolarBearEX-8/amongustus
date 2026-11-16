package augustus.modules.render;

import client.Client;
import client.event.EventWorldLoad;
import client.event.WorldLoadListener;
import client.module.Category;
import client.module.Module;
import client.setting.ModeSetting;
import client.util.BooleanSupplier;
import client.util.ConsumerTask;
import java.awt.Color;
import net.minecraft.client.Minecraft;

public class CustomName extends Module implements WorldLoadListener {
    public ModeSetting settingModes;
    public ModeSetting settingNameFormatting;
    public ModeSetting settingCusom;
    public String cachedUsername;
    public String displayName2;

    public CustomName() {
        super("CustomName", "KundenspezifischerName", new Color(63, 21, 21), Category.RENDER);
        this.settingModes = new ModeSetting(this, "Modes", "AugustusName", new String[]{"AugustusName", "IngameName", "PCName", "Custom"}).getModeSetting(() -> this.onModesAction());
        this.settingNameFormatting = new ModeSetting(
            this, "Name-Formatting", "Xanax", new String[]{"Xanax", "Xanax2", "Centaurus", "Centaurus2", "Skid", "Simple"}
        ).getModeSetting(() -> this.onModesAction());
        this.settingCusom = new ModeSetting(this, "Cusom", "CustomName", new String[]{""}).getModeSetting(() -> canExecute5());
        this.cachedUsername = System.getProperty("user.name");
    }

    @Override
    public void onEnable() {
        this.registerEvent(17);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        this.onModesAction();
    }

    public void onModesAction() {
        String selected = this.settingModes.getSelected();
        switch (selected) {
            case "AugustusName":
                Client client = Client.getClient();
                this.displayName2 = client.getString7();
                break;
            case "IngameName":
                this.displayName2 = minecraft.getSession().getText1();
                break;
            case "PCName":
                this.displayName2 = this.cachedUsername;
                break;
            case "Custom":
                this.displayName2 = this.settingCusom.getSelected();
        }
    }

    private static boolean canExecute5() {
        return false;
    }
}
