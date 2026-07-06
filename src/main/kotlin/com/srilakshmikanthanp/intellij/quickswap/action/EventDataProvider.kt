package com.srilakshmikanthanp.intellij.quickswap.action

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DataKey

class EventDataProvider(private val event: AnActionEvent) {
  fun <T> get(key: DataKey<T>): T? {
    return event.getData(key)
  }
}
