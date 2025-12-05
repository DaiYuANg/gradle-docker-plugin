package com.daiyuang.gradle.docker.plugin.task

import org.gradle.api.provider.Property
import org.gradle.api.services.ServiceReference
import org.gradle.api.tasks.TaskAction

abstract class DockerInfoTask : org.gradle.api.DefaultTask() {
  companion object {
    const val TASK_NAME = "dockerInfo"
  }

  @get:ServiceReference(com.daiyuang.gradle.docker.plugin.service.DockerService.Companion.SERVICE_NAME)
  abstract val dockerService: Property<com.daiyuang.gradle.docker.plugin.service.DockerService>

  @TaskAction
  fun printInfo() {
    val client = dockerService.get().client()

    val info = client.infoCmd().exec()
    println("Docker info$info")
    val version = client.versionCmd().exec()
    println("Docker Version: ${version.version}")
  }
}
