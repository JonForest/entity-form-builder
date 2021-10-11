package nz.co.telomere.models
import kotlinx.serialization.*

@Serializable()
data class Configuration(val entities: List<Entity>)

@Serializable()
data class ResponseError(val success: Boolean = false, val error: String)
