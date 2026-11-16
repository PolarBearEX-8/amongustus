package client.module;

import augustus.modules.misc.ClientSettings;
import augustus.modules.misc.RotationRecorder;
import augustus.modules.misc.RotationRenderer;
import augustus.modules.render.ClickGui;
import augustus.modules.render.Notifications;
import augustus.modules.render.OldNotification;
import client.Client;
import client.ClientGlobals;
import client.event.ActiveListener;
import client.event.EventBusManager;
import client.font.CustomFont;
import client.font.FontFace;
import client.font.FontRegistry;
import client.setting.BeverageNotifyInfoType;
import client.setting.BeverageNotifyModuleTogglesType;
import client.setting.BooleanSetting;
import client.setting.EasingFunction;
import client.setting.Setting;
import client.util.AuxiliaryListener60EntryAdapter;
import client.util.AuxiliaryStringArrayEntry;
import client.util.BeverageItemNotification;
import client.util.ClientManager;
import client.util.InternalListenerCoordinator;
import client.util.KeybindAnimation;
import client.util.ListenerTracker;
import client.util.MinecraftAccessor;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.function.Consumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import org.lwjgl.input.Keyboard;

public abstract class Module extends ListenerTracker implements MinecraftAccessor, Comparable {
   public final AuxiliaryStringArrayEntry enabledProperty;
   public final AuxiliaryStringArrayEntry auxiliaryStringArrayEntry3;
   public final AuxiliaryStringArrayEntry auxiliaryStringArrayEntry2;
   public final AuxiliaryStringArrayEntry auxiliaryStringArrayEntry4;
   public final AuxiliaryStringArrayEntry auxiliaryStringArrayEntry;
   public final InternalListenerCoordinator internalListenerCoordinator;
   public String description;
   public String suffix;
   public String name;
   public String displayName;
   public Category category;
   public Color color;
   public final ArrayList settings;
   public final ArrayList itemList2;
   public FontFace fontFace;
   public String text;
   public String labelText2;
   public int indexOffset2;
   public int indexOffset1;
   public int indexOffset;
   public int keyCode;
   public KeybindAnimation keybindAnimation;
   public boolean enabled2;
   public double coordOffset;
   public double value;
   public long epochMillis;
   public boolean enabled;
   public boolean visible;
   public String labelText3;
   public int indexOffset4;
   public int indexOffset5;
   public int indexOffset3;
   public boolean expanded;
   public int count3;
   public int count4;
   public int count2;
   public int count5;
   public final BooleanSetting settingLoadFromConfig;
   public final BooleanSetting settingShowInArrayList;
   public final BooleanSetting settingToggleSound;
   public final BooleanSetting settingKeyHoldMode;

   public String getDisplayName() {
      return this.displayName;
   }

   public void setActive(boolean active) {
      this.processBoolean(active, true);
   }

   public void registerEvent(int count, int count6) {
      Setting[] settingArray = ModuleManager.getSettingArray();
      if (this.isToggled()) {
         EventBusManager.getDietrichEvents2().unsubscribe(count, this);
         if (settingArray == null) {
            return;
         }
      }

      EventBusManager.getDietrichEvents2().subscribe(count, this, count6);
   }

   public boolean isToggled() {
      return (Boolean)this.enabledProperty.getValue();
   }

   public int getIndexOffset2() {
      return this.indexOffset2;
   }

   public void setState(boolean state) {
      AuxiliaryStringArrayEntry auxiliaryStringArrayEntry5 = this.auxiliaryStringArrayEntry2;
      Boolean flag = state;
      auxiliaryStringArrayEntry5.applyObject2(flag);
      this.settingShowInArrayList.setState3(state);
   }

   public void setIndexOffset1(int indexOffset6) {
      this.indexOffset1 = indexOffset6;
   }

   public boolean isVisible() {
      return this.visible;
   }

   public String getName() {
      return this.name;
   }

   public int getIndexOffset() {
      return this.indexOffset;
   }

   private int getIndex(Color color2, double offset, int count, int count6, int count7, int count8, int count9, int count10, boolean flag) {
      Setting[] settingArray = ModuleManager.getSettingArray();
      int count11 = count7 - 2;
      int count12 = this.checkCondition(count11, count8, count9, count10, count, count6) ? 1 : 0;
      if (settingArray == null) {
         if (count12 != 0) {
            if (settingArray != null) {
               return flag ? 1 : 0;
            }

            if (!flag) {
               flowBlockA: {
                  float[] factorArray;
                  float factor;
                  scopeBlock: {
                     factorArray = Color.RGBtoHSB(color2.getRed(), color2.getGreen(), color2.getBlue(), null);
                     factor = (float)((double)factorArray[2] + offset);
                     count12 = Double.compare((double)factorArray[2] + offset, 1.0);
                     if (settingArray == null) {
                        if (count12 > 0) {
                           factor = 1.0F;
                           if (settingArray == null) {
                              break scopeBlock;
                           }
                        }

                        count12 = Double.compare((double)factorArray[2] + offset, 0.0);
                     }

                     if (settingArray != null) {
                        break flowBlockA;
                     }

                     if (count12 < 0) {
                        factor = 0.0F;
                     }
                  }

                  count12 = Color.HSBtoRGB(factorArray[0], factorArray[1], factor);
               }

               int count13 = count12;
               if (settingArray == null) {
                  return count13;
               }
            }
         }

         count12 = color2.getRGB();
      }

      return count12;
   }

   private static void updateActiveListener(ActiveListener activeListener) {
      activeListener.setEnabled(true);
   }

   private void setToggled(Boolean toggled) {
      boolean flag = toggled;
      this.processBoolean(flag, false);
   }

   public double getCoordOffset() {
      return this.coordOffset;
   }

   public boolean canExecute() {
      return (Boolean)this.auxiliaryStringArrayEntry4.getValue();
   }

