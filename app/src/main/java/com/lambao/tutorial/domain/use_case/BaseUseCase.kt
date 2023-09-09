package com.lambao.tutorial.domain.use_case

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.lambao.tutorial.R
import com.lambao.tutorial.common.Resource
import kotlinx.coroutines.flow.flow
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

open class BaseUseCase {

    @Inject
    protected lateinit var app: Application

    fun <T> handleApi(source: suspend () -> T) = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = source()))
        } catch (t: Throwable) {
            emit(handleNetworkError(t))
        }
    }

    private fun <T> handleNetworkError(
        throwable: Throwable?
    ): Resource<T> {
        if (!hasInternetConnection()) {
            return Resource.Error(app.getString(R.string.msg_has_no_internet))
        }
        if (throwable == null)
            return Resource.Error(app.getString(R.string.msg_has_error))
        when (throwable) {
            // case no internet connection
            is UnknownHostException -> {
                return Resource.Error(app.getString(R.string.msg_has_error))
            }

            is ConnectException -> {
                return Resource.Error(app.getString(R.string.msg_has_error))
            }

            is SocketTimeoutException -> {
                return Resource.Error(app.getString(R.string.msg_has_error))
            }

            else -> {
                // convert throwable to base exception to get error information
//                val baseException = throwable.toBaseException()
                /*return when (baseException.httpCode) {
                    HttpURLConnection.HTTP_UNAUTHORIZED ->
                        Resource.Error(baseException.message)

                    HttpURLConnection.HTTP_INTERNAL_ERROR ->
                        Resource.Error(baseException.message)

                    else -> Resource.Error(app.getString(R.string.msg_has_error))
                }*/
//                return Resource.Error(baseException.message)
                return Resource.Error(app.getString(R.string.msg_has_error))
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = app.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities =
            connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}