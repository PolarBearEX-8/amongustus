package client.gui;

import client.ClientGlobals;
import client.font.FontFace;
import client.font.FontRegistry;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.ModuleModeOption;
import client.setting.ValueProperty;
import client.util.AuxiliaryStringArrayEntry;
import client.util.InternalListenerCoordinator;
import client.util.Listener60EntryAdapter;
import client.util.ListenerTracker;
import client.util.MinecraftAccessor;
import client.util.MovementHandler;
import client.util.SoundPlayer;
import java.awt.Color;
import java.io.IOException;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class PanelWindow
extends ListenerTracker
implements MinecraftAccessor {
    public int count7;
    public boolean enabled;
    public int count2;
    public final int count8;
    public int count5;
    public final AuxiliaryStringArrayEntry auxiliaryStringArrayEntry2;
    public final AuxiliaryStringArrayEntry auxiliaryStringArrayEntry;
    public final int count9;
    public String labelText2;
    public boolean enabled3;
    public boolean enabled2;
    public final InternalListenerCoordinator internalListenerCoordinator6;
    public int count6;
    public int count10;
    public int count3;
    public final InternalListenerCoordinator internalListenerCoordinator;
    public final InternalListenerCoordinator internalListenerCoordinator8;
    public final InternalListenerCoordinator internalListenerCoordinator3;
    public final InternalListenerCoordinator internalListenerCoordinator5;
    public int count4;
    public final InternalListenerCoordinator internalListenerCoordinator4;
    public boolean enabled4;
    public final InternalListenerCoordinator internalListenerCoordinator2;
    public final InternalListenerCoordinator internalListenerCoordinator7;
    public FontFace fontFace;

    public void applyIndex4(int n) {
        Integer n2 = n;
        ((ValueProperty)this.internalListenerCoordinator5).applyObject2(n2);
    }

    public int getCount5() {
        return (Integer)((ValueProperty)this.internalListenerCoordinator3).getValue();
    }

    public void applyIndex(int n) {
        Integer n2 = n;
        ((ValueProperty)this.internalListenerCoordinator3).applyObject2(n2);
    }

    public void setEnabled(boolean bl) {
        Boolean bl2 = bl;
        ((ValueProperty)this.auxiliaryStringArrayEntry2).applyObject2(bl2);
    }

    public void applyChar(char c, int n) throws IOException {
    }

    public void renderElement() {
        int n = 0;
        int n2 = 13635;
        int n3 = -1474763906;
        long l = ((long)n << 48 | (long)n2 << 48 >>> 16 | (long)n3 << 32 >>> 32) ^ 0x1E9191A30EBAL;
        long l2 = l ^ 0x65CF47CED16AL;
        long l3 = l ^ 0x498414347EB3L;
        long l4 = l ^ 0x3D3CAEAD7919L;
        this.setEnabledByFlag(false);
        this.setEnabled(false);
        int n4 = this.getCount6();
        this.applyIndex2(n4);
        int n5 = this.getCount4();
        this.applyIndex6(n5);
        int n6 = this.getCount2();
        this.applyIndex(n6);
        int n7 = this.getCountAsIndex();
        this.applyIndex7(n7);
    }

    private void applyColorByColor(Color color, RenderPass renderPass) {
        if (renderPass != RenderPass.SHADOW) {
            RenderUtils.processAction(0.0f, 0.0f, (float)this.getCount7(), (float)this.getCount3(), this.isActiveAsFlag() ? 0.0f : (float)this.count6, 1, color);
        } else {
            int n = Color.black.getRGB();
            RenderUtils.processFloat9(0.0f, 0.0f, this.getCount7(), this.getCount3(), this.isActiveAsFlag() ? 0.0f : (float)this.count6, 1, ClientGlobals.moduleManager.clickGui.settingClickGui.getInt3(n));
        }
    }

    public int getCountAsIndex() {
        return (Integer)((ValueProperty)this.internalListenerCoordinator8).getValue();
    }

    public int getCount() {
        return (Integer)((ValueProperty)this.internalListenerCoordinator6).getValue();
    }

    public void applyIndex(int n, int n2, int n3) {
        int n4 = 0;
        int n5 = 1873905318;
        int n6 = 5551;
        long l = ((long)n4 << 48 | (long)n5 << 32 >>> 16 | (long)n6 << 48 >>> 48) ^ 0x172A92602F22L;
        this.enabled2 = false;
        this.enabled4 = false;
        if (this.enabled) {
            int n7 = this.getCount7();
            this.applyIndex5(n7);
            int n8 = this.getCount3();
            this.applyIndex3(n8);
        }
        this.enabled = false;
    }

    public void applyIndex2(int n) {
        Integer n2 = n;
        ((ValueProperty)this.internalListenerCoordinator).applyObject2(n2);
    }

    public void applyIndex(int n, int n2, float f) {
        int n3;
        int n4;
        int n5 = n;
        int n6 = n2;
        long l = 0x36CE1EE42B3DL ^ 0x78D360911793L;
        long l2 = 0x36CE1EE42B3DL ^ 0x5498336BB84AL;
        long l3 = 0x36CE1EE42B3DL ^ 0x202089F2BFE0L;
        long l4 = 0x36CE1EE42B3DL ^ 0x5ED735EE75F7L;
        if (!this.isActive()) {
            return;
        }
        ScaledResolution scaledResolution = new ScaledResolution(MinecraftAccessor.minecraft);
        GL11.glPushMatrix();
        GL11.glScaled(1.0 / (double)scaledResolution.getScaleFactor(), 1.0 / (double)scaledResolution.getScaleFactor(), 1.0);
        n5 *= scaledResolution.getScaleFactor();
        n6 *= scaledResolution.getScaleFactor();
        GL11.glPushMatrix();
        if (this.enabled2 && !this.isActiveAsFlag()) {
            if (this.enabled4) {
                int n7 = n5 - (this.count5 - this.count10);
                this.applyIndex(n7);
                int n8 = n6 - (this.count3 - this.count7);
                this.applyIndex7(n8);
                int n9 = this.getCount5();
                this.applyIndex4(n9);
                int n10 = this.getCount();
                this.applyIndexByIndex(n10);
            }
            if (this.enabled) {
                int n11 = Math.max(400, n5 - this.getCount5());
                this.applyIndex2(n11);
                int n12 = Math.max(250, n6 - this.getCount());
                this.applyIndex6(n12);
            }
        }
        n5 -= this.getCount5();
        n6 -= this.getCount();
        GL11.glTranslatef(this.getCount5(), this.getCount(), 0.0f);
        MovementHandler.processBoolean(ClientGlobals.moduleManager.clickGui.settingClickGui.canExecute(), ClientGlobals.moduleManager.clickGui.settingClickGui.canExecute2());
        Color color = new Color(40, 39, 39, MathHelper.getInt12((int)ClientGlobals.moduleManager.clickGui.settingAlpha.getValue(), 0, 255));
        MovementHandler.applyRunnableTask(false, lambdaArgA -> this.applyColorByColor(color, lambdaArgA));
        if (!this.isActiveAsFlag() && color.getAlpha() > 0) {
            RenderUtils.applyColor5(this.getCount7() - 20, this.getCount3() - 20, this.getCount7(), this.getCount3(), this.isActiveAsFlag() ? 0.0f : (float)this.count6, 1, new Color(34, 34, 34, 80));
        }
        RenderUtils.processAction2(0.0f, 0.0f, this.getCount7(), 30.0f, this.isActiveAsFlag() ? 0.0f : (float)this.count6, 1, new Color(34, 34, 34));
        int n13 = this.getCount7() - 46;
        Color color2 = this.checkIndexByIndex(n13, 0, 46, 30, n5, n6) ? new Color(253, 232, 232) : new Color(185, 187, 190);
        int n14 = this.getCount7() - 46;
        Color color3 = this.checkIndexByIndex(n14, 0, 46, 30, n5, n6) ? new Color(240, 102, 104) : new Color(61, 63, 66);
        int n15 = this.getCount7() - 92;
        Color color4 = this.checkIndexByIndex(n15, 0, 45, 30, n5, n6) ? new Color(220, 200, 200) : new Color(185, 187, 190);
        int n16 = this.getCount7() - 138;
        Color color5 = this.checkIndexByIndex(n16, 0, 45, 30, n5, n6) ? new Color(220, 200, 200) : new Color(185, 187, 190);
        int n17 = this.getCount7() - 46;
        if (this.checkIndexByIndex(n17, 0, 46, 30, n5, n6)) {
            RenderUtils.applyColor2(this.getCount7() - 46, 0.0f, this.getCount7(), 30.0f, this.count6, 1, new Color(237, 66, 69));
        }
        if (this.checkIndexByIndex(n4 = this.getCount7() - 92, 0, 45, 30, n5, n6)) {
            RenderUtils.processAction(this.getCount7() - 92, 0, this.getCount7() - 92 + 45, 30, 45, 48, 49);
        }
        if (this.checkIndexByIndex(n3 = this.getCount7() - 138, 0, 45, 30, n5, n6)) {
            RenderUtils.processAction(this.getCount7() - 138, 0, this.getCount7() - 138 + 45, 30, 45, 48, 49);
        }
        RenderUtils.processAction(this.getCount7() - 20, 10, this.getCount7() - 29, 19, 1.0f, color3);
        RenderUtils.processAction(this.getCount7() - 19, 11, this.getCount7() - 28, 20, 1.0f, color3);
        RenderUtils.processAction(this.getCount7() - 28, 10, this.getCount7() - 19, 19, 1.0f, color3);
        RenderUtils.processAction(this.getCount7() - 29, 11, this.getCount7() - 20, 20, 1.0f, color3);
        RenderUtils.processAction(this.getCount7() - 29, 10, this.getCount7() - 19, 20, 1.0f, color2);
        RenderUtils.processAction(this.getCount7() - 19, 10, this.getCount7() - 29, 20, 1.0f, color2);
        RenderUtils.processAction(this.getCount7() - 65, 10, this.getCount7() - 75, 10, 1.0f, color4);
        RenderUtils.processAction(this.getCount7() - 65, 19, this.getCount7() - 75, 19, 1.0f, color4);
        RenderUtils.processAction(this.getCount7() - 65, 10, this.getCount7() - 65, 19, 1.0f, color4);
        RenderUtils.processAction(this.getCount7() - 74, 10, this.getCount7() - 74, 19, 1.0f, color4);
        RenderUtils.processAction(this.getCount7() - 111, 15, this.getCount7() - 121, 15, 1.0f, color5);
        if (!this.isActiveAsFlag() && ClientGlobals.moduleManager.clickGui.settingOutline.isEnabled()) {
            RenderUtils.processAction(0.0f, 0.0f, (float)this.getCount7(), (float)this.getCount3(), this.isActiveAsFlag() ? 0.0f : (float)this.count6, 1, 1, new Color(40, 39, 39));
        }
        double d = 8.0;
        double d2 = 10.0;
        String string = this.labelText2;
        this.fontFace.applyString8(string, d2, d, -4604994);
        this.count4 = n5;
        this.count2 = n6;
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    public boolean checkIndex(int n, int n2, int n3, int n4, int n5, int n6) {
        return n5 >= n && n6 >= n2 && n5 < n3 && n6 < n4 && this.isActive();
    }

    public void applyIndexByIndex(int n, int n2, int n3) throws IOException {
        int n4;
        int n5 = n;
        int n6 = n2;
        long l = 1990674444L;
        int n7 = 4101;
        long l2 = l << 16 | (long)n7 << 48 >>> 48;
        long l3 = l2 ^ 0x38BA36792CABL;
        long l4 = l2 ^ 0x14F165838372L;
        long l5 = l2 ^ 0x6049DF1A84D8L;
        ScaledResolution scaledResolution = new ScaledResolution(MinecraftAccessor.minecraft);
        this.count5 = n5 * scaledResolution.getScaleFactor();
        int n8 = this.count3 = n6 * scaledResolution.getScaleFactor();
        int n9 = this.count5;
        int n10 = 30;
        int n11 = this.getCount7() - 138;
        int n12 = this.getCount();
        int n13 = this.getCount5();
        this.enabled4 = this.checkIndexByIndex(n13, n12, n11, n10, n9, n8);
        int n14 = this.count3;
        int n15 = this.count5;
        int n16 = 15;
        int n17 = 15;
        int n18 = this.getCount() + this.getCount3() - 15;
        int n19 = this.getCount5() + this.getCount7() - 15;
        this.enabled = this.checkIndexByIndex(n19, n18, n17, n16, n15, n14);
        this.count10 = this.getCount5();
        this.count7 = this.getCount();
        GL11.glPushMatrix();
        GL11.glScaled(1.0 / (double)scaledResolution.getScaleFactor(), 1.0 / (double)scaledResolution.getScaleFactor(), 1.0);
        n5 *= scaledResolution.getScaleFactor();
        n6 *= scaledResolution.getScaleFactor();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.getCount5(), this.getCount(), 0.0f);
        int n20 = this.getCount7() - 46;
        if (this.checkIndexByIndex(n20, 0, 46, 30, n5 -= this.getCount5(), n6 -= this.getCount())) {
            RenderUtils.applyColor2(this.getCount7() - 46, 0.0f, this.getCount7(), 30.0f, this.count6, 1, new Color(232, 17, 35));
            this.renderElement();
            this.enabled3 = true;
            this.setEnabled(false);
            SoundPlayer.processAction(SoundPlayer.resourceLocation14);
        }
        if (this.checkIndexByIndex(n4 = this.getCount7() - 92, 0, 45, 30, n5, n6)) {
            RenderUtils.processAction(this.getCount7() - 92, 0, this.getCount7() - 92 + 45, 30, 229, 229, 229);
            if (this.isActiveAsFlag()) {
                int n21 = this.getCount6();
                this.applyIndex2(n21);
                int n22 = this.getCount4();
                this.applyIndex6(n22);
                int n23 = this.getCount2();
                this.applyIndex(n23);
                int n24 = this.getCountAsIndex();
                this.applyIndex7(n24);
            } else {
                int n25 = MinecraftAccessor.minecraft.getFramebuffer().indexOffset2;
                this.applyIndex2(n25);
                int n26 = MinecraftAccessor.minecraft.getFramebuffer().indexOffset1;
                this.applyIndex6(n26);
                this.applyIndex(0);
                this.applyIndex7(0);
            }
            boolean bl = !this.isActiveAsFlag();
            this.setEnabled(bl);
            SoundPlayer.processAction(SoundPlayer.resourceLocation14);
            return;
        }
        int n27 = this.getCount7() - 138;
        if (this.checkIndexByIndex(n27, 0, 45, 30, n5, n6)) {
            RenderUtils.processAction(this.getCount7() - 138, 0, this.getCount7() - 138 + 45, 30, 229, 229, 229);
            this.renderElement();
            SoundPlayer.processAction(SoundPlayer.resourceLocation14);
        }
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    public int getCount3() {
        return (Integer)((ValueProperty)this.internalListenerCoordinator4).getValue();
    }

    public void renderElement3() throws IOException {
    }

    public void applyIndex6(int n) {
        Integer n2 = n;
        ((ValueProperty)this.internalListenerCoordinator4).applyObject2(n2);
    }

    public int getCount6() {
        return (Integer)((ValueProperty)this.internalListenerCoordinator7).getValue();
    }

    public void applyText2(String string) {
        int n = 0;
        int n2 = 9651;
        int n3 = 960253775;
        long l = ((long)n << 48 | (long)n2 << 48 >>> 16 | (long)n3 << 32 >>> 32) ^ 0x1E9191A30EBAL;
        long l2 = l ^ 0x753FD6EA7D5BL;
        long l3 = l ^ 0x59748510D282L;
        long l4 = l ^ 0x2DCC3F89D528L;
        long l5 = l ^ 0x533B83951F3FL;
        this.applyIndex(0);
        this.applyIndex7(0);
        this.applyIndex2(0);
        this.applyIndex6(0);
        this.enabled2 = false;
        this.enabled4 = false;
        this.enabled = false;
        this.setEnabled(false);
        this.setEnabledByFlag(false);
        this.enabled3 = false;
        this.labelText2 = string;
        float f = MinecraftAccessor.minecraft.getFramebuffer().indexOffset2;
        float f2 = MinecraftAccessor.minecraft.getFramebuffer().indexOffset1;
        int n4 = (int)(f / 2.0f);
        int n5 = (int)(f2 / 2.0f);
        this.applyIndex5(n4);
        this.applyIndex3(n5);
        int n6 = this.count8;
        this.applyIndex2(n6);
        int n7 = this.count9;
        this.applyIndex6(n7);
        this.count6 = 10;
        int n8 = (int)((float)n4 - (float)this.getCount7() / 2.0f);
        this.applyIndex4(n8);
        int n9 = (int)((float)n5 - (float)this.getCount3() / 2.0f);
        this.applyIndexByIndex(n9);
        int n10 = this.getCount2();
        this.applyIndex(n10);
        int n11 = this.getCountAsIndex();
        this.applyIndex7(n11);
        this.setEnabled(false);
        this.fontFace = FontRegistry.fontFace10;
        this.renderElement2();
    }

    public int getCount7() {
        return (Integer)((ValueProperty)this.internalListenerCoordinator).getValue();
    }

    public void applyIndex7(int n) {
        Integer n2 = n;
        ((ValueProperty)this.internalListenerCoordinator6).applyObject2(n2);
    }

    public void applyTextByText(String string) {
        long l = 0x390fe634fd38L;
        long l2 = 0x1E9191A30EBAL ^ l ^ 0x30B99F706A0AL;
        if (this.enabled3) {
            String string2 = string;
            this.applyText2(string2);
        } else {
            boolean bl = !this.isActive();
            this.setEnabledByFlag(bl);
        }
    }

    public int getCount4() {
        return (Integer)((ValueProperty)this.internalListenerCoordinator2).getValue();
    }

    public void applyIndex3(int n) {
        Integer n2 = n;
        ((ValueProperty)this.internalListenerCoordinator2).applyObject2(n2);
    }

    public boolean isActiveAsFlag() {
        return (Boolean)((ValueProperty)this.auxiliaryStringArrayEntry2).getValue();
    }

    public void applyIndex5(int n) {
        Integer n2 = n;
        ((ValueProperty)this.internalListenerCoordinator7).applyObject2(n2);
    }

    public void renderElement2() {
        this.setEnabledByFlag(true);
    }

    public boolean checkIndexByIndex(int n, int n2, int n3, int n4, int n5, int n6) {
        return n5 >= n && n6 >= n2 && n5 < n + n3 && n6 < n2 + n4 && this.isActive();
    }

    public boolean isActive() {
        return (Boolean)((ValueProperty)this.auxiliaryStringArrayEntry).getValue();
    }

    public int getCount2() {
        return (Integer)((ValueProperty)this.internalListenerCoordinator5).getValue();
    }

    public void applyIndexByIndex(int n) {
        Integer n2 = n;
        ((ValueProperty)this.internalListenerCoordinator8).applyObject2(n2);
    }

    public void applyIndex(int n, int n2, int n3, long l) {
        this.enabled2 = true;
    }

    public void setEnabledByFlag(boolean bl) {
        Boolean bl2 = bl;
        ((ValueProperty)this.auxiliaryStringArrayEntry).applyObject2(bl2);
    }

    public PanelWindow(String string, int n, int n2, int n3, int n4) {
        super(Listener60EntryAdapter.listenerTracker, string);
        int n5 = 0;
        int n6 = 316463;
        int n7 = 2140106;
        long l = ((long)n5 << 56 | (long)n6 << 32 >>> 8 | (long)n7 << 40 >>> 40) ^ 0x1E9191A30EBAL;
        long l2 = l ^ 0x5458C0F695DEL;
        long l3 = l ^ 0x7813930C3A07L;
        long l4 = l ^ 0xCAB29953DADL;
        long l5 = l ^ 0x725C9589F7BAL;
        
        this.count4 = 0;
        this.count2 = 0;
        this.count5 = 0;
        this.count3 = 0;
        this.count10 = 0;
        this.count7 = 0;
        this.internalListenerCoordinator3 = new InternalListenerCoordinator((ModuleModeOption)this, "X", 0);
        this.internalListenerCoordinator6 = new InternalListenerCoordinator((ModuleModeOption)this, "Y", 0);
        this.internalListenerCoordinator = new InternalListenerCoordinator((ModuleModeOption)this, "Width", 0);
        this.internalListenerCoordinator4 = new InternalListenerCoordinator((ModuleModeOption)this, "Height", 0);
        this.internalListenerCoordinator7 = new InternalListenerCoordinator((ModuleModeOption)this, "WindowWidth", 0);
        this.internalListenerCoordinator2 = new InternalListenerCoordinator((ModuleModeOption)this, "WindowHeight", 0);
        this.internalListenerCoordinator5 = new InternalListenerCoordinator((ModuleModeOption)this, "WindowX", 0);
        this.internalListenerCoordinator8 = new InternalListenerCoordinator((ModuleModeOption)this, "WindowY", 0);
        this.auxiliaryStringArrayEntry = new AuxiliaryStringArrayEntry((ModuleModeOption)this, "Visible", false);
        this.auxiliaryStringArrayEntry2 = new AuxiliaryStringArrayEntry((ModuleModeOption)this, "FullScreen", false);
        this.enabled2 = false;
        this.enabled4 = false;
        this.enabled = false;
        this.enabled3 = false;
        this.labelText2 = string;
        this.applyIndex5(n3);
        this.applyIndex3(n4);
        this.count8 = n3;
        this.count9 = n4;
        int n8 = this.getCount6();
        this.applyIndex2(n8);
        int n9 = this.getCount4();
        this.applyIndex6(n9);
        this.count6 = 10;
        int n10 = (int)((float)n - (float)n3 / 2.0f);
        this.applyIndex4(n10);
        int n11 = (int)((float)n2 - (float)n4 / 2.0f);
        this.applyIndexByIndex(n11);
        int n12 = this.getCount2();
        this.applyIndex(n12);
        int n13 = this.getCountAsIndex();
        this.applyIndex7(n13);
        this.setEnabled(false);
        this.fontFace = FontRegistry.fontFace10;
        this.renderElement2();
    }
}

