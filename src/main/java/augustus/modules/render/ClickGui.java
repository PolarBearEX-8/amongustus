package augustus.modules.render;

import client.Client;
import client.ClientGlobals;
import client.event.EventClickGuiListener;
import client.event.TickListener;
import client.font.FontFace;
import client.gui.ClickGuiScreen;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.DynamicModeSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.SettingsDescriptionsManager;
import client.setting.SettingsNamesManager;
import client.setting.ShaderOptions;
import client.util.ConsumerTask;
import java.awt.Color;
import java.util.Comparator;
import java.util.concurrent.CompletableFuture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;

public class ClickGui extends Module implements EventClickGuiListener, TickListener {
    public ModeSetting settingSorting;
    public BooleanSetting settingGermanMode;
    public BooleanSetting settingOpenInInventory;
    public ShaderOptions settingClickGui;
    public BooleanSetting settingOutline;
    public NumberSetting settingAlpha;
    public NumberSetting settingDynamicKeyTime;
    public ModeSetting settingDescriptions;
    public ModeSetting settingSettingNames;
    public BooleanSetting settingModuleDescriptions;
    public BooleanSetting settingSettingDescriptions;
    public CompletableFuture completableFuture;
    public static Setting[] settingsArray;

    public ClickGui() {
        super("ClickGui", "KlickbareGrafischeBenutzeroberfläche", Color.BLACK, Category.RENDER);
        this.settingSorting = new ModeSetting(this, "Sorting", "Alphabet", new String[]{"Random", "Length", "Alphabet"}).getModeSetting(() -> this.onSortingAction2());
        BooleanSetting booleanSetting = new BooleanSetting(this, "GermanMode", false);
        this.settingGermanMode = (BooleanSetting)booleanSetting.getSetting(() -> this.onSortingAction());
        this.settingOpenInInventory = new BooleanSetting(this, "OpenInInventory", true);
        ShaderOptions shaderOptions = new ShaderOptions(this, "ClickGui");
        Color black = Color.black;
        this.settingClickGui = shaderOptions.getShaderOptions(black);
        this.settingOutline = new BooleanSetting(this, "Outline", true);
        this.settingAlpha = new NumberSetting(this, "Alpha", 200.0, 0.0, 255.0, 0);
        this.settingDynamicKeyTime = new NumberSetting(this, "DynamicKeyTime", 500.0, 0.0, 2000.0, 0);
        this.settingDescriptions = new ModeSetting(this, "Descriptions", "Default", new String[]{"Default"}).getModeSetting(() -> this.onDescriptionsAction());
        this.settingSettingNames = new ModeSetting(this, "SettingNames", "English_Default", new String[]{"English_Default", "English_New"}).getModeSetting(() -> this.onSettingNamesAction());
        this.settingModuleDescriptions = new BooleanSetting(this, "ModuleDescriptions", true);
        this.settingSettingDescriptions = new BooleanSetting(this, "SettingDescriptions", true);
        this.completableFuture = null;
        this.unregisterEvent(54);
        this.setState2(false);
    }

    @Override
    public void onEnable() {
        this.registerEvent(8);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
        Client.getClient().getAltItemEntry().updateState();
    }

    @Override
    public void onPreDisable() {
        if (minecraft.currentScreen instanceof GuiContainer && !this.settingOpenInInventory.isEnabled()) {
            this.onReset();
        } else {
            if (minecraft.thePlayer != null && minecraft.theWorld != null && (this.completableFuture == null || this.completableFuture.isDone())) {
                this.completableFuture = null;
            }

            if (this.completableFuture == null) {
                this.completableFuture = CompletableFuture.runAsync(() -> this.updateState2());
            }

            Client.getClient().getAltItemEntry().updateState();
            Client.getClient().getThreadEntry().updateState2();
            String[] stringArray = Client.getClient().getSettingsDescriptionsManager().getStringArray();
            this.settingDescriptions.applyStringArray2(stringArray);
            SettingsDescriptionsManager settingsDescriptionsManager = Client.getClient().getSettingsDescriptionsManager();
            String selected = this.settingDescriptions.getSelected();
            String[] options = this.settingDescriptions.getOptions();
            String text = settingsDescriptionsManager.getText(selected, options);
            this.settingDescriptions.setSelected(text);
            String[] stringArray2 = Client.getClient().getSettingsNamesManager().getStringArray();
            this.settingSettingNames.applyStringArray2(stringArray2);
            SettingsNamesManager settingsSettingsnamesManager = Client.getClient().getSettingsNamesManager();
            String selected2 = this.settingSettingNames.getSelected();
            String[] options2 = this.settingSettingNames.getOptions();
            String text2 = settingsSettingsnamesManager.getText(selected2, options2);
            this.settingSettingNames.setSelected(text2);
            if (minecraft.thePlayer != null) {
                if (minecraft.theWorld != null) {
                    if (minecraft.currentScreen != null) {
                        minecraft.thePlayer.updateState5();
                    }

                    ClientGlobals.moduleManager.customCape.updateState2();
                    ClickGuiScreen clickGuiScreen = Client.getClient().getClickGuiScreen();
                    minecraft.displayGuiScreen(clickGuiScreen);
                }
            }
        }
    }

