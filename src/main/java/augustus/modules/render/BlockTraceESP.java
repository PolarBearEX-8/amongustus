package augustus.modules.render;

import augustus.custom.RuntimeBridge;
import cc.baritone.pathing.PathPoint;
import cc.baritone.pathing.TracedBlockPath;
import client.event.BlockActionListener;
import client.event.EventBlockAction;
import client.event.EventWorldLoad;
import client.event.RenderListener;
import client.event.TickListener;
import client.event.WorldLoadListener;
import client.module.Category;
import client.module.Module;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.ColorSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.ShaderOptions;
import client.util.BooleanSupplier;
import client.util.IBlockState;
import client.util.MovementHandler;
import client.util.RandomUtil;
import client.util.RunnableTask;
import com.google.common.collect.Sets;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class BlockTraceESP extends Module implements RenderListener, TickListener, BlockActionListener, WorldLoadListener {
    public ModeSetting settingBlock;
    public NumberSetting settingCustomID;
    public ColorSetting settingBlockColor;
    public NumberSetting settingBlockLineWidth;
    public NumberSetting settingBlockDistance;
    public ShaderOptions settingBlock2;
    public ColorSetting settingPathColor;
    public ShaderOptions settingPath;
    public NumberSetting settingPathSearchRange;
    public TracedBlockPath tracedBlockPath;
    public ArrayList itemList3;
    public static Set entrySet = Sets.newConcurrentHashSet();
    public Block block;

    public BlockTraceESP() {
        super("BlockTraceESP", "BlockSpurESP", new Color(50, 168, 82), Category.RENDER);
        String[] textArray = new String[3];
        textArray[0] = "Bed";
        textArray[1] = "Cake";
        textArray[2] = "Custom";
        this.settingBlock = new ModeSetting(this, "Block", "Bed", textArray);
        this.settingCustomID = new NumberSetting(this, "CustomID", 26.0, 0.0, 400.0, 0).getNumberSetting(() -> this.isCustomIDVisible());
        Color color = new Color(21, 121, 230, 65);
        this.settingBlockColor = new ColorSetting(this, "BlockColor", color);
        this.settingBlockLineWidth = new NumberSetting(this, "BlockLineWidth", 6.0, 0.0, 15.0, 0);
        this.settingBlockDistance = new NumberSetting(this, "BlockDistance", 50.0, 1.0, 300.0, 0);
        ShaderOptions shaderOptions5 = new ShaderOptions(this, "Block").getShaderOptions5();
        Color black = Color.black;
        this.settingBlock2 = shaderOptions5.getShaderOptions(black);
        Color color2 = new Color(21, 121, 230, 65);
        this.settingPathColor = new ColorSetting(this, "PathColor", color2);
        ShaderOptions shaderOptions52 = new ShaderOptions(this, "Path").getShaderOptions5();
        Color black2 = Color.black;
        this.settingPath = shaderOptions52.getShaderOptions(black2);
        this.settingPathSearchRange = new NumberSetting(this, "PathSearchRange", 10.0, 1.0, 10.0, 0);
        this.tracedBlockPath = (TracedBlockPath)null;
        this.itemList3 = new ArrayList();
        this.block = (Block)null;
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
        this.itemList3.clear();
        entrySet.clear();
    }

    @Override
    public void onPreDisable() {
        entrySet.clear();
        minecraft.renderGlobal.updateState();
    }

    @Override
    public void onBlockAction(EventBlockAction event) {
        if (event.getIBlockState() != null) {
            if (event.getIBlockState().getBlock().equals(Block.processAction3(this.getInt2()))) {
                BlockPos blockPos = event.getBlockPos();
                if (blockPos != null) {
                    entrySet.add(blockPos);
                }
            }
        }
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        entrySet.clear();
    }

    @Override
    public void onRender3D(float partialTicks) {
        if (minecraft.thePlayer != null) {
            if (minecraft.theWorld != null) {
                RenderUtils.updateState();
                GL11.glAlphaFunc(516, 0.0F);
                boolean enabled = this.settingBlock2.canExecute();
                boolean enabled2 = this.settingBlock2.canExecute2();
                MovementHandler.processBoolean(enabled, enabled2);
                MovementHandler.applyRunnableTask(false, renderPass -> this.applyRenderPass(renderPass));
                RenderUtils.prepareScissor();
                if (this.tracedBlockPath == null) {
                    return;
                }

                RenderUtils.updateState();
                boolean enabled3 = this.settingPath.canExecute();
                boolean enabled4 = this.settingPath.canExecute2();
                MovementHandler.processBoolean(enabled3, enabled4);
                MovementHandler.applyRunnableTask(false, renderPass2 -> this.applyRenderPass2(renderPass2));
                GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
                RenderUtils.prepareScissor();
            }
        }
    }

    @Override
    public void onPreTick() {
        this.block = Block.processAction3(this.getInt2());
        entrySet.removeIf(object -> this.checkBlockPos((BlockPos)object));
        this.getBlockPos();
    }

    private boolean checkBlockPos(BlockPos blockPos) {
        if (blockPos != null && this.block != null) {
            IBlockState iBlockState = minecraft.theWorld.getIBlockState(blockPos.getX(), blockPos.getY(), blockPos.getZ());
            return iBlockState == null ? false : !iBlockState.getBlock().equals(this.block);
        } else {
            return true;
        }
    }

    private BlockPos getBlockPos() {
        int int12 = MathHelper.getInt12((int)this.settingPathSearchRange.getValue(), 0, 10);
        BlockPos blockPos = null;
        double offset = 100000.0;
        int index3 = -int12;
        if (index3 > int12) {
            this.tracedBlockPath = null;
            return null;
        } else {
            boolean completed2 = false;
            BlockPos blockPos2 = null;
            int index4 = 0;
            int index5 = 0;
            double offset2 = 0.0;
            boolean completed = false;
            index4 = -int12;
            if (index4 <= int12) {
                for (index5 = -int12; index5 <= int12; index5++) {
                    blockPos2 = new BlockPos(
                    minecraft.thePlayer.posX + (double)index3, minecraft.thePlayer.posY + (double)index4, minecraft.thePlayer.posZ + (double)index5
                    );
                    if (minecraft.theWorld.getBlockState(blockPos2).getBlock().equals(Block.processAction3(this.getInt2()))) {
                        offset2 = Math.sqrt(minecraft.thePlayer.getCoordinate(blockPos2));
                        { completed = true; break; }
                    }
                }
                if (!completed) {
                    index4++;
                }
            } else {
                index3++;
                while (true) {
                    if (index3 > int12) {
                        this.tracedBlockPath = null;
                        return null;
                    }
                    index4 = -int12;
                    if (index4 <= int12) {
                        for (index5 = -int12; index5 <= int12; index5++) {
                            blockPos2 = new BlockPos(
                            minecraft.thePlayer.posX + (double)index3,
                            minecraft.thePlayer.posY + (double)index4,
                            minecraft.thePlayer.posZ + (double)index5
                            );
                            if (minecraft.theWorld.getBlockState(blockPos2).getBlock().equals(Block.processAction3(this.getInt2()))) {
                                offset2 = Math.sqrt(minecraft.thePlayer.getCoordinate(blockPos2));
                                { completed = true; break; }
                            }
                        }
                        if (completed) break;
                        if (!completed) {
                            index4++;
                            break;
                        }
                    }
                    if (!completed) {
                        index3++;
                    }
                }
            }
            if (!completed) {
                while (true) {
                    while (index4 <= int12) {
                        for (int i = -int12; i <= int12; i++) {
                            BlockPos blockPos3 = new BlockPos(
                            minecraft.thePlayer.posX + (double)index3, minecraft.thePlayer.posY + (double)index4, minecraft.thePlayer.posZ + (double)i
                            );
                            if (minecraft.theWorld.getBlockState(blockPos3).getBlock().equals(Block.processAction3(this.getInt2()))) {
                                double offset3 = Math.sqrt(minecraft.thePlayer.getCoordinate(blockPos3));
                                if (offset3 < offset) {
                                    blockPos = blockPos3;
                                    offset = offset3;
                                }
                            }
                        }

                        index4++;
                    }
                    if (++index3 > int12) {
                        { completed2 = true; break; }
                    }
                    if (!completed2) {
                        index4 = -int12;
                    }
                }
            }
            if (!completed2) {
                outerLoop:
                while (true) {
                    if (offset2 < offset) {
                        blockPos = blockPos2;
                        offset = offset2;
                    }

                    do {
                        index5++;

                        while (index5 > int12) {
                            index4++;

                            while (index4 > int12) {
                                if (++index3 > int12) {
                                    break outerLoop;
                                }

                                index4 = -int12;
                            }

                            index5 = -int12;
                        }

                        blockPos2 = new BlockPos(
                        minecraft.thePlayer.posX + (double)index3, minecraft.thePlayer.posY + (double)index4, minecraft.thePlayer.posZ + (double)index5
                        );
                    } while (!minecraft.theWorld.getBlockState(blockPos2).getBlock().equals(Block.processAction3(this.getInt2())));

                    offset2 = Math.sqrt(minecraft.thePlayer.getCoordinate(blockPos2));
                }
            }


            if (blockPos == null) {
                this.tracedBlockPath = null;
                return null;
            } else {
                TracedBlockPath tracedBlockPath2 = RuntimeBridge.traceBlockPath(
                new PathPoint(blockPos), new PathPoint(new BlockPos(minecraft.thePlayer.getVec35(1.0F))), blockPos.getY()
                );
                if (tracedBlockPath2 != null) {
                    this.tracedBlockPath = tracedBlockPath2;
                }

                if (this.tracedBlockPath == null) {
                    return blockPos;
                } else {
                    TracedBlockPath tracedBlockPath3 = this.tracedBlockPath;
                    this.itemList3.clear();

                    for (int i2 = 0; tracedBlockPath3 != null && tracedBlockPath3.getTracedBlockPath() != null; i2++) {
                        BlockPos blockPos4 = tracedBlockPath3.getPathPoint().getBlockPos();
                        if (minecraft.theWorld.getBlockState(blockPos4).getBlock().isState18() && !this.itemList3.contains(blockPos4)) {
                            this.itemList3.add(tracedBlockPath3.getPathPoint().getBlockPos());
                        }

                        tracedBlockPath3 = tracedBlockPath3.getTracedBlockPath();
                        if (i2 > 300) {
                            break;
                        }
                    }

                    return this.itemList3.isEmpty() ? blockPos : (BlockPos)this.itemList3.get(this.itemList3.size() - 1);
                }
            }
        }
    }

    private boolean isCustomIDVisible() {
        return this.settingBlock.getSelected().equals("Custom");
    }

    private int getInt2() {
        String selected = this.settingBlock.getSelected();
switch (selected) {
            case "Bed":
                return 26;
            case "Cake":
                return 92;
            default:
                return (int)this.settingCustomID.getValue();
        }
    }

    private void applyRenderPass(RenderPass renderPass) {
        int index2 = 0;

        for (BlockPos blockPos : (Iterable<BlockPos>) entrySet) {
            if (blockPos != null) {
                float value = (float)(this.settingBlockLineWidth.getValue() / 2.0);
                int index3 = index2++;
                int int12 = this.settingBlockColor.getInt12(index3);
                if (minecraft.thePlayer.getDouble4((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ()) > 1.0) {
                    double offset = minecraft.thePlayer.getDouble4((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ()) / 20.0;
                    double offset2 = 1.0 - offset;
                    if (offset2 < 0.3) {
                        offset2 = 0.3;
                    }

                    value = (float)((double)value * offset2);
                }

                int index = RandomUtil.getIndex(renderPass, int12);
                RenderUtils.applyBlockPos(blockPos, this.settingBlock2.getIndex(renderPass, index, index2), 1.0F, value);
            }
        }
    }

    private void applyRenderPass2(RenderPass renderPass) {
        for (BlockPos blockPos : (Iterable<BlockPos>) this.itemList3) {
            if (blockPos != null) {
                int int12 = this.settingPathColor.getInt12(0);
                double x = (double)blockPos.getX();
                double offset = x - minecraft.getRenderManager().getRenderPosX();
                double y = (double)blockPos.getY();
                double offset2 = y - minecraft.getRenderManager().getRenderPosY();
                double z = (double)blockPos.getZ();
                double offset3 = z - minecraft.getRenderManager().getRenderPosZ();
                if (renderPass == RenderPass.SHADOW) {
                    RenderUtils.processAction(this.settingPath.getIndex(renderPass, int12), 1.0F);
                } else {
                    RenderUtils.setColor(int12);
                }

                RenderUtils.processAction4(offset, offset2, offset3, offset + 1.0, offset2 + 1.0, offset3 + 1.0);
            }
        }
    }
}
