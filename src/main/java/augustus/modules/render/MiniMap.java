package augustus.modules.render;

import client.event.BlockPlaceListener;
import client.event.EventBlockPlace;
import client.event.EventWindowAction;
import client.event.EventWorldLoad;
import client.event.MultiplayerChunkCacheListener;
import client.event.PacketListener;
import client.event.RenderListener;
import client.event.TickListener;
import client.event.WindowActionListener;
import client.event.WorldLoadListener;
import client.gui.TexturesGuiIconsPngManager;
import client.module.Category;
import client.module.Module;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.render.WorldRenderer;
import client.setting.NumberSetting;
import client.setting.ShaderOptions;
import client.util.AlertNotificationSound;
import client.util.DefaultVertexFormats;
import client.util.IBlockState;
import client.util.MapChunkBlockData;
import client.util.MovementHandler;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import java.awt.Color;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.HashSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBarrier;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

public class MiniMap extends Module implements TickListener, RenderListener, PacketListener, BlockPlaceListener, WorldLoadListener, WindowActionListener {
    public NumberSetting settingHeight = new NumberSetting(this, "Height", 255.0, 0.0, 255.0, 0);
    public NumberSetting settingPosX = new NumberSetting(this, "PosX", 0.0, 0.0, 100.0, 2);
    public NumberSetting settingPosY = new NumberSetting(this, "PosY", 20.0, 0.0, 100.0, 2);
    public NumberSetting settingSize = new NumberSetting(this, "Size", 200.0, 10.0, 400.0, 0);
    public NumberSetting settingScale = new NumberSetting(this, "Scale", 100.0, 50.0, 400.0, 2);
    public ShaderOptions settingScale2;
    public Long2ObjectMap long2ObjectMap;
    public int tickDelay;
    public int tickDelay1;
    public int[][] intValueArray;
    public ByteBuffer byteBuffer;
    public int tickDelay2;
    public int keyCode2;

    public MiniMap() {
        super("MiniMap", "TomTom", new Color(92, 214, 98), Category.RENDER);
        ShaderOptions shaderOptions = new ShaderOptions(this).getShaderOptions();
        Color black = Color.black;
        this.settingScale2 = shaderOptions.getShaderOptions(black);
        this.long2ObjectMap = new Long2ObjectOpenHashMap();
        this.tickDelay = 5;
        this.tickDelay1 = 5;
        this.intValueArray = (int[][])null;
        this.byteBuffer = null;
        this.tickDelay2 = -1;
        this.keyCode2 = 255;
    }

    @Override
    public void onEnable() {
        this.registerEvent(32);
        this.registerEvent(26);
        this.registerEvent(55);
        this.registerEvent(56);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        if (minecraft.theWorld != null) {
            this.processAction();
        }
    }

    @Override
    public void onWindowAction(EventWindowAction event) {
        this.processShort(event.getIndex2() << 4, event.getIndex() << 4);
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        int size = (int)this.settingSize.getValue();
        this.tickDelay = (int)((double)(minecraft.displayWidth - (size + 2)) * (this.settingPosX.getValue() / 100.0)) + 1;
        this.tickDelay1 = (int)((double)(minecraft.displayHeight - (size + 2)) * (this.settingPosY.getValue() / 100.0)) + 1;
        MovementHandler.processBoolean(false, this.settingScale2.canExecute2());
        MovementHandler.applyRunnableTask(false, renderPass -> this.applyScaledResolution2(scaledResolution, size, renderPass));
        RenderUtils.processAction();
    }

    @Override
    public void onBlockPlace(EventBlockPlace event) {
        if (minecraft.theWorld != null) {
            int index2 = event.getIndex2();
            int index3 = event.getIndex3();
            int index = event.getIndex();
            int index5 = index2 & 15;
            int index6 = index & 15;
            int[][][] intArray = this.getIntArray(index2, index);
            int index7 = intArray[1][index5][index6];
            if (index3 >= index7) {
                MapColor auxiliaryItemArrayEntry = this.getAuxiliaryItemArrayEntry(event.getIBlockState());
                if (auxiliaryItemArrayEntry == null) {
                    AlertNotificationSound loggerEntry2 = minecraft.theWorld.getLoggerEntry2(index2, index);

                    for (int i = Math.min(this.keyCode2, loggerEntry2.getInt6(index2, index)); i >= 0; i--) {
                        IBlockState iBlockState = minecraft.theWorld.getIBlockState(loggerEntry2, index2, i, index);
                        Block block = iBlockState.getBlock();
                        if (block.getMaterial() != Material.coreItemEntry && !(block instanceof BlockBarrier)) {
                            intArray[1][index5][index6] = i;
                            intArray[0][index5][index6] = iBlockState.getBlock().processAction3(iBlockState).indexOffset;
                            return;
                        }
                    }

                    intArray[1][index5][index6] = -1;
                    intArray[0][index5][index6] = -8477711;
                    return;
                }

                intArray[1][index5][index6] = index3;
                intArray[0][index5][index6] = auxiliaryItemArrayEntry.indexOffset;
            }
        }
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        this.updateState();
    }

