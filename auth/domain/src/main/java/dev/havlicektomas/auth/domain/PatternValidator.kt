package dev.havlicektomas.auth.domain

interface PatternValidator {
    fun matches(value: String): Boolean
}