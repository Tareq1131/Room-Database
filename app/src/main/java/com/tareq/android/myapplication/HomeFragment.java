package com.tareq.android.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tareq.android.myapplication.adapter.EmployeeAdapter;
import com.tareq.android.myapplication.callback.EmployeeActionListener;
import com.tareq.android.myapplication.databinding.FragmentHomeBinding;
import com.tareq.android.myapplication.entities.Employee;
import com.tareq.android.myapplication.viewmodels.EmployeeViewModel;


public class HomeFragment extends Fragment implements EmployeeActionListener {
    private FragmentHomeBinding binding;
    private EmployeeViewModel viewModel;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(requireActivity())
                .get(EmployeeViewModel.class);

        final EmployeeAdapter adapter = new EmployeeAdapter(this);
        binding.employeeRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.employeeRv.setAdapter(adapter);

        viewModel.getEmployees().observe(getViewLifecycleOwner(),
                employees -> {
                    adapter.submitNewList(employees);
                });


        binding.newEmpFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v)
                        .navigate(R.id.new_emp_fragment_action);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onEditEmployee(Employee employee) {
      final HomeFragmentDirections.NewEmpFragmentAction action=  HomeFragmentDirections.newEmpFragmentAction();
      action.setEmployee(employee);
      Navigation.findNavController(getActivity(),R.id.fragmentContainerView)
              .navigate(action);
    }

    @Override
    public void onDeleteEmployee(Employee employee) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.drawable.ic_baseline_delete_forever_24);
        builder.setTitle("Delete"+employee.getEmpName()+"?");
        builder.setMessage("You are about to delete this employee.Press yes to delete");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               viewModel.deleteEmployee(employee);
            }
        });
        builder.setNegativeButton("No",null );
        final AlertDialog dialog = builder.create();
        dialog.show();
    }
}