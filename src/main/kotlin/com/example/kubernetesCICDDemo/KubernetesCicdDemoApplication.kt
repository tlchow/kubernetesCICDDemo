package com.example.kubernetesCICDDemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KubernetesCicdDemoApplication

fun main(args: Array<String>) {
	runApplication<KubernetesCicdDemoApplication>(*args)
}
