package SnapIt.capture

import SnapIt.display.createWindow
import java.awt.Color
import java.awt.Graphics
import java.awt.Rectangle
import java.awt.Robot
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JFrame
import kotlin.math.min

class ScreenshotCaptureArea(val frame: JFrame) : MouseAdapter() {
    private var startX = 0
    private var startY = 0
    private var endX = 0
    private var endY = 0
    private var selectionRect: Rectangle? = null

    override fun mousePressed(e: MouseEvent) {
        startX = e.x
        startY = e.y
    }

    override fun mouseDragged(e: MouseEvent) {
        endX = e.x
        endY = e.y
        selectionRect = Rectangle(min(startX, endX), min(startY, endY), Math.abs(startX - endX), Math.abs(startY - endY))
        e.component.repaint()
    }

    override fun mouseReleased(e: MouseEvent) {
        captureSelection(selectionRect)
        frame.dispose()
    }

    private fun captureSelection(rect: Rectangle?) {
        if (rect != null) {
            val robot = Robot()
            val image = robot.createScreenCapture(rect)
            createWindow(image)
        }
    }

    fun paintSelection(g: Graphics) {
        if (selectionRect != null) {
            g.color = Color.RED
            g.drawRect(selectionRect!!.x - 2, selectionRect!!.y - 2, selectionRect!!.width + 2, selectionRect!!.height + 2)
        }
    }
}