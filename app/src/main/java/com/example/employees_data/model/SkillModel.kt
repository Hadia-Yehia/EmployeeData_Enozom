package com.example.employees_data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "skills_table")
data class SkillModel(var skill:String) {
    @PrimaryKey(autoGenerate = true)
    var id :Int=0
}