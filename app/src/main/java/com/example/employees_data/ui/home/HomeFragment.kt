package com.example.employees_data.ui.home

import android.R
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.MultiAutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.employees_data.database.LocalSource
import com.example.employees_data.databinding.FragmentHomeBinding
import com.example.employees_data.model.EmployeeModel
import com.example.employees_data.model.Repository
import com.example.employees_data.model.SkillModel


class HomeFragment : Fragment() {
    lateinit var homeViewModelFactory: HomeViewModelFactory
    lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    var employee:EmployeeModel = EmployeeModel("unknown")
    var skills = arrayOf("PHP","ASP.NET","iOS","Android")

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModelFactory = HomeViewModelFactory(
            Repository.getInstance(
                LocalSource(requireActivity())
            )
        )
        homeViewModel =
            ViewModelProvider(requireActivity(), homeViewModelFactory).get(HomeViewModel::class.java)


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }
    private fun setupAutoCompleteTextView() {

        // Create the adapter and set it to the AutoCompleteTextView
        val adapter = ArrayAdapter(requireActivity(),R.layout.simple_list_item_1, skills)
        binding.skillsInput.setAdapter(adapter)
        binding.skillsInput.threshold = 1
        binding.skillsInput.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())

        binding.addBtn.setOnClickListener {
            val text =   binding.skillsInput.text
            Toast.makeText(requireActivity(), text, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        for (i in skills){
            var skillModel:SkillModel= SkillModel(skill = i)
            homeViewModel.insertSkillInRoom(skillModel)
        }
        setupAutoCompleteTextView()

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}