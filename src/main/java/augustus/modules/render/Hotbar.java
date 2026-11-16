package augustus.modules.render;

import client.event.EventPacket;
import client.event.PacketListener;
import client.event.RenderListener;
import client.event.TickListener;
import client.font.FontRegistry;
import client.module.Category;
import client.module.Module;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.ShaderOptions;
import client.util.BooleanSupplier;
import client.util.MovementHandler;
import client.util.Packet;
import client.util.SlotManager;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C04PacketPlayerPosition;
import net.minecraft.network.play.client.C06PacketPlayerPosLook;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.CAnimateHandPacket;
import org.lwjgl.opengl.GL11;

public class Hotbar extends Module implements RenderListener, PacketListener, TickListener {
    public NumberSetting settingHotbarScale;
    public BooleanSetting settingArmor;
    public BooleanSetting settingArmorDurability;
    public BooleanSetting settingArmorShadow;
    public BooleanSetting settingCustomHotbar;
    public ShaderOptions settingCustomHotbar2;
    public BooleanSetting settingItemRectangle;
    public BooleanSetting settingSilentSlot;
    public BooleanSetting settingDebugInformation;
    public BooleanSetting settingOtherPingCalculation;
    public BooleanSetting settingInventoryItems;
    public ArrayList tickHistory;
    public ArrayList itemList3;
    public int tickDelay;
    public int tickDelay2;
    public double reachDistance1;
    public double reachDistance;
    public float motionMultiplier;
    public static Setting[] settingsArray;

    public Hotbar() {
        super("Hotbar", "Schnellzugriffsleiste", new Color(235, 64, 52), Category.RENDER);
        this.settingHotbarScale = new NumberSetting(this, "HotbarScale", 2.0, 1.0, 4.0, 2);
        this.settingArmor = new BooleanSetting(this, "Armor", true);
        this.settingArmorDurability = (BooleanSetting)new BooleanSetting(this, "ArmorDurability", true).getSetting(() -> this.isHotbarScaleVisible());
        this.settingArmorShadow = new BooleanSetting(this, "ArmorShadow", true);
        this.settingCustomHotbar = new BooleanSetting(this, "CustomHotbar", true);
        ShaderOptions shaderOptions = new ShaderOptions(this);
        Color black = Color.black;
        this.settingCustomHotbar2 = (ShaderOptions)shaderOptions.getShaderOptions(black).getSetting(() -> this.settingCustomHotbar.isEnabled());
        this.settingItemRectangle = (BooleanSetting)new BooleanSetting(this, "ItemRectangle", true).getSetting(() -> this.settingCustomHotbar.isEnabled());
        this.settingSilentSlot = (BooleanSetting)new BooleanSetting(this, "SilentSlot", false).getSetting(() -> this.settingCustomHotbar.isEnabled());
        this.settingDebugInformation = (BooleanSetting)new BooleanSetting(this, "DebugInformation", true).getSetting(() -> this.settingCustomHotbar.isEnabled());
        this.settingOtherPingCalculation = (BooleanSetting)new BooleanSetting(this, "OtherPingCalculation", true).getSetting(() -> this.settingDebugInformation.isEnabled());
        this.settingInventoryItems = (BooleanSetting)new BooleanSetting(this, "InventoryItems", false).getSetting(() -> this.settingCustomHotbar.isEnabled());
        this.tickHistory = new ArrayList();
        this.itemList3 = new ArrayList();
        this.tickDelay = 0;
        this.tickDelay2 = 0;
    }

