package weatherapp.mohammadsme2000.gmail.weather

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    var requestQueue:RequestQueue?=null
    var context :Context?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this
        requestQueue = Volley.newRequestQueue(context)
        getWeather()
    }


    fun getIcon(code: Int) {
        when (code) {
            in 200..232 -> icon.setImageResource(R.drawable.thunderstorm)
            in 300..321 -> icon.setImageResource(R.drawable.drizzle)
            in 500..531 -> icon.setImageResource(R.drawable.rain)
            in 600..622 -> icon.setImageResource(R.drawable.snow)
            in 700..781 -> icon.setImageResource(R.drawable.atmosphere)
            800 -> icon.setImageResource(R.drawable.clear)
            in 801..804 -> icon.setImageResource(R.drawable.cloudy)
        }
    }
    /*
   get weather fun is to one 1
             */


    fun getWeather() {
        buttonsearch.setOnClickListener {
            var city: String = editext.text.toString()
            var main_url = "https://api.openweathermap.org/data/2.5/weather?q="
            var main_part2 = "&appid="
            var key = "656c448c40cc8244698419f8639edc1c"
            city = main_url + city + main_part2 + key
            var jsonreq = JsonObjectRequest(Request.Method.GET, city, null,
                Response.Listener {
                    try {
                        /*
                            desc    */
                        var weather_array = it.getJSONArray("weather")
                        var weather_object = weather_array.getJSONObject(0)
                        var weather_text = weather_object.getString("description")
                        var weather_code = weather_object.getInt("id")
                        weather.text = weather_text
                        getIcon(weather_code)

                        /*
                          temp    */


                        var main_obj = it.getJSONObject("main")
                        var temp_weather:Long = main_obj.getLong("temp")
                        var humidity_weather = main_obj.getString("humidity")
                        var kelvin=temp_weather-273
                        temp.text =kelvin.toString()+" :C°"
                        hum_text.text = humidity_weather



                        /*
                                    wind
                                     */



                        var wind_obj = it.getJSONObject("wind")
                        var wind_speed = wind_obj.getString("speed")
                        wind_text.text = wind_speed





                        /*
                        sunset and city Country ->>>>>>>>
                        >>>>>
                         */
                        var sys = it.getJSONObject("sys")
                        var sunrise:Long=sys.getLong("sunrise")
                        var sunset:Long=sys.getLong("sunset")
                        val country=sys.getString("country")
                        val city = it.getString("name")
                        sunrise_input_textview.text=SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise*1000))
                        sunset_input_textview.text=SimpleDateFormat("hh:mm a",Locale.ENGLISH).format(Date(sunset*1000))
                        country_text.text=country
                        text_city.text=city




                    } catch (e: Exception) {

                        Toast.makeText(context,
                            "try: " + e.toString().toString(),


                            Toast.LENGTH_LONG).show() } },

                Response.ErrorListener {
                    Toast.makeText(context,
                        "res: " + it.toString().toString(),
                        Toast.LENGTH_LONG).show() })
            requestQueue!!.add(jsonreq)

        }
    }

}