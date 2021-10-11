package nz.co.telomere.models
import kotlinx.serialization.*


@Serializable()
data class Entity(
    val id: String? = null,
    val name: String,
    val description: String? = null,
    val isSoftDeletable: Boolean? = true,
    val properties: List<Property>? = null,
)

@Serializable()
data class Property(
    val id: String? = null,
    val name: String,
    val description: String? = null,
    val dataType: String? = "text",
    val neverNull: Boolean = false,
    val defaultValue: String? = null
)
