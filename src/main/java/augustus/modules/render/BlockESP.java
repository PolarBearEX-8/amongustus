package augustus.modules.render;

import augustus.modes.render.BlockEspEntry;
import client.event.BlockActionListener;
import client.event.EventBlockAction;
import client.event.EventWorldLoad;
import client.event.RenderListener;
import client.event.TickListener;
import client.event.WorldLoadListener;
import client.module.Category;
import client.module.Module;
import client.render.BlockEspGeometry;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.render.ScoreboardRow;
import client.setting.ColorSetting;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.ShaderOptions;
import client.util.BooleanSupplier;
import client.util.ClientManager;
import client.util.ConsumerTask;
import client.util.IBlockState;
import client.util.MovementHandler;
import client.util.RandomUtil;
import client.util.RunnableTask;
import com.google.common.collect.Maps;
import java.awt.Color;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;

public class BlockESP extends Module implements RenderListener, BlockActionListener, TickListener, WorldLoadListener {
    public ModeSetting settingMode;
    public ColorSetting settingColor;
    public NumberSetting settingLineWidth;
    public NumberSetting settingID;
    public ShaderOptions settingID2;
    public static HashMap entryMap = new HashMap();
    public static Map entryMap2 = Maps.newConcurrentMap();
    public long timestampMillis;
    public ScoreboardRow linkedListEntry;
    public boolean expanded2;

    public BlockESP() {
        super("BlockESP", "BlockDurchWandSehen", new Color(171, 9, 41), Category.RENDER);
        String[] textArray = new String[2];
        textArray[0] = "Old";
        textArray[1] = "New";
        this.settingMode = new ModeSetting(this, "Mode", "Old", textArray).getModeSetting(() -> this.onModeAction());
        Color color = new Color(21, 121, 230, 65);
        this.settingColor = new ColorSetting(this, "Color", color);
        this.settingLineWidth = new NumberSetting(this, "LineWidth", 6.0, 0.0, 15.0, 0).getNumberSetting(() -> this.isLineWidthVisible());
        this.settingID = new NumberSetting(this, "ID", 26.0, 1.0, 400.0, 0);
        ShaderOptions shaderOptions5 = new ShaderOptions(this).getShaderOptions5();
        Color black = Color.black;
        this.settingID2 = shaderOptions5.getShaderOptions(black);
        this.timestampMillis = 0L;
        ScoreboardRow linkedListEntry2 = new ScoreboardRow(200);
        this.linkedListEntry = linkedListEntry2;
        this.expanded2 = false;
        Iterator iterator = Block.iterableExtensionBridge.getSet().iterator();

        while (iterator.hasNext()) {
            String text = ((ResourceLocation)iterator.next()).getText1();
            Block block = Block.getBlockFromName(text);
            HashMap map = entryMap;
            boolean enabled;
            if (block != Blocks.bed) {
                enabled = false;
            } else {
                enabled = true;
            }

            BlockEspEntry blockEspEntry = new BlockEspEntry(this, text, enabled, null);
            map.put(block, blockEspEntry);
        }

        Collection collection = entryMap.values();
        BlockEspEntry[] blockEspEntryArray = new BlockEspEntry[0];
        BlockEspEntry[] blockEspEntryArray2 = ((BlockEspEntry[])(collection.toArray(blockEspEntryArray)));
        Arrays.sort(blockEspEntryArray2, Comparator.comparing(blockEspEntry2 -> ((BlockEspEntry)blockEspEntry2).getText()));
        new MultiSelectSetting(this, "Blocks", blockEspEntryArray2);
    }

    @Override
    public void onEnable() {
        this.registerEvent(32);
        this.registerEvent(30);
        this.registerEvent(40);
        this.registerEvent(17);
    }

    @Override
    public void onDisable() {
        this.updateState();
    }

    @Override
    public void onPreDisable() {
        this.updateState();
        minecraft.renderGlobal.updateState();
    }

    @Override
    public void onBlockAction(EventBlockAction event) {
        if (entryMap2.size() < 2000) {
            if (event.getIBlockState() == null) {
                return;
            }

            if (event.getIBlockState().getBlock() == Blocks.air) {
                return;
            }

            if (((BlockEspEntry)entryMap.get(event.getIBlockState().getBlock())).state) {
                BlockPos blockPos = event.getBlockPos();
                if (blockPos == null) {
                    return;
                }

                if (!entryMap2.containsKey(blockPos)) {
                    Block block = event.getIBlockState().getBlock();
                    BlockEspGeometry extendedFloatEntry = new BlockEspGeometry(
                    (double)blockPos.getX() + block.getCoordOffset(),
                    (double)blockPos.getY() + block.getCoordOffset11(),
                    (double)blockPos.getZ() + block.getCoordOffset2(),
                    block.getCoordOffset9(),
                    block.getCoordOffset6(),
                    block.getCoordOffset15()
                    );
                    entryMap2.put(blockPos, extendedFloatEntry);
                    if (!this.settingMode.getSelected().equals("New")) {
                        return;
                    }

                    ClientManager.applyRunnable2(() -> this.updateExtendedFloatEntry(extendedFloatEntry));
                }
            }
        }
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        this.updateState();
    }

