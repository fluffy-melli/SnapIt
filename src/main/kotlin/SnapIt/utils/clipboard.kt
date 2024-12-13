package SnapIt.utils

import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.Transferable
import java.awt.image.BufferedImage


fun copyImageToClipboard(image: BufferedImage) {
    val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
    val transferable: Transferable = object : Transferable {
        override fun getTransferData(flavor: DataFlavor?): Any {
            return image
        }

        override fun isDataFlavorSupported(flavor: DataFlavor?): Boolean {
            return DataFlavor.imageFlavor.equals(flavor)
        }

        override fun getTransferDataFlavors(): Array<DataFlavor> {
            return arrayOf(DataFlavor.imageFlavor)
        }
    }
    clipboard.setContents(transferable, null)
}
