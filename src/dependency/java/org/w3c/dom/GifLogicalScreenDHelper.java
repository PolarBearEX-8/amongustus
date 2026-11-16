package org.w3c.dom;

import client.util.BufferedImageEntry;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;

public class GifLogicalScreenDHelper {
   public static BufferedImageEntry[] resolveBufferedImageEntryArray(InputStream inputStream) throws IOException {
      ArrayList list = new ArrayList(2);
      ImageReader imageReader = ImageIO.getImageReadersByFormatName("gif").next();
      imageReader.setInput(ImageIO.createImageInputStream(inputStream));
      int count = -1;
      int count2 = -1;
      IIOMetadata streamMetadata = imageReader.getStreamMetadata();
      if (streamMetadata != null) {
         NodeList elementsByTagName = ((IIOMetadataNode)streamMetadata.getAsTree(streamMetadata.getNativeMetadataFormatName())).getElementsByTagName("LogicalScreenDescriptor");
         if (elementsByTagName != null && elementsByTagName.getLength() > 0) {
            IIOMetadataNode iIOMetadataNode = (IIOMetadataNode)elementsByTagName.item(0);
            if (iIOMetadataNode != null) {
               count = Integer.parseInt(iIOMetadataNode.getAttribute("logicalScreenWidth"));
               count2 = Integer.parseInt(iIOMetadataNode.getAttribute("logicalScreenHeight"));
            }
         }
      }

      BufferedImage bufferedImage = null;
      Graphics2D graphics2D = null;
      int count3 = 0;

      while (true) {
         BufferedImage bufferedImage2;
         try {
            bufferedImage2 = imageReader.read(count3);
         } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            if (graphics2D == null) {
               imageReader.dispose();
               inputStream.close();
               throw new IOException("Failed to parse gif: Graphics is null!");
            }

            graphics2D.dispose();
            imageReader.dispose();
            inputStream.close();
            return ((BufferedImageEntry[])list.toArray(new BufferedImageEntry[list.size()]));
         }

         if (count == -1 || count2 == -1) {
            count = bufferedImage2.getWidth();
            count2 = bufferedImage2.getHeight();
         }

         IIOMetadataNode asTree = (IIOMetadataNode)imageReader.getImageMetadata(count3).getAsTree("javax_imageio_gif_image_1.0");
         IIOMetadataNode iIOMetadataNode2 = (IIOMetadataNode)asTree.getElementsByTagName("GraphicControlExtension").item(0);
         int count4 = Integer.valueOf(iIOMetadataNode2.getAttribute("delayTime"));
         String attribute = iIOMetadataNode2.getAttribute("disposalMethod");
         int count5 = 0;
         int count6 = 0;
         if (bufferedImage == null) {
            bufferedImage = new BufferedImage(count, count2, 2);
            graphics2D = bufferedImage.createGraphics();
            graphics2D.setBackground(new Color(0, 0, 0, 0));
         } else {
            NodeList childNodes = asTree.getChildNodes();

            for (int index = 0; index < childNodes.getLength(); index++) {
               Node node = childNodes.item(index);
               if (node.getNodeName().equals("ImageDescriptor")) {
                  NamedNodeMap attributes = node.getAttributes();
                  count5 = Integer.valueOf(attributes.getNamedItem("imageLeftPosition").getNodeValue());
                  count6 = Integer.valueOf(attributes.getNamedItem("imageTopPosition").getNodeValue());
               }
            }
         }

         graphics2D.drawImage(bufferedImage2, count5, count6, (ImageObserver)null);
         BufferedImage bufferedImage3 = new BufferedImage(
            bufferedImage.getColorModel(), bufferedImage.copyData((WritableRaster)null), bufferedImage.isAlphaPremultiplied(), (Hashtable<?, ?>)null
         );
         list.add(new BufferedImageEntry(bufferedImage3, count4, attribute));
         if (!attribute.equals("restoreToPrevious")) {
            if (attribute.equals("restoreToBackgroundColor")) {
               graphics2D.clearRect(count5, count6, bufferedImage2.getWidth(), bufferedImage2.getHeight());
            }
         } else {
            BufferedImage bufferedImage4 = null;

            for (int index2 = count3 - 1; index2 >= 0; index2--) {
               if (!((BufferedImageEntry)list.get(index2)).getLabelText().equals("restoreToPrevious") || count3 == 0) {
                  bufferedImage4 = ((BufferedImageEntry)list.get(index2)).getBufferedImage();
                  break;
               }
            }

            bufferedImage = new BufferedImage(bufferedImage4.getColorModel(), bufferedImage4.copyData((WritableRaster)null), bufferedImage4.isAlphaPremultiplied(), (Hashtable<?, ?>)null);
            graphics2D = bufferedImage.createGraphics();
            graphics2D.setBackground(new Color(0, 0, 0, 0));
         }

         count3++;
      }
   }
}
