package nz.co.telomere.models
import kotlinx.serialization.Serializable

@Serializable
data class FormComponent(val key: String, val description: String? = null, val code: String)