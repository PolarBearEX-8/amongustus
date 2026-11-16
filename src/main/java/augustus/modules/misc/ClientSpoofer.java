package augustus.modules.misc;

import client.event.EventPacket;
import client.event.PacketListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.RangeSetting;
import client.setting.SettingContainer;
import client.util.BooleanSupplier;
import client.util.BrandProperty;
import client.util.Packet;
import client.util.PacketBuffer;
import io.netty.buffer.Unpooled;
import java.awt.Color;
import java.util.Random;
import net.minecraft.network.play.client.C15PacketClientSettings;
import net.minecraft.network.play.client.C17PacketCustomPayload;

public class ClientSpoofer extends Module implements PacketListener {
    public ModeSetting settingClients;
    public ModeSetting settingCustomString;
    public BooleanSetting settingRandomizeClientSettings;
    public RangeSetting settingRandomViewDistance;

    public ClientSpoofer() {
        super("ClientSpoofer", "KundenVernebler", new Color(163, 240, 223), Category.MISC);
        String[] textArray = new String[8];
        textArray[0] = "Default";
        textArray[1] = "LabyMod";
        textArray[2] = "CheatBreaker";
        textArray[3] = "PvPLounge";
        textArray[4] = "Lunar";
        textArray[5] = "Badlion";
        textArray[6] = "Custom";
        textArray[7] = "CancelPacket";
        this.settingClients = new ModeSetting(this, "Clients", "Default", textArray);
        String[] textArray2 = new String[0];
        this.settingCustomString = new ModeSetting(this, "CustomString", "", textArray2).getModeSetting(() -> canExecute5());
        this.settingRandomizeClientSettings = new BooleanSetting(this, "RandomizeClientSettings", false);
        this.settingRandomViewDistance = new RangeSetting(this, "RandomViewDistance", 10.0, 12.0, 2.0, 16.0, 0);
    }

    @Override
    public void onEnable() {
        this.registerEvent(26);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onPacket(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (!(packet2 instanceof C17PacketCustomPayload)) {
            if (packet2 instanceof C15PacketClientSettings) {
                if (!minecraft.isGamePaused()) {
                    if (packet2 instanceof C17PacketCustomPayload) {
                        C17PacketCustomPayload c17PacketCustomPayload = (C17PacketCustomPayload)packet2;
                        this.handleC17PacketCustomPayload2(c17PacketCustomPayload, packet);
                    }

                    if (packet2 instanceof C15PacketClientSettings) {
                        if (this.settingRandomizeClientSettings.isEnabled()) {
                            C15PacketClientSettings c15PacketClientSettings = (C15PacketClientSettings)packet2;
                            Object[] itemArray = minecraft.getLanguageManager().getEntryMap().values().toArray();
                            if (itemArray.length > 0 && itemArray[0] instanceof BrandProperty[]) {
                                BrandProperty[] comparableAdapterArray = (BrandProperty[])itemArray;
                                c15PacketClientSettings.updateText(
                                    comparableAdapterArray[SettingContainer.settingProvider
                                            .getInt2(0, minecraft.getLanguageManager().getEntryMap().values().size() - 1)]
                                        .getString()
                                );
                                int index = (int)this.settingRandomViewDistance.recalculate();
                                minecraft.gameSettings.indexOffset16 = index;
                                c15PacketClientSettings.processAction(index);
                                c15PacketClientSettings.setActive(new Random().nextBoolean());
                            }
                        }
                    }
                }
            }
        } else if (!minecraft.isGamePaused()) {
            if (packet2 instanceof C17PacketCustomPayload) {
                C17PacketCustomPayload c17PacketCustomPayload2 = (C17PacketCustomPayload)packet2;
                this.handleC17PacketCustomPayload2(c17PacketCustomPayload2, packet);
            }

            if (packet2 instanceof C15PacketClientSettings) {
                if (this.settingRandomizeClientSettings.isEnabled()) {
                    C15PacketClientSettings c15PacketClientSettings2 = (C15PacketClientSettings)packet2;
                    Object[] itemArray2 = minecraft.getLanguageManager().getEntryMap().values().toArray();
                    if (itemArray2.length > 0 && itemArray2[0] instanceof BrandProperty[]) {
                        BrandProperty[] comparableAdapterArray2 = (BrandProperty[])itemArray2;
                        c15PacketClientSettings2.updateText(
                            comparableAdapterArray2[SettingContainer.settingProvider
                                    .getInt2(0, minecraft.getLanguageManager().getEntryMap().values().size() - 1)]
                                .getString()
                        );
                        int index2 = (int)this.settingRandomViewDistance.recalculate();
                        minecraft.gameSettings.indexOffset16 = index2;
                        c15PacketClientSettings2.processAction(index2);
                        c15PacketClientSettings2.setActive(new Random().nextBoolean());
                    }
                }
            }
        }
    }

    private void handleC17PacketCustomPayload(C17PacketCustomPayload c17PacketCustomPayload, String text) {
        c17PacketCustomPayload.setPacketBuffer(new PacketBuffer(Unpooled.buffer()).getPacketBuffer(text));
    }

    private void handleC17PacketCustomPayload2(C17PacketCustomPayload c17PacketCustomPayload, EventPacket packet) {
        if (c17PacketCustomPayload.getText().equals("MC|Brand")) {
            String selected = this.settingClients.getSelected();
            switch (selected) {
                case "LabyMod":
                    this.handleC17PacketCustomPayload(c17PacketCustomPayload, "LMC");
                    break;
                case "CheatBreaker":
                    this.handleC17PacketCustomPayload(c17PacketCustomPayload, "CB");
                    break;
                case "PvPLounge":
                    this.handleC17PacketCustomPayload(c17PacketCustomPayload, "PLC18");
                    break;
                case "Lunar":
                    this.handleC17PacketCustomPayload(c17PacketCustomPayload, "Lunar-Client");
                    break;
                case "Badlion":
                    this.handleC17PacketCustomPayload(c17PacketCustomPayload, "badlion");
                    break;
                case "Custom":
                    this.handleC17PacketCustomPayload(c17PacketCustomPayload, this.settingCustomString.getSelected());
                    break;
                case "CancelPacket":
                    packet.cancel();
            }
        }
    }

    private static boolean canExecute5() {
        return false;
    }
}
