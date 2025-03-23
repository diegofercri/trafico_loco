# Enunciado 18º Olimpiada Informática
## Simulación de tráfico con coches autónomos

Trabajas para una empresa de coches autónomos y te han encargado desarrollar, utilizando el paradigma de la orientación a objetos, un simulador de carreteras con cruces para implementar una nueva funcionalidad en la que los coches autónomos deberán cederse el paso unos a otros para evitar colisiones.

---

## Objetivo

El objetivo principal es implementar un simulador capaz de:
- Generar un mapa virtual de carreteras con diferentes resoluciones y mostrarlo por pantalla.
- Representar vehículos en movimiento siguiendo reglas de tráfico básicas.
- Simular cruces, resolver conflictos y su influencia en el flujo de vehículos.

---

## Especificación del sistema

Cuando el usuario inicia la aplicación, basada en una interfaz de texto o consola, se muestra un sencillo menú de bienvenida en el que solamente puede seleccionar dos opciones: **Iniciar nueva simulación** y **Salir**.

Si el usuario pulsa la tecla para “Iniciar nueva simulación”, la aplicación le guiará a través de una serie de pasos para configurar el escenario de ejecución:

1. **Tamaño del mapa de carreteras.** El usuario podrá seleccionar entre tres opciones:
   - Pequeño (20x20)
   - Mediano (30x30)
   - Grande (40x40)

2. **Número de carreteras.**
   El usuario indicará el número de carreteras a generar que siempre será par. La aplicación deberá generar N/2 carreteras verticales y N/2 horizontales con una longitud variable y aleatoria, asegurando la existencia de, al menos, N/2 cruces. Las posiciones de comienzo y fin de carretera serán también aleatorias, asegurando que no inicien o terminen en el mismo borde del mapa.

3. **Número de vehículos a simular.**
   El usuario introducirá un número V de vehículos a simular que no será nunca mayor al número de carreteras generadas. Cada coche tendrá una velocidad asignada de manera aleatoria:
   - Velocidad media: Se desplaza una casilla por ciclo de simulación.
   - Velocidad alta: Se desplaza dos casillas por ciclo de simulación.

Una vez completados estos pasos, la aplicación mostrará por pantalla el mapa de carreteras, aun sin los vehículos.

Ejemplo de representación de un posible mapa generado:

![Mapa de carreteras simulado 0](/img/mapa00.png)

En la parte inferior de la pantalla se mostrará un sencillo menú de control con las siguientes opciones:
- **Comenzar simulación.** Posiciona los vehículos en sus puntos de partida y empieza a simular su movimiento.
- **Pausar simulación.** Para la simulación en curso.
- **Reiniciar simulación.** Posiciona los vehículos a los puntos de partida para la simulación en curso y empieza a simular su movimiento.
- **Finalizar simulación.** Termina la simulación actual y vuelve al menú de bienvenida de la aplicación.

---

## Posición inicial de los vehículos en el mapa y ruta a seguir

Cada vez que se inicia una nueva simulación deben colocarse los vehículos en el mapa siguiendo las siguientes reglas:
- La posición inicial de un coche debe ser un punto de cualquiera de las carreteras generadas.
- Dos coches no podrán tener la misma posición de salida.
- Dos coches no podrán iniciar su ruta en la misma carretera.

Cada vehículo tendrá un destino asignado automáticamente por el simulador y se moverá en cada paso con el objetivo de alcanzarlo. Hay que tener en cuenta los siguientes requisitos a la hora de generar los puntos de destino en el mapa:
- La posición final de un coche debe ser un punto inicial o terminal, es decir, cualquiera de los extremos de cualquiera de las carreteras generadas.
- Dos coches no podrán tener el mismo destino durante la simulación.

Los vehículos se representarán con un número que lo identifique y su destino con ese mismo número entre corchete o paréntesis tal y como se muestra en el siguiente ejemplo:  

![Mapa de carreteras simulado 1](/img/mapa01.png)

---

## Movimiento de los coches y manejo de las colisiones en los cruces

Cuando el simulador se encuentra en funcionamiento, este actualiza la posición de todos los vehículos en cada paso. La dirección y sentido de ese movimiento se debe calcular con base en la posición actual y el destino.

Dependiendo de la velocidad del vehículo en cuestión, su posición podrá actualizarse en una o dos casillas, siempre en la misma dirección y sentido, permitiendo también el adelantamiento entre vehículos. En ningún caso podrá:
- Saltarse un cruce entre dos carreteras.
- Hacer un cambio de dirección en un cruce (consecuencia del anterior).

