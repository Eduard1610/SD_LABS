import socket
import datetime

# funcion para iniciar el servidor de reloj
def iniciar_servidor():
    
    s = socket.socket()
    print("Socket creado exitosamente!")

    puerto = 8000
    s.bind(('', puerto))

    # escuchar las solicitudes
    s.listen(5)
    print("Socket escuchando en el puerto ", puerto)

    # Ejecucion continua del servidor
    while True:
        # conexion con el cliente
        conexion, direccion = s.accept()
        print("Conectado con ", direccion)

        # enviar la hora al cliente
        conexion.send(str(datetime.datetime.now()).encode())

        # cerrar la conexion
        conexion.close()

# funcion principal
def main():
    iniciar_servidor()