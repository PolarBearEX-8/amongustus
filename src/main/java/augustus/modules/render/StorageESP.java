package augustus.modules.render;

import augustus.custom.RuntimeBridge;
import client.event.EventWorldLoad;
import client.event.RenderListener;
import client.event.TickListener;
import client.event.WorldLoadListener;
import client.module.Category;
import client.module.Module;
import client.render.EntityEspRenderer;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.ColorSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.SettingGroup;
import client.setting.ShaderOptions;
import client.setting.StorageESPRenderMode;
import client.util.BooleanSupplier;
import client.util.ConsumerTask;
import client.util.MovementHandler;
import client.util.RandomUtil;
import client.util.RunnableTask;
import it.unimi.dsi.fastutil.ints.Int2ObjectAVLTreeMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderchest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class StorageESP extends Module implements RenderListener, TickListener, WorldLoadListener {
    public MultiSelectSetting settingModes;
    public SettingGroup settingBox;
    public ColorSetting settingBoxChestColor;
    public ColorSetting settingBoxEnderChestColor;
    public NumberSetting settingBoxLineWidth;
    public NumberSetting settingBoxExpand;
    public NumberSetting settingBoxPriority;
    public ShaderOptions settingBoxShader;
    public SettingGroup settingBlockBox;
    public ColorSetting settingBlockBoxChestColor;
    public ColorSetting settingBlockBoxEnderChestColor;
    public NumberSetting settingBlockBoxLineWidth;
    public NumberSetting settingBlockBoxPriority;
    public ShaderOptions settingBlockBoxShader;
    public SettingGroup setting3DCorner;
    public ColorSetting settingCorner3ChestColor;
    public ColorSetting settingCorner3EnderChestColor;
    public NumberSetting settingCorner3DPriority;
    public ShaderOptions settingCorner3DShader;
    public SettingGroup settingReal2D;
    public ColorSetting settingReal2DColorChest1;
    public ColorSetting settingReal2DColorChest2;
    public ColorSetting settingReal2DColorEnderChest1;
    public ColorSetting settingReal2DColorEnderChest2;
    public NumberSetting settingReal2DPriority;
    public ShaderOptions settingReal2DShader;
    public SettingGroup settingCorner;
    public ColorSetting settingCornerChestColor;
    public ColorSetting settingCornerEnderChestColor;
    public NumberSetting settingCornerLineWidth;
    public NumberSetting settingCornerHorizontalPercentage;
    public NumberSetting settingCornerVerticalPercentage;
    public NumberSetting settingCornerPriority;
    public ShaderOptions settingCornerShader;
    public NumberSetting settingMaxDistance;
    public HashMap entryMap;
    public ArrayList itemList3;
    public EntityEspRenderer entityEspRenderer;
    public Int2ObjectAVLTreeMap int2ObjectAVLTreeMap;
    public Int2ObjectAVLTreeMap int2ObjectAVLTreeMap2;

    public StorageESP() {
        super("StorageESP", "SpeicherRöntgenBlick", new Color(100, 166, 148, 255), Category.RENDER);
        StorageESPRenderMode[] storageESPRenderModeArray = StorageESPRenderMode.values();
        this.settingModes = new MultiSelectSetting(this, "Modes", storageESPRenderModeArray);
        this.settingBox = new SettingGroup(this, "Box", true).getSettingGroup(() -> StorageESPRenderMode.BOX.isSelected());
        Color color = new Color(21, 121, 230, 65);
        ColorSetting colorSetting = new ColorSetting(this, "BoxChestColor", color, this.settingBox);
        Setting setting = colorSetting.getSetting("ChestColor");
        this.settingBoxChestColor = (ColorSetting)setting;
        Color color2 = new Color(21, 121, 230, 65);
        ColorSetting colorSetting2 = new ColorSetting(this, "BoxEnderChestColor", color2, this.settingBox);
        this.settingBoxEnderChestColor = (ColorSetting)colorSetting2.getSetting("EnderChestColor");
        this.settingBoxLineWidth = new NumberSetting(this, "BoxLineWidth", 2.0, 0.0, 12.0, 0, this.settingBox).getNumberSetting("LineWidth");
        this.settingBoxExpand = new NumberSetting(this, "BoxExpand", 0.0, -1.0, 1.0, 4, this.settingBox).getNumberSetting("Expand");
        this.settingBoxPriority = (NumberSetting)new NumberSetting(this, "BoxPriority", 0.0, 0.0, 2.0, 0, this.settingBox).getNumberSetting("3DPriority").getSetting(() -> this.onBoxPriorityAction());
        ShaderOptions shaderOptions = new ShaderOptions(this, "BoxShader", this.settingBox);
        Color black = Color.black;
        this.settingBoxShader = shaderOptions.getShaderOptions(black).getShaderOptions5();
        this.settingBlockBox = new SettingGroup(this, "BlockBox", true).getSettingGroup(() -> StorageESPRenderMode.BLOCK_BOX.isSelected());
        Color color3 = new Color(21, 121, 230, 65);
        this.settingBlockBoxChestColor = (ColorSetting)new ColorSetting(this, "BlockBoxChestColor", color3, this.settingBlockBox).getSetting("ChestColor");
        Color color4 = new Color(21, 121, 230, 65);
        this.settingBlockBoxEnderChestColor = (ColorSetting)new ColorSetting(this, "BlockBoxEnderChestColor", color4, this.settingBlockBox).getSetting("EnderChestColor");
        this.settingBlockBoxLineWidth = new NumberSetting(this, "BlockBoxLineWidth", 2.0, 0.0, 12.0, 0, this.settingBlockBox).getNumberSetting("LineWidth");
        this.settingBlockBoxPriority = (NumberSetting)new NumberSetting(this, "BlockBoxPriority", 1.0, 0.0, 2.0, 0, this.settingBlockBox).getNumberSetting("3DPriority").getSetting(() -> this.onBoxPriorityAction());
        ShaderOptions shaderOptions2 = new ShaderOptions(this, "BlockBoxShader", this.settingBlockBox);
        Color black2 = Color.black;
        this.settingBlockBoxShader = shaderOptions2.getShaderOptions(black2).getShaderOptions5();
        this.setting3DCorner = new SettingGroup(this, "3DCorner", true).getSettingGroup(() -> StorageESPRenderMode.CORNER_3D.isSelected());
        Color color5 = new Color(21, 121, 230, 65);
        this.settingCorner3ChestColor = (ColorSetting)new ColorSetting(this, "Corner3ChestColor", color5, this.setting3DCorner).getSetting("ChestColor");
        Color color6 = new Color(21, 121, 230, 65);
        this.settingCorner3EnderChestColor = (ColorSetting)new ColorSetting(this, "Corner3EnderChestColor", color6, this.setting3DCorner).getSetting("EnderChestColor");
        this.settingCorner3DPriority = (NumberSetting)new NumberSetting(this, "Corner3DPriority", 2.0, 0.0, 2.0, 0, this.setting3DCorner).getNumberSetting("3DPriority").getSetting(() -> this.onBoxPriorityAction());
        ShaderOptions shaderOptions3 = new ShaderOptions(this, "Corner3DShader", this.setting3DCorner);
        Color black3 = Color.black;
        this.settingCorner3DShader = shaderOptions3.getShaderOptions(black3).getShaderOptions5();
        this.settingReal2D = new SettingGroup(this, "Real2D", true).getSettingGroup(() -> StorageESPRenderMode.REAL_2D.isSelected());
        Color color7 = new Color(21, 121, 230, 65);
        this.settingReal2DColorChest1 = (ColorSetting)new ColorSetting(this, "Real2DColorChest1", color7, this.settingReal2D).getSetting("ChestColor1");
        Color color8 = new Color(21, 121, 230);
        this.settingReal2DColorChest2 = (ColorSetting)new ColorSetting(this, "Real2DColorChest2", color8, this.settingReal2D).getSetting("ChestColor2");
        Color color9 = new Color(66, 245, 164, 65);
        this.settingReal2DColorEnderChest1 = (ColorSetting)new ColorSetting(this, "Real2DColorEnderChest1", color9, this.settingReal2D).getSetting("EnderChestColor1");
        Color color10 = new Color(66, 245, 164);
        this.settingReal2DColorEnderChest2 = (ColorSetting)new ColorSetting(this, "Real2DColorEnderChest2", color10, this.settingReal2D).getSetting("EnderChestColor2");
        this.settingReal2DPriority = (NumberSetting)new NumberSetting(this, "Real2DPriority", 0.0, 0.0, 1.0, 0, this.settingReal2D).getNumberSetting("2DPriority").getSetting(() -> this.onReal2DPriorityAction());
        ShaderOptions shaderOptions4 = new ShaderOptions(this, "Real2DShader", this.settingReal2D);
        Color black4 = Color.black;
        this.settingReal2DShader = shaderOptions4.getShaderOptions(black4).getShaderOptions5();
        this.settingCorner = new SettingGroup(this, "Corner", true).getSettingGroup(() -> StorageESPRenderMode.CORNER.isSelected());
        Color color11 = new Color(21, 121, 230);
        this.settingCornerChestColor = (ColorSetting)new ColorSetting(this, "CornerChestColor", color11, this.settingCorner).getSetting("ChestColor");
        Color color12 = new Color(66, 245, 164);
        this.settingCornerEnderChestColor = (ColorSetting)new ColorSetting(this, "CornerEnderChestColor", color12, this.settingCorner).getSetting("EnderChestColor");
        this.settingCornerLineWidth = new NumberSetting(this, "CornerLineWidth", 2.0, 1.0, 5.0, 0, this.settingCorner).getNumberSetting("LineWidth");
        this.settingCornerHorizontalPercentage = new NumberSetting(this, "CornerHorizontalPercentage", 50.0, 1.0, 100.0, 0, this.settingCorner);
        this.settingCornerVerticalPercentage = new NumberSetting(this, "CornerVerticalPercentage", 50.0, 1.0, 100.0, 0, this.settingCorner).getNumberSetting("VerticalPercentage");
        this.settingCornerPriority = (NumberSetting)new NumberSetting(this, "CornerPriority", 1.0, 0.0, 1.0, 0, this.settingCorner).getNumberSetting("2DPriority").getSetting(() -> this.onReal2DPriorityAction());
        ShaderOptions shaderOptions6 = new ShaderOptions(this, "CornerShader", this.settingCorner);
        Color black5 = Color.black;
        this.settingCornerShader = shaderOptions6.getShaderOptions(black5).getShaderOptions5();
        this.settingMaxDistance = new NumberSetting(this, "MaxDistance", 6.0, 0.0, 32.0, 0);
        this.entryMap = new HashMap();
        this.itemList3 = new ArrayList();
        this.entityEspRenderer = new EntityEspRenderer();
        this.int2ObjectAVLTreeMap = new Int2ObjectAVLTreeMap();
        this.int2ObjectAVLTreeMap2 = new Int2ObjectAVLTreeMap();
        this.onBoxPriorityAction();
        this.onReal2DPriorityAction();
    }

    @Override
    public void onEnable() {
        this.registerEvent(32, -9);
        this.registerEvent(40);
        this.registerEvent(17);
    }

    @Override
    public void onDisable() {
        this.itemList3.clear();
        this.entryMap.clear();
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        if (!this.itemList3.isEmpty()) {
            ObjectIterator objectIterator = this.int2ObjectAVLTreeMap.values().iterator();

            while (objectIterator.hasNext()) {
                ((Runnable)objectIterator.next()).run();
                RenderUtils.processAction();
            }
        }
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        this.itemList3.clear();
        this.entryMap.clear();
    }

    @Override
    public void onRender3D(float partialTicks) {
        if (!this.itemList3.isEmpty()) {
            for (Object object : this.int2ObjectAVLTreeMap2.values()) {
                Runnable runnable = (Runnable)object;
                GL11.glPushMatrix();
                RenderUtils.updateState();
                runnable.run();
                RenderUtils.prepareScissor();
                GL11.glPopMatrix();
            }

            if (StorageESPRenderMode.REAL_2D.isSelected() || StorageESPRenderMode.CORNER.isSelected()) {
                ScaledResolution scaledResolution = new ScaledResolution(minecraft);

                for (TileEntity tileEntity : (Iterable<TileEntity>) this.itemList3) {
                    AxisAlignedBB boundingBox = tileEntity.getBlock().processAction((World)minecraft.theWorld, tileEntity.getBlockPos());
                    if (tileEntity instanceof TileEntityChest) {
                        TileEntityChest tileEntityChest = (TileEntityChest)tileEntity;
                        if (tileEntityChest.tileEntityChest != null) {
                            boundingBox = boundingBox.getAxisAlignedBB4(
                            tileEntityChest.tileEntityChest.getBlock().processAction((World)minecraft.theWorld, tileEntityChest.tileEntityChest.getBlockPos())
                            );
                        } else if (tileEntityChest.tileEntityChest2 != null) {
                            boundingBox = boundingBox.getAxisAlignedBB4(
                            tileEntityChest.tileEntityChest2.getBlock().processAction((World)minecraft.theWorld, tileEntityChest.tileEntityChest2.getBlockPos())
                            );
                        } else if (tileEntityChest.tileEntityChest3 != null) {
                            boundingBox = boundingBox.getAxisAlignedBB4(
                            tileEntityChest.tileEntityChest3.getBlock().processAction((World)minecraft.theWorld, tileEntityChest.tileEntityChest3.getBlockPos())
                            );
                        } else if (tileEntityChest.tileEntityChest1 != null) {
                            boundingBox = boundingBox.getAxisAlignedBB4(
                            tileEntityChest.tileEntityChest1.getBlock().processAction((World)minecraft.theWorld, tileEntityChest.tileEntityChest1.getBlockPos())
                            );
                        }
                    }

                    this.entryMap.put(tileEntity, RuntimeBridge.projectBoundingBox(boundingBox, scaledResolution));
                }
            }
        }
    }

    @Override
    public void onRunTick() {
        if (minecraft.theWorld != null) {
            this.itemList3.clear();
            this.entityEspRenderer
            .processDouble(minecraft.getRenderViewEntity().posX, minecraft.getRenderViewEntity().posY, minecraft.getRenderViewEntity().posZ);

            for (TileEntity tileEntity : (Iterable<TileEntity>) minecraft.theWorld.itemList3) {
                if ((tileEntity instanceof TileEntityChest || (byte)((tileEntity instanceof TileEntityEnderchest) ? 1 : 0) != 0)
                && minecraft.thePlayer
                .getDouble4(
                (double)tileEntity.getBlockPos().getX(),
                (double)tileEntity.getBlockPos().getY(),
                (double)tileEntity.getBlockPos().getZ()
                ) <= this.settingMaxDistance.getValue() * 16.0
                && (
                !(tileEntity instanceof TileEntityChest)
                || ((TileEntityChest)tileEntity).tileEntityChest == null && ((TileEntityChest)tileEntity).tileEntityChest2 == null
                )) {
                    this.applyTileEntity(tileEntity);
                }
            }
        }
    }

    private void applyRenderPass(RenderPass renderPass) {
        for (int i = 0; i < this.itemList3.size(); i++) {
            TileEntity tileEntity = (TileEntity)this.itemList3.get(i);
            int index = this.getIndex(tileEntity, this.settingBoxChestColor, this.settingBoxEnderChestColor, i);
            float scaleFactor = this.getScaleFactor(tileEntity, this.settingBoxLineWidth);
            int index2 = this.settingBoxShader.getIndex(renderPass, index, i);
            RenderUtils.applyTileEntity(tileEntity, index2, 1.0F, scaleFactor, this.settingBoxExpand.getValue(), false);
        }
    }

    private void applyRenderPass2(RenderPass renderPass) {
        for (int i = 0; i < this.itemList3.size(); i++) {
            TileEntity tileEntity = (TileEntity)this.itemList3.get(i);
            int index = this.getIndex(tileEntity, this.settingBlockBoxChestColor, this.settingBlockBoxEnderChestColor, i);
            float scaleFactor = this.getScaleFactor(tileEntity, this.settingBlockBoxLineWidth);
            RenderUtils.applyTileEntity(tileEntity, this.settingBlockBoxShader.getIndex(renderPass, index, i), 1.0F, scaleFactor, 0.0, true);
        }
    }

    private void updateState() {
        if (StorageESPRenderMode.BOX.isSelected()) {
            boolean enabled = this.settingBoxShader.canExecute();
            boolean enabled2 = this.settingBoxShader.canExecute2();
            MovementHandler.processBoolean(enabled, enabled2);
            RunnableTask runnableTask = renderPass -> this.applyRenderPass(renderPass);
            MovementHandler.applyRunnableTask(true, runnableTask);
        }
    }

    private float getScaleFactor(TileEntity tileEntity, NumberSetting setting) {
        float value = (float)(setting.getValue() / 2.0);
        if (!(
        minecraft.thePlayer
        .getDouble4((double)tileEntity.getBlockPos().getX(), (double)tileEntity.getBlockPos().getY(), (double)tileEntity.getBlockPos().getZ())
        > 1.0
        )) {
            return value;
        } else {
            double offset = minecraft.thePlayer
            .getDouble4((double)tileEntity.getBlockPos().getX(), (double)tileEntity.getBlockPos().getY(), (double)tileEntity.getBlockPos().getZ())
            / 20.0;
            float value2 = (float)(1.0 - offset);
            if ((double)value2 < 0.3) {
                value2 = 0.3F;
            }

            return value * value2;
        }
    }

    private void updateState2() {
        if (StorageESPRenderMode.REAL_2D.isSelected()) {
            boolean enabled = this.settingReal2DShader.canExecute();
            boolean enabled2 = this.settingReal2DShader.canExecute2();
            MovementHandler.processBoolean(enabled, enabled2);
            RunnableTask runnableTask = renderPass -> this.applyRenderPass4(renderPass);
            MovementHandler.applyRunnableTask(false, runnableTask);
        }
    }

    private void applyRenderPass3(RenderPass renderPass) {
        for (int i = 0; i < this.itemList3.size(); i++) {
            TileEntity tileEntity = (TileEntity)this.itemList3.get(i);
            int index = this.getIndex(tileEntity, this.settingCorner3ChestColor, this.settingCorner3EnderChestColor, i);
            RenderUtils.applyTileEntity2(tileEntity, this.settingCorner3DShader.getIndex(renderPass, index, i));
        }
    }

    private void onReal2DPriorityAction() {
        this.int2ObjectAVLTreeMap.clear();
        this.int2ObjectAVLTreeMap.put((int)this.settingReal2DPriority.getValue(), ((Runnable)(() -> this.updateState2())));
        this.int2ObjectAVLTreeMap.put((int)this.settingCornerPriority.getValue(), ((Runnable)(() -> this.updateState5())));
    }

    private void updateState3() {
        if (StorageESPRenderMode.BLOCK_BOX.isSelected()) {
            boolean enabled = this.settingBlockBoxShader.canExecute();
            boolean enabled2 = this.settingBlockBoxShader.canExecute2();
            MovementHandler.processBoolean(enabled, enabled2);
            RunnableTask runnableTask = renderPass -> this.applyRenderPass2(renderPass);
            MovementHandler.applyRunnableTask(true, runnableTask);
        }
    }

    private void applyRenderPass4(RenderPass renderPass) {
        for (int i = 0; i < this.itemList3.size(); i++) {
            TileEntity tileEntity = (TileEntity)this.itemList3.get(i);
            double[] coordArray = (double[])this.entryMap.get(tileEntity);
            if (renderPass != RenderPass.SHADOW) {
                double offset = coordArray[0];
                double offset2 = coordArray[1];
                double offset3 = coordArray[2];
                double offset4 = coordArray[3];
                int index;
                if (tileEntity instanceof TileEntityEnderchest) {
                    index = this.settingReal2DColorEnderChest1.getInt12(i);
                } else {
                    index = this.settingReal2DColorChest1.getInt12(i);
                }

                RenderUtils.processDouble4(offset, offset2, offset3, offset4, index);
            }

            RenderPass renderPass2 = RenderPass.BLUR;
            if (renderPass != renderPass2) {
                int index3;
                if (tileEntity instanceof TileEntityEnderchest) {
                    index3 = this.settingReal2DColorEnderChest2.getInt12(i);
                } else {
                    index3 = this.settingReal2DColorChest2.getInt12(i);
                }

                double offset5 = coordArray[0];
                double offset6 = coordArray[1];
                double offset7 = coordArray[2];
                double offset8 = coordArray[3];
                int index2 = RandomUtil.getIndex(renderPass, index3);
                RenderUtils.processDouble10(offset5, offset6, offset7, offset8, 1.0F, this.settingReal2DShader.getIndex(renderPass, index2, i));
            }
        }
    }

    private void updateState4() {
        if (StorageESPRenderMode.CORNER_3D.isSelected()) {
            boolean enabled = this.settingCorner3DShader.canExecute();
            boolean enabled2 = this.settingCorner3DShader.canExecute2();
            MovementHandler.processBoolean(enabled, enabled2);
            RunnableTask runnableTask = renderPass -> this.applyRenderPass3(renderPass);
            MovementHandler.applyRunnableTask(true, runnableTask);
        }
    }

    private int getIndex(TileEntity tileEntity, ColorSetting colorSetting, ColorSetting colorSetting2, int index) {
        return (byte)(
        (byte)((tileEntity instanceof TileEntityEnderchest) ? 1 : 0) != 0
        ? colorSetting2.getInt12(index)
        : colorSetting.getInt12(index)
        );
    }

    private void applyTileEntity(TileEntity tileEntity) {
        BlockPos blockPos = tileEntity.getBlockPos();
        if (this.entityEspRenderer
        .checkCondition(
        (double)blockPos.getX(),
        (double)blockPos.getY(),
        (double)blockPos.getZ(),
        (double)(blockPos.getX() + 1),
        (double)(blockPos.getY() + 1),
        (double)(blockPos.getZ() + 1)
        )) {
            this.itemList3.add(tileEntity);
        }
    }

    private void applyRenderPass5(RenderPass renderPass) {
        for (int i = 0; i < this.itemList3.size(); i++) {
            TileEntity tileEntity = (TileEntity)this.itemList3.get(i);
            double[] coordArray = (double[])this.entryMap.get(tileEntity);
            int index;
            if (tileEntity instanceof TileEntityEnderchest) {
                index = this.settingCornerEnderChestColor.getInt12(i);
            } else {
                index = this.settingCornerChestColor.getInt12(i);
            }

            double offset = coordArray[0];
            double offset2 = coordArray[1];
            double offset3 = coordArray[2];
            double offset4 = coordArray[3];
            float cornerLineWidth = (float)this.settingCornerLineWidth.getValue();
            double value = this.settingCornerHorizontalPercentage.getValue();
            double value2 = this.settingCornerVerticalPercentage.getValue();
            int index2 = RandomUtil.getIndex(renderPass, index);
            RenderUtils.processAction(offset, offset2, offset3, offset4, cornerLineWidth, value, value2, this.settingCornerShader.getIndex(renderPass, index2, i));
        }
    }

    private void updateState5() {
        if (StorageESPRenderMode.CORNER.isSelected()) {
            boolean enabled = this.settingCornerShader.canExecute();
            boolean enabled2 = this.settingCornerShader.canExecute2();
            MovementHandler.processBoolean(enabled, enabled2);
            RunnableTask runnableTask = renderPass -> this.applyRenderPass5(renderPass);
            MovementHandler.applyRunnableTask(false, runnableTask);
        }
    }

    private void onBoxPriorityAction() {
        this.int2ObjectAVLTreeMap2.clear();
        this.int2ObjectAVLTreeMap2.put((int)this.settingBoxPriority.getValue(), ((Runnable)(() -> this.updateState())));
        this.int2ObjectAVLTreeMap2.put((int)this.settingBlockBoxPriority.getValue(), ((Runnable)(() -> this.updateState3())));
        this.int2ObjectAVLTreeMap2.put((int)this.settingCorner3DPriority.getValue(), ((Runnable)(() -> this.updateState4())));
    }
}
