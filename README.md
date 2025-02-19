# gabrieladobre.github.io

-APLICACIÓN CONSULTA-

¿Para qué sirve esta aplicación?

A partir de una Base de Datos SQL, que almacena artículos según el país de origen, precio y fecha de fabricación, podemos consultar dichos artículos tanto por sección, como por país de origen.
El buscador nos devolverá las coincidencias que encuentre según los parámetros introducidos. Por ejemplo, deseo saber qué artículos hay en China en la sección de cerámica.

#BBDD de los artículos:

![3](https://github.com/user-attachments/assets/f8597efa-286a-453e-92ed-a412aefcc824)

![2](https://github.com/user-attachments/assets/4dfd8c66-e871-4978-8d5c-9203adef87d1)

#Ejemplo de búsquedas:

1. En este caso, podemos ver todos los artículos, ya que sólo hemos filtrado por sección:
   
![Captura de pantalla 2025-02-19 120413](https://github.com/user-attachments/assets/cc578e46-61e1-4e0c-9765-620a4c477fc6)

2. En este segundo ejemplo, hemos utilizado ambos filtros, tanto por país como sección:
   
![Captura de pantalla 2025-02-19 121317](https://github.com/user-attachments/assets/87277f8b-bae3-4b9c-a5ba-163dd0247ee4)
![Captura de pantalla 2025-02-19 121413](https://github.com/user-attachments/assets/c5e9ae31-c7b5-48a2-b4e1-896c1dae478b)


¿Cómo se ha creado esta aplicación?

·Consta de dos clases, una de ellas es la main class -AplicacionConsulta- y la clase en la que se desarrollan todos los métodos -MarcoAplicacionConsulta-.
· Esta aplicación está creada con Java 8 en entorno de desarrollo Eclipse. He utilizado Javax Swing para crear los Panels y los Frames, así como jdbc para la conexión con la BBDD. Se han creado dos menús desplegables, un text field y un botón (llamado Consulta, el que nos devuelve los resultados de la búsqueda).
· Para la creación de la Base de Datos, he utilizado MySQL Workbench:

![Captura de pantalla 2025-02-19 121940](https://github.com/user-attachments/assets/3672479b-215e-4822-b3aa-be93c335e1b6)

· Para realizar la conexión de Eclipse con la Base de datos, en local host, he utilizado Xampp Control Panel y he alojado la Base de Datos en phpMyAdmin.


En este mismo proyecto pueden verse ambas clases con su respectivo código.