    @Override
    public void processAction() {
        for (Module module : ((Iterable<Module>) ClientGlobals.moduleManager.getCopyOnWriteArrayList2())) {
            String text;
            if (this.settingGermanMode.isEnabled()) {
                text = module.getName();
            } else {
                text = module.getString();
            }

            module.applyString3(text);
        }
    }

    @Override
    public void onRunTick() {
        if (!(minecraft.currentScreen instanceof ClickGuiScreen)) {
            if (this.isToggled()) {
                this.onReset();
            }
        }
    }

    private void updateState() {
        SettingsDescriptionsManager settingsDescriptionsManager = Client.getClient().getSettingsDescriptionsManager();
        String selected = this.settingDescriptions.getSelected();
        settingsDescriptionsManager.applyString(selected);
    }

    private void updateState2() {
        SettingsDescriptionsManager settingsDescriptionsManager = Client.getClient().getSettingsDescriptionsManager();
        String selected = this.settingDescriptions.getSelected();
        settingsDescriptionsManager.applyString(selected);
        SettingsNamesManager settingsSettingsnamesManager = Client.getClient().getSettingsNamesManager();
        String selected2 = this.settingSettingNames.getSelected();
        settingsSettingsnamesManager.applyString(selected2);
        ClientGlobals.moduleManager.fly.updateState();
        ClientGlobals.moduleManager.speed.updateState();
        DynamicModeSetting.itemList2.forEach(object -> ((Runnable)object).run());
    }

    private void onDescriptionsAction() {
        CompletableFuture.runAsync(() -> this.updateState());
    }

    private static double getCoordinate(Module module) {
        FontFace fontFace2 = Client.getClient().getClickGuiScreen().getClickGuiPanel().getFontFace2();
        String description = module.getDescription();
        return (double)(1000.0F - fontFace2.getScaleFactor(description));
    }

    public static void setSettingArray(Setting[] settingArray2) {
        settingsArray = settingArray2;
    }

    private void onSortingAction() {
        for (Module module : ((Iterable<Module>) ClientGlobals.moduleManager.getCopyOnWriteArrayList2())) {
            String text;
            if (this.settingGermanMode.isEnabled()) {
                text = module.getName();
            } else {
                text = module.getString();
            }

            module.applyString3(text);
        }
    }

    public static Setting[] getSettingArray() {
        return settingsArray;
    }

    private void updateState3() {
        SettingsNamesManager settingsSettingsnamesManager = Client.getClient().getSettingsNamesManager();
        String selected = this.settingSettingNames.getSelected();
        settingsSettingsnamesManager.applyString(selected);
    }

    private void onSettingNamesAction() {
        CompletableFuture.runAsync(() -> this.updateState3());
    }

    private void onSortingAction2() {
        String selected = this.settingSorting.getSelected();
        switch (selected) {
            case "Random":
                for (Category category : Category.values()) {
                    if (!category.hidden) {
                        category.getModuleList().clear();
                        category.getModuleList().addAll(category.getSubModuleList());
                    }
                }

                return;
            case "Alphabet":
                for (Category category2 : Category.values()) {
                    if (!category2.hidden) {
                        category2.getModuleList().sort(Comparator.comparing(object -> ((Module)object).getDescription()));
                    }
                }

                return;
            case "Length":
                for (Category category3 : Category.values()) {
                    if (!category3.hidden) {
                        category3.getModuleList().sort(Comparator.comparingDouble(object2 -> getCoordinate((Module)object2)));
                    }
                }

                return;
        }
    }
}
