# <center> Snake Game (en desarrollo)</center>

<div style="text-align: justify;">

Este proyecto desarrolla una aplicación de escritorio para el juego del Snake. <br><br>
La aplicación está desarrollada en Java, usando Java Swing para la parte de la interfaz gráfica de usuario.

<center >

![Logo](/images/LogoSnake.png)

</center>

Es el proyecto final de mi primer curso del grado superior de DAW (mayo 2023). En ese momento, no tenía todos los conocimientos actuales y cambiaría gran parte del codigo. Pero en parte, estoy orgulloso del trabajo realizado, por eso lo muestro aquí.<br><br>
Más adelante reestructuraré la app y trabajare los apartados a mejorar.

## Requisitos del Proyecto

### Enunciado Inicial:
1. **Barreras Aleatorias:** Barreras de entre 1 y 3 casillas aparecerán aleatoriamente durante 5 segundos y luego desaparecerán.
2. **Tamaños y Velocidades Variables:** Al menos 2 tamaños de tablero y 2 velocidades de juego.
3. **Objetos Aleatorios:** Se generan objetos aleatorios cada x segundos (entre 5 y 20 seg) que suman 5 puntos al ser consumidos.
4. **Aumento de Velocidad:** La velocidad del juego aumenta cada X segundos (entre 20 y 40) durante 5 segundos.

### Funcionalidades propias implementadas:
- **Modos de Juego:** Diferentes modos para variar la jugabilidad, como la posibilidad de quitar muros, que la serpiente pase de un lado a otro del tablero en lugar de chocar, tuberías, entre otros.
- **Interfaz Intuitiva:** Permite al jugador alternar entre modos fácilmente.
- **Principios SOLID:** Se ha intentado seguir principios SOLID para una estructura de código modular.
- **Archivo de Récords:** Los datos de récords se archivan para persistir entre ejecuciones.
- **Pantalla Final:** Informa al usuario cuando el juego ha finalizado.
- **Estilos Personalizados:** Posibilidad de personalizar la apariencia del juego.
- **Conceptos Aprendidos en Clase:** Aplicación de conceptos aprendidos en clase.

## Elaboración del Proyecto

### Metodología
- Enfoque ágil con construcción progresiva.

### Desarrollo Inicial
- Estudio de juegos de Snake.
- Creación de estructura básica y movimiento de la serpiente.

### Principios SOLID y Estructura del Código
- Intento de seguir los principios SOLID.
- Esquemas y estructura de clases.

### Generación de Obstáculos (Muros)
- Generación aleatoria y eliminación de muros.
- Detección de ubicación ocupada.

### Menús y Finalización del Juego
- Desarrollo de menús y resolución de la app usando hilos y parámetros modificables por el usuario.

## Realización de Pruebas
No trabajo mucho los test, pues no tenia mucho conocimiento de ellos al  realizar la aplicación.

### Pruebas de Funcionalidad
- Pruebas durante el desarrollo para garantizar el correcto funcionamiento.

## Funcionamiento: 

**Captura del menu principal de la aplicación.** Donde se pueden seleccionar distintos modos y estilos usando botones interactivos que afectan al comportamiento de la app.
<center>

![Menu Principal](/images/MenuPrincipal.PNG)

</center>

**Captura de una partida.** En ella aparecen distintos muros que el jugador tendrá que esquivar y comidas que deberá capturar.
<center>

![Menu Principal](/images/Jugando.PNG)

</center>

 **Captura del menu final del juego.**Aparece cuando el jugador pierde y se construye en base a la puntuación obtenida y la máxima obtenida en el juego. 

<center>

![Menu Principal](/images/MenuFinal.PNG)

</center>


## Conclusión

Este proyecto representa mi primer trabajo en el desarrollo de aplicaciones y ha sido una experiencia enriquecedora. Me ha permitido aplicar (aunque no estén totalmente implementados) distintos conceptos para desarrollo de aplicaciones. 

</div>