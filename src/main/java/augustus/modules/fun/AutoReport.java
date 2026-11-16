package augustus.modules.fun;

import client.ClientGlobals;
import client.event.EventPacket;
import client.event.PacketListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.network.NetworkPlayerInfo;
import client.setting.BooleanSetting;
import client.setting.RangeSetting;
import client.util.AutoReportCache;
import client.util.Packet;
import client.util.TabListBotFilter;
import client.util.TimeHelper;
import java.awt.Color;
import java.util.HashSet;
import java.util.Iterator;
import net.minecraft.network.play.server.S02PacketChat;

public class AutoReport extends Module implements PacketListener, TickListener {
    public RangeSetting settingDelay;
    public BooleanSetting settingHideMessages;
    public int keyCode2;
    public TimeHelper timer;
    public HashSet entrySet;
    public HashSet entrySet2;

    public AutoReport() {
        super("AutoReport", "AutomatischeMeldung", new Color(167, 245, 66), Category.FUN);
        this.settingDelay = new RangeSetting(this, "Delay", 11000.0, 13000.0, 0.0, 15000.0, 0)
            .getRangeSetting2();
        this.settingHideMessages = new BooleanSetting(this, "HideMessages", true);
        this.keyCode2 = 0;
        this.timer = new TimeHelper();
        this.entrySet = new HashSet();
        this.entrySet2 = new HashSet();
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
        this.registerEvent(26);
    }

    @Override
    public void onDisable() {
        this.entrySet2.clear();
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        if (this.settingHideMessages.isEnabled()) {
            Packet packet2 = packet.getPacket();
            if (!(packet2 instanceof S02PacketChat)) {
                return;
            }

            if (((S02PacketChat)packet2).getChatComponent().getString().contains("[Guardian]")) {
                packet.cancel();
            }
        }
    }

    @Override
    public void onRunTick() {
        this.applyString4("" + this.keyCode2);
        if (minecraft.thePlayer.ticksExisted > 50) {
            for (Object object : minecraft.getNetHandlerPlayClient().getCollection()) {
                String text2 = ((NetworkPlayerInfo)object).getText2();
                if (!text2.equals(minecraft.session.getText1())
                    && !this.entrySet.contains(text2)
                    && !ClientGlobals.moduleManager.midClick.checkString2(text2)) {
                    this.entrySet2.add(text2);
                }
            }

            boolean reached = this.timer.hasReached((long)this.settingDelay.getIndexOffset2());
            if (reached) {
                this.settingDelay.recalculate();
                Iterator iterator = this.entrySet2.iterator();

                while (iterator.hasNext()) {
                    String text = (String)iterator.next();
                    if (text.equals(minecraft.session.getText1())) {
                        return;
                    }

                    if (this.entrySet.contains(text)) {
                        return;
                    }

                    if (ClientGlobals.moduleManager.midClick.checkString2(text)) {
                        return;
                    }

                    if (TabListBotFilter.checkString4(text) && !AutoReportCache.entryMap2.containsKey(text)) {
                        boolean enabled = false;
                        Iterator iterator2 = minecraft.getNetHandlerPlayClient().getCollection().iterator();

                        while (true) {
                            if (iterator2.hasNext()) {
                                if (!((NetworkPlayerInfo)iterator2.next()).getText2().equals(text)) {
                                    continue;
                                }

                                enabled = true;
                            }

                            if (!enabled) {
                                minecraft.thePlayer.sendChatMessage("/report " + text + " hacking confirm");
                                this.timer.reset();
                                this.entrySet.add(text);
                                iterator.remove();
                                this.keyCode2++;
                                return;
                            }
                            break;
                        }
                    } else {
                        this.entrySet.add(text);
                        iterator.remove();
                    }
                }
            }
        }
    }
}
