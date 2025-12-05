package com.daiyuang.gradle.docker.plugin

import com.daiyuang.gradle.docker.plugin.model.TaskDef
import com.daiyuang.gradle.docker.plugin.task.DockerInfoTask
import com.daiyuang.gradle.docker.plugin.task.DockerRunTask

const val DOCKER_TASK_GROUP = "docker"

val TASK_DEFS = listOf(
  TaskDef(
    DockerInfoTask.TASK_NAME,
    DockerInfoTask::class.java
  ),
  TaskDef(
    com.daiyuang.gradle.docker.plugin.task.DockerPushTask.TASK_NAME,
    com.daiyuang.gradle.docker.plugin.task.DockerPushTask::class.java
  ),
  TaskDef(
    DockerRunTask.TASK_NAME,
    DockerRunTask::class.java
  ),
  TaskDef(
    com.daiyuang.gradle.docker.plugin.task.DockerBuildTask.TASK_NAME,
    com.daiyuang.gradle.docker.plugin.task.DockerBuildTask::class.java
  )
)
