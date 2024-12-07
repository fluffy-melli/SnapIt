package SnapIt.display

import SnapIt.capture.ScreenshotCaptureArea
import SnapIt.capture.captureScreenshot
import java.awt.Cursor
import java.awt.Graphics
import java.awt.Toolkit
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JFrame
import javax.swing.JPanel

fun createResize() {
    val frame = JFrame("SnapIt")
    val screenshot = captureScreenshot()
    val captureArea = ScreenshotCaptureArea(frame)
    val screenWidth = Toolkit.getDefaultToolkit().screenSize.width
    val screenHeight = Toolkit.getDefaultToolkit().screenSize.height
    val panel = object : JPanel() {
        init {
            setSize(screenWidth, screenHeight)
            addMouseListener(captureArea)
            addMouseMotionListener(captureArea)
        }
        override fun paintComponent(g: Graphics) {
            super.paintComponent(g)
            g.drawImage(screenshot, 0, 0, null)
            captureArea.paintSelection(g)
        }
    }
    panel.addKeyListener(object : KeyAdapter() {
        override fun keyPressed(e: KeyEvent) {
            if (e.keyCode == KeyEvent.VK_ESCAPE) {
                frame.dispose()
            }
        }
    })
    panel.isFocusable = true
    panel.requestFocusInWindow()
    frame.setSize(screenWidth, screenHeight)
    frame.add(panel)
    frame.isUndecorated = true
    frame.isVisible = true
    frame.cursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR)
    frame.isAlwaysOnTop = true
    frame.toFront()
}