    @Override
    public void onEnable() {
        this.registerEvent(32);
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
    public void onPacketSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if ((packet2 instanceof C0APacketAnimation || packet2 instanceof CAnimateHandPacket || packet2 instanceof C08PacketPlayerBlockPlacement)
        && !minecraft.playerController.isState()
        && (!(packet2 instanceof C08PacketPlayerBlockPlacement) || ((C08PacketPlayerBlockPlacement)packet2).getEntityId() == 255)) {
            this.tickDelay++;
        }

        if (packet2 instanceof C03PacketPlayer) {
            if (!(packet2 instanceof C04PacketPlayerPosition) && !(packet2 instanceof C06PacketPlayerPosLook)) {
                this.processAction(0.0F);
            } else {
                C03PacketPlayer c03PacketPlayer = (C03PacketPlayer)packet2;
                double offset = Math.abs(c03PacketPlayer.getCoordinate1() - this.reachDistance1);
                float value = (float)(Math.hypot(Math.abs(c03PacketPlayer.getCoordinate3() - this.reachDistance), offset) * 20.0);
                this.processAction(value);
                this.reachDistance1 = c03PacketPlayer.getCoordinate1();
                this.reachDistance = c03PacketPlayer.getCoordinate3();
            }
        }
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        if (this.settingCustomHotbar.isEnabled()) {
            GL11.glScaled((double)(1.0F / (float)scaledResolution.getScaleFactor()), (double)(1.0F / (float)scaledResolution.getScaleFactor()), 1.0);
            double double2 = this.settingHotbarScale.getDouble2(true);
            int index3 = (int)Math.round((double)minecraft.displayWidth / double2);
            int index4 = (int)Math.round((double)minecraft.displayHeight / double2);
            GL11.glScaled(double2, double2, 1.0);
            EntityPlayer player = (EntityPlayer)minecraft.getRenderViewEntity();
            int index5 = index3 / 2;
            MovementHandler.processBoolean(
            this.settingCustomHotbar2.canExecute(), this.settingCustomHotbar2.canExecute2()
            );
            MovementHandler.applyRunnableTask(false, renderPass -> this.applyRenderPass2(index4, index3, renderPass));
            if (this.settingItemRectangle.isEnabled()) {
                RenderUtils.processAction((float)index3 / 2.0F - 91.0F, (float)(index4 - 22), (float)index3 / 2.0F + 91.0F, (float)index4, Integer.MIN_VALUE);
            }

            if (minecraft.thePlayer.inventoryPlayer.currentItem != SlotManager.indexOffset && this.settingSilentSlot.isEnabled()) {
                RenderUtils.processAction(
                (float)((double)index5 - 90.3 + (double)(minecraft.thePlayer.inventoryPlayer.currentItem * 20)),
                (float)(index4 - 22),
                (float)((double)index5 - 68.9 + (double)(minecraft.thePlayer.inventoryPlayer.currentItem * 20)),
                (float)index4,
                154,
                206,
                243,
                185
                );
            }

            RenderUtils.processAction(
            (float)((double)index5 - 90.3 + (double)(SlotManager.indexOffset * 20)),
            (float)(index4 - 22),
            (float)((double)index5 - 68.9 + (double)(SlotManager.indexOffset * 20)),
            (float)index4,
            222,
            222,
            222,
            185
            );
            if (this.settingDebugInformation.isEnabled()) {
                RenderUtils.processAction((float)(index3 - 5), (float)(index4 - 22), (float)index3, (float)index4, 255, 255, 255, 200);
            }

            GuiIngame.processAction(index3, index4);
            GlStateManager.disableLighting();
            GlStateManager.disableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            RenderHelper.enableGUIStandardItemLighting();

            for (int i = 0; i < 9; i++) {
                int index = index3 / 2 - 90 + i * 20 + 2;
                int index6 = index4 - 16 - 3;
                minecraft.ingameGUI.applyEntityPlayer(i, index, index6, minecraft.getTimer().scaleFactor6, player);
            }

            if (this.settingInventoryItems.isEnabled() && minecraft.thePlayer != null && minecraft.thePlayer.inventoryPlayer != null) {
                ContainerPlayer containerAdapter = new ContainerPlayer(minecraft.thePlayer.inventoryPlayer, false, minecraft.thePlayer, true);
                int index7 = (int)((float)index3 / 2.0F) - 96;
                int index8 = index4 - 165;
                float value = (float)index7 + 5.5F;
                int index9 = index8 + 83;
                MovementHandler.processBoolean(
                this.settingCustomHotbar2.canExecute(), this.settingCustomHotbar2.canExecute2()
                );
                MovementHandler.applyRunnableTask(false, renderPass2 -> this.applyRenderPass(value, index9, renderPass2));

                for (int i2 = 0; i2 < containerAdapter.itemList2.size(); i2++) {
                    Slot slot = (Slot)containerAdapter.itemList2.get(i2);
                    this.applySlot(slot, index7, index8);
                }
            }

            RenderHelper.disableStandardItemLighting();
            GlStateManager.applyState8();
            GlStateManager.enableBlend();
            FontRegistry.fontFace.applyString4("A", 1.5, (double)(index4 - 20), Color.white.getRGB());
            GL11.glScaled(1.0 / double2, 1.0 / double2, 1.0);
            GL11.glScaled((double)scaledResolution.getScaleFactor(), (double)scaledResolution.getScaleFactor(), 1.0);
            RenderUtils.processAction();
        }
    }

