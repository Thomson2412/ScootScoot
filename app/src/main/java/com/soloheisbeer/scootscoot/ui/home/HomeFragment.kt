package com.soloheisbeer.scootscoot.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.soloheisbeer.scootscoot.R
import com.soloheisbeer.scootscoot.databinding.FragmentHomeBinding
import android.util.TypedValue




class HomeFragment : Fragment(), View.OnClickListener{

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var cruiseControlActivated = false
    private var cruiseControlSpeed = 0
    private val maxCruiseSpeed = 25

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textCruiseControl
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnCruiseControl = view.findViewById<Button>(R.id.btn_cruise_control)
        btnCruiseControl.setOnClickListener(this)
        val btnCruiseControlMinus = view.findViewById<Button>(R.id.btn_cruise_control_minus)
        btnCruiseControlMinus.setOnClickListener(this)
        val btnCruiseControlPlus = view.findViewById<Button>(R.id.btn_cruise_control_plus)
        btnCruiseControlPlus.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_cruise_control -> {
                cruiseControlActivated = !cruiseControlActivated
                var colorAttr = R.attr.colorPrimary
                if(cruiseControlActivated) {
                    colorAttr = R.attr.colorAccent
                    binding.textCruiseControl.text = String.format("%02d", cruiseControlSpeed)
                    binding.btnCruiseControlMinus.isEnabled = true
                    binding.btnCruiseControlPlus.isEnabled = true
                }
                else {
                    binding.textCruiseControl.text = "--"
                    binding.btnCruiseControlMinus.isEnabled = false
                    binding.btnCruiseControlPlus.isEnabled = false
                }
                val outValue = TypedValue()
                binding.root.context.theme.resolveAttribute(colorAttr, outValue, true);
                binding.btnCruiseControl.setBackgroundColor(outValue.data)
            }
            R.id.btn_cruise_control_minus -> {
                if(cruiseControlSpeed > 0) {
                    cruiseControlSpeed--
                    binding.textCruiseControl.text = String.format("%02d", cruiseControlSpeed)
                }
            }
            R.id.btn_cruise_control_plus -> {
                if(cruiseControlSpeed < maxCruiseSpeed) {
                    cruiseControlSpeed++
                    binding.textCruiseControl.text = String.format("%02d", cruiseControlSpeed)
                }
            }
        }
    }
}