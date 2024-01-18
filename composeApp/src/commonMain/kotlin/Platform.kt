enum class SupportedPlatform{
    ANDROID,
    DESKTOP
}
interface Platform {
    val name: String
    val platform: SupportedPlatform
}

expect fun getPlatform(): Platform