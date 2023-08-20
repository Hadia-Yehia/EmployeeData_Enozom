package com.example.employees_data.ui.home

import android.R
import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.MultiAutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.employees_data.database.LocalSource
import com.example.employees_data.database.RoomState
import com.example.employees_data.databinding.FragmentHomeBinding
import com.example.employees_data.model.EmployeeModel
import com.example.employees_data.model.EmployeeSkills
import com.example.employees_data.model.Repository
import com.example.employees_data.model.SkillModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    lateinit var homeViewModelFactory: HomeViewModelFactory
    lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    var employee:EmployeeModel = EmployeeModel("unknown")
    var skills = arrayOf("PHP","ASP.NET","iOS","Android")
     var skillsList = mutableListOf<String>()

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
    private fun setupAutoCompleteTextView(skillsList:MutableList<String>) {

        // Create the adapter and set it to the AutoCompleteTextView
        val adapter = ArrayAdapter(requireActivity(),R.layout.simple_list_item_1, skillsList)
        binding.skillsInput.setAdapter(adapter)
        binding.skillsInput.threshold = 1
        binding.skillsInput.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pd = ProgressDialog(requireActivity())
        skillsList.clear()

        for (i in skills){
            var skillModel:SkillModel= SkillModel(skill = i)
            homeViewModel.insertSkillInRoom(skillModel)
        }

        homeViewModel.getSkillsFromRoom()
        lifecycleScope.launch {
            homeViewModel.skillsObj.collectLatest {
                when(it){
                    is RoomState.Loading ->{

                        pd.setMessage("loading")
                        pd.show()


                    }
                    is RoomState.SuccessSkills->{
                        pd.dismiss()
                        for (i in it.data){
                            skillsList.add(i.skill)
                        }
                        setupAutoCompleteTextView(skillsList)

                    }
                    else->{
                        pd.dismiss()
                        Toast.makeText(requireActivity(),"Can't handle your request", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        binding.addButton.setOnClickListener {
            employee.Email = binding.emailTxt.text.toString()
            employee.name = binding.nameTxt.text.toString()
            homeViewModel.insertEmployeeInRoom(employee)
            lifecycleScope.launch {
                homeViewModel.empIdObj.collectLatest {
                    val customerId = it
                    val skillsArray = binding.skillsInput.text.split(",").toTypedArray()
                    for (skillName in skillsArray){
                        val skill:EmployeeSkills=EmployeeSkills(customerId, skillName )
                        homeViewModel.insertEmployeeSkillInRoom(skill)
                    }
                }
            }

        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}