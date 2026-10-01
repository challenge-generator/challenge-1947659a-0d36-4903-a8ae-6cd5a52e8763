# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/main/java/com/pragma/payments/infrastructure/config/Resilience4jConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/pragma/payments/Application.java` — `reactor.core.scheduler`: El import reactor.core.scheduler.Schedulers pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/domain/port/TransactionRepositoryPort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/domain/port/AntifraudServicePort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/domain/port/RiskBureauPort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/TransactionService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/TransactionService.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/TransactionService.java` — `reactor.core.scheduler`: El import reactor.core.scheduler.Scheduler pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/TransactionService.java` — `reactor.util.function`: El import reactor.util.function.Tuple2 pertenece a reactor.util.function, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapter/AntifraudWebClientAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapter/AntifraudWebClientAdapter.java` — `reactor.core.scheduler`: El import reactor.core.scheduler.Schedulers pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapter/RiskBureauWebClientAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapter/RiskBureauWebClientAdapter.java` — `reactor.core.scheduler`: El import reactor.core.scheduler.Schedulers pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/config/WebClientConfig.java` — `io.netty.channel`: El import io.netty.channel.ChannelOption pertenece a io.netty.channel, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/config/WebClientConfig.java` — `io.netty.handler`: El import io.netty.handler.timeout.ReadTimeoutHandler pertenece a io.netty.handler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/config/WebClientConfig.java` — `reactor.netty.http`: El import reactor.netty.http.client.HttpClient pertenece a reactor.netty.http, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/config/WebClientConfig.java` — `reactor.netty.resources`: El import reactor.netty.resources.ConnectionProvider pertenece a reactor.netty.resources, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/controller/TransactionController.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/exception/GlobalErrorHandler.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/payments/application/TransactionServiceTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapterTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/TransactionService.java` — `Transaction.getOperationNumber`: Se invoca `getOperationNumber` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/TransactionService.java` — `Transaction.getChannel`: Se invoca `getChannel` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/TransactionService.java` — `Transaction.getIdempotencyKey`: Se invoca `getIdempotencyKey` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/TransactionService.java` — `Transaction.getCreditOriginator`: Se invoca `getCreditOriginator` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/TransactionService.java` — `Transaction.getId`: Se invoca `getId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/TransactionService.java` — `Transaction.setId`: Se invoca `setId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/TransactionService.java` — `Transaction.setOperationNumber`: Se invoca `setOperationNumber` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/TransactionService.java` — `Transaction.setChannel`: Se invoca `setChannel` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/TransactionService.java` — `Transaction.setCreditOriginator`: Se invoca `setCreditOriginator` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/TransactionService.java` — `Transaction.setAccountNumber`: Se invoca `setAccountNumber` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/TransactionService.java` — `Transaction.getAccountNumber`: Se invoca `getAccountNumber` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/TransactionService.java` — `Transaction.setAmount`: Se invoca `setAmount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/TransactionService.java` — `Transaction.getAmount`: Se invoca `getAmount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/TransactionService.java` — `Transaction.setCurrency`: Se invoca `setCurrency` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/TransactionService.java` — `Transaction.getCurrency`: Se invoca `getCurrency` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/TransactionService.java` — `Transaction.setCreatedAt`: Se invoca `setCreatedAt` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/TransactionService.java` — `Transaction.setStatus`: Se invoca `setStatus` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/TransactionService.java` — `Transaction.setAntifraudResponse`: Se invoca `setAntifraudResponse` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/TransactionService.java` — `Transaction.getAntifraudResponse`: Se invoca `getAntifraudResponse` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/TransactionService.java` — `Transaction.setRiskBureauResponse`: Se invoca `setRiskBureauResponse` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/TransactionService.java` — `Transaction.getRiskBureauResponse`: Se invoca `getRiskBureauResponse` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/TransactionService.java` — `Transaction.setPaymentGatewayResponse`: Se invoca `setPaymentGatewayResponse` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/TransactionService.java` — `Transaction.getPaymentGatewayResponse`: Se invoca `getPaymentGatewayResponse` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/TransactionService.java` — `Transaction.setIdempotencyKey`: Se invoca `setIdempotencyKey` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapter.java` — `Transaction.getId`: Se invoca `getId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapter.java` — `Transaction.getOperationNumber`: Se invoca `getOperationNumber` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapter.java` — `Transaction.getChannel`: Se invoca `getChannel` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapter.java` — `Transaction.getCreditOriginator`: Se invoca `getCreditOriginator` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapter.java` — `Transaction.getAccountNumber`: Se invoca `getAccountNumber` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapter.java` — `Transaction.getAmount`: Se invoca `getAmount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapter.java` — `Transaction.getCurrency`: Se invoca `getCurrency` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapter.java` — `Transaction.getCreatedAt`: Se invoca `getCreatedAt` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapter.java` — `Transaction.getStatus`: Se invoca `getStatus` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapter.java` — `Transaction.getAntifraudResponse`: Se invoca `getAntifraudResponse` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapter.java` — `Transaction.getRiskBureauResponse`: Se invoca `getRiskBureauResponse` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapter.java` — `Transaction.getPaymentGatewayResponse`: Se invoca `getPaymentGatewayResponse` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapter.java` — `Transaction.getIdempotencyKey`: Se invoca `getIdempotencyKey` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapter.java` — `Transaction.setId`: Se invoca `setId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapter.java` — `Transaction.setOperationNumber`: Se invoca `setOperationNumber` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapter.java` — `Transaction.setChannel`: Se invoca `setChannel` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapter.java` — `Transaction.setCreditOriginator`: Se invoca `setCreditOriginator` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapter.java` — `Transaction.setAccountNumber`: Se invoca `setAccountNumber` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapter.java` — `Transaction.setAmount`: Se invoca `setAmount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapter.java` — `Transaction.setCurrency`: Se invoca `setCurrency` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapter.java` — `Transaction.setCreatedAt`: Se invoca `setCreatedAt` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapter.java` — `Transaction.setStatus`: Se invoca `setStatus` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapter.java` — `Transaction.setAntifraudResponse`: Se invoca `setAntifraudResponse` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapter.java` — `Transaction.setRiskBureauResponse`: Se invoca `setRiskBureauResponse` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapter.java` — `Transaction.setPaymentGatewayResponse`: Se invoca `setPaymentGatewayResponse` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapter.java` — `Transaction.setIdempotencyKey`: Se invoca `setIdempotencyKey` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/AntifraudWebClientAdapter.java` — `Transaction.getId`: Se invoca `getId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/AntifraudWebClientAdapter.java` — `Transaction.getOperationNumber`: Se invoca `getOperationNumber` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/AntifraudWebClientAdapter.java` — `Transaction.getChannel`: Se invoca `getChannel` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/AntifraudWebClientAdapter.java` — `Transaction.getCreditOriginator`: Se invoca `getCreditOriginator` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/AntifraudWebClientAdapter.java` — `Transaction.getAccountNumber`: Se invoca `getAccountNumber` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/AntifraudWebClientAdapter.java` — `Transaction.getAmount`: Se invoca `getAmount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/AntifraudWebClientAdapter.java` — `Transaction.getCurrency`: Se invoca `getCurrency` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/RiskBureauWebClientAdapter.java` — `Transaction.getId`: Se invoca `getId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/RiskBureauWebClientAdapter.java` — `Transaction.getCreditOriginator`: Se invoca `getCreditOriginator` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/RiskBureauWebClientAdapter.java` — `Transaction.getAccountNumber`: Se invoca `getAccountNumber` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/RiskBureauWebClientAdapter.java` — `Transaction.getAmount`: Se invoca `getAmount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/RiskBureauWebClientAdapter.java` — `Transaction.getCurrency`: Se invoca `getCurrency` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/TransactionController.java` — `TransactionService.findById`: Se invoca `findById` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/TransactionController.java` — `TransactionService.findAll`: Se invoca `findAll` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/TransactionController.java` — `TransactionService.deleteById`: Se invoca `deleteById` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/dto/TransactionResponse.java` — `Transaction.getId`: Se invoca `getId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/dto/TransactionResponse.java` — `Transaction.getOperationNumber`: Se invoca `getOperationNumber` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/dto/TransactionResponse.java` — `Transaction.getChannel`: Se invoca `getChannel` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/dto/TransactionResponse.java` — `Transaction.getCreditOriginator`: Se invoca `getCreditOriginator` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/dto/TransactionResponse.java` — `Transaction.getAccountNumber`: Se invoca `getAccountNumber` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/dto/TransactionResponse.java` — `Transaction.getAmount`: Se invoca `getAmount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/dto/TransactionResponse.java` — `Transaction.getCurrency`: Se invoca `getCurrency` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/dto/TransactionResponse.java` — `Transaction.getCreatedAt`: Se invoca `getCreatedAt` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/dto/TransactionResponse.java` — `Transaction.getStatus`: Se invoca `getStatus` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/dto/TransactionResponse.java` — `Transaction.getAntifraudResponse`: Se invoca `getAntifraudResponse` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/dto/TransactionResponse.java` — `Transaction.getRiskBureauResponse`: Se invoca `getRiskBureauResponse` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/dto/TransactionResponse.java` — `Transaction.getPaymentGatewayResponse`: Se invoca `getPaymentGatewayResponse` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/exception/GlobalErrorHandler.java` — `ExternalServiceException.getBindingResult`: Se invoca `getBindingResult` sobre `ExternalServiceException`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/exception/GlobalErrorHandler.java` — `ExternalServiceException.getReason`: Se invoca `getReason` sobre `ExternalServiceException`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/exception/GlobalErrorHandler.java` — `ExternalServiceException.getMessage`: Se invoca `getMessage` sobre `ExternalServiceException`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/exception/GlobalErrorHandler.java` — `ExternalServiceException.getStatusCode`: Se invoca `getStatusCode` sobre `ExternalServiceException`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/TransactionServiceTest.java` — `Transaction.getIdempotencyKey`: Se invoca `getIdempotencyKey` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/TransactionServiceTest.java` — `Transaction.getOperationNumber`: Se invoca `getOperationNumber` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/TransactionServiceTest.java` — `Transaction.getChannel`: Se invoca `getChannel` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/TransactionServiceTest.java` — `TransactionService.findById`: Se invoca `findById` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/TransactionServiceTest.java` — `TransactionService.deleteTransaction`: Se invoca `deleteTransaction` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapterTest.java` — `Transaction.getId`: Se invoca `getId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapterTest.java` — `Transaction.getOperationNumber`: Se invoca `getOperationNumber` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapterTest.java` — `Transaction.getChannel`: Se invoca `getChannel` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapterTest.java` — `Transaction.getAmount`: Se invoca `getAmount` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapterTest.java` — `Transaction.getStatus`: Se invoca `getStatus` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapterTest.java` — `Transaction.getIdempotencyKey`: Se invoca `getIdempotencyKey` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Senior

