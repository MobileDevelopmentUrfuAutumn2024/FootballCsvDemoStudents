import java.io.File

private object Handle

fun getResource(path: String): File? {
    return Handle::class.java.getResource(path)
        ?.toURI()
        ?.let(::File)
}
