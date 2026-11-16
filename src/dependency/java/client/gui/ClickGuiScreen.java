package client.gui;

import client.ClientGlobals;
import client.event.EventGuiOpenCallback;
import client.event.EventRenderArrayListListener;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.ClickGuiPanel;
import client.gui.ClickGuiWindow;
import client.gui.PanelWindow;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.ModuleModeOption;
import client.util.AuxiliaryNoneManager;
import client.util.MovementHandler;
import client.util.PicturesConfigPngManager;
import client.util.SoundPlayer;
import java.awt.Color;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ClickGuiScreen
extends GuiScreen {
    public int count2 = 0;
    public int count4 = 0;
    public PanelWindow panelWindow = null;
    public int count3 = 0;
    public int count5 = 0;
    public final ClickGuiWindow panelWindowAdapter;
    public final ClickGuiPanel clickGuiPanel = new ClickGuiPanel("ClickGui", -1, -1, 1154, 680);

    @Override
    public void updateState2() {
        int n = (int)((0x453EE7DED643L ^ 0x608DDEE2990CL) >>> 48);
        super.updateState2();
        this.enabled = true;
        int cfr_ignored_0 = this.mc.getFramebuffer().indexOffset2;
        int cfr_ignored_1 = this.mc.getFramebuffer().indexOffset1;
        if (((PanelWindow)this.clickGuiPanel).getCount5() < 0 || ((PanelWindow)this.clickGuiPanel).getCount() < 0 || ((PanelWindow)this.panelWindowAdapter).getCount5() < 0 || ((PanelWindow)this.panelWindowAdapter).getCount() < 0) {
            String string = ((ModuleModeOption)this.clickGuiPanel).getString();
            ((PanelWindow)this.clickGuiPanel).applyText2(string);
            String string2 = ((ModuleModeOption)this.panelWindowAdapter).getString();
            ((PanelWindow)this.panelWindowAdapter).applyText2(string2);
            ((PanelWindow)this.panelWindowAdapter).setEnabledByFlag(false);
        }
        this.panelWindowAdapter.renderElement7();
        this.panelWindow = null;
        GuiScreen.set.add(Minecraft.getMinecraft().currentScreen);
    }

    @Override
    public void enableRepeatEvents() {
        this.clickGuiPanel.renderElement4();
        super.enableRepeatEvents();
    }

    @Override
    public void applyIndex(int n, int n2, int n3, long l) {
        this.clickGuiPanel.applyIndex(n, n2, n3, l);
        this.panelWindowAdapter.applyIndex(n, n2, n3, l);
        super.applyIndex(n, n2, n3, l);
    }

    @Override
    public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
        super.applyAuxiliaryTexturesGuiIconsP(auxiliaryTexturesGuiIconsPngManagerAdapter);
        if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
            EventRenderArrayListListener.updateState12();
        }
    }

    @Override
    public void renderElement2() throws IOException {
        ((PanelWindow)this.clickGuiPanel).renderElement3();
        ((PanelWindow)this.panelWindowAdapter).renderElement3();
        super.renderElement2();
    }

    @Override
    public void applyIndex(int n, int n2, int n3) {
        int n4 = n3;
        int n5 = n2;
        int n6 = n;
        this.clickGuiPanel.applyIndex(n6, n5, n4);
        int n7 = n3;
        int n8 = n2;
        int n9 = n;
        this.panelWindowAdapter.applyIndex(n9, n8, n7);
        super.applyIndex(n, n2, n3);
    }

    @Override
    public void applyChar(char c, int n) throws IOException {
        if (!(this.clickGuiPanel.module.isExpanded() && n == 1 || (n == 28 || n == 156 || n == 1) && this.clickGuiPanel.getFontFaceEntry().isEnabled())) {
            super.applyChar(c, n);
        }
        this.clickGuiPanel.applyChar(c, n);
        this.panelWindowAdapter.applyChar(c, n);
    }

    @Override
    public void updateState4() {
        super.updateState4();
        this.clickGuiPanel.renderElement5();
    }

    public void setMc(Minecraft minecraft) {
        this.mc = minecraft;
    }

    private boolean checkIndex(int n, int n2, int n3, int n4, int n5, int n6) {
        return n5 >= n && n6 >= n2 && n5 < n + n3 && n6 < n2 + n4;
    }

    public ClickGuiWindow getPanelWindowAdapter() {
        return this.panelWindowAdapter;
    }

    public ClickGuiPanel getClickGuiPanel() {
        return this.clickGuiPanel;
    }

    @Override
    public void processAction(int n, int n2, int n3) throws IOException {
        int n4 = n;
        int n5 = n2;
        long l = 0x179572D2C6A5L ^ 0x51159DAA323EL;
        super.processAction(n4, n5, n3);
        int n6 = n4;
        int n7 = n5;
        ScaledResolution scaledResolution = new ScaledResolution(this.mc);
        n4 *= scaledResolution.getScaleFactor();
        n5 *= scaledResolution.getScaleFactor();
        if (this.panelWindow == null) {
            if (RenderUtils.checkCondition((float)((PanelWindow)this.clickGuiPanel).getCount5(), (float)((PanelWindow)this.clickGuiPanel).getCount(), ((PanelWindow)this.clickGuiPanel).getCount7(), ((PanelWindow)this.clickGuiPanel).getCount3(), n4, n5)) {
                this.panelWindow = this.clickGuiPanel;
            } else if (RenderUtils.checkCondition((float)((PanelWindow)this.panelWindowAdapter).getCount5(), (float)((PanelWindow)this.panelWindowAdapter).getCount(), ((PanelWindow)this.panelWindowAdapter).getCount7(), ((PanelWindow)this.panelWindowAdapter).getCount3(), n4, n5)) {
                this.panelWindow = this.panelWindowAdapter;
            }
        } else if (this.panelWindow.equals(this.clickGuiPanel)) {
            if (!RenderUtils.checkCondition((float)((PanelWindow)this.clickGuiPanel).getCount5(), (float)((PanelWindow)this.clickGuiPanel).getCount(), ((PanelWindow)this.clickGuiPanel).getCount7(), ((PanelWindow)this.clickGuiPanel).getCount3(), n4, n5) && RenderUtils.checkCondition((float)((PanelWindow)this.panelWindowAdapter).getCount5(), (float)((PanelWindow)this.panelWindowAdapter).getCount(), ((PanelWindow)this.panelWindowAdapter).getCount7(), ((PanelWindow)this.panelWindowAdapter).getCount3(), n4, n5)) {
                this.panelWindow = this.panelWindowAdapter;
            }
        } else if (this.panelWindow.equals(this.panelWindowAdapter) && !RenderUtils.checkCondition((float)((PanelWindow)this.panelWindowAdapter).getCount5(), (float)((PanelWindow)this.panelWindowAdapter).getCount(), ((PanelWindow)this.panelWindowAdapter).getCount7(), ((PanelWindow)this.panelWindowAdapter).getCount3(), n4, n5) && RenderUtils.checkCondition((float)((PanelWindow)this.clickGuiPanel).getCount5(), (float)((PanelWindow)this.clickGuiPanel).getCount(), ((PanelWindow)this.clickGuiPanel).getCount7(), ((PanelWindow)this.clickGuiPanel).getCount3(), n4, n5)) {
            this.panelWindow = this.clickGuiPanel;
        }
        if (!((PanelWindow)this.clickGuiPanel).isActive() && ((PanelWindow)this.panelWindowAdapter).isActive()) {
            this.panelWindow = this.panelWindowAdapter;
        }
        if (!((PanelWindow)this.panelWindowAdapter).isActive() && ((PanelWindow)this.clickGuiPanel).isActive()) {
            this.panelWindow = this.clickGuiPanel;
        }
        if (this.panelWindow != null) {
            if (this.panelWindow.equals(this.clickGuiPanel)) {
                int n8 = n3;
                int n9 = n7;
                int n10 = n6;
                this.clickGuiPanel.applyIndexByIndex(n10, n9, n8);
            } else {
                int n11 = n3;
                int n12 = n7;
                int n13 = n6;
                this.panelWindowAdapter.applyIndexByIndex(n13, n12, n11);
            }
        }
        if (!(n3 != 0 || ((PanelWindow)this.panelWindowAdapter).isActiveAsFlag() && ((PanelWindow)this.panelWindowAdapter).isActive() || ((PanelWindow)this.clickGuiPanel).isActiveAsFlag() && ((PanelWindow)this.clickGuiPanel).isActive())) {
            int n14 = 43;
            int n15 = this.count2 - 43 - 6;
            int n16 = 0;
            int n17 = 42;
            if (this.checkIndex(n15, n16, n17, n14, n4, n5)) {
                int n18 = ((PanelWindow)this.panelWindowAdapter).getCount3();
                int n19 = ((PanelWindow)this.panelWindowAdapter).getCount7();
                int n20 = ((PanelWindow)this.panelWindowAdapter).getCount();
                int n21 = ((PanelWindow)this.panelWindowAdapter).getCount5();
                if (!this.checkIndex(n21, n20, n19, n18, n4, n5) || !((PanelWindow)this.panelWindowAdapter).isActive()) {
                    int n22 = ((PanelWindow)this.clickGuiPanel).getCount3();
                    int n23 = ((PanelWindow)this.clickGuiPanel).getCount7();
                    int n24 = ((PanelWindow)this.clickGuiPanel).getCount();
                    int n25 = ((PanelWindow)this.clickGuiPanel).getCount5();
                    if (!this.checkIndex(n25, n24, n23, n22, n4, n5) || !((PanelWindow)this.clickGuiPanel).isActive()) {
                        if (!((PanelWindow)this.clickGuiPanel).isActive() && this.clickGuiPanel.enabled3) {
                            this.panelWindow = this.clickGuiPanel;
                        }
                        String string = "ClickGui";
                        ((PanelWindow)this.clickGuiPanel).applyTextByText(string);
                        this.panelWindow = this.clickGuiPanel;
                        SoundPlayer.processAction(SoundPlayer.resourceLocation14);
                    }
                }
            }
            int n26 = 43;
            int n27 = this.count2 + 1;
            int n28 = 0;
            int n29 = 42;
            if (this.checkIndex(n27, n28, n29, n26, n4, n5)) {
                int n30 = ((PanelWindow)this.panelWindowAdapter).getCount3();
                int n31 = ((PanelWindow)this.panelWindowAdapter).getCount7();
                int n32 = ((PanelWindow)this.panelWindowAdapter).getCount();
                int n33 = ((PanelWindow)this.panelWindowAdapter).getCount5();
                if (!this.checkIndex(n33, n32, n31, n30, n4, n5) || !((PanelWindow)this.panelWindowAdapter).isActive()) {
                    int n34 = ((PanelWindow)this.clickGuiPanel).getCount3();
                    int n35 = ((PanelWindow)this.clickGuiPanel).getCount7();
                    int n36 = ((PanelWindow)this.clickGuiPanel).getCount();
                    int n37 = ((PanelWindow)this.clickGuiPanel).getCount5();
                    if (!this.checkIndex(n37, n36, n35, n34, n4, n5) || !((PanelWindow)this.clickGuiPanel).isActive()) {
                        if (!((PanelWindow)this.panelWindowAdapter).isActive() && this.panelWindowAdapter.enabled3) {
                            this.panelWindow = this.panelWindowAdapter;
                        }
                        String string = "Configs";
                        ((PanelWindow)this.panelWindowAdapter).applyTextByText(string);
                        this.panelWindow = this.panelWindowAdapter;
                        SoundPlayer.processAction(SoundPlayer.resourceLocation14);
                    }
                }
            }
        }
    }

    @Override
    public void applyIndex(int n, int n2, float f) {
        int n3 = n;
        int n4 = n2;
        long l = 0xED85A06CF9BL ^ 0x4858B57E3B00L;
        MovementHandler.processAction12();
        ScaledResolution scaledResolution = new ScaledResolution(this.mc);
        if (((PanelWindow)this.clickGuiPanel).isActive() || ((PanelWindow)this.panelWindowAdapter).isActive()) {
            EventGuiOpenCallback.resolveEventGuiOpenByGuiScreen(this);
        }
        GL11.glPushMatrix();
        GL11.glScaled(1.0 / (double)scaledResolution.getScaleFactor(), 1.0 / (double)scaledResolution.getScaleFactor(), 1.0);
        int n5 = n3;
        int n6 = n4;
        n3 *= scaledResolution.getScaleFactor();
        n4 *= scaledResolution.getScaleFactor();
        this.count5 = this.mc.getFramebuffer().indexOffset2;
        this.count3 = this.mc.getFramebuffer().indexOffset1;
        this.count2 = (int)((float)this.count5 / 2.0f);
        this.count4 = (int)((float)this.count3 / 2.0f);
        if (!((PanelWindow)this.clickGuiPanel).isActiveAsFlag() && !((PanelWindow)this.panelWindowAdapter).isActiveAsFlag()) {
            MovementHandler.processBoolean(ClientGlobals.moduleManager.clickGui.settingClickGui.canExecute(), ClientGlobals.moduleManager.clickGui.settingClickGui.canExecute2());
            Color color = new Color(40, 39, 39, MathHelper.getInt12((int)ClientGlobals.moduleManager.clickGui.settingAlpha.getValue(), 0, 255));
            MovementHandler.applyRunnableTask(false, lambdaArgA -> this.applyRenderPass(6, lambdaArgA));
            int n7 = 43;
            int n8 = 42;
            int n9 = 0;
            int n10 = this.count2 - 43 - 6;
            if (this.checkIndex(n10, n9, n8, n7, n3, n4)) {
                int n11 = ((PanelWindow)this.panelWindowAdapter).getCount3();
                int n12 = ((PanelWindow)this.panelWindowAdapter).getCount7();
                int n13 = ((PanelWindow)this.panelWindowAdapter).getCount();
                int n14 = ((PanelWindow)this.panelWindowAdapter).getCount5();
                if (!this.checkIndex(n14, n13, n12, n11, n3, n4) || !((PanelWindow)this.panelWindowAdapter).isActive()) {
                    int n15 = ((PanelWindow)this.clickGuiPanel).getCount3();
                    int n16 = ((PanelWindow)this.clickGuiPanel).getCount7();
                    int n17 = ((PanelWindow)this.clickGuiPanel).getCount();
                    int n18 = ((PanelWindow)this.clickGuiPanel).getCount5();
                    if (!this.checkIndex(n18, n17, n16, n15, n3, n4) || !((PanelWindow)this.clickGuiPanel).isActive()) {
                        RenderUtils.processAction(this.count2 - 43 - 6, 0.0f, this.count2 - 1, 43, 6, 1, false, false, true, false, new Color(237, 66, 69, MathHelper.getInt12((int)ClientGlobals.moduleManager.clickGui.settingAlpha.getValue(), 0, 255)));
                    }
                }
            }
            int n19 = 43;
            int n20 = this.count2 + 1;
            int n21 = 0;
            int n22 = 42;
            if (this.checkIndex(n20, n21, n22, n19, n3, n4)) {
                int n23 = ((PanelWindow)this.panelWindowAdapter).getCount3();
                int n24 = ((PanelWindow)this.panelWindowAdapter).getCount7();
                int n25 = ((PanelWindow)this.panelWindowAdapter).getCount();
                int n26 = ((PanelWindow)this.panelWindowAdapter).getCount5();
                if (!this.checkIndex(n26, n25, n24, n23, n3, n4) || !((PanelWindow)this.panelWindowAdapter).isActive()) {
                    int n27 = ((PanelWindow)this.clickGuiPanel).getCount3();
                    int n28 = ((PanelWindow)this.clickGuiPanel).getCount7();
                    int n29 = ((PanelWindow)this.clickGuiPanel).getCount();
                    int n30 = ((PanelWindow)this.clickGuiPanel).getCount5();
                    if (!this.checkIndex(n30, n29, n28, n27, n3, n4) || !((PanelWindow)this.clickGuiPanel).isActive()) {
                        RenderUtils.processAction(this.count2 + 1, 0.0f, this.count2 + 43 + 6, 43, 6, 1, false, false, false, true, new Color(237, 66, 69, MathHelper.getInt12((int)ClientGlobals.moduleManager.clickGui.settingAlpha.getValue(), 0, 255)));
                    }
                }
            }
            if (ClientGlobals.moduleManager.clickGui.settingOutline.isEnabled()) {
                RenderUtils.processAction((float)(this.count2 - 43 - 6), (float)-6, (float)(this.count2 + 43 + 6), (float)43, (float)6, 1, 1, color);
            }
            double d = 1.0;
            int n31 = 36;
            int n32 = 36;
            int n33 = 2;
            int n34 = this.count2 - 36 - 7;
            PicturesConfigPngManager.applyResourceLocation(PicturesConfigPngManager.resourceLocation4, n34, n33, n32, n31, d);
            double d2 = 1.0;
            int n35 = 36;
            int n36 = 36;
            int n37 = 2;
            int n38 = this.count2 + 7;
            PicturesConfigPngManager.applyResourceLocation(PicturesConfigPngManager.resourceLocation, n38, n37, n36, n35, d2);
        }
        GL11.glPopMatrix();
        if (this.panelWindow != null) {
            if (this.panelWindow.equals(this.clickGuiPanel)) {
                if (!((PanelWindow)this.clickGuiPanel).isActiveAsFlag()) {
                    this.panelWindowAdapter.applyIndex(n5, n6, f);
                }
                this.clickGuiPanel.applyIndex(n5, n6, f);
            } else {
                if (!((PanelWindow)this.panelWindowAdapter).isActiveAsFlag()) {
                    this.clickGuiPanel.applyIndex(n5, n6, f);
                }
                this.panelWindowAdapter.applyIndex(n5, n6, f);
            }
        } else {
            this.panelWindowAdapter.applyIndex(n5, n6, f);
            this.clickGuiPanel.applyIndex(n5, n6, f);
        }
        MovementHandler.processAction5();
        super.applyIndex(n3, n4, f);
    }

    private void applyRenderPass(int n, RenderPass renderPass) {
        if (renderPass == RenderPass.SHADOW) {
            int n2 = Color.black.getRGB();
            RenderUtils.processFloat9(this.count2 - 43 - n, -n, this.count2 + 43 + n, 37 + n, n, 1, ClientGlobals.moduleManager.clickGui.settingClickGui.getInt3(n2));
        } else {
            RenderUtils.processAction((float)(this.count2 - 43 - n), (float)(-n), (float)(this.count2 + 43 + n), (float)(37 + n), (float)n, 1, new Color(40, 39, 39));
        }
    }

    public ClickGuiScreen() {
        this.panelWindowAdapter = new ClickGuiWindow("Configs", -1, -1, 654, 380).getClickGuiWindow();
    }
}

