package ie.wit.perfectpizza.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import ie.wit.perfectpizza.R
import ie.wit.perfectpizza.databinding.FragmentReviewBinding
import ie.wit.perfectpizza.main.MainApp


class ReviewFragment : Fragment() {

    lateinit var app : MainApp
    private var _fragBinding: FragmentReviewBinding? = null
    private val fragBinding get() = _fragBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as MainApp
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentReviewBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        return inflater.inflate(R.layout.fragment_review, container, false)
        }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReviewFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_perfectpizza, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            requireView().findNavController()) || super.onOptionsItemSelected(item)
    }
}