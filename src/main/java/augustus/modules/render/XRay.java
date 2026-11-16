package augustus.modules.render;

import augustus.modes.render.XRayBlockEntry;
import client.event.EventChunkLoad;
import client.event.EventChunkLoadCallback;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.XRayHideType;
import client.util.AlertNotificationSound;
import client.util.BooleanSupplier;
import client.util.IBlockState;
import java.awt.Color;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockQuartzOre;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class XRay extends Module implements EventChunkLoadCallback, TickListener {
    public static boolean activeState3;
    public static BooleanSetting booleanSetting;
    public static BooleanSetting booleanSetting2;
    public static NumberSetting numberSetting;
    public static boolean activeState;
    public static boolean activeState2;
    public static int tickDelay;
    public static HashMap entryMap = new HashMap();
    public boolean reloadPending = false;
    public static boolean activeState1;

    public XRay() {
        super("XRay", "Röntgenstrahl", Color.BLUE, Category.RENDER);
        Iterator iterator = Block.iterableExtensionBridge.getSet().iterator();

        while (iterator.hasNext()) {
            String text = ((ResourceLocation)iterator.next()).getText1();
            Block block = Block.getBlockFromName(text);
            HashMap map = entryMap;
            boolean enabled;
            if (block instanceof BlockQuartzOre) {
                enabled = true;
            } else if (!(block instanceof BlockLiquid)) {
                enabled = false;
            } else {
                enabled = true;
            }

            XRayBlockEntry xRayBlockEntry = new XRayBlockEntry(this, text, enabled, null);
            map.put(block, xRayBlockEntry);
        }

        Collection collection = entryMap.values();
        XRayBlockEntry[] xRayBlockEntryArray = new XRayBlockEntry[0];
        XRayBlockEntry[] xRayBlockEntryArray2 = ((XRayBlockEntry[])(collection.toArray(xRayBlockEntryArray)));
        Arrays.sort(xRayBlockEntryArray2, Comparator.comparing(xRayBlockEntry2 -> ((XRayBlockEntry)xRayBlockEntry2).getText()));
        new MultiSelectSetting(this, "Visible Blocks", xRayBlockEntryArray2);
        booleanSetting = new BooleanSetting(this, "Anti-Xray", false);
        BooleanSetting booleanSetting3 = new BooleanSetting(this, "Simple", true);
        booleanSetting2 = (BooleanSetting)booleanSetting3.getSetting(() -> booleanSetting.isEnabled());
        NumberSetting numberSetting2 = new NumberSetting(this, "Minimum free space", 1.0, 1.0, 10.0, 0);
        numberSetting = numberSetting2.getNumberSetting(() -> canExecute5());
    }

    @Override
    public void onEnable() {
        this.registerEvent(22);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
        activeState3 = false;
        minecraft.renderGlobal.updateState();
    }

    @Override
    public void onPreDisable() {
        activeState3 = true;
        minecraft.renderGlobal.updateState();
        this.updateState();
    }

    @Override
    public void onChunkLoad(EventChunkLoad event) {
        event.setActive(true);
    }

    @Override
    public void onChunkLoad2(EventChunkLoad event) {
        event.setActive(true);
    }

    @Override
    public void onPreTick() {
        if (this.reloadPending) {
            this.reloadPending = false;
            minecraft.renderGlobal.updateState();
        }

        if (booleanSetting.isEnabled() == activeState) {
            if (!activeState) {
                return;
            }

            BooleanSetting setting = booleanSetting2;
            if (activeState2 == setting.isEnabled()) {
                if (activeState2) {
                    return;
                }

                NumberSetting setting2 = numberSetting;
                if ((double)tickDelay == setting2.getValue()) {
                    return;
                }
            }
        }

        minecraft.renderGlobal.updateState();
        this.updateState();
    }

    private static boolean canExecute5() {
        return !booleanSetting.isEnabled() ? false : !booleanSetting2.isEnabled();
    }

    public static void setActive4(boolean enabled) {
        activeState1 = enabled;
    }

    private void updateState() {
        tickDelay = (int)numberSetting.getValue();
        activeState = booleanSetting.isEnabled();
        activeState2 = booleanSetting2.isEnabled();
    }

    public static boolean checkXRay(XRay xRay, boolean enabled) {
        return xRay.reloadPending = enabled;
    }

    public static boolean canExecute6() {
        return !canExecute7();
    }

    public static XRayHideType getXRayHideType(Block block, @Nullable IBlockState blockState, BlockPos blockPos) {
        XRayBlockEntry xRayBlockEntry = (XRayBlockEntry)entryMap.get(block);
        if (xRayBlockEntry == null) {
            return XRayHideType.HIDE;
        } else if (!xRayBlockEntry.state) {
            return XRayHideType.HIDE;
        } else if (!booleanSetting.isEnabled()) {
            return XRayHideType.FORCE_VISIBLE;
        } else if (booleanSetting2.isEnabled()) {
            return XRayHideType.NOTHING;
        } else {
            int numberSetting2 = (int)numberSetting.getValue();
            if (checkBlockPos(blockPos, EnumFacing.UP, numberSetting2)) {
                return XRayHideType.FORCE_VISIBLE;
            } else if (checkBlockPos(blockPos, EnumFacing.DOWN, numberSetting2)) {
                return XRayHideType.FORCE_VISIBLE;
            } else if (checkBlockPos(blockPos, EnumFacing.EAST, numberSetting2)) {
                return XRayHideType.FORCE_VISIBLE;
            } else if (checkBlockPos(blockPos, EnumFacing.WEST, numberSetting2)) {
                return XRayHideType.FORCE_VISIBLE;
            } else if (checkBlockPos(blockPos, EnumFacing.NORTH, numberSetting2)) {
                return XRayHideType.FORCE_VISIBLE;
            } else {
                return checkBlockPos(blockPos, EnumFacing.SOUTH, numberSetting2) ? XRayHideType.FORCE_VISIBLE : XRayHideType.HIDE;
            }
        }
    }

    private static boolean checkBlockPos(BlockPos blockPos, EnumFacing enumFacing, int index3) {
        for (int i = 1; i <= index3; i++) {
            int index = blockPos.getY() + i * enumFacing.getInt3();
            if (index < 0 || index > 255) {
                return false;
            }

            int x = blockPos.getX();
            int index2 = enumFacing.getInt2() * i;
            int index4 = x + index2;
            int index5 = blockPos.getZ() + enumFacing.getInt() * i;
            AlertNotificationSound loggerEntry = minecraft.theWorld.getLoggerEntry(index4, index5);
            if (loggerEntry.canExecute()) {
                return false;
            }

            if (!minecraft.theWorld.getIBlockState(loggerEntry, index4, blockPos.getY() + enumFacing.getInt3() * i, index5).getBlock().isState()) {
                return false;
            }
        }

        return true;
    }

    public static boolean canExecute7() {
        return activeState1;
    }
}
