### Descripcion
Este proyecto es un ejemplo de como utilizar kotlin multiplatform para crear una applicacion Jetpack
compose para Android y para Desktop. 

La estructura del codigo es como sigue

* `/composeApp` El directorio que contiene la aplicacion como tal y contiene varios directorios
sub-directorios especificos:
  - `commonMain` Codigo commun para las dos plataformas.
  - `androidMain` Codigo especifico para android. Contiene la Acitivy y lanza el codigo.
  - `desktopMain` Codigo especifico para desktop. Contiene un methodo `main` que lanza la app


Link para [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…


### Como ejecutar
Es necesario tener como minimo [Gradle 8](https://docs.gradle.org/current/userguide/installation.html) & 
[Java 17](https://openjdk.org/projects/jdk/17/) instalado luego se pueden ejecutar los siguientes comandos

##### Desktop
```
gradle composeApp:run
```


##### Android
* Para ejecutar android es necesario instalar Android SDK. Lo mas sencillo es instalar [Android Studio]
(https://developer.android.com/studio?gclid=Cj0KCQiAtaOtBhCwARIsAN_x-3LK7gHoE5eyZ2tzNExeV4NxdnuxjeIqanN6HL8NXaCrONWeyGpSX3oaArA-EALw_wcB&gclsrc=aw.ds)
* Abrir el proyecto en android studio
* ejecutar composeApp en la configuracion de ejecucion.