package com.daiyuang.kotlin.gradle.docker.plugin.service

import com.github.dockerjava.api.DockerClient
import com.github.dockerjava.core.DefaultDockerClientConfig
import com.github.dockerjava.core.DockerClientConfig
import com.github.dockerjava.core.DockerClientImpl
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient
import org.gradle.api.provider.Property
import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters

abstract class DockerService :
  BuildService<DockerService.Params>,
  AutoCloseable {
  companion object {
    const val SERVICE_NAME = "dockerService"
  }

  // Docker client 需要 lazy init
  @Volatile
  private var _client: DockerClient? = null

  fun client(): DockerClient =
    _client ?: synchronized(this) {
      _client ?: createClient().also { _client = it }
    }

  private fun createClient(): DockerClient {
    val cfg =
      DefaultDockerClientConfig
        .createDefaultConfigBuilder()
        .withDockerHost(parameters.host.get())
        .withApiVersion(parameters.apiVersion.get())
        .build()

    val httpClient =
      ApacheDockerHttpClient
        .Builder()
        .dockerHost(cfg.dockerHost)
        .sslConfig(cfg.sslConfig)
        .build()
    val standard: DockerClientConfig? = DefaultDockerClientConfig.createDefaultConfigBuilder().build()

    return DockerClientImpl.getInstance(standard, httpClient)
  }

  override fun close() {
    _client?.close()
  }

  interface Params : BuildServiceParameters {
    val host: Property<String>
    val apiVersion: Property<String>
  }
}