    @Override
    public void onRunTick() {
        this.tickHistory.add(this.tickDelay);

        while (this.tickHistory.size() > 20) {
            this.tickHistory.remove(0);
        }

        this.tickDelay2 = 0;

        for (Object object : this.tickHistory) {
            int index = (Integer)object;
            this.tickDelay2 += index;
        }

        this.tickDelay = 0;
    }

    private void applyRenderPass(float partialTicks, int index, RenderPass renderPass) {
        if (renderPass == RenderPass.SHADOW) {
            float value = (float)index;
            float value2 = partialTicks + 182.0F;
            float value3 = (float)(index + 60);
            int rGB = Color.black.getRGB();
            RenderUtils.processAction(partialTicks, value, value2, value3, this.settingCustomHotbar2.getIndex(renderPass, rGB));
        } else {
            RenderUtils.processAction(partialTicks, (float)index, partialTicks + 182.0F, (float)(index + 60), 0, 0, 0, 185);
        }
    }

    private void processAction(float value) {
        this.itemList3.add(value);

        while (this.itemList3.size() > 5) {
            this.itemList3.remove(0);
        }

        float value2 = 0.0F;

        for (float value3 : (Iterable<Float>) this.itemList3) {
            value2 += value3;
        }

        this.motionMultiplier = (float)((double)Math.round(value2 / (float)this.itemList3.size() * 100.0F) / 100.0);
    }

    private void applyRenderPass2(int index, int index2, RenderPass renderPass) {
        if (renderPass == RenderPass.SHADOW) {
            float value = (float)(index + 1);
            float value2 = (float)(index2 + 1);
            float value3 = (float)(index - 22);
            int rGB = Color.black.getRGB();
            RenderUtils.processAction(0.0F, value, value2, value3, this.settingCustomHotbar2.getInt3(rGB));
        } else {
            RenderUtils.processAction(0.0F, (float)(index + 1), (float)(index2 + 1), (float)(index - 22), 0, 0, 0, 185);
        }
    }

    private void applySlot(Slot slot, int index, int index2) {
        int index3 = slot.indexOffset + index;
        int index4 = slot.indexOffset2 + index2;
        ItemStack itemStack = slot.getStack();
        GlStateManager.disableDepth();
        minecraft.getRenderItem().renderItemIntoGUI(itemStack, index3, index4);
        minecraft.getRenderItem().renderItemOverlayIntoGUI(minecraft.fontRendererObj, itemStack, index3, index4, null);
    }

    private boolean isHotbarScaleVisible() {
        return !this.settingArmor.isVisible() ? false : this.settingArmor.isEnabled();
    }

    public int getInt2() {
        if (!this.settingCustomHotbar.isEnabled()) {
            return 0;
        } else {
            return this.settingInventoryItems.isEnabled() ? -62 : 0;
        }
    }

    public static Setting[] getSettingArray() {
        return settingsArray;
    }

    public static void setSettingArray(Setting[] settingArray2) {
        settingsArray = settingArray2;
    }
}