   private void applyString2(String text2) {
        Module entry = null;
      String text3;
      Setting[] settingArray;
      String text4;
      int count;
      text4 = text2;
      this.labelText2 = text2;
      Setting[] settingArray2 = ModuleManager.getSettingArray();
      text3 = ClientGlobals.moduleManager.itemList.settingSuffixCasing.getSelected();
      settingArray = settingArray2;
      byte byteValue = -1;
      count = text3.hashCode();
      searchBlock:
      if (settingArray == null) {
         switch (count) {
            case -2068429102:
               count = text3.equals("UpperCase") ? 1 : 0;
               if (settingArray != null) {
                  break searchBlock;
               }

               if (count == 0) {
                  break;
               }

               byteValue = 0;
               if (settingArray == null) {
                  break;
               }
            case 1488507313:
               count = text3.equals("LowerCase") ? 1 : 0;
               if (settingArray != null) {
                  break searchBlock;
               }

               if (count != 0) {
                  byteValue = 1;
               }
         }

         count = byteValue;
      }

      switch (count) {
         case 0:
            text4 = text2.toUpperCase(Locale.ROOT);
            if (settingArray == null) {
               break;
            }
         case 1:
            text4 = text4.toLowerCase(Locale.ROOT);
      }

      checkBlock: {
         Module module = this;
         if (settingArray == null) {
            text3 = this.text;
            if (minecraft.thePlayer != null) {
               String selected = ClientGlobals.moduleManager.itemList.settingSuffix.getSelected();
               byte byteValue2 = -1;
               count = selected.hashCode();
               guardBlock:
               if (settingArray == null) {
                  switch (count) {
                     case 2433880:
                        count = selected.equals("None") ? 1 : 0;
                        if (settingArray != null) {
                           break guardBlock;
                        }

                        if (count == 0) {
                           break;
                        }

                        byteValue2 = 0;
                        if (settingArray == null) {
                           break;
                        }
                     case -1818419758:
                        count = selected.equals("Simple") ? 1 : 0;
                        if (settingArray != null) {
                           break guardBlock;
                        }

                        if (count == 0) {
                           break;
                        }

                        byteValue2 = 1;
                        if (settingArray == null) {
                           break;
                        }
                     case 2122674:
                        count = selected.equals("Dash") ? 1 : 0;
                        if (settingArray != null) {
                           break guardBlock;
                        }

                        if (count == 0) {
                           break;
                        }

                        byteValue2 = 2;
                        if (settingArray == null) {
                           break;
                        }
                     case 1802060936:
                        count = selected.equals("Bracket") ? 1 : 0;
                        if (settingArray != null) {
                           break guardBlock;
                        }

                        if (count == 0) {
                           break;
                        }

                        byteValue2 = 3;
                        if (settingArray == null) {
                           break;
                        }
                     case 65290047:
                        count = selected.equals("Colon") ? 1 : 0;
                        if (settingArray != null) {
                           break guardBlock;
                        }

                        if (count == 0) {
                           break;
                        }

                        byteValue2 = 4;
                        if (settingArray == null) {
                           break;
                        }
                     case 2029746065:
                        count = selected.equals("Custom") ? 1 : 0;
                        if (settingArray != null) {
                           break guardBlock;
                        }

                        if (count != 0) {
                           byteValue2 = 5;
                        }
                  }

                  count = byteValue2;
               }

               switch (count) {
                  case 0:
                     this.text = "";
                     if (settingArray == null) {
                        break checkBlock;
                     }
                  case 1:
                     this.text = " " + text4;
                     if (settingArray == null) {
                        break checkBlock;
                     }
                  case 2:
                     this.text = " - " + text4;
                     if (settingArray == null) {
                        break checkBlock;
                     }
                  case 3:
                     this.text = " [" + text4 + "]";
                     if (settingArray == null) {
                        break checkBlock;
                     }
                  case 4:
                     this.text = " : " + text4;
                     if (settingArray == null) {
                        break checkBlock;
                     }
                  case 5:
                     this.text = " " + ClientGlobals.moduleManager.itemList.settingCustomSuffix.getSelected() + " " + text4;
                  default:
                     break checkBlock;
               }
            }

            module = this;
         }

         module.text = "";
      }

      branchBlock: {
         scanBlock: {
            evalBlock: {
               boolean flag = this.text.equalsIgnoreCase(text3);
               if (settingArray == null) {
                  if (!flag) {
                     break evalBlock;
                  }

                  entry = this;
                  if (settingArray != null) {
                     break branchBlock;
                  }

                  flag = this.labelText3.equals(ClientGlobals.moduleManager.itemList.settingSuffixCasing.getSelected());
               }

               if (flag) {
                  break scanBlock;
               }
            }

            ClientGlobals.moduleManager.updateState();
         }

         entry = this;
      }

      entry.labelText3 = ClientGlobals.moduleManager.itemList.settingSuffixCasing.getSelected();
   }

   public void setIndexOffset2(int indexOffset6) {
      this.indexOffset2 = indexOffset6;
   }

   public int getIndexOffset4() {
      return this.indexOffset4;
   }

   public void applyJsonObject(JsonObject jsonObject) {
      Module module;
      searchBlock: {
         Setting[] settingArray = ModuleManager.getSettingArray();
         boolean value2 = (Boolean)this.auxiliaryStringArrayEntry3.getValue();
         if (settingArray == null) {
            if (!value2) {
               return;
            }

            module = this;
            if (settingArray != null) {
               break searchBlock;
            }

            value2 = this instanceof ClickGui;
         }

         if (value2) {
            return;
         }

         module = this;
      }

      boolean asBoolean = jsonObject.get("Toggled").getAsBoolean();
      module.processBoolean(asBoolean, false);
   }

   public void setState2(boolean state) {
      AuxiliaryStringArrayEntry auxiliaryStringArrayEntry5 = this.auxiliaryStringArrayEntry4;
      Boolean flag = state;
      auxiliaryStringArrayEntry5.applyObject2(flag);
      this.settingToggleSound.setState3(state);
   }

   public String getDescription() {
      return this.description;
   }

   public ArrayList getSettings() {
      return this.settings;
   }

   public int getKeyCode() {
      return this.keyCode;
   }

   public Color getColor() {
      return this.color;
   }

   public String getSuffix() {
      return this.suffix;
   }

