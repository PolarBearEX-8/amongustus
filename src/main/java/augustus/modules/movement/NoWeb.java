package augustus.modules.movement;

import augustus.modes.movement.FlyIntaveHighMode;
import augustus.modes.movement.FlyIntaveLegacyMode;
import augustus.modes.movement.FlyIntaveoldMode;
import augustus.modes.movement.NoWebAACMode;
import augustus.modes.movement.NoWebCustomMode;
import augustus.modes.movement.NoWebIntaveMode;
import augustus.modes.movement.NoWebMatrixMode;
import augustus.modes.movement.NoWebNcpMode;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.event.EventPacket;
import client.event.MoveEntityListener;
import client.event.MoveListener;
import client.event.PacketListener;
import client.event.TriFunction;
import client.event.UpdateStateListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.DynamicModeSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.Packet;
import client.util.TimeHelper;
import java.awt.Color;
import java.util.function.BiFunction;
import net.minecraft.block.BlockCollisionHandler;
import net.minecraft.item.ItemBlock;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;

public class NoWeb extends Module implements MoveEntityListener, MoveListener, UpdateStateListener, PacketListener {
    public DynamicModeSetting settingModes;
    public BooleanSetting settingNoDown;
    public BooleanSetting settingNoNoDownWhileVelocity;
    public BooleanSetting settingUpDown;
    public BooleanSetting settingNoHorizontalMoveWhileUpAndDown;
    public NumberSetting settingUpSpeed;
    public NumberSetting settingDownSpeed;
    public TimeHelper timer;
    public TimeHelper timer2;
    public static String displayName2;

    public NoWeb() {
        super("NoWeb", "KeineSpinnenweben", new Color(179, 252, 255), Category.MOVEMENT);
        BiFunction[] biFunctionArray = new BiFunction[8];
        BiFunction biFunction = (object, object2) -> new FlyIntaveHighMode((NoWeb)object, (SettingGroup)object2);
        biFunctionArray[0] = biFunction;
        BiFunction biFunction2 = (object3, object4) -> new FlyIntaveLegacyMode((NoWeb)object3, (SettingGroup)object4);
        biFunctionArray[1] = biFunction2;
        BiFunction biFunction3 = (object5, object6) -> new NoWebIntaveMode((NoWeb)object5, (SettingGroup)object6);
        biFunctionArray[2] = biFunction3;
        BiFunction biFunction4 = (object7, object8) -> new NoWebAACMode((NoWeb)object7, (SettingGroup)object8);
        biFunctionArray[3] = biFunction4;
        BiFunction biFunction5 = (object9, object10) -> new NoWebMatrixMode((NoWeb)object9, (SettingGroup)object10);
        biFunctionArray[4] = biFunction5;
        BiFunction biFunction6 = (object11, object12) -> new FlyIntaveoldMode((NoWeb)object11, (SettingGroup)object12);
        biFunctionArray[5] = biFunction6;
        BiFunction biFunction7 = (object13, object14) -> new NoWebNcpMode((NoWeb)object13, (SettingGroup)object14);
        biFunctionArray[6] = biFunction7;
        BiFunction biFunction8 = (object15, object16) -> new BlockCollisionHandler((NoWeb)object15, (SettingGroup)object16);
        biFunctionArray[7] = biFunction8;
        this.settingModes = new DynamicModeSetting(this, "Modes", "Intave", biFunctionArray).getDynamicModeSetting((object17, object18, object19) -> new NoWebCustomMode((NoWeb)object17, (SettingGroup)object18, (String)object19));
        this.settingNoDown = (BooleanSetting)new BooleanSetting(this, "NoDown", false).getSetting(() -> this.isModesVisible());
        this.settingNoNoDownWhileVelocity = (BooleanSetting)new BooleanSetting(this, "NoNoDownWhileVelocity", false).getSetting(() -> this.isModesVisible2());
        this.settingUpDown = (BooleanSetting)new BooleanSetting(this, "UpDown", false).getSetting(() -> this.isModesVisible3());
        this.settingNoHorizontalMoveWhileUpAndDown = (BooleanSetting)new BooleanSetting(this, "NoHorizontalMoveWhileUpAndDown", false).getSetting(() -> this.isModesVisible4());
        this.settingUpSpeed = new NumberSetting(this, "UpSpeed", 2.4, 0.01, 4.0, 2).getNumberSetting(() -> this.isUpSpeedVisible());
        this.settingDownSpeed = new NumberSetting(this, "DownSpeed", 2.4, 0.01, 4.0, 2).getNumberSetting(() -> this.isDownSpeedVisible());
        this.timer = new TimeHelper();
        this.timer2 = new TimeHelper();
    }

    @Override
    public void onEnable() {
        this.registerEvent(20);
        this.registerEvent(21);
        this.registerEvent(43);
        this.registerEvent(26);
    }

    @Override
    public void onDisable() {
        minecraft.thePlayer.setScaleFactor4(0.1F);
    }

    @Override
    public void onPreDisable() {
        minecraft.thePlayer.setScaleFactor4(0.1F);
    }

    @Override
    public void onPacketSend(EventPacket packet) {
        Packet packet2;
        C08PacketPlayerBlockPlacement c08PacketPlayerBlockPlacement;
        if ((packet2 = packet.getPacket()) instanceof C08PacketPlayerBlockPlacement
            && (c08PacketPlayerBlockPlacement = (C08PacketPlayerBlockPlacement)packet2).getEntityId() < 5
            && c08PacketPlayerBlockPlacement.getItemStack() != null
            && c08PacketPlayerBlockPlacement.getItemStack().getItem() instanceof ItemBlock) {
            this.timer.reset();
        }
    }

    @Override
    public void onMove(EventMove event) {
        EventMove event2;
        if (!this.settingNoHorizontalMoveWhileUpAndDown.isEnabled()) {
            event2 = event;
        } else {
            event.setScaleFactor7(0.0F);
            event2 = event;
        }

        event2.setScaleFactor2(0.0F);
    }

    @Override
    public void onPostMoveEntity(EventMoveEntity event) {
        if (!minecraft.thePlayer.isInWeb()) {
            event.setCoordOffset1(0.0);
        }
    }

    @Override
    public void onPostUpdateState() {
        this.timer2.reset();
    }

    private boolean isModesVisible() {
        return this.settingModes.getSelected().equals("Ignore");
    }

    public static void updateText(String text) {
        displayName2 = text;
    }

    private boolean isModesVisible2() {
        return this.settingNoDown.isVisible();
    }

    public static String getText2() {
        return displayName2;
    }

    private boolean isDownSpeedVisible() {
        return this.settingUpDown.isEnabled();
    }

    private boolean isModesVisible3() {
        return this.settingModes.getSelected().equals("Ignore");
    }

    private boolean isModesVisible4() {
        return this.settingUpDown.isEnabled();
    }

    private boolean isUpSpeedVisible() {
        return this.settingUpDown.isEnabled();
    }
}
