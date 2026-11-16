package augustus.modules.world;

import augustus.modes.world.FastBreakBlockMode;
import augustus.modes.world.FastBreakToolEntry;
import client.event.EventPacket;
import client.event.PacketListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.PlayerDiggingActionType;
import client.util.RayTraceUtil;
import java.awt.Color;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;

public class FastBreak extends Module implements PacketListener {
    public BooleanSetting settingNoBlockHitDelay = new BooleanSetting(this, "NoBlockHitDelay", false);
    public BooleanSetting settingIgnoreMiningFatigue = new BooleanSetting(this, "IgnoreMiningFatigue", false);
    public BooleanSetting settingOnlyWhenBlink = new BooleanSetting(this, "OnlyWhenBlink", false);
    public BooleanSetting settingIgnoreBeds = new BooleanSetting(this, "IgnoreBeds", false);
    public BooleanSetting settingDebugBreakSpeed = new BooleanSetting(this, "DebugBreakSpeed", false);
    public ModeSetting settingMode = new ModeSetting(this, "Mode", "Simple", new String[]{"Simple", "Expert"});
    public SettingGroup settingSimpleSettings;
    public BooleanSetting settingCustomInAirMultiplier;
    public NumberSetting settingInAirMultiplier;
    public NumberSetting settingMultiplier;
    public NumberSetting settingArtificialMiningSpeedEffect;
    public BooleanSetting settingInstant;
    public BooleanSetting settingDedicatedItemHandling;
    public SettingGroup settingDedicatedItems;
    public HashMap entryMap;
    public HashMap entryMap2;
    public boolean expanded2;

    public FastBreak() {
        super("FastBreak", "Schnellbruch", new Color(52, 152, 219), Category.WORLD);
        SettingGroup settingGroup = new SettingGroup(this, "SimpleSettings", true);
        BooleanSupplier booleanSupplier = () -> this.isSimpleSettingsVisible();
        this.settingSimpleSettings = settingGroup.getSettingGroup(booleanSupplier);
        this.settingCustomInAirMultiplier = new BooleanSetting(
            this, "CustomInAirMultiplier", false, this.settingSimpleSettings
        );
        this.settingInAirMultiplier = new NumberSetting(this, "InAirMultiplier", 0.2, 0.0, 1.0, 2, this.settingSimpleSettings).getNumberSetting(this.settingCustomInAirMultiplier::isEnabled);
        this.settingMultiplier = new NumberSetting(this, "Multiplier", 2.0, 1.0, 2.0, 2, this.settingSimpleSettings);
        this.settingArtificialMiningSpeedEffect = new NumberSetting(
            this, "ArtificialMiningSpeedEffect", 0.0, 0.0, 3.0, 0, this.settingSimpleSettings
        );
        this.settingInstant = new BooleanSetting(this, "Instant", false, this.settingSimpleSettings);
        this.entryMap = new HashMap();
        this.entryMap2 = new HashMap();
        this.expanded2 = false;
        HashMap entryMap3 = this.entryMap;
        this.applyHashMap(entryMap3);
        FastBreakToolEntry[] fastBreakToolEntryArray = ((FastBreakToolEntry[])(this.entryMap.values().toArray(new FastBreakToolEntry[0])));
        Arrays.sort(fastBreakToolEntryArray, Comparator.comparing(fastBreakToolEntry -> ((FastBreakToolEntry)fastBreakToolEntry).getText()));
        new MultiSelectSetting(this, "BlocksToFastbreak", fastBreakToolEntryArray);
        this.entryMap.values().forEach(object -> this.applyFastBreakExpertMode((FastBreakToolEntry)object));
        Item[] itemArray = new Item[]{
            Items.woodenPickaxe,
            Items.stonePickaxe,
            Items.ironPickaxe,
            Items.goldenPickaxe,
            Items.diamondPickaxe,
            Items.woodenAxe,
            Items.stoneAxe,
            Items.ironAxe,
            Items.goldenAxe,
            Items.diamondAxe,
            Items.woodenShovel,
            Items.stoneShovel,
            Items.ironShovel,
            Items.goldenShovel,
            Items.diamondShovel,
            Items.shears
        };
        this.settingDedicatedItemHandling = new BooleanSetting(this, "DedicatedItemHandling", false);
        this.settingDedicatedItems = new SettingGroup(this, "DedicatedItems", true).getSettingGroup(this.settingDedicatedItemHandling::isEnabled);

        for (Item item : itemArray) {
            String text = ((ResourceLocation)Item.iterableExtensionAdapter.getObject2(item)).getText1();
            this.entryMap2.put(item, new FastBreakBlockMode(this, this, text));
        }

        FastBreakBlockMode[] fastBreakBlockModeArray = ((FastBreakBlockMode[])(this.entryMap2.values().toArray(new FastBreakBlockMode[0])));
        Arrays.sort(fastBreakBlockModeArray, Comparator.comparing(fastBreakBlockMode -> ((FastBreakBlockMode)fastBreakBlockMode).getText()));
        new MultiSelectSetting(this, "CustomItems", fastBreakBlockModeArray, this.settingDedicatedItems);
        this.entryMap2.values().forEach(object2 -> ((FastBreakBlockMode)object2).updateState());
    }

