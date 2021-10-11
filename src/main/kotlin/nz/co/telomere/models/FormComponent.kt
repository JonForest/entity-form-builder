package nz.co.telomere.models

import kotlinx.serialization.Serializable

@Serializable
data class FormComponent(val id: Int? = null, val key: String, val description: String? = null, val code: String)