package augustus.modules.misc;

import client.Client;
import client.ClientGlobals;
import client.event.EventPacket;
import client.event.EventWorldLoad;
import client.event.PacketListener;
import client.event.TickListener;
import client.event.WorldLoadListener;
import client.module.Category;
import client.module.Module;
import client.module.ModuleManager;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.ModuleDisableRule;
import client.util.RayTraceUtil;
import client.util.TimeHelper;
import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.Vec3;

public class AutoDisable extends Module implements TickListener, WorldLoadListener, PacketListener {
    public BooleanSetting settingDisableOnTeleport;
    public NumberSetting settingMinTeleportDistance;
    public ModeSetting settingMode;
    public NumberSetting settingForcedisableModulesForXMs;
    public NumberSetting settingRetoggleModulesAfterXMs;
    public HashMap entryMap;
    public boolean expanded2;
    public TimeHelper timer;
    public TimeHelper timer2;
    public HashSet entrySet;

    public AutoDisable() {
        super("AutoDisable", "AutoAusschalten", new Color(169, 224, 81), Category.MISC);
        BooleanSetting booleanSetting = new BooleanSetting(this, "Disable on Teleport", false);
        this.settingDisableOnTeleport = booleanSetting;
        this.settingMinTeleportDistance = new NumberSetting(this, "Min teleport distance", 50.0, 0.0, 1000.0, 1).getNumberSetting(() -> this.settingDisableOnTeleport.isEnabled());
        String[] textArray = new String[2];
        textArray[0] = "Simple";
        textArray[1] = "Expert";
        this.settingMode = new ModeSetting(this, "Mode", "Simple", textArray);
        this.settingForcedisableModulesForXMs = new NumberSetting(this, "Forcedisable modules for x ms", 0.0, 0.0, 5000.0, 0).getNumberSetting(() -> this.isForcedisableModulesForXMsVisible());
        this.settingRetoggleModulesAfterXMs = new NumberSetting(this, "Retoggle modules after x ms", 0.0, 0.0, 5000.0, 0).getNumberSetting(() -> this.isRetoggleModulesAfterXMsVisible());
        this.entryMap = new HashMap();
        this.expanded2 = false;
        this.timer = new TimeHelper();
        this.timer2 = new TimeHelper();
        this.entrySet = new HashSet();
    }

    @Override
    public void onEnable() {
        this.registerEvent(17);
        this.registerEvent(26);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        this.expanded2 = this.checkCondition();
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        if (packet.getPacket() instanceof S08PacketPlayerPosLook && minecraft.thePlayer != null) {
            S08PacketPlayerPosLook s08PacketPlayerPosLook = (S08PacketPlayerPosLook)packet.getPacket();
            if (new Vec3(s08PacketPlayerPosLook.getCoordinate(), s08PacketPlayerPosLook.getCoordinate1(), s08PacketPlayerPosLook.getCoordinate4())
            .getDouble2(minecraft.thePlayer.posX, minecraft.thePlayer.posY, minecraft.thePlayer.posZ) <= this.settingMinTeleportDistance.getValue()) {
                return;
            }

            boolean expanded22 = this.expanded2;
            this.expanded2 = this.checkCondition();
            if (!this.expanded2) {
                return;
            }

            if (!expanded22) {
                RayTraceUtil.applyString("Disabled modules because of teleport!");
            }
        }
    }

    @Override
    public void onPostTick() {
        if (this.expanded2) {
            if (minecraft.thePlayer != null) {
                if (minecraft.theWorld != null) {
                    if (!this.entrySet.isEmpty()) {
                        boolean enabled = false;
                        Iterator iterator = this.entrySet.iterator();

                        while (iterator.hasNext()) {
                            Module module = (Module)iterator.next();
                            int index2 = this.getIndex2(module);
                            int index = this.getIndex3(module);
                            int index3 = index2;
                            if (index2 > 0) {
                                index3 = 1;
                            }

                            boolean enabled2 = index > 0;
                            boolean reached = this.timer.hasReached((long)index2);
                            boolean reached2 = this.timer2.hasReached((long)index);
                            if (index3 != 0 && reached) {
                                module.setActive2(false);
                            }

                            if (enabled2 && reached && reached2) {
                                module.processBoolean(true, false);
                                iterator.remove();
                                enabled = true;
                            }
                        }

                        if (enabled) {
                            Client.getClient().getModuleManager().updateState();
                        }

                        if (this.entrySet.isEmpty()) {
                            this.expanded2 = false;
                        }
                    }
                }
            }
        }
    }

