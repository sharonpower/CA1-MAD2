package ie.wit.perfectpizza.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class PizzaPlaceMemStore : PizzaPlaceStore {

    val pizzaplaces = ArrayList<PizzaPlaceModel>()

    override fun findAll(): List<PizzaPlaceModel> {
        return pizzaplaces
    }

    override fun create(pizzaplace: PizzaPlaceModel) {
        pizzaplace.id = getId()
        pizzaplaces.add(pizzaplace)
        logAll()
    }

    override fun update(pizzaplace: PizzaPlaceModel) {
        var foundPizzaPlace: PizzaPlaceModel? = pizzaplaces.find { p -> p.id == pizzaplace.id }
        if (foundPizzaPlace != null) {
            foundPizzaPlace.name = pizzaplace.name
            foundPizzaPlace.location = pizzaplace.location
            foundPizzaPlace.review = pizzaplace.review
            foundPizzaPlace.image = pizzaplace.image
            foundPizzaPlace.lat = pizzaplace.lat
            foundPizzaPlace.lng = pizzaplace.lng
            foundPizzaPlace.zoom = pizzaplace.zoom
            logAll()
        }
    }

    override fun delete(pizzaplace: PizzaPlaceModel) {
        pizzaplaces.remove(pizzaplace)
    }

    fun logAll() {
        pizzaplaces.forEach{ i("${it}") }
    }
}