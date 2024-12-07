package SnapIt.capture

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter

fun loadSavedFolder(): String {
    val settingsFile = File("setting")
    if (settingsFile.exists()) {
        val reader = BufferedReader(FileReader(settingsFile))
        return reader.readLine() ?: ""
    }
    return ""
}

fun saveFolder(folderPath: String) {
    val settingsFile = File("setting")
    val writer = FileWriter(settingsFile)
    writer.write(folderPath)
    writer.close()
}