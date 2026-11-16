package client.util;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class DebugConsoleJFrame extends JFrame {
   public static boolean enabled2;
   public JTextPane jTextPane;
   public static DebugConsoleJFrame debugConsoleJFrame = null;
   public JPanel jPanel;
   public JTextField jTextField;

   private static void updateState3() {
      try {
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
         debugConsoleJFrame = new DebugConsoleJFrame();
         debugConsoleJFrame.updateState4();
         debugConsoleJFrame.setTitle("Debug Console");
         URL resource = Thread.currentThread().getContextClassLoader().getResource("assets/minecraft/morecosmetics/gui/logo/icon.png");
         debugConsoleJFrame.setIconImage(new ImageIcon(resource).getImage());
         debugConsoleJFrame.setResizable(false);
         debugConsoleJFrame.setVisible(true);
         debugConsoleJFrame.toFront();
      } catch (HeadlessException headlessException) {
         enabled2 = true;
         MoreCosmeticsManager.applyText("Failed to open console! Running headless.");
      } catch (Exception exception) {
         MoreCosmeticsManager.applyThrowable(exception);
      }
   }

   private void updateState4() {
      this.setBounds(100, 100, 1100, 600);
      this.jPanel = new JPanel();
      this.jPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      this.jPanel.setLayout((LayoutManager)null);
      this.jPanel.setBackground(new Color(25, 26, 31));
      this.jTextPane = new JTextPane();
      JScrollPane jScrollPane = new JScrollPane(this.jTextPane);
      jScrollPane.setBounds(10, 10, 1064, 509);
      jScrollPane.setBackground(new Color(19, 18, 23));
      jScrollPane.setBorder((Border)null);
      this.jTextPane.setBorder((Border)null);
      this.jTextPane.setEditable(false);
      this.jTextPane.setBackground(new Color(19, 18, 23));
      this.jTextPane.setForeground(Color.WHITE);
      this.jPanel.add(jScrollPane);
      this.jTextField = new JTextField();
      this.jTextField.setBorder((Border)null);
      this.jTextField.setForeground(Color.WHITE);
      this.jTextField.setBackground(new Color(19, 18, 23));
      this.jTextField.setBounds(10, 530, 1064, 20);
      this.jPanel.add(this.jTextField);
      this.setContentPane(this.jPanel);
   }

   public static void updateState() {
      if (enabled2) {
         MoreCosmeticsManager.applyText("Failed to open console! Running headless.");
      } else if (debugConsoleJFrame != null) {
         if (!debugConsoleJFrame.isVisible()) {
            EventQueue.invokeLater(DebugConsoleJFrame::updateState2);
         }
      } else {
         EventQueue.invokeLater(DebugConsoleJFrame::updateState3);
      }
   }

   private static void updateState2() {
      debugConsoleJFrame.setVisible(true);
      debugConsoleJFrame.toFront();
   }

   public static void applyText(String text, SplineAggregator splineAggregator) {
      if (enabled2 && splineAggregator == SplineAggregator.DEBUG) {
         MoreCosmeticsManager.applyText(splineAggregator.getLabelText() + " " + text);
      } else if (debugConsoleJFrame != null) {
         SwingUtilities.invokeLater(() -> DebugConsoleJFrame.applySplineAggregator(splineAggregator, text));
      }
   }

   private DebugConsoleJFrame() {
   }

   private static void applySplineAggregator(SplineAggregator splineAggregator, String text) {
      StyleContext defaultStyleContext = StyleContext.getDefaultStyleContext();
      AttributeSet attributeSet = defaultStyleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, splineAggregator.getColor());
      AttributeSet attributeSet2 = defaultStyleContext.addAttribute(attributeSet, StyleConstants.FontFamily, "Consolas");
      AttributeSet attributeSet3 = defaultStyleContext.addAttribute(attributeSet2, StyleConstants.Alignment, 3);
      int length = debugConsoleJFrame.jTextPane.getDocument().getLength();
      debugConsoleJFrame.jTextPane.setEditable(true);
      debugConsoleJFrame.jTextPane.setCaretPosition(length);
      debugConsoleJFrame.jTextPane.setCharacterAttributes(attributeSet3, false);
      debugConsoleJFrame.jTextPane.replaceSelection(splineAggregator.getLabelText() + " " + text + "\n");
      debugConsoleJFrame.jTextPane.setEditable(false);
   }
}
