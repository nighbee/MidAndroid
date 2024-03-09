package com.example.aviatickets.model.entity

import com.google.gson.*
import java.lang.reflect.Field
import java.lang.reflect.Type

data class Offer(
    val id: String,
    val price: Int,
    val flight: Flight
)

data class Flight(
    val departureLocation: Location,
    val arrivalLocation: Location,
    val departureTimeInfo: String,
    val arrivalTimeInfo: String,
    val flightNumber: String,
    val airline: Airline,
    val duration: Int
)

data class Location(
    val city: String,
    val airport: String
)

data class Airline(
    val name: String,
    val code: String
)

object SnakeCaseNamingStrategy : FieldNamingStrategy {
    override fun translateName(f: Field): String {
        val name = f.name
        val result = StringBuilder()
        for (i in name.indices) {
            val c = name[i]
            result.append(if (Character.isUpperCase(c)) "_" + Character.toLowerCase(c) else c)
        }
        return result.toString()
    }
}

class OfferDeserializer : JsonDeserializer<Offer> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Offer {
        val jsonObject = json?.asJsonObject
        return Offer(
            id = jsonObject?.get("id")?.asString.orEmpty(),
            price = jsonObject?.get("price")?.asInt ?: 0,
            flight = context?.deserialize(jsonObject?.get("flight"), Flight::class.java) ?: Flight(
                departureLocation = Location("", ""),
                arrivalLocation = Location("", ""),
                departureTimeInfo = "",
                arrivalTimeInfo = "",
                flightNumber = "",
                airline = Airline("", ""),
                duration = 0
            )
        )
    }
}