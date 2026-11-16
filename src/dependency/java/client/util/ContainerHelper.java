package client.util;

import client.Client;
import client.gui.CustomGuiScreen;
import client.util.MinecraftAccessor;
import client.util.Pair;
import client.util.TimeHelper;
import java.util.ArrayList;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerHelper
implements MinecraftAccessor {
    public static boolean isContainerActive() {
        return MinecraftAccessor.minecraft.currentScreen instanceof GuiInventory;
    }

    private static void applyContainer(Container container, int n, Runnable runnable) {
        runnable.run();
        ScaledResolution scaledResolution = new ScaledResolution(MinecraftAccessor.minecraft);
        int n2 = (scaledResolution.getScaledWidth() - 176) / 2;
        int n3 = (scaledResolution.getScaledHeight() - ContainerHelper.size(container)) / 2;
        Slot slot = container.getSlot(n);
        double[] dArray = new double[]{slot.indexOffset + 2 + n2, slot.indexOffset2 + 2 + n3};
    }

    public static void applyContainer(Container container, String string, int n, int n2, Runnable runnable) {
        ContainerHelper.applyContainer(container, n, runnable);
        TimeHelper.getStringArray();
        String string2 = string;
        int n3 = -1;
        switch (string.hashCode()) {
            case 355504491: {
                if (!string2.equals("Exploit")) break;
                n3 = 0;
                break;
            }
            case 63955982: {
                if (!string2.equals("Basic")) break;
                n3 = 1;
                break;
            }
            case 76517104: {
                if (!string2.equals("Other")) break;
                n3 = 2;
            }
        }
        switch (n3) {
            case 0: {
                Client.getClient().getClass();
                break;
            }
            case 1: 
            case 2: {
                MinecraftAccessor.minecraft.playerController.windowClick(container.indexOffset2, n, n2, 2, MinecraftAccessor.minecraft.thePlayer);
            }
        }
    }

    public static void applyContainer(Container container, String string, int n, Runnable runnable) {
        ContainerHelper.applyContainer(container, n, runnable);
        String string2 = string;
        TimeHelper.getStringArray();
        int n2 = -1;
        switch (string.hashCode()) {
            case 63955982: {
                if (!string2.equals("Basic")) break;
                n2 = 0;
                break;
            }
            case 76517104: {
                if (!string2.equals("Other")) break;
                n2 = 1;
                break;
            }
            case 355504491: {
                if (!string2.equals("Exploit")) break;
                n2 = 2;
            }
        }
        switch (n2) {
            case 0: {
                MinecraftAccessor.minecraft.playerController.windowClick(container.indexOffset2, n, 1, 4, MinecraftAccessor.minecraft.thePlayer);
                break;
            }
            case 1: {
                Client.getClient().getClass();
                break;
            }
            case 2: {
                Client.getClient().getClass();
            }
        }
    }

    public static boolean isActive() {
        return MinecraftAccessor.minecraft.currentScreen instanceof CustomGuiScreen;
    }

    private static int size(Container container) {
        return 114 + (container.itemList2.size() - 36) / 9 * 18;
    }

    public static void applyContainer(Container container, int n, int n2, Runnable runnable) {
        ContainerHelper.applyContainer(container, n, runnable);
        MinecraftAccessor.minecraft.playerController.windowClick(container.indexOffset2, n, n2, 0, MinecraftAccessor.minecraft.thePlayer);
    }

    public static void applyContainerByContainer(Container container, String string, int n, Runnable runnable) {
        ContainerHelper.applyContainer(container, n, runnable);
        String string2 = string;
        TimeHelper.getStringArray();
        int n2 = -1;
        switch (string.hashCode()) {
            case 63955982: {
                if (!string2.equals("Basic")) break;
                n2 = 0;
                break;
            }
            case 76517104: {
                if (!string2.equals("Other")) break;
                n2 = 1;
                break;
            }
            case 355504491: {
                if (!string2.equals("Exploit")) break;
                n2 = 2;
            }
        }
        switch (n2) {
            case 0: {
                MinecraftAccessor.minecraft.playerController.windowClick(container.indexOffset2, n, 0, 1, MinecraftAccessor.minecraft.thePlayer);
                break;
            }
            case 1: {
                Client.getClient().getClass();
                break;
            }
            case 2: {
                Client.getClient().getClass();
            }
        }
    }

    private static ArrayList resolveArrayList(ItemStack itemStack, Container container) {
        TimeHelper.getStringArray();
        if (itemStack.processAction5() <= 1) {
            return new ArrayList();
        }
        ArrayList<Pair> arrayList = new ArrayList<Pair>();
        for (int i = container.itemList2.size() - 36; i < container.itemList2.size(); ++i) {
            ItemStack itemStack2 = container.getSlot(i).getStack();
            if (itemStack2 == null || itemStack2.animationsToGo >= itemStack2.processAction5() || !itemStack2.getItem().equals(itemStack.getItem())) continue;
            arrayList.add(new Pair(i, itemStack2.processAction5() - itemStack2.animationsToGo));
        }
        return arrayList;
    }

    private static ArrayList resolveArrayList(int n, Container container) {
        TimeHelper.getStringArray();
        ItemStack itemStack = container.getSlot(n).getStack();
        if (itemStack == null) {
            return new ArrayList();
        }
        int n2 = itemStack.animationsToGo;
        ArrayList<Object> arrayList = new ArrayList<Object>();
        if (itemStack.processAction5() > 1) {
            for (Pair pair : (Iterable<Pair>)(Iterable)ContainerHelper.resolveArrayList(itemStack, container)) {
                arrayList.add(pair.getSecond());
                if ((n2 -= ((Integer)pair.getFirst()).intValue()) > 0) continue;
                return arrayList;
            }
        }
        for (int i = container.itemList2.size() - 1; i >= container.itemList2.size() - 36; --i) {
            if (container.getSlot(i).getStack() != null) continue;
            arrayList.add(i);
            break;
        }
        return arrayList;
    }
}

