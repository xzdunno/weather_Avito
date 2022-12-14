package com.example.weather.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.db.Hourly
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.hour_row.view.*

class HourAdapter(/*val all: MainActivity.all*/) : RecyclerView.Adapter<CustomViewHolder>() {
    private var listData: List<Hourly>? = null
    fun setListData(listData: List<Hourly>?) {
        this.listData = listData
    }

    override fun getItemCount(): Int {
        //
        if (listData == null) return 0
        return listData?.size!!
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hour_row, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(listData?.get(position)!!)
    }
}

class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val txtTemp = view.txtTemp
    val txtHumidity = view.txtHumidity
    val txtTime = view.txtTime
    val imgIcon = view.imgIcon

    fun bind(hourly: Hourly) {
        txtHumidity.text = hourly.humidity + "%"
        txtTemp.text = hourly.temp.toDouble().toInt().toString() + "°"
        txtTime.text = hourly.time
        if (hourly.picture == "13n" || hourly.picture == "13d") {
            imgIcon.setImageResource(R.drawable.snowflake)
        } else
            if (hourly.picture == "01n") imgIcon.setImageResource(R.drawable.moon)
            else Picasso.get().load("http://openweathermap.org/img/wn/${hourly.picture}@2x.png")
                .into(imgIcon)
    }
}