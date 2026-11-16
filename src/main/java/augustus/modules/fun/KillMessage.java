package augustus.modules.fun;

import client.Client;
import client.event.EventPacket;
import client.event.PacketListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.RangeSetting;
import client.util.BooleanSupplier;
import client.util.Packet;
import client.util.TimeHelper;
import java.awt.Color;
import java.util.HashMap;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.server.S02PacketChat;

public class KillMessage extends Module implements PacketListener, TickListener {
    public ModeSetting settingSequence;
    public ModeSetting settingKillMessageMode;
    public RangeSetting settingDelay;
    public BooleanSetting settingInInv;
    public ModeSetting settingKillMessageCustom;
    public int keyCode2;
    public HashMap entryMap;
    public TimeHelper timer;

    public KillMessage() {
        super("KillMessage", "Tötungsnachricht", new Color(46, 77, 66), Category.FUN);
        String[] textArray = new String[3];
        textArray[0] = "Irrelevant";
        textArray[1] = "YourFirst";
        textArray[2] = "OpponentFirst";
        this.settingSequence = new ModeSetting(this, "Sequence", "YourFirst", textArray);
        String[] textArray2 = new String[8];
        textArray2[0] = "Augustus";
        textArray2[1] = "L";
        textArray2[2] = "GG";
        textArray2[3] = "GG AC";
        textArray2[4] = "Hahaha";
        textArray2[5] = "Spectate";
        textArray2[6] = "Random";
        textArray2[7] = "Custom";
        this.settingKillMessageMode = new ModeSetting(this, "KillMessageMode", "Augustus", textArray2);
        this.settingDelay = new RangeSetting(this, "Delay", 500.0, 2000.0, 0.0, 4000.0, 0)
        .getRangeSetting2();
        this.settingInInv = new BooleanSetting(this, "InInv", false);
        StringBuilder sb = new StringBuilder().append("killed by Augustus");
        String text2 = Client.getClient().getString5();
        String text = sb.append(text2).append("!").toString();
        String[] textArray3 = new String[0];
        this.settingKillMessageCustom = new ModeSetting(this, "KillMessageCustom", text, textArray3).getModeSetting(() -> canExecute5());
        this.keyCode2 = 1;
        this.entryMap = new HashMap();
        this.timer = new TimeHelper();
    }

    @Override
    public void onEnable() {
        this.registerEvent(26);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
        this.entryMap.clear();
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (packet2 instanceof S02PacketChat) {
            if (minecraft.thePlayer == null) {
                return;
            }

            if (minecraft.theWorld != null) {
                String text2 = ((S02PacketChat)packet2).getChatComponent().getString();
                String text = minecraft.thePlayer.getText();
                if (!text2.contains(text)) {
                    return;
                }

                String[] textArray = text2.split(" ");

                outerLoop:
                for (Entity entity : (Iterable<Entity>) minecraft.theWorld.loadedEntityList) {
                    String text3 = entity.getText();
                    if (!(entity instanceof EntityPlayerSP) && text2.contains(text3)) {
                        int length = textArray.length;
                        int index = 0;

                        while (true) {
                            if (index >= length) {
                                this.entryMap.put(text3, (long)((double)System.currentTimeMillis() + this.settingDelay.recalculate()));
                                continue outerLoop;
                            }

                            String text4 = textArray[index];
                            if (this.settingSequence.getSelected().equals("YourFirst")) {
                                if (text4.contains(text)) {
                                    break;
                                }

                                if (text4.contains(text3)) {
                                    return;
                                }

                                index++;
                            } else {
                                if (text4.contains(text)) {
                                    return;
                                }

                                if (text4.contains(text3)) {
                                    break;
                                }

                                index++;
                            }
                        }

                        this.entryMap.put(text3, (long)((double)System.currentTimeMillis() + this.settingDelay.recalculate()));
                    }
                }

                return;
            }
        }
    }

    @Override
    public void onPreTick() {
        if (!this.settingInInv.isEnabled() || minecraft.currentScreen == null) {
            for (String text : ((Iterable<String>) this.entryMap.keySet())) {
                if ((Long)this.entryMap.get(text) > System.currentTimeMillis()) {
                    TimeHelper timer2 = this.timer;
                    long time = (long)this.settingDelay.getIndexOffset2();
                    if (timer2.hasReached(time)) {
                        this.applyString(text);
                        this.timer.reset();
                        return;
                    }
                }
            }
        }
    }

    private void applyString(String text) {
        String selected = this.settingKillMessageMode.getSelected();
switch (selected) {
            case "Augustus":
                minecraft.thePlayer
                .sendChatMessage(text + " killed by " + Client.getClient().getString4() + "! Learn to cheat Autoclicker User");
                return;
            case "L":
                minecraft.thePlayer.sendChatMessage("L " + text);
                return;
            case "GG":
                minecraft.thePlayer.sendChatMessage("gg " + text);
                return;
            case "GG AC":
                minecraft.thePlayer.sendChatMessage("gg ac user " + text);
                return;
            case "Hahaha":
                minecraft.thePlayer.sendChatMessage("hahahahahaha " + text);
                return;
            case "Spectate":
                minecraft.thePlayer.sendChatMessage("Wie ist es so zu spectaten " + text + "?");
                return;
            case "Random":
                switch (this.keyCode2) {
                    case 1:
                        minecraft.thePlayer.sendChatMessage("L " + text);
                        this.keyCode2++;
                        break;
                    case 2:
                        minecraft.thePlayer.sendChatMessage("gg " + text);
                        this.keyCode2++;
                        break;
                    case 3:
                        minecraft.thePlayer.sendChatMessage("gg ac user " + text);
                        this.keyCode2++;
                        break;
                    case 4:
                        minecraft.thePlayer.sendChatMessage("HAHAHAHAHHAHA " + text);
                        this.keyCode2 = 1;
                }
                return;
            case "Custom":
                minecraft.thePlayer.sendChatMessage(this.settingKillMessageCustom.getSelected() + " " + text);
                return;
        }
    }

    private static boolean canExecute5() {
        return false;
    }
}
