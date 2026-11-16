package client.util;

import client.setting.OptionManager;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class BezierCounter {
   public static String resolveTextByText(String text) throws IOException {
      StringReader stringReader = new StringReader(text);
      BufferedReader reader = new BufferedReader(stringReader);
      AimAggregator aimAggregator = new AimAggregator();
      StringBuilder builder = new StringBuilder();

      while (true) {
         String line = reader.readLine();
         if (line == null) {
            return builder.toString();
         }

         if (aimAggregator.matchesText(line) && !AimAggregator.matchesTextByText(line)) {
            builder.append(line);
            builder.append("\n");
         }
      }
   }

   private static List getList() {
      ArrayList list = new ArrayList();
      OptionManager[] optionManagerArray = PreEndHandManager.getOptionManagerArray();

      for (int index = 0; index < optionManagerArray.length; index++) {
         OptionManager optionManager = optionManagerArray[index];
         String text = optionManager.getText2();
         if (text != null && text.startsWith("#")) {
            list.add(optionManager);
         }
      }

      return list;
   }

   private static String resolveText(String text) throws IOException {
      StringBuilder builder = new StringBuilder();
      ArrayList list = null;
      StringReader stringReader = new StringReader(text);
      BufferedReader reader = new BufferedReader(stringReader);

      while (true) {
         String line = reader.readLine();
         if (line == null) {
            return builder.toString();
         }

         if (AimAggregator.matchesTextByText(line)) {
            if (builder.length() == 0) {
               builder.append(BezierEstimator.getText4());
            }

            if (list == null) {
               list = new ArrayList<>(Arrays.asList(BezierEstimator.getFovFactoryArray()));
            }

            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
               FovFactory fovFactory = (FovFactory)iterator.next();
               if (line.contains(fovFactory.getLabelText())) {
                  builder.append(fovFactory.getText());
                  builder.append("\n");
                  iterator.remove();
               }
            }
         }
      }
   }

   public static InputStream resolveInputStream(InputStream inputStream, String text) throws IOException {
      String text2 = BuildManager.resolveText(inputStream, "ASCII");
      String text3 = resolveText(text2);
      if (!text3.isEmpty()) {
         String text4 = text3 + text2;
         if (PreEndHandManager.enabled) {
            PreEndHandManager.applyText(text.replace(':', '/') + ".pre", text4);
         }

         text2 = resolveTextByText(text4);
      }

      if (PreEndHandManager.enabled) {
         PreEndHandManager.applyText(text.replace(':', '/'), text2);
      }

      byte[] bytes = text2.getBytes("ASCII");
      return new ByteArrayInputStream(bytes);
   }
}