    private int getIndex3(Module module) {
        return this.settingMode.getSelected().equals("Simple")
        ? (int)this.settingRetoggleModulesAfterXMs.getDouble2(true)
        : (int)((NumberSetting)((ModuleDisableRule)this.entryMap.get(module)).entry).getDouble2(true);
    }

    private int getIndex2(Module module) {
        return this.settingMode.getSelected().equals("Simple")
        ? (int)this.settingForcedisableModulesForXMs.getDouble2(true)
        : (int)((NumberSetting)((ModuleDisableRule)this.entryMap.get(module)).entry1).getDouble2(true);
    }

    public void applyModuleManager(ModuleManager moduleManager) {
        if (this.entryMap.isEmpty()) {
            for (Category category : Category.values()) {
                if (!category.hidden) {
                    SettingGroup settingGroup = new SettingGroup(this, category.name());

                    for (Module module : ((Iterable<Module>) moduleManager.getList(category))) {
                        if (!(module instanceof AutoDisable)) {
                            BooleanSetting booleanSetting = new BooleanSetting(this, module.getDescription(), false, settingGroup);
                            SettingGroup settingGroup2 = new SettingGroup(this, module.getDescription() + "Settings", settingGroup)
                            .getSettingGroup(() -> this.checkBooleanSetting(booleanSetting));
                            this.entryMap
                            .put(
                            module,
                            new ModuleDisableRule(
                            booleanSetting,
                            new NumberSetting(
                            this,
                            module.getString() + "Forcedisable modules for x ms",
                            0.0,
                            0.0,
                            5000.0,
                            0,
                            settingGroup2
                            )
                            .getNumberSetting("Forcedisable modules for x ms"),
                            new NumberSetting(
                            this,
                            module.getString() + "Retoggle modules after x ms",
                            0.0,
                            0.0,
                            5000.0,
                            0,
                            settingGroup2
                            )
                            .getNumberSetting("Retoggle modules after x ms")
                            )
                            );
                        }
                    }
                }
            }
        }
    }

    private boolean isRetoggleModulesAfterXMsVisible() {
        return this.settingMode.getSelected().equals("Simple");
    }

    private boolean checkBooleanSetting(BooleanSetting setting) {
        return !setting.isEnabled() ? false : this.settingMode.getSelected().equals("Expert");
    }

    private boolean isForcedisableModulesForXMsVisible() {
        return this.settingMode.getSelected().equals("Simple");
    }

    private boolean checkCondition() {
        boolean enabled = true;
        if (!this.expanded2) {
            this.entrySet.clear();
            enabled = false;
        }

        for (Module module : ((Iterable<Module>) ClientGlobals.moduleManager.getCopyOnWriteArrayList2())) {
            if (module.isToggled()) {
                ModuleDisableRule supplementalClientManager = (ModuleDisableRule)this.entryMap.get(module);
                if (supplementalClientManager != null) {
                    BooleanSetting setting = (BooleanSetting)supplementalClientManager.getEntry2();
                    if (setting != null && setting.isEnabled()) {
                        int index2 = this.getIndex2(module);
                        int index = this.getIndex3(module);
                        if (index2 > 0 || index > 0) {
                            enabled = true;
                        }

                        module.setActive(false);
                        this.entrySet.add(module);
                        if (index2 > 0) {
                            module.setActive(true);
                        }
                    }
                }
            }
        }

        this.timer.reset();
        this.timer2.reset();
        return enabled;
    }
}
