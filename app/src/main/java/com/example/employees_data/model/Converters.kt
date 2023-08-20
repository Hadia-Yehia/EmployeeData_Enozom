package com.example.employees_data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromSkillListToString(skillList:ArrayList<String?>?): String? {
        if (skillList == null) {
            return null
        }
        val gson = Gson()
        val type= object :
            TypeToken<ArrayList<String?>?>() {}.type
        return gson.toJson(skillList, type)
    }

    @TypeConverter
    fun fromStringToSkillList(skillString: String?): ArrayList<String>? {
        if (skillString == null) {
            return null
        }
        val gson = Gson()
        val type = object :
            TypeToken<ArrayList<String?>?>() {}.type
        return gson.fromJson<ArrayList<String>>(skillString, type)
    }
}