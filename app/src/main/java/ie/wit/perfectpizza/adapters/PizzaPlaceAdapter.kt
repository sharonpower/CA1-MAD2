package ie.wit.perfectpizza.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ie.wit.perfectpizza.databinding.CardPerfectpizzaBinding
import ie.wit.perfectpizza.models.PizzaPlaceModel

interface PizzaPlaceListener {
    fun onPizzaPlaceClick(pizzaplace: PizzaPlaceModel)
}

class PizzaPlaceAdapter constructor(
    private var pizzaplaces: List<PizzaPlaceModel>, private val listener: PizzaPlaceListener) :
    RecyclerView.Adapter<PizzaPlaceAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardPerfectpizzaBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val pizzaplace = pizzaplaces[holder.adapterPosition]
        holder.bind(pizzaplace, listener)
    }

    override fun getItemCount(): Int = pizzaplaces.size

    class MainHolder(private val binding : CardPerfectpizzaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pizzaplace: PizzaPlaceModel , listener: PizzaPlaceListener) {
            binding.pizzaplaceName.text = pizzaplace.name
            binding.location.text = pizzaplace.location
            binding.review.text = pizzaplace.review

            Picasso.get().load(pizzaplace.image).resize(200,200).into(binding.imageIcon)
            binding.root.setOnClickListener { listener.onPizzaPlaceClick(pizzaplace) }
        }
    }
}