   public void processAction(int count, int count6, int count7) throws IOException {
      scanBlock: {
         Setting[] settingArray;
         boolean flag;
         settingArray = ModuleManager.getSettingArray();
         int count8 = this.count5;
         int count9 = this.count2;
         int count10 = this.count4;
         int count11 = this.count3;
         flag = this.checkCondition(count11, count10, count9, count8, count, count6);
         searchBlock:
         if (settingArray == null) {
            if (flag) {
               flag = this.expanded;
               if (settingArray != null) {
                  break searchBlock;
               }

               if (!this.expanded) {
                  this.expanded = true;
                  if (settingArray == null) {
                     break scanBlock;
                  }
               }
            }

            flag = this.expanded;
         }

         if (settingArray != null) {
            return;
         }

         if (flag) {
            evalBlock: {
               checkBlock: {
                  byte byteValue;
                  int flagI;
                  guardBlock: {
                     int count12 = this.count2 + 20;
                     int count13 = this.count5;
                     int count14 = count12;
                     int count15 = this.count4;
                     int count16 = this.count3;
                     flagI = this.checkCondition(count16, count15, count14, count13, count, count6) ? 1 : 0;
                     if (settingArray == null) {
                        if (flagI != 0) {
                           flagI = count7;
                           if (settingArray == null) {
                              if (count7 == 0) {
                                 break checkBlock;
                              }

                              flagI = count7;
                           }

                           byteValue = 1;
                           if (settingArray != null) {
                              break guardBlock;
                           }

                           if (flagI == 1) {
                              break checkBlock;
                           }
                        }

                        flagI = count7;
                     }

                     byteValue = 1;
                  }

                  if (flagI <= byteValue) {
                     break evalBlock;
                  }
               }

               int count17 = count7 + 10000;
               this.unregisterEvent(count17);
            }

            this.expanded = false;
         }
      }

      this.settingLoadFromConfig.checkIndex(count, count6, count7);
      this.settingShowInArrayList.checkIndex(count, count6, count7);
      this.settingToggleSound.checkIndex(count, count6, count7);
      this.settingKeyHoldMode.checkIndex(count, count6, count7);
   }

   public boolean canExecute2() {
      Setting[] settingArray = ModuleManager.getSettingArray();
      boolean value2 = (Boolean)this.auxiliaryStringArrayEntry2.getValue();
      if (settingArray == null) {
         if (!value2) {
            return false;
         }

         value2 = this.enabled2;
      }

      return settingArray != null ? value2 : !value2;
   }

   public void setActive2(boolean active) {
      this.enabledProperty.setEnabled(active);
   }

   public int getIndexOffset1() {
      return this.indexOffset1;
   }

   public void setExpanded(boolean expanded2) {
      this.expanded = expanded2;
   }

   private void updateActiveListener2(ActiveListener activeListener) {
      Setting[] settingArray = ModuleManager.getSettingArray();
      boolean toggled = this.isToggled();
      if (settingArray == null) {
         toggled = !toggled;
      }

      boolean flag = toggled;
      activeListener.setEnabled(flag);
   }

   public boolean canExecute3() {
      return (Boolean)this.auxiliaryStringArrayEntry.getValue();
   }

   public void setColor(Color color2) {
      this.color = color2;
   }

   public void processChar2(char character, int count) throws IOException {
      Setting[] settingArray = ModuleManager.getSettingArray();
      Module module = this;
      if (settingArray == null) {
         if (!this.expanded) {
            return;
         }

         module = this;
      }

      int count6 = count;
      if (settingArray == null) {
         count6 = count == 1 ? 0 : count;
      }

      int count7 = count6;
      module.unregisterEvent(count7);
      this.expanded = false;
   }

   public int getIndexOffset5() {
      return this.indexOffset5;
   }

   public void setDisplayName(String displayName2) {
      this.displayName = displayName2;
   }

   public int getIndex(Module module) {
      Setting[] settingArray = ModuleManager.getSettingArray();
      int count = Client.getClient().getModuleManager().itemList.settingSorting.getSelected().equals("Alphabet") ? 1 : 0;
      if (settingArray == null) {
         if (count != 0) {
            return -module.description.compareTo(this.description);
         }

         count = module.indexOffset - this.indexOffset;
      }

      return count;
   }

   public void unregisterEvent(int count) {
      InternalListenerCoordinator internalListenerCoordinator2 = this.internalListenerCoordinator;
      Integer count6 = count;
      internalListenerCoordinator2.applyObject2(count6);
   }

   public ArrayList getItemList2() {
      return this.itemList2;
   }

   public void processBoolean(boolean flag, boolean flag2) {
      Module module;
      searchBlock: {
         Setting[] settingArray = ModuleManager.getSettingArray();
         boolean enabled3 = this.enabled2;
         if (settingArray == null) {
            if (this.enabled2) {
               AuxiliaryStringArrayEntry enabledProperty2 = this.enabledProperty;
               Boolean flag3 = true;
               enabledProperty2.applyObject2(flag3);
               return;
            }

            module = this;
            if (settingArray != null) {
               break searchBlock;
            }

            enabled3 = (Boolean)this.enabledProperty.getValue();
         }

         if (enabled3 == flag) {
            return;
         }

         module = this;
      }

      module.setActive3(flag2);
   }

   public void setEnabled(boolean enabled3) {
      this.enabled = enabled3;
   }

   public String getString2() {
      return this.suffix + this.text;
   }

   public boolean canExecute4() {
      return (Boolean)this.auxiliaryStringArrayEntry3.getValue();
   }

   public void updateEasingFunction(int count, EasingFunction easingFunction, int count6) {
   }

   public void registerEvent(int count) {
      this.registerEvent(count, 0);
   }

   public void applyString3(String text2) {
      this.description = text2;
      this.onLoad();
   }

   public void applyJsonObject2(JsonObject jsonObject) {
      flowBlockC: {
         flowBlockB: {
            Setting[] settingArray = ModuleManager.getSettingArray();
            Module module = this;
            if (settingArray == null) {
               if (!(this instanceof ClickGui)) {
                  boolean asBoolean = jsonObject.get("Toggled").getAsBoolean();
                  this.setActive(asBoolean);
               }

               try {
                  module = this;
               } catch (Exception exception) {
                  break flowBlockB;
               }
            }

            try {
               int asInt = jsonObject.get("Key").getAsInt();
               module.unregisterEvent(asInt);
               break flowBlockC;
            } catch (Exception exception2) {
            }
         }

         System.err.println("Error loading Key from module " + this.getDescription());
      }

      try {
         boolean asBoolean2 = jsonObject.get("IsInConfig").getAsBoolean();
         this.setState4(asBoolean2);
      } catch (Exception exception3) {
         System.err.println("Error loading IsInConfig from module " + this.getDescription());
      }

      try {
         boolean asBoolean3 = jsonObject.get("IsInArraylist").getAsBoolean();
         this.setState(asBoolean3);
      } catch (Exception exception4) {
         System.err.println("Error loading IsInArraylist from module " + this.getDescription());
      }

      try {
         boolean asBoolean4 = jsonObject.get("IsToggleSound").getAsBoolean();
         this.setState2(asBoolean4);
      } catch (Exception exception5) {
         System.err.println("Error loading IsToggleSound from module " + this.getDescription());
      }

      try {
         boolean asBoolean5 = jsonObject.get("OnlyActiveWhileKeyPressed").getAsBoolean();
         this.setState3(asBoolean5);
      } catch (Exception exception6) {
         System.err.println("Error loading OnlyActiveWhileKeyPressed from module " + this.getDescription());
      }
   }

