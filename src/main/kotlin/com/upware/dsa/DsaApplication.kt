package com.upware.dsa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DsaApplication

fun main(args: Array<String>) {
	runApplication<DsaApplication>(*args)
}
