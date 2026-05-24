Descomposición del problema

Para el proyecto final, el cual corresponde a un Buscaminas, decidimos que el tablero sería el punto de conexión de todas las clases del juego, por así decirlo, es el cimiento y parte principal de nuestro buscaminas. Decidimos que el tablero fuera el encargado de las celdas, repartir las minas y calcular la proximidad de estas con referencia a cada celda. Optamos por hacerlo así, ya que, si hubiésemos tenido todo el código disperso en otras clases, cabía la posibilidad de que se generaran problemas graves de funcionamiento, orden y facilidad de lectura. Dejando de lado la clase Tablero, las clases Juego y Consola las enfocamos únicamente en recibir los datos que ingrese el usuario (opciones juego y coordenadas de celdas), y mostrar el juego y sus diferentes modificaciones en tiempo real en la pantalla. Esta construcción centrada en la clase Tablero nos permitió que, cuando hiciéramos cambios o buscáramos errores a corregir, supiéramos exactamente dónde estaba el problema sin tener que perder el tiempo buscando por todo el proyecto.



Para empezar con el diseño del juego, requeríamos una descomposición clara para evitar que el código se volviera un caos. Así que separamos la interfaz (la parte visual que pide al usuario los datos - opciones) del funcionamiento del juego (la lógica que se encarga de calcular las minas y las minas adyacentes a cada celda, también entra la funcionalidad del estilo cascada), y logramos un sistema compacto y ordenado. Cada clase tiene una responsabilidad única, evitando errores y el desorden del código: el Main arranca la ejecución del juego, la clase Juego coordina los turnos, Tablero guarda el estado de la cuadrícula con minas luego de cada turno, y Celda representa el formato que compone todo el tablero y muestra la información (si colocaste bandera, número de minas adyacentes, las minas al perder, etc.). Esta separación nos facilitó el poder detectar cualquier error en el funcionamiento interno, sin afectar cómo el usuario veía la consola.



Decisiones de diseño orientado a objetos

Al programar las celdas, usamos una clase abstracta Celda. Determinamos hacerlo así, ya que todas las celdas, independientemente de su tipo, necesitan tener los mismos estados iniciales (si están ocultas, si tienen bandera o su posición en el tablero). Sin embargo, el funcionamiento final depende totalmente de si contienen una mina o no (CeldaSegura o CeldaMina).

Herencia vs. Composición: Optamos por la herencia para separar CeldaMina y CeldaSegura porque ambas son una celda (es decir, vienen de la misma clase madre “Celda”). Esto nos permitió añadir y usar el polimorfismo, haciendo que el tablero no necesitara saber qué tipo de celda está manejando, simplemente llama al método getSimbolo() y cada celda hija (ya segura o con mina) muestra el símbolo con que lo programamos (ya sea un número, una P de la bandera o las minas en caso de perder). Este polimorfismo nos ahorró el hecho de duplicar parte del código, gracias que no teníamos que definir nuevamente los atributos básicos en cada subclase.



Por otro lado, preferimos la composición para conectar el Tablero con las Celdas, ya que, como es lógico, la cuadrícula del tablero está compuesta por celdas (que pueden ser seguras o pueden contener una mina). El tablero contiene una matriz de celdas; esta relación de composición es más segura, más sencilla y tiene más sentido que intentar que una celda herede del tablero, ya que una celda es simplemente una celda, no un tablero (el tablero es una composición de una cantidad determinada de celdas). Esta separación evitó que las celdas dependieran innecesariamente de otra clase, manteniendo el código ordenado y con sentido lógico.

También, el uso de una clase abstracta nos da la capacidad de hacer futuras actualizaciones. Si, por ejemplo, decidiéramos añadir un tipo de celda que contenga un multiplicador de puntos o un bono temporal, solo tendríamos que hacer una subclase que extienda de Celda sin modificar ni una sola línea del código que ya funciona en la clase Tablero.



Elección de estructuras de datos

Para el tablero, usamos una matriz bidimensional. Pensamos inicialmente en hacer una lista de listas, pero la matriz nos pareció forma mucho más directa y sencilla de manejar las coordenadas (fila, columna), facilitando mucho la jugabilidad del usuario, y el cálculo de los vecinos adyacentes, ya que, solo necesitamos mirar las celdas cercanas (fila ±1 o -1, y columna ±1 o -1) sin tener que realizar conversiones o búsquedas complejas en listas dinámicas.



En cuanto al historial, usamos un ArrayList. Escogimos esta forma porque necesitamos agregar partidas y guardarlas en la memoria, y ArrayList permite agregar elementos (en este caso son las partidas y sus datos) de manera fácil y rápida. Además, al tener todos los datos de las partidas en una lista, pudimos implementar bubbleSort para organizar las partidas por tiempo, y binarySearch para buscar registros de partidas específicas por nivel de dificultad. Estos métodos son necesarios para mejorar la experiencia del usuario. Al trabajar con un arrayList (arreglo dinámico), podemos ordenar los datos de las partidas y manipularlos sin tener que agregar todos los datos desde cero cada vez que alguien consulta su rendimiento histórico.

La elección de ArrayList también es porque el número de partidas no es fijo. Si usáramos un arreglo estático, tendríamos que definir un límite que podría ser muy pequeño, o tan grande que desperdicie la memoria, además, podría causar errores de funcionamiento y una mala experiencia del usuario. Con la lista dinámica, el juego guarda los datos según el uso del jugador.



Flujo principal del juego

El funcionamiento del juego comienza cuando el usuario elige la dificultad, lo cual inicia el funcionamiento del código del Tablero con los parámetros correspondientes (filas, columnas y número de minas). El tablero se encarga de instanciar las celdas y ubicar las minas de forma aleatoria mediante un Random, que cuenta con una verificación que evita que se cree una mina donde ya había mina. Una vez que el juego está activo, el funcionamiento y resultado depende de las opciones que el usuario ingresa por consola (ya sea descubrir una celda o cambiarBandera).

La lógica de descubrirCascada es uno de los apartados que más tiempo llevo durante el proceso de programación del juego. Para verificar los ocho vecinos de cada celda usamos un arreglo con los valores “quemados” (por así decirlo) del +1 y -1, y si ellos también están vacíos (es decir, que la cantidad de minas adyacentes es cero) seguir abriendo. Esta funcionalidad es una de las que mejora y hace más sencilla la jugabilidad del usuario en un Buscaminas. Tuvimos mucho cuidado con este arreglo “quemado”, asegurándonos de que el ciclo se detenga al encontrar celdas que ya fueron descubiertas, celdas con banderas o celdas que tienen una mina adyacente, evitando así que el programa entrara en bucles infinitos u otro tipo de errores.

El flujo termina cuando se cumplen las condiciones de victoria o derrota. La victoria la programamos mediante un contador (descubiertasCont) que se compara con el número total de celdas seguras; si el contador llega al total de celdas seguras, el juego termina. La derrota se da inmediatamente al descubrir una CeldaMina, lo cual ejecuta el método revelarTodo() para que el usuario vea dónde estaban las minas.

Este flujo del juego ayuda a que cada turno sea funcional y sin errores. Al separar la entrada de datos de la lógica de funcionamiento del juego, evitamos que un comando mal escrito por parte del usuario dañe el tablero o cause estados no válidos en la partida.

