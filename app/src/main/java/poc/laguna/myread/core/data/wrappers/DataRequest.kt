package poc.laguna.myread.core.data.wrappers

suspend fun <T> DataRequest(callback: suspend () -> T): ResultWrapper<T> {
    return try {
        ResultWrapper.Success(callback())
    } catch (throwable: Throwable) {
        ResultWrapper.Error(throwable)
    }
}