   public void setState3(boolean state) {
      AuxiliaryStringArrayEntry auxiliaryStringArrayEntry5 = this.auxiliaryStringArrayEntry;
      Boolean flag = state;
      auxiliaryStringArrayEntry5.applyObject2(flag);
      this.settingKeyHoldMode.setState3(state);
   }

   private boolean checkCondition(int count, int count6, int count7, int count8, int count9, int count10) {
      Setting[] settingArray = ModuleManager.getSettingArray();
      int count11 = count9;
      int count12 = count;
      if (settingArray == null) {
         if (count9 < count) {
            return false;
         }

         count11 = count10;
         count12 = count6;
      }

      if (settingArray == null) {
         if (count11 < count12) {
            return false;
         }

         count11 = count9;
         count12 = count + count7;
      }

      if (settingArray == null) {
         if (count11 >= count12) {
            return false;
         }

         count11 = count10;
         if (settingArray != null) {
            return count10 != 0;
         }

         count12 = count6 + count8;
      }

      return (count11 < count12);
   }

   public void applyString4(String text2) {
      Setting[] settingArray = ModuleManager.getSettingArray();
      if (!Minecraft.getMinecraft().isSingleplayer()) {
         ClientManager.applyRunnable2(() -> this.applyString5(text2));
         if (settingArray == null) {
            return;
         }
      }

      this.applyString2(text2);
   }

   public String getText() {
      return this.text;
   }

   public void setActive3(boolean active) {
        java.lang.Object entry = null;
        java.lang.Object entry2 = null;
        boolean flag = false;
      Setting[] settingArray = ModuleManager.getSettingArray();
      int enabled3 = this.enabledProperty.isEnabled() ? 1 : 0;
      if (settingArray == null) {
         if (enabled3 != 0) {
            return;
         }

         enabled3 = this.enabled2 ? 1 : 0;
      }

      if (settingArray == null) {
         if (enabled3 != 0) {
            AuxiliaryStringArrayEntry enabledProperty2 = this.enabledProperty;
            Boolean flag2 = false;
            enabledProperty2.applyObject2(flag2);
            this.onEnable();
            this.itemList2.forEach(o -> updateActiveListener((ActiveListener)o));
            AuxiliaryStringArrayEntry enabledProperty3 = this.enabledProperty;
            Boolean flag3 = true;
            enabledProperty3.applyObject2(flag3);
            return;
         }

         enabled3 = this.isToggled() ? 1 : 0;
      }

      if (settingArray == null) {
         if (enabled3 == 0) {
            this.epochMillis = System.currentTimeMillis();
         }

         this.onEnable();
         this.itemList2.forEach(o -> this.updateActiveListener2((ActiveListener)o));
         enabled3 = 100;
      }

      int count;
      EasingFunction easingFunction;
      searchBlock: {
         count = enabled3;
         easingFunction = EasingFunction.easeInOutBack;
         ModuleManager moduleManager = Client.getClient().getModuleManager();
         if (settingArray == null) {
            if (moduleManager == null) {
               break searchBlock;
            }

            easingFunction = KeybindAnimation.getEasingFunction(Client.getClient().getModuleManager().itemList.settingToggleAnimation.getSelected());
            moduleManager = Client.getClient().getModuleManager();
         }

         count = (int)moduleManager.itemList.settingAnimationTime.getValue();
      }

      evalBlock: {
         Module module = this;
         if (settingArray == null) {
            if (this.isToggled()) {
               AuxiliaryStringArrayEntry enabledProperty4 = this.enabledProperty;
               Boolean flag4 = false;
               enabledProperty4.applyObject2(flag4);
               if (settingArray == null) {
                  guardBlock: {
                     if (minecraft.theWorld != null) {
                        entry = Client.getClient().getModuleManager().clientSettings;
                        if (settingArray != null) {
                           break guardBlock;
                        }

                        if (((ClientSettings)entry).settingModuleToggleSound.isEnabled()) {
                           entry = this;
                           if (settingArray != null) {
                              break guardBlock;
                           }

                           if (this.canExecute()) {
                              ClientManager.applyRunnable(ClientSettings::updateState);
                           }
                        }
                     }

                     this.onDisable();
                     entry = this;
                  }

                  ((Module)entry).keybindAnimation.updateEasingFunction(count, easingFunction, 0, false);
               }

               if (settingArray == null) {
                  break evalBlock;
               }
            }

            module = this;
         }

         checkBlock: {
            AuxiliaryStringArrayEntry enabledProperty5 = module.enabledProperty;
            Boolean flag5 = true;
            enabledProperty5.applyObject2(flag5);
            if (settingArray == null) {
               if (minecraft.theWorld != null) {
                  entry2 = Client.getClient().getModuleManager().clientSettings;
                  if (settingArray != null) {
                     break checkBlock;
                  }

                  if (((ClientSettings)entry2).settingModuleToggleSound.isEnabled()) {
                     entry2 = this;
                     if (settingArray != null) {
                        break checkBlock;
                     }

                     if (this.canExecute()) {
                        ClientManager.applyRunnable(ClientSettings::updateState2);
                     }
                  }
               }

               this.onPreDisable();
            }

            entry2 = this;
         }

         ((Module)entry2).keybindAnimation.updateEasingFunction(count, easingFunction, 1, false);
      }

      EntityPlayerSP player = minecraft.thePlayer;
      if (settingArray == null) {
         scanBlock:
         if (minecraft.thePlayer != null) {
            OldNotification oldNotification = Client.getClient().getModuleManager().oldNotification;
            if (settingArray == null) {
               if (!((Module)oldNotification).isToggled()) {
                  break scanBlock;
               }

               oldNotification = Client.getClient().getModuleManager().oldNotification;
            }

            oldNotification.applyModule2(this);
         }

         player = minecraft.thePlayer;
      }

      tryBlock: {
         if (player != null) {
            flag = this instanceof ClickGui;
            if (settingArray != null) {
               break tryBlock;
            }

            branchBlock:
            if (!flag) {
               Notifications notifications = Client.getClient().getModuleManager().notifications;
               if (settingArray == null) {
                  if (notifications == null) {
                     break branchBlock;
                  }

                  notifications = Client.getClient().getModuleManager().notifications;
               }

               flag = ((Module)notifications).isToggled();
               if (settingArray != null) {
                  break tryBlock;
               }

               if (flag) {
                  flag = BeverageNotifyModuleTogglesType.MODULE_TOGGLES.isSelected();
                  if (settingArray != null) {
                     break tryBlock;
                  }

                  if (flag) {
                     String description2 = this.getDescription();
                     String text2 = this.isToggled() ? "§aenabled" : "§cdisabled";
                     BeverageNotifyInfoType beverageNotifyInfoType = BeverageNotifyInfoType.INFO;
                     String text3 = text2;
                     BeverageItemNotification.updateText(description2, text3, beverageNotifyInfoType);
                  }
               }
            }
         }

         flag = active;
      }

      if (flag) {
         Client.getClient().getModuleManager().updateState();
      }
   }

