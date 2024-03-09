package nikita.app.vkapplication.viewmodels


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nikita.app.vkapplication.application.App
import nikita.app.vkapplication.data.network.ApiService
import nikita.app.vkapplication.data.network.common.Common
import java.lang.Exception

class MainViewModel: ViewModel() {

    private lateinit var apiService: ApiService
    private val repositoryProducts = App.getInstance().repositoryProducts

    fun getProducts() {
        apiService = Common.apiService

        viewModelScope.launch {
            try {
                val response = apiService.getProducts()
                repositoryProducts.setParseInfo(response)
            }
            catch (e: Exception) {
                Log.e("TAG", "Exception after request -> ${e.localizedMessage}")
            }
        }

    }

}