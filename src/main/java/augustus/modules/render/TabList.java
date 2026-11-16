package augustus.modules.render;

import client.ClientGlobals;
import client.event.RenderListener;
import client.gui.TexturesGuiIconsPngManager;
import client.module.Category;
import client.module.Module;
import client.network.NetworkPlayerInfo;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.ClientSettingsBlackType;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.ShaderOptions;
import client.setting.TabListCapeType;
import client.setting.TabListIntegerType;
import client.setting.WorldSettingsGameType;
import client.util.BooleanSupplier;
import client.util.MovementHandler;
import client.util.RunnableTask;
import client.util.ScoreboardData;
import client.util.ThePlayerNameManager;
import com.google.common.collect.Ordering;
import com.mojang.authlib.GameProfile;
import java.awt.Color;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.NetHandlerPlayClient;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TabList extends Module implements RenderListener {
    public BooleanSetting settingRemove;
    public NumberSetting settingScale;
    public BooleanSetting settingPingNumber;
    public BooleanSetting settingAlwaysOpen;
    public NumberSetting settingRowSize;
    public NumberSetting settingColumnSize;
    public ShaderOptions settingColumnSize2;

    public TabList() {
        super("TabList", "TabulaturListe", new Color(162, 250, 115), Category.RENDER);
        this.settingRemove = new BooleanSetting(this, "Remove", false);
        this.settingScale = new NumberSetting(this, "Scale", 2.0, 1.0, 4.0, 2).getNumberSetting(() -> this.isScaleVisible3());
        this.settingPingNumber = (BooleanSetting)new BooleanSetting(this, "PingNumber", false).getSetting(() -> this.isScaleVisible());
        this.settingAlwaysOpen = (BooleanSetting)new BooleanSetting(this, "AlwaysOpen", false).getSetting(() -> this.isScaleVisible2());
        this.settingRowSize = new NumberSetting(this, "RowSize", 80.0, 10.0, 200.0, 1).getNumberSetting(() -> this.isRowSizeVisible());
        this.settingColumnSize = new NumberSetting(this, "ColumnSize", 20.0, 1.0, 200.0, 1).getNumberSetting(() -> this.isColumnSizeVisible2());
        ShaderOptions shaderOptions = new ShaderOptions(this);
        Color black = Color.black;
        this.settingColumnSize2 = (ShaderOptions)shaderOptions.getShaderOptions(black).getSetting(() -> this.isColumnSizeVisible());
    }

    @Override
    public void onEnable() {
        this.registerEvent(32);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        if ((minecraft.gameSettings.keyBindPlayerList.isKeyDown() || this.settingAlwaysOpen.isEnabled())
        && !minecraft.isGamePaused()
        && !minecraft.thePlayer.netHandlerPlayClient.getCollection().isEmpty()
        && !ClientGlobals.moduleManager.antiStrike.canExecute7()
        && (!ClientGlobals.moduleManager.tabList.isToggled() || !ClientGlobals.moduleManager.tabList.settingRemove.isEnabled())) {
            GL11.glPushMatrix();
            ScaledResolution scaledResolution2 = scaledResolution;
            int scaledWidth = this.getScaledResolution(scaledResolution2).getScaledWidth();
            ThePlayerNameManager thePlayerNameManager = minecraft.theWorld.getThePlayerNameManager();
            ScoreboardData customItemEntry = thePlayerNameManager.getCustomItemEntry(0);
            NetHandlerPlayClient netHandlerPlayClient = minecraft.thePlayer.netHandlerPlayClient;
            Ordering ordering = GuiPlayerTabOverlay.ordering;
            Collection collection = netHandlerPlayClient.getCollection();
            List list = ordering.sortedCopy(collection);
            Iterator iterator = list.iterator();
            int index8 = 0;
            int index9 = 0;

            while (iterator.hasNext()) {
                Object object = iterator.next();
                NetworkPlayerInfo networkPlayerInfo = (NetworkPlayerInfo)object;
                FontRenderer fontRendererObj = minecraft.fontRendererObj;
                StringBuilder stringBuilder = new StringBuilder();
                String text = GuiPlayerTabOverlay.getText(networkPlayerInfo);
                StringBuilder sb = stringBuilder.append(text).append(" ");
                int indexOffset3 = networkPlayerInfo.getIndexOffset3();
                String text2 = sb.append(indexOffset3).toString();
                int index = fontRendererObj.getIndex(text2);
                int index10 = Math.max(index8, index);
                int index11;
                if (customItemEntry == null) {
                    index11 = index9;
                } else {
                    TabListIntegerType tabListIntegerTypeKind = customItemEntry.getTabListIntegerType();
                    TabListIntegerType tabListIntegerTypeKind2 = TabListIntegerType.HEARTS;
                    if (tabListIntegerTypeKind == tabListIntegerTypeKind2) {
                        index11 = index9;
                    } else {
                        FontRenderer fontRendererObj2 = minecraft.fontRendererObj;
                        StringBuilder sb2 = new StringBuilder().append(" ");
                        String name = networkPlayerInfo.getGameProfile().getName();
                        int index2 = thePlayerNameManager.getComparatorEntry(name, customItemEntry).getIndex();
                        String text3 = sb2.append(index2).toString();
                        int index3 = fontRendererObj2.getIndex(text3);
                        index11 = Math.max(index9, index3);
                    }
                }

                index8 = index10;
                index9 = index11;
            }

            int size = list.size();
            int rowSize = (int)this.settingRowSize.getValue();
            int index12 = Math.min(size, rowSize);
            List list2 = list.subList(0, index12);
            int size2 = list2.size();
            int columnSize = (int)this.settingColumnSize.getValue();
            int index13 = index9;
            int index14 = size2;

            int index15;
            for (index15 = 1; index14 > columnSize; index14 = (size2 + index15 - 1) / index15) {
                index15++;
            }

            boolean enabled;
            int index16;
            int index17;
            if (!minecraft.isGamePaused() && !minecraft.getNetHandlerPlayClient().getNetworkManager().canExecute()) {
                index17 = index8;
                index13 = index9;
                index16 = index15;
                enabled = false;
            } else {
                index17 = index8;
                index16 = index15;
                enabled = true;
            }

            int index18;
            if (customItemEntry != null) {
                index18 = index14;
                TabListIntegerType tabListIntegerTypeKind3 = customItemEntry.getTabListIntegerType();
                TabListIntegerType tabListIntegerTypeKind4 = TabListIntegerType.HEARTS;
                if (tabListIntegerTypeKind3 == tabListIntegerTypeKind4) {
                    index13 = 90;
                }
            } else {
                index18 = index14;
                index13 = 0;
            }

            byte state;
            if (enabled) {
                state = 9;
            } else {
                state = 0;
            }

            int index19 = state + index17 + index13 + 13;
            int index20 = index16 * index19;
            int index21 = scaledWidth - 50;
            int index22 = Math.min(index20, index21) / index16;
            int index23 = scaledWidth / 2;
            int index24 = index22 * index16;
            int index25 = (index16 - 1) * 5;
            int index26 = (index24 + index25) / 2;
            int index27 = index23 - index26;
            int index28 = index22 * index16;
            int index29 = (index16 - 1) * 5;
            int index30 = index28 + index29;
            List list3 = null;
            IChatComponent chatComponent2 = minecraft.ingameGUI.getOrderingEntry().getChatComponent2();
            IChatComponent chatComponent = minecraft.ingameGUI.getOrderingEntry().getChatComponent();
            List list4;
            if (chatComponent2 == null) {
                list4 = null;
            } else {
                FontRenderer fontRendererObj3 = minecraft.fontRendererObj;
                String text11 = chatComponent2.getString2();
                int index31 = scaledWidth - 50;
                list4 = fontRendererObj3.getList(text11, index31);
                Iterator iterator2 = list4.iterator();

                while (iterator2.hasNext()) {
                    String text4 = (String)iterator2.next();
                    int index4 = minecraft.fontRendererObj.getIndex(text4);
                    index30 = Math.max(index30, index4);
                }

            }

            if (chatComponent != null) {
                FontRenderer fontRendererObj4 = minecraft.fontRendererObj;
                String text12 = chatComponent.getString2();
                int index32 = scaledWidth - 50;
                List list5 = fontRendererObj4.getList(text12, index32);
                Iterator iterator3 = list5.iterator();

                while (iterator3.hasNext()) {
                    String text5 = (String)iterator3.next();
                    int index5 = minecraft.fontRendererObj.getIndex(text5);
                    index30 = Math.max(index30, index5);
                }

                list3 = list5;
            }

            boolean enabled2 = this.settingColumnSize2.canExecute();
            boolean enabled3 = this.settingColumnSize2.canExecute2();
            MovementHandler.processBoolean(enabled2, enabled3);
            MovementHandler.processAction11();
            List list6 = list4;
            List list7 = list4;
            List list8 = list3;
            int index33 = index18;
            int index34 = index30;
            MovementHandler.applyRunnableTask(false, renderPass -> ((TabList)this)
            .applyList3(scaledWidth, index34, 10, index33, (List)list8, (List)list7, index27, index22, size2, (List)list6, renderPass));
            int index35;
            if (list4 == null) {
                index35 = 10;
            } else {
                int index36 = scaledWidth / 2;
                int index37 = index30 / 2;
                int index38 = index36 - index37 - 1;
                int index39 = scaledWidth / 2;
                int index40 = index30 / 2;
                int index41 = index39 + index40 + 1;
                int size3 = list4.size();
                int indexOffset = minecraft.fontRendererObj.indexOffset;
                int index42 = size3 * indexOffset;
                int index43 = 10 + index42;
                RenderUtils.processAction(index38, 9, index41, index43, Integer.MIN_VALUE);
                Iterator iterator4 = list4.iterator();
                int index44 = 10;

                while (iterator4.hasNext()) {
                    String text6 = (String)iterator4.next();
                    int index6 = minecraft.fontRendererObj.getIndex(text6);
                    FontRenderer fontRendererObj5 = minecraft.fontRendererObj;
                    int index45 = scaledWidth / 2;
                    int index46 = index6 / 2;
                    float value = (float)(index45 - index46);
                    float value2 = (float)index44;
                    fontRendererObj5.getIndex2(text6, value, value2, -1);
                    int indexOffset2 = minecraft.fontRendererObj.indexOffset;
                    index44 += indexOffset2;
                }

                index35 = index44 + 1;
            }

            int index47 = scaledWidth / 2;
            int index48 = index30 / 2;
            int index49 = index47 - index48 - 1;
            int index50 = index35 - 1;
            int index51 = scaledWidth / 2;
            int index52 = index30 / 2;
            int index53 = index51 + index52 + 1;
            int index54 = index18 * 9;
            int index55 = index35 + index54;
            RenderUtils.processAction(index49, index50, index53, index55, Integer.MIN_VALUE);
            int index56 = index17;
            int index57 = size2;
            int index58 = index13;
            int index59 = 0;

            while (index59 < index57) {
                int index60 = index59 / index18;
                int index61 = index59 % index18;
                int index62 = index60 * index22;
                int index63 = index27 + index62;
                int index64 = index60 * 5;
                int index65 = index63 + index64;
                int index66 = index61 * 9;
                int index67 = index35 + index66;
                int index68 = index65 + index22;
                int index69 = index67 + 8;
                RenderUtils.processAction(index65, index67, index68, index69, 553648127);
                GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
                GlStateManager.applyState14();
                GlStateManager.disableBlend();
                GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
                int size4 = list2.size();
                int index70;
                int index71;
                int index72;
                if (index59 >= size4) {
                    index72 = index18;
                    index70 = index35;
                    index71 = index59;
                } else {
                    NetworkPlayerInfo networkPlayerInfo2 = (NetworkPlayerInfo)list2.get(index59);
                    String text7 = GuiPlayerTabOverlay.getText(networkPlayerInfo2);
                    GameProfile gameProfile = networkPlayerInfo2.getGameProfile();
                    String text8;
                    List list9;
                    int index73;
                    int index74;
                    int index75;
                    int index76;
                    int index77;
                    if (!enabled) {
                        list9 = list2;
                        index73 = index18;
                        index76 = index58;
                        index77 = index35;
                        index75 = index30;
                        index74 = index59;
                        text8 = text7;
                    } else {
                        EntityPlayer player = null;
                        boolean enabled4 = false;
                        boolean completed = false;
                        UUID id = gameProfile.getId();
                        player = minecraft.theWorld.getPlayerEntityByUUID(id);
                        if (player != null) {
                            TabListCapeType tabListCapeTypeKind = TabListCapeType.CAPE;
                            if (player.checkTabListCapeType(tabListCapeTypeKind) && (gameProfile.getName().equals("Dinnerbone") || gameProfile.getName().equals("Grumm"))) {
                                enabled4 = true;
                                completed = true;
                            }
                        }
                        if (!completed) {
                            enabled4 = false;
                        }


                        TextureManager textureManager = minecraft.getTextureManager();
                        ResourceLocation resourceLocation = networkPlayerInfo2.getResourceLocation();
                        textureManager.applyResourceLocation3(resourceLocation);
                        byte state2;
                        if (enabled4) {
                            state2 = 8;
                        } else {
                            state2 = 0;
                        }

                        int index78 = 8 + state2;
                        byte state3;
                        if (enabled4) {
                            state3 = -1;
                        } else {
                            state3 = 1;
                        }

                        int index79 = 8 * state3;
                        float value3 = (float)index78;
                        TexturesGuiIconsPngManager.processAction(index65, index67, 8.0F, value3, 8, index79, 8, 8, 64.0F, 64.0F);
                        int index80;
                        if (player == null) {
                            index73 = index18;
                            index76 = index58;
                            index80 = index65;
                        } else {
                            TabListCapeType tabListCapeTypeKind2 = TabListCapeType.HAT;
                            boolean enabled5 = player.checkTabListCapeType(tabListCapeTypeKind2);
                            if (!enabled5) {
                                index73 = index18;
                                index76 = index58;
                                index80 = index65;
                            } else {
                                boolean enabled6 = enabled4;
                                byte state4 = 8;
                                int index81;
                                int index82;
                                byte state5;
                                byte state6;
                                int index83;
                                if (!enabled4) {
                                    index81 = index58;
                                    index80 = index65;
                                    index82 = index56;
                                    index83 = index67;
                                    enabled6 = enabled4;
                                    state6 = state4;
                                    state5 = 0;
                                } else {
                                    index82 = index56;
                                    index81 = index58;
                                    index80 = index65;
                                    index83 = index67;
                                    state6 = state4;
                                    state5 = 8;
                                }

                                int index84 = state6 + state5;
                                byte state7;
                                byte state8;
                                int index85;
                                int index86;
                                int index87;
                                if (!enabled6) {
                                    index85 = index57;
                                    index86 = index59;
                                    index87 = index84;
                                    state7 = 8;
                                    state8 = 1;
                                } else {
                                    index85 = index57;
                                    index86 = index59;
                                    index87 = index84;
                                    state7 = 8;
                                    state8 = -1;
                                }

                                int index88 = state7 * state8;
                                float value4 = (float)index87;
                                TexturesGuiIconsPngManager.processAction(index80, index83, 40.0F, value4, 8, index88, 8, 8, 64.0F, 64.0F);
                                index56 = index82;
                                index57 = index85;
                                index73 = index18;
                                index76 = index81;
                                index59 = index86;
                                index67 = index83;
                            }
                        }

                        int index89 = index80 + 9;
                        list9 = list2;
                        index77 = index35;
                        index75 = index30;
                        index74 = index59;
                        index65 = index89;
                        text8 = text7;
                    }

                    WorldSettingsGameType worldSettingsGameTypeKind = networkPlayerInfo2.getWorldSettingsGameType();
                    WorldSettingsGameType worldSettingsGameTypeKind2 = WorldSettingsGameType.SPECTATOR;
                    int index90;
                    List list10;
                    int index91;
                    if (worldSettingsGameTypeKind != worldSettingsGameTypeKind2) {
                        index90 = index74;
                        FontRenderer fontRendererObj6 = minecraft.fontRendererObj;
                        float value5 = (float)index65;
                        float value6 = (float)index67;
                        fontRendererObj6.getIndex2(text8, value5, value6, -1);
                        index91 = index75;
                        list10 = list3;
                    } else {
                        StringBuilder stringBuilder2 = new StringBuilder();
                        ClientSettingsBlackType clientSettingsBlackTypeKind = ClientSettingsBlackType.ITALIC;
                        String text9 = stringBuilder2.append(clientSettingsBlackTypeKind).append(text8).toString();
                        FontRenderer fontRendererObj7 = minecraft.fontRendererObj;
                        float value7 = (float)index65;
                        float value8 = (float)index67;
                        fontRendererObj7.getIndex2(text9, value7, value8, -1862270977);
                        index91 = index75;
                        list10 = list3;
                        index90 = index74;
                    }

                    NetworkPlayerInfo networkPlayerInfo3;
                    int index92;
                    if (customItemEntry == null) {
                        index92 = index77;
                        networkPlayerInfo3 = networkPlayerInfo2;
                    } else {
                        WorldSettingsGameType worldSettingsGameTypeKind3 = networkPlayerInfo2.getWorldSettingsGameType();
                        WorldSettingsGameType worldSettingsGameTypeKind4 = WorldSettingsGameType.SPECTATOR;
                        if (worldSettingsGameTypeKind3 == worldSettingsGameTypeKind4) {
                            index92 = index77;
                            networkPlayerInfo3 = networkPlayerInfo2;
                        } else {
                            int index93 = index65 + index56 + 1;
                            int index94 = index93 + index76;
                            if (index94 - index93 <= 5) {
                                index92 = index77;
                                networkPlayerInfo3 = networkPlayerInfo2;
                            } else {
                                GuiPlayerTabOverlay orderingEntry = minecraft.ingameGUI.getOrderingEntry();
                                String name2 = gameProfile.getName();
                                orderingEntry.updateCustomItemEntry(customItemEntry, index67, name2, index93, index94, networkPlayerInfo2);
                                index92 = index77;
                                networkPlayerInfo3 = networkPlayerInfo2;
                            }
                        }
                    }

                    GuiPlayerTabOverlay orderingEntry2 = minecraft.ingameGUI.getOrderingEntry();
                    int index95 = index67;
                    byte state9;
                    int index96;
                    if (!enabled) {
                        index96 = index76;
                        index95 = index67;
                        state9 = 0;
                    } else {
                        index96 = index76;
                        state9 = 9;
                    }

                    int index97 = index65 - state9;
                    orderingEntry2.applyNetworkPlayerInfo(index22, index97, index95, networkPlayerInfo3);
                    list2 = list9;
                    index72 = index73;
                    index58 = index96;
                    index70 = index92;
                    index30 = index91;
                    list3 = list10;
                    index71 = index90;
                }

                index59 = index71 + 1;
                index18 = index72;
                index35 = index70;
            }

            GlStateManager.enableBlend();
            if (list3 != null) {
                int index98 = index18 * 9;
                int index99 = index35 + index98 + 1;
                int index100 = scaledWidth / 2;
                int index101 = index30 / 2;
                int index102 = index100 - index101 - 1;
                int index103 = index99 - 1;
                int index104 = scaledWidth / 2;
                int index105 = index30 / 2;
                int index106 = index104 + index105 + 1;
                int size5 = list3.size();
                int indexOffset4 = minecraft.fontRendererObj.indexOffset;
                int index107 = size5 * indexOffset4;
                int index108 = index99 + index107;
                RenderUtils.processAction(index102, index103, index106, index108, Integer.MIN_VALUE);
                Iterator iterator5 = list3.iterator();

                while (iterator5.hasNext()) {
                    String text10 = (String)iterator5.next();
                    int index7 = minecraft.fontRendererObj.getIndex(text10);
                    FontRenderer fontRendererObj8 = minecraft.fontRendererObj;
                    int index109 = scaledWidth / 2;
                    int index110 = index7 / 2;
                    float value9 = (float)(index109 - index110);
                    float value10 = (float)index99;
                    fontRendererObj8.getIndex2(text10, value9, value10, -1);
                    int indexOffset5 = minecraft.fontRendererObj.indexOffset;
                    index99 += indexOffset5;
                }
            }

            GL11.glPopMatrix();
        }
    }

    private void applyList(int x, int x2, int x3, int x4, int x5, int y, int z, List list, List list2) {
        int index2 = x3;
        if (list2 != null) {
            RenderUtils.processAction(
            x / 2 - x2 / 2 - 1, x3 - 1, x / 2 + x2 / 2 + 1, x3 + list2.size() * minecraft.fontRendererObj.indexOffset, Integer.MIN_VALUE
            );
            index2 = x3 + minecraft.fontRendererObj.indexOffset * list2.size();
            index2++;
        }

        RenderUtils.processAction(x / 2 - x2 / 2 - 1, index2 - 1, x / 2 + x2 / 2 + 1, index2 + x4 * 9, Integer.MIN_VALUE);

        for (int i = 0; i < z; i++) {
            int index = i / x4;
            int index3 = i % x4;
            int index4 = x5 + index * y + index * 5;
            int index5 = index2 + index3 * 9;
            RenderUtils.processAction(index4, index5, index4 + y, index5 + 8, 553648127);
        }

        if (list != null) {
            index2 = index2 + x4 * 9 + 1;
            RenderUtils.processAction(
            x / 2 - x2 / 2 - 1, index2 - 1, x / 2 + x2 / 2 + 1, index2 + list.size() * minecraft.fontRendererObj.indexOffset, Integer.MIN_VALUE
            );
        }
    }

    private boolean isScaleVisible() {
        return !this.settingRemove.isEnabled();
    }

    private void applyList2(int x, int x2, int y, int z, List list, List list2) {
        int index = y;
        if (list2 != null) {
            RenderUtils.processAction(
            x / 2 - x2 / 2 - 1,
            y - 1,
            x / 2 + x2 / 2 + 1,
            y + list2.size() * minecraft.fontRendererObj.indexOffset,
            this.settingColumnSize2.getInt3(Color.black.getRGB())
            );
            index = y + minecraft.fontRendererObj.indexOffset * list2.size();
            index++;
        }

        int index2 = x / 2 - x2 / 2 - 1;
        int index3 = index - 1;
        int index4 = x / 2 + x2 / 2 + 1;
        int index5 = index + z * 9;
        int rGB = Color.black.getRGB();
        RenderUtils.processAction(index2, index3, index4, index5, rGB);
        if (list != null) {
            index = index + z * 9 + 1;
            int index6 = x / 2 - x2 / 2 - 1;
            int index7 = index - 1;
            int index8 = x / 2 + x2 / 2 + 1;
            int index9 = index + list.size() * minecraft.fontRendererObj.indexOffset;
            int int3 = this.settingColumnSize2.getInt3(Color.black.getRGB());
            RenderUtils.processAction(index6, index7, index8, index9, int3);
        }
    }

    private ScaledResolution getScaledResolution(ScaledResolution scaledResolution) {
        GL11.glScaled((double)(1.0F / (float)scaledResolution.getScaleFactor()), (double)(1.0F / (float)scaledResolution.getScaleFactor()), 1.0);
        GL11.glScaled(this.settingScale.getValue(), this.settingScale.getValue(), 1.0);
        return new ScaledResolution(
        (double)minecraft.displayWidth * (1.0 / this.settingScale.getValue()),
        (double)minecraft.displayHeight * (1.0 / this.settingScale.getValue()),
        (int)this.settingScale.getValue()
        );
    }

    private boolean isScaleVisible2() {
        return !this.settingRemove.isEnabled();
    }

    private boolean isRowSizeVisible() {
        return !this.settingRemove.isEnabled();
    }

    private void applyList3(int x, int x2, int y, int z, List list, List list2, int x3, int y2, int z2, List list3, RenderPass renderPass) {
        if (renderPass == RenderPass.SHADOW) {
            this.applyList2(x, x2, y, z, list, list2);
        } else {
            this.applyList(x, x2, y, z, x3, y2, z2, list, list3);
        }
    }

    private boolean isColumnSizeVisible() {
        return !this.settingRemove.isEnabled();
    }

    private boolean isColumnSizeVisible2() {
        return !this.settingRemove.isEnabled();
    }

    private boolean isScaleVisible3() {
        return !this.settingRemove.isEnabled();
    }
}
