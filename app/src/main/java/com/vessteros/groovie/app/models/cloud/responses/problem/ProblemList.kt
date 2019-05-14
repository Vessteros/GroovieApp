package com.vessteros.groovie.app.models.cloud.responses.problem

/**
 * Модель проблемы при 200 OK
 */
class ProblemList {
    data class Problem(val line: Int?, val file: String?, val message: String?): BaseProblem
}