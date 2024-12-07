package SnapIt.display

import SnapIt.capture.loadSavedFolder
import SnapIt.capture.resizeImage
import SnapIt.capture.saveFolder
import java.awt.BorderLayout
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.awt.image.BufferedImage
import java.io.File
import java.time.Instant
import javax.imageio.ImageIO
import javax.swing.*

fun createWindow(capturedImage: BufferedImage) {
    val resizedImage = resizeImage(capturedImage, capturedImage.width, capturedImage.height)
    val frame = JFrame("SnapIt")
    frame.defaultCloseOperation = JFrame.DO_NOTHING_ON_CLOSE
    frame.layout = BorderLayout()
    val label = JLabel(ImageIcon(resizedImage))
    frame.add(label, BorderLayout.CENTER)
    val folderButton = JButton("Save")
    folderButton.addActionListener {
        val fileChooser = JFileChooser()
        fileChooser.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
        val lastFolder = loadSavedFolder()
        if (lastFolder.isNotEmpty()) {
            fileChooser.selectedFile = File(lastFolder)
        }
        if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            val selectedFolder = fileChooser.selectedFile
            saveFolder(selectedFolder.absolutePath)
            val imageFile = File(selectedFolder, "screenshot-${Instant.now().epochSecond}.png")
            ImageIO.write(resizedImage, "png", imageFile)
            frame.isVisible = false
        }
    }
    frame.add(folderButton, BorderLayout.SOUTH)
    frame.addWindowListener(object : WindowAdapter() {
        override fun windowClosing(e: WindowEvent) {
            frame.isVisible = false
        }
    })
    frame.pack()
    frame.setLocationRelativeTo(null)
    frame.isVisible = true
    frame.isAlwaysOnTop = true
    frame.toFront()
}