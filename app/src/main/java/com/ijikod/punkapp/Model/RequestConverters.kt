package com.ijikod.punkapp.Model

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.ParameterizedType


class RequestConverters {

    private val moshi = Moshi.Builder().build()
    private val listString : ParameterizedType = Types.newParameterizedType(List::class.java, String::class.java)
    private val listStringJsonAdapter: JsonAdapter<List<String>> = moshi.adapter(listString)


    private val listMash : ParameterizedType = Types.newParameterizedType(List::class.java, MashTemp::class.java)
    private val listMashJsonAdapter: JsonAdapter<List<MashTemp>> = moshi.adapter(listMash)


    private val listMalt : ParameterizedType = Types.newParameterizedType(List::class.java, Malt::class.java)
    private val listMaltJsonAdapter: JsonAdapter<List<Malt>> = moshi.adapter(listMalt)


    private val listHop : ParameterizedType = Types.newParameterizedType(List::class.java, Hop::class.java)
    private val listHopJsonAdapter: JsonAdapter<List<Hop>> = moshi.adapter(listHop)



    @TypeConverter
    fun listStringToJsonStr(listMyModel: List<String>?): String? {
        return listStringJsonAdapter.toJson(listMyModel)
    }

    @TypeConverter
    fun jsonStrToListString(jsonStr: String?): List<String>? {
        return jsonStr?.let { listStringJsonAdapter.fromJson(jsonStr) }
    }



    @TypeConverter
    fun listMashToJsonStr(listMash: List<MashTemp>?): String? {
        return listMashJsonAdapter.toJson(listMash)
    }

    @TypeConverter
    fun jsonMashToListString(jsonStr: String?): List<MashTemp>? {
        return jsonStr?.let { listMashJsonAdapter.fromJson(jsonStr) }
    }



    @TypeConverter
    fun listMaltToJsonStr(listMalt: List<Malt>?): String? {
        return listMaltJsonAdapter.toJson(listMalt)
    }

    @TypeConverter
    fun jsonMaltToListString(jsonStr: String?): List<Malt>? {
        return jsonStr?.let { listMaltJsonAdapter.fromJson(jsonStr) }
    }



    @TypeConverter
    fun listHopToJsonStr(listHop: List<Hop>?): String? {
        return listHopJsonAdapter.toJson(listHop)
    }

    @TypeConverter
    fun jsonHopToListString(jsonStr: String?): List<Hop>? {
        return jsonStr?.let { listHopJsonAdapter.fromJson(jsonStr) }
    }






}