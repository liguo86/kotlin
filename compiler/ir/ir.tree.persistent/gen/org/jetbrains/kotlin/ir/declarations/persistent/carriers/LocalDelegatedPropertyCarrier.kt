/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.declarations.persistent.carriers

import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
import org.jetbrains.kotlin.ir.declarations.IrVariable
import org.jetbrains.kotlin.ir.declarations.MetadataSource
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.types.IrType

// Auto-generated by compiler/ir/ir.tree.persistent/generator/src/org/jetbrains/kotlin/ir/persistentIrGenerator/Main.kt. DO NOT EDIT!

internal interface LocalDelegatedPropertyCarrier : DeclarationCarrier{
    var typeField: IrType
    var delegateField: IrVariable?
    var getterField: IrSimpleFunction?
    var setterField: IrSimpleFunction?
    var metadataField: MetadataSource?

    override fun clone(): LocalDelegatedPropertyCarrier {
        return LocalDelegatedPropertyCarrierImpl(
            lastModified,
            parentField,
            originField,
            annotationsField,
            typeField,
            delegateField,
            getterField,
            setterField,
            metadataField
        )
    }
}

internal class LocalDelegatedPropertyCarrierImpl(
    override val lastModified: Int,
    override var parentField: IrDeclarationParent?,
    override var originField: IrDeclarationOrigin,
    override var annotationsField: List<IrConstructorCall>,
    override var typeField: IrType,
    override var delegateField: IrVariable?,
    override var getterField: IrSimpleFunction?,
    override var setterField: IrSimpleFunction?,
    override var metadataField: MetadataSource?
) : LocalDelegatedPropertyCarrier
