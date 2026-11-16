package client.util;

import client.Client;
import client.ClientGlobals;
import client.event.KeyInputListener;
import client.module.Module;
import client.util.TimeHelper;
import java.util.LinkedList;
import java.util.Queue;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class QueueEntry {
    public static final Queue queue = new LinkedList();
    public static long timestamp;
    public static final int count = 10000;

    public static void updateState2() {
        Runnable runnable;
        timestamp = System.currentTimeMillis();
        TimeHelper.getStringArray();
        while ((runnable = (Runnable)queue.poll()) != null) {
            runnable.run();
        }
    }

    public static void updateState() {
        TimeHelper.getStringArray();
        if (Minecraft.getMinecraft().theWorld == null || Minecraft.getMinecraft().thePlayer == null) {
            return;
        }
        int n = Mouse.getEventButton() + 10000;
        boolean bl = Mouse.getEventButtonState();
        double d = ClientGlobals.moduleManager.clickGui.settingDynamicKeyTime.getValue();
        long l = System.currentTimeMillis() - timestamp;
        if ((double)l > d && d > 70.0) {
            d = l + 50L;
        }
        for (Module module : (Iterable<Module>)(Iterable)Client.getClient().getModuleManager().getCopyOnWriteArrayList2()) {
            if (n == 0 || module.getInt() != n) continue;
            if (!((double)(System.currentTimeMillis() - module.getEpochMillis()) > d && !bl && module.isToggled() || bl)) {
                if (!module.isToggled() || !module.canExecute3()) continue;
            }
            queue.add((Runnable)module::onReset);
        }
        if (bl && n != 0) {
            KeyInputListener.applyIndex(n, true);
        }
    }

    public static void updateState3() {
        TimeHelper.getStringArray();
        if (Minecraft.getMinecraft().theWorld == null || Minecraft.getMinecraft().thePlayer == null) {
            return;
        }
        boolean bl = Keyboard.getEventKeyState();
        int n = Keyboard.getEventKey();
        double d = ClientGlobals.moduleManager.clickGui.settingDynamicKeyTime.getValue();
        long l = System.currentTimeMillis() - timestamp;
        if ((double)l > d && d > 70.0) {
            d = l + 50L;
        }
        for (Module module : (Iterable<Module>)(Iterable)Client.getClient().getModuleManager().getCopyOnWriteArrayList2()) {
            if (n == 0 || module.getInt() != n) continue;
            if (!((double)(System.currentTimeMillis() - module.getEpochMillis()) > d && !bl && module.isToggled() || bl)) {
                if (!module.isToggled() || !module.canExecute3()) continue;
            }
            queue.add((Runnable)module::onReset);
        }
        if (bl && n != 0) {
            KeyInputListener.applyIndex(n, false);
        }
    }
}

