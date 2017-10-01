import com.karumi.ui.domain.Error.UnknownError
import com.karumi.ui.domain.Result
import com.karumi.ui.domain.Result.Right
import junit.framework.Assert
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test

class ResultTest {

    @Test
    fun shouldReturnSucessWhenCreateASuccessValue() {
        val success = Result.success(5)

        assertEquals(5, success.value)
    }

    @Test
    fun shouldReturnErrorWhenCreateAError() {
        val error = Result.error<Int>(UnknownError())

        assertTrue(error.error is UnknownError)
    }

    @Test
    fun shouldReturnValueAfterBindOtherValue() {
        val success = Result.success(5)

        val bindValue = success.bind { Result.success(3) }

        assertTrue(bindValue is Right)
        Assert.assertEquals(3, (bindValue as Right).value)
    }

    @Test
    fun shouldReturnValueAfterSeqOtherType() {
        val success = Result.success(5)
        val successFloat = Result.success("2")

        val seqValue = success.seq(successFloat)

        assertTrue(seqValue is Right)
        Assert.assertEquals("2", (seqValue as Right).value)
    }
}