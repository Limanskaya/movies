package www.movies.app.base

import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class BaseRepository {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Result<T, ErrorMessage> {

        return try {
            val result = call.invoke()

            if (result.isSuccessful)
                Result.Success(result.body()!!)
            else
                Result.Error(data = ErrorMessage("Произошла ошибка, попробуйте еще раз"))


        } catch (e: Exception) {
            when (e) {
                is ConnectException,
                is UnknownHostException,
                is SocketTimeoutException -> {
                    Result.Error(data = ErrorMessage("Ошибка подключения к сети"))
                }
                else -> Result.Error(data = ErrorMessage("Произошла ошибка, попробуйте еще раз"))
            }
        }

    }

    sealed class Result<out T: Any, out U: Any> {
        data class Success<out T : Any, out U: Any>(val data: T) : Result<T, U>()
        data class Error<out T : Any, out U: Any>(val data: U) : Result<T, U>()
    }

    data class ErrorMessage(val message: String)

}