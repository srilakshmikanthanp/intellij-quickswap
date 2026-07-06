package com.srilakshmikanthanp.intellij.quickswap.transformer

import com.intellij.openapi.vfs.VfsUtilCore
import com.intellij.openapi.vfs.VirtualFile

class DirectoryToChildFileTransformer : FileTransformer {
  private fun getChildFiles(file: VirtualFile): List<VirtualFile> {
    val childFiles = mutableListOf<VirtualFile>()
    VfsUtilCore.iterateChildrenRecursively(file, null) { childFile ->
      if (!childFile.isDirectory) {
        childFiles.add(childFile)
      }
      true
    }
    return childFiles
  }

  override fun transform(files: Collection<VirtualFile>): List<VirtualFile> {
    val childFiles = mutableListOf<VirtualFile>()
    for (file in files) {
      if (file.isDirectory) {
        childFiles.addAll(getChildFiles(file))
      } else {
        childFiles.add(file)
      }
    }
    return childFiles
  }
}
