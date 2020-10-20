# EDD PY2 SafeWay 2S2020

SafeWay es una aplicación que le permite al usuario poder viajar de un punto A hacia un
punto B de la manera más rápida y eficaz, el programa es capaz de cargar puntos a travez de la API de Google Maps.

## Implementación
Es necesario poder guardar la información tanto como de los usuarios, como de pilotos y facturas, para ello se implementó un arbol b para cada tipo de información

## Arbol B

## Tabla Hash
Para el catálogo de lugares se implemento una tabla hash para almacenar dichos lugares por su clave, con un direccionamiento abierto y resolución de colisiones por exploración cuadrática.

Para la optimización del espacio la tabla hash se redimensiona segun su porcentaje de uso, inicialente cuenta con 10 lugares, al llenarse un 75% la tabla crece 270% de su capacidad actual.
- Estructura:
- - id_lugar (clave) [int]
- - nombre [String]
- - latitud [double]
- - longitud [double]

## Block Chain