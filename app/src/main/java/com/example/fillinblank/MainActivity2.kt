package com.example.fillinblank

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.fillinblank.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    var strList= ArrayList<DataModel>()
    var spaceCount =0
    val regex = Regex("\\s{2,}")


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.okBtn.setOnClickListener {
            val resTxt = binding.inputTxt.text.toString()
            val parts = regex.split(resTxt)
            for (part in parts.indices) {
                strList.add(DataModel(parts[part]))
                if(part<parts.size-1) {
                    strList.add(DataModel("SPACE",part.toString()))
                }
            }
            binding.previewTxt.text=strList.toString()
            val intent=Intent(this,MainActivity::class.java)
            intent.putExtra("IMAGE_LIST", strList)
            startActivity(intent)
        }



    }
}