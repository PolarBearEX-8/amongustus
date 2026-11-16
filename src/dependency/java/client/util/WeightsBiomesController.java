package client.util;

import client.event.PostRenderObserver;
import client.setting.LocalOptionType;
import java.util.Properties;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEntry;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.EntityVillager;
import net.minecraft.entity.EntityWolf;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;

public class WeightsBiomesController {
   public String labelText = null;
   public ResourceLocation resourceLocation = null;
   public int count;
   public int[] countArray = null;
   public ResourceLocation[] resourceLocationArray = null;
   public int[] countArray2 = null;
   public BiomeIDIsOutOfBoundsManager[] biomeIDIsOutOfBoundsManagerArray = null;
   public TargetAggregator targetAggregator = null;
   public TargetAggregator targetAggregator2 = null;
   public boolean enabled = false;
   public GeometryObserver geometryObserver = null;
   public int[] countArray3 = null;
   public int count2 = 1;
   public SpecialIntArrayEntry[] specialIntArrayEntryArray = null;
   public LocalOptionType[] localOptionTypeArray = null;
   public Boolean memberRef = null;
   public TargetAggregator targetAggregator3 = null;
   public TargetAggregator targetAggregator4 = null;
   public SmoothWatcher[] smoothWatcherArray = null;

   public WeightsBiomesController(Properties properties, String text, ResourceLocation location, int count3, String text2, NetherManager netherManager) {
      this.labelText = text;
      this.resourceLocation = location;
      this.count = count3;
      this.countArray = netherManager.resolveIndexArrayByText(text2);
      this.countArray2 = netherManager.resolveIndexArrayByText(properties.getProperty("weights." + count3));
      this.biomeIDIsOutOfBoundsManagerArray = netherManager.resolveBiomeIDIsOutOfBoundsManagerA(properties.getProperty("biomes." + count3));
      this.targetAggregator = netherManager.resolveTargetAggregator(properties.getProperty("heights." + count3));
      if (this.targetAggregator == null) {
         this.targetAggregator = this.resolveTargetAggregator(properties, count3);
      }

      String property = properties.getProperty("health." + count3);
      if (property != null) {
         this.enabled = property.contains("%");
         String text3 = property.replace("%", "");
         this.targetAggregator2 = netherManager.resolveTargetAggregator(text3);
      }

      this.geometryObserver = netherManager.resolveGeometryObserver("name", properties.getProperty("name." + count3));
      this.specialIntArrayEntryArray = netherManager.resolveSpecialIntArrayEntryArray(properties.getProperty("professions." + count3));
      this.localOptionTypeArray = netherManager.resolveLocalOptionTypeArray(properties.getProperty("collarColors." + count3), "collar color", NetherManager.localOptionTypeArray);
      this.memberRef = netherManager.resolveBoolean(properties.getProperty("baby." + count3));
      this.targetAggregator3 = netherManager.resolveTargetAggregator(properties.getProperty("moonPhase." + count3));
      this.targetAggregator4 = netherManager.resolveTargetAggregator(properties.getProperty("dayTime." + count3));
      this.smoothWatcherArray = netherManager.resolveSmoothWatcherArray(properties.getProperty("weather." + count3), "weather." + count3, (SmoothWatcher[])null);
   }

   public ResourceLocation resolveResourceLocation(ResourceLocation location, int count3) {
      if (this.resourceLocationArray != null && this.resourceLocationArray.length != 0) {
         int count4 = 0;
         if (this.countArray2 == null) {
            count4 = count3 % this.resourceLocationArray.length;
         } else {
            int count5 = count3 % this.count2;

            for (int index = 0; index < this.countArray3.length; index++) {
               if (this.countArray3[index] > count5) {
                  count4 = index;
                  break;
               }
            }
         }

         return this.resourceLocationArray[count4];
      } else {
         return location;
      }
   }