    @Override
    public void onClientTick() {
        if (minecraft.thePlayer != null) {
            int height = (int)this.settingHeight.getValue();
            if (height != this.keyCode2 && minecraft.thePlayer.ticksExisted % 5 == 0) {
                this.keyCode2 = (int)this.settingHeight.getValue();
                this.updateState();
                this.processAction();
            }

            this.processAction((int)this.settingScale.getValue());
        }
    }

    public void applyIntArray(int index, int index2, int[][][] countMatrix) {
        this.long2ObjectMap.put((long)index & 4294967295L | ((long)index2 & 4294967295L) << 32, countMatrix);
    }

    private void processShort(int index4, int index5) {
        int[][][] intArray = this.getIntArray(index4, index5);
        AlertNotificationSound loggerEntry = minecraft.theWorld.getLoggerEntry(index4, index5);

        for (int i = 0; i < 16; i++) {
            outerLoop:
            for (int i2 = 0; i2 < 16; i2++) {
                for (int i3 = Math.min(this.keyCode2, loggerEntry.getInt6(index4 + i, index5 + i2)); i3 >= 0; i3--) {
                    IBlockState iBlockState = minecraft.theWorld.getIBlockState(loggerEntry, index4 + i, i3, index5 + i2);
                    Block block = iBlockState.getBlock();
                    if (block.getMaterial() != Material.coreItemEntry && !(block instanceof BlockBarrier)) {
                        intArray[1][i][i2] = i3;
                        intArray[0][i][i2] = iBlockState.getBlock().processAction3(iBlockState).indexOffset;
                        continue outerLoop;
                    }
                }

                intArray[1][i][i2] = -1;
                intArray[0][i][i2] = -8477711;
            }
        }
    }

    private MapColor getAuxiliaryItemArrayEntry(IBlockState blockState) {
        Block block = blockState.getBlock();
        if (block.getMaterial() == Material.coreItemEntry) {
            return null;
        } else {
            return !(block instanceof BlockBarrier) ? block.processAction3(blockState) : null;
        }
    }

    private void processAction() {
        if (minecraft.theWorld.getCommonListener() instanceof MultiplayerChunkCacheListener) {
            MultiplayerChunkCacheListener multiplayerChunkCacheListener = (MultiplayerChunkCacheListener)minecraft.theWorld.getCommonListener();
            HashSet hashSet = new HashSet();
            MapChunkBlockData[] extendedLongEntryArray = multiplayerChunkCacheListener.internalFloatEntry.extendedLongEntryArray;
            int length = extendedLongEntryArray.length;
            int index = 0;

            while (index < length) {
                MapChunkBlockData extendedLongEntry = extendedLongEntryArray[index];

                while (extendedLongEntry != null) {
                    if (hashSet.add(extendedLongEntry)) {
                        Object entry = extendedLongEntry.getEntry();
                        AlertNotificationSound loggerEntry = (AlertNotificationSound)entry;
                        this.processShort(loggerEntry.indexOffset1 << 4, loggerEntry.indexOffset << 4);
                        extendedLongEntry = extendedLongEntry.extendedLongEntry;
                    } else {
                        extendedLongEntry = extendedLongEntry.extendedLongEntry;
                    }
                }

                index++;
            }
        }
    }

    public int[][][] getIntArray(int index, int index2) {
        int index3 = index;
        int index4 = index2;
        synchronized (this.long2ObjectMap) {
            index3 >>= 4;
            index4 >>= 4;
            int[][][] countMatrix = (int[][][])this.long2ObjectMap.get((long)index3 & 4294967295L | ((long)index4 & 4294967295L) << 32);
            if (countMatrix == null) {
                countMatrix = new int[2][16][16];
                this.applyIntArray(index3, index4, countMatrix);
            }

            return countMatrix;
        }
    }

    private void processAction(int index) {
        if (this.tickDelay2 == -1) {
            this.tickDelay2 = this.getInt2(index, index);
        }

        if (this.intValueArray == null || this.byteBuffer == null || this.intValueArray.length != index) {
            this.updateInt2(index);
            GlStateManager.deleteTexture(this.tickDelay2);
            this.tickDelay2 = this.getInt2(index, index);
        }

        this.applyIntArray2(index, this.intValueArray);
        this.applyByteBuffer(this.byteBuffer, this.tickDelay2, this.intValueArray, index, index);
    }

    private void updateState() {
        this.intValueArray = (int[][])null;
        this.byteBuffer = null;
        synchronized (this.long2ObjectMap) {
            this.long2ObjectMap.clear();
        }
    }

