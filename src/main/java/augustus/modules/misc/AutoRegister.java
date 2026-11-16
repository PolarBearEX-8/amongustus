package augustus.modules.misc;

import client.Client;
import client.event.EventPacket;
import client.event.PacketListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.RangeSetting;
import client.util.AutoRegisterEncryption;
import client.util.AutoRegisterPayload;
import client.util.BooleanSupplier;
import client.util.ConfigManager;
import client.util.Packet;
import client.util.PlayerUtil;
import client.util.RayTraceUtil;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Random;
import net.minecraft.network.play.server.S02PacketChat;
import org.apache.commons.lang3.StringUtils;

public class AutoRegister extends Module implements PacketListener, TickListener {
    public ModeSetting settingPasswordMode;
    public RangeSetting settingDelay;
    public ModeSetting settingCustomPassword;
    public BooleanSetting settingGetPasswordFromTxtIfPresent;
    public BooleanSetting settingPrintOnRegister;
    public BooleanSetting settingPrintOnLogin;
    public HashMap entryMap;
    public HashMap entryMap2;

    public AutoRegister() {
        super("AutoRegister", "AutoRegistrieren", new Color(189, 32, 110), Category.MISC);
        String[] textArray = new String[4];
        textArray[0] = "Generated";
        textArray[1] = "Private";
        textArray[2] = "Private2";
        textArray[3] = "Custom";
        this.settingPasswordMode = new ModeSetting(this, "PasswordMode", "Generated", textArray);
        this.settingDelay = new RangeSetting(this, "Delay", 700.0, 1400.0, 0.0, 4000.0, 0)
            .getRangeSetting2();
        String[] textArray2 = new String[0];
        this.settingCustomPassword = new ModeSetting(this, "CustomPassword", "Hallo1337", textArray2).getModeSetting(() -> canExecute5());
        this.settingGetPasswordFromTxtIfPresent = new BooleanSetting(this, "Get password from txt if present", false);
        this.settingPrintOnRegister = new BooleanSetting(this, "Print on register", false);
        this.settingPrintOnLogin = new BooleanSetting(this, "Print on login", false);
        this.entryMap = new HashMap();
        this.entryMap2 = new HashMap();
        StringBuilder stringBuilder = new StringBuilder();
        String text = Client.getClient().getString4().toLowerCase();
        String text2 = stringBuilder.append(text).append(File.separator).toString();
        ConfigManager.applyString("assets/augustus/autoregister.txt", text2, false);
    }

    @Override
    public void onEnable() {
        this.registerEvent(26);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.entryMap.clear();
        this.updateState();
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (packet2 instanceof S02PacketChat) {
            String text = ((S02PacketChat)packet2).getChatComponent().getString();
            if (!StringUtils.containsIgnoreCase(text, "/reg") && !StringUtils.containsIgnoreCase(text, "/register")) {
                if (StringUtils.containsIgnoreCase(text, "/login")) {
                    this.processBoolean2(true);
                }
            } else {
                this.processBoolean2(false);
            }
        }
    }

    @Override
    public void onRunTick() {
        if (!this.entryMap.isEmpty()) {
            for (Double doubleVal : ((Iterable<Double>) this.entryMap.keySet())) {
                if (!(doubleVal > (double)System.currentTimeMillis())) {
                    minecraft.thePlayer.sendChatMessage((String)this.entryMap.get(doubleVal));
                    this.entryMap.remove(doubleVal);
                    break;
                }
            }
        }
    }

    public String getString3(String text, boolean enabled) {
        String text2 = minecraft.getSession().getText1();
        if (enabled && this.entryMap2.containsKey(text2)) {
            return (String)this.entryMap2.get(text2);
        } else if (text.equalsIgnoreCase("Custom")) {
            return this.settingCustomPassword.getSelected();
        } else {
            byte[] bytes = text2.getBytes(StandardCharsets.UTF_8);
            if (text.equalsIgnoreCase("private2")) {
                for (int i = 0; i < bytes.length; i++) {
                    bytes[i] = (byte)(bytes[i] ^ 5);
                }
            }

            if (text.equalsIgnoreCase("generated")) {
                byte[] byteArray = AutoRegisterPayload.getByteArray(bytes);
                return AutoRegisterEncryption.getAutoRegisterCrypto().getText(byteArray).substring(0, 12);
            } else {
                byte[] byteArray2 = AutoRegisterPayload.getByteArray(bytes);
                int index = Client.getClient().getString7().hashCode();

                for (int i2 = 0; i2 < byteArray2.length; i2++) {
                    byteArray2[i2] = (byte)(byteArray2[i2] ^ (byte)(i2 ^ 267 ^ bytes[i2 % bytes.length]) ^ (byte)(index >> i2 % 5 & 0xFF));
                }

                if (text.equalsIgnoreCase("Private")) {
                    Random random = new Random((long)index);
                    return AutoRegisterEncryption.getAutoRegisterCrypto().getText(byteArray2).substring(0, random.nextInt(3) + 10);
                } else {
                    Random random2 = new Random((long)(index ^ text2.hashCode()));
                    return AutoRegisterEncryption.getAutoRegisterCrypto().getText(byteArray2).substring(0, random2.nextInt(3) + 10);
                }
            }
        }
    }

    private void processBoolean2(boolean enabled) {
        double offset = this.settingDelay.recalculate();
        double offset2 = (double)System.currentTimeMillis() + offset;
        String text = PlayerUtil.getString(offset / 1000.0, 1);
        if (enabled) {
            String text3 = this.getString3(
                this.settingPasswordMode.getSelected(), this.settingGetPasswordFromTxtIfPresent.isEnabled()
            );
            StringBuilder sb2 = new StringBuilder().append("Logging in... (").append(text).append("s)");
            String text2 = this.settingPrintOnLogin.isEnabled() ? " with " + text3 : "";
            RayTraceUtil.applyString(sb2.append(text2).toString());
            this.entryMap.put(offset2, "/login " + text3);
        } else {
            String text5 = this.getString3(
                this.settingPasswordMode.getSelected(), this.settingGetPasswordFromTxtIfPresent.isEnabled()
            );
            StringBuilder sb4 = new StringBuilder().append("Register in... (").append(text).append("s)");
            String text4 = this.settingPrintOnRegister.isEnabled() ? " with " + text5 : "";
            RayTraceUtil.applyString(sb4.append(text4).toString());
            this.entryMap.put(offset2, "/register " + text5 + " " + text5);
        }
    }

    private static boolean canExecute5() {
        return false;
    }

    private void updateState() {
        this.entryMap2.clear();

        File file;
        try {
            file = ConfigManager.getFile("augustus/autoregister.txt");
        } catch (FileNotFoundException fileNotFoundException) {
            ConfigManager.applyString(
                "assets/augustus/autoregister.txt",
                Client.getClient().getString4().toLowerCase() + File.separator,
                
                false
            );

            try {
                file = ConfigManager.getFile("augustus/autoregister.txt");
            } catch (FileNotFoundException fileNotFoundException2) {
                return;
            }
        }

        try {
            for (String text : Files.readAllLines(file.toPath(), StandardCharsets.UTF_8)) {
                if (!text.startsWith("#")) {
                    String text2 = text.replace("\\:", "\u0000");
                    String[] textArray = text2.split(":");
                    if (textArray.length >= 2) {
                        this.entryMap2.put(textArray[0].replace("\u0000", ":"), textArray[1].replace("\u0000", ":"));
                    }
                }
            }
        } catch (Throwable e) {
        }
    }
}
