package ie.wit.piggybank.ui.security

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SecurityViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is security Fragment"
    }
    val text: LiveData<String> = _text
}