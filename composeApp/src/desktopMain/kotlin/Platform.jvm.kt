class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
    override val platform: SupportedPlatform = SupportedPlatform.DESKTOP
}

actual fun getPlatform(): Platform = JVMPlatform()