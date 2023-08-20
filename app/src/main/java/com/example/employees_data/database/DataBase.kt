package com.example.employees_data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.employees_data.model.Converters
import com.example.employees_data.model.EmployeeModel
import com.example.employees_data.model.EmployeeSkills
import com.example.employees_data.model.SkillModel

@Database(entities = arrayOf(EmployeeModel::class,SkillModel::class,EmployeeSkills::class), version = 1)
@TypeConverters(Converters::class)
    abstract class DataBase: RoomDatabase() {
        abstract fun getSkillsDao():SkillsDao
        abstract fun getEmployeeDao():EmployeeDao
        abstract fun getEmployeeSkillsDao():EmployeeSkillDao

        companion object{
            @Volatile
            private var INSTANCE :DataBase?=null

            fun getInstance (ctx: Context):DataBase{
                return INSTANCE?: synchronized(this){
                    val instance= Room.databaseBuilder(
                        ctx.applicationContext,DataBase::class.java,"employees_database")
                        .build()
                    INSTANCE=instance
                    instance
                }
            }
        }
    }
