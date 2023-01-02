package com.example.finalproject.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

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
    public static String UserName, Email, Country, Birthday, Gender;
    int GenderId;
    public static SharedPreferences sp;
    public static SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sp = getSharedPreferences("Login", MODE_PRIVATE);
        editor = sp.edit();
        ViewModel viewModel = new ViewModelProvider(this).get(ViewModel.class);

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
                                binding.ProfileBirth.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);// الشهر زائد 1 لانو الشهر في الكلندر بيبدا من صفر
                                age = String.valueOf(now.get(Calendar.YEAR) - year);
                            }
                        },
                        // عشان اول ما يفتح الديت بيكر يفتح على الوقت الحالي
                        now.get(Calendar.YEAR), // Initial year selection
                        now.get(Calendar.MONTH), // Initial month selection
                        now.get(Calendar.DAY_OF_MONTH) // Inital day selection
                );
                dpd.show(getSupportFragmentManager(), "Datepickerdialog");
            }
        });

        String[] type = new String[]{"Palestine", "Egypt", "Syria", "Lebanon", "Jordan", "Turkey"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.drop_down_item,
                type
        );
        binding.ProfileCountry.setAdapter(adapter);

        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserName = binding.ProfileUser.getText().toString().trim();
                Email = binding.ProfileEmail.getText().toString();
                Country = binding.ProfileCountry.getText().toString();
                Birthday = binding.ProfileBirth.getText().toString();
                GenderId = binding.RBGGender.getCheckedRadioButtonId();
                Gender = findViewById(GenderId).toString();

                if (binding.ProfileMale.isChecked()) {
                    Gender = "Male";
                } else {
                    Gender = "Female";
                }

                viewModel.AllUser().observe(ProfileActivity.this, new Observer<List<User>>() {
                    @Override
                    public void onChanged(List<User> users) {
                        if (!UserName.isEmpty() && !Email.isEmpty() && !Country.isEmpty() && !Birthday.isEmpty() && !Gender.isEmpty()){
                            if (!isValidEmail(Email)) {
                                binding.ProfileEmail.setError("Please check the email");         
                            }
                            else {
                                viewModel.UpdateUser(new User(1, UserName, Email, Birthday, Gender, Country));
                            }
                        }
                        else {
                            Toast.makeText(ProfileActivity.this, "please enter fields", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        binding.tvCountQ.setText(SplashActivity.sp.getInt(LevelActivity.CountQus, 0) + "");
        binding.tvCountF.setText(SplashActivity.sp.getInt(LevelActivity.CountFQus, 0) + "");
        binding.tvCountTQ.setText(SplashActivity.sp.getInt(LevelActivity.CountTQus, 0) + "");
    }
    public static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            // في كلاس Patterns هل الايميل مطابق للمتسلسل target
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

}