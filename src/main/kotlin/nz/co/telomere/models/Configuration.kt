package nz.co.telomere.models
import kotlinx.serialization.*

@Serializable()
data class Configuration(val entities: List<Entity>)
