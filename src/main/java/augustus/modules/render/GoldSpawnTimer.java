package augustus.modules.render;

import client.event.EventPacket;
import client.event.PacketListener;
import client.event.RenderListener;
import client.event.TickListener;
import client.gui.TexturesGuiIconsPngManager;
import client.module.Category;
import client.module.Module;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.ShaderOptions;
import client.util.BooleanSupplier;
import client.util.MovementHandler;
import client.util.Packet;
import client.util.RandomUtil;
import client.util.RayTraceUtil;
import java.awt.Color;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.IChatComponent;
import org.lwjgl.opengl.GL11;

public class GoldSpawnTimer extends Module implements RenderListener, TickListener, PacketListener {
    public NumberSetting settingXCoord;
    public NumberSetting settingYCoord;
    public NumberSetting settingScale;
    public BooleanSetting settingBackGround;
    public ColorSetting settingBackgroundColor;
    public ShaderOptions settingBackgroundColor2;
    public boolean expanded2;
    public long timestampMillis;
    public boolean enabled2;

    public GoldSpawnTimer() {
        super("GoldSpawnTimer", "GoldEntstehungsUhr", new Color(140, 255, 190), Category.RENDER);
        this.settingXCoord = new NumberSetting(this, "X-Coord", 0.0, 0.0, 100.0, 2);
        this.settingYCoord = new NumberSetting(this, "Y-Coord", 0.0, 0.0, 100.0, 2);
        this.settingScale = new NumberSetting(this, "Scale", 2.0, 0.5, 4.0, 2);
        this.settingBackGround = new BooleanSetting(this, "BackGround", true);
        Color color = new Color(0, 0, 0, 150);
        this.settingBackgroundColor = (ColorSetting)new ColorSetting(this, "BackgroundColor", color).getSetting(() -> this.settingBackGround.isEnabled());
        ShaderOptions shaderOptions = new ShaderOptions(this);
        Color black = Color.black;
        this.settingBackgroundColor2 = shaderOptions.getShaderOptions(black);
        this.expanded2 = false;
        this.timestampMillis = 0L;
        this.enabled2 = false;
    }

    @Override
    public void onEnable() {
        this.registerEvent(32);
        this.registerEvent(40);
        this.registerEvent(26);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.expanded2 = false;
        this.enabled2 = false;
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        if (this.enabled2) {
            GL11.glPushMatrix();
            double value = this.settingScale.getValue();
            GL11.glScaled((double)(1.0F / (float)scaledResolution.getScaleFactor()), (double)(1.0F / (float)scaledResolution.getScaleFactor()), 1.0);
            GL11.glScaled(value, value, 1.0);
            ScaledResolution scaledResolution2 = new ScaledResolution(
                (double)minecraft.displayWidth * (1.0 / value), (double)minecraft.displayHeight * (1.0 / value), (int)value
            );
            int index = (int)((System.currentTimeMillis() - this.timestampMillis) / 1000L);
            int index2;
            if (index >= 60) {
                index2 = 29 - index % 30;
            } else {
                int index3 = index % 60;
                index2 = 59 - index3;
            }

            int index4 = 1 + index2;
            String text = index4 + " sec";
            int index5 = 20 + minecraft.fontRendererObj.getIndex(text) + 3;
            int index6 = (int)((double)(scaledResolution2.getScaledWidth() - index5) * (this.settingXCoord.getValue() / 100.0));
            int index7 = (int)((double)(scaledResolution2.getScaledHeight() - 15) * (this.settingYCoord.getValue() / 100.0));
            if (this.settingBackGround.isEnabled()) {
                int int12 = this.settingBackgroundColor.getInt12(0);
                MovementHandler.processBoolean(
                    this.settingBackgroundColor2.canExecute(), this.settingBackgroundColor2.canExecute2()
                );
                MovementHandler.applyRunnableTask(false, renderPass -> this.applyRenderPass(index6, index7, index5, 15, int12, renderPass));
            }

            RenderUtils.applyItemStack(new ItemStack(Items.goldIngot), index6 + 2, index7, false);
            minecraft.fontRendererObj.getIndex(text, index6 + 22, index7 + 4, -1);
            GL11.glPopMatrix();
        }

        RenderUtils.processAction();
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (packet2 instanceof S02PacketChat) {
            S02PacketChat s02PacketChat = (S02PacketChat)packet2;
            if (s02PacketChat.getChatComponent() != null) {
                if (s02PacketChat.getChatComponent().getString2().contains("§r§aDie Runde beginnt!§r")) {
                    RayTraceUtil.applyString("Started");
                    this.timestampMillis = System.currentTimeMillis() + 500L;
                    this.enabled2 = true;
                }
            }
        }
    }

    @Override
    public void onRunTick() {
        this.updateState();
        if (minecraft.thePlayer.ticksExisted > 20) {
            IChatComponent chatComponent2 = minecraft.ingameGUI.getOrderingEntry().getChatComponent2();
            if (minecraft.ingameGUI != null
                && minecraft.ingameGUI.getOrderingEntry() != null
                && minecraft.ingameGUI.getOrderingEntry().getChatComponent2() != null
                && !chatComponent2.getString2().contains("BW")) {
                this.enabled2 = false;
            }
        }

        if (minecraft.isFullScreen()) {
            this.enabled2 = false;
        }
    }

    private void updateState() {
        if (minecraft.thePlayer != null) {
            if (minecraft.currentScreen == null) {
                if (minecraft.theWorld != null) {
                    if (!this.expanded2) {
                        if (minecraft.thePlayer.ticksExisted > 20) {
                            this.expanded2 = true;
                            minecraft.thePlayer.sendChatMessage("/language german");
                        }
                    }
                }
            }
        }
    }

    private void applyRenderPass(int x, int x2, int x3, int y, int z, RenderPass renderPass) {
        if (renderPass == RenderPass.SHADOW) {
            int index = x + x3;
            int index2 = x2 + y;
            int int13 = RandomUtil.getInt13(z, 255);
            TexturesGuiIconsPngManager.processAction(x, x2, index, index2, this.settingBackgroundColor2.getInt3(int13));
        } else {
            TexturesGuiIconsPngManager.processAction(x, x2, x + x3, x2 + y, z);
        }
    }
}
