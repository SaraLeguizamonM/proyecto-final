# 🚩 Juego Buscaminas - JAVA 
Desarrollo del juego clasico que se ejecuta en la terminal. Proyecto final del curso Pensamiento Computacional.

--- 
## Integrantes:
- Juan Andres Florez Montes
- Sara Nicolle Leguizamón Moreno
- Andres David Sojo Ochoa

---
## Estructura del proyecto
- [src/celdas/](src/celdas/) Celda, CeldaMina, CeldaSegura
- [src/niveles/](src/niveles/)  Clases de dificultad del juego
- [src/juego/](src/juego/) Tablero, Juego, Consola, Main
- [src/historial/](src/historial/) Partida, HistorialPartida
- [diseno.md](diseno.md) decisiones de diseño y diagrama UML
- [diagrama.drawio.png](diagrama.drawio.png) diagrama de clases UML

---
## Como copilarlo y ejecutarlo
**Desde VS Code:**
1. Clonar o descargar el repositorio
2. Abrir la carpeta en Visual Studio Code
3. Abrir `src/juego/Main.java`
4. Clic en `Run` sobre el método `main`

## Como jugar
Al ejecutar el programa aparece el menú principal:

|  Opción | Descripción |
| --- | --- |
| `1` | Principiante 9x9 con 10 minas |
| `2` | Intermedio 16x16 con 40 minas |
| `3` | Experto 16x30 con 99 minas |
| `4` | Personalizado |
| `5` | Ver historial (score) |
| `6` | Buscar partida por nivel |
| `0` | Salir |

Durante la partida:

|  Opción | Descripción |
| --- | --- |
| `1` | Descubrir celda |
| `2` | Poner o quitar bandera  |
| `0` | Abandonar partida |