   public int getIndexOffset3() {
      return this.indexOffset3;
   }

   public int getIndex(int count, int count6, int count7, char character, int count8, int count9, char character2, FontFace fontFace2, boolean flag) {
      String text2;
      StringBuilder builder;
      flowBlockD: {
         short shortValue = 23215;
         short shortValue2 = 21628;
         char character3 = '\u8464';
         Setting[] settingArray = ModuleManager.getSettingArray();
         builder = new StringBuilder().append("Key: ");
         int expanded2 = this.expanded ? 1 : 0;
         if (settingArray == null) {
            if (this.expanded) {
               text2 = "...";
               break flowBlockD;
            }

            expanded2 = this.getInt();
         }

         if (settingArray == null) {
            if (expanded2 >= 10000) {
               text2 = "Mouse" + (this.getInt() - 10000);
               break flowBlockD;
            }

            expanded2 = this.getInt();
         }

         text2 = Keyboard.getKeyName(expanded2);
      }

      String text3 = builder.append(text2).toString();
      this.count3 = count - 1;
      this.count4 = count6;
      this.count5 = (int)fontFace2.getFontSize() + 1;
      this.count2 = (int)fontFace2.getScaleFactor(text3) + 4;
      double offset = (double)count;
      double offset2 = (double)count6;
      Color color2 = new Color(185, 187, 190);
      int count10 = this.count5;
      int count11 = this.count2;
      int count12 = this.count4;
      int count13 = this.count3;
      double offset3 = 0.2;
      Color color3 = color2;
      int index = this.getIndex(color3, offset3, count8, count9, count13, count12, count11, count10, flag);
      double offset4 = offset2;
      double offset5 = offset;
      fontFace2.applyString8(text3, offset5, offset4, index);
      int count14 = count + 200;
      this.settingLoadFromConfig.applyFontFace(count8, count9, count14, count6, 0, 0, fontFace2, true);
      int count15 = count + 400;
      this.settingShowInArrayList.applyFontFace(count8, count9, count15, count6, 0, 0, fontFace2, true);
      int count16 = count + 600;
      this.settingToggleSound.applyFontFace(count8, count9, count16, count6, 0, 0, fontFace2, true);
      int count17 = count + 780;
      this.settingKeyHoldMode.applyFontFace(count8, count9, count17, count6, 0, 0, fontFace2, true);
      AuxiliaryStringArrayEntry auxiliaryStringArrayEntry5 = this.auxiliaryStringArrayEntry3;
      Boolean enabled3 = this.settingLoadFromConfig.isEnabled();
      auxiliaryStringArrayEntry5.applyObject2(enabled3);
      AuxiliaryStringArrayEntry auxiliaryStringArrayEntry6 = this.auxiliaryStringArrayEntry2;
      Boolean enabled4 = this.settingShowInArrayList.isEnabled();
      auxiliaryStringArrayEntry6.applyObject2(enabled4);
      AuxiliaryStringArrayEntry auxiliaryStringArrayEntry7 = this.auxiliaryStringArrayEntry4;
      Boolean enabled5 = this.settingToggleSound.isEnabled();
      auxiliaryStringArrayEntry7.applyObject2(enabled5);
      AuxiliaryStringArrayEntry auxiliaryStringArrayEntry8 = this.auxiliaryStringArrayEntry;
      Boolean enabled6 = this.settingKeyHoldMode.isEnabled();
      auxiliaryStringArrayEntry8.applyObject2(enabled6);
      return 930;
   }

   public boolean isExpanded() {
      return this.expanded;
   }

   public int getInt() {
      return (Integer)this.internalListenerCoordinator.getValue();
   }

