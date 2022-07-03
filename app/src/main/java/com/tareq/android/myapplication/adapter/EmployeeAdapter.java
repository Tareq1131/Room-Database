package com.tareq.android.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.tareq.android.myapplication.R;
import com.tareq.android.myapplication.callback.EmployeeActionListener;
import com.tareq.android.myapplication.databinding.EmployeeRowBinding;
import com.tareq.android.myapplication.entities.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>{

    private List<Employee> employeeList = new ArrayList<>();
    private EmployeeActionListener listener;

    public EmployeeAdapter(Fragment fragment) {
        listener = (EmployeeActionListener) fragment;
    }

    public void submitNewList(List<Employee> employees) {
        employeeList = employees;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         final EmployeeRowBinding binding = EmployeeRowBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent, false
        );
        return new EmployeeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        final Employee employee = employeeList.get(position);
        holder.bind(employee);
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {
        private EmployeeRowBinding binding;
        public EmployeeViewHolder(EmployeeRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            this.binding.rowMenuBtn.setOnClickListener(v -> {
                final Employee employee = employeeList.get(getAdapterPosition());
                final PopupMenu popupMenu = new PopupMenu(v.getContext(),v);
                final MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.employee_row_menu,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()){
                        case R.id.item_edit:
                            listener.onEditEmployee(employee);
                            return true;
                        case R.id.item_delete:
                            listener.onDeleteEmployee(employee);
                            return true;
                    }
                    return false;
                });
            });

        }

        public void bind(Employee employee) {
            binding.setEmployee(employee);
        }
    }
}
