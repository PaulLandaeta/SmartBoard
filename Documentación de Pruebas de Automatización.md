1. Introducción y Alcance de las Pruebas
1.1 Objetivo de la Documentación
Este documento tiene como finalidad detallar la configuración y preparación necesarias para la ejecución de pruebas automatizadas en el proyecto SmartBoard sobre dispositivos Android.
El objetivo principal es estandarizar el entorno de trabajo y definir los pasos básicos para que cualquier miembro del equipo pueda:
Instalar y configurar correctamente las herramientas de automatización.


Levantar el entorno de pruebas con Appium y un emulador Android.


Entender la arquitectura de pruebas utilizada.


1.2 Tecnologías Utilizadas
Android Studio: IDE principal del proyecto, utilizado para la gestión del código y de los emuladores Android.


Java (SDK 11 o superior): Lenguaje base del desarrollo y de las pruebas.


Gradle: Sistema de construcción y gestión de dependencias del proyecto.


Appium 2.x: Framework de automatización móvil.


Appium Inspector: Herramienta para inspección de elementos y configuración de Desired Capabilities.


Page Object Model (POM): Patrón de diseño adoptado en la estructura de las pruebas.


1.3 Patrón de Diseño (Page Object Model - POM)
El patrón Page Object Model (POM) se implementa para separar la lógica de prueba de la lógica de la interfaz de usuario.
Cada pantalla de la aplicación cuenta con una clase que representa sus elementos y acciones.


Los casos de prueba llaman a estas clases para ejecutar escenarios de manera reutilizable.


Esto mejora la mantenibilidad y facilita la adaptación a cambios en la UI.



2. Preparación y Configuración del Entorno
2.1 Requisitos del Sistema
Sistema Operativo: Windows 10/11 (64 bits).


Java JDK 11 o superior (configurado en variables de entorno).


Android Studio con:


SDK de Android instalado.


AVD Manager con al menos un emulador configurado (ejemplo: Pixel 4 API 30).


Node.js 16 o superior (para instalar Appium).


Git (para clonar el repositorio).


2.2 Configuración del Entorno
Instalar Node.js
 Descargar desde nodejs.org e instalar.


Instalar Appium y Appium Doctor
 Ejecutar en terminal:

 npm install -g appium
npm install -g appium-doctor
appium-doctor --android


Instalar Driver de Appium para Android

 appium driver install uiautomator2
appium driver list   # Para verificar


Instalar Appium Inspector
 Descargar desde: Appium Inspector Releases
 Se utiliza para definir y probar Desired Capabilities y explorar elementos de la app en el emulador.


Configurar Android Studio


Abrir Android Studio e instalar el SDK de Android (versión 11 o superior).


Crear un emulador desde AVD Manager (ejemplo: Pixel 4 API 30).


Probar la conexión con:

 adb devices


Variables de entorno
 Configurar en el sistema:


JAVA_HOME → ruta al JDK.


ANDROID_HOME → ruta al SDK de Android.
 Agregar al PATH:


%ANDROID_HOME%\platform-tools
%ANDROID_HOME%\tools
Verificar Instalación


Levantar Appium Server:

 appium


Iniciar emulador Android desde Android Studio.


Validar que el dispositivo está disponible:

 adb devices


2.3 Clonación del Proyecto
Hacer fork del repositorio base: PaulLandaeta/SmartBoard


Clonar tu fork en la máquina local.


Abrir el proyecto en Android Studio.


Sincronizar dependencias con Gradle.




   