Un caso especial que tratar es la llegada de dos o más vehículos a un cruce. En este escenario se definen una serie de reglas para manejar los posibles conflictos que puedan surgir y evitar así que choquen:
- Solamente un coche puede ocupar la celda en la que intersecan dos carreteras.
- Si un coche se encuentra esperando en una carretera a que se le permita el paso en una intersección, ese coche debe esperar en la misma carretera, detrás del vehículo inmediatamente anterior sin ocupar en ningún caso la misma celda de mapa.

Se pueden dar situaciones en las que dos vehículos ocupen el mismo punto del mapa, pero solo si se cruzan sus respectivos caminos hacia sus destinos (es decir, tienen movimientos en direcciones opuestas). Para solventar esa situación, las carreteras se consideran de doble sentido.

A continuación, se muestran ejemplos de algunos de los escenarios descritos anteriormente.

- **Ejemplo 1:** Prioridad en cruce por continuación por misma carretera

![Mapa de carreteras simulado 2](/img/mapa02.png)

- **Ejemplo 2:** Espera en cruce detrás de otro vehículo que espera

![Mapa de carreteras simulado 3](/img/mapa03.png)

- **Ejemplo 3:** Prioridad en cruce por giro a la derecha

![Mapa de carreteras simulado 4](/img/mapa04.png)

- **Ejemplo 4:** Aunque el vehículo 1 avance a una velocidad de 2 casillas por paso de simulación, este tiene que pasar sí o sí por la casilla compartida que tienen las dos carreteras.

![Mapa de carreteras simulado 5](/img/mapa05.png)

- **Ejemplo 5:** Aunque el vehículo 1 avance a una velocidad de 2 casillas por paso de simulación, este tiene que esperar sí o sí antes del cruce.

![Mapa de carreteras simulado 6](/img/mapa06.png)

---

## Entregables

- Breve documentación con todas las decisiones de diseño y las explicaciones que consideréis necesarias para una mejor comprensión de la solución, así como un sencillo manual de uso y puesta en marcha.
- Código fuente en Java, C++ o C# necesario para reproducir el ejercicio. Y también cualquier instrucción o particularidad que necesitemos saber para generar el ejecutable correspondiente.

---

# Documentación del Proyecto

## Introducción
Este proyecto implementa un simulador de tráfico basado en el paradigma de orientación a objetos. El objetivo es simular un mapa virtual de carreteras con cruces y vehículos autónomos que se mueven siguiendo reglas básicas de tráfico para evitar colisiones.

El simulador permite:
- Generar un mapa de carreteras con diferentes tamaños.
- Representar vehículos en movimiento hacia destinos específicos.
- Simular cruces y resolver conflictos para garantizar un flujo de tráfico seguro.

---

## Estructura del Código

### 1. Clase `Modelo`
- **Propósito:** Gestiona la generación de carreteras, cruces y la representación visual del mapa.
- **Funcionalidades principales:**
   - Genera carreteras alternando entre direcciones verticales y horizontales.
   - Detecta y almacena los cruces entre carreteras.
   - Proporciona métodos para "pintar" las carreteras en una matriz visual (`pintarCarreteras`).
- **Propiedades:**
   - `carreterasClass`: Array que almacena las carreteras generadas.
   - `cruces`: Lista que almacena los cruces generados.
   - `tamano`: Tamaño del mapa (20x20, 30x30, etc.).
   - `pintar`: Matriz utilizada para representar visualmente el mapa.
- **Métodos principales:**
   - `generarCarreteras()`: Genera las carreteras y los cruces en el mapa.
   - `pintarCarreteras()`: Imprime el mapa en la consola marcando las posiciones ocupadas por carreteras.
- **Observaciones:**
   - La matriz `pintar` tiene un tamaño fijo (`15x15`), lo que puede causar problemas si el tamaño del mapa es mayor.
   - La lógica para evitar cruces duplicados podría mejorarse.

---

### 2. Clase `Carretera`
- **Propósito:** Representa una carretera en el mapa, con sus posiciones, dirección y cruces asociados.
- **Funcionalidades principales:**
   - Almacena las posiciones inicial, final e intermedias de la carretera.
   - Calcula las posiciones intermedias basándose en la dirección (vertical u horizontal).
- **Propiedades:**
   - `id`: Identificador único de la carretera.
   - `posiciones`: Array que almacena las posiciones que conforman la carretera.
   - `cruces`: Array que almacena los cruces asociados a la carretera.
   - `direccion`: Dirección de la carretera (`VERTICAL` o `HORIZONTAL`).
- **Métodos principales:**
   - `setPosiciones(Position posicionInicial, Position posicionFinal)`: Genera las posiciones intermedias de la carretera.
