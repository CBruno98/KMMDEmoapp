package com.bruno.kmm_demoapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform