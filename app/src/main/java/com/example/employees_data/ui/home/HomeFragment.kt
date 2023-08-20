package com.example.employees_data.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.employees_data.databinding.FragmentHomeBinding
import com.example.employees_data.model.EmployeeModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    var employee:EmployeeModel = EmployeeModel("unknown")

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addBtn.setOnClickListener {
            employee.name = binding.nameTxt.toString()
            employee.Email = binding.emailTxt.toString()
//            employee.photo = binding.imgV.toString()
//            employee.Skills = binding.autoCompleteTextView.
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}