- **Observaciones:**
   - Falta validación para asegurarse de que las posiciones inicial y final sean coherentes con la dirección.

---

### 3. Clase `Cruce`
- **Propósito:** Representa un punto de intersección entre dos carreteras.
- **Funcionalidades principales:**
   - Almacena la posición del cruce.
   - Proporciona métodos para acceder a las coordenadas (`getX`, `getY`) y a la posición completa (`getPosicion`).
- **Propiedades:**
   - `posicion`: Posición del cruce en el mapa.
- **Observaciones:**
   - No incluye lógica para gestionar prioridades entre vehículos en el cruce.

---

### 4. Clase `Coche`
- **Propósito:** Representa un vehículo que se mueve por las carreteras hacia un destino.
- **Funcionalidades principales:**
   - Almacena la posición actual, la velocidad y el identificador del coche.
   - Proporciona métodos para acceder y modificar sus propiedades (`id`, `position`, `velocidad`).
- **Propiedades:**
   - `id`: Identificador único del coche.
   - `position`: Posición actual del coche en el mapa.
   - `velocidad`: Velocidad del coche (número de casillas que se mueve por ciclo de simulación).
- **Observaciones:**
   - No incluye lógica para moverse ni para calcular su siguiente posición basándose en su destino.
   - Falta validación para asegurarse de que la velocidad sea válida (1 o 2).

---

### 5. Clase `Position`
- **Propósito:** Representa una posición en el mapa mediante coordenadas `(x, y)`.
- **Funcionalidades principales:**
   - Almacena las coordenadas `x` e `y`.
   - Proporciona métodos para acceder y modificar las coordenadas (`getX`, `setX`, `getY`, `setY`).
   - Sobrescribe el método `toString` para facilitar la impresión de posiciones.
   - Sobrescribe el método `equals` para comparar posiciones.
- **Observaciones:**
   - Es una clase simple pero fundamental para representar ubicaciones en el mapa.

---

### 6. Enumerado `Direccion`
- **Propósito:** Define las posibles direcciones de una carretera (`VERTICAL` o `HORIZONTAL`).
- **Valores:**
   - `VERTICAL`: Indica que la carretera se extiende verticalmente.
   - `HORIZONTAL`: Indica que la carretera se extiende horizontalmente.
- **Observaciones:**
   - Proporciona valores predefinidos para garantizar consistencia en la dirección de las carreteras.

---

### 7. Clase `Simulador`
- **Propósito:** Actúa como punto de entrada para la simulación.
- **Funcionalidades principales:**
   - Crea un modelo con un tamaño de mapa específico.
   - Genera las carreteras y los cruces.
   - Pinta las carreteras en la consola.
- **Método principal:**
   - `main(String[] args)`: Inicia la simulación creando un modelo, generando carreteras y pintando el mapa.
- **Observaciones:**
   - El tamaño del mapa está codificado directamente (`10x10`), lo que limita la flexibilidad.
   - No incluye interacción con el usuario ni opciones para configurar el escenario.

---

## Interacción entre las Clases

1. **Generación del mapa:**
   - La clase `Simulador` crea un objeto `Modelo` y llama a sus métodos para generar carreteras y pintarlas.
   - La clase `Modelo` utiliza objetos de tipo `Carretera` para representar las carreteras y objetos de tipo `Cruce` para representar los puntos de intersección.

2. **Representación visual:**
   - La matriz `pintar` en la clase `Modelo` se utiliza para representar visualmente el mapa en la consola. Las posiciones ocupadas por carreteras se marcan con el símbolo `#`.

3. **Movimiento de vehículos (pendiente):**
   - La clase `Coche` debería interactuar con las carreteras y los cruces para simular el movimiento de los vehículos. Esto aún no está implementado.

---

## TODOs

1. **Generación de carreteras y cruces:**
   - Validar que las carreteras no se superpongan ni ocupen las mismas filas/columnas.
   - Mejorar la lógica para detectar y gestionar los cruces.

2. **Movimiento de vehículos:**
   - Implementar lógica para mover los coches hacia sus destinos.
   - Gestionar prioridades en los cruces para evitar colisiones.

3. **Interacción con el usuario:**
   - Agregar un menú interactivo para permitir al usuario configurar el tamaño del mapa, el número de carreteras y el número de vehículos.

4. **Visualización avanzada:**
   - Usar una interfaz gráfica (por ejemplo, JavaFX o Swing) para representar el mapa y el movimiento de los vehículos.

5. **Validaciones adicionales:**
   - Asegurarse de que las posiciones inicial y final de las carreteras sean coherentes con su dirección.
   - Validar que los vehículos no se salgan de las carreteras ni ocupen la misma posición simultáneamente.