### Brecha de conocimiento
Implementa un paradigma de programación distinto al imperativo, como el paradigma reactivo o el paradigma funcional. Domina los cuatro pilares especificados en el manifiesto de sistemas reactivos, favoreciendo mejor rendimiento, una mayor escalabilidad y una mayor resiliencia. Conoce las ventajas, desventajas y operadores básicos en la implementación de este paradigma.

### Misión / candidato
Candidato con experiencia como desarrollador Backend Senior en Java, con capacidad para asumir retos de arquitectura y paradigmas avanzados.

### Reto
- Tema: Adopción de Paradigmas de Programación No Imperativos: Con Enfoque Reactivo y Funcional
- Seniority: senior-l2
- Tipo: mixed
- Título: Integración de Paradigmas Reactivos y Funcionales en Sistema de Pagos
- Tiempo estimado: 40 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Exploración y Modelado Inicial — objetivo: Comprender y modelar el dominio de los sistemas de pago con enfoque en paradigmas no imperativos. — entregable (NO resolver): Modelo conceptual del sistema de pagos con identificación de actores, flujos y restricciones.
- Fase 2: Implementación de Procesamiento Reactivo — objetivo: Implementar el procesamiento de transacciones utilizando paradigmas reactivos. — entregable (NO resolver): Sistema de procesamiento de transacciones reactivo, capaz de manejar reintentos idempotentes y errores.
- Fase 3: Optimización y Refactorización Funcional — objetivo: Optimizar y refactorizar el sistema utilizando paradigmas funcionales. — entregable (NO resolver): Sistema refactorizado con paradigmas funcionales, optimizado para rendimiento y escalabilidad.
- Fase 4: Defensa de Decisiones Arquitectónicas — objetivo: Defender las decisiones arquitectónicas tomadas durante el desarrollo del sistema. — entregable (NO resolver): Documento de decisiones arquitectónicas con argumentos y trade-offs.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.pragma</groupId>
    <artifactId>payments</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>payments</name>
    <description>Sistema de pagos reactivo con arquitectura hexagonal</description>

    <properties>
        <java.version>21</java.version>
        <resilience4j.version>2.2.0</resilience4j.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webflux</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-r2dbc</artifactId>
        </dependency>
        <dependency>
            <groupId>io.r2dbc</groupId>
            <artifactId>r2dbc-postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot3</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-reactor</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.projectreactor</groupId>
            <artifactId>reactor-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>postgresql</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/pragma/payments/Application.java ===
