import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val platform: SupportedPlatform = SupportedPlatform.ANDROID
}

actual fun getPlatform(): Platform = AndroidPlatform()