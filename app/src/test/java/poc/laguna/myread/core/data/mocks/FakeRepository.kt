package poc.laguna.myread.core.data.mocks

import poc.laguna.myread.core.data.wrappers.ResultHandler
import poc.laguna.myread.core.data.wrappers.safeApiRequest

 class FakeRepository(private val fakeApi: FakeApiWithRequestInterface) {
    suspend fun doRequest() : ResultHandler<Unit> {
        return safeApiRequest {
            fakeApi.doRequest()
        }
    }
}