package com.pragma.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.scheduler.Schedulers;
import java.time.Duration;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8080")
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024))
                .build();
    }

    @Bean
    public reactor.core.scheduler.Scheduler boundedElasticScheduler() {
        return Schedulers.newBoundedElastic(10, 100, "bounded-elastic");
    }

    @Bean
    public reactor.core.scheduler.Scheduler singleScheduler() {
        return Schedulers.newSingle("single-scheduler");
    }

    @Bean
    public reactor.core.scheduler.Scheduler parallelScheduler() {
        return Schedulers.newParallel("parallel-scheduler", 4);
    }

    @Bean
    public io.github.resilience4j.circuitbreaker.CircuitBreakerConfig customCircuitBreakerConfig() {
        return io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .permittedNumberOfCallsInHalfOpenState(3)
                .slidingWindowSize(10)
                .recordExceptions(
                    java.io.IOException.class,
                    org.springframework.web.client.HttpServerErrorException.class,
                    org.springframework.web.reactive.function.client.WebClientResponseException.class
                )
                .build();
    }

    @Bean
    public io.github.resilience4j.retry.RetryConfig customRetryConfig() {
        return io.github.resilience4j.retry.RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofMillis(500))
                .retryExceptions(
                    java.io.IOException.class,
                    org.springframework.web.client.HttpServerErrorException.class,
                    org.springframework.web.reactive.function.client.WebClientResponseException.class
                )
                .build();
    }

    @Bean
    public io.github.resilience4j.bulkhead.BulkheadConfig customBulkheadConfig() {
        return io.github.resilience4j.bulkhead.BulkheadConfig.custom()
                .maxConcurrentCalls(25)
                .maxWaitDuration(Duration.ofMillis(100))
                .build();
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
server:
  port: 8080
  netty:
    connection-timeout: 2s
    idle-timeout: 15s

spring:
  application:
    name: payments-service
  r2dbc:
    url: r2dbc:postgresql://localhost:5432/payments_db
    username: postgres
    password: postgres
    pool:
      enabled: true
      initial-size: 5
      max-size: 20
      max-idle-time: 30m
      validation-query: SELECT 1

management:
  endpoints:
    web:
      exposure:
        include: "*"
  endpoint:
    health:
      show-details: always
      probes:
        enabled: true
  health:
    resilience4jcircuitbreakers:
      enabled: true
    r2dbc:
      enabled: true

resilience4j:
  circuitbreaker:
    instances:
      antifraudService:
        baseConfig: customCircuitBreakerConfig
        registerHealthIndicator: true
      riskBureauService:
        baseConfig: customCircuitBreakerConfig
        registerHealthIndicator: true
  retry:
    instances:
      antifraudService:
        baseConfig: customRetryConfig
        registerHealthIndicator: true
      riskBureauService:
        baseConfig: customRetryConfig
        registerHealthIndicator: true
  bulkhead:
    instances:
      antifraudService:
        baseConfig: customBulkheadConfig
        registerHealthIndicator: true
      riskBureauService:
        baseConfig: customBulkheadConfig
        registerHealthIndicator: true
  timelimiter:
    instances:
      antifraudService:
        timeoutDuration: 2s
        cancelRunningFuture: true
      riskBureauService:
        timeoutDuration: 2s
        cancelRunningFuture: true

logging:
  level:
    root: INFO
    com.pragma.payments: DEBUG
    org.springframework.r2dbc: DEBUG
    io.r2dbc.postgresql.QUERY: DEBUG
    io.r2dbc.postgresql.PARAM: DEBUG
    io.github.resilience4j: INFO

// === ARCHIVO: src/main/java/com/pragma/payments/domain/model/Transaction.java ===
package com.pragma.payments.domain.model;

import lombok.Builder;
import lombok.Value;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class Transaction {
    UUID id;
    String operationNumber;
    String channel;
    String creditOriginator;
    String accountNumber;
    BigDecimal amount;
    String currency;
    LocalDateTime createdAt;
    TransactionStatus status;
    String antifraudResponse;
    String riskBureauResponse;
    String paymentGatewayResponse;
    String idempotencyKey;

    public enum TransactionStatus {
        PENDING,
        APPROVED,
        REJECTED,
        FRAUD_DETECTED,
        RISK_REJECTED,
        PAYMENT_FAILED,
        COMPLETED
    }

    public boolean isIdempotentWithinWindow(String operationNumber, String channel, LocalDateTime windowStart) {
        return this.operationNumber.equals(operationNumber) 
                && this.channel.equals(channel) 
                && this.createdAt.isAfter(windowStart);
    }

    public Transaction withAntifraudResponse(String response) {
        return this.toBuilder().antifraudResponse(response).build();
    }

    public Transaction withRiskBureauResponse(String response) {
        return this.toBuilder().riskBureauResponse(response).build();
    }

    public Transaction withPaymentGatewayResponse(String response) {
        return this.toBuilder().paymentGatewayResponse(response).build();
    }

    public Transaction withStatus(TransactionStatus newStatus) {
        return this.toBuilder().status(newStatus).build();
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/port/TransactionRepositoryPort.java ===
package com.pragma.payments.domain.port;

import com.pragma.payments.domain.model.Transaction;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
import java.util.UUID;

public interface TransactionRepositoryPort {
    Mono<Transaction> save(Transaction transaction);
    
    Mono<Transaction> findById(UUID id);
    
    Mono<Boolean> existsByIdempotencyKey(String idempotencyKey);
    
    Mono<Transaction> findByOperationNumberAndChannel(String operationNumber, String channel);
    
    Mono<Void> deleteById(UUID id);
    
    Mono<Long> countByCreatedAtAfter(LocalDateTime cutoff);
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/port/AntifraudServicePort.java ===
package com.pragma.payments.domain.port;

import com.pragma.payments.domain.model.Transaction;
import reactor.core.publisher.Mono;

public interface AntifraudServicePort {
    Mono<Transaction> evaluate(Transaction transaction);
    
    Mono<Boolean> isFraudulent(Transaction transaction);
    
    default Mono<Transaction> withFallbackEvaluation(Transaction transaction) {
        return evaluate(transaction)
                .onErrorResume(e -> Mono.just(transaction.withAntifraudResponse("Fallback: " + e.getMessage())));
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/port/RiskBureauPort.java ===
package com.pragma.payments.domain.port;

import com.pragma.payments.domain.model.Transaction;
import reactor.core.publisher.Mono;

/**
 * Puerto del dominio para integración con el buró de riesgos.
 * Define el contrato que la capa de aplicación utiliza para evaluar
 * el riesgo crediticio de una transacción antes de procesarla.
 * La implementación concreta se inyecta en tiempo de ejecución.
 */
public interface RiskBureauPort {

    /**
     * Evalúa el riesgo crediticio asociado a una transacción.
     * Retorna la transacción con la respuesta del buró de riesgos adjunta.
     * @param transaction la transacción a evaluar
     * @return Mono con la transacción actualizada incluyendo la respuesta del buró
     */
    Mono<Transaction> evaluateRisk(Transaction transaction);

    /**
     * Consulta el historial de riesgos para un originador de crédito.
     * @param creditOriginator identificador del originador de crédito
     * @return Mono con el nivel de riesgo calculado (0.0 a 1.0)
     */
    Mono<Double> getRiskScore(String creditOriginator);

    /**
     * Verifica si un originador de crédito estábloqueado por riesgo excesivo.
     * @param creditOriginator identificador del originador
     * @return Mono verdadero si el originador estábloqueado
     */
    Mono<Boolean> isBlocked(String creditOriginator);

    /**
     * Método de conveniencia que evalúa riesgo con fallback automático.
     * Si el buró de riesgos falla, retorna la transacción sin respuesta
     * pero permite continuar el flujo (soft failure).
     * @param transaction la transacción a evaluar
     * @return Mono con la transacción evaluada o la original en caso de error
     */
    default Mono<Transaction> withFallbackEvaluation(Transaction transaction) {
        return evaluateRisk(transaction)
            .onErrorResume(error -> {
                return Mono.just(transaction);
            });
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/application/TransactionService.java ===
package com.pragma.payments.application;

import com.pragma.payments.domain.model.Transaction;
import com.pragma.payments.domain.model.Transaction.TransactionStatus;
import com.pragma.payments.domain.port.AntifraudServicePort;
import com.pragma.payments.domain.port.RiskBureauPort;
import com.pragma.payments.domain.port.TransactionRepositoryPort;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.retry.Retry;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Servicio de aplicación que orquesta el procesamiento de transacciones.
 * Implementa el flujo reactivo de extremo a extremo integrando:
 * - Validación de idempotencia
 * - Evaluación antifraude
 * - Consulta de buró de riesgos
 * - Persistencia en base de datos reactiva
 * Utiliza operadores funcionales de Project Reactor para composición.
 */
@Service
@RequiredArgsConstructor
public class TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);
    private static final Duration IDEMPOTENCY_WINDOW = Duration.ofHours(24);

    private final TransactionRepositoryPort transactionRepository;
    private final AntifraudServicePort antifraudService;
    private final RiskBureauPort riskBureauPort;
    private final Scheduler boundedElasticScheduler;
    private final Scheduler parallelScheduler;
    private final CircuitBreaker antifraudCircuitBreaker;
    private final CircuitBreaker riskBureauCircuitBreaker;
    private final Retry transactionRetry;

    /**
     * Procesa una nueva transacción orchestrado el flujo completo.
     * El flujo es reactivo y tolerante a fallos con circuit breakers.
     * @param transaction la transacción a procesar
     * @return Mono con la transacción procesada
     */
    public Mono<Transaction> processTransaction(Transaction transaction) {
        log.info("Iniciando procesamiento de transacción: operationNumber={}, channel={}",
                transaction.getOperationNumber(), transaction.getChannel());

        return validateIdempotency(transaction)
            .flatMap(this::evaluateAntifraud)
            .flatMap(this::evaluateRiskBureau)
            .flatMap(this::persistTransaction)
            .doOnSuccess(result -> log.info("Transacción procesada exitosamente: id={}, status={}",
                    result.getId(), result.getStatus()))
            .doOnError(error -> log.error("Error en procesamiento de transacción: {}", error.getMessage()));
    }

    /**
     * Valida que la transacción sea idempotente dentro de la ventana de 24 horas.
     * Si ya existe una transacción con el mismo número de operación y canal,
     * retorna la transacción existente.
     */
    private Mono<Transaction> validateIdempotency(Transaction transaction) {
        if (transaction.getIdempotencyKey() == null || transaction.getIdempotencyKey().isBlank()) {
            return Mono.just(transaction);
        }

        return transactionRepository.existsByIdempotencyKey(transaction.getIdempotencyKey())
            .flatMap(exists -> {
                if (exists) {
                    log.info("Transacción idempotente detectada, recuperando existente: idempotencyKey={}",
                            transaction.getIdempotencyKey());
                    return transactionRepository
                        .findByOperationNumberAndChannel(
                            transaction.getOperationNumber(), 
                            transaction.getChannel())
                        .switchIfEmpty(Mono.just(transaction));
                }
                return Mono.just(transaction);
            });
    }

    /**
     * Evalúa la transacción mediante el servicio antifraude.
     * Utiliza circuit breaker para tolerar fallos del servicio externo.
     */
    private Mono<Transaction> evaluateAntifraud(Transaction transaction) {
        return Mono.fromCallable(() -> transaction)
            .subscribeOn(boundedElasticScheduler)
            .flatMap(tx -> Mono.defer(() -> antifraudService.evaluate(tx))
                .transformDeferred(mono -> decorateWithCircuitBreaker(mono, antifraudCircuitBreaker, "antifraud"))
                .retryWhen(transactionRetry))
            .doOnNext(result -> log.debug("Evaluación antifraude completada para transacción: {}", result.getId()))
            .onErrorResume(error -> {
                log.warn("Error en evaluación antifraude, continuando con respuesta vacía: {}", error.getMessage());
                return Mono.just(transaction.withAntifraudResponse("ERROR:" + error.getMessage()));
            });
    }

    /**
     * Evalúa el riesgo crediticio mediante el buró de riesgos.
     * Consulta el score de riesgo antes de procesar la transacción.
     */
    private Mono<Transaction> evaluateRiskBureau(Transaction transaction) {
        return riskBureauPort.getRiskScore(transaction.getCreditOriginator())
            .subscribeOn(parallelScheduler)
            .flatMap(riskScore -> {
                log.debug("Risk score para {}: {}", transaction.getCreditOriginator(), riskScore);
                if (riskScore > 0.7) {
                    log.warn("Riesgo excesivo detectado para originador: {}, score={}",
                            transaction.getCreditOriginator(), riskScore);
                    return Mono.just(transaction
                        .withRiskBureauResponse("HIGH_RISK:" + riskScore)
                        .withStatus(TransactionStatus.REJECTED));
                }
                return riskBureauPort.evaluateRisk(transaction)
                    .transformDeferred(mono -> decorateWithCircuitBreaker(mono, riskBureauCircuitBreaker, "riskBureau"))
                    .retryWhen(transactionRetry)
                    .onErrorResume(error -> {
                        log.warn("Error en buró de riesgos, continuando: {}", error.getMessage());
                        return Mono.just(transaction.withRiskBureauResponse("ERROR:" + error.getMessage()));
                    });
            })
            .switchIfEmpty(Mono.defer(() -> {
                log.warn("No se obtuvo risk score, continuando sin evaluación de riesgo");
                return Mono.just(transaction);
            }));
    }

    /**
     * Persiste la transacción en la base de datos.
     * El flujo de persistencia es reactivo y no bloqueante.
     */
    private Mono<Transaction> persistTransaction(Transaction transaction) {
        Transaction transactionToSave = transaction.getId() == null
            ? createNewTransaction(transaction)
            : transaction;

        return transactionRepository.save(transactionToSave)
            .subscribeOn(boundedElasticScheduler)
            .doOnNext(saved -> log.info("Transacción persistida: id={}, status={}", saved.getId(), saved.getStatus()));
    }

    /**
     * Crea una nueva transacción con ID generado y timestamps.
     */
    private Transaction createNewTransaction(Transaction base) {
        Transaction newTransaction = new Transaction();
        newTransaction.setId(UUID.randomUUID());
        newTransaction.setOperationNumber(base.getOperationNumber());
        newTransaction.setChannel(base.getChannel());
        newTransaction.setCreditOriginator(base.getCreditOriginator());
        newTransaction.setAccountNumber(base.getAccountNumber());
        newTransaction.setAmount(base.getAmount());
        newTransaction.setCurrency(base.getCurrency() != null ? base.getCurrency() : "USD");
        newTransaction.setCreatedAt(LocalDateTime.now());
        newTransaction.setStatus(TransactionStatus.PENDING);
        newTransaction.setAntifraudResponse(base.getAntifraudResponse());
        newTransaction.setRiskBureauResponse(base.getRiskBureauResponse());
        newTransaction.setPaymentGatewayResponse(base.getPaymentGatewayResponse());
        newTransaction.setIdempotencyKey(base.getIdempotencyKey());
        return newTransaction;
    }

    /**
     * Decora un Mono con circuit breaker de Resilience4j.
     */
    private <T> reactor.core.publisher.Mono<T> decorateWithCircuitBreaker(
            reactor.core.publisher.Mono<T> mono, 
            CircuitBreaker circuitBreaker,
            String serviceName) {
        return io.github.resilience4j.reactor.MonoCircuitBreaker.of(mono, circuitBreaker);
    }

    /**
     * Recupera una transacción por su ID.
     * @param id identificador de la transacción
     * @return Mono con la transacción encontrada o vacío
     */
    public Mono<Transaction> getTransactionById(UUID id) {
        return transactionRepository.findById(id)
            .subscribeOn(boundedElasticScheduler);
    }

    /**
     * Obtiene estadísticas de transacciones en un período.
     * @param cutoff fecha de corte para la consulta
     * @return Mono con el conteo de transacciones
     */
    public Mono<Long> getTransactionCountSince(LocalDateTime cutoff) {
        return transactionRepository.countByCreatedAtAfter(cutoff)
            .subscribeOn(parallelScheduler);
    }

    /**
     * Procesa múltiples transacciones en paralelo utilizando Flux.
     * @param transactions flujo de transacciones a procesar
     * @return Flux con los resultados del procesamiento
     */
    public reactor.core.publisher.Flux<Transaction> processBatch(
            reactor.core.publisher.Flux<Transaction> transactions) {
        return transactions
            .parallel()
            .runOn(parallelScheduler)
            .flatMap(this::processTransaction)
            .sequential();
    }

    /**
     * Cancela una transacción existente cambiando su estado.
     * @param id identificador de la transacción
     * @return Mono con la transacción actualizada
     */
    public Mono<Transaction> cancelTransaction(UUID id) {
        return getTransactionById(id)
            .flatMap(transaction -> {
                Transaction cancelled = transaction.withStatus(TransactionStatus.CANCELLED);
                return transactionRepository.save(cancelled);
            });
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapter.java ===
package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.model.Transaction;
import com.pragma.payments.domain.model.Transaction.TransactionStatus;
import com.pragma.payments.domain.port.TransactionRepositoryPort;
import io.r2dbc.postgresql.codec.Json;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import io.r2dbc.spi.Statement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Adaptador de infraestructura que implementa el puerto de persistencia
 * utilizando R2DBC (Reactive Relational Database Connectivity).
 * Proporciona operaciones reactivas y no bloqueantes sobre PostgreSQL.
 */
@Repository
@RequiredArgsConstructor
@Slf4j
public class TransactionR2dbcAdapter implements TransactionRepositoryPort {

    private final DatabaseClient databaseClient;

    private static final String INSERT_SQL = """
        INSERT INTO transactions 
            (id, operation_number, channel, credit_originator, account_number, 
             amount, currency, created_at, status, antifraud_response, 
             risk_bureau_response, payment_gateway_response, idempotency_key)
        VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9, $10, $11, $12, $13)
        """;

    private static final String SELECT_BY_ID_SQL = """
        SELECT id, operation_number, channel, credit_originator, account_number,
               amount, currency, created_at, status, antifraud_response,
               risk_bureau_response, payment_gateway_response, idempotency_key
        FROM transactions WHERE id = $1
        """;

    private static final String SELECT_BY_IDEMPOTENCY_KEY_SQL = """
        SELECT COUNT(*) FROM transactions WHERE idempotency_key = $1
        """;

    private static final String SELECT_BY_OPERATION_CHANNEL_SQL = """
        SELECT id, operation_number, channel, credit_originator, account_number,
               amount, currency, created_at, status, antifraud_response,
               risk_bureau_response, payment_gateway_response, idempotency_key
        FROM transactions 
        WHERE operation_number = $1 AND channel = $2
        ORDER BY created_at DESC LIMIT 1
        """;

    private static final String DELETE_SQL = "DELETE FROM transactions WHERE id = $1";

    private static final String COUNT_SINCE_SQL = 
        "SELECT COUNT(*) FROM transactions WHERE created_at > $1";

    @Override
    public Mono<Transaction> save(Transaction transaction) {
        log.debug("Persistiendo transacción: id={}, operationNumber={}",
                transaction.getId(), transaction.getOperationNumber());

        return databaseClient.sql(INSERT_SQL)
            .bind("$1", transaction.getId())
            .bind("$2", transaction.getOperationNumber())
            .bind("$3", transaction.getChannel())
            .bind("$4", transaction.getCreditOriginator())
            .bind("$5", transaction.getAccountNumber())
            .bind("$6", transaction.getAmount())
            .bind("$7", transaction.getCurrency())
            .bind("$8", transaction.getCreatedAt())
            .bind("$9", transaction.getStatus().name())
            .bind("$10", transaction.getAntifraudResponse() != null 
                ? Json.of(transaction.getAntifraudResponse()) 
                : Json.of(""))
            .bind("$11", transaction.getRiskBureauResponse() != null 
                ? Json.of(transaction.getRiskBureauResponse()) 
                : Json.of(""))
            .bind("$12", transaction.getPaymentGatewayResponse() != null 
                ? Json.of(transaction.getPaymentGatewayResponse()) 
                : Json.of(""))
            .bind("$13", transaction.getIdempotencyKey() != null 
                ? transaction.getIdempotencyKey() 
                : "")
            .fetch()
            .rowsUpdated()
            .flatMap(rows -> {
                log.info("Transacción guardada exitosamente: rows={}", rows);
                return Mono.just(transaction);
            })
            .onErrorResume(error -> {
                log.error("Error al persistir transacción: {}", error.getMessage());
                return Mono.error(error);
            });
    }

    @Override
    public Mono<Transaction> findById(UUID id) {
        log.debug("Buscando transacción por id: {}", id);

        return databaseClient.sql(SELECT_BY_ID_SQL)
            .bind("$1", id)
            .map(this::mapRowToTransaction)
            .first()
            .switchIfEmpty(Mono.defer(() -> {
                log.debug("No se encontró transacción con id: {}", id);
                return Mono.empty();
            }));
    }

    @Override
    public Mono<Boolean> existsByIdempotencyKey(String idempotencyKey) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            return Mono.just(false);
        }

        log.debug("Verificando idempotency key: {}", idempotencyKey);

        return databaseClient.sql(SELECT_BY_IDEMPOTENCY_KEY_SQL)
            .bind("$1", idempotencyKey)
            .map((row, metadata) -> row.get(0, Long.class))
            .first()
            .defaultIfEmpty(0L)
            .map(count -> count > 0);
    }

    @Override
    public Mono<Transaction> findByOperationNumberAndChannel(String operationNumber, String channel) {
        log.debug("Buscando transacción por operationNumber={}, channel={}", operationNumber, channel);

        return databaseClient.sql(SELECT_BY_OPERATION_CHANNEL_SQL)
            .bind("$1", operationNumber)
            .bind("$2", channel)
            .map(this::mapRowToTransaction)
            .first()
            .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Void> deleteById(UUID id) {
        log.debug("Eliminando transacción: {}", id);

        return databaseClient.sql(DELETE_SQL)
            .bind("$1", id)
            .fetch()
            .rowsUpdated()
            .flatMap(rows -> {
                log.info("Transacción eliminada: id={}, rows={}", id, rows);
                return Mono.empty();
            });
    }

    @Override
    public Mono<Long> countByCreatedAtAfter(LocalDateTime cutoff) {
        log.debug("Contando transacciones desde: {}", cutoff);

        return databaseClient.sql(COUNT_SINCE_SQL)
            .bind("$1", cutoff)
            .map((row, metadata) -> row.get(0, Long.class))
            .first()
            .defaultIfEmpty(0L);
    }

    /**
     * Mapea una fila de resultado a una entidad Transaction.
     * Maneja la conversión de tipos de PostgreSQL a tipos Java.
     */
    private Transaction mapRowToTransaction(Row row, RowMetadata metadata) {
        Transaction transaction = new Transaction();
        
        transaction.setId(row.get("id", UUID.class));
        transaction.setOperationNumber(row.get("operation_number", String.class));
        transaction.setChannel(row.get("channel", String.class));
        transaction.setCreditOriginator(row.get("credit_originator", String.class));
        transaction.setAccountNumber(row.get("account_number", String.class));
        transaction.setAmount(row.get("amount", BigDecimal.class));
        transaction.setCurrency(row.get("currency", String.class));
        transaction.setCreatedAt(row.get("created_at", LocalDateTime.class));
        
        String statusStr = row.get("status", String.class);
        if (statusStr != null) {
            transaction.setStatus(TransactionStatus.valueOf(statusStr));
        }
        
        Object antifraudObj = row.get("antifraud_response");
        if (antifraudObj != null) {
            transaction.setAntifraudResponse(extractJsonValue(antifraudObj));
        }
        
        Object riskBureauObj = row.get("risk_bureau_response");
        if (riskBureauObj != null) {
            transaction.setRiskBureauResponse(extractJsonValue(riskBureauObj));
        }
        
        Object paymentGatewayObj = row.get("payment_gateway_response");
        if (paymentGatewayObj != null) {
            transaction.setPaymentGatewayResponse(extractJsonValue(paymentGatewayObj));
        }
        
        transaction.setIdempotencyKey(row.get("idempotency_key", String.class));
        
        return transaction;
    }

    /**
     * Extrae el valor de un campo JSON almacenado en PostgreSQL.
     * R2DBC PostgreSQL devuelve objetos Json que deben ser procesados.
     */
    private String extractJsonValue(Object jsonObject) {
        if (jsonObject instanceof Json) {
            return ((Json) jsonObject).asString();
        }
        if (jsonObject instanceof String) {
            return (String) jsonObject;
        }
        return jsonObject != null ? jsonObject.toString() : null;
    }

    /**
     * Ejecuta una consulta SQL cruda para casos de uso avanzados.
     * Útil para consultas complejas que no encajan en los métodos estándar.
     */
    public Mono<Long> executeCountQuery(String sql) {
        return databaseClient.sql(sql)
            .map((row, metadata) -> row.get(0, Long.class))
            .first()
            .defaultIfEmpty(0L);
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapter/AntifraudWebClientAdapter.java ===
package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.model.Transaction;
import com.pragma.payments.domain.port.AntifraudServicePort;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.retry.operator.RetryOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
@Slf4j
public class AntifraudWebClientAdapter implements AntifraudServicePort {

    private final WebClient webClient;
    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private final RetryRegistry retryRegistry;

    @Value("${external.services.antifraud.base-url:http://antifraud-service:8081}")
    private String antifraudBaseUrl;

    @Value("${external.services.antifraud.timeout-ms:5000}")
    private int timeoutMs;

    private static final String CIRCUIT_BREAKER_NAME = "antifraudService";
    private static final String RETRY_NAME = "antifraudServiceRetry";

    @Override
    public Mono<Transaction> evaluate(Transaction transaction) {
        log.debug("Evaluando transacción {} en servicio antifraude", transaction.getId());

        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker(CIRCUIT_BREAKER_NAME);
        Retry retry = retryRegistry.retry(RETRY_NAME);

        return webClient
                .post()
                .uri(antifraudBaseUrl + "/api/v1/evaluate")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(buildAntifraudRequest(transaction))
                .retrieve()
                .bodyToMono(AntifraudResponse.class)
                .timeout(Duration.ofMillis(timeoutMs), Mono.error(
                        new AntifraudTimeoutException("Timeout esperando respuesta del servicio antifraude")))
                .subscribeOn(Schedulers.boundedElastic())
                .transformDeferred(mono -> CircuitBreakerOperator.of(circuitBreaker).apply(mono))
                .transformDeferred(mono -> RetryOperator.of(retry).apply(mono))
                .onErrorResume(WebClientResponseException.ServiceUnavailable.class, e -> {
                    log.warn("Servicio antifraude no disponible: {}", e.getMessage());
                    return Mono.just(buildFallbackResponse(transaction, "SERVICE_UNAVAILABLE"));
                })
                .onErrorResume(WebClientResponseException.NotFound.class, e -> {
                    log.warn("Recurso antifraude no encontrado: {}", e.getMessage());
                    return Mono.just(buildFallbackResponse(transaction, "NOT_FOUND"));
                })
                .onErrorResume(Predicate.not(e -> e instanceof AntifraudResponseException.class), e -> {
                    log.error("Error inesperado en servicio antifraude: {}", e.getMessage());
                    return Mono.just(buildFallbackResponse(transaction, "ERROR"));
                })
                .map(response -> transaction.withAntifraudResponse(response.toJson()));
    }

    @Override
    public Mono<Boolean> isFraudulent(Transaction transaction) {
        log.debug("Verificando si transacción {} es fraudulenta", transaction.getId());

        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker(CIRCUIT_BREAKER_NAME);
        Retry retry = retryRegistry.retry(RETRY_NAME);

        return webClient
                .post()
                .uri(antifraudBaseUrl + "/api/v1/check-fraud")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(buildAntifraudRequest(transaction))
                .retrieve()
                .bodyToMono(FraudCheckResponse.class)
                .timeout(Duration.ofMillis(timeoutMs), Mono.error(
                        new AntifraudTimeoutException("Timeout en verificación de fraude")))
                .subscribeOn(Schedulers.boundedElastic())
                .transformDeferred(mono -> CircuitBreakerOperator.of(circuitBreaker).apply(mono))
                .transformDeferred(mono -> RetryOperator.of(retry).apply(mono))
                .onErrorResume(e -> {
                    log.error("Error en verificación de fraude, asumiendo no fraudulento: {}", e.getMessage());
                    return Mono.just(false);
                })
                .map(FraudCheckResponse::isFraudulent)
                .defaultIfEmpty(false);
    }

    @Override
    public Mono<Transaction> withFallbackEvaluation(Transaction transaction) {
        return evaluate(transaction)
                .onErrorResume(e -> {
                    log.warn("Evaluación con fallback para transacción {}: {}", transaction.getId(), e.getMessage());
                    return Mono.just(transaction.withAntifraudResponse("{\"status\":\"FALLBACK\"}"));
                });
    }

    private AntifraudRequest buildAntifraudRequest(Transaction transaction) {
        return new AntifraudRequest(
                transaction.getId().toString(),
                transaction.getOperationNumber(),
                transaction.getChannel(),
                transaction.getCreditOriginator(),
                transaction.getAccountNumber(),
                transaction.getAmount(),
                transaction.getCurrency()
        );
    }

    private AntifraudResponse buildFallbackResponse(Transaction transaction, String status) {
        return new AntifraudResponse(transaction.getId().toString(), status, "PENDING_REVIEW", 0.5);
    }

    private record AntifraudRequest(
            String transactionId,
            String operationNumber,
            String channel,
            String creditOriginator,
            String accountNumber,
            java.math.BigDecimal amount,
            String currency
    ) {}

    private record AntifraudResponse(
            String transactionId,
            String status,
            String riskLevel,
            double riskScore
    ) {
        String toJson() {
            return String.format("{\"transactionId\":\"%s\",\"status\":\"%s\",\"riskLevel\":\"%s\",\"riskScore\":%.2f}",
                    transactionId, status, riskLevel, riskScore);
        }
    }

    private record FraudCheckResponse(String transactionId, boolean fraudulent, String reason) {}

    private static class AntifraudResponseException extends RuntimeException {
        public AntifraudResponseException(String message) {
            super(message);
        }
    }

    private static class AntifraudTimeoutException extends RuntimeException {
        public AntifraudTimeoutException(String message) {
            super(message);
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapter/RiskBureauWebClientAdapter.java ===
package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.model.Transaction;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.retry.operator.RetryOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
@Slf4j
public class RiskBureauWebClientAdapter {

    private final WebClient webClient;
    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private final RetryRegistry retryRegistry;

    @Value("${external.services.risk-bureau.base-url:http://risk-bureau-service:8082}")
    private String riskBureauBaseUrl;

    @Value("${external.services.risk-bureau.timeout-ms:8000}")
    private int timeoutMs;

    private static final String CIRCUIT_BREAKER_NAME = "riskBureauService";
    private static final String RETRY_NAME = "riskBureauServiceRetry";

    public Mono<Transaction> evaluateRisk(Transaction transaction) {
        log.debug("Evaluando riesgo para transacción {}", transaction.getId());

        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker(CIRCUIT_BREAKER_NAME);
        Retry retry = retryRegistry.retry(RETRY_NAME);

        return webClient
                .post()
                .uri(riskBureauBaseUrl + "/api/v1/evaluate-risk")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(buildRiskEvaluationRequest(transaction))
                .retrieve()
                .bodyToMono(RiskEvaluationResponse.class)
                .timeout(Duration.ofMillis(timeoutMs), Mono.error(
                        new RiskBureauTimeoutException("Timeout evaluando riesgo")))
                .subscribeOn(Schedulers.boundedElastic())
                .transformDeferred(mono -> CircuitBreakerOperator.of(circuitBreaker).apply(mono))
                .transformDeferred(mono -> RetryOperator.of(retry).apply(mono))
                .onErrorResume(WebClientResponseException.ServiceUnavailable.class, e -> {
                    log.warn("Buró de riesgos no disponible: {}", e.getMessage());
                    return Mono.just(buildFallbackRiskResponse(transaction, "BUREAU_UNAVAILABLE"));
                })
                .onErrorResume(WebClientResponseException.BadRequest.class, e -> {
                    log.warn("Solicitud inválida al buró de riesgos: {}", e.getMessage());
                    return Mono.just(buildFallbackRiskResponse(transaction, "INVALID_REQUEST"));
                })
                .onErrorResume(Predicate.not(e -> e instanceof RiskBureauResponseException.class), e -> {
                    log.error("Error inesperado en buró de riesgos: {}", e.getMessage());
                    return Mono.just(buildFallbackRiskResponse(transaction, "ERROR"));
                })
                .map(response -> {
                    String riskJson = response.toJson();
                    return transaction.withRiskBureauResponse(riskJson);
                });
    }

    public Mono<RiskProfile> getRiskProfile(String creditOriginator, String accountNumber) {
        log.debug("Obteniendo perfil de riesgo para originador: {}, cuenta: {}", creditOriginator, accountNumber);

        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker(CIRCUIT_BREAKER_NAME);
        Retry retry = retryRegistry.retry(RETRY_NAME);

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("api", "v1", "risk-profile")
                        .queryParam("originator", creditOriginator)
                        .queryParam("account", accountNumber)
                        .build())
                .retrieve()
                .bodyToMono(RiskProfileResponse.class)
                .timeout(Duration.ofMillis(timeoutMs), Mono.error(
                        new RiskBureauTimeoutException("Timeout obteniendo perfil de riesgo")))
                .subscribeOn(Schedulers.boundedElastic())
                .transformDeferred(mono -> CircuitBreakerOperator.of(circuitBreaker).apply(mono))
                .transformDeferred(mono -> RetryOperator.of(retry).apply(mono))
                .onErrorResume(e -> {
                    log.error("Error obteniendo perfil de riesgo, retornando perfil por defecto: {}", e.getMessage());
                    return Mono.just(RiskProfile.defaultProfile());
                })
                .map(RiskProfileResponse::toRiskProfile)
                .defaultIfEmpty(RiskProfile.defaultProfile());
    }

    public Mono<Boolean> isHighRisk(String creditOriginator) {
        log.debug("Verificando si originador {} es de alto riesgo", creditOriginator);

        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker(CIRCUIT_BREAKER_NAME);

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("api", "v1", "high-risk")
                        .queryParam("originator", creditOriginator)
                        .build())
                .retrieve()
                .bodyToMono(HighRiskResponse.class)
                .timeout(Duration.ofMillis(timeoutMs), Mono.error(
                        new RiskBureauTimeoutException("Timeout verificando alto riesgo")))
                .subscribeOn(Schedulers.boundedElastic())
                .transformDeferred(mono -> CircuitBreakerOperator.of(circuitBreaker).apply(mono))
                .onErrorResume(e -> {
                    log.warn("Error verificando alto riesgo, asumiendo bajo riesgo: {}", e.getMessage());
                    return Mono.just(false);
                })
                .map(HighRiskResponse::isHighRisk)
                .defaultIfEmpty(false);
    }

    private RiskEvaluationRequest buildRiskEvaluationRequest(Transaction transaction) {
        return new RiskEvaluationRequest(
                transaction.getId().toString(),
                transaction.getCreditOriginator(),
                transaction.getAccountNumber(),
                transaction.getAmount(),
                transaction.getCurrency()
        );
    }

    private RiskEvaluationResponse buildFallbackRiskResponse(Transaction transaction, String status) {
        return new RiskEvaluationResponse(
                transaction.getId().toString(),
                status,
                "MEDIUM",
                0.5,
                "PENDING_MANUAL_REVIEW"
        );
    }

    private record RiskEvaluationRequest(
            String transactionId,
            String creditOriginator,
            String accountNumber,
            java.math.BigDecimal amount,
            String currency
    ) {}

    private record RiskEvaluationResponse(
            String transactionId,
            String status,
            String riskCategory,
            double riskScore,
            String recommendation
    ) {
        String toJson() {
            return String.format("{\"transactionId\":\"%s\",\"status\":\"%s\",\"riskCategory\":\"%s\",\"riskScore\":%.2f,\"recommendation\":\"%s\"}",
                    transactionId, status, riskCategory, riskScore, recommendation);
        }
    }

    private record RiskProfileResponse(
            String originator,
            String accountNumber,
            String riskCategory,
            double averageRiskScore,
            int totalTransactions,
            int flaggedTransactions
    ) {
        RiskProfile toRiskProfile() {
            return new RiskProfile(originator, accountNumber, riskCategory, averageRiskScore, totalTransactions, flaggedTransactions);
        }
    }

    private record HighRiskResponse(String originator, boolean highRisk, String reason) {}

    public record RiskProfile(
            String originator,
            String accountNumber,
            String riskCategory,
            double averageRiskScore,
            int totalTransactions,
            int flaggedTransactions
    ) {
        public static RiskProfile defaultProfile() {
            return new RiskProfile("UNKNOWN", "UNKNOWN", "MEDIUM", 0.5, 0, 0);
        }

        public boolean isHighRisk() {
            return "HIGH".equalsIgnoreCase(riskCategory) || averageRiskScore > 0.7;
        }
    }

    private static class RiskBureauResponseException extends RuntimeException {
        public RiskBureauResponseException(String message) {
            super(message);
        }
    }

    private static class RiskBureauTimeoutException extends RuntimeException {
        public RiskBureauTimeoutException(String message) {
            super(message);
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/config/WebClientConfig.java ===
package com.pragma.payments.infrastructure.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class WebClientConfig {

    @Value("${webclient.connection.max-connections:200}")
    private int maxConnections;

    @Value("${webclient.connection.pending-acquire-timeout-ms:60000}")
    private int pendingAcquireTimeoutMs;

    @Value("${webclient.connection.max-idle-time-ms:20000}")
    private int maxIdleTimeMs;

    @Value("${webclient.connection.max-life-time-ms:60000}")
    private int maxLifeTimeMs;

    @Value("${webclient.connection.pending-acquire-max-count:200}")
    private int pendingAcquireMaxCount;

    @Value("${webclient.connection.evict-in-background-period-ms:30000}")
    private int evictInBackgroundPeriodMs;

    @Value("${webclient.response.max-in-memory-size-bytes:16777216}")
    private int maxInMemorySizeBytes;

    @Value("${webclient.connect.timeout-ms:10000}")
    private int connectTimeoutMs;

    @Value("${webclient.response.timeout-ms:30000}")
    private int responseTimeoutMs;

    @Bean
    public ConnectionProvider connectionProvider() {
        log.info("Configurando ConnectionProvider con maxConnections={}, pendingAcquireTimeout={}ms",
                maxConnections, pendingAcquireTimeoutMs);

        return ConnectionProvider.builder("paymentsConnectionPool")
                .maxConnections(maxConnections)
                .pendingAcquireTimeout(Duration.ofMillis(pendingAcquireTimeoutMs))
                .maxIdleTime(Duration.ofMillis(maxIdleTimeMs))
                .maxLifeTime(Duration.ofMillis(maxLifeTimeMs))
                .pendingAcquireMaxCount(pendingAcquireMaxCount)
                .evictInBackground(Duration.ofMillis(evictInBackgroundPeriodMs))
                .metricsEnabled(true)
                .build();
    }

    @Bean
    public HttpClient httpClient(ConnectionProvider connectionProvider) {
        log.info("Configurando HttpClient con connectTimeout={}ms, responseTimeout={}ms",
                connectTimeoutMs, responseTimeoutMs);

        return HttpClient.create(connectionProvider)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeoutMs)
                .responseTimeout(Duration.ofMillis(responseTimeoutMs))
                .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(responseTimeoutMs, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(responseTimeoutMs, TimeUnit.MILLISECONDS)));
    }

    @Bean
    public WebClient webClient(HttpClient httpClient) {
        log.info("Configurando WebClient con maxInMemorySize={} bytes", maxInMemorySizeBytes);

        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(maxInMemorySizeBytes))
                .build();

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .exchangeStrategies(exchangeStrategies)
                .defaultHeader("Accept", "application/json")
                .defaultHeader("Content-Type", "application/json")
                .filter((request, next) -> {
                    log.debug("Enviando solicitud a: {} {}", request.method(), request.url());
                    return next.exchange(request)
                            .doOnNext(response -> {
                                log.debug("Respuesta recibida: {} {}", response.statusCode().value(), response.statusCode().reasonPhrase());
                            })
                            .doOnError(error -> {
                                log.error("Error en solicitud HTTP: {}", error.getMessage());
                            });
                })
                .build();
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/config/Resilience4jConfig.java ===
package com.pragma.payments.infrastructure.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.bulkhead.BulkheadConfig;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Resilience4jConfig {

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .slowCallRateThreshold(100)
                .slowCallDurationThreshold(Duration.ofSeconds(2))
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .permittedNumberOfCallsInHalfOpenState(3)
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .minimumNumberOfCalls(10)
                .build();
        return CircuitBreakerRegistry.of(config);
    }

    @Bean
    public RetryRegistry retryRegistry() {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofMillis(500))
                .retryExceptions(Exception.class)
                .build();
        return RetryRegistry.of(config);
    }

    @Bean
    public BulkheadRegistry bulkheadRegistry() {
        BulkheadConfig config = BulkheadConfig.custom()
                .maxConcurrentCalls(100)
                .maxDuration(Duration.ofSeconds(5))
                .build();
        return BulkheadRegistry.of(config);
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/controller/TransactionController.java ===
package com.pragma.payments.infrastructure.controller;

import com.pragma.payments.application.TransactionService;
import com.pragma.payments.infrastructure.controller.dto.TransactionRequest;
import com.pragma.payments.infrastructure.controller.dto.TransactionResponse;
import com.pragma.payments.infrastructure.controller.dto.ErrorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public Mono<ResponseEntity<TransactionResponse>> createTransaction(
            @Valid @RequestBody TransactionRequest request,
            @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey) {
        
        return transactionService.processTransaction(request.toDomain(), idempotencyKey)
                .map(transaction -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(TransactionResponse.fromDomain(transaction)))
                .onErrorResume(DuplicateTransactionException.class, e ->
                        Mono.just(ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .body(new ErrorResponse(
                                        "DUPLICATE_TRANSACTION",
                                        e.getMessage(),
                                        LocalDateTime.now())))
                                .map(r -> (ResponseEntity<TransactionResponse>) ResponseEntity.status(409).build()))
                .onErrorResume(ExternalServiceException.class, e ->
                        Mono.just(ResponseEntity
                                .status(HttpStatus.SERVICE_UNAVAILABLE)
                                .body(new ErrorResponse(
                                        "EXTERNAL_SERVICE_ERROR",
                                        e.getMessage(),
                                        LocalDateTime.now())))
                                .map(r -> (ResponseEntity<TransactionResponse>) ResponseEntity.status(503).build()));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<TransactionResponse>> getTransaction(@PathVariable UUID id) {
        return transactionService.findById(id)
                .map(transaction -> ResponseEntity.ok(TransactionResponse.fromDomain(transaction)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Flux<TransactionResponse> getAllTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return transactionService.findAll(page, size)
                .map(TransactionResponse::fromDomain);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteTransaction(@PathVariable UUID id) {
        return transactionService.deleteById(id)
                .then(Mono.just(ResponseEntity.noContent().build()))
                .onErrorResume(e -> Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping("/health")
    public Mono<ResponseEntity<String>> healthCheck() {
        return Mono.just(ResponseEntity.ok("UP"));
    }

    static class DuplicateTransactionException extends RuntimeException {
        public DuplicateTransactionException(String message) {
            super(message);
        }
    }

    static class ExternalServiceException extends RuntimeException {
        public ExternalServiceException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/controller/dto/TransactionRequest.java ===
package com.pragma.payments.infrastructure.controller.dto;



import com.pragma.payments.domain.model.TransactionStatus;
import com.pragma.payments.domain.model.Transaction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionRequest(
        @NotBlank(message = "Operation number is required")
        String operationNumber,
        
        @NotBlank(message = "Channel is required")
        @Size(max = 50)
        String channel,
        
        @NotBlank(message = "Credit originator is required")
        String creditOriginator,
        
        @NotBlank(message = "Account number is required")
        String accountNumber,
        
        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be positive")
        BigDecimal amount,
        
        @NotBlank(message = "Currency is required")
        @Size(min = 3, max = 3)
        String currency
) {
    public com.pragma.payments.domain.model.Transaction toDomain() {
        return com.pragma.payments.domain.model.Transaction.builder()
                .id(UUID.randomUUID())
                .operationNumber(operationNumber)
                .channel(channel)
                .creditOriginator(creditOriginator)
                .accountNumber(accountNumber)
                .amount(amount)
                .currency(currency)
                .createdAt(LocalDateTime.now())
                .status(com.pragma.payments.domain.model.Transaction.TransactionStatus.PENDING)
                .build();
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/controller/dto/TransactionResponse.java ===
package com.pragma.payments.infrastructure.controller.dto;

import com.pragma.payments.domain.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionResponse(
        UUID id,
        String operationNumber,
        String channel,
        String creditOriginator,
        String accountNumber,
        BigDecimal amount,
        String currency,
        LocalDateTime createdAt,
        String status,
        String antifraudResponse,
        String riskBureauResponse,
        String paymentGatewayResponse
) {
    public static TransactionResponse fromDomain(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getOperationNumber(),
                transaction.getChannel(),
                transaction.getCreditOriginator(),
                transaction.getAccountNumber(),
                transaction.getAmount(),
                transaction.getCurrency(),
                transaction.getCreatedAt(),
                transaction.getStatus().name(),
                transaction.getAntifraudResponse(),
                transaction.getRiskBureauResponse(),
                transaction.getPaymentGatewayResponse()
        );
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/controller/dto/ErrorResponse.java ===
package com.pragma.payments.infrastructure.controller.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
        String code,
        String message,
        LocalDateTime timestamp
) {
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/exception/GlobalErrorHandler.java ===
package com.pragma.payments.infrastructure.exception;

import com.pragma.payments.infrastructure.controller.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleValidationError(WebExchangeBindException ex) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        
        ErrorResponse errorResponse = new ErrorResponse(
                "VALIDATION_ERROR",
                errors,
                LocalDateTime.now()
        );
        
        return Mono.just(ResponseEntity.badRequest().body(errorResponse));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleResponseStatusException(ResponseStatusException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "HTTP_ERROR",
                ex.getReason() != null ? ex.getReason() : ex.getMessage(),
                LocalDateTime.now()
        );
        
        return Mono.just(ResponseEntity.status(ex.getStatusCode()).body(errorResponse));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleIllegalArgument(IllegalArgumentException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "BAD_REQUEST",
                ex.getMessage(),
                LocalDateTime.now()
        );
        
        return Mono.just(ResponseEntity.badRequest().body(errorResponse));
    }

    @ExceptionHandler(IllegalStateException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleIllegalState(IllegalStateException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "CONFLICT",
                ex.getMessage(),
                LocalDateTime.now()
        );
        
        return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ErrorResponse>> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "INTERNAL_ERROR",
                "An unexpected error occurred: " + ex.getMessage(),
                LocalDateTime.now()
        );
        
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse));
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleTransactionNotFound(TransactionNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "NOT_FOUND",
                ex.getMessage(),
                LocalDateTime.now()
        );
        
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse));
    }

    @ExceptionHandler(DuplicateTransactionException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleDuplicateTransaction(DuplicateTransactionException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "DUPLICATE_TRANSACTION",
                ex.getMessage(),
                LocalDateTime.now()
        );
        
        return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse));
    }

    @ExceptionHandler(ExternalServiceException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleExternalService(ExternalServiceException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "EXTERNAL_SERVICE_UNAVAILABLE",
                ex.getMessage(),
                LocalDateTime.now()
        );
        
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponse));
    }

    public static class TransactionNotFoundException extends RuntimeException {
        public TransactionNotFoundException(String message) {
            super(message);
        }
    }

    public static class DuplicateTransactionException extends RuntimeException {
        public DuplicateTransactionException(String message) {
            super(message);
        }
    }

    public static class ExternalServiceException extends RuntimeException {
        public ExternalServiceException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}

// === ARCHIVO: src/test/java/com/pragma/payments/application/TransactionServiceTest.java ===
package com.pragma.payments.application;

import com.pragma.payments.domain.model.Transaction;
import com.pragma.payments.domain.model.Transaction.TransactionStatus;
import com.pragma.payments.domain.port.TransactionRepositoryPort;
import com.pragma.payments.domain.port.AntifraudServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests para TransactionService - Caso de uso de procesamiento de transacciones")
class TransactionServiceTest {

    @Mock
    private TransactionRepositoryPort transactionRepositoryPort;

    @Mock
    private AntifraudServicePort antifraudServicePort;

    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        transactionService = new TransactionService(transactionRepositoryPort, antifraudServicePort);
    }

    @Test
    @DisplayName("Debe procesar correctamente una transacción válida con evaluación antifraude")
    void shouldProcessValidTransactionWithAntifraudEvaluation() {
        Transaction transaction = createSampleTransaction();
        Transaction savedTransaction = transaction.withStatus(TransactionStatus.APPROVED);

        when(transactionRepositoryPort.existsByIdempotencyKey(anyString()))
                .thenReturn(Mono.just(false));
        when(antifraudServicePort.evaluate(any(Transaction.class)))
                .thenReturn(Mono.just(transaction.withAntifraudResponse("APPROVED")));
        when(transactionRepositoryPort.save(any(Transaction.class)))
                .thenReturn(Mono.just(savedTransaction));

        StepVerifier.create(transactionService.processTransaction(transaction))
                .expectNextMatches(t -> t.getStatus() == TransactionStatus.APPROVED
                        && "APPROVED".equals(t.getAntifraudResponse()))
                .verifyComplete();

        verify(transactionRepositoryPort).existsByIdempotencyKey(transaction.getIdempotencyKey());
        verify(antifraudServicePort).evaluate(any(Transaction.class));
        verify(transactionRepositoryPort).save(any(Transaction.class));
    }

    @Test
    @DisplayName("Debe rechazar transacción idempotente dentro de la ventana de tiempo")
    void shouldRejectIdempotentTransactionWithinWindow() {
        Transaction transaction = createSampleTransaction();
        Transaction existingTransaction = transaction.withStatus(TransactionStatus.APPROVED);

        when(transactionRepositoryPort.existsByIdempotencyKey(anyString()))
                .thenReturn(Mono.just(true));
        when(transactionRepositoryPort.findByOperationNumberAndChannel(
                transaction.getOperationNumber(), transaction.getChannel()))
                .thenReturn(Mono.just(existingTransaction));

        StepVerifier.create(transactionService.processTransaction(transaction))
                .expectErrorMatches(throwable ->
                        throwable instanceof IllegalArgumentException &&
                        throwable.getMessage().contains("Idempotency"))
                .verify();

        verify(transactionRepositoryPort, never()).save(any(Transaction.class));
        verify(antifraudServicePort, never()).evaluate(any(Transaction.class));
    }

    @Test
    @DisplayName("Debe manejar transacción marcada como fraudulenta por el servicio antifraude")
    void shouldHandleFraudulentTransaction() {
        Transaction transaction = createSampleTransaction();
        Transaction fraudTransaction = transaction.withAntifraudResponse("REJECTED")
                .withStatus(TransactionStatus.REJECTED);

        when(transactionRepositoryPort.existsByIdempotencyKey(anyString()))
                .thenReturn(Mono.just(false));
        when(antifraudServicePort.evaluate(any(Transaction.class)))
                .thenReturn(Mono.just(fraudTransaction));
        when(transactionRepositoryPort.save(any(Transaction.class)))
                .thenReturn(Mono.just(fraudTransaction));

        StepVerifier.create(transactionService.processTransaction(transaction))
                .expectNextMatches(t -> t.getStatus() == TransactionStatus.REJECTED
                        && "REJECTED".equals(t.getAntifraudResponse()))
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe propagar error cuando el servicio antifraude falla")
    void shouldPropagateErrorWhenAntifraudServiceFails() {
        Transaction transaction = createSampleTransaction();

        when(transactionRepositoryPort.existsByIdempotencyKey(anyString()))
                .thenReturn(Mono.just(false));
        when(antifraudServicePort.evaluate(any(Transaction.class)))
                .thenReturn(Mono.error(new RuntimeException("Antifraud service unavailable")));

        StepVerifier.create(transactionService.processTransaction(transaction))
                .expectErrorMatches(throwable ->
                        throwable.getMessage().contains("Antifraud service unavailable"))
                .verify();

        verify(transactionRepositoryPort, never()).save(any(Transaction.class));
    }

    @Test
    @DisplayName("Debe validar que la transacción tenga datos requeridos antes del procesamiento")
    void shouldValidateRequiredTransactionData() {
        Transaction invalidTransaction = Transaction.builder()
                .id(UUID.randomUUID())
                .operationNumber(null)
                .channel("API")
                .amount(BigDecimal.valueOf(100))
                .currency("USD")
                .idempotencyKey(UUID.randomUUID().toString())
                .build();

        StepVerifier.create(transactionService.processTransaction(invalidTransaction))
                .expectError(IllegalArgumentException.class)
                .verify();
    }

    @Test
    @DisplayName("Debe buscar transacción existente por ID correctamente")
    void shouldFindTransactionById() {
        UUID transactionId = UUID.randomUUID();
        Transaction transaction = createSampleTransaction();

        when(transactionRepositoryPort.findById(transactionId))
                .thenReturn(Mono.just(transaction));

        StepVerifier.create(transactionService.findById(transactionId))
                .expectNext(transaction)
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe retornar vacío al buscar transacción inexistente")
    void shouldReturnEmptyWhenTransactionNotFound() {
        UUID transactionId = UUID.randomUUID();

        when(transactionRepositoryPort.findById(transactionId))
                .thenReturn(Mono.empty());

        StepVerifier.create(transactionService.findById(transactionId))
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe eliminar transacción por ID")
    void shouldDeleteTransactionById() {
        UUID transactionId = UUID.randomUUID();

        when(transactionRepositoryPort.deleteById(transactionId))
                .thenReturn(Mono.empty());

        StepVerifier.create(transactionService.deleteTransaction(transactionId))
                .verifyComplete();

        verify(transactionRepositoryPort).deleteById(transactionId);
    }

    private Transaction createSampleTransaction() {
        return Transaction.builder()
                .id(UUID.randomUUID())
                .operationNumber("OP-" + System.currentTimeMillis())
                .channel("API")
                .creditOriginator("CRED-001")
                .accountNumber("1234567890")
                .amount(BigDecimal.valueOf(1500.00))
                .currency("USD")
                .createdAt(LocalDateTime.now())
                .status(TransactionStatus.PENDING)
                .idempotencyKey(UUID.randomUUID().toString())
                .build();
    }
}

// === ARCHIVO: src/test/java/com/pragma/payments/infrastructure/adapter/TransactionR2dbcAdapterTest.java ===
package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.model.Transaction;
import com.pragma.payments.domain.model.Transaction.TransactionStatus;
import com.pragma.payments.domain.port.TransactionRepositoryPort;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.r2dbc.core.DatabaseClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Tests de integración para TransactionR2dbcAdapter con PostgreSQL")
class TransactionR2dbcAdapterTest {

    @Container
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(
            "postgres:16-alpine")
            .withDatabaseName("payments_test")
            .withUsername("test")
            .withPassword("test");

    private TransactionRepositoryPort adapter;
    private DatabaseClient databaseClient;

    @BeforeAll
    void setUp() {
        databaseClient = DatabaseClient.create(
                io.r2dbc.postgresql.PostgresqlConnectionConfiguration.builder()
                        .host(postgresContainer.getHost())
                        .port(postgresContainer.getMappedPort(5432))
                        .database("payments_test")
                        .username("test")
                        .password("test")
                        .build()
        );

        adapter = new TransactionR2dbcAdapter(databaseClient);

        createSchema();
    }

    private void createSchema() {
        databaseClient.sql("""
                CREATE TABLE IF NOT EXISTS transactions (
                    id UUID PRIMARY KEY,
                    operation_number VARCHAR(100) NOT NULL,
                    channel VARCHAR(50) NOT NULL,
                    credit_originator VARCHAR(100),
                    account_number VARCHAR(50),
                    amount DECIMAL(19,4) NOT NULL,
                    currency VARCHAR(3) NOT NULL,
                    created_at TIMESTAMP NOT NULL,
                    status VARCHAR(20) NOT NULL,
                    antifraud_response TEXT,
                    risk_bureau_response TEXT,
                    payment_gateway_response TEXT,
                    idempotency_key VARCHAR(100) UNIQUE
                )
                """).fetch().rowsUpdated().block();

        databaseClient.sql("""
                CREATE INDEX IF NOT EXISTS idx_transactions_operation_channel
                ON transactions(operation_number, channel)
                """).fetch().rowsUpdated().block();

        databaseClient.sql("""
                CREATE INDEX IF NOT EXISTS idx_transactions_created_at
                ON transactions(created_at)
                """).fetch().rowsUpdated().block();
    }

    @Test
    @DisplayName("Debe guardar una transacción correctamente en la base de datos")
    void shouldSaveTransactionSuccessfully() {
        Transaction transaction = createSampleTransaction();

        Mono<Transaction> saveResult = adapter.save(transaction);

        StepVerifier.create(saveResult)
                .assertThat(t -> {
                    assertThat(t.getId()).isEqualTo(transaction.getId());
                    assertThat(t.getOperationNumber()).isEqualTo(transaction.getOperationNumber());
                    assertThat(t.getChannel()).isEqualTo(transaction.getChannel());
                    assertThat(t.getAmount()).isEqualByComparingTo(transaction.getAmount());
                    assertThat(t.getStatus()).isEqualTo(transaction.getStatus());
                })
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe recuperar una transacción por su ID")
    void shouldFindTransactionById() {
        Transaction transaction = createSampleTransaction();
        Transaction saved = adapter.save(transaction).block();

        assertThat(saved).isNotNull();

        StepVerifier.create(adapter.findById(saved.getId()))
                .assertThat(t -> {
                    assertThat(t.getId()).isEqualTo(saved.getId());
                    assertThat(t.getOperationNumber()).isEqualTo(transaction.getOperationNumber());
                })
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe retornar vacío al buscar transacción inexistente")
    void shouldReturnEmptyForNonExistentTransaction() {
        UUID randomId = UUID.randomUUID();

        StepVerifier.create(adapter.findById(randomId))
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe verificar existencia de clave idempotente")
    void shouldCheckIdempotencyKeyExists() {
        Transaction transaction = createSampleTransaction();
        String idempotencyKey = transaction.getIdempotencyKey();

        StepVerifier.create(adapter.existsByIdempotencyKey(idempotencyKey))
                .expectNext(false)
                .verifyComplete();

        adapter.save(transaction).block();

        StepVerifier.create(adapter.existsByIdempotencyKey(idempotencyKey))
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe buscar transacción por número de operación y canal")
    void shouldFindByOperationNumberAndChannel() {
        Transaction transaction = createSampleTransaction();
        adapter.save(transaction).block();

        StepVerifier.create(adapter.findByOperationNumberAndChannel(
                transaction.getOperationNumber(),
                transaction.getChannel()))
                .assertThat(t -> {
                    assertThat(t.getOperationNumber()).isEqualTo(transaction.getOperationNumber());
                    assertThat(t.getChannel()).isEqualTo(transaction.getChannel());
                })
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe eliminar transacción por ID")
    void shouldDeleteTransactionById() {
        Transaction transaction = createSampleTransaction();
        Transaction saved = adapter.save(transaction).block();

        assertThat(saved).isNotNull();

        StepVerifier.create(adapter.deleteById(saved.getId()))
                .verifyComplete();

        StepVerifier.create(adapter.findById(saved.getId()))
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe contar transacciones creadas después de una fecha")
    void shouldCountTransactionsAfterDate() {
        LocalDateTime cutoff = LocalDateTime.now().minusHours(1);
        Transaction transaction = createSampleTransaction();
        adapter.save(transaction).block();

        StepVerifier.create(adapter.countByCreatedAtAfter(cutoff))
                .expectNext(1L)
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe guardar múltiples transacciones y mantener integridad")
    void shouldSaveMultipleTransactionsMaintainingIntegrity() {
        Transaction t1 = createSampleTransaction();
        Transaction t2 = createSampleTransaction();
        Transaction t3 = createSampleTransaction();

        adapter.save(t1).block();
        adapter.save(t2).block();
        adapter.save(t3).block();

        LocalDateTime cutoff = LocalDateTime.now().minusHours(1);

        StepVerifier.create(adapter.countByCreatedAtAfter(cutoff))
                .expectNext(3L)
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe rechazar clave idempotente duplicada")
    void shouldRejectDuplicateIdempotencyKey() {
        Transaction transaction = createSampleTransaction();
        String duplicateKey = transaction.getIdempotencyKey();

        adapter.save(transaction).block();

        Transaction duplicateTransaction = Transaction.builder()
                .id(UUID.randomUUID())
                .operationNumber("DIFFERENT-OP")
                .channel("API")
                .amount(BigDecimal.valueOf(200))
                .currency("USD")
                .createdAt(LocalDateTime.now())
                .status(TransactionStatus.PENDING)
                .idempotencyKey(duplicateKey)
                .build();

        StepVerifier.create(adapter.save(duplicateTransaction))
                .expectErrorMatches(t -> t.getMessage().contains("unique") ||
                        t.getMessage().contains("constraint"))
                .verify();
    }

    private Transaction createSampleTransaction() {
        return Transaction.builder()
                .id(UUID.randomUUID())
                .operationNumber("OP-" + UUID.randomUUID().toString().substring(0, 8))
                .channel("API")
                .creditOriginator("CRED-" + UUID.randomUUID().toString().substring(0, 4))
                .accountNumber("ACC" + System.currentTimeMillis())
                .amount(BigDecimal.valueOf(Math.random() * 10000))
                .currency("USD")
                .createdAt(LocalDateTime.now())
                .status(TransactionStatus.PENDING)
                .idempotencyKey("idem-" + UUID.randomUUID().toString())
                .build();
    }
}
```
