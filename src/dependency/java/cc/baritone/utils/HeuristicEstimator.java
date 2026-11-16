package cc.baritone.utils;

import cc.SettingsUtil;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import net.minecraft.util.ResourceLocation;

public class HeuristicEstimator {
   public Stream stream;

   private static boolean matchesAuxiliaryStringEntry(AuxiliaryStringEntry auxiliaryStringEntry) {
      return !auxiliaryStringEntry.isEnabled();
   }

   public HeuristicEstimator resolveHeuristicEstimatorByTextArray(String[] textArray) {
      return this.concat(Stream.of(textArray));
   }

   public HeuristicEstimator resolveHeuristicEstimatorByText(String text) {
      return this.filter(item -> matchesText(text, (String)item));
   }

   public String[] toArray() {
      return ((String[])this.stream.toArray(HeuristicEstimator::resolveTextArray));
   }

   public HeuristicEstimator resolveHeuristicEstimatorByClass(Class clazz) {
      return this.concatByStream(Stream.of((Enum[])clazz.getEnumConstants()).map(Enum::name).map(String::toLowerCase));
   }

   public HeuristicEstimator resolveHeuristicEstimator(GenericListener genericListener) {
      return this.concat(genericListener.getDequeEntry().getStream().flatMap((item -> HeuristicEstimator.resolveStream((DynamicListener)item))).distinct());
   }

   public HeuristicEstimator getHeuristicEstimatorAsHeuristicEstimat() {
      return this.concat(
         SettingsTxtManager.getSettings()
            .allSettings
            .stream()
            .filter(item -> HeuristicEstimator.matchesAuxiliaryStringEntry((AuxiliaryStringEntry)item))
            .map(entry -> ((AuxiliaryStringEntry)entry).getLabelText())
            .sorted(String.CASE_INSENSITIVE_ORDER)
      );
   }

   private static String[] resolveTextArray(int count) {
      return new String[count];
   }

   private static Stream resolveStream(DynamicListener dynamicListener) {
      return dynamicListener.getListAsList().stream();
   }

   public Stream getStream() {
      return this.stream;
   }

   public HeuristicEstimator resolveHeuristicEstimator(Class clazz) {
      return this.concat(Stream.of((Enum[])clazz.getEnumConstants()).map(Enum::name).map(String::toLowerCase));
   }

   public HeuristicEstimator getHeuristicEstimator3() {
      return this.concat(
         SettingsTxtManager.getSettings()
            .getAllValuesByType(Boolean.class)
            .stream()
            .map(entry -> ((AuxiliaryStringEntry)entry).getLabelText())
            .sorted(String.CASE_INSENSITIVE_ORDER)
      );
   }

   public HeuristicEstimator sorted(Comparator comparator) {
      this.stream = this.stream.sorted(comparator);
      return this;
   }

   public HeuristicEstimator getHeuristicEstimator2() {
      return this.sorted(String.CASE_INSENSITIVE_ORDER);
   }

   public HeuristicEstimator resolveHeuristicEstimator(String text) {
      return this.resolveHeuristicEstimatorByText(new ResourceLocation(text).toString());
   }

   public HeuristicEstimator(List list) {
      this.stream = list.stream();
   }

   public HeuristicEstimator concat(Stream stream2) {
      this.stream = Stream.concat(this.stream, stream2);
      return this;
   }

   public HeuristicEstimator resolveHeuristicEstimator(String[] textArray) {
      return this.concatByStream(Stream.of(textArray));
   }

   public HeuristicEstimator concatByStream(Stream stream2) {
      this.stream = Stream.concat(stream2, this.stream);
      return this;
   }

   public HeuristicEstimator() {
      this.stream = Stream.empty();
   }

   public HeuristicEstimator(String[] textArray) {
      this.stream = Stream.of(textArray);
   }

   public HeuristicEstimator getHeuristicEstimator() {
      return this.concat(
         SettingsUtil.modifiedSettings(SettingsTxtManager.getSettings())
            .stream()
            .map(entry -> ((AuxiliaryStringEntry)entry).getLabelText())
            .sorted(String.CASE_INSENSITIVE_ORDER)
      );
   }

   public HeuristicEstimator filter(Predicate predicate) {
      this.stream = this.stream.filter(predicate);
      return this;
   }

   private static boolean matchesText(String text, String text2) {
      return text2.toLowerCase(Locale.US).startsWith(text.toLowerCase(Locale.US));
   }

   public HeuristicEstimator map(Function mapper) {
      this.stream = this.stream.map(mapper);
      return this;
   }
}