    @Override
    public void onEnable() {
        this.registerEvent(26);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onPacketSend(EventPacket packet) {
        if (packet.getPacket() instanceof C07PacketPlayerDigging) {
            if (((C07PacketPlayerDigging)packet.getPacket()).getPlayerDiggingActionType() == PlayerDiggingActionType.START_DESTROY_BLOCK) {
                this.expanded2 = true;
            }
        }
    }

    private Item getItem() {
        ItemStack itemStack5 = minecraft.thePlayer.getHeldItem();
        return itemStack5 == null ? null : itemStack5.getItem();
    }

    private FastBreakBlockMode getFastBreakSimpleMode() {
        Item item = this.getItem();
        return item == null ? null : (FastBreakBlockMode)this.entryMap2.get(item);
    }

    public static void applyFastBreak(FastBreak fastBreak, HashMap map) {
        fastBreak.applyHashMap(map);
    }

    public double getCoordinate(Block block) {
        if (this.settingDedicatedItemHandling.isEnabled()) {
            FastBreakBlockMode fastBreakSimpleMode = this.getFastBreakSimpleMode();
            if (fastBreakSimpleMode != null) {
                return fastBreakSimpleMode.getCoordinate2(block);
            }
        }

        return this.settingMode.getSelected().equals("Simple")
            ? this.settingMultiplier.getValue()
            : FastBreakToolEntry.getNumberSetting3(this.getFastBreakToolEntry(block)).getValue();
    }

    public boolean checkBlock(Block block) {
        if (this.settingDedicatedItemHandling.isEnabled()) {
            FastBreakBlockMode fastBreakSimpleMode = this.getFastBreakSimpleMode();
            if (fastBreakSimpleMode != null) {
                return fastBreakSimpleMode.checkBlock(block);
            }
        }

        return this.settingMode.getSelected().equals("Simple")
            ? this.settingCustomInAirMultiplier.isEnabled()
            : FastBreakToolEntry.getBooleanSetting2(this.getFastBreakToolEntry(block)).isEnabled();
    }

    private void applyFastBreakExpertMode(FastBreakToolEntry fastBreakToolEntry) {
        BooleanSupplier booleanSupplier = () -> this.canExecute5();
        fastBreakToolEntry.applyModeSetting("", this.settingMode, (SettingGroup)null, booleanSupplier);
    }

    public static SettingGroup getSettingGroup(FastBreak fastBreak) {
        return fastBreak.settingDedicatedItems;
    }

    private boolean isSimpleSettingsVisible() {
        return this.settingMode.getSelected().equals("Simple");
    }

    public double getCoordinate3(Block block) {
        if (this.settingDedicatedItemHandling.isEnabled()) {
            FastBreakBlockMode fastBreakSimpleMode = this.getFastBreakSimpleMode();
            if (fastBreakSimpleMode != null) {
                return fastBreakSimpleMode.getCoordinate3(block);
            }
        }

        return this.settingMode.getSelected().equals("Simple")
            ? this.settingArtificialMiningSpeedEffect.getValue()
            : FastBreakToolEntry.getNumberSetting(this.getFastBreakToolEntry(block)).getValue();
    }

    public boolean checkBlock2(Block block) {
        if (!this.isToggled()) {
            return false;
        } else {
            FastBreakToolEntry fastBreakToolEntry = (FastBreakToolEntry)this.entryMap.get(block);
            return fastBreakToolEntry == null ? true : fastBreakToolEntry.isSelected();
        }
    }

    private FastBreakToolEntry getFastBreakToolEntry(Block block) {
        return (FastBreakToolEntry)this.entryMap.get(block);
    }

    private boolean canExecute5() {
        return this.settingMode.getSelected().equals("Expert");
    }

    private void applyHashMap(HashMap map) {
        Iterator iterator = Block.iterableExtensionBridge.getSet().iterator();

        while (iterator.hasNext()) {
            String text = ((ResourceLocation)iterator.next()).getText1();
            Block block = Block.getBlockFromName(text);
            map.put(block, new FastBreakToolEntry(this, this, text));
        }
    }

    public boolean checkBlock3(Block block) {
        if (this.settingDedicatedItemHandling.isEnabled()) {
            FastBreakBlockMode fastBreakSimpleMode = this.getFastBreakSimpleMode();
            if (fastBreakSimpleMode != null) {
                return fastBreakSimpleMode.checkBlock2(block);
            }
        }

        return this.settingMode.getSelected().equals("Simple")
            ? this.settingInstant.isEnabled()
            : FastBreakToolEntry.getBooleanSetting(this.getFastBreakToolEntry(block)).isEnabled();
    }

    public void applyBlock(Block block, BlockPos blockPos) {
        if (this.isToggled()) {
            if (this.expanded2) {
                if (this.settingDebugBreakSpeed.isEnabled()) {
                    this.expanded2 = false;
                    RayTraceUtil.applyString(
                        "BreakSpeed: " + Math.round(block.getScaleFactor(minecraft.thePlayer, minecraft.thePlayer.worldObj, blockPos) * 1000.0F)
                    );
                }
            }
        }
    }

    public double getCoordinate2(Block block) {
        if (this.settingDedicatedItemHandling.isEnabled()) {
            FastBreakBlockMode fastBreakSimpleMode = this.getFastBreakSimpleMode();
            if (fastBreakSimpleMode != null) {
                return fastBreakSimpleMode.getCoordinate(block);
            }
        }

        return this.settingMode.getSelected().equals("Simple")
            ? this.settingInAirMultiplier.getValue()
            : FastBreakToolEntry.getNumberSetting2(this.getFastBreakToolEntry(block)).getValue();
    }

    public static BooleanSetting getBooleanSetting(FastBreak fastBreak) {
        return fastBreak.settingDedicatedItemHandling;
    }
}
