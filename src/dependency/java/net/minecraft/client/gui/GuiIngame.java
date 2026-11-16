package net.minecraft.client.gui;

import client.ClientGlobals;
import client.font.FontFace;
import client.module.Module;
import client.network.PingTracker;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.util.MinecraftAccessor;
import client.util.MovementHandler;
import client.util.RandomUtil;
import client.util.StandardResourceLocationEntry;
import java.awt.Color;
import java.text.DateFormat;
import java.util.GregorianCalendar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiIngame
extends StandardResourceLocationEntry
implements MinecraftAccessor {
    public ResourceLocation resourceLocation10 = null;
    public static FontFace fontFace;
    public final RandomUtil randomUtil = new RandomUtil();
    public static float factor4;
    public boolean enabled2 = false;
    public int count10 = 0;
    public static float reservedTopOffset;
    public ResourceLocation resourceLocation9 = null;

    private static void applyRenderPass3(int n, int n2, boolean bl, RenderPass renderPass) {
        if (MinecraftAccessor.minecraft.thePlayer.getItemStack3(0) != null) {
            RenderUtils.applyItemStack(MinecraftAccessor.minecraft.thePlayer.getItemStack3(0), n - 21, n2, bl);
        }
    }

    @Override
    public void renderElement() {
        super.renderElement();
        try {
            if (((Module)ClientGlobals.moduleManager.fovArrows).isToggled()) {
                ClientGlobals.moduleManager.fovArrows.processShort();
            }
        }
        catch (Exception exception) {}
    }

    private static void applyRenderPassByRenderPass(int n, int n2, boolean bl, RenderPass renderPass) {
        if (MinecraftAccessor.minecraft.thePlayer.getItemStack3(1) != null) {
            RenderUtils.applyItemStack(MinecraftAccessor.minecraft.thePlayer.getItemStack3(1), n - 41, n2, bl);
        }
    }

    private static void applyRenderPass5(int n, int n2, boolean bl, RenderPass renderPass) {
        if (MinecraftAccessor.minecraft.thePlayer.getItemStack3(3) != null) {
            RenderUtils.applyItemStack(MinecraftAccessor.minecraft.thePlayer.getItemStack3(3), n - 81, n2, bl);
        }
    }

    private static void applyRenderPass7(int n, int n2, boolean bl, RenderPass renderPass) {
        if (MinecraftAccessor.minecraft.thePlayer.getItemStack3(2) != null) {
            RenderUtils.applyItemStack(MinecraftAccessor.minecraft.thePlayer.getItemStack3(2), n - 61, n2, bl);
        }
    }

    public static void processAction(int n, int n2) {
        if (!ClientGlobals.moduleManager.hotbar.settingDebugInformation.isEnabled()) {
            return;
        }
        FontRenderer fontRenderer = MinecraftAccessor.minecraft.fontRendererObj;
        int n3 = ClientGlobals.moduleManager.hotbar.settingOtherPingCalculation.isEnabled() ? PingTracker.getEstimatedPing() : (MinecraftAccessor.minecraft.getNetHandlerPlayClient() != null && MinecraftAccessor.minecraft.getNetHandlerPlayClient().getNetworkPlayerInfo(MinecraftAccessor.minecraft.thePlayer.getEntityUniqueID()) != null ? MinecraftAccessor.minecraft.getNetHandlerPlayClient().getNetworkPlayerInfo(MinecraftAccessor.minecraft.thePlayer.getEntityUniqueID()).getIndexOffset3() : 0);
        int n4 = ((Module)ClientGlobals.moduleManager.protector).isToggled() ? (int)MinecraftAccessor.minecraft.thePlayer.posX + ClientGlobals.moduleManager.protector.getIndex4() : (int)MinecraftAccessor.minecraft.thePlayer.posX;
        int n5 = (int)MinecraftAccessor.minecraft.thePlayer.posY;
        int n6 = ((Module)ClientGlobals.moduleManager.protector).isToggled() ? (int)MinecraftAccessor.minecraft.thePlayer.posZ + ClientGlobals.moduleManager.protector.getIndex2() : (int)MinecraftAccessor.minecraft.thePlayer.posZ;
        float f = 15.0f;
        String string = "§fFPS: §7" + Minecraft.getLimitFramerate() + "   §fPing: §7" + n3 + "   §fCPS: §7" + ClientGlobals.moduleManager.hotbar.tickDelay2;
        if ((float)n / 2.0f - 91.0f > (float)(fontRenderer.getIndex(string) + 6) + f) {
            fontRenderer.computeCount(string, (int)(6.0f + f), n2 - 20, Color.white.getRGB());
        } else {
            fontRenderer.computeCount("§fFPS: §7" + Minecraft.getLimitFramerate() + "   §fPing: §7" + n3, (int)(6.0f + f), n2 - 20, Color.white.getRGB());
        }
        String string2 = "§fX: §7" + n4 + "  §fY: §7" + n5 + "  §fZ: §7" + n6 + "  §fBPS: §7" + ClientGlobals.moduleManager.hotbar.motionMultiplier;
        if ((float)n / 2.0f - 91.0f > (float)(fontRenderer.getIndex(string2) + 6) + f) {
            fontRenderer.computeCount(string2, (int)(6.0f + f), n2 - 9, Color.white.getRGB());
        } else {
            fontRenderer.computeCount("§fX: §7" + n4 + "  §fY: §7" + n5 + "  §fZ: §7" + n6, (int)(6.0f + f), n2 - 9, Color.white.getRGB());
        }
        if (!((Module)ClientGlobals.moduleManager.protector).isToggled() || !ClientGlobals.moduleManager.protector.settingTime.isEnabled()) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            String string3 = DateFormat.getDateInstance(2).format(gregorianCalendar.getTime());
            String string4 = DateFormat.getTimeInstance(3).format(gregorianCalendar.getTime());
            float f2 = fontRenderer.getIndex(string3);
            fontRenderer.getIndex(string3, (float)(n - 10) - f2, n2 - 1 - fontRenderer.indexOffset, -570425345, true, 0.5f);
            fontRenderer.getIndex(string4, (float)((double)(n - 10) - (double)fontRenderer.getIndex(string4) * 0.5 - (double)f2 * 0.5), n2 - 11 - fontRenderer.indexOffset, -570425345, true, 0.5f);
        }
    }

    private static void applyRenderPass(RenderPass renderPass) {
        ClientGlobals.moduleManager.fovArrows.applyRenderPass(renderPass);
    }

    private void applyScaledResolution13(ScaledResolution scaledResolution) {
        GlStateManager.disableTexture2D();
        int n = scaledResolution.getScaledHeight() - 56;
        int n2 = scaledResolution.getScaledWidth() / 2 + 91;
        boolean bl = ClientGlobals.moduleManager.hotbar.settingArmorDurability.isEnabled();
        MovementHandler.processBoolean(false, ClientGlobals.moduleManager.hotbar.settingArmorShadow.isEnabled() && ((Module)ClientGlobals.moduleManager.shadow).isToggled());
        MovementHandler.applyRunnableTask(false, lambdaArgA -> GuiIngame.applyRenderPass5(n2, n, bl, lambdaArgA));
        MovementHandler.processBoolean(false, ClientGlobals.moduleManager.hotbar.settingArmorShadow.isEnabled() && ((Module)ClientGlobals.moduleManager.shadow).isToggled());
        MovementHandler.applyRunnableTask(false, lambdaArgA -> GuiIngame.applyRenderPass7(n2, n, bl, lambdaArgA));
        MovementHandler.processBoolean(false, ClientGlobals.moduleManager.hotbar.settingArmorShadow.isEnabled() && ((Module)ClientGlobals.moduleManager.shadow).isToggled());
        MovementHandler.applyRunnableTask(false, lambdaArgA -> GuiIngame.applyRenderPassByRenderPass(n2, n, bl, lambdaArgA));
        MovementHandler.processBoolean(false, ClientGlobals.moduleManager.hotbar.settingArmorShadow.isEnabled() && ((Module)ClientGlobals.moduleManager.shadow).isToggled());
        MovementHandler.applyRunnableTask(false, lambdaArgA -> GuiIngame.applyRenderPass3(n2, n, bl, lambdaArgA));
    }

    @Override
    public void applyFloat(float f) {
        super.applyFloat(f);
        try {
            if (((Module)ClientGlobals.moduleManager.hotbar).isToggled() && ClientGlobals.moduleManager.hotbar.settingArmor.isEnabled()) {
                GL11.glPushMatrix();
                ScaledResolution scaledResolution = new ScaledResolution(MinecraftAccessor.minecraft);
                GL11.glScaled(1.0f / (float)scaledResolution.getScaleFactor(), 1.0f / (float)scaledResolution.getScaleFactor(), 1.0);
                GL11.glScaled(ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue(), ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue(), 1.0);
                GL11.glTranslated(0.0, ClientGlobals.moduleManager.hotbar.getInt2(), 0.0);
                ScaledResolution scaledResolution2 = new ScaledResolution((double)MinecraftAccessor.minecraft.displayWidth * (1.0 / ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue()), (double)MinecraftAccessor.minecraft.displayHeight * (1.0 / ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue()), (int)ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue());
                this.applyScaledResolution13(scaledResolution2);
                GL11.glPopMatrix();
            }
            if (((Module)ClientGlobals.moduleManager.oldNotification).isToggled()) {
                ClientGlobals.moduleManager.oldNotification.processAction(f);
            }
            if (((Module)ClientGlobals.moduleManager.fovArrows).isToggled()) {
                MovementHandler.processBoolean(ClientGlobals.moduleManager.fovArrows.settingOutlineColor2.canExecute(), ClientGlobals.moduleManager.fovArrows.settingOutlineColor2.canExecute2());
                MovementHandler.applyRunnableTask(false, GuiIngame::applyRenderPass);
            }
        }
        catch (Exception exception) {}
    }

    public static void applyScaledResolution12(ScaledResolution scaledResolution) {
        GL11.glScaled(1.0 / (double)scaledResolution.getScaleFactor(), 1.0 / (double)scaledResolution.getScaleFactor(), 1.0);
        double d = (double)MinecraftAccessor.minecraft.getFramebuffer().indexOffset2 / 2.0;
        double d2 = (double)MinecraftAccessor.minecraft.getFramebuffer().indexOffset1 / 2.0;
        int n = (int)ClientGlobals.moduleManager.crosshair.settingWidth.getValue();
        int n2 = (int)ClientGlobals.moduleManager.crosshair.settingLength.getValue();
        int n3 = (int)ClientGlobals.moduleManager.crosshair.settingMargin.getValue();
        int n4 = ClientGlobals.moduleManager.crosshair.settingColor.getInt12(0);
        if (n % 2 == 0) {
            int n5 = n / 2;
            RenderUtils.processDouble4(d - (double)n5, d2 + (double)n3 + (double)n2, d + (double)n5, d2 + (double)n3, n4);
            if (!ClientGlobals.moduleManager.crosshair.settingTStyle.isEnabled()) {
                RenderUtils.processDouble4(d - (double)n5, d2 - (double)n3 - (double)n2, d + (double)n5, d2 - (double)n3, n4);
            }
            RenderUtils.processDouble4(d + (double)n3, d2 + (double)n5, d + (double)n3 + (double)n2, d2 - (double)n5, n4);
            RenderUtils.processDouble4(d - (double)n3 - (double)n2, d2 + (double)n5, d - (double)n3, d2 - (double)n5, n4);
            if (ClientGlobals.moduleManager.crosshair.settingDot.isEnabled()) {
                RenderUtils.processDouble4(d - (double)n5, d2 + (double)n5, d + (double)n5, d2 - (double)n5, n4);
            }
        } else {
            int n6 = (n - 1) / 2;
            RenderUtils.processDouble4(d - 1.0 - (double)n6, d2 + (double)n3 + (double)n2, d + (double)n6, d2 + (double)n3 + (double)(n3 > 0 ? -1 : 0), n4);
            if (!ClientGlobals.moduleManager.crosshair.settingTStyle.isEnabled()) {
                RenderUtils.processDouble4(d - 1.0 - (double)n6, d2 - (double)n3 - (double)n2 - 1.0, d + (double)n6, d2 - (double)n3, n4);
            }
            RenderUtils.processDouble4(d + (double)n3 + (double)(n3 > 0 ? -1 : 0), d2 + (double)n6, d + (double)n3 + (double)n2, d2 - (double)n6 - 1.0, n4);
            RenderUtils.processDouble4(d - (double)n3 - (double)n2 - 1.0, d2 + (double)n6, d - (double)n3, d2 - (double)n6 - 1.0, n4);
            if (ClientGlobals.moduleManager.crosshair.settingDot.isEnabled()) {
                RenderUtils.processDouble4(d - 1.0 - (double)n6, d2 + (double)n6, d + (double)n6, d2 - 1.0 - (double)n6, n4);
            }
        }
        GL11.glScaled(scaledResolution.getScaleFactor(), scaledResolution.getScaleFactor(), 1.0);
    }

    public GuiIngame(Minecraft minecraft) {
        super(minecraft);
        this.resourceLocation10 = new ResourceLocation("augustus", "pictures/augustus.png");
        this.resourceLocation9 = new ResourceLocation("augustus", "pictures/amogus.png");
        fontFace = new FontFace("Verdana", 16);
    }

    static {
        reservedTopOffset = 0.0f;
    }
}