    @Override
    public void onRender3D(float partialTicks) {
        if (minecraft.thePlayer != null) {
            if (minecraft.theWorld != null) {
                RenderUtils.updateState();
                boolean enabled = this.settingMode.getSelected().equals("Old");
                long time2 = System.currentTimeMillis();
                long time3;
                int index = ((time3 = time2 - this.timestampMillis - 1L) == 0L ? 0 : (time3 < 0L ? -1 : 1)) > 0 ? 1 : 0;
                if (index != 0) {
                    this.timestampMillis = time2;
                }

                boolean enabled2 = this.settingID2.canExecute();
                MovementHandler.processBoolean(enabled2, this.settingID2.canExecute2());
                MovementHandler.applyRunnableTask(true, renderPass -> this.applyRenderPass(enabled, (index & 1) != 0, renderPass));
                RenderUtils.prepareScissor();
            }
        }
    }

    @Override
    public void onRunTick() {
        if (this.expanded2) {
            minecraft.renderGlobal.updateState();
            this.expanded2 = false;
        }

        for (BlockPos blockPos : ((Iterable<BlockPos>) entryMap2.keySet())) {
            if (blockPos == null) {
                entryMap2.remove(blockPos);
            } else {
                boolean enabled = false;
                boolean completed = false;
                IBlockState iBlockState = minecraft.theWorld.getIBlockState(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                if (iBlockState != null) {
                    Object object = entryMap.get(iBlockState.getBlock());
                    if (!((BlockEspEntry)object).state) {
                        enabled = true;
                        completed = true;
                    }
                }
                if (!completed) {
                    enabled = false;
                }


                if (enabled) {
                    if (this.settingMode.getSelected().equals("New")) {
                        ScoreboardRow linkedListEntry2 = this.linkedListEntry;
                        BlockEspGeometry extendedFloatEntry = (BlockEspGeometry)entryMap2.get(blockPos);
                        linkedListEntry2.updateExtendedFloatEntry(extendedFloatEntry);
                    }

                    entryMap2.remove(blockPos);
                }
            }
        }
    }

    public static boolean checkBlockESP(BlockESP blockESP, boolean enabled) {
        return blockESP.expanded2 = enabled;
    }

    private void updateState() {
        entryMap2.clear();
        this.linkedListEntry.processAction();
    }

    private void updateExtendedFloatEntry(BlockEspGeometry extendedFloatEntry) {
        this.linkedListEntry.updateExtendedFloatEntry(17428, (byte)-94, extendedFloatEntry, 218304);
    }

    private void onModeAction() {
        this.updateState();
        minecraft.renderGlobal.updateState();
    }

    public static void applyBlockESP(BlockESP blockESP) {
        blockESP.updateState();
    }

    private boolean isLineWidthVisible() {
        return this.settingMode.getSelected().equals("Old");
    }

    private void applyRenderPass(boolean enabled, boolean enabled2, RenderPass renderPass) {
        GlStateManager.pushMatrix();
        GlStateManager.color4f2(0.0F, 1.0F, 0.0F, 1.0F);
        int index2 = 0;
        if (enabled) {
            for (BlockPos blockPos : ((Iterable<BlockPos>) entryMap2.keySet())) {
                if (blockPos != null) {
                    float value = (float)(this.settingLineWidth.getValue() / 2.0);
                    int int12 = this.settingColor.getInt12(index2);
                    double double4 = minecraft.thePlayer.getDouble4((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ());
                    if (double4 > 1.0) {
                        double offset = double4 / 20.0;
                        double offset2 = 1.0 - offset;
                        if (offset2 < 0.3) {
                            offset2 = 0.3;
                        }

                        value *= (float)offset2;
                    }

                    int index = RandomUtil.getIndex(renderPass, int12);
                    RenderUtils.applyBlockPos(blockPos, this.settingID2.getIndex(renderPass, index, index2), 1.0F, value);
                    index2++;
                }
            }
        } else {
            double offset3 = -minecraft.getRenderManager().getRenderPosX();
            double offset4 = -minecraft.getRenderManager().getRenderPosY();
            GlStateManager.translate3d2(offset3, offset4, -minecraft.getRenderManager().getRenderPosZ());

            for (BlockEspGeometry extendedFloatEntry : ((Iterable<BlockEspGeometry>) entryMap2.values())) {
                if (extendedFloatEntry != null) {
                    if (enabled2) {
                        int int122 = this.settingColor.getInt12(index2);
                        extendedFloatEntry.getExtendedFloatEntry(int122).updateState();
                    }

                    index2++;
                }
            }

            this.linkedListEntry.updateState();
        }

        GlStateManager.popMatrix();
    }
}
