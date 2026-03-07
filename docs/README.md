# Proyecto 1 Trimestre 2 - Blackjack

## Explicación del juego

Nuestro programa difiere un poco de las instrucciones originales ya que hemos querido hacer una simulación más "realista" del juego original, para empezar estos son los cambios respecto a las instrucciones:

- Baraja Estándar de 52 cartas : En nuestro juego se puede elegir si jugar con 52 o 104 cartas, ya que normalmente se juega con 2 barajas al blackjack.
- Elegir 1 o 2 cartas por jugador : Si bien el poder elegir es sencillo de implementar, hemos preferido mantenernos en robar 2 cartas de inicio siempre, siguiendo el juego original, ya que en caso de empezar con 1 siempre se robaría ya que es imposible pasarte de 21 con 2 cartas.
- 2 jugadores : En nuestro programa al empezar la partida al usuario se le preguntarán cuantos jugadores van a jugar (1-4), luego si quiere un croupier y por último se le preguntará (si no ha elegido 4 jugadores) cuantas Cpus quiere, esto está pensado así para asemejarse a una mesa "real" donde suele haber un máximo de plazas por mesa sin contar al croupier, por tanto nuestro programa es funcional para 1-4 entre jugadores y Cpu y un croupier extra si se quiere.
- Imprimir las rondas : Si bien era una función sencilla de implementar, nos parecía un poco confuso para el usuario el ver las rondas ya que al final el número de la ronda no está aportando nada al usuario.
  - Las cartas son siempre visibles salvo la primera del croupier en caso de hacerlo, esto es ya que de nuevo, hemos querido ajustarnos al juego original, aunque con un cambio para evitar posibles "trampas", las cartas solo se mostrarán una vez todas las entidades hayan tomado una decisión, cuando un jugador robe una carta no podrá saber cuál ha sido hasta que todas las entidades hayan terminado su turno.

Respecto a las mejoras respecto al planteamiento original básico:

- Hemos añadido la capacidad de jugar con un Croupier que jugará siempre cuando todo el resto de entidades se hayan pasado plantado, mostrando hasta antes de ese punto solo su segunda carta y, sin mostrar su puntuación.
- Hemos añadido que se puedan jugar de 1 a 4 jugadores.
- Hemos añadido que se puedan añadir Cpus respecto al número de jugadores (Si solo hay un jugador habrá que añadirse una, si hay 4 entonces no se podrá añadir ninguna).
- Los palos de las cartas se imprimen con colores.

Miembros del proyecto: Aidan Guzmán Postigo y Rocío Lobato Monferrer.
