package ie.wit.perfectpizza.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ie.wit.perfectpizza.R
import ie.wit.perfectpizza.databinding.FragmentReviewListBinding
import ie.wit.perfectpizza.main.MainApp

class ReviewListFragment : Fragment() {

    lateinit var app : MainApp
    private var _fragBinding: FragmentReviewListBinding? = null
    private val fragBinding get() = _fragBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as MainApp
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentReviewListBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        return root;
        return inflater.inflate(R.layout.fragment_review_list, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }

    override fun onResume() {
        super.onResume()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ReviewListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}