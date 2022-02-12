package ie.wit.perfectpizza.models

interface PizzaPlaceStore {
    fun findAll(): List<PizzaPlaceModel>
    fun create(pizzaplace: PizzaPlaceModel)
    fun update(pizzaplace: PizzaPlaceModel)
    fun delete(pizzaplace: PizzaPlaceModel)
}