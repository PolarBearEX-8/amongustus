package augustus.modules.player;

import client.Client;
import client.event.RenderListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.setting.ActionSetting;
import client.setting.BooleanSetting;
import client.setting.CrafterIronType;
import client.setting.CrafterLeatherType;
import client.setting.CrafterSwordType;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.SettingContainer;
import client.setting.SettingProvider;
import client.util.EnchantmentManager;
import client.util.LvlManager;
import client.util.Pair;
import client.util.TimeHelper;
import java.awt.Color;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChestContainer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGoldenBoots;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class Crafter extends Module implements TickListener, RenderListener {
    public NumberSetting settingStartDelay;
    public ActionSetting settingDelay;
    public BooleanSetting settingSmartDelay;
    public ModeSetting settingEventToClick;
    public ModeSetting settingClickMode;
    public TimeHelper timer;
    public TimeHelper timer2;
    public int tickDelay;
    public int tickDelay1;
    public CrafterSwordType itemProp3;
    public CrafterSwordType crafterSwordType;
    public boolean expanded2;

    public Crafter() {
        super("Crafter", "Handwerker", new Color(123, 230, 10), Category.PLAYER);
        this.settingStartDelay = new NumberSetting(this, "StartDelay", 100.0, 0.0, 1000.0, 0);
        this.settingDelay = new ActionSetting(this, "Delay", 70.0, 120.0, 0.0, 400.0, 0);
        this.settingSmartDelay = new BooleanSetting(this, "SmartDelay", true);
        String[] textArray = new String[2];
        textArray[0] = "EventTick";
        textArray[1] = "EventRender";
        this.settingEventToClick = new ModeSetting(this, "EventToClick", "EventTick", textArray);
        Client.getClient();
        String[] textArray2 = new String[1];
        textArray2[0] = "Basic";
        this.settingClickMode = new ModeSetting(this, "ClickMode", "Basic", textArray2);
        this.timer = new TimeHelper();
        this.timer2 = new TimeHelper();
        this.tickDelay = 0;
        this.tickDelay1 = -1;
        this.itemProp3 = CrafterSwordType.SWORD;
        this.crafterSwordType = (CrafterSwordType)null;
        this.expanded2 = false;
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
        this.registerEvent(32);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onRender3D(float partialTicks) {
        this.processChar();
    }

    @Override
    public void onPreTick() {
        this.processChar();
    }

    private int getIndex(GuiChestContainer guiChestContainer, Item item, int index2) {
        for (int i = 10; i < guiChestContainer.container.itemList2.size(); i++) {
            ItemStack itemStack = ((Slot)guiChestContainer.container.itemList2.get(i)).getStack();
            if (item == null && itemStack == null || itemStack != null && itemStack.getItem().equals(item) && itemStack.animationsToGo >= index2) {
                return i;
            }
        }

        return -1;
    }

    private HashMap getHashMap() {
        HashMap hashMap = new HashMap();
        Object[] itemArray = CrafterSwordType.values();
        int length = itemArray.length;
        if (0 < length) {
            Object object = itemArray[0];
            double offset = -1.0;
            if (0 < minecraft.thePlayer.container.itemList2.size()) {
                minecraft.thePlayer.container.getSlot(0).getStack();
            }

            hashMap.put(object, offset);
        }

        return hashMap;
    }

    private void processChar() {
        if (!(minecraft.currentScreen instanceof GuiChestContainer)) {
            this.expanded2 = false;
            this.itemProp3 = CrafterSwordType.SWORD;
            this.timer2.reset();
            this.tickDelay1 = -1;
            this.crafterSwordType = null;
        } else {
            GuiChestContainer guiChestContainer = (GuiChestContainer)minecraft.currentScreen;
            if (this.timer2.hasReachedDouble(this.settingStartDelay.getValue())
            && this.timer.hasReached((long)this.tickDelay)) {
                HashMap hashMap = this.getHashMap();
                CrafterSwordType[] crafterSwordTypeArray = CrafterSwordType.values();
                int length = crafterSwordTypeArray.length;

                while (0 < length) {
                    CrafterSwordType crafterSwordTypeKind = crafterSwordTypeArray[0];
                    Pair pair = this.getPair(guiChestContainer);
                    HashMap map = (HashMap)pair.getSecond();
                    HashMap map2 = (HashMap)pair.getFirst();
                    hashMap.get(crafterSwordTypeKind);
                    Pair pair2 = this.getPair(crafterSwordTypeKind, map, map2);
                    pair2.getSecond();
                    CrafterIronType crafterIronTypeKind = (CrafterIronType)pair2.getFirst();
                }

                this.timer.reset();
                this.tickDelay = SettingContainer.settingProvider
                .getInt(this.settingDelay.getDouble3(), this.settingDelay.getDouble6());
            }
        }
    }

    private boolean checkGuiChestContainer3(GuiChestContainer guiChestContainer, int index) {
        Slot slot = (Slot)guiChestContainer.container.itemList2.get(index);
        if (this.settingSmartDelay.isEnabled()) {
            double offset = Math.abs((double)(slot.indexOffset + 2 + guiChestContainer.indexOffset1) - guiChestContainer.coordOffsetArray[0]);
            double offset2 = Math.abs((double)(slot.indexOffset2 + 2 + guiChestContainer.indexOffset2) - guiChestContainer.coordOffsetArray[1]);
            double offset3 = Math.sqrt(offset * offset + offset2 * offset2);
            double offset4 = this.settingDelay.getDouble3()
            + this.settingDelay.getDouble6()
            - this.settingDelay.getDouble3() / 2.0;
            double offset5 = offset3 / 30.0;
            if (offset3 > 36.0 && !this.timer.hasReached((long)(offset4 * offset5))) {
                return false;
            } else {
                guiChestContainer.coordOffsetArray = new double[]{(double)(slot.indexOffset + 2 + guiChestContainer.indexOffset1), (double)(slot.indexOffset2 + 2 + guiChestContainer.indexOffset2)};
                String selected = this.settingClickMode.getSelected();
switch (selected) {
            case "Basic":
                minecraft.playerController.windowClick(guiChestContainer.container.indexOffset2, index, 0, 0, minecraft.thePlayer);
                break;
            case "Other":
                Client.getClient().getClass();
                break;
            case "Exploit":
                Client.getClient().getClass();
        }

                this.timer.reset();
                this.tickDelay = SettingContainer.settingProvider
                .getInt(this.settingDelay.getDouble3(), this.settingDelay.getDouble6());
                return true;
            }
        } else {
            guiChestContainer.coordOffsetArray = new double[]{(double)(slot.indexOffset + 2 + guiChestContainer.indexOffset1), (double)(slot.indexOffset2 + 2 + guiChestContainer.indexOffset2)};
            String selected2 = this.settingClickMode.getSelected();
switch (selected2) {
            case "Basic":
                minecraft.playerController.windowClick(guiChestContainer.container.indexOffset2, index, 0, 0, minecraft.thePlayer);
                this.timer.reset();
                this.tickDelay = SettingContainer.settingProvider
                                .getInt(this.settingDelay.getDouble3(), this.settingDelay.getDouble6());
                return true;
            case "Other":
                Client.getClient().getClass();
                this.timer.reset();
                this.tickDelay = SettingContainer.settingProvider
                                .getInt(this.settingDelay.getDouble3(), this.settingDelay.getDouble6());
                return true;
            case "Exploit":
                Client.getClient().getClass();
                this.timer.reset();
                this.tickDelay = SettingContainer.settingProvider
                                .getInt(this.settingDelay.getDouble3(), this.settingDelay.getDouble6());
                return true;
            default:
                this.timer.reset();
                this.tickDelay = SettingContainer.settingProvider
                                .getInt(this.settingDelay.getDouble3(), this.settingDelay.getDouble6());
                return true;
        }
        }
    }

    private boolean checkGuiChestContainer(GuiChestContainer guiChestContainer, int index, int index2) {
        if (this.tickDelay1 != index2) {
            return false;
        } else {
            if (this.checkGuiChestContainer(guiChestContainer, index)) {
                this.tickDelay1++;
                this.expanded2 = true;
            }

            return this.settingDelay.getDouble3() != 0.0
            || this.settingDelay.getDouble6() != 0.0;
        }
    }

    private double getCoordinate(ItemStack itemStack) {
        return 0.0 + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager1.indexOffset, itemStack) / 11.0;
    }

    private boolean checkGuiChestContainer(GuiChestContainer guiChestContainer, CrafterIronType crafterIronTypeKind) {
        return this.tickDelay1 != 0;
    }

    private boolean checkGuiChestContainer(GuiChestContainer guiChestContainer, int index) {
        if (minecraft.thePlayer.inventoryPlayer.getItemStack() == null) {
            return false;
        } else {
            int animationsToGo = minecraft.thePlayer.inventoryPlayer.getItemStack().animationsToGo;
            Slot slot = (Slot)guiChestContainer.container.itemList2.get(index);
            if (this.settingSmartDelay.isEnabled()) {
                double offset = Math.abs((double)(slot.indexOffset + 2 + guiChestContainer.indexOffset1) - guiChestContainer.coordOffsetArray[0]);
                double offset2 = Math.abs((double)(slot.indexOffset2 + 2 + guiChestContainer.indexOffset2) - guiChestContainer.coordOffsetArray[1]);
                double offset3 = Math.sqrt(offset * offset + offset2 * offset2);
                double double3 = this.settingDelay.getDouble3();
                double offset4 = double3 + this.settingDelay.getDouble6();
                double offset5 = offset4 - this.settingDelay.getDouble3() / 2.0;
                double offset6 = offset3 / 30.0;
                if (offset3 > 36.0) {
                    long time2 = (long)(offset5 * offset6);
                    if (!this.timer.hasReached(time2)) {
                        return false;
                    }
                }
            }

            guiChestContainer.coordOffsetArray = new double[]{(double)(slot.indexOffset + 2 + guiChestContainer.indexOffset1), (double)(slot.indexOffset2 + 2 + guiChestContainer.indexOffset2)};
            String selected = this.settingClickMode.getSelected();
switch (selected) {
            case "Basic":
                int index2 = animationsToGo == 1 ? 0 : 1;
                minecraft.playerController.windowClick(guiChestContainer.container.indexOffset2, index, index2, 0, minecraft.thePlayer);
                break;
            case "Other":
                Client.getClient().getClass();
                break;
            case "Exploit":
                Client.getClient().getClass();
        }

            this.timer.reset();
            double double32 = this.settingDelay.getDouble3();
            double double6 = this.settingDelay.getDouble6();
            this.tickDelay = SettingContainer.settingProvider.getInt(double32, double6);
            return true;
        }
    }

    private Pair getPair(GuiChestContainer guiChestContainer) {
        int index = 0;
        int index2 = 0;
        int index3 = 0;
        int index4 = 0;
        int index5 = 0;
        int index6 = 0;
        int index7 = 0;
        int index8 = 0;
        int index9 = 0;
        int index10 = 0;
        if (0 < guiChestContainer.container.itemList2.size()) {
            ItemStack itemStack = ((Slot)guiChestContainer.container.itemList2.get(index10)).getStack();

            while (true) {
                if (index10 == 0) {
                    itemStack = minecraft.thePlayer.inventoryPlayer.getItemStack();
                }

                if (itemStack == null) {
                    if (index10 >= 10) {
                        index++;
                    }
                } else {
                    Item item = itemStack.getItem();
                    if (item.equals(Items.diamond)) {
                        index2++;
                        index6 += itemStack.animationsToGo;
                    } else if (item.equals(Items.ironIngot)) {
                        index4++;
                        index8 += itemStack.animationsToGo;
                    } else if (item.equals(Items.stick)) {
                        index3++;
                        index7 += itemStack.animationsToGo;
                    } else if (item.equals(Item.processAction(5))) {
                        index5++;
                        index9 += itemStack.animationsToGo;
                    }
                }

                if (++index10 >= guiChestContainer.container.itemList2.size()) {
                    HashMap hashMap = new HashMap();
                    hashMap.put(CrafterIronType.DIAMOND, index2);
                    hashMap.put(CrafterIronType.IRON, index4);
                    hashMap.put(CrafterIronType.STICKS, index3);
                    hashMap.put(CrafterIronType.PLANKS, index5);
                    hashMap.put(CrafterIronType.AIR, index);
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put(CrafterIronType.DIAMOND, index6);
                    hashMap2.put(CrafterIronType.IRON, index8);
                    hashMap2.put(CrafterIronType.STICKS, index7);
                    hashMap2.put(CrafterIronType.PLANKS, index9);
                    return new Pair(hashMap, hashMap2);
                }

                itemStack = ((Slot)guiChestContainer.container.itemList2.get(index10)).getStack();
            }
        } else {
            HashMap hashMap3 = new HashMap();
            hashMap3.put(CrafterIronType.DIAMOND, index2);
            hashMap3.put(CrafterIronType.IRON, index4);
            hashMap3.put(CrafterIronType.STICKS, index3);
            hashMap3.put(CrafterIronType.PLANKS, index5);
            hashMap3.put(CrafterIronType.AIR, index);
            HashMap hashMap4 = new HashMap();
            hashMap4.put(CrafterIronType.DIAMOND, index6);
            hashMap4.put(CrafterIronType.IRON, index8);
            hashMap4.put(CrafterIronType.STICKS, index7);
            hashMap4.put(CrafterIronType.PLANKS, index9);
            return new Pair(hashMap3, hashMap4);
        }
    }

    private double getCoordinate2(ItemStack itemStack) {
        double offset = 0.0;
        if (!(itemStack.getItem() instanceof ItemGoldenBoots) && ((ItemGoldenBoots)itemStack.getItem()).getCrafterLeatherType() == CrafterLeatherType.GOLD) {
            offset -= 0.01;
        }

        return offset;
    }

    private boolean checkGuiChestContainer2(GuiChestContainer guiChestContainer, int index) {
        Slot slot = guiChestContainer.container.getSlot(index);
        if (this.settingSmartDelay.isEnabled()) {
            double offset = Math.abs((double)(slot.indexOffset + 2 + guiChestContainer.indexOffset1) - guiChestContainer.coordOffsetArray[0]);
            double offset2 = Math.abs((double)(slot.indexOffset2 + 2 + guiChestContainer.indexOffset2) - guiChestContainer.coordOffsetArray[1]);
            double offset3 = Math.sqrt(offset * offset + offset2 * offset2);
            double double3 = this.settingDelay.getDouble3();
            double offset4 = double3 + this.settingDelay.getDouble6();
            double offset5 = offset4 - this.settingDelay.getDouble3() / 2.0;
            double offset6 = offset3 / 30.0;
            if (offset3 > 36.0) {
                long time2 = (long)(offset5 * offset6);
                if (!this.timer.hasReached(time2)) {
                    return false;
                }

                guiChestContainer.coordOffsetArray = new double[]{(double)(slot.indexOffset + 2 + guiChestContainer.indexOffset1), (double)(slot.indexOffset2 + 2 + guiChestContainer.indexOffset2)};
                String selected = this.settingClickMode.getSelected();
switch (selected) {
            case "Exploit":
                Client.getClient().getClass();
                break;
            case "Basic":
            case "Other":
                minecraft.playerController.windowClick(guiChestContainer.container.indexOffset2, index, 0, 1, minecraft.thePlayer);
        }

                this.timer.reset();
                double double32 = this.settingDelay.getDouble3();
                double double6 = this.settingDelay.getDouble6();
                this.tickDelay = SettingContainer.settingProvider.getInt(double32, double6);
                return true;
            }
        }

        guiChestContainer.coordOffsetArray = new double[]{(double)(slot.indexOffset + 2 + guiChestContainer.indexOffset1), (double)(slot.indexOffset2 + 2 + guiChestContainer.indexOffset2)};
        String selected2 = this.settingClickMode.getSelected();
switch (selected2) {
            case "Exploit":
                Client.getClient().getClass();
                this.timer.reset();
                double double33 = this.settingDelay.getDouble3();
                double double62 = this.settingDelay.getDouble6();
                this.tickDelay = SettingContainer.settingProvider.getInt(double33, double62);
                return true;
            case "Basic":
            case "Other":
                minecraft.playerController.windowClick(guiChestContainer.container.indexOffset2, index, 0, 1, minecraft.thePlayer);
                this.timer.reset();
                double double34 = this.settingDelay.getDouble3();
                double double63 = this.settingDelay.getDouble6();
                this.tickDelay = SettingContainer.settingProvider.getInt(double34, double63);
                return true;
            default:
                this.timer.reset();
                double double35 = this.settingDelay.getDouble3();
                double double64 = this.settingDelay.getDouble6();
                this.tickDelay = SettingContainer.settingProvider.getInt(double35, double64);
                return true;
        }
    }

    private boolean checkGuiChestContainer4(GuiChestContainer guiChestContainer, int index, int index2) {
        if (this.tickDelay1 != index2) {
            return false;
        } else {
            if (this.checkGuiChestContainer2(guiChestContainer, index)) {
                this.tickDelay1++;
                this.expanded2 = true;
            }

            return this.settingDelay.getDouble3() != 0.0
            || this.settingDelay.getDouble6() != 0.0;
        }
    }

    private boolean checkCrafterSwordType(CrafterSwordType crafterSwordTypeKind, GuiChestContainer guiChestContainer) {
        Pair pair = this.getPair(guiChestContainer);
        HashMap map = (HashMap)pair.getSecond();
        HashMap map2 = (HashMap)pair.getFirst();
        double offset = (Double)this.getHashMap().get(crafterSwordTypeKind);
        return (Double)this.getPair(crafterSwordTypeKind, map, map2).getSecond() != offset;
    }

    private Pair getPair(CrafterSwordType crafterSwordTypeKind, HashMap map, HashMap map2) {
        double offset = -11.0;
        CrafterIronType crafterIronTypeKind = CrafterIronType.AIR;
        map.get(CrafterIronType.AIR);
        Object[] itemArray = CrafterIronType.values();
        if (0 < itemArray.length) {
            Object object = itemArray[0];
        }

        return new Pair(offset, crafterIronTypeKind);
    }

    private boolean checkGuiChestContainer(GuiChestContainer guiChestContainer) {
        Pair pair = this.getPair(guiChestContainer);
        HashMap map = (HashMap)pair.getSecond();
        HashMap map2 = (HashMap)pair.getFirst();
        HashMap hashMap = this.getHashMap();
        CrafterSwordType[] crafterSwordTypeArray = CrafterSwordType.values();
        int length = crafterSwordTypeArray.length;
        if (0 < length) {
            CrafterSwordType crafterSwordTypeKind = crafterSwordTypeArray[0];
            hashMap.get(crafterSwordTypeKind);
            this.getPair(crafterSwordTypeKind, map, map2).getSecond();
        }

        return false;
    }
}
