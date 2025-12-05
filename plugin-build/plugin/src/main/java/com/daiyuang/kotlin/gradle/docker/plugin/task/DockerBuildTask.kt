package com.daiyuang.kotlin.gradle.docker.plugin.task

import com.github.dockerjava.core.DefaultDockerClientConfig
import com.github.dockerjava.core.DockerClientConfig
import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option

abstract class DockerBuildTask : DefaultTask() {
  companion object {
    const val TASK_NAME = "dockerBuild"
  }

  init {
    description = "docker build task"

    // Don't forget to set the group here.
    // group = BasePlugin.BUILD_GROUP
  }

  @get:Input
  @get:Option(option = "message", description = "A message to be printed in the output file")
  @get:Optional
  abstract val message: Property<String>

  @get:Input
  @get:Option(option = "tag", description = "A Tag to be used for debug and in the output file")
  @get:Optional
  abstract val tag: Property<String>

  @get:OutputFile
  abstract val outputFile: RegularFileProperty

  @TaskAction
  fun sampleAction() {
    val standard: DockerClientConfig? = DefaultDockerClientConfig.createDefaultConfigBuilder().build()
    standard?.let {
      logger.lifecycle(" standard is: $standard")
    }
    val prettyTag = tag.orNull?.let { "[$it]" }.orEmpty()
//        logger.lifecycle("$prettyTag message is: ${message.orNull}")
//        logger.lifecycle("$prettyTag tag is: ${tag.orNull}")
//        logger.lifecycle("$prettyTag outputFile is: ${outputFile.orNull}")

//        outputFile.get().asFile.writeText("$prettyTag ${message.get()}")
  }
}
