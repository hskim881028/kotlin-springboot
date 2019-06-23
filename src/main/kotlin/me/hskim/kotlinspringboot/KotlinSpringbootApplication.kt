package me.hskim.kotlinspringboot

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinSpringbootApplication

fun main(args: Array<String>) {
    runApplication<KotlinSpringbootApplication>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}
