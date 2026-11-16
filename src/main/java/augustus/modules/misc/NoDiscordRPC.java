package augustus.modules.misc;

import client.Client;
import client.gui.ClickGuiScreen;
import client.module.Category;
import client.module.Module;
import client.util.DiscordRpcWorker;
import java.awt.Color;
import net.minecraft.client.gui.GuiChat;

public class NoDiscordRPC extends Module {
    public NoDiscordRPC() {
        super("NoDiscordRPC", "KeinDiscord", new Color(180, 246, 250), Category.MISC);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
        if (minecraft.isIntegratedServerRunning()
            || minecraft.currentScreen instanceof GuiChat
            || minecraft.thePlayer != null
            || minecraft.theWorld != null
            || minecraft.currentScreen instanceof ClickGuiScreen) {
            Client.getClient().getThreadEntry().updateState2();
            if (minecraft.theWorld != null) {
                if (minecraft.theWorld.state) {
                    Client.getClient().getThreadEntry().applyString("Playing lonely in singleplayer", "Ingame");
                } else {
                    Client.getClient().getThreadEntry().applyString("Maybe cheating on ReifenHD.net", "Cheating");
                }
            }
        }
    }

    @Override
    public void onPreDisable() {
        if (minecraft.isIntegratedServerRunning()
            || minecraft.currentScreen instanceof GuiChat
            || minecraft.thePlayer != null
            || minecraft.theWorld != null
            || minecraft.currentScreen instanceof ClickGuiScreen) {
            Client.getClient().getThreadEntry().updateState();
            Client.getClient().getThreadEntry().updateState2();
        }
    }
}
