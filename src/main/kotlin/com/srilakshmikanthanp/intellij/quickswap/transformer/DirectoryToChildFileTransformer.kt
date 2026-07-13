package com.srilakshmikanthanp.intellij.quickswap.transformer

import com.intellij.openapi.vfs.VirtualFile

class DirectoryToChildFileTransformer : FileTransformer {
  private fun getChildFiles(file: VirtualFile): Sequence<VirtualFile> = sequence {
    val filesToVisit = ArrayDeque<VirtualFile>()
    filesToVisit.addFirst(file)

    while (filesToVisit.isNotEmpty()) {
      val currentFile = filesToVisit.removeFirst()
      if (currentFile.isDirectory) {
        val children = currentFile.children
        for (index in children.indices.reversed()) {
          filesToVisit.addFirst(children[index])
        }
      } else {
        yield(currentFile)
      }
    }
  }

  override fun transform(files: Sequence<VirtualFile>): Sequence<VirtualFile> {
    return files.flatMap { file ->
      if (file.isDirectory) {
        getChildFiles(file)
      } else {
        sequenceOf(file)
      }
    }
  }
}
