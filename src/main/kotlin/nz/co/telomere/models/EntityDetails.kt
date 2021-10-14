package nz.co.telomere.models
import kotlinx.serialization.*


@Serializable()
data class Entity(
    val id: String? = null,
    val key: String,
    val description: String? = null,
    val isSoftDeletable: Boolean? = true,
    val properties: List<Property>? = null,
    val relationships: List<Relationships>? =null
)

@Serializable()
data class Property(
    val id: String? = null,
    val key: String,
    val description: String? = null,
    val dataType: String? = "text",
    val neverNull: Boolean = false,
    val defaultValue: String? = null
)

@Serializable()
data class Relationships(
    val id: String? = null,
    val name: String? = null,
    val entity: String, // Also serves as the property name
    val description: String? = null,
    val type: String? = "MANY_TO_ONE",  // TODO: Find a way to limiting these types in a way that can be unpacked by JSON.
                                        //  This might just be using Ids from a lookup instead
)
