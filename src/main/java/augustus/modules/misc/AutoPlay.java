package augustus.modules.misc;

import client.event.ClickListener;
import client.event.EventClick;
import client.event.EventPacket;
import client.event.EventWorldLoad;
import client.event.MotionListener;
import client.event.PacketListener;
import client.event.RenderListener;
import client.event.TickListener;
import client.event.WorldLoadListener;
import client.gui.ClickGuiScreen;
import client.gui.CustomGuiScreen;
import client.module.Category;
import client.module.Module;
import client.setting.AutoPlayTargetEntity;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.RangeSetting;
import client.setting.SettingContainer;
import client.setting.SettingProvider;
import client.util.BooleanSupplier;
import client.util.Packet;
import client.util.RayTraceUtil;
import client.util.SlotManager;
import client.util.TimeHelper;
import java.awt.Color;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenServerList;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S40PacketDisconnect;
import org.lwjgl.input.Mouse;

public class AutoPlay extends Module implements WorldLoadListener, ClickListener, TickListener, RenderListener, PacketListener, MotionListener {
    public ModeSetting settingMode;
    public ModeSetting settingGamemodeMMC;
    public ModeSetting settingGamemodeKit1v1;
    public ModeSetting settingGamemodeGamster;
    public BooleanSetting settingRemoveEscape;
    public BooleanSetting settingCancelSound;
    public NumberSetting settingHotbarItemID;
    public NumberSetting settingChestslot;
    public BooleanSetting settingSetSlotAfterRespawn;
    public RangeSetting settingDelayToSetSlowAfterRespawn;
    public NumberSetting settingSlotToSetAfterRespawn;
    public TimeHelper timer;
    public TimeHelper timer2;
    public TimeHelper timer3;
    public int tickDelay;
    public float motionMultiplier;
    public float[] scaleFactorArray;
    public int keyCode2;
    public boolean expanded2;
    public HashMap entryMap;

    public AutoPlay() {
        super("AutoPlay", "AutoSpielen", Color.green, Category.MISC);
        String[] textArray = new String[12];
        textArray[0] = "MinemenUnranked";
        textArray[1] = "MinemenRanked";
        textArray[2] = "PikaSkywars";
        textArray[3] = "HypixelNormal";
        textArray[4] = "HypixelInsane";
        textArray[5] = "GommeKit1v1Ranked";
        textArray[6] = "GommeKit1v1Unranked";
        textArray[7] = "Kitpvp";
        textArray[8] = "PvpDojo";
        textArray[9] = "UniversocraftSkywars";
        textArray[10] = "AutoGamblePVPDojo";
        textArray[11] = "Gamster";
        this.settingMode = new ModeSetting(this, "Mode", "MinemenRanked", textArray);
        String[] textArray2 = new String[3];
        textArray2[0] = "Sumo";
        textArray2[1] = "Boxing";
        textArray2[2] = "Parkour";
        this.settingGamemodeMMC = new ModeSetting(this, "GamemodeMMC", "Sumo", textArray2).getModeSetting("Gamemode").getModeSetting(() -> this.isGamemodeMMCVisible());
        String[] textArray3 = new String[6];
        textArray3[0] = "UHC";
        textArray3[1] = "OnlySword";
        textArray3[2] = "Soup";
        textArray3[3] = "SG";
        textArray3[4] = "SW";
        textArray3[5] = "QSG";
        this.settingGamemodeKit1v1 = new ModeSetting(this, "GamemodeKit1v1", "OnlySword", textArray3).getModeSetting("Gamemode").getModeSetting(() -> this.isGamemodeKit1v1Visible());
        String[] textArray4 = new String[7];
        textArray4[0] = "SkyWarsSolo";
        textArray4[1] = "SkyWarsTeams";
        textArray4[2] = "SkyWarsLucky";
        textArray4[3] = "BedWarsSolo";
        textArray4[4] = "BedWarsDuos";
        textArray4[5] = "BedWarsTrios";
        textArray4[6] = "BedWarsTeams";
        this.settingGamemodeGamster = new ModeSetting(this, "GamemodeGamster", "SkyWarsSolo", textArray4).getModeSetting(() -> this.isGamemodeGamsterVisible());
        this.settingRemoveEscape = new BooleanSetting(this, "RemoveEscape", true);
        this.settingCancelSound = new BooleanSetting(this, "CancelSound", true);
        this.settingHotbarItemID = new NumberSetting(this, "HotbarItemID", 54.0, 0.0, 2267.0, 0);
        this.settingChestslot = new NumberSetting(this, "Chestslot", 0.0, 0.0, 54.0, 0).getNumberSetting(() -> this.isChestslotVisible());
        this.settingSetSlotAfterRespawn = new BooleanSetting(this, "SetSlotAfterRespawn", false);
        this.settingDelayToSetSlowAfterRespawn = new RangeSetting(this, "DelayToSetSlowAfterRespawn", 5.0, 10.0, 0.0, 20.0, 0)
            .getRangeSetting2().getRangeSetting(15343, () -> this.settingSetSlotAfterRespawn.isEnabled());
        this.settingSlotToSetAfterRespawn = new NumberSetting(this, "SlotToSetAfterRespawn", 0.0, 0.0, 8.0, 0).getNumberSetting(() -> this.settingSetSlotAfterRespawn.isEnabled());
        this.timer = new TimeHelper();
        this.timer2 = new TimeHelper();
        this.timer3 = new TimeHelper();
        this.tickDelay = 0;
        this.scaleFactorArray = new float[2];
        this.keyCode2 = 0;
        this.expanded2 = false;
        this.entryMap = new HashMap();
        this.entryMap.put("SkyWarsSolo", "/play sw-solo");
        this.entryMap.put("SkyWarsTeams", "/play sw-teams");
        this.entryMap.put("SkyWarsLucky", "/play sw-lucky");
        this.entryMap.put("BedWarsSolo", "/play bw-solo");
        this.entryMap.put("BedWarsDuos", "/play bw-duo");
        this.entryMap.put("BedWarsTrios", "/play bw-trio");
        this.entryMap.put("BedWarsTeams", "/play bw-teams");
    }

