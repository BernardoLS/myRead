package poc.laguna.myread.core.data.wrappers

sealed class ResultHandler<out T> {
    data class Success<out T>(val value: T) : ResultHandler<T>()
    data class Error(val throwable: Throwable) : ResultHandler<Nothing>()
}