package nz.co.telomere.models

import kotlinx.serialization.Serializable

@Serializable
data class Field (
    val key: String? = null,
    val description: String? = null,
    val property: String? = null,
    val fieldType: String? = null,
    val form: Form?);

@Serializable
data class Form (
    val key: String? = null,
    val description: String? = null,
    val version: String? = null,
    val fields: List<Field>?);