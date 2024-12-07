package SnapIt.capture

import sun.java2d.loops.SurfaceType.RadialGradientPaint
import java.awt.*
import java.awt.image.BufferedImage

fun captureScreenshot(): BufferedImage {
    val robot = Robot()
    val screenRect = Rectangle(Toolkit.getDefaultToolkit().screenSize)
    return robot.createScreenCapture(screenRect)
}