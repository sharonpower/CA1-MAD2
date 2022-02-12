package ie.wit.perfectpizza.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import ie.wit.perfectpizza.R
import ie.wit.perfectpizza.adapters.PizzaPlaceAdapter
import ie.wit.perfectpizza.adapters.PizzaPlaceListener
import ie.wit.perfectpizza.databinding.ActivityPerfectpizzaListBinding
import ie.wit.perfectpizza.main.MainApp
import ie.wit.perfectpizza.models.PizzaPlaceModel


class PerfectPizzaListActivity : AppCompatActivity(), PizzaPlaceListener{

    lateinit var app : MainApp
    private lateinit var binding: ActivityPerfectpizzaListBinding
    private lateinit var refreshIntentLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfectpizzaListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        loadPizzaplaces()

        registerRefreshCallback()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, PerfectPizzaActivity::class.java)
                refreshIntentLauncher.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPizzaPlaceClick(pizzaplace: PizzaPlaceModel) {
        val launcherIntent = Intent(this, PerfectPizzaActivity::class.java)
        launcherIntent.putExtra("pizzaplace_edit", pizzaplace)
        refreshIntentLauncher.launch(launcherIntent)
    }


    private fun registerRefreshCallback() {
        refreshIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                loadPizzaplaces()
            }
    }

    private fun loadPizzaplaces() {
        showPizzaplaces(app.pizzaplaces.findAll())
    }

    fun showPizzaplaces( pizzaplaces: List<PizzaPlaceModel>) {
        binding.recyclerView.adapter = PizzaPlaceAdapter(pizzaplaces, this)
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }



}

