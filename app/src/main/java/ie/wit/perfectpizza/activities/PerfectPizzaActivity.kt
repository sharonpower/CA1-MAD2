package ie.wit.perfectpizza.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import ie.wit.perfectpizza.R
import ie.wit.perfectpizza.R.id.*
import ie.wit.perfectpizza.adapters.PizzaPlaceAdapter
import ie.wit.perfectpizza.databinding.ActivityPerfectpizzaBinding
import ie.wit.perfectpizza.databinding.ActivityPerfectpizzaListBinding
import ie.wit.perfectpizza.helpers.showImagePicker
import ie.wit.perfectpizza.main.MainApp
import ie.wit.perfectpizza.models.Destination
import ie.wit.perfectpizza.models.PizzaPlaceModel
import timber.log.Timber
import timber.log.Timber.i
import timber.log.Timber.plant

class PerfectPizzaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerfectpizzaBinding
    var pizzaplace = PizzaPlaceModel()
    lateinit var app : MainApp
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    private lateinit var mapIntentLauncher : ActivityResultLauncher<Intent>
    //var destination = Destination(52.256311, -7.139252, 17f)
    var edit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfectpizzaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)


        app = application as MainApp

        if (intent.hasExtra("pizzaplace_edit")) {
            edit = true
            pizzaplace = intent.extras?.getParcelable("pizzaplace_edit")!!
            binding.pizzaplaceName.setText(pizzaplace.name)
            binding.location.setText(pizzaplace.location)
            binding.review.setText(pizzaplace.review)
            binding.btnAdd.setText(R.string.save_pizzaplace)
            Picasso.get()
                .load(pizzaplace.image)
                .into(binding.pizzaImage)
            if(pizzaplace.image != Uri.EMPTY) {
                binding.chooseImage.setText(R.string.change_pizza_image)
            }
        }

        binding.btnAdd.setOnClickListener() {
            pizzaplace.name = binding.pizzaplaceName.text.toString()
            pizzaplace.location = binding.location.text.toString()
            pizzaplace.review = binding.review.text.toString()
            if (pizzaplace.name.isEmpty()) {
                Snackbar.make(it, R.string.enter_pizzaplace_title, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit) {
                    app.pizzaplaces.update(pizzaplace.copy())
                } else {
                    app.pizzaplaces.create(pizzaplace.copy())
                }
            }
                i("Add Button Pressed: $pizzaplace")
                setResult(RESULT_OK)
                finish()
            }


                binding.chooseImage.setOnClickListener {
                    showImagePicker(imageIntentLauncher)
            }



                binding.pizzaplaceDestination.setOnClickListener {
                    val destination = Destination(52.256311, -7.139252, 17f)
                    if (pizzaplace.zoom != 0f) {
                        destination.lat = pizzaplace.lat
                        destination.lng = pizzaplace.lng
                        destination.zoom = pizzaplace.zoom
                    }
                    val launcherIntent = Intent(this, MapActivity::class.java)
                        .putExtra("destination", destination)
                    mapIntentLauncher.launch(launcherIntent)
                }

            registerImagePickerCallback()
            registerMapCallback()
        }

        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            menuInflater.inflate(R.menu.menu_perfectpizza, menu)
            if (edit) menu.getItem(0).isVisible = true
            return super.onCreateOptionsMenu(menu)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.item_delete -> {
                    app.pizzaplaces.delete(pizzaplace)
                    finish()
                }
                item_cancel -> {
                    finish()
                }
            }
            return super.onOptionsItemSelected(item)
        }

        private fun registerImagePickerCallback() {
            imageIntentLauncher =
                registerForActivityResult(ActivityResultContracts.StartActivityForResult())
                { result ->
                    when(result.resultCode){
                        RESULT_OK -> {
                            if (result.data != null) {
                                i("Got Result ${result.data!!.data}")
                                pizzaplace.image = result.data!!.data!!
                                Picasso.get()
                                    .load(pizzaplace.image)
                                    .into(binding.pizzaImage)
                                binding.chooseImage.setText(R.string.change_pizza_image)
                            }
                        }
                        RESULT_CANCELED -> { } else -> { }
                    }
                }
        }

        private fun registerMapCallback() {
            mapIntentLauncher =
                registerForActivityResult(ActivityResultContracts.StartActivityForResult())
                { result ->
                    when (result.resultCode) {
                        RESULT_OK -> {
                            if (result.data != null) {
                                i("Got Destination ${result.data.toString()}")
                                val destination = result.data!!.extras?.getParcelable<Destination>("destination")!!
                                i("Destination == $destination")
                                pizzaplace.lat = destination.lat
                                pizzaplace.lng = destination.lng
                                pizzaplace.zoom = destination.zoom
                            }
                        }
                        RESULT_CANCELED -> { } else -> { }
                    }
                }
        }
    }