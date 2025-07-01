package com.blazemeter.jmeter.commons;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlazemeterLabsLogo extends JLabel {

  private static final Logger LOG = LoggerFactory.getLogger(BlazemeterLabsLogo.class);
  private static final ImageIcon BLAZEMETER_LOGO = ThemedIcon
      .fromResourceName("blazemeter-by-perforce-logo.png");

  public BlazemeterLabsLogo(String pageURL) {
    super(BLAZEMETER_LOGO);
    setBrowseOnClick(pageURL);
  }

  public BlazemeterLabsLogo(String pageURL, int width, int height) {
    super();
    setLogoSize(width, height);
    setBrowseOnClick(pageURL);
  }

  private void setBrowseOnClick(final String url) {
    this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    this.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent mouseEvent) {
        if (Desktop.isDesktopSupported()) {
          try {
            Desktop.getDesktop().browse(new URI(url));
          } catch (URISyntaxException | IOException exception) {
            BlazemeterLabsLogo.LOG.error("Problem when accessing repository", exception);
          }
        }
      }
    });
  }

  private void setLogoSize(int width, int height) {
    Image scaledImg = BLAZEMETER_LOGO.getImage()
        .getScaledInstance(width, height, Image.SCALE_SMOOTH);
    setIcon(new ImageIcon(scaledImg));
    setPreferredSize(new Dimension(width, height));
    setMinimumSize(new Dimension(width, height));
    setMaximumSize(new Dimension(width, height));
    setHorizontalAlignment(CENTER);
    setVerticalAlignment(CENTER);
    revalidate();
    repaint();
  }
}
