package com.example.employees_data.ui.dashboard

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.employees_data.database.LocalSource
import com.example.employees_data.database.RoomState
import com.example.employees_data.databinding.FragmentDashboardBinding
import com.example.employees_data.model.Repository
import com.example.employees_data.ui.home.HomeViewModel
import com.example.employees_data.ui.home.HomeViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {
    lateinit var dashboardViewModelFactory: DashboardViewModelFactory
    lateinit var dashboardViewModel: DashboardViewModel
    lateinit var employeeAdapter: EmployeeAdapter

    private var _binding: FragmentDashboardBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         dashboardViewModelFactory= DashboardViewModelFactory(
            Repository.getInstance(
                LocalSource(requireActivity())
            )
        )
        dashboardViewModel =
            ViewModelProvider(requireActivity(), dashboardViewModelFactory).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pd = ProgressDialog(requireActivity())
        employeeAdapter = EmployeeAdapter()
        binding.employeesRv.apply {
            this.adapter = employeeAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        }
        lifecycleScope.launch {
            dashboardViewModel.employeesObj.collectLatest {
                    when(it){
                        is RoomState.Loading ->{

                            pd.setMessage("loading")
                            pd.show()


                        }
                        is RoomState.SuccessEmployee->{
                            pd.dismiss()
                            employeeAdapter.submitList(it.data)
                            employeeAdapter.notifyDataSetChanged()
                        }
                        else->{
                            pd.dismiss()
                            Toast.makeText(requireActivity(),"Can't handle your request", Toast.LENGTH_SHORT).show()
                        }
                    }

            }
        }

    }

    override fun onResume() {
        super.onResume()
        dashboardViewModel.getEmployeesFromRoom()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}