    private void applyIntArray2(int index4, int[][] countMatrix) {
        int index5 = (int)minecraft.thePlayer.posX - index4 / 2;
        int index6 = (int)minecraft.thePlayer.posZ - index4 / 2;
        int index7 = index4 / 16 + 2;
        int index8 = -(index5 & 15);
        int index9 = -(index6 & 15);

        for (int i = -1; i < index7; i++) {
            for (int i2 = -1; i2 < index7; i2++) {
                int index = index5 + i * 16;
                int index2 = index6 + i2 * 16;
                int[][] countMatrix2 = this.getIntArray(index, index2)[0];

                for (int i3 = 0; i3 < 16; i3++) {
                    int index3 = 0;

                    while (index3 < 16) {
                        int[] countArray = countMatrix2[i3];
                        int index10 = countArray[index3];
                        int index11 = index + i3 - index5 + index8;
                        int index12 = index2 + index3 - index6 + index9;
                        if (index11 < index4) {
                            if (index11 < 0) {
                                index3++;
                            } else {
                                if (index12 < index4 && index12 >= 0) {
                                    countMatrix[index11][index12] = index10;
                                }

                                index3++;
                            }
                        } else {
                            index3++;
                        }
                    }
                }
            }
        }
    }

    private void applyByteBuffer(ByteBuffer buffer, int index2, int[][] countMatrix, int index3, int index4) {
        int index5 = 0;

        while (index5 < index4) {
            for (int i = 0; i < index3; i++) {
                int[] countArray = countMatrix[i];
                int index = countArray[index5];
                int index6 = index >> 16 & 0xFF;
                int index7 = index >> 8 & 0xFF;
                int index8 = index & 0xFF;
                int index9 = index != 0 ? 255 : 0;
                buffer.put((byte)index6);
                buffer.put((byte)index7);
                buffer.put((byte)index8);
                buffer.put((byte)index9);
                countMatrix[i][index5] = 0;
            }

            index5++;
        }

        ((Buffer)buffer).flip();
        GlStateManager.setActiveTexture(index2);
        GL11.glTexSubImage2D(3553, 0, 0, 0, index3, index4, 6408, 5121, buffer);
        ((Buffer)buffer).clear();
    }

    private void applyScaledResolution2(ScaledResolution scaledResolution, int index, RenderPass renderPass) {
        GlStateManager.applyState();
        RenderUtils.processAction(255, 255, 255, 255);
        GlStateManager.pushMatrix();
        GlStateManager.translate3f3(1.0F / (float)scaledResolution.getScaleFactor(), 1.0F / (float)scaledResolution.getScaleFactor(), 1.0F);
        int index2 = this.tickDelay - 1;
        int index3 = this.tickDelay1 - 1;
        int index4 = this.tickDelay + index + 1;
        int index5 = this.tickDelay1 + index + 1;
        TexturesGuiIconsPngManager.processAction(index2, index3, index4, index5, this.settingScale2.getIndex(renderPass, -267119596));
        int index6 = this.tickDelay + index;
        int index7 = this.tickDelay1 + index;
        TexturesGuiIconsPngManager.processAction(this.tickDelay, this.tickDelay1, index6, index7, this.settingScale2.getIndex(renderPass, -15461356));
        RenderUtils.processAction(255, 255, 255, 255);
        this.updateInt(index);
        TexturesGuiIconsPngManager.processAction(
        this.tickDelay + index / 2 - 1, this.tickDelay1 + index / 2 - 1, this.tickDelay + index / 2 + 1, this.tickDelay1 + index / 2 + 1, Color.RED.getRGB()
        );
        GlStateManager.popMatrix();
        GlStateManager.applyState();
        RenderUtils.processAction(255, 255, 255, 255);
    }

    private int getInt2(int index, int index2) {
        int stateInt6 = GlStateManager.getStateInt6();
        GlStateManager.setActiveTexture(stateInt6);
        GL11.glTexImage2D(3553, 0, 6408, index, index2, 0, 6408, 5121, (ByteBuffer)null);
        GL11.glTexParameteri(3553, 10241, 9728);
        GL11.glTexParameteri(3553, 10240, 9728);
        return stateInt6;
    }

    private void updateInt(int index) {
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(this.tickDelay2);
        Tessellator tessellator = Tessellator.getTessellator();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
        worldRenderer.getWorldRenderer((double)this.tickDelay, (double)(this.tickDelay1 + index), 0.0).getWorldRenderer6(0.0, 1.0).updateState();
        worldRenderer.getWorldRenderer((double)(this.tickDelay + index), (double)(this.tickDelay1 + index), 0.0).getWorldRenderer6(1.0, 1.0).updateState();
        worldRenderer.getWorldRenderer((double)(this.tickDelay + index), (double)this.tickDelay1, 0.0).getWorldRenderer6(1.0, 0.0).updateState();
        worldRenderer.getWorldRenderer((double)this.tickDelay, (double)this.tickDelay1, 0.0).getWorldRenderer6(0.0, 0.0).updateState();
        tessellator.updateState();
    }

    private void updateInt2(int index) {
        this.intValueArray = new int[index][index];
        this.byteBuffer = ByteBuffer.allocateDirect(index * index * 4);
    }
}
