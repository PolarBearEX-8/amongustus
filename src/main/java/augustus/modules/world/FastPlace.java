package augustus.modules.world;

import client.event.ClickListener;
import client.event.EventClick;
import client.event.MotionListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.FastPlaceBlocksType;
import client.setting.MultiSelectSetting;
import client.setting.RangeSetting;
import client.setting.SettingContainer;
import client.setting.SettingGroup;
import client.setting.SettingProvider;
import client.util.BooleanSupplier;
import client.util.CombatUtil;
import client.util.MovingObjectType;
import client.util.SlotManager;
import client.util.SmoothDamper;
import java.awt.Color;
import net.minecraft.block.BlockCache;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;

public class FastPlace extends Module implements ClickListener, MotionListener {
    public MultiSelectSetting settingItems = new MultiSelectSetting(this, "Items", FastPlaceBlocksType.values());
    public BooleanSetting settingOnlyOnClick = new BooleanSetting(this, "OnlyOnClick", true);
    public BooleanSetting settingAlwaysPlaceWhenPossible;
    public BooleanSetting settingOneClick;
    public BooleanSetting settingClickPause;
    public BooleanSetting settingAutoSwap;
    public BooleanSetting settingOnlyInAir;
    public BooleanSetting settingOnlyIfMotionIsPositive;
    public boolean activeState = false;
    public boolean expanded2 = false;
    public boolean enabled2;
    public int tickDelay1 = 0;
    public int tickDelay;
    public int keyCode2;

    public FastPlace() {
        super("FastPlace", "SchnellPlatzierung", new Color(54, 144, 217), Category.WORLD);
        this.tickDelay = SettingContainer.settingProvider.getInt2(0, 2);
        this.keyCode2 = SettingContainer.settingProvider.getInt2(0, 2);

        for (FastPlaceBlocksType fastPlaceBlocksTypeKind : FastPlaceBlocksType.values()) {
            SettingGroup settingGroup2 = new SettingGroup(this, fastPlaceBlocksTypeKind.getText()).getSettingGroup(() -> checkFastPlaceBlocksType(fastPlaceBlocksTypeKind));
            RangeSetting rangeSetting2 = new RangeSetting(this, fastPlaceBlocksTypeKind.text + "CPS", 10.0, 20.0, 0.0, 60.0, 0, settingGroup2)
                .getRangeSetting2();
            fastPlaceBlocksTypeKind.rangeSetting = rangeSetting2.getRangeSetting("CPS");
            fastPlaceBlocksTypeKind.rangeSetting.recalculate();
            RangeSetting rangeSetting22 = new RangeSetting(this, fastPlaceBlocksTypeKind.text + "DCPS", 2.0, 4.0, 0.0, 20.0, 0, settingGroup2)
                .getRangeSetting2();
            fastPlaceBlocksTypeKind.rangeSetting2 = rangeSetting22.getRangeSetting("DCPS");
            fastPlaceBlocksTypeKind.rangeSetting2.recalculate();
            if (fastPlaceBlocksTypeKind.entry.equals(ItemBlock.class)) {
                this.settingAlwaysPlaceWhenPossible = new BooleanSetting(this, "AlwaysPlaceWhenPossible", false, settingGroup2);
                this.settingOneClick = new BooleanSetting(this, "OneClick", false, settingGroup2);
                this.settingClickPause = new BooleanSetting(this, "ClickPause", false, settingGroup2);
                this.settingAutoSwap = new BooleanSetting(this, "AutoSwap", false, settingGroup2);
                this.settingOnlyInAir = new BooleanSetting(this, "OnlyInAir", false, settingGroup2);
                BooleanSetting booleanSetting = new BooleanSetting(this, "OnlyIfMotionIsPositive", false, settingGroup2);
                this.settingOnlyIfMotionIsPositive = (BooleanSetting)booleanSetting.getSetting(this.settingOnlyInAir::isEnabled);
            }
        }
    }

