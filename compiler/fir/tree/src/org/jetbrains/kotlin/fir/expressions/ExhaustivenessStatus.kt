/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.expressions

import org.jetbrains.kotlin.fir.symbols.CallableId
import org.jetbrains.kotlin.name.ClassId

sealed class ExhaustivenessStatus {
    object Exhaustive : ExhaustivenessStatus()
    class NotExhaustive(val reasons: List<WhenMissingCase>) : ExhaustivenessStatus() {
        companion object {
            val NO_ELSE_BRANCH = NotExhaustive(listOf(WhenMissingCase.Unknown))
        }
    }
}

sealed class WhenMissingCase() {
    abstract val branchConditionText: String

    object Unknown : WhenMissingCase() {
        override fun toString(): String = "unknown"

        override val branchConditionText: String = "else"
    }

    object NullIsMissing : WhenMissingCase() {
        override val branchConditionText: String = "null"
    }

    sealed class BooleanIsMissing(val value: Boolean) : WhenMissingCase() {
        object True : BooleanIsMissing(true)
        object False : BooleanIsMissing(false)

        override val branchConditionText: String = value.toString()
    }

    class IsTypeCheckIsMissing(val classId: ClassId, val isSingleton: Boolean) : WhenMissingCase() {
        override val branchConditionText: String = run {
            val fqName = classId.asSingleFqName().toString()
            if (isSingleton) fqName else "is $fqName"
        }

        override fun toString(): String {
            val name = classId.shortClassName.identifier
            return if (isSingleton) name else "is $name"
        }
    }

    class EnumCheckIsMissing(val callableId: CallableId) : WhenMissingCase() {
        override val branchConditionText: String = callableId.asFqNameForDebugInfo().toString()

        override fun toString(): String {
            return callableId.callableName.identifier
        }
    }

    override fun toString(): String {
        return branchConditionText
    }
}

val FirWhenExpression.isExhaustive: Boolean
    get() = exhaustivenessStatus == ExhaustivenessStatus.Exhaustive
