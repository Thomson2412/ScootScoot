package com.soloheisbeer.scootscoot.ui.config

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.soloheisbeer.scootscoot.databinding.FragmentConfigBinding

class ConfigFragment : Fragment() {

    private lateinit var dashboardViewModel: ConfigViewModel
    private var _binding: FragmentConfigBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(ConfigViewModel::class.java)

        _binding = FragmentConfigBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textConfig
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}