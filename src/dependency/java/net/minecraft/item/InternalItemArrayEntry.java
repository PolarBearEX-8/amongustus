package net.minecraft.item;

import client.util.AuxiliaryClassEntry;
import client.util.ClassEntry2Entry;
import client.util.EnhancedLoggerEntry;
import client.util.FovBuffer;
import client.util.FovRegistry;
import client.util.HitBoxConverter;
import client.util.HitBoxFormatter;
import client.util.HitBoxParser;
import client.util.HitBoxReader;
import client.util.HitBoxValidator;
import client.util.HitBoxWriter;
import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class InternalItemArrayEntry {
   public static final AuxiliaryClassEntry[] auxiliaryClassEntryArray = new AuxiliaryClassEntry[]{
      new AuxiliaryClassEntry(IronPickaxe.class, 30, 0, true),
      new AuxiliaryClassEntry(GoldenEssence.class, 10, 4),
      new AuxiliaryClassEntry(GoldenRelic.class, 10, 4),
      new AuxiliaryClassEntry(GoldenToken.class, 10, 3),
      new AuxiliaryClassEntry(GoldenSpear.class, 5, 2),
      new AuxiliaryClassEntry(HitBoxReader.class, 5, 1)
   };
   public static final AuxiliaryClassEntry[] auxiliaryClassEntryArray2 = new AuxiliaryClassEntry[]{
      new AuxiliaryClassEntry(FovRegistry.class, 25, 0, true),
      new AuxiliaryClassEntry(HitBoxWriter.class, 15, 5),
      new AuxiliaryClassEntry(HitBoxConverter.class, 5, 10),
      new AuxiliaryClassEntry(HitBoxParser.class, 5, 10),
      new AuxiliaryClassEntry(FovBuffer.class, 10, 3, true),
      new AuxiliaryClassEntry(GoldenScythe.class, 7, 2),
      new AuxiliaryClassEntry(HitBoxFormatter.class, 5, 2)
   };

   public static void updateState() {
      EnhancedLoggerEntry.applyClassByClass(GoldenEssence.class, "NeBCr");
      EnhancedLoggerEntry.applyClassByClass(HitBoxValidator.class, "NeBEF");
      EnhancedLoggerEntry.applyClassByClass(IronPickaxe.class, "NeBS");
      EnhancedLoggerEntry.applyClassByClass(FovBuffer.class, "NeCCS");
      EnhancedLoggerEntry.applyClassByClass(GoldenScythe.class, "NeCTB");
      EnhancedLoggerEntry.applyClassByClass(HitBoxReader.class, "NeCE");
      EnhancedLoggerEntry.applyClassByClass(HitBoxWriter.class, "NeSCSC");
      EnhancedLoggerEntry.applyClassByClass(HitBoxParser.class, "NeSCLT");
      EnhancedLoggerEntry.applyClassByClass(FovRegistry.class, "NeSC");
      EnhancedLoggerEntry.applyClassByClass(HitBoxConverter.class, "NeSCRT");
      EnhancedLoggerEntry.applyClassByClass(HitBoxFormatter.class, "NeCSR");
      EnhancedLoggerEntry.applyClassByClass(GoldenSpear.class, "NeMT");
      EnhancedLoggerEntry.applyClassByClass(GoldenRelic.class, "NeRC");
      EnhancedLoggerEntry.applyClassByClass(GoldenToken.class, "NeSR");
      EnhancedLoggerEntry.applyClassByClass(ClassEntry2Entry.class, "NeStart");
   }

   private static TertiaryItemEntryAdapter resolveTertiaryItemEntryAdapterByAuxiliaryClassEn(
      AuxiliaryClassEntry auxiliaryClassEntry, List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4
   ) {
      Class memberRef = auxiliaryClassEntry.memberRef;
      Object entry = null;
      if (memberRef == IronPickaxe.class) {
         entry = IronPickaxe.resolveIronPickaxe(list, random, count, count2, count3, enumFacing, count4);
      } else if (memberRef == GoldenEssence.class) {
         entry = GoldenEssence.resolveGoldenEssence(list, random, count, count2, count3, enumFacing, count4);
      } else if (memberRef == GoldenRelic.class) {
         entry = GoldenRelic.resolveGoldenRelic(list, random, count, count2, count3, enumFacing, count4);
      } else if (memberRef == GoldenToken.class) {
         entry = GoldenToken.resolveGoldenToken(list, random, count, count2, count3, count4, enumFacing);
      } else if (memberRef == GoldenSpear.class) {
         entry = GoldenSpear.resolveGoldenSpear(list, random, count, count2, count3, count4, enumFacing);
      } else if (memberRef == HitBoxReader.class) {
         entry = HitBoxReader.resolveHitBoxReader(list, random, count, count2, count3, enumFacing, count4);
      } else if (memberRef == FovRegistry.class) {
         entry = FovRegistry.resolveFovRegistry(list, random, count, count2, count3, enumFacing, count4);
      } else if (memberRef == HitBoxConverter.class) {
         entry = HitBoxConverter.resolveHitBoxConverter(list, random, count, count2, count3, enumFacing, count4);
      } else if (memberRef == HitBoxParser.class) {
         entry = HitBoxParser.resolveHitBoxParser(list, random, count, count2, count3, enumFacing, count4);
      } else if (memberRef == FovBuffer.class) {
         entry = FovBuffer.resolveFovBuffer(list, random, count, count2, count3, enumFacing, count4);
      } else if (memberRef == GoldenScythe.class) {
         entry = GoldenScythe.resolveGoldenScythe(list, random, count, count2, count3, enumFacing, count4);
      } else if (memberRef == HitBoxWriter.class) {
         entry = HitBoxWriter.resolveHitBoxWriter(list, random, count, count2, count3, enumFacing, count4);
      } else if (memberRef == HitBoxFormatter.class) {
         entry = HitBoxFormatter.resolveHitBoxFormatter(list, random, count, count2, count3, enumFacing, count4);
      }

      return (TertiaryItemEntryAdapter)entry;
   }

   public static AuxiliaryClassEntry[] getAuxiliaryClassEntryArray() {
      return auxiliaryClassEntryArray;
   }

   public static TertiaryItemEntryAdapter resolveTertiaryItemEntryAdapter(
      AuxiliaryClassEntry auxiliaryClassEntry, List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4
   ) {
      return resolveTertiaryItemEntryAdapterByAuxiliaryClassEn(auxiliaryClassEntry, list, random, count, count2, count3, enumFacing, count4);
   }

   public static AuxiliaryClassEntry[] getAuxiliaryClassEntryArray2() {
      return auxiliaryClassEntryArray2;
   }
}
