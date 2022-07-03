package com.tareq.android.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tareq.android.myapplication.databinding.FragmentNewEmployeeBinding;
import com.tareq.android.myapplication.entities.Employee;
import com.tareq.android.myapplication.viewmodels.EmployeeViewModel;


public class NewEmployeeFragment extends Fragment {
    private FragmentNewEmployeeBinding binding;
    private EmployeeViewModel viewModel;
    private int id = 0;

    public NewEmployeeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Employee employee = NewEmployeeFragmentArgs.fromBundle(getArguments()).getEmployee();
       binding = FragmentNewEmployeeBinding.inflate(inflater);
        viewModel = new ViewModelProvider(requireActivity())
                .get(EmployeeViewModel.class);
        if (employee != null ) {
            id = employee.getId();
            binding.nameInputET.setText(employee.getEmpName());
            binding.salaryInputET.setText(String.valueOf(employee.getEmpSalary()));
            binding.saveBtn.setText("UPDATE");
        }

        binding.saveBtn.setOnClickListener(v -> {
            final String name = binding.nameInputET.getText().toString();
            final double salary = Double.parseDouble(
                    binding.salaryInputET.getText().toString());

            if (id > 0) {
                final Employee emp = new Employee(id, name, salary);
                viewModel.updateEmployee(emp);
            }else {
                final Employee emp = new Employee(name,salary);
                viewModel.addEmployee(emp);
            }

            Navigation.findNavController(v).popBackStack();
        });
        return binding.getRoot();
    }
}