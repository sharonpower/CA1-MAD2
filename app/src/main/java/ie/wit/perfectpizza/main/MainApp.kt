package ie.wit.perfectpizza.main

import android.app.Application
import ie.wit.perfectpizza.models.PizzaPlaceJSONStore
import ie.wit.perfectpizza.models.PizzaPlaceMemStore
import ie.wit.perfectpizza.models.PizzaPlaceModel
import ie.wit.perfectpizza.models.PizzaPlaceStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    lateinit var PizzaPlaceStore: Any
    lateinit var pizzaplaces : PizzaPlaceStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        pizzaplaces = PizzaPlaceJSONStore(applicationContext)
        //pizzaplaces = PizzaPlaceMemStore()
        i("PerfectPizza started")
    }
}