package poc.laguna.myread.core.data.wrappers

suspend fun <T> SafeApiRequest(request: suspend () -> T): ResultHandler<T> {
    return try {
        ResultHandler.Success(request())
    } catch (throwable: Throwable) {
        ResultHandler.Error(throwable)

    }
}