   public Category getCategory() {
      return this.category;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   private void applyString5(String text2) {
      this.applyString2(text2);
   }

   private int getIndex(Color color2, double offset, int count, int count6, boolean flag) {
      Setting[] settingArray = ModuleManager.getSettingArray();
      int count7 = this.indexOffset4 - 2;
      int count8 = this.indexOffset3 + 2;
      short shortValue = 160;
      int indexOffset6 = this.indexOffset5;
      int count9 = count7;
      int count10 = this.checkCondition(count9, indexOffset6, shortValue, count8, count, count6) ? 1 : 0;
      if (settingArray == null) {
         if (count10 != 0) {
            if (settingArray != null) {
               return flag ? 1 : 0;
            }

            if (!flag) {
               flowBlockF: {
                  float[] factorArray;
                  float factor;
                  flowBlockE: {
                     factorArray = Color.RGBtoHSB(color2.getRed(), color2.getGreen(), color2.getBlue(), null);
                     factor = (float)((double)factorArray[2] + offset);
                     count10 = Double.compare((double)factorArray[2] + offset, 1.0);
                     if (settingArray == null) {
                        if (count10 > 0) {
                           factor = 1.0F;
                           if (settingArray == null) {
                              break flowBlockE;
                           }
                        }

                        count10 = Double.compare((double)factorArray[2] + offset, 0.0);
                     }

                     if (settingArray != null) {
                        break flowBlockF;
                     }

                     if (count10 < 0) {
                        factor = 0.0F;
                     }
                  }

                  count10 = Color.HSBtoRGB(factorArray[0], factorArray[1], factor);
               }

               int count11 = count10;
               if (settingArray == null) {
                  return count11;
               }
            }
         }

         count10 = color2.getRGB();
      }

      return count10;
   }

   public void applyModule(int count, int count6, int count7, int count8, Module module, FontFace fontFace2, boolean flag) {
      Setting[] settingArray;
      int count9;
      searchBlock: {
         this.fontFace = fontFace2;
         Setting[] settingArray2 = ModuleManager.getSettingArray();
         this.indexOffset4 = count7;
         settingArray = settingArray2;
         this.indexOffset5 = count8;
         Module module2 = this;
         if (settingArray == null) {
            if (this.isToggled()) {
               Color color2 = Client.getClient().getColor();
               double offset = 0.2;
               Color color3 = color2;
               count9 = this.getIndex(color3, offset, count, count6, flag);
               break searchBlock;
            }

            module2 = this;
         }

         Color color4 = new Color(185, 187, 190);
         double offset2 = 0.2;
         Color color5 = color4;
         count9 = module2.getIndex(color5, offset2, count, count6, flag);
      }

      int count10;
      guardBlock: {
         count10 = count9;
         Module module3 = module;
         if (settingArray == null) {
            if (module == null) {
               break guardBlock;
            }

            module3 = module;
         }

         if (module3.equals(this)) {
            String text2 = "> " + this.getDescription();
            double offset3 = (double)count7;
            double offset4 = (double)count8;
            double offset5 = offset3;
            String text3 = text2;
            fontFace2.applyString8(text3, offset5, offset4, count10);
            if (settingArray == null) {
               return;
            }
         }
      }

      String description2 = this.getDescription();
      double offset6 = (double)count7;
      double offset7 = (double)count8;
      double offset8 = offset6;
      String text4 = description2;
      fontFace2.applyString8(text4, offset8, offset7, count10);
   }

   public long getEpochMillis() {
      return this.epochMillis;
   }

   public JsonObject getJsonObject() {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("Name", this.getString());
      jsonObject.addProperty("IsInConfig", this.canExecute4());
      jsonObject.addProperty("IsInArraylist", this.canExecute2());
      jsonObject.addProperty("IsToggleSound", this.canExecute());
      jsonObject.addProperty("OnlyActiveWhileKeyPressed", this.canExecute3());
      jsonObject.addProperty("Toggled", this.isToggled());
      jsonObject.addProperty("Key", this.getInt());
      return jsonObject;
   }

   public void setState4(boolean state) {
      AuxiliaryStringArrayEntry auxiliaryStringArrayEntry5 = this.auxiliaryStringArrayEntry3;
      Boolean flag = state;
      auxiliaryStringArrayEntry5.applyObject2(flag);
      BooleanSetting settingLoadFromConfig2 = this.settingLoadFromConfig;
      boolean value2 = (Boolean)this.auxiliaryStringArrayEntry3.getValue();
      settingLoadFromConfig2.setState3(value2);
   }

   private void onWorldTick() {
      Setting[] settingArray = ModuleManager.getSettingArray();
      Client.getClient().getClass();
      boolean flag = this instanceof RotationRecorder;
      if (settingArray == null) {
         if (flag) {
            return;
         }

         flag = this instanceof RotationRenderer;
      }

      if (settingArray == null) {
         if (flag) {
            return;
         }

         this.category.getModuleList().add(this);
         this.category.getSubModuleList().add(this);
      }
   }

   public abstract void onEnable();

   public void onReset() {
      this.setActive3(true);
   }

   public void onUpdate() {
   }

   public void onPostUpdate() {
      Setting[] settingArray;
      int count;
      searchBlock: {
         settingArray = ModuleManager.getSettingArray();
         augustus.modules.render.ArrayList itemList = Client.getClient().getModuleManager().itemList;
         if (settingArray == null) {
            if (itemList.settingFont.getSelected().equals("Minecraft")) {
               count = Client.getClient().getModuleManager().itemList.settingBold.isEnabled() ? 1 : 0;
               if (settingArray == null) {
                  count = count != 0 ? minecraft.fontRendererObj.getIndex("§l" + this.getString2()) : minecraft.fontRendererObj.getIndex(this.getString2());
               }
               break searchBlock;
            }

            itemList = Client.getClient().getModuleManager().itemList;
         }

         CustomFont fontFaceAdapter = itemList.getFontFaceAdapter();
         String string = this.getString2();
         count = (int)fontFaceAdapter.getScaleFactor(string);
      }

      guardBlock: {
         this.indexOffset = count;
         augustus.modules.render.ArrayList itemList3 = Client.getClient().getModuleManager().itemList;
         if (settingArray == null) {
            if (itemList3.settingFont.getSelected().equals("Minecraft")) {
               count = minecraft.fontRendererObj.indexOffset;
               break guardBlock;
            }

            itemList3 = Client.getClient().getModuleManager().itemList;
         }

         count = (int)itemList3.getFontFaceAdapter().getFontSize();
      }

      this.keyCode = count;
   }

   public abstract void onDisable();

   public void onLoad() {
        Module entry = null;
      Setting[] settingArray;
      int count;
      String selected = ClientGlobals.moduleManager.itemList.settingCasing.getSelected();
      Setting[] settingArray2 = ModuleManager.getSettingArray();
      byte byteValue = -1;
      settingArray = settingArray2;
      count = selected.hashCode();
      searchBlock:
      if (settingArray == null) {
         switch (count) {
            case -1085510111:
               count = selected.equals("Default") ? 1 : 0;
               if (settingArray != null) {
                  break searchBlock;
               }

               if (count == 0) {
                  break;
               }

               byteValue = 0;
               if (settingArray == null) {
                  break;
               }
            case -2068429102:
               count = selected.equals("UpperCase") ? 1 : 0;
               if (settingArray != null) {
                  break searchBlock;
               }

               if (count == 0) {
                  break;
               }

               byteValue = 1;
               if (settingArray == null) {
                  break;
               }
            case 1488507313:
               count = selected.equals("LowerCase") ? 1 : 0;
               if (settingArray != null) {
                  break searchBlock;
               }

               if (count != 0) {
                  byteValue = 2;
               }
         }

         count = byteValue;
      }

      guardBlock: {
         if (settingArray == null) {
            switch (count) {
               case 0:
                  this.suffix = this.description;
                  if (settingArray == null) {
                     break;
                  }
               case 1:
                  this.suffix = this.description.toUpperCase(Locale.ROOT);
                  if (settingArray == null) {
                     break;
                  }
               case 2:
                  this.suffix = this.description.toLowerCase(Locale.ROOT);
            }

            entry = this;
            if (settingArray != null) {
               break guardBlock;
            }

            count = (this.labelText2.equals("") ? 1 : 0);
         }

         if (count == 0) {
            String labelText = this.labelText2;
            this.applyString4(labelText);
         }

         entry = this;
      }

      double offset;
      checkBlock: {
         augustus.modules.render.ArrayList itemList = ClientGlobals.moduleManager.itemList;
         if (settingArray == null) {
            if (ClientGlobals.moduleManager.itemList.settingFont.getSelected().equals("Minecraft")) {
               int enabled3 = ClientGlobals.moduleManager.itemList.settingBold.isEnabled() ? 1 : 0;
               if (settingArray == null) {
                  if (enabled3 != 0) {
                     offset = (double)minecraft.fontRendererObj.getIndex("§l" + this.suffix);
                     break checkBlock;
                  }

                  enabled3 = minecraft.fontRendererObj.getIndex(this.suffix);
               }

               offset = (double)enabled3;
               break checkBlock;
            }

            itemList = ClientGlobals.moduleManager.itemList;
         }

         CustomFont fontFaceAdapter = itemList.getFontFaceAdapter();
         String suffix2 = this.suffix;
         offset = (double)fontFaceAdapter.getScaleFactor(suffix2);
      }

      entry.coordOffset = offset;
   }

   public void onUnload() {
      this.visible = true;
      this.enabled = true;
   }

   public abstract void onPreDisable();

   public Module(String text2) {
      super(null, text2);
      AuxiliaryStringArrayEntry auxiliaryStringArrayEntry5 = new AuxiliaryStringArrayEntry(this, "M_T", false);
      Consumer consumer = o -> this.setToggled((Boolean)o);
      this.enabledProperty = (AuxiliaryStringArrayEntry)auxiliaryStringArrayEntry5.withConsumer(consumer);
      auxiliaryStringArrayEntry5 = new AuxiliaryStringArrayEntry(this, "M_C", true);
      Consumer consumer2 = o -> this.setState4((Boolean)o);
      this.auxiliaryStringArrayEntry3 = (AuxiliaryStringArrayEntry)auxiliaryStringArrayEntry5.withConsumer(consumer2);
      auxiliaryStringArrayEntry5 = new AuxiliaryStringArrayEntry(this, "M_A", true);
      Consumer consumer3 = o -> this.setState((Boolean)o);
      this.auxiliaryStringArrayEntry2 = (AuxiliaryStringArrayEntry)auxiliaryStringArrayEntry5.withConsumer(consumer3);
      auxiliaryStringArrayEntry5 = new AuxiliaryStringArrayEntry(this, "M_S", true);
      Consumer consumer4 = o -> this.setState2((Boolean)o);
      this.auxiliaryStringArrayEntry4 = (AuxiliaryStringArrayEntry)auxiliaryStringArrayEntry5.withConsumer(consumer4);
      auxiliaryStringArrayEntry5 = new AuxiliaryStringArrayEntry(this, "M_KM", false);
      Consumer consumer5 = o -> this.setState3((Boolean)o);
      this.auxiliaryStringArrayEntry = (AuxiliaryStringArrayEntry)auxiliaryStringArrayEntry5.withConsumer(consumer5);
      this.internalListenerCoordinator = new InternalListenerCoordinator(this, "M_K", 0);
      this.name = "";
      this.displayName = "";
      this.settings = new ArrayList();
      this.itemList2 = new ArrayList();
      this.fontFace = FontRegistry.fontFace11;
      this.text = "";
      this.labelText2 = "";
      this.indexOffset2 = 0;
      this.indexOffset1 = 0;
      this.indexOffset = 0;
      this.keyCode = 0;
      this.keybindAnimation = new KeybindAnimation(0.0F, 0.0F, 0.0F, 0.0F);
      this.coordOffset = 0.0;
      this.value = 0.0;
      this.epochMillis = System.currentTimeMillis();
      this.enabled = false;
      this.visible = false;
      this.labelText3 = "";
      this.indexOffset3 = (int)this.fontFace.getFontSize();
      this.settingLoadFromConfig = new BooleanSetting("LoadFromConfig", true);
      this.settingShowInArrayList = new BooleanSetting("ShowInArrayList", true);
      this.settingToggleSound = new BooleanSetting("ToggleSound", true);
      this.settingKeyHoldMode = new BooleanSetting("KeyHoldMode", false);
      this.description = text2;
      this.suffix = text2;
      this.name = text2;
      this.color = Color.GREEN;
      this.category = Category.LEGIT;
      this.setState4(true);
      this.enabled2 = false;
   }

   public Module(String text2, String text3, Color color2, Category category2) {
      super(AuxiliaryListener60EntryAdapter.listenerTracker, text2);
      Setting[] settingArray = ModuleManager.getSettingArray();
      AuxiliaryStringArrayEntry auxiliaryStringArrayEntry5 = new AuxiliaryStringArrayEntry(this, "M_T", false);
      Consumer consumer = o -> this.setToggled((Boolean)o);
      this.enabledProperty = (AuxiliaryStringArrayEntry)auxiliaryStringArrayEntry5.withConsumer(consumer);
      auxiliaryStringArrayEntry5 = new AuxiliaryStringArrayEntry(this, "M_C", true);
      Consumer consumer2 = o -> this.setState4((Boolean)o);
      this.auxiliaryStringArrayEntry3 = (AuxiliaryStringArrayEntry)auxiliaryStringArrayEntry5.withConsumer(consumer2);
      auxiliaryStringArrayEntry5 = new AuxiliaryStringArrayEntry(this, "M_A", true);
      Consumer consumer3 = o -> this.setState((Boolean)o);
      this.auxiliaryStringArrayEntry2 = (AuxiliaryStringArrayEntry)auxiliaryStringArrayEntry5.withConsumer(consumer3);
      auxiliaryStringArrayEntry5 = new AuxiliaryStringArrayEntry(this, "M_S", true);
      Consumer consumer4 = o -> this.setState2((Boolean)o);
      this.auxiliaryStringArrayEntry4 = (AuxiliaryStringArrayEntry)auxiliaryStringArrayEntry5.withConsumer(consumer4);
      auxiliaryStringArrayEntry5 = new AuxiliaryStringArrayEntry(this, "M_KM", false);
      Consumer consumer5 = o -> this.setState3((Boolean)o);
      this.auxiliaryStringArrayEntry = (AuxiliaryStringArrayEntry)auxiliaryStringArrayEntry5.withConsumer(consumer5);
      Setting[] settingArray2 = settingArray;
      this.internalListenerCoordinator = new InternalListenerCoordinator(this, "M_K", 0);
      this.name = "";
      this.displayName = "";
      this.settings = new ArrayList();
      this.itemList2 = new ArrayList();
      this.fontFace = FontRegistry.fontFace11;
      this.text = "";
      this.labelText2 = "";
      this.indexOffset2 = 0;
      this.indexOffset1 = 0;
      this.indexOffset = 0;
      this.keyCode = 0;
      this.keybindAnimation = new KeybindAnimation(0.0F, 0.0F, 0.0F, 0.0F);
      this.coordOffset = 0.0;
      this.value = 0.0;
      this.epochMillis = System.currentTimeMillis();
      this.enabled = false;
      this.visible = false;
      this.labelText3 = "";
      this.indexOffset3 = (int)this.fontFace.getFontSize();
      this.settingLoadFromConfig = new BooleanSetting("LoadFromConfig", true);
      this.settingShowInArrayList = new BooleanSetting("ShowInArrayList", true);
      this.settingToggleSound = new BooleanSetting("ToggleSound", true);
      this.settingKeyHoldMode = new BooleanSetting("KeyHoldMode", false);
      this.description = text2;
      this.suffix = text2;
      this.name = text3;
      this.color = color2;
      this.category = category2;
      this.setState4(this.category != Category.RENDER);
      Client.getClient().getCopyOnWriteArrayListEntry().getCopyOnWriteArrayList().add(this);
      this.enabled2 = false;
      this.onWorldTick();
   }

   public Module(String text2, String text3, boolean flag, Color color2, Category category2) {
      super(AuxiliaryListener60EntryAdapter.listenerTracker, text2);
      AuxiliaryStringArrayEntry auxiliaryStringArrayEntry5 = new AuxiliaryStringArrayEntry(this, "M_T", false);
      Consumer consumer = o -> this.setToggled((Boolean)o);
      this.enabledProperty = (AuxiliaryStringArrayEntry)auxiliaryStringArrayEntry5.withConsumer(consumer);
      Setting[] settingArray = ModuleManager.getSettingArray();
      AuxiliaryStringArrayEntry auxiliaryStringArrayEntry6 = new AuxiliaryStringArrayEntry(this, "M_C", true);
      Consumer consumer2 = o -> this.setState4((Boolean)o);
      this.auxiliaryStringArrayEntry3 = (AuxiliaryStringArrayEntry)auxiliaryStringArrayEntry6.withConsumer(consumer2);
      auxiliaryStringArrayEntry6 = new AuxiliaryStringArrayEntry(this, "M_A", true);
      Consumer consumer3 = o -> this.setState((Boolean)o);
      this.auxiliaryStringArrayEntry2 = (AuxiliaryStringArrayEntry)auxiliaryStringArrayEntry6.withConsumer(consumer3);
      Setting[] settingArray2 = settingArray;
      auxiliaryStringArrayEntry5 = new AuxiliaryStringArrayEntry(this, "M_S", true);
      Consumer consumer4 = o -> this.setState2((Boolean)o);
      this.auxiliaryStringArrayEntry4 = (AuxiliaryStringArrayEntry)auxiliaryStringArrayEntry5.withConsumer(consumer4);
      auxiliaryStringArrayEntry5 = new AuxiliaryStringArrayEntry(this, "M_KM", false);
      Consumer consumer5 = o -> this.setState3((Boolean)o);
      this.auxiliaryStringArrayEntry = (AuxiliaryStringArrayEntry)auxiliaryStringArrayEntry5.withConsumer(consumer5);
      this.internalListenerCoordinator = new InternalListenerCoordinator(this, "M_K", 0);
      this.name = "";
      this.displayName = "";
      this.settings = new ArrayList();
      this.itemList2 = new ArrayList();
      this.fontFace = FontRegistry.fontFace11;
      this.text = "";
      this.labelText2 = "";
      this.indexOffset2 = 0;
      this.indexOffset1 = 0;
      this.indexOffset = 0;
      this.keyCode = 0;
      this.keybindAnimation = new KeybindAnimation(0.0F, 0.0F, 0.0F, 0.0F);
      this.coordOffset = 0.0;
      this.value = 0.0;
      this.epochMillis = System.currentTimeMillis();
      this.enabled = false;
      this.visible = false;
      this.labelText3 = "";
      this.indexOffset3 = (int)this.fontFace.getFontSize();
      this.settingLoadFromConfig = new BooleanSetting("LoadFromConfig", true);
      this.settingShowInArrayList = new BooleanSetting("ShowInArrayList", true);
      this.settingToggleSound = new BooleanSetting("ToggleSound", true);
      this.settingKeyHoldMode = new BooleanSetting("KeyHoldMode", false);
      this.description = text2;
      this.name = text3;
      this.suffix = text2;
      this.color = color2;
      this.category = category2;
      this.setState4(this.category != Category.RENDER);
      this.setState(false);
      Client.getClient().getCopyOnWriteArrayListEntry().getCopyOnWriteArrayList().add(this);
      this.enabled2 = true;
      this.onWorldTick();
   }

   @Override
   public int compareTo(Object entry) {
      Module module = (Module)entry;
      return this.getIndex(module);
   }
}
