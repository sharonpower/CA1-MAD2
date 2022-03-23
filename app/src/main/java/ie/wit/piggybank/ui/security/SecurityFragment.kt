package ie.wit.piggybank.ui.security

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ie.wit.piggybank.databinding.FragmentSecurityBinding

class SecurityFragment : Fragment() {

    private var _binding: FragmentSecurityBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val personalPiggyBankViewModel =
            ViewModelProvider(this).get(SecurityViewModel::class.java)

        _binding = FragmentSecurityBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textSecurity
//        personalPiggyBankViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}