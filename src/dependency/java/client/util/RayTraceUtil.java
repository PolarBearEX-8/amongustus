package client.util;

import client.Client;
import client.util.ClientManager;
import client.util.MinecraftAccessor;
import client.util.TimeHelper;
import com.viaversion.viaversion.libs.mcstructs.text.TextComponent;
import com.viaversion.viaversion.libs.mcstructs.text.serializer.TextComponentSerializer;
import com.viaversion.viaversion.libs.mcstructs.text.stringformat.StringFormat;
import com.viaversion.viaversion.libs.mcstructs.text.stringformat.handling.ColorHandling;
import com.viaversion.viaversion.libs.mcstructs.text.stringformat.handling.DeserializerUnknownHandling;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentSerializer;
import net.minecraft.util.IChatComponent;

public class RayTraceUtil
implements MinecraftAccessor {
    public static void applyString(String string) {
        if (MinecraftAccessor.minecraft != null && MinecraftAccessor.minecraft.thePlayer != null && MinecraftAccessor.minecraft.theWorld != null && MinecraftAccessor.minecraft.ingameGUI != null && MinecraftAccessor.minecraft.ingameGUI.getInternalLoggerEntry() != null) {
            IChatComponent iChatComponent = RayTraceUtil.processAction("§6[§9" + Client.getClient().getString4() + "§6] §7" + string);
            if (Minecraft.getMinecraft().isSingleplayer()) {
                MinecraftAccessor.minecraft.ingameGUI.getInternalLoggerEntry().applyIChatComponent(iChatComponent);
            } else {
                ClientManager.applyRunnable2(() -> RayTraceUtil.applyIChatComponent(iChatComponent));
            }
        }
    }

    public static String resolveText(String string, String string2) {
        String string3 = "§6[§9" + string + "§6] §r" + string2;
        if (MinecraftAccessor.minecraft != null && MinecraftAccessor.minecraft.thePlayer != null && MinecraftAccessor.minecraft.theWorld != null && MinecraftAccessor.minecraft.ingameGUI != null && MinecraftAccessor.minecraft.ingameGUI.getInternalLoggerEntry() != null) {
            MinecraftAccessor.minecraft.ingameGUI.getInternalLoggerEntry().applyIChatComponent(RayTraceUtil.processAction(string3));
        }
        return string3;
    }

    public static IChatComponent processAction(String string) {
        TextComponent textComponent = StringFormat.vanilla().fromString(string, ColorHandling.RESET, DeserializerUnknownHandling.WHITE);
        return ChatComponentSerializer.parseChatComponent(TextComponentSerializer.V1_8.serialize(textComponent));
    }

    public static void applyDouble(double d) {
        RayTraceUtil.applyString(d + "");
    }

    private static void applyIChatComponent(IChatComponent iChatComponent) {
        MinecraftAccessor.minecraft.ingameGUI.getInternalLoggerEntry().applyIChatComponent(iChatComponent);
    }

    public static void applyFloat(float f) {
        RayTraceUtil.applyString(f + "");
    }
}