    @Override
    public void onEnable() {
        this.registerEvent(7);
        this.registerEvent(40);
        this.registerEvent(32);
        this.registerEvent(26);
        this.registerEvent(34);
        this.registerEvent(17);
    }

    @Override
    public void onDisable() {
        minecraft.gameSettings.updateAutoPlayTargetEntity(AutoPlayTargetEntity.MASTER, this.motionMultiplier);
    }

    @Override
    public void onPreDisable() {
        this.motionMultiplier = minecraft.gameSettings.getScaleFactor(AutoPlayTargetEntity.MASTER);
        if (this.settingCancelSound.isEnabled()) {
            minecraft.gameSettings.updateAutoPlayTargetEntity(AutoPlayTargetEntity.MASTER, 0.0F);
        }

        this.expanded2 = false;
    }

    @Override
    public void onClick(EventClick event) {
        if (minecraft.isIntegratedServerRunning()) {
            ItemStack itemStack;
            if ((itemStack = minecraft.thePlayer.container.getSlot(minecraft.thePlayer.inventoryPlayer.currentItem + 36).getStack()) != null) {
                if (itemStack.getString().contains("Unranked") && this.settingMode.getSelected().equals("MinemenUnranked")) {
                    event.updateInt2(1);
                }

                if (!itemStack.getString().contains("Unranked")
                    && itemStack.getString().contains("Ranked")
                    && this.settingMode.getSelected().equals("MinemenRanked")) {
                    event.updateInt2(1);
                }

                if (itemStack.getString().contains("Play Again") && this.settingMode.getSelected().equals("MinemenUnranked")) {
                    event.updateInt2(1);
                }

                if (itemStack.getString().contains("Play Again")
                    && this.timer2.hasReached(6000L)
                    && this.settingMode.getSelected().equals("PikaSkywars")) {
                    event.updateInt2(1);
                    this.timer2.reset();
                }

                if (this.settingMode.getSelected().equals("GommeKit1v1Ranked")) {
                    String text = itemStack.getString();
                    if (this.checkString(text) || itemStack.getItem().equals(Items.blazeRod)) {
                        long time = (long)SettingContainer.settingProvider.getInt2(150, 250);
                        if (this.timer3.hasReached(time)) {
                            event.updateInt2(1);
                            this.timer3.reset();
                        }
                    }
                }

                if (this.settingMode.getSelected().equals("GommeKit1v1Unranked")) {
                    String text2 = itemStack.getString();
                    if (this.checkString(text2) || itemStack.getItem().equals(Items.blazeRod)) {
                        long time2 = (long)SettingContainer.settingProvider.getInt2(150, 250);
                        if (this.timer3.hasReached(time2)) {
                            event.updateInt(1);
                            this.timer3.reset();
                        }
                    }
                }

                if (this.settingMode.getSelected().equalsIgnoreCase("kitpvp")) {
                    long time3 = (long)SettingContainer.settingProvider.getInt2(1000, 2000);
                    if (this.timer3.hasReached(time3)
                        && Item.processAction((int)this.settingHotbarItemID.getValue()) == itemStack.getItem()) {
                        event.updateInt2(1);
                        this.timer3.reset();
                    }
                }

                if (this.settingMode.getSelected().equals("AutoGamblePVPDojo")
                    && !this.expanded2
                    && SlotManager.checkModule(this)) {
                    long time4 = (long)SettingContainer.settingProvider.getInt2(1000, 2000);
                    if (this.timer3.hasReached(time4) && itemStack.getString().contains("Collection")) {
                        event.updateInt2(1);
                        this.timer3.reset();
                    }
                }
            }
        }
    }

