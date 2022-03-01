package edu.itesm.numeros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import edu.itesm.numeros.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val list = mutableListOf<Double>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI(){
        binding.agregar.setOnClickListener { addInt() }
        binding.promedio.setOnClickListener { if(validateList()) getAvg() }
        binding.mayor.setOnClickListener { if(validateList()) getMax() }
        binding.pares.setOnClickListener { if(validateList()) getEven() }

    }

    private fun validateList(): Boolean{
        if(list.isEmpty()) {
            Toast.makeText(this, "List is empty, add some numbers", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun addInt(){
        val num = binding.editTextNumberSigned.text.toString().toDoubleOrNull()
        if (num == null){
            Toast.makeText(this, "Invalid number", Toast.LENGTH_SHORT).show()
            return
        }
        list.add(num)
        binding.editTextNumberSigned.text.clear()
    }

    private fun getAvg(){
        binding.res.text = list.average().toString()
    }

    private fun getMax(){
        binding.res.text = "${list.maxOrNull() ?:0}"
    }

    private fun getEven(){
        var ans = ""
        for(num in list.iterator()){
            if(num%2==0.0) ans+="${num}, "
        }
        binding.res.text = ans.subSequence(0,ans.length-2)
    }
}