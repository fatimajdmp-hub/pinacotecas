## Descripción del proyecto
Este proyecto es una aplicación de escritorio desarrollada en Java diseñada para la gestión de pinacotecas. El sistema permite administrar información del mundo del arte, incluyendo artistas (pintores), sus obras (cuadros), centros artísticas (escuelas), patrocinadores (mecenas) y los museos donde se exhiben.
La aplicación utiliza una arquitectura robusta basada en el DAO (Data Access Object) y conectividad JDBC para garantizar una persistencia de datos eficiente y organizada en una base de datos SQLite.

## Requisitos previos
Para ejecutar este proyecto, asegúrate de tener instalado el siguiente software:
    - Java JDK: Versión 17 o superior.
    - Gestor de Dependencias: Apache Maven 3.8 o superior.
    - Base de Datos: SQLite (incluida en el proyecto como archivo .db).
    - IDE Recomendado: IntelliJ IDEA (configurado con el JDK correcto, se recomienda JDK 25).

## Configuración de la base de datos
Pasos detallados para:
1. Crear la base de datos.
2. Ejecutar el script SQL de creación de tablas.
3. Cargar los datos iniciales de prueba (si los hay).
4. Configurar la cadena de conexión en el proyecto.

## Clonar el repositorio
Para obtener una copia local del proyecto, ejecuta los siguientes comandos en tu terminal:

git clone https://github.com/[usuario]/pinacotecas.git
cd pinacoteca

## Compilación y ejecución
Para compilar el proyecto y ejecutarlo, puedes hacerlo mediante la línea de comandos o IDE.

Desde la línea de comandos (Maven):
    - Compilar el proyecto:
            mvn clean compile
    - Ejecutar la aplicación:
            mvn exec:java -Dexec.mainClass="app.Main"

Desde IntelliJ IDEA:
    - Importa el proyecto como un proyecto Maven (selecciona el archivo pom.xml).
    - Asegúrate de que el SDK del proyecto esté configurado en File > Project Structure.
    - Localiza la clase Main.java en src/main/java/app/ y haz clic derecho en Run 'Main.main()'.

## Estructura del proyecto
El proyecto sigue el estándar de Maven para la organización de archivos:

C:.
│   .gitignore                                    # Archivo para excluir archivos temporales de Git. 
│   identifier.sqlite                             # Archivo de metadatos de la base de datos 
│   Pinacoteca.db                                 # Base de datos SQLite del proyecto. 
│   pom.xml                                       # Archivo de configuración de Maven (dependencias) 
│   README.md                                     # Documentación general del proyecto. 
│
├───.idea                                         # Configuraciones del entorno de desarrollo (IntelliJ). 
│   │   .gitignore                                # Archivo para excluir archivos temporales de Git.               
│   │   dataSources.xml                           # Configuración de las conexiones a bases de datos. 
│   │   data_source_mapping.xml                   # Reglas de correspondencia entre dos estructuras de datos diferentes.
│   │   encodings.xml                             # Configuración de la codificación de caracteres (UTF-8). 
│   │   misc.xml                                  # Información técnica del proyecto y versión del JDK. 
│   │   vcs.xml                                   # Integración con el sistema de control de versiones. 
│   │
│   ├───codeStyles                                # Estilo de código del proyecto. 
│   │       codeStyleConfig.xml
│   │       Project.xml
│
└───src
    └───main
        └───java  
            ├───app                               # CAPA DE CONTROL DE CLASES
            │       Main.java                     # Punto de entrada principal. 
            │       MenuCuadro.java               # Interfaz de usuario para cuadros. 
            │       MenuEscuela.java              # Interfaz de usuario para escuelas. 
            │       MenuMecena.java               # Interfaz de usuario para mecenas. 
            │       MenuPinacotecas.java          # Interfaz de usuario para museos. 
            │       MenuPintor.java               # Interfaz de usuario para pintores. 
            │
            ├───conexion                          # CAPA DE CONEXIÓN
            │       ConexionBD.java               # Gestión del driver JDBC y conexión SQLite. 
            │
            ├───dao                               # CAPA DE ACCESO A DATOS 
            │       CuadradoDAO.java              # Interfaz para operaciones de Cuadros. 
            │       CuadradoDaoImpl.java          # Implementación SQL y creación de tabla Cuadros. 
            │       EscuelaDao.java               # Interfaz para operaciones de Escuelas. 
            │       EscuelaDaoImpl.java           # Implementación SQL y creación de tabla Escuelas. 
            │       MecenaDao.java                # Interfaz para operaciones de Mecenas. 
            │       MecenaDaoImpl.java            # Implementación SQL y creación de tabla Mecenas. 
            │       PinacotecaDao.java            # Interfaz para operaciones de Pinacotecas. 
            │       PinacotecaDaoImpl.java        # Implementación SQL y creación de tabla Pinacotecas. 
            │       PintorDao.java                # Interfaz para operaciones de Pintores. 
            │       PintorDaoImpl.java            # Implementación SQL y creación de tabla Pintores. 
            │
            └───modelo                            # CAPA DE ENTIDADES (POJO) 
                    Cuadrado.java                 # Representación del Cuadro. 
                    Escuela.java                  # Representación de la Escuela. 
                    Mecena.java                   # Representación del Mecena. 
                    Pinacoteca.java               # Representación de la Pinacoteca. 
                    Pintor.java                   # Representación del Pintor. 

## Equipo
El proyecto ha sido desarrollado siguiendo una estrategia de colaboración por roles:
   Integrantes                  Rol                                       Tareas Principales
Fátima Vázquez            Jefe de Proyecto                  Coordinación, gestión de plazos y supervisión.
Alejandro Andrade         Diseñador de BD                   Modelo E-R, modelo lógico y restricciones de integridad.
Juan Francisco Garrido    Calidad y Doc.                    Memoria, diagramas UML y mantenimiento del repositorio.
Hugo Rodríguez            Desarrollador Interfaz            Implementación de menús y lógica de interacción (Scanner).
Diego Manuel Carrasco     Diseñador Backend                 Lógica de negocio, entidades y capa de acceso a datos (DAO).