    @Override
    public void onEnable() {
        this.registerEvent(7);
        this.registerEvent(34);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.expanded2 = true;
    }

    @Override
    public void onClick(EventClick event) {
        this.activeState = false;
        if (minecraft.isIntegratedServerRunning()
            && (minecraft.gameSettings.keyBindUseItem.isKeyDown() || !this.settingOnlyOnClick.isEnabled())
            && minecraft.thePlayer.getHeldItem() != null) {
            for (FastPlaceBlocksType fastPlaceBlocksTypeKind : FastPlaceBlocksType.values()) {
                if (fastPlaceBlocksTypeKind.isSelected()) {
                    Item item = minecraft.thePlayer.getHeldItem().getItem();
                    if (item.getClass().equals(fastPlaceBlocksTypeKind.entry) || fastPlaceBlocksTypeKind.entry.equals(ItemBlock.class) && item instanceof ItemBlock) {
                        if (!(item instanceof ItemBlock)) {
                            SmoothDamper smoothDamper = fastPlaceBlocksTypeKind.smoothDamper;
                            int indexOffset2 = fastPlaceBlocksTypeKind.rangeSetting.getIndexOffset2();
                            int intEntry = smoothDamper.getInt(indexOffset2);
                            boolean enabled = intEntry > 0;

                            boolean enabled3;
                            for (enabled3 = false; intEntry > 0; enabled3 = true) {
                                event.updateInt2(1);
                                intEntry--;
                            }

                            if (enabled) {
                                SmoothDamper smoothDamper2 = fastPlaceBlocksTypeKind.smoothDamper2;
                                int indexOffset22 = fastPlaceBlocksTypeKind.rangeSetting2.getIndexOffset2();

                                for (int i = smoothDamper2.getInt(indexOffset22); i > 0; i--) {
                                    event.updateInt2(1);
                                    enabled3 = true;
                                }

                                this.updateFastPlaceBlocksType(fastPlaceBlocksTypeKind);
                            }

                            if (this.expanded2 && !enabled3) {
                                event.updateInt2(1);
                            }

                            event.setState3(true);
                            event.setState11(false);
                            this.expanded2 = false;
                            break;
                        }

                        if (this.settingOnlyInAir.isEnabled()) {
                            if (minecraft.thePlayer.onGround) {
                                continue;
                            }
                        }

                        if (!this.settingOnlyInAir.isEnabled() || !this.settingOnlyIfMotionIsPositive.isEnabled() || !(minecraft.thePlayer.motionY <= 0.0)) {
                            double offset = minecraft.thePlayer.posY - 0.5;
                            boolean state18 = minecraft.theWorld.getIBlockState2(minecraft.thePlayer.posX, offset, minecraft.thePlayer.posZ).getBlock().isState18();
                            if (state18 && !this.enabled2) {
                                this.tickDelay = SettingContainer.settingProvider.getInt2(0, 1);
                                this.keyCode2 = SettingContainer.settingProvider.getInt2(0, 1);
                            }

                            if (!state18 && this.enabled2) {
                                this.tickDelay1 = this.keyCode2;
                            }

                            boolean enabled4;
                            if ((this.tickDelay >= 0 || !this.enabled2) && !state18) {
                                enabled4 = true;
                            } else {
                                enabled4 = false;
                            }

                            this.enabled2 = state18;
                            int index = this.tickDelay - 1;
                            double[] coordOffsetArray = CombatUtil.getCoordOffsetArray(index, minecraft.thePlayer);
                            if (state18) {
                                double offset2 = coordOffsetArray[0];
                                if (!minecraft.theWorld.getIBlockState2(offset2, minecraft.thePlayer.posY - 1.0, coordOffsetArray[2]).getBlock().isState18()) {
                                    enabled4 = true;
                                }
                            }

                            if (state18 && this.tickDelay1 > 0) {
                                enabled4 = true;
                                this.tickDelay1--;
                            }

                            if (minecraft.objectMouseOver != null && minecraft.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
                                boolean enabled5 = false;
                                if (enabled4 || !this.settingClickPause.isEnabled()) {
                                    ItemStack itemStack5 = minecraft.thePlayer.getHeldItem();
                                    BlockPos blockPos = minecraft.objectMouseOver.getBlockPos();
                                    Vec3 hitVec = minecraft.objectMouseOver.hitVec;
                                    EnumFacing sideHit = minecraft.objectMouseOver.sideHit;
                                    boolean enabled6 = CombatUtil.checkItemStack(itemStack5, blockPos, sideHit, hitVec);
                                    SmoothDamper smoothDamper3 = fastPlaceBlocksTypeKind.smoothDamper;
                                    int indexOffset23 = fastPlaceBlocksTypeKind.rangeSetting.getIndexOffset2();
                                    int intEntry2 = smoothDamper3.getInt(indexOffset23);
                                    boolean enabled7;
                                    if (intEntry2 > 0 && !this.settingOneClick.isEnabled()
                                        || enabled6 && (this.settingOneClick.isEnabled() || this.settingAlwaysPlaceWhenPossible.isEnabled())) {
                                        enabled7 = true;
                                    } else {
                                        enabled7 = false;
                                    }

                                    while (
                                        intEntry2 > 0 && !this.settingOneClick.isEnabled()
                                            || enabled6 && (this.settingOneClick.isEnabled() || this.settingAlwaysPlaceWhenPossible.isEnabled())
                                    ) {
                                        event.updateInt2(1);
                                        boolean enabled8;
                                        if (!this.settingOneClick.isEnabled() && !this.settingAlwaysPlaceWhenPossible.isEnabled()) {
                                            enabled8 = false;
                                        } else {
                                            enabled8 = true;
                                        }

                                        event.setState7(enabled8);
                                        enabled6 = false;
                                        enabled5 = true;
                                        if (--intEntry2 < -30) {
                                            break;
                                        }
                                    }

                                    if (enabled7) {
                                        SmoothDamper smoothDamper22 = fastPlaceBlocksTypeKind.smoothDamper2;
                                        int indexOffset24 = fastPlaceBlocksTypeKind.rangeSetting2.getIndexOffset2();

                                        for (int i2 = smoothDamper22.getInt(indexOffset24); i2 > 0; enabled5 = true) {
                                            event.updateInt2(1);
                                            i2--;
                                        }

                                        this.updateFastPlaceBlocksType(fastPlaceBlocksTypeKind);
                                    }
                                }

                                if (this.expanded2 && !enabled5) {
                                    event.updateInt2(1);
                                }

                                event.setState3(true);
                                event.setState11(false);
                                this.activeState = true;
                                this.expanded2 = false;
                            }
                            break;
                        }
                    }
                }
            }
        } else {
            this.expanded2 = true;
        }
    }

    @Override
    public void onPostMotion() {
        if (this.settingAutoSwap.isEnabled()) {
            if (FastPlaceBlocksType.BLOCKS.isSelected()) {
                if (this.activeState) {
                    for (int i = 0; i < 9; i++) {
                        ItemStack itemStack = minecraft.thePlayer.container.getSlot(i + 36).getStack();
                        if (BlockCache.checkItemStack(itemStack)) {
                            SlotManager.applyModule(this, -2, i, false);
                            break;
                        }
                    }
                }
            }
        }
    }

    private static boolean checkFastPlaceBlocksType(FastPlaceBlocksType fastPlaceBlocksTypeKind) {
        return fastPlaceBlocksTypeKind.state;
    }

    private void updateFastPlaceBlocksType(FastPlaceBlocksType fastPlaceBlocksTypeKind) {
        fastPlaceBlocksTypeKind.rangeSetting.recalculate();
        fastPlaceBlocksTypeKind.rangeSetting2.recalculate();
    }
}
