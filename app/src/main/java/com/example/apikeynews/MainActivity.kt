package com.example.apikeynews

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupListOfDataIntoRecyclerView()

    }

    fun setupListOfDataIntoRecyclerView() {
        rv_item.layoutManager = LinearLayoutManager(this)


        // Ambil data CEO dari API
        var apiInterface: ApiInterface = ApiClient().getApiClient()!!.create(ApiInterface::class.java)
        apiInterface.getCEOs().enqueue(object : Callback<ArrayList<CEOModel>> {


            override fun onFailure(call: Call<ArrayList<CEOModel>>?, t: Throwable) {
                Toast.makeText(baseContext, "Data downloading is failed", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<ArrayList<CEOModel>>?, response: Response<ArrayList<CEOModel>>?) {
                var ceoData = response?.body()!!
                if (ceoData.size > 0) {
                    rv_item.visibility = View.VISIBLE
                    TV_No_record.visibility = View.GONE
                    rv_item.adapter = MyAdapter(this@MainActivity, ceoData)
                } else {
                    rv_item.visibility = View.GONE
                    TV_No_record.visibility = View.VISIBLE
                }
                Toast.makeText(baseContext,"Data download success", Toast.LENGTH_LONG).show()

            }
        })

    }
    // method to close keyboard
    private fun closeKeyboard(){
        val view = this.currentFocus
        if (view!= null){
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken,0)
        }
    }

}
