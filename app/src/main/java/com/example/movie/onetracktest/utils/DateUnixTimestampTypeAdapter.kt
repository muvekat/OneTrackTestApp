package com.example.movie.onetracktest.utils

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.util.*

class DateUnixTimestampTypeAdapter: TypeAdapter<Date>(){
    override fun write(out: JsonWriter?, value: Date?) {
        if (value == null || out == null)
            return

        out.value(value.time)
    }

    override fun read(reader: JsonReader?): Date? {
        if (reader == null || reader.peek() == JsonToken.NULL){
            reader?.nextNull()
            return null
        }

        val unixTimestamp = reader.nextLong()
        return Date(unixTimestamp)
    }
}