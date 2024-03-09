package nikita.app.vkapplication.application

import android.app.Application
import nikita.app.vkapplication.data.repository.RepositoryProducts

class App: Application() {

    val repositoryProducts = RepositoryProducts()

    companion object {
        private lateinit var instance: App

        fun getInstance() : App {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}