   public boolean matchesPostRenderObserver(PostRenderObserver postRenderObserver) {
      if (this.biomeIDIsOutOfBoundsManagerArray != null
         && !GeometrySelector.matchesBiomeIDIsOutOfBoundsManage(postRenderObserver.getBiomeIDIsOutOfBoundsManager(), this.biomeIDIsOutOfBoundsManagerArray)) {
         return false;
      } else {
         if (this.targetAggregator != null) {
            BlockPos blockPos = postRenderObserver.getBlockPos();
            if (blockPos != null && !this.targetAggregator.checkIndex(blockPos.getY())) {
               return false;
            }
         }

         if (this.targetAggregator2 != null) {
            int count3 = postRenderObserver.getCount();
            if (this.enabled) {
               int count4 = postRenderObserver.getCount2();
               if (count4 > 0) {
                  count3 = (int)((double)(count3 * 100) / (double)count4);
               }
            }

            if (!this.targetAggregator2.checkIndex(count3)) {
               return false;
            }
         }

         if (this.geometryObserver != null) {
            String text = postRenderObserver.getText();
            if (!this.geometryObserver.matchesText(text)) {
               return false;
            }
         }

         if (this.specialIntArrayEntryArray != null && postRenderObserver instanceof EntityEntry) {
            Entity entityAsEntity = ((EntityEntry)postRenderObserver).getEntityAsEntity();
            if (entityAsEntity instanceof EntityVillager) {
               EntityVillager entityVillager = (EntityVillager)entityAsEntity;
               int count5 = entityVillager.getCount10();
               int count6 = entityVillager.count36;
               if (count5 < 0 || count6 < 0) {
                  return false;
               }

               boolean flag = false;

               for (int index = 0; index < this.specialIntArrayEntryArray.length; index++) {
                  if (this.specialIntArrayEntryArray[index].checkIndex(count5, count6)) {
                     flag = true;
                     break;
                  }
               }

               if (!flag) {
                  return false;
               }
            }
         }

         if (this.localOptionTypeArray != null && postRenderObserver instanceof EntityEntry) {
            Entity entityAsEntity2 = ((EntityEntry)postRenderObserver).getEntityAsEntity();
            if (entityAsEntity2 instanceof EntityWolf) {
               EntityWolf entityWolf = (EntityWolf)entityAsEntity2;
               if (!entityWolf.isActive32()) {
                  return false;
               }

               if (!BuildManager.matchesTarget(entityWolf.getLocalOptionType(), this.localOptionTypeArray)) {
                  return false;
               }
            }
         }

         if (this.memberRef != null && postRenderObserver instanceof EntityEntry) {
            Entity entityAsEntity3 = ((EntityEntry)postRenderObserver).getEntityAsEntity();
            if (entityAsEntity3 instanceof EntityMob && ((EntityMob)entityAsEntity3).isActive16() != this.memberRef) {
               return false;
            }
         }

         if (this.targetAggregator3 != null) {
            WorldClient world = BuildManager.getMc().theWorld;
            if (world != null) {
               int count7 = world.getCount2();
               if (!this.targetAggregator3.checkIndex(count7)) {
                  return false;
               }
            }
         }

         if (this.targetAggregator4 != null) {
            WorldClient world2 = BuildManager.getMc().theWorld;
            if (world2 != null) {
               int timestamp = (int)world2.getRandomSeedManager().getTimestamp();
               if (!this.targetAggregator4.checkIndex(timestamp)) {
                  return false;
               }
            }
         }

         if (this.smoothWatcherArray != null) {
            WorldClient world3 = BuildManager.getMc().theWorld;
            if (world3 != null) {
               SmoothWatcher smoothWatcher = SmoothWatcher.resolveSmoothWatcher(world3, 0.0F);
               if (!GivenArrayHelper.matchesTargetArray(this.smoothWatcherArray, smoothWatcher)) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   private TargetAggregator resolveTargetAggregator(Properties properties, int count3) {
      String property = properties.getProperty("minHeight." + count3);
      String property2 = properties.getProperty("maxHeight." + count3);
      if (property == null && property2 == null) {
         return null;
      } else {
         int count4 = 0;
         if (property != null) {
            count4 = BuildManager.computeCount(property, -1);
            if (count4 < 0) {
               BuildManager.applyText3("Invalid minHeight: " + property);
               return null;
            }
         }

         int count5 = 256;
         if (property2 != null) {
            count5 = BuildManager.computeCount(property2, -1);
            if (count5 < 0) {
               BuildManager.applyText3("Invalid maxHeight: " + property2);
               return null;
            }
         }

         if (count5 < 0) {
            BuildManager.applyText3("Invalid minHeight, maxHeight: " + property + ", " + property2);
            return null;
         } else {
            TargetAggregator targetAggregator5 = new TargetAggregator();
            targetAggregator5.applyRotationMonitor(new RotationMonitor(count4, count5));
            return targetAggregator5;
         }
      }
   }

   public boolean matchesText(String text) {
      if (this.countArray == null || this.countArray.length == 0) {
         BuildManager.applyText3("Invalid skins for rule: " + this.count);
         return false;
      } else if (this.resourceLocationArray != null) {
         return true;
      } else {
         this.resourceLocationArray = new ResourceLocation[this.countArray.length];
         boolean flag = this.labelText.startsWith("mcpatcher/mob/");
         ResourceLocation location = OptifineRandomManager.resolveResourceLocationByResourceLocation(this.resourceLocation, flag);
         if (location == null) {
            BuildManager.applyText3("Invalid path: " + this.resourceLocation.getText1());
            return false;
         } else {
            for (int index = 0; index < this.resourceLocationArray.length; index++) {
               int count3 = this.countArray[index];
               if (count3 <= 1) {
                  this.resourceLocationArray[index] = this.resourceLocation;
               } else {
                  ResourceLocation location2 = OptifineRandomManager.resolveResourceLocation(location, count3);
                  if (location2 == null) {
                     BuildManager.applyText3("Invalid path: " + this.resourceLocation.getText1());
                     return false;
                  }

                  if (!BuildManager.matchesResourceLocation3(location2)) {
                     BuildManager.applyText3("Texture not found: " + location2.getText1());
                     return false;
                  }

                  this.resourceLocationArray[index] = location2;
               }
            }

            if (this.countArray2 != null) {
               if (this.countArray2.length > this.resourceLocationArray.length) {
                  BuildManager.applyText3("More weights defined than skins, trimming weights: " + text);
                  int[] countArray4 = new int[this.resourceLocationArray.length];
                  System.arraycopy(this.countArray2, 0, countArray4, 0, countArray4.length);
                  this.countArray2 = countArray4;
               }

               if (this.countArray2.length < this.resourceLocationArray.length) {
                  BuildManager.applyText3("Less weights defined than skins, expanding weights: " + text);
                  int[] countArray5 = new int[this.resourceLocationArray.length];
                  System.arraycopy(this.countArray2, 0, countArray5, 0, this.countArray2.length);
                  int count4 = AltFloatEntry.computeCount(this.countArray2);

                  for (int index2 = this.countArray2.length; index2 < countArray5.length; index2++) {
                     countArray5[index2] = count4;
                  }

                  this.countArray2 = countArray5;
               }

               this.countArray3 = new int[this.countArray2.length];
               int count5 = 0;

               for (int index3 = 0; index3 < this.countArray2.length; index3++) {
                  if (this.countArray2[index3] < 0) {
                     BuildManager.applyText3("Invalid weight: " + this.countArray2[index3]);
                     return false;
                  }

                  count5 += this.countArray2[index3];
                  this.countArray3[index3] = count5;
               }

               this.count2 = count5;
               if (this.count2 <= 0) {
                  BuildManager.applyText3("Invalid sum of all weights: " + count5);
                  this.count2 = 1;
               }
            }

            if (this.specialIntArrayEntryArray == NetherManager.specialIntArrayEntryArray) {
               BuildManager.applyText3("Invalid professions or careers: " + text);
               return false;
            } else if (this.localOptionTypeArray == NetherManager.localOptionTypeArray) {
               BuildManager.applyText3("Invalid collar colors: " + text);
               return false;
            } else {
               return true;
            }
         }
      }
   }
}