    @Override
    public void onPacketPostReceive(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (packet2 instanceof S40PacketDisconnect) {
            minecraft.mouseHelper.updateState();
        }

        if (packet2 instanceof S02PacketChat) {
            String text = ((S02PacketChat)packet2).getChatComponent().getString();
            if (this.settingMode.getSelected().equals("HypixelNormal")) {
                if (text.contains("You won! Want to play again? Click here!")) {
                    minecraft.thePlayer.sendChatMessage("/play solo_normal");
                }

                if (text.contains("You died!")) {
                    minecraft.thePlayer.sendChatMessage("/play solo_normal");
                }
            }

            if (this.settingMode.getSelected().equals("HypixelInsane")) {
                if (text.contains("You won! Want to play again? Click here!")) {
                    minecraft.thePlayer.sendChatMessage("/play solo_insane");
                }

                if (text.contains("You died!")) {
                    minecraft.thePlayer.sendChatMessage("/play solo_insane");
                }
            }

            if (this.settingMode.getSelected().equals("UniversocraftSkywars")) {
                if (text.contains("Jugar de nuevo - Salir")) {
                    minecraft.thePlayer.sendChatMessage("/skywars random");
                }
            }
        }
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        this.expanded2 = false;
        this.settingDelayToSetSlowAfterRespawn.recalculate();
        this.tickDelay = 0;
    }

    @Override
    public void onRender3D(float partialTicks) {
        if (minecraft.currentScreen instanceof ClickGuiScreen && Mouse.isGrabbed()) {
            Mouse.setGrabbed(false);
        }

        if (minecraft.currentScreen instanceof GuiScreenServerList) {
            if (this.settingRemoveEscape.isEnabled()) {
                minecraft.currentScreen = (GuiScreen)null;
                minecraft.thePlayer.setGuiScreen(null);
            }
        }
    }

    @Override
    public void onPreMotion() {
        if (minecraft.isIntegratedServerRunning()) {
            if (this.settingSetSlotAfterRespawn.isEnabled()) {
                int tickDelay2 = this.tickDelay;
                if (tickDelay2 == Math.max(0, this.settingDelayToSetSlowAfterRespawn.getIndexOffset2())) {
                    SlotManager.indexOffset = minecraft.thePlayer.inventoryPlayer.currentItem = (int)this.settingSlotToSetAfterRespawn.getDouble2(true);
                }

                this.tickDelay++;
            }
        }
    }

    @Override
    public void onPostMotion() {
        if (minecraft.isIntegratedServerRunning()) {
            int index = 36;

            while (index < minecraft.thePlayer.container.itemList2.size()) {
                ItemStack itemStack = minecraft.thePlayer.container.getSlot(index).getStack();
                if (itemStack == null) {
                    index++;
                } else {
                    Item item = itemStack.getItem();
                    String text = itemStack.getString();
                    String selected = this.settingMode.getSelected();
                    selected.hashCode();
                    switch (selected) {
                        case "MinemenUnranked":
                            if (text.contains("Unranked")) {
                                SlotManager.applyModule3(this, 20, index - 36);
                            } else if (text.contains("Play Again")) {
                                SlotManager.applyModule3(this, 20, index - 36);
                            }
                            break;
                        case "MinemenRanked":
                            if (!text.contains("Unranked") && itemStack.getString().contains("Ranked")) {
                                SlotManager.applyModule3(this, 20, index - 36);
                            }
                            break;
                        case "PikaSkywars":
                            if (text.contains("Play Again")) {
                                SlotManager.applyModule3(this, 20, index - 36);
                            }
                            break;
                        case "GommeKit1v1Ranked":
                        case "GommeKit1v1Unranked":
                            if (this.checkString(text)) {
                                SlotManager.applyModule3(this, 20, index - 36);
                            }

                            if (item.equals(Items.blazeRod)) {
                                SlotManager.applyModule3(this, 20, index - 36);
                            }
                            break;
                        case "AutoGamblePVPDojo":
                            if (text.contains("Collection") && !this.expanded2) {
                                SlotManager.applyModule3(this, 20, index - 36);
                            }
                            break;
                        case "Gamster":
                            this.applyItemStack(itemStack, item);
                    }

                    index++;
                }
            }
        }
    }

