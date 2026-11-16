package augustus.modules.fun;

import client.event.EventKeyInput;
import client.event.EventPacket;
import client.event.KeyInputListener;
import client.event.PacketListener;
import client.event.RenderListener;
import client.event.TickListener;
import client.gui.CustomGuiScreen;
import client.module.Category;
import client.module.Module;
import client.network.NetworkPlayerInfo;
import client.setting.LagSendMessageType;
import client.setting.StringSetting;
import client.util.Packet;
import client.util.RayTraceUtil;
import client.util.TabListBotFilter;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S07PacketRespawn;

public class Lag extends Module implements TickListener, PacketListener, KeyInputListener, RenderListener {
    public StringSetting settingLagToggleKey;
    public LagSendMessageType lagSendMessageType;
    public boolean expanded2;
    public ArrayList itemList3;
    public ArrayList laggedPlayers;

    public Lag() {
        super("Lag", "Verzögerung", new Color(201, 240, 153), Category.FUN);
        this.settingLagToggleKey = new StringSetting(this, "LagToggleKey");
        this.expanded2 = false;
        this.itemList3 = new ArrayList();
        this.laggedPlayers = new ArrayList();
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
        this.registerEvent(26);
        this.registerEvent(14);
        this.registerEvent(32);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.lagSendMessageType = LagSendMessageType.SEND_MESSAGE;
        this.expanded2 = false;
        this.itemList3.clear();
        this.laggedPlayers.clear();
        if (minecraft.thePlayer != null) {
            minecraft.thePlayer.sendChatMessage("/language german");
        }
    }

    @Override
    public void onKeyInput(EventKeyInput event) {
        if (event.getIndex() == this.settingLagToggleKey.getInt3()) {
            this.expanded2 = !this.expanded2;
            if (this.expanded2) {
                this.lagSendMessageType = LagSendMessageType.SEND_MESSAGE;
                RayTraceUtil.applyString("StartLag");
                return;
            }

            RayTraceUtil.applyString("StopLag");
        }
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (packet2 instanceof S07PacketRespawn) {
            this.laggedPlayers.clear();
        }

        if (packet2 instanceof S02PacketChat) {
            if (((S02PacketChat)packet2).getChatComponent().getString().contains("Spieler konnte nicht gefunden werden")) {
                this.lagSendMessageType = LagSendMessageType.SEND_MESSAGE;
                packet.cancel();
            }
        }
    }

    @Override
    public void onRender3D(float partialTicks) {
        if (this.expanded2) {
            minecraft.currentScreen = (GuiScreen)null;
        }
    }

    @Override
    public void onPreTick() {
        if (this.expanded2) {
            if ((this.lagSendMessageType) == LagSendMessageType.SEND_MESSAGE) {
                for (String text : (Iterable<String>) this.itemList3) {
                    if (!this.laggedPlayers.contains(text)) {
                        minecraft.thePlayer.sendChatMessage("/report " + text);
                        this.lagSendMessageType = LagSendMessageType.CLICK;
                        this.laggedPlayers.add(text);
                        break;
                    }
                }
            } else if ((this.lagSendMessageType) == LagSendMessageType.CLICK) {
                if (minecraft.guiScreen2 instanceof CustomGuiScreen) {
                    CustomGuiScreen customGuiScreen = (CustomGuiScreen)minecraft.guiScreen2;

                    for (int i = 0; i < customGuiScreen.container.itemList2.size(); i++) {
                        ItemStack itemStack = customGuiScreen.container.getSlot(i).getStack();
                        if (itemStack != null && itemStack.getItem() instanceof ItemSword) {
                            minecraft.playerController.windowClick(customGuiScreen.container.indexOffset2, -999, 0, 4, minecraft.thePlayer);
                            minecraft.playerController.windowClick(customGuiScreen.container.indexOffset2, i, 0, 0, minecraft.thePlayer);
                            this.lagSendMessageType = LagSendMessageType.CLOSE;
                            break;
                        }
                    }
                }
            } else if ((this.lagSendMessageType) == LagSendMessageType.CLOSE) {
                if (minecraft.guiScreen2 instanceof CustomGuiScreen) {
                    minecraft.thePlayer.updateState5();
                    this.lagSendMessageType = LagSendMessageType.SEND_MESSAGE;
                }
            }
        } else {
            if (minecraft.guiScreen2 instanceof CustomGuiScreen
            && (this.lagSendMessageType == LagSendMessageType.CLOSE || this.lagSendMessageType == LagSendMessageType.CLICK)) {
                minecraft.thePlayer.updateState5();
                this.lagSendMessageType = LagSendMessageType.SEND_MESSAGE;
            }

            Iterator iterator = minecraft.getNetHandlerPlayClient().getCollection().iterator();

            while (iterator.hasNext()) {
                String text2 = ((NetworkPlayerInfo)iterator.next()).getText2();
                if (TabListBotFilter.checkString2(text2) && !this.itemList3.contains(text2)) {
                    if (!text2.equals(minecraft.session.getText1())) {
                        this.itemList3.add(text2);
                    }
                }
            }
        }
    }
}
