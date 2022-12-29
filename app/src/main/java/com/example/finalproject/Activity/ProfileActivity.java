package com.example.finalproject.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.finalproject.R;
import com.example.finalproject.databinding.ActivityProfileBinding;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Date;
import java.util.List;

import RoomDatabase.DateConverter;
import RoomDatabase.User;
import RoomDatabase.ViewModel;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;
    DatePickerDialog dpd;
    String age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewModel viewModel = new ViewModelProvider(this).get(ViewModel.class);

        String user_name = binding.ProfileUserName.getText().toString().trim();
        String email = binding.ProfileEmail.getText().toString().trim();
        String birthDate = binding.ProfileBirth.getText().toString().trim();
        String male = binding.ProfileMale.getText().toString();
        String female = binding.ProfileFemale.getText().toString();
        String country = binding.ProfileCountry.getText().toString().trim();

        binding.ProfileBirth.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                // بجيب التاريخ الحالي
                Calendar now = Calendar.getInstance();
                dpd = DatePickerDialog.newInstance(
                        //عبارة عن ليسنر لمن اضغط على عنصر في الكليندر يروح ينفذلي اياه
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            // ليسنر على دالة أون ديت ست
                            // تستدعى لمن المستخدم يختار تاريخ
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                String birth = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                binding.ProfileBirth.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                // الشهر زائد 1 لانو الشهر في الكلندر بيبدا من صفر
                                age = String.valueOf(now.get(Calendar.YEAR) - year);
                            }
                        },
                        // عشان اول ما يفتح الديت بيكر يفتح على الوقت الحالي
                        now.get(Calendar.YEAR), // Initial year selection
                        now.get(Calendar.MONTH), // Initial month selection
                        now.get(Calendar.DAY_OF_MONTH) // Inital day selection
                );
                dpd.show(getSupportFragmentManager(),"Datepickerdialog");
            }
        });

        String[] type = new String[]{"Palestine", "Egypt", "Syria", "Lebanon", "Jordan", "Turkey"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.drop_down_item,
                type
        );
        binding.ProfileCountry.setAdapter(adapter);

        viewModel.UpdateUser(new User(1,user_name,email,birthDate,male,female,country));

        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.AllUser().observe(ProfileActivity.this, new Observer<List<User>>() {
                    @Override
                    public void onChanged(List<User> users) {
//                         users.get(0).getId();

//                        viewModel.UpdateUser(new User(1,user_name,email,birthDate,male,female,country));
                        Log.d("user ",users.get(0).toString());
                    }
                });
            }
        });



//        viewModel.InsertUser(new User(user_name,email,,male,female,country));
    }
}