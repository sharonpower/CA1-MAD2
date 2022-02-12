package ie.wit.perfectpizza.models

import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import ie.wit.perfectpizza.helpers.*
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val JSON_FILE = "pizzaplaces.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<PizzaPlaceModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class PizzaPlaceJSONStore(private val context: Context) : PizzaPlaceStore {

    var pizzaplaces = mutableListOf<PizzaPlaceModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<PizzaPlaceModel> {
        logAll()
        return pizzaplaces
    }

    override fun create(pizzaplace: PizzaPlaceModel) {
        pizzaplace.id = generateRandomId()
        pizzaplaces.add(pizzaplace)
        serialize()
    }


    override fun update(pizzaplace: PizzaPlaceModel) {
        val pizzaplacesList = findAll() as ArrayList<PizzaPlaceModel>
        var foundPizzaPlace: PizzaPlaceModel? = pizzaplacesList.find { p -> p.id == pizzaplace.id }
        if (foundPizzaPlace != null) {
            foundPizzaPlace.name = pizzaplace.name
            foundPizzaPlace.location = pizzaplace.location
            foundPizzaPlace.review = pizzaplace.review
            foundPizzaPlace.image = pizzaplace.image
            foundPizzaPlace.lat = pizzaplace.lat
            foundPizzaPlace.lng = pizzaplace.lng
            foundPizzaPlace.zoom = pizzaplace.zoom
        }
        serialize()
    }

    override fun delete(pizzaplace: PizzaPlaceModel) {
        pizzaplaces.remove(pizzaplace)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(pizzaplaces, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        pizzaplaces = gsonBuilder.fromJson(jsonString, listType)
    }

    private fun logAll() {
        pizzaplaces.forEach { Timber.i("$it") }
    }
}

class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}