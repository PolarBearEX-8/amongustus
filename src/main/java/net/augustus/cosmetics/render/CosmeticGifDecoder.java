package net.augustus.cosmetics.render;

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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CosmeticGifDecoder {

   public static CosmeticGifFrame[] decodeGifFrames(InputStream in) throws IOException {
      ArrayList list = new ArrayList(2);
      ImageReader imageReader = ImageIO.getImageReadersByFormatName("gif").next();
      imageReader.setInput(ImageIO.createImageInputStream(in));
      int n = -1;
      int n2 = -1;
      IIOMetadata streamMetadata = imageReader.getStreamMetadata();
      if (streamMetadata != null) {
         NodeList elementsByTagName = ((IIOMetadataNode)streamMetadata.getAsTree(streamMetadata.getNativeMetadataFormatName())).getElementsByTagName("LogicalScreenDescriptor");
         if (elementsByTagName != null && elementsByTagName.getLength() > 0) {
            IIOMetadataNode ioMetadataNode = (IIOMetadataNode)elementsByTagName.item(0);
            if (ioMetadataNode != null) {
               n = Integer.parseInt(ioMetadataNode.getAttribute("logicalScreenWidth"));
               n2 = Integer.parseInt(ioMetadataNode.getAttribute("logicalScreenHeight"));
            }
         }
      }

      BufferedImage image = null;
      Graphics2D g2d = null;
      int n3 = 0;

      while (true) {
         BufferedImage image2;
         try {
            image2 = imageReader.read(n3);
         } catch (IndexOutOfBoundsException e) {
            if (g2d == null) {
               imageReader.dispose();
               in.close();
               throw new IOException("Failed to parse gif: Graphics is null!");
            }

            g2d.dispose();
            imageReader.dispose();
            in.close();
            return (CosmeticGifFrame[])list.toArray(new CosmeticGifFrame[list.size()]);
         }

         if (n == -1 || n2 == -1) {
            n = image2.getWidth();
            n2 = image2.getHeight();
         }

         IIOMetadataNode asTree2 = (IIOMetadataNode)imageReader.getImageMetadata(n3).getAsTree("javax_imageio_gif_image_1.0");
         IIOMetadataNode ioMetadataNode2 = (IIOMetadataNode)asTree2.getElementsByTagName("GraphicControlExtension").item(0);
         int n4 = Integer.valueOf(ioMetadataNode2.getAttribute("delayTime"));
         String attribute = ioMetadataNode2.getAttribute("disposalMethod");
         int n5 = 0;
         int n6 = 0;
         if (image == null) {
            image = new BufferedImage(n, n2, 2);
            g2d = image.createGraphics();
            g2d.setBackground(new Color(0, 0, 0, 0));
         } else {
            NodeList childNodes = asTree2.getChildNodes();

            for (int i = 0; i < childNodes.getLength(); i++) {
               Node node = childNodes.item(i);
               if (node.getNodeName().equals("ImageDescriptor")) {
                  NamedNodeMap attributes = node.getAttributes();
                  n5 = Integer.valueOf(attributes.getNamedItem("imageLeftPosition").getNodeValue());
                  n6 = Integer.valueOf(attributes.getNamedItem("imageTopPosition").getNodeValue());
               }
            }
         }

         g2d.drawImage(image2, n5, n6, (ImageObserver)null);
         BufferedImage image3 = new BufferedImage(
            image.getColorModel(), image.copyData((WritableRaster)null), image.isAlphaPremultiplied(), (Hashtable<?, ?>)null
         );
         list.add(new CosmeticGifFrame(image3, n4, attribute));
         short s = 2699;
         if (!attribute.equals("restoreToPrevious")) {
            if (attribute.equals("restoreToBackgroundColor")) {
               g2d.clearRect(n5, n6, image2.getWidth(), image2.getHeight());
            }
         } else {
            BufferedImage image4 = null;

            for (int j = n3 - 1; j >= 0; j--) {
               CosmeticGifFrame cosmeticGifFrame = (CosmeticGifFrame)list.get(j);
               if (!cosmeticGifFrame.getDisposalMethod().equals("restoreToPrevious") || n3 == 0) {
                  cosmeticGifFrame = (CosmeticGifFrame)list.get(j);
                  image4 = cosmeticGifFrame.getImage();
                  break;
               }
            }

            image = new BufferedImage(image4.getColorModel(), image4.copyData((WritableRaster)null), image4.isAlphaPremultiplied(), (Hashtable<?, ?>)null);
            g2d = image.createGraphics();
            g2d.setBackground(new Color(0, 0, 0, 0));
         }

         n3++;
      }
   }
}