    @Override
    public void onPreTick() {
        if (minecraft.isIntegratedServerRunning()) {
            this.timer.reset();
            this.keyCode2 = SettingContainer.settingProvider.getInt2(0, 150);
        } else if (minecraft.currentScreen instanceof CustomGuiScreen) {
            long time = (long)(350 + this.keyCode2);
            if (this.timer.hasReached(time)) {
                CustomGuiScreen customGuiScreen = (CustomGuiScreen)minecraft.currentScreen;
                ItemStack itemStack = customGuiScreen.container.getSlot(8).getStack();
                boolean enabled = false;

                for (int i = 0; i < customGuiScreen.container.itemList2.size(); i++) {
                    ItemStack itemStack2 = customGuiScreen.container.getSlot(i).getStack();
                    double offset = (double)i;
                    if (offset == this.settingChestslot.getValue() && itemStack2 != null && this.settingMode.getSelected().equals("Kitpvp")) {
                        this.processAction3(i);
                        break;
                    }

                    if (itemStack2 != null) {
                        boolean enabled2 = false;
                        String selected = this.settingMode.getSelected();
                        switch (selected) {
                            case "MinemenUnranked":
                                String text = itemStack2.getString();
                                if (text.contains(this.settingGamemodeMMC.getSelected())) {
                                    PrintStream out = System.out;
                                    StringBuilder sb = new StringBuilder().append(itemStack2).append(" ");
                                    out.println(sb.append(this.settingGamemodeMMC.getSelected()).toString());
                                    this.processAction3(i);
                                    enabled2 = true;
                                }
                                break;
                            case "MinemenRanked":
                                String text2 = itemStack2.getString();
                                if (text2.contains(this.settingGamemodeMMC.getSelected())) {
                                    this.processAction3(i);
                                    enabled2 = true;
                                }
                                break;
                            case "GamsterKitpvp":
                                if (itemStack2.getItem() == Items.diamondSword) {
                                    this.processAction3(i);
                                    enabled2 = true;
                                }
                                break;
                            case "PvpDojo":
                                if (itemStack2.getItem() == Items.stoneSword || itemStack2.getItem() == Items.netherStar) {
                                    this.processAction3(i);
                                    enabled2 = true;
                                }
                                break;
                            case "AutoGamblePVPDojo":
                                if (itemStack2.getString().contains("Collection") && i < 36) {
                                    enabled = true;
                                    this.processAction3(i);
                                }
                        }

                        if (enabled2) {
                            break;
                        }
                    }
                }

                if (this.settingMode.getSelected().equals("AutoGamblePVPDojo") && !enabled && itemStack != null && itemStack.getItem().equals(Items.sign)) {
                    this.expanded2 = true;
                }
            }
        }
    }

    private boolean isGamemodeGamsterVisible() {
        return this.settingMode.getSelected().equals("Gamster");
    }

    private boolean checkString(String text) {
        String selected = this.settingGamemodeKit1v1.getSelected();
switch (selected) {
            case "UHC":
                return text.equals("§7Kit: §f§eUHC");
            case "OnlySword":
                return text.equals("§7Kit: §f§bOnly Sword");
            case "Soup":
                return text.equals("§7Kit: §f§cSOUP");
            case "SG":
                return text.equals("§7Kit: §f§aSG");
            case "SW":
                return text.equals("§7Kit: §f§4SW");
            case "QSG":
                return text.equals("§6Season-Kit: §f§5QSG");
            default:
                return false;
        }
    }

    private boolean isGamemodeMMCVisible() {
        return this.settingMode.getSelected().equals("MinemenUnranked") || this.settingMode.getSelected().equals("MinemenRanked");
    }

    private void processAction3(int index) {
        CustomGuiScreen customGuiScreen = (CustomGuiScreen)minecraft.currentScreen;
        Slot slot = customGuiScreen.container.getSlot(index);
        System.out.println(slot.getStack());

        try {
            customGuiScreen.processAction(slot.indexOffset + 2 + customGuiScreen.indexOffset1, slot.indexOffset2 + 2 + customGuiScreen.indexOffset2, 0);
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }

        this.timer.reset();
    }

    private void applyItemStack(ItemStack itemStack, Item item) {
        if (Items.paper.equals(item)) {
            if (itemStack.getString().contains("Play")) {
                if (itemStack.getString().contains("Again")) {
                    String text = (String)this.entryMap.get(this.settingGamemodeGamster.getSelected());
                    if (text != null) {
                        RayTraceUtil.applyString(text);
                    }
                }
            }
        }
    }

    private boolean isGamemodeKit1v1Visible() {
        return this.settingMode.getSelected().equals("GommeKit1v1Ranked")
            || this.settingMode.getSelected().equals("GommeKit1v1Unranked");
    }

    private boolean isChestslotVisible() {
        return this.settingMode.getSelected().equalsIgnoreCase("Kitpvp");
    }
}
