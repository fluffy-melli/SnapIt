package SnapIt.capture

import java.awt.*
import java.awt.image.BufferedImage

val borderThickness = 2

fun resizeImage(image: BufferedImage, width: Int, height: Int): BufferedImage {
    val resizedImage = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    val g2d = resizedImage.createGraphics()
    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC)
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
    g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON)
    g2d.clearRect(0, 0, width, height)
    g2d.drawImage(image, 0, 0, width, height, null)

    g2d.clearRect(0, 0, width, borderThickness)
    g2d.clearRect(0, height - borderThickness, width, borderThickness)
    g2d.clearRect(0, 0, borderThickness, height)
    g2d.clearRect(width - borderThickness, 0, borderThickness, height)

    g2d.stroke = BasicStroke(borderThickness.toFloat())
    g2d.color = Color(60, 120, 255)
    g2d.drawRoundRect(0, 0, width, height, 20, 20)
    g2d.dispose()

    return resizedImage
}