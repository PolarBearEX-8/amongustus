package client.util;

import client.event.SimpleListener;
import client.render.AllocateTextureMapManager;
import client.render.EmissiveTexturesManager;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;
import net.minecraft.block.BlocksGrassTopManager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ListenerAdapter27 extends ListenerAdapter26 implements Listener68Extension {
   public static final boolean enabled5 = Boolean.parseBoolean(System.getProperty("fml.skipFirstTextureLoad", "true"));
   public static final Logger logger = LogManager.getLogger();
   public static final ResourceLocation resourceLocation2 = new ResourceLocation("missingno");
   public static final ResourceLocation resourceLocation = new ResourceLocation("textures/atlas/blocks.png");
   public final List list;
   public final Map map2;
   public final Map map;
   public final String labelText;
   public final SimpleListener simpleListener;
   public int count6;
   public final ServiceStringEntry serviceStringEntry;
   public boolean enabled6 = false;
   public ServiceStringEntry[] serviceStringEntryArray = null;
   public int count5 = -1;
   public int count3 = -1;
   public int count2 = -1;
   public double value = -1.0;
   public double value2 = -1.0;
   public RayTraceEvaluator rayTraceEvaluator = new RayTraceEvaluator(0);
   public int count7 = 0;
   public int count8 = 0;
   public int count9;
   public int count4;

   public ServiceStringEntry resolveServiceStringEntry(double offset, double offset2) {
      if (this.serviceStringEntryArray == null) {
         return null;
      } else {
         int count = (int)(offset / this.value);
         count = (int)(offset2 / this.value2) * this.count3 + count;
         return count >= 0 && count <= this.serviceStringEntryArray.length ? this.serviceStringEntryArray[count] : null;
      }
   }

   public int getCount6() {
      return this.count6;
   }

   public boolean matchesText(String text, ServiceStringEntry serviceStringEntry2) {
      if (!this.map2.containsKey(text)) {
         this.map2.put(text, serviceStringEntry2);
         serviceStringEntry2.applyRayTraceEvaluator(this.rayTraceEvaluator);
         return true;
      } else {
         return false;
      }
   }

   public boolean matchesServiceStringEntry(ServiceStringEntry serviceStringEntry2) {
      return this.matchesText(serviceStringEntry2.getLabelText3(), serviceStringEntry2);
   }

   public ServiceStringEntry get(String text) {
      ServiceStringEntry serviceStringEntry2 = (ServiceStringEntry)this.map.get(text);
      if (serviceStringEntry2 == null) {
         serviceStringEntry2 = this.serviceStringEntry;
      }

      return serviceStringEntry2;
   }

   public int size() {
      return this.list.size();
   }

   private int computeCount(Map map3, IResourceManager iResourceManager) {
      int count = this.computeCount(map3, iResourceManager, 20);
      if (count < 16) {
         count = 16;
      }

      count = MathHelper.processAction(count);
      if (count > 16) {
         BuildManager.applyText("Sprite size: " + count);
      }

      count = MathHelper.computeCountByIndex(count);
      if (count < 4) {
         count = 4;
      }

      return count;
   }

   @Override
   public void updateState2() {
      this.updateState4();
   }

   public int getCountAsIndex() {
      return this.rayTraceEvaluator.getCountAsIndex();
   }

   public void applyIResourceManager(IResourceManager iResourceManager, SimpleListener simpleListener2) {
      this.map2.clear();
      this.rayTraceEvaluator.updateState();
      simpleListener2.applyListenerAdapter27(this);
      if (this.count6 >= 4) {
         this.count6 = this.computeCount(this.map2, iResourceManager);
         BuildManager.applyText("Mipmap levels: " + this.count6);
      }

      this.updateState5();
      this.updateState3();
      this.applyIResourceManagerByIResourceManager(iResourceManager);
   }

   private int[] resolveIndexArray(int count) {
      BufferedImage bufferedImage = new BufferedImage(16, 16, 2);
      bufferedImage.setRGB(0, 0, 16, 16, SpecialLoggerEntry.countArray3, 0, 16);
      bufferedImage = InternalPngManager.resolveBufferedImage(bufferedImage, count);
      int[] countArray = new int[count * count];
      bufferedImage.getRGB(0, 0, count, count, countArray, 0, count);
      return countArray;
   }

   public ServiceStringEntry resolveServiceStringEntry(ResourceLocation location) {
      if (location == null) {
         throw new IllegalArgumentException("Location cannot be null!");
      } else {
         ServiceStringEntry serviceStringEntry2 = (ServiceStringEntry)this.map2.get(location.toString());
         if (serviceStringEntry2 == null) {
            serviceStringEntry2 = ServiceStringEntry.resolveServiceStringEntry(location);
            this.map2.put(location.toString(), serviceStringEntry2);
            serviceStringEntry2.applyRayTraceEvaluator(this.rayTraceEvaluator);
            if (BuildManager.isActive39()) {
               this.applyResourceLocationByResourceLocation(location, serviceStringEntry2);
            }
         }

         return serviceStringEntry2;
      }
   }

   public void updateState4() {
      boolean flag = false;
      boolean flag2 = false;
      SpecialLoggerEntry.applyIndexByIndex(this.getCount());
      int count = 0;

      for (Object serviceStringEntry2Obj : this.list) { ServiceStringEntry serviceStringEntry2 = (ServiceStringEntry)serviceStringEntry2Obj;
         if (this.matchesServiceStringEntryByServiceStringEnt(serviceStringEntry2)) {
            serviceStringEntry2.updateState3();
            if (serviceStringEntry2.isEnabled2()) {
               count++;
            }

            if (serviceStringEntry2.serviceStringEntry != null) {
               flag = true;
            }

            if (serviceStringEntry2.serviceStringEntry2 != null) {
               flag2 = true;
            }
         }
      }

      if (BuildManager.isActive46()) {
         for (Object serviceStringEntry3Obj : this.list) { ServiceStringEntry serviceStringEntry3 = (ServiceStringEntry)serviceStringEntry3Obj;
            if (this.matchesServiceStringEntryByServiceStringEnt(serviceStringEntry3)) {
               ServiceStringEntry serviceStringEntry4 = serviceStringEntry3.serviceStringEntry3;
               if (serviceStringEntry4 != null) {
                  if (serviceStringEntry3 == InternalPngManager.serviceStringEntry18 || serviceStringEntry3 == InternalPngManager.serviceStringEntry8) {
                     serviceStringEntry4.count10 = serviceStringEntry3.count10;
                  }

                  serviceStringEntry3.updateState6();
                  serviceStringEntry4.updateState3();
                  if (serviceStringEntry4.isEnabled2()) {
                     count++;
                  }
               }
            }
         }

         SpecialLoggerEntry.applyIndexByIndex(this.getCount());
      }

      if (BuildManager.canExecute()) {
         if (flag) {
            SpecialLoggerEntry.applyIndexByIndex(this.getDistanceTransformer().count3);

            for (Object serviceStringEntry5Obj : this.list) { ServiceStringEntry serviceStringEntry5 = (ServiceStringEntry)serviceStringEntry5Obj;
               if (serviceStringEntry5.serviceStringEntry != null && this.matchesServiceStringEntryByServiceStringEnt(serviceStringEntry5)) {
                  if (serviceStringEntry5 == InternalPngManager.serviceStringEntry18 || serviceStringEntry5 == InternalPngManager.serviceStringEntry8) {
                     serviceStringEntry5.serviceStringEntry.count10 = serviceStringEntry5.count10;
                  }

                  serviceStringEntry5.serviceStringEntry.updateState3();
                  if (serviceStringEntry5.serviceStringEntry.isEnabled2()) {
                     count++;
                  }
               }
            }
         }

         if (flag2) {
            SpecialLoggerEntry.applyIndexByIndex(this.getDistanceTransformer().count2);

            for (Object serviceStringEntry6Obj : this.list) { ServiceStringEntry serviceStringEntry6 = (ServiceStringEntry)serviceStringEntry6Obj;
               if (serviceStringEntry6.serviceStringEntry2 != null && this.matchesServiceStringEntryByServiceStringEnt(serviceStringEntry6)) {
                  if (serviceStringEntry6 == InternalPngManager.serviceStringEntry18 || serviceStringEntry6 == InternalPngManager.serviceStringEntry8) {
                     serviceStringEntry6.serviceStringEntry.count10 = serviceStringEntry6.count10;
                  }

                  serviceStringEntry6.serviceStringEntry2.updateState3();
                  if (serviceStringEntry6.serviceStringEntry2.isEnabled2()) {
                     count++;
                  }
               }
            }
         }

         if (flag || flag2) {
            SpecialLoggerEntry.applyIndexByIndex(this.getCount());
         }
      }

      int count10 = BuildManager.getMc().entityRenderer.count5;
      if (count10 != this.count4) {
         this.count9 = count;
         this.count4 = count10;
      }

      if (BitSetHolder.isActive()) {
         BitSetHolder.clear();
      }
   }

   public ServiceStringEntry getServiceStringEntry() {
      return this.serviceStringEntry;
   }

   public ServiceStringEntry getByText(String text) {
      return (ServiceStringEntry)this.map2.get(text);
   }

   private void applyResourceLocationByResourceLocation(ResourceLocation location, ServiceStringEntry serviceStringEntry2) {
      String labelText2 = EmissiveTexturesManager.getLabelText3();
      if (labelText2 != null && !location.getText1().endsWith(labelText2)) {
         ResourceLocation location2 = new ResourceLocation(location.getText(), location.getText1() + labelText2);
         if (BuildManager.matchesResourceLocation3(this.resolveResourceLocation(location2))) {
            ServiceStringEntry serviceStringEntry3 = this.resolveServiceStringEntry(location2);
            serviceStringEntry3.enabled4 = true;
            serviceStringEntry2.serviceStringEntry4 = serviceStringEntry3;
         }
      }
   }

   private boolean matchesServiceStringEntryByServiceStringEnt(ServiceStringEntry serviceStringEntry2) {
      return serviceStringEntry2 == InternalPngManager.serviceStringEntry15 || serviceStringEntry2 == InternalPngManager.serviceStringEntry13
         ? BuildManager.isActive54()
         : (
            serviceStringEntry2 == InternalPngManager.serviceStringEntry2 || serviceStringEntry2 == InternalPngManager.serviceStringEntry4
               ? BuildManager.isActive25()
               : (
                  serviceStringEntry2 == InternalPngManager.serviceStringEntry17 || serviceStringEntry2 == InternalPngManager.serviceStringEntry6
                     ? BuildManager.isActive75()
                     : (
                        serviceStringEntry2 == InternalPngManager.serviceStringEntry9
                           ? BuildManager.isActive57()
                           : (
                              serviceStringEntry2 != InternalPngManager.serviceStringEntry18 && serviceStringEntry2 != InternalPngManager.serviceStringEntry8
                                 ? BuildManager.isActive53()
                                 : true
                           )
                     )
               )
         );
   }

   private void updateState5() {
      int count = this.getCount2();
      int[] countArray = this.resolveIndexArray(count);
      this.serviceStringEntry.applyIndexByIndex(count);
      this.serviceStringEntry.applyIndex2(count);
      int[][] countMatrix = new int[this.count6 + 1][];
      countMatrix[0] = countArray;
      this.serviceStringEntry.applyList(Lists.newArrayList(new int[][][]{countMatrix}));
      this.serviceStringEntry.setCount8(this.rayTraceEvaluator.getCount());
   }

   public void setCount6(int count) {
      this.count6 = count;
   }

   public ServiceStringEntry resolveServiceStringEntry(String text) {
      ResourceLocation location = new ResourceLocation(text);
      return (ServiceStringEntry)this.map2.get(location.toString());
   }

   private int getCount2() {
      int count = 1 << this.count6;
      if (count < 8) {
         count = 8;
      }

      return count;
   }

   public ServiceStringEntry resolveServiceStringEntry2(ResourceLocation location) {
      return (ServiceStringEntry)this.map2.get(location.toString());
   }

   public ResourceLocation resolveResourceLocation(ResourceLocation location) {
      return this.resolveResourceLocation(location, 0);
   }

   private boolean matchesResourceLocationByResourceLocation(ResourceLocation location) {
      String text = location.getText1();
      return this.matchesText(text);
   }

   public boolean isActive() {
      int stateInt = GlStateManager.getStateInt4();
      int count = this.getCount();
      return stateInt == count;
   }

   private void applyIndex(int count, int count10) {
      this.count3 = -1;
      this.count2 = -1;
      this.serviceStringEntryArray = null;
      if (this.count5 > 0) {
         this.count3 = count / this.count5;
         this.count2 = count10 / this.count5;
         this.serviceStringEntryArray = new ServiceStringEntry[this.count3 * this.count2];
         this.value = 1.0 / (double)this.count3;
         this.value2 = 1.0 / (double)this.count2;

         for (Object serviceStringEntry2Obj : this.map.values()) { ServiceStringEntry serviceStringEntry2 = (ServiceStringEntry)serviceStringEntry2Obj;
            double offset = 0.5 / (double)count;
            double offset2 = 0.5 / (double)count10;
            double offset3 = (double)Math.min(serviceStringEntry2.getFactor5(), serviceStringEntry2.getFactor2()) + offset;
            double offset4 = (double)Math.min(serviceStringEntry2.getFactor(), serviceStringEntry2.getFactor6()) + offset2;
            offset = (double)Math.max(serviceStringEntry2.getFactor5(), serviceStringEntry2.getFactor2()) - offset;
            offset2 = (double)Math.max(serviceStringEntry2.getFactor(), serviceStringEntry2.getFactor6()) - offset2;
            int count11 = (int)(offset3 / this.value);
            int count12 = (int)(offset4 / this.value2);
            int count13 = (int)(offset / this.value);

            for (int index = (int)(offset2 / this.value2); count11 <= count13; count11++) {
               if (count11 >= 0 && count11 < this.count3) {
                  for (int index2 = count12; index2 <= index; index2++) {
                     if (index2 >= 0 && index2 < this.count3) {
                        int count14 = index2 * this.count3 + count11;
                        this.serviceStringEntryArray[count14] = serviceStringEntry2;
                     } else {
                        BuildManager.applyText3("Invalid grid V: " + index2 + ", icon: " + serviceStringEntry2.getLabelText3());
                     }
                  }
               } else {
                  BuildManager.applyText3("Invalid grid U: " + count11 + ", icon: " + serviceStringEntry2.getLabelText3());
               }
            }
         }
      }
   }

   public ResourceLocation resolveResourceLocation(ResourceLocation location, int count) {
      return this.matchesResourceLocationByResourceLocation(location)
         ? new ResourceLocation(location.getText(), location.getText1() + ".png")
         : (
            count == 0
               ? new ResourceLocation(location.getText(), String.format("%s/%s%s", this.labelText, location.getText1(), ".png"))
               : new ResourceLocation(location.getText(), String.format("%s/mipmaps/%s.%d%s", this.labelText, location.getText1(), count, ".png"))
         );
   }

   public int getCount9() {
      return this.count9;
   }

   public void applyIResourceManagerByIResourceManager(IResourceManager iResourceManager) {
      BuildManager.applyText4("Multitexture: " + BuildManager.isActive46());
      if (BuildManager.isActive46()) {
         for (Object serviceStringEntry2Obj : this.map.values()) { ServiceStringEntry serviceStringEntry2 = (ServiceStringEntry)serviceStringEntry2Obj;
            serviceStringEntry2.updateState2();
         }
      }

      McpatcherCtmDefaultManager.applyListenerAdapter27(this);
      InvalidItemIDManager.applyListenerAdapter27(this);
      BlocksGrassTopManager.applyListenerAdapter27(this);
      int count = InternalPngManager.getCount();
      ExtendedSetEntry extendedSetEntry = new ExtendedSetEntry(count, count, true, 0, this.count6);
      this.map.clear();
      this.list.clear();
      count = Integer.MAX_VALUE;
      int count10 = this.getCount2();
      this.count5 = count10;
      int count11 = 1 << this.count6;
      int count12 = 0;
      int count13 = 0;

      for (Object entryObj : this.map2.entrySet()) { Entry entry = (Entry)entryObj;
         if (!this.enabled6) {
            ServiceStringEntry value3 = (ServiceStringEntry)entry.getValue();
            ResourceLocation location = new ResourceLocation(value3.getLabelText3());
            ResourceLocation location2 = this.resolveResourceLocation(location, 0);
            value3.applyRayTraceEvaluator(this.rayTraceEvaluator);
            if (value3.matchesIResourceManager(iResourceManager, location)) {
               if (!value3.matchesIResourceManager2(iResourceManager, location)) {
                  count = Math.min(count, Math.min(value3.getCount(), value3.getCount2()));
                  extendedSetEntry.applyServiceStringEntry(value3);
                  BuildManager.applyTextByText("Custom loader (skipped): " + value3);
                  count13++;
               }

               BuildManager.applyTextByText("Custom loader: " + value3);
               count12++;
            } else {
               try {
                  IResource iResource = iResourceManager.resolveIResourceByResourceLocation(location2);
                  BufferedImage[] bufferedImageArray = new BufferedImage[1 + this.count6];
                  bufferedImageArray[0] = SpecialLoggerEntry.resolveBufferedImage(iResource.getInputStream());
                  int width = bufferedImageArray[0].getWidth();
                  int height = bufferedImageArray[0].getHeight();
                  if (width < 1 || height < 1) {
                     BuildManager.applyText3("Invalid sprite size: " + value3);
                     continue;
                  }

                  if (width < count10 || this.count6 > 0) {
                     int count14 = this.count6 > 0 ? InternalPngManager.computeCount(width, count10) : InternalPngManager.computeCountByIndex(width, count10);
                     if (count14 != width) {
                        if (!InternalPngManager.checkIndex(width)) {
                           BuildManager.applyText("Scaled non power of 2: " + value3.getLabelText3() + ", " + width + " -> " + count14);
                        } else {
                           BuildManager.applyText("Scaled too small texture: " + value3.getLabelText3() + ", " + width + " -> " + count14);
                        }

                        int count15 = height * count14 / width;
                        bufferedImageArray[0] = InternalPngManager.resolveBufferedImage(bufferedImageArray[0], count14);
                     }
                  }

                  SupplementalListEntry supplementalListEntry = (SupplementalListEntry)iResource.resolveIMetadataSection("texture");
                  if (supplementalListEntry != null) {
                     List list2 = supplementalListEntry.unmodifiableList();
                     if (!list2.isEmpty()) {
                        width = bufferedImageArray[0].getWidth();
                        height = bufferedImageArray[0].getHeight();
                        if (MathHelper.processAction(width) != width || MathHelper.processAction(height) != height) {
                           throw new RuntimeException("Unable to load extra miplevels, source-texture is not power of two");
                        }
                     }

                     for (Object count16Obj : list2) { int count16 = (Integer)count16Obj;
                        if (count16 > 0 && count16 < bufferedImageArray.length - 1 && bufferedImageArray[count16] == null) {
                           ResourceLocation location3 = this.resolveResourceLocation(location, count16);

                           try {
                              bufferedImageArray[count16] = SpecialLoggerEntry.resolveBufferedImage(iResourceManager.resolveIResourceByResourceLocation(location3).getInputStream());
                           } catch (IOException ioException) {
                              logger.error("Unable to load miplevel {} from: {}", new Object[]{count16, location3, ioException});
                           }
                        }
                     }
                  }

                  BaseListEntry baseListEntry = (BaseListEntry)iResource.resolveIMetadataSection("animation");
                  value3.applyBufferedImageArray(bufferedImageArray, baseListEntry);
               } catch (RuntimeException exception) {
                  logger.error("Unable to parse metadata from " + location2, exception);
                  continue;
               } catch (IOException ioException2) {
                  logger.error("Using missing texture, unable to load " + location2 + ", " + ioException2.getClass().getName());
                  continue;
               }

               count = Math.min(count, Math.min(value3.getCount(), value3.getCount2()));
               int count17 = Math.min(Integer.lowestOneBit(value3.getCount()), Integer.lowestOneBit(value3.getCount2()));
               if (count17 < count11) {
                  logger.warn(
                     "Texture {} with size {}x{} limits mip level from {} to {}",
                     new Object[]{location2, value3.getCount(), value3.getCount2(), MathHelper.computeCountByIndex(count11), MathHelper.computeCountByIndex(count17)}
                  );
                  count11 = count17;
               }

               extendedSetEntry.applyServiceStringEntry(value3);
            }
            continue;
         }
         break;
      }

      if (count12 > 0) {
         BuildManager.applyText4("Custom loader sprites: " + count12);
      }

      if (count13 > 0) {
         BuildManager.applyText4("Custom loader sprites (skipped): " + count13);
      }

      count = Math.min(count, count11);
      count10 = MathHelper.computeCountByIndex(count);
      if (count10 < 0) {
         count10 = 0;
      }

      if (count10 < this.count6) {
         logger.warn("{}: dropping miplevel from {} to {}, because of minimum power of two: {}", new Object[]{this.labelText, this.count6, count10, count});
         this.count6 = count10;
      }

      for (Object serviceStringEntry3Obj : this.map2.values()) { ServiceStringEntry serviceStringEntry3 = (ServiceStringEntry)serviceStringEntry3Obj;
         if (this.enabled6) {
            break;
         }

         try {
            serviceStringEntry3.applyIndex4(this.count6);
         } catch (Throwable throwable) {
            CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Applying mipmap");
            CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("Sprite being mipmapped");
            crashReportCategory.applyText("Sprite name", new FovCoordinator(this, serviceStringEntry3));
            crashReportCategory.applyText("Sprite size", new FovReader(this, serviceStringEntry3));
            crashReportCategory.applyText("Sprite frames", new BezierRegistry(this, serviceStringEntry3));
            crashReportCategory.add("Mipmap levels", this.count6);
            throw new SecondaryRuntimeExceptionAdapter(crashReport);
         }
      }

      this.serviceStringEntry.applyIndex4(this.count6);
      extendedSetEntry.applyServiceStringEntry(this.serviceStringEntry);
      this.enabled6 = false;

      try {
         extendedSetEntry.updateState();
      } catch (ExtendedRuntimeExceptionAdapter exception2) {
         throw exception2;
      }

      logger.info("Created: {}x{} {}-atlas", new Object[]{extendedSetEntry.getCount(), extendedSetEntry.getCount3(), this.labelText});
      if (BuildManager.canExecute()) {
         AllocateTextureMapManager.applyExtendedSetEntry(this.getCount(), this.count6, extendedSetEntry.getCount(), extendedSetEntry.getCount3(), extendedSetEntry, this);
      } else {
         SpecialLoggerEntry.applyIndex(this.getCount(), this.count6, extendedSetEntry.getCount(), extendedSetEntry.getCount3());
      }

      HashMap map3 = Maps.newHashMap(this.map2);

      for (Object serviceStringEntry4Obj : extendedSetEntry.getList()) { ServiceStringEntry serviceStringEntry4 = (ServiceStringEntry)serviceStringEntry4Obj;
         String labelText2 = serviceStringEntry4.getLabelText3();
         map3.remove(labelText2);
         this.map.put(labelText2, serviceStringEntry4);

         try {
            if (BuildManager.canExecute()) {
               AllocateTextureMapManager.applyListenerAdapter27(
                  this, serviceStringEntry4.getLabelText3(), serviceStringEntry4.get(0), serviceStringEntry4.getCount(), serviceStringEntry4.getCount2(), serviceStringEntry4.getCount3(), serviceStringEntry4.getCount6(), false, false
               );
            } else {
               SpecialLoggerEntry.applyIndexArray(serviceStringEntry4.get(0), serviceStringEntry4.getCount(), serviceStringEntry4.getCount2(), serviceStringEntry4.getCount3(), serviceStringEntry4.getCount6(), false, false);
            }
         } catch (Throwable throwable2) {
            CrashReport crashReport2 = CrashReport.resolveCrashReport(throwable2, "Stitching texture atlas");
            CrashReportCategory crashReportCategory2 = crashReport2.resolveCrashReportCategory("Texture being stitched together");
            crashReportCategory2.add("Atlas path", this.labelText);
            crashReportCategory2.add("Sprite", serviceStringEntry4);
            throw new SecondaryRuntimeExceptionAdapter(crashReport2);
         }

         if (serviceStringEntry4.isActive()) {
            serviceStringEntry4.applyIndex3(this.list.size());
            this.list.add(serviceStringEntry4);
         }
      }

      for (Object serviceStringEntry5Obj : map3.values()) { ServiceStringEntry serviceStringEntry5 = (ServiceStringEntry)serviceStringEntry5Obj;
         serviceStringEntry5.applyServiceStringEntry(this.serviceStringEntry);
      }

      BuildManager.applyText("Animated sprites: " + this.list.size());
      if (BuildManager.isActive46()) {
         count = extendedSetEntry.getCount();
         int count18 = extendedSetEntry.getCount3();

         for (Object serviceStringEntry6Obj : extendedSetEntry.getList()) { ServiceStringEntry serviceStringEntry6 = (ServiceStringEntry)serviceStringEntry6Obj;
            serviceStringEntry6.count12 = count;
            serviceStringEntry6.count9 = count18;
            serviceStringEntry6.count4 = this.count6;
            ServiceStringEntry serviceStringEntry7 = serviceStringEntry6.serviceStringEntry3;
            if (serviceStringEntry7 != null) {
               if (serviceStringEntry7.getCount() <= 0) {
                  serviceStringEntry7.applyIndexByIndex(serviceStringEntry6.getCount());
                  serviceStringEntry7.applyIndex2(serviceStringEntry6.getCount2());
                  serviceStringEntry7.applyIndex(serviceStringEntry6.getCount(), serviceStringEntry6.getCount2(), 0, 0, false);
                  serviceStringEntry7.updateState4();
                  List list3 = serviceStringEntry6.addAll();
                  serviceStringEntry7.applyList(list3);
                  serviceStringEntry7.setBaseListEntry(serviceStringEntry6.getBaseListEntry());
               }

               serviceStringEntry7.count12 = count;
               serviceStringEntry7.count9 = count18;
               serviceStringEntry7.count4 = this.count6;
               serviceStringEntry7.applyIndex3(serviceStringEntry6.getCount11());
               serviceStringEntry6.updateState6();
               boolean flag = false;

               try {
                  SpecialLoggerEntry.applyIndexArray(serviceStringEntry7.get(0), serviceStringEntry7.getCount(), serviceStringEntry7.getCount2(), serviceStringEntry7.getCount3(), serviceStringEntry7.getCount6(), flag, true);
               } catch (Exception exception3) {
                  BuildManager.applyText4("Error uploading sprite single: " + serviceStringEntry7 + ", parent: " + serviceStringEntry6);
                  exception3.printStackTrace();
               }
            }
         }

         BuildManager.getMc().getTextureManager().applyResourceLocation3(resourceLocation);
      }

      this.applyIndex(extendedSetEntry.getCount(), extendedSetEntry.getCount3());
      if (BuildManager.matchesTarget(System.getProperty("saveTextureMap"), "true")) {
         BuildManager.applyText4("Exporting texture map: " + this.labelText);
         InternalPngManager.applyText("debug/" + this.labelText.replaceAll("/", "_"), this.getCount(), this.count6, extendedSetEntry.getCount(), extendedSetEntry.getCount3());
      }
   }

   @Override
   public void applyIResourceManager(IResourceManager iResourceManager) throws IOException {
      if (this.simpleListener != null) {
         this.applyIResourceManager(iResourceManager, this.simpleListener);
      }
   }

   public String getLabelText() {
      return this.labelText;
   }

   private boolean matchesText(String text) {
      String text2 = text.toLowerCase();
      return text2.startsWith("mcpatcher/") || text2.startsWith("optifine/");
   }

   private int computeCount(Map map3, IResourceManager iResourceManager, int count) {
      HashMap map4 = new HashMap();

      for (Object entryObj : map3.entrySet()) { Entry entry = (Entry)entryObj;
         ServiceStringEntry value3 = (ServiceStringEntry)entry.getValue();
         ResourceLocation location = new ResourceLocation(value3.getLabelText3());
         ResourceLocation location2 = this.resolveResourceLocation(location);
         if (!value3.matchesIResourceManager(iResourceManager, location)) {
            try {
               IResource iResource = iResourceManager.resolveIResourceByResourceLocation(location2);
               if (iResource != null) {
                  InputStream inputStream = iResource.getInputStream();
                  if (inputStream != null) {
                     Dimension dimension = InternalPngManager.resolveDimension(inputStream, "png");
                     inputStream.close();
                     if (dimension != null) {
                        int count10 = MathHelper.processAction(dimension.width);
                        if (!map4.containsKey(count10)) {
                           map4.put(count10, 1);
                        } else {
                           int count11 = (Integer)map4.get(count10);
                           map4.put(count10, count11 + 1);
                        }
                     }
                  }
               }
            } catch (Exception exception) {
            }
         }
      }

      int count12 = 0;
      Set keys = map4.keySet();
      TreeSet treeSet = new TreeSet(keys);

      for (Object count13Obj : treeSet) { int count13 = (Integer)count13Obj;
         int count14 = (Integer)map4.get(count13);
         count12 += count14;
      }

      int count15 = 16;
      int count16 = 0;
      int count17 = count12 * count / 100;

      for (Object count18Obj : treeSet) { int count18 = (Integer)count18Obj;
         int count19 = (Integer)map4.get(count18);
         count16 += count19;
         if (count18 > count15) {
            count15 = count18;
         }

         if (count16 > count17) {
            return count15;
         }
      }

      return count15;
   }

   public ListenerAdapter27(String text, boolean flag) {
      this(text, (SimpleListener)null, flag);
   }

   public ListenerAdapter27(String text, SimpleListener simpleListener2) {
      this(text, simpleListener2, false);
   }

   public ListenerAdapter27(String text) {
      this(text, (SimpleListener)null);
   }

   public ListenerAdapter27(String text, SimpleListener simpleListener2, boolean flag) {
      this.list = Lists.newArrayList();
      this.map2 = Maps.newHashMap();
      this.map = Maps.newHashMap();
      this.serviceStringEntry = new ServiceStringEntry("missingno");
      this.labelText = text;
      this.simpleListener = simpleListener2;
      this.enabled6 = flag && enabled5;
   }
}
