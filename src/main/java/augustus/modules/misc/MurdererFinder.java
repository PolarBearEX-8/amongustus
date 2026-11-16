package augustus.modules.misc;

import client.event.EventWorldLoad;
import client.event.TickListener;
import client.event.WorldLoadListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.util.BooleanSupplier;
import client.util.SoundPlayer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.Items;

public class MurdererFinder extends Module implements TickListener, WorldLoadListener {
    public BooleanSetting settingAcousticWarning;
    public NumberSetting settingVolume;
    public HashSet entrySet;
    public boolean expanded2;
    public static ArrayList itemList3 = new ArrayList(
        Arrays.asList(
            Items.ironSword,
            Item.processAction(130),
            Items.cookedChicken,
            Items.bone,
            Items.blazeRod,
            Items.netherbrick,
            Items.carrotOnAStick,
            Items.stoneSword,
            Item.processAction(19),
            Item.processAction(32),
            Items.boat,
            Item.processAction(382),
            Items.goldenPickaxe,
            Items.cookedBeef,
            Items.book,
            Items.apple,
            Items.prismarineShard,
            Items.quartz,
            Items.diamondSword,
            Items.nameTag,
            Items.diamondShovel,
            Item.getItem("double_plant"),
            Items.pumpkinPie,
            Items.diamondHoe,
            Items.carrot,
            Item.processAction(351),
            Items.fish,
            Items.cookedFish,
            Items.shears,
            Items.ironShovel,
            Items.goldenCarrot,
            Items.woodenSword,
            Items.stick,
            Items.stoneShovel,
            Items.cookie,
            Items.diamondAxe,
            Items.goldenSword,
            Items.woodenAxe
        )
    );
    public static Setting[] settingsArray;

    public MurdererFinder() {
        super("MurdererFinder", "MörderFinder", new Color(252, 186, 3), Category.MISC);
        this.settingAcousticWarning = new BooleanSetting(this, "AcousticWarning", true);
        this.settingVolume = new NumberSetting(this, "Volume", 93.0, 0.0, 100.0, 0).getNumberSetting(() -> ((BooleanSetting)this.settingAcousticWarning).isEnabled());
        this.entrySet = new HashSet();
        this.expanded2 = true;
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
        this.registerEvent(17);
    }

    @Override
    public void onDisable() {
        this.entrySet.clear();
        this.expanded2 = true;
    }

    @Override
    public void onPreDisable() {
        this.entrySet.clear();
        this.expanded2 = true;
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        this.entrySet.clear();
    }

    @Override
    public void onRunTick() {
        for (Entity entity : (Iterable<Entity>) minecraft.theWorld.loadedEntityList) {
            if (entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer)entity;
                if (player.getItemStack() != null
                && !this.entrySet.contains(player.getText())
                && player.getItemStack() != null
                && !this.entrySet.contains(player.getText())) {
                    Item item = player.getItemStack().getItem();
                    if (itemList3.contains(item) || item instanceof ItemDye) {
                        this.entrySet.add(player.getText());
                    }
                }
            }
        }

        if (!this.entrySet.isEmpty() && this.expanded2 && this.settingAcousticWarning.isEnabled()) {
            minecraft.getMcSoundHandler()
            .applyISound2(
            PositionedSoundRecord.createPositionedSound(SoundPlayer.resourceLocation, (float)(this.settingVolume.getValue() / 100.0))
            );
        }

        this.expanded2 = this.entrySet.isEmpty();
    }

    public boolean checkString(String text) {
        return !this.isToggled() ? false : this.entrySet.contains(text);
    }

    public static Setting[] getSettingArray() {
        return settingsArray;
    }

    public static void setSettingArray(Setting[] settingArray2) {
        settingsArray = settingArray2;
    }
}
