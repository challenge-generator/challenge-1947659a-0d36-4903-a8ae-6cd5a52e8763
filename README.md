# Integración de Paradigmas Reactivos y Funcionales en Sistema de Pagos

En el dominio de los sistemas de pago, donde la resiliencia y la escalabilidad son críticas, se requiere adoptar paradigmas de programación no imperativos. El sistema debe manejar un volumen de 1 500 transacciones por segundo en hora pico, con un SLA de 99.99%. Los actores involucrados incluyen el originador de créditos, el motor antifraude, el buró de riesgos, y el gateway de pagos. El sistema debe asegurar idempotencia en el registro de transacciones por número de operación y canal, con una ventana de 24 horas para reintentos. Se espera que el candidato implemente y defienda decisiones arquitectónicas que favorezcan el rendimiento, escalabilidad y resiliencia.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Adopción de Paradigmas de Programación No Imperativos: Con Enfoque Reactivo y Funcional |
| **Nivel** | senior-l2 |
| **Tipo** | mixed |
| **Tiempo estimado** | 40 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Exploración y Modelado Inicial

**Objetivo:** Comprender y modelar el dominio de los sistemas de pago con enfoque en paradigmas no imperativos.

**Tiempo estimado:** 8 horas

**Instrucciones:**

- Identifica los actores y flujos clave en el dominio de los sistemas de pago.
- Modela el sistema para manejar un volumen de 1 500 transacciones por segundo, asegurando idempotencia en el registro de transacciones.

**Entregable:** Modelo conceptual del sistema de pagos con identificación de actores, flujos y restricciones.

<details>
<summary>Pistas de conocimiento</summary>

- Considera los cuatro pilares de los sistemas reactivos: respuesta, resiliencia, elasticidad y mensajería.
- Piensa en cómo los paradigmas funcionales pueden mejorar la consistencia y la mantenibilidad del código.

</details>

### Fase 2: Implementación de Procesamiento Reactivo

**Objetivo:** Implementar el procesamiento de transacciones utilizando paradigmas reactivos.

**Tiempo estimado:** 12 horas

**Instrucciones:**

- Implementa el procesamiento de transacciones utilizando operadores básicos de programación reactiva.
- Asegura que el sistema maneje correctamente los reintentos idempotentes y los errores del buró de riesgos.

**Entregable:** Sistema de procesamiento de transacciones reactivo, capaz de manejar reintentos idempotentes y errores.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza operadores como map, flatMap, y retry para manejar flujos de datos reactivos.
- Considera el uso de patrones de diseño reactivos para mejorar la resiliencia y la escalabilidad.

</details>

### Fase 3: Optimización y Refactorización Funcional

**Objetivo:** Optimizar y refactorizar el sistema utilizando paradigmas funcionales.

**Tiempo estimado:** 10 horas

**Instrucciones:**

- Refactoriza el código para utilizar paradigmas funcionales, mejorando la consistencia y la mantenibilidad.
- Evalúa el rendimiento y la escalabilidad del sistema después de la refactorización.

**Entregable:** Sistema refactorizado con paradigmas funcionales, optimizado para rendimiento y escalabilidad.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza funciones puras y composición de funciones para mejorar la consistencia del código.
- Evalúa el impacto de la refactorización en el rendimiento y la escalabilidad del sistema.

</details>

### Fase 4: Defensa de Decisiones Arquitectónicas

**Objetivo:** Defender las decisiones arquitectónicas tomadas durante el desarrollo del sistema.

**Tiempo estimado:** 10 horas

**Instrucciones:**

- Documenta y defiende las decisiones arquitectónicas tomadas durante el desarrollo del sistema.
- Proporciona argumentos sólidos para cada decisión, considerando trade-offs y alternativas.

**Entregable:** Documento de decisiones arquitectónicas con argumentos y trade-offs.

<details>
<summary>Pistas de conocimiento</summary>

- Considera trade-offs como consistencia vs. disponibilidad, sincronía vs. asincronía.
- Proporciona ejemplos concretos de cómo cada decisión impacta el sistema.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son los paradigmas reactivos y funcionales y cómo se aplican en el dominio de los sistemas de pago?
- **paraQueSirve**: ¿Para qué sirven los paradigmas reactivos y funcionales en términos de rendimiento, escalabilidad y resiliencia?
- **comoSeUsa**: ¿Cómo se utilizan los operadores básicos de programación reactiva y funcional en el sistema de pagos?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar paradigmas reactivos y funcionales y cómo se pueden mitigar?
- **queDecisionesImplica**: ¿Qué decisiones arquitectónicas implica la adopción de paradigmas reactivos y funcionales y cómo se defienden?

## Criterios de Evaluacion

- Implementación de procesamiento reactivo de transacciones con idempotencia.
- Refactorización del sistema utilizando paradigmas funcionales.
- Defensa de decisiones arquitectónicas con argumentos sólidos y consideración de trade-offs.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
