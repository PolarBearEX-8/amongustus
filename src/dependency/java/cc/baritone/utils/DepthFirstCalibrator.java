package cc.baritone.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public enum DepthFirstCalibrator implements Listener10Extension {
   INSTANCE;

   public static String labelText = "[kKmM]";
   public static Pattern pattern = Pattern.compile("^(~?)([+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)(" + labelText + "?)|)$");

   public Double resolveDouble(HeuristicSampler heuristicSampler, Double offset) throws ExceptionAdapter {
      Double offset2 = offset;
      if (offset == null) {
         offset2 = 0.0;
      }

      Matcher matcher = pattern.matcher(heuristicSampler.getDepthFirstManager().getText());
      if (!matcher.matches()) {
         throw new IllegalArgumentException("pattern doesn't match");
      } else {
         boolean flag = !matcher.group(1).isEmpty();
         double offset3 = matcher.group(2).isEmpty() ? 0.0 : Double.parseDouble(matcher.group(2).replaceAll(labelText, ""));
         if (matcher.group(2).toLowerCase().contains("k")) {
            offset3 *= 1000.0;
         }

         if (matcher.group(2).toLowerCase().contains("m")) {
            offset3 *= 1000000.0;
         }

         return flag ? offset2 + offset3 : offset3;
      }
   }

   @Override
   public Object resolveTarget(HeuristicSampler heuristicSampler, Object entry) throws ExceptionAdapter {
      return this.resolveDouble(heuristicSampler, (Double)entry);
   }

   @Override
   public Stream empty(HeuristicSampler heuristicSampler) throws ExceptionAdapter {
      DepthFirstManager depthFirstManager = heuristicSampler.getDepthFirstManager();
      return !depthFirstManager.size(2) && depthFirstManager.getText().matches("^(~|$)") ? Stream.of("~") : Stream.empty();
   }
}
