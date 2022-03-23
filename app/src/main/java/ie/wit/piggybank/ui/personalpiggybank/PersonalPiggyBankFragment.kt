package ie.wit.piggybank.ui.personalpiggybank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ie.wit.piggybank.databinding.FragmentPersonalpiggybankBinding

class PersonalPiggyBankFragment : Fragment() {

    private var _binding: FragmentPersonalpiggybankBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val personalPiggyBankViewModel =
            ViewModelProvider(this).get(PersonalPiggyBankViewModel::class.java)

        _binding = FragmentPersonalpiggybankBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        personalPiggyBankViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}