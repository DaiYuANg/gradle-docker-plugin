package com.daiyuang.kotlin.gradle.docker.plugin

import com.daiyuang.kotlin.gradle.docker.plugin.service.DockerService
import com.daiyuang.kotlin.gradle.docker.plugin.service.DockerService.Companion.SERVICE_NAME
import com.daiyuang.kotlin.gradle.docker.plugin.task.DockerBuildTask
import com.daiyuang.kotlin.gradle.docker.plugin.task.DockerInfoTask
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("UnnecessaryAbstractClass")
abstract class DockerPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    val ext = project.extensions.create(EXTENSION_NAME, DockerExtension::class.java, project)
    project.gradle.sharedServices.registerIfAbsent(
      SERVICE_NAME,
      DockerService::class.java,
      { spec ->
        run {
          spec.parameters.host.set(ext.host)
          spec.parameters.apiVersion.set(ext.apiVersion)
        }
      },
    )

    project.tasks.register(DockerInfoTask.TASK_NAME, DockerInfoTask::class.java) {
      it.group = DOCKER_TASK_GROUP
    }
    project.tasks.register(DockerBuildTask.TASK_NAME, DockerBuildTask::class.java) {
      it.tag.set(ext.tag)
      it.message.set(ext.message)
      it.outputFile.set(ext.outputFile)
      it.group = DOCKER_TASK_GROUP
    }
  }
}
