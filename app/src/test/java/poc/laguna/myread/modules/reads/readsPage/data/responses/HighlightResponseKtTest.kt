package poc.laguna.myread.modules.reads.readsPage.data.responses

import junit.framework.TestCase.assertEquals
import org.junit.Test
import poc.laguna.myread.modules.reads.readsPage.domain.HighlightModel

class HighlightResponseKtTest {
    @Test
    fun `when call toDomain method, shoul crate HighlightModel with same values`() {
        val highlightResponse = HighlightResponse(
            id = "123",
            highlightsUrl = "http://example.com"
        )

        val highlightModel = HighlightModel(
            id = "123",
            highlightsUrl = "http://example.com"
        )

        assertEquals(highlightModel, highlightResponse.toDomain())
    }
}