package client.gui;

import client.event.PostUpdateProcessor;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.ClientScreen;
import client.gui.InternalTexturesGuiIconsPngManagerAdapter;
import client.setting.ClientSettingsBlackType;
import java.util.UUID;
import net.minecraft.client.gui.GuiScreen;

public class ClientScreenAdapter
extends ClientScreen {
    public InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter;
    public static boolean enabled7 = false;
    public InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter2;
    public static String labelText;
    public static String labelText2;

    private static void setLabelText(String string) {
        labelText = string;
    }

    @Override
    public void applyIndexByIndex(int n, int n2, float f) {
        this.mc.fontRendererObj.getIndex2("Target Address", this.internalTexturesGuiIconsPngManagerAdapter.count7 + this.internalTexturesGuiIconsPngManagerAdapter.getCount() + 12, (float)this.internalTexturesGuiIconsPngManagerAdapter.count10 + (float)this.internalTexturesGuiIconsPngManagerAdapter.getCount11() / 4.0f, -1);
        this.mc.fontRendererObj.getIndex2("Player UUID", this.internalTexturesGuiIconsPngManagerAdapter2.count7 + this.internalTexturesGuiIconsPngManagerAdapter2.getCount() + 12, (float)this.internalTexturesGuiIconsPngManagerAdapter2.count10 + (float)this.internalTexturesGuiIconsPngManagerAdapter2.getCount11() / 4.0f, -1);
    }

    private static void setLabelText2(String string) {
        labelText2 = string;
    }

    private String getTextAsText() {
        return "Enabled: " + (enabled7 ? (Object)((Object)ClientSettingsBlackType.GREEN) + "yes" : (Object)((Object)ClientSettingsBlackType.RED) + "no");
    }

    @Override
    public void renderElement() {
        int n = 0;
        long l = 0x221fe2d6b876L;
        long l2 = ((long)n << 48 | l << 16 >>> 16) ^ 0x6FCE4948CEB3L;
        super.renderElement();
        int n2 = this.height / 4 + 48;
        int n3 = this.width / 2 - 100;
        this.internalTexturesGuiIconsPngManagerAdapter = ((ClientScreen)this).resolveInternalTexturesGuiIconsPngM(n3, n2, 200, 20);
        int n4 = n2 + 20 + 3;
        int n5 = this.width / 2 - 100;
        this.internalTexturesGuiIconsPngManagerAdapter2 = ((ClientScreen)this).resolveInternalTexturesGuiIconsPngM(n5, n4, 200, 20);
        this.internalTexturesGuiIconsPngManagerAdapter.applyText(labelText2);
        this.internalTexturesGuiIconsPngManagerAdapter2.applyText(labelText);
        this.internalTexturesGuiIconsPngManagerAdapter.setConsumer(text -> ClientScreenAdapter.setLabelText2((String)text));
        this.internalTexturesGuiIconsPngManagerAdapter2.setConsumer(text -> ClientScreenAdapter.setLabelText((String)text));
        PostUpdateProcessor postUpdateProcessor = this::applyAuxiliaryTexturesGuiIconsP2;
        String string = this.getTextAsText();
        int n6 = 20;
        int n7 = 200;
        int n8 = this.height / 2 + 40;
        int n9 = this.width / 2 - 100;
        ((ClientScreen)this).resolveAuxiliaryTexturesGuiIconsPng(n9, n8, n7, n6, string, postUpdateProcessor);
    }

    private void applyAuxiliaryTexturesGuiIconsP2(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
        enabled7 = !enabled7;
        auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = this.getTextAsText();
    }

    public static String resolveText(String string) {
        String string2 = string;
        if (enabled7) {
            string2 = string2 + "\u0000" + labelText2 + "\u00000" + labelText.replace("-", "");
        }
        return string2;
    }

    public ClientScreenAdapter(GuiScreen guiScreen) {
        super(guiScreen);
    }

    static {
        labelText2 = "127.0.0.1";
        labelText = UUID.randomUUID().toString();
    }
}

