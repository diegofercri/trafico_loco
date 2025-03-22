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

# Resolución del problema

 