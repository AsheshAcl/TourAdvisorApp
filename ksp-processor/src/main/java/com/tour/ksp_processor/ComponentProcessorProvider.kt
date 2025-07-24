package com.tour.ksp_processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.tour.annotations.Component

class ComponentProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return ComponentProcessor(environment.codeGenerator, environment.logger)
    }
}

class ComponentProcessor(
    private val codeGenerator: CodeGenerator, private val logger: KSPLogger
) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation(Component::class.qualifiedName!!)
        val map = mutableMapOf<String, String>()
        logger.warn("Processing: Warning from KSP}")
        for (symbol in symbols) {
            if (symbol is KSFunctionDeclaration) {
                val annotation = symbol.annotations.first {
                    it.shortName.asString() == "Component"
                }
                val typeName = annotation.arguments.first().value as String
                val qualifiedName = symbol.qualifiedName?.asString() ?: continue
                map[typeName] = qualifiedName
            }
        }
        try {
            val file = codeGenerator.createNewFile(
                Dependencies(true), "com.tour.advisor", "ComponentRegistry"
            )

            file.bufferedWriter().use { writer ->
                writer.appendLine("package com.tour.advisor")
                writer.appendLine()
                writer.appendLine("import androidx.compose.runtime.Composable")
                writer.appendLine("import com.tour.advisor.domain.models.ComponentStateModel")
                writer.appendLine("import com.tour.advisor.presentation.dynamicUI.action.ComponentActionHandler")

                map.forEach { (_, fqFunctionName) ->
                    val functionImport =
                        fqFunctionName.substringBeforeLast(".") + "." + fqFunctionName.substringAfterLast(
                            "."
                        )
                    writer.appendLine("import $functionImport")
                }

                writer.appendLine()
                writer.appendLine("object ComponentRegistry {")
                writer.appendLine("  val components: Map<String, @Composable (ComponentStateModel, ComponentActionHandler) -> Unit> = mapOf(")

                map.forEach { (type, fqFunctionName) ->
                    val functionName = fqFunctionName.substringAfterLast(".")
                    writer.appendLine("    \"$type\" to { component, handler -> $functionName(component, handler) },")
                }

                writer.appendLine("  )")
                writer.appendLine("}")
            }
        } catch (e: Exception) {
            logger.warn("File already exist: ${e.message}")
        }


        return emptyList()
    }
}